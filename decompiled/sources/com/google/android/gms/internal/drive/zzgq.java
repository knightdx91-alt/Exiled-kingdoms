package com.google.android.gms.internal.drive;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.drive.DriveId;
import java.util.List;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@SafeParcelable.Class(creator = "SetResourceParentsRequestCreator")
@SafeParcelable.Reserved({1})
public final class zzgq extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzgq> CREATOR = new zzgr();

    @SafeParcelable.Field(id = 2)
    private final DriveId zzic;

    @SafeParcelable.Field(id = 3)
    private final List<DriveId> zzid;

    @SafeParcelable.Constructor
    @VisibleForTesting
    public zzgq(@SafeParcelable.Param(id = 2) DriveId driveId, @SafeParcelable.Param(id = 3) List<DriveId> list) {
        this.zzic = driveId;
        this.zzid = list;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzic, i2, false);
        SafeParcelWriter.writeTypedList(parcel, 3, this.zzid, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}
