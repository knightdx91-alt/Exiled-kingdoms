package com.google.android.gms.tasks;

import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class TaskExecutors {
    public static final Executor MAIN_THREAD = new zza();
    static final Executor zzw = new zzt();

    private static final class zza implements Executor {
        private final Handler mHandler = new Handler(Looper.getMainLooper());

        @Override // java.util.concurrent.Executor
        public final void execute(Runnable runnable) {
            this.mHandler.post(runnable);
        }
    }

    private TaskExecutors() {
    }
}
