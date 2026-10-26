package ekvr;

import java.util.EnumMap;
import java.util.Properties;

/**
 * Which Touch controller button does which game function, plus the VR comfort settings
 * (deobf/QUEST_VR_SPEC.md §5.2, §5.4). Saved as ekvr.properties in the game's local storage.
 * A button has at most one function; a function may have several buttons.
 */
public class Binds {
    public enum Btn {
        A("A"), B("B"), X("X"), Y("Y"), RTRIG("R TRIGGER"), LTRIG("L TRIGGER"),
        RGRIP("R GRIP"), LGRIP("L GRIP"), RSTICK("R STICK CLICK"), LSTICK("L STICK CLICK"),
        // gestures (QUEST_VR_SPEC.md §5.2b): detected by VrCore, bound like buttons
        SWING_R("SWING R"), SWING_L("SWING L"), MOUTH_R("MOUTH R"), MOUTH_L("MOUTH L");
        public boolean gesture() { return ordinal() >= SWING_R.ordinal(); }
        public final String label;
        Btn(String l) { label = l; }
    }

    public enum Fn {
        ACT("ATTACK / ACT"), CLICK("POINT & CLICK"), CANCEL("CLOSE / CANCEL"), GAME_MENU("GAME MENU"),
        NEXT_ITEM("NEXT QUICK ITEM"), PREV_ITEM("PREV QUICK ITEM"), USE_ITEM("USE QUICK ITEM"),
        RECOVERY("RECOVERY"), SKILL1("SKILL 1"), SKILL2("SKILL 2"), SKILL3("SKILL 3"), SKILL4("SKILL 4"),
        VIEW("CHANGE VIEW"), RECENTER("RECENTER"), NONE("-");
        public final String label;
        Fn(String l) { label = l; }
    }

    public final EnumMap<Btn, Fn> map = new EnumMap<>(Btn.class);
    public boolean smoothTurn = true, follow = true;
    public int turnSpeed = 90;                       // degrees per second (smooth)
    public int startView = 1;                        // VrCore.Mode ordinal: 0 FIRST, 1 WALK, 2 TABLE
    public boolean moveRight;                        // move with the right stick (turn with the left)
    public boolean moveHead;                         // stick directions relative to the head, not the view
    public int pointer;                              // laser hand: 0 auto (last trigger), 1 right, 2 left
    public int camDist = 1, camHeight = 1;           // third person: index into DISTS / HEIGHTS
    public static final float[] DISTS = {1.6f, 2.4f, 3.2f}, HEIGHTS = {1.5f, 2.0f, 2.5f};
    /** Draw distance = EK's map camera zoom (QUEST_VR_SPEC.md §3): Near, Medium, Far, Max. */
    public static final float[] ZOOMS = {2.6f, 4f, 6f, 9f};
    public static final String[] ZOOM_NAMES = {"NEAR", "MEDIUM", "FAR", "MAX"};
    public int drawDist = 1;
    static final int[] SPEEDS = {60, 90, 120, 180};
    static final String FILE = "ekvr.properties";

    public Binds() { defaults(); }

    public void defaults() {
        map.clear();
        map.put(Btn.RTRIG, Fn.ACT);
        map.put(Btn.A, Fn.CLICK);
        map.put(Btn.B, Fn.CANCEL);
        map.put(Btn.Y, Fn.GAME_MENU);
        map.put(Btn.X, Fn.NEXT_ITEM);
        map.put(Btn.LTRIG, Fn.USE_ITEM);
        map.put(Btn.RGRIP, Fn.SKILL1);
        map.put(Btn.LGRIP, Fn.SKILL2);
        map.put(Btn.RSTICK, Fn.VIEW);
        map.put(Btn.LSTICK, Fn.RECENTER);
        gestureDefaults(null);
        smoothTurn = true; follow = true; turnSpeed = 90; startView = 1;
        moveRight = false; moveHead = false; pointer = 0; camDist = 1; camHeight = 1; drawDist = 1;
        gestures = true; swingSens = 1;
    }

    public boolean gestures = true;
    public int swingSens = 1;                        // index into SWING (m/s): Low, Medium, High
    public static final float[] SWING = {3.0f, 2.2f, 1.6f};
    public static final String[] SENS_NAMES = {"LOW", "MEDIUM", "HIGH"};

    /** Default gesture bindings; with {@code p}, only for gestures that file doesn't mention (older files). */
    void gestureDefaults(Properties p) {
        Object[][] d = {{Btn.SWING_R, Fn.ACT}, {Btn.SWING_L, Fn.ACT}, {Btn.MOUTH_R, Fn.USE_ITEM}, {Btn.MOUTH_L, Fn.USE_ITEM}};
        for (Object[] e : d) if (p == null || p.getProperty("btn." + ((Btn) e[0]).name()) == null) map.put((Btn) e[0], (Fn) e[1]);
    }

    public Fn fn(Btn b) { Fn f = map.get(b); return f == null ? Fn.NONE : f; }

    public void bind(Btn b, Fn f) { map.put(b, f); }

    /** "R TRIGGER, A" — the buttons bound to a function, or "-". */
    public String buttons(Fn f) {
        StringBuilder sb = new StringBuilder();
        for (Btn b : Btn.values()) if (map.get(b) == f) sb.append(sb.length() > 0 ? ", " : "").append(b.label);
        return sb.length() == 0 ? "-" : sb.toString();
    }

    public void nextSpeed() {
        for (int i = 0; i < SPEEDS.length; i++) if (SPEEDS[i] == turnSpeed) { turnSpeed = SPEEDS[(i + 1) % SPEEDS.length]; return; }
        turnSpeed = 90;
    }

    public void load() {
        try {
            com.badlogic.gdx.q.a f = com.badlogic.gdx.Gdx.files.local(FILE);
            if (!f.exists()) return;
            Properties p = new Properties();
            try (java.io.Reader r = new java.io.StringReader(f.readString())) { p.load(r); }
            map.clear();
            for (Btn b : Btn.values()) {
                String v = p.getProperty("btn." + b.name());
                if (v != null) try { Fn fn = Fn.valueOf(v); if (fn != Fn.NONE) map.put(b, fn); } catch (IllegalArgumentException ignored) {}
            }
            gestureDefaults(p);
            gestures = !"off".equals(p.getProperty("gestures", "on"));
            swingSens = Math.max(0, Math.min(2, Integer.parseInt(p.getProperty("swingSens", "1"))));
            smoothTurn = !"snap".equals(p.getProperty("turn", "smooth"));
            follow = !"off".equals(p.getProperty("follow", "on"));
            turnSpeed = Integer.parseInt(p.getProperty("turnSpeed", "90"));
            startView = Math.max(0, Math.min(2, Integer.parseInt(p.getProperty("startView", "1"))));
            moveRight = "right".equals(p.getProperty("moveStick", "left"));
            moveHead = "head".equals(p.getProperty("moveRelative", "view"));
            pointer = Math.max(0, Math.min(2, Integer.parseInt(p.getProperty("pointer", "0"))));
            camDist = Math.max(0, Math.min(2, Integer.parseInt(p.getProperty("camDist", "1"))));
            camHeight = Math.max(0, Math.min(2, Integer.parseInt(p.getProperty("camHeight", "1"))));
            drawDist = Math.max(0, Math.min(3, Integer.parseInt(p.getProperty("drawDist", "1"))));
        } catch (Throwable t) {
            System.out.println("[ekvr] can't read " + FILE + ": " + t);
            defaults();
        }
    }

    public void save() {
        try {
            StringBuilder sb = new StringBuilder("# Exiled Kingdoms VR controls (deobf/QUEST_VR_SPEC.md 5.4)\n");
            for (Btn b : Btn.values()) sb.append("btn.").append(b.name()).append('=').append(fn(b).name()).append('\n');
            sb.append("gestures=").append(gestures ? "on" : "off").append('\n');
            sb.append("swingSens=").append(swingSens).append('\n');
            sb.append("turn=").append(smoothTurn ? "smooth" : "snap").append('\n');
            sb.append("follow=").append(follow ? "on" : "off").append('\n');
            sb.append("turnSpeed=").append(turnSpeed).append('\n');
            sb.append("startView=").append(startView).append('\n');
            sb.append("moveStick=").append(moveRight ? "right" : "left").append('\n');
            sb.append("moveRelative=").append(moveHead ? "head" : "view").append('\n');
            sb.append("pointer=").append(pointer).append('\n');
            sb.append("camDist=").append(camDist).append('\n');
            sb.append("camHeight=").append(camHeight).append('\n');
            sb.append("drawDist=").append(drawDist).append('\n');
            com.badlogic.gdx.Gdx.files.local(FILE).writeString(sb.toString(), false);
        } catch (Throwable t) {
            System.out.println("[ekvr] can't save " + FILE + ": " + t);
        }
    }
}
