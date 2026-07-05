package com.google.android.gms.signin.internal;

import android.os.Parcel;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Status;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public abstract class zae extends com.google.android.gms.internal.base.zab implements zad {
    public zae() {
        super("com.google.android.gms.signin.internal.ISignInCallbacks");
    }

    @Override // com.google.android.gms.internal.base.zab
    protected boolean dispatchTransaction(int i2, Parcel parcel, Parcel parcel2, int i3) {
        if (i2 == 3) {
            zaa((ConnectionResult) com.google.android.gms.internal.base.zac.zaa(parcel, ConnectionResult.CREATOR), (zaa) com.google.android.gms.internal.base.zac.zaa(parcel, zaa.CREATOR));
        } else if (i2 == 4) {
            zag((Status) com.google.android.gms.internal.base.zac.zaa(parcel, Status.CREATOR));
        } else if (i2 == 6) {
            zah((Status) com.google.android.gms.internal.base.zac.zaa(parcel, Status.CREATOR));
        } else if (i2 == 7) {
            zaa((Status) com.google.android.gms.internal.base.zac.zaa(parcel, Status.CREATOR), (GoogleSignInAccount) com.google.android.gms.internal.base.zac.zaa(parcel, GoogleSignInAccount.CREATOR));
        } else {
            if (i2 != 8) {
                return false;
            }
            zab((zaj) com.google.android.gms.internal.base.zac.zaa(parcel, zaj.CREATOR));
        }
        parcel2.writeNoException();
        return true;
    }
}
