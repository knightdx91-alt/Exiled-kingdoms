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
        RGRIP("R GRIP"), LGRIP("L GRIP"), RSTICK("R STICK CLICK"), LSTICK("L STICK CLICK");
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
        smoothTurn = true; follow = true; turnSpeed = 90; startView = 1;
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
            smoothTurn = !"snap".equals(p.getProperty("turn", "smooth"));
            follow = !"off".equals(p.getProperty("follow", "on"));
            turnSpeed = Integer.parseInt(p.getProperty("turnSpeed", "90"));
            startView = Math.max(0, Math.min(2, Integer.parseInt(p.getProperty("startView", "1"))));
        } catch (Throwable t) {
            System.out.println("[ekvr] can't read " + FILE + ": " + t);
            defaults();
        }
    }

    public void save() {
        try {
            StringBuilder sb = new StringBuilder("# Exiled Kingdoms VR controls (deobf/QUEST_VR_SPEC.md 5.4)\n");
            for (Btn b : Btn.values()) sb.append("btn.").append(b.name()).append('=').append(fn(b).name()).append('\n');
            sb.append("turn=").append(smoothTurn ? "smooth" : "snap").append('\n');
            sb.append("follow=").append(follow ? "on" : "off").append('\n');
            sb.append("turnSpeed=").append(turnSpeed).append('\n');
            sb.append("startView=").append(startView).append('\n');
            com.badlogic.gdx.Gdx.files.local(FILE).writeString(sb.toString(), false);
        } catch (Throwable t) {
            System.out.println("[ekvr] can't save " + FILE + ": " + t);
        }
    }
}
