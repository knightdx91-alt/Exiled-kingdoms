package com.google.android.gms.games.video;

import android.os.Bundle;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class CaptureState {
    private final boolean zzrp;
    private final int zzrq;
    private final int zzrr;
    private final boolean zzrs;
    private final boolean zzrt;

    private CaptureState(boolean z2, int i2, int i3, boolean z3, boolean z4) {
        Preconditions.checkArgument(VideoConfiguration.isValidCaptureMode(i2, true));
        Preconditions.checkArgument(VideoConfiguration.isValidQualityLevel(i3, true));
        this.zzrp = z2;
        this.zzrq = i2;
        this.zzrr = i3;
        this.zzrs = z3;
        this.zzrt = z4;
    }

    public static CaptureState zzb(Bundle bundle) {
        if (bundle == null || bundle.get("IsCapturing") == null) {
            return null;
        }
        return new CaptureState(bundle.getBoolean("IsCapturing", false), bundle.getInt("CaptureMode", -1), bundle.getInt("CaptureQuality", -1), bundle.getBoolean("IsOverlayVisible", false), bundle.getBoolean("IsPaused", false));
    }

    public final int getCaptureMode() {
        return this.zzrq;
    }

    public final int getCaptureQuality() {
        return this.zzrr;
    }

    public final boolean isCapturing() {
        return this.zzrp;
    }

    public final boolean isOverlayVisible() {
        return this.zzrs;
    }

    public final boolean isPaused() {
        return this.zzrt;
    }

    public final String toString() {
        return Objects.toStringHelper(this).add("IsCapturing", Boolean.valueOf(this.zzrp)).add("CaptureMode", Integer.valueOf(this.zzrq)).add("CaptureQuality", Integer.valueOf(this.zzrr)).add("IsOverlayVisible", Boolean.valueOf(this.zzrs)).add("IsPaused", Boolean.valueOf(this.zzrt)).toString();
    }
}
