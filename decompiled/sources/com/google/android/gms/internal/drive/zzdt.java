package com.google.android.gms.internal.drive;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.drive.MetadataChangeSet;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzdt extends zzea {
    private final /* synthetic */ MetadataChangeSet zzfb;
    private final /* synthetic */ zzdp zzgo;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzdt(zzdp zzdpVar, GoogleApiClient googleApiClient, MetadataChangeSet metadataChangeSet) {
        super(zzdpVar, googleApiClient, null);
        this.zzgo = zzdpVar;
        this.zzfb = metadataChangeSet;
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    protected final /* synthetic */ void doExecute(Api.AnyClient anyClient) {
        zzaw zzawVar = (zzaw) anyClient;
        this.zzfb.zzp().zza(zzawVar.getContext());
        ((zzeo) zzawVar.getService()).zza(new zzgz(this.zzgo.zzk, this.zzfb.zzp()), new zzdy(this));
    }
}
