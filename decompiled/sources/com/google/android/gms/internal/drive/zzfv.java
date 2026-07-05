package com.google.android.gms.internal.drive;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class zzfv implements Parcelable.Creator<zzfu> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzfu createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        zzgi zzgiVar = null;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            if (SafeParcelReader.getFieldId(header) != 2) {
                SafeParcelReader.skipUnknownField(parcel, header);
            } else {
                zzgiVar = (zzgi) SafeParcelReader.createParcelable(parcel, header, zzgi.CREATOR);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new zzfu(zzgiVar);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzfu[] newArray(int i2) {
        return new zzfu[i2];
    }
}
