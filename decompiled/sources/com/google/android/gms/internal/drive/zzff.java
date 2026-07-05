package com.google.android.gms.internal.drive;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@SafeParcelable.Class(creator = "OnDownloadProgressResponseCreator")
@SafeParcelable.Reserved({1})
public final class zzff extends AbstractSafeParcelable {

    @SafeParcelable.Field(id = 4)
    private final int status;

    @SafeParcelable.Field(id = 2)
    final long zzhi;

    @SafeParcelable.Field(id = 3)
    final long zzhj;

    @SafeParcelable.Field(id = 5)
    private final List<com.google.android.gms.drive.zzh> zzhk;
    private static final List<com.google.android.gms.drive.zzh> zzhh = Collections.emptyList();
    public static final Parcelable.Creator<zzff> CREATOR = new zzfg();

    @SafeParcelable.Constructor
    public zzff(@SafeParcelable.Param(id = 2) long j2, @SafeParcelable.Param(id = 3) long j3, @SafeParcelable.Param(id = 4) int i2, @SafeParcelable.Param(id = 5) List<com.google.android.gms.drive.zzh> list) {
        this.zzhi = j2;
        this.zzhj = j3;
        this.status = i2;
        this.zzhk = list;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeLong(parcel, 2, this.zzhi);
        SafeParcelWriter.writeLong(parcel, 3, this.zzhj);
        SafeParcelWriter.writeInt(parcel, 4, this.status);
        SafeParcelWriter.writeTypedList(parcel, 5, this.zzhk, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}
