package com.google.android.gms.internal.drive;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.RegisterListenerMethod;
import com.google.android.gms.common.internal.ICancelToken;
import com.google.android.gms.drive.DriveFile;
import com.google.android.gms.drive.events.OpenFileCallback;
import com.google.android.gms.tasks.TaskCompletionSource;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzcu extends RegisterListenerMethod<zzaw, OpenFileCallback> {
    private final /* synthetic */ DriveFile zzfq;
    private final /* synthetic */ int zzfr;
    private final /* synthetic */ zzg zzfs;
    private final /* synthetic */ ListenerHolder zzft;
    private final /* synthetic */ zzch zzfu;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzcu(zzch zzchVar, ListenerHolder listenerHolder, DriveFile driveFile, int i2, zzg zzgVar, ListenerHolder listenerHolder2) {
        super(listenerHolder);
        this.zzfu = zzchVar;
        this.zzfq = driveFile;
        this.zzfr = i2;
        this.zzfs = zzgVar;
        this.zzft = listenerHolder2;
    }

    @Override // com.google.android.gms.common.api.internal.RegisterListenerMethod
    protected final /* synthetic */ void registerListener(Api.AnyClient anyClient, TaskCompletionSource taskCompletionSource) {
        this.zzfs.setCancelToken(ICancelToken.Stub.asInterface(((zzeo) ((zzaw) anyClient).getService()).zza(new zzgd(this.zzfq.getDriveId(), this.zzfr, 0), new zzdk(this.zzfu, this.zzfs, this.zzft)).zzgq));
        taskCompletionSource.setResult(null);
    }
}
