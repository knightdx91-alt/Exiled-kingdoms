package com.google.android.gms.internal.drive;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.drive.DriveApi;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzan extends zzl {
    private final BaseImplementation.ResultHolder<DriveApi.DriveIdResult> zzdv;

    public zzan(BaseImplementation.ResultHolder<DriveApi.DriveIdResult> resultHolder) {
        this.zzdv = resultHolder;
    }

    @Override // com.google.android.gms.internal.drive.zzl, com.google.android.gms.internal.drive.zzeq
    public final void zza(Status status) {
        this.zzdv.setResult(new zzao(status, null));
    }

    @Override // com.google.android.gms.internal.drive.zzl, com.google.android.gms.internal.drive.zzeq
    public final void zza(zzfh zzfhVar) {
        this.zzdv.setResult(new zzao(Status.RESULT_SUCCESS, zzfhVar.zzdb));
    }

    @Override // com.google.android.gms.internal.drive.zzl, com.google.android.gms.internal.drive.zzeq
    public final void zza(zzfs zzfsVar) {
        this.zzdv.setResult(new zzao(Status.RESULT_SUCCESS, new zzaa(zzfsVar.zzdl).getDriveId()));
    }
}
