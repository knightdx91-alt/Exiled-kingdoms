package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.ConnectionResult;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zaao extends zabf {
    private final /* synthetic */ ConnectionResult zagl;
    private final /* synthetic */ zaan zagm;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zaao(zaan zaanVar, zabd zabdVar, ConnectionResult connectionResult) {
        super(zabdVar);
        this.zagm = zaanVar;
        this.zagl = connectionResult;
    }

    @Override // com.google.android.gms.common.api.internal.zabf
    public final void zaan() {
        this.zagm.zagi.zae(this.zagl);
    }
}
