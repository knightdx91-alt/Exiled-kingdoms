package android.support.v4.media.session;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.support.v4.media.session.a;

/* JADX INFO: compiled from: IMediaSession.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public interface b extends IInterface {

    /* JADX INFO: compiled from: IMediaSession.java */
    public static abstract class a extends Binder implements b {

        /* JADX INFO: renamed from: android.support.v4.media.session.b$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: IMediaSession.java */
        private static class C0008a implements b {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            private IBinder f527a;

            C0008a(IBinder iBinder) {
                this.f527a = iBinder;
            }

            @Override // android.support.v4.media.session.b
            public final void a(android.support.v4.media.session.a aVar) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    parcelObtain.writeStrongBinder((a.AbstractBinderC0007a) aVar);
                    this.f527a.transact(3, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IInterface
            public final IBinder asBinder() {
                return this.f527a;
            }
        }

        public static b i(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("android.support.v4.media.session.IMediaSession");
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof b)) ? new C0008a(iBinder) : (b) iInterfaceQueryLocalInterface;
        }
    }

    void a(android.support.v4.media.session.a aVar);
}
