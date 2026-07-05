package com.google.android.gms.internal.auth;

import com.google.android.gms.common.api.Status;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzm extends zzn {
    private final /* synthetic */ zzl zzag;

    zzm(zzl zzlVar) {
        this.zzag = zzlVar;
    }

    @Override // com.google.android.gms.internal.auth.zzn, com.google.android.gms.auth.account.zza
    public final void zza(boolean z2) {
        this.zzag.setResult(new zzq(z2 ? Status.RESULT_SUCCESS : zzh.zzad));
    }
}
