package com.google.android.gms.games;

import android.app.Activity;
import android.content.Context;
import com.google.android.gms.common.internal.PendingResultUtil;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.GamesMetadata;
import com.google.android.gms.tasks.Task;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class GamesMetadataClient extends com.google.android.gms.internal.games.zzu {
    private static final PendingResultUtil.ResultConverter<GamesMetadata.LoadGamesResult, Game> zzbf = new zzv();
    private static final com.google.android.gms.games.internal.zzq<GamesMetadata.LoadGamesResult> zzbg = new zzw();

    GamesMetadataClient(Activity activity, Games.GamesOptions gamesOptions) {
        super(activity, gamesOptions);
    }

    public Task<Game> getCurrentGame() {
        return doRead(new zzu(this));
    }

    public Task<AnnotatedData<Game>> loadGame() {
        return com.google.android.gms.games.internal.zzi.zza(Games.GamesMetadata.loadGame(asGoogleApiClient()), zzbf, zzbg);
    }

    GamesMetadataClient(Context context, Games.GamesOptions gamesOptions) {
        super(context, gamesOptions);
    }
}
