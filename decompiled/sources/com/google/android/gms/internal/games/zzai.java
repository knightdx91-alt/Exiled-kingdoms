package com.google.android.gms.internal.games;

import android.content.Intent;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.multiplayer.Invitations;
import com.google.android.gms.games.multiplayer.OnInvitationReceivedListener;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class zzai implements Invitations {
    @Override // com.google.android.gms.games.multiplayer.Invitations
    public final Intent getInvitationInboxIntent(GoogleApiClient googleApiClient) {
        return Games.zza(googleApiClient).zzz();
    }

    @Override // com.google.android.gms.games.multiplayer.Invitations
    public final PendingResult<Invitations.LoadInvitationsResult> loadInvitations(GoogleApiClient googleApiClient) {
        return loadInvitations(googleApiClient, 0);
    }

    @Override // com.google.android.gms.games.multiplayer.Invitations
    public final void registerInvitationListener(GoogleApiClient googleApiClient, OnInvitationReceivedListener onInvitationReceivedListener) {
        com.google.android.gms.games.internal.zze zzeVarZza = Games.zza(googleApiClient, false);
        if (zzeVarZza != null) {
            zzeVarZza.zzb(googleApiClient.registerListener(onInvitationReceivedListener));
        }
    }

    @Override // com.google.android.gms.games.multiplayer.Invitations
    public final void unregisterInvitationListener(GoogleApiClient googleApiClient) {
        com.google.android.gms.games.internal.zze zzeVarZza = Games.zza(googleApiClient, false);
        if (zzeVarZza != null) {
            zzeVarZza.zzab();
        }
    }

    @Override // com.google.android.gms.games.multiplayer.Invitations
    public final PendingResult<Invitations.LoadInvitationsResult> loadInvitations(GoogleApiClient googleApiClient, int i2) {
        return googleApiClient.enqueue(new zzaj(this, googleApiClient, i2));
    }
}
