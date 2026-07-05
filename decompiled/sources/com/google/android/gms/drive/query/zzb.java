package com.google.android.gms.drive.query;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.drive.DriveSpace;
import com.google.android.gms.drive.query.internal.zzr;
import java.util.ArrayList;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class zzb implements Parcelable.Creator<Query> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Query createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        zzr zzrVar = null;
        String strCreateString = null;
        SortOrder sortOrder = null;
        ArrayList<String> arrayListCreateStringList = null;
        ArrayList arrayListCreateTypedList = null;
        boolean z2 = false;
        boolean z3 = false;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case 1:
                    zzrVar = (zzr) SafeParcelReader.createParcelable(parcel, header, zzr.CREATOR);
                    break;
                case 2:
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
                case 3:
                    strCreateString = SafeParcelReader.createString(parcel, header);
                    break;
                case 4:
                    sortOrder = (SortOrder) SafeParcelReader.createParcelable(parcel, header, SortOrder.CREATOR);
                    break;
                case 5:
                    arrayListCreateStringList = SafeParcelReader.createStringList(parcel, header);
                    break;
                case 6:
                    z2 = SafeParcelReader.readBoolean(parcel, header);
                    break;
                case 7:
                    arrayListCreateTypedList = SafeParcelReader.createTypedList(parcel, header, DriveSpace.CREATOR);
                    break;
                case 8:
                    z3 = SafeParcelReader.readBoolean(parcel, header);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new Query(zzrVar, strCreateString, sortOrder, arrayListCreateStringList, z2, arrayListCreateTypedList, z3);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Query[] newArray(int i2) {
        return new Query[i2];
    }
}
