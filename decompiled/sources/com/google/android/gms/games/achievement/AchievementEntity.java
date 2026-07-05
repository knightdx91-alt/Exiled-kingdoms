package com.google.android.gms.games.achievement;

import android.database.CharArrayBuffer;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.auth.api.credentials.CredentialsApi;
import com.google.android.gms.common.internal.Asserts;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.util.DataUtils;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.PlayerEntity;
import com.google.android.gms.games.internal.zzd;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@SafeParcelable.Class(creator = "AchievementEntityCreator")
@SafeParcelable.Reserved({CredentialsApi.ACTIVITY_RESULT_ADD_ACCOUNT})
public final class AchievementEntity extends zzd implements Achievement {
    public static final Parcelable.Creator<AchievementEntity> CREATOR = new zza();

    @SafeParcelable.Field(getter = "getDescription", id = 4)
    private final String description;

    @SafeParcelable.Field(getter = "getName", id = 3)
    private final String name;

    @SafeParcelable.Field(getter = "getState", id = 12)
    private final int state;

    @SafeParcelable.Field(getter = "getType", id = 2)
    private final int type;

    @SafeParcelable.Field(getter = "getAchievementId", id = 1)
    private final String zzfa;

    @SafeParcelable.Field(getter = "getUnlockedImageUri", id = 5)
    private final Uri zzfb;

    @SafeParcelable.Field(getter = "getUnlockedImageUrl", id = 6)
    private final String zzfc;

    @SafeParcelable.Field(getter = "getRevealedImageUri", id = 7)
    private final Uri zzfd;

    @SafeParcelable.Field(getter = "getRevealedImageUrl", id = 8)
    private final String zzfe;

    @SafeParcelable.Field(getter = "getTotalStepsRaw", id = 9)
    private final int zzff;

    @SafeParcelable.Field(getter = "getFormattedTotalStepsRaw", id = 10)
    private final String zzfg;

    @SafeParcelable.Field(getter = "getPlayer", id = 11)
    private final PlayerEntity zzfh;

    @SafeParcelable.Field(getter = "getCurrentStepsRaw", id = 13)
    private final int zzfi;

    @SafeParcelable.Field(getter = "getFormattedCurrentStepsRaw", id = 14)
    private final String zzfj;

    @SafeParcelable.Field(getter = "getLastUpdatedTimestamp", id = 15)
    private final long zzfk;

    @SafeParcelable.Field(getter = "getXpValue", id = 16)
    private final long zzfl;

    public AchievementEntity(Achievement achievement) {
        String achievementId = achievement.getAchievementId();
        this.zzfa = achievementId;
        this.type = achievement.getType();
        this.name = achievement.getName();
        String description = achievement.getDescription();
        this.description = description;
        this.zzfb = achievement.getUnlockedImageUri();
        this.zzfc = achievement.getUnlockedImageUrl();
        this.zzfd = achievement.getRevealedImageUri();
        this.zzfe = achievement.getRevealedImageUrl();
        this.zzfh = (PlayerEntity) achievement.getPlayer().freeze();
        this.state = achievement.getState();
        this.zzfk = achievement.getLastUpdatedTimestamp();
        this.zzfl = achievement.getXpValue();
        if (achievement.getType() == 1) {
            this.zzff = achievement.getTotalSteps();
            this.zzfg = achievement.getFormattedTotalSteps();
            this.zzfi = achievement.getCurrentSteps();
            this.zzfj = achievement.getFormattedCurrentSteps();
        } else {
            this.zzff = 0;
            this.zzfg = null;
            this.zzfi = 0;
            this.zzfj = null;
        }
        Asserts.checkNotNull(achievementId);
        Asserts.checkNotNull(description);
    }

    static String zza(Achievement achievement) {
        Objects.ToStringHelper toStringHelperAdd = Objects.toStringHelper(achievement).add("Id", achievement.getAchievementId()).add("Type", Integer.valueOf(achievement.getType())).add("Name", achievement.getName()).add("Description", achievement.getDescription()).add("Player", achievement.getPlayer()).add("State", Integer.valueOf(achievement.getState()));
        if (achievement.getType() == 1) {
            toStringHelperAdd.add("CurrentSteps", Integer.valueOf(achievement.getCurrentSteps()));
            toStringHelperAdd.add("TotalSteps", Integer.valueOf(achievement.getTotalSteps()));
        }
        return toStringHelperAdd.toString();
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof Achievement)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        Achievement achievement = (Achievement) obj;
        if (achievement.getType() == getType()) {
            return (getType() != 1 || (achievement.getCurrentSteps() == getCurrentSteps() && achievement.getTotalSteps() == getTotalSteps())) && achievement.getXpValue() == getXpValue() && achievement.getState() == getState() && achievement.getLastUpdatedTimestamp() == getLastUpdatedTimestamp() && Objects.equal(achievement.getAchievementId(), getAchievementId()) && Objects.equal(achievement.getName(), getName()) && Objects.equal(achievement.getDescription(), getDescription()) && Objects.equal(achievement.getPlayer(), getPlayer());
        }
        return false;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.android.gms.common.data.Freezable
    public final Achievement freeze() {
        return this;
    }

    @Override // com.google.android.gms.games.achievement.Achievement
    public final String getAchievementId() {
        return this.zzfa;
    }

    @Override // com.google.android.gms.games.achievement.Achievement
    public final int getCurrentSteps() {
        Asserts.checkState(getType() == 1);
        return this.zzfi;
    }

    @Override // com.google.android.gms.games.achievement.Achievement
    public final String getDescription() {
        return this.description;
    }

    @Override // com.google.android.gms.games.achievement.Achievement
    public final String getFormattedCurrentSteps() {
        Asserts.checkState(getType() == 1);
        return this.zzfj;
    }

    @Override // com.google.android.gms.games.achievement.Achievement
    public final String getFormattedTotalSteps() {
        Asserts.checkState(getType() == 1);
        return this.zzfg;
    }

    @Override // com.google.android.gms.games.achievement.Achievement
    public final long getLastUpdatedTimestamp() {
        return this.zzfk;
    }

    @Override // com.google.android.gms.games.achievement.Achievement
    public final String getName() {
        return this.name;
    }

    @Override // com.google.android.gms.games.achievement.Achievement
    public final Player getPlayer() {
        return this.zzfh;
    }

    @Override // com.google.android.gms.games.achievement.Achievement
    public final Uri getRevealedImageUri() {
        return this.zzfd;
    }

    @Override // com.google.android.gms.games.achievement.Achievement
    public final String getRevealedImageUrl() {
        return this.zzfe;
    }

    @Override // com.google.android.gms.games.achievement.Achievement
    public final int getState() {
        return this.state;
    }

    @Override // com.google.android.gms.games.achievement.Achievement
    public final int getTotalSteps() {
        Asserts.checkState(getType() == 1);
        return this.zzff;
    }

    @Override // com.google.android.gms.games.achievement.Achievement
    public final int getType() {
        return this.type;
    }

    @Override // com.google.android.gms.games.achievement.Achievement
    public final Uri getUnlockedImageUri() {
        return this.zzfb;
    }

    @Override // com.google.android.gms.games.achievement.Achievement
    public final String getUnlockedImageUrl() {
        return this.zzfc;
    }

    @Override // com.google.android.gms.games.achievement.Achievement
    public final long getXpValue() {
        return this.zzfl;
    }

    public final int hashCode() {
        int currentSteps;
        int totalSteps;
        if (getType() == 1) {
            currentSteps = getCurrentSteps();
            totalSteps = getTotalSteps();
        } else {
            currentSteps = 0;
            totalSteps = 0;
        }
        return Objects.hashCode(getAchievementId(), getName(), Integer.valueOf(getType()), getDescription(), Long.valueOf(getXpValue()), Integer.valueOf(getState()), Long.valueOf(getLastUpdatedTimestamp()), getPlayer(), Integer.valueOf(currentSteps), Integer.valueOf(totalSteps));
    }

    @Override // com.google.android.gms.common.data.Freezable
    public final boolean isDataValid() {
        return true;
    }

    public final String toString() {
        return zza(this);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, getAchievementId(), false);
        SafeParcelWriter.writeInt(parcel, 2, getType());
        SafeParcelWriter.writeString(parcel, 3, getName(), false);
        SafeParcelWriter.writeString(parcel, 4, getDescription(), false);
        SafeParcelWriter.writeParcelable(parcel, 5, getUnlockedImageUri(), i2, false);
        SafeParcelWriter.writeString(parcel, 6, getUnlockedImageUrl(), false);
        SafeParcelWriter.writeParcelable(parcel, 7, getRevealedImageUri(), i2, false);
        SafeParcelWriter.writeString(parcel, 8, getRevealedImageUrl(), false);
        SafeParcelWriter.writeInt(parcel, 9, this.zzff);
        SafeParcelWriter.writeString(parcel, 10, this.zzfg, false);
        SafeParcelWriter.writeParcelable(parcel, 11, getPlayer(), i2, false);
        SafeParcelWriter.writeInt(parcel, 12, getState());
        SafeParcelWriter.writeInt(parcel, 13, this.zzfi);
        SafeParcelWriter.writeString(parcel, 14, this.zzfj, false);
        SafeParcelWriter.writeLong(parcel, 15, getLastUpdatedTimestamp());
        SafeParcelWriter.writeLong(parcel, 16, getXpValue());
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    @SafeParcelable.Constructor
    AchievementEntity(@SafeParcelable.Param(id = 1) String str, @SafeParcelable.Param(id = 2) int i2, @SafeParcelable.Param(id = 3) String str2, @SafeParcelable.Param(id = 4) String str3, @SafeParcelable.Param(id = 5) Uri uri, @SafeParcelable.Param(id = 6) String str4, @SafeParcelable.Param(id = 7) Uri uri2, @SafeParcelable.Param(id = 8) String str5, @SafeParcelable.Param(id = 9) int i3, @SafeParcelable.Param(id = 10) String str6, @SafeParcelable.Param(id = 11) PlayerEntity playerEntity, @SafeParcelable.Param(id = 12) int i4, @SafeParcelable.Param(id = 13) int i5, @SafeParcelable.Param(id = 14) String str7, @SafeParcelable.Param(id = 15) long j2, @SafeParcelable.Param(id = 16) long j3) {
        this.zzfa = str;
        this.type = i2;
        this.name = str2;
        this.description = str3;
        this.zzfb = uri;
        this.zzfc = str4;
        this.zzfd = uri2;
        this.zzfe = str5;
        this.zzff = i3;
        this.zzfg = str6;
        this.zzfh = playerEntity;
        this.state = i4;
        this.zzfi = i5;
        this.zzfj = str7;
        this.zzfk = j2;
        this.zzfl = j3;
    }

    @Override // com.google.android.gms.games.achievement.Achievement
    public final void getDescription(CharArrayBuffer charArrayBuffer) {
        DataUtils.copyStringToBuffer(this.description, charArrayBuffer);
    }

    @Override // com.google.android.gms.games.achievement.Achievement
    public final void getFormattedCurrentSteps(CharArrayBuffer charArrayBuffer) {
        Asserts.checkState(getType() == 1);
        DataUtils.copyStringToBuffer(this.zzfj, charArrayBuffer);
    }

    @Override // com.google.android.gms.games.achievement.Achievement
    public final void getFormattedTotalSteps(CharArrayBuffer charArrayBuffer) {
        Asserts.checkState(getType() == 1);
        DataUtils.copyStringToBuffer(this.zzfg, charArrayBuffer);
    }

    @Override // com.google.android.gms.games.achievement.Achievement
    public final void getName(CharArrayBuffer charArrayBuffer) {
        DataUtils.copyStringToBuffer(this.name, charArrayBuffer);
    }
}
