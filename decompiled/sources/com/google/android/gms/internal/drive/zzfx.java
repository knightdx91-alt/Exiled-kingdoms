package com.google.android.gms.internal.drive;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.List;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@SafeParcelable.Class(creator = "OnResourceIdSetResponseCreator")
@SafeParcelable.Reserved({1})
public final class zzfx extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzfx> CREATOR = new zzfy();

    @SafeParcelable.Field(getter = "getResourceIds", id = 2)
    private final List<String> zzhw;

    @SafeParcelable.Constructor
    zzfx(@SafeParcelable.Param(id = 2) List<String> list) {
        this.zzhw = list;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeStringList(parcel, 2, this.zzhw, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}
