package com.google.android.gms.internal.drive;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.drive.DriveApi;
import com.google.android.gms.drive.MetadataBuffer;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzdx extends zzl {
    private final BaseImplementation.ResultHolder<DriveApi.MetadataBufferResult> zzdv;

    public zzdx(BaseImplementation.ResultHolder<DriveApi.MetadataBufferResult> resultHolder) {
        this.zzdv = resultHolder;
    }

    @Override // com.google.android.gms.internal.drive.zzl, com.google.android.gms.internal.drive.zzeq
    public final void zza(Status status) {
        this.zzdv.setResult(new zzaq(status, null, false));
    }

    @Override // com.google.android.gms.internal.drive.zzl, com.google.android.gms.internal.drive.zzeq
    public final void zza(zzfp zzfpVar) {
        this.zzdv.setResult(new zzaq(Status.RESULT_SUCCESS, new MetadataBuffer(zzfpVar.zzht), false));
    }
}
