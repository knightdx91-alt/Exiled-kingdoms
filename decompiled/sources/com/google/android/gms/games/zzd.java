package com.google.android.gms.games;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.PendingResultUtil;
import com.google.android.gms.games.achievement.Achievements;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzd implements PendingResultUtil.ResultConverter<Achievements.UpdateAchievementResult, Boolean> {
    zzd() {
    }

    @Override // com.google.android.gms.common.internal.PendingResultUtil.ResultConverter
    public final /* synthetic */ Boolean convert(Result result) {
        Achievements.UpdateAchievementResult updateAchievementResult = (Achievements.UpdateAchievementResult) result;
        return Boolean.valueOf(updateAchievementResult != null && updateAchievementResult.getStatus().getStatusCode() == 3003);
    }
}
