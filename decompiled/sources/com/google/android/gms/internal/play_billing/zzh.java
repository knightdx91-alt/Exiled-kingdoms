package com.google.android.gms.internal.play_billing;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* JADX INFO: compiled from: com.android.billingclient:billing@@6.0.1 */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class zzh implements IInterface {
    private final IBinder zza;
    private final String zzb = "com.android.vending.billing.IInAppBillingService";

    protected zzh(IBinder iBinder, String str) {
        this.zza = iBinder;
    }

    @Override // android.os.IInterface
    public final IBinder asBinder() {
        return this.zza;
    }

    protected final Parcel zzn() {
        Parcel parcelObtain = Parcel.obtain();
        parcelObtain.writeInterfaceToken(this.zzb);
        return parcelObtain;
    }

    protected final Parcel zzo(int i2, Parcel parcel) {
        Parcel parcelObtain = Parcel.obtain();
        try {
            try {
                this.zza.transact(i2, parcel, parcelObtain, 0);
                parcelObtain.readException();
                return parcelObtain;
            } catch (RuntimeException e2) {
                parcelObtain.recycle();
                throw e2;
            }
        } finally {
            parcel.recycle();
        }
    }

    protected final void zzp(int i2, Parcel parcel) {
        Parcel parcelObtain = Parcel.obtain();
        try {
            this.zza.transact(1201, parcel, parcelObtain, 0);
            parcelObtain.readException();
        } finally {
            parcel.recycle();
            parcelObtain.recycle();
        }
    }
}
