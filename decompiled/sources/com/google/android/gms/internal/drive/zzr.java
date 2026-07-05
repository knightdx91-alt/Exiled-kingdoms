package com.google.android.gms.internal.drive;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@SafeParcelable.Class(creator = "CreateContentsRequestCreator")
@SafeParcelable.Reserved({1})
public final class zzr extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzr> CREATOR = new zzs();

    @SafeParcelable.Field(defaultValueUnchecked = "com.google.android.gms.drive.DriveFile.MODE_WRITE_ONLY", id = 2)
    private final int mode;

    @SafeParcelable.Constructor
    public zzr(@SafeParcelable.Param(id = 2) int i2) {
        Preconditions.checkArgument(i2 == 536870912 || i2 == 805306368, "Cannot create a new read-only contents!");
        this.mode = i2;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 2, this.mode);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}
