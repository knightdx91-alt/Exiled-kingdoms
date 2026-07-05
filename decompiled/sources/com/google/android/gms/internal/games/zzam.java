package com.google.android.gms.internal.games;

import android.content.Intent;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.leaderboard.LeaderboardScoreBuffer;
import com.google.android.gms.games.leaderboard.Leaderboards;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class zzam implements Leaderboards {
    @Override // com.google.android.gms.games.leaderboard.Leaderboards
    public final Intent getAllLeaderboardsIntent(GoogleApiClient googleApiClient) {
        return Games.zza(googleApiClient).zzw();
    }

    @Override // com.google.android.gms.games.leaderboard.Leaderboards
    public final Intent getLeaderboardIntent(GoogleApiClient googleApiClient, String str) {
        return getLeaderboardIntent(googleApiClient, str, -1);
    }

    @Override // com.google.android.gms.games.leaderboard.Leaderboards
    public final PendingResult<Leaderboards.LoadPlayerScoreResult> loadCurrentPlayerLeaderboardScore(GoogleApiClient googleApiClient, String str, int i2, int i3) {
        return googleApiClient.enqueue(new zzap(this, googleApiClient, str, i2, i3));
    }

    @Override // com.google.android.gms.games.leaderboard.Leaderboards
    public final PendingResult<Leaderboards.LeaderboardMetadataResult> loadLeaderboardMetadata(GoogleApiClient googleApiClient, String str, boolean z2) {
        return googleApiClient.enqueue(new zzao(this, googleApiClient, str, z2));
    }

    @Override // com.google.android.gms.games.leaderboard.Leaderboards
    public final PendingResult<Leaderboards.LoadScoresResult> loadMoreScores(GoogleApiClient googleApiClient, LeaderboardScoreBuffer leaderboardScoreBuffer, int i2, int i3) {
        return googleApiClient.enqueue(new zzas(this, googleApiClient, leaderboardScoreBuffer, i2, i3));
    }

    @Override // com.google.android.gms.games.leaderboard.Leaderboards
    public final PendingResult<Leaderboards.LoadScoresResult> loadPlayerCenteredScores(GoogleApiClient googleApiClient, String str, int i2, int i3, int i4) {
        return loadPlayerCenteredScores(googleApiClient, str, i2, i3, i4, false);
    }

    @Override // com.google.android.gms.games.leaderboard.Leaderboards
    public final PendingResult<Leaderboards.LoadScoresResult> loadTopScores(GoogleApiClient googleApiClient, String str, int i2, int i3, int i4) {
        return loadTopScores(googleApiClient, str, i2, i3, i4, false);
    }

    @Override // com.google.android.gms.games.leaderboard.Leaderboards
    public final void submitScore(GoogleApiClient googleApiClient, String str, long j2) {
        submitScore(googleApiClient, str, j2, null);
    }

    @Override // com.google.android.gms.games.leaderboard.Leaderboards
    public final PendingResult<Leaderboards.SubmitScoreResult> submitScoreImmediate(GoogleApiClient googleApiClient, String str, long j2) {
        return submitScoreImmediate(googleApiClient, str, j2, null);
    }

    @Override // com.google.android.gms.games.leaderboard.Leaderboards
    public final Intent getLeaderboardIntent(GoogleApiClient googleApiClient, String str, int i2) {
        return getLeaderboardIntent(googleApiClient, str, i2, -1);
    }

    @Override // com.google.android.gms.games.leaderboard.Leaderboards
    public final PendingResult<Leaderboards.LeaderboardMetadataResult> loadLeaderboardMetadata(GoogleApiClient googleApiClient, boolean z2) {
        return googleApiClient.enqueue(new zzan(this, googleApiClient, z2));
    }

    @Override // com.google.android.gms.games.leaderboard.Leaderboards
    public final PendingResult<Leaderboards.LoadScoresResult> loadPlayerCenteredScores(GoogleApiClient googleApiClient, String str, int i2, int i3, int i4, boolean z2) {
        return googleApiClient.enqueue(new zzar(this, googleApiClient, str, i2, i3, i4, z2));
    }

    @Override // com.google.android.gms.games.leaderboard.Leaderboards
    public final PendingResult<Leaderboards.LoadScoresResult> loadTopScores(GoogleApiClient googleApiClient, String str, int i2, int i3, int i4, boolean z2) {
        return googleApiClient.enqueue(new zzaq(this, googleApiClient, str, i2, i3, i4, z2));
    }

    @Override // com.google.android.gms.games.leaderboard.Leaderboards
    public final void submitScore(GoogleApiClient googleApiClient, String str, long j2, String str2) {
        com.google.android.gms.games.internal.zze zzeVarZza = Games.zza(googleApiClient, false);
        if (zzeVarZza != null) {
            try {
                zzeVarZza.zza((BaseImplementation.ResultHolder<Leaderboards.SubmitScoreResult>) null, str, j2, str2);
            } catch (RemoteException unused) {
                com.google.android.gms.games.internal.zzh.w("LeaderboardsImpl", "service died");
            }
        }
    }

    @Override // com.google.android.gms.games.leaderboard.Leaderboards
    public final PendingResult<Leaderboards.SubmitScoreResult> submitScoreImmediate(GoogleApiClient googleApiClient, String str, long j2, String str2) {
        return googleApiClient.execute(new zzat(this, googleApiClient, str, j2, str2));
    }

    @Override // com.google.android.gms.games.leaderboard.Leaderboards
    public final Intent getLeaderboardIntent(GoogleApiClient googleApiClient, String str, int i2, int i3) {
        return Games.zza(googleApiClient).zza(str, i2, i3);
    }
}
