package com.google.android.gms.games;

import android.view.View;
import com.google.android.gms.tasks.TaskCompletionSource;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzo extends com.google.android.gms.internal.games.zzah<Void> {
    private final /* synthetic */ View zzbe;

    zzo(GamesClient gamesClient, View view) {
        this.zzbe = view;
    }

    @Override // com.google.android.gms.internal.games.zzah
    protected final void zza(com.google.android.gms.games.internal.zze zzeVar, TaskCompletionSource<Void> taskCompletionSource) {
        zzeVar.zza(this.zzbe);
        taskCompletionSource.setResult(null);
    }
}
