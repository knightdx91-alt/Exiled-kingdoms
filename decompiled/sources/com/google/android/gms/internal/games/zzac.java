package com.google.android.gms.internal.games;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzac implements Result {
    private final /* synthetic */ Status zzbc;

    zzac(zzab zzabVar, Status status) {
        this.zzbc = status;
    }

    @Override // com.google.android.gms.common.api.Result
    public final Status getStatus() {
        return this.zzbc;
    }
}
