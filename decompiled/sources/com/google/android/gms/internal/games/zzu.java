package com.google.android.gms.internal.games;

import android.app.Activity;
import android.content.Context;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.games.Games;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class zzu extends GoogleApi<Games.GamesOptions> {
    protected zzu(Activity activity, Games.GamesOptions gamesOptions) {
        super(activity, Games.API, gamesOptions, GoogleApi.Settings.DEFAULT_SETTINGS);
    }

    protected zzu(Context context, Games.GamesOptions gamesOptions) {
        super(context, Games.API, gamesOptions, GoogleApi.Settings.DEFAULT_SETTINGS);
    }
}
