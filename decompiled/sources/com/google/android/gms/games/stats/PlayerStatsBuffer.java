package com.google.android.gms.games.stats;

import com.google.android.gms.common.data.AbstractDataBuffer;
import com.google.android.gms.common.data.DataHolder;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class PlayerStatsBuffer extends AbstractDataBuffer<PlayerStats> {
    public PlayerStatsBuffer(DataHolder dataHolder) {
        super(dataHolder);
    }

    @Override // com.google.android.gms.common.data.AbstractDataBuffer, com.google.android.gms.common.data.DataBuffer
    public final /* synthetic */ Object get(int i2) {
        return new zzc(this.mDataHolder, i2);
    }
}
