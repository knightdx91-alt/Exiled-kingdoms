package com.google.android.gms.internal.drive;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class zzv implements Parcelable.Creator<zzu> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzu createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        MetadataBundle metadataBundle = null;
        String strCreateString = null;
        DriveId driveId = null;
        Integer integerObject = null;
        int i2 = 0;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(header);
            if (fieldId == 2) {
                metadataBundle = (MetadataBundle) SafeParcelReader.createParcelable(parcel, header, MetadataBundle.CREATOR);
            } else if (fieldId == 3) {
                i2 = SafeParcelReader.readInt(parcel, header);
            } else if (fieldId == 4) {
                strCreateString = SafeParcelReader.createString(parcel, header);
            } else if (fieldId == 5) {
                driveId = (DriveId) SafeParcelReader.createParcelable(parcel, header, DriveId.CREATOR);
            } else if (fieldId != 6) {
                SafeParcelReader.skipUnknownField(parcel, header);
            } else {
                integerObject = SafeParcelReader.readIntegerObject(parcel, header);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new zzu(metadataBundle, i2, strCreateString, driveId, integerObject);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzu[] newArray(int i2) {
        return new zzu[i2];
    }
}
