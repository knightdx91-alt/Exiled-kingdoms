package com.google.android.gms.common;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@SafeParcelable.Class(creator = "GoogleCertificatesQueryCreator")
public final class zzk extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzk> CREATOR = new zzl();

    @SafeParcelable.Field(getter = "getAllowTestKeys", id = 3)
    private final boolean zzaa;

    @SafeParcelable.Field(getter = "getCallingPackage", id = 1)
    private final String zzy;

    @SafeParcelable.Field(getter = "getCallingCertificateBinder", id = 2, type = "android.os.IBinder")
    private final zze zzz;

    @SafeParcelable.Constructor
    zzk(@SafeParcelable.Param(id = 1) String str, @SafeParcelable.Param(id = 2) IBinder iBinder, @SafeParcelable.Param(id = 3) boolean z2) {
        this.zzy = str;
        this.zzz = zza(iBinder);
        this.zzaa = z2;
    }

    private static zze zza(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        try {
            IObjectWrapper iObjectWrapperZzb = com.google.android.gms.common.internal.zzj.zzb(iBinder).zzb();
            byte[] bArr = iObjectWrapperZzb == null ? null : (byte[]) ObjectWrapper.unwrap(iObjectWrapperZzb);
            if (bArr != null) {
                return new zzf(bArr);
            }
            Log.e("GoogleCertificatesQuery", "Could not unwrap certificate");
            return null;
        } catch (RemoteException e2) {
            Log.e("GoogleCertificatesQuery", "Could not unwrap certificate", e2);
            return null;
        }
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        IBinder iBinderAsBinder;
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.zzy, false);
        zze zzeVar = this.zzz;
        if (zzeVar == null) {
            Log.w("GoogleCertificatesQuery", "certificate binder is null");
            iBinderAsBinder = null;
        } else {
            iBinderAsBinder = zzeVar.asBinder();
        }
        SafeParcelWriter.writeIBinder(parcel, 2, iBinderAsBinder, false);
        SafeParcelWriter.writeBoolean(parcel, 3, this.zzaa);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    zzk(String str, zze zzeVar, boolean z2) {
        this.zzy = str;
        this.zzz = zzeVar;
        this.zzaa = z2;
    }
}
