package com.google.android.gms.internal.drive;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.drive.DriveFile;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzbj extends zzam {
    private final /* synthetic */ zzbi zzet;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzbj(zzbi zzbiVar, GoogleApiClient googleApiClient) {
        super(googleApiClient);
        this.zzet = zzbiVar;
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    protected final /* synthetic */ void doExecute(Api.AnyClient anyClient) {
        ((zzeo) ((zzaw) anyClient).getService()).zza(new zzgd(this.zzet.getDriveId(), DriveFile.MODE_WRITE_ONLY, this.zzet.zzeq.getRequestId()), new zzgf(this, null));
    }
}
