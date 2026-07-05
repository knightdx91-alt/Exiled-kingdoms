package com.google.android.gms.internal.drive;

import a.a;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.events.ChangeEvent;
import com.google.android.gms.drive.events.CompletionEvent;
import com.google.android.gms.drive.events.DriveEvent;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@SafeParcelable.Class(creator = "OnEventResponseCreator")
@SafeParcelable.Reserved({1, 4, 8})
public final class zzfj extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzfj> CREATOR = new zzfk();

    @SafeParcelable.Field(id = 2)
    private final int zzcy;

    @SafeParcelable.Field(id = 3)
    private final ChangeEvent zzhl;

    @SafeParcelable.Field(id = 5)
    private final CompletionEvent zzhm;

    @SafeParcelable.Field(id = 6)
    private final com.google.android.gms.drive.events.zzo zzhn;

    @SafeParcelable.Field(id = 7)
    private final com.google.android.gms.drive.events.zzb zzho;

    @SafeParcelable.Field(id = 9)
    private final com.google.android.gms.drive.events.zzv zzhp;

    @SafeParcelable.Field(id = 10)
    private final com.google.android.gms.drive.events.zzr zzhq;

    @SafeParcelable.Constructor
    zzfj(@SafeParcelable.Param(id = 2) int i2, @SafeParcelable.Param(id = 3) ChangeEvent changeEvent, @SafeParcelable.Param(id = 5) CompletionEvent completionEvent, @SafeParcelable.Param(id = 6) com.google.android.gms.drive.events.zzo zzoVar, @SafeParcelable.Param(id = 7) com.google.android.gms.drive.events.zzb zzbVar, @SafeParcelable.Param(id = 9) com.google.android.gms.drive.events.zzv zzvVar, @SafeParcelable.Param(id = 10) com.google.android.gms.drive.events.zzr zzrVar) {
        this.zzcy = i2;
        this.zzhl = changeEvent;
        this.zzhm = completionEvent;
        this.zzhn = zzoVar;
        this.zzho = zzbVar;
        this.zzhp = zzvVar;
        this.zzhq = zzrVar;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 2, this.zzcy);
        SafeParcelWriter.writeParcelable(parcel, 3, this.zzhl, i2, false);
        SafeParcelWriter.writeParcelable(parcel, 5, this.zzhm, i2, false);
        SafeParcelWriter.writeParcelable(parcel, 6, this.zzhn, i2, false);
        SafeParcelWriter.writeParcelable(parcel, 7, this.zzho, i2, false);
        SafeParcelWriter.writeParcelable(parcel, 9, this.zzhp, i2, false);
        SafeParcelWriter.writeParcelable(parcel, 10, this.zzhq, i2, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    public final DriveEvent zzak() {
        int i2 = this.zzcy;
        if (i2 == 1) {
            return this.zzhl;
        }
        if (i2 == 2) {
            return this.zzhm;
        }
        if (i2 == 3) {
            return this.zzhn;
        }
        if (i2 == 4) {
            return this.zzho;
        }
        if (i2 == 7) {
            return this.zzhp;
        }
        if (i2 == 8) {
            return this.zzhq;
        }
        throw new IllegalStateException(a.g(33, this.zzcy, "Unexpected event type "));
    }
}
