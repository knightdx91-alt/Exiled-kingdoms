package com.google.android.gms.internal.p000authapi;

import com.google.android.gms.auth.api.credentials.Credential;
import com.google.android.gms.common.api.Status;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzk extends zzg {
    private final /* synthetic */ zzj zzan;

    zzk(zzj zzjVar) {
        this.zzan = zzjVar;
    }

    @Override // com.google.android.gms.internal.p000authapi.zzg, com.google.android.gms.internal.p000authapi.zzu
    public final void zzc(Status status, Credential credential) {
        this.zzan.setResult(new zzh(status, credential));
    }

    @Override // com.google.android.gms.internal.p000authapi.zzg, com.google.android.gms.internal.p000authapi.zzu
    public final void zzc(Status status) {
        this.zzan.setResult(zzh.zzd(status));
    }
}
