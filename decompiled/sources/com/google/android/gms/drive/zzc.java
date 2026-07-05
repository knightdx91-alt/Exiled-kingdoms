package com.google.android.gms.drive;

import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class zzc implements Parcelable.Creator<Contents> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Contents createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        ParcelFileDescriptor parcelFileDescriptor = null;
        DriveId driveId = null;
        String strCreateString = null;
        int i2 = 0;
        int i3 = 0;
        boolean z2 = false;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(header);
            if (fieldId == 2) {
                parcelFileDescriptor = (ParcelFileDescriptor) SafeParcelReader.createParcelable(parcel, header, ParcelFileDescriptor.CREATOR);
            } else if (fieldId == 3) {
                i2 = SafeParcelReader.readInt(parcel, header);
            } else if (fieldId == 4) {
                i3 = SafeParcelReader.readInt(parcel, header);
            } else if (fieldId == 5) {
                driveId = (DriveId) SafeParcelReader.createParcelable(parcel, header, DriveId.CREATOR);
            } else if (fieldId == 7) {
                z2 = SafeParcelReader.readBoolean(parcel, header);
            } else if (fieldId != 8) {
                SafeParcelReader.skipUnknownField(parcel, header);
            } else {
                strCreateString = SafeParcelReader.createString(parcel, header);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new Contents(parcelFileDescriptor, i2, i3, driveId, z2, strCreateString);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Contents[] newArray(int i2) {
        return new Contents[i2];
    }
}
