package com.google.android.gms.drive.events;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@SafeParcelable.Class(creator = "TransferProgressEventCreator")
@SafeParcelable.Reserved({1})
public final class zzr extends AbstractSafeParcelable implements DriveEvent {
    public static final Parcelable.Creator<zzr> CREATOR = new zzs();

    @SafeParcelable.Field(id = 2)
    private final com.google.android.gms.internal.drive.zzh zzcq;

    @SafeParcelable.Constructor
    public zzr(@SafeParcelable.Param(id = 2) com.google.android.gms.internal.drive.zzh zzhVar) {
        this.zzcq = zzhVar;
    }

    public final boolean equals(Object obj) {
        if (obj == null || obj.getClass() != zzr.class) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        return Objects.equal(this.zzcq, ((zzr) obj).zzcq);
    }

    @Override // com.google.android.gms.drive.events.DriveEvent
    public final int getType() {
        return 8;
    }

    public final int hashCode() {
        return Objects.hashCode(this.zzcq);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzcq, i2, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    public final com.google.android.gms.internal.drive.zzh zzab() {
        return this.zzcq;
    }
}
