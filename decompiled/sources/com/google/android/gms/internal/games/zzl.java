package com.google.android.gms.internal.games;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.games.achievement.Achievements;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzl extends zzr {
    private final /* synthetic */ String val$id;
    private final /* synthetic */ int zzjh;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzl(zzf zzfVar, String str, GoogleApiClient googleApiClient, String str2, int i2) {
        super(str, googleApiClient);
        this.val$id = str2;
        this.zzjh = i2;
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    public final /* synthetic */ void doExecute(Api.AnyClient anyClient) {
        ((com.google.android.gms.games.internal.zze) anyClient).zza((BaseImplementation.ResultHolder<Achievements.UpdateAchievementResult>) null, this.val$id, this.zzjh);
    }
}
