package com.google.android.gms.games.leaderboard;

import android.database.CharArrayBuffer;
import android.net.Uri;
import com.google.android.gms.common.data.DataBufferRef;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.GameRef;
import java.util.ArrayList;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class LeaderboardRef extends DataBufferRef implements Leaderboard {
    private final Game zzmy;
    private final int zzmz;

    LeaderboardRef(DataHolder dataHolder, int i2, int i3) {
        super(dataHolder, i2);
        this.zzmz = i3;
        this.zzmy = new GameRef(dataHolder, i2);
    }

    @Override // com.google.android.gms.common.data.DataBufferRef
    public final boolean equals(Object obj) {
        return LeaderboardEntity.zza(this, obj);
    }

    @Override // com.google.android.gms.common.data.Freezable
    public final /* synthetic */ Leaderboard freeze() {
        return new LeaderboardEntity(this);
    }

    @Override // com.google.android.gms.games.leaderboard.Leaderboard
    public final String getDisplayName() {
        return getString("name");
    }

    @Override // com.google.android.gms.games.leaderboard.Leaderboard
    public final Game getGame() {
        return this.zzmy;
    }

    @Override // com.google.android.gms.games.leaderboard.Leaderboard
    public final Uri getIconImageUri() {
        return parseUri("board_icon_image_uri");
    }

    @Override // com.google.android.gms.games.leaderboard.Leaderboard
    public final String getIconImageUrl() {
        return getString("board_icon_image_url");
    }

    @Override // com.google.android.gms.games.leaderboard.Leaderboard
    public final String getLeaderboardId() {
        return getString("external_leaderboard_id");
    }

    @Override // com.google.android.gms.games.leaderboard.Leaderboard
    public final int getScoreOrder() {
        return getInteger("score_order");
    }

    @Override // com.google.android.gms.games.leaderboard.Leaderboard
    public final ArrayList<LeaderboardVariant> getVariants() {
        ArrayList<LeaderboardVariant> arrayList = new ArrayList<>(this.zzmz);
        for (int i2 = 0; i2 < this.zzmz; i2++) {
            arrayList.add(new zzc(this.mDataHolder, this.mDataRow + i2));
        }
        return arrayList;
    }

    @Override // com.google.android.gms.common.data.DataBufferRef
    public final int hashCode() {
        return LeaderboardEntity.zza(this);
    }

    public final String toString() {
        return LeaderboardEntity.zzb(this);
    }

    @Override // com.google.android.gms.games.leaderboard.Leaderboard
    public final void getDisplayName(CharArrayBuffer charArrayBuffer) {
        copyToBuffer("name", charArrayBuffer);
    }
}
