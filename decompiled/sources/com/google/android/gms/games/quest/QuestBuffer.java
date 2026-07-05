package com.google.android.gms.games.quest;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.EntityBuffer;
import com.google.android.gms.common.util.VisibleForTesting;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@VisibleForTesting
@Deprecated
public final class QuestBuffer extends EntityBuffer<Quest> {
    public QuestBuffer(DataHolder dataHolder) {
        super(dataHolder);
    }

    @Override // com.google.android.gms.common.data.EntityBuffer
    protected final /* synthetic */ Quest getEntry(int i2, int i3) {
        return new QuestRef(this.mDataHolder, i2, i3);
    }

    @Override // com.google.android.gms.common.data.EntityBuffer
    protected final String getPrimaryDataMarkerColumn() {
        return "external_quest_id";
    }
}
