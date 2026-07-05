package com.google.android.gms.internal.games;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.achievement.Achievements;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzs implements Achievements.UpdateAchievementResult {
    private final /* synthetic */ Status zzbc;
    private final /* synthetic */ zzr zzjj;

    zzs(zzr zzrVar, Status status) {
        this.zzjj = zzrVar;
        this.zzbc = status;
    }

    @Override // com.google.android.gms.games.achievement.Achievements.UpdateAchievementResult
    public final String getAchievementId() {
        return this.zzjj.zzji;
    }

    @Override // com.google.android.gms.common.api.Result
    public final Status getStatus() {
        return this.zzbc;
    }
}
