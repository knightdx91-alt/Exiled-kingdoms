package com.google.android.gms.games.quest;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.games.GameEntity;
import java.util.ArrayList;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class zzc implements Parcelable.Creator<QuestEntity> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ QuestEntity createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        GameEntity gameEntity = null;
        String strCreateString = null;
        Uri uri = null;
        String strCreateString2 = null;
        String strCreateString3 = null;
        Uri uri2 = null;
        String strCreateString4 = null;
        String strCreateString5 = null;
        ArrayList arrayListCreateTypedList = null;
        long j2 = 0;
        long j3 = 0;
        long j4 = 0;
        long j5 = 0;
        long j6 = 0;
        int i2 = 0;
        int i3 = 0;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case 1:
                    gameEntity = (GameEntity) SafeParcelReader.createParcelable(parcel, header, GameEntity.CREATOR);
                    break;
                case 2:
                    strCreateString = SafeParcelReader.createString(parcel, header);
                    break;
                case 3:
                    j2 = SafeParcelReader.readLong(parcel, header);
                    break;
                case 4:
                    uri = (Uri) SafeParcelReader.createParcelable(parcel, header, Uri.CREATOR);
                    break;
                case 5:
                    strCreateString2 = SafeParcelReader.createString(parcel, header);
                    break;
                case 6:
                    strCreateString3 = SafeParcelReader.createString(parcel, header);
                    break;
                case 7:
                    j3 = SafeParcelReader.readLong(parcel, header);
                    break;
                case 8:
                    j4 = SafeParcelReader.readLong(parcel, header);
                    break;
                case 9:
                    uri2 = (Uri) SafeParcelReader.createParcelable(parcel, header, Uri.CREATOR);
                    break;
                case 10:
                    strCreateString4 = SafeParcelReader.createString(parcel, header);
                    break;
                case 11:
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
                case 12:
                    strCreateString5 = SafeParcelReader.createString(parcel, header);
                    break;
                case 13:
                    j5 = SafeParcelReader.readLong(parcel, header);
                    break;
                case 14:
                    j6 = SafeParcelReader.readLong(parcel, header);
                    break;
                case 15:
                    i2 = SafeParcelReader.readInt(parcel, header);
                    break;
                case 16:
                    i3 = SafeParcelReader.readInt(parcel, header);
                    break;
                case 17:
                    arrayListCreateTypedList = SafeParcelReader.createTypedList(parcel, header, MilestoneEntity.CREATOR);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new QuestEntity(gameEntity, strCreateString, j2, uri, strCreateString2, strCreateString3, j3, j4, uri2, strCreateString4, strCreateString5, j5, j6, i2, i3, arrayListCreateTypedList);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ QuestEntity[] newArray(int i2) {
        return new QuestEntity[i2];
    }
}
