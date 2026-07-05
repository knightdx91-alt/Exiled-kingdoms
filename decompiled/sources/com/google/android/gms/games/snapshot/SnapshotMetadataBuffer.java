package com.google.android.gms.games.snapshot;

import com.google.android.gms.common.data.AbstractDataBuffer;
import com.google.android.gms.common.data.DataHolder;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class SnapshotMetadataBuffer extends AbstractDataBuffer<SnapshotMetadata> {
    public SnapshotMetadataBuffer(DataHolder dataHolder) {
        super(dataHolder);
    }

    @Override // com.google.android.gms.common.data.AbstractDataBuffer, com.google.android.gms.common.data.DataBuffer
    public final SnapshotMetadata get(int i2) {
        return new SnapshotMetadataRef(this.mDataHolder, i2);
    }
}
