package com.google.android.gms.internal.games;

import android.content.Intent;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.quest.QuestUpdateListener;
import com.google.android.gms.games.quest.Quests;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class zzbo implements Quests {
    @Override // com.google.android.gms.games.quest.Quests
    public final PendingResult<Quests.AcceptQuestResult> accept(GoogleApiClient googleApiClient, String str) {
        return googleApiClient.execute(new zzbp(this, googleApiClient, str));
    }

    @Override // com.google.android.gms.games.quest.Quests
    public final PendingResult<Quests.ClaimMilestoneResult> claim(GoogleApiClient googleApiClient, String str, String str2) {
        return googleApiClient.execute(new zzbq(this, googleApiClient, str, str2));
    }

    @Override // com.google.android.gms.games.quest.Quests
    public final Intent getQuestIntent(GoogleApiClient googleApiClient, String str) {
        return Games.zza(googleApiClient).zzd(str);
    }

    @Override // com.google.android.gms.games.quest.Quests
    public final Intent getQuestsIntent(GoogleApiClient googleApiClient, int[] iArr) {
        return Games.zza(googleApiClient).zza(iArr);
    }

    @Override // com.google.android.gms.games.quest.Quests
    public final PendingResult<Quests.LoadQuestsResult> load(GoogleApiClient googleApiClient, int[] iArr, int i2, boolean z2) {
        return googleApiClient.enqueue(new zzbr(this, googleApiClient, iArr, i2, z2));
    }

    @Override // com.google.android.gms.games.quest.Quests
    public final PendingResult<Quests.LoadQuestsResult> loadByIds(GoogleApiClient googleApiClient, boolean z2, String... strArr) {
        return googleApiClient.enqueue(new zzbs(this, googleApiClient, z2, strArr));
    }

    @Override // com.google.android.gms.games.quest.Quests
    public final void registerQuestUpdateListener(GoogleApiClient googleApiClient, QuestUpdateListener questUpdateListener) {
        com.google.android.gms.games.internal.zze zzeVarZza = Games.zza(googleApiClient, false);
        if (zzeVarZza != null) {
            zzeVarZza.zze(googleApiClient.registerListener(questUpdateListener));
        }
    }

    @Override // com.google.android.gms.games.quest.Quests
    public final void showStateChangedPopup(GoogleApiClient googleApiClient, String str) {
        com.google.android.gms.games.internal.zze zzeVarZza = Games.zza(googleApiClient, false);
        if (zzeVarZza != null) {
            zzeVarZza.zze(str);
        }
    }

    @Override // com.google.android.gms.games.quest.Quests
    public final void unregisterQuestUpdateListener(GoogleApiClient googleApiClient) {
        com.google.android.gms.games.internal.zze zzeVarZza = Games.zza(googleApiClient, false);
        if (zzeVarZza != null) {
            zzeVarZza.zzae();
        }
    }
}
