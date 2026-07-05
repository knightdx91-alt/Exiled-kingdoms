package com.google.android.gms.auth.api.signin.internal;

import com.google.android.gms.common.api.Status;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzl extends zzc {
    private final /* synthetic */ zzk zzbl;

    zzl(zzk zzkVar) {
        this.zzbl = zzkVar;
    }

    @Override // com.google.android.gms.auth.api.signin.internal.zzc, com.google.android.gms.auth.api.signin.internal.zzs
    public final void zze(Status status) {
        this.zzbl.setResult(status);
    }
}
