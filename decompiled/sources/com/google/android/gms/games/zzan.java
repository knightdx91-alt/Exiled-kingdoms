package com.google.android.gms.games;

import com.google.android.gms.games.leaderboard.Leaderboards;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzan implements com.google.android.gms.games.internal.zzq<Leaderboards.LeaderboardMetadataResult> {
    zzan() {
    }

    @Override // com.google.android.gms.games.internal.zzq
    public final /* synthetic */ void release(Leaderboards.LeaderboardMetadataResult leaderboardMetadataResult) {
        Leaderboards.LeaderboardMetadataResult leaderboardMetadataResult2 = leaderboardMetadataResult;
        if (leaderboardMetadataResult2.getLeaderboards() != null) {
            leaderboardMetadataResult2.getLeaderboards().release();
        }
    }
}
