package com.google.android.gms.internal.drive;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.ArrayList;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class zzen implements Parcelable.Creator<zzem> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzem createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        ArrayList arrayListCreateTypedList = null;
        int i2 = 0;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(header);
            if (fieldId == 2) {
                arrayListCreateTypedList = SafeParcelReader.createTypedList(parcel, header, com.google.android.gms.drive.zzr.CREATOR);
            } else if (fieldId != 3) {
                SafeParcelReader.skipUnknownField(parcel, header);
            } else {
                i2 = SafeParcelReader.readInt(parcel, header);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new zzem(arrayListCreateTypedList, i2);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzem[] newArray(int i2) {
        return new zzem[i2];
    }
}
