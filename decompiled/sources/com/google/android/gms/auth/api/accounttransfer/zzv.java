package com.google.android.gms.auth.api.accounttransfer;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class zzv implements Parcelable.Creator<DeviceMetaData> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ DeviceMetaData createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        int i2 = 0;
        boolean z2 = false;
        boolean z3 = false;
        long j2 = 0;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(header);
            if (fieldId == 1) {
                i2 = SafeParcelReader.readInt(parcel, header);
            } else if (fieldId == 2) {
                z2 = SafeParcelReader.readBoolean(parcel, header);
            } else if (fieldId == 3) {
                j2 = SafeParcelReader.readLong(parcel, header);
            } else if (fieldId != 4) {
                SafeParcelReader.skipUnknownField(parcel, header);
            } else {
                z3 = SafeParcelReader.readBoolean(parcel, header);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new DeviceMetaData(i2, z2, j2, z3);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ DeviceMetaData[] newArray(int i2) {
        return new DeviceMetaData[i2];
    }
}
