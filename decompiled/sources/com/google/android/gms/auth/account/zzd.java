package com.google.android.gms.auth.account;

import android.os.IBinder;
import android.os.IInterface;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public abstract class zzd extends com.google.android.gms.internal.auth.zzb implements zzc {
    public static zzc zzc(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.auth.account.IWorkAccountService");
        return iInterfaceQueryLocalInterface instanceof zzc ? (zzc) iInterfaceQueryLocalInterface : new zze(iBinder);
    }
}
