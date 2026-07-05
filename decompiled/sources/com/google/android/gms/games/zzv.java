package com.google.android.gms.games;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.PendingResultUtil;
import com.google.android.gms.games.GamesMetadata;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzv implements PendingResultUtil.ResultConverter<GamesMetadata.LoadGamesResult, Game> {
    zzv() {
    }

    private static Game zza(GamesMetadata.LoadGamesResult loadGamesResult) {
        GameBuffer games;
        if (loadGamesResult == null || (games = loadGamesResult.getGames()) == null) {
            return null;
        }
        try {
            if (games.getCount() > 0) {
                return ((Game) games.get(0)).freeze();
            }
            return null;
        } finally {
            games.release();
        }
    }

    @Override // com.google.android.gms.common.internal.PendingResultUtil.ResultConverter
    public final /* synthetic */ Game convert(Result result) {
        return zza((GamesMetadata.LoadGamesResult) result);
    }
}
