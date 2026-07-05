package com.google.android.gms.internal.drive;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@SafeParcelable.Class(creator = "DriveServiceResponseCreator")
@SafeParcelable.Reserved({1})
public final class zzec extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzec> CREATOR = new zzed();

    @SafeParcelable.Field(id = 2)
    final IBinder zzgq;

    @SafeParcelable.Constructor
    zzec(@SafeParcelable.Param(id = 2) IBinder iBinder) {
        this.zzgq = iBinder;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeIBinder(parcel, 2, this.zzgq, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}
