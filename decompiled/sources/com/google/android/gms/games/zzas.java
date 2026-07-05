package com.google.android.gms.games;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.PendingResultUtil;
import com.google.android.gms.games.stats.PlayerStats;
import com.google.android.gms.games.stats.Stats;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzas implements PendingResultUtil.ResultConverter<Stats.LoadPlayerStatsResult, PlayerStats> {
    zzas() {
    }

    @Override // com.google.android.gms.common.internal.PendingResultUtil.ResultConverter
    public final /* synthetic */ PlayerStats convert(Result result) {
        PlayerStats playerStats;
        Stats.LoadPlayerStatsResult loadPlayerStatsResult = (Stats.LoadPlayerStatsResult) result;
        if (loadPlayerStatsResult == null || (playerStats = loadPlayerStatsResult.getPlayerStats()) == null) {
            return null;
        }
        return playerStats.freeze();
    }
}
