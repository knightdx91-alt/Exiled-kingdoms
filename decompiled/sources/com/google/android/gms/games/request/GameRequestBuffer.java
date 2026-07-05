package com.google.android.gms.games.request;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.EntityBuffer;
import com.google.android.gms.common.util.VisibleForTesting;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@VisibleForTesting
@Deprecated
public final class GameRequestBuffer extends EntityBuffer<GameRequest> {
    public GameRequestBuffer(DataHolder dataHolder) {
        super(dataHolder);
    }

    @Override // com.google.android.gms.common.data.EntityBuffer
    protected final /* synthetic */ GameRequest getEntry(int i2, int i3) {
        return new zzb(this.mDataHolder, i2, i3);
    }

    @Override // com.google.android.gms.common.data.EntityBuffer
    protected final String getPrimaryDataMarkerColumn() {
        return "external_request_id";
    }
}
