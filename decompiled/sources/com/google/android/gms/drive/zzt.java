package com.google.android.gms.drive;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class zzt implements Parcelable.Creator<UserMetadata> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ UserMetadata createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        String strCreateString = null;
        String strCreateString2 = null;
        String strCreateString3 = null;
        String strCreateString4 = null;
        boolean z2 = false;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(header);
            if (fieldId == 2) {
                strCreateString = SafeParcelReader.createString(parcel, header);
            } else if (fieldId == 3) {
                strCreateString2 = SafeParcelReader.createString(parcel, header);
            } else if (fieldId == 4) {
                strCreateString3 = SafeParcelReader.createString(parcel, header);
            } else if (fieldId == 5) {
                z2 = SafeParcelReader.readBoolean(parcel, header);
            } else if (fieldId != 6) {
                SafeParcelReader.skipUnknownField(parcel, header);
            } else {
                strCreateString4 = SafeParcelReader.createString(parcel, header);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new UserMetadata(strCreateString, strCreateString2, strCreateString3, z2, strCreateString4);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ UserMetadata[] newArray(int i2) {
        return new UserMetadata[i2];
    }
}
