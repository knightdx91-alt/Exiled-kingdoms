package com.google.android.gms.games;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.PendingResultUtil;
import com.google.android.gms.games.multiplayer.turnbased.LoadMatchesResponse;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzce implements PendingResultUtil.ResultConverter<TurnBasedMultiplayer.LoadMatchesResult, LoadMatchesResponse> {
    zzce() {
    }

    @Override // com.google.android.gms.common.internal.PendingResultUtil.ResultConverter
    public final /* synthetic */ LoadMatchesResponse convert(Result result) {
        TurnBasedMultiplayer.LoadMatchesResult loadMatchesResult = (TurnBasedMultiplayer.LoadMatchesResult) result;
        if (loadMatchesResult == null) {
            return null;
        }
        return loadMatchesResult.getMatches();
    }
}
