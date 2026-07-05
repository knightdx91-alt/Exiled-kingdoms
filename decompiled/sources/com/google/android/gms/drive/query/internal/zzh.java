package com.google.android.gms.drive.query.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class zzh implements Parcelable.Creator<FilterHolder> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ FilterHolder createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        zzb zzbVar = null;
        zzd zzdVar = null;
        zzr zzrVar = null;
        zzv zzvVar = null;
        zzp zzpVar = null;
        zzt zztVar = null;
        zzn zznVar = null;
        zzl zzlVar = null;
        zzz zzzVar = null;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case 1:
                    zzbVar = (zzb) SafeParcelReader.createParcelable(parcel, header, zzb.CREATOR);
                    break;
                case 2:
                    zzdVar = (zzd) SafeParcelReader.createParcelable(parcel, header, zzd.CREATOR);
                    break;
                case 3:
                    zzrVar = (zzr) SafeParcelReader.createParcelable(parcel, header, zzr.CREATOR);
                    break;
                case 4:
                    zzvVar = (zzv) SafeParcelReader.createParcelable(parcel, header, zzv.CREATOR);
                    break;
                case 5:
                    zzpVar = (zzp) SafeParcelReader.createParcelable(parcel, header, zzp.CREATOR);
                    break;
                case 6:
                    zztVar = (zzt) SafeParcelReader.createParcelable(parcel, header, zzt.CREATOR);
                    break;
                case 7:
                    zznVar = (zzn) SafeParcelReader.createParcelable(parcel, header, zzn.CREATOR);
                    break;
                case 8:
                    zzlVar = (zzl) SafeParcelReader.createParcelable(parcel, header, zzl.CREATOR);
                    break;
                case 9:
                    zzzVar = (zzz) SafeParcelReader.createParcelable(parcel, header, zzz.CREATOR);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new FilterHolder(zzbVar, zzdVar, zzrVar, zzvVar, zzpVar, zztVar, zznVar, zzlVar, zzzVar);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ FilterHolder[] newArray(int i2) {
        return new FilterHolder[i2];
    }
}
