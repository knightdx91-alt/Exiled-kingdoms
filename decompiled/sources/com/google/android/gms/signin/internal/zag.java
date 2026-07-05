package com.google.android.gms.signin.internal;

import android.os.IBinder;
import android.os.Parcel;
import com.google.android.gms.common.internal.IAccountAccessor;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class zag extends com.google.android.gms.internal.base.zaa implements zaf {
    zag(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.signin.internal.ISignInService");
    }

    @Override // com.google.android.gms.signin.internal.zaf
    public final void zaa(IAccountAccessor iAccountAccessor, int i2, boolean z2) {
        Parcel parcelZaa = zaa();
        com.google.android.gms.internal.base.zac.zaa(parcelZaa, iAccountAccessor);
        parcelZaa.writeInt(i2);
        com.google.android.gms.internal.base.zac.writeBoolean(parcelZaa, z2);
        zab(9, parcelZaa);
    }

    @Override // com.google.android.gms.signin.internal.zaf
    public final void zam(int i2) {
        Parcel parcelZaa = zaa();
        parcelZaa.writeInt(i2);
        zab(7, parcelZaa);
    }

    @Override // com.google.android.gms.signin.internal.zaf
    public final void zaa(zah zahVar, zad zadVar) {
        Parcel parcelZaa = zaa();
        com.google.android.gms.internal.base.zac.zaa(parcelZaa, zahVar);
        com.google.android.gms.internal.base.zac.zaa(parcelZaa, zadVar);
        zab(12, parcelZaa);
    }
}
