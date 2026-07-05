package com.google.android.gms.drive;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Base64;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.drive.zzbn;
import com.google.android.gms.internal.drive.zzbs;
import com.google.android.gms.internal.drive.zzdp;
import com.google.android.gms.internal.drive.zzhn;
import com.google.android.gms.internal.drive.zzho;
import com.google.android.gms.internal.drive.zziw;
import com.google.android.gms.internal.drive.zzix;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@SafeParcelable.Class(creator = "DriveIdCreator")
@SafeParcelable.Reserved({1})
public class DriveId extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Parcelable.Creator<DriveId> CREATOR = new zzk();
    public static final int RESOURCE_TYPE_FILE = 0;
    public static final int RESOURCE_TYPE_FOLDER = 1;
    public static final int RESOURCE_TYPE_UNKNOWN = -1;

    @SafeParcelable.Field(id = 2)
    private final String zzab;

    @SafeParcelable.Field(id = 3)
    private final long zzac;

    @SafeParcelable.Field(defaultValueUnchecked = "com.google.android.gms.drive.DriveId.RESOURCE_TYPE_UNKNOWN", id = 5)
    private final int zzad;

    @SafeParcelable.Field(id = 4)
    private final long zzf;
    private volatile String zzh = null;
    private volatile String zzae = null;

    @SafeParcelable.Constructor
    public DriveId(@SafeParcelable.Param(id = 2) String str, @SafeParcelable.Param(id = 3) long j2, @SafeParcelable.Param(id = 4) long j3, @SafeParcelable.Param(id = 5) int i2) {
        this.zzab = str;
        boolean z2 = true;
        Preconditions.checkArgument(!"".equals(str));
        if (str == null && j2 == -1) {
            z2 = false;
        }
        Preconditions.checkArgument(z2);
        this.zzac = j2;
        this.zzf = j3;
        this.zzad = i2;
    }

    public static DriveId decodeFromString(String str) {
        Preconditions.checkArgument(str.startsWith("DriveId:"), str.length() != 0 ? "Invalid DriveId: ".concat(str) : new String("Invalid DriveId: "));
        return zza(Base64.decode(str.substring(8), 10));
    }

    @VisibleForTesting
    public static DriveId zza(String str) {
        Preconditions.checkNotNull(str);
        return new DriveId(str, -1L, -1L, -1);
    }

    public DriveFile asDriveFile() {
        if (this.zzad != 1) {
            return new zzbn(this);
        }
        throw new IllegalStateException("This DriveId corresponds to a folder. Call asDriveFolder instead.");
    }

    public DriveFolder asDriveFolder() {
        if (this.zzad != 0) {
            return new zzbs(this);
        }
        throw new IllegalStateException("This DriveId corresponds to a file. Call asDriveFile instead.");
    }

    public DriveResource asDriveResource() {
        int i2 = this.zzad;
        return i2 == 1 ? asDriveFolder() : i2 == 0 ? asDriveFile() : new zzdp(this);
    }

    public final String encodeToString() {
        if (this.zzh == null) {
            zzhn zzhnVar = new zzhn();
            zzhnVar.versionCode = 1;
            String str = this.zzab;
            if (str == null) {
                str = "";
            }
            zzhnVar.zzab = str;
            zzhnVar.zzac = this.zzac;
            zzhnVar.zzf = this.zzf;
            zzhnVar.zzad = this.zzad;
            String strValueOf = String.valueOf(Base64.encodeToString(zzix.zza(zzhnVar), 10));
            this.zzh = strValueOf.length() != 0 ? "DriveId:".concat(strValueOf) : new String("DriveId:");
        }
        return this.zzh;
    }

    public boolean equals(Object obj) {
        String str;
        if (obj != null && obj.getClass() == DriveId.class) {
            DriveId driveId = (DriveId) obj;
            if (driveId.zzf != this.zzf) {
                return false;
            }
            long j2 = driveId.zzac;
            if (j2 == -1 && this.zzac == -1) {
                return driveId.zzab.equals(this.zzab);
            }
            String str2 = this.zzab;
            if (str2 != null && (str = driveId.zzab) != null) {
                return j2 == this.zzac && str.equals(str2);
            }
            if (j2 == this.zzac) {
                return true;
            }
        }
        return false;
    }

    public String getResourceId() {
        return this.zzab;
    }

    public int getResourceType() {
        return this.zzad;
    }

    public int hashCode() {
        if (this.zzac == -1) {
            return this.zzab.hashCode();
        }
        String strValueOf = String.valueOf(String.valueOf(this.zzf));
        String strValueOf2 = String.valueOf(String.valueOf(this.zzac));
        return (strValueOf2.length() != 0 ? strValueOf.concat(strValueOf2) : new String(strValueOf)).hashCode();
    }

    public final String toInvariantString() {
        if (this.zzae == null) {
            zzho zzhoVar = new zzho();
            zzhoVar.zzac = this.zzac;
            zzhoVar.zzf = this.zzf;
            this.zzae = Base64.encodeToString(zzix.zza(zzhoVar), 10);
        }
        return this.zzae;
    }

    public String toString() {
        return encodeToString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.zzab, false);
        SafeParcelWriter.writeLong(parcel, 3, this.zzac);
        SafeParcelWriter.writeLong(parcel, 4, this.zzf);
        SafeParcelWriter.writeInt(parcel, 5, this.zzad);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    @VisibleForTesting
    private static DriveId zza(byte[] bArr) {
        try {
            zzhn zzhnVar = (zzhn) zzix.zza(new zzhn(), bArr, 0, bArr.length);
            return new DriveId("".equals(zzhnVar.zzab) ? null : zzhnVar.zzab, zzhnVar.zzac, zzhnVar.zzf, zzhnVar.zzad);
        } catch (zziw unused) {
            throw new IllegalArgumentException();
        }
    }
}
