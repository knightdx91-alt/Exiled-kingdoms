package com.google.android.gms.internal.games;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.leaderboard.Leaderboard;
import com.google.android.gms.games.leaderboard.LeaderboardScoreBuffer;
import com.google.android.gms.games.leaderboard.Leaderboards;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzaz implements Leaderboards.LoadScoresResult {
    private final /* synthetic */ Status zzbc;

    zzaz(zzay zzayVar, Status status) {
        this.zzbc = status;
    }

    @Override // com.google.android.gms.games.leaderboard.Leaderboards.LoadScoresResult
    public final Leaderboard getLeaderboard() {
        return null;
    }

    @Override // com.google.android.gms.games.leaderboard.Leaderboards.LoadScoresResult
    public final LeaderboardScoreBuffer getScores() {
        return new LeaderboardScoreBuffer(DataHolder.empty(14));
    }

    @Override // com.google.android.gms.common.api.Result
    public final Status getStatus() {
        return this.zzbc;
    }

    @Override // com.google.android.gms.common.api.Releasable
    public final void release() {
    }
}
