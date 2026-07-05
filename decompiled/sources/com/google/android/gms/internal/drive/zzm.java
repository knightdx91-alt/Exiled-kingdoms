package com.google.android.gms.internal.drive;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.drive.Contents;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@SafeParcelable.Class(creator = "CloseContentsAndUpdateMetadataRequestCreator")
@SafeParcelable.Reserved({1})
public final class zzm extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzm> CREATOR = new zzn();

    @SafeParcelable.Field(id = 6)
    private final String zzal;

    @SafeParcelable.Field(id = 5)
    private final boolean zzam;

    @SafeParcelable.Field(defaultValue = "true", id = 10)
    private final boolean zzar;

    @SafeParcelable.Field(id = 2)
    private final DriveId zzdb;

    @SafeParcelable.Field(id = 3)
    private final MetadataBundle zzdc;

    @SafeParcelable.Field(id = 4)
    private final Contents zzdd;

    @SafeParcelable.Field(id = 7)
    private final int zzde;

    @SafeParcelable.Field(id = 8)
    private final int zzdf;

    @SafeParcelable.Field(id = 9)
    private final boolean zzdg;

    @VisibleForTesting
    public zzm(DriveId driveId, MetadataBundle metadataBundle, int i2, boolean z2, com.google.android.gms.drive.zzn zznVar) {
        this(driveId, metadataBundle, null, zznVar.zzl(), zznVar.zzk(), zznVar.zzm(), i2, z2, zznVar.zzo());
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzdb, i2, false);
        SafeParcelWriter.writeParcelable(parcel, 3, this.zzdc, i2, false);
        SafeParcelWriter.writeParcelable(parcel, 4, this.zzdd, i2, false);
        SafeParcelWriter.writeBoolean(parcel, 5, this.zzam);
        SafeParcelWriter.writeString(parcel, 6, this.zzal, false);
        SafeParcelWriter.writeInt(parcel, 7, this.zzde);
        SafeParcelWriter.writeInt(parcel, 8, this.zzdf);
        SafeParcelWriter.writeBoolean(parcel, 9, this.zzdg);
        SafeParcelWriter.writeBoolean(parcel, 10, this.zzar);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    @SafeParcelable.Constructor
    zzm(@SafeParcelable.Param(id = 2) DriveId driveId, @SafeParcelable.Param(id = 3) MetadataBundle metadataBundle, @SafeParcelable.Param(id = 4) Contents contents, @SafeParcelable.Param(id = 5) boolean z2, @SafeParcelable.Param(id = 6) String str, @SafeParcelable.Param(id = 7) int i2, @SafeParcelable.Param(id = 8) int i3, @SafeParcelable.Param(id = 9) boolean z3, @SafeParcelable.Param(id = 10) boolean z4) {
        this.zzdb = driveId;
        this.zzdc = metadataBundle;
        this.zzdd = contents;
        this.zzam = z2;
        this.zzal = str;
        this.zzde = i2;
        this.zzdf = i3;
        this.zzdg = z3;
        this.zzar = z4;
    }
}
