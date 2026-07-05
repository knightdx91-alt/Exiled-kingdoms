package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.annotation.KeepName;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@KeepForSdk
@KeepName
public final class BinderWrapper implements Parcelable {
    public static final Parcelable.Creator<BinderWrapper> CREATOR = new zza();
    private IBinder zzcy;

    public BinderWrapper() {
        this.zzcy = null;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        parcel.writeStrongBinder(this.zzcy);
    }

    @KeepForSdk
    public BinderWrapper(IBinder iBinder) {
        this.zzcy = iBinder;
    }

    private BinderWrapper(Parcel parcel) {
        this.zzcy = null;
        this.zzcy = parcel.readStrongBinder();
    }

    /* synthetic */ BinderWrapper(Parcel parcel, zza zzaVar) {
        this(parcel);
    }
}
