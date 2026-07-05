package com.google.android.gms.internal.drive;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.tasks.TaskCompletionSource;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzbc extends TaskApiCall<zzaw, DriveId> {
    private final /* synthetic */ String zzdu;

    zzbc(zzbb zzbbVar, String str) {
        this.zzdu = str;
    }

    @Override // com.google.android.gms.common.api.internal.TaskApiCall
    protected final /* synthetic */ void doExecute(Api.AnyClient anyClient, TaskCompletionSource<DriveId> taskCompletionSource) {
        ((zzeo) ((zzaw) anyClient).getService()).zza(new zzek(DriveId.zza(this.zzdu), false), new zzhf(taskCompletionSource));
    }
}
