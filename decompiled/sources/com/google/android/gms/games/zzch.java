package com.google.android.gms.games;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.PendingResultUtil;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzch implements PendingResultUtil.ResultConverter<TurnBasedMultiplayer.CancelMatchResult, String> {
    zzch() {
    }

    @Override // com.google.android.gms.common.internal.PendingResultUtil.ResultConverter
    public final /* synthetic */ String convert(Result result) {
        TurnBasedMultiplayer.CancelMatchResult cancelMatchResult = (TurnBasedMultiplayer.CancelMatchResult) result;
        if (cancelMatchResult == null) {
            return null;
        }
        return cancelMatchResult.getMatchId();
    }
}
