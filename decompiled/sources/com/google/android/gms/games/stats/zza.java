package com.google.android.gms.games.stats;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.auth.api.credentials.CredentialsApi;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.games.internal.zzd;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@SafeParcelable.Class(creator = "PlayerStatsEntityCreator")
@SafeParcelable.Reserved({CredentialsApi.ACTIVITY_RESULT_ADD_ACCOUNT})
public final class zza extends zzd implements PlayerStats {
    public static final Parcelable.Creator<zza> CREATOR = new zzb();

    @SafeParcelable.Field(getter = "getAverageSessionLength", id = 1)
    private final float zzre;

    @SafeParcelable.Field(getter = "getChurnProbability", id = 2)
    private final float zzrf;

    @SafeParcelable.Field(getter = "getDaysSinceLastPlayed", id = 3)
    private final int zzrg;

    @SafeParcelable.Field(getter = "getNumberOfPurchases", id = 4)
    private final int zzrh;

    @SafeParcelable.Field(getter = "getNumberOfSessions", id = 5)
    private final int zzri;

    @SafeParcelable.Field(getter = "getSessionPercentile", id = 6)
    private final float zzrj;

    @SafeParcelable.Field(getter = "getSpendPercentile", id = 7)
    private final float zzrk;

    @SafeParcelable.Field(getter = "getRawValues", id = 8)
    private final Bundle zzrl;

    @SafeParcelable.Field(getter = "getSpendProbability", id = 9)
    private final float zzrm;

    @SafeParcelable.Field(getter = "getHighSpenderProbability", id = 10)
    private final float zzrn;

    @SafeParcelable.Field(getter = "getTotalSpendNext28Days", id = 11)
    private final float zzro;

    @SafeParcelable.Constructor
    zza(@SafeParcelable.Param(id = 1) float f2, @SafeParcelable.Param(id = 2) float f3, @SafeParcelable.Param(id = 3) int i2, @SafeParcelable.Param(id = 4) int i3, @SafeParcelable.Param(id = 5) int i4, @SafeParcelable.Param(id = 6) float f4, @SafeParcelable.Param(id = 7) float f5, @SafeParcelable.Param(id = 8) Bundle bundle, @SafeParcelable.Param(id = 9) float f6, @SafeParcelable.Param(id = 10) float f7, @SafeParcelable.Param(id = 11) float f8) {
        this.zzre = f2;
        this.zzrf = f3;
        this.zzrg = i2;
        this.zzrh = i3;
        this.zzri = i4;
        this.zzrj = f4;
        this.zzrk = f5;
        this.zzrl = bundle;
        this.zzrm = f6;
        this.zzrn = f7;
        this.zzro = f8;
    }

    static int zza(PlayerStats playerStats) {
        return Objects.hashCode(Float.valueOf(playerStats.getAverageSessionLength()), Float.valueOf(playerStats.getChurnProbability()), Integer.valueOf(playerStats.getDaysSinceLastPlayed()), Integer.valueOf(playerStats.getNumberOfPurchases()), Integer.valueOf(playerStats.getNumberOfSessions()), Float.valueOf(playerStats.getSessionPercentile()), Float.valueOf(playerStats.getSpendPercentile()), Float.valueOf(playerStats.getSpendProbability()), Float.valueOf(playerStats.getHighSpenderProbability()), Float.valueOf(playerStats.getTotalSpendNext28Days()));
    }

    static String zzb(PlayerStats playerStats) {
        return Objects.toStringHelper(playerStats).add("AverageSessionLength", Float.valueOf(playerStats.getAverageSessionLength())).add("ChurnProbability", Float.valueOf(playerStats.getChurnProbability())).add("DaysSinceLastPlayed", Integer.valueOf(playerStats.getDaysSinceLastPlayed())).add("NumberOfPurchases", Integer.valueOf(playerStats.getNumberOfPurchases())).add("NumberOfSessions", Integer.valueOf(playerStats.getNumberOfSessions())).add("SessionPercentile", Float.valueOf(playerStats.getSessionPercentile())).add("SpendPercentile", Float.valueOf(playerStats.getSpendPercentile())).add("SpendProbability", Float.valueOf(playerStats.getSpendProbability())).add("HighSpenderProbability", Float.valueOf(playerStats.getHighSpenderProbability())).add("TotalSpendNext28Days", Float.valueOf(playerStats.getTotalSpendNext28Days())).toString();
    }

    public final boolean equals(Object obj) {
        return zza(this, obj);
    }

    @Override // com.google.android.gms.common.data.Freezable
    public final /* bridge */ /* synthetic */ PlayerStats freeze() {
        return this;
    }

    @Override // com.google.android.gms.games.stats.PlayerStats
    public final float getAverageSessionLength() {
        return this.zzre;
    }

    @Override // com.google.android.gms.games.stats.PlayerStats
    public final float getChurnProbability() {
        return this.zzrf;
    }

    @Override // com.google.android.gms.games.stats.PlayerStats
    public final int getDaysSinceLastPlayed() {
        return this.zzrg;
    }

    @Override // com.google.android.gms.games.stats.PlayerStats
    public final float getHighSpenderProbability() {
        return this.zzrn;
    }

    @Override // com.google.android.gms.games.stats.PlayerStats
    public final int getNumberOfPurchases() {
        return this.zzrh;
    }

    @Override // com.google.android.gms.games.stats.PlayerStats
    public final int getNumberOfSessions() {
        return this.zzri;
    }

    @Override // com.google.android.gms.games.stats.PlayerStats
    public final float getSessionPercentile() {
        return this.zzrj;
    }

    @Override // com.google.android.gms.games.stats.PlayerStats
    public final float getSpendPercentile() {
        return this.zzrk;
    }

    @Override // com.google.android.gms.games.stats.PlayerStats
    public final float getSpendProbability() {
        return this.zzrm;
    }

    @Override // com.google.android.gms.games.stats.PlayerStats
    public final float getTotalSpendNext28Days() {
        return this.zzro;
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

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeFloat(parcel, 1, getAverageSessionLength());
        SafeParcelWriter.writeFloat(parcel, 2, getChurnProbability());
        SafeParcelWriter.writeInt(parcel, 3, getDaysSinceLastPlayed());
        SafeParcelWriter.writeInt(parcel, 4, getNumberOfPurchases());
        SafeParcelWriter.writeInt(parcel, 5, getNumberOfSessions());
        SafeParcelWriter.writeFloat(parcel, 6, getSessionPercentile());
        SafeParcelWriter.writeFloat(parcel, 7, getSpendPercentile());
        SafeParcelWriter.writeBundle(parcel, 8, this.zzrl, false);
        SafeParcelWriter.writeFloat(parcel, 9, getSpendProbability());
        SafeParcelWriter.writeFloat(parcel, 10, getHighSpenderProbability());
        SafeParcelWriter.writeFloat(parcel, 11, getTotalSpendNext28Days());
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    @Override // com.google.android.gms.games.stats.PlayerStats
    public final Bundle zzcn() {
        return this.zzrl;
    }

    public zza(PlayerStats playerStats) {
        this.zzre = playerStats.getAverageSessionLength();
        this.zzrf = playerStats.getChurnProbability();
        this.zzrg = playerStats.getDaysSinceLastPlayed();
        this.zzrh = playerStats.getNumberOfPurchases();
        this.zzri = playerStats.getNumberOfSessions();
        this.zzrj = playerStats.getSessionPercentile();
        this.zzrk = playerStats.getSpendPercentile();
        this.zzrm = playerStats.getSpendProbability();
        this.zzrn = playerStats.getHighSpenderProbability();
        this.zzro = playerStats.getTotalSpendNext28Days();
        this.zzrl = playerStats.zzcn();
    }

    static boolean zza(PlayerStats playerStats, Object obj) {
        if (!(obj instanceof PlayerStats)) {
            return false;
        }
        if (playerStats == obj) {
            return true;
        }
        PlayerStats playerStats2 = (PlayerStats) obj;
        return Objects.equal(Float.valueOf(playerStats2.getAverageSessionLength()), Float.valueOf(playerStats.getAverageSessionLength())) && Objects.equal(Float.valueOf(playerStats2.getChurnProbability()), Float.valueOf(playerStats.getChurnProbability())) && Objects.equal(Integer.valueOf(playerStats2.getDaysSinceLastPlayed()), Integer.valueOf(playerStats.getDaysSinceLastPlayed())) && Objects.equal(Integer.valueOf(playerStats2.getNumberOfPurchases()), Integer.valueOf(playerStats.getNumberOfPurchases())) && Objects.equal(Integer.valueOf(playerStats2.getNumberOfSessions()), Integer.valueOf(playerStats.getNumberOfSessions())) && Objects.equal(Float.valueOf(playerStats2.getSessionPercentile()), Float.valueOf(playerStats.getSessionPercentile())) && Objects.equal(Float.valueOf(playerStats2.getSpendPercentile()), Float.valueOf(playerStats.getSpendPercentile())) && Objects.equal(Float.valueOf(playerStats2.getSpendProbability()), Float.valueOf(playerStats.getSpendProbability())) && Objects.equal(Float.valueOf(playerStats2.getHighSpenderProbability()), Float.valueOf(playerStats.getHighSpenderProbability())) && Objects.equal(Float.valueOf(playerStats2.getTotalSpendNext28Days()), Float.valueOf(playerStats.getTotalSpendNext28Days()));
    }
}
