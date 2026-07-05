package com.google.android.gms.internal.play_billing;

/* JADX INFO: compiled from: com.android.billingclient:billing@@6.0.1 */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class zzgd extends zzcb implements zzdg {
    private static final zzgd zzb;
    private int zzd;
    private int zze;

    static {
        zzgd zzgdVar = new zzgd();
        zzb = zzgdVar;
        zzcb.zzp(zzgd.class, zzgdVar);
    }

    private zzgd() {
    }

    public static zzgd zzw() {
        return zzb;
    }

    @Override // com.google.android.gms.internal.play_billing.zzcb
    protected final Object zzu(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zzcb.zzm(zzb, "\u0001\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0000\u0001᠌\u0000", new Object[]{"zzd", "zze", zzgc.zza});
        }
        if (i3 == 3) {
            return new zzgd();
        }
        zzga zzgaVar = null;
        if (i3 == 4) {
            return new zzgb(zzgaVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zzb;
    }
}
