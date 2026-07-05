package com.google.android.gms.internal.drive;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzbl implements ResultCallback<Status> {
    zzbl(zzbi zzbiVar) {
    }

    @Override // com.google.android.gms.common.api.ResultCallback
    public final /* synthetic */ void onResult(Result result) {
        Status status = (Status) result;
        if (status.isSuccess()) {
            return;
        }
        zzbi.zzbx.efmt("DriveContentsImpl", "Error discarding contents, status: %s", status);
    }
}
