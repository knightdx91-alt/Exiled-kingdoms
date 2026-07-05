package com.google.android.gms.internal.drive;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.drive.DriveResource;
import com.google.android.gms.drive.Metadata;
import com.google.android.gms.tasks.TaskCompletionSource;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzdc extends TaskApiCall<zzaw, Metadata> {
    private final /* synthetic */ DriveResource zzfo;
    private final /* synthetic */ boolean zzfy = false;

    zzdc(zzch zzchVar, DriveResource driveResource, boolean z2) {
        this.zzfo = driveResource;
    }

    @Override // com.google.android.gms.common.api.internal.TaskApiCall
    protected final /* synthetic */ void doExecute(Api.AnyClient anyClient, TaskCompletionSource<Metadata> taskCompletionSource) {
        ((zzeo) ((zzaw) anyClient).getService()).zza(new zzek(this.zzfo.getDriveId(), this.zzfy), new zzhj(taskCompletionSource));
    }
}
