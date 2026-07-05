package com.google.android.gms.games;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.PendingResultUtil;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatch;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzcm implements PendingResultUtil.ResultConverter<TurnBasedMultiplayer.UpdateMatchResult, TurnBasedMatch> {
    zzcm() {
    }

    @Override // com.google.android.gms.common.internal.PendingResultUtil.ResultConverter
    public final /* synthetic */ TurnBasedMatch convert(Result result) {
        TurnBasedMatch match;
        TurnBasedMultiplayer.UpdateMatchResult updateMatchResult = (TurnBasedMultiplayer.UpdateMatchResult) result;
        if (updateMatchResult == null || (match = updateMatchResult.getMatch()) == null) {
            return null;
        }
        return match.freeze();
    }
}
