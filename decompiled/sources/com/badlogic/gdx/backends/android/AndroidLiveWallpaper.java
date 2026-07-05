package com.badlogic.gdx.backends.android;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Debug;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.android.AndroidLiveWallpaperService;
import com.badlogic.gdx.backends.android.surfaceview.FillResolutionStrategy;
import com.badlogic.gdx.backends.android.surfaceview.ResolutionStrategy;
import com.badlogic.gdx.c;
import com.badlogic.gdx.d;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.k;
import com.badlogic.gdx.m;
import com.badlogic.gdx.utils.f;
import com.badlogic.gdx.utils.k0;
import com.badlogic.gdx.utils.l;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class AndroidLiveWallpaper implements AndroidApplicationBase {
    protected com.badlogic.gdx.b applicationLogger;
    protected AndroidAudio audio;
    protected AndroidClipboard clipboard;
    protected AndroidFiles files;
    protected AndroidGraphicsLiveWallpaper graphics;
    protected AndroidInput input;
    protected com.badlogic.gdx.a listener;

    /* JADX INFO: renamed from: net, reason: collision with root package name */
    protected AndroidNet f1608net;
    protected AndroidLiveWallpaperService service;
    protected boolean firstResume = true;
    protected final com.badlogic.gdx.utils.a<Runnable> runnables = new com.badlogic.gdx.utils.a<>();
    protected final com.badlogic.gdx.utils.a<Runnable> executedRunnables = new com.badlogic.gdx.utils.a<>();
    protected final k0<k> lifecycleListeners = new k0<>(k.class);
    protected int logLevel = 2;
    protected volatile Color[] wallpaperColors = null;

    static {
        l.i();
    }

    public AndroidLiveWallpaper(AndroidLiveWallpaperService androidLiveWallpaperService) {
        this.service = androidLiveWallpaperService;
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
        return new DefaultAndroidInput(this, getService(), this.graphics.view, androidApplicationConfiguration);
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
        throw new UnsupportedOperationException();
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
        return this.service;
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
        throw new UnsupportedOperationException();
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
        return this.f1608net;
    }

    @Override // com.badlogic.gdx.Application
    public m getPreferences(String str) {
        return new AndroidPreferences(this.service.getSharedPreferences(str, 0));
    }

    @Override // com.badlogic.gdx.backends.android.AndroidApplicationBase
    public com.badlogic.gdx.utils.a<Runnable> getRunnables() {
        return this.runnables;
    }

    public AndroidLiveWallpaperService getService() {
        return this.service;
    }

    @Override // com.badlogic.gdx.Application
    public Application.a getType() {
        return Application.a.f1563a;
    }

    @Override // com.badlogic.gdx.Application
    public int getVersion() {
        return Build.VERSION.SDK_INT;
    }

    @Override // com.badlogic.gdx.backends.android.AndroidApplicationBase
    public WindowManager getWindowManager() {
        return this.service.getWindowManager();
    }

    public void initialize(com.badlogic.gdx.a aVar, AndroidApplicationConfiguration androidApplicationConfiguration) {
        if (getVersion() < 14) {
            throw new com.badlogic.gdx.utils.m("LibGDX requires Android API Level 14 or later.");
        }
        setApplicationLogger(new AndroidApplicationLogger());
        ResolutionStrategy fillResolutionStrategy = androidApplicationConfiguration.resolutionStrategy;
        if (fillResolutionStrategy == null) {
            fillResolutionStrategy = new FillResolutionStrategy();
        }
        this.graphics = new AndroidGraphicsLiveWallpaper(this, androidApplicationConfiguration, fillResolutionStrategy);
        this.input = createInput(this, getService(), this.graphics.view, androidApplicationConfiguration);
        this.audio = createAudio(getService(), androidApplicationConfiguration);
        getService().getFilesDir();
        this.files = new AndroidFiles(getService().getAssets(), getService());
        this.f1608net = new AndroidNet(this, androidApplicationConfiguration);
        this.listener = aVar;
        this.clipboard = new AndroidClipboard(getService());
        Gdx.app = this;
        Gdx.input = this.input;
        Gdx.audio = this.audio;
        Gdx.files = this.files;
        Gdx.graphics = this.graphics;
        Gdx.f1569net = this.f1608net;
    }

    @Override // com.badlogic.gdx.Application
    public void log(String str, String str2) {
        if (this.logLevel >= 2) {
            getApplicationLogger().log(str, str2);
        }
    }

    public void notifyColorsChanged(Color color, Color color2, Color color3) {
        if (Build.VERSION.SDK_INT < 27) {
            return;
        }
        this.wallpaperColors = new Color[]{new Color(color), new Color(color2), new Color(color3)};
        AndroidLiveWallpaperService.AndroidWallpaperEngine androidWallpaperEngine = this.service.linkedEngine;
        if (androidWallpaperEngine != null) {
            androidWallpaperEngine.notifyColorsChanged();
        }
    }

    public void onDestroy() {
        AndroidGraphicsLiveWallpaper androidGraphicsLiveWallpaper = this.graphics;
        if (androidGraphicsLiveWallpaper != null) {
            androidGraphicsLiveWallpaper.onDestroyGLSurfaceView();
        }
        AndroidAudio androidAudio = this.audio;
        if (androidAudio != null) {
            androidAudio.dispose();
        }
    }

    public void onPause() {
        if (AndroidLiveWallpaperService.DEBUG) {
            Log.d("WallpaperService", " > AndroidLiveWallpaper - onPause()");
        }
        this.audio.pause();
        this.input.onPause();
        AndroidGraphicsLiveWallpaper androidGraphicsLiveWallpaper = this.graphics;
        if (androidGraphicsLiveWallpaper != null) {
            androidGraphicsLiveWallpaper.onPauseGLSurfaceView();
        }
        if (AndroidLiveWallpaperService.DEBUG) {
            Log.d("WallpaperService", " > AndroidLiveWallpaper - onPause() done!");
        }
    }

    public void onResume() {
        Gdx.app = this;
        AndroidInput androidInput = this.input;
        Gdx.input = androidInput;
        Gdx.audio = this.audio;
        Gdx.files = this.files;
        Gdx.graphics = this.graphics;
        Gdx.f1569net = this.f1608net;
        androidInput.onResume();
        AndroidGraphicsLiveWallpaper androidGraphicsLiveWallpaper = this.graphics;
        if (androidGraphicsLiveWallpaper != null) {
            androidGraphicsLiveWallpaper.onResumeGLSurfaceView();
        }
        if (this.firstResume) {
            this.firstResume = false;
        } else {
            this.audio.resume();
            this.graphics.resume();
        }
    }

    @Override // com.badlogic.gdx.Application
    public void postRunnable(Runnable runnable) {
        synchronized (this.runnables) {
            this.runnables.a(runnable);
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
    public void startActivity(Intent intent) {
        this.service.startActivity(intent);
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
}
