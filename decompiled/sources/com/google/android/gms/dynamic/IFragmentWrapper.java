package com.google.android.gms.dynamic;

import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.badlogic.gdx.graphics.g3d.decals.Decal;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.common.zzb;
import com.google.android.gms.internal.common.zzc;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public interface IFragmentWrapper extends IInterface {

    public static abstract class Stub extends zzb implements IFragmentWrapper {

        public static class zza extends com.google.android.gms.internal.common.zza implements IFragmentWrapper {
            zza(IBinder iBinder) {
                super(iBinder, "com.google.android.gms.dynamic.IFragmentWrapper");
            }

            @Override // com.google.android.gms.dynamic.IFragmentWrapper
            public final Bundle getArguments() {
                Parcel parcelZza = zza(3, zza());
                Bundle bundle = (Bundle) zzc.zza(parcelZza, Bundle.CREATOR);
                parcelZza.recycle();
                return bundle;
            }

            @Override // com.google.android.gms.dynamic.IFragmentWrapper
            public final int getId() {
                Parcel parcelZza = zza(4, zza());
                int i2 = parcelZza.readInt();
                parcelZza.recycle();
                return i2;
            }

            @Override // com.google.android.gms.dynamic.IFragmentWrapper
            public final boolean getRetainInstance() {
                Parcel parcelZza = zza(7, zza());
                boolean zZza = zzc.zza(parcelZza);
                parcelZza.recycle();
                return zZza;
            }

            @Override // com.google.android.gms.dynamic.IFragmentWrapper
            public final String getTag() {
                Parcel parcelZza = zza(8, zza());
                String string = parcelZza.readString();
                parcelZza.recycle();
                return string;
            }

            @Override // com.google.android.gms.dynamic.IFragmentWrapper
            public final int getTargetRequestCode() {
                Parcel parcelZza = zza(10, zza());
                int i2 = parcelZza.readInt();
                parcelZza.recycle();
                return i2;
            }

            @Override // com.google.android.gms.dynamic.IFragmentWrapper
            public final boolean getUserVisibleHint() {
                Parcel parcelZza = zza(11, zza());
                boolean zZza = zzc.zza(parcelZza);
                parcelZza.recycle();
                return zZza;
            }

            @Override // com.google.android.gms.dynamic.IFragmentWrapper
            public final boolean isAdded() {
                Parcel parcelZza = zza(13, zza());
                boolean zZza = zzc.zza(parcelZza);
                parcelZza.recycle();
                return zZza;
            }

            @Override // com.google.android.gms.dynamic.IFragmentWrapper
            public final boolean isDetached() {
                Parcel parcelZza = zza(14, zza());
                boolean zZza = zzc.zza(parcelZza);
                parcelZza.recycle();
                return zZza;
            }

            @Override // com.google.android.gms.dynamic.IFragmentWrapper
            public final boolean isHidden() {
                Parcel parcelZza = zza(15, zza());
                boolean zZza = zzc.zza(parcelZza);
                parcelZza.recycle();
                return zZza;
            }

            @Override // com.google.android.gms.dynamic.IFragmentWrapper
            public final boolean isInLayout() {
                Parcel parcelZza = zza(16, zza());
                boolean zZza = zzc.zza(parcelZza);
                parcelZza.recycle();
                return zZza;
            }

            @Override // com.google.android.gms.dynamic.IFragmentWrapper
            public final boolean isRemoving() {
                Parcel parcelZza = zza(17, zza());
                boolean zZza = zzc.zza(parcelZza);
                parcelZza.recycle();
                return zZza;
            }

            @Override // com.google.android.gms.dynamic.IFragmentWrapper
            public final boolean isResumed() {
                Parcel parcelZza = zza(18, zza());
                boolean zZza = zzc.zza(parcelZza);
                parcelZza.recycle();
                return zZza;
            }

            @Override // com.google.android.gms.dynamic.IFragmentWrapper
            public final boolean isVisible() {
                Parcel parcelZza = zza(19, zza());
                boolean zZza = zzc.zza(parcelZza);
                parcelZza.recycle();
                return zZza;
            }

            @Override // com.google.android.gms.dynamic.IFragmentWrapper
            public final void setHasOptionsMenu(boolean z2) {
                Parcel parcelZza = zza();
                zzc.writeBoolean(parcelZza, z2);
                zzb(21, parcelZza);
            }

            @Override // com.google.android.gms.dynamic.IFragmentWrapper
            public final void setMenuVisibility(boolean z2) {
                Parcel parcelZza = zza();
                zzc.writeBoolean(parcelZza, z2);
                zzb(22, parcelZza);
            }

            @Override // com.google.android.gms.dynamic.IFragmentWrapper
            public final void setRetainInstance(boolean z2) {
                Parcel parcelZza = zza();
                zzc.writeBoolean(parcelZza, z2);
                zzb(23, parcelZza);
            }

            @Override // com.google.android.gms.dynamic.IFragmentWrapper
            public final void setUserVisibleHint(boolean z2) {
                Parcel parcelZza = zza();
                zzc.writeBoolean(parcelZza, z2);
                zzb(24, parcelZza);
            }

            @Override // com.google.android.gms.dynamic.IFragmentWrapper
            public final void startActivity(Intent intent) {
                Parcel parcelZza = zza();
                zzc.zza(parcelZza, intent);
                zzb(25, parcelZza);
            }

            @Override // com.google.android.gms.dynamic.IFragmentWrapper
            public final void startActivityForResult(Intent intent, int i2) {
                Parcel parcelZza = zza();
                zzc.zza(parcelZza, intent);
                parcelZza.writeInt(i2);
                zzb(26, parcelZza);
            }

            @Override // com.google.android.gms.dynamic.IFragmentWrapper
            public final void zza(IObjectWrapper iObjectWrapper) {
                Parcel parcelZza = zza();
                zzc.zza(parcelZza, iObjectWrapper);
                zzb(20, parcelZza);
            }

            @Override // com.google.android.gms.dynamic.IFragmentWrapper
            public final IObjectWrapper zzad() {
                Parcel parcelZza = zza(2, zza());
                IObjectWrapper iObjectWrapperAsInterface = IObjectWrapper.Stub.asInterface(parcelZza.readStrongBinder());
                parcelZza.recycle();
                return iObjectWrapperAsInterface;
            }

            @Override // com.google.android.gms.dynamic.IFragmentWrapper
            public final IFragmentWrapper zzae() {
                Parcel parcelZza = zza(5, zza());
                IFragmentWrapper iFragmentWrapperAsInterface = Stub.asInterface(parcelZza.readStrongBinder());
                parcelZza.recycle();
                return iFragmentWrapperAsInterface;
            }

            @Override // com.google.android.gms.dynamic.IFragmentWrapper
            public final IObjectWrapper zzaf() {
                Parcel parcelZza = zza(6, zza());
                IObjectWrapper iObjectWrapperAsInterface = IObjectWrapper.Stub.asInterface(parcelZza.readStrongBinder());
                parcelZza.recycle();
                return iObjectWrapperAsInterface;
            }

            @Override // com.google.android.gms.dynamic.IFragmentWrapper
            public final IFragmentWrapper zzag() {
                Parcel parcelZza = zza(9, zza());
                IFragmentWrapper iFragmentWrapperAsInterface = Stub.asInterface(parcelZza.readStrongBinder());
                parcelZza.recycle();
                return iFragmentWrapperAsInterface;
            }

            @Override // com.google.android.gms.dynamic.IFragmentWrapper
            public final IObjectWrapper zzah() {
                Parcel parcelZza = zza(12, zza());
                IObjectWrapper iObjectWrapperAsInterface = IObjectWrapper.Stub.asInterface(parcelZza.readStrongBinder());
                parcelZza.recycle();
                return iObjectWrapperAsInterface;
            }

            @Override // com.google.android.gms.dynamic.IFragmentWrapper
            public final void zzb(IObjectWrapper iObjectWrapper) {
                Parcel parcelZza = zza();
                zzc.zza(parcelZza, iObjectWrapper);
                zzb(27, parcelZza);
            }
        }

        public Stub() {
            super("com.google.android.gms.dynamic.IFragmentWrapper");
        }

        public static IFragmentWrapper asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.dynamic.IFragmentWrapper");
            return iInterfaceQueryLocalInterface instanceof IFragmentWrapper ? (IFragmentWrapper) iInterfaceQueryLocalInterface : new zza(iBinder);
        }

        @Override // com.google.android.gms.internal.common.zzb
        protected final boolean zza(int i2, Parcel parcel, Parcel parcel2, int i3) {
            switch (i2) {
                case 2:
                    IObjectWrapper iObjectWrapperZzad = zzad();
                    parcel2.writeNoException();
                    zzc.zza(parcel2, iObjectWrapperZzad);
                    return true;
                case 3:
                    Bundle arguments = getArguments();
                    parcel2.writeNoException();
                    zzc.zzb(parcel2, arguments);
                    return true;
                case 4:
                    int id = getId();
                    parcel2.writeNoException();
                    parcel2.writeInt(id);
                    return true;
                case 5:
                    IFragmentWrapper iFragmentWrapperZzae = zzae();
                    parcel2.writeNoException();
                    zzc.zza(parcel2, iFragmentWrapperZzae);
                    return true;
                case 6:
                    IObjectWrapper iObjectWrapperZzaf = zzaf();
                    parcel2.writeNoException();
                    zzc.zza(parcel2, iObjectWrapperZzaf);
                    return true;
                case 7:
                    boolean retainInstance = getRetainInstance();
                    parcel2.writeNoException();
                    zzc.writeBoolean(parcel2, retainInstance);
                    return true;
                case 8:
                    String tag = getTag();
                    parcel2.writeNoException();
                    parcel2.writeString(tag);
                    return true;
                case 9:
                    IFragmentWrapper iFragmentWrapperZzag = zzag();
                    parcel2.writeNoException();
                    zzc.zza(parcel2, iFragmentWrapperZzag);
                    return true;
                case 10:
                    int targetRequestCode = getTargetRequestCode();
                    parcel2.writeNoException();
                    parcel2.writeInt(targetRequestCode);
                    return true;
                case 11:
                    boolean userVisibleHint = getUserVisibleHint();
                    parcel2.writeNoException();
                    zzc.writeBoolean(parcel2, userVisibleHint);
                    return true;
                case 12:
                    IObjectWrapper iObjectWrapperZzah = zzah();
                    parcel2.writeNoException();
                    zzc.zza(parcel2, iObjectWrapperZzah);
                    return true;
                case 13:
                    boolean zIsAdded = isAdded();
                    parcel2.writeNoException();
                    zzc.writeBoolean(parcel2, zIsAdded);
                    return true;
                case 14:
                    boolean zIsDetached = isDetached();
                    parcel2.writeNoException();
                    zzc.writeBoolean(parcel2, zIsDetached);
                    return true;
                case 15:
                    boolean zIsHidden = isHidden();
                    parcel2.writeNoException();
                    zzc.writeBoolean(parcel2, zIsHidden);
                    return true;
                case 16:
                    boolean zIsInLayout = isInLayout();
                    parcel2.writeNoException();
                    zzc.writeBoolean(parcel2, zIsInLayout);
                    return true;
                case 17:
                    boolean zIsRemoving = isRemoving();
                    parcel2.writeNoException();
                    zzc.writeBoolean(parcel2, zIsRemoving);
                    return true;
                case 18:
                    boolean zIsResumed = isResumed();
                    parcel2.writeNoException();
                    zzc.writeBoolean(parcel2, zIsResumed);
                    return true;
                case 19:
                    boolean zIsVisible = isVisible();
                    parcel2.writeNoException();
                    zzc.writeBoolean(parcel2, zIsVisible);
                    return true;
                case 20:
                    zza(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case Decal.C4 /* 21 */:
                    setHasOptionsMenu(zzc.zza(parcel));
                    parcel2.writeNoException();
                    return true;
                case Decal.U4 /* 22 */:
                    setMenuVisibility(zzc.zza(parcel));
                    parcel2.writeNoException();
                    return true;
                case Decal.V4 /* 23 */:
                    setRetainInstance(zzc.zza(parcel));
                    parcel2.writeNoException();
                    return true;
                case Decal.SIZE /* 24 */:
                    setUserVisibleHint(zzc.zza(parcel));
                    parcel2.writeNoException();
                    return true;
                case 25:
                    startActivity((Intent) zzc.zza(parcel, Intent.CREATOR));
                    parcel2.writeNoException();
                    return true;
                case 26:
                    startActivityForResult((Intent) zzc.zza(parcel, Intent.CREATOR), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 27:
                    zzb(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                default:
                    return false;
            }
        }
    }

    Bundle getArguments();

    int getId();

    boolean getRetainInstance();

    String getTag();

    int getTargetRequestCode();

    boolean getUserVisibleHint();

    boolean isAdded();

    boolean isDetached();

    boolean isHidden();

    boolean isInLayout();

    boolean isRemoving();

    boolean isResumed();

    boolean isVisible();

    void setHasOptionsMenu(boolean z2);

    void setMenuVisibility(boolean z2);

    void setRetainInstance(boolean z2);

    void setUserVisibleHint(boolean z2);

    void startActivity(Intent intent);

    void startActivityForResult(Intent intent, int i2);

    void zza(IObjectWrapper iObjectWrapper);

    IObjectWrapper zzad();

    IFragmentWrapper zzae();

    IObjectWrapper zzaf();

    IFragmentWrapper zzag();

    IObjectWrapper zzah();

    void zzb(IObjectWrapper iObjectWrapper);
}
