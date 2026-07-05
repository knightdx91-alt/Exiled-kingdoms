package com.google.android.gms.internal.games;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.GamesMetadata;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class zzad implements GamesMetadata {
    @Override // com.google.android.gms.games.GamesMetadata
    public final Game getCurrentGame(GoogleApiClient googleApiClient) {
        return Games.zza(googleApiClient).zzu();
    }

    @Override // com.google.android.gms.games.GamesMetadata
    public final PendingResult<GamesMetadata.LoadGamesResult> loadGame(GoogleApiClient googleApiClient) {
        return googleApiClient.enqueue(new zzae(this, googleApiClient));
    }
}
