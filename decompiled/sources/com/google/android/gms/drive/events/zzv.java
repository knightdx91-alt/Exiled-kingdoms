package com.google.android.gms.drive.events;

import a.a;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.List;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@SafeParcelable.Class(creator = "TransferStateEventCreator")
@SafeParcelable.Reserved({1, 2})
public final class zzv extends AbstractSafeParcelable implements DriveEvent {
    public static final Parcelable.Creator<zzv> CREATOR = new zzw();

    @SafeParcelable.Field(id = 3)
    private final List<com.google.android.gms.internal.drive.zzh> zzcs;

    @SafeParcelable.Constructor
    public zzv(@SafeParcelable.Param(id = 3) List<com.google.android.gms.internal.drive.zzh> list) {
        this.zzcs = list;
    }

    public final boolean equals(Object obj) {
        if (obj == null || obj.getClass() != zzv.class) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        return Objects.equal(this.zzcs, ((zzv) obj).zzcs);
    }

    @Override // com.google.android.gms.drive.events.DriveEvent
    public final int getType() {
        return 7;
    }

    public final int hashCode() {
        return Objects.hashCode(this.zzcs);
    }

    public final String toString() {
        return a.l("TransferStateEvent[", TextUtils.join("','", this.zzcs), "]");
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedList(parcel, 3, this.zzcs, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}
