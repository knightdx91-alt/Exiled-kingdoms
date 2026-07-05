package com.google.android.gms.games;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.PendingResultUtil;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatch;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzck implements PendingResultUtil.ResultConverter<TurnBasedMultiplayer.LeaveMatchResult, TurnBasedMatch> {
    zzck() {
    }

    @Override // com.google.android.gms.common.internal.PendingResultUtil.ResultConverter
    public final /* synthetic */ TurnBasedMatch convert(Result result) {
        TurnBasedMatch match;
        TurnBasedMultiplayer.LeaveMatchResult leaveMatchResult = (TurnBasedMultiplayer.LeaveMatchResult) result;
        if (leaveMatchResult == null || (match = leaveMatchResult.getMatch()) == null) {
            return null;
        }
        return match.freeze();
    }
}
