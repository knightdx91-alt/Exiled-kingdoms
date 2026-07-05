package com.google.android.gms.internal.drive;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.query.internal.FilterHolder;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class zzgh implements Parcelable.Creator<zzgg> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzgg createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        String strCreateString = null;
        String[] strArrCreateStringArray = null;
        DriveId driveId = null;
        FilterHolder filterHolder = null;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(header);
            if (fieldId == 2) {
                strCreateString = SafeParcelReader.createString(parcel, header);
            } else if (fieldId == 3) {
                strArrCreateStringArray = SafeParcelReader.createStringArray(parcel, header);
            } else if (fieldId == 4) {
                driveId = (DriveId) SafeParcelReader.createParcelable(parcel, header, DriveId.CREATOR);
            } else if (fieldId != 5) {
                SafeParcelReader.skipUnknownField(parcel, header);
            } else {
                filterHolder = (FilterHolder) SafeParcelReader.createParcelable(parcel, header, FilterHolder.CREATOR);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new zzgg(strCreateString, strArrCreateStringArray, driveId, filterHolder);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzgg[] newArray(int i2) {
        return new zzgg[i2];
    }
}
