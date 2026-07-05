package com.google.android.gms.common.api.internal;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.ComponentCallbacks2;
import android.content.res.Configuration;
import android.os.Bundle;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.util.PlatformVersion;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@KeepForSdk
public final class BackgroundDetector implements Application.ActivityLifecycleCallbacks, ComponentCallbacks2 {
    private static final BackgroundDetector zzas = new BackgroundDetector();
    private final AtomicBoolean zzat = new AtomicBoolean();
    private final AtomicBoolean zzau = new AtomicBoolean();
    private final ArrayList<BackgroundStateChangeListener> zzav = new ArrayList<>();
    private boolean zzaw = false;

    @KeepForSdk
    public interface BackgroundStateChangeListener {
        @KeepForSdk
        void onBackgroundStateChanged(boolean z2);
    }

    @KeepForSdk
    private BackgroundDetector() {
    }

    @KeepForSdk
    public static BackgroundDetector getInstance() {
        return zzas;
    }

    @KeepForSdk
    public static void initialize(Application application) {
        BackgroundDetector backgroundDetector = zzas;
        synchronized (backgroundDetector) {
            try {
                if (!backgroundDetector.zzaw) {
                    application.registerActivityLifecycleCallbacks(backgroundDetector);
                    application.registerComponentCallbacks(backgroundDetector);
                    backgroundDetector.zzaw = true;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private final void onBackgroundStateChanged(boolean z2) {
        synchronized (zzas) {
            try {
                ArrayList<BackgroundStateChangeListener> arrayList = this.zzav;
                int size = arrayList.size();
                int i2 = 0;
                while (i2 < size) {
                    BackgroundStateChangeListener backgroundStateChangeListener = arrayList.get(i2);
                    i2++;
                    backgroundStateChangeListener.onBackgroundStateChanged(z2);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @KeepForSdk
    public final void addListener(BackgroundStateChangeListener backgroundStateChangeListener) {
        synchronized (zzas) {
            this.zzav.add(backgroundStateChangeListener);
        }
    }

    @KeepForSdk
    public final boolean isInBackground() {
        return this.zzat.get();
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityCreated(Activity activity, Bundle bundle) {
        boolean zCompareAndSet = this.zzat.compareAndSet(true, false);
        this.zzau.set(true);
        if (zCompareAndSet) {
            onBackgroundStateChanged(false);
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityDestroyed(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityPaused(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityResumed(Activity activity) {
        boolean zCompareAndSet = this.zzat.compareAndSet(true, false);
        this.zzau.set(true);
        if (zCompareAndSet) {
            onBackgroundStateChanged(false);
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityStarted(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityStopped(Activity activity) {
    }

    @Override // android.content.ComponentCallbacks
    public final void onConfigurationChanged(Configuration configuration) {
    }

    @Override // android.content.ComponentCallbacks
    public final void onLowMemory() {
    }

    @Override // android.content.ComponentCallbacks2
    public final void onTrimMemory(int i2) {
        if (i2 == 20 && this.zzat.compareAndSet(false, true)) {
            this.zzau.set(true);
            onBackgroundStateChanged(true);
        }
    }

    @KeepForSdk
    @TargetApi(16)
    public final boolean readCurrentStateIfPossible(boolean z2) {
        if (!this.zzau.get()) {
            if (!PlatformVersion.isAtLeastJellyBean()) {
                return z2;
            }
            ActivityManager.RunningAppProcessInfo runningAppProcessInfo = new ActivityManager.RunningAppProcessInfo();
            ActivityManager.getMyMemoryState(runningAppProcessInfo);
            if (!this.zzau.getAndSet(true) && runningAppProcessInfo.importance > 100) {
                this.zzat.set(true);
            }
        }
        return isInBackground();
    }
}
