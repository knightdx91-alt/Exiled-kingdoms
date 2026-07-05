package com.google.android.gms.internal.games;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.leaderboard.Leaderboards;
import com.google.android.gms.games.leaderboard.ScoreSubmissionData;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzbb implements Leaderboards.SubmitScoreResult {
    private final /* synthetic */ Status zzbc;

    zzbb(zzba zzbaVar, Status status) {
        this.zzbc = status;
    }

    @Override // com.google.android.gms.games.leaderboard.Leaderboards.SubmitScoreResult
    public final ScoreSubmissionData getScoreData() {
        return new ScoreSubmissionData(DataHolder.empty(14));
    }

    @Override // com.google.android.gms.common.api.Result
    public final Status getStatus() {
        return this.zzbc;
    }

    @Override // com.google.android.gms.common.api.Releasable
    public final void release() {
    }
}
