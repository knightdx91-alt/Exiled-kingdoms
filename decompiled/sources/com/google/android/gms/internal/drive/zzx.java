package com.google.android.gms.internal.drive;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.drive.Contents;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class zzx implements Parcelable.Creator<zzw> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzw createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        Integer integerObject = null;
        DriveId driveId = null;
        MetadataBundle metadataBundle = null;
        Contents contents = null;
        String strCreateString = null;
        boolean z2 = false;
        int i2 = 0;
        int i3 = 0;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case 2:
                    driveId = (DriveId) SafeParcelReader.createParcelable(parcel, header, DriveId.CREATOR);
                    break;
                case 3:
                    metadataBundle = (MetadataBundle) SafeParcelReader.createParcelable(parcel, header, MetadataBundle.CREATOR);
                    break;
                case 4:
                    contents = (Contents) SafeParcelReader.createParcelable(parcel, header, Contents.CREATOR);
                    break;
                case 5:
                    integerObject = SafeParcelReader.readIntegerObject(parcel, header);
                    break;
                case 6:
                    z2 = SafeParcelReader.readBoolean(parcel, header);
                    break;
                case 7:
                    strCreateString = SafeParcelReader.createString(parcel, header);
                    break;
                case 8:
                    i2 = SafeParcelReader.readInt(parcel, header);
                    break;
                case 9:
                    i3 = SafeParcelReader.readInt(parcel, header);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new zzw(driveId, metadataBundle, contents, integerObject.intValue(), z2, strCreateString, i2, i3);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzw[] newArray(int i2) {
        return new zzw[i2];
    }
}
