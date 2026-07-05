package com.google.android.gms.auth;

import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import com.google.android.gms.common.logging.Logger;
import com.google.android.gms.internal.auth.zzay;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzi implements zzj<Boolean> {
    private final /* synthetic */ String zzt;

    zzi(String str) {
        this.zzt = str;
    }

    @Override // com.google.android.gms.auth.zzj
    public final /* synthetic */ Boolean zzb(IBinder iBinder) throws GoogleAuthException {
        Bundle bundle = (Bundle) zzd.zza(com.google.android.gms.internal.auth.zzf.zza(iBinder).zza(this.zzt));
        String string = bundle.getString("Error");
        Intent intent = (Intent) bundle.getParcelable("userRecoveryIntent");
        zzay zzayVarZzc = zzay.zzc(string);
        if (zzay.SUCCESS.equals(zzayVarZzc)) {
            return Boolean.TRUE;
        }
        if (!zzay.zza(zzayVarZzc)) {
            throw new GoogleAuthException(string);
        }
        Logger logger = zzd.zzn;
        String strValueOf = String.valueOf(zzayVarZzc);
        StringBuilder sb = new StringBuilder(strValueOf.length() + 31);
        sb.append("isUserRecoverableError status: ");
        sb.append(strValueOf);
        logger.w("GoogleAuthUtil", sb.toString());
        throw new UserRecoverableAuthException(string, intent);
    }
}
