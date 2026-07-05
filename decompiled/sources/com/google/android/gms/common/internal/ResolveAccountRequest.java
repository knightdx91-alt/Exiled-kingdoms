package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@SafeParcelable.Class(creator = "ResolveAccountRequestCreator")
public class ResolveAccountRequest extends AbstractSafeParcelable {
    public static final Parcelable.Creator<ResolveAccountRequest> CREATOR = new zam();

    @SafeParcelable.VersionField(id = 1)
    private final int zale;

    @SafeParcelable.Field(getter = "getSessionId", id = 3)
    private final int zaoz;

    @SafeParcelable.Field(getter = "getSignInAccountHint", id = 4)
    private final GoogleSignInAccount zapa;

    @SafeParcelable.Field(getter = "getAccount", id = 2)
    private final Account zax;

    @SafeParcelable.Constructor
    ResolveAccountRequest(@SafeParcelable.Param(id = 1) int i2, @SafeParcelable.Param(id = 2) Account account, @SafeParcelable.Param(id = 3) int i3, @SafeParcelable.Param(id = 4) GoogleSignInAccount googleSignInAccount) {
        this.zale = i2;
        this.zax = account;
        this.zaoz = i3;
        this.zapa = googleSignInAccount;
    }

    public Account getAccount() {
        return this.zax;
    }

    public int getSessionId() {
        return this.zaoz;
    }

    public GoogleSignInAccount getSignInAccountHint() {
        return this.zapa;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zale);
        SafeParcelWriter.writeParcelable(parcel, 2, getAccount(), i2, false);
        SafeParcelWriter.writeInt(parcel, 3, getSessionId());
        SafeParcelWriter.writeParcelable(parcel, 4, getSignInAccountHint(), i2, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    public ResolveAccountRequest(Account account, int i2, GoogleSignInAccount googleSignInAccount) {
        this(2, account, i2, googleSignInAccount);
    }
}
