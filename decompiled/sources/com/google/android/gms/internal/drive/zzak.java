package com.google.android.gms.internal.drive;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.drive.DriveApi;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzak extends zzl {
    private final BaseImplementation.ResultHolder<DriveApi.DriveContentsResult> zzdv;

    zzak(BaseImplementation.ResultHolder<DriveApi.DriveContentsResult> resultHolder) {
        this.zzdv = resultHolder;
    }

    @Override // com.google.android.gms.internal.drive.zzl, com.google.android.gms.internal.drive.zzeq
    public final void zza(Status status) {
        this.zzdv.setResult(new zzal(status, null));
    }

    @Override // com.google.android.gms.internal.drive.zzl, com.google.android.gms.internal.drive.zzeq
    public final void zza(zzfb zzfbVar) {
        this.zzdv.setResult(new zzal(Status.RESULT_SUCCESS, new zzbi(zzfbVar.zzeq)));
    }
}
