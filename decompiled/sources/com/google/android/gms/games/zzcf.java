package com.google.android.gms.games;

import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzcf implements com.google.android.gms.games.internal.zzq<TurnBasedMultiplayer.LoadMatchesResult> {
    zzcf() {
    }

    @Override // com.google.android.gms.games.internal.zzq
    public final /* synthetic */ void release(TurnBasedMultiplayer.LoadMatchesResult loadMatchesResult) {
        TurnBasedMultiplayer.LoadMatchesResult loadMatchesResult2 = loadMatchesResult;
        if (loadMatchesResult2.getMatches() != null) {
            loadMatchesResult2.getMatches().release();
        }
    }
}
