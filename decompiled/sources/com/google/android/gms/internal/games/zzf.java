package com.google.android.gms.internal.games;

import android.content.Intent;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.achievement.Achievements;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class zzf implements Achievements {
    @Override // com.google.android.gms.games.achievement.Achievements
    public final Intent getAchievementsIntent(GoogleApiClient googleApiClient) {
        return Games.zza(googleApiClient).zzx();
    }

    @Override // com.google.android.gms.games.achievement.Achievements
    public final void increment(GoogleApiClient googleApiClient, String str, int i2) {
        googleApiClient.execute(new zzl(this, str, googleApiClient, str, i2));
    }

    @Override // com.google.android.gms.games.achievement.Achievements
    public final PendingResult<Achievements.UpdateAchievementResult> incrementImmediate(GoogleApiClient googleApiClient, String str, int i2) {
        return googleApiClient.execute(new zzm(this, str, googleApiClient, str, i2));
    }

    @Override // com.google.android.gms.games.achievement.Achievements
    public final PendingResult<Achievements.LoadAchievementsResult> load(GoogleApiClient googleApiClient, boolean z2) {
        return googleApiClient.enqueue(new zzg(this, googleApiClient, z2));
    }

    @Override // com.google.android.gms.games.achievement.Achievements
    public final void reveal(GoogleApiClient googleApiClient, String str) {
        googleApiClient.execute(new zzh(this, str, googleApiClient, str));
    }

    @Override // com.google.android.gms.games.achievement.Achievements
    public final PendingResult<Achievements.UpdateAchievementResult> revealImmediate(GoogleApiClient googleApiClient, String str) {
        return googleApiClient.execute(new zzi(this, str, googleApiClient, str));
    }

    @Override // com.google.android.gms.games.achievement.Achievements
    public final void setSteps(GoogleApiClient googleApiClient, String str, int i2) {
        googleApiClient.execute(new zzn(this, str, googleApiClient, str, i2));
    }

    @Override // com.google.android.gms.games.achievement.Achievements
    public final PendingResult<Achievements.UpdateAchievementResult> setStepsImmediate(GoogleApiClient googleApiClient, String str, int i2) {
        return googleApiClient.execute(new zzo(this, str, googleApiClient, str, i2));
    }

    @Override // com.google.android.gms.games.achievement.Achievements
    public final void unlock(GoogleApiClient googleApiClient, String str) {
        googleApiClient.execute(new zzj(this, str, googleApiClient, str));
    }

    @Override // com.google.android.gms.games.achievement.Achievements
    public final PendingResult<Achievements.UpdateAchievementResult> unlockImmediate(GoogleApiClient googleApiClient, String str) {
        return googleApiClient.execute(new zzk(this, str, googleApiClient, str));
    }
}
