package com.google.android.gms.games.leaderboard;

import a.a;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.internal.games.zzei;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class zzb implements LeaderboardVariant {
    private final int zznn;
    private final int zzno;
    private final boolean zznp;
    private final long zznq;
    private final String zznr;
    private final long zzns;
    private final String zznt;
    private final String zznu;
    private final long zznv;
    private final String zznw;
    private final String zznx;
    private final String zzny;

    public zzb(LeaderboardVariant leaderboardVariant) {
        this.zznn = leaderboardVariant.getTimeSpan();
        this.zzno = leaderboardVariant.getCollection();
        this.zznp = leaderboardVariant.hasPlayerInfo();
        this.zznq = leaderboardVariant.getRawPlayerScore();
        this.zznr = leaderboardVariant.getDisplayPlayerScore();
        this.zzns = leaderboardVariant.getPlayerRank();
        this.zznt = leaderboardVariant.getDisplayPlayerRank();
        this.zznu = leaderboardVariant.getPlayerScoreTag();
        this.zznv = leaderboardVariant.getNumScores();
        this.zznw = leaderboardVariant.zzcd();
        this.zznx = leaderboardVariant.zzce();
        this.zzny = leaderboardVariant.zzcf();
    }

    static int zza(LeaderboardVariant leaderboardVariant) {
        return Objects.hashCode(Integer.valueOf(leaderboardVariant.getTimeSpan()), Integer.valueOf(leaderboardVariant.getCollection()), Boolean.valueOf(leaderboardVariant.hasPlayerInfo()), Long.valueOf(leaderboardVariant.getRawPlayerScore()), leaderboardVariant.getDisplayPlayerScore(), Long.valueOf(leaderboardVariant.getPlayerRank()), leaderboardVariant.getDisplayPlayerRank(), Long.valueOf(leaderboardVariant.getNumScores()), leaderboardVariant.zzcd(), leaderboardVariant.zzcf(), leaderboardVariant.zzce());
    }

    static String zzb(LeaderboardVariant leaderboardVariant) {
        String str;
        Objects.ToStringHelper toStringHelperAdd = Objects.toStringHelper(leaderboardVariant).add("TimeSpan", zzei.zzn(leaderboardVariant.getTimeSpan()));
        int collection = leaderboardVariant.getCollection();
        if (collection == -1) {
            str = "UNKNOWN";
        } else if (collection == 0) {
            str = "PUBLIC";
        } else if (collection == 1) {
            str = "SOCIAL";
        } else {
            if (collection != 2) {
                throw new IllegalArgumentException(a.g(43, collection, "Unknown leaderboard collection: "));
            }
            str = "SOCIAL_1P";
        }
        return toStringHelperAdd.add("Collection", str).add("RawPlayerScore", leaderboardVariant.hasPlayerInfo() ? Long.valueOf(leaderboardVariant.getRawPlayerScore()) : "none").add("DisplayPlayerScore", leaderboardVariant.hasPlayerInfo() ? leaderboardVariant.getDisplayPlayerScore() : "none").add("PlayerRank", leaderboardVariant.hasPlayerInfo() ? Long.valueOf(leaderboardVariant.getPlayerRank()) : "none").add("DisplayPlayerRank", leaderboardVariant.hasPlayerInfo() ? leaderboardVariant.getDisplayPlayerRank() : "none").add("NumScores", Long.valueOf(leaderboardVariant.getNumScores())).add("TopPageNextToken", leaderboardVariant.zzcd()).add("WindowPageNextToken", leaderboardVariant.zzcf()).add("WindowPagePrevToken", leaderboardVariant.zzce()).toString();
    }

    public final boolean equals(Object obj) {
        return zza(this, obj);
    }

    @Override // com.google.android.gms.common.data.Freezable
    public final /* bridge */ /* synthetic */ LeaderboardVariant freeze() {
        return this;
    }

    @Override // com.google.android.gms.games.leaderboard.LeaderboardVariant
    public final int getCollection() {
        return this.zzno;
    }

    @Override // com.google.android.gms.games.leaderboard.LeaderboardVariant
    public final String getDisplayPlayerRank() {
        return this.zznt;
    }

    @Override // com.google.android.gms.games.leaderboard.LeaderboardVariant
    public final String getDisplayPlayerScore() {
        return this.zznr;
    }

    @Override // com.google.android.gms.games.leaderboard.LeaderboardVariant
    public final long getNumScores() {
        return this.zznv;
    }

    @Override // com.google.android.gms.games.leaderboard.LeaderboardVariant
    public final long getPlayerRank() {
        return this.zzns;
    }

    @Override // com.google.android.gms.games.leaderboard.LeaderboardVariant
    public final String getPlayerScoreTag() {
        return this.zznu;
    }

    @Override // com.google.android.gms.games.leaderboard.LeaderboardVariant
    public final long getRawPlayerScore() {
        return this.zznq;
    }

    @Override // com.google.android.gms.games.leaderboard.LeaderboardVariant
    public final int getTimeSpan() {
        return this.zznn;
    }

    @Override // com.google.android.gms.games.leaderboard.LeaderboardVariant
    public final boolean hasPlayerInfo() {
        return this.zznp;
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

    @Override // com.google.android.gms.games.leaderboard.LeaderboardVariant
    public final String zzcd() {
        return this.zznw;
    }

    @Override // com.google.android.gms.games.leaderboard.LeaderboardVariant
    public final String zzce() {
        return this.zznx;
    }

    @Override // com.google.android.gms.games.leaderboard.LeaderboardVariant
    public final String zzcf() {
        return this.zzny;
    }

    static boolean zza(LeaderboardVariant leaderboardVariant, Object obj) {
        if (!(obj instanceof LeaderboardVariant)) {
            return false;
        }
        if (leaderboardVariant == obj) {
            return true;
        }
        LeaderboardVariant leaderboardVariant2 = (LeaderboardVariant) obj;
        return Objects.equal(Integer.valueOf(leaderboardVariant2.getTimeSpan()), Integer.valueOf(leaderboardVariant.getTimeSpan())) && Objects.equal(Integer.valueOf(leaderboardVariant2.getCollection()), Integer.valueOf(leaderboardVariant.getCollection())) && Objects.equal(Boolean.valueOf(leaderboardVariant2.hasPlayerInfo()), Boolean.valueOf(leaderboardVariant.hasPlayerInfo())) && Objects.equal(Long.valueOf(leaderboardVariant2.getRawPlayerScore()), Long.valueOf(leaderboardVariant.getRawPlayerScore())) && Objects.equal(leaderboardVariant2.getDisplayPlayerScore(), leaderboardVariant.getDisplayPlayerScore()) && Objects.equal(Long.valueOf(leaderboardVariant2.getPlayerRank()), Long.valueOf(leaderboardVariant.getPlayerRank())) && Objects.equal(leaderboardVariant2.getDisplayPlayerRank(), leaderboardVariant.getDisplayPlayerRank()) && Objects.equal(Long.valueOf(leaderboardVariant2.getNumScores()), Long.valueOf(leaderboardVariant.getNumScores())) && Objects.equal(leaderboardVariant2.zzcd(), leaderboardVariant.zzcd()) && Objects.equal(leaderboardVariant2.zzcf(), leaderboardVariant.zzcf()) && Objects.equal(leaderboardVariant2.zzce(), leaderboardVariant.zzce());
    }
}
