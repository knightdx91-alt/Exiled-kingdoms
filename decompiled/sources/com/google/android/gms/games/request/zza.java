package com.google.android.gms.games.request;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.games.GameEntity;
import com.google.android.gms.games.PlayerEntity;
import java.util.ArrayList;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class zza implements Parcelable.Creator<GameRequestEntity> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ GameRequestEntity createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        GameEntity gameEntity = null;
        PlayerEntity playerEntity = null;
        byte[] bArrCreateByteArray = null;
        String strCreateString = null;
        ArrayList arrayListCreateTypedList = null;
        Bundle bundleCreateBundle = null;
        int i2 = 0;
        int i3 = 0;
        long j2 = 0;
        long j3 = 0;
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
                    bArrCreateByteArray = SafeParcelReader.createByteArray(parcel, header);
                    break;
                case 4:
                    strCreateString = SafeParcelReader.createString(parcel, header);
                    break;
                case 5:
                    arrayListCreateTypedList = SafeParcelReader.createTypedList(parcel, header, PlayerEntity.CREATOR);
                    break;
                case 6:
                case 8:
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
                case 7:
                    i2 = SafeParcelReader.readInt(parcel, header);
                    break;
                case 9:
                    j2 = SafeParcelReader.readLong(parcel, header);
                    break;
                case 10:
                    j3 = SafeParcelReader.readLong(parcel, header);
                    break;
                case 11:
                    bundleCreateBundle = SafeParcelReader.createBundle(parcel, header);
                    break;
                case 12:
                    i3 = SafeParcelReader.readInt(parcel, header);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new GameRequestEntity(gameEntity, playerEntity, bArrCreateByteArray, strCreateString, arrayListCreateTypedList, i2, j2, j3, bundleCreateBundle, i3);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ GameRequestEntity[] newArray(int i2) {
        return new GameRequestEntity[i2];
    }
}
