package net.fdgames.ek.android.lan;

import com.badlogic.gdx.Gdx;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Random;
import net.fdgames.GameEntities.Character;
import net.fdgames.GameEntities.MapActor;
import net.fdgames.GameEntities.Final.NPC;
import net.fdgames.GameEntities.Final.Player;
import net.fdgames.GameLevel.GameLevel;
import net.fdgames.GameWorld.GameData;
import net.fdgames.Helpers.GameConsole;

/**
 * Kill rewards split by damage share between players (deobf/KILL_REWARDS_SPEC.md). A "side" is a
 * player plus their companions and summons. The host settles every kill once.
 */
public final class EkKill {
    private EkKill() {}

    static final long SETTLE_DELAY_MS = 1500L;
    private static final Random RNG = new Random();

    // ---- ledger (game thread): monster key -> side name -> damage -------------------------------------
    private static final LinkedHashMap<String, HashMap<String, Integer>> LEDGER =
            new LinkedHashMap<String, HashMap<String, Integer>>();
    private static String ledgerLevel;

    // ---- killer device (game thread): the reward block's output, sent right after the death -----------
    static final class Pending {
        String key;
        String level;
        int x;
        int y;
        int xp;
        int gold;
        final ArrayList<Integer> items = new ArrayList<Integer>();
    }

    private static final HashMap<NPC, Pending> PENDING = new HashMap<NPC, Pending>();

    // ---- host -----------------------------------------------------------------------------------------
    static final class Kill {
        String key;
        String reporter;
        String level;
        int x;
        int y;
        int xp;
        int gold;
        String items;
        HashMap<String, Integer> dmg = new HashMap<String, Integer>();
    }

    private static final HashMap<String, Kill> KILLS = new HashMap<String, Kill>();
    private static final HashMap<String, HashMap<String, Integer>> REPORTS = new HashMap<String, HashMap<String, Integer>>();
    private static final LinkedHashSet<String> SETTLED = new LinkedHashSet<String>();

    // ---- helpers ------------------------------------------------------------------------------------

    private static LanSessionManager mgr() {
        return LanSessionManager.getInstanceIfReady();
    }

    static boolean session() {
        try {
            return LanGameBridge.isSessionRunning();
        } catch (Throwable e) {
            return false;
        }
    }

    static String me() {
        LanSessionManager m = mgr();
        String n = m == null ? null : m.getLocalPlayerName();
        return n == null ? "" : n.trim();
    }

    private static String level() {
        GameData gd = GameData.O();
        return gd == null || gd.CurrentLevel == null ? "" : gd.CurrentLevel;
    }

    static String key(NPC n) {
        return level() + ":" + n.m() + ":" + n.spawn_id;
    }

    private static String clean(String s) {
        return s == null ? "" : s.replace('\t', ' ').replace('\n', ' ').replace('\r', ' ');
    }

    /** Allies and other players' stand-ins are never "monsters" for the ledger. */
    private static boolean tracked(NPC n) {
        return n != null && !n.P() && !n.lanPeerVisual && !(n.summoned && "player_summon".equals(n.tag));
    }

    /** Which side dealt a hit from actor id `from`: me (player, companions, summons) or a peer's name. */
    static String sideOf(int from) {
        if (from == 1) {
            return me();
        }
        try {
            LinkedHashMap peers = LanGameBridge.ekPeerActors();
            if (peers != null) {
                for (Object o : peers.entrySet()) {
                    Map.Entry e = (Map.Entry) o;
                    if (e.getValue() instanceof NPC && ((NPC) e.getValue()).m() == from) {
                        return String.valueOf(e.getKey()).trim();
                    }
                }
                LinkedHashMap owners = LanGameBridge.ekPeerSummonOwners();
                Object owner = owners == null ? null : owners.get(Integer.valueOf(from));
                if (owner instanceof Integer) {
                    int oid = ((Integer) owner).intValue();
                    for (Object o : peers.entrySet()) {
                        Map.Entry e = (Map.Entry) o;
                        if (e.getValue() instanceof NPC && ((NPC) e.getValue()).m() == oid) {
                            return String.valueOf(e.getKey()).trim();
                        }
                    }
                }
            }
        } catch (Throwable e) {
            // fall through
        }
        MapActor a = GameLevel.b(from);
        if (a instanceof NPC) {
            NPC n = (NPC) a;
            if (n.P() || (n.summoned && "player_summon".equals(n.tag))) {
                return me();
            }
        }
        return null; // other NPCs, traps, the world: nobody's damage
    }

    // ---- hooks: Character.a(Damage,I,Z,I) wrapper ------------------------------------------------------

    public static int before(Character c) {
        try {
            return c != null && c.sheet != null && c.sheet.stats != null ? c.sheet.stats.missingHP : -1;
        } catch (Throwable e) {
            return -1;
        }
    }

    public static void after(Character c, int from, int before) {
        try {
            if (before < 0 || !(c instanceof NPC) || !session() || !tracked((NPC) c)) {
                return;
            }
            int max = c.sheet.z();
            int now = Math.min(c.sheet.stats.missingHP, max);
            int d = now - Math.min(before, max);
            if (d <= 0) {
                return;
            }
            String side = sideOf(from);
            if (side == null || side.length() == 0) {
                return;
            }
            String lvl = level();
            if (!lvl.equals(ledgerLevel)) {
                LEDGER.clear();
                ledgerLevel = lvl;
            }
            String k = key((NPC) c);
            HashMap<String, Integer> m = LEDGER.get(k);
            if (m == null) {
                m = new HashMap<String, Integer>();
                LEDGER.put(k, m);
                if (LEDGER.size() > 300) {
                    Iterator<String> it = LEDGER.keySet().iterator();
                    it.next();
                    it.remove();
                }
            }
            Integer old = m.get(side);
            m.put(side, (old == null ? 0 : old.intValue()) + d);
        } catch (Throwable e) {
            // never break combat
        }
    }

    private static boolean othersInvolved(String k) {
        HashMap<String, Integer> m = LEDGER.get(k);
        if (m == null) {
            return false;
        }
        String self = me();
        for (Map.Entry<String, Integer> e : m.entrySet()) {
            if (!e.getKey().equals(self) && e.getValue().intValue() > 0) {
                return true;
            }
        }
        return false;
    }

    // ---- hooks: NPC.E() (death) ----------------------------------------------------------------------

    /** Start of NPC.E(): every device the monster dies on reports its own side's exact damage. */
    public static void onDeath(NPC n) {
        try {
            if (!session() || !tracked(n)) {
                return;
            }
            String k = key(n);
            HashMap<String, Integer> m = LEDGER.get(k);
            Integer mine = m == null ? null : m.get(me());
            if (mine != null && mine.intValue() > 0 && othersInvolved(k)) {
                toHost("EKDMG\t" + clean(k) + "\t" + clean(me()) + "\t" + mine);
            }
        } catch (Throwable e) {
            // ignore
        }
    }

    /** Replaces GameLevel.a(x,y,items,gold) in NPC.E(): shared kills go to the host instead. */
    public static void loot(NPC n, int x, int y, ArrayList items, int gold) {
        try {
            if (session() && tracked(n) && othersInvolved(key(n))) {
                Pending p = pending(n);
                p.x = x;
                p.y = y;
                p.gold += gold;
                if (items != null) {
                    for (Object o : items) {
                        if (o instanceof Integer) {
                            p.items.add((Integer) o);
                        }
                    }
                }
                return;
            }
        } catch (Throwable e) {
            // fall back to vanilla
        }
        GameLevel.a(x, y, items, gold);
    }

    /** Replaces Player.k(xp) for the kill in NPC.E(). */
    public static void xp(NPC n, Player pl, int xp) {
        try {
            if (session() && tracked(n) && othersInvolved(key(n))) {
                Pending p = pending(n);
                p.xp += xp;
                if (p.x == 0 && p.y == 0) {
                    p.x = n.x + 32;
                    p.y = n.y + 32;
                }
                return;
            }
        } catch (Throwable e) {
            // fall back to vanilla
        }
        pl.k(xp);
    }

    private static Pending pending(final NPC n) {
        Pending p = PENDING.get(n);
        if (p == null) {
            p = new Pending();
            p.key = key(n);
            p.level = level();
            PENDING.put(n, p);
            // after NPC.E() and the rest of this hit (the ledger gets the final blow first)
            Gdx.app.postRunnable(new Runnable() {
                public void run() {
                    flush(n);
                }
            });
        }
        return p;
    }

    private static void flush(NPC n) {
        Pending p = PENDING.remove(n);
        if (p == null) {
            return;
        }
        StringBuilder items = new StringBuilder();
        LinkedHashMap<Integer, Integer> counts = new LinkedHashMap<Integer, Integer>();
        for (Integer id : p.items) {
            Integer c = counts.get(id);
            counts.put(id, c == null ? 1 : c + 1);
        }
        for (Map.Entry<Integer, Integer> e : counts.entrySet()) {
            if (items.length() > 0) {
                items.append(',');
            }
            items.append(e.getKey()).append(':').append(e.getValue());
        }
        StringBuilder led = new StringBuilder();
        HashMap<String, Integer> m = LEDGER.get(p.key);
        if (m != null) {
            for (Map.Entry<String, Integer> e : m.entrySet()) {
                if (led.length() > 0) {
                    led.append(';');
                }
                led.append(clean(e.getKey()).replace('=', ' ').replace(';', ' ')).append('=').append(e.getValue());
            }
        }
        toHost("EKKILL\t" + clean(p.key) + "\t" + clean(me()) + "\t" + clean(p.level) + "\t" + p.x + "\t" + p.y
                + "\t" + p.xp + "\t" + p.gold + "\t" + items + "\t" + led);
    }

    // ---- transport -----------------------------------------------------------------------------------

    private static void toHost(String line) {
        LanSessionManager m = mgr();
        if (m == null) {
            return;
        }
        if (m.isHosting()) {
            hostLine(line);
        } else {
            m.ekSendToHost(line);
        }
    }

    /** From EkShare.hostLine / clientLine. true = ours. */
    public static boolean line(String line, boolean asHost) {
        if (line.startsWith("EKKILL\t") || line.startsWith("EKDMG\t")) {
            if (asHost) {
                hostLine(line);
            }
            return true;
        }
        if (line.startsWith("EKREWARD\t")) {
            String[] p = line.split("\t", -1);
            if (!asHost && p.length >= 10 && p[1].equals(me())) {
                apply(p);
            }
            return true;
        }
        return false;
    }

    // ---- host: collect, wait for the other sides' own damage, split once ------------------------------

    static void hostLine(String line) {
        String[] p = line.split("\t", -1);
        try {
            if (p[0].equals("EKDMG") && p.length >= 4) {
                synchronized (KILLS) {
                    HashMap<String, Integer> r = REPORTS.get(p[1]);
                    if (r == null) {
                        if (REPORTS.size() > 500) {
                            REPORTS.clear(); // stale reports for kills nobody claimed
                        }
                        r = new HashMap<String, Integer>();
                        REPORTS.put(p[1], r);
                    }
                    int d = Integer.parseInt(p[3].trim());
                    Integer old = r.get(p[2]);
                    r.put(p[2], Math.max(d, old == null ? 0 : old.intValue()));
                }
                return;
            }
            if (!p[0].equals("EKKILL") || p.length < 10) {
                return;
            }
            final String k = p[1];
            synchronized (KILLS) {
                if (SETTLED.contains(k) || KILLS.containsKey(k)) {
                    return; // already paid / being paid (a second reward block, e.g. alwaysReward)
                }
                Kill kill = new Kill();
                kill.key = k;
                kill.reporter = p[2];
                kill.level = p[3];
                kill.x = Integer.parseInt(p[4].trim());
                kill.y = Integer.parseInt(p[5].trim());
                kill.xp = Integer.parseInt(p[6].trim());
                kill.gold = Integer.parseInt(p[7].trim());
                kill.items = p[8];
                if (p[9].length() > 0) {
                    for (String s : p[9].split(";")) {
                        int eq = s.lastIndexOf('=');
                        if (eq > 0) {
                            kill.dmg.put(s.substring(0, eq), Integer.parseInt(s.substring(eq + 1).trim()));
                        }
                    }
                }
                KILLS.put(k, kill);
            }
            Thread t = new Thread(new Runnable() {
                public void run() {
                    try {
                        Thread.sleep(SETTLE_DELAY_MS);
                    } catch (InterruptedException e) {
                        // go on
                    }
                    settle(k);
                }
            }, "ek-kill-settle");
            t.setDaemon(true);
            t.start();
        } catch (Throwable e) {
            // a bad line pays nobody twice
        }
    }

    static void settle(String k) {
        Kill kill;
        HashMap<String, Integer> merged = new HashMap<String, Integer>();
        synchronized (KILLS) {
            kill = KILLS.remove(k);
            if (kill == null || SETTLED.contains(k)) {
                return;
            }
            SETTLED.add(k);
            if (SETTLED.size() > 1000) {
                Iterator<String> it = SETTLED.iterator();
                it.next();
                it.remove();
            }
            merged.putAll(kill.dmg);
            HashMap<String, Integer> r = REPORTS.remove(k);
            if (r != null) {
                for (Map.Entry<String, Integer> e : r.entrySet()) {
                    Integer old = merged.get(e.getKey());
                    merged.put(e.getKey(), Math.max(e.getValue(), old == null ? 0 : old.intValue()));
                }
            }
        }
        // sides that did damage
        LinkedHashMap<String, Integer> sides = new LinkedHashMap<String, Integer>();
        long total = 0;
        for (Map.Entry<String, Integer> e : merged.entrySet()) {
            if (e.getValue().intValue() > 0 && e.getKey().length() > 0) {
                sides.put(e.getKey(), e.getValue());
                total += e.getValue().intValue();
            }
        }
        if (total <= 0) {
            sides.clear();
            sides.put(kill.reporter, 1);
            total = 1;
        }
        // XP and gold by share; items one by one, weighted by share
        HashMap<String, Integer> xp = new HashMap<String, Integer>();
        HashMap<String, Integer> gold = new HashMap<String, Integer>();
        HashMap<String, StringBuilder> items = new HashMap<String, StringBuilder>();
        String top = null;
        int goldLeft = kill.gold;
        for (Map.Entry<String, Integer> e : sides.entrySet()) {
            double share = e.getValue().doubleValue() / total;
            int x = (int) Math.round(kill.xp * share);
            if (kill.xp > 0 && x < 1) {
                x = 1;
            }
            xp.put(e.getKey(), x);
            int g = (int) Math.floor(kill.gold * share);
            gold.put(e.getKey(), g);
            goldLeft -= g;
            items.put(e.getKey(), new StringBuilder());
            if (top == null || e.getValue().intValue() > sides.get(top).intValue()) {
                top = e.getKey();
            }
        }
        if (goldLeft > 0 && top != null) {
            gold.put(top, gold.get(top) + goldLeft);
        }
        if (kill.items != null && kill.items.length() > 0) {
            for (String s : kill.items.split(",")) {
                String[] kv = s.split(":");
                try {
                    int id = Integer.parseInt(kv[0].trim());
                    int n = kv.length > 1 ? Integer.parseInt(kv[1].trim()) : 1;
                    for (int u = 0; u < n; u++) {
                        long roll = (long) (RNG.nextDouble() * total);
                        String who = top;
                        long acc = 0;
                        for (Map.Entry<String, Integer> e : sides.entrySet()) {
                            acc += e.getValue().intValue();
                            if (roll < acc) {
                                who = e.getKey();
                                break;
                            }
                        }
                        StringBuilder b = items.get(who);
                        if (b.length() > 0) {
                            b.append(',');
                        }
                        b.append(id);
                    }
                } catch (NumberFormatException e) {
                    // skip
                }
            }
        }
        String self = me();
        LanSessionManager m = mgr();
        for (Map.Entry<String, Integer> e : sides.entrySet()) {
            String who = e.getKey();
            int pct = (int) Math.round(100.0 * e.getValue().intValue() / total);
            String line = "EKREWARD\t" + who + "\t" + kill.key + "\t" + kill.level + "\t" + kill.x + "\t" + kill.y + "\t"
                    + xp.get(who) + "\t" + gold.get(who) + "\t" + items.get(who) + "\t" + pct;
            if (who.equals(self)) {
                apply(line.split("\t", -1));
            } else if (m != null) {
                m.ekBroadcast(line);
            }
        }
    }

    // ---- receiving my share (any thread -> game thread) -----------------------------------------------

    static void apply(final String[] p) {
        Gdx.app.postRunnable(new Runnable() {
            public void run() {
                try {
                    String lvl = p[3];
                    int x = Integer.parseInt(p[4].trim());
                    int y = Integer.parseInt(p[5].trim());
                    int xp = Integer.parseInt(p[6].trim());
                    int gold = Integer.parseInt(p[7].trim());
                    ArrayList<Integer> items = new ArrayList<Integer>();
                    if (p[8].length() > 0) {
                        for (String s : p[8].split(",")) {
                            items.add(Integer.valueOf(s.trim()));
                        }
                    }
                    String pct = p[9];
                    GameData gd = GameData.O();
                    Player pl = GameLevel.h();
                    if (gd == null || pl == null) {
                        return;
                    }
                    if (xp > 0) {
                        pl.k(xp);
                    }
                    if (gold > 0 || !items.isEmpty()) {
                        if (lvl.equals(gd.CurrentLevel)) {
                            GameLevel.a(x, y, items, gold); // a bag only you can see, where it died
                        } else {
                            if (gold > 0) {
                                pl.s(gold);
                            }
                            ArrayList<Integer> over = new ArrayList<Integer>();
                            for (Integer id : items) {
                                if (!gd.backpack.a(id.intValue())) {
                                    over.add(id);
                                }
                            }
                            if (!over.isEmpty()) {
                                GameLevel.a(pl.x + 16, pl.y + 16, over, 0);
                            }
                        }
                    }
                    StringBuilder msg = new StringBuilder("[GREEN]Shared kill: your part " + pct + "% - +" + xp + " XP");
                    if (gold > 0) {
                        msg.append(", ").append(gold).append(" gold");
                    }
                    if (!items.isEmpty()) {
                        msg.append(", ").append(items.size()).append(items.size() == 1 ? " item" : " items");
                    }
                    GameConsole.a(msg.append("[]").toString());
                } catch (Throwable e) {
                    // ignore a bad reward line
                }
            }
        });
    }
}
