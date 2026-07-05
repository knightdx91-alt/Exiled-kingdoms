package com.google.android.gms.internal.drive;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.drive.DriveId;
import java.util.ArrayList;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class zzgr implements Parcelable.Creator<zzgq> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzgq createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        DriveId driveId = null;
        ArrayList arrayListCreateTypedList = null;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(header);
            if (fieldId == 2) {
                driveId = (DriveId) SafeParcelReader.createParcelable(parcel, header, DriveId.CREATOR);
            } else if (fieldId != 3) {
                SafeParcelReader.skipUnknownField(parcel, header);
            } else {
                arrayListCreateTypedList = SafeParcelReader.createTypedList(parcel, header, DriveId.CREATOR);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new zzgq(driveId, arrayListCreateTypedList);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzgq[] newArray(int i2) {
        return new zzgq[i2];
    }
}
