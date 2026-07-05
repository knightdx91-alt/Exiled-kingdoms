package com.google.android.gms.internal.play_billing;

import sun.misc.Unsafe;

/* JADX INFO: compiled from: com.android.billingclient:billing@@6.0.1 */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
abstract class zzep {
    final Unsafe zza;

    zzep(Unsafe unsafe) {
        this.zza = unsafe;
    }

    public abstract double zza(Object obj, long j2);

    public abstract float zzb(Object obj, long j2);

    public abstract void zzc(Object obj, long j2, boolean z2);

    public abstract void zzd(Object obj, long j2, byte b2);

    public abstract void zze(Object obj, long j2, double d2);

    public abstract void zzf(Object obj, long j2, float f2);

    public abstract boolean zzg(Object obj, long j2);
}
