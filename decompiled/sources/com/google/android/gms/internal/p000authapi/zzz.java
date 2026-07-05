package com.google.android.gms.internal.p000authapi;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.auth.api.credentials.Credential;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class zzz implements Parcelable.Creator<zzy> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzy createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        Credential credential = null;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            if (SafeParcelReader.getFieldId(header) != 1) {
                SafeParcelReader.skipUnknownField(parcel, header);
            } else {
                credential = (Credential) SafeParcelReader.createParcelable(parcel, header, Credential.CREATOR);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new zzy(credential);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzy[] newArray(int i2) {
        return new zzy[i2];
    }
}
