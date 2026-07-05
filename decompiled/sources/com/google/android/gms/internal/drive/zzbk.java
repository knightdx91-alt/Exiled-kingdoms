package com.google.android.gms.internal.drive;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.drive.MetadataChangeSet;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzbk extends zzav {
    private final /* synthetic */ zzbi zzet;
    private final /* synthetic */ MetadataChangeSet zzeu;
    private final /* synthetic */ com.google.android.gms.drive.zzn zzev;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzbk(zzbi zzbiVar, GoogleApiClient googleApiClient, MetadataChangeSet metadataChangeSet, com.google.android.gms.drive.zzn zznVar) {
        super(googleApiClient);
        this.zzet = zzbiVar;
        this.zzeu = metadataChangeSet;
        this.zzev = zznVar;
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    protected final /* synthetic */ void doExecute(Api.AnyClient anyClient) {
        zzaw zzawVar = (zzaw) anyClient;
        this.zzeu.zzp().zza(zzawVar.getContext());
        ((zzeo) zzawVar.getService()).zza(new zzm(this.zzet.zzeq.getDriveId(), this.zzeu.zzp(), this.zzet.zzeq.getRequestId(), this.zzet.zzeq.zza(), this.zzev), new zzgs(this));
    }
}
