package com.google.android.gms.games.internal;

import android.os.Parcel;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public abstract class zzx extends com.google.android.gms.internal.games.zzb implements zzw {
    public zzx() {
        super("com.google.android.gms.games.internal.IGamesClient");
    }

    @Override // com.google.android.gms.internal.games.zzb
    protected final boolean dispatchTransaction(int i2, Parcel parcel, Parcel parcel2, int i3) {
        if (i2 != 1001) {
            return false;
        }
        zzaa zzaaVarZzn = zzn();
        parcel2.writeNoException();
        com.google.android.gms.internal.games.zzc.zzb(parcel2, zzaaVarZzn);
        return true;
    }
}
