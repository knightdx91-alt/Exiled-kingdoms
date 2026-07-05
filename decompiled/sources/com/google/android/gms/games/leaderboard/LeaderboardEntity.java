package com.google.android.gms.games.leaderboard;

import android.database.CharArrayBuffer;
import android.net.Uri;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.util.DataUtils;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.GameEntity;
import java.util.ArrayList;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class LeaderboardEntity implements Leaderboard {
    private final String zzac;
    private final String zzmv;
    private final int zzmw;
    private final ArrayList<zzb> zzmx;
    private final Game zzmy;
    private final String zzn;
    private final Uri zzr;

    public LeaderboardEntity(Leaderboard leaderboard) {
        this.zzmv = leaderboard.getLeaderboardId();
        this.zzn = leaderboard.getDisplayName();
        this.zzr = leaderboard.getIconImageUri();
        this.zzac = leaderboard.getIconImageUrl();
        this.zzmw = leaderboard.getScoreOrder();
        Game game = leaderboard.getGame();
        this.zzmy = game == null ? null : new GameEntity(game);
        ArrayList<LeaderboardVariant> variants = leaderboard.getVariants();
        int size = variants.size();
        this.zzmx = new ArrayList<>(size);
        for (int i2 = 0; i2 < size; i2++) {
            this.zzmx.add((zzb) variants.get(i2).freeze());
        }
    }

    static int zza(Leaderboard leaderboard) {
        return Objects.hashCode(leaderboard.getLeaderboardId(), leaderboard.getDisplayName(), leaderboard.getIconImageUri(), Integer.valueOf(leaderboard.getScoreOrder()), leaderboard.getVariants());
    }

    static String zzb(Leaderboard leaderboard) {
        return Objects.toStringHelper(leaderboard).add("LeaderboardId", leaderboard.getLeaderboardId()).add("DisplayName", leaderboard.getDisplayName()).add("IconImageUri", leaderboard.getIconImageUri()).add("IconImageUrl", leaderboard.getIconImageUrl()).add("ScoreOrder", Integer.valueOf(leaderboard.getScoreOrder())).add("Variants", leaderboard.getVariants()).toString();
    }

    public final boolean equals(Object obj) {
        return zza(this, obj);
    }

    @Override // com.google.android.gms.common.data.Freezable
    public final /* bridge */ /* synthetic */ Leaderboard freeze() {
        return this;
    }

    @Override // com.google.android.gms.games.leaderboard.Leaderboard
    public final String getDisplayName() {
        return this.zzn;
    }

    @Override // com.google.android.gms.games.leaderboard.Leaderboard
    public final Game getGame() {
        return this.zzmy;
    }

    @Override // com.google.android.gms.games.leaderboard.Leaderboard
    public final Uri getIconImageUri() {
        return this.zzr;
    }

    @Override // com.google.android.gms.games.leaderboard.Leaderboard
    public final String getIconImageUrl() {
        return this.zzac;
    }

    @Override // com.google.android.gms.games.leaderboard.Leaderboard
    public final String getLeaderboardId() {
        return this.zzmv;
    }

    @Override // com.google.android.gms.games.leaderboard.Leaderboard
    public final int getScoreOrder() {
        return this.zzmw;
    }

    @Override // com.google.android.gms.games.leaderboard.Leaderboard
    public final ArrayList<LeaderboardVariant> getVariants() {
        return new ArrayList<>(this.zzmx);
    }

    public final int hashCode() {
        return zza(this);
    }

    @Override // com.google.android.gms.common.data.Freezable
    public final boolean isDataValid() {
        return true;
    }

    public final String toString() {
        return zzb(this);
    }

    static boolean zza(Leaderboard leaderboard, Object obj) {
        if (!(obj instanceof Leaderboard)) {
            return false;
        }
        if (leaderboard == obj) {
            return true;
        }
        Leaderboard leaderboard2 = (Leaderboard) obj;
        return Objects.equal(leaderboard2.getLeaderboardId(), leaderboard.getLeaderboardId()) && Objects.equal(leaderboard2.getDisplayName(), leaderboard.getDisplayName()) && Objects.equal(leaderboard2.getIconImageUri(), leaderboard.getIconImageUri()) && Objects.equal(Integer.valueOf(leaderboard2.getScoreOrder()), Integer.valueOf(leaderboard.getScoreOrder())) && Objects.equal(leaderboard2.getVariants(), leaderboard.getVariants());
    }

    @Override // com.google.android.gms.games.leaderboard.Leaderboard
    public final void getDisplayName(CharArrayBuffer charArrayBuffer) {
        DataUtils.copyStringToBuffer(this.zzn, charArrayBuffer);
    }
}
