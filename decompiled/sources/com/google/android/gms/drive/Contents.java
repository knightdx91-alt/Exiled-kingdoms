package com.google.android.gms.drive;

import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@KeepForSdk
@SafeParcelable.Class(creator = "ContentsCreator")
@SafeParcelable.Reserved({1})
public class Contents extends AbstractSafeParcelable {
    public static final Parcelable.Creator<Contents> CREATOR = new zzc();

    @SafeParcelable.Field(id = 4)
    private final int mode;

    @SafeParcelable.Field(id = 2)
    private final ParcelFileDescriptor zzi;

    @SafeParcelable.Field(id = 3)
    final int zzj;

    @SafeParcelable.Field(id = 5)
    private final DriveId zzk;

    @SafeParcelable.Field(id = 7)
    private final boolean zzl;

    @SafeParcelable.Field(id = 8)
    private final String zzm;

    @SafeParcelable.Constructor
    public Contents(@SafeParcelable.Param(id = 2) ParcelFileDescriptor parcelFileDescriptor, @SafeParcelable.Param(id = 3) int i2, @SafeParcelable.Param(id = 4) int i3, @SafeParcelable.Param(id = 5) DriveId driveId, @SafeParcelable.Param(id = 7) boolean z2, @SafeParcelable.Param(id = 8) String str) {
        this.zzi = parcelFileDescriptor;
        this.zzj = i2;
        this.mode = i3;
        this.zzk = driveId;
        this.zzl = z2;
        this.zzm = str;
    }

    public final DriveId getDriveId() {
        return this.zzk;
    }

    public final InputStream getInputStream() {
        return new FileInputStream(this.zzi.getFileDescriptor());
    }

    public final int getMode() {
        return this.mode;
    }

    public final OutputStream getOutputStream() {
        return new FileOutputStream(this.zzi.getFileDescriptor());
    }

    @KeepForSdk
    public ParcelFileDescriptor getParcelFileDescriptor() {
        return this.zzi;
    }

    public final int getRequestId() {
        return this.zzj;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzi, i2, false);
        SafeParcelWriter.writeInt(parcel, 3, this.zzj);
        SafeParcelWriter.writeInt(parcel, 4, this.mode);
        SafeParcelWriter.writeParcelable(parcel, 5, this.zzk, i2, false);
        SafeParcelWriter.writeBoolean(parcel, 7, this.zzl);
        SafeParcelWriter.writeString(parcel, 8, this.zzm, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    public final boolean zza() {
        return this.zzl;
    }
}
