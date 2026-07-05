package com.google.android.gms.games.multiplayer.realtime;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zza implements Parcelable.Creator<RealTimeMessage> {
    zza() {
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ RealTimeMessage createFromParcel(Parcel parcel) {
        return new RealTimeMessage(parcel, null);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ RealTimeMessage[] newArray(int i2) {
        return new RealTimeMessage[i2];
    }
}
