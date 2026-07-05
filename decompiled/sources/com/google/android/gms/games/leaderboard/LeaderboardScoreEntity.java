package com.google.android.gms.games.leaderboard;

import android.database.CharArrayBuffer;
import android.net.Uri;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.DataUtils;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.PlayerEntity;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class LeaderboardScoreEntity implements LeaderboardScore {
    private final long rawScore;
    private final String scoreTag;
    private final long zznc;
    private final String zznd;
    private final String zzne;
    private final long zznf;
    private final String zzng;
    private final Uri zznh;
    private final Uri zzni;
    private final PlayerEntity zznj;
    private final String zznk;
    private final String zznl;

    public LeaderboardScoreEntity(LeaderboardScore leaderboardScore) {
        this.zznc = leaderboardScore.getRank();
        this.zznd = (String) Preconditions.checkNotNull(leaderboardScore.getDisplayRank());
        this.zzne = (String) Preconditions.checkNotNull(leaderboardScore.getDisplayScore());
        this.rawScore = leaderboardScore.getRawScore();
        this.zznf = leaderboardScore.getTimestampMillis();
        this.zzng = leaderboardScore.getScoreHolderDisplayName();
        this.zznh = leaderboardScore.getScoreHolderIconImageUri();
        this.zzni = leaderboardScore.getScoreHolderHiResImageUri();
        Player scoreHolder = leaderboardScore.getScoreHolder();
        this.zznj = scoreHolder == null ? null : (PlayerEntity) scoreHolder.freeze();
        this.scoreTag = leaderboardScore.getScoreTag();
        this.zznk = leaderboardScore.getScoreHolderIconImageUrl();
        this.zznl = leaderboardScore.getScoreHolderHiResImageUrl();
    }

    static int zza(LeaderboardScore leaderboardScore) {
        return Objects.hashCode(Long.valueOf(leaderboardScore.getRank()), leaderboardScore.getDisplayRank(), Long.valueOf(leaderboardScore.getRawScore()), leaderboardScore.getDisplayScore(), Long.valueOf(leaderboardScore.getTimestampMillis()), leaderboardScore.getScoreHolderDisplayName(), leaderboardScore.getScoreHolderIconImageUri(), leaderboardScore.getScoreHolderHiResImageUri(), leaderboardScore.getScoreHolder());
    }

    static String zzb(LeaderboardScore leaderboardScore) {
        return Objects.toStringHelper(leaderboardScore).add("Rank", Long.valueOf(leaderboardScore.getRank())).add("DisplayRank", leaderboardScore.getDisplayRank()).add("Score", Long.valueOf(leaderboardScore.getRawScore())).add("DisplayScore", leaderboardScore.getDisplayScore()).add("Timestamp", Long.valueOf(leaderboardScore.getTimestampMillis())).add("DisplayName", leaderboardScore.getScoreHolderDisplayName()).add("IconImageUri", leaderboardScore.getScoreHolderIconImageUri()).add("IconImageUrl", leaderboardScore.getScoreHolderIconImageUrl()).add("HiResImageUri", leaderboardScore.getScoreHolderHiResImageUri()).add("HiResImageUrl", leaderboardScore.getScoreHolderHiResImageUrl()).add("Player", leaderboardScore.getScoreHolder() == null ? null : leaderboardScore.getScoreHolder()).add("ScoreTag", leaderboardScore.getScoreTag()).toString();
    }

    public final boolean equals(Object obj) {
        return zza(this, obj);
    }

    @Override // com.google.android.gms.common.data.Freezable
    public final /* bridge */ /* synthetic */ LeaderboardScore freeze() {
        return this;
    }

    @Override // com.google.android.gms.games.leaderboard.LeaderboardScore
    public final String getDisplayRank() {
        return this.zznd;
    }

    @Override // com.google.android.gms.games.leaderboard.LeaderboardScore
    public final String getDisplayScore() {
        return this.zzne;
    }

    @Override // com.google.android.gms.games.leaderboard.LeaderboardScore
    public final long getRank() {
        return this.zznc;
    }

    @Override // com.google.android.gms.games.leaderboard.LeaderboardScore
    public final long getRawScore() {
        return this.rawScore;
    }

    @Override // com.google.android.gms.games.leaderboard.LeaderboardScore
    public final Player getScoreHolder() {
        return this.zznj;
    }

    @Override // com.google.android.gms.games.leaderboard.LeaderboardScore
    public final String getScoreHolderDisplayName() {
        PlayerEntity playerEntity = this.zznj;
        return playerEntity == null ? this.zzng : playerEntity.getDisplayName();
    }

    @Override // com.google.android.gms.games.leaderboard.LeaderboardScore
    public final Uri getScoreHolderHiResImageUri() {
        PlayerEntity playerEntity = this.zznj;
        return playerEntity == null ? this.zzni : playerEntity.getHiResImageUri();
    }

    @Override // com.google.android.gms.games.leaderboard.LeaderboardScore
    public final String getScoreHolderHiResImageUrl() {
        PlayerEntity playerEntity = this.zznj;
        return playerEntity == null ? this.zznl : playerEntity.getHiResImageUrl();
    }

    @Override // com.google.android.gms.games.leaderboard.LeaderboardScore
    public final Uri getScoreHolderIconImageUri() {
        PlayerEntity playerEntity = this.zznj;
        return playerEntity == null ? this.zznh : playerEntity.getIconImageUri();
    }

    @Override // com.google.android.gms.games.leaderboard.LeaderboardScore
    public final String getScoreHolderIconImageUrl() {
        PlayerEntity playerEntity = this.zznj;
        return playerEntity == null ? this.zznk : playerEntity.getIconImageUrl();
    }

    @Override // com.google.android.gms.games.leaderboard.LeaderboardScore
    public final String getScoreTag() {
        return this.scoreTag;
    }

    @Override // com.google.android.gms.games.leaderboard.LeaderboardScore
    public final long getTimestampMillis() {
        return this.zznf;
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

    static boolean zza(LeaderboardScore leaderboardScore, Object obj) {
        if (!(obj instanceof LeaderboardScore)) {
            return false;
        }
        if (leaderboardScore == obj) {
            return true;
        }
        LeaderboardScore leaderboardScore2 = (LeaderboardScore) obj;
        return Objects.equal(Long.valueOf(leaderboardScore2.getRank()), Long.valueOf(leaderboardScore.getRank())) && Objects.equal(leaderboardScore2.getDisplayRank(), leaderboardScore.getDisplayRank()) && Objects.equal(Long.valueOf(leaderboardScore2.getRawScore()), Long.valueOf(leaderboardScore.getRawScore())) && Objects.equal(leaderboardScore2.getDisplayScore(), leaderboardScore.getDisplayScore()) && Objects.equal(Long.valueOf(leaderboardScore2.getTimestampMillis()), Long.valueOf(leaderboardScore.getTimestampMillis())) && Objects.equal(leaderboardScore2.getScoreHolderDisplayName(), leaderboardScore.getScoreHolderDisplayName()) && Objects.equal(leaderboardScore2.getScoreHolderIconImageUri(), leaderboardScore.getScoreHolderIconImageUri()) && Objects.equal(leaderboardScore2.getScoreHolderHiResImageUri(), leaderboardScore.getScoreHolderHiResImageUri()) && Objects.equal(leaderboardScore2.getScoreHolder(), leaderboardScore.getScoreHolder()) && Objects.equal(leaderboardScore2.getScoreTag(), leaderboardScore.getScoreTag());
    }

    @Override // com.google.android.gms.games.leaderboard.LeaderboardScore
    public final void getDisplayRank(CharArrayBuffer charArrayBuffer) {
        DataUtils.copyStringToBuffer(this.zznd, charArrayBuffer);
    }

    @Override // com.google.android.gms.games.leaderboard.LeaderboardScore
    public final void getDisplayScore(CharArrayBuffer charArrayBuffer) {
        DataUtils.copyStringToBuffer(this.zzne, charArrayBuffer);
    }

    @Override // com.google.android.gms.games.leaderboard.LeaderboardScore
    public final void getScoreHolderDisplayName(CharArrayBuffer charArrayBuffer) {
        PlayerEntity playerEntity = this.zznj;
        if (playerEntity == null) {
            DataUtils.copyStringToBuffer(this.zzng, charArrayBuffer);
        } else {
            playerEntity.getDisplayName(charArrayBuffer);
        }
    }
}
