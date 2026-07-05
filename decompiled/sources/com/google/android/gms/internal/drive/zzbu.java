package com.google.android.gms.internal.drive;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.drive.MetadataChangeSet;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzbu extends zzca {
    private final /* synthetic */ MetadataChangeSet zzfb;
    private final /* synthetic */ zzbs zzff;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzbu(zzbs zzbsVar, GoogleApiClient googleApiClient, MetadataChangeSet metadataChangeSet) {
        super(googleApiClient);
        this.zzff = zzbsVar;
        this.zzfb = metadataChangeSet;
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    protected final /* synthetic */ void doExecute(Api.AnyClient anyClient) {
        zzaw zzawVar = (zzaw) anyClient;
        this.zzfb.zzp().zza(zzawVar.getContext());
        ((zzeo) zzawVar.getService()).zza(new zzy(this.zzff.getDriveId(), this.zzfb.zzp()), new zzbw(this));
    }
}
