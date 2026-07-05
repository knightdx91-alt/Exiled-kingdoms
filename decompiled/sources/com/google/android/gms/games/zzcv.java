package com.google.android.gms.games;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.ApiExceptionUtil;
import com.google.android.gms.games.TurnBasedMultiplayerClient;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatch;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzcv implements com.google.android.gms.games.internal.zzp<TurnBasedMatch> {
    zzcv() {
    }

    @Override // com.google.android.gms.games.internal.zzp
    public final /* synthetic */ ApiException zza(Status status, TurnBasedMatch turnBasedMatch) {
        return status.getStatusCode() == 26593 ? new TurnBasedMultiplayerClient.MatchOutOfDateApiException(status, turnBasedMatch) : ApiExceptionUtil.fromStatus(status);
    }
}
