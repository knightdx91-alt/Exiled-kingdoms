package com.google.android.gms.games;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class zzaq implements Parcelable.Creator<PlayerLevel> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ PlayerLevel createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        int i2 = 0;
        long j2 = 0;
        long j3 = 0;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(header);
            if (fieldId == 1) {
                i2 = SafeParcelReader.readInt(parcel, header);
            } else if (fieldId == 2) {
                j2 = SafeParcelReader.readLong(parcel, header);
            } else if (fieldId != 3) {
                SafeParcelReader.skipUnknownField(parcel, header);
            } else {
                j3 = SafeParcelReader.readLong(parcel, header);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new PlayerLevel(i2, j2, j3);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ PlayerLevel[] newArray(int i2) {
        return new PlayerLevel[i2];
    }
}
