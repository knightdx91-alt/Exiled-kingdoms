package com.google.android.gms.drive.events;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class zzc implements Parcelable.Creator<zzb> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzb createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        zze zzeVar = null;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            if (SafeParcelReader.getFieldId(header) != 3) {
                SafeParcelReader.skipUnknownField(parcel, header);
            } else {
                zzeVar = (zze) SafeParcelReader.createParcelable(parcel, header, zze.CREATOR);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new zzb(zzeVar);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzb[] newArray(int i2) {
        return new zzb[i2];
    }
}
