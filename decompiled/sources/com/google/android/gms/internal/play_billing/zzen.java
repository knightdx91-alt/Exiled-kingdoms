package com.google.android.gms.internal.play_billing;

import sun.misc.Unsafe;

/* JADX INFO: compiled from: com.android.billingclient:billing@@6.0.1 */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzen extends zzep {
    zzen(Unsafe unsafe) {
        super(unsafe);
    }

    @Override // com.google.android.gms.internal.play_billing.zzep
    public final double zza(Object obj, long j2) {
        return Double.longBitsToDouble(this.zza.getLong(obj, j2));
    }

    @Override // com.google.android.gms.internal.play_billing.zzep
    public final float zzb(Object obj, long j2) {
        return Float.intBitsToFloat(this.zza.getInt(obj, j2));
    }

    @Override // com.google.android.gms.internal.play_billing.zzep
    public final void zzc(Object obj, long j2, boolean z2) {
        if (zzeq.zzb) {
            zzeq.zzD(obj, j2, z2 ? (byte) 1 : (byte) 0);
        } else {
            zzeq.zzE(obj, j2, z2 ? (byte) 1 : (byte) 0);
        }
    }

    @Override // com.google.android.gms.internal.play_billing.zzep
    public final void zzd(Object obj, long j2, byte b2) {
        if (zzeq.zzb) {
            zzeq.zzD(obj, j2, b2);
        } else {
            zzeq.zzE(obj, j2, b2);
        }
    }

    @Override // com.google.android.gms.internal.play_billing.zzep
    public final void zze(Object obj, long j2, double d2) {
        this.zza.putLong(obj, j2, Double.doubleToLongBits(d2));
    }

    @Override // com.google.android.gms.internal.play_billing.zzep
    public final void zzf(Object obj, long j2, float f2) {
        this.zza.putInt(obj, j2, Float.floatToIntBits(f2));
    }

    @Override // com.google.android.gms.internal.play_billing.zzep
    public final boolean zzg(Object obj, long j2) {
        return zzeq.zzb ? zzeq.zzt(obj, j2) : zzeq.zzu(obj, j2);
    }
}
