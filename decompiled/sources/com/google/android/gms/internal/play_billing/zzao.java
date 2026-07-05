package com.google.android.gms.internal.play_billing;

/* JADX INFO: compiled from: com.android.billingclient:billing@@6.0.1 */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzao {
    static int zza(byte[] bArr, int i2, zzan zzanVar) {
        int iZzj = zzj(bArr, i2, zzanVar);
        int i3 = zzanVar.zza;
        if (i3 < 0) {
            throw zzci.zzd();
        }
        if (i3 > bArr.length - iZzj) {
            throw zzci.zzg();
        }
        if (i3 == 0) {
            zzanVar.zzc = zzba.zzb;
            return iZzj;
        }
        zzanVar.zzc = zzba.zzl(bArr, iZzj, i3);
        return iZzj + i3;
    }

    static int zzb(byte[] bArr, int i2) {
        int i3 = bArr[i2] & 255;
        int i4 = bArr[i2 + 1] & 255;
        int i5 = bArr[i2 + 2] & 255;
        return ((bArr[i2 + 3] & 255) << 24) | (i4 << 8) | i3 | (i5 << 16);
    }

    static int zzc(zzdp zzdpVar, byte[] bArr, int i2, int i3, int i4, zzan zzanVar) {
        Object objZze = zzdpVar.zze();
        int iZzn = zzn(objZze, zzdpVar, bArr, i2, i3, i4, zzanVar);
        zzdpVar.zzf(objZze);
        zzanVar.zzc = objZze;
        return iZzn;
    }

    static int zzd(zzdp zzdpVar, byte[] bArr, int i2, int i3, zzan zzanVar) {
        Object objZze = zzdpVar.zze();
        int iZzo = zzo(objZze, zzdpVar, bArr, i2, i3, zzanVar);
        zzdpVar.zzf(objZze);
        zzanVar.zzc = objZze;
        return iZzo;
    }

    static int zze(zzdp zzdpVar, int i2, byte[] bArr, int i3, int i4, zzcf zzcfVar, zzan zzanVar) {
        int iZzd = zzd(zzdpVar, bArr, i3, i4, zzanVar);
        zzcfVar.add(zzanVar.zzc);
        while (iZzd < i4) {
            int iZzj = zzj(bArr, iZzd, zzanVar);
            if (i2 != zzanVar.zza) {
                break;
            }
            iZzd = zzd(zzdpVar, bArr, iZzj, i4, zzanVar);
            zzcfVar.add(zzanVar.zzc);
        }
        return iZzd;
    }

    static int zzf(byte[] bArr, int i2, zzcf zzcfVar, zzan zzanVar) {
        zzcc zzccVar = (zzcc) zzcfVar;
        int iZzj = zzj(bArr, i2, zzanVar);
        int i3 = zzanVar.zza + iZzj;
        while (iZzj < i3) {
            iZzj = zzj(bArr, iZzj, zzanVar);
            zzccVar.zzf(zzanVar.zza);
        }
        if (iZzj == i3) {
            return iZzj;
        }
        throw zzci.zzg();
    }

    static int zzg(byte[] bArr, int i2, zzan zzanVar) throws zzci {
        int iZzj = zzj(bArr, i2, zzanVar);
        int i3 = zzanVar.zza;
        if (i3 < 0) {
            throw zzci.zzd();
        }
        if (i3 == 0) {
            zzanVar.zzc = "";
            return iZzj;
        }
        zzanVar.zzc = new String(bArr, iZzj, i3, zzcg.zzb);
        return iZzj + i3;
    }

    static int zzh(byte[] bArr, int i2, zzan zzanVar) throws zzci {
        int iZzj = zzj(bArr, i2, zzanVar);
        int i3 = zzanVar.zza;
        if (i3 < 0) {
            throw zzci.zzd();
        }
        if (i3 == 0) {
            zzanVar.zzc = "";
            return iZzj;
        }
        int i4 = zzev.zza;
        int length = bArr.length;
        if ((((length - iZzj) - i3) | iZzj | i3) < 0) {
            throw new ArrayIndexOutOfBoundsException(String.format("buffer length=%d, index=%d, size=%d", Integer.valueOf(length), Integer.valueOf(iZzj), Integer.valueOf(i3)));
        }
        int i5 = iZzj + i3;
        char[] cArr = new char[i3];
        int i6 = 0;
        while (iZzj < i5) {
            byte b2 = bArr[iZzj];
            if (!zzer.zzd(b2)) {
                break;
            }
            iZzj++;
            cArr[i6] = (char) b2;
            i6++;
        }
        int i7 = i6;
        while (iZzj < i5) {
            int i8 = iZzj + 1;
            byte b3 = bArr[iZzj];
            if (zzer.zzd(b3)) {
                cArr[i7] = (char) b3;
                i7++;
                iZzj = i8;
                while (iZzj < i5) {
                    byte b4 = bArr[iZzj];
                    if (zzer.zzd(b4)) {
                        iZzj++;
                        cArr[i7] = (char) b4;
                        i7++;
                    }
                }
            } else if (b3 < -32) {
                if (i8 >= i5) {
                    throw zzci.zzc();
                }
                iZzj += 2;
                zzer.zzc(b3, bArr[i8], cArr, i7);
                i7++;
            } else if (b3 < -16) {
                if (i8 >= i5 - 1) {
                    throw zzci.zzc();
                }
                int i9 = iZzj + 2;
                iZzj += 3;
                zzer.zzb(b3, bArr[i8], bArr[i9], cArr, i7);
                i7++;
            } else {
                if (i8 >= i5 - 2) {
                    throw zzci.zzc();
                }
                byte b5 = bArr[i8];
                int i10 = iZzj + 3;
                byte b6 = bArr[iZzj + 2];
                iZzj += 4;
                zzer.zza(b3, b5, b6, bArr[i10], cArr, i7);
                i7 += 2;
            }
        }
        zzanVar.zzc = new String(cArr, 0, i7);
        return i5;
    }

    static int zzi(int i2, byte[] bArr, int i3, int i4, zzeh zzehVar, zzan zzanVar) {
        if ((i2 >>> 3) == 0) {
            throw zzci.zzb();
        }
        int i5 = i2 & 7;
        if (i5 == 0) {
            int iZzm = zzm(bArr, i3, zzanVar);
            zzehVar.zzj(i2, Long.valueOf(zzanVar.zzb));
            return iZzm;
        }
        if (i5 == 1) {
            zzehVar.zzj(i2, Long.valueOf(zzp(bArr, i3)));
            return i3 + 8;
        }
        if (i5 == 2) {
            int iZzj = zzj(bArr, i3, zzanVar);
            int i6 = zzanVar.zza;
            if (i6 < 0) {
                throw zzci.zzd();
            }
            if (i6 > bArr.length - iZzj) {
                throw zzci.zzg();
            }
            if (i6 == 0) {
                zzehVar.zzj(i2, zzba.zzb);
            } else {
                zzehVar.zzj(i2, zzba.zzl(bArr, iZzj, i6));
            }
            return iZzj + i6;
        }
        if (i5 != 3) {
            if (i5 != 5) {
                throw zzci.zzb();
            }
            zzehVar.zzj(i2, Integer.valueOf(zzb(bArr, i3)));
            return i3 + 4;
        }
        int i7 = (i2 & (-8)) | 4;
        zzeh zzehVarZzf = zzeh.zzf();
        int i8 = 0;
        while (true) {
            if (i3 >= i4) {
                break;
            }
            int iZzj2 = zzj(bArr, i3, zzanVar);
            int i9 = zzanVar.zza;
            i8 = i9;
            if (i9 == i7) {
                i3 = iZzj2;
                break;
            }
            int iZzi = zzi(i8, bArr, iZzj2, i4, zzehVarZzf, zzanVar);
            i8 = i9;
            i3 = iZzi;
        }
        if (i3 > i4 || i8 != i7) {
            throw zzci.zze();
        }
        zzehVar.zzj(i2, zzehVarZzf);
        return i3;
    }

    static int zzj(byte[] bArr, int i2, zzan zzanVar) {
        int i3 = i2 + 1;
        byte b2 = bArr[i2];
        if (b2 < 0) {
            return zzk(b2, bArr, i3, zzanVar);
        }
        zzanVar.zza = b2;
        return i3;
    }

    static int zzk(int i2, byte[] bArr, int i3, zzan zzanVar) {
        byte b2 = bArr[i3];
        int i4 = i3 + 1;
        int i5 = i2 & 127;
        if (b2 >= 0) {
            zzanVar.zza = i5 | (b2 << 7);
            return i4;
        }
        int i6 = i5 | ((b2 & 127) << 7);
        int i7 = i3 + 2;
        byte b3 = bArr[i4];
        if (b3 >= 0) {
            zzanVar.zza = i6 | (b3 << 14);
            return i7;
        }
        int i8 = i6 | ((b3 & 127) << 14);
        int i9 = i3 + 3;
        byte b4 = bArr[i7];
        if (b4 >= 0) {
            zzanVar.zza = i8 | (b4 << 21);
            return i9;
        }
        int i10 = i8 | ((b4 & 127) << 21);
        int i11 = i3 + 4;
        byte b5 = bArr[i9];
        if (b5 >= 0) {
            zzanVar.zza = i10 | (b5 << 28);
            return i11;
        }
        int i12 = i10 | ((b5 & 127) << 28);
        while (true) {
            int i13 = i11 + 1;
            if (bArr[i11] >= 0) {
                zzanVar.zza = i12;
                return i13;
            }
            i11 = i13;
        }
    }

    static int zzl(int i2, byte[] bArr, int i3, int i4, zzcf zzcfVar, zzan zzanVar) {
        zzcc zzccVar = (zzcc) zzcfVar;
        int iZzj = zzj(bArr, i3, zzanVar);
        zzccVar.zzf(zzanVar.zza);
        while (iZzj < i4) {
            int iZzj2 = zzj(bArr, iZzj, zzanVar);
            if (i2 != zzanVar.zza) {
                break;
            }
            iZzj = zzj(bArr, iZzj2, zzanVar);
            zzccVar.zzf(zzanVar.zza);
        }
        return iZzj;
    }

    static int zzm(byte[] bArr, int i2, zzan zzanVar) {
        long j2 = bArr[i2];
        int i3 = i2 + 1;
        if (j2 >= 0) {
            zzanVar.zzb = j2;
            return i3;
        }
        int i4 = i2 + 2;
        byte b2 = bArr[i3];
        long j3 = (j2 & 127) | (((long) (b2 & 127)) << 7);
        int i5 = 7;
        while (b2 < 0) {
            int i6 = i4 + 1;
            byte b3 = bArr[i4];
            i5 += 7;
            j3 |= ((long) (b3 & 127)) << i5;
            b2 = b3;
            i4 = i6;
        }
        zzanVar.zzb = j3;
        return i4;
    }

    static int zzn(Object obj, zzdp zzdpVar, byte[] bArr, int i2, int i3, int i4, zzan zzanVar) {
        int iZzc = ((zzdi) zzdpVar).zzc(obj, bArr, i2, i3, i4, zzanVar);
        zzanVar.zzc = obj;
        return iZzc;
    }

    static int zzo(Object obj, zzdp zzdpVar, byte[] bArr, int i2, int i3, zzan zzanVar) {
        int iZzk = i2 + 1;
        int i4 = bArr[i2];
        if (i4 < 0) {
            iZzk = zzk(i4, bArr, iZzk, zzanVar);
            i4 = zzanVar.zza;
        }
        int i5 = iZzk;
        if (i4 < 0 || i4 > i3 - i5) {
            throw zzci.zzg();
        }
        int i6 = i4 + i5;
        zzdpVar.zzh(obj, bArr, i5, i6, zzanVar);
        zzanVar.zzc = obj;
        return i6;
    }

    static long zzp(byte[] bArr, int i2) {
        return (((long) bArr[i2]) & 255) | ((((long) bArr[i2 + 1]) & 255) << 8) | ((((long) bArr[i2 + 2]) & 255) << 16) | ((((long) bArr[i2 + 3]) & 255) << 24) | ((((long) bArr[i2 + 4]) & 255) << 32) | ((((long) bArr[i2 + 5]) & 255) << 40) | ((((long) bArr[i2 + 6]) & 255) << 48) | ((((long) bArr[i2 + 7]) & 255) << 56);
    }
}
