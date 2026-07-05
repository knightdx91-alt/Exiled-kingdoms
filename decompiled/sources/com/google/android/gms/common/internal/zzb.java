package com.google.android.gms.common.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@SafeParcelable.Class(creator = "ConnectionInfoCreator")
public final class zzb extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzb> CREATOR = new zzc();

    @SafeParcelable.Field(id = 1)
    Bundle zzcz;

    @SafeParcelable.Field(id = 2)
    Feature[] zzda;

    @SafeParcelable.Constructor
    zzb(@SafeParcelable.Param(id = 1) Bundle bundle, @SafeParcelable.Param(id = 2) Feature[] featureArr) {
        this.zzcz = bundle;
        this.zzda = featureArr;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeBundle(parcel, 1, this.zzcz, false);
        SafeParcelWriter.writeTypedArray(parcel, 2, this.zzda, i2, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    public zzb() {
    }
}
