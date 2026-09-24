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
import net.fdgames.GameWorld.WorldContainer;
import net.fdgames.Helpers.GameConsole;
import net.fdgames.Helpers.Json;
import net.fdgames.Helpers.SaveGameData;
import net.fdgames.Helpers.Serializer;
import net.fdgames.TiledMap.Objects.Coords;
import net.fdgames.TiledMap.Objects.Transition;
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
    static final String PREF_HOME_SUB = "ek_home_sub";

    /** The character block: exactly what travels between worlds. */
    public static class Block {
        public Player player;
        public Items backpack;
        public Party party;
        public ArrayList<NPC> companions;
        public ArrayList<Variable> vars;
        /** Your own storage (§9): vault, vault2-4, bag_of_holding, bag_of_holding2-5; null = old block. */
        public ArrayList<WorldContainer> stores;
        public boolean hasVault, hasVault2, hasVault3, hasVault4, bagHolding;
        public Block() {}
    }

    private static volatile boolean applying;     // suppress forwarding while applying remote changes
    private static volatile boolean joinPrepared;  // we started a join from inside a game
    private static volatile String joinBlock;      // character captured when the join started
    private static volatile boolean welcomed;      // WELCOME seen for the current join
    private static volatile boolean reqSent;       // EKWREQ sent for the current join
    // v61: where I was in each host's world (owner: "when I join their game again it spawns me in the same place")
    private static volatile String worldId;        // EKWID from the host of the current join
    private static String[] pendingTravel;         // saved spot in another area: travel there once loaded
    private static long pendingSince;
    private static long noRecordUntil;
    private static int loadSlot = -1;
    private static boolean saveHomeNextTick;

    // ---- helpers ---------------------------------------------------------------------------------

    private static MainActivity activity() {
        Object a = Gdx.app;
        return a instanceof MainActivity ? (MainActivity) a : null;
    }

    static boolean isCharVar(String n) {
        return n != null && (n.startsWith("REP_") || n.startsWith("know_") || n.startsWith("item_upg_")
                || n.equals("summon_path"));
    }

    private static File blockFile(int slot) {
        MainActivity a = activity();
        return a == null ? null : new File(a.getFilesDir(), "data/saves/ek_block_" + slot + ".json");
    }

    /**
     * v66: written to a temporary file and renamed over the target, so a reader (the game loading an area while the
     * other player's copy of it arrives from the network, deobf/SHARED_WORLD_SPEC.md §9) sees the whole old file or
     * the whole new one, never half of each.
     */
    private static void writeText(File f, String s) throws Exception {
        f.getParentFile().mkdirs();
        File tmp = new File(f.getParentFile(), f.getName() + ".ek" + Long.toHexString(System.nanoTime()) + ".tmp");
        OutputStream o = new FileOutputStream(tmp);
        try {
            o.write(s.getBytes("UTF-8"));
            o.flush();
            try {
                ((FileOutputStream) o).getFD().sync();
            } catch (Throwable e) {
                // best effort
            }
        } finally {
            o.close();
        }
        if (!tmp.renameTo(f)) {                     // rename(2) replaces atomically; this is a fallback only
            f.delete();
            if (!tmp.renameTo(f)) {
                tmp.delete();
                throw new java.io.IOException("could not replace " + f.getName());
            }
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

    static int homeSub() {
        try {
            return Integer.parseInt(activity().getSharedPreferences(EkFriends.PREFS, 0).getString(PREF_HOME_SUB, "0"));
        } catch (Throwable e) {
            return 0;
        }
    }

    private static void setHomeSlot(int s, int sub) {
        try {
            activity().getSharedPreferences(EkFriends.PREFS, 0).edit().putString(PREF_HOME, String.valueOf(s))
                    .putString(PREF_HOME_SUB, String.valueOf(sub)).commit();
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
        GameLevelData ld = GameLevelData.s();
        return captureBlock(gd, ld == null ? null : ld.npcs);
    }

    /** Any GameData (live, or parsed from a save file when joining from the menu) -> block JSON. */
    static String captureBlock(GameData gd, ArrayList npcs) {
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
            if (npcs != null && gd.party != null && gd.party.companions != null) {
                for (Object o : npcs) {
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
            b.stores = new ArrayList<WorldContainer>();
            for (Object o : containers(gd)) {
                if (o instanceof WorldContainer && isStore(((WorldContainer) o).id)) {
                    b.stores.add((WorldContainer) o);
                }
            }
            b.hasVault = gd.hasVault;
            b.hasVault2 = gd.hasVault2;
            b.hasVault3 = gd.hasVault3;
            b.hasVault4 = gd.hasVault4;
            b.bagHolding = getBool(gd, "bagHolding");
            Json j = Serializer.ekJson();
            j.setIgnoreUnknownFields(true);
            return j.prettyPrint(b);
        } finally {
            p.activables = act;
            p.numActivables = nact;
            p.conversations = conv;
        }
    }

    // ---- own storage (vaults, bags of holding): GameData private fields, by reflection ----------------

    static boolean isStore(String id) {
        return id != null && (id.startsWith("vault") || id.startsWith("bag_of_holding")) && !id.equals("vault_active");
    }

    @SuppressWarnings("unchecked")
    static ArrayList<Object> containers(GameData gd) {
        try {
            java.lang.reflect.Field f = GameData.class.getDeclaredField("worldContainers");
            f.setAccessible(true);
            ArrayList<Object> l = (ArrayList<Object>) f.get(gd);
            if (l == null) {
                l = new ArrayList<Object>();
                f.set(gd, l);
            }
            return l;
        } catch (Throwable e) {
            return new ArrayList<Object>();
        }
    }

    private static boolean getBool(GameData gd, String name) {
        try {
            java.lang.reflect.Field f = GameData.class.getDeclaredField(name);
            f.setAccessible(true);
            return f.getBoolean(gd);
        } catch (Throwable e) {
            return false;
        }
    }

    private static void setBool(GameData gd, String name, boolean v) {
        try {
            java.lang.reflect.Field f = GameData.class.getDeclaredField(name);
            f.setAccessible(true);
            f.setBoolean(gd, v);
        } catch (Throwable e) {
            // ignore
        }
    }

    /** The world's vaults/bags become the block's (the world's own never reach this character). */
    private static void graftStores(GameData gd, Block b) {
        if (b.stores == null) {
            return; // block written by an older build: leave storage alone
        }
        ArrayList<Object> l = containers(gd);
        for (int i = l.size() - 1; i >= 0; i--) {
            Object o = l.get(i);
            if (o instanceof WorldContainer && isStore(((WorldContainer) o).id)) {
                l.remove(i);
            }
        }
        l.addAll(b.stores);
        gd.hasVault = b.hasVault;
        gd.hasVault2 = b.hasVault2;
        gd.hasVault3 = b.hasVault3;
        gd.hasVault4 = b.hasVault4;
        setBool(gd, "bagHolding", b.bagHolding);
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
            if (loadSlot == GUEST_SLOT) {
                pendingTravel = null;
                String[] at = savedSpot();
                if (at != null && at[0].equals(gd.CurrentLevel)) {
                    b.player.x = Integer.parseInt(at[1]);   // same area as the host: just stand there
                    b.player.y = Integer.parseInt(at[2]);
                } else if (at != null) {
                    pendingTravel = at;                      // another area: the game's own travel, once live
                    pendingSince = System.currentTimeMillis();
                }
                noRecordUntil = System.currentTimeMillis() + 5000L;
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
            graftStores(gd, b);
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
        worldId = null;                               // the host sends it again (EKWID) with the world
        pendingTravel = null;
        joinBlock = null;
        welcomed = false;
        reqSent = false;
        GameData now = GameData.O();
        if (now == null || now.player == null || !now.B()) {
            prepareFromMenu(); // no game loaded: your newest save comes along (plain file work)
            requestWorldIfReady();
            return;
        }
        // in a game: save + capture on the game thread (it may be paused while the lobby is open;
        // the world is requested once both this and the host's WELCOME have happened, in any order)
        runOnGame(new Runnable() {
            public void run() {
                try {
                    GameData gd = GameData.O();
                    if (gd == null || gd.player == null || !gd.B()) {
                        return;
                    }
                    if (gd.slot != GUEST_SLOT) {
                        setHomeSlot(gd.slot, 0);
                        Serializer.d(gd.slot, 0); // your own world, saved as you leave it
                    }
                    joinBlock = captureBlock();
                    joinPrepared = joinBlock != null;
                } catch (Throwable e) {
                    joinPrepared = false;
                }
                requestWorldIfReady();
            }
        });
    }

    /** Sends EKWREQ once, when the character is ready and the host has welcomed us. */
    private static void requestWorldIfReady() {
        synchronized (EkShare.class) {
            if (!welcomed || !joinPrepared || reqSent) {
                return;
            }
            reqSent = true;
        }
        Thread t = new Thread(new Runnable() {
            public void run() {
                try {
                    LanSessionManager m = mgr();
                    if (m != null && m.ekConnected()) {
                        clearGuestSlot();
                        m.ekSendToHost("EKWREQ");
                    }
                } catch (Throwable e) {
                    // ignore
                }
            }
        }, "ek-world-req");
        t.setDaemon(true);
        t.start();
    }

    /** Joining from the main menu (§9): the newest save file becomes home and brings the character. */
    private static void prepareFromMenu() {
        MainActivity a = activity();
        if (a == null) {
            return;
        }
        int bestSlot = -1;
        int bestSub = 0;
        long bestTime = 0;
        for (int slot = 0; slot < 10; slot++) {
            for (int sub = 0; sub < 8; sub++) {
                File f = new File(a.getFilesDir(), Serializer.e(slot, sub));
                if (f.exists() && f.lastModified() > bestTime) {
                    bestTime = f.lastModified();
                    bestSlot = slot;
                    bestSub = sub;
                }
            }
        }
        if (bestSlot < 0) {
            say("No saved game to bring along: start or load a game, then join.");
            return;
        }
        try {
            String text = Serializer.ekDecode(readText(new File(a.getFilesDir(), Serializer.e(bestSlot, bestSub))));
            Json j = Serializer.ekJson();
            j.setIgnoreUnknownFields(true);
            SaveGameData sgd = (SaveGameData) j.fromJson(SaveGameData.class, text);
            if (sgd == null || sgd.gamedata == null) {
                return;
            }
            String block = captureBlock(sgd.gamedata, sgd.leveldata == null ? null : sgd.leveldata.npcs);
            if (block != null) {
                setHomeSlot(bestSlot, bestSub);
                joinBlock = block;
                joinPrepared = true;
            }
        } catch (Throwable e) {
            say("Could not read your save: " + e);
        }
    }

    /** Called by EkAuto.tick every 3 s (game thread). */
    public static void tick() {
        try {
            EkTrade.tick();
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
                } else if (pendingTravel != null) {
                    if (System.currentTimeMillis() - pendingSince >= 1500L && gd.player != null) {
                        String[] at = pendingTravel;
                        pendingTravel = null;
                        Transition tr = new Transition(at[0], 0);   // entry 0 + coords = land exactly there
                        tr.coords = new Coords(Integer.parseInt(at[1]), Integer.parseInt(at[2]));
                        noRecordUntil = System.currentTimeMillis() + 10000L;
                        say("Back to where you left off...");
                        gd.player.a(tr);
                    }
                } else {
                    recordSpot(gd);
                }
            }
        } catch (Throwable e) {
            // ignore
        }
    }

    // ---- v61: my spot in each host's world, kept on this phone -------------------------------------------

    /** This phone's world id (random, made once) + the save slot: one id per host character/world. */
    private static String hostWorldId(GameData gd) {
        try {
            MainActivity a = activity();
            if (a == null || gd == null) {
                return null;
            }
            android.content.SharedPreferences sp = a.getSharedPreferences(EkFriends.PREFS, 0);
            String id = sp.getString("ek_world_id", null);
            if (id == null || id.length() < 8) {
                id = Long.toHexString(new java.security.SecureRandom().nextLong());
                sp.edit().putString("ek_world_id", id).commit();
            }
            return id + ":" + gd.slot;
        } catch (Throwable e) {
            return null;
        }
    }

    private static String spotKey() {
        String w = worldId;
        return w == null || w.length() == 0 ? null : "ek_wpos_" + w;
    }

    /** {level, x, y} saved for the current host's world, or null. Arenas are never restored. */
    static String[] savedSpot() {
        try {
            String k = spotKey();
            MainActivity a = activity();
            if (k == null || a == null) {
                return null;
            }
            String v = a.getSharedPreferences(EkFriends.PREFS, 0).getString(k, null);
            String[] p = v == null ? null : v.split("\t");
            if (p == null || p.length < 3 || !okLevel(p[0]) || p[0].contains("arena")) {
                return null;
            }
            int x = Integer.parseInt(p[1]);
            int y = Integer.parseInt(p[2]);
            return x > 0 && y > 0 ? p : null;
        } catch (Throwable e) {
            return null;
        }
    }

    /** Every tick while in someone's world, and when leaving it. */
    static void recordSpot(GameData gd) {
        try {
            String k = spotKey();
            MainActivity a = activity();
            if (k == null || a == null || gd == null || gd.player == null || System.currentTimeMillis() < noRecordUntil) {
                return;
            }
            String lvl = gd.CurrentLevel;
            if (!okLevel(lvl) || lvl.contains("arena") || gd.player.x <= 0 || gd.player.y <= 0) {
                return;
            }
            String v = lvl + "\t" + gd.player.x + "\t" + gd.player.y;
            android.content.SharedPreferences sp = a.getSharedPreferences(EkFriends.PREFS, 0);
            if (!v.equals(sp.getString(k, null))) {
                sp.edit().putString(k, v).apply();
            }
        } catch (Throwable e) {
            // next tick
        }
    }

    /** Leave the host's world: take the character home, reload home, save. */
    public static void goHome() {
        try {
            int home = homeSlot();
            if (home < 0) {
                return;
            }
            GameData now = GameData.O();
            if (now != null && now.slot == GUEST_SLOT && pendingTravel == null) {
                noRecordUntil = 0L;
                recordSpot(now);
            }
            String s = captureBlock();
            if (s != null) {
                writeText(blockFile(home), s);
            }
            int sub = homeSub(); // 0 = the save made when joining (keeps your areas' cache)
            MainActivity a = activity();
            if (a != null && !new File(a.getFilesDir(), Serializer.e(home, sub)).exists()) {
                Integer latest = Serializer.f(home);
                sub = latest == null ? 0 : latest.intValue();
            }
            say("Returning to your world...");
            Serializer.a(home, sub);
        } catch (Throwable e) {
            say("Could not return home: " + e);
        }
    }

    // ---- other areas: the level cache (§9) -----------------------------------------------------------

    private static File cacheDir(int slot) {
        MainActivity a = activity();
        return a == null || slot < 0 ? null : new File(a.getFilesDir(), Serializer.a(slot));
    }

    private static boolean okLevel(String n) {
        return n != null && n.length() > 0 && n.indexOf('/') < 0 && n.indexOf('\\') < 0 && !n.contains("..");
    }

    private static void deleteTree(File f) {
        if (f == null || !f.exists()) {
            return;
        }
        File[] kids = f.listFiles();
        if (kids != null) {
            for (File k : kids) {
                deleteTree(k);
            }
        }
        f.delete();
    }

    /** Before asking for a world: no leftovers from an earlier visit. */
    private static void clearGuestSlot() {
        MainActivity a = activity();
        if (a != null) {
            deleteTree(new File(a.getFilesDir(), "data/saves/" + GUEST_SLOT));
        }
    }

    /** Host, join: every cached area except the one the snapshot already holds live. */
    static void sendCaches(LanSessionManager m, Object peer, File dir, String here) {
        try {
            File[] fs = dir == null ? null : dir.listFiles();
            if (fs == null) {
                return;
            }
            for (File f : fs) {
                String n = f.getName();
                if (!n.endsWith(".sav")) {
                    continue;
                }
                String level = n.substring(0, n.length() - 4);
                if (!okLevel(level) || level.equals(here)) {
                    continue;
                }
                m.ekSendTo(peer, "EKCACHE\t" + level + "\t" + readText(f).replace("\n", "").replace("\r", ""));
            }
        } catch (Throwable e) {
            // the guest then sees defaults in the areas that are missing
        }
    }

    /** Serializer.f()V wrapper (after the area you are leaving was cached): share it. */
    public static void onLevelSaved(final String level) {
        try {
            final LanSessionManager m = mgr();
            GameData gd = GameData.O();
            if (m == null || gd == null || !okLevel(level)) {
                return;
            }
            final boolean host = hostingSession(m);
            if (!host && !(m.ekConnected() && isGuest())) {
                return;
            }
            final File f = new File(cacheDir(gd.slot), level + ".sav");
            if (!f.exists() || System.currentTimeMillis() - f.lastModified() > 10000L) {
                return; // nothing new was written
            }
            Thread t = new Thread(new Runnable() {
                public void run() {
                    try {
                        String line = "EKCACHE\t" + level + "\t" + readText(f).replace("\n", "").replace("\r", "");
                        if (host) {
                            m.ekBroadcast(line);
                        } else {
                            m.ekSendToHost(line);
                        }
                    } catch (Throwable e) {
                        // ignore
                    }
                }
            }, "ek-cache-send");
            t.setDaemon(true);
            t.start();
        } catch (Throwable e) {
            // ignore
        }
    }

    /** An area's cache from the other side. Never overwrites the area you are standing in. */
    static boolean receiveCache(String line, boolean asHost) {
        try {
            String[] p = line.split("\t", 3);
            if (p.length < 3 || !okLevel(p[1])) {
                return false;
            }
            GameData gd = GameData.O();
            int slot;
            if (asHost) {
                if (gd == null || gd.slot < 0 || gd.slot == GUEST_SLOT || p[1].equals(gd.CurrentLevel)) {
                    return false;
                }
                slot = gd.slot;
            } else if (joinPrepared) {
                slot = GUEST_SLOT; // arriving with the world
            } else if (isGuest() && !p[1].equals(gd.CurrentLevel)) {
                slot = GUEST_SLOT;
            } else {
                return false;
            }
            writeText(new File(cacheDir(slot), p[1] + ".sav"), p[2]);
            return true;
        } catch (Throwable e) {
            return false;
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
        if (EkItems.hostLine(peer, line) || EkTrade.line(line, true) || EkKill.line(line, true)) {
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
                        GameData gd = GameData.O();
                        final File cache = cacheDir(gd.slot);
                        final String here = gd.CurrentLevel;
                        final String wid = hostWorldId(gd);
                        Thread t = new Thread(new Runnable() {
                            public void run() {
                                if (wid != null) {
                                    m.ekSendTo(peer, "EKWID\t" + wid);
                                }
                                sendCaches(m, peer, cache, here);
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
        if (line.startsWith("EKCACHE\t")) {
            if (receiveCache(line, true)) {
                m.ekBroadcast(line); // everyone's copy of that area follows
            }
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
            welcomed = true;
            requestWorldIfReady();
            return false; // the engine handles WELCOME as usual
        }
        if (line.startsWith("EK") && (EkItems.clientLine(line) || EkTrade.line(line, false) || EkKill.line(line, false))) {
            return true;
        }
        if (line.startsWith("EKCACHE\t")) {
            receiveCache(line, false);
            return true;
        }
        if (line.startsWith("EKWID\t")) {
            worldId = line.substring(6).trim();
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
                        writeText(new File(a.getFilesDir(), Serializer.e(GUEST_SLOT, 0)), b64);
                        writeText(blockFile(GUEST_SLOT), block);
                        say("Entering the host's world...");
                        Serializer.a(GUEST_SLOT, 0); // sub 0: LoadGame keeps the cache (other areas)
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
