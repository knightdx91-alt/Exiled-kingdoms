package com.google.android.gms.games;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.internal.PendingResultUtil;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.leaderboard.Leaderboard;
import com.google.android.gms.games.leaderboard.LeaderboardBuffer;
import com.google.android.gms.games.leaderboard.LeaderboardScore;
import com.google.android.gms.games.leaderboard.LeaderboardScoreBuffer;
import com.google.android.gms.games.leaderboard.Leaderboards;
import com.google.android.gms.games.leaderboard.ScoreSubmissionData;
import com.google.android.gms.tasks.Task;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class LeaderboardsClient extends com.google.android.gms.internal.games.zzu {
    private static final PendingResultUtil.ResultConverter<Leaderboards.LeaderboardMetadataResult, LeaderboardBuffer> zzbj = new zzal();
    private static final PendingResultUtil.ResultConverter<Leaderboards.LeaderboardMetadataResult, Leaderboard> zzbk = new zzam();
    private static final com.google.android.gms.games.internal.zzq<Leaderboards.LeaderboardMetadataResult> zzbl = new zzan();
    private static final PendingResultUtil.ResultConverter<Leaderboards.LoadPlayerScoreResult, LeaderboardScore> zzbm = new zzac();
    private static final com.google.android.gms.games.internal.zzr zzbn = new zzad();
    private static final PendingResultUtil.ResultConverter<Leaderboards.SubmitScoreResult, ScoreSubmissionData> zzbo = new zzae();
    private static final PendingResultUtil.ResultConverter<Leaderboards.LoadScoresResult, LeaderboardScores> zzbp = new zzaf();

    public static class LeaderboardScores implements Releasable {
        private final Leaderboard zzbv;
        private final LeaderboardScoreBuffer zzbw;

        LeaderboardScores(Leaderboard leaderboard, LeaderboardScoreBuffer leaderboardScoreBuffer) {
            this.zzbv = leaderboard;
            this.zzbw = leaderboardScoreBuffer;
        }

        public Leaderboard getLeaderboard() {
            return this.zzbv;
        }

        public LeaderboardScoreBuffer getScores() {
            return this.zzbw;
        }

        @Override // com.google.android.gms.common.api.Releasable
        public void release() {
            LeaderboardScoreBuffer leaderboardScoreBuffer = this.zzbw;
            if (leaderboardScoreBuffer != null) {
                leaderboardScoreBuffer.release();
            }
        }
    }

    LeaderboardsClient(Activity activity, Games.GamesOptions gamesOptions) {
        super(activity, gamesOptions);
    }

    public Task<Intent> getAllLeaderboardsIntent() {
        return doRead(new zzab(this));
    }

    public Task<Intent> getLeaderboardIntent(String str) {
        return doRead(new zzag(this, str));
    }

    public Task<AnnotatedData<LeaderboardScore>> loadCurrentPlayerLeaderboardScore(String str, int i2, int i3) {
        return com.google.android.gms.games.internal.zzi.zza(Games.Leaderboards.loadCurrentPlayerLeaderboardScore(asGoogleApiClient(), str, i2, i3), zzbm);
    }

    public Task<AnnotatedData<Leaderboard>> loadLeaderboardMetadata(String str, boolean z2) {
        return com.google.android.gms.games.internal.zzi.zza(Games.Leaderboards.loadLeaderboardMetadata(asGoogleApiClient(), str, z2), zzbk, zzbl);
    }

    public Task<AnnotatedData<LeaderboardScores>> loadMoreScores(LeaderboardScoreBuffer leaderboardScoreBuffer, int i2, int i3) {
        return com.google.android.gms.games.internal.zzi.zzb(Games.Leaderboards.loadMoreScores(asGoogleApiClient(), leaderboardScoreBuffer, i2, i3), zzbp);
    }

    public Task<AnnotatedData<LeaderboardScores>> loadPlayerCenteredScores(String str, int i2, int i3, int i4) {
        return com.google.android.gms.games.internal.zzi.zzb(Games.Leaderboards.loadPlayerCenteredScores(asGoogleApiClient(), str, i2, i3, i4), zzbp);
    }

    public Task<AnnotatedData<LeaderboardScores>> loadTopScores(String str, int i2, int i3, int i4) {
        return com.google.android.gms.games.internal.zzi.zzb(Games.Leaderboards.loadTopScores(asGoogleApiClient(), str, i2, i3, i4), zzbp);
    }

    public void submitScore(String str, long j2) {
        doWrite(new zzaj(this, str, j2));
    }

    public Task<ScoreSubmissionData> submitScoreImmediate(String str, long j2) {
        return com.google.android.gms.games.internal.zzi.zza(Games.Leaderboards.submitScoreImmediate(asGoogleApiClient(), str, j2), zzbn, zzbo);
    }

    LeaderboardsClient(Context context, Games.GamesOptions gamesOptions) {
        super(context, gamesOptions);
    }

    public Task<Intent> getLeaderboardIntent(String str, int i2) {
        return doRead(new zzah(this, str, i2));
    }

    public Task<AnnotatedData<LeaderboardBuffer>> loadLeaderboardMetadata(boolean z2) {
        return com.google.android.gms.games.internal.zzi.zzb(Games.Leaderboards.loadLeaderboardMetadata(asGoogleApiClient(), z2), zzbj);
    }

    public Task<AnnotatedData<LeaderboardScores>> loadPlayerCenteredScores(String str, int i2, int i3, int i4, boolean z2) {
        return com.google.android.gms.games.internal.zzi.zzb(Games.Leaderboards.loadPlayerCenteredScores(asGoogleApiClient(), str, i2, i3, i4, z2), zzbp);
    }

    public Task<AnnotatedData<LeaderboardScores>> loadTopScores(String str, int i2, int i3, int i4, boolean z2) {
        return com.google.android.gms.games.internal.zzi.zzb(Games.Leaderboards.loadTopScores(asGoogleApiClient(), str, i2, i3, i4, z2), zzbp);
    }

    public void submitScore(String str, long j2, String str2) {
        doWrite(new zzak(this, str, j2, str2));
    }

    public Task<ScoreSubmissionData> submitScoreImmediate(String str, long j2, String str2) {
        return com.google.android.gms.games.internal.zzi.zza(Games.Leaderboards.submitScoreImmediate(asGoogleApiClient(), str, j2, str2), zzbn, zzbo);
    }

    public Task<Intent> getLeaderboardIntent(String str, int i2, int i3) {
        return doRead(new zzai(this, str, i2, i3));
    }
}
