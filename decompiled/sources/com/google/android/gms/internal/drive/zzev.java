package com.google.android.gms.internal.drive;

import android.os.IBinder;
import android.os.IInterface;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public abstract class zzev extends zzb implements zzeu {
    public static zzeu zza(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.drive.internal.IEventReleaseCallback");
        return iInterfaceQueryLocalInterface instanceof zzeu ? (zzeu) iInterfaceQueryLocalInterface : new zzew(iBinder);
    }
}
