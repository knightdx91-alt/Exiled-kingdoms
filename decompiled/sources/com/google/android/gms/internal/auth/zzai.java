package com.google.android.gms.internal.auth;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class zzai implements Parcelable.Creator<zzah> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzah createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        int i2 = 0;
        String strCreateString = null;
        PendingIntent pendingIntent = null;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(header);
            if (fieldId == 1) {
                i2 = SafeParcelReader.readInt(parcel, header);
            } else if (fieldId == 2) {
                strCreateString = SafeParcelReader.createString(parcel, header);
            } else if (fieldId != 3) {
                SafeParcelReader.skipUnknownField(parcel, header);
            } else {
                pendingIntent = (PendingIntent) SafeParcelReader.createParcelable(parcel, header, PendingIntent.CREATOR);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new zzah(i2, strCreateString, pendingIntent);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzah[] newArray(int i2) {
        return new zzah[i2];
    }
}
