package com.google.android.gms.internal.drive;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import java.util.List;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzds extends zzav {
    private final /* synthetic */ List zzfz;
    private final /* synthetic */ zzdp zzgo;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzds(zzdp zzdpVar, GoogleApiClient googleApiClient, List list) {
        super(googleApiClient);
        this.zzgo = zzdpVar;
        this.zzfz = list;
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    protected final /* synthetic */ void doExecute(Api.AnyClient anyClient) {
        ((zzeo) ((zzaw) anyClient).getService()).zza(new zzgq(this.zzgo.zzk, this.zzfz), new zzgs(this));
    }
}
