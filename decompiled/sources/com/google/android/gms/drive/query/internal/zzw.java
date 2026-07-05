package com.google.android.gms.drive.query.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class zzw implements Parcelable.Creator<zzv> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzv createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        FilterHolder filterHolder = null;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            if (SafeParcelReader.getFieldId(header) != 1) {
                SafeParcelReader.skipUnknownField(parcel, header);
            } else {
                filterHolder = (FilterHolder) SafeParcelReader.createParcelable(parcel, header, FilterHolder.CREATOR);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new zzv(filterHolder);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzv[] newArray(int i2) {
        return new zzv[i2];
    }
}
