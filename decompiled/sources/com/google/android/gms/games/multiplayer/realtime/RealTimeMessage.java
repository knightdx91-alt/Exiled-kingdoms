package com.google.android.gms.games.multiplayer.realtime;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Preconditions;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class RealTimeMessage implements Parcelable {
    public static final Parcelable.Creator<RealTimeMessage> CREATOR = new zza();
    public static final int RELIABLE = 1;
    public static final int UNRELIABLE = 0;
    private final String zzoo;
    private final byte[] zzop;
    private final int zzoq;

    private RealTimeMessage(Parcel parcel) {
        this(parcel.readString(), parcel.createByteArray(), parcel.readInt());
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public final byte[] getMessageData() {
        return this.zzop;
    }

    public final String getSenderParticipantId() {
        return this.zzoo;
    }

    public final boolean isReliable() {
        return this.zzoq == 1;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.zzoo);
        parcel.writeByteArray(this.zzop);
        parcel.writeInt(this.zzoq);
    }

    /* synthetic */ RealTimeMessage(Parcel parcel, zza zzaVar) {
        this(parcel);
    }

    private RealTimeMessage(String str, byte[] bArr, int i2) {
        this.zzoo = (String) Preconditions.checkNotNull(str);
        this.zzop = (byte[]) ((byte[]) Preconditions.checkNotNull(bArr)).clone();
        this.zzoq = i2;
    }
}
