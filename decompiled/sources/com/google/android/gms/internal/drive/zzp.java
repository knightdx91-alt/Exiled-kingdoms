package com.google.android.gms.internal.drive;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.drive.Contents;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class zzp implements Parcelable.Creator<zzo> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzo createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        Contents contents = null;
        int i2 = 0;
        Boolean booleanObject = null;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(header);
            if (fieldId == 2) {
                contents = (Contents) SafeParcelReader.createParcelable(parcel, header, Contents.CREATOR);
            } else if (fieldId == 3) {
                booleanObject = SafeParcelReader.readBooleanObject(parcel, header);
            } else if (fieldId != 4) {
                SafeParcelReader.skipUnknownField(parcel, header);
            } else {
                i2 = SafeParcelReader.readInt(parcel, header);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new zzo(contents, booleanObject, i2);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzo[] newArray(int i2) {
        return new zzo[i2];
    }
}
