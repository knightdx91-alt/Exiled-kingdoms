package com.google.android.gms.games.multiplayer;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.games.PlayerEntity;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class zzc implements Parcelable.Creator<ParticipantEntity> {
    @Override // android.os.Parcelable.Creator
    public /* synthetic */ ParticipantEntity[] newArray(int i2) {
        return new ParticipantEntity[i2];
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: zze */
    public ParticipantEntity createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        String strCreateString = null;
        String strCreateString2 = null;
        Uri uri = null;
        Uri uri2 = null;
        String strCreateString3 = null;
        PlayerEntity playerEntity = null;
        ParticipantResult participantResult = null;
        String strCreateString4 = null;
        String strCreateString5 = null;
        int i2 = 0;
        boolean z2 = false;
        int i3 = 0;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case 1:
                    strCreateString = SafeParcelReader.createString(parcel, header);
                    break;
                case 2:
                    strCreateString2 = SafeParcelReader.createString(parcel, header);
                    break;
                case 3:
                    uri = (Uri) SafeParcelReader.createParcelable(parcel, header, Uri.CREATOR);
                    break;
                case 4:
                    uri2 = (Uri) SafeParcelReader.createParcelable(parcel, header, Uri.CREATOR);
                    break;
                case 5:
                    i2 = SafeParcelReader.readInt(parcel, header);
                    break;
                case 6:
                    strCreateString3 = SafeParcelReader.createString(parcel, header);
                    break;
                case 7:
                    z2 = SafeParcelReader.readBoolean(parcel, header);
                    break;
                case 8:
                    playerEntity = (PlayerEntity) SafeParcelReader.createParcelable(parcel, header, PlayerEntity.CREATOR);
                    break;
                case 9:
                    i3 = SafeParcelReader.readInt(parcel, header);
                    break;
                case 10:
                    participantResult = (ParticipantResult) SafeParcelReader.createParcelable(parcel, header, ParticipantResult.CREATOR);
                    break;
                case 11:
                    strCreateString4 = SafeParcelReader.createString(parcel, header);
                    break;
                case 12:
                    strCreateString5 = SafeParcelReader.createString(parcel, header);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new ParticipantEntity(strCreateString, strCreateString2, uri, uri2, i2, strCreateString3, z2, playerEntity, i3, participantResult, strCreateString4, strCreateString5);
    }
}
