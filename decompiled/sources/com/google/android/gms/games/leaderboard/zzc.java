package com.google.android.gms.games.leaderboard;

import com.google.android.gms.common.data.DataBufferRef;
import com.google.android.gms.common.data.DataHolder;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class zzc extends DataBufferRef implements LeaderboardVariant {
    zzc(DataHolder dataHolder, int i2) {
        super(dataHolder, i2);
    }

    @Override // com.google.android.gms.common.data.DataBufferRef
    public final boolean equals(Object obj) {
        return zzb.zza(this, obj);
    }

    @Override // com.google.android.gms.common.data.Freezable
    public final /* synthetic */ LeaderboardVariant freeze() {
        return new zzb(this);
    }

    @Override // com.google.android.gms.games.leaderboard.LeaderboardVariant
    public final int getCollection() {
        return getInteger("collection");
    }

    @Override // com.google.android.gms.games.leaderboard.LeaderboardVariant
    public final String getDisplayPlayerRank() {
        return getString("player_display_rank");
    }

    @Override // com.google.android.gms.games.leaderboard.LeaderboardVariant
    public final String getDisplayPlayerScore() {
        return getString("player_display_score");
    }

    @Override // com.google.android.gms.games.leaderboard.LeaderboardVariant
    public final long getNumScores() {
        if (hasNull("total_scores")) {
            return -1L;
        }
        return getLong("total_scores");
    }

    @Override // com.google.android.gms.games.leaderboard.LeaderboardVariant
    public final long getPlayerRank() {
        if (hasNull("player_rank")) {
            return -1L;
        }
        return getLong("player_rank");
    }

    @Override // com.google.android.gms.games.leaderboard.LeaderboardVariant
    public final String getPlayerScoreTag() {
        return getString("player_score_tag");
    }

    @Override // com.google.android.gms.games.leaderboard.LeaderboardVariant
    public final long getRawPlayerScore() {
        if (hasNull("player_raw_score")) {
            return -1L;
        }
        return getLong("player_raw_score");
    }

    @Override // com.google.android.gms.games.leaderboard.LeaderboardVariant
    public final int getTimeSpan() {
        return getInteger("timespan");
    }

    @Override // com.google.android.gms.games.leaderboard.LeaderboardVariant
    public final boolean hasPlayerInfo() {
        return !hasNull("player_raw_score");
    }

    @Override // com.google.android.gms.common.data.DataBufferRef
    public final int hashCode() {
        return zzb.zza(this);
    }

    public final String toString() {
        return zzb.zzb(this);
    }

    @Override // com.google.android.gms.games.leaderboard.LeaderboardVariant
    public final String zzcd() {
        return getString("top_page_token_next");
    }

    @Override // com.google.android.gms.games.leaderboard.LeaderboardVariant
    public final String zzce() {
        return getString("window_page_token_prev");
    }

    @Override // com.google.android.gms.games.leaderboard.LeaderboardVariant
    public final String zzcf() {
        return getString("window_page_token_next");
    }
}
