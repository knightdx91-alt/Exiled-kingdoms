package com.google.android.gms.internal.drive;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.drive.DriveFolder;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzbw extends zzl {
    private final BaseImplementation.ResultHolder<DriveFolder.DriveFolderResult> zzdv;

    public zzbw(BaseImplementation.ResultHolder<DriveFolder.DriveFolderResult> resultHolder) {
        this.zzdv = resultHolder;
    }

    @Override // com.google.android.gms.internal.drive.zzl, com.google.android.gms.internal.drive.zzeq
    public final void zza(Status status) {
        this.zzdv.setResult(new zzbz(status, null));
    }

    @Override // com.google.android.gms.internal.drive.zzl, com.google.android.gms.internal.drive.zzeq
    public final void zza(zzfh zzfhVar) {
        this.zzdv.setResult(new zzbz(Status.RESULT_SUCCESS, new zzbs(zzfhVar.zzdb)));
    }
}
