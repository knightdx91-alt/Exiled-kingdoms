package com.google.android.gms.internal.p000authapi;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class zzc implements IInterface {
    private final IBinder zzc;
    private final String zzd;

    protected zzc(IBinder iBinder, String str) {
        this.zzc = iBinder;
        this.zzd = str;
    }

    @Override // android.os.IInterface
    public IBinder asBinder() {
        return this.zzc;
    }

    protected final Parcel obtainAndWriteInterfaceToken() {
        Parcel parcelObtain = Parcel.obtain();
        parcelObtain.writeInterfaceToken(this.zzd);
        return parcelObtain;
    }

    protected final void transactAndReadExceptionReturnVoid(int i2, Parcel parcel) {
        Parcel parcelObtain = Parcel.obtain();
        try {
            this.zzc.transact(i2, parcel, parcelObtain, 0);
            parcelObtain.readException();
        } finally {
            parcel.recycle();
            parcelObtain.recycle();
        }
    }
}
