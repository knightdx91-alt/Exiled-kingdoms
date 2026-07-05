package com.google.android.gms.internal.drive;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.drive.DriveId;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@SafeParcelable.Class(creator = "RemoveEventListenerRequestCreator")
@SafeParcelable.Reserved({1})
public final class zzgm extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzgm> CREATOR = new zzgn();

    @SafeParcelable.Field(id = 3)
    private final int zzcy;

    @SafeParcelable.Field(id = 4)
    private final com.google.android.gms.drive.events.zzt zzda;

    @SafeParcelable.Field(id = 2)
    private final DriveId zzk;

    @VisibleForTesting
    public zzgm(DriveId driveId, int i2) {
        this(driveId, i2, null);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzk, i2, false);
        SafeParcelWriter.writeInt(parcel, 3, this.zzcy);
        SafeParcelWriter.writeParcelable(parcel, 4, this.zzda, i2, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    @SafeParcelable.Constructor
    zzgm(@SafeParcelable.Param(id = 2) DriveId driveId, @SafeParcelable.Param(id = 3) int i2, @SafeParcelable.Param(id = 4) com.google.android.gms.drive.events.zzt zztVar) {
        this.zzk = driveId;
        this.zzcy = i2;
        this.zzda = zztVar;
    }
}
