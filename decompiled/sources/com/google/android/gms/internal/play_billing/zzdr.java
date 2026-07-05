package com.google.android.gms.internal.play_billing;

import a.a;
import java.util.List;

/* JADX INFO: compiled from: com.android.billingclient:billing@@6.0.1 */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzdr {
    public static final /* synthetic */ int zza = 0;
    private static final Class zzb;
    private static final zzeg zzc;
    private static final zzeg zzd;

    static {
        Class<?> cls;
        Class<?> cls2;
        zzeg zzegVar = null;
        try {
            cls = Class.forName("com.google.protobuf.GeneratedMessage");
        } catch (Throwable unused) {
            cls = null;
        }
        zzb = cls;
        try {
            cls2 = Class.forName("com.google.protobuf.UnknownFieldSetSchema");
        } catch (Throwable unused2) {
            cls2 = null;
        }
        if (cls2 != null) {
            try {
                zzegVar = (zzeg) cls2.getConstructor(null).newInstance(null);
            } catch (Throwable unused3) {
            }
        }
        zzc = zzegVar;
        zzd = new zzei();
    }

    static Object zzA(Object obj, int i2, int i3, Object obj2, zzeg zzegVar) {
        if (obj2 == null) {
            obj2 = zzegVar.zzc(obj);
        }
        zzegVar.zzf(obj2, i2, i3);
        return obj2;
    }

    static void zzB(zzeg zzegVar, Object obj, Object obj2) {
        zzegVar.zzh(obj, zzegVar.zze(zzegVar.zzd(obj), zzegVar.zzd(obj2)));
    }

    public static void zzC(Class cls) {
        Class cls2;
        if (!zzcb.class.isAssignableFrom(cls) && (cls2 = zzb) != null && !cls2.isAssignableFrom(cls)) {
            throw new IllegalArgumentException("Message classes must extend GeneratedMessage or GeneratedMessageLite");
        }
    }

    public static void zzD(int i2, List list, zzey zzeyVar, boolean z2) {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzeyVar.zzc(i2, list, z2);
    }

    public static void zzE(int i2, List list, zzey zzeyVar) {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzeyVar.zze(i2, list);
    }

    public static void zzF(int i2, List list, zzey zzeyVar, boolean z2) {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzeyVar.zzg(i2, list, z2);
    }

    public static void zzG(int i2, List list, zzey zzeyVar, boolean z2) {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzeyVar.zzj(i2, list, z2);
    }

    public static void zzH(int i2, List list, zzey zzeyVar, boolean z2) {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzeyVar.zzl(i2, list, z2);
    }

    public static void zzI(int i2, List list, zzey zzeyVar, boolean z2) {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzeyVar.zzn(i2, list, z2);
    }

    public static void zzJ(int i2, List list, zzey zzeyVar, boolean z2) {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzeyVar.zzp(i2, list, z2);
    }

    public static void zzK(int i2, List list, zzey zzeyVar, zzdp zzdpVar) {
        if (list == null || list.isEmpty()) {
            return;
        }
        for (int i3 = 0; i3 < list.size(); i3++) {
            ((zzbj) zzeyVar).zzq(i2, list.get(i3), zzdpVar);
        }
    }

    public static void zzL(int i2, List list, zzey zzeyVar, boolean z2) {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzeyVar.zzs(i2, list, z2);
    }

    public static void zzM(int i2, List list, zzey zzeyVar, boolean z2) {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzeyVar.zzu(i2, list, z2);
    }

    public static void zzN(int i2, List list, zzey zzeyVar, zzdp zzdpVar) {
        if (list == null || list.isEmpty()) {
            return;
        }
        for (int i3 = 0; i3 < list.size(); i3++) {
            ((zzbj) zzeyVar).zzv(i2, list.get(i3), zzdpVar);
        }
    }

    public static void zzO(int i2, List list, zzey zzeyVar, boolean z2) {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzeyVar.zzx(i2, list, z2);
    }

    public static void zzP(int i2, List list, zzey zzeyVar, boolean z2) {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzeyVar.zzz(i2, list, z2);
    }

    public static void zzQ(int i2, List list, zzey zzeyVar, boolean z2) {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzeyVar.zzB(i2, list, z2);
    }

    public static void zzR(int i2, List list, zzey zzeyVar, boolean z2) {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzeyVar.zzD(i2, list, z2);
    }

    public static void zzS(int i2, List list, zzey zzeyVar) {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzeyVar.zzG(i2, list);
    }

    public static void zzT(int i2, List list, zzey zzeyVar, boolean z2) {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzeyVar.zzI(i2, list, z2);
    }

    public static void zzU(int i2, List list, zzey zzeyVar, boolean z2) {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzeyVar.zzK(i2, list, z2);
    }

    static boolean zzV(Object obj, Object obj2) {
        if (obj != obj2) {
            return obj != null && obj.equals(obj2);
        }
        return true;
    }

    static int zza(int i2, List list, boolean z2) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return (zzbi.zzx(i2 << 3) + 1) * size;
    }

    static int zzb(int i2, List list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int iZzx = zzbi.zzx(i2 << 3) * size;
        for (int i3 = 0; i3 < list.size(); i3++) {
            int iZzd = ((zzba) list.get(i3)).zzd();
            iZzx = a.c(iZzd, iZzd, iZzx);
        }
        return iZzx;
    }

    static int zzc(int i2, List list, boolean z2) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return (zzbi.zzx(i2 << 3) * size) + zzd(list);
    }

    static int zzd(List list) {
        int iZzu;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzcc) {
            zzcc zzccVar = (zzcc) list;
            iZzu = 0;
            while (i2 < size) {
                iZzu += zzbi.zzu(zzccVar.zze(i2));
                i2++;
            }
        } else {
            iZzu = 0;
            while (i2 < size) {
                iZzu += zzbi.zzu(((Integer) list.get(i2)).intValue());
                i2++;
            }
        }
        return iZzu;
    }

    static int zze(int i2, List list, boolean z2) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return (zzbi.zzx(i2 << 3) + 4) * size;
    }

    static int zzf(List list) {
        return list.size() * 4;
    }

    static int zzg(int i2, List list, boolean z2) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return (zzbi.zzx(i2 << 3) + 8) * size;
    }

    static int zzh(List list) {
        return list.size() * 8;
    }

    static int zzi(int i2, List list, zzdp zzdpVar) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int iZzt = 0;
        for (int i3 = 0; i3 < size; i3++) {
            iZzt += zzbi.zzt(i2, (zzdf) list.get(i3), zzdpVar);
        }
        return iZzt;
    }

    static int zzj(int i2, List list, boolean z2) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return (zzbi.zzx(i2 << 3) * size) + zzk(list);
    }

    static int zzk(List list) {
        int iZzu;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzcc) {
            zzcc zzccVar = (zzcc) list;
            iZzu = 0;
            while (i2 < size) {
                iZzu += zzbi.zzu(zzccVar.zze(i2));
                i2++;
            }
        } else {
            iZzu = 0;
            while (i2 < size) {
                iZzu += zzbi.zzu(((Integer) list.get(i2)).intValue());
                i2++;
            }
        }
        return iZzu;
    }

    static int zzl(int i2, List list, boolean z2) {
        if (list.size() == 0) {
            return 0;
        }
        return (zzbi.zzx(i2 << 3) * list.size()) + zzm(list);
    }

    static int zzm(List list) {
        int iZzy;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzcu) {
            zzcu zzcuVar = (zzcu) list;
            iZzy = 0;
            while (i2 < size) {
                iZzy += zzbi.zzy(zzcuVar.zze(i2));
                i2++;
            }
        } else {
            iZzy = 0;
            while (i2 < size) {
                iZzy += zzbi.zzy(((Long) list.get(i2)).longValue());
                i2++;
            }
        }
        return iZzy;
    }

    static int zzn(int i2, Object obj, zzdp zzdpVar) {
        if (!(obj instanceof zzcl)) {
            return zzbi.zzx(i2 << 3) + zzbi.zzv((zzdf) obj, zzdpVar);
        }
        int i3 = zzbi.zzb;
        int iZza = ((zzcl) obj).zza();
        return zzbi.zzx(i2 << 3) + zzbi.zzx(iZza) + iZza;
    }

    static int zzo(int i2, List list, zzdp zzdpVar) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int iZzx = zzbi.zzx(i2 << 3) * size;
        for (int i3 = 0; i3 < size; i3++) {
            Object obj = list.get(i3);
            if (obj instanceof zzcl) {
                int iZza = ((zzcl) obj).zza();
                iZzx = a.c(iZza, iZza, iZzx);
            } else {
                iZzx += zzbi.zzv((zzdf) obj, zzdpVar);
            }
        }
        return iZzx;
    }

    static int zzp(int i2, List list, boolean z2) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return (zzbi.zzx(i2 << 3) * size) + zzq(list);
    }

    static int zzq(List list) {
        int iZzx;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzcc) {
            zzcc zzccVar = (zzcc) list;
            iZzx = 0;
            while (i2 < size) {
                int iZze = zzccVar.zze(i2);
                iZzx += zzbi.zzx((iZze >> 31) ^ (iZze + iZze));
                i2++;
            }
        } else {
            iZzx = 0;
            while (i2 < size) {
                int iIntValue = ((Integer) list.get(i2)).intValue();
                iZzx += zzbi.zzx((iIntValue >> 31) ^ (iIntValue + iIntValue));
                i2++;
            }
        }
        return iZzx;
    }

    static int zzr(int i2, List list, boolean z2) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return (zzbi.zzx(i2 << 3) * size) + zzs(list);
    }

    static int zzs(List list) {
        int iZzy;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzcu) {
            zzcu zzcuVar = (zzcu) list;
            iZzy = 0;
            while (i2 < size) {
                long jZze = zzcuVar.zze(i2);
                iZzy += zzbi.zzy((jZze >> 63) ^ (jZze + jZze));
                i2++;
            }
        } else {
            iZzy = 0;
            while (i2 < size) {
                long jLongValue = ((Long) list.get(i2)).longValue();
                iZzy += zzbi.zzy((jLongValue >> 63) ^ (jLongValue + jLongValue));
                i2++;
            }
        }
        return iZzy;
    }

    static int zzt(int i2, List list) {
        int size = list.size();
        int i3 = 0;
        if (size == 0) {
            return 0;
        }
        boolean z2 = list instanceof zzcn;
        int iZzx = zzbi.zzx(i2 << 3) * size;
        if (z2) {
            zzcn zzcnVar = (zzcn) list;
            while (i3 < size) {
                Object objZzf = zzcnVar.zzf(i3);
                if (objZzf instanceof zzba) {
                    int iZzd = ((zzba) objZzf).zzd();
                    iZzx = a.c(iZzd, iZzd, iZzx);
                } else {
                    iZzx = zzbi.zzw((String) objZzf) + iZzx;
                }
                i3++;
            }
        } else {
            while (i3 < size) {
                Object obj = list.get(i3);
                if (obj instanceof zzba) {
                    int iZzd2 = ((zzba) obj).zzd();
                    iZzx = a.c(iZzd2, iZzd2, iZzx);
                } else {
                    iZzx = zzbi.zzw((String) obj) + iZzx;
                }
                i3++;
            }
        }
        return iZzx;
    }

    static int zzu(int i2, List list, boolean z2) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return (zzbi.zzx(i2 << 3) * size) + zzv(list);
    }

    static int zzv(List list) {
        int iZzx;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzcc) {
            zzcc zzccVar = (zzcc) list;
            iZzx = 0;
            while (i2 < size) {
                iZzx += zzbi.zzx(zzccVar.zze(i2));
                i2++;
            }
        } else {
            iZzx = 0;
            while (i2 < size) {
                iZzx += zzbi.zzx(((Integer) list.get(i2)).intValue());
                i2++;
            }
        }
        return iZzx;
    }

    static int zzw(int i2, List list, boolean z2) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return (zzbi.zzx(i2 << 3) * size) + zzx(list);
    }

    static int zzx(List list) {
        int iZzy;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzcu) {
            zzcu zzcuVar = (zzcu) list;
            iZzy = 0;
            while (i2 < size) {
                iZzy += zzbi.zzy(zzcuVar.zze(i2));
                i2++;
            }
        } else {
            iZzy = 0;
            while (i2 < size) {
                iZzy += zzbi.zzy(((Long) list.get(i2)).longValue());
                i2++;
            }
        }
        return iZzy;
    }

    public static zzeg zzy() {
        return zzc;
    }

    public static zzeg zzz() {
        return zzd;
    }
}
