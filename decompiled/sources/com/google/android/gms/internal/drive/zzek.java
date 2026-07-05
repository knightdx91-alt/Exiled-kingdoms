package com.google.android.gms.internal.drive;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.drive.DriveId;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@SafeParcelable.Class(creator = "GetMetadataRequestCreator")
@SafeParcelable.Reserved({1})
public final class zzek extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzek> CREATOR = new zzel();

    @SafeParcelable.Field(id = 2)
    private final DriveId zzdb;

    @SafeParcelable.Field(id = 3)
    private final boolean zzgy;

    @SafeParcelable.Constructor
    @VisibleForTesting
    public zzek(@SafeParcelable.Param(id = 2) DriveId driveId, @SafeParcelable.Param(id = 3) boolean z2) {
        this.zzdb = driveId;
        this.zzgy = z2;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzdb, i2, false);
        SafeParcelWriter.writeBoolean(parcel, 3, this.zzgy);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}
