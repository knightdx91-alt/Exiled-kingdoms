package com.google.android.gms.games;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.PendingResultUtil;
import com.google.android.gms.games.LeaderboardsClient;
import com.google.android.gms.games.leaderboard.Leaderboards;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzaf implements PendingResultUtil.ResultConverter<Leaderboards.LoadScoresResult, LeaderboardsClient.LeaderboardScores> {
    zzaf() {
    }

    @Override // com.google.android.gms.common.internal.PendingResultUtil.ResultConverter
    public final /* synthetic */ LeaderboardsClient.LeaderboardScores convert(Result result) {
        Leaderboards.LoadScoresResult loadScoresResult = (Leaderboards.LoadScoresResult) result;
        if (loadScoresResult != null) {
            return new LeaderboardsClient.LeaderboardScores(loadScoresResult.getLeaderboard() != null ? loadScoresResult.getLeaderboard().freeze() : null, loadScoresResult.getScores());
        }
        return null;
    }
}
