package com.google.android.gms.games;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.PendingResultUtil;
import com.google.android.gms.games.achievement.AchievementBuffer;
import com.google.android.gms.games.achievement.Achievements;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzb implements PendingResultUtil.ResultConverter<Achievements.LoadAchievementsResult, AchievementBuffer> {
    zzb() {
    }

    @Override // com.google.android.gms.common.internal.PendingResultUtil.ResultConverter
    public final /* synthetic */ AchievementBuffer convert(Result result) {
        Achievements.LoadAchievementsResult loadAchievementsResult = (Achievements.LoadAchievementsResult) result;
        if (loadAchievementsResult == null) {
            return null;
        }
        return loadAchievementsResult.getAchievements();
    }
}
