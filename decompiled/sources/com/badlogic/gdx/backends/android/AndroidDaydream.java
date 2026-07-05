package com.badlogic.gdx.backends.android;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Debug;
import android.os.Handler;
import android.os.Looper;
import android.service.dreams.DreamService;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.android.surfaceview.FillResolutionStrategy;
import com.badlogic.gdx.backends.android.surfaceview.ResolutionStrategy;
import com.badlogic.gdx.c;
import com.badlogic.gdx.d;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.k;
import com.badlogic.gdx.m;
import com.badlogic.gdx.utils.f;
import com.badlogic.gdx.utils.k0;
import com.badlogic.gdx.utils.l;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@TargetApi(17)
public class AndroidDaydream extends DreamService implements AndroidApplicationBase {
    protected com.badlogic.gdx.b applicationLogger;
    protected AndroidAudio audio;
    protected AndroidClipboard clipboard;
    protected AndroidFiles files;
    protected AndroidGraphics graphics;
    protected Handler handler;
    protected AndroidInput input;
    protected com.badlogic.gdx.a listener;

    /* JADX INFO: renamed from: net, reason: collision with root package name */
    protected AndroidNet f1606net;
    protected boolean firstResume = true;
    protected final com.badlogic.gdx.utils.a<Runnable> runnables = new com.badlogic.gdx.utils.a<>();
    protected final com.badlogic.gdx.utils.a<Runnable> executedRunnables = new com.badlogic.gdx.utils.a<>();
    protected final k0<k> lifecycleListeners = new k0<>(k.class);
    protected int logLevel = 2;

    static {
        l.i();
    }

    private void init(com.badlogic.gdx.a aVar, AndroidApplicationConfiguration androidApplicationConfiguration, boolean z2) {
        setApplicationLogger(new AndroidApplicationLogger());
        ResolutionStrategy fillResolutionStrategy = androidApplicationConfiguration.resolutionStrategy;
        if (fillResolutionStrategy == null) {
            fillResolutionStrategy = new FillResolutionStrategy();
        }
        AndroidGraphics androidGraphics = new AndroidGraphics(this, androidApplicationConfiguration, fillResolutionStrategy);
        this.graphics = androidGraphics;
        this.input = createInput(this, this, androidGraphics.view, androidApplicationConfiguration);
        this.audio = createAudio(this, androidApplicationConfiguration);
        getFilesDir();
        this.files = new AndroidFiles(getAssets(), this);
        this.f1606net = new AndroidNet(this, androidApplicationConfiguration);
        this.listener = aVar;
        this.handler = new Handler();
        this.clipboard = new AndroidClipboard(this);
        addLifecycleListener(new k() { // from class: com.badlogic.gdx.backends.android.AndroidDaydream.1
            @Override // com.badlogic.gdx.k
            public void dispose() {
                AndroidDaydream.this.audio.dispose();
                AndroidDaydream.this.audio = null;
            }

            @Override // com.badlogic.gdx.k
            public void pause() {
                AndroidDaydream.this.audio.pause();
            }

            @Override // com.badlogic.gdx.k
            public void resume() {
                AndroidDaydream.this.audio.resume();
            }
        });
        Gdx.app = this;
        Gdx.input = getInput();
        Gdx.audio = getAudio();
        Gdx.files = getFiles();
        Gdx.graphics = getGraphics();
        Gdx.f1569net = getNet();
        if (!z2) {
            setFullscreen(true);
            setContentView(this.graphics.getView(), createLayoutParams());
        }
        createWakeLock(androidApplicationConfiguration.useWakelock);
        hideStatusBar(androidApplicationConfiguration);
        if (getResources().getConfiguration().keyboard != 1) {
            this.input.setKeyboardAvailable(true);
        }
    }

    @Override // com.badlogic.gdx.Application
    public void addLifecycleListener(k kVar) {
        synchronized (this.lifecycleListeners) {
            this.lifecycleListeners.a(kVar);
        }
    }

    @Override // com.badlogic.gdx.backends.android.AndroidApplicationBase
    public AndroidAudio createAudio(Context context, AndroidApplicationConfiguration androidApplicationConfiguration) {
        return new DefaultAndroidAudio(context, androidApplicationConfiguration);
    }

    @Override // com.badlogic.gdx.backends.android.AndroidApplicationBase
    public AndroidInput createInput(Application application, Context context, Object obj, AndroidApplicationConfiguration androidApplicationConfiguration) {
        return new DefaultAndroidInput(this, this, this.graphics.view, androidApplicationConfiguration);
    }

    protected FrameLayout.LayoutParams createLayoutParams() {
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        layoutParams.gravity = 17;
        return layoutParams;
    }

    protected void createWakeLock(boolean z2) {
        if (z2) {
            getWindow().addFlags(VertexAttributes.Usage.Tangent);
        }
    }

    @Override // com.badlogic.gdx.Application
    public void debug(String str, String str2) {
        if (this.logLevel >= 3) {
            getApplicationLogger().debug(str, str2);
        }
    }

    @Override // com.badlogic.gdx.Application
    public void error(String str, String str2) {
        if (this.logLevel >= 1) {
            getApplicationLogger().error(str, str2);
        }
    }

    @Override // com.badlogic.gdx.Application
    public void exit() {
        this.handler.post(new Runnable() { // from class: com.badlogic.gdx.backends.android.AndroidDaydream.2
            @Override // java.lang.Runnable
            public void run() {
                AndroidDaydream.this.finish();
            }
        });
    }

    @Override // com.badlogic.gdx.Application
    public com.badlogic.gdx.a getApplicationListener() {
        return this.listener;
    }

    @Override // com.badlogic.gdx.Application
    public com.badlogic.gdx.b getApplicationLogger() {
        return this.applicationLogger;
    }

    @Override // com.badlogic.gdx.backends.android.AndroidApplicationBase
    public Window getApplicationWindow() {
        return getWindow();
    }

    @Override // com.badlogic.gdx.Application
    public c getAudio() {
        return this.audio;
    }

    @Override // com.badlogic.gdx.Application
    public f getClipboard() {
        return this.clipboard;
    }

    @Override // com.badlogic.gdx.backends.android.AndroidApplicationBase
    public Context getContext() {
        return this;
    }

    @Override // com.badlogic.gdx.backends.android.AndroidApplicationBase
    public com.badlogic.gdx.utils.a<Runnable> getExecutedRunnables() {
        return this.executedRunnables;
    }

    @Override // com.badlogic.gdx.Application
    public d getFiles() {
        return this.files;
    }

    @Override // com.badlogic.gdx.Application
    public com.badlogic.gdx.f getGraphics() {
        return this.graphics;
    }

    @Override // com.badlogic.gdx.backends.android.AndroidApplicationBase
    public Handler getHandler() {
        return this.handler;
    }

    @Override // com.badlogic.gdx.Application
    public long getJavaHeap() {
        return Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
    }

    @Override // com.badlogic.gdx.backends.android.AndroidApplicationBase
    public k0<k> getLifecycleListeners() {
        return this.lifecycleListeners;
    }

    @Override // com.badlogic.gdx.Application
    public int getLogLevel() {
        return this.logLevel;
    }

    @Override // com.badlogic.gdx.Application
    public long getNativeHeap() {
        return Debug.getNativeHeapAllocatedSize();
    }

    @Override // com.badlogic.gdx.Application
    public com.badlogic.gdx.l getNet() {
        return this.f1606net;
    }

    @Override // com.badlogic.gdx.Application
    public m getPreferences(String str) {
        return new AndroidPreferences(getSharedPreferences(str, 0));
    }

    @Override // com.badlogic.gdx.backends.android.AndroidApplicationBase
    public com.badlogic.gdx.utils.a<Runnable> getRunnables() {
        return this.runnables;
    }

    @Override // com.badlogic.gdx.Application
    public Application.a getType() {
        return Application.a.f1563a;
    }

    @Override // com.badlogic.gdx.Application
    public int getVersion() {
        return Build.VERSION.SDK_INT;
    }

    protected void hideStatusBar(AndroidApplicationConfiguration androidApplicationConfiguration) {
        if (androidApplicationConfiguration.hideStatusBar) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(0);
            decorView.setSystemUiVisibility(1);
        }
    }

    public void initialize(com.badlogic.gdx.a aVar) {
        initialize(aVar, new AndroidApplicationConfiguration());
    }

    public View initializeForView(com.badlogic.gdx.a aVar) {
        return initializeForView(aVar, new AndroidApplicationConfiguration());
    }

    @Override // com.badlogic.gdx.Application
    public void log(String str, String str2) {
        if (this.logLevel >= 2) {
            getApplicationLogger().log(str, str2);
        }
    }

    @Override // android.app.Service, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.input.setKeyboardAvailable(configuration.hardKeyboardHidden == 1);
    }

    @Override // android.service.dreams.DreamService, android.view.Window.Callback
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    @Override // android.service.dreams.DreamService
    public void onDreamingStarted() {
        Gdx.app = this;
        Gdx.input = getInput();
        Gdx.audio = getAudio();
        Gdx.files = getFiles();
        Gdx.graphics = getGraphics();
        Gdx.f1569net = getNet();
        this.input.onDreamingStarted();
        AndroidGraphics androidGraphics = this.graphics;
        if (androidGraphics != null) {
            androidGraphics.onResumeGLSurfaceView();
        }
        if (this.firstResume) {
            this.firstResume = false;
        } else {
            this.graphics.resume();
        }
        super.onDreamingStarted();
    }

    @Override // android.service.dreams.DreamService
    public void onDreamingStopped() {
        boolean zIsContinuousRendering = this.graphics.isContinuousRendering();
        this.graphics.setContinuousRendering(true);
        this.graphics.pause();
        this.input.onDreamingStopped();
        this.graphics.clearManagedCaches();
        this.graphics.destroy();
        this.graphics.setContinuousRendering(zIsContinuousRendering);
        this.graphics.onPauseGLSurfaceView();
        super.onDreamingStopped();
    }

    @Override // com.badlogic.gdx.Application
    public void postRunnable(Runnable runnable) {
        synchronized (this.runnables) {
            this.runnables.a(runnable);
            Gdx.graphics.requestRendering();
        }
    }

    @Override // com.badlogic.gdx.Application
    public void removeLifecycleListener(k kVar) {
        synchronized (this.lifecycleListeners) {
            this.lifecycleListeners.q(kVar, true);
        }
    }

    @Override // com.badlogic.gdx.backends.android.AndroidApplicationBase
    public void runOnUiThread(Runnable runnable) {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            new Handler(Looper.getMainLooper()).post(runnable);
        } else {
            runnable.run();
        }
    }

    @Override // com.badlogic.gdx.Application
    public void setApplicationLogger(com.badlogic.gdx.b bVar) {
        this.applicationLogger = bVar;
    }

    @Override // com.badlogic.gdx.Application
    public void setLogLevel(int i2) {
        this.logLevel = i2;
    }

    @Override // com.badlogic.gdx.backends.android.AndroidApplicationBase
    public void useImmersiveMode(boolean z2) {
        throw new UnsupportedOperationException();
    }

    @Override // com.badlogic.gdx.Application
    public void debug(String str, String str2, Throwable th) {
        if (this.logLevel >= 3) {
            getApplicationLogger().debug(str, str2, th);
        }
    }

    @Override // com.badlogic.gdx.Application
    public void error(String str, String str2, Throwable th) {
        if (this.logLevel >= 1) {
            getApplicationLogger().error(str, str2, th);
        }
    }

    @Override // com.badlogic.gdx.Application
    public AndroidInput getInput() {
        return this.input;
    }

    @Override // com.badlogic.gdx.Application
    public void log(String str, String str2, Throwable th) {
        if (this.logLevel >= 2) {
            getApplicationLogger().log(str, str2, th);
        }
    }

    public void initialize(com.badlogic.gdx.a aVar, AndroidApplicationConfiguration androidApplicationConfiguration) {
        init(aVar, androidApplicationConfiguration, false);
    }

    public View initializeForView(com.badlogic.gdx.a aVar, AndroidApplicationConfiguration androidApplicationConfiguration) {
        init(aVar, androidApplicationConfiguration, true);
        return this.graphics.getView();
    }
}
