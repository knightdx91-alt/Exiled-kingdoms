package com.google.android.gms.auth.api.accounttransfer;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.ArrayList;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class zzp implements Parcelable.Creator<zzo> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzo createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        int i2 = 0;
        ArrayList<String> arrayListCreateStringList = null;
        ArrayList<String> arrayListCreateStringList2 = null;
        ArrayList<String> arrayListCreateStringList3 = null;
        ArrayList<String> arrayListCreateStringList4 = null;
        ArrayList<String> arrayListCreateStringList5 = null;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case 1:
                    i2 = SafeParcelReader.readInt(parcel, header);
                    break;
                case 2:
                    arrayListCreateStringList = SafeParcelReader.createStringList(parcel, header);
                    break;
                case 3:
                    arrayListCreateStringList2 = SafeParcelReader.createStringList(parcel, header);
                    break;
                case 4:
                    arrayListCreateStringList3 = SafeParcelReader.createStringList(parcel, header);
                    break;
                case 5:
                    arrayListCreateStringList4 = SafeParcelReader.createStringList(parcel, header);
                    break;
                case 6:
                    arrayListCreateStringList5 = SafeParcelReader.createStringList(parcel, header);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new zzo(i2, arrayListCreateStringList, arrayListCreateStringList2, arrayListCreateStringList3, arrayListCreateStringList4, arrayListCreateStringList5);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzo[] newArray(int i2) {
        return new zzo[i2];
    }
}
