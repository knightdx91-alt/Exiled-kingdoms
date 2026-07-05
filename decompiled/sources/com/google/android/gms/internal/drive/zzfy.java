package com.google.android.gms.internal.drive;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.ArrayList;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class zzfy implements Parcelable.Creator<zzfx> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzfx createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        ArrayList<String> arrayListCreateStringList = null;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            if (SafeParcelReader.getFieldId(header) != 2) {
                SafeParcelReader.skipUnknownField(parcel, header);
            } else {
                arrayListCreateStringList = SafeParcelReader.createStringList(parcel, header);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new zzfx(arrayListCreateStringList);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzfx[] newArray(int i2) {
        return new zzfx[i2];
    }
}
