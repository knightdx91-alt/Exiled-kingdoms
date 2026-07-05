package com.google.android.gms.internal.play_billing;

import a.a;
import android.os.BadParcelableException;
import android.os.Bundle;
import android.os.Parcel;

/* JADX INFO: compiled from: com.android.billingclient:billing@@6.0.1 */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public abstract class zzf extends zzi implements zzg {
    public zzf() {
        super("com.android.vending.billing.IInAppBillingServiceCallback");
    }

    @Override // com.google.android.gms.internal.play_billing.zzi
    protected final boolean zzb(int i2, Parcel parcel, Parcel parcel2, int i3) {
        if (i2 != 1) {
            return false;
        }
        Bundle bundle = (Bundle) zzj.zza(parcel, Bundle.CREATOR);
        int iDataAvail = parcel.dataAvail();
        if (iDataAvail > 0) {
            throw new BadParcelableException(a.h(iDataAvail, "Parcel data not fully consumed, unread size: "));
        }
        zza(bundle);
        parcel2.writeNoException();
        return true;
    }
}
