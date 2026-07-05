package com.google.android.gms.internal.auth;

import android.os.Parcel;
import com.google.android.gms.auth.api.proxy.ProxyResponse;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public abstract class zzam extends zzb implements zzal {
    public zzam() {
        super("com.google.android.gms.auth.api.internal.IAuthCallbacks");
    }

    @Override // com.google.android.gms.internal.auth.zzb
    protected final boolean dispatchTransaction(int i2, Parcel parcel, Parcel parcel2, int i3) {
        if (i2 == 1) {
            zza((ProxyResponse) zzc.zza(parcel, ProxyResponse.CREATOR));
        } else {
            if (i2 != 2) {
                return false;
            }
            zzb(parcel.readString());
        }
        parcel2.writeNoException();
        return true;
    }
}
