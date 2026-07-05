package com.google.android.gms.games;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.PendingResultUtil;
import com.google.android.gms.games.Players;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzax implements PendingResultUtil.ResultConverter<Players.LoadPlayersResult, PlayerBuffer> {
    zzax() {
    }

    @Override // com.google.android.gms.common.internal.PendingResultUtil.ResultConverter
    public final /* synthetic */ PlayerBuffer convert(Result result) {
        Players.LoadPlayersResult loadPlayersResult = (Players.LoadPlayersResult) result;
        if (loadPlayersResult == null) {
            return null;
        }
        return loadPlayersResult.getPlayers();
    }
}
