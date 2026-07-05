package com.google.android.gms.internal.drive;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.ArrayList;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class zzgu implements Parcelable.Creator<zzgt> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzgt createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        ArrayList<String> arrayListCreateStringList = null;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            if (SafeParcelReader.getFieldId(header) != 2) {
                SafeParcelReader.skipUnknownField(parcel, header);
            } else {
                arrayListCreateStringList = SafeParcelReader.createStringList(parcel, header);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new zzgt(arrayListCreateStringList);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzgt[] newArray(int i2) {
        return new zzgt[i2];
    }
}
