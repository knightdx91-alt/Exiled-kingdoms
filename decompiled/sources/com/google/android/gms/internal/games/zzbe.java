package com.google.android.gms.internal.games;

import android.content.Intent;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.PlayerEntity;
import com.google.android.gms.games.Players;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class zzbe implements Players {
    @Override // com.google.android.gms.games.Players
    public final Intent getCompareProfileIntent(GoogleApiClient googleApiClient, Player player) {
        return Games.zza(googleApiClient).zzb(new PlayerEntity(player));
    }

    @Override // com.google.android.gms.games.Players
    public final Player getCurrentPlayer(GoogleApiClient googleApiClient) {
        return Games.zza(googleApiClient).zzs();
    }

    @Override // com.google.android.gms.games.Players
    public final String getCurrentPlayerId(GoogleApiClient googleApiClient) {
        return Games.zza(googleApiClient).zzb(true);
    }

    @Override // com.google.android.gms.games.Players
    public final Intent getPlayerSearchIntent(GoogleApiClient googleApiClient) {
        return Games.zza(googleApiClient).zzah();
    }

    @Override // com.google.android.gms.games.Players
    public final PendingResult<Players.LoadPlayersResult> loadConnectedPlayers(GoogleApiClient googleApiClient, boolean z2) {
        return googleApiClient.enqueue(new zzbl(this, googleApiClient, z2));
    }

    @Override // com.google.android.gms.games.Players
    public final PendingResult<Players.LoadPlayersResult> loadInvitablePlayers(GoogleApiClient googleApiClient, int i2, boolean z2) {
        return googleApiClient.enqueue(new zzbh(this, googleApiClient, i2, z2));
    }

    @Override // com.google.android.gms.games.Players
    public final PendingResult<Players.LoadPlayersResult> loadMoreInvitablePlayers(GoogleApiClient googleApiClient, int i2) {
        return googleApiClient.enqueue(new zzbi(this, googleApiClient, i2));
    }

    @Override // com.google.android.gms.games.Players
    public final PendingResult<Players.LoadPlayersResult> loadMoreRecentlyPlayedWithPlayers(GoogleApiClient googleApiClient, int i2) {
        return googleApiClient.enqueue(new zzbk(this, googleApiClient, i2));
    }

    @Override // com.google.android.gms.games.Players
    public final PendingResult<Players.LoadPlayersResult> loadPlayer(GoogleApiClient googleApiClient, String str) {
        return googleApiClient.enqueue(new zzbf(this, googleApiClient, str));
    }

    @Override // com.google.android.gms.games.Players
    public final PendingResult<Players.LoadPlayersResult> loadRecentlyPlayedWithPlayers(GoogleApiClient googleApiClient, int i2, boolean z2) {
        return googleApiClient.enqueue(new zzbj(this, googleApiClient, i2, z2));
    }

    @Override // com.google.android.gms.games.Players
    public final PendingResult<Players.LoadPlayersResult> loadPlayer(GoogleApiClient googleApiClient, String str, boolean z2) {
        return googleApiClient.enqueue(new zzbg(this, googleApiClient, str, z2));
    }
}
