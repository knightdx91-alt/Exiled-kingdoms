package com.google.android.gms.drive.events;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.drive.DriveSpace;
import java.util.ArrayList;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class zzf implements Parcelable.Creator<zze> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zze createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        int i2 = 0;
        ArrayList arrayListCreateTypedList = null;
        boolean z2 = false;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(header);
            if (fieldId == 2) {
                i2 = SafeParcelReader.readInt(parcel, header);
            } else if (fieldId == 3) {
                z2 = SafeParcelReader.readBoolean(parcel, header);
            } else if (fieldId != 4) {
                SafeParcelReader.skipUnknownField(parcel, header);
            } else {
                arrayListCreateTypedList = SafeParcelReader.createTypedList(parcel, header, DriveSpace.CREATOR);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new zze(i2, z2, arrayListCreateTypedList);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zze[] newArray(int i2) {
        return new zze[i2];
    }
}
