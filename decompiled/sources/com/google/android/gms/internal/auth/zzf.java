package com.google.android.gms.internal.auth;

import android.os.IBinder;
import android.os.IInterface;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public abstract class zzf extends zzb implements zze {
    public static zze zza(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.auth.IAuthManagerService");
        return iInterfaceQueryLocalInterface instanceof zze ? (zze) iInterfaceQueryLocalInterface : new zzg(iBinder);
    }
}
