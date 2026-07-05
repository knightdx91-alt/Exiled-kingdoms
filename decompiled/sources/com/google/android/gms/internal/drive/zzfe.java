package com.google.android.gms.internal.drive;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class zzfe implements Parcelable.Creator<zzfd> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzfd createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        zzei zzeiVar = null;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            if (SafeParcelReader.getFieldId(header) != 2) {
                SafeParcelReader.skipUnknownField(parcel, header);
            } else {
                zzeiVar = (zzei) SafeParcelReader.createParcelable(parcel, header, zzei.CREATOR);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new zzfd(zzeiVar);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzfd[] newArray(int i2) {
        return new zzfd[i2];
    }
}
