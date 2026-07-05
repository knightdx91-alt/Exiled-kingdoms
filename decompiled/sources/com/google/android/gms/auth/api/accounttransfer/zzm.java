package com.google.android.gms.auth.api.accounttransfer;

import a.a;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.ArrayList;
import java.util.HashSet;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class zzm implements Parcelable.Creator<zzl> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzl createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        HashSet hashSet = new HashSet();
        int i2 = 0;
        ArrayList arrayListCreateTypedList = null;
        zzo zzoVar = null;
        int i3 = 0;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(header);
            if (fieldId == 1) {
                i3 = SafeParcelReader.readInt(parcel, header);
                hashSet.add(1);
            } else if (fieldId == 2) {
                arrayListCreateTypedList = SafeParcelReader.createTypedList(parcel, header, zzr.CREATOR);
                hashSet.add(2);
            } else if (fieldId == 3) {
                i2 = SafeParcelReader.readInt(parcel, header);
                hashSet.add(3);
            } else if (fieldId != 4) {
                SafeParcelReader.skipUnknownField(parcel, header);
            } else {
                zzoVar = (zzo) SafeParcelReader.createParcelable(parcel, header, zzo.CREATOR);
                hashSet.add(4);
            }
        }
        if (parcel.dataPosition() == iValidateObjectHeader) {
            return new zzl(hashSet, i3, arrayListCreateTypedList, i2, zzoVar);
        }
        throw new SafeParcelReader.ParseException(a.g(37, iValidateObjectHeader, "Overread allowed size end="), parcel);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzl[] newArray(int i2) {
        return new zzl[i2];
    }
}
