package com.google.android.gms.games;

import com.google.android.gms.common.internal.ApiExceptionUtil;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.List;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzbc extends com.google.android.gms.internal.games.zzah<Void> {
    private final /* synthetic */ byte[] zzdf;
    private final /* synthetic */ String zzdg;
    private final /* synthetic */ List zzdi;

    zzbc(RealTimeMultiplayerClient realTimeMultiplayerClient, List list, byte[] bArr, String str) {
        this.zzdi = list;
        this.zzdf = bArr;
        this.zzdg = str;
    }

    @Override // com.google.android.gms.internal.games.zzah
    protected final void zza(com.google.android.gms.games.internal.zze zzeVar, TaskCompletionSource<Void> taskCompletionSource) {
        Preconditions.checkNotNull(this.zzdi, "Participant IDs must not be null");
        List list = this.zzdi;
        if (((com.google.android.gms.games.internal.zzy) zzeVar.getService()).zzb(this.zzdf, this.zzdg, (String[]) list.toArray(new String[list.size()])) == 0) {
            taskCompletionSource.setResult(null);
        } else {
            taskCompletionSource.trySetException(ApiExceptionUtil.fromStatus(GamesClientStatusCodes.zza(GamesClientStatusCodes.REAL_TIME_MESSAGE_SEND_FAILED)));
        }
    }
}
