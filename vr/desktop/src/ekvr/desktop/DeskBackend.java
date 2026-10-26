package ekvr.desktop;

import com.badlogic.gdx.Gdx;
import java.io.File;
import java.lang.reflect.Proxy;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

/**
 * Desktop test bed for the Quest build: runs the hero-v78 APK's own game code (dex2jar'd)
 * in a GLFW window, through a minimal libGDX backend written against the APK's obfuscated
 * libGDX interfaces (their method names survived R8; only the interface names were shortened:
 * Graphics = f, Input = g, Files = d, Audio = c, Net = l, ApplicationListener = a,
 * InputProcessor = j, FileHandle = q.a, FileType = d$a). Spec: deobf/QUEST_VR_SPEC.md.
 *
 * Usage: java -cp game.jar:vr-desktop.jar:lwjgl*.jar ekvr.desktop.DeskBackend <assets dir> [w h]
 */
public class DeskBackend implements com.badlogic.gdx.Application {
    public static File assets;
    public static File storage;
    static long window;
    static int width = 1280, height = 720;
    static float delta = 1 / 60f;
    static long frameId;
    static com.badlogic.gdx.a listener;
    static final List<Runnable> runnables = new ArrayList<>();
    /** Per-frame hook for scripted tests (ekvr.desktop.Script); null = interactive. */
    public static Runnable onFrame;

    public static void main(String[] args) throws Exception {
        System.loadLibrary("gdx");          // the APK's libGDX 1.9.12 natives, desktop build
        assets = new File(args[0]);
        if (args.length >= 3) { width = Integer.parseInt(args[1]); height = Integer.parseInt(args[2]); }
        storage = new File(System.getProperty("ekvr.storage", "/tmp/ekvr-storage"));
        new File(storage, "local").mkdirs();
        new File(storage, "external").mkdirs();
        run(() -> {
            try {
                return (com.badlogic.gdx.a) Class.forName("net.fdgames.ek.ExiledKingdoms")
                        .getConstructor(net.fdgames.ek.IPlatformResolver.class).newInstance(platform());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    /** Opens the window and the backend first (the game's constructor already reads Gdx.graphics). */
    static void run(java.util.function.Supplier<com.badlogic.gdx.a> make) throws Exception {
        if (!GLFW.glfwInit()) throw new IllegalStateException("glfwInit failed");
        GLFW.glfwWindowHint(GLFW.GLFW_VISIBLE, GLFW.GLFW_TRUE);
        window = GLFW.glfwCreateWindow(width, height, "Exiled Kingdoms (desktop test bed)", 0, 0);
        GLFW.glfwMakeContextCurrent(window);
        GLFW.glfwSwapInterval(1);
        GL.createCapabilities();
        DeskBackend app = new DeskBackend();
        Gdx.app = app;
        Gdx.graphics = new DeskGraphics();
        Gdx.gl = Gdx.gl20 = new ekvr.GlTap(new DeskGL20());
        Gdx.input = DeskInput.install(window);
        Gdx.files = new DeskFiles();
        Gdx.audio = silentAudio();
        Gdx.net = (com.badlogic.gdx.l) Proxy.newProxyInstance(DeskBackend.class.getClassLoader(),
                new Class[]{com.badlogic.gdx.l.class}, (o, m, a) -> m.getReturnType() == boolean.class ? false : null);
        ekvr.Pad.install(vr);                     // the simulated headset drives the game like the Quest does
        if (System.getProperty("ekvr.script") != null)
            onFrame = new Script(System.getProperty("ekvr.script"), System.getProperty("ekvr.shotDir", "/tmp"));
        listener = make.get();
        listener.a();
        listener.a(width, height);
        long last = System.nanoTime();
        while (!GLFW.glfwWindowShouldClose(window)) {
            long now = System.nanoTime();
            delta = Math.min(0.1f, (now - last) / 1e9f);
            last = now;
            GLFW.glfwPollEvents();
            synchronized (runnables) {
                for (Runnable r : new ArrayList<>(runnables)) r.run();
                runnables.clear();
            }
            if (vr) SimXr.frame(listener, width, height, delta);
            else if (mode3d) frame3d(); else {
                GL11.glViewport(0, 0, width, height);
                listener.b();
            }
            if (onFrame != null) onFrame.run();
            String shots = System.getProperty("ekvr.shotEvery");
            if (shots != null && frameId % Integer.parseInt(shots) == 0)
                Shot.save(System.getProperty("ekvr.shotDir", "/tmp") + String.format("/f%05d.png", frameId));
            String quit = System.getProperty("ekvr.quitAt");
            if (quit != null && frameId >= Integer.parseInt(quit)) GLFW.glfwSetWindowShouldClose(window, true);
            DeskInput.endFrame();
            GLFW.glfwSwapBuffers(window);
            frameId++;
            long spare = 16_666_667L - (System.nanoTime() - now);   // hold 60 fps (scripted runs: steady timing)
            if (spare > 0) try { Thread.sleep(spare / 1_000_000L, (int) (spare % 1_000_000L)); } catch (InterruptedException ignored) {}
        }
        listener.pause();
        listener.dispose();
        GLFW.glfwTerminate();
    }

    // ---- 3D test view ----------------------------------------------------------------------
    /** -Dekvr.mode=3d: the map in 3D from a third-person camera, the game's UI on top. */
    public static boolean mode3d = "3d".equals(System.getProperty("ekvr.mode")) || "table".equals(System.getProperty("ekvr.mode"));
    public static boolean table = "table".equals(System.getProperty("ekvr.mode"));
    /** -Dekvr.mode=vr: the Quest build's VrCore with a simulated headset (SimXr). */
    public static boolean vr = "vr".equals(System.getProperty("ekvr.mode"));
    public static float camYaw = (float) Math.toRadians(45), camDist = 7f, camHeight = 4.2f;
    static ekvr.Scene3D scene;
    static ekvr.UiLayer ui;
    static float[] lastMvp;

    static void frame3d() {
        if (scene == null) { scene = new ekvr.Scene3D(); ui = new ekvr.UiLayer(width, height); }
        scene.beginFrame();
        ui.begin();
        listener.b();
        ui.end(0);
        scene.endFrame();
        GL11.glViewport(0, 0, width, height);
        GL11.glClearColor(0.42f, 0.55f, 0.72f, 1);
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
        float hx = scene.heroX, hz = scene.heroZ;
        float[] proj = ekvr.Mat.perspective(55, width / (float) height, 0.1f, 200f);
        float[] view, model;
        float ex, ez;
        if (table) {
            // tabletop: the map as a 4 cm-per-cell board, seen by someone seated at it
            float k = 0.04f;
            model = ekvr.Mat.mul(ekvr.Mat.scale(k), ekvr.Mat.translate(-hx, 0, -hz));
            ex = (float) Math.sin(camYaw) * 0.55f; ez = (float) Math.cos(camYaw) * 0.55f;
            view = ekvr.Mat.lookAt(ex, 0.5f, ez, 0, 0, 0);
            ex = hx + ex / k; ez = hz + ez / k;
        } else {
            model = ekvr.Mat.id();
            ex = hx + (float) Math.sin(camYaw) * camDist;
            ez = hz + (float) Math.cos(camYaw) * camDist;
            view = ekvr.Mat.lookAt(ex, camHeight, ez, hx, 1.0f, hz);
        }
        lastMvp = ekvr.Mat.mul(proj, ekvr.Mat.mul(view, model));
        scene.draw(lastMvp, ex, ez);
        float[] o = ekvr.Mat.id();
        ui.draw(o, new float[]{-1, 1, 0}, new float[]{1, 1, 0}, new float[]{1, -1, 0}, new float[]{-1, -1, 0});
    }

    /** Desktop click in 3D mode: UI if the game's layer is opaque there, else the ground under the cursor. */
    public static int[] route(int x, int y) {
        if (!mode3d || ui == null || lastMvp == null || ui.opaqueAt(x, y)) return new int[]{x, y};
        float[] inv = invert(lastMvp);
        float nx = 2f * x / width - 1, ny = 1 - 2f * y / height;
        float[] a = unproject(inv, nx, ny, -1), b = unproject(inv, nx, ny, 1);
        if (Math.abs(b[1] - a[1]) < 1e-6) return new int[]{x, y};
        float t = -a[1] / (b[1] - a[1]);
        float gx = a[0] + (b[0] - a[0]) * t, gz = a[2] + (b[2] - a[2]) * t;
        return ekvr.UiLayer.groundToTouch(gx, gz, width, height);
    }

    static float[] unproject(float[] inv, float x, float y, float z) {
        float[] r = new float[4];
        for (int i = 0; i < 4; i++) r[i] = inv[i] * x + inv[4 + i] * y + inv[8 + i] * z + inv[12 + i];
        return new float[]{r[0] / r[3], r[1] / r[3], r[2] / r[3]};
    }

    static float[] invert(float[] m) {
        double[][] a = new double[4][8];
        for (int r = 0; r < 4; r++) { for (int c = 0; c < 4; c++) a[r][c] = m[c * 4 + r]; a[r][4 + r] = 1; }
        for (int c = 0; c < 4; c++) {
            int p = c; for (int r = c + 1; r < 4; r++) if (Math.abs(a[r][c]) > Math.abs(a[p][c])) p = r;
            double[] t = a[c]; a[c] = a[p]; a[p] = t;
            double d = a[c][c]; for (int k = 0; k < 8; k++) a[c][k] /= d;
            for (int r = 0; r < 4; r++) if (r != c) { double f = a[r][c]; for (int k = 0; k < 8; k++) a[r][k] -= f * a[c][k]; }
        }
        float[] out = new float[16];
        for (int r = 0; r < 4; r++) for (int c = 0; c < 4; c++) out[c * 4 + r] = (float) a[r][4 + c];
        return out;
    }

    /** The IPlatformResolver the game gets instead of MainActivity: an offline phone. */
    static net.fdgames.ek.IPlatformResolver platform() {
        return (net.fdgames.ek.IPlatformResolver) Proxy.newProxyInstance(DeskBackend.class.getClassLoader(),
                new Class[]{net.fdgames.ek.IPlatformResolver.class}, (o, m, a) -> {
                    Class<?> r = m.getReturnType();
                    String n = m.getName();
                    if (n.equals("getDeviceName")) return "Quest desktop test bed";
                    if (n.equals("h")) return "en";                 // language
                    if (n.equals("d")) return 4096;                 // total memory, MB
                    if (r == int.class) return 0;
                    if (r == boolean.class) return false;
                    if (r == String.class) return "";
                    return null;
                });
    }

    static com.badlogic.gdx.c silentAudio() {
        ClassLoader cl = DeskBackend.class.getClassLoader();
        java.lang.reflect.InvocationHandler none = (o, m, a) -> {
            Class<?> r = m.getReturnType();
            if (r == boolean.class) return false;
            if (r == float.class) return 0f;
            if (r == long.class) return 0L;
            if (r == int.class) return 0;
            return null;
        };
        return (com.badlogic.gdx.c) Proxy.newProxyInstance(cl, new Class[]{com.badlogic.gdx.c.class}, (o, m, a) -> {
            if (m.getName().equals("newMusic"))
                return Proxy.newProxyInstance(cl, new Class[]{com.badlogic.gdx.p.c.class}, none);
            if (m.getName().equals("newSound"))
                return Proxy.newProxyInstance(cl, new Class[]{com.badlogic.gdx.p.d.class}, none);
            return null;
        });
    }

    static DeskBackend app() { return (DeskBackend) Gdx.app; }

    // ---- Application -------------------------------------------------------------------
    public void addLifecycleListener(com.badlogic.gdx.k l) {}
    public void removeLifecycleListener(com.badlogic.gdx.k l) {}
    public void debug(String t, String m) {}
    public void debug(String t, String m, Throwable e) {}
    public void error(String t, String m) { System.out.println("[E] " + t + ": " + m); }
    public void error(String t, String m, Throwable e) { System.out.println("[E] " + t + ": " + m); e.printStackTrace(System.out); }
    public void log(String t, String m) { System.out.println("[I] " + t + ": " + m); }
    public void log(String t, String m, Throwable e) { System.out.println("[I] " + t + ": " + m + " " + e); }
    public void exit() { GLFW.glfwSetWindowShouldClose(window, true); }
    /** The game object itself, as on the Quest since v10 (EK casts it to Game / ExiledKingdoms). */
    public com.badlogic.gdx.a getApplicationListener() { return listener; }
    public com.badlogic.gdx.b getApplicationLogger() { return null; }
    public com.badlogic.gdx.c getAudio() { return Gdx.audio; }
    public com.badlogic.gdx.utils.f getClipboard() {
        return (com.badlogic.gdx.utils.f) Proxy.newProxyInstance(getClass().getClassLoader(),
                new Class[]{com.badlogic.gdx.utils.f.class}, (o, m, a) -> m.getReturnType() == String.class ? "" : null);
    }
    public com.badlogic.gdx.d getFiles() { return Gdx.files; }
    public com.badlogic.gdx.f getGraphics() { return Gdx.graphics; }
    public com.badlogic.gdx.g getInput() { return Gdx.input; }
    public long getJavaHeap() { return Runtime.getRuntime().totalMemory(); }
    public int getLogLevel() { return 2; }
    public long getNativeHeap() { return 0; }
    public com.badlogic.gdx.l getNet() { return Gdx.net; }
    public com.badlogic.gdx.m getPreferences(String name) { return null; }
    /** Report Android, so the game takes its phone code paths (UI scale, storage) as on the Quest. */
    public com.badlogic.gdx.Application.a getType() { return com.badlogic.gdx.Application.a.values()[0]; }
    public int getVersion() { return 29; }
    public void postRunnable(Runnable r) { synchronized (runnables) { runnables.add(r); } }
    public void setLogLevel(int l) {}
    public void setApplicationLogger(com.badlogic.gdx.b l) {}

    // ---- Graphics ----------------------------------------------------------------------
    public static class DeskGraphics implements com.badlogic.gdx.f {
        public int getBackBufferHeight() { return height; }
        public int getBackBufferWidth() { return width; }
        public float getDeltaTime() { return delta; }
        public float getRawDeltaTime() { return delta; }
        public com.badlogic.gdx.f.b getDisplayMode() { return null; }
        public int getFramesPerSecond() { return 60; }
        public com.badlogic.gdx.graphics.GL20 getGL20() { return Gdx.gl20; }
        public com.badlogic.gdx.graphics.GL30 getGL30() { return null; }
        public com.badlogic.gdx.graphics.glutils.GLVersion getGLVersion() {
            return new com.badlogic.gdx.graphics.glutils.GLVersion(com.badlogic.gdx.Application.a.values()[1],
                    GL11.glGetString(GL11.GL_VERSION), GL11.glGetString(GL11.GL_VENDOR), GL11.glGetString(GL11.GL_RENDERER));
        }
        public int getHeight() { return height; }
        public float getPpiX() { return 320; }
        public float getPpiY() { return 320; }
        public com.badlogic.gdx.f.c getType() { return com.badlogic.gdx.f.c.values()[0]; }
        public int getWidth() { return width; }
        public boolean isContinuousRendering() { return true; }
        public boolean isFullscreen() { return true; }
        public boolean isGL30Available() { return false; }
        public com.badlogic.gdx.graphics.Cursor newCursor(com.badlogic.gdx.graphics.Pixmap p, int x, int y) { return null; }
        public void requestRendering() {}
        public void setCursor(com.badlogic.gdx.graphics.Cursor c) {}
        public void setGL20(com.badlogic.gdx.graphics.GL20 g) { Gdx.gl = Gdx.gl20 = g; }
        public void setGL30(com.badlogic.gdx.graphics.GL30 g) {}
        public boolean supportsExtension(String e) { return GLFW.glfwExtensionSupported(e); }
        public long getFrameId() { return frameId; }
    }

    // ---- Files -------------------------------------------------------------------------
    /** Like Lwjgl3FileHandle: keeps the game's relative path, resolves it against its root. */
    static class FH extends com.badlogic.gdx.q.a {
        FH(String p, com.badlogic.gdx.d.a t) { super(new File(p), t); }
        public File file() {
            switch (type.ordinal()) {
                case 1: return new File(assets, file.getPath());
                case 2: return new File(new File(storage, "external"), file.getPath());
                case 4: return new File(new File(storage, "local"), file.getPath());
                default: return file;
            }
        }
        public com.badlogic.gdx.q.a child(String name) {
            return new FH(file.getPath().length() == 0 ? name : new File(file, name).getPath(), type);
        }
        public com.badlogic.gdx.q.a sibling(String name) {
            return new FH(new File(file.getParentFile() == null ? new File("") : file.getParentFile(), name).getPath(), type);
        }
        public com.badlogic.gdx.q.a parent() {
            File p = file.getParentFile();
            return new FH(p == null ? "" : p.getPath(), type);
        }
    }

    public static class DeskFiles implements com.badlogic.gdx.d {
        static com.badlogic.gdx.d.a type(int i) { return com.badlogic.gdx.d.a.values()[i]; } // Classpath, Internal, External, Absolute, Local
        public com.badlogic.gdx.q.a classpath(String p) { return new FH(p, type(1)); }
        public com.badlogic.gdx.q.a internal(String p) { return new FH(p, type(1)); }
        public com.badlogic.gdx.q.a external(String p) { return new FH(p, type(2)); }
        public com.badlogic.gdx.q.a local(String p) { return new FH(p, type(4)); }
        public String getExternalStoragePath() { return new File(storage, "external").getPath() + "/"; }
        public String getLocalStoragePath() { return new File(storage, "local").getPath() + "/"; }
        public com.badlogic.gdx.q.a getFileHandle(String p, com.badlogic.gdx.d.a t) {
            switch (t.ordinal()) {
                case 2: return external(p);
                case 3: return new FH(p, t);
                case 4: return local(p);
                default: return internal(p);
            }
        }
        public boolean isExternalStorageAvailable() { return true; }
        public boolean isLocalStorageAvailable() { return true; }
    }
}
