package com.google.android.gms.internal.drive;

import android.os.Parcel;
import com.badlogic.gdx.graphics.g3d.decals.Decal;
import com.google.android.gms.common.api.Status;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public abstract class zzer extends zzb implements zzeq {
    public zzer() {
        super("com.google.android.gms.drive.internal.IDriveServiceCallbacks");
    }

    @Override // com.google.android.gms.internal.drive.zzb
    protected final boolean dispatchTransaction(int i2, Parcel parcel, Parcel parcel2, int i3) {
        switch (i2) {
            case 1:
                zza((zzff) zzc.zza(parcel, zzff.CREATOR));
                break;
            case 2:
                zza((zzfn) zzc.zza(parcel, zzfn.CREATOR));
                break;
            case 3:
                zza((zzfh) zzc.zza(parcel, zzfh.CREATOR));
                break;
            case 4:
                zza((zzfs) zzc.zza(parcel, zzfs.CREATOR));
                break;
            case 5:
                zza((zzfb) zzc.zza(parcel, zzfb.CREATOR));
                break;
            case 6:
                zza((Status) zzc.zza(parcel, Status.CREATOR));
                break;
            case 7:
                onSuccess();
                break;
            case 8:
                zza((zzfp) zzc.zza(parcel, zzfp.CREATOR));
                break;
            case 9:
                zza((zzgb) zzc.zza(parcel, zzgb.CREATOR));
                break;
            case 10:
            case 19:
            default:
                return false;
            case 11:
                zza((zzfr) zzc.zza(parcel, zzfr.CREATOR), zzim.zzb(parcel.readStrongBinder()));
                break;
            case 12:
                zza((zzfx) zzc.zza(parcel, zzfx.CREATOR));
                break;
            case 13:
                zza((zzfu) zzc.zza(parcel, zzfu.CREATOR));
                break;
            case 14:
                zza((zzfd) zzc.zza(parcel, zzfd.CREATOR));
                break;
            case 15:
                zzb(zzc.zza(parcel));
                break;
            case 16:
                zza((zzfl) zzc.zza(parcel, zzfl.CREATOR));
                break;
            case 17:
                zza((com.google.android.gms.drive.zza) zzc.zza(parcel, com.google.android.gms.drive.zza.CREATOR));
                break;
            case 18:
                zza((zzez) zzc.zza(parcel, zzez.CREATOR));
                break;
            case 20:
                zza((zzem) zzc.zza(parcel, zzem.CREATOR));
                break;
            case Decal.C4 /* 21 */:
                zza((zzgt) zzc.zza(parcel, zzgt.CREATOR));
                break;
            case Decal.U4 /* 22 */:
                zza((zzfz) zzc.zza(parcel, zzfz.CREATOR));
                break;
        }
        parcel2.writeNoException();
        return true;
    }
}
