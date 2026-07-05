package com.google.android.gms.common.internal;

import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@KeepForSdk
public abstract class DowngradeableSafeParcel extends AbstractSafeParcelable implements ReflectedParcelable {
    private static final Object zzdb = new Object();
    private static ClassLoader zzdc;
    private static Integer zzdd;
    private boolean zzde = false;

    @KeepForSdk
    protected static boolean canUnparcelSafely(String str) {
        zzp();
        return true;
    }

    @KeepForSdk
    protected static Integer getUnparcelClientVersion() {
        synchronized (zzdb) {
        }
        return null;
    }

    private static ClassLoader zzp() {
        synchronized (zzdb) {
        }
        return null;
    }

    @KeepForSdk
    protected abstract boolean prepareForClientVersion(int i2);

    @KeepForSdk
    public void setShouldDowngrade(boolean z2) {
        this.zzde = z2;
    }

    @KeepForSdk
    protected boolean shouldDowngrade() {
        return this.zzde;
    }
}
