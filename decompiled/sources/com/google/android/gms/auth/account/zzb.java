package com.google.android.gms.auth.account;

import android.accounts.Account;
import android.os.Parcel;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public abstract class zzb extends com.google.android.gms.internal.auth.zzb implements zza {
    public zzb() {
        super("com.google.android.gms.auth.account.IWorkAccountCallback");
    }

    @Override // com.google.android.gms.internal.auth.zzb
    protected final boolean dispatchTransaction(int i2, Parcel parcel, Parcel parcel2, int i3) {
        if (i2 == 1) {
            zzc((Account) com.google.android.gms.internal.auth.zzc.zza(parcel, Account.CREATOR));
        } else {
            if (i2 != 2) {
                return false;
            }
            zza(com.google.android.gms.internal.auth.zzc.zza(parcel));
        }
        return true;
    }
}
