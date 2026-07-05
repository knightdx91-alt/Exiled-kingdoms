package com.google.android.gms.drive;

import a.a;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@SafeParcelable.Class(creator = "UserMetadataCreator")
@SafeParcelable.Reserved({1})
public class UserMetadata extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Parcelable.Creator<UserMetadata> CREATOR = new zzt();

    @SafeParcelable.Field(id = 2)
    private final String zzbm;

    @SafeParcelable.Field(id = 3)
    private final String zzbn;

    @SafeParcelable.Field(id = 4)
    private final String zzbo;

    @SafeParcelable.Field(id = 5)
    private final boolean zzbp;

    @SafeParcelable.Field(id = 6)
    private final String zzbq;

    @SafeParcelable.Constructor
    public UserMetadata(@SafeParcelable.Param(id = 2) String str, @SafeParcelable.Param(id = 3) String str2, @SafeParcelable.Param(id = 4) String str3, @SafeParcelable.Param(id = 5) boolean z2, @SafeParcelable.Param(id = 6) String str4) {
        this.zzbm = str;
        this.zzbn = str2;
        this.zzbo = str3;
        this.zzbp = z2;
        this.zzbq = str4;
    }

    public String toString() {
        String str = this.zzbm;
        String str2 = this.zzbn;
        String str3 = this.zzbo;
        boolean z2 = this.zzbp;
        String str4 = this.zzbq;
        StringBuilder sb = new StringBuilder("Permission ID: '");
        sb.append(str);
        sb.append("', Display Name: '");
        sb.append(str2);
        sb.append("', Picture URL: '");
        sb.append(str3);
        sb.append("', Authenticated User: ");
        sb.append(z2);
        sb.append(", Email: '");
        return a.m(str4, "'", sb);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.zzbm, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzbn, false);
        SafeParcelWriter.writeString(parcel, 4, this.zzbo, false);
        SafeParcelWriter.writeBoolean(parcel, 5, this.zzbp);
        SafeParcelWriter.writeString(parcel, 6, this.zzbq, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}
