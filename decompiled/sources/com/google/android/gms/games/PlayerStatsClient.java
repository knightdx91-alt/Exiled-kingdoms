package com.google.android.gms.games;

import android.app.Activity;
import android.content.Context;
import com.google.android.gms.common.internal.PendingResultUtil;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.stats.PlayerStats;
import com.google.android.gms.games.stats.Stats;
import com.google.android.gms.tasks.Task;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class PlayerStatsClient extends com.google.android.gms.internal.games.zzu {
    private static final PendingResultUtil.ResultConverter<Stats.LoadPlayerStatsResult, PlayerStats> zzcy = new zzas();

    PlayerStatsClient(Activity activity, Games.GamesOptions gamesOptions) {
        super(activity, gamesOptions);
    }

    public Task<AnnotatedData<PlayerStats>> loadPlayerStats(boolean z2) {
        return com.google.android.gms.games.internal.zzi.zza(Games.Stats.loadPlayerStats(asGoogleApiClient(), z2), zzcy);
    }

    PlayerStatsClient(Context context, Games.GamesOptions gamesOptions) {
        super(context, gamesOptions);
    }
}
