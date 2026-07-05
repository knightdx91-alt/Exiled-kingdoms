package com.badlogic.gdx.backends.android;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Debug;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.android.surfaceview.FillResolutionStrategy;
import com.badlogic.gdx.backends.android.surfaceview.ResolutionStrategy;
import com.badlogic.gdx.c;
import com.badlogic.gdx.d;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.k;
import com.badlogic.gdx.utils.f;
import com.badlogic.gdx.utils.k0;
import com.badlogic.gdx.utils.l;
import com.badlogic.gdx.utils.m;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class AndroidApplication extends Activity implements AndroidApplicationBase {
    protected com.badlogic.gdx.b applicationLogger;
    protected AndroidAudio audio;
    protected AndroidClipboard clipboard;
    protected AndroidFiles files;
    protected AndroidGraphics graphics;
    public Handler handler;
    protected AndroidInput input;
    protected com.badlogic.gdx.a listener;

    /* JADX INFO: renamed from: net, reason: collision with root package name */
    protected AndroidNet f1601net;
    protected boolean firstResume = true;
    protected final com.badlogic.gdx.utils.a<Runnable> runnables = new com.badlogic.gdx.utils.a<>();
    protected final com.badlogic.gdx.utils.a<Runnable> executedRunnables = new com.badlogic.gdx.utils.a<>();
    protected final k0<k> lifecycleListeners = new k0<>(k.class);
    private final com.badlogic.gdx.utils.a<AndroidEventListener> androidEventListeners = new com.badlogic.gdx.utils.a<>();
    protected int logLevel = 2;
    protected boolean useImmersiveMode = false;
    protected boolean hideStatusBar = false;
    private int wasFocusChanged = -1;
    private boolean isWaitingForAudio = false;

    static {
        l.i();
    }

    private void init(com.badlogic.gdx.a aVar, AndroidApplicationConfiguration androidApplicationConfiguration, boolean z2) {
        if (getVersion() < 14) {
            throw new m("LibGDX requires Android API Level 14 or later.");
        }
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
        this.f1601net = new AndroidNet(this, androidApplicationConfiguration);
        this.listener = aVar;
        this.handler = new Handler();
        this.useImmersiveMode = androidApplicationConfiguration.useImmersiveMode;
        this.hideStatusBar = androidApplicationConfiguration.hideStatusBar;
        this.clipboard = new AndroidClipboard(this);
        addLifecycleListener(new k() { // from class: com.badlogic.gdx.backends.android.AndroidApplication.1
            @Override // com.badlogic.gdx.k
            public void dispose() {
                AndroidApplication.this.audio.dispose();
            }

            @Override // com.badlogic.gdx.k
            public void pause() {
                AndroidApplication.this.audio.pause();
            }

            @Override // com.badlogic.gdx.k
            public void resume() {
            }
        });
        Gdx.app = this;
        Gdx.input = getInput();
        Gdx.audio = getAudio();
        Gdx.files = getFiles();
        Gdx.graphics = getGraphics();
        Gdx.f1569net = getNet();
        if (!z2) {
            try {
                requestWindowFeature(1);
            } catch (Exception e2) {
                log("AndroidApplication", "Content already displayed, cannot request FEATURE_NO_TITLE", e2);
            }
            getWindow().setFlags(GL20.GL_STENCIL_BUFFER_BIT, GL20.GL_STENCIL_BUFFER_BIT);
            getWindow().clearFlags(2048);
            setContentView(this.graphics.getView(), createLayoutParams());
        }
        createWakeLock(androidApplicationConfiguration.useWakelock);
        hideStatusBar(this.hideStatusBar);
        useImmersiveMode(this.useImmersiveMode);
        if (this.useImmersiveMode && getVersion() >= 19) {
            new AndroidVisibilityListener().createListener(this);
        }
        if (getResources().getConfiguration().keyboard != 1) {
            this.input.setKeyboardAvailable(true);
        }
    }

    public void addAndroidEventListener(AndroidEventListener androidEventListener) {
        synchronized (this.androidEventListeners) {
            this.androidEventListeners.a(androidEventListener);
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
        this.handler.post(new Runnable() { // from class: com.badlogic.gdx.backends.android.AndroidApplication.2
            @Override // java.lang.Runnable
            public void run() {
                AndroidApplication.this.finish();
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
        return this.f1601net;
    }

    @Override // com.badlogic.gdx.Application
    public com.badlogic.gdx.m getPreferences(String str) {
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

    protected void hideStatusBar(boolean z2) {
        if (z2) {
            getWindow().getDecorView().setSystemUiVisibility(1);
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

    @Override // android.app.Activity
    protected void onActivityResult(int i2, int i3, Intent intent) {
        super.onActivityResult(i2, i3, intent);
        synchronized (this.androidEventListeners) {
            int i4 = 0;
            while (true) {
                try {
                    com.badlogic.gdx.utils.a<AndroidEventListener> aVar = this.androidEventListeners;
                    if (i4 < aVar.f1750b) {
                        aVar.get(i4).onActivityResult(i2, i3, intent);
                        i4++;
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.input.setKeyboardAvailable(configuration.hardKeyboardHidden == 1);
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override // android.app.Activity
    protected void onPause() {
        boolean zIsContinuousRendering = this.graphics.isContinuousRendering();
        boolean z2 = AndroidGraphics.enforceContinuousRendering;
        AndroidGraphics.enforceContinuousRendering = true;
        this.graphics.setContinuousRendering(true);
        this.graphics.pause();
        this.input.onPause();
        if (isFinishing()) {
            this.graphics.clearManagedCaches();
            this.graphics.destroy();
        }
        AndroidGraphics.enforceContinuousRendering = z2;
        this.graphics.setContinuousRendering(zIsContinuousRendering);
        this.graphics.onPauseGLSurfaceView();
        super.onPause();
    }

    @Override // android.app.Activity
    protected void onResume() {
        Gdx.app = this;
        Gdx.input = getInput();
        Gdx.audio = getAudio();
        Gdx.files = getFiles();
        Gdx.graphics = getGraphics();
        Gdx.f1569net = getNet();
        this.input.onResume();
        AndroidGraphics androidGraphics = this.graphics;
        if (androidGraphics != null) {
            androidGraphics.onResumeGLSurfaceView();
        }
        if (this.firstResume) {
            this.firstResume = false;
        } else {
            this.graphics.resume();
        }
        this.isWaitingForAudio = true;
        int i2 = this.wasFocusChanged;
        if (i2 == 1 || i2 == -1) {
            this.audio.resume();
            this.isWaitingForAudio = false;
        }
        super.onResume();
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean z2) {
        super.onWindowFocusChanged(z2);
        useImmersiveMode(this.useImmersiveMode);
        hideStatusBar(this.hideStatusBar);
        if (!z2) {
            this.wasFocusChanged = 0;
            return;
        }
        this.wasFocusChanged = 1;
        if (this.isWaitingForAudio) {
            this.audio.resume();
            this.isWaitingForAudio = false;
        }
    }

    @Override // com.badlogic.gdx.Application
    public void postRunnable(Runnable runnable) {
        synchronized (this.runnables) {
            this.runnables.a(runnable);
            Gdx.graphics.requestRendering();
        }
    }

    public void removeAndroidEventListener(AndroidEventListener androidEventListener) {
        synchronized (this.androidEventListeners) {
            this.androidEventListeners.q(androidEventListener, true);
        }
    }

    @Override // com.badlogic.gdx.Application
    public void removeLifecycleListener(k kVar) {
        synchronized (this.lifecycleListeners) {
            this.lifecycleListeners.q(kVar, true);
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
    @TargetApi(19)
    public void useImmersiveMode(boolean z2) {
        if (!z2 || getVersion() < 19) {
            return;
        }
        getWindow().getDecorView().setSystemUiVisibility(5894);
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
