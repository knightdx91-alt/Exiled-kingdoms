package com.google.android.gms.games.leaderboard;

import android.database.CharArrayBuffer;
import android.net.Uri;
import com.google.android.gms.common.data.DataBufferRef;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.PlayerRef;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class LeaderboardScoreRef extends DataBufferRef implements LeaderboardScore {
    private final PlayerRef zznm;

    LeaderboardScoreRef(DataHolder dataHolder, int i2) {
        super(dataHolder, i2);
        this.zznm = new PlayerRef(dataHolder, i2);
    }

    @Override // com.google.android.gms.common.data.DataBufferRef
    public final boolean equals(Object obj) {
        return LeaderboardScoreEntity.zza(this, obj);
    }

    @Override // com.google.android.gms.common.data.Freezable
    public final /* synthetic */ LeaderboardScore freeze() {
        return new LeaderboardScoreEntity(this);
    }

    @Override // com.google.android.gms.games.leaderboard.LeaderboardScore
    public final String getDisplayRank() {
        return getString("display_rank");
    }

    @Override // com.google.android.gms.games.leaderboard.LeaderboardScore
    public final String getDisplayScore() {
        return getString("display_score");
    }

    @Override // com.google.android.gms.games.leaderboard.LeaderboardScore
    public final long getRank() {
        return getLong("rank");
    }

    @Override // com.google.android.gms.games.leaderboard.LeaderboardScore
    public final long getRawScore() {
        return getLong("raw_score");
    }

    @Override // com.google.android.gms.games.leaderboard.LeaderboardScore
    public final Player getScoreHolder() {
        if (hasNull("external_player_id")) {
            return null;
        }
        return this.zznm;
    }

    @Override // com.google.android.gms.games.leaderboard.LeaderboardScore
    public final String getScoreHolderDisplayName() {
        return hasNull("external_player_id") ? getString("default_display_name") : this.zznm.getDisplayName();
    }

    @Override // com.google.android.gms.games.leaderboard.LeaderboardScore
    public final Uri getScoreHolderHiResImageUri() {
        if (hasNull("external_player_id")) {
            return null;
        }
        return this.zznm.getHiResImageUri();
    }

    @Override // com.google.android.gms.games.leaderboard.LeaderboardScore
    public final String getScoreHolderHiResImageUrl() {
        if (hasNull("external_player_id")) {
            return null;
        }
        return this.zznm.getHiResImageUrl();
    }

    @Override // com.google.android.gms.games.leaderboard.LeaderboardScore
    public final Uri getScoreHolderIconImageUri() {
        return hasNull("external_player_id") ? parseUri("default_display_image_uri") : this.zznm.getIconImageUri();
    }

    @Override // com.google.android.gms.games.leaderboard.LeaderboardScore
    public final String getScoreHolderIconImageUrl() {
        return hasNull("external_player_id") ? getString("default_display_image_url") : this.zznm.getIconImageUrl();
    }

    @Override // com.google.android.gms.games.leaderboard.LeaderboardScore
    public final String getScoreTag() {
        return getString("score_tag");
    }

    @Override // com.google.android.gms.games.leaderboard.LeaderboardScore
    public final long getTimestampMillis() {
        return getLong("achieved_timestamp");
    }

    @Override // com.google.android.gms.common.data.DataBufferRef
    public final int hashCode() {
        return LeaderboardScoreEntity.zza(this);
    }

    public final String toString() {
        return LeaderboardScoreEntity.zzb(this);
    }

    @Override // com.google.android.gms.games.leaderboard.LeaderboardScore
    public final void getDisplayRank(CharArrayBuffer charArrayBuffer) {
        copyToBuffer("display_rank", charArrayBuffer);
    }

    @Override // com.google.android.gms.games.leaderboard.LeaderboardScore
    public final void getDisplayScore(CharArrayBuffer charArrayBuffer) {
        copyToBuffer("display_score", charArrayBuffer);
    }

    @Override // com.google.android.gms.games.leaderboard.LeaderboardScore
    public final void getScoreHolderDisplayName(CharArrayBuffer charArrayBuffer) {
        if (hasNull("external_player_id")) {
            copyToBuffer("default_display_name", charArrayBuffer);
        } else {
            this.zznm.getDisplayName(charArrayBuffer);
        }
    }
}
