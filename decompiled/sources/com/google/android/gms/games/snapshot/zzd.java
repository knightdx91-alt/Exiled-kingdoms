package com.google.android.gms.games.snapshot;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.data.BitmapTeleporter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class zzd implements Parcelable.Creator<zze> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zze createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        String strCreateString = null;
        Long longObject = null;
        BitmapTeleporter bitmapTeleporter = null;
        Uri uri = null;
        Long longObject2 = null;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(header);
            if (fieldId == 1) {
                strCreateString = SafeParcelReader.createString(parcel, header);
            } else if (fieldId == 2) {
                longObject = SafeParcelReader.readLongObject(parcel, header);
            } else if (fieldId == 4) {
                uri = (Uri) SafeParcelReader.createParcelable(parcel, header, Uri.CREATOR);
            } else if (fieldId == 5) {
                bitmapTeleporter = (BitmapTeleporter) SafeParcelReader.createParcelable(parcel, header, BitmapTeleporter.CREATOR);
            } else if (fieldId != 6) {
                SafeParcelReader.skipUnknownField(parcel, header);
            } else {
                longObject2 = SafeParcelReader.readLongObject(parcel, header);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new zze(strCreateString, longObject, bitmapTeleporter, uri, longObject2);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zze[] newArray(int i2) {
        return new zze[i2];
    }
}
