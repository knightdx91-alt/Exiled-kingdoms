package com.google.android.gms.games;

import android.content.Intent;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.util.VisibleForTesting;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@VisibleForTesting
@Deprecated
public interface Players {
    public static final String EXTRA_PLAYER_SEARCH_RESULTS = "player_search_results";

    @Deprecated
    public interface LoadPlayersResult extends Releasable, Result {
        PlayerBuffer getPlayers();
    }

    Intent getCompareProfileIntent(GoogleApiClient googleApiClient, Player player);

    Player getCurrentPlayer(GoogleApiClient googleApiClient);

    String getCurrentPlayerId(GoogleApiClient googleApiClient);

    Intent getPlayerSearchIntent(GoogleApiClient googleApiClient);

    @Deprecated
    PendingResult<LoadPlayersResult> loadConnectedPlayers(GoogleApiClient googleApiClient, boolean z2);

    @Deprecated
    PendingResult<LoadPlayersResult> loadInvitablePlayers(GoogleApiClient googleApiClient, int i2, boolean z2);

    @Deprecated
    PendingResult<LoadPlayersResult> loadMoreInvitablePlayers(GoogleApiClient googleApiClient, int i2);

    PendingResult<LoadPlayersResult> loadMoreRecentlyPlayedWithPlayers(GoogleApiClient googleApiClient, int i2);

    PendingResult<LoadPlayersResult> loadPlayer(GoogleApiClient googleApiClient, String str);

    PendingResult<LoadPlayersResult> loadPlayer(GoogleApiClient googleApiClient, String str, boolean z2);

    PendingResult<LoadPlayersResult> loadRecentlyPlayedWithPlayers(GoogleApiClient googleApiClient, int i2, boolean z2);
}
