package com.google.android.gms.internal.drive;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.util.VisibleForTesting;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@SafeParcelable.Class(creator = "SetFileUploadPreferencesRequestCreator")
@SafeParcelable.Reserved({1})
public final class zzgo extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzgo> CREATOR = new zzgp();

    @SafeParcelable.Field(id = 2)
    private final zzei zzhg;

    @SafeParcelable.Constructor
    @VisibleForTesting
    public zzgo(@SafeParcelable.Param(id = 2) zzei zzeiVar) {
        this.zzhg = zzeiVar;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzhg, i2, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}
