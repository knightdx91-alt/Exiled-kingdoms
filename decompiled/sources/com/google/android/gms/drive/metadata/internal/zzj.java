package com.google.android.gms.drive.metadata.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class zzj implements Parcelable.Creator<MetadataBundle> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ MetadataBundle createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        Bundle bundleCreateBundle = null;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            if (SafeParcelReader.getFieldId(header) != 2) {
                SafeParcelReader.skipUnknownField(parcel, header);
            } else {
                bundleCreateBundle = SafeParcelReader.createBundle(parcel, header);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new MetadataBundle(bundleCreateBundle);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ MetadataBundle[] newArray(int i2) {
        return new MetadataBundle[i2];
    }
}
