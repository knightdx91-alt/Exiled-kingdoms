package com.google.android.gms.internal.play_billing;

import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX INFO: compiled from: com.android.billingclient:billing@@6.0.1 */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public abstract class zzbi extends zzaq {
    public static final /* synthetic */ int zzb = 0;
    private static final Logger zzc = Logger.getLogger(zzbi.class.getName());
    private static final boolean zzd = zzeq.zzx();
    zzbj zza;

    private zzbi() {
    }

    @Deprecated
    static int zzt(int i2, zzdf zzdfVar, zzdp zzdpVar) {
        int iZza = ((zzak) zzdfVar).zza(zzdpVar);
        int iZzx = zzx(i2 << 3);
        return iZzx + iZzx + iZza;
    }

    public static int zzu(int i2) {
        if (i2 >= 0) {
            return zzx(i2);
        }
        return 10;
    }

    static int zzv(zzdf zzdfVar, zzdp zzdpVar) {
        int iZza = ((zzak) zzdfVar).zza(zzdpVar);
        return zzx(iZza) + iZza;
    }

    public static int zzw(String str) {
        int length;
        try {
            length = zzev.zzc(str);
        } catch (zzeu unused) {
            length = str.getBytes(zzcg.zzb).length;
        }
        return zzx(length) + length;
    }

    public static int zzx(int i2) {
        if ((i2 & (-128)) == 0) {
            return 1;
        }
        if ((i2 & (-16384)) == 0) {
            return 2;
        }
        if (((-2097152) & i2) == 0) {
            return 3;
        }
        return (i2 & (-268435456)) == 0 ? 4 : 5;
    }

    public static int zzy(long j2) {
        int i2;
        if (((-128) & j2) == 0) {
            return 1;
        }
        if (j2 < 0) {
            return 10;
        }
        if (((-34359738368L) & j2) != 0) {
            j2 >>>= 28;
            i2 = 6;
        } else {
            i2 = 2;
        }
        if (((-2097152) & j2) != 0) {
            j2 >>>= 14;
            i2 += 2;
        }
        return (j2 & (-16384)) != 0 ? i2 + 1 : i2;
    }

    public static zzbi zzz(byte[] bArr, int i2, int i3) {
        return new zzbf(bArr, 0, i3);
    }

    public final void zzA() {
        if (zza() != 0) {
            throw new IllegalStateException("Did not write as much data as expected.");
        }
    }

    final void zzB(String str, zzeu zzeuVar) throws zzbg {
        zzc.logp(Level.WARNING, "com.google.protobuf.CodedOutputStream", "inefficientWriteStringNoTag", "Converting ill-formed UTF-16. Your Protocol Buffer will not round trip correctly!", (Throwable) zzeuVar);
        byte[] bytes = str.getBytes(zzcg.zzb);
        try {
            int length = bytes.length;
            zzq(length);
            zzl(bytes, 0, length);
        } catch (IndexOutOfBoundsException e2) {
            throw new zzbg(e2);
        }
    }

    public abstract int zza();

    public abstract void zzb(byte b2);

    public abstract void zzd(int i2, boolean z2);

    public abstract void zze(int i2, zzba zzbaVar);

    public abstract void zzf(int i2, int i3);

    public abstract void zzg(int i2);

    public abstract void zzh(int i2, long j2);

    public abstract void zzi(long j2);

    public abstract void zzj(int i2, int i3);

    public abstract void zzk(int i2);

    public abstract void zzl(byte[] bArr, int i2, int i3);

    public abstract void zzm(int i2, String str);

    public abstract void zzo(int i2, int i3);

    public abstract void zzp(int i2, int i3);

    public abstract void zzq(int i2);

    public abstract void zzr(int i2, long j2);

    public abstract void zzs(long j2);

    /* synthetic */ zzbi(zzbh zzbhVar) {
    }
}
