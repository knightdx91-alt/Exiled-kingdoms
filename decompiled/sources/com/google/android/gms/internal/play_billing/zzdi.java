package com.google.android.gms.internal.play_billing;

import a.a;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.decals.Decal;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.drive.DriveFile;
import com.google.android.gms.games.Notifications;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import sun.misc.Unsafe;

/* JADX INFO: compiled from: com.android.billingclient:billing@@6.0.1 */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzdi<T> implements zzdp<T> {
    private static final int[] zza = new int[0];
    private static final Unsafe zzb = zzeq.zzg();
    private final int[] zzc;
    private final Object[] zzd;
    private final int zze;
    private final int zzf;
    private final zzdf zzg;
    private final boolean zzh;
    private final int[] zzi;
    private final int zzj;
    private final int zzk;
    private final zzct zzl;
    private final zzeg zzm;
    private final zzbo zzn;
    private final int zzo;
    private final zzdk zzp;
    private final zzda zzq;

    private zzdi(int[] iArr, Object[] objArr, int i2, int i3, zzdf zzdfVar, int i4, boolean z2, int[] iArr2, int i5, int i6, zzdk zzdkVar, zzct zzctVar, zzeg zzegVar, zzbo zzboVar, zzda zzdaVar) {
        this.zzc = iArr;
        this.zzd = objArr;
        this.zze = i2;
        this.zzf = i3;
        this.zzo = i4;
        boolean z3 = false;
        if (zzboVar != null && zzboVar.zzc(zzdfVar)) {
            z3 = true;
        }
        this.zzh = z3;
        this.zzi = iArr2;
        this.zzj = i5;
        this.zzk = i6;
        this.zzp = zzdkVar;
        this.zzl = zzctVar;
        this.zzm = zzegVar;
        this.zzn = zzboVar;
        this.zzg = zzdfVar;
        this.zzq = zzdaVar;
    }

    private final zzce zzA(int i2) {
        int i3 = i2 / 3;
        return (zzce) this.zzd[i3 + i3 + 1];
    }

    private final zzdp zzB(int i2) {
        int i3 = i2 / 3;
        int i4 = i3 + i3;
        zzdp zzdpVar = (zzdp) this.zzd[i4];
        if (zzdpVar != null) {
            return zzdpVar;
        }
        zzdp zzdpVarZzb = zzdn.zza().zzb((Class) this.zzd[i4 + 1]);
        this.zzd[i4] = zzdpVarZzb;
        return zzdpVarZzb;
    }

    private final Object zzC(int i2) {
        int i3 = i2 / 3;
        return this.zzd[i3 + i3];
    }

    private final Object zzD(Object obj, int i2) {
        zzdp zzdpVarZzB = zzB(i2);
        int iZzy = zzy(i2) & 1048575;
        if (!zzP(obj, i2)) {
            return zzdpVarZzB.zze();
        }
        Object object = zzb.getObject(obj, iZzy);
        if (zzS(object)) {
            return object;
        }
        Object objZze = zzdpVarZzB.zze();
        if (object != null) {
            zzdpVarZzB.zzg(objZze, object);
        }
        return objZze;
    }

    private final Object zzE(Object obj, int i2, int i3) {
        zzdp zzdpVarZzB = zzB(i3);
        if (!zzT(obj, i2, i3)) {
            return zzdpVarZzB.zze();
        }
        Object object = zzb.getObject(obj, zzy(i3) & 1048575);
        if (zzS(object)) {
            return object;
        }
        Object objZze = zzdpVarZzB.zze();
        if (object != null) {
            zzdpVarZzB.zzg(objZze, object);
        }
        return objZze;
    }

    private static Field zzF(Class cls, String str) {
        try {
            return cls.getDeclaredField(str);
        } catch (NoSuchFieldException unused) {
            Field[] declaredFields = cls.getDeclaredFields();
            for (Field field : declaredFields) {
                if (str.equals(field.getName())) {
                    return field;
                }
            }
            throw new RuntimeException("Field " + str + " for " + cls.getName() + " not found. Known fields are " + Arrays.toString(declaredFields));
        }
    }

    private static void zzG(Object obj) {
        if (!zzS(obj)) {
            throw new IllegalArgumentException("Mutating immutable message: ".concat(String.valueOf(obj)));
        }
    }

    private final void zzH(Object obj, Object obj2, int i2) {
        if (zzP(obj2, i2)) {
            int iZzy = zzy(i2) & 1048575;
            Unsafe unsafe = zzb;
            long j2 = iZzy;
            Object object = unsafe.getObject(obj2, j2);
            if (object == null) {
                throw new IllegalStateException("Source subfield " + this.zzc[i2] + " is present but null: " + obj2.toString());
            }
            zzdp zzdpVarZzB = zzB(i2);
            if (!zzP(obj, i2)) {
                if (zzS(object)) {
                    Object objZze = zzdpVarZzB.zze();
                    zzdpVarZzB.zzg(objZze, object);
                    unsafe.putObject(obj, j2, objZze);
                } else {
                    unsafe.putObject(obj, j2, object);
                }
                zzJ(obj, i2);
                return;
            }
            Object object2 = unsafe.getObject(obj, j2);
            if (!zzS(object2)) {
                Object objZze2 = zzdpVarZzB.zze();
                zzdpVarZzB.zzg(objZze2, object2);
                unsafe.putObject(obj, j2, objZze2);
                object2 = objZze2;
            }
            zzdpVarZzB.zzg(object2, object);
        }
    }

    private final void zzI(Object obj, Object obj2, int i2) {
        int i3 = this.zzc[i2];
        if (zzT(obj2, i3, i2)) {
            int iZzy = zzy(i2) & 1048575;
            Unsafe unsafe = zzb;
            long j2 = iZzy;
            Object object = unsafe.getObject(obj2, j2);
            if (object == null) {
                throw new IllegalStateException("Source subfield " + this.zzc[i2] + " is present but null: " + obj2.toString());
            }
            zzdp zzdpVarZzB = zzB(i2);
            if (!zzT(obj, i3, i2)) {
                if (zzS(object)) {
                    Object objZze = zzdpVarZzB.zze();
                    zzdpVarZzB.zzg(objZze, object);
                    unsafe.putObject(obj, j2, objZze);
                } else {
                    unsafe.putObject(obj, j2, object);
                }
                zzK(obj, i3, i2);
                return;
            }
            Object object2 = unsafe.getObject(obj, j2);
            if (!zzS(object2)) {
                Object objZze2 = zzdpVarZzB.zze();
                zzdpVarZzB.zzg(objZze2, object2);
                unsafe.putObject(obj, j2, objZze2);
                object2 = objZze2;
            }
            zzdpVarZzB.zzg(object2, object);
        }
    }

    private final void zzJ(Object obj, int i2) {
        int iZzv = zzv(i2);
        long j2 = 1048575 & iZzv;
        if (j2 == 1048575) {
            return;
        }
        zzeq.zzq(obj, j2, (1 << (iZzv >>> 20)) | zzeq.zzc(obj, j2));
    }

    private final void zzK(Object obj, int i2, int i3) {
        zzeq.zzq(obj, zzv(i3) & 1048575, i2);
    }

    private final void zzL(Object obj, int i2, Object obj2) {
        zzb.putObject(obj, zzy(i2) & 1048575, obj2);
        zzJ(obj, i2);
    }

    private final void zzM(Object obj, int i2, int i3, Object obj2) {
        zzb.putObject(obj, zzy(i3) & 1048575, obj2);
        zzK(obj, i2, i3);
    }

    private final void zzN(zzey zzeyVar, int i2, Object obj, int i3) {
        if (obj == null) {
            return;
        }
        throw null;
    }

    private final boolean zzO(Object obj, Object obj2, int i2) {
        return zzP(obj, i2) == zzP(obj2, i2);
    }

    private final boolean zzP(Object obj, int i2) {
        int iZzv = zzv(i2);
        long j2 = iZzv & 1048575;
        if (j2 != 1048575) {
            return (zzeq.zzc(obj, j2) & (1 << (iZzv >>> 20))) != 0;
        }
        int iZzy = zzy(i2);
        long j3 = iZzy & 1048575;
        switch (zzx(iZzy)) {
            case 0:
                return Double.doubleToRawLongBits(zzeq.zza(obj, j3)) != 0;
            case 1:
                return Float.floatToRawIntBits(zzeq.zzb(obj, j3)) != 0;
            case 2:
                return zzeq.zzd(obj, j3) != 0;
            case 3:
                return zzeq.zzd(obj, j3) != 0;
            case 4:
                return zzeq.zzc(obj, j3) != 0;
            case 5:
                return zzeq.zzd(obj, j3) != 0;
            case 6:
                return zzeq.zzc(obj, j3) != 0;
            case 7:
                return zzeq.zzw(obj, j3);
            case 8:
                Object objZzf = zzeq.zzf(obj, j3);
                if (objZzf instanceof String) {
                    return !((String) objZzf).isEmpty();
                }
                if (objZzf instanceof zzba) {
                    return !zzba.zzb.equals(objZzf);
                }
                throw new IllegalArgumentException();
            case 9:
                return zzeq.zzf(obj, j3) != null;
            case 10:
                return !zzba.zzb.equals(zzeq.zzf(obj, j3));
            case 11:
                return zzeq.zzc(obj, j3) != 0;
            case 12:
                return zzeq.zzc(obj, j3) != 0;
            case 13:
                return zzeq.zzc(obj, j3) != 0;
            case 14:
                return zzeq.zzd(obj, j3) != 0;
            case 15:
                return zzeq.zzc(obj, j3) != 0;
            case 16:
                return zzeq.zzd(obj, j3) != 0;
            case 17:
                return zzeq.zzf(obj, j3) != null;
            default:
                throw new IllegalArgumentException();
        }
    }

    private final boolean zzQ(Object obj, int i2, int i3, int i4, int i5) {
        return i3 == 1048575 ? zzP(obj, i2) : (i4 & i5) != 0;
    }

    private static boolean zzR(Object obj, int i2, zzdp zzdpVar) {
        return zzdpVar.zzk(zzeq.zzf(obj, i2 & 1048575));
    }

    private static boolean zzS(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof zzcb) {
            return ((zzcb) obj).zzt();
        }
        return true;
    }

    private final boolean zzT(Object obj, int i2, int i3) {
        return zzeq.zzc(obj, (long) (zzv(i3) & 1048575)) == i2;
    }

    private static boolean zzU(Object obj, long j2) {
        return ((Boolean) zzeq.zzf(obj, j2)).booleanValue();
    }

    private static final void zzV(int i2, Object obj, zzey zzeyVar) {
        if (obj instanceof String) {
            zzeyVar.zzF(i2, (String) obj);
        } else {
            zzeyVar.zzd(i2, (zzba) obj);
        }
    }

    static zzeh zzd(Object obj) {
        zzcb zzcbVar = (zzcb) obj;
        zzeh zzehVar = zzcbVar.zzc;
        if (zzehVar != zzeh.zzc()) {
            return zzehVar;
        }
        zzeh zzehVarZzf = zzeh.zzf();
        zzcbVar.zzc = zzehVarZzf;
        return zzehVarZzf;
    }

    /* JADX WARN: Removed duplicated region for block: B:123:0x0250  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x0256  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x026c  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x026f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    static zzdi zzl(Class cls, zzdc zzdcVar, zzdk zzdkVar, zzct zzctVar, zzeg zzegVar, zzbo zzboVar, zzda zzdaVar) {
        int i2;
        int iCharAt;
        int iCharAt2;
        int i3;
        int[] iArr;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        char cCharAt;
        int i9;
        char cCharAt2;
        int i10;
        char cCharAt3;
        int i11;
        char cCharAt4;
        int i12;
        char cCharAt5;
        int i13;
        char cCharAt6;
        int i14;
        char cCharAt7;
        int i15;
        char cCharAt8;
        int i16;
        int i17;
        int i18;
        int i19;
        int i20;
        int iObjectFieldOffset;
        String str;
        int i21;
        int i22;
        int i23;
        int iObjectFieldOffset2;
        Field fieldZzF;
        char cCharAt9;
        int i24;
        int i25;
        Object obj;
        Field fieldZzF2;
        Object obj2;
        Field fieldZzF3;
        int i26;
        char cCharAt10;
        int i27;
        char cCharAt11;
        int i28;
        char cCharAt12;
        int i29;
        char cCharAt13;
        if (!(zzdcVar instanceof zzdo)) {
            throw null;
        }
        zzdo zzdoVar = (zzdo) zzdcVar;
        String strZzd = zzdoVar.zzd();
        int length = strZzd.length();
        char c2 = 55296;
        if (strZzd.charAt(0) >= 55296) {
            int i30 = 1;
            while (true) {
                i2 = i30 + 1;
                if (strZzd.charAt(i30) < 55296) {
                    break;
                }
                i30 = i2;
            }
        } else {
            i2 = 1;
        }
        int i31 = i2 + 1;
        int iCharAt3 = strZzd.charAt(i2);
        if (iCharAt3 >= 55296) {
            int i32 = iCharAt3 & 8191;
            int i33 = 13;
            while (true) {
                i29 = i31 + 1;
                cCharAt13 = strZzd.charAt(i31);
                if (cCharAt13 < 55296) {
                    break;
                }
                i32 |= (cCharAt13 & 8191) << i33;
                i33 += 13;
                i31 = i29;
            }
            iCharAt3 = i32 | (cCharAt13 << i33);
            i31 = i29;
        }
        if (iCharAt3 == 0) {
            iCharAt = 0;
            iCharAt2 = 0;
            i4 = 0;
            i7 = 0;
            i3 = 0;
            i5 = 0;
            iArr = zza;
            i6 = 0;
        } else {
            int i34 = i31 + 1;
            int iCharAt4 = strZzd.charAt(i31);
            if (iCharAt4 >= 55296) {
                int i35 = iCharAt4 & 8191;
                int i36 = 13;
                while (true) {
                    i15 = i34 + 1;
                    cCharAt8 = strZzd.charAt(i34);
                    if (cCharAt8 < 55296) {
                        break;
                    }
                    i35 |= (cCharAt8 & 8191) << i36;
                    i36 += 13;
                    i34 = i15;
                }
                iCharAt4 = i35 | (cCharAt8 << i36);
                i34 = i15;
            }
            int i37 = i34 + 1;
            int iCharAt5 = strZzd.charAt(i34);
            if (iCharAt5 >= 55296) {
                int i38 = iCharAt5 & 8191;
                int i39 = 13;
                while (true) {
                    i14 = i37 + 1;
                    cCharAt7 = strZzd.charAt(i37);
                    if (cCharAt7 < 55296) {
                        break;
                    }
                    i38 |= (cCharAt7 & 8191) << i39;
                    i39 += 13;
                    i37 = i14;
                }
                iCharAt5 = i38 | (cCharAt7 << i39);
                i37 = i14;
            }
            int i40 = i37 + 1;
            int iCharAt6 = strZzd.charAt(i37);
            if (iCharAt6 >= 55296) {
                int i41 = iCharAt6 & 8191;
                int i42 = 13;
                while (true) {
                    i13 = i40 + 1;
                    cCharAt6 = strZzd.charAt(i40);
                    if (cCharAt6 < 55296) {
                        break;
                    }
                    i41 |= (cCharAt6 & 8191) << i42;
                    i42 += 13;
                    i40 = i13;
                }
                iCharAt6 = i41 | (cCharAt6 << i42);
                i40 = i13;
            }
            int i43 = i40 + 1;
            int iCharAt7 = strZzd.charAt(i40);
            if (iCharAt7 >= 55296) {
                int i44 = iCharAt7 & 8191;
                int i45 = 13;
                while (true) {
                    i12 = i43 + 1;
                    cCharAt5 = strZzd.charAt(i43);
                    if (cCharAt5 < 55296) {
                        break;
                    }
                    i44 |= (cCharAt5 & 8191) << i45;
                    i45 += 13;
                    i43 = i12;
                }
                iCharAt7 = i44 | (cCharAt5 << i45);
                i43 = i12;
            }
            int i46 = i43 + 1;
            iCharAt = strZzd.charAt(i43);
            if (iCharAt >= 55296) {
                int i47 = iCharAt & 8191;
                int i48 = 13;
                while (true) {
                    i11 = i46 + 1;
                    cCharAt4 = strZzd.charAt(i46);
                    if (cCharAt4 < 55296) {
                        break;
                    }
                    i47 |= (cCharAt4 & 8191) << i48;
                    i48 += 13;
                    i46 = i11;
                }
                iCharAt = i47 | (cCharAt4 << i48);
                i46 = i11;
            }
            int i49 = i46 + 1;
            iCharAt2 = strZzd.charAt(i46);
            if (iCharAt2 >= 55296) {
                int i50 = iCharAt2 & 8191;
                int i51 = 13;
                while (true) {
                    i10 = i49 + 1;
                    cCharAt3 = strZzd.charAt(i49);
                    if (cCharAt3 < 55296) {
                        break;
                    }
                    i50 |= (cCharAt3 & 8191) << i51;
                    i51 += 13;
                    i49 = i10;
                }
                iCharAt2 = i50 | (cCharAt3 << i51);
                i49 = i10;
            }
            int i52 = i49 + 1;
            int iCharAt8 = strZzd.charAt(i49);
            if (iCharAt8 >= 55296) {
                int i53 = iCharAt8 & 8191;
                int i54 = 13;
                while (true) {
                    i9 = i52 + 1;
                    cCharAt2 = strZzd.charAt(i52);
                    if (cCharAt2 < 55296) {
                        break;
                    }
                    i53 |= (cCharAt2 & 8191) << i54;
                    i54 += 13;
                    i52 = i9;
                }
                iCharAt8 = i53 | (cCharAt2 << i54);
                i52 = i9;
            }
            int i55 = i52 + 1;
            int iCharAt9 = strZzd.charAt(i52);
            if (iCharAt9 >= 55296) {
                int i56 = iCharAt9 & 8191;
                int i57 = 13;
                while (true) {
                    i8 = i55 + 1;
                    cCharAt = strZzd.charAt(i55);
                    if (cCharAt < 55296) {
                        break;
                    }
                    i56 |= (cCharAt & 8191) << i57;
                    i57 += 13;
                    i55 = i8;
                }
                iCharAt9 = i56 | (cCharAt << i57);
                i55 = i8;
            }
            i3 = iCharAt4 + iCharAt4 + iCharAt5;
            iArr = new int[iCharAt9 + iCharAt2 + iCharAt8];
            i4 = iCharAt6;
            i5 = iCharAt9;
            i6 = iCharAt4;
            i7 = iCharAt7;
            i31 = i55;
        }
        Unsafe unsafe = zzb;
        Object[] objArrZze = zzdoVar.zze();
        Class<?> cls2 = zzdoVar.zza().getClass();
        int i58 = i5 + iCharAt2;
        int i59 = iCharAt + iCharAt;
        int[] iArr2 = new int[iCharAt * 3];
        Object[] objArr = new Object[i59];
        int i60 = 0;
        int i61 = 0;
        int i62 = i5;
        int i63 = i58;
        while (i31 < length) {
            int i64 = i31 + 1;
            int iCharAt10 = strZzd.charAt(i31);
            if (iCharAt10 >= c2) {
                int i65 = iCharAt10 & 8191;
                int i66 = i64;
                int i67 = 13;
                while (true) {
                    i28 = i66 + 1;
                    cCharAt12 = strZzd.charAt(i66);
                    if (cCharAt12 < c2) {
                        break;
                    }
                    i65 |= (cCharAt12 & 8191) << i67;
                    i67 += 13;
                    i66 = i28;
                }
                iCharAt10 = i65 | (cCharAt12 << i67);
                i16 = i28;
            } else {
                i16 = i64;
            }
            int i68 = i16 + 1;
            int iCharAt11 = strZzd.charAt(i16);
            if (iCharAt11 >= c2) {
                int i69 = iCharAt11 & 8191;
                int i70 = i68;
                int i71 = 13;
                while (true) {
                    i27 = i70 + 1;
                    cCharAt11 = strZzd.charAt(i70);
                    if (cCharAt11 < c2) {
                        break;
                    }
                    i69 |= (cCharAt11 & 8191) << i71;
                    i71 += 13;
                    i70 = i27;
                }
                iCharAt11 = i69 | (cCharAt11 << i71);
                i17 = i27;
            } else {
                i17 = i68;
            }
            if ((iCharAt11 & GL20.GL_STENCIL_BUFFER_BIT) != 0) {
                iArr[i60] = i61;
                i60++;
            }
            int i72 = iCharAt11 & 255;
            if (i72 >= 51) {
                int i73 = i17 + 1;
                int iCharAt12 = strZzd.charAt(i17);
                i18 = length;
                char c3 = 55296;
                if (iCharAt12 >= 55296) {
                    int i74 = iCharAt12 & 8191;
                    int i75 = 13;
                    while (true) {
                        i26 = i73 + 1;
                        cCharAt10 = strZzd.charAt(i73);
                        if (cCharAt10 < c3) {
                            break;
                        }
                        i74 |= (cCharAt10 & 8191) << i75;
                        i75 += 13;
                        i73 = i26;
                        c3 = 55296;
                    }
                    iCharAt12 = i74 | (cCharAt10 << i75);
                    i73 = i26;
                }
                int i76 = i72 - 51;
                int i77 = i73;
                if (i76 == 9 || i76 == 17) {
                    int i78 = i61 / 3;
                    i25 = i3 + 1;
                    objArr[i78 + i78 + 1] = objArrZze[i3];
                } else {
                    if (i76 == 12 && (zzdoVar.zzc() == 1 || (iCharAt11 & 2048) != 0)) {
                        int i79 = i61 / 3;
                        i25 = i3 + 1;
                        objArr[i79 + i79 + 1] = objArrZze[i3];
                    }
                    int i80 = iCharAt12 + iCharAt12;
                    obj = objArrZze[i80];
                    if (obj instanceof Field) {
                        fieldZzF2 = zzF(cls2, (String) obj);
                        objArrZze[i80] = fieldZzF2;
                    } else {
                        fieldZzF2 = (Field) obj;
                    }
                    int i81 = i4;
                    i19 = i7;
                    iObjectFieldOffset2 = (int) unsafe.objectFieldOffset(fieldZzF2);
                    int i82 = i80 + 1;
                    obj2 = objArrZze[i82];
                    if (obj2 instanceof Field) {
                        fieldZzF3 = zzF(cls2, (String) obj2);
                        objArrZze[i82] = fieldZzF3;
                    } else {
                        fieldZzF3 = (Field) obj2;
                    }
                    i20 = i81;
                    i21 = i3;
                    i22 = i77;
                    str = strZzd;
                    iObjectFieldOffset = (int) unsafe.objectFieldOffset(fieldZzF3);
                    i23 = 0;
                }
                i3 = i25;
                int i802 = iCharAt12 + iCharAt12;
                obj = objArrZze[i802];
                if (obj instanceof Field) {
                }
                int i812 = i4;
                i19 = i7;
                iObjectFieldOffset2 = (int) unsafe.objectFieldOffset(fieldZzF2);
                int i822 = i802 + 1;
                obj2 = objArrZze[i822];
                if (obj2 instanceof Field) {
                }
                i20 = i812;
                i21 = i3;
                i22 = i77;
                str = strZzd;
                iObjectFieldOffset = (int) unsafe.objectFieldOffset(fieldZzF3);
                i23 = 0;
            } else {
                i18 = length;
                int i83 = i4;
                i19 = i7;
                int i84 = i3 + 1;
                Field fieldZzF4 = zzF(cls2, (String) objArrZze[i3]);
                if (i72 == 9 || i72 == 17) {
                    i20 = i83;
                    int i85 = i61 / 3;
                    objArr[i85 + i85 + 1] = fieldZzF4.getType();
                } else {
                    if (i72 == 27 || i72 == 49) {
                        i20 = i83;
                        int i86 = i61 / 3;
                        i24 = i3 + 2;
                        objArr[i86 + i86 + 1] = objArrZze[i84];
                    } else if (i72 == 12 || i72 == 30 || i72 == 44) {
                        i20 = i83;
                        if (zzdoVar.zzc() == 1 || (iCharAt11 & 2048) != 0) {
                            int i87 = i61 / 3;
                            i24 = i3 + 2;
                            objArr[i87 + i87 + 1] = objArrZze[i84];
                        }
                    } else if (i72 == 50) {
                        int i88 = i62 + 1;
                        iArr[i62] = i61;
                        int i89 = i61 / 3;
                        int i90 = i3 + 2;
                        int i91 = i89 + i89;
                        objArr[i91] = objArrZze[i84];
                        if ((iCharAt11 & 2048) != 0) {
                            i84 = i3 + 3;
                            objArr[i91 + 1] = objArrZze[i90];
                            i20 = i83;
                            i62 = i88;
                        } else {
                            i62 = i88;
                            i84 = i90;
                            i20 = i83;
                        }
                    } else {
                        i20 = i83;
                    }
                    i84 = i24;
                }
                int iObjectFieldOffset3 = (int) unsafe.objectFieldOffset(fieldZzF4);
                iObjectFieldOffset = 1048575;
                if ((iCharAt11 & 4096) == 0 || i72 > 17) {
                    str = strZzd;
                    i21 = i84;
                    i22 = i17;
                    i23 = 0;
                } else {
                    int i92 = i17 + 1;
                    int iCharAt13 = strZzd.charAt(i17);
                    if (iCharAt13 >= 55296) {
                        int i93 = iCharAt13 & 8191;
                        int i94 = 13;
                        while (true) {
                            i22 = i92 + 1;
                            cCharAt9 = strZzd.charAt(i92);
                            if (cCharAt9 < 55296) {
                                break;
                            }
                            i93 |= (cCharAt9 & 8191) << i94;
                            i94 += 13;
                            i92 = i22;
                        }
                        iCharAt13 = i93 | (cCharAt9 << i94);
                    } else {
                        i22 = i92;
                    }
                    int i95 = (iCharAt13 / 32) + i6 + i6;
                    Object obj3 = objArrZze[i95];
                    if (obj3 instanceof Field) {
                        fieldZzF = (Field) obj3;
                    } else {
                        fieldZzF = zzF(cls2, (String) obj3);
                        objArrZze[i95] = fieldZzF;
                    }
                    str = strZzd;
                    i21 = i84;
                    i23 = iCharAt13 % 32;
                    iObjectFieldOffset = (int) unsafe.objectFieldOffset(fieldZzF);
                }
                if (i72 >= 18 && i72 <= 49) {
                    iArr[i63] = iObjectFieldOffset3;
                    i63++;
                }
                iObjectFieldOffset2 = iObjectFieldOffset3;
            }
            int i96 = i61 + 1;
            iArr2[i61] = iCharAt10;
            int i97 = i61 + 2;
            iArr2[i96] = iObjectFieldOffset2 | ((iCharAt11 & 256) != 0 ? DriveFile.MODE_READ_ONLY : 0) | ((iCharAt11 & GL20.GL_NEVER) != 0 ? DriveFile.MODE_WRITE_ONLY : 0) | (i72 << 20);
            i61 += 3;
            iArr2[i97] = (i23 << 20) | iObjectFieldOffset;
            strZzd = str;
            i31 = i22;
            length = i18;
            i4 = i20;
            i3 = i21;
            i7 = i19;
            c2 = 55296;
        }
        return new zzdi(iArr2, objArr, i4, i7, zzdoVar.zza(), zzdoVar.zzc(), false, iArr, i5, i58, zzdkVar, zzctVar, zzegVar, zzboVar, zzdaVar);
    }

    private static double zzm(Object obj, long j2) {
        return ((Double) zzeq.zzf(obj, j2)).doubleValue();
    }

    private static float zzn(Object obj, long j2) {
        return ((Float) zzeq.zzf(obj, j2)).floatValue();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private final int zzo(Object obj) {
        int i2;
        int iZzn;
        int iZzt;
        boolean z2;
        int iZzc;
        Unsafe unsafe = zzb;
        int i3 = 1048575;
        int i4 = 1048575;
        int i5 = 0;
        int iC = 0;
        int i6 = 0;
        while (i5 < this.zzc.length) {
            int iZzy = zzy(i5);
            int[] iArr = this.zzc;
            int i7 = iArr[i5];
            int iZzx = zzx(iZzy);
            if (iZzx <= 17) {
                int i8 = iArr[i5 + 2];
                int i9 = i8 & i3;
                int i10 = i8 >>> 20;
                if (i9 != i4) {
                    i6 = unsafe.getInt(obj, i9);
                    i4 = i9;
                }
                i2 = 1 << i10;
            } else {
                i2 = 0;
            }
            long j2 = iZzy & i3;
            switch (iZzx) {
                case 0:
                    if ((i6 & i2) != 0) {
                        iC = a.c(i7 << 3, 8, iC);
                    }
                    break;
                case 1:
                    if ((i6 & i2) != 0) {
                        iC = a.c(i7 << 3, 4, iC);
                    }
                    break;
                case 2:
                    if ((i6 & i2) != 0) {
                        iC = a.c(i7 << 3, zzbi.zzy(unsafe.getLong(obj, j2)), iC);
                    }
                    break;
                case 3:
                    if ((i6 & i2) != 0) {
                        iC = a.c(i7 << 3, zzbi.zzy(unsafe.getLong(obj, j2)), iC);
                    }
                    break;
                case 4:
                    if ((i6 & i2) != 0) {
                        iC = a.c(i7 << 3, zzbi.zzu(unsafe.getInt(obj, j2)), iC);
                    }
                    break;
                case 5:
                    if ((i6 & i2) != 0) {
                        iC = a.c(i7 << 3, 8, iC);
                    }
                    break;
                case 6:
                    if ((i6 & i2) != 0) {
                        iC = a.c(i7 << 3, 4, iC);
                    }
                    break;
                case 7:
                    if ((i6 & i2) != 0) {
                        iC = a.c(i7 << 3, 1, iC);
                    }
                    break;
                case 8:
                    if ((i6 & i2) != 0) {
                        Object object = unsafe.getObject(obj, j2);
                        if (!(object instanceof zzba)) {
                            iC = a.c(i7 << 3, zzbi.zzw((String) object), iC);
                        } else {
                            int i11 = zzbi.zzb;
                            int iZzd = ((zzba) object).zzd();
                            iC = a.c(i7 << 3, zzbi.zzx(iZzd) + iZzd, iC);
                        }
                    }
                    break;
                case 9:
                    if ((i6 & i2) != 0) {
                        iZzn = zzdr.zzn(i7, unsafe.getObject(obj, j2), zzB(i5));
                        iC += iZzn;
                    }
                    break;
                case 10:
                    if ((i6 & i2) != 0) {
                        zzba zzbaVar = (zzba) unsafe.getObject(obj, j2);
                        int i12 = zzbi.zzb;
                        int iZzd2 = zzbaVar.zzd();
                        iC = a.c(i7 << 3, zzbi.zzx(iZzd2) + iZzd2, iC);
                    }
                    break;
                case 11:
                    if ((i6 & i2) != 0) {
                        iC = a.c(i7 << 3, zzbi.zzx(unsafe.getInt(obj, j2)), iC);
                    }
                    break;
                case 12:
                    if ((i6 & i2) != 0) {
                        iC = a.c(i7 << 3, zzbi.zzu(unsafe.getInt(obj, j2)), iC);
                    }
                    break;
                case 13:
                    if ((i6 & i2) != 0) {
                        iC = a.c(i7 << 3, 4, iC);
                    }
                    break;
                case 14:
                    if ((i6 & i2) != 0) {
                        iC = a.c(i7 << 3, 8, iC);
                    }
                    break;
                case 15:
                    if ((i6 & i2) != 0) {
                        int i13 = unsafe.getInt(obj, j2);
                        iC = a.c((i13 >> 31) ^ (i13 + i13), zzbi.zzx(i7 << 3), iC);
                    }
                    break;
                case 16:
                    if ((i2 & i6) != 0) {
                        long j3 = unsafe.getLong(obj, j2);
                        iC += zzbi.zzy((j3 >> 63) ^ (j3 + j3)) + zzbi.zzx(i7 << 3);
                    }
                    break;
                case 17:
                    if ((i6 & i2) != 0) {
                        iZzn = zzbi.zzt(i7, (zzdf) unsafe.getObject(obj, j2), zzB(i5));
                        iC += iZzn;
                    }
                    break;
                case 18:
                    iZzn = zzdr.zzg(i7, (List) unsafe.getObject(obj, j2), false);
                    iC += iZzn;
                    break;
                case 19:
                    iZzn = zzdr.zze(i7, (List) unsafe.getObject(obj, j2), false);
                    iC += iZzn;
                    break;
                case 20:
                    iZzn = zzdr.zzl(i7, (List) unsafe.getObject(obj, j2), false);
                    iC += iZzn;
                    break;
                case Decal.C4 /* 21 */:
                    iZzn = zzdr.zzw(i7, (List) unsafe.getObject(obj, j2), false);
                    iC += iZzn;
                    break;
                case Decal.U4 /* 22 */:
                    iZzn = zzdr.zzj(i7, (List) unsafe.getObject(obj, j2), false);
                    iC += iZzn;
                    break;
                case Decal.V4 /* 23 */:
                    iZzn = zzdr.zzg(i7, (List) unsafe.getObject(obj, j2), false);
                    iC += iZzn;
                    break;
                case Decal.SIZE /* 24 */:
                    iZzn = zzdr.zze(i7, (List) unsafe.getObject(obj, j2), false);
                    iC += iZzn;
                    break;
                case 25:
                    iZzn = zzdr.zza(i7, (List) unsafe.getObject(obj, j2), false);
                    iC += iZzn;
                    break;
                case 26:
                    iZzt = zzdr.zzt(i7, (List) unsafe.getObject(obj, j2));
                    iC += iZzt;
                    break;
                case 27:
                    iZzt = zzdr.zzo(i7, (List) unsafe.getObject(obj, j2), zzB(i5));
                    iC += iZzt;
                    break;
                case 28:
                    iZzt = zzdr.zzb(i7, (List) unsafe.getObject(obj, j2));
                    iC += iZzt;
                    break;
                case 29:
                    iZzt = zzdr.zzu(i7, (List) unsafe.getObject(obj, j2), false);
                    iC += iZzt;
                    break;
                case 30:
                    z2 = false;
                    iZzc = zzdr.zzc(i7, (List) unsafe.getObject(obj, j2), false);
                    iC += iZzc;
                    break;
                case 31:
                    z2 = false;
                    iZzc = zzdr.zze(i7, (List) unsafe.getObject(obj, j2), false);
                    iC += iZzc;
                    break;
                case 32:
                    z2 = false;
                    iZzc = zzdr.zzg(i7, (List) unsafe.getObject(obj, j2), false);
                    iC += iZzc;
                    break;
                case 33:
                    z2 = false;
                    iZzc = zzdr.zzp(i7, (List) unsafe.getObject(obj, j2), false);
                    iC += iZzc;
                    break;
                case 34:
                    z2 = false;
                    iZzc = zzdr.zzr(i7, (List) unsafe.getObject(obj, j2), false);
                    iC += iZzc;
                    break;
                case 35:
                    int iZzh = zzdr.zzh((List) unsafe.getObject(obj, j2));
                    if (iZzh > 0) {
                        iC = a.d(i7 << 3, zzbi.zzx(iZzh), iZzh, iC);
                    }
                    break;
                case 36:
                    int iZzf = zzdr.zzf((List) unsafe.getObject(obj, j2));
                    if (iZzf > 0) {
                        iC = a.d(i7 << 3, zzbi.zzx(iZzf), iZzf, iC);
                    }
                    break;
                case 37:
                    int iZzm = zzdr.zzm((List) unsafe.getObject(obj, j2));
                    if (iZzm > 0) {
                        iC = a.d(i7 << 3, zzbi.zzx(iZzm), iZzm, iC);
                    }
                    break;
                case 38:
                    int iZzx2 = zzdr.zzx((List) unsafe.getObject(obj, j2));
                    if (iZzx2 > 0) {
                        iC = a.d(i7 << 3, zzbi.zzx(iZzx2), iZzx2, iC);
                    }
                    break;
                case 39:
                    int iZzk = zzdr.zzk((List) unsafe.getObject(obj, j2));
                    if (iZzk > 0) {
                        iC = a.d(i7 << 3, zzbi.zzx(iZzk), iZzk, iC);
                    }
                    break;
                case 40:
                    int iZzh2 = zzdr.zzh((List) unsafe.getObject(obj, j2));
                    if (iZzh2 > 0) {
                        iC = a.d(i7 << 3, zzbi.zzx(iZzh2), iZzh2, iC);
                    }
                    break;
                case 41:
                    int iZzf2 = zzdr.zzf((List) unsafe.getObject(obj, j2));
                    if (iZzf2 > 0) {
                        iC = a.d(i7 << 3, zzbi.zzx(iZzf2), iZzf2, iC);
                    }
                    break;
                case 42:
                    List list = (List) unsafe.getObject(obj, j2);
                    int i14 = zzdr.zza;
                    int size = list.size();
                    if (size > 0) {
                        iC = a.d(i7 << 3, zzbi.zzx(size), size, iC);
                    }
                    break;
                case 43:
                    int iZzv = zzdr.zzv((List) unsafe.getObject(obj, j2));
                    if (iZzv > 0) {
                        iC = a.d(i7 << 3, zzbi.zzx(iZzv), iZzv, iC);
                    }
                    break;
                case 44:
                    int iZzd3 = zzdr.zzd((List) unsafe.getObject(obj, j2));
                    if (iZzd3 > 0) {
                        iC = a.d(i7 << 3, zzbi.zzx(iZzd3), iZzd3, iC);
                    }
                    break;
                case 45:
                    int iZzf3 = zzdr.zzf((List) unsafe.getObject(obj, j2));
                    if (iZzf3 > 0) {
                        iC = a.d(i7 << 3, zzbi.zzx(iZzf3), iZzf3, iC);
                    }
                    break;
                case 46:
                    int iZzh3 = zzdr.zzh((List) unsafe.getObject(obj, j2));
                    if (iZzh3 > 0) {
                        iC = a.d(i7 << 3, zzbi.zzx(iZzh3), iZzh3, iC);
                    }
                    break;
                case 47:
                    int iZzq = zzdr.zzq((List) unsafe.getObject(obj, j2));
                    if (iZzq > 0) {
                        iC = a.d(i7 << 3, zzbi.zzx(iZzq), iZzq, iC);
                    }
                    break;
                case 48:
                    int iZzs = zzdr.zzs((List) unsafe.getObject(obj, j2));
                    if (iZzs > 0) {
                        iC = a.d(i7 << 3, zzbi.zzx(iZzs), iZzs, iC);
                    }
                    break;
                case 49:
                    iZzt = zzdr.zzi(i7, (List) unsafe.getObject(obj, j2), zzB(i5));
                    iC += iZzt;
                    break;
                case 50:
                    zzda.zza(i7, unsafe.getObject(obj, j2), zzC(i5));
                    break;
                case 51:
                    if (zzT(obj, i7, i5)) {
                        iC = a.c(i7 << 3, 8, iC);
                    }
                    break;
                case 52:
                    if (zzT(obj, i7, i5)) {
                        iC = a.c(i7 << 3, 4, iC);
                    }
                    break;
                case 53:
                    if (zzT(obj, i7, i5)) {
                        iC = a.c(i7 << 3, zzbi.zzy(zzz(obj, j2)), iC);
                    }
                    break;
                case 54:
                    if (zzT(obj, i7, i5)) {
                        iC = a.c(i7 << 3, zzbi.zzy(zzz(obj, j2)), iC);
                    }
                    break;
                case 55:
                    if (zzT(obj, i7, i5)) {
                        iC = a.c(i7 << 3, zzbi.zzu(zzp(obj, j2)), iC);
                    }
                    break;
                case 56:
                    if (zzT(obj, i7, i5)) {
                        iC = a.c(i7 << 3, 8, iC);
                    }
                    break;
                case 57:
                    if (zzT(obj, i7, i5)) {
                        iC = a.c(i7 << 3, 4, iC);
                    }
                    break;
                case 58:
                    if (zzT(obj, i7, i5)) {
                        iC = a.c(i7 << 3, 1, iC);
                    }
                    break;
                case 59:
                    if (zzT(obj, i7, i5)) {
                        Object object2 = unsafe.getObject(obj, j2);
                        if (object2 instanceof zzba) {
                            int i15 = zzbi.zzb;
                            int iZzd4 = ((zzba) object2).zzd();
                            iC = a.c(i7 << 3, zzbi.zzx(iZzd4) + iZzd4, iC);
                        } else {
                            iC = a.c(i7 << 3, zzbi.zzw((String) object2), iC);
                        }
                    }
                    break;
                case 60:
                    if (zzT(obj, i7, i5)) {
                        iZzt = zzdr.zzn(i7, unsafe.getObject(obj, j2), zzB(i5));
                        iC += iZzt;
                    }
                    break;
                case 61:
                    if (zzT(obj, i7, i5)) {
                        zzba zzbaVar2 = (zzba) unsafe.getObject(obj, j2);
                        int i16 = zzbi.zzb;
                        int iZzd5 = zzbaVar2.zzd();
                        iC = a.c(i7 << 3, zzbi.zzx(iZzd5) + iZzd5, iC);
                    }
                    break;
                case 62:
                    if (zzT(obj, i7, i5)) {
                        iC = a.c(i7 << 3, zzbi.zzx(zzp(obj, j2)), iC);
                    }
                    break;
                case Notifications.NOTIFICATION_TYPES_ALL /* 63 */:
                    if (zzT(obj, i7, i5)) {
                        iC = a.c(i7 << 3, zzbi.zzu(zzp(obj, j2)), iC);
                    }
                    break;
                case VertexAttributes.Usage.BoneWeight /* 64 */:
                    if (zzT(obj, i7, i5)) {
                        iC = a.c(i7 << 3, 4, iC);
                    }
                    break;
                case 65:
                    if (zzT(obj, i7, i5)) {
                        iC = a.c(i7 << 3, 8, iC);
                    }
                    break;
                case 66:
                    if (zzT(obj, i7, i5)) {
                        int iZzp = zzp(obj, j2);
                        iC = a.c((iZzp >> 31) ^ (iZzp + iZzp), zzbi.zzx(i7 << 3), iC);
                    }
                    break;
                case 67:
                    if (zzT(obj, i7, i5)) {
                        long jZzz = zzz(obj, j2);
                        iC += zzbi.zzy((jZzz >> 63) ^ (jZzz + jZzz)) + zzbi.zzx(i7 << 3);
                    }
                    break;
                case 68:
                    if (zzT(obj, i7, i5)) {
                        iZzt = zzbi.zzt(i7, (zzdf) unsafe.getObject(obj, j2), zzB(i5));
                        iC += iZzt;
                    }
                    break;
            }
            i5 += 3;
            i3 = 1048575;
        }
        zzeg zzegVar = this.zzm;
        int iZza = iC + zzegVar.zza(zzegVar.zzd(obj));
        if (!this.zzh) {
            return iZza;
        }
        this.zzn.zza(obj);
        throw null;
    }

    private static int zzp(Object obj, long j2) {
        return ((Integer) zzeq.zzf(obj, j2)).intValue();
    }

    private final int zzq(Object obj, byte[] bArr, int i2, int i3, int i4, long j2, zzan zzanVar) {
        Unsafe unsafe = zzb;
        Object objZzC = zzC(i4);
        Object object = unsafe.getObject(obj, j2);
        if (!((zzcz) object).zze()) {
            zzcz zzczVarZzb = zzcz.zza().zzb();
            zzda.zzb(zzczVarZzb, object);
            unsafe.putObject(obj, j2, zzczVarZzb);
        }
        throw null;
    }

    private final int zzr(Object obj, byte[] bArr, int i2, int i3, int i4, int i5, int i6, int i7, int i8, long j2, int i9, zzan zzanVar) throws zzci {
        Unsafe unsafe = zzb;
        long j3 = this.zzc[i9 + 2] & 1048575;
        switch (i8) {
            case 51:
                if (i6 != 1) {
                    return i2;
                }
                unsafe.putObject(obj, j2, Double.valueOf(Double.longBitsToDouble(zzao.zzp(bArr, i2))));
                int i10 = i2 + 8;
                unsafe.putInt(obj, j3, i5);
                return i10;
            case 52:
                if (i6 != 5) {
                    return i2;
                }
                unsafe.putObject(obj, j2, Float.valueOf(Float.intBitsToFloat(zzao.zzb(bArr, i2))));
                int i11 = i2 + 4;
                unsafe.putInt(obj, j3, i5);
                return i11;
            case 53:
            case 54:
                if (i6 != 0) {
                    return i2;
                }
                int iZzm = zzao.zzm(bArr, i2, zzanVar);
                unsafe.putObject(obj, j2, Long.valueOf(zzanVar.zzb));
                unsafe.putInt(obj, j3, i5);
                return iZzm;
            case 55:
            case 62:
                if (i6 != 0) {
                    return i2;
                }
                int iZzj = zzao.zzj(bArr, i2, zzanVar);
                unsafe.putObject(obj, j2, Integer.valueOf(zzanVar.zza));
                unsafe.putInt(obj, j3, i5);
                return iZzj;
            case 56:
            case 65:
                if (i6 != 1) {
                    return i2;
                }
                unsafe.putObject(obj, j2, Long.valueOf(zzao.zzp(bArr, i2)));
                int i12 = i2 + 8;
                unsafe.putInt(obj, j3, i5);
                return i12;
            case 57:
            case VertexAttributes.Usage.BoneWeight /* 64 */:
                if (i6 != 5) {
                    return i2;
                }
                unsafe.putObject(obj, j2, Integer.valueOf(zzao.zzb(bArr, i2)));
                int i13 = i2 + 4;
                unsafe.putInt(obj, j3, i5);
                return i13;
            case 58:
                if (i6 != 0) {
                    return i2;
                }
                int iZzm2 = zzao.zzm(bArr, i2, zzanVar);
                unsafe.putObject(obj, j2, Boolean.valueOf(zzanVar.zzb != 0));
                unsafe.putInt(obj, j3, i5);
                return iZzm2;
            case 59:
                if (i6 != 2) {
                    return i2;
                }
                int iZzj2 = zzao.zzj(bArr, i2, zzanVar);
                int i14 = zzanVar.zza;
                if (i14 == 0) {
                    unsafe.putObject(obj, j2, "");
                } else {
                    if ((i7 & DriveFile.MODE_WRITE_ONLY) != 0 && !zzev.zze(bArr, iZzj2, iZzj2 + i14)) {
                        throw zzci.zzc();
                    }
                    unsafe.putObject(obj, j2, new String(bArr, iZzj2, i14, zzcg.zzb));
                    iZzj2 += i14;
                }
                unsafe.putInt(obj, j3, i5);
                return iZzj2;
            case 60:
                if (i6 != 2) {
                    return i2;
                }
                Object objZzE = zzE(obj, i5, i9);
                int iZzo = zzao.zzo(objZzE, zzB(i9), bArr, i2, i3, zzanVar);
                zzM(obj, i5, i9, objZzE);
                return iZzo;
            case 61:
                if (i6 != 2) {
                    return i2;
                }
                int iZza = zzao.zza(bArr, i2, zzanVar);
                unsafe.putObject(obj, j2, zzanVar.zzc);
                unsafe.putInt(obj, j3, i5);
                return iZza;
            case Notifications.NOTIFICATION_TYPES_ALL /* 63 */:
                if (i6 != 0) {
                    return i2;
                }
                int iZzj3 = zzao.zzj(bArr, i2, zzanVar);
                int i15 = zzanVar.zza;
                zzce zzceVarZzA = zzA(i9);
                if (zzceVarZzA == null || zzceVarZzA.zza(i15)) {
                    unsafe.putObject(obj, j2, Integer.valueOf(i15));
                    unsafe.putInt(obj, j3, i5);
                } else {
                    zzd(obj).zzj(i4, Long.valueOf(i15));
                }
                return iZzj3;
            case 66:
                if (i6 != 0) {
                    return i2;
                }
                int iZzj4 = zzao.zzj(bArr, i2, zzanVar);
                unsafe.putObject(obj, j2, Integer.valueOf(zzbe.zzb(zzanVar.zza)));
                unsafe.putInt(obj, j3, i5);
                return iZzj4;
            case 67:
                if (i6 != 0) {
                    return i2;
                }
                int iZzm3 = zzao.zzm(bArr, i2, zzanVar);
                unsafe.putObject(obj, j2, Long.valueOf(zzbe.zzc(zzanVar.zzb)));
                unsafe.putInt(obj, j3, i5);
                return iZzm3;
            case 68:
                if (i6 != 3) {
                    return i2;
                }
                Object objZzE2 = zzE(obj, i5, i9);
                int iZzn = zzao.zzn(objZzE2, zzB(i9), bArr, i2, i3, (i4 & (-8)) | 4, zzanVar);
                zzM(obj, i5, i9, objZzE2);
                return iZzn;
            default:
                return i2;
        }
    }

    private final int zzs(Object obj, byte[] bArr, int i2, int i3, int i4, int i5, int i6, int i7, long j2, int i8, long j3, zzan zzanVar) throws zzci {
        int i9;
        int i10;
        int i11;
        int i12;
        int iZzl;
        int iZzj = i2;
        Unsafe unsafe = zzb;
        zzcf zzcfVarZzd = (zzcf) unsafe.getObject(obj, j3);
        if (!zzcfVarZzd.zzc()) {
            int size = zzcfVarZzd.size();
            zzcfVarZzd = zzcfVarZzd.zzd(size == 0 ? 10 : size + size);
            unsafe.putObject(obj, j3, zzcfVarZzd);
        }
        switch (i8) {
            case 18:
            case 35:
                if (i6 == 2) {
                    zzbk zzbkVar = (zzbk) zzcfVarZzd;
                    int iZzj2 = zzao.zzj(bArr, iZzj, zzanVar);
                    int i13 = zzanVar.zza + iZzj2;
                    while (iZzj2 < i13) {
                        zzbkVar.zze(Double.longBitsToDouble(zzao.zzp(bArr, iZzj2)));
                        iZzj2 += 8;
                    }
                    if (iZzj2 == i13) {
                        return iZzj2;
                    }
                    throw zzci.zzg();
                }
                if (i6 == 1) {
                    zzbk zzbkVar2 = (zzbk) zzcfVarZzd;
                    zzbkVar2.zze(Double.longBitsToDouble(zzao.zzp(bArr, i2)));
                    while (true) {
                        i9 = iZzj + 8;
                        if (i9 < i3) {
                            iZzj = zzao.zzj(bArr, i9, zzanVar);
                            if (i4 == zzanVar.zza) {
                                zzbkVar2.zze(Double.longBitsToDouble(zzao.zzp(bArr, iZzj)));
                            }
                        }
                    }
                    return i9;
                }
                break;
            case 19:
            case 36:
                if (i6 == 2) {
                    zzbu zzbuVar = (zzbu) zzcfVarZzd;
                    int iZzj3 = zzao.zzj(bArr, iZzj, zzanVar);
                    int i14 = zzanVar.zza + iZzj3;
                    while (iZzj3 < i14) {
                        zzbuVar.zze(Float.intBitsToFloat(zzao.zzb(bArr, iZzj3)));
                        iZzj3 += 4;
                    }
                    if (iZzj3 == i14) {
                        return iZzj3;
                    }
                    throw zzci.zzg();
                }
                if (i6 == 5) {
                    zzbu zzbuVar2 = (zzbu) zzcfVarZzd;
                    zzbuVar2.zze(Float.intBitsToFloat(zzao.zzb(bArr, i2)));
                    while (true) {
                        i10 = iZzj + 4;
                        if (i10 < i3) {
                            iZzj = zzao.zzj(bArr, i10, zzanVar);
                            if (i4 == zzanVar.zza) {
                                zzbuVar2.zze(Float.intBitsToFloat(zzao.zzb(bArr, iZzj)));
                            }
                        }
                    }
                    return i10;
                }
                break;
            case 20:
            case Decal.C4 /* 21 */:
            case 37:
            case 38:
                if (i6 == 2) {
                    zzcu zzcuVar = (zzcu) zzcfVarZzd;
                    int iZzj4 = zzao.zzj(bArr, iZzj, zzanVar);
                    int i15 = zzanVar.zza + iZzj4;
                    while (iZzj4 < i15) {
                        iZzj4 = zzao.zzm(bArr, iZzj4, zzanVar);
                        zzcuVar.zzf(zzanVar.zzb);
                    }
                    if (iZzj4 == i15) {
                        return iZzj4;
                    }
                    throw zzci.zzg();
                }
                if (i6 == 0) {
                    zzcu zzcuVar2 = (zzcu) zzcfVarZzd;
                    int iZzm = zzao.zzm(bArr, iZzj, zzanVar);
                    zzcuVar2.zzf(zzanVar.zzb);
                    while (iZzm < i3) {
                        int iZzj5 = zzao.zzj(bArr, iZzm, zzanVar);
                        if (i4 != zzanVar.zza) {
                            return iZzm;
                        }
                        iZzm = zzao.zzm(bArr, iZzj5, zzanVar);
                        zzcuVar2.zzf(zzanVar.zzb);
                    }
                    return iZzm;
                }
                break;
            case Decal.U4 /* 22 */:
            case 29:
            case 39:
            case 43:
                if (i6 == 2) {
                    return zzao.zzf(bArr, iZzj, zzcfVarZzd, zzanVar);
                }
                if (i6 == 0) {
                    return zzao.zzl(i4, bArr, i2, i3, zzcfVarZzd, zzanVar);
                }
                break;
            case Decal.V4 /* 23 */:
            case 32:
            case 40:
            case 46:
                if (i6 == 2) {
                    zzcu zzcuVar3 = (zzcu) zzcfVarZzd;
                    int iZzj6 = zzao.zzj(bArr, iZzj, zzanVar);
                    int i16 = zzanVar.zza + iZzj6;
                    while (iZzj6 < i16) {
                        zzcuVar3.zzf(zzao.zzp(bArr, iZzj6));
                        iZzj6 += 8;
                    }
                    if (iZzj6 == i16) {
                        return iZzj6;
                    }
                    throw zzci.zzg();
                }
                if (i6 == 1) {
                    zzcu zzcuVar4 = (zzcu) zzcfVarZzd;
                    zzcuVar4.zzf(zzao.zzp(bArr, i2));
                    while (true) {
                        i11 = iZzj + 8;
                        if (i11 < i3) {
                            iZzj = zzao.zzj(bArr, i11, zzanVar);
                            if (i4 == zzanVar.zza) {
                                zzcuVar4.zzf(zzao.zzp(bArr, iZzj));
                            }
                        }
                    }
                    return i11;
                }
                break;
            case Decal.SIZE /* 24 */:
            case 31:
            case 41:
            case 45:
                if (i6 == 2) {
                    zzcc zzccVar = (zzcc) zzcfVarZzd;
                    int iZzj7 = zzao.zzj(bArr, iZzj, zzanVar);
                    int i17 = zzanVar.zza + iZzj7;
                    while (iZzj7 < i17) {
                        zzccVar.zzf(zzao.zzb(bArr, iZzj7));
                        iZzj7 += 4;
                    }
                    if (iZzj7 == i17) {
                        return iZzj7;
                    }
                    throw zzci.zzg();
                }
                if (i6 == 5) {
                    zzcc zzccVar2 = (zzcc) zzcfVarZzd;
                    zzccVar2.zzf(zzao.zzb(bArr, i2));
                    while (true) {
                        i12 = iZzj + 4;
                        if (i12 < i3) {
                            iZzj = zzao.zzj(bArr, i12, zzanVar);
                            if (i4 == zzanVar.zza) {
                                zzccVar2.zzf(zzao.zzb(bArr, iZzj));
                            }
                        }
                    }
                    return i12;
                }
                break;
            case 25:
            case 42:
                if (i6 == 2) {
                    zzap zzapVar = (zzap) zzcfVarZzd;
                    int iZzj8 = zzao.zzj(bArr, iZzj, zzanVar);
                    int i18 = zzanVar.zza + iZzj8;
                    while (iZzj8 < i18) {
                        iZzj8 = zzao.zzm(bArr, iZzj8, zzanVar);
                        zzapVar.zze(zzanVar.zzb != 0);
                    }
                    if (iZzj8 == i18) {
                        return iZzj8;
                    }
                    throw zzci.zzg();
                }
                if (i6 == 0) {
                    zzap zzapVar2 = (zzap) zzcfVarZzd;
                    int iZzm2 = zzao.zzm(bArr, iZzj, zzanVar);
                    zzapVar2.zze(zzanVar.zzb != 0);
                    while (iZzm2 < i3) {
                        int iZzj9 = zzao.zzj(bArr, iZzm2, zzanVar);
                        if (i4 != zzanVar.zza) {
                            return iZzm2;
                        }
                        iZzm2 = zzao.zzm(bArr, iZzj9, zzanVar);
                        zzapVar2.zze(zzanVar.zzb != 0);
                    }
                    return iZzm2;
                }
                break;
            case 26:
                if (i6 == 2) {
                    if ((j2 & 536870912) == 0) {
                        int iZzj10 = zzao.zzj(bArr, iZzj, zzanVar);
                        int i19 = zzanVar.zza;
                        if (i19 < 0) {
                            throw zzci.zzd();
                        }
                        if (i19 == 0) {
                            zzcfVarZzd.add("");
                        } else {
                            zzcfVarZzd.add(new String(bArr, iZzj10, i19, zzcg.zzb));
                            iZzj10 += i19;
                        }
                        while (iZzj10 < i3) {
                            int iZzj11 = zzao.zzj(bArr, iZzj10, zzanVar);
                            if (i4 != zzanVar.zza) {
                                return iZzj10;
                            }
                            iZzj10 = zzao.zzj(bArr, iZzj11, zzanVar);
                            int i20 = zzanVar.zza;
                            if (i20 < 0) {
                                throw zzci.zzd();
                            }
                            if (i20 == 0) {
                                zzcfVarZzd.add("");
                            } else {
                                zzcfVarZzd.add(new String(bArr, iZzj10, i20, zzcg.zzb));
                                iZzj10 += i20;
                            }
                        }
                        return iZzj10;
                    }
                    int iZzj12 = zzao.zzj(bArr, iZzj, zzanVar);
                    int i21 = zzanVar.zza;
                    if (i21 < 0) {
                        throw zzci.zzd();
                    }
                    if (i21 == 0) {
                        zzcfVarZzd.add("");
                    } else {
                        int i22 = iZzj12 + i21;
                        if (!zzev.zze(bArr, iZzj12, i22)) {
                            throw zzci.zzc();
                        }
                        zzcfVarZzd.add(new String(bArr, iZzj12, i21, zzcg.zzb));
                        iZzj12 = i22;
                    }
                    while (iZzj12 < i3) {
                        int iZzj13 = zzao.zzj(bArr, iZzj12, zzanVar);
                        if (i4 != zzanVar.zza) {
                            return iZzj12;
                        }
                        iZzj12 = zzao.zzj(bArr, iZzj13, zzanVar);
                        int i23 = zzanVar.zza;
                        if (i23 < 0) {
                            throw zzci.zzd();
                        }
                        if (i23 == 0) {
                            zzcfVarZzd.add("");
                        } else {
                            int i24 = iZzj12 + i23;
                            if (!zzev.zze(bArr, iZzj12, i24)) {
                                throw zzci.zzc();
                            }
                            zzcfVarZzd.add(new String(bArr, iZzj12, i23, zzcg.zzb));
                            iZzj12 = i24;
                        }
                    }
                    return iZzj12;
                }
                break;
            case 27:
                if (i6 == 2) {
                    return zzao.zze(zzB(i7), i4, bArr, i2, i3, zzcfVarZzd, zzanVar);
                }
                break;
            case 28:
                if (i6 == 2) {
                    int iZzj14 = zzao.zzj(bArr, iZzj, zzanVar);
                    int i25 = zzanVar.zza;
                    if (i25 < 0) {
                        throw zzci.zzd();
                    }
                    if (i25 > bArr.length - iZzj14) {
                        throw zzci.zzg();
                    }
                    if (i25 == 0) {
                        zzcfVarZzd.add(zzba.zzb);
                    } else {
                        zzcfVarZzd.add(zzba.zzl(bArr, iZzj14, i25));
                        iZzj14 += i25;
                    }
                    while (iZzj14 < i3) {
                        int iZzj15 = zzao.zzj(bArr, iZzj14, zzanVar);
                        if (i4 != zzanVar.zza) {
                            return iZzj14;
                        }
                        iZzj14 = zzao.zzj(bArr, iZzj15, zzanVar);
                        int i26 = zzanVar.zza;
                        if (i26 < 0) {
                            throw zzci.zzd();
                        }
                        if (i26 > bArr.length - iZzj14) {
                            throw zzci.zzg();
                        }
                        if (i26 == 0) {
                            zzcfVarZzd.add(zzba.zzb);
                        } else {
                            zzcfVarZzd.add(zzba.zzl(bArr, iZzj14, i26));
                            iZzj14 += i26;
                        }
                    }
                    return iZzj14;
                }
                break;
            case 30:
            case 44:
                if (i6 == 2) {
                    iZzl = zzao.zzf(bArr, iZzj, zzcfVarZzd, zzanVar);
                } else if (i6 == 0) {
                    iZzl = zzao.zzl(i4, bArr, i2, i3, zzcfVarZzd, zzanVar);
                }
                zzce zzceVarZzA = zzA(i7);
                zzeg zzegVar = this.zzm;
                int i27 = zzdr.zza;
                if (zzceVarZzA != null) {
                    Object objZzA = null;
                    if (zzcfVarZzd != null) {
                        int size2 = zzcfVarZzd.size();
                        int i28 = 0;
                        for (int i29 = 0; i29 < size2; i29++) {
                            Integer num = (Integer) zzcfVarZzd.get(i29);
                            int iIntValue = num.intValue();
                            if (zzceVarZzA.zza(iIntValue)) {
                                if (i29 != i28) {
                                    zzcfVarZzd.set(i28, num);
                                }
                                i28++;
                            } else {
                                objZzA = zzdr.zzA(obj, i5, iIntValue, objZzA, zzegVar);
                            }
                        }
                        if (i28 != size2) {
                            zzcfVarZzd.subList(i28, size2).clear();
                            return iZzl;
                        }
                    } else {
                        Iterator it = zzcfVarZzd.iterator();
                        while (it.hasNext()) {
                            int iIntValue2 = ((Integer) it.next()).intValue();
                            if (!zzceVarZzA.zza(iIntValue2)) {
                                objZzA = zzdr.zzA(obj, i5, iIntValue2, objZzA, zzegVar);
                                it.remove();
                            }
                        }
                    }
                }
                return iZzl;
            case 33:
            case 47:
                if (i6 == 2) {
                    zzcc zzccVar3 = (zzcc) zzcfVarZzd;
                    int iZzj16 = zzao.zzj(bArr, iZzj, zzanVar);
                    int i30 = zzanVar.zza + iZzj16;
                    while (iZzj16 < i30) {
                        iZzj16 = zzao.zzj(bArr, iZzj16, zzanVar);
                        zzccVar3.zzf(zzbe.zzb(zzanVar.zza));
                    }
                    if (iZzj16 == i30) {
                        return iZzj16;
                    }
                    throw zzci.zzg();
                }
                if (i6 == 0) {
                    zzcc zzccVar4 = (zzcc) zzcfVarZzd;
                    int iZzj17 = zzao.zzj(bArr, iZzj, zzanVar);
                    zzccVar4.zzf(zzbe.zzb(zzanVar.zza));
                    while (iZzj17 < i3) {
                        int iZzj18 = zzao.zzj(bArr, iZzj17, zzanVar);
                        if (i4 != zzanVar.zza) {
                            return iZzj17;
                        }
                        iZzj17 = zzao.zzj(bArr, iZzj18, zzanVar);
                        zzccVar4.zzf(zzbe.zzb(zzanVar.zza));
                    }
                    return iZzj17;
                }
                break;
            case 34:
            case 48:
                if (i6 == 2) {
                    zzcu zzcuVar5 = (zzcu) zzcfVarZzd;
                    int iZzj19 = zzao.zzj(bArr, iZzj, zzanVar);
                    int i31 = zzanVar.zza + iZzj19;
                    while (iZzj19 < i31) {
                        iZzj19 = zzao.zzm(bArr, iZzj19, zzanVar);
                        zzcuVar5.zzf(zzbe.zzc(zzanVar.zzb));
                    }
                    if (iZzj19 == i31) {
                        return iZzj19;
                    }
                    throw zzci.zzg();
                }
                if (i6 == 0) {
                    zzcu zzcuVar6 = (zzcu) zzcfVarZzd;
                    int iZzm3 = zzao.zzm(bArr, iZzj, zzanVar);
                    zzcuVar6.zzf(zzbe.zzc(zzanVar.zzb));
                    while (iZzm3 < i3) {
                        int iZzj20 = zzao.zzj(bArr, iZzm3, zzanVar);
                        if (i4 != zzanVar.zza) {
                            return iZzm3;
                        }
                        iZzm3 = zzao.zzm(bArr, iZzj20, zzanVar);
                        zzcuVar6.zzf(zzbe.zzc(zzanVar.zzb));
                    }
                    return iZzm3;
                }
                break;
            default:
                if (i6 == 3) {
                    zzdp zzdpVarZzB = zzB(i7);
                    int i32 = (i4 & (-8)) | 4;
                    int iZzc = zzao.zzc(zzdpVarZzB, bArr, i2, i3, i32, zzanVar);
                    zzcfVarZzd.add(zzanVar.zzc);
                    while (iZzc < i3) {
                        int iZzj21 = zzao.zzj(bArr, iZzc, zzanVar);
                        if (i4 != zzanVar.zza) {
                            return iZzc;
                        }
                        iZzc = zzao.zzc(zzdpVarZzB, bArr, iZzj21, i3, i32, zzanVar);
                        zzcfVarZzd.add(zzanVar.zzc);
                    }
                    return iZzc;
                }
                break;
        }
        return iZzj;
    }

    private final int zzt(int i2) {
        if (i2 < this.zze || i2 > this.zzf) {
            return -1;
        }
        return zzw(i2, 0);
    }

    private final int zzu(int i2, int i3) {
        if (i2 < this.zze || i2 > this.zzf) {
            return -1;
        }
        return zzw(i2, i3);
    }

    private final int zzv(int i2) {
        return this.zzc[i2 + 2];
    }

    private final int zzw(int i2, int i3) {
        int length = (this.zzc.length / 3) - 1;
        while (i3 <= length) {
            int i4 = (length + i3) >>> 1;
            int i5 = i4 * 3;
            int i6 = this.zzc[i5];
            if (i2 == i6) {
                return i5;
            }
            if (i2 < i6) {
                length = i4 - 1;
            } else {
                i3 = i4 + 1;
            }
        }
        return -1;
    }

    private static int zzx(int i2) {
        return (i2 >>> 20) & 255;
    }

    private final int zzy(int i2) {
        return this.zzc[i2 + 1];
    }

    private static long zzz(Object obj, long j2) {
        return ((Long) zzeq.zzf(obj, j2)).longValue();
    }

    @Override // com.google.android.gms.internal.play_billing.zzdp
    public final int zza(Object obj) {
        int iZzn;
        int iZzx;
        int iZzy;
        zzew zzewVar = zzew.zza;
        if (this.zzo - 1 == 0) {
            return zzo(obj);
        }
        Unsafe unsafe = zzb;
        int iC = 0;
        for (int i2 = 0; i2 < this.zzc.length; i2 += 3) {
            int iZzy2 = zzy(i2);
            int iZzx2 = zzx(iZzy2);
            int i3 = this.zzc[i2];
            int i4 = iZzy2 & 1048575;
            if (iZzx2 >= zzbt.zzJ.zza() && iZzx2 <= zzbt.zzW.zza()) {
                int i5 = this.zzc[i2 + 2];
            }
            long j2 = i4;
            switch (iZzx2) {
                case 0:
                    if (zzP(obj, i2)) {
                        iC = a.c(i3 << 3, 8, iC);
                    }
                    break;
                case 1:
                    if (zzP(obj, i2)) {
                        iC = a.c(i3 << 3, 4, iC);
                    }
                    break;
                case 2:
                    if (zzP(obj, i2)) {
                        iC = a.c(i3 << 3, zzbi.zzy(zzeq.zzd(obj, j2)), iC);
                    }
                    break;
                case 3:
                    if (zzP(obj, i2)) {
                        iC = a.c(i3 << 3, zzbi.zzy(zzeq.zzd(obj, j2)), iC);
                    }
                    break;
                case 4:
                    if (zzP(obj, i2)) {
                        iC = a.c(i3 << 3, zzbi.zzu(zzeq.zzc(obj, j2)), iC);
                    }
                    break;
                case 5:
                    if (zzP(obj, i2)) {
                        iC = a.c(i3 << 3, 8, iC);
                    }
                    break;
                case 6:
                    if (zzP(obj, i2)) {
                        iC = a.c(i3 << 3, 4, iC);
                    }
                    break;
                case 7:
                    if (zzP(obj, i2)) {
                        iC = a.c(i3 << 3, 1, iC);
                    }
                    break;
                case 8:
                    if (zzP(obj, i2)) {
                        Object objZzf = zzeq.zzf(obj, j2);
                        if (objZzf instanceof zzba) {
                            int i6 = i3 << 3;
                            int i7 = zzbi.zzb;
                            int iZzd = ((zzba) objZzf).zzd();
                            iC = a.c(i6, zzbi.zzx(iZzd) + iZzd, iC);
                        } else {
                            iC = a.c(i3 << 3, zzbi.zzw((String) objZzf), iC);
                        }
                    }
                    break;
                case 9:
                    if (zzP(obj, i2)) {
                        iZzn = zzdr.zzn(i3, zzeq.zzf(obj, j2), zzB(i2));
                        iC += iZzn;
                    }
                    break;
                case 10:
                    if (zzP(obj, i2)) {
                        zzba zzbaVar = (zzba) zzeq.zzf(obj, j2);
                        int i8 = i3 << 3;
                        int i9 = zzbi.zzb;
                        int iZzd2 = zzbaVar.zzd();
                        iC = a.c(i8, zzbi.zzx(iZzd2) + iZzd2, iC);
                    }
                    break;
                case 11:
                    if (zzP(obj, i2)) {
                        iC = a.c(i3 << 3, zzbi.zzx(zzeq.zzc(obj, j2)), iC);
                    }
                    break;
                case 12:
                    if (zzP(obj, i2)) {
                        iC = a.c(i3 << 3, zzbi.zzu(zzeq.zzc(obj, j2)), iC);
                    }
                    break;
                case 13:
                    if (zzP(obj, i2)) {
                        iC = a.c(i3 << 3, 4, iC);
                    }
                    break;
                case 14:
                    if (zzP(obj, i2)) {
                        iC = a.c(i3 << 3, 8, iC);
                    }
                    break;
                case 15:
                    if (zzP(obj, i2)) {
                        int iZzc = zzeq.zzc(obj, j2);
                        iC = a.c((iZzc >> 31) ^ (iZzc + iZzc), zzbi.zzx(i3 << 3), iC);
                    }
                    break;
                case 16:
                    if (zzP(obj, i2)) {
                        long jZzd = zzeq.zzd(obj, j2);
                        iZzx = zzbi.zzx(i3 << 3);
                        iZzy = zzbi.zzy((jZzd >> 63) ^ (jZzd + jZzd));
                        iC += iZzy + iZzx;
                    }
                    break;
                case 17:
                    if (zzP(obj, i2)) {
                        iZzn = zzbi.zzt(i3, (zzdf) zzeq.zzf(obj, j2), zzB(i2));
                        iC += iZzn;
                    }
                    break;
                case 18:
                    iZzn = zzdr.zzg(i3, (List) zzeq.zzf(obj, j2), false);
                    iC += iZzn;
                    break;
                case 19:
                    iZzn = zzdr.zze(i3, (List) zzeq.zzf(obj, j2), false);
                    iC += iZzn;
                    break;
                case 20:
                    iZzn = zzdr.zzl(i3, (List) zzeq.zzf(obj, j2), false);
                    iC += iZzn;
                    break;
                case Decal.C4 /* 21 */:
                    iZzn = zzdr.zzw(i3, (List) zzeq.zzf(obj, j2), false);
                    iC += iZzn;
                    break;
                case Decal.U4 /* 22 */:
                    iZzn = zzdr.zzj(i3, (List) zzeq.zzf(obj, j2), false);
                    iC += iZzn;
                    break;
                case Decal.V4 /* 23 */:
                    iZzn = zzdr.zzg(i3, (List) zzeq.zzf(obj, j2), false);
                    iC += iZzn;
                    break;
                case Decal.SIZE /* 24 */:
                    iZzn = zzdr.zze(i3, (List) zzeq.zzf(obj, j2), false);
                    iC += iZzn;
                    break;
                case 25:
                    iZzn = zzdr.zza(i3, (List) zzeq.zzf(obj, j2), false);
                    iC += iZzn;
                    break;
                case 26:
                    iZzn = zzdr.zzt(i3, (List) zzeq.zzf(obj, j2));
                    iC += iZzn;
                    break;
                case 27:
                    iZzn = zzdr.zzo(i3, (List) zzeq.zzf(obj, j2), zzB(i2));
                    iC += iZzn;
                    break;
                case 28:
                    iZzn = zzdr.zzb(i3, (List) zzeq.zzf(obj, j2));
                    iC += iZzn;
                    break;
                case 29:
                    iZzn = zzdr.zzu(i3, (List) zzeq.zzf(obj, j2), false);
                    iC += iZzn;
                    break;
                case 30:
                    iZzn = zzdr.zzc(i3, (List) zzeq.zzf(obj, j2), false);
                    iC += iZzn;
                    break;
                case 31:
                    iZzn = zzdr.zze(i3, (List) zzeq.zzf(obj, j2), false);
                    iC += iZzn;
                    break;
                case 32:
                    iZzn = zzdr.zzg(i3, (List) zzeq.zzf(obj, j2), false);
                    iC += iZzn;
                    break;
                case 33:
                    iZzn = zzdr.zzp(i3, (List) zzeq.zzf(obj, j2), false);
                    iC += iZzn;
                    break;
                case 34:
                    iZzn = zzdr.zzr(i3, (List) zzeq.zzf(obj, j2), false);
                    iC += iZzn;
                    break;
                case 35:
                    int iZzh = zzdr.zzh((List) unsafe.getObject(obj, j2));
                    if (iZzh > 0) {
                        iC = a.d(i3 << 3, zzbi.zzx(iZzh), iZzh, iC);
                    }
                    break;
                case 36:
                    int iZzf = zzdr.zzf((List) unsafe.getObject(obj, j2));
                    if (iZzf > 0) {
                        iC = a.d(i3 << 3, zzbi.zzx(iZzf), iZzf, iC);
                    }
                    break;
                case 37:
                    int iZzm = zzdr.zzm((List) unsafe.getObject(obj, j2));
                    if (iZzm > 0) {
                        iC = a.d(i3 << 3, zzbi.zzx(iZzm), iZzm, iC);
                    }
                    break;
                case 38:
                    int iZzx3 = zzdr.zzx((List) unsafe.getObject(obj, j2));
                    if (iZzx3 > 0) {
                        iC = a.d(i3 << 3, zzbi.zzx(iZzx3), iZzx3, iC);
                    }
                    break;
                case 39:
                    int iZzk = zzdr.zzk((List) unsafe.getObject(obj, j2));
                    if (iZzk > 0) {
                        iC = a.d(i3 << 3, zzbi.zzx(iZzk), iZzk, iC);
                    }
                    break;
                case 40:
                    int iZzh2 = zzdr.zzh((List) unsafe.getObject(obj, j2));
                    if (iZzh2 > 0) {
                        iC = a.d(i3 << 3, zzbi.zzx(iZzh2), iZzh2, iC);
                    }
                    break;
                case 41:
                    int iZzf2 = zzdr.zzf((List) unsafe.getObject(obj, j2));
                    if (iZzf2 > 0) {
                        iC = a.d(i3 << 3, zzbi.zzx(iZzf2), iZzf2, iC);
                    }
                    break;
                case 42:
                    List list = (List) unsafe.getObject(obj, j2);
                    int i10 = zzdr.zza;
                    int size = list.size();
                    if (size > 0) {
                        iC = a.d(i3 << 3, zzbi.zzx(size), size, iC);
                    }
                    break;
                case 43:
                    int iZzv = zzdr.zzv((List) unsafe.getObject(obj, j2));
                    if (iZzv > 0) {
                        iC = a.d(i3 << 3, zzbi.zzx(iZzv), iZzv, iC);
                    }
                    break;
                case 44:
                    int iZzd3 = zzdr.zzd((List) unsafe.getObject(obj, j2));
                    if (iZzd3 > 0) {
                        iC = a.d(i3 << 3, zzbi.zzx(iZzd3), iZzd3, iC);
                    }
                    break;
                case 45:
                    int iZzf3 = zzdr.zzf((List) unsafe.getObject(obj, j2));
                    if (iZzf3 > 0) {
                        iC = a.d(i3 << 3, zzbi.zzx(iZzf3), iZzf3, iC);
                    }
                    break;
                case 46:
                    int iZzh3 = zzdr.zzh((List) unsafe.getObject(obj, j2));
                    if (iZzh3 > 0) {
                        iC = a.d(i3 << 3, zzbi.zzx(iZzh3), iZzh3, iC);
                    }
                    break;
                case 47:
                    int iZzq = zzdr.zzq((List) unsafe.getObject(obj, j2));
                    if (iZzq > 0) {
                        iC = a.d(i3 << 3, zzbi.zzx(iZzq), iZzq, iC);
                    }
                    break;
                case 48:
                    int iZzs = zzdr.zzs((List) unsafe.getObject(obj, j2));
                    if (iZzs > 0) {
                        iC = a.d(i3 << 3, zzbi.zzx(iZzs), iZzs, iC);
                    }
                    break;
                case 49:
                    iZzn = zzdr.zzi(i3, (List) zzeq.zzf(obj, j2), zzB(i2));
                    iC += iZzn;
                    break;
                case 50:
                    zzda.zza(i3, zzeq.zzf(obj, j2), zzC(i2));
                    break;
                case 51:
                    if (zzT(obj, i3, i2)) {
                        iC = a.c(i3 << 3, 8, iC);
                    }
                    break;
                case 52:
                    if (zzT(obj, i3, i2)) {
                        iC = a.c(i3 << 3, 4, iC);
                    }
                    break;
                case 53:
                    if (zzT(obj, i3, i2)) {
                        iC = a.c(i3 << 3, zzbi.zzy(zzz(obj, j2)), iC);
                    }
                    break;
                case 54:
                    if (zzT(obj, i3, i2)) {
                        iC = a.c(i3 << 3, zzbi.zzy(zzz(obj, j2)), iC);
                    }
                    break;
                case 55:
                    if (zzT(obj, i3, i2)) {
                        iC = a.c(i3 << 3, zzbi.zzu(zzp(obj, j2)), iC);
                    }
                    break;
                case 56:
                    if (zzT(obj, i3, i2)) {
                        iC = a.c(i3 << 3, 8, iC);
                    }
                    break;
                case 57:
                    if (zzT(obj, i3, i2)) {
                        iC = a.c(i3 << 3, 4, iC);
                    }
                    break;
                case 58:
                    if (zzT(obj, i3, i2)) {
                        iC = a.c(i3 << 3, 1, iC);
                    }
                    break;
                case 59:
                    if (zzT(obj, i3, i2)) {
                        Object objZzf2 = zzeq.zzf(obj, j2);
                        if (objZzf2 instanceof zzba) {
                            int i11 = i3 << 3;
                            int i12 = zzbi.zzb;
                            int iZzd4 = ((zzba) objZzf2).zzd();
                            iC = a.c(i11, zzbi.zzx(iZzd4) + iZzd4, iC);
                        } else {
                            iC = a.c(i3 << 3, zzbi.zzw((String) objZzf2), iC);
                        }
                    }
                    break;
                case 60:
                    if (zzT(obj, i3, i2)) {
                        iZzn = zzdr.zzn(i3, zzeq.zzf(obj, j2), zzB(i2));
                        iC += iZzn;
                    }
                    break;
                case 61:
                    if (zzT(obj, i3, i2)) {
                        zzba zzbaVar2 = (zzba) zzeq.zzf(obj, j2);
                        int i13 = i3 << 3;
                        int i14 = zzbi.zzb;
                        int iZzd5 = zzbaVar2.zzd();
                        iC = a.c(i13, zzbi.zzx(iZzd5) + iZzd5, iC);
                    }
                    break;
                case 62:
                    if (zzT(obj, i3, i2)) {
                        iC = a.c(i3 << 3, zzbi.zzx(zzp(obj, j2)), iC);
                    }
                    break;
                case Notifications.NOTIFICATION_TYPES_ALL /* 63 */:
                    if (zzT(obj, i3, i2)) {
                        iC = a.c(i3 << 3, zzbi.zzu(zzp(obj, j2)), iC);
                    }
                    break;
                case VertexAttributes.Usage.BoneWeight /* 64 */:
                    if (zzT(obj, i3, i2)) {
                        iC = a.c(i3 << 3, 4, iC);
                    }
                    break;
                case 65:
                    if (zzT(obj, i3, i2)) {
                        iC = a.c(i3 << 3, 8, iC);
                    }
                    break;
                case 66:
                    if (zzT(obj, i3, i2)) {
                        int iZzp = zzp(obj, j2);
                        iC = a.c((iZzp >> 31) ^ (iZzp + iZzp), zzbi.zzx(i3 << 3), iC);
                    }
                    break;
                case 67:
                    if (zzT(obj, i3, i2)) {
                        long jZzz = zzz(obj, j2);
                        iZzx = zzbi.zzx(i3 << 3);
                        iZzy = zzbi.zzy((jZzz >> 63) ^ (jZzz + jZzz));
                        iC += iZzy + iZzx;
                    }
                    break;
                case 68:
                    if (zzT(obj, i3, i2)) {
                        iZzn = zzbi.zzt(i3, (zzdf) zzeq.zzf(obj, j2), zzB(i2));
                        iC += iZzn;
                    }
                    break;
            }
        }
        zzeg zzegVar = this.zzm;
        return iC + zzegVar.zza(zzegVar.zzd(obj));
    }

    @Override // com.google.android.gms.internal.play_billing.zzdp
    public final int zzb(Object obj) {
        int i2;
        long jDoubleToLongBits;
        int i3;
        int iFloatToIntBits;
        int iZzc;
        int length = this.zzc.length;
        int i4 = 0;
        for (int i5 = 0; i5 < length; i5 += 3) {
            int iZzy = zzy(i5);
            int i6 = this.zzc[i5];
            long j2 = 1048575 & iZzy;
            int iHashCode = 37;
            switch (zzx(iZzy)) {
                case 0:
                    i2 = i4 * 53;
                    jDoubleToLongBits = Double.doubleToLongBits(zzeq.zza(obj, j2));
                    byte[] bArr = zzcg.zzd;
                    iZzc = (int) (jDoubleToLongBits ^ (jDoubleToLongBits >>> 32));
                    i4 = i2 + iZzc;
                    break;
                case 1:
                    i3 = i4 * 53;
                    iFloatToIntBits = Float.floatToIntBits(zzeq.zzb(obj, j2));
                    i4 = iFloatToIntBits + i3;
                    break;
                case 2:
                    i2 = i4 * 53;
                    jDoubleToLongBits = zzeq.zzd(obj, j2);
                    byte[] bArr2 = zzcg.zzd;
                    iZzc = (int) (jDoubleToLongBits ^ (jDoubleToLongBits >>> 32));
                    i4 = i2 + iZzc;
                    break;
                case 3:
                    i2 = i4 * 53;
                    jDoubleToLongBits = zzeq.zzd(obj, j2);
                    byte[] bArr3 = zzcg.zzd;
                    iZzc = (int) (jDoubleToLongBits ^ (jDoubleToLongBits >>> 32));
                    i4 = i2 + iZzc;
                    break;
                case 4:
                    i2 = i4 * 53;
                    iZzc = zzeq.zzc(obj, j2);
                    i4 = i2 + iZzc;
                    break;
                case 5:
                    i2 = i4 * 53;
                    jDoubleToLongBits = zzeq.zzd(obj, j2);
                    byte[] bArr4 = zzcg.zzd;
                    iZzc = (int) (jDoubleToLongBits ^ (jDoubleToLongBits >>> 32));
                    i4 = i2 + iZzc;
                    break;
                case 6:
                    i2 = i4 * 53;
                    iZzc = zzeq.zzc(obj, j2);
                    i4 = i2 + iZzc;
                    break;
                case 7:
                    i3 = i4 * 53;
                    iFloatToIntBits = zzcg.zza(zzeq.zzw(obj, j2));
                    i4 = iFloatToIntBits + i3;
                    break;
                case 8:
                    i3 = i4 * 53;
                    iFloatToIntBits = ((String) zzeq.zzf(obj, j2)).hashCode();
                    i4 = iFloatToIntBits + i3;
                    break;
                case 9:
                    Object objZzf = zzeq.zzf(obj, j2);
                    if (objZzf != null) {
                        iHashCode = objZzf.hashCode();
                    }
                    i4 = (i4 * 53) + iHashCode;
                    break;
                case 10:
                    i3 = i4 * 53;
                    iFloatToIntBits = zzeq.zzf(obj, j2).hashCode();
                    i4 = iFloatToIntBits + i3;
                    break;
                case 11:
                    i2 = i4 * 53;
                    iZzc = zzeq.zzc(obj, j2);
                    i4 = i2 + iZzc;
                    break;
                case 12:
                    i2 = i4 * 53;
                    iZzc = zzeq.zzc(obj, j2);
                    i4 = i2 + iZzc;
                    break;
                case 13:
                    i2 = i4 * 53;
                    iZzc = zzeq.zzc(obj, j2);
                    i4 = i2 + iZzc;
                    break;
                case 14:
                    i2 = i4 * 53;
                    jDoubleToLongBits = zzeq.zzd(obj, j2);
                    byte[] bArr5 = zzcg.zzd;
                    iZzc = (int) (jDoubleToLongBits ^ (jDoubleToLongBits >>> 32));
                    i4 = i2 + iZzc;
                    break;
                case 15:
                    i2 = i4 * 53;
                    iZzc = zzeq.zzc(obj, j2);
                    i4 = i2 + iZzc;
                    break;
                case 16:
                    i2 = i4 * 53;
                    jDoubleToLongBits = zzeq.zzd(obj, j2);
                    byte[] bArr6 = zzcg.zzd;
                    iZzc = (int) (jDoubleToLongBits ^ (jDoubleToLongBits >>> 32));
                    i4 = i2 + iZzc;
                    break;
                case 17:
                    Object objZzf2 = zzeq.zzf(obj, j2);
                    if (objZzf2 != null) {
                        iHashCode = objZzf2.hashCode();
                    }
                    i4 = (i4 * 53) + iHashCode;
                    break;
                case 18:
                case 19:
                case 20:
                case Decal.C4 /* 21 */:
                case Decal.U4 /* 22 */:
                case Decal.V4 /* 23 */:
                case Decal.SIZE /* 24 */:
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case 48:
                case 49:
                    i3 = i4 * 53;
                    iFloatToIntBits = zzeq.zzf(obj, j2).hashCode();
                    i4 = iFloatToIntBits + i3;
                    break;
                case 50:
                    i3 = i4 * 53;
                    iFloatToIntBits = zzeq.zzf(obj, j2).hashCode();
                    i4 = iFloatToIntBits + i3;
                    break;
                case 51:
                    if (zzT(obj, i6, i5)) {
                        i2 = i4 * 53;
                        jDoubleToLongBits = Double.doubleToLongBits(zzm(obj, j2));
                        byte[] bArr7 = zzcg.zzd;
                        iZzc = (int) (jDoubleToLongBits ^ (jDoubleToLongBits >>> 32));
                        i4 = i2 + iZzc;
                    }
                    break;
                case 52:
                    if (zzT(obj, i6, i5)) {
                        i3 = i4 * 53;
                        iFloatToIntBits = Float.floatToIntBits(zzn(obj, j2));
                        i4 = iFloatToIntBits + i3;
                    }
                    break;
                case 53:
                    if (zzT(obj, i6, i5)) {
                        i2 = i4 * 53;
                        jDoubleToLongBits = zzz(obj, j2);
                        byte[] bArr8 = zzcg.zzd;
                        iZzc = (int) (jDoubleToLongBits ^ (jDoubleToLongBits >>> 32));
                        i4 = i2 + iZzc;
                    }
                    break;
                case 54:
                    if (zzT(obj, i6, i5)) {
                        i2 = i4 * 53;
                        jDoubleToLongBits = zzz(obj, j2);
                        byte[] bArr9 = zzcg.zzd;
                        iZzc = (int) (jDoubleToLongBits ^ (jDoubleToLongBits >>> 32));
                        i4 = i2 + iZzc;
                    }
                    break;
                case 55:
                    if (zzT(obj, i6, i5)) {
                        i2 = i4 * 53;
                        iZzc = zzp(obj, j2);
                        i4 = i2 + iZzc;
                    }
                    break;
                case 56:
                    if (zzT(obj, i6, i5)) {
                        i2 = i4 * 53;
                        jDoubleToLongBits = zzz(obj, j2);
                        byte[] bArr10 = zzcg.zzd;
                        iZzc = (int) (jDoubleToLongBits ^ (jDoubleToLongBits >>> 32));
                        i4 = i2 + iZzc;
                    }
                    break;
                case 57:
                    if (zzT(obj, i6, i5)) {
                        i2 = i4 * 53;
                        iZzc = zzp(obj, j2);
                        i4 = i2 + iZzc;
                    }
                    break;
                case 58:
                    if (zzT(obj, i6, i5)) {
                        i3 = i4 * 53;
                        iFloatToIntBits = zzcg.zza(zzU(obj, j2));
                        i4 = iFloatToIntBits + i3;
                    }
                    break;
                case 59:
                    if (zzT(obj, i6, i5)) {
                        i3 = i4 * 53;
                        iFloatToIntBits = ((String) zzeq.zzf(obj, j2)).hashCode();
                        i4 = iFloatToIntBits + i3;
                    }
                    break;
                case 60:
                    if (zzT(obj, i6, i5)) {
                        i3 = i4 * 53;
                        iFloatToIntBits = zzeq.zzf(obj, j2).hashCode();
                        i4 = iFloatToIntBits + i3;
                    }
                    break;
                case 61:
                    if (zzT(obj, i6, i5)) {
                        i3 = i4 * 53;
                        iFloatToIntBits = zzeq.zzf(obj, j2).hashCode();
                        i4 = iFloatToIntBits + i3;
                    }
                    break;
                case 62:
                    if (zzT(obj, i6, i5)) {
                        i2 = i4 * 53;
                        iZzc = zzp(obj, j2);
                        i4 = i2 + iZzc;
                    }
                    break;
                case Notifications.NOTIFICATION_TYPES_ALL /* 63 */:
                    if (zzT(obj, i6, i5)) {
                        i2 = i4 * 53;
                        iZzc = zzp(obj, j2);
                        i4 = i2 + iZzc;
                    }
                    break;
                case VertexAttributes.Usage.BoneWeight /* 64 */:
                    if (zzT(obj, i6, i5)) {
                        i2 = i4 * 53;
                        iZzc = zzp(obj, j2);
                        i4 = i2 + iZzc;
                    }
                    break;
                case 65:
                    if (zzT(obj, i6, i5)) {
                        i2 = i4 * 53;
                        jDoubleToLongBits = zzz(obj, j2);
                        byte[] bArr11 = zzcg.zzd;
                        iZzc = (int) (jDoubleToLongBits ^ (jDoubleToLongBits >>> 32));
                        i4 = i2 + iZzc;
                    }
                    break;
                case 66:
                    if (zzT(obj, i6, i5)) {
                        i2 = i4 * 53;
                        iZzc = zzp(obj, j2);
                        i4 = i2 + iZzc;
                    }
                    break;
                case 67:
                    if (zzT(obj, i6, i5)) {
                        i2 = i4 * 53;
                        jDoubleToLongBits = zzz(obj, j2);
                        byte[] bArr12 = zzcg.zzd;
                        iZzc = (int) (jDoubleToLongBits ^ (jDoubleToLongBits >>> 32));
                        i4 = i2 + iZzc;
                    }
                    break;
                case 68:
                    if (zzT(obj, i6, i5)) {
                        i3 = i4 * 53;
                        iFloatToIntBits = zzeq.zzf(obj, j2).hashCode();
                        i4 = iFloatToIntBits + i3;
                    }
                    break;
            }
        }
        int iHashCode2 = this.zzm.zzd(obj).hashCode() + (i4 * 53);
        if (!this.zzh) {
            return iHashCode2;
        }
        this.zzn.zza(obj);
        throw null;
    }

    /* JADX WARN: Code restructure failed: missing block: B:109:0x0363, code lost:
    
        if (r0 != r13) goto L110;
     */
    /* JADX WARN: Code restructure failed: missing block: B:110:0x0365, code lost:
    
        r15 = r31;
        r14 = r32;
        r12 = r33;
        r13 = r35;
        r11 = r36;
        r9 = r37;
        r8 = r19;
        r5 = r20;
        r3 = r20;
        r6 = r22;
        r2 = r24;
        r1 = r25;
     */
    /* JADX WARN: Code restructure failed: missing block: B:112:0x0381, code lost:
    
        r2 = r0;
        r7 = r20;
        r6 = r22;
        r0 = r36;
     */
    /* JADX WARN: Code restructure failed: missing block: B:118:0x03b7, code lost:
    
        if (r0 != r15) goto L110;
     */
    /* JADX WARN: Code restructure failed: missing block: B:122:0x03dc, code lost:
    
        if (r0 != r15) goto L110;
     */
    /* JADX WARN: Code restructure failed: missing block: B:143:0x0457, code lost:
    
        if (r6 == 1048575) goto L145;
     */
    /* JADX WARN: Code restructure failed: missing block: B:144:0x0459, code lost:
    
        r29.putInt(r12, r6, r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:145:0x045f, code lost:
    
        r2 = r8.zzj;
     */
    /* JADX WARN: Code restructure failed: missing block: B:147:0x0463, code lost:
    
        if (r2 >= r8.zzk) goto L224;
     */
    /* JADX WARN: Code restructure failed: missing block: B:148:0x0465, code lost:
    
        r4 = r8.zzi[r2];
        r5 = r8.zzc[r4];
        r5 = com.google.android.gms.internal.play_billing.zzeq.zzf(r12, r8.zzy(r4) & 1048575);
     */
    /* JADX WARN: Code restructure failed: missing block: B:149:0x0477, code lost:
    
        if (r5 != null) goto L151;
     */
    /* JADX WARN: Code restructure failed: missing block: B:152:0x047e, code lost:
    
        if (r8.zzA(r4) != null) goto L225;
     */
    /* JADX WARN: Code restructure failed: missing block: B:153:0x0480, code lost:
    
        r2 = r2 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:154:0x0483, code lost:
    
        r5 = (com.google.android.gms.internal.play_billing.zzcz) r5;
        r0 = (com.google.android.gms.internal.play_billing.zzcy) r8.zzC(r4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:155:0x048b, code lost:
    
        throw null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:156:0x048c, code lost:
    
        if (r9 != 0) goto L162;
     */
    /* JADX WARN: Code restructure failed: missing block: B:158:0x0490, code lost:
    
        if (r0 != r35) goto L160;
     */
    /* JADX WARN: Code restructure failed: missing block: B:161:0x0497, code lost:
    
        throw com.google.android.gms.internal.play_billing.zzci.zze();
     */
    /* JADX WARN: Code restructure failed: missing block: B:163:0x049a, code lost:
    
        if (r0 > r35) goto L166;
     */
    /* JADX WARN: Code restructure failed: missing block: B:164:0x049c, code lost:
    
        if (r3 != r9) goto L166;
     */
    /* JADX WARN: Code restructure failed: missing block: B:165:0x049e, code lost:
    
        return r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:167:0x04a3, code lost:
    
        throw com.google.android.gms.internal.play_billing.zzci.zze();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    final int zzc(Object obj, byte[] bArr, int i2, int i3, int i4, zzan zzanVar) throws zzci {
        Unsafe unsafe;
        int i5;
        Object obj2;
        zzdi<T> zzdiVar;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        int i11;
        int i12;
        int i13;
        Object obj3;
        zzan zzanVar2;
        int i14;
        int i15;
        int i16;
        int i17;
        int i18;
        byte[] bArr2;
        int i19;
        int i20;
        int i21;
        int i22;
        int i23;
        int i24;
        int i25;
        byte[] bArr3;
        zzdi<T> zzdiVar2 = this;
        Object obj4 = obj;
        byte[] bArr4 = bArr;
        int i26 = i3;
        int i27 = i4;
        zzan zzanVar3 = zzanVar;
        zzG(obj);
        Unsafe unsafe2 = zzb;
        int i28 = -1;
        int iZzi = i2;
        int i29 = -1;
        int i30 = 0;
        int i31 = 0;
        int i32 = 0;
        int i33 = 1048575;
        while (true) {
            if (iZzi < i26) {
                int i34 = iZzi + 1;
                byte b2 = bArr4[iZzi];
                if (b2 < 0) {
                    int iZzk = zzao.zzk(b2, bArr4, i34, zzanVar3);
                    i6 = zzanVar3.zza;
                    i34 = iZzk;
                } else {
                    i6 = b2;
                }
                int i35 = i6 >>> 3;
                int iZzu = i35 > i29 ? zzdiVar2.zzu(i35, i30 / 3) : zzdiVar2.zzt(i35);
                if (iZzu == i28) {
                    i7 = i35;
                    i8 = i34;
                    i9 = i6;
                    i10 = i32;
                    i11 = i28;
                    unsafe = unsafe2;
                    i12 = i27;
                    i13 = 0;
                } else {
                    int i36 = i6 & 7;
                    int[] iArr = zzdiVar2.zzc;
                    int i37 = iArr[iZzu + 1];
                    int iZzx = zzx(i37);
                    int i38 = i6;
                    long j2 = i37 & 1048575;
                    if (iZzx <= 17) {
                        int i39 = iArr[iZzu + 2];
                        int i40 = 1 << (i39 >>> 20);
                        int i41 = i39 & 1048575;
                        if (i41 != i33) {
                            if (i33 != 1048575) {
                                unsafe2.putInt(obj4, i33, i32);
                            }
                            i18 = i41;
                            i17 = unsafe2.getInt(obj4, i41);
                        } else {
                            i17 = i32;
                            i18 = i33;
                        }
                        switch (iZzx) {
                            case 0:
                                bArr2 = bArr;
                                i19 = i35;
                                i20 = iZzu;
                                i21 = i18;
                                i22 = i34;
                                i23 = i40;
                                i24 = i38;
                                if (i36 != 1) {
                                    i33 = i21;
                                    i12 = i4;
                                    unsafe = unsafe2;
                                    i13 = i20;
                                    i10 = i17;
                                    i8 = i22;
                                    i7 = i19;
                                    i9 = i24;
                                    i11 = -1;
                                } else {
                                    zzeq.zzo(obj4, j2, Double.longBitsToDouble(zzao.zzp(bArr2, i22)));
                                    iZzi = i22 + 8;
                                    i32 = i17 | i23;
                                    i26 = i3;
                                    bArr4 = bArr2;
                                    i30 = i20;
                                    i29 = i19;
                                    i31 = i24;
                                    i28 = -1;
                                    i33 = i21;
                                    i27 = i4;
                                }
                                break;
                            case 1:
                                bArr2 = bArr;
                                i19 = i35;
                                i20 = iZzu;
                                i21 = i18;
                                i22 = i34;
                                i23 = i40;
                                i24 = i38;
                                if (i36 != 5) {
                                    i33 = i21;
                                    i12 = i4;
                                    unsafe = unsafe2;
                                    i13 = i20;
                                    i10 = i17;
                                    i8 = i22;
                                    i7 = i19;
                                    i9 = i24;
                                    i11 = -1;
                                } else {
                                    zzeq.zzp(obj4, j2, Float.intBitsToFloat(zzao.zzb(bArr2, i22)));
                                    iZzi = i22 + 4;
                                    i32 = i17 | i23;
                                    i26 = i3;
                                    bArr4 = bArr2;
                                    i30 = i20;
                                    i29 = i19;
                                    i31 = i24;
                                    i28 = -1;
                                    i33 = i21;
                                    i27 = i4;
                                }
                                break;
                            case 2:
                            case 3:
                                i19 = i35;
                                i20 = iZzu;
                                i21 = i18;
                                i22 = i34;
                                i24 = i38;
                                if (i36 != 0) {
                                    i33 = i21;
                                    i12 = i4;
                                    unsafe = unsafe2;
                                    i13 = i20;
                                    i10 = i17;
                                    i8 = i22;
                                    i7 = i19;
                                    i9 = i24;
                                    i11 = -1;
                                } else {
                                    int iZzm = zzao.zzm(bArr, i22, zzanVar3);
                                    unsafe2.putLong(obj, j2, zzanVar3.zzb);
                                    i32 = i17 | i40;
                                    bArr4 = bArr;
                                    i30 = i20;
                                    iZzi = iZzm;
                                    i29 = i19;
                                    i31 = i24;
                                    i28 = -1;
                                    i33 = i21;
                                    i26 = i3;
                                    i27 = i4;
                                }
                                break;
                            case 4:
                            case 11:
                                bArr2 = bArr;
                                i19 = i35;
                                i20 = iZzu;
                                i21 = i18;
                                i22 = i34;
                                i23 = i40;
                                i24 = i38;
                                if (i36 != 0) {
                                    i33 = i21;
                                    i12 = i4;
                                    unsafe = unsafe2;
                                    i13 = i20;
                                    i10 = i17;
                                    i8 = i22;
                                    i7 = i19;
                                    i9 = i24;
                                    i11 = -1;
                                } else {
                                    iZzi = zzao.zzj(bArr2, i22, zzanVar3);
                                    unsafe2.putInt(obj4, j2, zzanVar3.zza);
                                    i32 = i17 | i23;
                                    i26 = i3;
                                    bArr4 = bArr2;
                                    i30 = i20;
                                    i29 = i19;
                                    i31 = i24;
                                    i28 = -1;
                                    i33 = i21;
                                    i27 = i4;
                                }
                                break;
                            case 5:
                            case 14:
                                bArr2 = bArr;
                                i19 = i35;
                                i20 = iZzu;
                                i21 = i18;
                                i25 = i38;
                                i23 = i40;
                                if (i36 != 1) {
                                    i24 = i25;
                                    i22 = i34;
                                    i33 = i21;
                                    i12 = i4;
                                    unsafe = unsafe2;
                                    i13 = i20;
                                    i10 = i17;
                                    i8 = i22;
                                    i7 = i19;
                                    i9 = i24;
                                    i11 = -1;
                                } else {
                                    i24 = i25;
                                    i22 = i34;
                                    unsafe2.putLong(obj, j2, zzao.zzp(bArr2, i34));
                                    iZzi = i22 + 8;
                                    i32 = i17 | i23;
                                    i26 = i3;
                                    bArr4 = bArr2;
                                    i30 = i20;
                                    i29 = i19;
                                    i31 = i24;
                                    i28 = -1;
                                    i33 = i21;
                                    i27 = i4;
                                }
                                break;
                            case 6:
                            case 13:
                                bArr3 = bArr;
                                i19 = i35;
                                i20 = iZzu;
                                i21 = i18;
                                i25 = i38;
                                if (i36 != 5) {
                                    i24 = i25;
                                    i22 = i34;
                                    i33 = i21;
                                    i12 = i4;
                                    unsafe = unsafe2;
                                    i13 = i20;
                                    i10 = i17;
                                    i8 = i22;
                                    i7 = i19;
                                    i9 = i24;
                                    i11 = -1;
                                } else {
                                    unsafe2.putInt(obj4, j2, zzao.zzb(bArr3, i34));
                                    iZzi = i34 + 4;
                                    i32 = i17 | i40;
                                    bArr4 = bArr3;
                                    i30 = i20;
                                    i31 = i25;
                                    i29 = i19;
                                    i28 = -1;
                                    i33 = i21;
                                    i26 = i3;
                                    i27 = i4;
                                }
                                break;
                            case 7:
                                bArr3 = bArr;
                                i19 = i35;
                                i20 = iZzu;
                                i21 = i18;
                                i25 = i38;
                                if (i36 != 0) {
                                    i24 = i25;
                                    i22 = i34;
                                    i33 = i21;
                                    i12 = i4;
                                    unsafe = unsafe2;
                                    i13 = i20;
                                    i10 = i17;
                                    i8 = i22;
                                    i7 = i19;
                                    i9 = i24;
                                    i11 = -1;
                                } else {
                                    iZzi = zzao.zzm(bArr3, i34, zzanVar3);
                                    zzeq.zzm(obj4, j2, zzanVar3.zzb != 0);
                                    i32 = i17 | i40;
                                    bArr4 = bArr3;
                                    i30 = i20;
                                    i31 = i25;
                                    i29 = i19;
                                    i28 = -1;
                                    i33 = i21;
                                    i26 = i3;
                                    i27 = i4;
                                }
                                break;
                            case 8:
                                bArr3 = bArr;
                                i19 = i35;
                                i20 = iZzu;
                                i21 = i18;
                                i25 = i38;
                                if (i36 != 2) {
                                    i24 = i25;
                                    i22 = i34;
                                    i33 = i21;
                                    i12 = i4;
                                    unsafe = unsafe2;
                                    i13 = i20;
                                    i10 = i17;
                                    i8 = i22;
                                    i7 = i19;
                                    i9 = i24;
                                    i11 = -1;
                                } else {
                                    iZzi = (536870912 & i37) == 0 ? zzao.zzg(bArr3, i34, zzanVar3) : zzao.zzh(bArr3, i34, zzanVar3);
                                    unsafe2.putObject(obj4, j2, zzanVar3.zzc);
                                    i32 = i17 | i40;
                                    bArr4 = bArr3;
                                    i30 = i20;
                                    i31 = i25;
                                    i29 = i19;
                                    i28 = -1;
                                    i33 = i21;
                                    i26 = i3;
                                    i27 = i4;
                                }
                                break;
                            case 9:
                                bArr3 = bArr;
                                i19 = i35;
                                i20 = iZzu;
                                i21 = i18;
                                i25 = i38;
                                if (i36 != 2) {
                                    i24 = i25;
                                    i22 = i34;
                                    i33 = i21;
                                    i12 = i4;
                                    unsafe = unsafe2;
                                    i13 = i20;
                                    i10 = i17;
                                    i8 = i22;
                                    i7 = i19;
                                    i9 = i24;
                                    i11 = -1;
                                } else {
                                    Object objZzD = zzdiVar2.zzD(obj4, i20);
                                    iZzi = zzao.zzo(objZzD, zzdiVar2.zzB(i20), bArr, i34, i3, zzanVar);
                                    zzdiVar2.zzL(obj4, i20, objZzD);
                                    i32 = i17 | i40;
                                    bArr4 = bArr3;
                                    i30 = i20;
                                    i31 = i25;
                                    i29 = i19;
                                    i28 = -1;
                                    i33 = i21;
                                    i26 = i3;
                                    i27 = i4;
                                }
                                break;
                            case 10:
                                bArr3 = bArr;
                                i19 = i35;
                                i20 = iZzu;
                                i21 = i18;
                                i25 = i38;
                                if (i36 != 2) {
                                    i24 = i25;
                                    i22 = i34;
                                    i33 = i21;
                                    i12 = i4;
                                    unsafe = unsafe2;
                                    i13 = i20;
                                    i10 = i17;
                                    i8 = i22;
                                    i7 = i19;
                                    i9 = i24;
                                    i11 = -1;
                                } else {
                                    iZzi = zzao.zza(bArr3, i34, zzanVar3);
                                    unsafe2.putObject(obj4, j2, zzanVar3.zzc);
                                    i32 = i17 | i40;
                                    bArr4 = bArr3;
                                    i30 = i20;
                                    i31 = i25;
                                    i29 = i19;
                                    i28 = -1;
                                    i33 = i21;
                                    i26 = i3;
                                    i27 = i4;
                                }
                                break;
                            case 12:
                                bArr3 = bArr;
                                i19 = i35;
                                i20 = iZzu;
                                i21 = i18;
                                i25 = i38;
                                if (i36 != 0) {
                                    i24 = i25;
                                    i22 = i34;
                                    i33 = i21;
                                    i12 = i4;
                                    unsafe = unsafe2;
                                    i13 = i20;
                                    i10 = i17;
                                    i8 = i22;
                                    i7 = i19;
                                    i9 = i24;
                                    i11 = -1;
                                } else {
                                    iZzi = zzao.zzj(bArr3, i34, zzanVar3);
                                    int i42 = zzanVar3.zza;
                                    zzce zzceVarZzA = zzdiVar2.zzA(i20);
                                    if (zzceVarZzA == null || zzceVarZzA.zza(i42)) {
                                        unsafe2.putInt(obj4, j2, i42);
                                        i32 = i17 | i40;
                                        bArr4 = bArr3;
                                        i30 = i20;
                                        i31 = i25;
                                        i29 = i19;
                                        i28 = -1;
                                        i33 = i21;
                                        i26 = i3;
                                        i27 = i4;
                                    } else {
                                        zzd(obj).zzj(i25, Long.valueOf(i42));
                                        i30 = i20;
                                        i32 = i17;
                                        i31 = i25;
                                        i29 = i19;
                                        i28 = -1;
                                        i26 = i3;
                                        i27 = i4;
                                        bArr4 = bArr3;
                                        i33 = i21;
                                    }
                                }
                                break;
                            case 15:
                                bArr3 = bArr;
                                i19 = i35;
                                i20 = iZzu;
                                i21 = i18;
                                i25 = i38;
                                if (i36 != 0) {
                                    i24 = i25;
                                    i22 = i34;
                                    i33 = i21;
                                    i12 = i4;
                                    unsafe = unsafe2;
                                    i13 = i20;
                                    i10 = i17;
                                    i8 = i22;
                                    i7 = i19;
                                    i9 = i24;
                                    i11 = -1;
                                } else {
                                    iZzi = zzao.zzj(bArr3, i34, zzanVar3);
                                    unsafe2.putInt(obj4, j2, zzbe.zzb(zzanVar3.zza));
                                    i32 = i17 | i40;
                                    bArr4 = bArr3;
                                    i30 = i20;
                                    i31 = i25;
                                    i29 = i19;
                                    i28 = -1;
                                    i33 = i21;
                                    i26 = i3;
                                    i27 = i4;
                                }
                                break;
                            case 16:
                                i19 = i35;
                                i20 = iZzu;
                                i21 = i18;
                                i25 = i38;
                                if (i36 != 0) {
                                    i24 = i25;
                                    i22 = i34;
                                    i33 = i21;
                                    i12 = i4;
                                    unsafe = unsafe2;
                                    i13 = i20;
                                    i10 = i17;
                                    i8 = i22;
                                    i7 = i19;
                                    i9 = i24;
                                    i11 = -1;
                                } else {
                                    int iZzm2 = zzao.zzm(bArr, i34, zzanVar3);
                                    unsafe2.putLong(obj, j2, zzbe.zzc(zzanVar3.zzb));
                                    i32 = i17 | i40;
                                    bArr4 = bArr;
                                    iZzi = iZzm2;
                                    i30 = i20;
                                    i31 = i25;
                                    i29 = i19;
                                    i28 = -1;
                                    i33 = i21;
                                    i26 = i3;
                                    i27 = i4;
                                }
                                break;
                            default:
                                if (i36 != 3) {
                                    i19 = i35;
                                    i20 = iZzu;
                                    i21 = i18;
                                    i22 = i34;
                                    i24 = i38;
                                    i33 = i21;
                                    i12 = i4;
                                    unsafe = unsafe2;
                                    i13 = i20;
                                    i10 = i17;
                                    i8 = i22;
                                    i7 = i19;
                                    i9 = i24;
                                    i11 = -1;
                                } else {
                                    Object objZzD2 = zzdiVar2.zzD(obj4, iZzu);
                                    iZzi = zzao.zzn(objZzD2, zzdiVar2.zzB(iZzu), bArr, i34, i3, (i35 << 3) | 4, zzanVar);
                                    zzdiVar2.zzL(obj4, iZzu, objZzD2);
                                    i32 = i17 | i40;
                                    bArr4 = bArr;
                                    i33 = i18;
                                    i30 = iZzu;
                                    i31 = i38;
                                    i29 = i35;
                                    i28 = -1;
                                    i26 = i3;
                                    i27 = i4;
                                }
                                break;
                        }
                    } else {
                        int i43 = i34;
                        if (iZzx != 27) {
                            i10 = i32;
                            i15 = i33;
                            if (iZzx <= 49) {
                                i11 = -1;
                                unsafe = unsafe2;
                                i13 = iZzu;
                                i7 = i35;
                                iZzi = zzs(obj, bArr, i43, i3, i38, i35, i36, iZzu, i37, iZzx, j2, zzanVar);
                            } else {
                                unsafe = unsafe2;
                                i13 = iZzu;
                                i7 = i35;
                                i16 = i43;
                                i11 = -1;
                                if (iZzx != 50) {
                                    iZzi = zzr(obj, bArr, i16, i3, i38, i7, i36, i37, iZzx, j2, i13, zzanVar);
                                } else if (i36 == 2) {
                                    iZzi = zzq(obj, bArr, i16, i3, i13, j2, zzanVar);
                                }
                            }
                        } else if (i36 == 2) {
                            zzcf zzcfVarZzd = (zzcf) unsafe2.getObject(obj4, j2);
                            if (!zzcfVarZzd.zzc()) {
                                int size = zzcfVarZzd.size();
                                zzcfVarZzd = zzcfVarZzd.zzd(size == 0 ? 10 : size + size);
                                unsafe2.putObject(obj4, j2, zzcfVarZzd);
                            }
                            iZzi = zzao.zze(zzdiVar2.zzB(iZzu), i38, bArr, i43, i3, zzcfVarZzd, zzanVar);
                            i26 = i3;
                            i30 = iZzu;
                            i29 = i35;
                            i32 = i32;
                            i31 = i38;
                            i33 = i33;
                            i28 = -1;
                            bArr4 = bArr;
                            i27 = i4;
                        } else {
                            i10 = i32;
                            i15 = i33;
                            unsafe = unsafe2;
                            i13 = iZzu;
                            i7 = i35;
                            i16 = i43;
                            i11 = -1;
                        }
                        i12 = i4;
                        i8 = i16;
                        i9 = i38;
                        i33 = i15;
                    }
                }
                if (i9 != i12 || i12 == 0) {
                    int i44 = i12;
                    if (this.zzh) {
                        zzanVar2 = zzanVar;
                        zzbn zzbnVar = zzanVar2.zzd;
                        if (zzbnVar != zzbn.zza) {
                            i14 = i7;
                            if (zzbnVar.zzb(this.zzg, i14) != null) {
                                throw null;
                            }
                            iZzi = zzao.zzi(i9, bArr, i8, i3, zzd(obj), zzanVar);
                            obj3 = obj;
                            i26 = i3;
                            i31 = i9;
                            zzdiVar2 = this;
                            i29 = i14;
                            obj4 = obj3;
                            i28 = i11;
                            i32 = i10;
                            i30 = i13;
                            bArr4 = bArr;
                            i27 = i44;
                            zzanVar3 = zzanVar2;
                            unsafe2 = unsafe;
                        } else {
                            obj3 = obj;
                        }
                    } else {
                        obj3 = obj;
                        zzanVar2 = zzanVar;
                    }
                    i14 = i7;
                    iZzi = zzao.zzi(i9, bArr, i8, i3, zzd(obj), zzanVar);
                    i26 = i3;
                    i31 = i9;
                    zzdiVar2 = this;
                    i29 = i14;
                    obj4 = obj3;
                    i28 = i11;
                    i32 = i10;
                    i30 = i13;
                    bArr4 = bArr;
                    i27 = i44;
                    zzanVar3 = zzanVar2;
                    unsafe2 = unsafe;
                } else {
                    zzdiVar = this;
                    obj2 = obj;
                    i5 = i12;
                    iZzi = i8;
                    i31 = i9;
                    i32 = i10;
                }
            } else {
                unsafe = unsafe2;
                i5 = i27;
                obj2 = obj4;
                zzdiVar = zzdiVar2;
            }
        }
    }

    @Override // com.google.android.gms.internal.play_billing.zzdp
    public final Object zze() {
        return ((zzcb) this.zzg).zzi();
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x006c  */
    @Override // com.google.android.gms.internal.play_billing.zzdp
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void zzf(Object obj) {
        if (zzS(obj)) {
            if (obj instanceof zzcb) {
                zzcb zzcbVar = (zzcb) obj;
                zzcbVar.zzq(Api.BaseClientBuilder.API_PRIORITY_OTHER);
                zzcbVar.zza = 0;
                zzcbVar.zzo();
            }
            int length = this.zzc.length;
            for (int i2 = 0; i2 < length; i2 += 3) {
                int iZzy = zzy(i2);
                int i3 = 1048575 & iZzy;
                int iZzx = zzx(iZzy);
                long j2 = i3;
                if (iZzx != 9) {
                    if (iZzx != 60 && iZzx != 68) {
                        switch (iZzx) {
                            case 18:
                            case 19:
                            case 20:
                            case Decal.C4 /* 21 */:
                            case Decal.U4 /* 22 */:
                            case Decal.V4 /* 23 */:
                            case Decal.SIZE /* 24 */:
                            case 25:
                            case 26:
                            case 27:
                            case 28:
                            case 29:
                            case 30:
                            case 31:
                            case 32:
                            case 33:
                            case 34:
                            case 35:
                            case 36:
                            case 37:
                            case 38:
                            case 39:
                            case 40:
                            case 41:
                            case 42:
                            case 43:
                            case 44:
                            case 45:
                            case 46:
                            case 47:
                            case 48:
                            case 49:
                                this.zzl.zza(obj, j2);
                                break;
                            case 50:
                                Unsafe unsafe = zzb;
                                Object object = unsafe.getObject(obj, j2);
                                if (object != null) {
                                    ((zzcz) object).zzc();
                                    unsafe.putObject(obj, j2, object);
                                }
                                break;
                        }
                    } else if (zzT(obj, this.zzc[i2], i2)) {
                        zzB(i2).zzf(zzb.getObject(obj, j2));
                    }
                } else if (zzP(obj, i2)) {
                    zzB(i2).zzf(zzb.getObject(obj, j2));
                }
            }
            this.zzm.zzg(obj);
            if (this.zzh) {
                this.zzn.zzb(obj);
            }
        }
    }

    @Override // com.google.android.gms.internal.play_billing.zzdp
    public final void zzg(Object obj, Object obj2) {
        zzG(obj);
        obj2.getClass();
        for (int i2 = 0; i2 < this.zzc.length; i2 += 3) {
            int iZzy = zzy(i2);
            int i3 = this.zzc[i2];
            long j2 = 1048575 & iZzy;
            switch (zzx(iZzy)) {
                case 0:
                    if (zzP(obj2, i2)) {
                        zzeq.zzo(obj, j2, zzeq.zza(obj2, j2));
                        zzJ(obj, i2);
                    }
                    break;
                case 1:
                    if (zzP(obj2, i2)) {
                        zzeq.zzp(obj, j2, zzeq.zzb(obj2, j2));
                        zzJ(obj, i2);
                    }
                    break;
                case 2:
                    if (zzP(obj2, i2)) {
                        zzeq.zzr(obj, j2, zzeq.zzd(obj2, j2));
                        zzJ(obj, i2);
                    }
                    break;
                case 3:
                    if (zzP(obj2, i2)) {
                        zzeq.zzr(obj, j2, zzeq.zzd(obj2, j2));
                        zzJ(obj, i2);
                    }
                    break;
                case 4:
                    if (zzP(obj2, i2)) {
                        zzeq.zzq(obj, j2, zzeq.zzc(obj2, j2));
                        zzJ(obj, i2);
                    }
                    break;
                case 5:
                    if (zzP(obj2, i2)) {
                        zzeq.zzr(obj, j2, zzeq.zzd(obj2, j2));
                        zzJ(obj, i2);
                    }
                    break;
                case 6:
                    if (zzP(obj2, i2)) {
                        zzeq.zzq(obj, j2, zzeq.zzc(obj2, j2));
                        zzJ(obj, i2);
                    }
                    break;
                case 7:
                    if (zzP(obj2, i2)) {
                        zzeq.zzm(obj, j2, zzeq.zzw(obj2, j2));
                        zzJ(obj, i2);
                    }
                    break;
                case 8:
                    if (zzP(obj2, i2)) {
                        zzeq.zzs(obj, j2, zzeq.zzf(obj2, j2));
                        zzJ(obj, i2);
                    }
                    break;
                case 9:
                    zzH(obj, obj2, i2);
                    break;
                case 10:
                    if (zzP(obj2, i2)) {
                        zzeq.zzs(obj, j2, zzeq.zzf(obj2, j2));
                        zzJ(obj, i2);
                    }
                    break;
                case 11:
                    if (zzP(obj2, i2)) {
                        zzeq.zzq(obj, j2, zzeq.zzc(obj2, j2));
                        zzJ(obj, i2);
                    }
                    break;
                case 12:
                    if (zzP(obj2, i2)) {
                        zzeq.zzq(obj, j2, zzeq.zzc(obj2, j2));
                        zzJ(obj, i2);
                    }
                    break;
                case 13:
                    if (zzP(obj2, i2)) {
                        zzeq.zzq(obj, j2, zzeq.zzc(obj2, j2));
                        zzJ(obj, i2);
                    }
                    break;
                case 14:
                    if (zzP(obj2, i2)) {
                        zzeq.zzr(obj, j2, zzeq.zzd(obj2, j2));
                        zzJ(obj, i2);
                    }
                    break;
                case 15:
                    if (zzP(obj2, i2)) {
                        zzeq.zzq(obj, j2, zzeq.zzc(obj2, j2));
                        zzJ(obj, i2);
                    }
                    break;
                case 16:
                    if (zzP(obj2, i2)) {
                        zzeq.zzr(obj, j2, zzeq.zzd(obj2, j2));
                        zzJ(obj, i2);
                    }
                    break;
                case 17:
                    zzH(obj, obj2, i2);
                    break;
                case 18:
                case 19:
                case 20:
                case Decal.C4 /* 21 */:
                case Decal.U4 /* 22 */:
                case Decal.V4 /* 23 */:
                case Decal.SIZE /* 24 */:
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case 48:
                case 49:
                    this.zzl.zzb(obj, obj2, j2);
                    break;
                case 50:
                    int i4 = zzdr.zza;
                    zzeq.zzs(obj, j2, zzda.zzb(zzeq.zzf(obj, j2), zzeq.zzf(obj2, j2)));
                    break;
                case 51:
                case 52:
                case 53:
                case 54:
                case 55:
                case 56:
                case 57:
                case 58:
                case 59:
                    if (zzT(obj2, i3, i2)) {
                        zzeq.zzs(obj, j2, zzeq.zzf(obj2, j2));
                        zzK(obj, i3, i2);
                    }
                    break;
                case 60:
                    zzI(obj, obj2, i2);
                    break;
                case 61:
                case 62:
                case Notifications.NOTIFICATION_TYPES_ALL /* 63 */:
                case VertexAttributes.Usage.BoneWeight /* 64 */:
                case 65:
                case 66:
                case 67:
                    if (zzT(obj2, i3, i2)) {
                        zzeq.zzs(obj, j2, zzeq.zzf(obj2, j2));
                        zzK(obj, i3, i2);
                    }
                    break;
                case 68:
                    zzI(obj, obj2, i2);
                    break;
            }
        }
        zzdr.zzB(this.zzm, obj, obj2);
        if (this.zzh) {
            this.zzn.zza(obj2);
            throw null;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:113:0x0304, code lost:
    
        if (r0 != r15) goto L105;
     */
    /* JADX WARN: Code restructure failed: missing block: B:115:0x0307, code lost:
    
        r6 = r32;
        r2 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:118:0x0328, code lost:
    
        if (r0 != r15) goto L105;
     */
    /* JADX WARN: Failed to find 'out' block for switch in B:30:0x00a2. Please report as an issue. */
    @Override // com.google.android.gms.internal.play_billing.zzdp
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void zzh(Object obj, byte[] bArr, int i2, int i3, zzan zzanVar) throws zzci {
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        Unsafe unsafe;
        int i9;
        boolean z2;
        int i10;
        int i11;
        int i12;
        int i13;
        boolean z3;
        int i14;
        int i15;
        int i16;
        zzdi<T> zzdiVar = this;
        Object obj2 = obj;
        byte[] bArr2 = bArr;
        int i17 = i3;
        zzan zzanVar2 = zzanVar;
        boolean z4 = true;
        zzew zzewVar = zzew.zza;
        int i18 = -1;
        if (zzdiVar.zzo - 1 == 0) {
            zzc(obj, bArr, i2, i3, 0, zzanVar);
            return;
        }
        zzG(obj);
        Unsafe unsafe2 = zzb;
        int i19 = 1048575;
        int iZzi = i2;
        int i20 = 1048575;
        int i21 = -1;
        int i22 = 0;
        int i23 = 0;
        while (iZzi < i17) {
            int i24 = iZzi + 1;
            byte b2 = bArr2[iZzi];
            if (b2 < 0) {
                int iZzk = zzao.zzk(b2, bArr2, i24, zzanVar2);
                i4 = zzanVar2.zza;
                i24 = iZzk;
            } else {
                i4 = b2;
            }
            int i25 = i4 >>> 3;
            int iZzu = i25 > i21 ? zzdiVar.zzu(i25, i22 / 3) : zzdiVar.zzt(i25);
            if (iZzu == i18) {
                i5 = i25;
                i6 = i24;
                i7 = i23;
                i8 = i20;
                unsafe = unsafe2;
                i9 = i18;
                z2 = z4;
                i10 = 0;
            } else {
                int i26 = i4 & 7;
                int[] iArr = zzdiVar.zzc;
                int i27 = iArr[iZzu + 1];
                int iZzx = zzx(i27);
                Unsafe unsafe3 = unsafe2;
                long j2 = i27 & i19;
                if (iZzx <= 17) {
                    int i28 = iArr[iZzu + 2];
                    int i29 = 1 << (i28 >>> 20);
                    int i30 = i28 & 1048575;
                    if (i30 != i20) {
                        if (i20 != 1048575) {
                            unsafe2 = unsafe3;
                            unsafe2.putInt(obj2, i20, i23);
                        } else {
                            unsafe2 = unsafe3;
                        }
                        if (i30 != 1048575) {
                            i23 = unsafe2.getInt(obj2, i30);
                        }
                        i11 = i30;
                        i7 = i23;
                    } else {
                        unsafe2 = unsafe3;
                        i7 = i23;
                        i11 = i20;
                    }
                    switch (iZzx) {
                        case 0:
                            i5 = i25;
                            i12 = iZzu;
                            i13 = i24;
                            z3 = true;
                            if (i26 != 1) {
                                z2 = z3;
                                unsafe = unsafe2;
                                i6 = i13;
                                i8 = i11;
                                i9 = -1;
                                i10 = i12;
                                iZzi = zzao.zzi(i4, bArr, i6, i3, zzd(obj), zzanVar);
                                zzdiVar = this;
                                obj2 = obj;
                                bArr2 = bArr;
                                i17 = i3;
                                zzanVar2 = zzanVar;
                                i23 = i7;
                            } else {
                                zzeq.zzo(obj2, j2, Double.longBitsToDouble(zzao.zzp(bArr2, i13)));
                                iZzi = i13 + 8;
                                i23 = i7 | i29;
                                z4 = true;
                                i22 = i12;
                                i19 = 1048575;
                                i20 = i11;
                                i21 = i5;
                                i18 = -1;
                                i17 = i3;
                            }
                            break;
                        case 1:
                            i5 = i25;
                            i12 = iZzu;
                            i13 = i24;
                            if (i26 != 5) {
                                z3 = true;
                                z2 = z3;
                                unsafe = unsafe2;
                                i6 = i13;
                                i8 = i11;
                                i9 = -1;
                                i10 = i12;
                                iZzi = zzao.zzi(i4, bArr, i6, i3, zzd(obj), zzanVar);
                                zzdiVar = this;
                                obj2 = obj;
                                bArr2 = bArr;
                                i17 = i3;
                                zzanVar2 = zzanVar;
                                i23 = i7;
                            } else {
                                zzeq.zzp(obj2, j2, Float.intBitsToFloat(zzao.zzb(bArr2, i13)));
                                iZzi = i13 + 4;
                                i23 = i7 | i29;
                                i22 = i12;
                                i19 = 1048575;
                                i20 = i11;
                                i21 = i5;
                                i18 = -1;
                                z4 = true;
                                i17 = i3;
                            }
                            break;
                        case 2:
                        case 3:
                            i5 = i25;
                            i12 = iZzu;
                            i13 = i24;
                            if (i26 != 0) {
                                z3 = true;
                                z2 = z3;
                                unsafe = unsafe2;
                                i6 = i13;
                                i8 = i11;
                                i9 = -1;
                                i10 = i12;
                                iZzi = zzao.zzi(i4, bArr, i6, i3, zzd(obj), zzanVar);
                                zzdiVar = this;
                                obj2 = obj;
                                bArr2 = bArr;
                                i17 = i3;
                                zzanVar2 = zzanVar;
                                i23 = i7;
                            } else {
                                int iZzm = zzao.zzm(bArr2, i13, zzanVar2);
                                unsafe2.putLong(obj, j2, zzanVar2.zzb);
                                i23 = i7 | i29;
                                i22 = i12;
                                iZzi = iZzm;
                                i19 = 1048575;
                                i20 = i11;
                                i21 = i5;
                                i18 = -1;
                                z4 = true;
                                i17 = i3;
                            }
                            break;
                        case 4:
                        case 11:
                            i5 = i25;
                            i12 = iZzu;
                            i13 = i24;
                            if (i26 != 0) {
                                z3 = true;
                                z2 = z3;
                                unsafe = unsafe2;
                                i6 = i13;
                                i8 = i11;
                                i9 = -1;
                                i10 = i12;
                                iZzi = zzao.zzi(i4, bArr, i6, i3, zzd(obj), zzanVar);
                                zzdiVar = this;
                                obj2 = obj;
                                bArr2 = bArr;
                                i17 = i3;
                                zzanVar2 = zzanVar;
                                i23 = i7;
                            } else {
                                iZzi = zzao.zzj(bArr2, i13, zzanVar2);
                                unsafe2.putInt(obj2, j2, zzanVar2.zza);
                                i23 = i7 | i29;
                                i22 = i12;
                                i19 = 1048575;
                                i20 = i11;
                                i21 = i5;
                                i18 = -1;
                                z4 = true;
                                i17 = i3;
                            }
                            break;
                        case 5:
                        case 14:
                            i5 = i25;
                            i12 = iZzu;
                            if (i26 != 1) {
                                i13 = i24;
                                z3 = true;
                                z2 = z3;
                                unsafe = unsafe2;
                                i6 = i13;
                                i8 = i11;
                                i9 = -1;
                                i10 = i12;
                                iZzi = zzao.zzi(i4, bArr, i6, i3, zzd(obj), zzanVar);
                                zzdiVar = this;
                                obj2 = obj;
                                bArr2 = bArr;
                                i17 = i3;
                                zzanVar2 = zzanVar;
                                i23 = i7;
                            } else {
                                unsafe2.putLong(obj, j2, zzao.zzp(bArr2, i24));
                                iZzi = i24 + 8;
                                i23 = i7 | i29;
                                i22 = i12;
                                i19 = 1048575;
                                i20 = i11;
                                i21 = i5;
                                i18 = -1;
                                z4 = true;
                                i17 = i3;
                            }
                            break;
                        case 6:
                        case 13:
                            i5 = i25;
                            i12 = iZzu;
                            if (i26 != 5) {
                                i13 = i24;
                                z3 = true;
                                z2 = z3;
                                unsafe = unsafe2;
                                i6 = i13;
                                i8 = i11;
                                i9 = -1;
                                i10 = i12;
                                iZzi = zzao.zzi(i4, bArr, i6, i3, zzd(obj), zzanVar);
                                zzdiVar = this;
                                obj2 = obj;
                                bArr2 = bArr;
                                i17 = i3;
                                zzanVar2 = zzanVar;
                                i23 = i7;
                            } else {
                                unsafe2.putInt(obj2, j2, zzao.zzb(bArr2, i24));
                                iZzi = i24 + 4;
                                i23 = i7 | i29;
                                i22 = i12;
                                i19 = 1048575;
                                i20 = i11;
                                i21 = i5;
                                i18 = -1;
                                z4 = true;
                                i17 = i3;
                            }
                            break;
                        case 7:
                            i5 = i25;
                            i12 = iZzu;
                            if (i26 != 0) {
                                i13 = i24;
                                z3 = true;
                                z2 = z3;
                                unsafe = unsafe2;
                                i6 = i13;
                                i8 = i11;
                                i9 = -1;
                                i10 = i12;
                                iZzi = zzao.zzi(i4, bArr, i6, i3, zzd(obj), zzanVar);
                                zzdiVar = this;
                                obj2 = obj;
                                bArr2 = bArr;
                                i17 = i3;
                                zzanVar2 = zzanVar;
                                i23 = i7;
                            } else {
                                iZzi = zzao.zzm(bArr2, i24, zzanVar2);
                                zzeq.zzm(obj2, j2, zzanVar2.zzb != 0);
                                i23 = i7 | i29;
                                i22 = i12;
                                i19 = 1048575;
                                i20 = i11;
                                i21 = i5;
                                i18 = -1;
                                z4 = true;
                                i17 = i3;
                            }
                            break;
                        case 8:
                            i5 = i25;
                            i12 = iZzu;
                            if (i26 != 2) {
                                i13 = i24;
                                z3 = true;
                                z2 = z3;
                                unsafe = unsafe2;
                                i6 = i13;
                                i8 = i11;
                                i9 = -1;
                                i10 = i12;
                                iZzi = zzao.zzi(i4, bArr, i6, i3, zzd(obj), zzanVar);
                                zzdiVar = this;
                                obj2 = obj;
                                bArr2 = bArr;
                                i17 = i3;
                                zzanVar2 = zzanVar;
                                i23 = i7;
                            } else {
                                iZzi = (536870912 & i27) == 0 ? zzao.zzg(bArr2, i24, zzanVar2) : zzao.zzh(bArr2, i24, zzanVar2);
                                unsafe2.putObject(obj2, j2, zzanVar2.zzc);
                                i23 = i7 | i29;
                                i22 = i12;
                                i19 = 1048575;
                                i20 = i11;
                                i21 = i5;
                                i18 = -1;
                                z4 = true;
                                i17 = i3;
                            }
                            break;
                        case 9:
                            i5 = i25;
                            i14 = iZzu;
                            if (i26 != 2) {
                                i12 = i14;
                                i13 = i24;
                                z3 = true;
                                z2 = z3;
                                unsafe = unsafe2;
                                i6 = i13;
                                i8 = i11;
                                i9 = -1;
                                i10 = i12;
                                iZzi = zzao.zzi(i4, bArr, i6, i3, zzd(obj), zzanVar);
                                zzdiVar = this;
                                obj2 = obj;
                                bArr2 = bArr;
                                i17 = i3;
                                zzanVar2 = zzanVar;
                                i23 = i7;
                            } else {
                                Object objZzD = zzdiVar.zzD(obj2, i14);
                                iZzi = zzao.zzo(objZzD, zzdiVar.zzB(i14), bArr, i24, i3, zzanVar);
                                zzdiVar.zzL(obj2, i14, objZzD);
                                i23 = i7 | i29;
                                i22 = i14;
                                i19 = 1048575;
                                i20 = i11;
                                i21 = i5;
                                i18 = -1;
                                z4 = true;
                                i17 = i3;
                            }
                            break;
                        case 10:
                            i5 = i25;
                            i14 = iZzu;
                            if (i26 != 2) {
                                i12 = i14;
                                i13 = i24;
                                z3 = true;
                                z2 = z3;
                                unsafe = unsafe2;
                                i6 = i13;
                                i8 = i11;
                                i9 = -1;
                                i10 = i12;
                                iZzi = zzao.zzi(i4, bArr, i6, i3, zzd(obj), zzanVar);
                                zzdiVar = this;
                                obj2 = obj;
                                bArr2 = bArr;
                                i17 = i3;
                                zzanVar2 = zzanVar;
                                i23 = i7;
                            } else {
                                int iZza = zzao.zza(bArr2, i24, zzanVar2);
                                unsafe2.putObject(obj2, j2, zzanVar2.zzc);
                                i23 = i7 | i29;
                                iZzi = iZza;
                                i22 = i14;
                                i19 = 1048575;
                                i20 = i11;
                                i21 = i5;
                                i18 = -1;
                                z4 = true;
                                i17 = i3;
                            }
                            break;
                        case 12:
                            i5 = i25;
                            i14 = iZzu;
                            if (i26 != 0) {
                                i12 = i14;
                                i13 = i24;
                                z3 = true;
                                z2 = z3;
                                unsafe = unsafe2;
                                i6 = i13;
                                i8 = i11;
                                i9 = -1;
                                i10 = i12;
                                iZzi = zzao.zzi(i4, bArr, i6, i3, zzd(obj), zzanVar);
                                zzdiVar = this;
                                obj2 = obj;
                                bArr2 = bArr;
                                i17 = i3;
                                zzanVar2 = zzanVar;
                                i23 = i7;
                            } else {
                                iZzi = zzao.zzj(bArr2, i24, zzanVar2);
                                unsafe2.putInt(obj2, j2, zzanVar2.zza);
                                i23 = i7 | i29;
                                i22 = i14;
                                i19 = 1048575;
                                i20 = i11;
                                i21 = i5;
                                i18 = -1;
                                z4 = true;
                                i17 = i3;
                            }
                            break;
                        case 15:
                            i5 = i25;
                            i14 = iZzu;
                            if (i26 != 0) {
                                i12 = i14;
                                i13 = i24;
                                z3 = true;
                                z2 = z3;
                                unsafe = unsafe2;
                                i6 = i13;
                                i8 = i11;
                                i9 = -1;
                                i10 = i12;
                                iZzi = zzao.zzi(i4, bArr, i6, i3, zzd(obj), zzanVar);
                                zzdiVar = this;
                                obj2 = obj;
                                bArr2 = bArr;
                                i17 = i3;
                                zzanVar2 = zzanVar;
                                i23 = i7;
                            } else {
                                iZzi = zzao.zzj(bArr2, i24, zzanVar2);
                                unsafe2.putInt(obj2, j2, zzbe.zzb(zzanVar2.zza));
                                i23 = i7 | i29;
                                i22 = i14;
                                i19 = 1048575;
                                i20 = i11;
                                i21 = i5;
                                i18 = -1;
                                z4 = true;
                                i17 = i3;
                            }
                            break;
                        case 16:
                            if (i26 != 0) {
                                i5 = i25;
                                i12 = iZzu;
                                i13 = i24;
                                z3 = true;
                                z2 = z3;
                                unsafe = unsafe2;
                                i6 = i13;
                                i8 = i11;
                                i9 = -1;
                                i10 = i12;
                                iZzi = zzao.zzi(i4, bArr, i6, i3, zzd(obj), zzanVar);
                                zzdiVar = this;
                                obj2 = obj;
                                bArr2 = bArr;
                                i17 = i3;
                                zzanVar2 = zzanVar;
                                i23 = i7;
                            } else {
                                int iZzm2 = zzao.zzm(bArr2, i24, zzanVar2);
                                i5 = i25;
                                i14 = iZzu;
                                unsafe2.putLong(obj, j2, zzbe.zzc(zzanVar2.zzb));
                                i23 = i7 | i29;
                                iZzi = iZzm2;
                                i22 = i14;
                                i19 = 1048575;
                                i20 = i11;
                                i21 = i5;
                                i18 = -1;
                                z4 = true;
                                i17 = i3;
                            }
                            break;
                        default:
                            i5 = i25;
                            i12 = iZzu;
                            i13 = i24;
                            z3 = true;
                            z2 = z3;
                            unsafe = unsafe2;
                            i6 = i13;
                            i8 = i11;
                            i9 = -1;
                            i10 = i12;
                            iZzi = zzao.zzi(i4, bArr, i6, i3, zzd(obj), zzanVar);
                            zzdiVar = this;
                            obj2 = obj;
                            bArr2 = bArr;
                            i17 = i3;
                            zzanVar2 = zzanVar;
                            i23 = i7;
                            break;
                    }
                } else {
                    i5 = i25;
                    int i31 = i24;
                    unsafe2 = unsafe3;
                    if (iZzx != 27) {
                        z2 = true;
                        i10 = iZzu;
                        int i32 = i20;
                        int i33 = i23;
                        if (iZzx <= 49) {
                            i9 = -1;
                            i15 = i33;
                            unsafe = unsafe2;
                            i8 = i32;
                            iZzi = zzs(obj, bArr, i31, i3, i4, i5, i26, i10, i27, iZzx, j2, zzanVar);
                            if (iZzi == i31) {
                                i7 = i15;
                                i6 = iZzi;
                            }
                        } else {
                            i15 = i33;
                            i8 = i32;
                            unsafe = unsafe2;
                            i16 = i31;
                            i9 = -1;
                            if (iZzx != 50) {
                                iZzi = zzr(obj, bArr, i16, i3, i4, i5, i26, i27, iZzx, j2, i10, zzanVar);
                            } else if (i26 == 2) {
                                iZzi = zzq(obj, bArr, i16, i3, i10, j2, zzanVar);
                            }
                        }
                        zzdiVar = this;
                        obj2 = obj;
                        bArr2 = bArr;
                        i23 = i15;
                        i17 = i3;
                        zzanVar2 = zzanVar;
                    } else if (i26 == 2) {
                        zzcf zzcfVarZzd = (zzcf) unsafe2.getObject(obj2, j2);
                        if (!zzcfVarZzd.zzc()) {
                            int size = zzcfVarZzd.size();
                            zzcfVarZzd = zzcfVarZzd.zzd(size == 0 ? 10 : size + size);
                            unsafe2.putObject(obj2, j2, zzcfVarZzd);
                        }
                        iZzi = zzao.zze(zzdiVar.zzB(iZzu), i4, bArr, i31, i3, zzcfVarZzd, zzanVar);
                        bArr2 = bArr;
                        i23 = i23;
                        i20 = i20;
                        i19 = 1048575;
                        z4 = true;
                        i22 = iZzu;
                        i21 = i5;
                        i18 = -1;
                        i17 = i3;
                        zzanVar2 = zzanVar;
                    } else {
                        i10 = iZzu;
                        z2 = true;
                        i15 = i23;
                        i8 = i20;
                        unsafe = unsafe2;
                        i16 = i31;
                        i9 = -1;
                    }
                    i7 = i15;
                    i6 = i16;
                    iZzi = zzao.zzi(i4, bArr, i6, i3, zzd(obj), zzanVar);
                    zzdiVar = this;
                    obj2 = obj;
                    bArr2 = bArr;
                    i17 = i3;
                    zzanVar2 = zzanVar;
                    i23 = i7;
                }
                i18 = i9;
                z4 = z2;
                i22 = i10;
                i21 = i5;
                unsafe2 = unsafe;
                i20 = i8;
                i19 = 1048575;
            }
            iZzi = zzao.zzi(i4, bArr, i6, i3, zzd(obj), zzanVar);
            zzdiVar = this;
            obj2 = obj;
            bArr2 = bArr;
            i17 = i3;
            zzanVar2 = zzanVar;
            i23 = i7;
            i18 = i9;
            z4 = z2;
            i22 = i10;
            i21 = i5;
            unsafe2 = unsafe;
            i20 = i8;
            i19 = 1048575;
        }
        int i34 = i23;
        int i35 = i20;
        Unsafe unsafe4 = unsafe2;
        if (i35 != i19) {
            unsafe4.putInt(obj, i35, i34);
        }
        if (iZzi != i3) {
            throw zzci.zze();
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.google.android.gms.internal.play_billing.zzdp
    public final void zzi(Object obj, zzey zzeyVar) {
        int i2;
        int i3;
        int i4;
        zzew zzewVar = zzew.zza;
        int i5 = 0;
        int i6 = 1048575;
        if (this.zzo - 1 != 0) {
            if (this.zzh) {
                this.zzn.zza(obj);
                throw null;
            }
            int length = this.zzc.length;
            for (int i7 = 0; i7 < length; i7 += 3) {
                int iZzy = zzy(i7);
                int i8 = this.zzc[i7];
                switch (zzx(iZzy)) {
                    case 0:
                        if (zzP(obj, i7)) {
                            zzeyVar.zzf(i8, zzeq.zza(obj, iZzy & 1048575));
                        }
                        break;
                    case 1:
                        if (zzP(obj, i7)) {
                            zzeyVar.zzo(i8, zzeq.zzb(obj, iZzy & 1048575));
                        }
                        break;
                    case 2:
                        if (zzP(obj, i7)) {
                            zzeyVar.zzt(i8, zzeq.zzd(obj, iZzy & 1048575));
                        }
                        break;
                    case 3:
                        if (zzP(obj, i7)) {
                            zzeyVar.zzJ(i8, zzeq.zzd(obj, iZzy & 1048575));
                        }
                        break;
                    case 4:
                        if (zzP(obj, i7)) {
                            zzeyVar.zzr(i8, zzeq.zzc(obj, iZzy & 1048575));
                        }
                        break;
                    case 5:
                        if (zzP(obj, i7)) {
                            zzeyVar.zzm(i8, zzeq.zzd(obj, iZzy & 1048575));
                        }
                        break;
                    case 6:
                        if (zzP(obj, i7)) {
                            zzeyVar.zzk(i8, zzeq.zzc(obj, iZzy & 1048575));
                        }
                        break;
                    case 7:
                        if (zzP(obj, i7)) {
                            zzeyVar.zzb(i8, zzeq.zzw(obj, iZzy & 1048575));
                        }
                        break;
                    case 8:
                        if (zzP(obj, i7)) {
                            zzV(i8, zzeq.zzf(obj, iZzy & 1048575), zzeyVar);
                        }
                        break;
                    case 9:
                        if (zzP(obj, i7)) {
                            zzeyVar.zzv(i8, zzeq.zzf(obj, iZzy & 1048575), zzB(i7));
                        }
                        break;
                    case 10:
                        if (zzP(obj, i7)) {
                            zzeyVar.zzd(i8, (zzba) zzeq.zzf(obj, iZzy & 1048575));
                        }
                        break;
                    case 11:
                        if (zzP(obj, i7)) {
                            zzeyVar.zzH(i8, zzeq.zzc(obj, iZzy & 1048575));
                        }
                        break;
                    case 12:
                        if (zzP(obj, i7)) {
                            zzeyVar.zzi(i8, zzeq.zzc(obj, iZzy & 1048575));
                        }
                        break;
                    case 13:
                        if (zzP(obj, i7)) {
                            zzeyVar.zzw(i8, zzeq.zzc(obj, iZzy & 1048575));
                        }
                        break;
                    case 14:
                        if (zzP(obj, i7)) {
                            zzeyVar.zzy(i8, zzeq.zzd(obj, iZzy & 1048575));
                        }
                        break;
                    case 15:
                        if (zzP(obj, i7)) {
                            zzeyVar.zzA(i8, zzeq.zzc(obj, iZzy & 1048575));
                        }
                        break;
                    case 16:
                        if (zzP(obj, i7)) {
                            zzeyVar.zzC(i8, zzeq.zzd(obj, iZzy & 1048575));
                        }
                        break;
                    case 17:
                        if (zzP(obj, i7)) {
                            zzeyVar.zzq(i8, zzeq.zzf(obj, iZzy & 1048575), zzB(i7));
                        }
                        break;
                    case 18:
                        zzdr.zzF(i8, (List) zzeq.zzf(obj, iZzy & 1048575), zzeyVar, false);
                        break;
                    case 19:
                        zzdr.zzJ(i8, (List) zzeq.zzf(obj, iZzy & 1048575), zzeyVar, false);
                        break;
                    case 20:
                        zzdr.zzM(i8, (List) zzeq.zzf(obj, iZzy & 1048575), zzeyVar, false);
                        break;
                    case Decal.C4 /* 21 */:
                        zzdr.zzU(i8, (List) zzeq.zzf(obj, iZzy & 1048575), zzeyVar, false);
                        break;
                    case Decal.U4 /* 22 */:
                        zzdr.zzL(i8, (List) zzeq.zzf(obj, iZzy & 1048575), zzeyVar, false);
                        break;
                    case Decal.V4 /* 23 */:
                        zzdr.zzI(i8, (List) zzeq.zzf(obj, iZzy & 1048575), zzeyVar, false);
                        break;
                    case Decal.SIZE /* 24 */:
                        zzdr.zzH(i8, (List) zzeq.zzf(obj, iZzy & 1048575), zzeyVar, false);
                        break;
                    case 25:
                        zzdr.zzD(i8, (List) zzeq.zzf(obj, iZzy & 1048575), zzeyVar, false);
                        break;
                    case 26:
                        zzdr.zzS(i8, (List) zzeq.zzf(obj, iZzy & 1048575), zzeyVar);
                        break;
                    case 27:
                        zzdr.zzN(i8, (List) zzeq.zzf(obj, iZzy & 1048575), zzeyVar, zzB(i7));
                        break;
                    case 28:
                        zzdr.zzE(i8, (List) zzeq.zzf(obj, iZzy & 1048575), zzeyVar);
                        break;
                    case 29:
                        zzdr.zzT(i8, (List) zzeq.zzf(obj, iZzy & 1048575), zzeyVar, false);
                        break;
                    case 30:
                        zzdr.zzG(i8, (List) zzeq.zzf(obj, iZzy & 1048575), zzeyVar, false);
                        break;
                    case 31:
                        zzdr.zzO(i8, (List) zzeq.zzf(obj, iZzy & 1048575), zzeyVar, false);
                        break;
                    case 32:
                        zzdr.zzP(i8, (List) zzeq.zzf(obj, iZzy & 1048575), zzeyVar, false);
                        break;
                    case 33:
                        zzdr.zzQ(i8, (List) zzeq.zzf(obj, iZzy & 1048575), zzeyVar, false);
                        break;
                    case 34:
                        zzdr.zzR(i8, (List) zzeq.zzf(obj, iZzy & 1048575), zzeyVar, false);
                        break;
                    case 35:
                        zzdr.zzF(i8, (List) zzeq.zzf(obj, iZzy & 1048575), zzeyVar, true);
                        break;
                    case 36:
                        zzdr.zzJ(i8, (List) zzeq.zzf(obj, iZzy & 1048575), zzeyVar, true);
                        break;
                    case 37:
                        zzdr.zzM(i8, (List) zzeq.zzf(obj, iZzy & 1048575), zzeyVar, true);
                        break;
                    case 38:
                        zzdr.zzU(i8, (List) zzeq.zzf(obj, iZzy & 1048575), zzeyVar, true);
                        break;
                    case 39:
                        zzdr.zzL(i8, (List) zzeq.zzf(obj, iZzy & 1048575), zzeyVar, true);
                        break;
                    case 40:
                        zzdr.zzI(i8, (List) zzeq.zzf(obj, iZzy & 1048575), zzeyVar, true);
                        break;
                    case 41:
                        zzdr.zzH(i8, (List) zzeq.zzf(obj, iZzy & 1048575), zzeyVar, true);
                        break;
                    case 42:
                        zzdr.zzD(i8, (List) zzeq.zzf(obj, iZzy & 1048575), zzeyVar, true);
                        break;
                    case 43:
                        zzdr.zzT(i8, (List) zzeq.zzf(obj, iZzy & 1048575), zzeyVar, true);
                        break;
                    case 44:
                        zzdr.zzG(i8, (List) zzeq.zzf(obj, iZzy & 1048575), zzeyVar, true);
                        break;
                    case 45:
                        zzdr.zzO(i8, (List) zzeq.zzf(obj, iZzy & 1048575), zzeyVar, true);
                        break;
                    case 46:
                        zzdr.zzP(i8, (List) zzeq.zzf(obj, iZzy & 1048575), zzeyVar, true);
                        break;
                    case 47:
                        zzdr.zzQ(i8, (List) zzeq.zzf(obj, iZzy & 1048575), zzeyVar, true);
                        break;
                    case 48:
                        zzdr.zzR(i8, (List) zzeq.zzf(obj, iZzy & 1048575), zzeyVar, true);
                        break;
                    case 49:
                        zzdr.zzK(i8, (List) zzeq.zzf(obj, iZzy & 1048575), zzeyVar, zzB(i7));
                        break;
                    case 50:
                        zzN(zzeyVar, i8, zzeq.zzf(obj, iZzy & 1048575), i7);
                        break;
                    case 51:
                        if (zzT(obj, i8, i7)) {
                            zzeyVar.zzf(i8, zzm(obj, iZzy & 1048575));
                        }
                        break;
                    case 52:
                        if (zzT(obj, i8, i7)) {
                            zzeyVar.zzo(i8, zzn(obj, iZzy & 1048575));
                        }
                        break;
                    case 53:
                        if (zzT(obj, i8, i7)) {
                            zzeyVar.zzt(i8, zzz(obj, iZzy & 1048575));
                        }
                        break;
                    case 54:
                        if (zzT(obj, i8, i7)) {
                            zzeyVar.zzJ(i8, zzz(obj, iZzy & 1048575));
                        }
                        break;
                    case 55:
                        if (zzT(obj, i8, i7)) {
                            zzeyVar.zzr(i8, zzp(obj, iZzy & 1048575));
                        }
                        break;
                    case 56:
                        if (zzT(obj, i8, i7)) {
                            zzeyVar.zzm(i8, zzz(obj, iZzy & 1048575));
                        }
                        break;
                    case 57:
                        if (zzT(obj, i8, i7)) {
                            zzeyVar.zzk(i8, zzp(obj, iZzy & 1048575));
                        }
                        break;
                    case 58:
                        if (zzT(obj, i8, i7)) {
                            zzeyVar.zzb(i8, zzU(obj, iZzy & 1048575));
                        }
                        break;
                    case 59:
                        if (zzT(obj, i8, i7)) {
                            zzV(i8, zzeq.zzf(obj, iZzy & 1048575), zzeyVar);
                        }
                        break;
                    case 60:
                        if (zzT(obj, i8, i7)) {
                            zzeyVar.zzv(i8, zzeq.zzf(obj, iZzy & 1048575), zzB(i7));
                        }
                        break;
                    case 61:
                        if (zzT(obj, i8, i7)) {
                            zzeyVar.zzd(i8, (zzba) zzeq.zzf(obj, iZzy & 1048575));
                        }
                        break;
                    case 62:
                        if (zzT(obj, i8, i7)) {
                            zzeyVar.zzH(i8, zzp(obj, iZzy & 1048575));
                        }
                        break;
                    case Notifications.NOTIFICATION_TYPES_ALL /* 63 */:
                        if (zzT(obj, i8, i7)) {
                            zzeyVar.zzi(i8, zzp(obj, iZzy & 1048575));
                        }
                        break;
                    case VertexAttributes.Usage.BoneWeight /* 64 */:
                        if (zzT(obj, i8, i7)) {
                            zzeyVar.zzw(i8, zzp(obj, iZzy & 1048575));
                        }
                        break;
                    case 65:
                        if (zzT(obj, i8, i7)) {
                            zzeyVar.zzy(i8, zzz(obj, iZzy & 1048575));
                        }
                        break;
                    case 66:
                        if (zzT(obj, i8, i7)) {
                            zzeyVar.zzA(i8, zzp(obj, iZzy & 1048575));
                        }
                        break;
                    case 67:
                        if (zzT(obj, i8, i7)) {
                            zzeyVar.zzC(i8, zzz(obj, iZzy & 1048575));
                        }
                        break;
                    case 68:
                        if (zzT(obj, i8, i7)) {
                            zzeyVar.zzq(i8, zzeq.zzf(obj, iZzy & 1048575), zzB(i7));
                        }
                        break;
                }
            }
            zzeg zzegVar = this.zzm;
            zzegVar.zzi(zzegVar.zzd(obj), zzeyVar);
            return;
        }
        if (this.zzh) {
            this.zzn.zza(obj);
            throw null;
        }
        int length2 = this.zzc.length;
        Unsafe unsafe = zzb;
        int i9 = 0;
        int i10 = 0;
        int i11 = 1048575;
        while (i9 < length2) {
            int iZzy2 = zzy(i9);
            int[] iArr = this.zzc;
            int i12 = iArr[i9];
            int iZzx = zzx(iZzy2);
            if (iZzx <= 17) {
                int i13 = iArr[i9 + 2];
                int i14 = i13 & i6;
                if (i14 != i11) {
                    i10 = unsafe.getInt(obj, i14);
                    i11 = i14;
                }
                i2 = 1 << (i13 >>> 20);
            } else {
                i2 = i5;
            }
            long j2 = iZzy2 & i6;
            switch (iZzx) {
                case 0:
                    i3 = 0;
                    if ((i10 & i2) != 0) {
                        zzeyVar.zzf(i12, zzeq.zza(obj, j2));
                    }
                    break;
                case 1:
                    i3 = 0;
                    if ((i10 & i2) != 0) {
                        zzeyVar.zzo(i12, zzeq.zzb(obj, j2));
                    }
                    break;
                case 2:
                    i3 = 0;
                    if ((i10 & i2) != 0) {
                        zzeyVar.zzt(i12, unsafe.getLong(obj, j2));
                    }
                    break;
                case 3:
                    i3 = 0;
                    if ((i10 & i2) != 0) {
                        zzeyVar.zzJ(i12, unsafe.getLong(obj, j2));
                    }
                    break;
                case 4:
                    i3 = 0;
                    if ((i10 & i2) != 0) {
                        zzeyVar.zzr(i12, unsafe.getInt(obj, j2));
                    }
                    break;
                case 5:
                    i3 = 0;
                    if ((i10 & i2) != 0) {
                        zzeyVar.zzm(i12, unsafe.getLong(obj, j2));
                    }
                    break;
                case 6:
                    i3 = 0;
                    if ((i10 & i2) != 0) {
                        zzeyVar.zzk(i12, unsafe.getInt(obj, j2));
                    }
                    break;
                case 7:
                    i3 = 0;
                    if ((i10 & i2) != 0) {
                        zzeyVar.zzb(i12, zzeq.zzw(obj, j2));
                    }
                    break;
                case 8:
                    i3 = 0;
                    if ((i10 & i2) != 0) {
                        zzV(i12, unsafe.getObject(obj, j2), zzeyVar);
                    }
                    break;
                case 9:
                    i3 = 0;
                    if ((i10 & i2) != 0) {
                        zzeyVar.zzv(i12, unsafe.getObject(obj, j2), zzB(i9));
                    }
                    break;
                case 10:
                    i3 = 0;
                    if ((i10 & i2) != 0) {
                        zzeyVar.zzd(i12, (zzba) unsafe.getObject(obj, j2));
                    }
                    break;
                case 11:
                    i3 = 0;
                    if ((i10 & i2) != 0) {
                        zzeyVar.zzH(i12, unsafe.getInt(obj, j2));
                    }
                    break;
                case 12:
                    i3 = 0;
                    if ((i10 & i2) != 0) {
                        zzeyVar.zzi(i12, unsafe.getInt(obj, j2));
                    }
                    break;
                case 13:
                    i3 = 0;
                    if ((i10 & i2) != 0) {
                        zzeyVar.zzw(i12, unsafe.getInt(obj, j2));
                    }
                    break;
                case 14:
                    i3 = 0;
                    if ((i10 & i2) != 0) {
                        zzeyVar.zzy(i12, unsafe.getLong(obj, j2));
                    }
                    break;
                case 15:
                    i3 = 0;
                    if ((i10 & i2) != 0) {
                        zzeyVar.zzA(i12, unsafe.getInt(obj, j2));
                    }
                    break;
                case 16:
                    i3 = 0;
                    if ((i10 & i2) != 0) {
                        zzeyVar.zzC(i12, unsafe.getLong(obj, j2));
                    }
                    break;
                case 17:
                    i3 = 0;
                    if ((i10 & i2) != 0) {
                        zzeyVar.zzq(i12, unsafe.getObject(obj, j2), zzB(i9));
                    }
                    break;
                case 18:
                    i3 = 0;
                    zzdr.zzF(this.zzc[i9], (List) unsafe.getObject(obj, j2), zzeyVar, false);
                    break;
                case 19:
                    i3 = 0;
                    zzdr.zzJ(this.zzc[i9], (List) unsafe.getObject(obj, j2), zzeyVar, false);
                    break;
                case 20:
                    i3 = 0;
                    zzdr.zzM(this.zzc[i9], (List) unsafe.getObject(obj, j2), zzeyVar, false);
                    break;
                case Decal.C4 /* 21 */:
                    i3 = 0;
                    zzdr.zzU(this.zzc[i9], (List) unsafe.getObject(obj, j2), zzeyVar, false);
                    break;
                case Decal.U4 /* 22 */:
                    i3 = 0;
                    zzdr.zzL(this.zzc[i9], (List) unsafe.getObject(obj, j2), zzeyVar, false);
                    break;
                case Decal.V4 /* 23 */:
                    i3 = 0;
                    zzdr.zzI(this.zzc[i9], (List) unsafe.getObject(obj, j2), zzeyVar, false);
                    break;
                case Decal.SIZE /* 24 */:
                    i3 = 0;
                    zzdr.zzH(this.zzc[i9], (List) unsafe.getObject(obj, j2), zzeyVar, false);
                    break;
                case 25:
                    i3 = 0;
                    zzdr.zzD(this.zzc[i9], (List) unsafe.getObject(obj, j2), zzeyVar, false);
                    break;
                case 26:
                    zzdr.zzS(this.zzc[i9], (List) unsafe.getObject(obj, j2), zzeyVar);
                    i3 = 0;
                    break;
                case 27:
                    zzdr.zzN(this.zzc[i9], (List) unsafe.getObject(obj, j2), zzeyVar, zzB(i9));
                    i3 = 0;
                    break;
                case 28:
                    zzdr.zzE(this.zzc[i9], (List) unsafe.getObject(obj, j2), zzeyVar);
                    i3 = 0;
                    break;
                case 29:
                    i4 = 0;
                    zzdr.zzT(this.zzc[i9], (List) unsafe.getObject(obj, j2), zzeyVar, false);
                    i3 = i4;
                    break;
                case 30:
                    i4 = 0;
                    zzdr.zzG(this.zzc[i9], (List) unsafe.getObject(obj, j2), zzeyVar, false);
                    i3 = i4;
                    break;
                case 31:
                    i4 = 0;
                    zzdr.zzO(this.zzc[i9], (List) unsafe.getObject(obj, j2), zzeyVar, false);
                    i3 = i4;
                    break;
                case 32:
                    i4 = 0;
                    zzdr.zzP(this.zzc[i9], (List) unsafe.getObject(obj, j2), zzeyVar, false);
                    i3 = i4;
                    break;
                case 33:
                    i4 = 0;
                    zzdr.zzQ(this.zzc[i9], (List) unsafe.getObject(obj, j2), zzeyVar, false);
                    i3 = i4;
                    break;
                case 34:
                    i4 = 0;
                    zzdr.zzR(this.zzc[i9], (List) unsafe.getObject(obj, j2), zzeyVar, false);
                    i3 = i4;
                    break;
                case 35:
                    zzdr.zzF(this.zzc[i9], (List) unsafe.getObject(obj, j2), zzeyVar, true);
                    i3 = 0;
                    break;
                case 36:
                    zzdr.zzJ(this.zzc[i9], (List) unsafe.getObject(obj, j2), zzeyVar, true);
                    i3 = 0;
                    break;
                case 37:
                    zzdr.zzM(this.zzc[i9], (List) unsafe.getObject(obj, j2), zzeyVar, true);
                    i3 = 0;
                    break;
                case 38:
                    zzdr.zzU(this.zzc[i9], (List) unsafe.getObject(obj, j2), zzeyVar, true);
                    i3 = 0;
                    break;
                case 39:
                    zzdr.zzL(this.zzc[i9], (List) unsafe.getObject(obj, j2), zzeyVar, true);
                    i3 = 0;
                    break;
                case 40:
                    zzdr.zzI(this.zzc[i9], (List) unsafe.getObject(obj, j2), zzeyVar, true);
                    i3 = 0;
                    break;
                case 41:
                    zzdr.zzH(this.zzc[i9], (List) unsafe.getObject(obj, j2), zzeyVar, true);
                    i3 = 0;
                    break;
                case 42:
                    zzdr.zzD(this.zzc[i9], (List) unsafe.getObject(obj, j2), zzeyVar, true);
                    i3 = 0;
                    break;
                case 43:
                    zzdr.zzT(this.zzc[i9], (List) unsafe.getObject(obj, j2), zzeyVar, true);
                    i3 = 0;
                    break;
                case 44:
                    zzdr.zzG(this.zzc[i9], (List) unsafe.getObject(obj, j2), zzeyVar, true);
                    i3 = 0;
                    break;
                case 45:
                    zzdr.zzO(this.zzc[i9], (List) unsafe.getObject(obj, j2), zzeyVar, true);
                    i3 = 0;
                    break;
                case 46:
                    zzdr.zzP(this.zzc[i9], (List) unsafe.getObject(obj, j2), zzeyVar, true);
                    i3 = 0;
                    break;
                case 47:
                    zzdr.zzQ(this.zzc[i9], (List) unsafe.getObject(obj, j2), zzeyVar, true);
                    i3 = 0;
                    break;
                case 48:
                    zzdr.zzR(this.zzc[i9], (List) unsafe.getObject(obj, j2), zzeyVar, true);
                    i3 = 0;
                    break;
                case 49:
                    zzdr.zzK(this.zzc[i9], (List) unsafe.getObject(obj, j2), zzeyVar, zzB(i9));
                    i3 = 0;
                    break;
                case 50:
                    zzN(zzeyVar, i12, unsafe.getObject(obj, j2), i9);
                    i3 = 0;
                    break;
                case 51:
                    if (zzT(obj, i12, i9)) {
                        zzeyVar.zzf(i12, zzm(obj, j2));
                    }
                    i3 = 0;
                    break;
                case 52:
                    if (zzT(obj, i12, i9)) {
                        zzeyVar.zzo(i12, zzn(obj, j2));
                    }
                    i3 = 0;
                    break;
                case 53:
                    if (zzT(obj, i12, i9)) {
                        zzeyVar.zzt(i12, zzz(obj, j2));
                    }
                    i3 = 0;
                    break;
                case 54:
                    if (zzT(obj, i12, i9)) {
                        zzeyVar.zzJ(i12, zzz(obj, j2));
                    }
                    i3 = 0;
                    break;
                case 55:
                    if (zzT(obj, i12, i9)) {
                        zzeyVar.zzr(i12, zzp(obj, j2));
                    }
                    i3 = 0;
                    break;
                case 56:
                    if (zzT(obj, i12, i9)) {
                        zzeyVar.zzm(i12, zzz(obj, j2));
                    }
                    i3 = 0;
                    break;
                case 57:
                    if (zzT(obj, i12, i9)) {
                        zzeyVar.zzk(i12, zzp(obj, j2));
                    }
                    i3 = 0;
                    break;
                case 58:
                    if (zzT(obj, i12, i9)) {
                        zzeyVar.zzb(i12, zzU(obj, j2));
                    }
                    i3 = 0;
                    break;
                case 59:
                    if (zzT(obj, i12, i9)) {
                        zzV(i12, unsafe.getObject(obj, j2), zzeyVar);
                    }
                    i3 = 0;
                    break;
                case 60:
                    if (zzT(obj, i12, i9)) {
                        zzeyVar.zzv(i12, unsafe.getObject(obj, j2), zzB(i9));
                    }
                    i3 = 0;
                    break;
                case 61:
                    if (zzT(obj, i12, i9)) {
                        zzeyVar.zzd(i12, (zzba) unsafe.getObject(obj, j2));
                    }
                    i3 = 0;
                    break;
                case 62:
                    if (zzT(obj, i12, i9)) {
                        zzeyVar.zzH(i12, zzp(obj, j2));
                    }
                    i3 = 0;
                    break;
                case Notifications.NOTIFICATION_TYPES_ALL /* 63 */:
                    if (zzT(obj, i12, i9)) {
                        zzeyVar.zzi(i12, zzp(obj, j2));
                    }
                    i3 = 0;
                    break;
                case VertexAttributes.Usage.BoneWeight /* 64 */:
                    if (zzT(obj, i12, i9)) {
                        zzeyVar.zzw(i12, zzp(obj, j2));
                    }
                    i3 = 0;
                    break;
                case 65:
                    if (zzT(obj, i12, i9)) {
                        zzeyVar.zzy(i12, zzz(obj, j2));
                    }
                    i3 = 0;
                    break;
                case 66:
                    if (zzT(obj, i12, i9)) {
                        zzeyVar.zzA(i12, zzp(obj, j2));
                    }
                    i3 = 0;
                    break;
                case 67:
                    if (zzT(obj, i12, i9)) {
                        zzeyVar.zzC(i12, zzz(obj, j2));
                    }
                    i3 = 0;
                    break;
                case 68:
                    if (zzT(obj, i12, i9)) {
                        zzeyVar.zzq(i12, unsafe.getObject(obj, j2), zzB(i9));
                    }
                    i3 = 0;
                    break;
                default:
                    i3 = 0;
                    break;
            }
            i9 += 3;
            i5 = i3;
            i6 = 1048575;
        }
        zzeg zzegVar2 = this.zzm;
        zzegVar2.zzi(zzegVar2.zzd(obj), zzeyVar);
    }

    @Override // com.google.android.gms.internal.play_billing.zzdp
    public final boolean zzj(Object obj, Object obj2) {
        boolean zZzV;
        int length = this.zzc.length;
        for (int i2 = 0; i2 < length; i2 += 3) {
            int iZzy = zzy(i2);
            long j2 = iZzy & 1048575;
            switch (zzx(iZzy)) {
                case 0:
                    if (!zzO(obj, obj2, i2) || Double.doubleToLongBits(zzeq.zza(obj, j2)) != Double.doubleToLongBits(zzeq.zza(obj2, j2))) {
                        return false;
                    }
                    continue;
                    break;
                    break;
                case 1:
                    if (!zzO(obj, obj2, i2) || Float.floatToIntBits(zzeq.zzb(obj, j2)) != Float.floatToIntBits(zzeq.zzb(obj2, j2))) {
                        return false;
                    }
                    continue;
                    break;
                    break;
                case 2:
                    if (!zzO(obj, obj2, i2) || zzeq.zzd(obj, j2) != zzeq.zzd(obj2, j2)) {
                        return false;
                    }
                    continue;
                    break;
                    break;
                case 3:
                    if (!zzO(obj, obj2, i2) || zzeq.zzd(obj, j2) != zzeq.zzd(obj2, j2)) {
                        return false;
                    }
                    continue;
                    break;
                    break;
                case 4:
                    if (!zzO(obj, obj2, i2) || zzeq.zzc(obj, j2) != zzeq.zzc(obj2, j2)) {
                        return false;
                    }
                    continue;
                    break;
                    break;
                case 5:
                    if (!zzO(obj, obj2, i2) || zzeq.zzd(obj, j2) != zzeq.zzd(obj2, j2)) {
                        return false;
                    }
                    continue;
                    break;
                    break;
                case 6:
                    if (!zzO(obj, obj2, i2) || zzeq.zzc(obj, j2) != zzeq.zzc(obj2, j2)) {
                        return false;
                    }
                    continue;
                    break;
                    break;
                case 7:
                    if (!zzO(obj, obj2, i2) || zzeq.zzw(obj, j2) != zzeq.zzw(obj2, j2)) {
                        return false;
                    }
                    continue;
                    break;
                    break;
                case 8:
                    if (!zzO(obj, obj2, i2) || !zzdr.zzV(zzeq.zzf(obj, j2), zzeq.zzf(obj2, j2))) {
                        return false;
                    }
                    continue;
                    break;
                    break;
                case 9:
                    if (!zzO(obj, obj2, i2) || !zzdr.zzV(zzeq.zzf(obj, j2), zzeq.zzf(obj2, j2))) {
                        return false;
                    }
                    continue;
                    break;
                    break;
                case 10:
                    if (!zzO(obj, obj2, i2) || !zzdr.zzV(zzeq.zzf(obj, j2), zzeq.zzf(obj2, j2))) {
                        return false;
                    }
                    continue;
                    break;
                    break;
                case 11:
                    if (!zzO(obj, obj2, i2) || zzeq.zzc(obj, j2) != zzeq.zzc(obj2, j2)) {
                        return false;
                    }
                    continue;
                    break;
                    break;
                case 12:
                    if (!zzO(obj, obj2, i2) || zzeq.zzc(obj, j2) != zzeq.zzc(obj2, j2)) {
                        return false;
                    }
                    continue;
                    break;
                    break;
                case 13:
                    if (!zzO(obj, obj2, i2) || zzeq.zzc(obj, j2) != zzeq.zzc(obj2, j2)) {
                        return false;
                    }
                    continue;
                    break;
                    break;
                case 14:
                    if (!zzO(obj, obj2, i2) || zzeq.zzd(obj, j2) != zzeq.zzd(obj2, j2)) {
                        return false;
                    }
                    continue;
                    break;
                    break;
                case 15:
                    if (!zzO(obj, obj2, i2) || zzeq.zzc(obj, j2) != zzeq.zzc(obj2, j2)) {
                        return false;
                    }
                    continue;
                    break;
                    break;
                case 16:
                    if (!zzO(obj, obj2, i2) || zzeq.zzd(obj, j2) != zzeq.zzd(obj2, j2)) {
                        return false;
                    }
                    continue;
                    break;
                    break;
                case 17:
                    if (!zzO(obj, obj2, i2) || !zzdr.zzV(zzeq.zzf(obj, j2), zzeq.zzf(obj2, j2))) {
                        return false;
                    }
                    continue;
                    break;
                    break;
                case 18:
                case 19:
                case 20:
                case Decal.C4 /* 21 */:
                case Decal.U4 /* 22 */:
                case Decal.V4 /* 23 */:
                case Decal.SIZE /* 24 */:
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case 48:
                case 49:
                    zZzV = zzdr.zzV(zzeq.zzf(obj, j2), zzeq.zzf(obj2, j2));
                    break;
                case 50:
                    zZzV = zzdr.zzV(zzeq.zzf(obj, j2), zzeq.zzf(obj2, j2));
                    break;
                case 51:
                case 52:
                case 53:
                case 54:
                case 55:
                case 56:
                case 57:
                case 58:
                case 59:
                case 60:
                case 61:
                case 62:
                case Notifications.NOTIFICATION_TYPES_ALL /* 63 */:
                case VertexAttributes.Usage.BoneWeight /* 64 */:
                case 65:
                case 66:
                case 67:
                case 68:
                    long jZzv = zzv(i2) & 1048575;
                    if (zzeq.zzc(obj, jZzv) != zzeq.zzc(obj2, jZzv) || !zzdr.zzV(zzeq.zzf(obj, j2), zzeq.zzf(obj2, j2))) {
                        return false;
                    }
                    continue;
                    break;
                    break;
                default:
                    break;
            }
            if (!zZzV) {
                return false;
            }
        }
        if (!this.zzm.zzd(obj).equals(this.zzm.zzd(obj2))) {
            return false;
        }
        if (!this.zzh) {
            return true;
        }
        this.zzn.zza(obj);
        this.zzn.zza(obj2);
        throw null;
    }

    /* JADX WARN: Removed duplicated region for block: B:42:0x009e  */
    @Override // com.google.android.gms.internal.play_billing.zzdp
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final boolean zzk(Object obj) {
        int i2;
        int i3;
        int i4 = 0;
        int i5 = 0;
        int i6 = 1048575;
        while (i5 < this.zzj) {
            int i7 = this.zzi[i5];
            int i8 = this.zzc[i7];
            int iZzy = zzy(i7);
            int i9 = this.zzc[i7 + 2];
            int i10 = i9 & 1048575;
            int i11 = 1 << (i9 >>> 20);
            if (i10 != i6) {
                if (i10 != 1048575) {
                    i4 = zzb.getInt(obj, i10);
                }
                i3 = i4;
                i2 = i10;
            } else {
                i2 = i6;
                i3 = i4;
            }
            if ((268435456 & iZzy) != 0 && !zzQ(obj, i7, i2, i3, i11)) {
                return false;
            }
            int iZzx = zzx(iZzy);
            if (iZzx == 9 || iZzx == 17) {
                if (zzQ(obj, i7, i2, i3, i11) && !zzR(obj, iZzy, zzB(i7))) {
                    return false;
                }
            } else if (iZzx == 27) {
                List list = (List) zzeq.zzf(obj, iZzy & 1048575);
                if (list.isEmpty()) {
                    continue;
                } else {
                    zzdp zzdpVarZzB = zzB(i7);
                    for (int i12 = 0; i12 < list.size(); i12++) {
                        if (!zzdpVarZzB.zzk(list.get(i12))) {
                            return false;
                        }
                    }
                }
            } else if (iZzx == 60 || iZzx == 68) {
                if (zzT(obj, i8, i7) && !zzR(obj, iZzy, zzB(i7))) {
                    return false;
                }
            } else if (iZzx != 49) {
                if (iZzx == 50 && !((zzcz) zzeq.zzf(obj, iZzy & 1048575)).isEmpty()) {
                    throw null;
                }
            }
            i5++;
            i6 = i2;
            i4 = i3;
        }
        if (!this.zzh) {
            return true;
        }
        this.zzn.zza(obj);
        throw null;
    }
}
