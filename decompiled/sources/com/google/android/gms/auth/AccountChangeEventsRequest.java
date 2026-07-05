package com.google.android.gms.auth;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@SafeParcelable.Class(creator = "AccountChangeEventsRequestCreator")
public class AccountChangeEventsRequest extends AbstractSafeParcelable {
    public static final Parcelable.Creator<AccountChangeEventsRequest> CREATOR = new zzb();

    @SafeParcelable.VersionField(id = 1)
    private final int zze;

    @SafeParcelable.Field(id = 3)
    @Deprecated
    private String zzg;

    @SafeParcelable.Field(id = 2)
    private int zzi;

    @SafeParcelable.Field(id = 4)
    private Account zzk;

    @SafeParcelable.Constructor
    AccountChangeEventsRequest(@SafeParcelable.Param(id = 1) int i2, @SafeParcelable.Param(id = 2) int i3, @SafeParcelable.Param(id = 3) String str, @SafeParcelable.Param(id = 4) Account account) {
        this.zze = i2;
        this.zzi = i3;
        this.zzg = str;
        if (account != null || TextUtils.isEmpty(str)) {
            this.zzk = account;
        } else {
            this.zzk = new Account(str, "com.google");
        }
    }

    public Account getAccount() {
        return this.zzk;
    }

    @Deprecated
    public String getAccountName() {
        return this.zzg;
    }

    public int getEventIndex() {
        return this.zzi;
    }

    public AccountChangeEventsRequest setAccount(Account account) {
        this.zzk = account;
        return this;
    }

    @Deprecated
    public AccountChangeEventsRequest setAccountName(String str) {
        this.zzg = str;
        return this;
    }

    public AccountChangeEventsRequest setEventIndex(int i2) {
        this.zzi = i2;
        return this;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zze);
        SafeParcelWriter.writeInt(parcel, 2, this.zzi);
        SafeParcelWriter.writeString(parcel, 3, this.zzg, false);
        SafeParcelWriter.writeParcelable(parcel, 4, this.zzk, i2, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    public AccountChangeEventsRequest() {
        this.zze = 1;
    }
}
