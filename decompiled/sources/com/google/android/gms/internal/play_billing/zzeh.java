package com.google.android.gms.internal.play_billing;

import a.a;
import java.util.Arrays;

/* JADX INFO: compiled from: com.android.billingclient:billing@@6.0.1 */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class zzeh {
    private static final zzeh zza = new zzeh(0, new int[0], new Object[0], false);
    private int zzb;
    private int[] zzc;
    private Object[] zzd;
    private int zze;
    private boolean zzf;

    private zzeh(int i2, int[] iArr, Object[] objArr, boolean z2) {
        this.zze = -1;
        this.zzb = i2;
        this.zzc = iArr;
        this.zzd = objArr;
        this.zzf = z2;
    }

    public static zzeh zzc() {
        return zza;
    }

    static zzeh zze(zzeh zzehVar, zzeh zzehVar2) {
        int i2 = zzehVar.zzb + zzehVar2.zzb;
        int[] iArrCopyOf = Arrays.copyOf(zzehVar.zzc, i2);
        System.arraycopy(zzehVar2.zzc, 0, iArrCopyOf, zzehVar.zzb, zzehVar2.zzb);
        Object[] objArrCopyOf = Arrays.copyOf(zzehVar.zzd, i2);
        System.arraycopy(zzehVar2.zzd, 0, objArrCopyOf, zzehVar.zzb, zzehVar2.zzb);
        return new zzeh(i2, iArrCopyOf, objArrCopyOf, true);
    }

    static zzeh zzf() {
        return new zzeh(0, new int[8], new Object[8], true);
    }

    private final void zzl(int i2) {
        int[] iArr = this.zzc;
        if (i2 > iArr.length) {
            int i3 = this.zzb;
            int i4 = (i3 / 2) + i3;
            if (i4 >= i2) {
                i2 = i4;
            }
            if (i2 < 8) {
                i2 = 8;
            }
            this.zzc = Arrays.copyOf(iArr, i2);
            this.zzd = Arrays.copyOf(this.zzd, i2);
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof zzeh)) {
            return false;
        }
        zzeh zzehVar = (zzeh) obj;
        int i2 = this.zzb;
        if (i2 == zzehVar.zzb) {
            int[] iArr = this.zzc;
            int[] iArr2 = zzehVar.zzc;
            int i3 = 0;
            while (true) {
                if (i3 >= i2) {
                    Object[] objArr = this.zzd;
                    Object[] objArr2 = zzehVar.zzd;
                    int i4 = this.zzb;
                    for (int i5 = 0; i5 < i4; i5++) {
                        if (objArr[i5].equals(objArr2[i5])) {
                        }
                    }
                    return true;
                }
                if (iArr[i3] != iArr2[i3]) {
                    break;
                }
                i3++;
            }
        }
        return false;
    }

    public final int hashCode() {
        int i2 = this.zzb;
        int i3 = i2 + 527;
        int[] iArr = this.zzc;
        int iHashCode = 17;
        int i4 = 17;
        for (int i5 = 0; i5 < i2; i5++) {
            i4 = (i4 * 31) + iArr[i5];
        }
        int i6 = (i3 * 31) + i4;
        Object[] objArr = this.zzd;
        int i7 = this.zzb;
        for (int i8 = 0; i8 < i7; i8++) {
            iHashCode = (iHashCode * 31) + objArr[i8].hashCode();
        }
        return (i6 * 31) + iHashCode;
    }

    public final int zza() {
        int i2 = this.zze;
        if (i2 != -1) {
            return i2;
        }
        int iC = 0;
        for (int i3 = 0; i3 < this.zzb; i3++) {
            int i4 = this.zzc[i3];
            int i5 = i4 >>> 3;
            int i6 = i4 & 7;
            if (i6 == 0) {
                iC = a.c(i5 << 3, zzbi.zzy(((Long) this.zzd[i3]).longValue()), iC);
            } else if (i6 == 1) {
                ((Long) this.zzd[i3]).getClass();
                iC = a.c(i5 << 3, 8, iC);
            } else if (i6 == 2) {
                zzba zzbaVar = (zzba) this.zzd[i3];
                int i7 = zzbi.zzb;
                int iZzd = zzbaVar.zzd();
                iC = a.c(i5 << 3, zzbi.zzx(iZzd) + iZzd, iC);
            } else if (i6 == 3) {
                int i8 = i5 << 3;
                int i9 = zzbi.zzb;
                int iZza = ((zzeh) this.zzd[i3]).zza();
                int iZzx = zzbi.zzx(i8);
                iC = iZzx + iZzx + iZza + iC;
            } else {
                if (i6 != 5) {
                    throw new IllegalStateException(zzci.zza());
                }
                ((Integer) this.zzd[i3]).getClass();
                iC = a.c(i5 << 3, 4, iC);
            }
        }
        this.zze = iC;
        return iC;
    }

    public final int zzb() {
        int i2 = this.zze;
        if (i2 != -1) {
            return i2;
        }
        int iD = 0;
        for (int i3 = 0; i3 < this.zzb; i3++) {
            int i4 = this.zzc[i3] >>> 3;
            zzba zzbaVar = (zzba) this.zzd[i3];
            int i5 = zzbi.zzb;
            int iZzd = zzbaVar.zzd();
            int iZzx = zzbi.zzx(iZzd) + iZzd;
            int iZzx2 = zzbi.zzx(16);
            int iZzx3 = zzbi.zzx(i4);
            int iZzx4 = zzbi.zzx(8);
            iD = a.d(24, iZzx, iZzx2 + iZzx3 + iZzx4 + iZzx4, iD);
        }
        this.zze = iD;
        return iD;
    }

    final zzeh zzd(zzeh zzehVar) {
        if (zzehVar.equals(zza)) {
            return this;
        }
        zzg();
        int i2 = this.zzb + zzehVar.zzb;
        zzl(i2);
        System.arraycopy(zzehVar.zzc, 0, this.zzc, this.zzb, zzehVar.zzb);
        System.arraycopy(zzehVar.zzd, 0, this.zzd, this.zzb, zzehVar.zzb);
        this.zzb = i2;
        return this;
    }

    final void zzg() {
        if (!this.zzf) {
            throw new UnsupportedOperationException();
        }
    }

    public final void zzh() {
        if (this.zzf) {
            this.zzf = false;
        }
    }

    final void zzi(StringBuilder sb, int i2) {
        for (int i3 = 0; i3 < this.zzb; i3++) {
            zzdh.zzb(sb, i2, String.valueOf(this.zzc[i3] >>> 3), this.zzd[i3]);
        }
    }

    final void zzj(int i2, Object obj) {
        zzg();
        zzl(this.zzb + 1);
        int[] iArr = this.zzc;
        int i3 = this.zzb;
        iArr[i3] = i2;
        this.zzd[i3] = obj;
        this.zzb = i3 + 1;
    }

    public final void zzk(zzey zzeyVar) {
        if (this.zzb != 0) {
            for (int i2 = 0; i2 < this.zzb; i2++) {
                int i3 = this.zzc[i2];
                Object obj = this.zzd[i2];
                int i4 = i3 & 7;
                int i5 = i3 >>> 3;
                if (i4 == 0) {
                    zzeyVar.zzt(i5, ((Long) obj).longValue());
                } else if (i4 == 1) {
                    zzeyVar.zzm(i5, ((Long) obj).longValue());
                } else if (i4 == 2) {
                    zzeyVar.zzd(i5, (zzba) obj);
                } else if (i4 == 3) {
                    zzeyVar.zzE(i5);
                    ((zzeh) obj).zzk(zzeyVar);
                    zzeyVar.zzh(i5);
                } else {
                    if (i4 != 5) {
                        throw new RuntimeException(zzci.zza());
                    }
                    zzeyVar.zzk(i5, ((Integer) obj).intValue());
                }
            }
        }
    }

    private zzeh() {
        this(0, new int[8], new Object[8], true);
    }
}
