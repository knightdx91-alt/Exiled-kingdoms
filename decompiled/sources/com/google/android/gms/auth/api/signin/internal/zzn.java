package com.google.android.gms.auth.api.signin.internal;

import com.google.android.gms.common.api.Status;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzn extends zzc {
    private final /* synthetic */ zzm zzbm;

    zzn(zzm zzmVar) {
        this.zzbm = zzmVar;
    }

    @Override // com.google.android.gms.auth.api.signin.internal.zzc, com.google.android.gms.auth.api.signin.internal.zzs
    public final void zzf(Status status) {
        this.zzbm.setResult(status);
    }
}
