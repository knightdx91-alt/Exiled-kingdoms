package com.google.android.gms.drive.events;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.DriveSpace;
import java.util.List;
import java.util.Locale;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@SafeParcelable.Class(creator = "TransferStateOptionsCreator")
@SafeParcelable.Reserved({1})
public final class zzx extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzx> CREATOR = new zzy();

    @SafeParcelable.Field(id = 2)
    private final List<DriveSpace> zzbw;

    @SafeParcelable.Constructor
    zzx(@SafeParcelable.Param(id = 2) List<DriveSpace> list) {
        this.zzbw = list;
    }

    public final boolean equals(Object obj) {
        if (obj == null || obj.getClass() != zzx.class) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        return Objects.equal(this.zzbw, ((zzx) obj).zzbw);
    }

    public final int hashCode() {
        return Objects.hashCode(this.zzbw);
    }

    public final String toString() {
        return String.format(Locale.US, "TransferStateOptions[Spaces=%s]", this.zzbw);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedList(parcel, 2, this.zzbw, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}
