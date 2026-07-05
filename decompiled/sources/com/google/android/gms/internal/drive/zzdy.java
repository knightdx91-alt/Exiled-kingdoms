package com.google.android.gms.internal.drive;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.drive.DriveResource;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzdy extends zzl {
    private final BaseImplementation.ResultHolder<DriveResource.MetadataResult> zzdv;

    public zzdy(BaseImplementation.ResultHolder<DriveResource.MetadataResult> resultHolder) {
        this.zzdv = resultHolder;
    }

    @Override // com.google.android.gms.internal.drive.zzl, com.google.android.gms.internal.drive.zzeq
    public final void zza(Status status) {
        this.zzdv.setResult(new zzdz(status, null));
    }

    @Override // com.google.android.gms.internal.drive.zzl, com.google.android.gms.internal.drive.zzeq
    public final void zza(zzfs zzfsVar) {
        this.zzdv.setResult(new zzdz(Status.RESULT_SUCCESS, new zzaa(zzfsVar.zzdl)));
    }
}
