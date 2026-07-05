package com.google.android.gms.games.video;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class zza implements Parcelable.Creator<VideoCapabilities> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ VideoCapabilities createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        boolean z2 = false;
        boolean z3 = false;
        boolean z4 = false;
        boolean[] zArrCreateBooleanArray = null;
        boolean[] zArrCreateBooleanArray2 = null;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(header);
            if (fieldId == 1) {
                z2 = SafeParcelReader.readBoolean(parcel, header);
            } else if (fieldId == 2) {
                z3 = SafeParcelReader.readBoolean(parcel, header);
            } else if (fieldId == 3) {
                z4 = SafeParcelReader.readBoolean(parcel, header);
            } else if (fieldId == 4) {
                zArrCreateBooleanArray = SafeParcelReader.createBooleanArray(parcel, header);
            } else if (fieldId != 5) {
                SafeParcelReader.skipUnknownField(parcel, header);
            } else {
                zArrCreateBooleanArray2 = SafeParcelReader.createBooleanArray(parcel, header);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new VideoCapabilities(z2, z3, z4, zArrCreateBooleanArray, zArrCreateBooleanArray2);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ VideoCapabilities[] newArray(int i2) {
        return new VideoCapabilities[i2];
    }
}
