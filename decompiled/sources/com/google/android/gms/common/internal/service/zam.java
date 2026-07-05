package com.google.android.gms.common.internal.service;

import android.os.IBinder;
import android.os.Parcel;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class zam extends com.google.android.gms.internal.base.zaa implements zal {
    zam(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.common.internal.service.ICommonService");
    }

    @Override // com.google.android.gms.common.internal.service.zal
    public final void zaa(zaj zajVar) {
        Parcel parcelZaa = zaa();
        com.google.android.gms.internal.base.zac.zaa(parcelZaa, zajVar);
        zac(1, parcelZaa);
    }
}
