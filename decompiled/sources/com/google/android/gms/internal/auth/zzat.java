package com.google.android.gms.internal.auth;

import com.google.android.gms.auth.api.proxy.ProxyResponse;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzat extends zzaj {
    private final /* synthetic */ zzas zzcf;

    zzat(zzas zzasVar) {
        this.zzcf = zzasVar;
    }

    @Override // com.google.android.gms.internal.auth.zzaj, com.google.android.gms.internal.auth.zzal
    public final void zza(ProxyResponse proxyResponse) {
        this.zzcf.setResult(new zzaw(proxyResponse));
    }
}
