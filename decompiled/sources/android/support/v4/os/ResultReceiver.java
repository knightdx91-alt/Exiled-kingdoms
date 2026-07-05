package android.support.v4.os;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import android.support.v4.os.a;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class ResultReceiver implements Parcelable {
    public static final Parcelable.Creator<ResultReceiver> CREATOR = new a();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    android.support.v4.os.a f533a;

    static class a implements Parcelable.Creator<ResultReceiver> {
        @Override // android.os.Parcelable.Creator
        public final ResultReceiver createFromParcel(Parcel parcel) {
            android.support.v4.os.a c0010a;
            ResultReceiver resultReceiver = new ResultReceiver();
            IBinder strongBinder = parcel.readStrongBinder();
            int i2 = a.AbstractBinderC0009a.f535a;
            if (strongBinder == null) {
                c0010a = null;
            } else {
                IInterface iInterfaceQueryLocalInterface = strongBinder.queryLocalInterface("android.support.v4.os.IResultReceiver");
                c0010a = (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof android.support.v4.os.a)) ? new a.AbstractBinderC0009a.C0010a(strongBinder) : (android.support.v4.os.a) iInterfaceQueryLocalInterface;
            }
            resultReceiver.f533a = c0010a;
            return resultReceiver;
        }

        @Override // android.os.Parcelable.Creator
        public final ResultReceiver[] newArray(int i2) {
            return new ResultReceiver[i2];
        }
    }

    class b extends a.AbstractBinderC0009a {
        b() {
            attachInterface(this, "android.support.v4.os.IResultReceiver");
        }

        @Override // android.support.v4.os.a
        public final void h(int i2, Bundle bundle) {
            ResultReceiver resultReceiver = ResultReceiver.this;
            resultReceiver.getClass();
            resultReceiver.a(i2, bundle);
        }
    }

    protected void a(int i2, Bundle bundle) {
    }

    public final void b(int i2, Bundle bundle) {
        android.support.v4.os.a aVar = this.f533a;
        if (aVar != null) {
            try {
                aVar.h(i2, bundle);
            } catch (RemoteException unused) {
            }
        }
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        synchronized (this) {
            try {
                if (this.f533a == null) {
                    this.f533a = new b();
                }
                parcel.writeStrongBinder(this.f533a.asBinder());
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
