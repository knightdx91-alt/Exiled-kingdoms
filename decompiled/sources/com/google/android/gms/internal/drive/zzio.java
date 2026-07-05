package com.google.android.gms.internal.drive;

import com.google.android.gms.common.api.Api;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class zzio {
    private final byte[] buffer;
    private final int zzmn;
    private final int zzmo;
    private int zzmp;
    private int zzmq;
    private int zzmr;
    private int zzms = Api.BaseClientBuilder.API_PRIORITY_OTHER;
    private int zzmt = 64;
    private int zzmu = 67108864;

    private zzio(byte[] bArr, int i2, int i3) {
        this.buffer = bArr;
        this.zzmn = i2;
        int i4 = i3 + i2;
        this.zzmp = i4;
        this.zzmo = i4;
        this.zzmq = i2;
    }

    public static zzio zza(byte[] bArr, int i2, int i3) {
        return new zzio(bArr, 0, i3);
    }

    private final byte zzbg() throws zziw {
        int i2 = this.zzmq;
        if (i2 == this.zzmp) {
            throw zziw.zzbk();
        }
        byte[] bArr = this.buffer;
        this.zzmq = i2 + 1;
        return bArr[i2];
    }

    private final void zzl(int i2) throws zziw {
        if (i2 < 0) {
            throw zziw.zzbl();
        }
        int i3 = this.zzmq;
        int i4 = i3 + i2;
        int i5 = this.zzms;
        if (i4 > i5) {
            zzl(i5 - i3);
            throw zziw.zzbk();
        }
        if (i2 > this.zzmp - i3) {
            throw zziw.zzbk();
        }
        this.zzmq = i3 + i2;
    }

    public final int getPosition() {
        return this.zzmq - this.zzmn;
    }

    public final String readString() throws zziw {
        int iZzbe = zzbe();
        if (iZzbe < 0) {
            throw zziw.zzbl();
        }
        int i2 = this.zzmp;
        int i3 = this.zzmq;
        if (iZzbe > i2 - i3) {
            throw zziw.zzbk();
        }
        String str = new String(this.buffer, i3, iZzbe, zziv.UTF_8);
        this.zzmq += iZzbe;
        return str;
    }

    public final int zzbd() throws zziw {
        if (this.zzmq == this.zzmp) {
            this.zzmr = 0;
            return 0;
        }
        int iZzbe = zzbe();
        this.zzmr = iZzbe;
        if (iZzbe != 0) {
            return iZzbe;
        }
        throw new zziw("Protocol message contained an invalid tag (zero).");
    }

    public final int zzbe() throws zziw {
        int i2;
        byte bZzbg = zzbg();
        if (bZzbg >= 0) {
            return bZzbg;
        }
        int i3 = bZzbg & 127;
        byte bZzbg2 = zzbg();
        if (bZzbg2 >= 0) {
            i2 = bZzbg2 << 7;
        } else {
            i3 |= (bZzbg2 & 127) << 7;
            byte bZzbg3 = zzbg();
            if (bZzbg3 >= 0) {
                i2 = bZzbg3 << 14;
            } else {
                i3 |= (bZzbg3 & 127) << 14;
                byte bZzbg4 = zzbg();
                if (bZzbg4 < 0) {
                    int i4 = i3 | ((bZzbg4 & 127) << 21);
                    byte bZzbg5 = zzbg();
                    int i5 = i4 | (bZzbg5 << 28);
                    if (bZzbg5 >= 0) {
                        return i5;
                    }
                    for (int i6 = 0; i6 < 5; i6++) {
                        if (zzbg() >= 0) {
                            return i5;
                        }
                    }
                    throw zziw.zzbm();
                }
                i2 = bZzbg4 << 21;
            }
        }
        return i3 | i2;
    }

    public final long zzbf() throws zziw {
        long j2 = 0;
        for (int i2 = 0; i2 < 64; i2 += 7) {
            byte bZzbg = zzbg();
            j2 |= ((long) (bZzbg & 127)) << i2;
            if ((bZzbg & 128) == 0) {
                return j2;
            }
        }
        throw zziw.zzbm();
    }

    public final void zzj(int i2) throws zziw {
        if (this.zzmr != i2) {
            throw new zziw("Protocol message end-group tag did not match expected tag.");
        }
    }

    public final boolean zzk(int i2) throws zziw {
        int iZzbd;
        int i3 = i2 & 7;
        if (i3 == 0) {
            zzbe();
            return true;
        }
        if (i3 == 1) {
            zzbg();
            zzbg();
            zzbg();
            zzbg();
            zzbg();
            zzbg();
            zzbg();
            zzbg();
            return true;
        }
        if (i3 == 2) {
            zzl(zzbe());
            return true;
        }
        if (i3 == 3) {
            do {
                iZzbd = zzbd();
                if (iZzbd == 0) {
                    break;
                }
            } while (zzk(iZzbd));
            zzj(((i2 >>> 3) << 3) | 4);
            return true;
        }
        if (i3 == 4) {
            return false;
        }
        if (i3 != 5) {
            throw new zziw("Protocol message tag had invalid wire type.");
        }
        zzbg();
        zzbg();
        zzbg();
        zzbg();
        return true;
    }

    public final byte[] zza(int i2, int i3) {
        if (i3 == 0) {
            return zzja.zzns;
        }
        byte[] bArr = new byte[i3];
        System.arraycopy(this.buffer, this.zzmn + i2, bArr, 0, i3);
        return bArr;
    }
}
