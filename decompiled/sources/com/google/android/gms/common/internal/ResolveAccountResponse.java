package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.IAccountAccessor;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@SafeParcelable.Class(creator = "ResolveAccountResponseCreator")
public class ResolveAccountResponse extends AbstractSafeParcelable {
    public static final Parcelable.Creator<ResolveAccountResponse> CREATOR = new zan();

    @SafeParcelable.Field(getter = "getConnectionResult", id = 3)
    private ConnectionResult zadh;

    @SafeParcelable.Field(getter = "getSaveDefaultAccount", id = 4)
    private boolean zagf;

    @SafeParcelable.VersionField(id = 1)
    private final int zale;

    @SafeParcelable.Field(id = 2)
    private IBinder zanw;

    @SafeParcelable.Field(getter = "isFromCrossClientAuth", id = 5)
    private boolean zapb;

    @SafeParcelable.Constructor
    ResolveAccountResponse(@SafeParcelable.Param(id = 1) int i2, @SafeParcelable.Param(id = 2) IBinder iBinder, @SafeParcelable.Param(id = 3) ConnectionResult connectionResult, @SafeParcelable.Param(id = 4) boolean z2, @SafeParcelable.Param(id = 5) boolean z3) {
        this.zale = i2;
        this.zanw = iBinder;
        this.zadh = connectionResult;
        this.zagf = z2;
        this.zapb = z3;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ResolveAccountResponse)) {
            return false;
        }
        ResolveAccountResponse resolveAccountResponse = (ResolveAccountResponse) obj;
        return this.zadh.equals(resolveAccountResponse.zadh) && getAccountAccessor().equals(resolveAccountResponse.getAccountAccessor());
    }

    public IAccountAccessor getAccountAccessor() {
        return IAccountAccessor.Stub.asInterface(this.zanw);
    }

    public ConnectionResult getConnectionResult() {
        return this.zadh;
    }

    public boolean getSaveDefaultAccount() {
        return this.zagf;
    }

    public boolean isFromCrossClientAuth() {
        return this.zapb;
    }

    public ResolveAccountResponse setAccountAccessor(IAccountAccessor iAccountAccessor) {
        this.zanw = iAccountAccessor == null ? null : iAccountAccessor.asBinder();
        return this;
    }

    public ResolveAccountResponse setIsFromCrossClientAuth(boolean z2) {
        this.zapb = z2;
        return this;
    }

    public ResolveAccountResponse setSaveDefaultAccount(boolean z2) {
        this.zagf = z2;
        return this;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zale);
        SafeParcelWriter.writeIBinder(parcel, 2, this.zanw, false);
        SafeParcelWriter.writeParcelable(parcel, 3, getConnectionResult(), i2, false);
        SafeParcelWriter.writeBoolean(parcel, 4, getSaveDefaultAccount());
        SafeParcelWriter.writeBoolean(parcel, 5, isFromCrossClientAuth());
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    public ResolveAccountResponse(ConnectionResult connectionResult) {
        this(1, null, connectionResult, false, false);
    }

    public ResolveAccountResponse(int i2) {
        this(new ConnectionResult(i2, null));
    }
}
