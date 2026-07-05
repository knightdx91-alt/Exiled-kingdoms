package com.google.android.gms.internal.play_billing;

import java.util.Arrays;

/* JADX INFO: compiled from: com.android.billingclient:billing@@6.0.1 */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzaf extends zzx {
    static final zzx zza = new zzaf(null, new Object[0], 0);
    final transient Object[] zzb;
    private final transient Object zzc;
    private final transient int zzd;

    private zzaf(Object obj, Object[] objArr, int i2) {
        this.zzc = obj;
        this.zzb = objArr;
        this.zzd = i2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r15v0 */
    /* JADX WARN: Type inference failed for: r2v11 */
    /* JADX WARN: Type inference failed for: r2v13 */
    /* JADX WARN: Type inference failed for: r2v14 */
    /* JADX WARN: Type inference failed for: r2v18 */
    /* JADX WARN: Type inference failed for: r2v19, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r2v24 */
    /* JADX WARN: Type inference failed for: r2v27 */
    /* JADX WARN: Type inference failed for: r2v34 */
    /* JADX WARN: Type inference failed for: r2v35 */
    /* JADX WARN: Type inference failed for: r2v5 */
    /* JADX WARN: Type inference failed for: r2v6 */
    /* JADX WARN: Type inference failed for: r5v4, types: [int[]] */
    /* JADX WARN: Type inference failed for: r5v8 */
    /* JADX WARN: Type inference failed for: r6v10 */
    /* JADX WARN: Type inference failed for: r6v11 */
    /* JADX WARN: Type inference failed for: r6v3, types: [java.lang.Object[]] */
    /* JADX WARN: Type inference failed for: r6v4 */
    static zzaf zzf(int i2, Object[] objArr, zzw zzwVar) {
        int iHighestOneBit;
        short[] sArr;
        ?? r6;
        ?? r2;
        int i3 = i2;
        Object[] objArrCopyOf = objArr;
        if (i3 == 0) {
            return (zzaf) zza;
        }
        zzv zzvVar = null;
        ?? r22 = 0;
        zzv zzvVar2 = null;
        zzv zzvVar3 = null;
        if (i3 == 1) {
            Object obj = objArrCopyOf[0];
            obj.getClass();
            Object obj2 = objArrCopyOf[1];
            obj2.getClass();
            zzp.zza(obj, obj2);
            return new zzaf(null, objArrCopyOf, 1);
        }
        zzm.zzb(i3, objArrCopyOf.length >> 1, "index");
        char c2 = 2;
        int iMax = Math.max(i3, 2);
        if (iMax < 751619276) {
            iHighestOneBit = Integer.highestOneBit(iMax - 1);
            do {
                iHighestOneBit += iHighestOneBit;
            } while (((double) iHighestOneBit) * 0.7d < iMax);
        } else {
            iHighestOneBit = 1073741824;
            if (iMax >= 1073741824) {
                throw new IllegalArgumentException("collection too large");
            }
        }
        if (i3 == 1) {
            Object obj3 = objArrCopyOf[0];
            obj3.getClass();
            Object obj4 = objArrCopyOf[1];
            obj4.getClass();
            zzp.zza(obj3, obj4);
        } else {
            int i4 = iHighestOneBit - 1;
            byte b2 = -1;
            if (iHighestOneBit <= 128) {
                byte[] bArr = new byte[iHighestOneBit];
                Arrays.fill(bArr, (byte) -1);
                int i5 = 0;
                for (int i6 = 0; i6 < i3; i6++) {
                    int i7 = i5 + i5;
                    int i8 = i6 + i6;
                    Object obj5 = objArrCopyOf[i8];
                    obj5.getClass();
                    Object obj6 = objArrCopyOf[i8 ^ 1];
                    obj6.getClass();
                    zzp.zza(obj5, obj6);
                    int iZza = zzq.zza(obj5.hashCode());
                    while (true) {
                        int i9 = iZza & i4;
                        int i10 = bArr[i9] & 255;
                        if (i10 == 255) {
                            bArr[i9] = (byte) i7;
                            if (i5 < i6) {
                                objArrCopyOf[i7] = obj5;
                                objArrCopyOf[i7 ^ 1] = obj6;
                            }
                            i5++;
                        } else {
                            if (obj5.equals(objArrCopyOf[i10 == true ? 1 : 0])) {
                                int i11 = ~i10;
                                Object obj7 = objArrCopyOf[i11 == true ? 1 : 0];
                                obj7.getClass();
                                zzv zzvVar4 = new zzv(obj5, obj6, obj7);
                                objArrCopyOf[i11 == true ? 1 : 0] = obj6;
                                zzvVar2 = zzvVar4;
                                break;
                            }
                            iZza = i9 + 1;
                        }
                    }
                }
                if (i5 == i3) {
                    r2 = bArr;
                    c2 = 2;
                    r22 = r2;
                } else {
                    r22 = new Object[]{bArr, Integer.valueOf(i5), zzvVar2};
                    c2 = 2;
                }
            } else if (iHighestOneBit <= 32768) {
                sArr = new short[iHighestOneBit];
                Arrays.fill(sArr, (short) -1);
                int i12 = 0;
                for (int i13 = 0; i13 < i3; i13++) {
                    int i14 = i12 + i12;
                    int i15 = i13 + i13;
                    Object obj8 = objArrCopyOf[i15];
                    obj8.getClass();
                    Object obj9 = objArrCopyOf[i15 ^ 1];
                    obj9.getClass();
                    zzp.zza(obj8, obj9);
                    int iZza2 = zzq.zza(obj8.hashCode());
                    while (true) {
                        int i16 = iZza2 & i4;
                        char c3 = (char) sArr[i16];
                        if (c3 == 65535) {
                            sArr[i16] = (short) i14;
                            if (i12 < i13) {
                                objArrCopyOf[i14] = obj8;
                                objArrCopyOf[i14 ^ 1] = obj9;
                            }
                            i12++;
                        } else {
                            if (obj8.equals(objArrCopyOf[c3])) {
                                int i17 = c3 ^ 1;
                                Object obj10 = objArrCopyOf[i17 == true ? 1 : 0];
                                obj10.getClass();
                                zzv zzvVar5 = new zzv(obj8, obj9, obj10);
                                objArrCopyOf[i17 == true ? 1 : 0] = obj9;
                                zzvVar3 = zzvVar5;
                                break;
                            }
                            iZza2 = i16 + 1;
                        }
                    }
                }
                if (i12 != i3) {
                    c2 = 2;
                    r6 = new Object[]{sArr, Integer.valueOf(i12), zzvVar3};
                    r22 = r6;
                }
                r2 = sArr;
                c2 = 2;
                r22 = r2;
            } else {
                sArr = new int[iHighestOneBit];
                Arrays.fill((int[]) sArr, -1);
                int i18 = 0;
                int i19 = 0;
                while (i18 < i3) {
                    int i20 = i19 + i19;
                    int i21 = i18 + i18;
                    Object obj11 = objArrCopyOf[i21];
                    obj11.getClass();
                    Object obj12 = objArrCopyOf[i21 ^ 1];
                    obj12.getClass();
                    zzp.zza(obj11, obj12);
                    int iZza3 = zzq.zza(obj11.hashCode());
                    while (true) {
                        int i22 = iZza3 & i4;
                        ?? r15 = sArr[i22];
                        if (r15 == b2) {
                            sArr[i22] = i20;
                            if (i19 < i18) {
                                objArrCopyOf[i20] = obj11;
                                objArrCopyOf[i20 ^ 1] = obj12;
                            }
                            i19++;
                        } else {
                            if (obj11.equals(objArrCopyOf[r15])) {
                                int i23 = r15 ^ 1;
                                Object obj13 = objArrCopyOf[i23 == true ? 1 : 0];
                                obj13.getClass();
                                zzv zzvVar6 = new zzv(obj11, obj12, obj13);
                                objArrCopyOf[i23 == true ? 1 : 0] = obj12;
                                zzvVar = zzvVar6;
                                break;
                            }
                            iZza3 = i22 + 1;
                            b2 = -1;
                        }
                    }
                    i18++;
                    b2 = -1;
                }
                if (i19 != i3) {
                    c2 = 2;
                    r6 = new Object[]{sArr, Integer.valueOf(i19), zzvVar};
                    r22 = r6;
                }
                r2 = sArr;
                c2 = 2;
                r22 = r2;
            }
        }
        boolean z2 = r22 instanceof Object[];
        ?? r23 = r22;
        if (z2) {
            Object[] objArr2 = (Object[]) r22;
            zzwVar.zzc = (zzv) objArr2[c2];
            Object obj14 = objArr2[0];
            int iIntValue = ((Integer) objArr2[1]).intValue();
            objArrCopyOf = Arrays.copyOf(objArrCopyOf, iIntValue + iIntValue);
            r23 = obj14;
            i3 = iIntValue;
        }
        return new zzaf(r23, objArrCopyOf, i3);
    }

    /* JADX WARN: Removed duplicated region for block: B:4:0x0009 A[EDGE_INSN: B:43:0x0009->B:4:0x0009 BREAK  A[LOOP:0: B:15:0x0038->B:21:0x004e], EDGE_INSN: B:45:0x0009->B:4:0x0009 BREAK  A[LOOP:1: B:25:0x0063->B:31:0x007a], EDGE_INSN: B:47:0x0009->B:4:0x0009 BREAK  A[LOOP:2: B:33:0x0089->B:42:0x00a0]] */
    @Override // com.google.android.gms.internal.play_billing.zzx, java.util.Map
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object get(Object obj) {
        Object obj2;
        Object obj3 = this.zzc;
        Object[] objArr = this.zzb;
        int i2 = this.zzd;
        if (obj != null) {
            if (i2 == 1) {
                Object obj4 = objArr[0];
                obj4.getClass();
                if (obj4.equals(obj)) {
                    obj2 = objArr[1];
                    obj2.getClass();
                } else {
                    obj2 = null;
                }
            } else if (obj3 != null) {
                if (obj3 instanceof byte[]) {
                    byte[] bArr = (byte[]) obj3;
                    int length = bArr.length - 1;
                    int iZza = zzq.zza(obj.hashCode());
                    while (true) {
                        int i3 = iZza & length;
                        int i4 = bArr[i3] & 255;
                        if (i4 == 255) {
                            break;
                        }
                        if (obj.equals(objArr[i4])) {
                            obj2 = objArr[i4 ^ 1];
                            break;
                        }
                        iZza = i3 + 1;
                    }
                } else if (obj3 instanceof short[]) {
                    short[] sArr = (short[]) obj3;
                    int length2 = sArr.length - 1;
                    int iZza2 = zzq.zza(obj.hashCode());
                    while (true) {
                        int i5 = iZza2 & length2;
                        char c2 = (char) sArr[i5];
                        if (c2 == 65535) {
                            break;
                        }
                        if (obj.equals(objArr[c2])) {
                            obj2 = objArr[c2 ^ 1];
                            break;
                        }
                        iZza2 = i5 + 1;
                    }
                } else {
                    int[] iArr = (int[]) obj3;
                    int length3 = iArr.length - 1;
                    int iZza3 = zzq.zza(obj.hashCode());
                    while (true) {
                        int i6 = iZza3 & length3;
                        int i7 = iArr[i6];
                        if (i7 == -1) {
                            break;
                        }
                        if (obj.equals(objArr[i7])) {
                            obj2 = objArr[i7 ^ 1];
                            break;
                        }
                        iZza3 = i6 + 1;
                    }
                }
            }
        }
        if (obj2 == null) {
            return null;
        }
        return obj2;
    }

    @Override // java.util.Map
    public final int size() {
        return this.zzd;
    }

    @Override // com.google.android.gms.internal.play_billing.zzx
    final zzr zza() {
        return new zzae(this.zzb, 1, this.zzd);
    }

    @Override // com.google.android.gms.internal.play_billing.zzx
    final zzy zzc() {
        return new zzac(this, this.zzb, 0, this.zzd);
    }

    @Override // com.google.android.gms.internal.play_billing.zzx
    final zzy zzd() {
        return new zzad(this, new zzae(this.zzb, 0, this.zzd));
    }
}
