package com.google.android.gms.internal.drive;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.ArrayList;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class zzfg implements Parcelable.Creator<zzff> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzff createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        long j2 = 0;
        long j3 = 0;
        int i2 = 0;
        ArrayList arrayListCreateTypedList = null;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(header);
            if (fieldId == 2) {
                j2 = SafeParcelReader.readLong(parcel, header);
            } else if (fieldId == 3) {
                j3 = SafeParcelReader.readLong(parcel, header);
            } else if (fieldId == 4) {
                i2 = SafeParcelReader.readInt(parcel, header);
            } else if (fieldId != 5) {
                SafeParcelReader.skipUnknownField(parcel, header);
            } else {
                arrayListCreateTypedList = SafeParcelReader.createTypedList(parcel, header, com.google.android.gms.drive.zzh.CREATOR);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new zzff(j2, j3, i2, arrayListCreateTypedList);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzff[] newArray(int i2) {
        return new zzff[i2];
    }
}
