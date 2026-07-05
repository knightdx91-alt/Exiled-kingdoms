package com.google.android.gms.internal.games;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.leaderboard.LeaderboardScoreBuffer;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzas extends zzay {
    private final /* synthetic */ int zzjo;
    private final /* synthetic */ LeaderboardScoreBuffer zzjp;
    private final /* synthetic */ int zzjq;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzas(zzam zzamVar, GoogleApiClient googleApiClient, LeaderboardScoreBuffer leaderboardScoreBuffer, int i2, int i3) {
        super(googleApiClient, null);
        this.zzjp = leaderboardScoreBuffer;
        this.zzjo = i2;
        this.zzjq = i3;
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    protected final /* synthetic */ void doExecute(Api.AnyClient anyClient) {
        ((com.google.android.gms.games.internal.zze) anyClient).zza(this, this.zzjp, this.zzjo, this.zzjq);
    }
}
