package com.google.android.gms.internal.drive;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.RegisterListenerMethod;
import com.google.android.gms.drive.DriveResource;
import com.google.android.gms.tasks.TaskCompletionSource;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzcp extends RegisterListenerMethod<zzaw, zzdi> {
    private final /* synthetic */ DriveResource zzfo;
    private final /* synthetic */ zzdi zzfp;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzcp(zzch zzchVar, ListenerHolder listenerHolder, DriveResource driveResource, zzdi zzdiVar) {
        super(listenerHolder);
        this.zzfo = driveResource;
        this.zzfp = zzdiVar;
    }

    @Override // com.google.android.gms.common.api.internal.RegisterListenerMethod
    protected final /* synthetic */ void registerListener(Api.AnyClient anyClient, TaskCompletionSource taskCompletionSource) {
        ((zzeo) ((zzaw) anyClient).getService()).zza(new zzj(1, this.zzfo.getDriveId()), this.zzfp.zzgf, (String) null, new zzhl(taskCompletionSource));
    }
}
