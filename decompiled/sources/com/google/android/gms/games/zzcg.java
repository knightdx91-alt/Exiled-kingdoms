package com.google.android.gms.games;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.PendingResultUtil;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatch;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzcg implements PendingResultUtil.ResultConverter<TurnBasedMultiplayer.LoadMatchResult, TurnBasedMatch> {
    zzcg() {
    }

    @Override // com.google.android.gms.common.internal.PendingResultUtil.ResultConverter
    public final /* synthetic */ TurnBasedMatch convert(Result result) {
        TurnBasedMatch match;
        TurnBasedMultiplayer.LoadMatchResult loadMatchResult = (TurnBasedMultiplayer.LoadMatchResult) result;
        if (loadMatchResult == null || (match = loadMatchResult.getMatch()) == null) {
            return null;
        }
        return match.freeze();
    }
}
