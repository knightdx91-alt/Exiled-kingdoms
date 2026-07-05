package com.google.android.gms.games.multiplayer.turnbased;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.badlogic.gdx.graphics.g3d.decals.Decal;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.games.GameEntity;
import com.google.android.gms.games.multiplayer.ParticipantEntity;
import java.util.ArrayList;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class zzc implements Parcelable.Creator<TurnBasedMatchEntity> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ TurnBasedMatchEntity createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        GameEntity gameEntity = null;
        String strCreateString = null;
        String strCreateString2 = null;
        String strCreateString3 = null;
        String strCreateString4 = null;
        byte[] bArrCreateByteArray = null;
        ArrayList arrayListCreateTypedList = null;
        String strCreateString5 = null;
        byte[] bArrCreateByteArray2 = null;
        Bundle bundleCreateBundle = null;
        String strCreateString6 = null;
        String strCreateString7 = null;
        long j2 = 0;
        long j3 = 0;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        boolean z2 = false;
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
                    strCreateString2 = SafeParcelReader.createString(parcel, header);
                    break;
                case 4:
                    j2 = SafeParcelReader.readLong(parcel, header);
                    break;
                case 5:
                    strCreateString3 = SafeParcelReader.createString(parcel, header);
                    break;
                case 6:
                    j3 = SafeParcelReader.readLong(parcel, header);
                    break;
                case 7:
                    strCreateString4 = SafeParcelReader.createString(parcel, header);
                    break;
                case 8:
                    i2 = SafeParcelReader.readInt(parcel, header);
                    break;
                case 9:
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
                case 10:
                    i3 = SafeParcelReader.readInt(parcel, header);
                    break;
                case 11:
                    i4 = SafeParcelReader.readInt(parcel, header);
                    break;
                case 12:
                    bArrCreateByteArray = SafeParcelReader.createByteArray(parcel, header);
                    break;
                case 13:
                    arrayListCreateTypedList = SafeParcelReader.createTypedList(parcel, header, ParticipantEntity.CREATOR);
                    break;
                case 14:
                    strCreateString5 = SafeParcelReader.createString(parcel, header);
                    break;
                case 15:
                    bArrCreateByteArray2 = SafeParcelReader.createByteArray(parcel, header);
                    break;
                case 16:
                    i5 = SafeParcelReader.readInt(parcel, header);
                    break;
                case 17:
                    bundleCreateBundle = SafeParcelReader.createBundle(parcel, header);
                    break;
                case 18:
                    i6 = SafeParcelReader.readInt(parcel, header);
                    break;
                case 19:
                    z2 = SafeParcelReader.readBoolean(parcel, header);
                    break;
                case 20:
                    strCreateString6 = SafeParcelReader.createString(parcel, header);
                    break;
                case Decal.C4 /* 21 */:
                    strCreateString7 = SafeParcelReader.createString(parcel, header);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new TurnBasedMatchEntity(gameEntity, strCreateString, strCreateString2, j2, strCreateString3, j3, strCreateString4, i2, i3, i4, bArrCreateByteArray, arrayListCreateTypedList, strCreateString5, bArrCreateByteArray2, i5, bundleCreateBundle, i6, z2, strCreateString6, strCreateString7);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ TurnBasedMatchEntity[] newArray(int i2) {
        return new TurnBasedMatchEntity[i2];
    }
}
