package com.google.android.gms.games;

import com.google.android.gms.tasks.TaskCompletionSource;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzcx extends com.google.android.gms.internal.games.zzah<Boolean> {
    zzcx(VideosClient videosClient) {
    }

    @Override // com.google.android.gms.internal.games.zzah
    protected final void zza(com.google.android.gms.games.internal.zze zzeVar, TaskCompletionSource<Boolean> taskCompletionSource) {
        taskCompletionSource.setResult(Boolean.valueOf(zzeVar.zzaz()));
    }
}
