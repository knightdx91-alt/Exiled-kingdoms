package com.google.android.gms.internal.drive;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.ICancelToken;
import com.google.android.gms.drive.DriveFile;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzbo extends zzam {
    private final /* synthetic */ int zzdt;
    private final /* synthetic */ DriveFile.DownloadProgressListener zzew;
    private final /* synthetic */ zzbn zzex;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzbo(zzbn zzbnVar, GoogleApiClient googleApiClient, int i2, DriveFile.DownloadProgressListener downloadProgressListener) {
        super(googleApiClient);
        this.zzex = zzbnVar;
        this.zzdt = i2;
        this.zzew = downloadProgressListener;
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    protected final /* synthetic */ void doExecute(Api.AnyClient anyClient) {
        setCancelToken(ICancelToken.Stub.asInterface(((zzeo) ((zzaw) anyClient).getService()).zza(new zzgd(this.zzex.getDriveId(), this.zzdt, 0), new zzgf(this, this.zzew)).zzgq));
    }
}
