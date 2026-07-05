package com.google.android.gms.internal.games;

import android.os.Bundle;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.multiplayer.turnbased.LoadMatchesResponse;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzdv implements TurnBasedMultiplayer.LoadMatchesResult {
    private final /* synthetic */ Status zzbc;

    zzdv(zzdu zzduVar, Status status) {
        this.zzbc = status;
    }

    @Override // com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer.LoadMatchesResult
    public final LoadMatchesResponse getMatches() {
        return new LoadMatchesResponse(new Bundle());
    }

    @Override // com.google.android.gms.common.api.Result
    public final Status getStatus() {
        return this.zzbc;
    }

    @Override // com.google.android.gms.common.api.Releasable
    public final void release() {
    }
}
