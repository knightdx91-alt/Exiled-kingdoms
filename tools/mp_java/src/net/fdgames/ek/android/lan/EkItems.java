package net.fdgames.ek.android.lan;

import com.badlogic.gdx.Gdx;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;
import net.fdgames.GameEntities.Final.Loot;
import net.fdgames.GameEntities.Final.Player;
import net.fdgames.GameEntities.Helpers.Items;
import net.fdgames.GameLevel.GameLevel;
import net.fdgames.GameLevel.GameLevelData;
import net.fdgames.GameWorld.GameData;
import net.fdgames.Helpers.GameConsole;
import net.fdgames.ek.android.MainActivity;

/**
 * Shared-world phase D (deobf/SHARED_WORLD_SPEC.md §8): drops everyone can see and pick up, with the
 * host deciding who got an item first; PvP everywhere (host setting) with a loot bag for the loser.
 */
public final class EkItems {
    private EkItems() {}

    static final String PREFIX = "ekdrop_";
    static final String PREF_PVP = "ek_pvp";
    private static final Random RNG = new Random();
    private static volatile boolean applying;
    private static volatile boolean sessionPvp;

    /** Host registry: drop id -> (item -> units left), gold under key 0. */
    private static final Map<String, Map<Integer, Integer>> registry = new HashMap<String, Map<Integer, Integer>>();

    // ---- plumbing --------------------------------------------------------------------------------

    private static LanSessionManager mgr() {
        return LanSessionManager.getInstanceIfReady();
    }

    private static boolean hostingSession() {
        LanSessionManager m = mgr();
        return m != null && m.isHosting() && m.getPlayerCount() >= 2;
    }

    private static boolean inSession() {
        LanSessionManager m = mgr();
        return m != null && (hostingSession() || m.ekConnected());
    }

    private static String me() {
        try {
            LanSessionManager m = mgr();
            String n = m == null ? null : m.getLocalPlayerName();
            return n == null ? "" : n;
        } catch (Throwable e) {
            return "";
        }
    }

    /** Host: to every client. Client: to the host. */
    private static void send(String line) {
        try {
            LanSessionManager m = mgr();
            if (m == null) {
                return;
            }
            if (m.isHosting()) {
                m.ekBroadcast(line);
            } else if (m.ekConnected()) {
                m.ekSendToHost(line);
            }
        } catch (Throwable e) {
            // ignore
        }
    }

    private static void runOnGame(Runnable r) {
        try {
            Gdx.app.postRunnable(r);
        } catch (Throwable e) {
            // ignore
        }
    }

    private static void say(String s) {
        try {
            GameConsole.a(s);
        } catch (Throwable e) {
            // ignore
        }
    }

    private static String level() {
        GameData gd = GameData.O();
        return gd == null || gd.CurrentLevel == null ? "" : gd.CurrentLevel;
    }

    // ---- contents helpers --------------------------------------------------------------------------

    private static Map<Integer, Integer> contents(Loot l) {
        Map<Integer, Integer> c = new LinkedHashMap<Integer, Integer>();
        for (int k = 0; k < 20; k++) {
            int id = l.getItem(k);
            if (id > 0 && !c.containsKey(id)) {
                c.put(id, l.h().g(id));
            }
        }
        return c;
    }

    private static String encode(Map<Integer, Integer> c) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Integer, Integer> e : c.entrySet()) {
            if (sb.length() > 0) {
                sb.append(',');
            }
            sb.append(e.getKey()).append(':').append(e.getValue());
        }
        return sb.toString();
    }

    private static Map<Integer, Integer> decode(String s) {
        Map<Integer, Integer> c = new LinkedHashMap<Integer, Integer>();
        if (s == null || s.length() == 0) {
            return c;
        }
        for (String part : s.split(",")) {
            String[] kv = part.split(":");
            try {
                c.put(Integer.parseInt(kv[0].trim()), kv.length > 1 ? Integer.parseInt(kv[1].trim()) : 1);
            } catch (NumberFormatException e) {
                // skip
            }
        }
        return c;
    }

    private static Loot findLoot(String id) {
        try {
            ArrayList list = GameLevel.f();
            if (list != null) {
                for (Object o : list) {
                    if (o instanceof Loot && id.equals(((Loot) o).tag)) {
                        return (Loot) o;
                    }
                }
            }
        } catch (Throwable e) {
            // ignore
        }
        return null;
    }

    // ---- drops ---------------------------------------------------------------------------------------

    /** GameLevel.a(III) hook: every new ground drop; in a session it becomes a shared drop. */
    public static void onNewDrop(Loot l) {
        try {
            if (applying || l == null || !inSession()) {
                return;
            }
            announce(l, 0);
        } catch (Throwable e) {
            // ignore
        }
    }

    private static void announce(Loot l, int gold) {
        l.tag = PREFIX + Long.toHexString(RNG.nextLong() & 0xffffffffffffL);
        Map<Integer, Integer> c = contents(l);
        String line = "EKDROP\t" + l.tag + "\t" + level() + "\t" + l.x + "\t" + l.y + "\t" + gold + "\t" + encode(c);
        if (mgr() != null && mgr().isHosting()) {
            register(l.tag, c, gold);
        }
        send(line);
    }

    private static void register(String id, Map<Integer, Integer> c, int gold) {
        synchronized (registry) {
            Map<Integer, Integer> m = new HashMap<Integer, Integer>(c);
            if (gold > 0) {
                m.put(0, gold);
            }
            registry.put(id, m);
        }
    }

    /** Loot.removeItem(slot) hook, called with the item id taken. */
    public static void onTake(Loot l, int itemId) {
        if (applying || l == null || itemId <= 0 || l.tag == null || !l.tag.startsWith(PREFIX)) {
            return;
        }
        pick(l.tag, itemId, 1);
    }

    /** Loot.d() hook (gold taken). */
    public static void onTakeGold(Loot l, int gold) {
        if (applying || l == null || gold <= 0 || l.tag == null || !l.tag.startsWith(PREFIX)) {
            return;
        }
        pick(l.tag, 0, gold);
    }

    /** Loot.b() (take all) hook: the items that were in the bag before it was emptied. */
    public static void onTakeAll(Loot l, String before) {
        if (applying || l == null || l.tag == null || !l.tag.startsWith(PREFIX)) {
            return;
        }
        for (Map.Entry<Integer, Integer> e : decode(before).entrySet()) {
            pick(l.tag, e.getKey(), e.getValue());
        }
    }

    public static String snapshot(Loot l) {
        try {
            return encode(contents(l));
        } catch (Throwable e) {
            return "";
        }
    }

    private static void pick(String id, int item, int count) {
        String line = "EKPICK\t" + id + "\t" + item + "\t" + count + "\t" + me();
        if (mgr() != null && mgr().isHosting()) {
            arbitrate(line, null);
        } else {
            send(line);
        }
    }

    /** Host: decide a pick. peer == null means the host's own pick. */
    private static void arbitrate(String line, Object peer) {
        String[] p = line.split("\t", -1);
        if (p.length < 5) {
            return;
        }
        String id = p[1];
        int item;
        int count;
        try {
            item = Integer.parseInt(p[2]);
            count = Integer.parseInt(p[3]);
        } catch (NumberFormatException e) {
            return;
        }
        boolean ok;
        synchronized (registry) {
            Map<Integer, Integer> m = registry.get(id);
            Integer left = m == null ? null : m.get(item);
            ok = left != null && left >= count;
            if (ok) {
                if (left == count) {
                    m.remove(item);
                } else {
                    m.put(item, left - count);
                }
                if (m.isEmpty()) {
                    registry.remove(id);
                }
            }
        }
        LanSessionManager m = mgr();
        if (ok) {
            String gone = "EKGONE\t" + id + "\t" + item + "\t" + count + "\t" + p[4];
            if (m != null) {
                m.ekBroadcast(gone);
            }
            if (peer != null) {
                applyGone(gone); // the host's own copy of the bag
            }
        } else {
            String deny = "EKDENY\t" + id + "\t" + item + "\t" + count;
            if (peer == null) {
                applyDeny(deny);
            } else if (m != null) {
                m.ekSendTo(peer, deny);
            }
        }
    }

    private static void applyGone(final String line) {
        runOnGame(new Runnable() {
            public void run() {
                String[] p = line.split("\t", -1);
                if (p.length < 5 || p[4].equals(me())) {
                    return; // the picker already has it
                }
                Loot l = findLoot(p[1]);
                if (l == null) {
                    return;
                }
                applying = true;
                try {
                    int item = Integer.parseInt(p[2]);
                    int count = Integer.parseInt(p[3]);
                    if (item == 0) {
                        l.ekSetGold(Math.max(0, l.g() - count));
                    } else {
                        for (int n = 0; n < count; n++) {
                            for (int k = 0; k < 20; k++) {
                                if (l.getItem(k) == item) {
                                    l.removeItem(k);
                                    break;
                                }
                            }
                        }
                    }
                } catch (Throwable e) {
                    // ignore
                } finally {
                    applying = false;
                }
            }
        });
    }

    private static void applyDeny(final String line) {
        runOnGame(new Runnable() {
            public void run() {
                String[] p = line.split("\t", -1);
                GameData gd = GameData.O();
                if (p.length < 4 || gd == null) {
                    return;
                }
                try {
                    int item = Integer.parseInt(p[2]);
                    int count = Integer.parseInt(p[3]);
                    if (item == 0) {
                        gd.player.s(-Math.min(count, gd.player.g()));
                    } else {
                        for (int n = 0; n < count; n++) {
                            gd.backpack.i(item);
                        }
                    }
                    say("[YELLOW]Someone else picked that up first.[]");
                } catch (Throwable e) {
                    // ignore
                }
            }
        });
    }

    private static void applyDrop(final String line) {
        runOnGame(new Runnable() {
            public void run() {
                String[] p = line.split("\t", -1);
                if (p.length < 7 || !p[2].equals(level()) || findLoot(p[1]) != null) {
                    return;
                }
                try {
                    ArrayList<Integer> items = new ArrayList<Integer>();
                    for (Map.Entry<Integer, Integer> e : decode(p[6]).entrySet()) {
                        for (int n = 0; n < e.getValue(); n++) {
                            items.add(e.getKey());
                        }
                    }
                    Loot l = new Loot(Integer.parseInt(p[3]), Integer.parseInt(p[4]), items, Integer.parseInt(p[5]));
                    l.tag = p[1];
                    applying = true;
                    try {
                        GameLevelData.a(l);
                    } finally {
                        applying = false;
                    }
                } catch (Throwable e) {
                    // ignore
                }
            }
        });
    }

    // ---- PvP everywhere --------------------------------------------------------------------------

    /**
     * v56: the host now uses its saved setting directly (it used to count only when toggled while already
     * hosting), and guests learn it from EKPVP, which the host resends every 3 s (hostTick) - the join-time
     * send (onClientJoined) was never hooked, so guests always had PvP off.
     */
    public static boolean pvpAnywhere() {
        if (!inSession()) {
            return false;
        }
        LanSessionManager m = mgr();
        if (m != null && m.isHosting()) {
            Object o = Gdx.app;
            if (o instanceof android.app.Activity) {
                return hostPvpPref((android.app.Activity) o);
            }
        }
        return sessionPvp;
    }

    /** From EkAuto.tick (every 3 s): the host keeps every guest in step with its PvP setting. */
    public static void hostTick() {
        try {
            LanSessionManager m = mgr();
            Object o = Gdx.app;
            if (m != null && m.isHosting() && m.getPlayerCount() >= 2 && o instanceof android.app.Activity) {
                sessionPvp = hostPvpPref((android.app.Activity) o);
                m.ekBroadcast("EKPVP\t" + (sessionPvp ? 1 : 0));
            }
        } catch (Throwable e) {
            // next tick
        }
    }

    static boolean hostPvpPref(android.app.Activity a) {
        try {
            // default ON (v56, owner: players must be able to attack each other); "PvP everywhere" in
            // My address turns it off
            return !"0".equals(a.getSharedPreferences(EkFriends.PREFS, 0).getString(PREF_PVP, "1"));
        } catch (Throwable e) {
            return false;
        }
    }

    /** Host: turn PvP everywhere on/off for the room. */
    public static void setHostPvp(android.app.Activity a, boolean on) {
        try {
            a.getSharedPreferences(EkFriends.PREFS, 0).edit().putString(PREF_PVP, on ? "1" : "0").commit();
        } catch (Throwable e) {
            // ignore
        }
        LanSessionManager m = mgr();
        if (m != null && m.isHosting()) {
            sessionPvp = on;
            m.ekBroadcast("EKPVP\t" + (on ? 1 : 0));
        }
    }

    /** Engine elimination branch (receiveRemoteCombat) outside the arena: drop the PvP loot bag. */
    public static void onPvpDefeat() {
        try {
            GameData gd = GameData.O();
            if (gd == null || gd.player == null || "H10_pvp_arena".equals(gd.CurrentLevel)) {
                return;
            }
            Player p = gd.player;
            Items bag = gd.backpack;
            ArrayList<Integer> ids = new ArrayList<Integer>();
            for (int k = 0; k < 20; k++) {
                int id = bag.d(k);
                if (id > 0 && !ids.contains(id)) {
                    ids.add(id);
                }
            }
            ArrayList<Integer> drop = new ArrayList<Integer>();
            if (!ids.isEmpty()) {
                int id = ids.get(RNG.nextInt(ids.size()));
                int n = bag.g(id);
                for (int k = 0; k < n; k++) {
                    bag.i(id);
                    drop.add(id);
                }
            }
            int gold = p.g() / 10;
            if (gold > 0) {
                p.s(-gold);
            }
            if (drop.isEmpty() && gold <= 0) {
                return;
            }
            Loot l = new Loot(p.x + 16, p.y + 16, drop, gold);
            applying = true;
            try {
                GameLevelData.a(l);
            } finally {
                applying = false;
            }
            if (inSession()) {
                announce(l, gold);
            }
            say("[RED]You were defeated and dropped some loot.[]");
        } catch (Throwable e) {
            // ignore
        }
    }

    // ---- message entry points (from EkShare.hostLine / clientLine) ----------------------------------

    /** Host side. true = handled. */
    public static boolean hostLine(Object peer, String line) {
        if (line.startsWith("EKDROP\t")) {
            String[] p = line.split("\t", -1);
            if (p.length >= 7) {
                int gold = 0;
                try {
                    gold = Integer.parseInt(p[5]);
                } catch (NumberFormatException e) {
                    gold = 0;
                }
                register(p[1], decode(p[6]), gold);
            }
            applyDrop(line);
            LanSessionManager m = mgr();
            if (m != null) {
                m.ekBroadcast(line);
            }
            return true;
        }
        if (line.startsWith("EKPICK\t")) {
            arbitrate(line, peer);
            return true;
        }
        return false;
    }

    /** Client side. true = handled. */
    public static boolean clientLine(String line) {
        if (line.startsWith("WELCOME\t")) {
            return false;
        }
        if (line.startsWith("EKDROP\t")) {
            applyDrop(line);
            return true;
        }
        if (line.startsWith("EKGONE\t")) {
            applyGone(line);
            return true;
        }
        if (line.startsWith("EKDENY\t")) {
            applyDeny(line);
            return true;
        }
        if (line.startsWith("EKPVP\t")) {
            sessionPvp = line.endsWith("1");
            return true;
        }
        return false;
    }

    /** Host: a client joined -> tell it the PvP setting. */
    public static void onClientJoined() {
        MainActivity a = null;
        Object o = Gdx.app;
        if (o instanceof MainActivity) {
            a = (MainActivity) o;
        }
        LanSessionManager m = mgr();
        if (a != null && m != null && m.isHosting()) {
            sessionPvp = hostPvpPref(a);
            m.ekBroadcast("EKPVP\t" + (sessionPvp ? 1 : 0));
        }
    }
}
