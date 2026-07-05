package com.google.android.gms.common.api.internal;

import android.app.Activity;
import com.google.android.gms.common.annotation.KeepForSdk;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@KeepForSdk
public abstract class ActivityLifecycleObserver {
    @KeepForSdk
    public static final ActivityLifecycleObserver of(Activity activity) {
        return new zaa(activity);
    }

    @KeepForSdk
    public abstract ActivityLifecycleObserver onStopCallOnce(Runnable runnable);
}
