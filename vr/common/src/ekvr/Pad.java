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
    public final boolean[] buttons = new boolean[32];
    public boolean connected;

    public PovDirection a(int pov) { return PovDirection.values()[0]; }
    public boolean b(int button) { return button >= 0 && button < buttons.length && buttons[button]; }
    public float c(int axis) { return axis >= 0 && axis < axes.length ? axes[axis] : 0f; }
    public String getName() { return "Quest Touch"; }

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
