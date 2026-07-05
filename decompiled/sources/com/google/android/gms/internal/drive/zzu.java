package com.google.android.gms.internal.drive;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@SafeParcelable.Class(creator = "CreateFileIntentSenderRequestCreator")
@SafeParcelable.Reserved({1})
public final class zzu extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzu> CREATOR = new zzv();

    @SafeParcelable.Field(id = 4)
    private final String zzay;

    @SafeParcelable.Field(id = 5)
    private final DriveId zzbb;

    @SafeParcelable.Field(id = 2)
    private final MetadataBundle zzdl;

    @SafeParcelable.Field(id = 6)
    private final Integer zzdm;

    @SafeParcelable.Field(id = 3)
    private final int zzj;

    @SafeParcelable.Constructor
    @VisibleForTesting
    public zzu(@SafeParcelable.Param(id = 2) MetadataBundle metadataBundle, @SafeParcelable.Param(id = 3) int i2, @SafeParcelable.Param(id = 4) String str, @SafeParcelable.Param(id = 5) DriveId driveId, @SafeParcelable.Param(id = 6) Integer num) {
        this.zzdl = metadataBundle;
        this.zzj = i2;
        this.zzay = str;
        this.zzbb = driveId;
        this.zzdm = num;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzdl, i2, false);
        SafeParcelWriter.writeInt(parcel, 3, this.zzj);
        SafeParcelWriter.writeString(parcel, 4, this.zzay, false);
        SafeParcelWriter.writeParcelable(parcel, 5, this.zzbb, i2, false);
        SafeParcelWriter.writeIntegerObject(parcel, 6, this.zzdm, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}
