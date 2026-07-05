package com.google.android.gms.games.snapshot;

import android.database.CharArrayBuffer;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.auth.api.credentials.CredentialsApi;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.util.DataUtils;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.GameEntity;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.PlayerEntity;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@SafeParcelable.Class(creator = "SnapshotMetadataEntityCreator")
@SafeParcelable.Reserved({CredentialsApi.ACTIVITY_RESULT_ADD_ACCOUNT})
public final class SnapshotMetadataEntity extends com.google.android.gms.games.internal.zzd implements SnapshotMetadata {
    public static final Parcelable.Creator<SnapshotMetadataEntity> CREATOR = new zzf();

    @SafeParcelable.Field(getter = "getDescription", id = 8)
    private final String description;

    @SafeParcelable.Field(getter = "getDeviceName", id = 15)
    private final String deviceName;

    @SafeParcelable.Field(getter = "getTitle", id = 7)
    private final String zzcc;

    @SafeParcelable.Field(getter = "getSnapshotId", id = 3)
    private final String zzgo;

    @SafeParcelable.Field(getter = "getGame", id = 1)
    private final GameEntity zzky;

    @SafeParcelable.Field(getter = "getCoverImageUri", id = 5)
    private final Uri zzqs;

    @SafeParcelable.Field(getter = "getOwner", id = 2)
    private final PlayerEntity zzqv;

    @SafeParcelable.Field(getter = "getCoverImageUrl", id = 6)
    private final String zzqw;

    @SafeParcelable.Field(getter = "getLastModifiedTimestamp", id = 9)
    private final long zzqx;

    @SafeParcelable.Field(getter = "getPlayedTime", id = 10)
    private final long zzqy;

    @SafeParcelable.Field(getter = "getCoverImageAspectRatio", id = 11)
    private final float zzqz;

    @SafeParcelable.Field(getter = "getUniqueName", id = 12)
    private final String zzra;

    @SafeParcelable.Field(getter = "hasChangePending", id = 13)
    private final boolean zzrb;

    @SafeParcelable.Field(getter = "getProgressValue", id = 14)
    private final long zzrc;

    @SafeParcelable.Constructor
    SnapshotMetadataEntity(@SafeParcelable.Param(id = 1) GameEntity gameEntity, @SafeParcelable.Param(id = 2) PlayerEntity playerEntity, @SafeParcelable.Param(id = 3) String str, @SafeParcelable.Param(id = 5) Uri uri, @SafeParcelable.Param(id = 6) String str2, @SafeParcelable.Param(id = 7) String str3, @SafeParcelable.Param(id = 8) String str4, @SafeParcelable.Param(id = 9) long j2, @SafeParcelable.Param(id = 10) long j3, @SafeParcelable.Param(id = 11) float f2, @SafeParcelable.Param(id = 12) String str5, @SafeParcelable.Param(id = 13) boolean z2, @SafeParcelable.Param(id = 14) long j4, @SafeParcelable.Param(id = 15) String str6) {
        this.zzky = gameEntity;
        this.zzqv = playerEntity;
        this.zzgo = str;
        this.zzqs = uri;
        this.zzqw = str2;
        this.zzqz = f2;
        this.zzcc = str3;
        this.description = str4;
        this.zzqx = j2;
        this.zzqy = j3;
        this.zzra = str5;
        this.zzrb = z2;
        this.zzrc = j4;
        this.deviceName = str6;
    }

    static int zza(SnapshotMetadata snapshotMetadata) {
        return Objects.hashCode(snapshotMetadata.getGame(), snapshotMetadata.getOwner(), snapshotMetadata.getSnapshotId(), snapshotMetadata.getCoverImageUri(), Float.valueOf(snapshotMetadata.getCoverImageAspectRatio()), snapshotMetadata.getTitle(), snapshotMetadata.getDescription(), Long.valueOf(snapshotMetadata.getLastModifiedTimestamp()), Long.valueOf(snapshotMetadata.getPlayedTime()), snapshotMetadata.getUniqueName(), Boolean.valueOf(snapshotMetadata.hasChangePending()), Long.valueOf(snapshotMetadata.getProgressValue()), snapshotMetadata.getDeviceName());
    }

    static String zzb(SnapshotMetadata snapshotMetadata) {
        return Objects.toStringHelper(snapshotMetadata).add("Game", snapshotMetadata.getGame()).add("Owner", snapshotMetadata.getOwner()).add("SnapshotId", snapshotMetadata.getSnapshotId()).add("CoverImageUri", snapshotMetadata.getCoverImageUri()).add("CoverImageUrl", snapshotMetadata.getCoverImageUrl()).add("CoverImageAspectRatio", Float.valueOf(snapshotMetadata.getCoverImageAspectRatio())).add("Description", snapshotMetadata.getDescription()).add("LastModifiedTimestamp", Long.valueOf(snapshotMetadata.getLastModifiedTimestamp())).add("PlayedTime", Long.valueOf(snapshotMetadata.getPlayedTime())).add("UniqueName", snapshotMetadata.getUniqueName()).add("ChangePending", Boolean.valueOf(snapshotMetadata.hasChangePending())).add("ProgressValue", Long.valueOf(snapshotMetadata.getProgressValue())).add("DeviceName", snapshotMetadata.getDeviceName()).toString();
    }

    public final boolean equals(Object obj) {
        return zza(this, obj);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.android.gms.common.data.Freezable
    public final SnapshotMetadata freeze() {
        return this;
    }

    @Override // com.google.android.gms.games.snapshot.SnapshotMetadata
    public final float getCoverImageAspectRatio() {
        return this.zzqz;
    }

    @Override // com.google.android.gms.games.snapshot.SnapshotMetadata
    public final Uri getCoverImageUri() {
        return this.zzqs;
    }

    @Override // com.google.android.gms.games.snapshot.SnapshotMetadata
    public final String getCoverImageUrl() {
        return this.zzqw;
    }

    @Override // com.google.android.gms.games.snapshot.SnapshotMetadata
    public final String getDescription() {
        return this.description;
    }

    @Override // com.google.android.gms.games.snapshot.SnapshotMetadata
    public final String getDeviceName() {
        return this.deviceName;
    }

    @Override // com.google.android.gms.games.snapshot.SnapshotMetadata
    public final Game getGame() {
        return this.zzky;
    }

    @Override // com.google.android.gms.games.snapshot.SnapshotMetadata
    public final long getLastModifiedTimestamp() {
        return this.zzqx;
    }

    @Override // com.google.android.gms.games.snapshot.SnapshotMetadata
    public final Player getOwner() {
        return this.zzqv;
    }

    @Override // com.google.android.gms.games.snapshot.SnapshotMetadata
    public final long getPlayedTime() {
        return this.zzqy;
    }

    @Override // com.google.android.gms.games.snapshot.SnapshotMetadata
    public final long getProgressValue() {
        return this.zzrc;
    }

    @Override // com.google.android.gms.games.snapshot.SnapshotMetadata
    public final String getSnapshotId() {
        return this.zzgo;
    }

    @Override // com.google.android.gms.games.snapshot.SnapshotMetadata
    public final String getTitle() {
        return this.zzcc;
    }

    @Override // com.google.android.gms.games.snapshot.SnapshotMetadata
    public final String getUniqueName() {
        return this.zzra;
    }

    @Override // com.google.android.gms.games.snapshot.SnapshotMetadata
    public final boolean hasChangePending() {
        return this.zzrb;
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
        SafeParcelWriter.writeParcelable(parcel, 1, getGame(), i2, false);
        SafeParcelWriter.writeParcelable(parcel, 2, getOwner(), i2, false);
        SafeParcelWriter.writeString(parcel, 3, getSnapshotId(), false);
        SafeParcelWriter.writeParcelable(parcel, 5, getCoverImageUri(), i2, false);
        SafeParcelWriter.writeString(parcel, 6, getCoverImageUrl(), false);
        SafeParcelWriter.writeString(parcel, 7, this.zzcc, false);
        SafeParcelWriter.writeString(parcel, 8, getDescription(), false);
        SafeParcelWriter.writeLong(parcel, 9, getLastModifiedTimestamp());
        SafeParcelWriter.writeLong(parcel, 10, getPlayedTime());
        SafeParcelWriter.writeFloat(parcel, 11, getCoverImageAspectRatio());
        SafeParcelWriter.writeString(parcel, 12, getUniqueName(), false);
        SafeParcelWriter.writeBoolean(parcel, 13, hasChangePending());
        SafeParcelWriter.writeLong(parcel, 14, getProgressValue());
        SafeParcelWriter.writeString(parcel, 15, getDeviceName(), false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    public SnapshotMetadataEntity(SnapshotMetadata snapshotMetadata) {
        this.zzky = new GameEntity(snapshotMetadata.getGame());
        this.zzqv = new PlayerEntity(snapshotMetadata.getOwner());
        this.zzgo = snapshotMetadata.getSnapshotId();
        this.zzqs = snapshotMetadata.getCoverImageUri();
        this.zzqw = snapshotMetadata.getCoverImageUrl();
        this.zzqz = snapshotMetadata.getCoverImageAspectRatio();
        this.zzcc = snapshotMetadata.getTitle();
        this.description = snapshotMetadata.getDescription();
        this.zzqx = snapshotMetadata.getLastModifiedTimestamp();
        this.zzqy = snapshotMetadata.getPlayedTime();
        this.zzra = snapshotMetadata.getUniqueName();
        this.zzrb = snapshotMetadata.hasChangePending();
        this.zzrc = snapshotMetadata.getProgressValue();
        this.deviceName = snapshotMetadata.getDeviceName();
    }

    static boolean zza(SnapshotMetadata snapshotMetadata, Object obj) {
        if (!(obj instanceof SnapshotMetadata)) {
            return false;
        }
        if (snapshotMetadata == obj) {
            return true;
        }
        SnapshotMetadata snapshotMetadata2 = (SnapshotMetadata) obj;
        return Objects.equal(snapshotMetadata2.getGame(), snapshotMetadata.getGame()) && Objects.equal(snapshotMetadata2.getOwner(), snapshotMetadata.getOwner()) && Objects.equal(snapshotMetadata2.getSnapshotId(), snapshotMetadata.getSnapshotId()) && Objects.equal(snapshotMetadata2.getCoverImageUri(), snapshotMetadata.getCoverImageUri()) && Objects.equal(Float.valueOf(snapshotMetadata2.getCoverImageAspectRatio()), Float.valueOf(snapshotMetadata.getCoverImageAspectRatio())) && Objects.equal(snapshotMetadata2.getTitle(), snapshotMetadata.getTitle()) && Objects.equal(snapshotMetadata2.getDescription(), snapshotMetadata.getDescription()) && Objects.equal(Long.valueOf(snapshotMetadata2.getLastModifiedTimestamp()), Long.valueOf(snapshotMetadata.getLastModifiedTimestamp())) && Objects.equal(Long.valueOf(snapshotMetadata2.getPlayedTime()), Long.valueOf(snapshotMetadata.getPlayedTime())) && Objects.equal(snapshotMetadata2.getUniqueName(), snapshotMetadata.getUniqueName()) && Objects.equal(Boolean.valueOf(snapshotMetadata2.hasChangePending()), Boolean.valueOf(snapshotMetadata.hasChangePending())) && Objects.equal(Long.valueOf(snapshotMetadata2.getProgressValue()), Long.valueOf(snapshotMetadata.getProgressValue())) && Objects.equal(snapshotMetadata2.getDeviceName(), snapshotMetadata.getDeviceName());
    }

    @Override // com.google.android.gms.games.snapshot.SnapshotMetadata
    public final void getDescription(CharArrayBuffer charArrayBuffer) {
        DataUtils.copyStringToBuffer(this.description, charArrayBuffer);
    }
}
