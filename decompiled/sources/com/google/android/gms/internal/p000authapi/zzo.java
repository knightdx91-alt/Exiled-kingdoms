package com.google.android.gms.internal.p000authapi;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzo extends zzg {
    private BaseImplementation.ResultHolder<Status> zzap;

    zzo(BaseImplementation.ResultHolder<Status> resultHolder) {
        this.zzap = resultHolder;
    }

    @Override // com.google.android.gms.internal.p000authapi.zzg, com.google.android.gms.internal.p000authapi.zzu
    public final void zzc(Status status) {
        this.zzap.setResult(status);
    }
}
