package com.google.android.gms.internal.games;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.quest.Quest;
import com.google.android.gms.games.quest.Quests;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzbu implements Quests.AcceptQuestResult {
    private final /* synthetic */ Status zzbc;

    zzbu(zzbt zzbtVar, Status status) {
        this.zzbc = status;
    }

    @Override // com.google.android.gms.games.quest.Quests.AcceptQuestResult
    public final Quest getQuest() {
        return null;
    }

    @Override // com.google.android.gms.common.api.Result
    public final Status getStatus() {
        return this.zzbc;
    }
}
