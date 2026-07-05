package com.google.android.gms.signin.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.ResolveAccountRequest;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@SafeParcelable.Class(creator = "SignInRequestCreator")
public final class zah extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zah> CREATOR = new zai();

    @SafeParcelable.VersionField(id = 1)
    private final int zale;

    @SafeParcelable.Field(getter = "getResolveAccountRequest", id = 2)
    private final ResolveAccountRequest zasa;

    @SafeParcelable.Constructor
    zah(@SafeParcelable.Param(id = 1) int i2, @SafeParcelable.Param(id = 2) ResolveAccountRequest resolveAccountRequest) {
        this.zale = i2;
        this.zasa = resolveAccountRequest;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zale);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zasa, i2, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    public zah(ResolveAccountRequest resolveAccountRequest) {
        this(1, resolveAccountRequest);
    }
}
