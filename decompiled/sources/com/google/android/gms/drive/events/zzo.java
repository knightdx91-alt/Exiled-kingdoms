package com.google.android.gms.drive.events;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@SafeParcelable.Class(creator = "QueryResultEventParcelableCreator")
@SafeParcelable.Reserved({1})
public final class zzo extends com.google.android.gms.drive.zzu implements DriveEvent {
    public static final Parcelable.Creator<zzo> CREATOR = new zzp();

    @SafeParcelable.Field(id = 2)
    private final DataHolder zzat;

    @SafeParcelable.Field(id = 3)
    private final boolean zzco;

    @SafeParcelable.Field(id = 4)
    private final int zzcp;

    @SafeParcelable.Constructor
    public zzo(@SafeParcelable.Param(id = 2) DataHolder dataHolder, @SafeParcelable.Param(id = 3) boolean z2, @SafeParcelable.Param(id = 4) int i2) {
        this.zzat = dataHolder;
        this.zzco = z2;
        this.zzcp = i2;
    }

    @Override // com.google.android.gms.drive.events.DriveEvent
    public final int getType() {
        return 3;
    }

    @Override // com.google.android.gms.drive.zzu
    public final void zza(Parcel parcel, int i2) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzat, i2, false);
        SafeParcelWriter.writeBoolean(parcel, 3, this.zzco);
        SafeParcelWriter.writeInt(parcel, 4, this.zzcp);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    public final int zzaa() {
        return this.zzcp;
    }

    public final DataHolder zzy() {
        return this.zzat;
    }

    public final boolean zzz() {
        return this.zzco;
    }
}
