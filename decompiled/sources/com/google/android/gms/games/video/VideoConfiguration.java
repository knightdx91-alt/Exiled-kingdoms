package com.google.android.gms.games.video;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.auth.api.credentials.CredentialsApi;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@SafeParcelable.Class(creator = "VideoConfigurationCreator")
@SafeParcelable.Reserved({CredentialsApi.ACTIVITY_RESULT_ADD_ACCOUNT})
public final class VideoConfiguration extends AbstractSafeParcelable {
    public static final int CAPTURE_MODE_FILE = 0;
    public static final int CAPTURE_MODE_STREAM = 1;
    public static final int CAPTURE_MODE_UNKNOWN = -1;
    public static final Parcelable.Creator<VideoConfiguration> CREATOR = new zzb();
    public static final int NUM_CAPTURE_MODE = 2;
    public static final int NUM_QUALITY_LEVEL = 4;
    public static final int QUALITY_LEVEL_FULLHD = 3;
    public static final int QUALITY_LEVEL_HD = 1;
    public static final int QUALITY_LEVEL_SD = 0;
    public static final int QUALITY_LEVEL_UNKNOWN = -1;
    public static final int QUALITY_LEVEL_XHD = 2;

    @SafeParcelable.Field(getter = "getCaptureMode", id = 2)
    private final int zzrq;

    @SafeParcelable.Field(getter = "getQualityLevel", id = 1)
    private final int zzrz;

    @SafeParcelable.Field(getter = "shouldShowToastAfterRecording", id = 7)
    private final boolean zzsa;

    public static final class Builder {
        private int zzrq;
        private int zzrz;
        private boolean zzsa = true;

        public Builder(int i2, int i3) {
            this.zzrz = i2;
            this.zzrq = i3;
        }

        public final VideoConfiguration build() {
            return new VideoConfiguration(this.zzrz, this.zzrq, this.zzsa);
        }

        public final Builder setCaptureMode(int i2) {
            this.zzrq = i2;
            return this;
        }

        public final Builder setQualityLevel(int i2) {
            this.zzrz = i2;
            return this;
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface ValidCaptureModes {
    }

    @SafeParcelable.Constructor
    public VideoConfiguration(@SafeParcelable.Param(id = 1) int i2, @SafeParcelable.Param(id = 2) int i3, @SafeParcelable.Param(id = 7) boolean z2) {
        Preconditions.checkArgument(isValidQualityLevel(i2, false));
        Preconditions.checkArgument(isValidCaptureMode(i3, false));
        this.zzrz = i2;
        this.zzrq = i3;
        this.zzsa = z2;
    }

    public static boolean isValidCaptureMode(int i2, boolean z2) {
        if (i2 != -1) {
            if (i2 == 0) {
                return true;
            }
            if (i2 != 1) {
                return false;
            }
        }
        return z2;
    }

    public static boolean isValidQualityLevel(int i2, boolean z2) {
        if (i2 != -1) {
            z2 = true;
            if (i2 != 0 && i2 != 1 && i2 != 2 && i2 != 3) {
                return false;
            }
        }
        return z2;
    }

    public final int getCaptureMode() {
        return this.zzrq;
    }

    public final int getQualityLevel() {
        return this.zzrz;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, getQualityLevel());
        SafeParcelWriter.writeInt(parcel, 2, getCaptureMode());
        SafeParcelWriter.writeBoolean(parcel, 7, this.zzsa);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}
