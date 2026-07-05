package com.google.android.gms.games.internal.player;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class zzf implements Parcelable.Creator<StockProfileImageEntity> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ StockProfileImageEntity createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        String strCreateString = null;
        Uri uri = null;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(header);
            if (fieldId == 1) {
                strCreateString = SafeParcelReader.createString(parcel, header);
            } else if (fieldId != 2) {
                SafeParcelReader.skipUnknownField(parcel, header);
            } else {
                uri = (Uri) SafeParcelReader.createParcelable(parcel, header, Uri.CREATOR);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new StockProfileImageEntity(strCreateString, uri);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ StockProfileImageEntity[] newArray(int i2) {
        return new StockProfileImageEntity[i2];
    }
}
