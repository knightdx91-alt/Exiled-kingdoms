package com.google.android.gms.internal.play_billing;

import a.a;

/* JADX INFO: compiled from: com.android.billingclient:billing@@6.0.1 */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class zzm {
    public static int zza(int i2, int i3, String str) {
        String strZza;
        if (i2 >= 0 && i2 < i3) {
            return i2;
        }
        if (i2 < 0) {
            strZza = zzn.zza("%s (%s) must not be negative", "index", Integer.valueOf(i2));
        } else {
            if (i3 < 0) {
                throw new IllegalArgumentException(a.h(i3, "negative size: "));
            }
            strZza = zzn.zza("%s (%s) must be less than size (%s)", "index", Integer.valueOf(i2), Integer.valueOf(i3));
        }
        throw new IndexOutOfBoundsException(strZza);
    }

    public static int zzb(int i2, int i3, String str) {
        if (i2 < 0 || i2 > i3) {
            throw new IndexOutOfBoundsException(zze(i2, i3, "index"));
        }
        return i2;
    }

    public static Object zzc(Object obj, Object obj2) {
        if (obj != null) {
            return obj;
        }
        throw new NullPointerException((String) obj2);
    }

    public static void zzd(int i2, int i3, int i4) {
        if (i2 < 0 || i3 < i2 || i3 > i4) {
            throw new IndexOutOfBoundsException((i2 < 0 || i2 > i4) ? zze(i2, i4, "start index") : (i3 < 0 || i3 > i4) ? zze(i3, i4, "end index") : zzn.zza("end index (%s) must not be less than start index (%s)", Integer.valueOf(i3), Integer.valueOf(i2)));
        }
    }

    private static String zze(int i2, int i3, String str) {
        if (i2 < 0) {
            return zzn.zza("%s (%s) must not be negative", str, Integer.valueOf(i2));
        }
        if (i3 >= 0) {
            return zzn.zza("%s (%s) must not be greater than size (%s)", str, Integer.valueOf(i2), Integer.valueOf(i3));
        }
        throw new IllegalArgumentException(a.h(i3, "negative size: "));
    }
}
