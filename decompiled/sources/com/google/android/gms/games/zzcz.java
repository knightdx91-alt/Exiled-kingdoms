package com.google.android.gms.games;

import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.games.video.Videos;
import com.google.android.gms.tasks.TaskCompletionSource;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzcz extends com.google.android.gms.games.internal.zzt<Videos.CaptureOverlayStateListener> {
    zzcz(VideosClient videosClient, ListenerHolder.ListenerKey listenerKey) {
        super(listenerKey);
    }

    @Override // com.google.android.gms.games.internal.zzt
    protected final void zzc(com.google.android.gms.games.internal.zze zzeVar, TaskCompletionSource<Boolean> taskCompletionSource) {
        zzeVar.zzbb();
        taskCompletionSource.setResult(Boolean.TRUE);
    }
}
