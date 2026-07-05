package com.google.android.gms.games.video;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.auth.api.credentials.CredentialsApi;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.games.internal.zzd;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@SafeParcelable.Class(creator = "VideoCapabilitiesCreator")
@SafeParcelable.Reserved({CredentialsApi.ACTIVITY_RESULT_ADD_ACCOUNT})
public final class VideoCapabilities extends zzd {
    public static final Parcelable.Creator<VideoCapabilities> CREATOR = new zza();

    @SafeParcelable.Field(getter = "isCameraSupported", id = 1)
    private final boolean zzru;

    @SafeParcelable.Field(getter = "isMicSupported", id = 2)
    private final boolean zzrv;

    @SafeParcelable.Field(getter = "isWriteStorageSupported", id = 3)
    private final boolean zzrw;

    @SafeParcelable.Field(getter = "getSupportedCaptureModes", id = 4)
    private final boolean[] zzrx;

    @SafeParcelable.Field(getter = "getSupportedQualityLevels", id = 5)
    private final boolean[] zzry;

    @SafeParcelable.Constructor
    public VideoCapabilities(@SafeParcelable.Param(id = 1) boolean z2, @SafeParcelable.Param(id = 2) boolean z3, @SafeParcelable.Param(id = 3) boolean z4, @SafeParcelable.Param(id = 4) boolean[] zArr, @SafeParcelable.Param(id = 5) boolean[] zArr2) {
        this.zzru = z2;
        this.zzrv = z3;
        this.zzrw = z4;
        this.zzrx = zArr;
        this.zzry = zArr2;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof VideoCapabilities)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        VideoCapabilities videoCapabilities = (VideoCapabilities) obj;
        return Objects.equal(videoCapabilities.getSupportedCaptureModes(), getSupportedCaptureModes()) && Objects.equal(videoCapabilities.getSupportedQualityLevels(), getSupportedQualityLevels()) && Objects.equal(Boolean.valueOf(videoCapabilities.isCameraSupported()), Boolean.valueOf(isCameraSupported())) && Objects.equal(Boolean.valueOf(videoCapabilities.isMicSupported()), Boolean.valueOf(isMicSupported())) && Objects.equal(Boolean.valueOf(videoCapabilities.isWriteStorageSupported()), Boolean.valueOf(isWriteStorageSupported()));
    }

    public final boolean[] getSupportedCaptureModes() {
        return this.zzrx;
    }

    public final boolean[] getSupportedQualityLevels() {
        return this.zzry;
    }

    public final int hashCode() {
        return Objects.hashCode(getSupportedCaptureModes(), getSupportedQualityLevels(), Boolean.valueOf(isCameraSupported()), Boolean.valueOf(isMicSupported()), Boolean.valueOf(isWriteStorageSupported()));
    }

    public final boolean isCameraSupported() {
        return this.zzru;
    }

    public final boolean isFullySupported(int i2, int i3) {
        return this.zzru && this.zzrv && this.zzrw && supportsCaptureMode(i2) && supportsQualityLevel(i3);
    }

    public final boolean isMicSupported() {
        return this.zzrv;
    }

    public final boolean isWriteStorageSupported() {
        return this.zzrw;
    }

    public final boolean supportsCaptureMode(int i2) {
        Preconditions.checkState(VideoConfiguration.isValidCaptureMode(i2, false));
        return this.zzrx[i2];
    }

    public final boolean supportsQualityLevel(int i2) {
        Preconditions.checkState(VideoConfiguration.isValidQualityLevel(i2, false));
        return this.zzry[i2];
    }

    public final String toString() {
        return Objects.toStringHelper(this).add("SupportedCaptureModes", getSupportedCaptureModes()).add("SupportedQualityLevels", getSupportedQualityLevels()).add("CameraSupported", Boolean.valueOf(isCameraSupported())).add("MicSupported", Boolean.valueOf(isMicSupported())).add("StorageWriteSupported", Boolean.valueOf(isWriteStorageSupported())).toString();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeBoolean(parcel, 1, isCameraSupported());
        SafeParcelWriter.writeBoolean(parcel, 2, isMicSupported());
        SafeParcelWriter.writeBoolean(parcel, 3, isWriteStorageSupported());
        SafeParcelWriter.writeBooleanArray(parcel, 4, getSupportedCaptureModes(), false);
        SafeParcelWriter.writeBooleanArray(parcel, 5, getSupportedQualityLevels(), false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}
