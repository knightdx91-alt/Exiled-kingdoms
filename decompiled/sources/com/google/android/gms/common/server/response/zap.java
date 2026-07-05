package com.google.android.gms.common.server.response;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class zap implements Parcelable.Creator<SafeParcelResponse> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ SafeParcelResponse createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        int i2 = 0;
        Parcel parcelCreateParcel = null;
        zak zakVar = null;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(header);
            if (fieldId == 1) {
                i2 = SafeParcelReader.readInt(parcel, header);
            } else if (fieldId == 2) {
                parcelCreateParcel = SafeParcelReader.createParcel(parcel, header);
            } else if (fieldId != 3) {
                SafeParcelReader.skipUnknownField(parcel, header);
            } else {
                zakVar = (zak) SafeParcelReader.createParcelable(parcel, header, zak.CREATOR);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new SafeParcelResponse(i2, parcelCreateParcel, zakVar);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ SafeParcelResponse[] newArray(int i2) {
        return new SafeParcelResponse[i2];
    }
}
