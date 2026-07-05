package com.google.android.gms.drive;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@SafeParcelable.Class(creator = "PermissionCreator")
@SafeParcelable.Reserved({1})
public final class zzr extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzr> CREATOR = new zzs();

    @SafeParcelable.Field(getter = "getAccountType", id = 3)
    private int accountType;

    @SafeParcelable.Field(getter = "getAccountIdentifier", id = 2)
    private String zzbe;

    @SafeParcelable.Field(getter = "getAccountDisplayName", id = 4)
    private String zzbf;

    @SafeParcelable.Field(getter = "getPhotoLink", id = 5)
    private String zzbg;

    @SafeParcelable.Field(getter = "getRole", id = 6)
    private int zzbh;

    @SafeParcelable.Field(getter = "isLinkRequiredForAccess", id = 7)
    private boolean zzbi;

    @SafeParcelable.Constructor
    public zzr(@SafeParcelable.Param(id = 2) String str, @SafeParcelable.Param(id = 3) int i2, @SafeParcelable.Param(id = 4) String str2, @SafeParcelable.Param(id = 5) String str3, @SafeParcelable.Param(id = 6) int i3, @SafeParcelable.Param(id = 7) boolean z2) {
        this.zzbe = str;
        this.accountType = i2;
        this.zzbf = str2;
        this.zzbg = str3;
        this.zzbh = i3;
        this.zzbi = z2;
    }

    private static boolean zzb(int i2) {
        switch (i2) {
            case 256:
            case 257:
            case 258:
                return true;
            default:
                return false;
        }
    }

    public final boolean equals(Object obj) {
        if (obj != null && obj.getClass() == zzr.class) {
            if (obj == this) {
                return true;
            }
            zzr zzrVar = (zzr) obj;
            if (Objects.equal(this.zzbe, zzrVar.zzbe) && this.accountType == zzrVar.accountType && this.zzbh == zzrVar.zzbh && this.zzbi == zzrVar.zzbi) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hashCode(this.zzbe, Integer.valueOf(this.accountType), Integer.valueOf(this.zzbh), Boolean.valueOf(this.zzbi));
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, !zzb(this.accountType) ? null : this.zzbe, false);
        SafeParcelWriter.writeInt(parcel, 3, !zzb(this.accountType) ? -1 : this.accountType);
        SafeParcelWriter.writeString(parcel, 4, this.zzbf, false);
        SafeParcelWriter.writeString(parcel, 5, this.zzbg, false);
        int i3 = this.zzbh;
        SafeParcelWriter.writeInt(parcel, 6, (i3 == 0 || i3 == 1 || i3 == 2 || i3 == 3) ? i3 : -1);
        SafeParcelWriter.writeBoolean(parcel, 7, this.zzbi);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}
