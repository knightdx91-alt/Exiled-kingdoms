package com.google.android.gms.games.internal.experience;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.games.GameEntity;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class zza implements Parcelable.Creator<ExperienceEventEntity> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ ExperienceEventEntity createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        String strCreateString = null;
        GameEntity gameEntity = null;
        String strCreateString2 = null;
        String strCreateString3 = null;
        String strCreateString4 = null;
        Uri uri = null;
        long j2 = 0;
        long j3 = 0;
        long j4 = 0;
        int i2 = 0;
        int i3 = 0;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case 1:
                    strCreateString = SafeParcelReader.createString(parcel, header);
                    break;
                case 2:
                    gameEntity = (GameEntity) SafeParcelReader.createParcelable(parcel, header, GameEntity.CREATOR);
                    break;
                case 3:
                    strCreateString2 = SafeParcelReader.createString(parcel, header);
                    break;
                case 4:
                    strCreateString3 = SafeParcelReader.createString(parcel, header);
                    break;
                case 5:
                    strCreateString4 = SafeParcelReader.createString(parcel, header);
                    break;
                case 6:
                    uri = (Uri) SafeParcelReader.createParcelable(parcel, header, Uri.CREATOR);
                    break;
                case 7:
                    j2 = SafeParcelReader.readLong(parcel, header);
                    break;
                case 8:
                    j3 = SafeParcelReader.readLong(parcel, header);
                    break;
                case 9:
                    j4 = SafeParcelReader.readLong(parcel, header);
                    break;
                case 10:
                    i2 = SafeParcelReader.readInt(parcel, header);
                    break;
                case 11:
                    i3 = SafeParcelReader.readInt(parcel, header);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new ExperienceEventEntity(strCreateString, gameEntity, strCreateString2, strCreateString3, strCreateString4, uri, j2, j3, j4, i2, i3);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ ExperienceEventEntity[] newArray(int i2) {
        return new ExperienceEventEntity[i2];
    }
}
