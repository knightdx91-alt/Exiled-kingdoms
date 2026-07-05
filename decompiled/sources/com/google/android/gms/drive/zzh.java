package com.google.android.gms.drive;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@SafeParcelable.Class(creator = "DriveFileRangeCreator")
@SafeParcelable.Reserved({1})
public final class zzh extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzh> CREATOR = new zzi();

    @SafeParcelable.Field(id = 3)
    private final long zzaa;

    @SafeParcelable.Field(id = 2)
    private final long zzz;

    @SafeParcelable.Constructor
    public zzh(@SafeParcelable.Param(id = 2) long j2, @SafeParcelable.Param(id = 3) long j3) {
        this.zzz = j2;
        this.zzaa = j3;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeLong(parcel, 2, this.zzz);
        SafeParcelWriter.writeLong(parcel, 3, this.zzaa);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}
