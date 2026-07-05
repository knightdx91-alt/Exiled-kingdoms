package com.google.android.gms.internal.p000authapi;

import android.os.Parcel;
import com.google.android.gms.auth.api.credentials.Credential;
import com.google.android.gms.common.api.Status;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public abstract class zzv extends zzd implements zzu {
    public zzv() {
        super("com.google.android.gms.auth.api.credentials.internal.ICredentialsCallbacks");
    }

    @Override // com.google.android.gms.internal.p000authapi.zzd
    protected final boolean dispatchTransaction(int i2, Parcel parcel, Parcel parcel2, int i3) {
        if (i2 == 1) {
            zzc((Status) zze.zzc(parcel, Status.CREATOR), (Credential) zze.zzc(parcel, Credential.CREATOR));
        } else if (i2 == 2) {
            zzc((Status) zze.zzc(parcel, Status.CREATOR));
        } else {
            if (i2 != 3) {
                return false;
            }
            zzc((Status) zze.zzc(parcel, Status.CREATOR), parcel.readString());
        }
        parcel2.writeNoException();
        return true;
    }
}
