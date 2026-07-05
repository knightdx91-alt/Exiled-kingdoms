package com.google.android.gms.drive;

import a.a;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Base64;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.drive.zzhm;
import com.google.android.gms.internal.drive.zzix;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@SafeParcelable.Class(creator = "ChangeSequenceNumberCreator")
@SafeParcelable.Reserved({1})
public class zza extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zza> CREATOR = new zzb();

    @SafeParcelable.Field(id = 2)
    private final long zze;

    @SafeParcelable.Field(id = 3)
    private final long zzf;

    @SafeParcelable.Field(id = 4)
    private final long zzg;
    private volatile String zzh = null;

    @SafeParcelable.Constructor
    public zza(@SafeParcelable.Param(id = 2) long j2, @SafeParcelable.Param(id = 3) long j3, @SafeParcelable.Param(id = 4) long j4) {
        Preconditions.checkArgument(j2 != -1);
        Preconditions.checkArgument(j3 != -1);
        Preconditions.checkArgument(j4 != -1);
        this.zze = j2;
        this.zzf = j3;
        this.zzg = j4;
    }

    public boolean equals(Object obj) {
        if (obj != null && obj.getClass() == zza.class) {
            zza zzaVar = (zza) obj;
            if (zzaVar.zzf == this.zzf && zzaVar.zzg == this.zzg && zzaVar.zze == this.zze) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        String strValueOf = String.valueOf(this.zze);
        String strValueOf2 = String.valueOf(this.zzf);
        String strValueOf3 = String.valueOf(this.zzg);
        StringBuilder sb = new StringBuilder(a.e(a.e(String.valueOf(strValueOf).length(), strValueOf2), strValueOf3));
        sb.append(strValueOf);
        sb.append(strValueOf2);
        sb.append(strValueOf3);
        return sb.toString().hashCode();
    }

    public String toString() {
        if (this.zzh == null) {
            zzhm zzhmVar = new zzhm();
            zzhmVar.versionCode = 1;
            zzhmVar.zze = this.zze;
            zzhmVar.zzf = this.zzf;
            zzhmVar.zzg = this.zzg;
            String strValueOf = String.valueOf(Base64.encodeToString(zzix.zza(zzhmVar), 10));
            this.zzh = strValueOf.length() != 0 ? "ChangeSequenceNumber:".concat(strValueOf) : new String("ChangeSequenceNumber:");
        }
        return this.zzh;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeLong(parcel, 2, this.zze);
        SafeParcelWriter.writeLong(parcel, 3, this.zzf);
        SafeParcelWriter.writeLong(parcel, 4, this.zzg);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}
