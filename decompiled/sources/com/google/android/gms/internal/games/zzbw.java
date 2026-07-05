package com.google.android.gms.internal.games;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.quest.Milestone;
import com.google.android.gms.games.quest.Quest;
import com.google.android.gms.games.quest.Quests;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzbw implements Quests.ClaimMilestoneResult {
    private final /* synthetic */ Status zzbc;

    zzbw(zzbv zzbvVar, Status status) {
        this.zzbc = status;
    }

    @Override // com.google.android.gms.games.quest.Quests.ClaimMilestoneResult
    public final Milestone getMilestone() {
        return null;
    }

    @Override // com.google.android.gms.games.quest.Quests.ClaimMilestoneResult
    public final Quest getQuest() {
        return null;
    }

    @Override // com.google.android.gms.common.api.Result
    public final Status getStatus() {
        return this.zzbc;
    }
}
