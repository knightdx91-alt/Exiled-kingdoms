package com.google.android.gms.tasks;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzh implements Runnable {
    private final /* synthetic */ zzg zzk;

    zzh(zzg zzgVar) {
        this.zzk = zzgVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        synchronized (this.zzk.mLock) {
            try {
                if (this.zzk.zzj != null) {
                    this.zzk.zzj.onCanceled();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
