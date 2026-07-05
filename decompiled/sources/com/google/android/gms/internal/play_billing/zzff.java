package com.google.android.gms.internal.play_billing;

/* JADX INFO: compiled from: com.android.billingclient:billing@@6.0.1 */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class zzff extends zzcb implements zzdg {
    private static final zzff zzb;
    private int zzd;
    private int zze = 0;
    private Object zzf;
    private int zzg;

    static {
        zzff zzffVar = new zzff();
        zzb = zzffVar;
        zzcb.zzp(zzff.class, zzffVar);
    }

    private zzff() {
    }

    public static zzfe zzv() {
        return (zzfe) zzb.zzg();
    }

    static /* synthetic */ void zzx(zzff zzffVar, zzfw zzfwVar) {
        zzfwVar.getClass();
        zzffVar.zzf = zzfwVar;
        zzffVar.zze = 2;
    }

    static /* synthetic */ void zzy(zzff zzffVar, int i2) {
        zzffVar.zzg = i2 - 1;
        zzffVar.zzd |= 1;
    }

    @Override // com.google.android.gms.internal.play_billing.zzcb
    protected final Object zzu(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zzcb.zzm(zzb, "\u0001\u0002\u0001\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001᠌\u0000\u0002<\u0000", new Object[]{"zzf", "zze", "zzd", "zzg", zzfc.zza, zzfw.class});
        }
        if (i3 == 3) {
            return new zzff();
        }
        zzfd zzfdVar = null;
        if (i3 == 4) {
            return new zzfe(zzfdVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zzb;
    }
}
