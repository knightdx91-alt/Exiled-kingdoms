package com.google.android.gms.games;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.PendingResultUtil;
import com.google.android.gms.games.leaderboard.LeaderboardBuffer;
import com.google.android.gms.games.leaderboard.Leaderboards;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzal implements PendingResultUtil.ResultConverter<Leaderboards.LeaderboardMetadataResult, LeaderboardBuffer> {
    zzal() {
    }

    @Override // com.google.android.gms.common.internal.PendingResultUtil.ResultConverter
    public final /* synthetic */ LeaderboardBuffer convert(Result result) {
        Leaderboards.LeaderboardMetadataResult leaderboardMetadataResult = (Leaderboards.LeaderboardMetadataResult) result;
        if (leaderboardMetadataResult == null) {
            return null;
        }
        return leaderboardMetadataResult.getLeaderboards();
    }
}
