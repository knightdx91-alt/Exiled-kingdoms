package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.Parcel;
import com.google.android.gms.dynamic.IObjectWrapper;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class zah extends com.google.android.gms.internal.base.zaa implements ISignInButtonCreator {
    zah(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.common.internal.ISignInButtonCreator");
    }

    @Override // com.google.android.gms.common.internal.ISignInButtonCreator
    public final IObjectWrapper newSignInButton(IObjectWrapper iObjectWrapper, int i2, int i3) {
        Parcel parcelZaa = zaa();
        com.google.android.gms.internal.base.zac.zaa(parcelZaa, iObjectWrapper);
        parcelZaa.writeInt(i2);
        parcelZaa.writeInt(i3);
        Parcel parcelZaa2 = zaa(1, parcelZaa);
        IObjectWrapper iObjectWrapperAsInterface = IObjectWrapper.Stub.asInterface(parcelZaa2.readStrongBinder());
        parcelZaa2.recycle();
        return iObjectWrapperAsInterface;
    }

    @Override // com.google.android.gms.common.internal.ISignInButtonCreator
    public final IObjectWrapper newSignInButtonFromConfig(IObjectWrapper iObjectWrapper, SignInButtonConfig signInButtonConfig) {
        Parcel parcelZaa = zaa();
        com.google.android.gms.internal.base.zac.zaa(parcelZaa, iObjectWrapper);
        com.google.android.gms.internal.base.zac.zaa(parcelZaa, signInButtonConfig);
        Parcel parcelZaa2 = zaa(2, parcelZaa);
        IObjectWrapper iObjectWrapperAsInterface = IObjectWrapper.Stub.asInterface(parcelZaa2.readStrongBinder());
        parcelZaa2.recycle();
        return iObjectWrapperAsInterface;
    }
}
