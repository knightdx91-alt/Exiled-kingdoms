package com.google.android.gms.internal.drive;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class zzha implements Parcelable.Creator<zzgz> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzgz createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        DriveId driveId = null;
        MetadataBundle metadataBundle = null;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(header);
            if (fieldId == 2) {
                driveId = (DriveId) SafeParcelReader.createParcelable(parcel, header, DriveId.CREATOR);
            } else if (fieldId != 3) {
                SafeParcelReader.skipUnknownField(parcel, header);
            } else {
                metadataBundle = (MetadataBundle) SafeParcelReader.createParcelable(parcel, header, MetadataBundle.CREATOR);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new zzgz(driveId, metadataBundle);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzgz[] newArray(int i2) {
        return new zzgz[i2];
    }
}
