package ekvr;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/**
 * Everything VR that is not platform plumbing (deobf/QUEST_VR_SPEC.md §5): where the map
 * sits in the room, the two view modes, the game's UI panel, the Touch controllers mapped
 * onto the game's own touch input, and drawing one eye. The Quest (ekvr.android.VrListener)
 * feeds it OpenXR poses; the desktop test bed feeds it a simulated head and controllers.
 *
 * Stage space = OpenXR LOCAL_FLOOR (metres, y up, floor at 0).
 * World space = Scene3D units (1 = one map cell; X = column, Z = -row).
 */
public class VrCore {
    public interface Touch {
        void down(int x, int y);
        void drag(int x, int y);
        void up(int x, int y);
        void key(int androidKeyCode);
    }

    /** One controller this frame (OpenXR aim pose, stage space). */
    public static class Hand {
        public boolean active;
        public float[] aim = Mat.id();            // aim pose matrix: origin = laser start, -Z = pointing
        public float trigger, grip, sx, sy;
        public boolean a, b, stick, menu;
        public void set(float[] in, int o) {
            active = in[o] > 0.5f;
            aim = Mat.pose(in[o + 1], in[o + 2], in[o + 3], in[o + 4], in[o + 5], in[o + 6], in[o + 7]);
            trigger = in[o + 8]; grip = in[o + 9]; sx = in[o + 10]; sy = in[o + 11];
            a = in[o + 12] > 0.5f; b = in[o + 13] > 0.5f; stick = in[o + 14] > 0.5f; menu = in[o + 15] > 0.5f;
        }
    }

    public enum Mode { TABLE, WALK }

    public final Scene3D scene = new Scene3D();
    public UiLayer ui;
    public final int vw, vh;                       // the game's virtual screen (UI layer pixels)
    public Mode mode = Mode.TABLE;
    public boolean passthrough;                    // TABLE over the real room when the headset can
    public float tableScale = 0.045f;              // metres per cell on the table
    float yaw;                                     // board / world rotation about Y
    final float[] anchor = {0, 0.75f, -0.6f};      // TABLE: board centre; WALK: hero's spot on the floor
    final float[] head = {0, 1.2f, 0};
    float headYaw;
    float fx, fz;                                  // smoothed hero position (world)
    boolean placed, haveFocus;
    public final Hand left = new Hand(), right = new Hand();
    public Touch touch;
    // UI panel (stage space): centre, right and up half-extent vectors
    final float[] pc = new float[3], pr = new float[3], pu = new float[3];
    // pointer state
    boolean trigHeld, stickHeld, aWas, bWas, menuWas, clickWas, turnLatch, gripWas;
    int lastX, lastY;
    final float[] grabFrom = new float[3];
    float[] laserEnd;
    public String hover = "";                       // what the laser is on: "ui", "ground", ""

    public VrCore(int vw, int vh) { this.vw = vw; this.vh = vh; }

    // ---- per frame -------------------------------------------------------------------------
    /** Run the real game for one frame: map recorded for 3D, UI into the layer. */
    public void gameFrame(Runnable renderGame, int defaultFbo) {
        if (ui == null) ui = new UiLayer(vw, vh);
        scene.beginFrame();
        ui.begin();
        renderGame.run();
        ui.end(defaultFbo);
        scene.endFrame();
        if (!haveFocus) { fx = scene.heroX; fz = scene.heroZ; haveFocus = true; }
        float k = mode == Mode.WALK ? 0.08f : 0.25f;          // follow: gentler when walking inside
        fx += (scene.heroX - fx) * k;
        fz += (scene.heroZ - fz) * k;
    }

    /** World (map units) -> stage (metres). */
    public float[] world() {
        float s = mode == Mode.TABLE ? tableScale : 1f;
        return Mat.mul(Mat.translate(anchor[0], anchor[1], anchor[2]),
                Mat.mul(Mat.rotY(yaw), Mat.mul(Mat.scale(s), Mat.translate(-fx, 0, -fz))));
    }

    /** Place the board / walk spot in front of the user (start, and left-stick click). */
    public void recenter(float[] headPose) {
        head[0] = headPose[12]; head[1] = headPose[13]; head[2] = headPose[14];
        float fx_ = -headPose[8], fz_ = -headPose[10];                      // head forward (−Z axis)
        float l = (float) Math.sqrt(fx_ * fx_ + fz_ * fz_);
        if (l < 1e-3) { fx_ = 0; fz_ = -1; l = 1; }
        fx_ /= l; fz_ /= l;
        headYaw = (float) Math.atan2(-fx_, -fz_);
        if (mode == Mode.TABLE) {
            anchor[0] = head[0] + fx_ * 0.6f; anchor[1] = Math.max(0.5f, head[1] - 0.5f); anchor[2] = head[2] + fz_ * 0.6f;
        } else {
            anchor[0] = head[0] + fx_ * 2.6f; anchor[1] = 0; anchor[2] = head[2] + fz_ * 2.6f;
        }
        // the map's "up the screen" direction (iso view: world -X,-Z) points away from the user
        yaw = headYaw - (float) Math.toRadians(45);
        placed = true;
        placePanel(fx_, fz_);
    }

    void placePanel(float fx_, float fz_) {
        float w, h, dist, y;
        // TABLE: like a screen standing behind the board; WALK: a large screen ahead, a bit low
        if (mode == Mode.TABLE) { w = 1.0f; dist = 1.15f; y = anchor[1] + 0.42f; }
        else { w = 1.6f; dist = 1.5f; y = head[1] - 0.1f; }
        h = w * vh / vw;
        pc[0] = head[0] + fx_ * dist; pc[1] = y; pc[2] = head[2] + fz_ * dist;
        pr[0] = -fz_ * w / 2; pr[1] = 0; pr[2] = fx_ * w / 2;              // right of the user
        pu[0] = 0; pu[1] = h / 2; pu[2] = 0;
    }

    public void setMode(Mode m, float[] headPose) { mode = m; passthrough = m == Mode.TABLE; recenter(headPose); }

    // ---- input -------------------------------------------------------------------------------
    /** Map the controllers onto the game. {@code headPose} is the head (centre eye) matrix. */
    public void input(float[] headPose, float dt) {
        if (!placed) recenter(headPose);
        head[0] = headPose[12]; head[1] = headPose[13]; head[2] = headPose[14];
        // left stick click: recenter;  right stick X: snap turn;  Y: zoom (table)
        if (left.stick && !clickWas) recenter(headPose);
        clickWas = left.stick;
        if (Math.abs(right.sx) > 0.7f) {
            if (!turnLatch) { yaw += (float) Math.toRadians(right.sx > 0 ? -30 : 30); turnLatch = true; }
        } else turnLatch = false;
        if (mode == Mode.TABLE && Math.abs(right.sy) > 0.4f)
            tableScale = Math.max(0.015f, Math.min(0.2f, tableScale * (1 + right.sy * dt * 1.2f)));
        // left grip: move the board with your hand
        if (mode == Mode.TABLE && left.active) {
            float[] p = {left.aim[12], left.aim[13], left.aim[14]};
            if (left.grip > 0.6f) {
                if (!gripWas) { grabFrom[0] = p[0] - anchor[0]; grabFrom[1] = p[1] - anchor[1]; grabFrom[2] = p[2] - anchor[2]; }
                else { anchor[0] = p[0] - grabFrom[0]; anchor[1] = p[1] - grabFrom[1]; anchor[2] = p[2] - grabFrom[2]; }
                gripWas = true;
            } else gripWas = false;
        }
        // A: the HUD attack button; B / Y / menu: the phone's back key (close window, game menu)
        if (right.a && !aWas) tap(Math.round(vw * 0.957f), Math.round(vh * 0.9375f));
        aWas = right.a;
        boolean back = right.b || left.b || left.menu;
        if (back && !bWas && touch != null) touch.key(4);
        bWas = back;
        if (left.a && !menuWas) tap(Math.round(vw * 0.03f), Math.round(vh * 0.79f));   // X: the HUD potion slot
        menuWas = left.a;
        // right trigger: laser click (UI first, else the ground under the laser)
        int[] at = right.active ? laserTarget(right.aim) : null;
        boolean trig = right.trigger > 0.6f;
        if (touch != null) {
            if (trig && !trigHeld && at != null) { touch.down(at[0], at[1]); lastX = at[0]; lastY = at[1]; trigHeld = true; }
            else if (trig && trigHeld && at != null && (at[0] != lastX || at[1] != lastY)) { touch.drag(at[0], at[1]); lastX = at[0]; lastY = at[1]; }
            else if (!trig && trigHeld) { touch.up(lastX, lastY); trigHeld = false; }
        }
        // left stick: walk — hold a touch on the ground ahead of the hero (EK's hold-to-move)
        float m = (float) Math.sqrt(left.sx * left.sx + left.sy * left.sy);
        if (touch != null && !trigHeld) {
            if (m > 0.3f) {
                float hy = headYawOf(headPose);
                float dx = (float) (Math.cos(hy) * left.sx - Math.sin(hy) * left.sy);
                float dz = (float) (-Math.sin(hy) * left.sx - Math.cos(hy) * left.sy);
                // stage direction -> world direction (undo the board yaw)
                float c = (float) Math.cos(-yaw), s = (float) Math.sin(-yaw);
                float wx = c * dx + s * dz, wz = -s * dx + c * dz;
                float reach = 2.5f;
                int[] p = UiLayer.groundToTouch(scene.heroX + wx * reach, scene.heroZ + wz * reach, vw, vh);
                if (p == null) return;
                if (!stickHeld) { touch.down(p[0], p[1]); stickHeld = true; } else touch.drag(p[0], p[1]);
                lastX = p[0]; lastY = p[1];
            } else if (stickHeld) { touch.up(lastX, lastY); stickHeld = false; }
        }
    }

    void tap(int x, int y) { if (touch != null) { touch.down(x, y); touch.up(x, y); } }

    /** Yaw of a pose's forward (−Z) direction: forward = (−sin h, −cos h). */
    static float headYawOf(float[] pose) {
        return (float) Math.atan2(pose[8], pose[10]);
    }

    /** Where the laser lands, as a pixel of the game's screen (UI or the ground), or null. */
    int[] laserTarget(float[] aim) {
        float ox = aim[12], oy = aim[13], oz = aim[14];
        float dx = -aim[8], dy = -aim[9], dz = -aim[10];
        // 1. the UI panel, where the layer is opaque
        float nx = pr[1] * pu[2] - pr[2] * pu[1], ny = pr[2] * pu[0] - pr[0] * pu[2], nz = pr[0] * pu[1] - pr[1] * pu[0];
        float den = nx * dx + ny * dy + nz * dz;
        if (Math.abs(den) > 1e-6) {
            float t = (nx * (pc[0] - ox) + ny * (pc[1] - oy) + nz * (pc[2] - oz)) / den;
            if (t > 0) {
                float hx = ox + dx * t - pc[0], hy = oy + dy * t - pc[1], hz = oz + dz * t - pc[2];
                float u = (hx * pr[0] + hy * pr[1] + hz * pr[2]) / (pr[0] * pr[0] + pr[1] * pr[1] + pr[2] * pr[2]);
                float v = (hx * pu[0] + hy * pu[1] + hz * pu[2]) / (pu[0] * pu[0] + pu[1] * pu[1] + pu[2] * pu[2]);
                if (Math.abs(u) <= 1 && Math.abs(v) <= 1) {
                    int px = Math.round((u + 1) / 2 * (vw - 1)), py = Math.round((1 - v) / 2 * (vh - 1));
                    if (ui != null && ui.opaqueAt(px, py)) {
                        laserEnd = new float[]{ox + dx * t, oy + dy * t, oz + dz * t};
                        hover = "ui";
                        return new int[]{px, py};
                    }
                }
            }
        }
        // 2. the map's ground plane
        float[] w = world();
        float[] inv = invertAffine(w);
        float[] o = Mat.xform(inv, ox, oy, oz);
        float[] o2 = Mat.xform(inv, ox + dx, oy + dy, oz + dz);
        float ddx = o2[0] - o[0], ddy = o2[1] - o[1], ddz = o2[2] - o[2];
        if (Math.abs(ddy) > 1e-6) {
            float t = -o[1] / ddy;
            if (t > 0) {
                float gx = o[0] + ddx * t, gz = o[2] + ddz * t;
                int[] p = UiLayer.groundToTouch(gx, gz, vw, vh);
                if (p != null) {
                    laserEnd = Mat.xform(w, gx, 0, gz);
                    hover = "ground";
                    return p;
                }
            }
        }
        laserEnd = new float[]{ox + dx * 3, oy + dy * 3, oz + dz * 3};
        hover = "";
        return null;
    }

    static float[] invertAffine(float[] m) {
        // general 3x3 + translation inverse (world() includes uniform scale)
        float a = m[0], b = m[4], c = m[8], d = m[1], e = m[5], f = m[9], g = m[2], h = m[6], i = m[10];
        float det = a * (e * i - f * h) - b * (d * i - f * g) + c * (d * h - e * g);
        float[] r = Mat.id();
        r[0] = (e * i - f * h) / det; r[4] = (c * h - b * i) / det; r[8] = (b * f - c * e) / det;
        r[1] = (f * g - d * i) / det; r[5] = (a * i - c * g) / det; r[9] = (c * d - a * f) / det;
        r[2] = (d * h - e * g) / det; r[6] = (b * g - a * h) / det; r[10] = (a * e - b * d) / det;
        r[12] = -(r[0] * m[12] + r[4] * m[13] + r[8] * m[14]);
        r[13] = -(r[1] * m[12] + r[5] * m[13] + r[9] * m[14]);
        r[14] = -(r[2] * m[12] + r[6] * m[13] + r[10] * m[14]);
        return r;
    }

    // ---- drawing one eye -------------------------------------------------------------------
    int lineProg, lPos, lMvp, lCol;
    final FloatBuffer lineBuf = ByteBuffer.allocateDirect(64).order(ByteOrder.nativeOrder()).asFloatBuffer();

    /** Draw one eye into the bound framebuffer. {@code view} = stage -> eye; {@code proj} = eye projection. */
    public void drawEye(float[] proj, float[] view, int w, int h) {
        GL20 gl = UiLayer.gl();
        gl.glViewport(0, 0, w, h);
        if (passthrough && mode == Mode.TABLE) gl.glClearColor(0, 0, 0, 0);
        else gl.glClearColor(0.42f * scene.tint[0], 0.55f * scene.tint[1], 0.72f * scene.tint[2], 1);
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        float[] pv = Mat.mul(proj, view);
        float[] wm = world();
        float[] inv = invertAffine(wm);
        float[] eye = Mat.xform(Mat.rigidInverse(view), 0, 0, 0);
        float[] eyeW = Mat.xform(inv, eye[0], eye[1], eye[2]);
        scene.draw(Mat.mul(pv, wm), eyeW[0], eyeW[2]);
        if (ui != null) {
            float[] tl = {pc[0] - pr[0] + pu[0], pc[1] - pr[1] + pu[1], pc[2] - pr[2] + pu[2]};
            float[] tr = {pc[0] + pr[0] + pu[0], pc[1] + pr[1] + pu[1], pc[2] + pr[2] + pu[2]};
            float[] br = {pc[0] + pr[0] - pu[0], pc[1] + pr[1] - pu[1], pc[2] + pr[2] - pu[2]};
            float[] bl = {pc[0] - pr[0] - pu[0], pc[1] - pr[1] - pu[1], pc[2] - pr[2] - pu[2]};
            ui.draw(pv, tl, tr, br, bl);
        }
        if (right.active && laserEnd != null) line(gl, pv, right.aim[12], right.aim[13], right.aim[14], laserEnd);
    }

    void line(GL20 gl, float[] pv, float x, float y, float z, float[] end) {
        if (lineProg == 0) {
            int vs = Scene3D.shader(gl, GL20.GL_VERTEX_SHADER, "attribute vec3 a_pos; uniform mat4 u_mvp; void main(){ gl_Position=u_mvp*vec4(a_pos,1.0); gl_PointSize=9.0; }");
            int fs = Scene3D.shader(gl, GL20.GL_FRAGMENT_SHADER, "#ifdef GL_ES\nprecision mediump float;\n#endif\nuniform vec4 u_col; void main(){ gl_FragColor=u_col; }");
            lineProg = gl.glCreateProgram();
            gl.glAttachShader(lineProg, vs); gl.glAttachShader(lineProg, fs); gl.glLinkProgram(lineProg);
            lPos = gl.glGetAttribLocation(lineProg, "a_pos");
            lMvp = gl.glGetUniformLocation(lineProg, "u_mvp");
            lCol = gl.glGetUniformLocation(lineProg, "u_col");
        }
        UiLayer.clientArrays(gl);
        gl.glUseProgram(lineProg);
        gl.glUniformMatrix4fv(lMvp, 1, false, pv, 0);
        gl.glUniform4f(lCol, 1f, 0.85f, 0.4f, 1f);
        lineBuf.clear();
        lineBuf.put(x).put(y).put(z).put(end[0]).put(end[1]).put(end[2]);
        lineBuf.position(0);
        gl.glEnableVertexAttribArray(lPos);
        gl.glVertexAttribPointer(lPos, 3, GL20.GL_FLOAT, false, 12, lineBuf);
        gl.glLineWidth(3f);
        gl.glDrawArrays(GL20.GL_LINES, 0, 2);
        gl.glDrawArrays(GL20.GL_POINTS, 1, 1);
    }
}
