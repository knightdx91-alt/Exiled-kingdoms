package com.badlogic.gdx.backends.android;

import android.app.WallpaperColors;
import android.os.Build;
import android.os.Bundle;
import android.service.wallpaper.WallpaperService;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.WindowManager;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.l;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public abstract class AndroidLiveWallpaperService extends WallpaperService {
    static boolean DEBUG = false;
    static final String TAG = "WallpaperService";
    protected int viewFormat;
    protected int viewHeight;
    protected int viewWidth;
    protected volatile AndroidLiveWallpaper app = null;
    protected SurfaceHolder.Callback view = null;
    protected int engines = 0;
    protected int visibleEngines = 0;
    protected volatile AndroidWallpaperEngine linkedEngine = null;
    protected volatile boolean isPreviewNotified = false;
    protected volatile boolean notifiedPreviewState = false;
    volatile int[] sync = new int[0];

    public class AndroidWallpaperEngine extends WallpaperService.Engine {
        protected int engineFormat;
        protected int engineHeight;
        protected boolean engineIsVisible;
        protected int engineWidth;
        boolean iconDropConsumed;
        boolean offsetsConsumed;
        int xIconDrop;
        float xOffset;
        float xOffsetStep;
        int xPixelOffset;
        int yIconDrop;
        float yOffset;
        float yOffsetStep;
        int yPixelOffset;

        public AndroidWallpaperEngine() {
            super(AndroidLiveWallpaperService.this);
            this.engineIsVisible = false;
            this.iconDropConsumed = true;
            this.offsetsConsumed = true;
            this.xOffset = 0.0f;
            this.yOffset = 0.0f;
            this.xOffsetStep = 0.0f;
            this.yOffsetStep = 0.0f;
            this.xPixelOffset = 0;
            this.yPixelOffset = 0;
            if (AndroidLiveWallpaperService.DEBUG) {
                Log.d(AndroidLiveWallpaperService.TAG, " > AndroidWallpaperEngine() " + hashCode());
            }
        }

        private void notifySurfaceChanged(int i2, int i3, int i4, boolean z2) {
            if (!z2) {
                AndroidLiveWallpaperService androidLiveWallpaperService = AndroidLiveWallpaperService.this;
                if (i2 == androidLiveWallpaperService.viewFormat && i3 == androidLiveWallpaperService.viewWidth && i4 == androidLiveWallpaperService.viewHeight) {
                    if (AndroidLiveWallpaperService.DEBUG) {
                        Log.d(AndroidLiveWallpaperService.TAG, " > surface is current, skipping surfaceChanged event");
                        return;
                    }
                    return;
                }
            }
            this.engineFormat = i2;
            this.engineWidth = i3;
            this.engineHeight = i4;
            if (AndroidLiveWallpaperService.this.linkedEngine != this) {
                if (AndroidLiveWallpaperService.DEBUG) {
                    Log.d(AndroidLiveWallpaperService.TAG, " > engine is not active, skipping surfaceChanged event");
                    return;
                }
                return;
            }
            AndroidLiveWallpaperService androidLiveWallpaperService2 = AndroidLiveWallpaperService.this;
            androidLiveWallpaperService2.viewFormat = this.engineFormat;
            androidLiveWallpaperService2.viewWidth = this.engineWidth;
            androidLiveWallpaperService2.viewHeight = this.engineHeight;
            SurfaceHolder.Callback callback = androidLiveWallpaperService2.view;
            SurfaceHolder surfaceHolder = getSurfaceHolder();
            AndroidLiveWallpaperService androidLiveWallpaperService3 = AndroidLiveWallpaperService.this;
            callback.surfaceChanged(surfaceHolder, androidLiveWallpaperService3.viewFormat, androidLiveWallpaperService3.viewWidth, androidLiveWallpaperService3.viewHeight);
        }

        private void notifyVisibilityChanged(boolean z2) {
            if (this.engineIsVisible == z2) {
                if (AndroidLiveWallpaperService.DEBUG) {
                    Log.d(AndroidLiveWallpaperService.TAG, " > visible state is current, skipping visibilityChanged event!");
                }
            } else {
                this.engineIsVisible = z2;
                if (z2) {
                    onResume();
                } else {
                    onPause();
                }
            }
        }

        protected void notifyIconDropped() {
            if (AndroidLiveWallpaperService.this.linkedEngine == this && (AndroidLiveWallpaperService.this.app.listener instanceof AndroidWallpaperListener) && !this.iconDropConsumed) {
                this.iconDropConsumed = true;
                AndroidLiveWallpaperService.this.app.postRunnable(new Runnable() { // from class: com.badlogic.gdx.backends.android.AndroidLiveWallpaperService.AndroidWallpaperEngine.1
                    @Override // java.lang.Runnable
                    public void run() {
                        AndroidWallpaperEngine androidWallpaperEngine;
                        boolean z2;
                        synchronized (AndroidLiveWallpaperService.this.sync) {
                            AndroidWallpaperEngine androidWallpaperEngine2 = AndroidLiveWallpaperService.this.linkedEngine;
                            androidWallpaperEngine = AndroidWallpaperEngine.this;
                            z2 = androidWallpaperEngine2 == androidWallpaperEngine;
                        }
                        if (z2) {
                            AndroidWallpaperListener androidWallpaperListener = (AndroidWallpaperListener) AndroidLiveWallpaperService.this.app.listener;
                            AndroidWallpaperEngine androidWallpaperEngine3 = AndroidWallpaperEngine.this;
                            androidWallpaperListener.iconDropped(androidWallpaperEngine3.xIconDrop, androidWallpaperEngine3.yIconDrop);
                        }
                    }
                });
            }
        }

        protected void notifyOffsetsChanged() {
            if (AndroidLiveWallpaperService.this.linkedEngine == this && (AndroidLiveWallpaperService.this.app.listener instanceof AndroidWallpaperListener) && !this.offsetsConsumed) {
                this.offsetsConsumed = true;
                AndroidLiveWallpaperService.this.app.postRunnable(new Runnable() { // from class: com.badlogic.gdx.backends.android.AndroidLiveWallpaperService.AndroidWallpaperEngine.2
                    @Override // java.lang.Runnable
                    public void run() {
                        AndroidWallpaperEngine androidWallpaperEngine;
                        boolean z2;
                        synchronized (AndroidLiveWallpaperService.this.sync) {
                            AndroidWallpaperEngine androidWallpaperEngine2 = AndroidLiveWallpaperService.this.linkedEngine;
                            androidWallpaperEngine = AndroidWallpaperEngine.this;
                            z2 = androidWallpaperEngine2 == androidWallpaperEngine;
                        }
                        if (z2) {
                            AndroidWallpaperListener androidWallpaperListener = (AndroidWallpaperListener) AndroidLiveWallpaperService.this.app.listener;
                            AndroidWallpaperEngine androidWallpaperEngine3 = AndroidWallpaperEngine.this;
                            androidWallpaperListener.offsetChange(androidWallpaperEngine3.xOffset, androidWallpaperEngine3.yOffset, androidWallpaperEngine3.xOffsetStep, androidWallpaperEngine3.yOffsetStep, androidWallpaperEngine3.xPixelOffset, androidWallpaperEngine3.yPixelOffset);
                        }
                    }
                });
            }
        }

        protected void notifyPreviewState() {
            if (AndroidLiveWallpaperService.this.linkedEngine == this && (AndroidLiveWallpaperService.this.app.listener instanceof AndroidWallpaperListener)) {
                final boolean zIsPreview = AndroidLiveWallpaperService.this.linkedEngine.isPreview();
                AndroidLiveWallpaperService.this.app.postRunnable(new Runnable() { // from class: com.badlogic.gdx.backends.android.AndroidLiveWallpaperService.AndroidWallpaperEngine.3
                    @Override // java.lang.Runnable
                    public void run() {
                        boolean z2;
                        AndroidLiveWallpaper androidLiveWallpaper;
                        synchronized (AndroidLiveWallpaperService.this.sync) {
                            try {
                                if (AndroidLiveWallpaperService.this.isPreviewNotified && AndroidLiveWallpaperService.this.notifiedPreviewState == zIsPreview) {
                                    z2 = false;
                                } else {
                                    AndroidLiveWallpaperService.this.notifiedPreviewState = zIsPreview;
                                    AndroidLiveWallpaperService.this.isPreviewNotified = true;
                                    z2 = true;
                                }
                            } catch (Throwable th) {
                                throw th;
                            }
                        }
                        if (!z2 || (androidLiveWallpaper = AndroidLiveWallpaperService.this.app) == null) {
                            return;
                        }
                        ((AndroidWallpaperListener) androidLiveWallpaper.listener).previewStateChange(zIsPreview);
                    }
                });
            }
        }

        @Override // android.service.wallpaper.WallpaperService.Engine
        public Bundle onCommand(String str, int i2, int i3, int i4, Bundle bundle, boolean z2) {
            if (AndroidLiveWallpaperService.DEBUG) {
                StringBuilder sb = new StringBuilder(" > AndroidWallpaperEngine - onCommand(");
                sb.append(str);
                sb.append(" ");
                sb.append(i2);
                sb.append(" ");
                sb.append(i3);
                sb.append(" ");
                sb.append(i4);
                sb.append(" ");
                sb.append(bundle);
                sb.append(" ");
                sb.append(z2);
                sb.append("), linked: ");
                sb.append(AndroidLiveWallpaperService.this.linkedEngine == this);
                Log.d(AndroidLiveWallpaperService.TAG, sb.toString());
            }
            if (str.equals("android.home.drop")) {
                this.iconDropConsumed = false;
                this.xIconDrop = i2;
                this.yIconDrop = i3;
                notifyIconDropped();
            }
            return super.onCommand(str, i2, i3, i4, bundle, z2);
        }

        @Override // android.service.wallpaper.WallpaperService.Engine
        public WallpaperColors onComputeColors() {
            Color[] colorArr;
            Application application = Gdx.app;
            if (Build.VERSION.SDK_INT < 27 || !(application instanceof AndroidLiveWallpaper) || (colorArr = ((AndroidLiveWallpaper) application).wallpaperColors) == null) {
                return super.onComputeColors();
            }
            Color color = colorArr[0];
            android.graphics.Color colorValueOf = android.graphics.Color.valueOf(color.f1680r, color.f1679g, color.f1678b, color.f1677a);
            Color color2 = colorArr[1];
            android.graphics.Color colorValueOf2 = android.graphics.Color.valueOf(color2.f1680r, color2.f1679g, color2.f1678b, color2.f1677a);
            Color color3 = colorArr[2];
            return new WallpaperColors(colorValueOf, colorValueOf2, android.graphics.Color.valueOf(color3.f1680r, color3.f1679g, color3.f1678b, color3.f1677a));
        }

        @Override // android.service.wallpaper.WallpaperService.Engine
        public void onCreate(SurfaceHolder surfaceHolder) {
            if (AndroidLiveWallpaperService.DEBUG) {
                StringBuilder sb = new StringBuilder(" > AndroidWallpaperEngine - onCreate() ");
                sb.append(hashCode());
                sb.append(" running: ");
                sb.append(AndroidLiveWallpaperService.this.engines);
                sb.append(", linked: ");
                sb.append(AndroidLiveWallpaperService.this.linkedEngine == this);
                sb.append(", thread: ");
                sb.append(Thread.currentThread().toString());
                Log.d(AndroidLiveWallpaperService.TAG, sb.toString());
            }
            super.onCreate(surfaceHolder);
        }

        @Override // android.service.wallpaper.WallpaperService.Engine
        public void onDestroy() {
            super.onDestroy();
        }

        @Override // android.service.wallpaper.WallpaperService.Engine
        public void onOffsetsChanged(float f2, float f3, float f4, float f5, int i2, int i3) {
            this.offsetsConsumed = false;
            this.xOffset = f2;
            this.yOffset = f3;
            this.xOffsetStep = f4;
            this.yOffsetStep = f5;
            this.xPixelOffset = i2;
            this.yPixelOffset = i3;
            notifyOffsetsChanged();
            if (!Gdx.graphics.isContinuousRendering()) {
                Gdx.graphics.requestRendering();
            }
            super.onOffsetsChanged(f2, f3, f4, f5, i2, i3);
        }

        public void onPause() {
            AndroidLiveWallpaperService.this.visibleEngines--;
            if (AndroidLiveWallpaperService.DEBUG) {
                StringBuilder sb = new StringBuilder(" > AndroidWallpaperEngine - onPause() ");
                sb.append(hashCode());
                sb.append(", running: ");
                sb.append(AndroidLiveWallpaperService.this.engines);
                sb.append(", linked: ");
                sb.append(AndroidLiveWallpaperService.this.linkedEngine == this);
                sb.append(", visible: ");
                sb.append(AndroidLiveWallpaperService.this.visibleEngines);
                Log.d(AndroidLiveWallpaperService.TAG, sb.toString());
            }
            Log.i(AndroidLiveWallpaperService.TAG, "engine paused");
            AndroidLiveWallpaperService androidLiveWallpaperService = AndroidLiveWallpaperService.this;
            if (androidLiveWallpaperService.visibleEngines >= androidLiveWallpaperService.engines) {
                Log.e(AndroidLiveWallpaperService.TAG, "wallpaper lifecycle error, counted too many visible engines! repairing..");
                AndroidLiveWallpaperService androidLiveWallpaperService2 = AndroidLiveWallpaperService.this;
                androidLiveWallpaperService2.visibleEngines = Math.max(androidLiveWallpaperService2.engines - 1, 0);
            }
            if (AndroidLiveWallpaperService.this.linkedEngine != null) {
                AndroidLiveWallpaperService androidLiveWallpaperService3 = AndroidLiveWallpaperService.this;
                if (androidLiveWallpaperService3.visibleEngines == 0) {
                    androidLiveWallpaperService3.app.onPause();
                }
            }
            if (AndroidLiveWallpaperService.DEBUG) {
                Log.d(AndroidLiveWallpaperService.TAG, " > AndroidWallpaperEngine - onPause() done!");
            }
        }

        public void onResume() {
            AndroidLiveWallpaperService.this.visibleEngines++;
            if (AndroidLiveWallpaperService.DEBUG) {
                StringBuilder sb = new StringBuilder(" > AndroidWallpaperEngine - onResume() ");
                sb.append(hashCode());
                sb.append(", running: ");
                sb.append(AndroidLiveWallpaperService.this.engines);
                sb.append(", linked: ");
                sb.append(AndroidLiveWallpaperService.this.linkedEngine == this);
                sb.append(", visible: ");
                sb.append(AndroidLiveWallpaperService.this.visibleEngines);
                Log.d(AndroidLiveWallpaperService.TAG, sb.toString());
            }
            Log.i(AndroidLiveWallpaperService.TAG, "engine resumed");
            if (AndroidLiveWallpaperService.this.linkedEngine != null) {
                if (AndroidLiveWallpaperService.this.linkedEngine != this) {
                    AndroidLiveWallpaperService.this.setLinkedEngine(this);
                    AndroidLiveWallpaperService.this.view.surfaceDestroyed(getSurfaceHolder());
                    notifySurfaceChanged(this.engineFormat, this.engineWidth, this.engineHeight, false);
                    AndroidLiveWallpaperService.this.view.surfaceCreated(getSurfaceHolder());
                } else {
                    notifySurfaceChanged(this.engineFormat, this.engineWidth, this.engineHeight, false);
                }
                AndroidLiveWallpaperService androidLiveWallpaperService = AndroidLiveWallpaperService.this;
                if (androidLiveWallpaperService.visibleEngines == 1) {
                    androidLiveWallpaperService.app.onResume();
                }
                notifyPreviewState();
                notifyOffsetsChanged();
                if (Gdx.graphics.isContinuousRendering()) {
                    return;
                }
                Gdx.graphics.requestRendering();
            }
        }

        @Override // android.service.wallpaper.WallpaperService.Engine
        public void onSurfaceChanged(SurfaceHolder surfaceHolder, int i2, int i3, int i4) {
            if (AndroidLiveWallpaperService.DEBUG) {
                StringBuilder sb = new StringBuilder(" > AndroidWallpaperEngine - onSurfaceChanged() isPreview: ");
                sb.append(isPreview());
                sb.append(", ");
                sb.append(hashCode());
                sb.append(", running: ");
                sb.append(AndroidLiveWallpaperService.this.engines);
                sb.append(", linked: ");
                sb.append(AndroidLiveWallpaperService.this.linkedEngine == this);
                sb.append(", sufcace valid: ");
                sb.append(getSurfaceHolder().getSurface().isValid());
                Log.d(AndroidLiveWallpaperService.TAG, sb.toString());
            }
            Log.i(AndroidLiveWallpaperService.TAG, "engine surface changed");
            super.onSurfaceChanged(surfaceHolder, i2, i3, i4);
            notifySurfaceChanged(i2, i3, i4, true);
        }

        @Override // android.service.wallpaper.WallpaperService.Engine
        public void onSurfaceCreated(SurfaceHolder surfaceHolder) {
            AndroidLiveWallpaperService androidLiveWallpaperService = AndroidLiveWallpaperService.this;
            androidLiveWallpaperService.engines++;
            androidLiveWallpaperService.setLinkedEngine(this);
            if (AndroidLiveWallpaperService.DEBUG) {
                StringBuilder sb = new StringBuilder(" > AndroidWallpaperEngine - onSurfaceCreated() ");
                sb.append(hashCode());
                sb.append(", running: ");
                sb.append(AndroidLiveWallpaperService.this.engines);
                sb.append(", linked: ");
                sb.append(AndroidLiveWallpaperService.this.linkedEngine == this);
                Log.d(AndroidLiveWallpaperService.TAG, sb.toString());
            }
            Log.i(AndroidLiveWallpaperService.TAG, "engine surface created");
            super.onSurfaceCreated(surfaceHolder);
            AndroidLiveWallpaperService androidLiveWallpaperService2 = AndroidLiveWallpaperService.this;
            int i2 = androidLiveWallpaperService2.engines;
            if (i2 == 1) {
                androidLiveWallpaperService2.visibleEngines = 0;
            }
            if (i2 == 1 && androidLiveWallpaperService2.app == null) {
                AndroidLiveWallpaperService androidLiveWallpaperService3 = AndroidLiveWallpaperService.this;
                androidLiveWallpaperService3.viewFormat = 0;
                androidLiveWallpaperService3.viewWidth = 0;
                androidLiveWallpaperService3.viewHeight = 0;
                androidLiveWallpaperService3.app = new AndroidLiveWallpaper(AndroidLiveWallpaperService.this);
                AndroidLiveWallpaperService.this.onCreateApplication();
                if (AndroidLiveWallpaperService.this.app.graphics == null) {
                    throw new Error("You must override 'AndroidLiveWallpaperService.onCreateApplication' method and call 'initialize' from its body.");
                }
            }
            AndroidLiveWallpaperService androidLiveWallpaperService4 = AndroidLiveWallpaperService.this;
            androidLiveWallpaperService4.view = androidLiveWallpaperService4.app.graphics.view;
            getSurfaceHolder().removeCallback(AndroidLiveWallpaperService.this.view);
            AndroidLiveWallpaperService androidLiveWallpaperService5 = AndroidLiveWallpaperService.this;
            this.engineFormat = androidLiveWallpaperService5.viewFormat;
            this.engineWidth = androidLiveWallpaperService5.viewWidth;
            this.engineHeight = androidLiveWallpaperService5.viewHeight;
            if (androidLiveWallpaperService5.engines == 1) {
                androidLiveWallpaperService5.view.surfaceCreated(surfaceHolder);
            } else {
                androidLiveWallpaperService5.view.surfaceDestroyed(surfaceHolder);
                notifySurfaceChanged(this.engineFormat, this.engineWidth, this.engineHeight, false);
                AndroidLiveWallpaperService.this.view.surfaceCreated(surfaceHolder);
            }
            notifyPreviewState();
            notifyOffsetsChanged();
            if (Gdx.graphics.isContinuousRendering()) {
                return;
            }
            Gdx.graphics.requestRendering();
        }

        @Override // android.service.wallpaper.WallpaperService.Engine
        public void onSurfaceDestroyed(SurfaceHolder surfaceHolder) {
            SurfaceHolder.Callback callback;
            AndroidLiveWallpaperService.this.engines--;
            if (AndroidLiveWallpaperService.DEBUG) {
                StringBuilder sb = new StringBuilder(" > AndroidWallpaperEngine - onSurfaceDestroyed() ");
                sb.append(hashCode());
                sb.append(", running: ");
                sb.append(AndroidLiveWallpaperService.this.engines);
                sb.append(" ,linked: ");
                sb.append(AndroidLiveWallpaperService.this.linkedEngine == this);
                sb.append(", isVisible: ");
                sb.append(this.engineIsVisible);
                Log.d(AndroidLiveWallpaperService.TAG, sb.toString());
            }
            Log.i(AndroidLiveWallpaperService.TAG, "engine surface destroyed");
            AndroidLiveWallpaperService androidLiveWallpaperService = AndroidLiveWallpaperService.this;
            if (androidLiveWallpaperService.engines == 0) {
                androidLiveWallpaperService.onDeepPauseApplication();
            }
            if (AndroidLiveWallpaperService.this.linkedEngine == this && (callback = AndroidLiveWallpaperService.this.view) != null) {
                callback.surfaceDestroyed(surfaceHolder);
            }
            this.engineFormat = 0;
            this.engineWidth = 0;
            this.engineHeight = 0;
            AndroidLiveWallpaperService androidLiveWallpaperService2 = AndroidLiveWallpaperService.this;
            if (androidLiveWallpaperService2.engines == 0) {
                androidLiveWallpaperService2.linkedEngine = null;
            }
            super.onSurfaceDestroyed(surfaceHolder);
        }

        @Override // android.service.wallpaper.WallpaperService.Engine
        public void onTouchEvent(MotionEvent motionEvent) {
            if (AndroidLiveWallpaperService.this.linkedEngine == this) {
                AndroidLiveWallpaperService.this.app.input.onTouch(null, motionEvent);
            }
        }

        @Override // android.service.wallpaper.WallpaperService.Engine
        public void onVisibilityChanged(boolean z2) {
            boolean zIsVisible = isVisible();
            if (AndroidLiveWallpaperService.DEBUG) {
                Log.d(AndroidLiveWallpaperService.TAG, " > AndroidWallpaperEngine - onVisibilityChanged(paramVisible: " + z2 + " reportedVisible: " + zIsVisible + ") " + hashCode() + ", sufcace valid: " + getSurfaceHolder().getSurface().isValid());
            }
            super.onVisibilityChanged(z2);
            if (zIsVisible || !z2) {
                notifyVisibilityChanged(z2);
            } else if (AndroidLiveWallpaperService.DEBUG) {
                Log.d(AndroidLiveWallpaperService.TAG, " > fake visibilityChanged event! Android WallpaperService likes do that!");
            }
        }
    }

    static {
        l.i();
        DEBUG = false;
    }

    protected void finalize() throws Throwable {
        Log.i(TAG, "service finalized");
        super.finalize();
    }

    public AndroidLiveWallpaper getLiveWallpaper() {
        return this.app;
    }

    public SurfaceHolder getSurfaceHolder() {
        if (DEBUG) {
            Log.d(TAG, " > AndroidLiveWallpaperService - getSurfaceHolder()");
        }
        synchronized (this.sync) {
            try {
                if (this.linkedEngine == null) {
                    return null;
                }
                return this.linkedEngine.getSurfaceHolder();
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public WindowManager getWindowManager() {
        return (WindowManager) getSystemService("window");
    }

    public void initialize(com.badlogic.gdx.a aVar) {
        initialize(aVar, new AndroidApplicationConfiguration());
    }

    @Override // android.service.wallpaper.WallpaperService, android.app.Service
    public void onCreate() {
        if (DEBUG) {
            Log.d(TAG, " > AndroidLiveWallpaperService - onCreate() " + hashCode());
        }
        Log.i(TAG, "service created");
        super.onCreate();
    }

    public void onCreateApplication() {
        if (DEBUG) {
            Log.d(TAG, " > AndroidLiveWallpaperService - onCreateApplication()");
        }
    }

    @Override // android.service.wallpaper.WallpaperService
    public WallpaperService.Engine onCreateEngine() {
        if (DEBUG) {
            Log.d(TAG, " > AndroidLiveWallpaperService - onCreateEngine()");
        }
        Log.i(TAG, "engine created");
        return new AndroidWallpaperEngine();
    }

    public void onDeepPauseApplication() {
        if (DEBUG) {
            Log.d(TAG, " > AndroidLiveWallpaperService - onDeepPauseApplication()");
        }
        if (this.app != null) {
            this.app.graphics.clearManagedCaches();
        }
    }

    @Override // android.service.wallpaper.WallpaperService, android.app.Service
    public void onDestroy() {
        if (DEBUG) {
            Log.d(TAG, " > AndroidLiveWallpaperService - onDestroy() " + hashCode());
        }
        Log.i(TAG, "service destroyed");
        super.onDestroy();
        if (this.app != null) {
            this.app.onDestroy();
            this.app = null;
            this.view = null;
        }
    }

    protected void setLinkedEngine(AndroidWallpaperEngine androidWallpaperEngine) {
        synchronized (this.sync) {
            this.linkedEngine = androidWallpaperEngine;
        }
    }

    public void initialize(com.badlogic.gdx.a aVar, AndroidApplicationConfiguration androidApplicationConfiguration) {
        if (DEBUG) {
            Log.d(TAG, " > AndroidLiveWallpaperService - initialize()");
        }
        this.app.initialize(aVar, androidApplicationConfiguration);
        if (!androidApplicationConfiguration.getTouchEventsForLiveWallpaper || Integer.parseInt(Build.VERSION.SDK) < 7) {
            return;
        }
        this.linkedEngine.setTouchEventsEnabled(true);
    }
}
