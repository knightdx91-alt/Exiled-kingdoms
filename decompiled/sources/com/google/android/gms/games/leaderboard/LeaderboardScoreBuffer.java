package com.google.android.gms.games.leaderboard;

import com.google.android.gms.common.data.AbstractDataBuffer;
import com.google.android.gms.common.data.DataHolder;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class LeaderboardScoreBuffer extends AbstractDataBuffer<LeaderboardScore> {
    private final zza zzna;

    public LeaderboardScoreBuffer(DataHolder dataHolder) {
        super(dataHolder);
        this.zzna = new zza(dataHolder.getMetadata());
    }

    @Override // com.google.android.gms.common.data.AbstractDataBuffer, com.google.android.gms.common.data.DataBuffer
    public final LeaderboardScore get(int i2) {
        return new LeaderboardScoreRef(this.mDataHolder, i2);
    }

    public final zza zzcb() {
        return this.zzna;
    }
}
