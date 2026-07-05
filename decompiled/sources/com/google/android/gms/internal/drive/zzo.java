package com.google.android.gms.internal.drive;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.drive.Contents;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@SafeParcelable.Class(creator = "CloseContentsRequestCreator")
@SafeParcelable.Reserved({1})
public final class zzo extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzo> CREATOR = new zzp();

    @SafeParcelable.Field(id = 2)
    private final Contents zzdd;

    @SafeParcelable.Field(id = 4)
    private final int zzdf;

    @SafeParcelable.Field(id = 3)
    private final Boolean zzdh;

    @VisibleForTesting
    public zzo(int i2, boolean z2) {
        this(null, Boolean.FALSE, i2);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzdd, i2, false);
        SafeParcelWriter.writeBooleanObject(parcel, 3, this.zzdh, false);
        SafeParcelWriter.writeInt(parcel, 4, this.zzdf);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    @SafeParcelable.Constructor
    zzo(@SafeParcelable.Param(id = 2) Contents contents, @SafeParcelable.Param(id = 3) Boolean bool, @SafeParcelable.Param(id = 4) int i2) {
        this.zzdd = contents;
        this.zzdh = bool;
        this.zzdf = i2;
    }
}
