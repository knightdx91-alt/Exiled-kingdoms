package com.google.android.gms.internal.drive;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.FileUploadPreferences;
import com.google.android.gms.drive.TransferPreferences;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@SafeParcelable.Class(creator = "FileUploadPreferencesImplCreator")
@SafeParcelable.Reserved({1})
@Deprecated
public final class zzei extends AbstractSafeParcelable implements FileUploadPreferences {
    public static final Parcelable.Creator<zzei> CREATOR = new zzej();

    @SafeParcelable.Field(id = 3)
    private int zzbl;

    @SafeParcelable.Field(id = 2)
    private int zzgw;

    @SafeParcelable.Field(id = 4)
    private boolean zzgx;

    @SafeParcelable.Constructor
    public zzei(@SafeParcelable.Param(id = 2) int i2, @SafeParcelable.Param(id = 3) int i3, @SafeParcelable.Param(id = 4) boolean z2) {
        this.zzgw = i2;
        this.zzbl = i3;
        this.zzgx = z2;
    }

    private static boolean zzh(int i2) {
        return i2 == 1 || i2 == 2;
    }

    private static boolean zzi(int i2) {
        return i2 == 256 || i2 == 257;
    }

    @Override // com.google.android.gms.drive.FileUploadPreferences
    public final int getBatteryUsagePreference() {
        if (zzi(this.zzbl)) {
            return this.zzbl;
        }
        return 0;
    }

    @Override // com.google.android.gms.drive.FileUploadPreferences
    public final int getNetworkTypePreference() {
        if (zzh(this.zzgw)) {
            return this.zzgw;
        }
        return 0;
    }

    @Override // com.google.android.gms.drive.FileUploadPreferences
    public final boolean isRoamingAllowed() {
        return this.zzgx;
    }

    @Override // com.google.android.gms.drive.FileUploadPreferences
    public final void setBatteryUsagePreference(int i2) {
        if (!zzi(i2)) {
            throw new IllegalArgumentException("Invalid battery usage preference value.");
        }
        this.zzbl = i2;
    }

    @Override // com.google.android.gms.drive.FileUploadPreferences
    public final void setNetworkTypePreference(int i2) {
        if (!zzh(i2)) {
            throw new IllegalArgumentException("Invalid data connection preference value.");
        }
        this.zzgw = i2;
    }

    @Override // com.google.android.gms.drive.FileUploadPreferences
    public final void setRoamingAllowed(boolean z2) {
        this.zzgx = z2;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 2, this.zzgw);
        SafeParcelWriter.writeInt(parcel, 3, this.zzbl);
        SafeParcelWriter.writeBoolean(parcel, 4, this.zzgx);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    public zzei(TransferPreferences transferPreferences) {
        this(transferPreferences.getNetworkPreference(), transferPreferences.getBatteryUsagePreference(), transferPreferences.isRoamingAllowed());
    }
}
