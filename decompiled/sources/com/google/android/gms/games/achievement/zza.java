package com.google.android.gms.games.achievement;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.games.PlayerEntity;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class zza implements Parcelable.Creator<AchievementEntity> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ AchievementEntity createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        String strCreateString = null;
        String strCreateString2 = null;
        String strCreateString3 = null;
        Uri uri = null;
        String strCreateString4 = null;
        Uri uri2 = null;
        String strCreateString5 = null;
        String strCreateString6 = null;
        PlayerEntity playerEntity = null;
        String strCreateString7 = null;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        long j2 = 0;
        long j3 = 0;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case 1:
                    strCreateString = SafeParcelReader.createString(parcel, header);
                    break;
                case 2:
                    i2 = SafeParcelReader.readInt(parcel, header);
                    break;
                case 3:
                    strCreateString2 = SafeParcelReader.createString(parcel, header);
                    break;
                case 4:
                    strCreateString3 = SafeParcelReader.createString(parcel, header);
                    break;
                case 5:
                    uri = (Uri) SafeParcelReader.createParcelable(parcel, header, Uri.CREATOR);
                    break;
                case 6:
                    strCreateString4 = SafeParcelReader.createString(parcel, header);
                    break;
                case 7:
                    uri2 = (Uri) SafeParcelReader.createParcelable(parcel, header, Uri.CREATOR);
                    break;
                case 8:
                    strCreateString5 = SafeParcelReader.createString(parcel, header);
                    break;
                case 9:
                    i3 = SafeParcelReader.readInt(parcel, header);
                    break;
                case 10:
                    strCreateString6 = SafeParcelReader.createString(parcel, header);
                    break;
                case 11:
                    playerEntity = (PlayerEntity) SafeParcelReader.createParcelable(parcel, header, PlayerEntity.CREATOR);
                    break;
                case 12:
                    i4 = SafeParcelReader.readInt(parcel, header);
                    break;
                case 13:
                    i5 = SafeParcelReader.readInt(parcel, header);
                    break;
                case 14:
                    strCreateString7 = SafeParcelReader.createString(parcel, header);
                    break;
                case 15:
                    j2 = SafeParcelReader.readLong(parcel, header);
                    break;
                case 16:
                    j3 = SafeParcelReader.readLong(parcel, header);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new AchievementEntity(strCreateString, i2, strCreateString2, strCreateString3, uri, strCreateString4, uri2, strCreateString5, i3, strCreateString6, playerEntity, i4, i5, strCreateString7, j2, j3);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ AchievementEntity[] newArray(int i2) {
        return new AchievementEntity[i2];
    }
}
