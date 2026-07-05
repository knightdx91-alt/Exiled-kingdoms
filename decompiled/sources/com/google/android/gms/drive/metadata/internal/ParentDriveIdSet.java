package com.google.android.gms.drive.metadata.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@SafeParcelable.Class(creator = "ParentDriveIdSetCreator")
@SafeParcelable.Reserved({1})
public class ParentDriveIdSet extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Parcelable.Creator<ParentDriveIdSet> CREATOR = new zzn();

    @SafeParcelable.Field(id = 2)
    final List<zzq> zzit;

    public ParentDriveIdSet() {
        this(new ArrayList());
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedList(parcel, 2, this.zzit, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    @SafeParcelable.Constructor
    ParentDriveIdSet(@SafeParcelable.Param(id = 2) List<zzq> list) {
        this.zzit = list;
    }
}
