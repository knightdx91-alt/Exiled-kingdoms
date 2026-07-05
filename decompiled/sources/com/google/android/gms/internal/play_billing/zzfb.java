package com.google.android.gms.internal.play_billing;

/* JADX INFO: compiled from: com.android.billingclient:billing@@6.0.1 */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class zzfb extends zzcb implements zzdg {
    private static final zzfb zzb;
    private int zzd;
    private int zze = 0;
    private Object zzf;
    private int zzg;
    private zzfj zzh;
    private zzfs zzi;

    static {
        zzfb zzfbVar = new zzfb();
        zzb = zzfbVar;
        zzcb.zzp(zzfb.class, zzfbVar);
    }

    private zzfb() {
    }

    static /* synthetic */ void zzA(zzfb zzfbVar, int i2) {
        zzfbVar.zzg = i2 - 1;
        zzfbVar.zzd |= 1;
    }

    public static zzfa zzv() {
        return (zzfa) zzb.zzg();
    }

    public static zzfb zzx(byte[] bArr, zzbn zzbnVar) {
        return (zzfb) zzcb.zzj(zzb, bArr, zzbnVar);
    }

    static /* synthetic */ void zzy(zzfb zzfbVar, zzfj zzfjVar) {
        zzfjVar.getClass();
        zzfbVar.zzh = zzfjVar;
        zzfbVar.zzd |= 2;
    }

    static /* synthetic */ void zzz(zzfb zzfbVar, zzfw zzfwVar) {
        zzfwVar.getClass();
        zzfbVar.zzf = zzfwVar;
        zzfbVar.zze = 4;
    }

    @Override // com.google.android.gms.internal.play_billing.zzcb
    protected final Object zzu(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return (byte) 1;
        }
        if (i3 == 2) {
            return zzcb.zzm(zzb, "\u0001\u0004\u0001\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001᠌\u0000\u0002ဉ\u0001\u0003ဉ\u0002\u0004<\u0000", new Object[]{"zzf", "zze", "zzd", "zzg", zzfc.zza, "zzh", "zzi", zzfw.class});
        }
        if (i3 == 3) {
            return new zzfb();
        }
        zzez zzezVar = null;
        if (i3 == 4) {
            return new zzfa(zzezVar);
        }
        if (i3 != 5) {
            return null;
        }
        return zzb;
    }
}
