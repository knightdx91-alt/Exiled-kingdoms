package com.google.android.gms.internal.auth;

import android.content.Context;
import com.google.android.gms.common.api.GoogleApiClient;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzau extends zzaq {
    zzau(zzar zzarVar, GoogleApiClient googleApiClient) {
        super(googleApiClient);
    }

    @Override // com.google.android.gms.internal.auth.zzaq
    protected final void zza(Context context, zzan zzanVar) {
        zzanVar.zza(new zzav(this));
    }
}
