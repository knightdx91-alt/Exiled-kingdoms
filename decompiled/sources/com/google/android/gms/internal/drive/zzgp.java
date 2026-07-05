package com.google.android.gms.internal.drive;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class zzgp implements Parcelable.Creator<zzgo> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzgo createFromParcel(Parcel parcel) {
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
        return new zzgo(zzeiVar);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzgo[] newArray(int i2) {
        return new zzgo[i2];
    }
}
