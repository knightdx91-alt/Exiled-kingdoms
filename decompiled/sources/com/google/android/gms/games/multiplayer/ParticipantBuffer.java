package com.google.android.gms.games.multiplayer;

import com.google.android.gms.common.data.AbstractDataBuffer;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class ParticipantBuffer extends AbstractDataBuffer<Participant> {
    @Override // com.google.android.gms.common.data.AbstractDataBuffer, com.google.android.gms.common.data.DataBuffer
    public final Participant get(int i2) {
        return new ParticipantRef(this.mDataHolder, i2);
    }

    @Override // com.google.android.gms.common.data.AbstractDataBuffer, com.google.android.gms.common.data.DataBuffer
    public final /* synthetic */ Object get(int i2) {
        throw new NoSuchMethodError();
    }
}
