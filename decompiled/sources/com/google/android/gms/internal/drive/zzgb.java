package com.google.android.gms.internal.drive;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@SafeParcelable.Class(creator = "OnSyncMoreResponseCreator")
@SafeParcelable.Reserved({1})
public final class zzgb extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzgb> CREATOR = new zzgc();

    @SafeParcelable.Field(id = 2)
    private final boolean zzdy;

    @SafeParcelable.Constructor
    public zzgb(@SafeParcelable.Param(id = 2) boolean z2) {
        this.zzdy = z2;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeBoolean(parcel, 2, this.zzdy);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}
