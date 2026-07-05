package com.google.android.gms.internal.drive;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.drive.DriveId;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class zzk implements Parcelable.Creator<zzj> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzj createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        DriveId driveId = null;
        com.google.android.gms.drive.events.zze zzeVar = null;
        com.google.android.gms.drive.events.zzx zzxVar = null;
        com.google.android.gms.drive.events.zzt zztVar = null;
        int i2 = 0;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(header);
            if (fieldId == 2) {
                driveId = (DriveId) SafeParcelReader.createParcelable(parcel, header, DriveId.CREATOR);
            } else if (fieldId == 3) {
                i2 = SafeParcelReader.readInt(parcel, header);
            } else if (fieldId == 4) {
                zzeVar = (com.google.android.gms.drive.events.zze) SafeParcelReader.createParcelable(parcel, header, com.google.android.gms.drive.events.zze.CREATOR);
            } else if (fieldId == 5) {
                zzxVar = (com.google.android.gms.drive.events.zzx) SafeParcelReader.createParcelable(parcel, header, com.google.android.gms.drive.events.zzx.CREATOR);
            } else if (fieldId != 6) {
                SafeParcelReader.skipUnknownField(parcel, header);
            } else {
                zztVar = (com.google.android.gms.drive.events.zzt) SafeParcelReader.createParcelable(parcel, header, com.google.android.gms.drive.events.zzt.CREATOR);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new zzj(driveId, i2, zzeVar, zzxVar, zztVar);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzj[] newArray(int i2) {
        return new zzj[i2];
    }
}
