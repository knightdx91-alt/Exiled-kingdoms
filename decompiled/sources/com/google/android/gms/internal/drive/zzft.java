package com.google.android.gms.internal.drive;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class zzft implements Parcelable.Creator<zzfs> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzfs createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        MetadataBundle metadataBundle = null;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            if (SafeParcelReader.getFieldId(header) != 2) {
                SafeParcelReader.skipUnknownField(parcel, header);
            } else {
                metadataBundle = (MetadataBundle) SafeParcelReader.createParcelable(parcel, header, MetadataBundle.CREATOR);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new zzfs(metadataBundle);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzfs[] newArray(int i2) {
        return new zzfs[i2];
    }
}
