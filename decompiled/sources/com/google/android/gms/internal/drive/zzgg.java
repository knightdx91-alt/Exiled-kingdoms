package com.google.android.gms.internal.drive;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.query.internal.FilterHolder;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@SafeParcelable.Class(creator = "OpenFileIntentSenderRequestCreator")
@SafeParcelable.Reserved({1})
public final class zzgg extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzgg> CREATOR = new zzgh();

    @SafeParcelable.Field(id = 2)
    private final String zzay;

    @SafeParcelable.Field(id = 3)
    private final String[] zzaz;

    @SafeParcelable.Field(id = 4)
    private final DriveId zzbb;

    @SafeParcelable.Field(id = 5)
    private final FilterHolder zzbc;

    @SafeParcelable.Constructor
    @VisibleForTesting
    public zzgg(@SafeParcelable.Param(id = 2) String str, @SafeParcelable.Param(id = 3) String[] strArr, @SafeParcelable.Param(id = 4) DriveId driveId, @SafeParcelable.Param(id = 5) FilterHolder filterHolder) {
        this.zzay = str;
        this.zzaz = strArr;
        this.zzbb = driveId;
        this.zzbc = filterHolder;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.zzay, false);
        SafeParcelWriter.writeStringArray(parcel, 3, this.zzaz, false);
        SafeParcelWriter.writeParcelable(parcel, 4, this.zzbb, i2, false);
        SafeParcelWriter.writeParcelable(parcel, 5, this.zzbc, i2, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}
