package com.google.android.gms.internal.drive;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.drive.query.Query;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@SafeParcelable.Class(creator = "QueryRequestCreator")
@SafeParcelable.Reserved({1})
public final class zzgk extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzgk> CREATOR = new zzgl();

    @SafeParcelable.Field(id = 2)
    private final Query zzib;

    @SafeParcelable.Constructor
    @VisibleForTesting
    public zzgk(@SafeParcelable.Param(id = 2) Query query) {
        this.zzib = query;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzib, i2, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}
