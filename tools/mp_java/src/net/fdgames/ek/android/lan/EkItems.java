package net.fdgames.ek.android.lan;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.Toast;
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

    // ---- PvP: each player's own choice (v57) ----------------------------------------------------
    // Everyone starts with PvP ON. A player who turns it off stays off (saved on their phone) until they
    // turn it back on. Two players can hurt each other only when BOTH have it on. The host keeps the
    // table (name -> on/off) under the names it knows each connection by and broadcasts it every 3 s;
    // clients report their own choice to the host. deobf/MULTIPLAYER_PORT_SPEC.md v57.

    static final String PREF_PVP_ME = "ek_pvp_me";
    private static final java.util.concurrent.ConcurrentHashMap<String, Boolean> PVP_TABLE =
            new java.util.concurrent.ConcurrentHashMap<String, Boolean>();      // everyone (from host)
    private static final java.util.concurrent.ConcurrentHashMap<String, Boolean> PVP_CLIENTS =
            new java.util.concurrent.ConcurrentHashMap<String, Boolean>();      // host: reported by clients
    private static volatile boolean wasInSession;

    static android.app.Activity act() {
        Object o = Gdx.app;
        return o instanceof android.app.Activity ? (android.app.Activity) o : null;
    }

    /** My own PvP choice (default ON). */
    public static boolean myPvp() {
        try {
            android.app.Activity a = act();
            return a == null || !"0".equals(a.getSharedPreferences(EkFriends.PREFS, 0).getString(PREF_PVP_ME, "1"));
        } catch (Throwable e) {
            return true;
        }
    }

    public static void setMyPvp(boolean on) {
        try {
            android.app.Activity a = act();
            if (a != null) {
                a.getSharedPreferences(EkFriends.PREFS, 0).edit().putString(PREF_PVP_ME, on ? "1" : "0").commit();
            }
        } catch (Throwable e) {
            // ignore
        }
        Thread t = new Thread(new Runnable() {       // tell the others right away (never on the UI thread)
            public void run() {
                pvpTick();
            }
        }, "ek-pvp");
        t.setDaemon(true);
        t.start();
    }

    /** receiveRemoteCombat (damage from another player outside the arena): only if I have PvP on. */
    public static boolean pvpAnywhere() {
        return inSession() && myPvp();
    }

    /** Peer puppet hostility: both of us have PvP on. Unknown players count as off until the table says. */
    public static boolean pvpWith(String name) {
        return name != null && inSession() && myPvp() && Boolean.TRUE.equals(PVP_TABLE.get(name.trim()));
    }

    public static boolean pvpWithState(LanSessionManager.PlayerState st) {
        return st != null && pvpWith(st.playerName);
    }

    // v59: a PvP peer keeps the player faction (guards, townsfolk, summons and companions leave it alone, monsters
    // still hunt it) and gets "neutral" as its second faction. That pair is a marker no game data uses; the two
    // WorldFactions hostility checks treat it as hostile to the local player only (deobf/MULTIPLAYER_PORT_SPEC.md).
    static final int FACTION_PLAYER = 100;
    static final int FACTION_NEUTRAL = 105;

    static boolean pvpMarked(int[] f) {
        return f != null && f.length > 1 && f[0] == FACTION_PLAYER && f[1] == FACTION_NEUTRAL;
    }

    /** createPeerActor: the spawn's faction string. */
    public static String pvpFactionState(LanSessionManager.PlayerState st, String faction) {
        return "player".equals(faction) && pvpWithState(st) ? "player,neutral" : faction;
    }

    // Whose puppet is this (faction arrays are per actor; the puppet's array is replaced every sync, weak keys).
    private static final java.util.Map<int[], String> PUPPET_BY_FACTIONS =
            java.util.Collections.synchronizedMap(new java.util.WeakHashMap<int[], String>());
    private static final java.util.Map<Object, String> PUPPET_BY_NPC =
            java.util.Collections.synchronizedMap(new java.util.WeakHashMap<Object, String>());

    /** getOrCreatePeerActor: mark / unmark an existing puppet that has the player faction (not the arena's "enemy"). */
    public static void pvpMarkActor(net.fdgames.GameEntities.Final.NPC npc, String name) {
        if (npc == null) {
            return;
        }
        int[] f = npc.worldfactions;
        if (f == null || f.length < 2 || f[0] != FACTION_PLAYER || (f[1] != 0 && f[1] != FACTION_NEUTRAL)) {
            return;
        }
        f[1] = pvpWith(name) ? FACTION_NEUTRAL : 0;
        if (name != null) {
            PUPPET_BY_FACTIONS.put(f, name.trim());
            PUPPET_BY_NPC.put(npc, name.trim());
        }
    }

    // ---- v60 retaliation: a player's summons/companions fight back only at someone who attacked that player ----
    static final long AGGRO_MS = 30000;          // how long after the last hit the summons keep fighting back
    private static final long AGGRO_RESEND_MS = 5000;
    private static final java.util.concurrent.ConcurrentHashMap<String, Long> AGGRESSORS =
            new java.util.concurrent.ConcurrentHashMap<String, Long>();   // attacker name -> last hit on me
    private static final java.util.concurrent.ConcurrentHashMap<String, Long> AGGRO_SENT =
            new java.util.concurrent.ConcurrentHashMap<String, Long>();   // victim name -> last EKAGGRO sent
    private static java.util.Set<int[]> allyFactions = java.util.Collections.emptySet();
    private static long allyAt;

    static boolean isAggressor(String name) {
        Long t = name == null ? null : AGGRESSORS.get(name);
        return t != null && System.currentTimeMillis() - t < AGGRO_MS;
    }

    /** Faction arrays of my own summons and companions (CompanionAI; peers' puppets run "idle"), cached 0.5 s. */
    private static java.util.Set<int[]> myAllies() {
        long now = System.currentTimeMillis();
        if (now - allyAt < 500) {
            return allyFactions;
        }
        allyAt = now;
        java.util.Set<int[]> s = java.util.Collections.newSetFromMap(new java.util.IdentityHashMap<int[], Boolean>());
        try {
            ArrayList<?> actors = GameLevel.e();
            if (actors != null) {
                for (Object o : new ArrayList<Object>(actors)) {
                    if (o instanceof net.fdgames.GameEntities.Final.NPC) {
                        net.fdgames.GameEntities.Final.NPC n = (net.fdgames.GameEntities.Final.NPC) o;
                        if (!n.lanPeerVisual && !n.destroy && n.worldfactions != null && n.I0()) {
                            s.add(n.worldfactions);
                        }
                    }
                }
            }
        } catch (Throwable e) {
            // keep the last set
            return allyFactions;
        }
        allyFactions = s;
        return s;
    }

    /** Character.a(msg, source, ...) hook: I hit a PvP puppet -> tell its player that I attacked them. */
    public static void pvpOnHit(net.fdgames.GameEntities.Character target, int source, String msg, Object damage) {
        try {
            if (!(target instanceof net.fdgames.GameEntities.Final.NPC) || !pvpMarked(target.worldfactions)) {
                return;
            }
            if (damage == null && !"ATTACK".equals(msg)) {
                return;
            }
            GameData gd = GameData.O();
            Player p = gd == null ? null : gd.player;
            if (p == null || source != p.m() || source == target.m()) {
                return;
            }
            String victim = PUPPET_BY_NPC.get(target);
            if (victim == null) {
                return;
            }
            long now = System.currentTimeMillis();
            Long last = AGGRO_SENT.get(victim);
            if (last != null && now - last < AGGRO_RESEND_MS) {
                return;
            }
            AGGRO_SENT.put(victim, now);
            send("EKAGGRO\t" + java.net.URLEncoder.encode(me().trim(), "UTF-8") + "\t"
                    + java.net.URLEncoder.encode(victim, "UTF-8"));
        } catch (Throwable e) {
            // ignore
        }
    }

    /** EKAGGRO\tattacker\tvictim: if I'm the victim, my summons may fight the attacker for AGGRO_MS. */
    private static void onAggro(String line) {
        try {
            String[] p = line.split("\t", -1);
            if (p.length < 3) {
                return;
            }
            String attacker = java.net.URLDecoder.decode(p[1], "UTF-8").trim();
            String victim = java.net.URLDecoder.decode(p[2], "UTF-8").trim();
            if (!attacker.isEmpty() && victim.equals(me().trim()) && !attacker.equals(victim)) {
                AGGRESSORS.put(attacker, System.currentTimeMillis());
            }
        } catch (Throwable e) {
            // ignore
        }
    }

    /** WorldFactions "is this faction set hostile to faction `who`": a marked puppet is hostile to the player. */
    public static boolean pvpHostileTo(int[] f, Integer who) {
        return who != null && who.intValue() == FACTION_PLAYER && pvpMarked(f);
    }

    /** WorldFactions "are these two hostile": the local player and a marked puppet; my summons/companions and a
     *  marked puppet whose player attacked me recently (retaliation). Nobody else. */
    public static boolean pvpPair(int[] a, int[] b) {
        boolean ma = pvpMarked(a);
        boolean mb = pvpMarked(b);
        if (ma == mb) {
            return false;
        }
        int[] puppet = ma ? a : b;
        int[] other = ma ? b : a;
        GameData gd = GameData.O();
        Player me = gd == null ? null : gd.player;
        int[] mine = me == null ? null : me.worldfactions;
        if (mine != null && other == mine) {
            return true;
        }
        // my summons/companions: only against a player who attacked me in the last AGGRO_MS
        return !AGGRESSORS.isEmpty() && isAggressor(PUPPET_BY_FACTIONS.get(puppet)) && myAllies().contains(other);
    }

    private static String peerName(Object peer) {
        try {
            java.lang.reflect.Field f = peer.getClass().getDeclaredField("playerName");
            f.setAccessible(true);
            Object v = f.get(peer);
            return v == null ? null : v.toString().trim();
        } catch (Throwable e) {
            return null;
        }
    }

    /** Every 3 s (EkAuto.tick) and on changes: host broadcasts the table, a client reports its choice. */
    public static synchronized void pvpTick() {
        try {
            LanSessionManager m = mgr();
            boolean in = inSession();
            if (in && !wasInSession) {
                announcePvp();
            }
            wasInSession = in;
            if (m == null || !in) {
                return;
            }
            if (m.isHosting()) {
                String me = me().trim();
                PVP_TABLE.put(me, myPvp());
                StringBuilder sb = new StringBuilder("EKPVPT");
                java.util.Set<String> live = new java.util.HashSet<String>();
                live.add(me);
                try {
                    java.lang.reflect.Field f = m.getClass().getDeclaredField("hostPeers");
                    f.setAccessible(true);
                    Object list = f.get(m);
                    if (list instanceof java.util.List) {
                        for (Object peer : new ArrayList<Object>((java.util.List<?>) list)) {
                            String n = peerName(peer);
                            if (n != null) {
                                live.add(n);
                                PVP_TABLE.put(n, Boolean.TRUE.equals(PVP_CLIENTS.get(n)));
                            }
                        }
                    }
                } catch (Throwable e) {
                    // table as far as known
                }
                for (String n : new ArrayList<String>(PVP_TABLE.keySet())) {
                    if (!live.contains(n)) {
                        PVP_TABLE.remove(n);
                    }
                }
                for (java.util.Map.Entry<String, Boolean> e : PVP_TABLE.entrySet()) {
                    sb.append('\t').append(java.net.URLEncoder.encode(e.getKey(), "UTF-8")).append('=')
                            .append(e.getValue() ? '1' : '0');
                }
                m.ekBroadcast(sb.toString());
            } else {
                m.ekSendToHost("EKPVPME\t" + (myPvp() ? 1 : 0));
            }
        } catch (Throwable e) {
            // next tick
        }
    }

    /** Joining (or someone joining you): say PvP is on and offer to turn it off; or note it's off. */
    private static void announcePvp() {
        final android.app.Activity lob = EkFriends.lobbyIfOpen();
        final android.app.Activity a = lob != null ? lob : act();
        if (a == null) {
            return;
        }
        final boolean on = myPvp();
        a.runOnUiThread(new Runnable() {
            public void run() {
                try {
                    if (!on) {
                        Toast.makeText(a, "PvP is OFF for you (your setting). Turn it on in My address.", 1).show();
                        return;
                    }
                    new AlertDialog.Builder(a).setTitle("PvP is ON")
                            .setMessage("Other players can attack you, and you can attack them, anywhere in the"
                                    + " world. Anyone who turns PvP off can't attack or be attacked.\n\nYour choice is"
                                    + " remembered until you change it (My address in the multiplayer menu).")
                            .setPositiveButton("Keep PvP on", null)
                            .setNegativeButton("Turn PvP off", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface d, int w) {
                                    setMyPvp(false);
                                    Toast.makeText(a, "PvP off. It stays off until you turn it back on.", 1).show();
                                }
                            }).show();
                } catch (Throwable e) {
                    // ignore
                }
            }
        });
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
        if (line.startsWith("EKAGGRO\t")) {
            onAggro(line);                           // the host may be the victim
            LanSessionManager m = mgr();
            if (m != null) {
                m.ekBroadcast(line);                 // or another guest is
            }
            return true;
        }
        if (line.startsWith("EKPVPME\t")) {
            String n = peerName(peer);
            if (n != null) {
                PVP_CLIENTS.put(n, line.endsWith("1"));
                pvpTick();                           // everyone learns the change right away
            }
            return true;
        }
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
        if (line.startsWith("EKAGGRO\t")) {
            onAggro(line);
            return true;
        }
        if (line.startsWith("EKPVPT")) {
            java.util.HashMap<String, Boolean> t = new java.util.HashMap<String, Boolean>();
            String[] p = line.split("\t");
            for (int i = 1; i < p.length; i++) {
                int eq = p[i].lastIndexOf('=');
                if (eq > 0) {
                    try {
                        t.put(java.net.URLDecoder.decode(p[i].substring(0, eq), "UTF-8").trim(), p[i].endsWith("1"));
                    } catch (Throwable e) {
                        // skip
                    }
                }
            }
            PVP_TABLE.clear();
            PVP_TABLE.putAll(t);
            return true;
        }
        if (line.startsWith("EKPVP\t")) {
            return true;                             // pre-v57 host: ignored
        }
        return false;
    }

    /** Host: a client joined -> send it the PvP table now (and every 3 s after). */
    public static void onClientJoined() {
        pvpTick();
    }
}
