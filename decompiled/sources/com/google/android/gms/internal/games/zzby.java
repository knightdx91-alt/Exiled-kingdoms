package com.google.android.gms.internal.games;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.quest.QuestBuffer;
import com.google.android.gms.games.quest.Quests;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzby implements Quests.LoadQuestsResult {
    private final /* synthetic */ Status zzbc;

    zzby(zzbx zzbxVar, Status status) {
        this.zzbc = status;
    }

    @Override // com.google.android.gms.games.quest.Quests.LoadQuestsResult
    public final QuestBuffer getQuests() {
        return new QuestBuffer(DataHolder.empty(this.zzbc.getStatusCode()));
    }

    @Override // com.google.android.gms.common.api.Result
    public final Status getStatus() {
        return this.zzbc;
    }

    @Override // com.google.android.gms.common.api.Releasable
    public final void release() {
    }
}
