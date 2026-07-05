package com.google.android.gms.games;

import com.google.android.gms.common.api.Status;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zze implements com.google.android.gms.games.internal.zzr {
    zze() {
    }

    @Override // com.google.android.gms.games.internal.zzr
    public final boolean zza(Status status) {
        return status.isSuccess() || status.getStatusCode() == 3003;
    }
}
