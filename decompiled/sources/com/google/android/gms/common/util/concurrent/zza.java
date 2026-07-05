package com.google.android.gms.common.util.concurrent;

import android.os.Process;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zza implements Runnable {
    private final int priority;
    private final Runnable zzhs;

    public zza(Runnable runnable, int i2) {
        this.zzhs = runnable;
        this.priority = i2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Process.setThreadPriority(this.priority);
        this.zzhs.run();
    }
}
