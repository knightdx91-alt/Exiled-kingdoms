package com.google.android.gms.common.api.internal;

import android.os.RemoteException;
import android.os.TransactionTooLargeException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.GoogleApiManager;
import com.google.android.gms.common.util.PlatformVersion;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public abstract class zab {
    private final int type;

    public zab(int i2) {
        this.type = i2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Status zaa(RemoteException remoteException) {
        StringBuilder sb = new StringBuilder();
        if (PlatformVersion.isAtLeastIceCreamSandwichMR1() && (remoteException instanceof TransactionTooLargeException)) {
            sb.append("TransactionTooLargeException: ");
        }
        sb.append(remoteException.getLocalizedMessage());
        return new Status(8, sb.toString());
    }

    public abstract void zaa(Status status);

    public abstract void zaa(GoogleApiManager.zaa<?> zaaVar);

    public abstract void zaa(zaab zaabVar, boolean z2);

    public abstract void zaa(RuntimeException runtimeException);
}
