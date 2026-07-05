package com.google.android.gms.games.quest;

import com.google.android.gms.common.data.AbstractDataBuffer;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@Deprecated
public final class MilestoneBuffer extends AbstractDataBuffer<Milestone> {
    @Override // com.google.android.gms.common.data.AbstractDataBuffer, com.google.android.gms.common.data.DataBuffer
    public final Milestone get(int i2) {
        return new zzb(this.mDataHolder, i2);
    }

    @Override // com.google.android.gms.common.data.AbstractDataBuffer, com.google.android.gms.common.data.DataBuffer
    public final /* synthetic */ Object get(int i2) {
        throw new NoSuchMethodError();
    }
}
