package com.google.android.gms.internal.drive;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.drive.DriveFolder;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzbv extends zzl {
    private final BaseImplementation.ResultHolder<DriveFolder.DriveFileResult> zzdv;

    public zzbv(BaseImplementation.ResultHolder<DriveFolder.DriveFileResult> resultHolder) {
        this.zzdv = resultHolder;
    }

    @Override // com.google.android.gms.internal.drive.zzl, com.google.android.gms.internal.drive.zzeq
    public final void zza(Status status) {
        this.zzdv.setResult(new zzbx(status, null));
    }

    @Override // com.google.android.gms.internal.drive.zzl, com.google.android.gms.internal.drive.zzeq
    public final void zza(zzfh zzfhVar) {
        this.zzdv.setResult(new zzbx(Status.RESULT_SUCCESS, new zzbn(zzfhVar.zzdb)));
    }
}
