package ekvr;

import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.ControllerManager;
import com.badlogic.gdx.controllers.PovDirection;

/**
 * A virtual gamepad the game reads through its own controller support
 * (ExiledKingdoms.a(ControllerCommand) / b(ControllerCommand)): the Quest Touch controllers
 * (or a test script) set its axes and buttons. Installed as the libGDX ControllerManager for
 * our Application, so Controllers never tries to load AndroidControllers (which needs an
 * AndroidApplication's View). Obfuscated names: Controller.a(int)=getPov, b(int)=getButton,
 * c(int)=getAxis; ControllerManager.a()=getControllers; ObjectMap(z).b(K,V)=put.
 */
public class Pad implements Controller {
    public static final Pad PAD = new Pad();
    public final float[] axes = new float[8];
    public final boolean[] buttons = new boolean[256];   // EK uses Android gamepad key codes: A = 96 ...
    public boolean connected;

    public PovDirection a(int pov) { return PovDirection.values()[0]; }
    public boolean b(int button) { return button >= 0 && button < buttons.length && buttons[button]; }
    public float c(int axis) { return axis >= 0 && axis < axes.length ? axes[axis] : 0f; }
    public String getName() { return "Quest Touch"; }

    /** Release everything (call once per frame before setting this frame's input). */
    public void clear() { java.util.Arrays.fill(axes, 0f); java.util.Arrays.fill(buttons, false); }

    /**
     * Drive one of the game's own controller commands (Settings.a = ControllerConfig, so a
     * custom data/config/controls.ini is honoured): v in 0..1. Command types by ordinal:
     * 0 NEGATIVE_AXIS, 1 POSITIVE_AXIS, 2 BUTTON (3 KEY, 4 POV: not driven).
     */
    public void set(net.fdgames.ek.ControllerCommand c, float v) {
        if (c == null || c.type == null || v <= 0) return;
        int t = c.type.ordinal();
        if (t == 0 && c.id >= 0 && c.id < axes.length) axes[c.id] = -v;
        else if (t == 1 && c.id >= 0 && c.id < axes.length) axes[c.id] = v;
        else if (t == 2 && c.id >= 0 && c.id < buttons.length) buttons[c.id] = true;
    }

    /** Is the game reading this pad (ExiledKingdoms.n)? Makes it so when we are installed. */
    public static boolean active() {
        if (!PAD.connected) return false;
        if (net.fdgames.ek.ExiledKingdoms.n != PAD) net.fdgames.ek.ExiledKingdoms.n = PAD;
        return true;
    }

    /** Register for the current Gdx.app; call after Gdx.app is set, before the game starts. */
    @SuppressWarnings("unchecked")
    public static void install(boolean connected) throws Exception {
        PAD.connected = connected;
        final com.badlogic.gdx.utils.a<Controller> list = new com.badlogic.gdx.utils.a<>();
        if (connected) list.add(PAD);
        ControllerManager mgr = () -> list;
        java.lang.reflect.Field f = com.badlogic.gdx.controllers.Controllers.class.getDeclaredField("a");
        f.setAccessible(true);
        ((com.badlogic.gdx.utils.z<Object, Object>) f.get(null)).b(com.badlogic.gdx.Gdx.app, mgr);
    }
}
