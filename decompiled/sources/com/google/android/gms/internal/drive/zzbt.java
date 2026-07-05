package com.google.android.gms.internal.drive;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.drive.ExecutionOptions;
import com.google.android.gms.drive.MetadataChangeSet;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzbt extends zzby {
    private final /* synthetic */ MetadataChangeSet zzfb;
    private final /* synthetic */ int zzfc;
    private final /* synthetic */ int zzfd;
    private final /* synthetic */ ExecutionOptions zzfe;
    private final /* synthetic */ zzbs zzff;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzbt(zzbs zzbsVar, GoogleApiClient googleApiClient, MetadataChangeSet metadataChangeSet, int i2, int i3, ExecutionOptions executionOptions) {
        super(googleApiClient);
        this.zzff = zzbsVar;
        this.zzfb = metadataChangeSet;
        this.zzfc = i2;
        this.zzfd = i3;
        this.zzfe = executionOptions;
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    protected final /* synthetic */ void doExecute(Api.AnyClient anyClient) {
        zzaw zzawVar = (zzaw) anyClient;
        this.zzfb.zzp().zza(zzawVar.getContext());
        ((zzeo) zzawVar.getService()).zza(new zzw(this.zzff.getDriveId(), this.zzfb.zzp(), this.zzfc, this.zzfd, this.zzfe), new zzbv(this));
    }
}
