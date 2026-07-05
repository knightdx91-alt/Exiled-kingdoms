package com.google.android.gms.internal.p001authapiphone;

import android.os.Parcel;
import com.google.android.gms.common.api.Status;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public abstract class zzh extends zzb implements zzg {
    public zzh() {
        super("com.google.android.gms.auth.api.phone.internal.ISmsRetrieverResultCallback");
    }

    @Override // com.google.android.gms.internal.p001authapiphone.zzb
    protected final boolean dispatchTransaction(int i2, Parcel parcel, Parcel parcel2, int i3) {
        if (i2 != 1) {
            return false;
        }
        zza((Status) zzc.zza(parcel, Status.CREATOR));
        return true;
    }
}
