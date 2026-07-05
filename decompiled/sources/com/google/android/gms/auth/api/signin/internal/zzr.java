package com.google.android.gms.auth.api.signin.internal;

import android.os.Parcel;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public abstract class zzr extends com.google.android.gms.internal.p000authapi.zzd implements zzq {
    public zzr() {
        super("com.google.android.gms.auth.api.signin.internal.IRevocationService");
    }

    @Override // com.google.android.gms.internal.p000authapi.zzd
    protected final boolean dispatchTransaction(int i2, Parcel parcel, Parcel parcel2, int i3) {
        if (i2 == 1) {
            zzj();
        } else {
            if (i2 != 2) {
                return false;
            }
            zzk();
        }
        return true;
    }
}
