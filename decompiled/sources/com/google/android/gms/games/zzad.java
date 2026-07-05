package com.google.android.gms.games;

import com.google.android.gms.common.api.Status;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzad implements com.google.android.gms.games.internal.zzr {
    zzad() {
    }

    @Override // com.google.android.gms.games.internal.zzr
    public final boolean zza(Status status) {
        return status.isSuccess() || status.getStatusCode() == 5;
    }
}
