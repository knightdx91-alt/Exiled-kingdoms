package com.google.android.gms.internal.play_billing;

import a.a;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Locale;

/* JADX INFO: compiled from: com.android.billingclient:billing@@6.0.1 */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public abstract class zzba implements Iterable, Serializable {
    private static final Comparator zza;
    public static final zzba zzb = new zzax(zzcg.zzd);
    private static final zzaz zzd;
    private int zzc = 0;

    static {
        int i2 = zzam.zza;
        zzd = new zzaz(null);
        zza = new zzas();
    }

    zzba() {
    }

    static int zzj(int i2, int i3, int i4) {
        int i5 = i3 - i2;
        if ((i2 | i3 | i5 | (i4 - i3)) >= 0) {
            return i5;
        }
        if (i2 < 0) {
            throw new IndexOutOfBoundsException("Beginning index: " + i2 + " < 0");
        }
        if (i3 < i2) {
            throw new IndexOutOfBoundsException("Beginning index larger than ending index: " + i2 + ", " + i3);
        }
        throw new IndexOutOfBoundsException("End index: " + i3 + " >= " + i4);
    }

    public static zzba zzl(byte[] bArr, int i2, int i3) {
        zzj(i2, i2 + i3, bArr.length);
        byte[] bArr2 = new byte[i3];
        System.arraycopy(bArr, i2, bArr2, 0, i3);
        return new zzax(bArr2);
    }

    public abstract boolean equals(Object obj);

    public final int hashCode() {
        int iZze = this.zzc;
        if (iZze == 0) {
            int iZzd = zzd();
            iZze = zze(iZzd, 0, iZzd);
            if (iZze == 0) {
                iZze = 1;
            }
            this.zzc = iZze;
        }
        return iZze;
    }

    @Override // java.lang.Iterable
    public final /* synthetic */ Iterator iterator() {
        return new zzar(this);
    }

    public final String toString() {
        Locale locale = Locale.ROOT;
        String hexString = Integer.toHexString(System.identityHashCode(this));
        int iZzd = zzd();
        String strZza = zzd() <= 50 ? zzee.zza(this) : zzee.zza(zzf(0, 47)).concat("...");
        StringBuilder sb = new StringBuilder("<ByteString@");
        sb.append(hexString);
        sb.append(" size=");
        sb.append(iZzd);
        sb.append(" contents=\"");
        return a.m(strZza, "\">", sb);
    }

    public abstract byte zza(int i2);

    abstract byte zzb(int i2);

    public abstract int zzd();

    protected abstract int zze(int i2, int i3, int i4);

    public abstract zzba zzf(int i2, int i3);

    protected abstract String zzg(Charset charset);

    abstract void zzh(zzaq zzaqVar);

    public abstract boolean zzi();

    protected final int zzk() {
        return this.zzc;
    }

    public final String zzm(Charset charset) {
        return zzd() == 0 ? "" : zzg(charset);
    }
}
