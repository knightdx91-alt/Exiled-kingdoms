package com.google.android.gms.common.internal;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zza implements Parcelable.Creator<BinderWrapper> {
    zza() {
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ BinderWrapper createFromParcel(Parcel parcel) {
        return new BinderWrapper(parcel, null);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ BinderWrapper[] newArray(int i2) {
        return new BinderWrapper[i2];
    }
}
