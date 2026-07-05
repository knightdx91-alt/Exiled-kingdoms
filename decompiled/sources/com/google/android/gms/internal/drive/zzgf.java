package com.google.android.gms.internal.drive;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.drive.DriveApi;
import com.google.android.gms.drive.DriveFile;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzgf extends zzl {
    private final BaseImplementation.ResultHolder<DriveApi.DriveContentsResult> zzdv;
    private final DriveFile.DownloadProgressListener zzia;

    zzgf(BaseImplementation.ResultHolder<DriveApi.DriveContentsResult> resultHolder, DriveFile.DownloadProgressListener downloadProgressListener) {
        this.zzdv = resultHolder;
        this.zzia = downloadProgressListener;
    }

    @Override // com.google.android.gms.internal.drive.zzl, com.google.android.gms.internal.drive.zzeq
    public final void zza(Status status) {
        this.zzdv.setResult(new zzal(status, null));
    }

    @Override // com.google.android.gms.internal.drive.zzl, com.google.android.gms.internal.drive.zzeq
    public final void zza(zzfb zzfbVar) {
        this.zzdv.setResult(new zzal(zzfbVar.zzhf ? new Status(-1) : Status.RESULT_SUCCESS, new zzbi(zzfbVar.zzeq)));
    }

    @Override // com.google.android.gms.internal.drive.zzl, com.google.android.gms.internal.drive.zzeq
    public final void zza(zzff zzffVar) {
        DriveFile.DownloadProgressListener downloadProgressListener = this.zzia;
        if (downloadProgressListener != null) {
            downloadProgressListener.onProgress(zzffVar.zzhi, zzffVar.zzhj);
        }
    }
}
