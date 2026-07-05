package com.google.android.gms.signin.internal;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@SafeParcelable.Class(creator = "AuthAccountResultCreator")
public final class zaa extends AbstractSafeParcelable implements Result {
    public static final Parcelable.Creator<zaa> CREATOR = new zab();

    @SafeParcelable.VersionField(id = 1)
    private final int zale;

    @SafeParcelable.Field(getter = "getConnectionResultCode", id = 2)
    private int zarw;

    @SafeParcelable.Field(getter = "getRawAuthResolutionIntent", id = 3)
    private Intent zarx;

    @SafeParcelable.Constructor
    zaa(@SafeParcelable.Param(id = 1) int i2, @SafeParcelable.Param(id = 2) int i3, @SafeParcelable.Param(id = 3) Intent intent) {
        this.zale = i2;
        this.zarw = i3;
        this.zarx = intent;
    }

    @Override // com.google.android.gms.common.api.Result
    public final Status getStatus() {
        return this.zarw == 0 ? Status.RESULT_SUCCESS : Status.RESULT_CANCELED;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zale);
        SafeParcelWriter.writeInt(parcel, 2, this.zarw);
        SafeParcelWriter.writeParcelable(parcel, 3, this.zarx, i2, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    public zaa() {
        this(0, null);
    }

    private zaa(int i2, Intent intent) {
        this(2, 0, null);
    }
}
