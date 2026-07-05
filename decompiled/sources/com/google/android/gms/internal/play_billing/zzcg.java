package com.google.android.gms.internal.play_billing;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

/* JADX INFO: compiled from: com.android.billingclient:billing@@6.0.1 */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class zzcg {
    static final Charset zza = Charset.forName("US-ASCII");
    static final Charset zzb = Charset.forName("UTF-8");
    static final Charset zzc = Charset.forName("ISO-8859-1");
    public static final byte[] zzd;
    public static final ByteBuffer zze;
    public static final zzbe zzf;

    static {
        byte[] bArr = new byte[0];
        zzd = bArr;
        zze = ByteBuffer.wrap(bArr);
        zzbc zzbcVar = new zzbc(bArr, 0, 0, false, null);
        try {
            zzbcVar.zza(0);
            zzf = zzbcVar;
        } catch (zzci e2) {
            throw new IllegalArgumentException(e2);
        }
    }

    public static int zza(boolean z2) {
        return z2 ? 1231 : 1237;
    }

    static int zzb(int i2, byte[] bArr, int i3, int i4) {
        for (int i5 = 0; i5 < i4; i5++) {
            i2 = (i2 * 31) + bArr[i5];
        }
        return i2;
    }

    static Object zzc(Object obj, String str) {
        if (obj != null) {
            return obj;
        }
        throw new NullPointerException("messageType");
    }

    public static String zzd(byte[] bArr) {
        return new String(bArr, zzb);
    }
}
