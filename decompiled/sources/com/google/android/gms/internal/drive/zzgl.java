package com.google.android.gms.internal.drive;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.drive.query.Query;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class zzgl implements Parcelable.Creator<zzgk> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzgk createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        Query query = null;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            if (SafeParcelReader.getFieldId(header) != 2) {
                SafeParcelReader.skipUnknownField(parcel, header);
            } else {
                query = (Query) SafeParcelReader.createParcelable(parcel, header, Query.CREATOR);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new zzgk(query);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzgk[] newArray(int i2) {
        return new zzgk[i2];
    }
}
