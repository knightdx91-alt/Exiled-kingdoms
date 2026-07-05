package com.google.android.gms.games;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.PendingResultUtil;
import com.google.android.gms.games.Players;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzaz implements PendingResultUtil.ResultConverter<Players.LoadPlayersResult, Player> {
    zzaz() {
    }

    private static Player zza(Players.LoadPlayersResult loadPlayersResult) {
        if (loadPlayersResult == null) {
            return null;
        }
        PlayerBuffer players = loadPlayersResult.getPlayers();
        if (players != null) {
            try {
                if (players.getCount() > 0) {
                    return ((Player) players.get(0)).freeze();
                }
            } finally {
                players.release();
            }
        }
        if (players != null) {
        }
        return null;
    }

    @Override // com.google.android.gms.common.internal.PendingResultUtil.ResultConverter
    public final /* synthetic */ Player convert(Result result) {
        return zza((Players.LoadPlayersResult) result);
    }
}
