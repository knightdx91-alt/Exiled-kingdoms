package com.google.android.gms.tasks;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class CancellationTokenSource {
    private final zza zzc = new zza();

    public void cancel() {
        this.zzc.cancel();
    }

    public CancellationToken getToken() {
        return this.zzc;
    }
}
