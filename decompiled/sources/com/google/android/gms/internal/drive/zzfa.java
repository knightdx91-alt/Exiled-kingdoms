package com.google.android.gms.internal.drive;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.drive.DriveId;
import java.util.ArrayList;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class zzfa implements Parcelable.Creator<zzez> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzez createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        DataHolder dataHolder = null;
        com.google.android.gms.drive.zza zzaVar = null;
        boolean z2 = false;
        ArrayList arrayListCreateTypedList = null;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(header);
            if (fieldId == 2) {
                dataHolder = (DataHolder) SafeParcelReader.createParcelable(parcel, header, DataHolder.CREATOR);
            } else if (fieldId == 3) {
                arrayListCreateTypedList = SafeParcelReader.createTypedList(parcel, header, DriveId.CREATOR);
            } else if (fieldId == 4) {
                zzaVar = (com.google.android.gms.drive.zza) SafeParcelReader.createParcelable(parcel, header, com.google.android.gms.drive.zza.CREATOR);
            } else if (fieldId != 5) {
                SafeParcelReader.skipUnknownField(parcel, header);
            } else {
                z2 = SafeParcelReader.readBoolean(parcel, header);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new zzez(dataHolder, arrayListCreateTypedList, zzaVar, z2);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzez[] newArray(int i2) {
        return new zzez[i2];
    }
}
