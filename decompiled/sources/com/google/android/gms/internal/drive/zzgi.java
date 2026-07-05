package com.google.android.gms.internal.drive;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.TransferPreferences;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@SafeParcelable.Class(creator = "ParcelableTransferPreferencesCreator")
@SafeParcelable.Reserved({1})
public final class zzgi extends AbstractSafeParcelable implements TransferPreferences {
    public static final Parcelable.Creator<zzgi> CREATOR = new zzgj();

    @SafeParcelable.Field(id = 4)
    private final boolean zzbk;

    @SafeParcelable.Field(id = 3)
    private final int zzbl;

    @SafeParcelable.Field(id = 2)
    private final int zzgw;

    @SafeParcelable.Constructor
    zzgi(@SafeParcelable.Param(id = 2) int i2, @SafeParcelable.Param(id = 3) int i3, @SafeParcelable.Param(id = 4) boolean z2) {
        this.zzgw = i2;
        this.zzbl = i3;
        this.zzbk = z2;
    }

    @Override // com.google.android.gms.drive.TransferPreferences
    public final int getBatteryUsagePreference() {
        return this.zzbl;
    }

    @Override // com.google.android.gms.drive.TransferPreferences
    public final int getNetworkPreference() {
        return this.zzgw;
    }

    @Override // com.google.android.gms.drive.TransferPreferences
    public final boolean isRoamingAllowed() {
        return this.zzbk;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 2, this.zzgw);
        SafeParcelWriter.writeInt(parcel, 3, this.zzbl);
        SafeParcelWriter.writeBoolean(parcel, 4, this.zzbk);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}
