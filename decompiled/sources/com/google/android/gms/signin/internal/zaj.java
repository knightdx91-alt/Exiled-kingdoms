package com.google.android.gms.signin.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.ResolveAccountResponse;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@SafeParcelable.Class(creator = "SignInResponseCreator")
public final class zaj extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zaj> CREATOR = new zak();

    @SafeParcelable.Field(getter = "getConnectionResult", id = 2)
    private final ConnectionResult zadh;

    @SafeParcelable.VersionField(id = 1)
    private final int zale;

    @SafeParcelable.Field(getter = "getResolveAccountResponse", id = 3)
    private final ResolveAccountResponse zasb;

    @SafeParcelable.Constructor
    zaj(@SafeParcelable.Param(id = 1) int i2, @SafeParcelable.Param(id = 2) ConnectionResult connectionResult, @SafeParcelable.Param(id = 3) ResolveAccountResponse resolveAccountResponse) {
        this.zale = i2;
        this.zadh = connectionResult;
        this.zasb = resolveAccountResponse;
    }

    public final ConnectionResult getConnectionResult() {
        return this.zadh;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zale);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zadh, i2, false);
        SafeParcelWriter.writeParcelable(parcel, 3, this.zasb, i2, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    public final ResolveAccountResponse zacw() {
        return this.zasb;
    }

    public zaj(int i2) {
        this(new ConnectionResult(8, null), null);
    }

    private zaj(ConnectionResult connectionResult, ResolveAccountResponse resolveAccountResponse) {
        this(1, connectionResult, null);
    }
}
