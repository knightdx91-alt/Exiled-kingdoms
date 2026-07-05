package com.google.android.gms.games;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.internal.PendingResultUtil;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.achievement.AchievementBuffer;
import com.google.android.gms.games.achievement.Achievements;
import com.google.android.gms.tasks.Task;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class AchievementsClient extends com.google.android.gms.internal.games.zzu {
    private static final PendingResultUtil.ResultConverter<Achievements.LoadAchievementsResult, AchievementBuffer> zze = new zzb();
    private static final PendingResultUtil.ResultConverter<Achievements.UpdateAchievementResult, Void> zzf = new zzc();
    private static final PendingResultUtil.ResultConverter<Achievements.UpdateAchievementResult, Boolean> zzg = new zzd();
    private static final com.google.android.gms.games.internal.zzr zzh = new zze();

    AchievementsClient(Activity activity, Games.GamesOptions gamesOptions) {
        super(activity, gamesOptions);
    }

    private static Task<Void> zza(PendingResult<Achievements.UpdateAchievementResult> pendingResult) {
        return com.google.android.gms.games.internal.zzi.zza(pendingResult, zzh, zzf);
    }

    private static Task<Boolean> zzb(PendingResult<Achievements.UpdateAchievementResult> pendingResult) {
        return com.google.android.gms.games.internal.zzi.zza(pendingResult, zzh, zzg);
    }

    public Task<Intent> getAchievementsIntent() {
        return doRead(new zza(this));
    }

    public void increment(String str, int i2) {
        Games.Achievements.increment(asGoogleApiClient(), str, i2);
    }

    public Task<Boolean> incrementImmediate(String str, int i2) {
        return zzb(Games.Achievements.incrementImmediate(asGoogleApiClient(), str, i2));
    }

    public Task<AnnotatedData<AchievementBuffer>> load(boolean z2) {
        return com.google.android.gms.games.internal.zzi.zzb(Games.Achievements.load(asGoogleApiClient(), z2), zze);
    }

    public void reveal(String str) {
        Games.Achievements.reveal(asGoogleApiClient(), str);
    }

    public Task<Void> revealImmediate(String str) {
        return zza(Games.Achievements.revealImmediate(asGoogleApiClient(), str));
    }

    public void setSteps(String str, int i2) {
        Games.Achievements.setSteps(asGoogleApiClient(), str, i2);
    }

    public Task<Boolean> setStepsImmediate(String str, int i2) {
        return zzb(Games.Achievements.setStepsImmediate(asGoogleApiClient(), str, i2));
    }

    public void unlock(String str) {
        Games.Achievements.unlock(asGoogleApiClient(), str);
    }

    public Task<Void> unlockImmediate(String str) {
        return zza(Games.Achievements.unlockImmediate(asGoogleApiClient(), str));
    }

    AchievementsClient(Context context, Games.GamesOptions gamesOptions) {
        super(context, gamesOptions);
    }
}
