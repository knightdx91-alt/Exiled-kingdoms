package com.google.android.gms.internal.drive;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.DriveId;
import java.util.List;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@SafeParcelable.Class(creator = "OnChangesResponseCreator")
@SafeParcelable.Reserved({1})
public final class zzez extends com.google.android.gms.drive.zzu {
    public static final Parcelable.Creator<zzez> CREATOR = new zzfa();

    @SafeParcelable.Field(id = 2)
    private final DataHolder zzhb;

    @SafeParcelable.Field(id = 3)
    private final List<DriveId> zzhc;

    @SafeParcelable.Field(id = 4)
    private final com.google.android.gms.drive.zza zzhd;

    @SafeParcelable.Field(id = 5)
    private final boolean zzhe;

    @SafeParcelable.Constructor
    public zzez(@SafeParcelable.Param(id = 2) DataHolder dataHolder, @SafeParcelable.Param(id = 3) List<DriveId> list, @SafeParcelable.Param(id = 4) com.google.android.gms.drive.zza zzaVar, @SafeParcelable.Param(id = 5) boolean z2) {
        this.zzhb = dataHolder;
        this.zzhc = list;
        this.zzhd = zzaVar;
        this.zzhe = z2;
    }

    @Override // com.google.android.gms.drive.zzu
    protected final void zza(Parcel parcel, int i2) {
        int i3 = i2 | 1;
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzhb, i3, false);
        SafeParcelWriter.writeTypedList(parcel, 3, this.zzhc, false);
        SafeParcelWriter.writeParcelable(parcel, 4, this.zzhd, i3, false);
        SafeParcelWriter.writeBoolean(parcel, 5, this.zzhe);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}
