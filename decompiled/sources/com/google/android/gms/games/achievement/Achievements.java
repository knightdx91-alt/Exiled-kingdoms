package com.google.android.gms.games.achievement;

import android.content.Intent;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.util.VisibleForTesting;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@VisibleForTesting
@Deprecated
public interface Achievements {

    @Deprecated
    public interface LoadAchievementsResult extends Releasable, Result {
        AchievementBuffer getAchievements();
    }

    @Deprecated
    public interface UpdateAchievementResult extends Result {
        String getAchievementId();
    }

    Intent getAchievementsIntent(GoogleApiClient googleApiClient);

    void increment(GoogleApiClient googleApiClient, String str, int i2);

    PendingResult<UpdateAchievementResult> incrementImmediate(GoogleApiClient googleApiClient, String str, int i2);

    PendingResult<LoadAchievementsResult> load(GoogleApiClient googleApiClient, boolean z2);

    void reveal(GoogleApiClient googleApiClient, String str);

    PendingResult<UpdateAchievementResult> revealImmediate(GoogleApiClient googleApiClient, String str);

    void setSteps(GoogleApiClient googleApiClient, String str, int i2);

    PendingResult<UpdateAchievementResult> setStepsImmediate(GoogleApiClient googleApiClient, String str, int i2);

    void unlock(GoogleApiClient googleApiClient, String str);

    PendingResult<UpdateAchievementResult> unlockImmediate(GoogleApiClient googleApiClient, String str);
}
