package com.google.android.gms.internal.games;

import android.content.Intent;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.multiplayer.ParticipantResult;
import com.google.android.gms.games.multiplayer.turnbased.OnTurnBasedMatchUpdateReceivedListener;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatchConfig;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer;
import java.util.List;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class zzdb implements TurnBasedMultiplayer {
    @Override // com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer
    public final PendingResult<TurnBasedMultiplayer.InitiateMatchResult> acceptInvitation(GoogleApiClient googleApiClient, String str) {
        return googleApiClient.execute(new zzdf(this, googleApiClient, str));
    }

    @Override // com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer
    public final PendingResult<TurnBasedMultiplayer.CancelMatchResult> cancelMatch(GoogleApiClient googleApiClient, String str) {
        return googleApiClient.execute(new zzdk(this, str, googleApiClient, str));
    }

    @Override // com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer
    public final PendingResult<TurnBasedMultiplayer.InitiateMatchResult> createMatch(GoogleApiClient googleApiClient, TurnBasedMatchConfig turnBasedMatchConfig) {
        return googleApiClient.execute(new zzdc(this, googleApiClient, turnBasedMatchConfig));
    }

    @Override // com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer
    public final void declineInvitation(GoogleApiClient googleApiClient, String str) {
        com.google.android.gms.games.internal.zze zzeVarZza = Games.zza(googleApiClient, false);
        if (zzeVarZza != null) {
            zzeVarZza.zze(str, 1);
        }
    }

    @Override // com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer
    public final void dismissInvitation(GoogleApiClient googleApiClient, String str) {
        com.google.android.gms.games.internal.zze zzeVarZza = Games.zza(googleApiClient, false);
        if (zzeVarZza != null) {
            zzeVarZza.zzc(str, 1);
        }
    }

    @Override // com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer
    public final void dismissMatch(GoogleApiClient googleApiClient, String str) {
        com.google.android.gms.games.internal.zze zzeVarZza = Games.zza(googleApiClient, false);
        if (zzeVarZza != null) {
            zzeVarZza.zzc(str);
        }
    }

    @Override // com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer
    public final PendingResult<TurnBasedMultiplayer.UpdateMatchResult> finishMatch(GoogleApiClient googleApiClient, String str) {
        return finishMatch(googleApiClient, str, (byte[]) null, (ParticipantResult[]) null);
    }

    @Override // com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer
    public final Intent getInboxIntent(GoogleApiClient googleApiClient) {
        return Games.zza(googleApiClient).zzy();
    }

    @Override // com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer
    public final int getMaxMatchDataSize(GoogleApiClient googleApiClient) {
        return Games.zza(googleApiClient).zzap();
    }

    @Override // com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer
    public final Intent getSelectOpponentsIntent(GoogleApiClient googleApiClient, int i2, int i3) {
        return Games.zza(googleApiClient).zzb(i2, i3, true);
    }

    @Override // com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer
    public final PendingResult<TurnBasedMultiplayer.LeaveMatchResult> leaveMatch(GoogleApiClient googleApiClient, String str) {
        return googleApiClient.execute(new zzdi(this, googleApiClient, str));
    }

    @Override // com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer
    public final PendingResult<TurnBasedMultiplayer.LeaveMatchResult> leaveMatchDuringTurn(GoogleApiClient googleApiClient, String str, String str2) {
        return googleApiClient.execute(new zzdj(this, googleApiClient, str, str2));
    }

    @Override // com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer
    public final PendingResult<TurnBasedMultiplayer.LoadMatchResult> loadMatch(GoogleApiClient googleApiClient, String str) {
        return googleApiClient.enqueue(new zzdd(this, googleApiClient, str));
    }

    @Override // com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer
    public final PendingResult<TurnBasedMultiplayer.LoadMatchesResult> loadMatchesByStatus(GoogleApiClient googleApiClient, int i2, int[] iArr) {
        return googleApiClient.enqueue(new zzdl(this, googleApiClient, i2, iArr));
    }

    @Override // com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer
    public final void registerMatchUpdateListener(GoogleApiClient googleApiClient, OnTurnBasedMatchUpdateReceivedListener onTurnBasedMatchUpdateReceivedListener) {
        com.google.android.gms.games.internal.zze zzeVarZza = Games.zza(googleApiClient, false);
        if (zzeVarZza != null) {
            zzeVarZza.zzd(googleApiClient.registerListener(onTurnBasedMatchUpdateReceivedListener));
        }
    }

    @Override // com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer
    public final PendingResult<TurnBasedMultiplayer.InitiateMatchResult> rematch(GoogleApiClient googleApiClient, String str) {
        return googleApiClient.execute(new zzde(this, googleApiClient, str));
    }

    @Override // com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer
    public final PendingResult<TurnBasedMultiplayer.UpdateMatchResult> takeTurn(GoogleApiClient googleApiClient, String str, byte[] bArr, String str2) {
        return takeTurn(googleApiClient, str, bArr, str2, (ParticipantResult[]) null);
    }

    @Override // com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer
    public final void unregisterMatchUpdateListener(GoogleApiClient googleApiClient) {
        com.google.android.gms.games.internal.zze zzeVarZza = Games.zza(googleApiClient, false);
        if (zzeVarZza != null) {
            zzeVarZza.zzad();
        }
    }

    @Override // com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer
    public final PendingResult<TurnBasedMultiplayer.UpdateMatchResult> finishMatch(GoogleApiClient googleApiClient, String str, byte[] bArr, List<ParticipantResult> list) {
        return finishMatch(googleApiClient, str, bArr, list == null ? null : (ParticipantResult[]) list.toArray(new ParticipantResult[list.size()]));
    }

    @Override // com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer
    public final Intent getSelectOpponentsIntent(GoogleApiClient googleApiClient, int i2, int i3, boolean z2) {
        return Games.zza(googleApiClient).zzb(i2, i3, z2);
    }

    @Override // com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer
    public final PendingResult<TurnBasedMultiplayer.LoadMatchesResult> loadMatchesByStatus(GoogleApiClient googleApiClient, int[] iArr) {
        return loadMatchesByStatus(googleApiClient, 0, iArr);
    }

    @Override // com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer
    public final PendingResult<TurnBasedMultiplayer.UpdateMatchResult> takeTurn(GoogleApiClient googleApiClient, String str, byte[] bArr, String str2, List<ParticipantResult> list) {
        return takeTurn(googleApiClient, str, bArr, str2, list == null ? null : (ParticipantResult[]) list.toArray(new ParticipantResult[list.size()]));
    }

    @Override // com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer
    public final PendingResult<TurnBasedMultiplayer.UpdateMatchResult> finishMatch(GoogleApiClient googleApiClient, String str, byte[] bArr, ParticipantResult... participantResultArr) {
        return googleApiClient.execute(new zzdh(this, googleApiClient, str, bArr, participantResultArr));
    }

    @Override // com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer
    public final PendingResult<TurnBasedMultiplayer.UpdateMatchResult> takeTurn(GoogleApiClient googleApiClient, String str, byte[] bArr, String str2, ParticipantResult... participantResultArr) {
        return googleApiClient.execute(new zzdg(this, googleApiClient, str, bArr, str2, participantResultArr));
    }
}
