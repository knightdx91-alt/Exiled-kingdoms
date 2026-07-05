package com.google.android.gms.internal.drive;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.UnregisterListenerMethod;
import com.google.android.gms.drive.DriveResource;
import com.google.android.gms.tasks.TaskCompletionSource;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzcq extends UnregisterListenerMethod<zzaw, zzdi> {
    private final /* synthetic */ DriveResource zzfo;
    private final /* synthetic */ zzdi zzfp;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzcq(zzch zzchVar, ListenerHolder.ListenerKey listenerKey, DriveResource driveResource, zzdi zzdiVar) {
        super(listenerKey);
        this.zzfo = driveResource;
        this.zzfp = zzdiVar;
    }

    @Override // com.google.android.gms.common.api.internal.UnregisterListenerMethod
    protected final /* synthetic */ void unregisterListener(Api.AnyClient anyClient, TaskCompletionSource taskCompletionSource) {
        ((zzeo) ((zzaw) anyClient).getService()).zza(new zzgm(this.zzfo.getDriveId(), 1), this.zzfp.zzgf, (String) null, new zzhk(taskCompletionSource));
    }
}
