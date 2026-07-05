package com.google.android.gms.internal.drive;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class zzgc implements Parcelable.Creator<zzgb> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzgb createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        boolean z2 = false;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            if (SafeParcelReader.getFieldId(header) != 2) {
                SafeParcelReader.skipUnknownField(parcel, header);
            } else {
                z2 = SafeParcelReader.readBoolean(parcel, header);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new zzgb(z2);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzgb[] newArray(int i2) {
        return new zzgb[i2];
    }
}
