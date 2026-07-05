package com.google.android.gms.internal.drive;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.drive.DriveId;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class zzgn implements Parcelable.Creator<zzgm> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzgm createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        DriveId driveId = null;
        int i2 = 0;
        com.google.android.gms.drive.events.zzt zztVar = null;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(header);
            if (fieldId == 2) {
                driveId = (DriveId) SafeParcelReader.createParcelable(parcel, header, DriveId.CREATOR);
            } else if (fieldId == 3) {
                i2 = SafeParcelReader.readInt(parcel, header);
            } else if (fieldId != 4) {
                SafeParcelReader.skipUnknownField(parcel, header);
            } else {
                zztVar = (com.google.android.gms.drive.events.zzt) SafeParcelReader.createParcelable(parcel, header, com.google.android.gms.drive.events.zzt.CREATOR);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new zzgm(driveId, i2, zztVar);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzgm[] newArray(int i2) {
        return new zzgm[i2];
    }
}
