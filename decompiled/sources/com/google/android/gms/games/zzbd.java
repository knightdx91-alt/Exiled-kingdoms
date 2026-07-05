package com.google.android.gms.games;

import com.google.android.gms.common.internal.ApiExceptionUtil;
import com.google.android.gms.tasks.TaskCompletionSource;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzbd extends com.google.android.gms.internal.games.zzah<Void> {
    private final /* synthetic */ byte[] zzdf;
    private final /* synthetic */ String zzdg;

    zzbd(RealTimeMultiplayerClient realTimeMultiplayerClient, byte[] bArr, String str) {
        this.zzdf = bArr;
        this.zzdg = str;
    }

    @Override // com.google.android.gms.internal.games.zzah
    protected final void zza(com.google.android.gms.games.internal.zze zzeVar, TaskCompletionSource<Void> taskCompletionSource) {
        if (zzeVar.zza(this.zzdf, this.zzdg) == 0) {
            taskCompletionSource.setResult(null);
        } else {
            taskCompletionSource.trySetException(ApiExceptionUtil.fromStatus(GamesClientStatusCodes.zza(GamesClientStatusCodes.REAL_TIME_MESSAGE_SEND_FAILED)));
        }
    }
}
