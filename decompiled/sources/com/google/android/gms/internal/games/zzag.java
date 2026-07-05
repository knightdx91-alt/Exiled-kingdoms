package com.google.android.gms.internal.games;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.GameBuffer;
import com.google.android.gms.games.GamesMetadata;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzag implements GamesMetadata.LoadGamesResult {
    private final /* synthetic */ Status zzbc;

    zzag(zzaf zzafVar, Status status) {
        this.zzbc = status;
    }

    @Override // com.google.android.gms.games.GamesMetadata.LoadGamesResult
    public final GameBuffer getGames() {
        return new GameBuffer(DataHolder.empty(14));
    }

    @Override // com.google.android.gms.common.api.Result
    public final Status getStatus() {
        return this.zzbc;
    }

    @Override // com.google.android.gms.common.api.Releasable
    public final void release() {
    }
}
