package com.google.android.gms.drive.events;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.DriveSpace;
import java.util.List;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@SafeParcelable.Class(creator = "ChangesAvailableOptionsCreator")
@SafeParcelable.Reserved({1})
public final class zze extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zze> CREATOR = new zzf();

    @SafeParcelable.Field(id = 2)
    private final int zzbu;

    @SafeParcelable.Field(id = 3)
    private final boolean zzbv;

    @SafeParcelable.Field(id = 4)
    private final List<DriveSpace> zzbw;

    @SafeParcelable.Constructor
    zze(@SafeParcelable.Param(id = 2) int i2, @SafeParcelable.Param(id = 3) boolean z2, @SafeParcelable.Param(id = 4) List<DriveSpace> list) {
        this.zzbu = i2;
        this.zzbv = z2;
        this.zzbw = list;
    }

    public final boolean equals(Object obj) {
        if (obj != null && obj.getClass() == zze.class) {
            if (obj == this) {
                return true;
            }
            zze zzeVar = (zze) obj;
            if (Objects.equal(this.zzbw, zzeVar.zzbw) && this.zzbu == zzeVar.zzbu && this.zzbv == zzeVar.zzbv) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hashCode(this.zzbw, Integer.valueOf(this.zzbu), Boolean.valueOf(this.zzbv));
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 2, this.zzbu);
        SafeParcelWriter.writeBoolean(parcel, 3, this.zzbv);
        SafeParcelWriter.writeTypedList(parcel, 4, this.zzbw, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}
