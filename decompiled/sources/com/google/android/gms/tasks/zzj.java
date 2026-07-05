package com.google.android.gms.tasks;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzj implements Runnable {
    private final /* synthetic */ Task zzg;
    private final /* synthetic */ zzi zzm;

    zzj(zzi zziVar, Task task) {
        this.zzm = zziVar;
        this.zzg = task;
    }

    @Override // java.lang.Runnable
    public final void run() {
        synchronized (this.zzm.mLock) {
            try {
                if (this.zzm.zzl != null) {
                    this.zzm.zzl.onComplete(this.zzg);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
