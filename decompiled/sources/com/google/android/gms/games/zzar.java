package com.google.android.gms.games;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class zzar implements Parcelable.Creator<PlayerLevelInfo> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ PlayerLevelInfo createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        long j2 = 0;
        long j3 = 0;
        PlayerLevel playerLevel = null;
        PlayerLevel playerLevel2 = null;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(header);
            if (fieldId == 1) {
                j2 = SafeParcelReader.readLong(parcel, header);
            } else if (fieldId == 2) {
                j3 = SafeParcelReader.readLong(parcel, header);
            } else if (fieldId == 3) {
                playerLevel = (PlayerLevel) SafeParcelReader.createParcelable(parcel, header, PlayerLevel.CREATOR);
            } else if (fieldId != 4) {
                SafeParcelReader.skipUnknownField(parcel, header);
            } else {
                playerLevel2 = (PlayerLevel) SafeParcelReader.createParcelable(parcel, header, PlayerLevel.CREATOR);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new PlayerLevelInfo(j2, j3, playerLevel, playerLevel2);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ PlayerLevelInfo[] newArray(int i2) {
        return new PlayerLevelInfo[i2];
    }
}
