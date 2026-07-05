package com.google.android.gms.common.api.internal;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzb implements Runnable {
    private final /* synthetic */ LifecycleCallback zzbh;
    private final /* synthetic */ String zzbi;
    private final /* synthetic */ zza zzbj;

    zzb(zza zzaVar, LifecycleCallback lifecycleCallback, String str) {
        this.zzbj = zzaVar;
        this.zzbh = lifecycleCallback;
        this.zzbi = str;
    }

    @Override // java.lang.Runnable
    public final void run() {
        if (this.zzbj.zzbf > 0) {
            this.zzbh.onCreate(this.zzbj.zzbg != null ? this.zzbj.zzbg.getBundle(this.zzbi) : null);
        }
        if (this.zzbj.zzbf >= 2) {
            this.zzbh.onStart();
        }
        if (this.zzbj.zzbf >= 3) {
            this.zzbh.onResume();
        }
        if (this.zzbj.zzbf >= 4) {
            this.zzbh.onStop();
        }
        if (this.zzbj.zzbf >= 5) {
            this.zzbh.onDestroy();
        }
    }
}
