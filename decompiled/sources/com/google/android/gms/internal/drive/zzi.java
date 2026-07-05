package com.google.android.gms.internal.drive;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.drive.DriveId;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class zzi implements Parcelable.Creator<zzh> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzh createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        int i2 = 0;
        int i3 = 0;
        DriveId driveId = null;
        long j2 = 0;
        long j3 = 0;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(header);
            if (fieldId == 2) {
                i2 = SafeParcelReader.readInt(parcel, header);
            } else if (fieldId == 3) {
                driveId = (DriveId) SafeParcelReader.createParcelable(parcel, header, DriveId.CREATOR);
            } else if (fieldId == 4) {
                i3 = SafeParcelReader.readInt(parcel, header);
            } else if (fieldId == 5) {
                j2 = SafeParcelReader.readLong(parcel, header);
            } else if (fieldId != 6) {
                SafeParcelReader.skipUnknownField(parcel, header);
            } else {
                j3 = SafeParcelReader.readLong(parcel, header);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new zzh(i2, driveId, i3, j2, j3);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzh[] newArray(int i2) {
        return new zzh[i2];
    }
}
