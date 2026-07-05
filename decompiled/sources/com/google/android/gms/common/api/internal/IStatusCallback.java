package com.google.android.gms.common.api.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.gms.common.api.Status;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public interface IStatusCallback extends IInterface {

    public static abstract class Stub extends com.google.android.gms.internal.base.zab implements IStatusCallback {

        public static class zaa extends com.google.android.gms.internal.base.zaa implements IStatusCallback {
            zaa(IBinder iBinder) {
                super(iBinder, "com.google.android.gms.common.api.internal.IStatusCallback");
            }

            @Override // com.google.android.gms.common.api.internal.IStatusCallback
            public final void onResult(Status status) {
                Parcel parcelZaa = zaa();
                com.google.android.gms.internal.base.zac.zaa(parcelZaa, status);
                zac(1, parcelZaa);
            }
        }

        public Stub() {
            super("com.google.android.gms.common.api.internal.IStatusCallback");
        }

        public static IStatusCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.common.api.internal.IStatusCallback");
            return iInterfaceQueryLocalInterface instanceof IStatusCallback ? (IStatusCallback) iInterfaceQueryLocalInterface : new zaa(iBinder);
        }

        @Override // com.google.android.gms.internal.base.zab
        protected boolean dispatchTransaction(int i2, Parcel parcel, Parcel parcel2, int i3) {
            if (i2 != 1) {
                return false;
            }
            onResult((Status) com.google.android.gms.internal.base.zac.zaa(parcel, Status.CREATOR));
            return true;
        }
    }

    void onResult(Status status);
}
