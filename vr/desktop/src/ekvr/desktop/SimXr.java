package ekvr.desktop;

import ekvr.Mat;
import ekvr.VrCore;

/**
 * A simulated Quest for the desktop test bed (-Dekvr.mode=vr): a seated head at 1.2 m and a
 * right controller whose laser follows the mouse (or a script's "aim"). Drives the same
 * ekvr.VrCore the headset build uses, rendering one eye to the window.
 * Keys: WASD = left stick, mouse = right laser + trigger, Space = A, Esc = B, X = X,
 * Q/E = turn head, M = next view (first person / third person / table), C = recenter.
 */
public class SimXr {
    public static VrCore core;
    public static float headYaw = 0, headPitch = -38;
    public static float stickX, stickY;
    public static int aimX = -1, aimY = -1;
    public static float[] aimStage, aimFrom;        // script "aimsprite": aim at a stage point (from a point) instead
    public static boolean trigger, a, b, x, click;
    static float[] proj, view, headPose;

    static void frame(com.badlogic.gdx.a game, int w, int h, float dt) {
        if (core == null) {
            core = new VrCore(w, h);
            core.touch = new VrCore.Touch() {
                public void down(int x, int y) { DeskInput.rawPress(x, y); }
                public void drag(int x, int y) { DeskInput.rawMove(x, y); }
                public void up(int x, int y) { DeskInput.rawRelease(x, y); }
                public void key(int code) { DeskInput.key(code, true); DeskInput.key(code, false); }
            };
            String vm = System.getProperty("ekvr.vrmode");
            if (vm != null) core.mode = VrCore.Mode.valueOf(vm.toUpperCase());
        }
        core.gameFrame(game::b, 0);
        headPose = Mat.mul(Mat.translate(0, 1.2f, 0),
                Mat.mul(Mat.rotY((float) Math.toRadians(headYaw)), rotX((float) Math.toRadians(headPitch))));
        view = Mat.rigidInverse(headPose);
        proj = Mat.perspective(70, w / (float) h, 0.05f, 300f);
        // right controller: from just below the head, pointing through the aimed pixel
        VrCore.Hand r = core.right;
        r.active = aimX >= 0 || aimStage != null;
        if (aimStage != null) {
            float[] o = aimFrom != null ? aimFrom : new float[]{0.18f, 1.0f, -0.1f};
            r.aim = Mat.rigidInverse(Mat.lookAt(o[0], o[1], o[2], aimStage[0], aimStage[1], aimStage[2]));
        } else if (r.active) {
            float[] inv = DeskBackend.invert(Mat.mul(proj, view));
            float nx = 2f * aimX / w - 1, ny = 1 - 2f * aimY / h;
            float[] p0 = DeskBackend.unproject(inv, nx, ny, -1), p1 = DeskBackend.unproject(inv, nx, ny, 1);
            float[] o = {0.18f, 1.0f, -0.1f};
            float dx = p1[0] - p0[0], dy = p1[1] - p0[1], dz = p1[2] - p0[2];
            // aim the laser from the hand to where the mouse ray meets a point 3 m out
            float l = (float) Math.sqrt(dx * dx + dy * dy + dz * dz);
            float tx = p0[0] + dx / l * 3 - o[0], ty = p0[1] + dy / l * 3 - o[1], tz = p0[2] + dz / l * 3 - o[2];
            r.aim = Mat.rigidInverse(Mat.lookAt(o[0], o[1], o[2], o[0] + tx, o[1] + ty, o[2] + tz));
        }
        r.trigger = trigger ? 1 : 0;
        r.a = a; r.b = b;
        core.left.sx = stickX; core.left.sy = stickY;
        core.left.a = x; core.left.stick = click;
        core.input(headPose, dt);
        core.drawEye(proj, view, w, h);
    }

    static float[] rotX(float rad) {
        float[] m = Mat.id();
        float c = (float) Math.cos(rad), s = (float) Math.sin(rad);
        m[5] = c; m[6] = s; m[9] = -s; m[10] = c;
        return m;
    }
}
