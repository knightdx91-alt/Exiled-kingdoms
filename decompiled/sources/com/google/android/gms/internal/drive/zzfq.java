package com.google.android.gms.internal.drive;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class zzfq implements Parcelable.Creator<zzfp> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzfp createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        DataHolder dataHolder = null;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            if (SafeParcelReader.getFieldId(header) != 2) {
                SafeParcelReader.skipUnknownField(parcel, header);
            } else {
                dataHolder = (DataHolder) SafeParcelReader.createParcelable(parcel, header, DataHolder.CREATOR);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new zzfp(dataHolder);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzfp[] newArray(int i2) {
        return new zzfp[i2];
    }
}
