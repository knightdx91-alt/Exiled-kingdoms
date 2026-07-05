package com.google.android.gms.games;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.badlogic.gdx.graphics.g3d.decals.Decal;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class zzap implements Parcelable.Creator<PlayerEntity> {
    @Override // android.os.Parcelable.Creator
    public /* synthetic */ PlayerEntity[] newArray(int i2) {
        return new PlayerEntity[i2];
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: zzc */
    public PlayerEntity createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        String strCreateString = null;
        String strCreateString2 = null;
        Uri uri = null;
        Uri uri2 = null;
        String strCreateString3 = null;
        String strCreateString4 = null;
        String strCreateString5 = null;
        com.google.android.gms.games.internal.player.zzb zzbVar = null;
        PlayerLevelInfo playerLevelInfo = null;
        String strCreateString6 = null;
        String strCreateString7 = null;
        Uri uri3 = null;
        String strCreateString8 = null;
        Uri uri4 = null;
        String strCreateString9 = null;
        long j2 = 0;
        long j3 = 0;
        long j4 = 0;
        int i2 = 0;
        boolean z2 = false;
        boolean z3 = false;
        int i3 = 0;
        boolean z4 = false;
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
                    j2 = SafeParcelReader.readLong(parcel, header);
                    break;
                case 6:
                    i2 = SafeParcelReader.readInt(parcel, header);
                    break;
                case 7:
                    j3 = SafeParcelReader.readLong(parcel, header);
                    break;
                case 8:
                    strCreateString3 = SafeParcelReader.createString(parcel, header);
                    break;
                case 9:
                    strCreateString4 = SafeParcelReader.createString(parcel, header);
                    break;
                case 10:
                case 11:
                case 12:
                case 13:
                case 17:
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
                case 14:
                    strCreateString5 = SafeParcelReader.createString(parcel, header);
                    break;
                case 15:
                    zzbVar = (com.google.android.gms.games.internal.player.zzb) SafeParcelReader.createParcelable(parcel, header, com.google.android.gms.games.internal.player.zzb.CREATOR);
                    break;
                case 16:
                    playerLevelInfo = (PlayerLevelInfo) SafeParcelReader.createParcelable(parcel, header, PlayerLevelInfo.CREATOR);
                    break;
                case 18:
                    z2 = SafeParcelReader.readBoolean(parcel, header);
                    break;
                case 19:
                    z3 = SafeParcelReader.readBoolean(parcel, header);
                    break;
                case 20:
                    strCreateString6 = SafeParcelReader.createString(parcel, header);
                    break;
                case Decal.C4 /* 21 */:
                    strCreateString7 = SafeParcelReader.createString(parcel, header);
                    break;
                case Decal.U4 /* 22 */:
                    uri3 = (Uri) SafeParcelReader.createParcelable(parcel, header, Uri.CREATOR);
                    break;
                case Decal.V4 /* 23 */:
                    strCreateString8 = SafeParcelReader.createString(parcel, header);
                    break;
                case Decal.SIZE /* 24 */:
                    uri4 = (Uri) SafeParcelReader.createParcelable(parcel, header, Uri.CREATOR);
                    break;
                case 25:
                    strCreateString9 = SafeParcelReader.createString(parcel, header);
                    break;
                case 26:
                    i3 = SafeParcelReader.readInt(parcel, header);
                    break;
                case 27:
                    j4 = SafeParcelReader.readLong(parcel, header);
                    break;
                case 28:
                    z4 = SafeParcelReader.readBoolean(parcel, header);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new PlayerEntity(strCreateString, strCreateString2, uri, uri2, j2, i2, j3, strCreateString3, strCreateString4, strCreateString5, zzbVar, playerLevelInfo, z2, z3, strCreateString6, strCreateString7, uri3, strCreateString8, uri4, strCreateString9, i3, j4, z4);
    }
}
