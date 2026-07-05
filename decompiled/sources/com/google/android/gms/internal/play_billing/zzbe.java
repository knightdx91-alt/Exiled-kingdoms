package com.google.android.gms.internal.play_billing;

/* JADX INFO: compiled from: com.android.billingclient:billing@@6.0.1 */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class zzbe {
    public static final /* synthetic */ int zza = 0;
    private static volatile int zzb = 100;

    /* synthetic */ zzbe(zzbd zzbdVar) {
    }

    public static int zzb(int i2) {
        return (i2 >>> 1) ^ (-(i2 & 1));
    }

    public static long zzc(long j2) {
        return (j2 >>> 1) ^ (-(1 & j2));
    }
}
