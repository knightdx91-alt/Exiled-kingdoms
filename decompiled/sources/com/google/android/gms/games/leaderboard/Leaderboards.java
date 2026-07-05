package com.google.android.gms.games.leaderboard;

import android.content.Intent;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.Result;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@Deprecated
public interface Leaderboards {

    @Deprecated
    public interface LeaderboardMetadataResult extends Releasable, Result {
        LeaderboardBuffer getLeaderboards();
    }

    @Deprecated
    public interface LoadPlayerScoreResult extends Result {
        LeaderboardScore getScore();
    }

    @Deprecated
    public interface LoadScoresResult extends Releasable, Result {
        Leaderboard getLeaderboard();

        LeaderboardScoreBuffer getScores();
    }

    @Deprecated
    public interface SubmitScoreResult extends Releasable, Result {
        ScoreSubmissionData getScoreData();
    }

    Intent getAllLeaderboardsIntent(GoogleApiClient googleApiClient);

    Intent getLeaderboardIntent(GoogleApiClient googleApiClient, String str);

    Intent getLeaderboardIntent(GoogleApiClient googleApiClient, String str, int i2);

    Intent getLeaderboardIntent(GoogleApiClient googleApiClient, String str, int i2, int i3);

    PendingResult<LoadPlayerScoreResult> loadCurrentPlayerLeaderboardScore(GoogleApiClient googleApiClient, String str, int i2, int i3);

    PendingResult<LeaderboardMetadataResult> loadLeaderboardMetadata(GoogleApiClient googleApiClient, String str, boolean z2);

    PendingResult<LeaderboardMetadataResult> loadLeaderboardMetadata(GoogleApiClient googleApiClient, boolean z2);

    PendingResult<LoadScoresResult> loadMoreScores(GoogleApiClient googleApiClient, LeaderboardScoreBuffer leaderboardScoreBuffer, int i2, int i3);

    PendingResult<LoadScoresResult> loadPlayerCenteredScores(GoogleApiClient googleApiClient, String str, int i2, int i3, int i4);

    PendingResult<LoadScoresResult> loadPlayerCenteredScores(GoogleApiClient googleApiClient, String str, int i2, int i3, int i4, boolean z2);

    PendingResult<LoadScoresResult> loadTopScores(GoogleApiClient googleApiClient, String str, int i2, int i3, int i4);

    PendingResult<LoadScoresResult> loadTopScores(GoogleApiClient googleApiClient, String str, int i2, int i3, int i4, boolean z2);

    void submitScore(GoogleApiClient googleApiClient, String str, long j2);

    void submitScore(GoogleApiClient googleApiClient, String str, long j2, String str2);

    PendingResult<SubmitScoreResult> submitScoreImmediate(GoogleApiClient googleApiClient, String str, long j2);

    PendingResult<SubmitScoreResult> submitScoreImmediate(GoogleApiClient googleApiClient, String str, long j2, String str2);
}
