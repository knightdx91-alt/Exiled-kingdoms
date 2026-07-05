package com.google.android.gms.common;

import android.util.Log;
import com.google.android.gms.common.util.AndroidUtilsLight;
import com.google.android.gms.common.util.Hex;
import java.util.concurrent.Callable;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
class zzm {
    private static final zzm zzab = new zzm(true, null, null);
    private final Throwable cause;
    final boolean zzac;
    private final String zzad;

    zzm(boolean z2, String str, Throwable th) {
        this.zzac = z2;
        this.zzad = str;
        this.cause = th;
    }

    static zzm zza(Callable<String> callable) {
        return new zzo(callable);
    }

    static zzm zzb(String str) {
        return new zzm(false, str, null);
    }

    static zzm zze() {
        return zzab;
    }

    String getErrorMessage() {
        return this.zzad;
    }

    final void zzf() {
        if (this.zzac || !Log.isLoggable("GoogleCertificatesRslt", 3)) {
            return;
        }
        if (this.cause != null) {
            Log.d("GoogleCertificatesRslt", getErrorMessage(), this.cause);
        } else {
            Log.d("GoogleCertificatesRslt", getErrorMessage());
        }
    }

    static zzm zza(String str, Throwable th) {
        return new zzm(false, str, th);
    }

    static String zza(String str, zze zzeVar, boolean z2, boolean z3) {
        return (z3 ? "debug cert rejected" : "not whitelisted") + ": pkg=" + str + ", sha1=" + Hex.zza(AndroidUtilsLight.zzi("SHA-1").digest(zzeVar.getBytes())) + ", atk=" + z2 + ", ver=12451009.false";
    }
}
