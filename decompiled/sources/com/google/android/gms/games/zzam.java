package com.google.android.gms.games;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.PendingResultUtil;
import com.google.android.gms.games.leaderboard.Leaderboard;
import com.google.android.gms.games.leaderboard.LeaderboardBuffer;
import com.google.android.gms.games.leaderboard.Leaderboards;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzam implements PendingResultUtil.ResultConverter<Leaderboards.LeaderboardMetadataResult, Leaderboard> {
    zzam() {
    }

    private static Leaderboard zza(Leaderboards.LeaderboardMetadataResult leaderboardMetadataResult) {
        if (leaderboardMetadataResult == null) {
            return null;
        }
        LeaderboardBuffer leaderboards = leaderboardMetadataResult.getLeaderboards();
        if (leaderboards != null) {
            try {
                if (leaderboards.getCount() > 0) {
                    return leaderboards.get(0).freeze();
                }
            } finally {
                leaderboards.release();
            }
        }
        if (leaderboards != null) {
        }
        return null;
    }

    @Override // com.google.android.gms.common.internal.PendingResultUtil.ResultConverter
    public final /* synthetic */ Leaderboard convert(Result result) {
        return zza((Leaderboards.LeaderboardMetadataResult) result);
    }
}
