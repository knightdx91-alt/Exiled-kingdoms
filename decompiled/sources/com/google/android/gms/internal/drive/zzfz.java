package com.google.android.gms.internal.drive;

import android.os.IBinder;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@SafeParcelable.Class(creator = "OnStartStreamSessionCreator")
@SafeParcelable.Reserved({1})
public final class zzfz extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzfz> CREATOR = new zzga();

    @SafeParcelable.Field(id = 2)
    private final ParcelFileDescriptor zzhx;

    @SafeParcelable.Field(id = 3)
    private final IBinder zzhy;

    @SafeParcelable.Field(id = 4)
    private final String zzm;

    @SafeParcelable.Constructor
    zzfz(@SafeParcelable.Param(id = 2) ParcelFileDescriptor parcelFileDescriptor, @SafeParcelable.Param(id = 3) IBinder iBinder, @SafeParcelable.Param(id = 4) String str) {
        this.zzhx = parcelFileDescriptor;
        this.zzhy = iBinder;
        this.zzm = str;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzhx, i2 | 1, false);
        SafeParcelWriter.writeIBinder(parcel, 3, this.zzhy, false);
        SafeParcelWriter.writeString(parcel, 4, this.zzm, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}
