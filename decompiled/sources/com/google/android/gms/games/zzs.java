package com.google.android.gms.games;

import android.os.Bundle;
import com.google.android.gms.tasks.TaskCompletionSource;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzs extends com.google.android.gms.internal.games.zzah<Bundle> {
    zzs(GamesClient gamesClient) {
    }

    @Override // com.google.android.gms.internal.games.zzah
    protected final void zza(com.google.android.gms.games.internal.zze zzeVar, TaskCompletionSource<Bundle> taskCompletionSource) {
        taskCompletionSource.setResult(zzeVar.zzo());
    }
}
