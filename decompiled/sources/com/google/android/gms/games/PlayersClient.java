package com.google.android.gms.games;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.google.android.gms.common.internal.PendingResultUtil;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.Players;
import com.google.android.gms.tasks.Task;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class PlayersClient extends com.google.android.gms.internal.games.zzu {
    public static final String EXTRA_PLAYER_SEARCH_RESULTS = "player_search_results";
    private static final PendingResultUtil.ResultConverter<Players.LoadPlayersResult, PlayerBuffer> zzcz = new zzax();
    private static final com.google.android.gms.games.internal.zzq<Players.LoadPlayersResult> zzda = new zzay();
    private static final PendingResultUtil.ResultConverter<Players.LoadPlayersResult, Player> zzdb = new zzaz();

    PlayersClient(Activity activity, Games.GamesOptions gamesOptions) {
        super(activity, gamesOptions);
    }

    public Task<Intent> getCompareProfileIntent(Player player) {
        return doRead(new zzav(this, player));
    }

    public Task<Player> getCurrentPlayer() {
        return doRead(new zzau(this));
    }

    public Task<String> getCurrentPlayerId() {
        return doRead(new zzat(this));
    }

    public Task<Intent> getPlayerSearchIntent() {
        return doRead(new zzaw(this));
    }

    public Task<AnnotatedData<PlayerBuffer>> loadMoreRecentlyPlayedWithPlayers(int i2) {
        return com.google.android.gms.games.internal.zzi.zzb(Games.Players.loadMoreRecentlyPlayedWithPlayers(asGoogleApiClient(), i2), zzcz);
    }

    public Task<AnnotatedData<Player>> loadPlayer(String str) {
        return loadPlayer(str, false);
    }

    public Task<AnnotatedData<PlayerBuffer>> loadRecentlyPlayedWithPlayers(int i2, boolean z2) {
        return com.google.android.gms.games.internal.zzi.zzb(Games.Players.loadRecentlyPlayedWithPlayers(asGoogleApiClient(), i2, z2), zzcz);
    }

    PlayersClient(Context context, Games.GamesOptions gamesOptions) {
        super(context, gamesOptions);
    }

    public Task<AnnotatedData<Player>> loadPlayer(String str, boolean z2) {
        return com.google.android.gms.games.internal.zzi.zza(Games.Players.loadPlayer(asGoogleApiClient(), str, z2), zzdb, zzda);
    }
}
