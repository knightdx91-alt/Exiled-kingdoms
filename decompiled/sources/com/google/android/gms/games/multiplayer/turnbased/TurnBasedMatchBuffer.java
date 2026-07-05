package com.google.android.gms.games.multiplayer.turnbased;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.EntityBuffer;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class TurnBasedMatchBuffer extends EntityBuffer<TurnBasedMatch> {
    public TurnBasedMatchBuffer(DataHolder dataHolder) {
        super(dataHolder);
    }

    @Override // com.google.android.gms.common.data.EntityBuffer
    protected final /* synthetic */ TurnBasedMatch getEntry(int i2, int i3) {
        return new zzd(this.mDataHolder, i2, i3);
    }

    @Override // com.google.android.gms.common.data.EntityBuffer
    protected final String getPrimaryDataMarkerColumn() {
        return "external_match_id";
    }
}
