package com.google.android.gms.games;

import com.google.android.gms.common.internal.ApiExceptionUtil;
import com.google.android.gms.tasks.TaskCompletionSource;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzbb extends com.google.android.gms.internal.games.zzah<Void> {
    private final /* synthetic */ byte[] zzdf;
    private final /* synthetic */ String zzdg;
    private final /* synthetic */ String zzdh;

    zzbb(RealTimeMultiplayerClient realTimeMultiplayerClient, byte[] bArr, String str, String str2) {
        this.zzdf = bArr;
        this.zzdg = str;
        this.zzdh = str2;
    }

    @Override // com.google.android.gms.internal.games.zzah
    protected final void zza(com.google.android.gms.games.internal.zze zzeVar, TaskCompletionSource<Void> taskCompletionSource) {
        if (((com.google.android.gms.games.internal.zzy) zzeVar.getService()).zzb(this.zzdf, this.zzdg, new String[]{this.zzdh}) == 0) {
            taskCompletionSource.setResult(null);
        } else {
            taskCompletionSource.trySetException(ApiExceptionUtil.fromStatus(GamesClientStatusCodes.zza(GamesClientStatusCodes.REAL_TIME_MESSAGE_SEND_FAILED)));
        }
    }
}
