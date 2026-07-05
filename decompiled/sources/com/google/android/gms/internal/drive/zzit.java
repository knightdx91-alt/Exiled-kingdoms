package com.google.android.gms.internal.drive;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class zzit implements Cloneable {
    private static final zziu zzmy = new zziu();
    private int mSize;
    private boolean zzmz;
    private int[] zzna;
    private zziu[] zznb;

    zzit() {
        this(10);
    }

    private static int idealIntArraySize(int i2) {
        int i3 = i2 << 2;
        int i4 = 4;
        while (true) {
            if (i4 >= 32) {
                break;
            }
            int i5 = (1 << i4) - 12;
            if (i3 <= i5) {
                i3 = i5;
                break;
            }
            i4++;
        }
        return i3 / 4;
    }

    private final int zzt(int i2) {
        int i3 = this.mSize - 1;
        int i4 = 0;
        while (i4 <= i3) {
            int i5 = (i4 + i3) >>> 1;
            int i6 = this.zzna[i5];
            if (i6 < i2) {
                i4 = i5 + 1;
            } else {
                if (i6 <= i2) {
                    return i5;
                }
                i3 = i5 - 1;
            }
        }
        return ~i4;
    }

    public final /* synthetic */ Object clone() {
        int i2 = this.mSize;
        zzit zzitVar = new zzit(i2);
        System.arraycopy(this.zzna, 0, zzitVar.zzna, 0, i2);
        for (int i3 = 0; i3 < i2; i3++) {
            zziu zziuVar = this.zznb[i3];
            if (zziuVar != null) {
                zzitVar.zznb[i3] = (zziu) zziuVar.clone();
            }
        }
        zzitVar.mSize = i2;
        return zzitVar;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzit)) {
            return false;
        }
        zzit zzitVar = (zzit) obj;
        int i2 = this.mSize;
        if (i2 != zzitVar.mSize) {
            return false;
        }
        int[] iArr = this.zzna;
        int[] iArr2 = zzitVar.zzna;
        int i3 = 0;
        while (true) {
            if (i3 >= i2) {
                zziu[] zziuVarArr = this.zznb;
                zziu[] zziuVarArr2 = zzitVar.zznb;
                int i4 = this.mSize;
                for (int i5 = 0; i5 < i4; i5++) {
                    if (zziuVarArr[i5].equals(zziuVarArr2[i5])) {
                    }
                }
                return true;
            }
            if (iArr[i3] != iArr2[i3]) {
                break;
            }
            i3++;
        }
        return false;
    }

    public final int hashCode() {
        int iHashCode = 17;
        for (int i2 = 0; i2 < this.mSize; i2++) {
            iHashCode = (((iHashCode * 31) + this.zzna[i2]) * 31) + this.zznb[i2].hashCode();
        }
        return iHashCode;
    }

    public final boolean isEmpty() {
        return this.mSize == 0;
    }

    final int size() {
        return this.mSize;
    }

    final void zza(int i2, zziu zziuVar) {
        int iZzt = zzt(i2);
        if (iZzt >= 0) {
            this.zznb[iZzt] = zziuVar;
            return;
        }
        int i3 = ~iZzt;
        int i4 = this.mSize;
        if (i3 < i4) {
            zziu[] zziuVarArr = this.zznb;
            if (zziuVarArr[i3] == zzmy) {
                this.zzna[i3] = i2;
                zziuVarArr[i3] = zziuVar;
                return;
            }
        }
        if (i4 >= this.zzna.length) {
            int iIdealIntArraySize = idealIntArraySize(i4 + 1);
            int[] iArr = new int[iIdealIntArraySize];
            zziu[] zziuVarArr2 = new zziu[iIdealIntArraySize];
            int[] iArr2 = this.zzna;
            System.arraycopy(iArr2, 0, iArr, 0, iArr2.length);
            zziu[] zziuVarArr3 = this.zznb;
            System.arraycopy(zziuVarArr3, 0, zziuVarArr2, 0, zziuVarArr3.length);
            this.zzna = iArr;
            this.zznb = zziuVarArr2;
        }
        int i5 = this.mSize;
        if (i5 - i3 != 0) {
            int[] iArr3 = this.zzna;
            int i6 = i3 + 1;
            System.arraycopy(iArr3, i3, iArr3, i6, i5 - i3);
            zziu[] zziuVarArr4 = this.zznb;
            System.arraycopy(zziuVarArr4, i3, zziuVarArr4, i6, this.mSize - i3);
        }
        this.zzna[i3] = i2;
        this.zznb[i3] = zziuVar;
        this.mSize++;
    }

    final zziu zzr(int i2) {
        zziu zziuVar;
        int iZzt = zzt(i2);
        if (iZzt < 0 || (zziuVar = this.zznb[iZzt]) == zzmy) {
            return null;
        }
        return zziuVar;
    }

    final zziu zzs(int i2) {
        return this.zznb[i2];
    }

    private zzit(int i2) {
        this.zzmz = false;
        int iIdealIntArraySize = idealIntArraySize(i2);
        this.zzna = new int[iIdealIntArraySize];
        this.zznb = new zziu[iIdealIntArraySize];
        this.mSize = 0;
    }
}
