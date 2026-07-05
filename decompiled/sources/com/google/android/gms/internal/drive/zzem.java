package com.google.android.gms.internal.drive;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.List;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@SafeParcelable.Class(creator = "GetPermissionsResponseCreator")
@SafeParcelable.Reserved({1})
public final class zzem extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzem> CREATOR = new zzen();

    @SafeParcelable.Field(id = 3)
    private final int responseCode;

    @SafeParcelable.Field(id = 2)
    private final List<com.google.android.gms.drive.zzr> zzgz;

    @SafeParcelable.Constructor
    public zzem(@SafeParcelable.Param(id = 2) List<com.google.android.gms.drive.zzr> list, @SafeParcelable.Param(id = 3) int i2) {
        this.zzgz = list;
        this.responseCode = i2;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedList(parcel, 2, this.zzgz, false);
        SafeParcelWriter.writeInt(parcel, 3, this.responseCode);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}
