package com.google.android.gms.games.snapshot;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.drive.Contents;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class zzb implements Parcelable.Creator<zza> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zza createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        Contents contents = null;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            if (SafeParcelReader.getFieldId(header) != 1) {
                SafeParcelReader.skipUnknownField(parcel, header);
            } else {
                contents = (Contents) SafeParcelReader.createParcelable(parcel, header, Contents.CREATOR);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new zza(contents);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zza[] newArray(int i2) {
        return new zza[i2];
    }
}
