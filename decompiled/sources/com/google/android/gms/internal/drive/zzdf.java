package com.google.android.gms.internal.drive;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.drive.DriveResource;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.List;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzdf extends TaskApiCall<zzaw, Void> {
    private final /* synthetic */ DriveResource zzfo;
    private final /* synthetic */ List zzfz;

    zzdf(zzch zzchVar, DriveResource driveResource, List list) {
        this.zzfo = driveResource;
        this.zzfz = list;
    }

    @Override // com.google.android.gms.common.api.internal.TaskApiCall
    protected final /* synthetic */ void doExecute(Api.AnyClient anyClient, TaskCompletionSource<Void> taskCompletionSource) {
        ((zzeo) ((zzaw) anyClient).getService()).zza(new zzgq(this.zzfo.getDriveId(), this.zzfz), new zzhl(taskCompletionSource));
    }
}
