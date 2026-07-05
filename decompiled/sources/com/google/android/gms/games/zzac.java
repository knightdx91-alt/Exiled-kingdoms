package com.google.android.gms.games;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.PendingResultUtil;
import com.google.android.gms.games.leaderboard.LeaderboardScore;
import com.google.android.gms.games.leaderboard.Leaderboards;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzac implements PendingResultUtil.ResultConverter<Leaderboards.LoadPlayerScoreResult, LeaderboardScore> {
    zzac() {
    }

    @Override // com.google.android.gms.common.internal.PendingResultUtil.ResultConverter
    public final /* synthetic */ LeaderboardScore convert(Result result) {
        LeaderboardScore score;
        Leaderboards.LoadPlayerScoreResult loadPlayerScoreResult = (Leaderboards.LoadPlayerScoreResult) result;
        if (loadPlayerScoreResult == null || (score = loadPlayerScoreResult.getScore()) == null) {
            return null;
        }
        return score.freeze();
    }
}
