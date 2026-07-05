package com.google.android.gms.games;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.auth.api.credentials.CredentialsApi;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@SafeParcelable.Class(creator = "PlayerLevelCreator")
@SafeParcelable.Reserved({CredentialsApi.ACTIVITY_RESULT_ADD_ACCOUNT})
public final class PlayerLevel extends com.google.android.gms.games.internal.zzd {
    public static final Parcelable.Creator<PlayerLevel> CREATOR = new zzaq();

    @SafeParcelable.Field(getter = "getLevelNumber", id = 1)
    private final int zzcp;

    @SafeParcelable.Field(getter = "getMinXp", id = 2)
    private final long zzcq;

    @SafeParcelable.Field(getter = "getMaxXp", id = 3)
    private final long zzcr;

    @SafeParcelable.Constructor
    public PlayerLevel(@SafeParcelable.Param(id = 1) int i2, @SafeParcelable.Param(id = 2) long j2, @SafeParcelable.Param(id = 3) long j3) {
        Preconditions.checkState(j2 >= 0, "Min XP must be positive!");
        Preconditions.checkState(j3 > j2, "Max XP must be more than min XP!");
        this.zzcp = i2;
        this.zzcq = j2;
        this.zzcr = j3;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof PlayerLevel)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        PlayerLevel playerLevel = (PlayerLevel) obj;
        return Objects.equal(Integer.valueOf(playerLevel.getLevelNumber()), Integer.valueOf(getLevelNumber())) && Objects.equal(Long.valueOf(playerLevel.getMinXp()), Long.valueOf(getMinXp())) && Objects.equal(Long.valueOf(playerLevel.getMaxXp()), Long.valueOf(getMaxXp()));
    }

    public final int getLevelNumber() {
        return this.zzcp;
    }

    public final long getMaxXp() {
        return this.zzcr;
    }

    public final long getMinXp() {
        return this.zzcq;
    }

    public final int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.zzcp), Long.valueOf(this.zzcq), Long.valueOf(this.zzcr));
    }

    public final String toString() {
        return Objects.toStringHelper(this).add("LevelNumber", Integer.valueOf(getLevelNumber())).add("MinXp", Long.valueOf(getMinXp())).add("MaxXp", Long.valueOf(getMaxXp())).toString();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, getLevelNumber());
        SafeParcelWriter.writeLong(parcel, 2, getMinXp());
        SafeParcelWriter.writeLong(parcel, 3, getMaxXp());
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}
