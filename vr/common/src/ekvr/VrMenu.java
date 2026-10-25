package ekvr;

import com.badlogic.gdx.graphics.GL20;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;

/**
 * The VR controls screen (deobf/QUEST_VR_SPEC.md §5.4): remap the Touch buttons like an
 * emulator (pick a function, press the button you want) and set turning / camera options.
 * Drawn into its own texture with a built-in 5x7 pixel font, so it needs nothing from the game.
 */
public class VrMenu {
    static final String CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789-/&:,.()><?=";
    static final int[][] FONT = {
            {14, 17, 17, 31, 17, 17, 17},   // A
            {30, 17, 17, 30, 17, 17, 30},   // B
            {14, 17, 16, 16, 16, 17, 14},   // C
            {30, 17, 17, 17, 17, 17, 30},   // D
            {31, 16, 16, 30, 16, 16, 31},   // E
            {31, 16, 16, 30, 16, 16, 16},   // F
            {14, 17, 16, 23, 17, 17, 15},   // G
            {17, 17, 17, 31, 17, 17, 17},   // H
            {14, 4, 4, 4, 4, 4, 14},   // I
            {7, 2, 2, 2, 2, 18, 12},   // J
            {17, 18, 20, 24, 20, 18, 17},   // K
            {16, 16, 16, 16, 16, 16, 31},   // L
            {17, 27, 21, 21, 17, 17, 17},   // M
            {17, 17, 25, 21, 19, 17, 17},   // N
            {14, 17, 17, 17, 17, 17, 14},   // O
            {30, 17, 17, 30, 16, 16, 16},   // P
            {14, 17, 17, 17, 21, 18, 13},   // Q
            {30, 17, 17, 30, 20, 18, 17},   // R
            {15, 16, 16, 14, 1, 1, 30},   // S
            {31, 4, 4, 4, 4, 4, 4},   // T
            {17, 17, 17, 17, 17, 17, 14},   // U
            {17, 17, 17, 17, 17, 10, 4},   // V
            {17, 17, 17, 21, 21, 21, 10},   // W
            {17, 17, 10, 4, 10, 17, 17},   // X
            {17, 17, 10, 4, 4, 4, 4},   // Y
            {31, 1, 2, 4, 8, 16, 31},   // Z
            {14, 17, 19, 21, 25, 17, 14},   // 0
            {4, 12, 4, 4, 4, 4, 14},   // 1
            {14, 17, 1, 2, 4, 8, 31},   // 2
            {31, 2, 4, 2, 1, 17, 14},   // 3
            {2, 6, 10, 18, 31, 2, 2},   // 4
            {31, 16, 30, 1, 1, 17, 14},   // 5
            {6, 8, 16, 30, 17, 17, 14},   // 6
            {31, 1, 2, 4, 8, 8, 8},   // 7
            {14, 17, 17, 14, 17, 17, 14},   // 8
            {14, 17, 17, 15, 1, 2, 12},   // 9
            {0, 0, 0, 31, 0, 0, 0},   // -
            {1, 1, 2, 4, 8, 16, 16},   // /
            {12, 18, 20, 8, 21, 18, 13},   // &
            {0, 12, 12, 0, 12, 12, 0},   // :
            {0, 0, 0, 0, 12, 4, 8},   // ,
            {0, 0, 0, 0, 0, 12, 12},   // .
            {2, 4, 8, 8, 8, 4, 2},   // (
            {8, 4, 2, 2, 2, 4, 8},   // )
            {8, 4, 2, 1, 2, 4, 8},   // >
            {2, 4, 8, 16, 8, 4, 2},   // <
            {14, 17, 1, 2, 4, 0, 4},   // ?
            {0, 0, 31, 0, 31, 0, 0},   // =
    };
    static final int TW = 640, TH = 646, SC = 2, CW = 6 * SC, ROW = 22, TOP = 52;

    public boolean open;
    final Binds binds;
    /** Function being rebound (waiting for a button), or null. */
    Binds.Fn waiting;
    boolean released;                      // while waiting: every button has been let go since
    int hover = -1;
    /** Set when a row asks VrCore to recenter (calibrate) the view. */
    public boolean recenter;
    String note = "";
    final ArrayList<Object> rows = new ArrayList<>();   // Binds.Fn, or a String option key
    int tex;
    boolean dirty = true;
    final ByteBuffer px = ByteBuffer.allocateDirect(TW * TH * 4).order(ByteOrder.nativeOrder());
    // panel (stage space): centre, right and up half-extents
    final float[] c = new float[3], r = new float[3], u = new float[3];

    public VrMenu(Binds b) {
        binds = b;
        for (Binds.Fn f : Binds.Fn.values()) if (f != Binds.Fn.NONE) rows.add(f);
        rows.add("turn"); rows.add("speed"); rows.add("follow"); rows.add("view");
        rows.add("move"); rows.add("rel"); rows.add("pointer"); rows.add("dist"); rows.add("height");
        rows.add("recenter"); rows.add("reset"); rows.add("close");
    }

    /** Open in front of the head (stage space). */
    public void show(float[] head, float fx, float fz) {
        open = true; waiting = null; note = ""; dirty = true;
        float w = 0.95f, h = w * TH / TW, d = 1.25f;
        c[0] = head[0] + fx * d; c[1] = head[1] - 0.05f; c[2] = head[2] + fz * d;
        r[0] = -fz * w / 2; r[1] = 0; r[2] = fx * w / 2;
        u[0] = 0; u[1] = h / 2; u[2] = 0;
    }

    /** (tests) the stage point at the middle of row i's label. */
    public float[] rowPoint(int i) {
        float pxl = 150, pyl = TOP + i * ROW + 8;
        float uu = pxl / (TW - 1) * 2 - 1, vv = 1 - pyl / (TH - 1) * 2;
        return new float[]{c[0] + r[0] * uu + u[0] * vv, c[1] + r[1] * uu + u[1] * vv, c[2] + r[2] * uu + u[2] * vv};
    }

    public void close() { open = false; waiting = null; }

    /** Laser hit on the panel as texture pixel (x, y), or null. */
    int[] hit(float[] aim, float[] end) {
        float[] uv = VrCore.panelHit(aim, c, r, u, end);
        if (uv == null) return null;
        return new int[]{Math.round((uv[0] + 1) / 2 * (TW - 1)), Math.round((1 - uv[1]) / 2 * (TH - 1))};
    }

    void setHover(int[] p) {
        int h = p == null ? -1 : (p[1] - TOP) / ROW;
        if (p != null && (p[1] < TOP || h >= rows.size())) h = -1;
        if (h != hover) { hover = h; dirty = true; }
    }

    /** Trigger on the hovered row. */
    void click() {
        if (hover < 0) return;
        Object o = rows.get(hover);
        if (o instanceof Binds.Fn) { waiting = (Binds.Fn) o; released = false; note = ""; }
        else switch ((String) o) {
            case "turn": binds.smoothTurn = !binds.smoothTurn; break;
            case "speed": binds.nextSpeed(); break;
            case "follow": binds.follow = !binds.follow; break;
            case "view": binds.startView = (binds.startView + 2) % 3; break;   // third -> first -> table
            case "move": binds.moveRight = !binds.moveRight; break;
            case "rel": binds.moveHead = !binds.moveHead; break;
            case "pointer": binds.pointer = (binds.pointer + 1) % 3; break;
            case "dist": binds.camDist = (binds.camDist + 1) % 3; recenter = true; break;
            case "height": binds.camHeight = (binds.camHeight + 1) % 3; recenter = true; break;
            case "recenter": recenter = true; note = "RECENTERED: FORWARD IS WHERE YOU LOOK"; break;
            case "reset": binds.defaults(); note = "DEFAULTS RESTORED"; recenter = true; break;
            case "close": close(); break;
        }
        binds.save();
        dirty = true;
    }

    /** While waiting for a button: feed the pressed buttons (null = none). Returns true once bound. */
    boolean capture(Binds.Btn pressed) {
        if (waiting == null) return false;
        if (pressed == null) { released = true; return false; }
        if (!released) return false;
        binds.bind(pressed, waiting);
        note = pressed.label + " = " + waiting.label;
        waiting = null;
        binds.save();
        dirty = true;
        return true;
    }

    static String viewName(int v) { return v == 0 ? "FIRST PERSON" : v == 2 ? "TABLETOP" : "THIRD PERSON"; }

    // ---- drawing ---------------------------------------------------------------------------
    void fill(int x0, int y0, int x1, int y1, int rr, int gg, int bb, int aa) {
        x0 = Math.max(0, x0); y0 = Math.max(0, y0); x1 = Math.min(TW, x1); y1 = Math.min(TH, y1);
        for (int y = y0; y < y1; y++)
            for (int x = x0; x < x1; x++) {
                int o = (y * TW + x) * 4;
                // premultiplied alpha (the panel is drawn with ONE, ONE_MINUS_SRC_ALPHA)
                px.put(o, (byte) (rr * aa / 255)); px.put(o + 1, (byte) (gg * aa / 255));
                px.put(o + 2, (byte) (bb * aa / 255)); px.put(o + 3, (byte) aa);
            }
    }

    void text(int x, int y, String s, int rr, int gg, int bb) {
        for (int i = 0; i < s.length(); i++, x += CW) {
            int k = CHARS.indexOf(Character.toUpperCase(s.charAt(i)));
            if (k < 0) continue;
            int[] g = FONT[k];
            for (int row = 0; row < 7; row++)
                for (int col = 0; col < 5; col++)
                    if ((g[row] >> (4 - col) & 1) != 0) fill(x + col * SC, y + row * SC, x + col * SC + SC, y + row * SC + SC, rr, gg, bb, 255);
        }
    }

    void render() {
        fill(0, 0, TW, TH, 12, 14, 22, 235);
        fill(0, 0, TW, 4, 200, 160, 70, 255);
        text(16, 12, "VR CONTROLS", 240, 200, 110);
        text(16, 30, waiting != null ? "PRESS A BUTTON FOR " + waiting.label + "   (MENU = CANCEL)"
                : note.length() > 0 ? note : "POINT + TRIGGER TO CHANGE.  MENU = CLOSE", 170, 175, 190);
        for (int i = 0; i < rows.size(); i++) {
            int y = TOP + i * ROW;
            Object o = rows.get(i);
            boolean w = o == waiting;
            if (i == hover || w) fill(8, y - 3, TW - 8, y + ROW - 5, w ? 90 : 50, w ? 70 : 60, w ? 20 : 90, 255);
            String left, right;
            if (o instanceof Binds.Fn) {
                left = ((Binds.Fn) o).label;
                right = w ? "PRESS A BUTTON..." : binds.buttons((Binds.Fn) o);
            } else switch ((String) o) {
                case "turn": left = "TURNING"; right = binds.smoothTurn ? "SMOOTH" : "SNAP 30"; break;
                case "speed": left = "TURN SPEED"; right = binds.turnSpeed + " DEG/S"; break;
                case "follow": left = "CAMERA FOLLOWS HERO"; right = binds.follow ? "ON" : "OFF"; break;
                case "view": left = "START VIEW"; right = viewName(binds.startView); break;
                case "move": left = "MOVE WITH"; right = binds.moveRight ? "RIGHT STICK (TURN: LEFT)" : "LEFT STICK (TURN: RIGHT)"; break;
                case "rel": left = "MOVE RELATIVE TO"; right = binds.moveHead ? "HEAD" : "VIEW"; break;
                case "pointer": left = "POINTER HAND"; right = binds.pointer == 1 ? "RIGHT" : binds.pointer == 2 ? "LEFT" : "AUTO (LAST TRIGGER)"; break;
                case "dist": left = "CAMERA DISTANCE"; right = Binds.DISTS[binds.camDist] + " M"; break;
                case "height": left = "CAMERA HEIGHT"; right = Binds.HEIGHTS[binds.camHeight] + " M"; break;
                case "recenter": left = "RECENTER NOW"; right = ""; break;
                case "reset": left = "RESET TO DEFAULTS"; right = ""; break;
                default: left = "CLOSE"; right = ""; break;
            }
            boolean opt = !(o instanceof Binds.Fn);
            text(20, y + 2, left, opt ? 150 : 230, opt ? 200 : 230, opt ? 240 : 230);
            text(300, y + 2, right, w ? 255 : 240, w ? 220 : 200, w ? 90 : 110);
        }
    }

    /** Draw the panel (stage space) through {@code ui}'s textured-quad shader. */
    public void draw(UiLayer ui, float[] pv) {
        GL20 gl = UiLayer.gl();
        if (tex == 0) {
            java.nio.IntBuffer ib = ByteBuffer.allocateDirect(4).order(ByteOrder.nativeOrder()).asIntBuffer();
            gl.glGenTextures(1, ib);
            tex = ib.get(0);
            gl.glBindTexture(GL20.GL_TEXTURE_2D, tex);
            gl.glTexParameteri(GL20.GL_TEXTURE_2D, GL20.GL_TEXTURE_MIN_FILTER, GL20.GL_LINEAR);
            gl.glTexParameteri(GL20.GL_TEXTURE_2D, GL20.GL_TEXTURE_MAG_FILTER, GL20.GL_LINEAR);
            gl.glTexParameteri(GL20.GL_TEXTURE_2D, GL20.GL_TEXTURE_WRAP_S, GL20.GL_CLAMP_TO_EDGE);
            gl.glTexParameteri(GL20.GL_TEXTURE_2D, GL20.GL_TEXTURE_WRAP_T, GL20.GL_CLAMP_TO_EDGE);
            dirty = true;
        }
        if (dirty) {
            render();
            gl.glBindTexture(GL20.GL_TEXTURE_2D, tex);
            gl.glPixelStorei(GL20.GL_UNPACK_ALIGNMENT, 4);
            px.position(0);
            gl.glTexImage2D(GL20.GL_TEXTURE_2D, 0, GL20.GL_RGBA, TW, TH, 0, GL20.GL_RGBA, GL20.GL_UNSIGNED_BYTE, px);
            dirty = false;
        }
        // texture row 0 = the top of the panel
        float[] tl = {c[0] - r[0] + u[0], c[1] - r[1] + u[1], c[2] - r[2] + u[2]};
        float[] tr = {c[0] + r[0] + u[0], c[1] + r[1] + u[1], c[2] + r[2] + u[2]};
        float[] br = {c[0] + r[0] - u[0], c[1] + r[1] - u[1], c[2] + r[2] - u[2]};
        float[] bl = {c[0] - r[0] - u[0], c[1] - r[1] - u[1], c[2] - r[2] - u[2]};
        ui.drawTexture(tex, pv, tl, tr, br, bl);   // texture row 0 (v = 0) is the panel's top
    }
}
