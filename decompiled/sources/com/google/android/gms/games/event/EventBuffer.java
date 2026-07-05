package com.google.android.gms.games.event;

import com.google.android.gms.common.data.AbstractDataBuffer;
import com.google.android.gms.common.data.DataHolder;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class EventBuffer extends AbstractDataBuffer<Event> {
    public EventBuffer(DataHolder dataHolder) {
        super(dataHolder);
    }

    @Override // com.google.android.gms.common.data.AbstractDataBuffer, com.google.android.gms.common.data.DataBuffer
    public final Event get(int i2) {
        return new EventRef(this.mDataHolder, i2);
    }
}
