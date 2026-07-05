package com.google.android.gms.internal.drive;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@SafeParcelable.Class(creator = "OnRealtimeLoadedResponseCreator")
@SafeParcelable.Reserved({1})
public final class zzfr extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzfr> CREATOR = new zzfw();

    @SafeParcelable.Field(id = 2)
    private final boolean zzhu;

    @SafeParcelable.Constructor
    public zzfr(@SafeParcelable.Param(id = 2) boolean z2) {
        this.zzhu = z2;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeBoolean(parcel, 2, this.zzhu);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}
