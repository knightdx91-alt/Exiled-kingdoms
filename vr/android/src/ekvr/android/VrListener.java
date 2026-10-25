package ekvr.android;

import android.opengl.EGL14;
import android.opengl.EGLConfig;
import android.opengl.EGLContext;
import android.opengl.EGLDisplay;
import android.os.SystemClock;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import ekvr.GlTap;
import ekvr.Mat;
import ekvr.VrCore;
import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

/**
 * The game's ApplicationListener, wrapped for the headset. Runs on libGDX's GL thread:
 * each frame = one OpenXR frame. The real game renders its UI into VrCore's layer while its
 * map is recorded for Scene3D; then each eye is drawn into its OpenXR swapchain image.
 * If OpenXR is unavailable (e.g. installed on a phone) it simply runs the normal 2D game.
 */
public class VrListener implements com.badlogic.gdx.a {
    static final String TAG = "ekvr";
    static final int VW = 1280, VH = 720;       // the game's virtual screen inside the headset

    final android.app.Activity activity;
    final com.badlogic.gdx.a game;
    boolean xr;
    VrCore core;
    final FloatBuffer views = ByteBuffer.allocateDirect(22 * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
    final FloatBuffer input = ByteBuffer.allocateDirect(32 * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
    final IntBuffer images = ByteBuffer.allocateDirect(8).order(ByteOrder.nativeOrder()).asIntBuffer();
    int[][] fbo = new int[2][];
    int[] depth = new int[2];
    int ew, eh;
    long last;
    View view;

    public VrListener(android.app.Activity a, com.badlogic.gdx.a game) { this.activity = a; this.game = game; }

    public void a() {                                         // create
        GL20 raw = Gdx.gl20;
        Gdx.gl = Gdx.gl20 = new GlTap(raw);
        try { view = (View) Gdx.graphics.getClass().getMethod("getView").invoke(Gdx.graphics); } catch (Exception ignored) {}
        try {
            EGLDisplay dpy = EGL14.eglGetCurrentDisplay();
            EGLContext ctx = EGL14.eglGetCurrentContext();
            int[] id = new int[1];
            EGL14.eglQueryContext(dpy, ctx, EGL14.EGL_CONFIG_ID, id, 0);
            EGLConfig[] cfg = new EGLConfig[1];
            int[] n = new int[1];
            EGL14.eglChooseConfig(dpy, new int[]{EGL14.EGL_CONFIG_ID, id[0], EGL14.EGL_NONE}, 0, cfg, 0, 1, n, 0);
            EGL14.eglSwapInterval(dpy, 0);                    // OpenXR paces the frames, not the window
            xr = Xr.init(activity, dpy.getNativeHandle(), cfg[0].getNativeHandle(), ctx.getNativeHandle(), views, input, images);
        } catch (Throwable t) {
            Log.e(TAG, "OpenXR init failed, running flat", t);
            xr = false;
        }
        if (xr) {
            ew = Xr.width(); eh = Xr.height();
            for (int e = 0; e < 2; e++) {
                int cnt = Xr.imageCount(e);
                fbo[e] = new int[cnt];
                IntBuffer ib = ByteBuffer.allocateDirect(4).order(ByteOrder.nativeOrder()).asIntBuffer();
                raw.glGenRenderbuffers(1, ib);
                depth[e] = ib.get(0);
                raw.glBindRenderbuffer(GL20.GL_RENDERBUFFER, depth[e]);
                raw.glRenderbufferStorage(GL20.GL_RENDERBUFFER, GL20.GL_DEPTH_COMPONENT16, ew, eh);
                for (int i = 0; i < cnt; i++) {
                    ib.clear();
                    raw.glGenFramebuffers(1, ib);
                    fbo[e][i] = ib.get(0);
                    raw.glBindFramebuffer(GL20.GL_FRAMEBUFFER, fbo[e][i]);
                    raw.glFramebufferTexture2D(GL20.GL_FRAMEBUFFER, GL20.GL_COLOR_ATTACHMENT0, GL20.GL_TEXTURE_2D, Xr.image(e, i), 0);
                    raw.glFramebufferRenderbuffer(GL20.GL_FRAMEBUFFER, GL20.GL_DEPTH_ATTACHMENT, GL20.GL_RENDERBUFFER, depth[e]);
                }
            }
            raw.glBindFramebuffer(GL20.GL_FRAMEBUFFER, 0);
            core = new VrCore(VW, VH);
            core.passthrough = Xr.hasPassthrough();
            core.touch = new VrCore.Touch() {
                public void down(int x, int y) { touch(MotionEvent.ACTION_DOWN, x, y); }
                public void drag(int x, int y) { touch(MotionEvent.ACTION_MOVE, x, y); }
                public void up(int x, int y) { touch(MotionEvent.ACTION_UP, x, y); }
                public void key(int code) { sendKey(KeyEvent.ACTION_DOWN, code); sendKey(KeyEvent.ACTION_UP, code); }
            };
            forceSize();
        }
        game.a();
        if (xr) game.a(VW, VH);
        last = System.nanoTime();
    }

    long downTime;

    /** A synthetic finger on the game's view: DefaultAndroidInput handles it like a real touch. */
    void touch(int action, int x, int y) {
        long now = SystemClock.uptimeMillis();
        if (action == MotionEvent.ACTION_DOWN) downTime = now;
        MotionEvent ev = MotionEvent.obtain(downTime, now, action, x, y, 0);
        try { ((View.OnTouchListener) Gdx.input).onTouch(view, ev); } finally { ev.recycle(); }
    }

    void sendKey(int action, int code) {
        ((View.OnKeyListener) Gdx.input).onKey(view, code, new KeyEvent(action, code));
    }

    /** The game lays out its UI for the surface size: keep it at our virtual 1280x720 screen. */
    void forceSize() {
        try {
            Class<?> g = Gdx.graphics.getClass();
            Field w = g.getDeclaredField("width"), h = g.getDeclaredField("height");
            w.setAccessible(true); h.setAccessible(true);
            w.setInt(Gdx.graphics, VW); h.setInt(Gdx.graphics, VH);
        } catch (Exception e) {
            Log.w(TAG, "can't set virtual size: " + e);
        }
    }

    public void a(int w, int h) {                              // resize
        if (xr) { forceSize(); game.a(VW, VH); } else game.a(w, h);
    }

    public void b() {                                         // render
        if (!xr) { game.b(); return; }
        if (!(Gdx.gl20 instanceof GlTap)) Gdx.gl = Gdx.gl20 = new GlTap(Gdx.gl20);   // context recreated on resume
        forceSize();
        long now = System.nanoTime();
        float dt = Math.min(0.1f, (now - last) / 1e9f);
        last = now;
        int st = Xr.beginFrame();
        if (st < 0) { activity.finish(); return; }
        core.gameFrame(game::b, 0);
        if (st == 1) {
            float[] v = new float[22];
            views.position(0);
            views.get(v);
            float[] in = new float[32];
            input.position(0);
            input.get(in);
            core.left.set(in, 0);
            core.right.set(in, 16);
            float[] eye0 = Mat.pose(v[0], v[1], v[2], v[3], v[4], v[5], v[6]);
            float[] head = eye0.clone();
            head[12] = (v[4] + v[15]) / 2; head[13] = (v[5] + v[16]) / 2; head[14] = (v[6] + v[17]) / 2;
            if (core.right.stick && !modeLatch)
                core.setMode(core.mode == VrCore.Mode.TABLE ? VrCore.Mode.WALK : VrCore.Mode.TABLE, head);
            modeLatch = core.right.stick;
            core.input(head, dt);
            GL20 gl = ((GlTap) Gdx.gl20).gl;
            for (int e = 0; e < 2; e++) {
                int o = e * 11;
                float[] pose = Mat.pose(v[o], v[o + 1], v[o + 2], v[o + 3], v[o + 4], v[o + 5], v[o + 6]);
                float[] proj = Mat.frustum(v[o + 7], v[o + 8], v[o + 9], v[o + 10], 0.05f, 500f);
                gl.glBindFramebuffer(GL20.GL_FRAMEBUFFER, fbo[e][images.get(e)]);
                core.drawEye(proj, Mat.rigidInverse(pose), ew, eh);
            }
            gl.glBindFramebuffer(GL20.GL_FRAMEBUFFER, 0);
        }
        Xr.endFrame(st == 1, core.passthrough && core.mode == VrCore.Mode.TABLE);
    }

    boolean modeLatch;

    public void pause() { game.pause(); }
    public void resume() { game.resume(); }
    public void dispose() { game.dispose(); }
}
