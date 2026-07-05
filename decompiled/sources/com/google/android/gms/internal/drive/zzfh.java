package com.google.android.gms.internal.drive;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.DriveId;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@SafeParcelable.Class(creator = "OnDriveIdResponseCreator")
@SafeParcelable.Reserved({1})
public final class zzfh extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzfh> CREATOR = new zzfi();

    @SafeParcelable.Field(id = 2)
    DriveId zzdb;

    @SafeParcelable.Constructor
    public zzfh(@SafeParcelable.Param(id = 2) DriveId driveId) {
        this.zzdb = driveId;
    }

    public final DriveId getDriveId() {
        return this.zzdb;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzdb, i2, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}
