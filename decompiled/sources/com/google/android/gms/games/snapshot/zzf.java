package com.google.android.gms.games.snapshot;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.games.GameEntity;
import com.google.android.gms.games.PlayerEntity;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class zzf implements Parcelable.Creator<SnapshotMetadataEntity> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ SnapshotMetadataEntity createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        GameEntity gameEntity = null;
        PlayerEntity playerEntity = null;
        String strCreateString = null;
        Uri uri = null;
        String strCreateString2 = null;
        String strCreateString3 = null;
        String strCreateString4 = null;
        String strCreateString5 = null;
        String strCreateString6 = null;
        long j2 = 0;
        long j3 = 0;
        long j4 = 0;
        float f2 = 0.0f;
        boolean z2 = false;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case 1:
                    gameEntity = (GameEntity) SafeParcelReader.createParcelable(parcel, header, GameEntity.CREATOR);
                    break;
                case 2:
                    playerEntity = (PlayerEntity) SafeParcelReader.createParcelable(parcel, header, PlayerEntity.CREATOR);
                    break;
                case 3:
                    strCreateString = SafeParcelReader.createString(parcel, header);
                    break;
                case 4:
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
                case 5:
                    uri = (Uri) SafeParcelReader.createParcelable(parcel, header, Uri.CREATOR);
                    break;
                case 6:
                    strCreateString2 = SafeParcelReader.createString(parcel, header);
                    break;
                case 7:
                    strCreateString3 = SafeParcelReader.createString(parcel, header);
                    break;
                case 8:
                    strCreateString4 = SafeParcelReader.createString(parcel, header);
                    break;
                case 9:
                    j2 = SafeParcelReader.readLong(parcel, header);
                    break;
                case 10:
                    j3 = SafeParcelReader.readLong(parcel, header);
                    break;
                case 11:
                    f2 = SafeParcelReader.readFloat(parcel, header);
                    break;
                case 12:
                    strCreateString5 = SafeParcelReader.createString(parcel, header);
                    break;
                case 13:
                    z2 = SafeParcelReader.readBoolean(parcel, header);
                    break;
                case 14:
                    j4 = SafeParcelReader.readLong(parcel, header);
                    break;
                case 15:
                    strCreateString6 = SafeParcelReader.createString(parcel, header);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new SnapshotMetadataEntity(gameEntity, playerEntity, strCreateString, uri, strCreateString2, strCreateString3, strCreateString4, j2, j3, f2, strCreateString5, z2, j4, strCreateString6);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ SnapshotMetadataEntity[] newArray(int i2) {
        return new SnapshotMetadataEntity[i2];
    }
}
