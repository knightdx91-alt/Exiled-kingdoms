package com.google.android.gms.internal.drive;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.drive.Contents;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.ExecutionOptions;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@SafeParcelable.Class(creator = "CreateFileRequestCreator")
@SafeParcelable.Reserved({1, 10})
public final class zzw extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzw> CREATOR = new zzx();

    @SafeParcelable.Field(id = 7)
    private final String zzal;

    @SafeParcelable.Field(id = 4)
    private final Contents zzdd;

    @SafeParcelable.Field(id = 3)
    private final MetadataBundle zzdl;

    @SafeParcelable.Field(id = 5)
    private final Integer zzdm;

    @SafeParcelable.Field(id = 2)
    private final DriveId zzdn;

    @SafeParcelable.Field(id = 6)
    private final boolean zzdo;

    @SafeParcelable.Field(id = 8)
    private final int zzdp;

    @SafeParcelable.Field(id = 9)
    private final int zzdq;

    @VisibleForTesting
    public zzw(DriveId driveId, MetadataBundle metadataBundle, int i2, int i3, ExecutionOptions executionOptions) {
        this(driveId, metadataBundle, null, i3, executionOptions.zzl(), executionOptions.zzk(), executionOptions.zzm(), i2);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzdn, i2, false);
        SafeParcelWriter.writeParcelable(parcel, 3, this.zzdl, i2, false);
        SafeParcelWriter.writeParcelable(parcel, 4, this.zzdd, i2, false);
        SafeParcelWriter.writeIntegerObject(parcel, 5, this.zzdm, false);
        SafeParcelWriter.writeBoolean(parcel, 6, this.zzdo);
        SafeParcelWriter.writeString(parcel, 7, this.zzal, false);
        SafeParcelWriter.writeInt(parcel, 8, this.zzdp);
        SafeParcelWriter.writeInt(parcel, 9, this.zzdq);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    @SafeParcelable.Constructor
    zzw(@SafeParcelable.Param(id = 2) DriveId driveId, @SafeParcelable.Param(id = 3) MetadataBundle metadataBundle, @SafeParcelable.Param(id = 4) Contents contents, @SafeParcelable.Param(id = 5) int i2, @SafeParcelable.Param(id = 6) boolean z2, @SafeParcelable.Param(id = 7) String str, @SafeParcelable.Param(id = 8) int i3, @SafeParcelable.Param(id = 9) int i4) {
        if (contents != null && i4 != 0) {
            Preconditions.checkArgument(contents.getRequestId() == i4, "inconsistent contents reference");
        }
        if (i2 == 0 && contents == null && i4 == 0) {
            throw new IllegalArgumentException("Need a valid contents");
        }
        this.zzdn = (DriveId) Preconditions.checkNotNull(driveId);
        this.zzdl = (MetadataBundle) Preconditions.checkNotNull(metadataBundle);
        this.zzdd = contents;
        this.zzdm = Integer.valueOf(i2);
        this.zzal = str;
        this.zzdp = i3;
        this.zzdo = z2;
        this.zzdq = i4;
    }
}
