package com.google.android.gms.auth.api.accounttransfer;

import a.a;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.HashSet;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class zzs implements Parcelable.Creator<zzr> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzr createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        HashSet hashSet = new HashSet();
        zzt zztVar = null;
        String strCreateString = null;
        String strCreateString2 = null;
        String strCreateString3 = null;
        int i2 = 0;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(header);
            if (fieldId == 1) {
                i2 = SafeParcelReader.readInt(parcel, header);
                hashSet.add(1);
            } else if (fieldId == 2) {
                zztVar = (zzt) SafeParcelReader.createParcelable(parcel, header, zzt.CREATOR);
                hashSet.add(2);
            } else if (fieldId == 3) {
                strCreateString = SafeParcelReader.createString(parcel, header);
                hashSet.add(3);
            } else if (fieldId == 4) {
                strCreateString2 = SafeParcelReader.createString(parcel, header);
                hashSet.add(4);
            } else if (fieldId != 5) {
                SafeParcelReader.skipUnknownField(parcel, header);
            } else {
                strCreateString3 = SafeParcelReader.createString(parcel, header);
                hashSet.add(5);
            }
        }
        if (parcel.dataPosition() == iValidateObjectHeader) {
            return new zzr(hashSet, i2, zztVar, strCreateString, strCreateString2, strCreateString3);
        }
        throw new SafeParcelReader.ParseException(a.g(37, iValidateObjectHeader, "Overread allowed size end="), parcel);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzr[] newArray(int i2) {
        return new zzr[i2];
    }
}
