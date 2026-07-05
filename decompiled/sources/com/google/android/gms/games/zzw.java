package com.google.android.gms.games;

import com.google.android.gms.games.GamesMetadata;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzw implements com.google.android.gms.games.internal.zzq<GamesMetadata.LoadGamesResult> {
    zzw() {
    }

    @Override // com.google.android.gms.games.internal.zzq
    public final /* synthetic */ void release(GamesMetadata.LoadGamesResult loadGamesResult) {
        GamesMetadata.LoadGamesResult loadGamesResult2 = loadGamesResult;
        if (loadGamesResult2.getGames() != null) {
            loadGamesResult2.getGames().release();
        }
    }
}
