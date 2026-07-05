package com.google.android.gms.common.providers;

import com.google.android.gms.common.annotation.KeepForSdk;
import java.util.concurrent.ScheduledExecutorService;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@KeepForSdk
public class PooledExecutorsProvider {
    private static PooledExecutorFactory zzey;

    public interface PooledExecutorFactory {
        @KeepForSdk
        ScheduledExecutorService newSingleThreadScheduledExecutor();
    }

    private PooledExecutorsProvider() {
    }

    @KeepForSdk
    public static synchronized PooledExecutorFactory getInstance() {
        try {
            if (zzey == null) {
                zzey = new zza();
            }
        } catch (Throwable th) {
            throw th;
        }
        return zzey;
    }
}
