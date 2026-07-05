package com.google.android.gms.internal.drive;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.drive.DriveApi;
import com.google.android.gms.drive.MetadataBuffer;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class zzaq implements DriveApi.MetadataBufferResult {
    private final Status zzdw;
    private final MetadataBuffer zzdx;
    private final boolean zzdy;

    public zzaq(Status status, MetadataBuffer metadataBuffer, boolean z2) {
        this.zzdw = status;
        this.zzdx = metadataBuffer;
        this.zzdy = z2;
    }

    @Override // com.google.android.gms.drive.DriveApi.MetadataBufferResult
    public final MetadataBuffer getMetadataBuffer() {
        return this.zzdx;
    }

    @Override // com.google.android.gms.common.api.Result
    public final Status getStatus() {
        return this.zzdw;
    }

    @Override // com.google.android.gms.common.api.Releasable
    public final void release() {
        MetadataBuffer metadataBuffer = this.zzdx;
        if (metadataBuffer != null) {
            metadataBuffer.release();
        }
    }
}
