package com.google.android.gms.internal.drive;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.DriveId;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@SafeParcelable.Class(creator = "TransferProgressDataCreator")
@SafeParcelable.Reserved({1})
public final class zzh extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzh> CREATOR = new zzi();

    @SafeParcelable.Field(id = 4)
    final int status;

    @SafeParcelable.Field(id = 2)
    final int zzcr;

    @SafeParcelable.Field(id = 5)
    final long zzcu;

    @SafeParcelable.Field(id = 6)
    final long zzcv;

    @SafeParcelable.Field(id = 3)
    final DriveId zzk;

    @SafeParcelable.Constructor
    public zzh(@SafeParcelable.Param(id = 2) int i2, @SafeParcelable.Param(id = 3) DriveId driveId, @SafeParcelable.Param(id = 4) int i3, @SafeParcelable.Param(id = 5) long j2, @SafeParcelable.Param(id = 6) long j3) {
        this.zzcr = i2;
        this.zzk = driveId;
        this.status = i3;
        this.zzcu = j2;
        this.zzcv = j3;
    }

    public final boolean equals(Object obj) {
        if (obj != null && obj.getClass() == zzh.class) {
            if (obj == this) {
                return true;
            }
            zzh zzhVar = (zzh) obj;
            if (this.zzcr == zzhVar.zzcr && Objects.equal(this.zzk, zzhVar.zzk) && this.status == zzhVar.status && this.zzcu == zzhVar.zzcu && this.zzcv == zzhVar.zzcv) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.zzcr), this.zzk, Integer.valueOf(this.status), Long.valueOf(this.zzcu), Long.valueOf(this.zzcv));
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 2, this.zzcr);
        SafeParcelWriter.writeParcelable(parcel, 3, this.zzk, i2, false);
        SafeParcelWriter.writeInt(parcel, 4, this.status);
        SafeParcelWriter.writeLong(parcel, 5, this.zzcu);
        SafeParcelWriter.writeLong(parcel, 6, this.zzcv);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}
