package com.google.android.gms.internal.drive;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class zzfo implements Parcelable.Creator<zzfn> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzfn createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        DataHolder dataHolder = null;
        boolean z2 = false;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(header);
            if (fieldId == 2) {
                dataHolder = (DataHolder) SafeParcelReader.createParcelable(parcel, header, DataHolder.CREATOR);
            } else if (fieldId != 3) {
                SafeParcelReader.skipUnknownField(parcel, header);
            } else {
                z2 = SafeParcelReader.readBoolean(parcel, header);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new zzfn(dataHolder, z2);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzfn[] newArray(int i2) {
        return new zzfn[i2];
    }
}
