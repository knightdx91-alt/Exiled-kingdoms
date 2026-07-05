package com.google.android.gms.internal.play_billing;

/* JADX INFO: compiled from: com.android.billingclient:billing@@6.0.1 */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzdo implements zzdc {
    private final zzdf zza;
    private final String zzb;
    private final Object[] zzc;
    private final int zzd;

    zzdo(zzdf zzdfVar, String str, Object[] objArr) {
        this.zza = zzdfVar;
        this.zzb = str;
        this.zzc = objArr;
        char cCharAt = str.charAt(0);
        if (cCharAt < 55296) {
            this.zzd = cCharAt;
            return;
        }
        int i2 = cCharAt & 8191;
        int i3 = 1;
        int i4 = 13;
        while (true) {
            int i5 = i3 + 1;
            char cCharAt2 = str.charAt(i3);
            if (cCharAt2 < 55296) {
                this.zzd = i2 | (cCharAt2 << i4);
                return;
            } else {
                i2 |= (cCharAt2 & 8191) << i4;
                i4 += 13;
                i3 = i5;
            }
        }
    }

    @Override // com.google.android.gms.internal.play_billing.zzdc
    public final zzdf zza() {
        return this.zza;
    }

    @Override // com.google.android.gms.internal.play_billing.zzdc
    public final boolean zzb() {
        return (this.zzd & 2) == 2;
    }

    @Override // com.google.android.gms.internal.play_billing.zzdc
    public final int zzc() {
        return (this.zzd & 1) != 0 ? 1 : 2;
    }

    final String zzd() {
        return this.zzb;
    }

    final Object[] zze() {
        return this.zzc;
    }
}
