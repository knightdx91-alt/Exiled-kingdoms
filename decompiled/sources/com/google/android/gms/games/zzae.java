package com.google.android.gms.games;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.PendingResultUtil;
import com.google.android.gms.games.leaderboard.Leaderboards;
import com.google.android.gms.games.leaderboard.ScoreSubmissionData;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzae implements PendingResultUtil.ResultConverter<Leaderboards.SubmitScoreResult, ScoreSubmissionData> {
    zzae() {
    }

    @Override // com.google.android.gms.common.internal.PendingResultUtil.ResultConverter
    public final /* synthetic */ ScoreSubmissionData convert(Result result) {
        Leaderboards.SubmitScoreResult submitScoreResult = (Leaderboards.SubmitScoreResult) result;
        if (submitScoreResult == null) {
            return null;
        }
        return submitScoreResult.getScoreData();
    }
}
