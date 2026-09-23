package net.fdgames.ek.android.lan;

import com.badlogic.gdx.Gdx;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import net.fdgames.GameEntities.Final.NPC;
import net.fdgames.GameEntities.Final.Player;
import net.fdgames.GameEntities.Helpers.Activable;
import net.fdgames.GameEntities.Helpers.Items;
import net.fdgames.GameLevel.GameLevelData;
import net.fdgames.GameWorld.GameData;
import net.fdgames.GameWorld.Party;
import net.fdgames.GameWorld.Variable;
import net.fdgames.Helpers.GameConsole;
import net.fdgames.Helpers.Json;
import net.fdgames.Helpers.SaveGameData;
import net.fdgames.Helpers.Serializer;
import net.fdgames.ek.android.MainActivity;

/**
 * Shared world, phases B + C (deobf/SHARED_WORLD_SPEC.md §7): you play in the host's world with your
 * own character; your character (player, backpack, party + companions, REP_/know_/item_upg_ variables)
 * always goes home with you. Everything runs through the game's own save format and LoadGame.
 */
public final class EkShare {
    private EkShare() {}

    public static final int GUEST_SLOT = 42;
    static final String PREF_HOME = "ek_home_slot";

    /** The character block: exactly what travels between worlds. */
    public static class Block {
        public Player player;
        public Items backpack;
        public Party party;
        public ArrayList<NPC> companions;
        public ArrayList<Variable> vars;
        public Block() {}
    }

    private static volatile boolean applying;     // suppress forwarding while applying remote changes
    private static volatile boolean joinPrepared;  // we started a join from inside a game
    private static volatile String joinBlock;      // character captured when the join started
    private static int loadSlot = -1;
    private static boolean saveHomeNextTick;

    // ---- helpers ---------------------------------------------------------------------------------

    private static MainActivity activity() {
        Object a = Gdx.app;
        return a instanceof MainActivity ? (MainActivity) a : null;
    }

    static boolean isCharVar(String n) {
        return n != null && (n.startsWith("REP_") || n.startsWith("know_") || n.startsWith("item_upg_"));
    }

    private static File blockFile(int slot) {
        MainActivity a = activity();
        return a == null ? null : new File(a.getFilesDir(), "data/saves/ek_block_" + slot + ".json");
    }

    private static void writeText(File f, String s) throws Exception {
        f.getParentFile().mkdirs();
        OutputStream o = new FileOutputStream(f);
        try {
            o.write(s.getBytes("UTF-8"));
        } finally {
            o.close();
        }
    }

    private static String readText(File f) throws Exception {
        InputStream in = new FileInputStream(f);
        try {
            byte[] b = new byte[(int) f.length()];
            int off = 0;
            while (off < b.length) {
                int r = in.read(b, off, b.length - off);
                if (r < 0) {
                    break;
                }
                off += r;
            }
            return new String(b, 0, off, "UTF-8");
        } finally {
            in.close();
        }
    }

    static int homeSlot() {
        try {
            return Integer.parseInt(activity().getSharedPreferences(EkFriends.PREFS, 0).getString(PREF_HOME, "-1"));
        } catch (Throwable e) {
            return -1;
        }
    }

    private static void setHomeSlot(int s) {
        try {
            activity().getSharedPreferences(EkFriends.PREFS, 0).edit().putString(PREF_HOME, String.valueOf(s)).commit();
        } catch (Throwable e) {
            // ignore
        }
    }

    public static boolean isGuest() {
        GameData gd = GameData.O();
        return gd != null && gd.slot == GUEST_SLOT;
    }

    private static void runOnGame(Runnable r) {
        try {
            Gdx.app.postRunnable(r);
        } catch (Throwable e) {
            // ignore
        }
    }

    private static void say(final String s) {
        runOnGame(new Runnable() {
            public void run() {
                try {
                    GameConsole.a(s);
                } catch (Throwable e) {
                    // ignore
                }
            }
        });
    }

    // ---- character block -------------------------------------------------------------------------

    /** Live character -> JSON (game thread). Mirrors the save's clearing of activables/conversations. */
    static String captureBlock() {
        GameData gd = GameData.O();
        if (gd == null || gd.player == null) {
            return null;
        }
        Player p = gd.player;
        Activable[] act = p.activables;
        int nact = p.numActivables;
        ArrayList conv = p.conversations;
        try {
            p.activables = null;
            p.numActivables = 0;
            p.conversations = null;
            Block b = new Block();
            b.player = p;
            b.backpack = gd.backpack;
            b.party = gd.party;
            b.companions = new ArrayList<NPC>();
            GameLevelData ld = GameLevelData.s();
            if (ld != null && ld.npcs != null && gd.party != null && gd.party.companions != null) {
                for (Object o : ld.npcs) {
                    if (o instanceof NPC && inParty((NPC) o, gd.party)) {
                        b.companions.add((NPC) o);
                    }
                }
            }
            b.vars = new ArrayList<Variable>();
            if (gd.gameVariables != null) {
                for (Object o : gd.gameVariables.ekVars()) {
                    Variable v = (Variable) o;
                    if (isCharVar(v.name)) {
                        b.vars.add(v);
                    }
                }
            }
            Json j = Serializer.ekJson();
            j.setIgnoreUnknownFields(true);
            return j.prettyPrint(b);
        } finally {
            p.activables = act;
            p.numActivables = nact;
            p.conversations = conv;
        }
    }

    private static boolean inParty(NPC n, Party party) {
        if (n == null || n.tag == null || party == null || party.companions == null) {
            return false;
        }
        for (Object o : party.companions) {
            if (o instanceof NPC && n.tag.equals(((NPC) o).tag)) {
                return true;
            }
        }
        return false;
    }

    // ---- LoadGame hooks (Serializer.a(II)) ------------------------------------------------------

    public static void onLoadStart(int slot, int sub) {
        loadSlot = slot;
    }

    /** Right after the save is parsed, before it goes live: apply a pending character block. */
    public static void onLoaded(SaveGameData sgd) {
        try {
            if (sgd == null || sgd.gamedata == null) {
                return;
            }
            File f = blockFile(loadSlot);
            if (f == null || !f.exists()) {
                return;
            }
            Json j = Serializer.ekJson();
            j.setIgnoreUnknownFields(true);
            Block b = (Block) j.fromJson(Block.class, readText(f));
            if (b == null || b.player == null) {
                return;
            }
            GameData gd = sgd.gamedata;
            Player old = gd.player;
            if (old != null) {
                b.player.x = old.x;
                b.player.y = old.y;
            }
            Party oldParty = gd.party;
            gd.player = b.player;
            if (b.backpack != null) {
                gd.backpack = b.backpack;
            }
            if (b.party != null) {
                gd.party = b.party;
            }
            if (gd.gameVariables != null) {
                ArrayList list = gd.gameVariables.ekVars();
                for (int i = list.size() - 1; i >= 0; i--) {
                    if (isCharVar(((Variable) list.get(i)).name)) {
                        list.remove(i);
                    }
                }
                if (b.vars != null) {
                    list.addAll(b.vars);
                }
            }
            if (sgd.leveldata != null && sgd.leveldata.npcs != null) {
                ArrayList npcs = sgd.leveldata.npcs;
                for (int i = npcs.size() - 1; i >= 0; i--) {
                    Object o = npcs.get(i);
                    if (o instanceof NPC && inParty((NPC) o, oldParty)) {
                        npcs.remove(i);
                    }
                }
                if (b.companions != null) {
                    int k = 0;
                    for (NPC c : b.companions) {
                        c.x = b.player.x + 24 + 16 * k;
                        c.y = b.player.y + 24;
                        npcs.add(c);
                        k++;
                    }
                }
            }
            if (loadSlot == GUEST_SLOT) {
                gd.slot = GUEST_SLOT;
                f.delete(); // the guest world can always be fetched again
            } else {
                saveHomeNextTick = true; // keep the file until the home save really happened
            }
        } catch (Throwable e) {
            say("Character transfer failed: " + e);
        }
    }

    // ---- Save hooks (Serializer.d(II)) ----------------------------------------------------------

    public static void onSaveAfter(int slot) {
        try {
            if (slot == GUEST_SLOT) {
                int home = homeSlot();
                String s = captureBlock();
                if (home >= 0 && s != null) {
                    writeText(blockFile(home), s); // your progress always lands in the home save
                }
            } else if (slot == homeSlot()) {
                File f = blockFile(slot);
                if (f != null && f.exists() && !isGuest()) {
                    f.delete();
                }
            }
        } catch (Throwable e) {
            // ignore
        }
    }

    // ---- join / leave --------------------------------------------------------------------------------

    /** Called from EkAuto.noteJoin (join thread): prepare on the game thread. */
    public static void prepareJoin() {
        joinPrepared = false;
        joinBlock = null;
        runOnGame(new Runnable() {
            public void run() {
                try {
                    GameData gd = GameData.O();
                    if (gd == null || gd.player == null || !gd.B()) {
                        return; // not in a game: joining from the menu only chats / shows peers
                    }
                    if (gd.slot != GUEST_SLOT) {
                        setHomeSlot(gd.slot);
                        Serializer.d(gd.slot, 0); // your own world, saved as you leave it
                    }
                    joinBlock = captureBlock();
                    joinPrepared = joinBlock != null;
                } catch (Throwable e) {
                    joinPrepared = false;
                }
            }
        });
    }

    /** Called by EkAuto.tick every 3 s (game thread). */
    public static void tick() {
        try {
            GameData gd = GameData.O();
            if (gd == null) {
                return;
            }
            if (saveHomeNextTick && gd.B() && gd.slot != GUEST_SLOT) {
                saveHomeNextTick = false;
                Serializer.d(gd.slot, 0);
            }
            if (gd.slot == GUEST_SLOT) {
                LanSessionManager m = LanSessionManager.getInstanceIfReady();
                if (m == null || !m.ekConnected()) {
                    goHome();
                }
            }
        } catch (Throwable e) {
            // ignore
        }
    }

    /** Leave the host's world: take the character home, reload home, save. */
    public static void goHome() {
        try {
            int home = homeSlot();
            if (home < 0) {
                return;
            }
            String s = captureBlock();
            if (s != null) {
                writeText(blockFile(home), s);
            }
            Integer sub = Serializer.f(home);
            say("Returning to your world...");
            Serializer.a(home, sub == null ? 0 : sub.intValue());
        } catch (Throwable e) {
            say("Could not return home: " + e);
        }
    }

    // ---- world sync (C) --------------------------------------------------------------------------

    private static LanSessionManager mgr() {
        return LanSessionManager.getInstanceIfReady();
    }

    private static boolean hostingSession(LanSessionManager m) {
        return m != null && m.isHosting() && m.getPlayerCount() >= 2;
    }

    /** GameVariables.b(String,I) hook (after the set). */
    private static final java.util.HashMap<String, Integer> lastVar = new java.util.HashMap<String, Integer>();

    /** Per-player/session variables the engine itself writes every frame: never synced. */
    static boolean isLocalVar(String n) {
        return n.startsWith("lan_") || n.startsWith("pvp_");
    }

    public static void onVar(String name, int value) {
        if (applying || name == null || isCharVar(name) || isLocalVar(name)) {
            return;
        }
        synchronized (lastVar) {
            Integer prev = lastVar.get(name);
            if (prev != null && prev.intValue() == value) {
                return;
            }
            lastVar.put(name, value);
        }
        send("EKVAR\t" + clean(name) + "\t" + value);
    }

    /** GameData.l(String) hook: a unique NPC died. */
    public static void onDead(String tag) {
        if (!applying && tag != null) {
            send("EKDEAD\t" + clean(tag));
        }
    }

    /** GameData.i(String) hook: a container was looted. */
    public static void onLooted(String id) {
        if (!applying && id != null) {
            send("EKLOOT\t" + clean(id));
        }
    }

    private static String clean(String s) {
        return s.replace('\t', ' ').replace('\n', ' ').replace('\r', ' ');
    }

    private static void send(String line) {
        try {
            LanSessionManager m = mgr();
            if (m == null) {
                return;
            }
            if (hostingSession(m)) {
                m.ekBroadcast(line);
            } else if (m.ekConnected() && isGuest()) {
                m.ekSendToHost(line);
            }
        } catch (Throwable e) {
            // ignore
        }
    }

    private static void applyOnGame(final String line) {
        runOnGame(new Runnable() {
            public void run() {
                GameData gd = GameData.O();
                if (gd == null) {
                    return;
                }
                String[] p = line.split("\t", -1);
                boolean host = hostingSession(mgr());
                applying = !host; // the host re-broadcasts through its own hooks
                try {
                    if (p[0].equals("EKVAR") && p.length >= 3 && !isCharVar(p[1]) && !isLocalVar(p[1])) {
                        int v = Integer.parseInt(p[2].trim());
                        if (gd.gameVariables.b(p[1]) != v) {
                            gd.gameVariables.b(p[1], v);
                        }
                    } else if (p[0].equals("EKDEAD") && p.length >= 2) {
                        if (!gd.g(p[1])) {
                            gd.l(p[1]);
                        }
                    } else if (p[0].equals("EKLOOT") && p.length >= 2) {
                        if (!gd.b(p[1])) {
                            gd.i(p[1]);
                        }
                    }
                } catch (Throwable e) {
                    // ignore a bad line
                } finally {
                    applying = false;
                }
            }
        });
    }

    /** Host side: a line from a client. true = ours (handled). */
    public static boolean hostLine(final LanSessionManager m, final Object peer, String line) {
        if (line == null || !line.startsWith("EK")) {
            return false;
        }
        if (line.startsWith("EKHELLO")) {
            EkItems.onClientJoined();
            return true;
        }
        if (EkItems.hostLine(peer, line) || EkTrade.line(line, true)) {
            return true;
        }
        if (line.startsWith("EKWREQ")) {
            runOnGame(new Runnable() {
                public void run() {
                    try {
                        final String snap = Serializer.ekSnapshot();
                        if (snap == null) {
                            return;
                        }
                        Thread t = new Thread(new Runnable() {
                            public void run() {
                                m.ekSendTo(peer, "EKWORLD\t" + snap);
                            }
                        }, "ek-world-send");
                        t.setDaemon(true);
                        t.start();
                    } catch (Throwable e) {
                        // ignore
                    }
                }
            });
            return true;
        }
        if (line.startsWith("EKVAR\t") || line.startsWith("EKDEAD\t") || line.startsWith("EKLOOT\t")) {
            applyOnGame(line);
            return true;
        }
        return false;
    }

    /** Client side: a line from the host. true = ours (handled). */
    public static boolean clientLine(LanSessionManager m, String line) {
        if (line == null) {
            return false;
        }
        if (line.startsWith("WELCOME\t")) {
            m.ekSendToHost("EKHELLO");
            if (joinPrepared) {
                m.ekSendToHost("EKWREQ");
            }
            return false; // the engine handles WELCOME as usual
        }
        if (line.startsWith("EK") && (EkItems.clientLine(line) || EkTrade.line(line, false))) {
            return true;
        }
        if (line.startsWith("EKWORLD\t")) {
            final String b64 = line.substring(8);
            final String block = joinBlock;
            joinPrepared = false;
            if (block == null) {
                return true;
            }
            runOnGame(new Runnable() {
                public void run() {
                    try {
                        MainActivity a = activity();
                        File dir = new File(a.getFilesDir(), "data/saves/" + GUEST_SLOT);
                        dir.mkdirs();
                        writeText(new File(dir, "game.sav"), b64);
                        writeText(blockFile(GUEST_SLOT), block);
                        say("Entering the host's world...");
                        Serializer.a(GUEST_SLOT, 1);
                    } catch (Throwable e) {
                        say("Could not enter the host's world: " + e);
                    }
                }
            });
            return true;
        }
        if (line.startsWith("EKVAR\t") || line.startsWith("EKDEAD\t") || line.startsWith("EKLOOT\t")) {
            if (isGuest()) {
                applyOnGame(line);
            }
            return true;
        }
        return false;
    }
}
