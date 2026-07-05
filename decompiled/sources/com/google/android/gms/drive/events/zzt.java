package com.google.android.gms.drive.events;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@SafeParcelable.Class(creator = "TransferProgressOptionsCreator")
@SafeParcelable.Reserved({1})
public final class zzt extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzt> CREATOR = new zzu();

    @SafeParcelable.Field(id = 2)
    private final int zzcr;

    @SafeParcelable.Constructor
    public zzt(@SafeParcelable.Param(id = 2) int i2) {
        this.zzcr = i2;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 2, this.zzcr);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}
