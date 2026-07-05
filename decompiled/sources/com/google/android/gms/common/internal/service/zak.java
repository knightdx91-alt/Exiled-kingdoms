package com.google.android.gms.common.internal.service;

import android.os.Parcel;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public abstract class zak extends com.google.android.gms.internal.base.zab implements zaj {
    public zak() {
        super("com.google.android.gms.common.internal.service.ICommonCallbacks");
    }

    @Override // com.google.android.gms.internal.base.zab
    protected boolean dispatchTransaction(int i2, Parcel parcel, Parcel parcel2, int i3) {
        if (i2 != 1) {
            return false;
        }
        zaj(parcel.readInt());
        parcel2.writeNoException();
        return true;
    }
}
