package com.google.android.gms.auth.api.credentials;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class zzg implements Parcelable.Creator<CredentialRequest> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ CredentialRequest createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        int i2 = 0;
        boolean z2 = false;
        boolean z3 = false;
        boolean z4 = false;
        String[] strArrCreateStringArray = null;
        CredentialPickerConfig credentialPickerConfig = null;
        CredentialPickerConfig credentialPickerConfig2 = null;
        String strCreateString = null;
        String strCreateString2 = null;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(header);
            if (fieldId != 1000) {
                switch (fieldId) {
                    case 1:
                        z2 = SafeParcelReader.readBoolean(parcel, header);
                        break;
                    case 2:
                        strArrCreateStringArray = SafeParcelReader.createStringArray(parcel, header);
                        break;
                    case 3:
                        credentialPickerConfig = (CredentialPickerConfig) SafeParcelReader.createParcelable(parcel, header, CredentialPickerConfig.CREATOR);
                        break;
                    case 4:
                        credentialPickerConfig2 = (CredentialPickerConfig) SafeParcelReader.createParcelable(parcel, header, CredentialPickerConfig.CREATOR);
                        break;
                    case 5:
                        z3 = SafeParcelReader.readBoolean(parcel, header);
                        break;
                    case 6:
                        strCreateString = SafeParcelReader.createString(parcel, header);
                        break;
                    case 7:
                        strCreateString2 = SafeParcelReader.createString(parcel, header);
                        break;
                    case 8:
                        z4 = SafeParcelReader.readBoolean(parcel, header);
                        break;
                    default:
                        SafeParcelReader.skipUnknownField(parcel, header);
                        break;
                }
            } else {
                i2 = SafeParcelReader.readInt(parcel, header);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new CredentialRequest(i2, z2, strArrCreateStringArray, credentialPickerConfig, credentialPickerConfig2, z3, strCreateString, strCreateString2, z4);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ CredentialRequest[] newArray(int i2) {
        return new CredentialRequest[i2];
    }
}
