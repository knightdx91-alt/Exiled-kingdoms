package ekvr.desktop;

import org.lwjgl.glfw.GLFW;

/** Mouse + keyboard as the game's single-touch phone input (pointer 0). */
public class DeskInput implements com.badlogic.gdx.g {
    static DeskInput in;
    com.badlogic.gdx.j processor;
    int x, y, dx, dy;
    boolean touched;
    final boolean[] keys = new boolean[512];
    final boolean[] justPressed = new boolean[512];

    static DeskInput install(long window) {
        in = new DeskInput();
        GLFW.glfwSetCursorPosCallback(window, (w, mx, my) -> move((int) mx, (int) my));
        GLFW.glfwSetMouseButtonCallback(window, (w, b, action, mods) -> {
            if (b != 0) return;
            if (action == GLFW.GLFW_PRESS) press(in.x, in.y); else release(in.x, in.y);
        });
        GLFW.glfwSetScrollCallback(window, (w, sx, sy) -> { if (in.processor != null) in.processor.scrolled((float) -sx, (float) -sy); });
        GLFW.glfwSetKeyCallback(window, (w, key, sc, action, mods) -> {
            int k = gdxKey(key);
            if (k < 0) return;
            if (action == GLFW.GLFW_PRESS) { in.keys[k] = true; in.justPressed[k] = true; if (in.processor != null) in.processor.keyDown(k); }
            else if (action == GLFW.GLFW_RELEASE) { in.keys[k] = false; if (in.processor != null) in.processor.keyUp(k); }
        });
        GLFW.glfwSetCharCallback(window, (w, cp) -> { if (in.processor != null) in.processor.keyTyped((char) cp); });
        return in;
    }

    // Scripted/test entry points (also used by the GLFW callbacks).
    public static void move(int mx, int my) {
        if (DeskBackend.vr) { SimXr.aimX = mx; SimXr.aimY = my; return; }
        in.dx = mx - in.x; in.dy = my - in.y; in.x = mx; in.y = my;
        if (in.processor == null) return;
        if (in.touched) in.processor.touchDragged(mx, my, 0); else in.processor.mouseMoved(mx, my);
    }
    // Raw (unrouted) touch for the VR simulation: the coordinates are already game-screen pixels.
    public static void rawPress(int mx, int my) {
        in.x = mx; in.y = my; in.touched = true;
        if (in.processor != null) in.processor.touchDown(mx, my, 0, 0);
    }
    public static void rawMove(int mx, int my) {
        in.dx = mx - in.x; in.dy = my - in.y; in.x = mx; in.y = my;
        if (in.processor != null) in.processor.touchDragged(mx, my, 0);
    }
    public static void rawRelease(int mx, int my) {
        in.x = mx; in.y = my; in.touched = false;
        if (in.processor != null) in.processor.touchUp(mx, my, 0, 0);
    }

    public static void press(int mx, int my) {
        if (DeskBackend.vr) { SimXr.aimX = mx; SimXr.aimY = my; SimXr.trigger = true; return; }
        int[] r = DeskBackend.route(mx, my); mx = r[0]; my = r[1];
        in.x = mx; in.y = my; in.touched = true;
        if (in.processor != null) in.processor.touchDown(mx, my, 0, 0);
    }
    public static void release(int mx, int my) {
        if (DeskBackend.vr) { SimXr.trigger = false; return; }
        if (DeskBackend.mode3d && in.touched) { mx = in.x; my = in.y; }   // release where the press landed
        in.x = mx; in.y = my; in.touched = false;
        if (in.processor != null) in.processor.touchUp(mx, my, 0, 0);
    }
    public static void key(int gdxKey, boolean down) {
        in.keys[gdxKey] = down;
        if (down) in.justPressed[gdxKey] = true;
        if (in.processor != null) { if (down) in.processor.keyDown(gdxKey); else in.processor.keyUp(gdxKey); }
    }
    static void endFrame() { java.util.Arrays.fill(in.justPressed, false); in.dx = in.dy = 0; }

    /** GLFW key -> libGDX Input.Keys code, for the keys the game reads. */
    static int gdxKey(int k) {
        if (k >= GLFW.GLFW_KEY_A && k <= GLFW.GLFW_KEY_Z) return 29 + (k - GLFW.GLFW_KEY_A);
        if (k >= GLFW.GLFW_KEY_0 && k <= GLFW.GLFW_KEY_9) return 7 + (k - GLFW.GLFW_KEY_0);
        switch (k) {
            case GLFW.GLFW_KEY_ESCAPE: return 4;      // BACK (the phone's back button)
            case GLFW.GLFW_KEY_SPACE: return 62;
            case GLFW.GLFW_KEY_ENTER: return 66;
            case GLFW.GLFW_KEY_BACKSPACE: return 67;
            case GLFW.GLFW_KEY_UP: return 19;
            case GLFW.GLFW_KEY_DOWN: return 20;
            case GLFW.GLFW_KEY_LEFT: return 21;
            case GLFW.GLFW_KEY_RIGHT: return 22;
            case GLFW.GLFW_KEY_TAB: return 61;
            default: return -1;
        }
    }

    public long getCurrentEventTime() { return System.nanoTime(); }
    public int getDeltaX() { return dx; }
    public int getDeltaY() { return dy; }
    public int getDeltaX(int p) { return p == 0 ? dx : 0; }
    public int getDeltaY(int p) { return p == 0 ? dy : 0; }
    public int getX() { return x; }
    public int getY() { return y; }
    public int getX(int p) { return p == 0 ? x : 0; }
    public int getY(int p) { return p == 0 ? y : 0; }
    public boolean isButtonPressed(int b) { return b == 0 && touched; }
    public boolean isCatchBackKey() { return true; }
    public boolean isKeyJustPressed(int k) { return k >= 0 && k < 512 && justPressed[k]; }
    public boolean isKeyPressed(int k) { return k >= 0 && k < 512 && keys[k]; }
    public boolean isPeripheralAvailable(com.badlogic.gdx.g.c p) { return false; }
    public boolean isTouched() { return touched; }
    public boolean isTouched(int p) { return p == 0 && touched; }
    public void setCatchBackKey(boolean c) {}
    public void setInputProcessor(com.badlogic.gdx.j p) { processor = p; }
    public com.badlogic.gdx.j getInputProcessor() { return processor; }
    public void setOnscreenKeyboardVisible(boolean v) {}
}
