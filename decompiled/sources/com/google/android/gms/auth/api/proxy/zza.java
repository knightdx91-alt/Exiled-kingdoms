package com.google.android.gms.auth.api.proxy;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class zza implements Parcelable.Creator<ProxyRequest> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ ProxyRequest createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        int i2 = 0;
        int i3 = 0;
        String strCreateString = null;
        byte[] bArrCreateByteArray = null;
        Bundle bundleCreateBundle = null;
        long j2 = 0;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(header);
            if (fieldId == 1) {
                strCreateString = SafeParcelReader.createString(parcel, header);
            } else if (fieldId == 2) {
                i3 = SafeParcelReader.readInt(parcel, header);
            } else if (fieldId == 3) {
                j2 = SafeParcelReader.readLong(parcel, header);
            } else if (fieldId == 4) {
                bArrCreateByteArray = SafeParcelReader.createByteArray(parcel, header);
            } else if (fieldId == 5) {
                bundleCreateBundle = SafeParcelReader.createBundle(parcel, header);
            } else if (fieldId != 1000) {
                SafeParcelReader.skipUnknownField(parcel, header);
            } else {
                i2 = SafeParcelReader.readInt(parcel, header);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new ProxyRequest(i2, strCreateString, i3, j2, bArrCreateByteArray, bundleCreateBundle);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ ProxyRequest[] newArray(int i2) {
        return new ProxyRequest[i2];
    }
}
