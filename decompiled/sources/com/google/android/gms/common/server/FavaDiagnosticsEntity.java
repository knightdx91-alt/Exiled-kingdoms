package com.google.android.gms.common.server;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@KeepForSdk
@SafeParcelable.Class(creator = "FavaDiagnosticsEntityCreator")
public class FavaDiagnosticsEntity extends AbstractSafeParcelable implements ReflectedParcelable {

    @KeepForSdk
    public static final Parcelable.Creator<FavaDiagnosticsEntity> CREATOR = new zaa();

    @SafeParcelable.VersionField(id = 1)
    private final int zale;

    @SafeParcelable.Field(id = 2)
    private final String zapi;

    @SafeParcelable.Field(id = 3)
    private final int zapj;

    @SafeParcelable.Constructor
    public FavaDiagnosticsEntity(@SafeParcelable.Param(id = 1) int i2, @SafeParcelable.Param(id = 2) String str, @SafeParcelable.Param(id = 3) int i3) {
        this.zale = i2;
        this.zapi = str;
        this.zapj = i3;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zale);
        SafeParcelWriter.writeString(parcel, 2, this.zapi, false);
        SafeParcelWriter.writeInt(parcel, 3, this.zapj);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    @KeepForSdk
    public FavaDiagnosticsEntity(String str, int i2) {
        this.zale = 1;
        this.zapi = str;
        this.zapj = i2;
    }
}
