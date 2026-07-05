package com.google.android.gms.internal.drive;

import android.os.IBinder;
import android.os.IInterface;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class zzim extends zzb implements zzil {
    public static zzil zzb(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.drive.realtime.internal.IRealtimeService");
        return iInterfaceQueryLocalInterface instanceof zzil ? (zzil) iInterfaceQueryLocalInterface : new zzin(iBinder);
    }
}
