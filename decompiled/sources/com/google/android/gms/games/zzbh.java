package com.google.android.gms.games;

import com.google.android.gms.common.api.internal.TaskUtil;
import com.google.android.gms.tasks.TaskCompletionSource;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzbh extends com.google.android.gms.games.internal.zza {
    private final /* synthetic */ TaskCompletionSource zzdk;

    zzbh(zzbg zzbgVar, TaskCompletionSource taskCompletionSource) {
        this.zzdk = taskCompletionSource;
    }

    @Override // com.google.android.gms.games.internal.zza, com.google.android.gms.games.internal.zzu
    public final void onLeftRoom(int i2, String str) {
        TaskUtil.setResultOrApiException(GamesClientStatusCodes.zza(GamesClientStatusCodes.zzb(i2)), str, this.zzdk);
    }
}
