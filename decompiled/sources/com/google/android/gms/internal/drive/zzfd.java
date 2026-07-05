package com.google.android.gms.internal.drive;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@SafeParcelable.Class(creator = "OnDeviceUsagePreferenceResponseCreator")
@SafeParcelable.Reserved({1})
public final class zzfd extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzfd> CREATOR = new zzfe();

    @SafeParcelable.Field(id = 2)
    final zzei zzhg;

    @SafeParcelable.Constructor
    public zzfd(@SafeParcelable.Param(id = 2) zzei zzeiVar) {
        this.zzhg = zzeiVar;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzhg, i2, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    public final zzei zzaj() {
        return this.zzhg;
    }
}
