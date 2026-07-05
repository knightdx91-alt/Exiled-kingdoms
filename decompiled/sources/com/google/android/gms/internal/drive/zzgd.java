package com.google.android.gms.internal.drive;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.drive.DriveId;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@SafeParcelable.Class(creator = "OpenContentsRequestCreator")
@SafeParcelable.Reserved({1})
public final class zzgd extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzgd> CREATOR = new zzge();

    @SafeParcelable.Field(id = 3)
    private final int mode;

    @SafeParcelable.Field(id = 2)
    private final DriveId zzdb;

    @SafeParcelable.Field(id = 4)
    private final int zzhz;

    @SafeParcelable.Constructor
    @VisibleForTesting
    public zzgd(@SafeParcelable.Param(id = 2) DriveId driveId, @SafeParcelable.Param(id = 3) int i2, @SafeParcelable.Param(id = 4) int i3) {
        this.zzdb = driveId;
        this.mode = i2;
        this.zzhz = i3;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzdb, i2, false);
        SafeParcelWriter.writeInt(parcel, 3, this.mode);
        SafeParcelWriter.writeInt(parcel, 4, this.zzhz);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}
