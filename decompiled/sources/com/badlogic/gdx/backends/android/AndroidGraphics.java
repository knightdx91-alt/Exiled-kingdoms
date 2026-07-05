package com.badlogic.gdx.backends.android;

import android.opengl.GLSurfaceView;
import android.os.Build;
import android.os.Process;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.DisplayCutout;
import android.view.View;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.android.surfaceview.GLSurfaceView20;
import com.badlogic.gdx.backends.android.surfaceview.GdxEglConfigChooser;
import com.badlogic.gdx.backends.android.surfaceview.ResolutionStrategy;
import com.badlogic.gdx.e;
import com.badlogic.gdx.f;
import com.badlogic.gdx.graphics.Cubemap;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureArray;
import com.badlogic.gdx.graphics.glutils.GLFrameBuffer;
import com.badlogic.gdx.graphics.glutils.GLVersion;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.k;
import com.badlogic.gdx.utils.c;
import com.badlogic.gdx.utils.k0;
import com.badlogic.gdx.utils.l;
import com.badlogic.gdx.utils.m;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;
import javax.microedition.khronos.opengles.GL10;
import l0.d;
import net.fdgames.assets.Assets;
import net.fdgames.ek.ControllerConfig;
import net.fdgames.ek.ExiledKingdoms;
import net.fdgames.ek.Settings;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class AndroidGraphics implements f, GLSurfaceView.Renderer {
    private static final String LOG_TAG = "AndroidGraphics";
    static volatile boolean enforceContinuousRendering;
    AndroidApplicationBase app;
    private f.a bufferFormat;
    protected final AndroidApplicationConfiguration config;
    volatile boolean created;
    protected float deltaTime;
    private float density;
    volatile boolean destroy;
    EGLContext eglContext;
    String extensions;
    protected int fps;
    protected long frameId;
    protected long frameStart;
    protected int frames;
    GL20 gl20;
    GL30 gl30;
    GLVersion glVersion;
    int height;
    private boolean isContinuous;
    protected long lastFrameTime;
    volatile boolean pause;
    private float ppcX;
    private float ppcY;
    private float ppiX;
    private float ppiY;
    volatile boolean resume;
    volatile boolean running;
    int safeInsetBottom;
    int safeInsetLeft;
    int safeInsetRight;
    int safeInsetTop;
    Object synch;
    int[] value;
    final GLSurfaceView20 view;
    int width;

    private class AndroidDisplayMode extends f.b {
        protected AndroidDisplayMode(int i2, int i3, int i4, int i5) {
            super(i2, i3, i4, i5);
        }
    }

    private class AndroidMonitor extends f.d {
        public AndroidMonitor(int i2, int i3, String str) {
            super(i2, i3, str);
        }
    }

    public AndroidGraphics(AndroidApplicationBase androidApplicationBase, AndroidApplicationConfiguration androidApplicationConfiguration, ResolutionStrategy resolutionStrategy) {
        this(androidApplicationBase, androidApplicationConfiguration, resolutionStrategy, true);
    }

    private int getAttrib(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig eGLConfig, int i2, int i3) {
        return egl10.eglGetConfigAttrib(eGLDisplay, eGLConfig, i2, this.value) ? this.value[0] : i3;
    }

    protected boolean checkGL20() {
        EGL10 egl10 = (EGL10) EGLContext.getEGL();
        EGLDisplay eGLDisplayEglGetDisplay = egl10.eglGetDisplay(EGL10.EGL_DEFAULT_DISPLAY);
        egl10.eglInitialize(eGLDisplayEglGetDisplay, new int[2]);
        int[] iArr = new int[1];
        egl10.eglChooseConfig(eGLDisplayEglGetDisplay, new int[]{12324, 4, 12323, 4, 12322, 4, 12352, 4, 12344}, new EGLConfig[10], 10, iArr);
        egl10.eglTerminate(eGLDisplayEglGetDisplay);
        return iArr[0] > 0;
    }

    public void clearManagedCaches() {
        Mesh.clearAllMeshes(this.app);
        Texture.clearAllTextures(this.app);
        Cubemap.clearAllCubemaps(this.app);
        TextureArray.clearAllTextureArrays(this.app);
        ShaderProgram.clearAllShaderPrograms(this.app);
        GLFrameBuffer.clearAllFrameBuffers(this.app);
        logManagedCachesStatus();
    }

    protected GLSurfaceView20 createGLSurfaceView(AndroidApplicationBase androidApplicationBase, ResolutionStrategy resolutionStrategy) {
        if (!checkGL20()) {
            throw new m("Libgdx requires OpenGL ES 2.0");
        }
        GLSurfaceView.EGLConfigChooser eglConfigChooser = getEglConfigChooser();
        GLSurfaceView20 gLSurfaceView20 = new GLSurfaceView20(androidApplicationBase.getContext(), resolutionStrategy, this.config.useGL30 ? 3 : 2);
        if (eglConfigChooser != null) {
            gLSurfaceView20.setEGLConfigChooser(eglConfigChooser);
        } else {
            AndroidApplicationConfiguration androidApplicationConfiguration = this.config;
            gLSurfaceView20.setEGLConfigChooser(androidApplicationConfiguration.f1605r, androidApplicationConfiguration.f1604g, androidApplicationConfiguration.f1603b, androidApplicationConfiguration.f1602a, androidApplicationConfiguration.depth, androidApplicationConfiguration.stencil);
        }
        gLSurfaceView20.setRenderer(this);
        return gLSurfaceView20;
    }

    void destroy() {
        synchronized (this.synch) {
            this.running = false;
            this.destroy = true;
            while (this.destroy) {
                try {
                    this.synch.wait();
                } catch (InterruptedException unused) {
                    Gdx.app.log(LOG_TAG, "waiting for destroy synchronization failed!");
                }
            }
        }
    }

    @Override // com.badlogic.gdx.f
    public int getBackBufferHeight() {
        return this.height;
    }

    @Override // com.badlogic.gdx.f
    public int getBackBufferWidth() {
        return this.width;
    }

    public f.a getBufferFormat() {
        return this.bufferFormat;
    }

    @Override // com.badlogic.gdx.f
    public float getDeltaTime() {
        return this.deltaTime;
    }

    public float getDensity() {
        return this.density;
    }

    public f.b getDisplayMode(f.d dVar) {
        return getDisplayMode();
    }

    public f.b[] getDisplayModes(f.d dVar) {
        return getDisplayModes();
    }

    protected GLSurfaceView.EGLConfigChooser getEglConfigChooser() {
        AndroidApplicationConfiguration androidApplicationConfiguration = this.config;
        return new GdxEglConfigChooser(androidApplicationConfiguration.f1605r, androidApplicationConfiguration.f1604g, androidApplicationConfiguration.f1603b, androidApplicationConfiguration.f1602a, androidApplicationConfiguration.depth, androidApplicationConfiguration.stencil, androidApplicationConfiguration.numSamples);
    }

    public long getFrameId() {
        return this.frameId;
    }

    @Override // com.badlogic.gdx.f
    public int getFramesPerSecond() {
        return this.fps;
    }

    @Override // com.badlogic.gdx.f
    public GL20 getGL20() {
        return this.gl20;
    }

    @Override // com.badlogic.gdx.f
    public GL30 getGL30() {
        return this.gl30;
    }

    @Override // com.badlogic.gdx.f
    public GLVersion getGLVersion() {
        return this.glVersion;
    }

    @Override // com.badlogic.gdx.f
    public int getHeight() {
        return this.height;
    }

    public f.d getMonitor() {
        return getPrimaryMonitor();
    }

    public f.d[] getMonitors() {
        return new f.d[]{getPrimaryMonitor()};
    }

    public float getPpcX() {
        return this.ppcX;
    }

    public float getPpcY() {
        return this.ppcY;
    }

    @Override // com.badlogic.gdx.f
    public float getPpiX() {
        return this.ppiX;
    }

    @Override // com.badlogic.gdx.f
    public float getPpiY() {
        return this.ppiY;
    }

    public f.d getPrimaryMonitor() {
        return new AndroidMonitor(0, 0, "Primary Monitor");
    }

    public float getRawDeltaTime() {
        return this.deltaTime;
    }

    public int getSafeInsetBottom() {
        return this.safeInsetBottom;
    }

    public int getSafeInsetLeft() {
        return this.safeInsetLeft;
    }

    public int getSafeInsetRight() {
        return this.safeInsetRight;
    }

    public int getSafeInsetTop() {
        return this.safeInsetTop;
    }

    @Override // com.badlogic.gdx.f
    public f.c getType() {
        return f.c.f1658a;
    }

    public View getView() {
        return this.view;
    }

    @Override // com.badlogic.gdx.f
    public int getWidth() {
        return this.width;
    }

    @Override // com.badlogic.gdx.f
    public boolean isContinuousRendering() {
        return this.isContinuous;
    }

    @Override // com.badlogic.gdx.f
    public boolean isFullscreen() {
        return true;
    }

    @Override // com.badlogic.gdx.f
    public boolean isGL30Available() {
        return this.gl30 != null;
    }

    protected void logConfig(EGLConfig eGLConfig) {
        EGL10 egl10 = (EGL10) EGLContext.getEGL();
        EGLDisplay eGLDisplayEglGetDisplay = egl10.eglGetDisplay(EGL10.EGL_DEFAULT_DISPLAY);
        int attrib = getAttrib(egl10, eGLDisplayEglGetDisplay, eGLConfig, 12324, 0);
        int attrib2 = getAttrib(egl10, eGLDisplayEglGetDisplay, eGLConfig, 12323, 0);
        int attrib3 = getAttrib(egl10, eGLDisplayEglGetDisplay, eGLConfig, 12322, 0);
        int attrib4 = getAttrib(egl10, eGLDisplayEglGetDisplay, eGLConfig, 12321, 0);
        int attrib5 = getAttrib(egl10, eGLDisplayEglGetDisplay, eGLConfig, 12325, 0);
        int attrib6 = getAttrib(egl10, eGLDisplayEglGetDisplay, eGLConfig, 12326, 0);
        int iMax = Math.max(getAttrib(egl10, eGLDisplayEglGetDisplay, eGLConfig, 12337, 0), getAttrib(egl10, eGLDisplayEglGetDisplay, eGLConfig, GdxEglConfigChooser.EGL_COVERAGE_SAMPLES_NV, 0));
        boolean z2 = getAttrib(egl10, eGLDisplayEglGetDisplay, eGLConfig, GdxEglConfigChooser.EGL_COVERAGE_SAMPLES_NV, 0) != 0;
        Gdx.app.log(LOG_TAG, "framebuffer: (" + attrib + ", " + attrib2 + ", " + attrib3 + ", " + attrib4 + ")");
        Application application = Gdx.app;
        StringBuilder sb = new StringBuilder("depthbuffer: (");
        sb.append(attrib5);
        sb.append(")");
        application.log(LOG_TAG, sb.toString());
        Gdx.app.log(LOG_TAG, "stencilbuffer: (" + attrib6 + ")");
        Gdx.app.log(LOG_TAG, "samples: (" + iMax + ")");
        Gdx.app.log(LOG_TAG, "coverage sampling: (" + z2 + ")");
        this.bufferFormat = new f.a(attrib, attrib2, attrib3, attrib4, attrib5, attrib6, iMax, z2);
    }

    protected void logManagedCachesStatus() {
        Gdx.app.log(LOG_TAG, Mesh.getManagedStatus());
        Gdx.app.log(LOG_TAG, Texture.getManagedStatus());
        Gdx.app.log(LOG_TAG, Cubemap.getManagedStatus());
        Gdx.app.log(LOG_TAG, ShaderProgram.getManagedStatus());
        Gdx.app.log(LOG_TAG, GLFrameBuffer.getManagedStatus());
    }

    @Override // com.badlogic.gdx.f
    public Cursor newCursor(Pixmap pixmap, int i2, int i3) {
        return null;
    }

    @Override // android.opengl.GLSurfaceView.Renderer
    public void onDrawFrame(GL10 gl10) {
        boolean z2;
        boolean z3;
        boolean z4;
        boolean z5;
        long jNanoTime = System.nanoTime();
        if (this.resume) {
            this.deltaTime = 0.0f;
        } else {
            this.deltaTime = (jNanoTime - this.lastFrameTime) / 1.0E9f;
        }
        this.lastFrameTime = jNanoTime;
        synchronized (this.synch) {
            try {
                z2 = this.running;
                z3 = this.pause;
                z4 = this.destroy;
                z5 = this.resume;
                if (this.resume) {
                    this.resume = false;
                }
                if (this.pause) {
                    this.pause = false;
                    this.synch.notifyAll();
                }
                if (this.destroy) {
                    this.destroy = false;
                    this.synch.notifyAll();
                }
            } finally {
            }
        }
        if (z5) {
            k0<k> lifecycleListeners = this.app.getLifecycleListeners();
            synchronized (lifecycleListeners) {
                try {
                    k[] kVarArrW = lifecycleListeners.w();
                    int i2 = lifecycleListeners.f1750b;
                    for (int i3 = 0; i3 < i2; i3++) {
                        kVarArrW[i3].resume();
                    }
                    lifecycleListeners.x();
                } finally {
                }
            }
            this.app.getApplicationListener().resume();
            Gdx.app.log(LOG_TAG, "resumed");
        }
        if (z2) {
            synchronized (this.app.getRunnables()) {
                this.app.getExecutedRunnables().clear();
                this.app.getExecutedRunnables().b(this.app.getRunnables());
                this.app.getRunnables().clear();
            }
            for (int i4 = 0; i4 < this.app.getExecutedRunnables().f1750b; i4++) {
                try {
                    this.app.getExecutedRunnables().get(i4).run();
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
            this.app.getInput().processEvents();
            this.frameId++;
            ((e) this.app.getApplicationListener()).a();
        }
        if (z3) {
            k0<k> lifecycleListeners2 = this.app.getLifecycleListeners();
            synchronized (lifecycleListeners2) {
                try {
                    k[] kVarArrW2 = lifecycleListeners2.w();
                    int i5 = lifecycleListeners2.f1750b;
                    for (int i6 = 0; i6 < i5; i6++) {
                        kVarArrW2[i6].pause();
                    }
                } finally {
                }
            }
            this.app.getApplicationListener().pause();
            Gdx.app.log(LOG_TAG, "paused");
        }
        if (z4) {
            k0<k> lifecycleListeners3 = this.app.getLifecycleListeners();
            synchronized (lifecycleListeners3) {
                try {
                    k[] kVarArrW3 = lifecycleListeners3.w();
                    int i7 = lifecycleListeners3.f1750b;
                    for (int i8 = 0; i8 < i7; i8++) {
                        kVarArrW3[i8].dispose();
                    }
                } finally {
                }
            }
            this.app.getApplicationListener().dispose();
            Gdx.app.log(LOG_TAG, "destroyed");
        }
        if (jNanoTime - this.frameStart > 1000000000) {
            this.fps = this.frames;
            this.frames = 0;
            this.frameStart = jNanoTime;
        }
        this.frames++;
    }

    public void onPauseGLSurfaceView() {
        GLSurfaceView20 gLSurfaceView20 = this.view;
        if (gLSurfaceView20 != null) {
            gLSurfaceView20.onPause();
        }
    }

    public void onResumeGLSurfaceView() {
        GLSurfaceView20 gLSurfaceView20 = this.view;
        if (gLSurfaceView20 != null) {
            gLSurfaceView20.onResume();
        }
    }

    @Override // android.opengl.GLSurfaceView.Renderer
    public void onSurfaceChanged(GL10 gl10, int i2, int i3) {
        this.width = i2;
        this.height = i3;
        updatePpi();
        updateSafeAreaInsets();
        gl10.glViewport(0, 0, this.width, this.height);
        if (!this.created) {
            ExiledKingdoms exiledKingdoms = (ExiledKingdoms) this.app.getApplicationListener();
            exiledKingdoms.getClass();
            Gdx.app.setLogLevel(3);
            if (ExiledKingdoms.f3378h) {
                Assets assets = Assets.f3309a;
                if (ExiledKingdoms.f3378h) {
                    Assets.f3310b = new Pixmap(Gdx.files.internal("data/ui/mouse.png"));
                    Assets.f3311c = new Pixmap(Gdx.files.internal("data/ui/mouse_red.png"));
                    Assets.f3312d = Gdx.graphics.newCursor(Assets.f3310b, 0, 0);
                    Assets.f3313e = Gdx.graphics.newCursor(Assets.f3311c, 0, 0);
                    f fVar = Gdx.graphics;
                    fVar.setCursor(fVar.newCursor(Assets.f3310b, 0, 0));
                }
            }
            l.d("ExiledKingdoms.create() - Opening splash screen....");
            exiledKingdoms.c(new d(exiledKingdoms));
            ControllerConfig controllerConfig = Settings.f3418a;
            if (Gdx.files.local("data/saves/settings.ini").exists()) {
                Gdx.files.local("data/saves/settings.ini").copyTo(Gdx.files.local("data/config/settings.ini"));
                Gdx.files.local("data/saves/settings.ini").delete();
            }
            if (Gdx.files.local("data/saves/global.ini").exists()) {
                Gdx.files.local("data/saves/global.sav").writeString(c.f(Gdx.files.local("data/saves/global.ini").readString()), false);
                Gdx.files.local("data/saves/global.ini").delete();
            }
            this.created = true;
            synchronized (this) {
                this.running = true;
            }
        }
        this.app.getApplicationListener().b(i2, i3);
    }

    @Override // android.opengl.GLSurfaceView.Renderer
    public void onSurfaceCreated(GL10 gl10, EGLConfig eGLConfig) {
        this.eglContext = ((EGL10) EGLContext.getEGL()).eglGetCurrentContext();
        setupGL(gl10);
        logConfig(eGLConfig);
        updatePpi();
        updateSafeAreaInsets();
        Mesh.invalidateAllMeshes(this.app);
        Texture.invalidateAllTextures(this.app);
        Cubemap.invalidateAllCubemaps(this.app);
        TextureArray.invalidateAllTextureArrays(this.app);
        ShaderProgram.invalidateAllShaderPrograms(this.app);
        GLFrameBuffer.invalidateAllFrameBuffers(this.app);
        logManagedCachesStatus();
        Display defaultDisplay = this.app.getWindowManager().getDefaultDisplay();
        this.width = defaultDisplay.getWidth();
        this.height = defaultDisplay.getHeight();
        this.lastFrameTime = System.nanoTime();
        gl10.glViewport(0, 0, this.width, this.height);
    }

    void pause() {
        synchronized (this.synch) {
            try {
                if (this.running) {
                    this.running = false;
                    this.pause = true;
                    this.view.queueEvent(new Runnable() { // from class: com.badlogic.gdx.backends.android.AndroidGraphics.1
                        @Override // java.lang.Runnable
                        public void run() {
                            if (AndroidGraphics.this.pause) {
                                AndroidGraphics.this.onDrawFrame(null);
                            }
                        }
                    });
                    while (this.pause) {
                        try {
                            this.synch.wait(4000L);
                            if (this.pause) {
                                Gdx.app.error(LOG_TAG, "waiting for pause synchronization took too long; assuming deadlock and killing");
                                Process.killProcess(Process.myPid());
                            }
                        } catch (InterruptedException unused) {
                            Gdx.app.log(LOG_TAG, "waiting for pause synchronization failed!");
                        }
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    protected void preserveEGLContextOnPause() {
        this.view.setPreserveEGLContextOnPause(true);
    }

    @Override // com.badlogic.gdx.f
    public void requestRendering() {
        GLSurfaceView20 gLSurfaceView20 = this.view;
        if (gLSurfaceView20 != null) {
            gLSurfaceView20.requestRender();
        }
    }

    void resume() {
        synchronized (this.synch) {
            this.running = true;
            this.resume = true;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v1 */
    /* JADX WARN: Type inference failed for: r2v2, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r2v3 */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public void setContinuousRendering(boolean z2) {
        if (this.view != null) {
            ?? r2 = (enforceContinuousRendering || z2) ? 1 : 0;
            this.isContinuous = r2;
            this.view.setRenderMode(r2);
        }
    }

    @Override // com.badlogic.gdx.f
    public void setCursor(Cursor cursor) {
    }

    public boolean setFullscreenMode(f.b bVar) {
        return false;
    }

    @Override // com.badlogic.gdx.f
    public void setGL20(GL20 gl20) {
        this.gl20 = gl20;
        if (this.gl30 == null) {
            Gdx.gl = gl20;
            Gdx.gl20 = gl20;
        }
    }

    @Override // com.badlogic.gdx.f
    public void setGL30(GL30 gl30) {
        this.gl30 = gl30;
        if (gl30 != null) {
            this.gl20 = gl30;
            Gdx.gl = gl30;
            Gdx.gl20 = gl30;
            Gdx.gl30 = gl30;
        }
    }

    public void setResizable(boolean z2) {
    }

    public void setSystemCursor(Cursor.SystemCursor systemCursor) {
    }

    public void setTitle(String str) {
    }

    public void setUndecorated(boolean z2) {
        this.app.getApplicationWindow().setFlags(GL20.GL_STENCIL_BUFFER_BIT, z2 ? 1 : 0);
    }

    public void setVSync(boolean z2) {
    }

    public boolean setWindowedMode(int i2, int i3) {
        return false;
    }

    protected void setupGL(GL10 gl10) {
        GLVersion gLVersion = new GLVersion(Application.a.f1563a, gl10.glGetString(GL20.GL_VERSION), gl10.glGetString(GL20.GL_VENDOR), gl10.glGetString(GL20.GL_RENDERER));
        this.glVersion = gLVersion;
        if (!this.config.useGL30 || gLVersion.getMajorVersion() <= 2) {
            if (this.gl20 != null) {
                return;
            }
            AndroidGL20 androidGL20 = new AndroidGL20();
            this.gl20 = androidGL20;
            Gdx.gl = androidGL20;
            Gdx.gl20 = androidGL20;
        } else {
            if (this.gl30 != null) {
                return;
            }
            AndroidGL30 androidGL30 = new AndroidGL30();
            this.gl30 = androidGL30;
            this.gl20 = androidGL30;
            Gdx.gl = androidGL30;
            Gdx.gl20 = androidGL30;
            Gdx.gl30 = androidGL30;
        }
        Gdx.app.log(LOG_TAG, "OGL renderer: " + gl10.glGetString(GL20.GL_RENDERER));
        Gdx.app.log(LOG_TAG, "OGL vendor: " + gl10.glGetString(GL20.GL_VENDOR));
        Gdx.app.log(LOG_TAG, "OGL version: " + gl10.glGetString(GL20.GL_VERSION));
        Gdx.app.log(LOG_TAG, "OGL extensions: " + gl10.glGetString(GL20.GL_EXTENSIONS));
    }

    public boolean supportsDisplayModeChange() {
        return false;
    }

    @Override // com.badlogic.gdx.f
    public boolean supportsExtension(String str) {
        if (this.extensions == null) {
            this.extensions = Gdx.gl.glGetString(GL20.GL_EXTENSIONS);
        }
        return this.extensions.contains(str);
    }

    protected void updatePpi() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        this.app.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        float f2 = displayMetrics.xdpi;
        this.ppiX = f2;
        float f3 = displayMetrics.ydpi;
        this.ppiY = f3;
        this.ppcX = f2 / 2.54f;
        this.ppcY = f3 / 2.54f;
        this.density = displayMetrics.density;
    }

    protected void updateSafeAreaInsets() {
        this.safeInsetLeft = 0;
        this.safeInsetTop = 0;
        this.safeInsetRight = 0;
        this.safeInsetBottom = 0;
        if (Build.VERSION.SDK_INT >= 28) {
            try {
                DisplayCutout displayCutout = this.app.getApplicationWindow().getDecorView().getRootWindowInsets().getDisplayCutout();
                if (displayCutout != null) {
                    this.safeInsetRight = displayCutout.getSafeInsetRight();
                    this.safeInsetBottom = displayCutout.getSafeInsetBottom();
                    this.safeInsetTop = displayCutout.getSafeInsetTop();
                    this.safeInsetLeft = displayCutout.getSafeInsetLeft();
                }
            } catch (UnsupportedOperationException unused) {
                Gdx.app.log(LOG_TAG, "Unable to get safe area insets");
            }
        }
    }

    public AndroidGraphics(AndroidApplicationBase androidApplicationBase, AndroidApplicationConfiguration androidApplicationConfiguration, ResolutionStrategy resolutionStrategy, boolean z2) {
        this.lastFrameTime = System.nanoTime();
        this.deltaTime = 0.0f;
        this.frameStart = System.nanoTime();
        this.frameId = -1L;
        this.frames = 0;
        this.created = false;
        this.running = false;
        this.pause = false;
        this.resume = false;
        this.destroy = false;
        this.ppiX = 0.0f;
        this.ppiY = 0.0f;
        this.ppcX = 0.0f;
        this.ppcY = 0.0f;
        this.density = 1.0f;
        this.bufferFormat = new f.a(5, 6, 5, 0, 16, 0, 0, false);
        this.isContinuous = true;
        this.value = new int[1];
        this.synch = new Object();
        this.config = androidApplicationConfiguration;
        this.app = androidApplicationBase;
        GLSurfaceView20 gLSurfaceView20CreateGLSurfaceView = createGLSurfaceView(androidApplicationBase, resolutionStrategy);
        this.view = gLSurfaceView20CreateGLSurfaceView;
        preserveEGLContextOnPause();
        if (z2) {
            gLSurfaceView20CreateGLSurfaceView.setFocusable(true);
            gLSurfaceView20CreateGLSurfaceView.setFocusableInTouchMode(true);
        }
    }

    @Override // com.badlogic.gdx.f
    public f.b getDisplayMode() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        this.app.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return new AndroidDisplayMode(displayMetrics.widthPixels, displayMetrics.heightPixels, 0, 0);
    }

    public f.b[] getDisplayModes() {
        return new f.b[]{getDisplayMode()};
    }
}
