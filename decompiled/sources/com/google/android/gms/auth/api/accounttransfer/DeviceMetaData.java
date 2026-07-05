package com.google.android.gms.auth.api.accounttransfer;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@SafeParcelable.Class(creator = "DeviceMetaDataCreator")
public class DeviceMetaData extends AbstractSafeParcelable {
    public static final Parcelable.Creator<DeviceMetaData> CREATOR = new zzv();

    @SafeParcelable.Field(getter = "isLockScreenSolved", id = 2)
    private boolean zzbs;

    @SafeParcelable.Field(getter = "getMinAgeOfLockScreen", id = 3)
    private long zzbt;

    @SafeParcelable.Field(getter = "isChallengeAllowed", id = 4)
    private final boolean zzbu;

    @SafeParcelable.VersionField(id = 1)
    private final int zzv;

    @SafeParcelable.Constructor
    DeviceMetaData(@SafeParcelable.Param(id = 1) int i2, @SafeParcelable.Param(id = 2) boolean z2, @SafeParcelable.Param(id = 3) long j2, @SafeParcelable.Param(id = 4) boolean z3) {
        this.zzv = i2;
        this.zzbs = z2;
        this.zzbt = j2;
        this.zzbu = z3;
    }

    public long getMinAgeOfLockScreen() {
        return this.zzbt;
    }

    public boolean isChallengeAllowed() {
        return this.zzbu;
    }

    public boolean isLockScreenSolved() {
        return this.zzbs;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zzv);
        SafeParcelWriter.writeBoolean(parcel, 2, isLockScreenSolved());
        SafeParcelWriter.writeLong(parcel, 3, getMinAgeOfLockScreen());
        SafeParcelWriter.writeBoolean(parcel, 4, isChallengeAllowed());
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}
