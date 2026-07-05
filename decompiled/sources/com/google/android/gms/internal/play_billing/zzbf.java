package com.google.android.gms.internal.play_billing;

import com.badlogic.gdx.graphics.VertexAttributes;

/* JADX INFO: compiled from: com.android.billingclient:billing@@6.0.1 */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzbf extends zzbi {
    private final byte[] zzc;
    private final int zzd;
    private int zze;

    zzbf(byte[] bArr, int i2, int i3) {
        super(null);
        int length = bArr.length;
        if (((length - i3) | i3) < 0) {
            throw new IllegalArgumentException(String.format("Array range is invalid. Buffer.length=%d, offset=%d, length=%d", Integer.valueOf(length), 0, Integer.valueOf(i3)));
        }
        this.zzc = bArr;
        this.zze = 0;
        this.zzd = i3;
    }

    @Override // com.google.android.gms.internal.play_billing.zzbi
    public final int zza() {
        return this.zzd - this.zze;
    }

    @Override // com.google.android.gms.internal.play_billing.zzbi
    public final void zzb(byte b2) throws zzbg {
        try {
            byte[] bArr = this.zzc;
            int i2 = this.zze;
            this.zze = i2 + 1;
            bArr[i2] = b2;
        } catch (IndexOutOfBoundsException e2) {
            throw new zzbg(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.zze), Integer.valueOf(this.zzd), 1), e2);
        }
    }

    public final void zzc(byte[] bArr, int i2, int i3) {
        try {
            System.arraycopy(bArr, 0, this.zzc, this.zze, i3);
            this.zze += i3;
        } catch (IndexOutOfBoundsException e2) {
            throw new zzbg(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.zze), Integer.valueOf(this.zzd), Integer.valueOf(i3)), e2);
        }
    }

    @Override // com.google.android.gms.internal.play_billing.zzbi
    public final void zzd(int i2, boolean z2) throws zzbg {
        zzq(i2 << 3);
        zzb(z2 ? (byte) 1 : (byte) 0);
    }

    @Override // com.google.android.gms.internal.play_billing.zzbi
    public final void zze(int i2, zzba zzbaVar) {
        zzq((i2 << 3) | 2);
        zzq(zzbaVar.zzd());
        zzbaVar.zzh(this);
    }

    @Override // com.google.android.gms.internal.play_billing.zzbi
    public final void zzf(int i2, int i3) throws zzbg {
        zzq((i2 << 3) | 5);
        zzg(i3);
    }

    @Override // com.google.android.gms.internal.play_billing.zzbi
    public final void zzg(int i2) throws zzbg {
        try {
            byte[] bArr = this.zzc;
            int i3 = this.zze;
            int i4 = i3 + 1;
            this.zze = i4;
            bArr[i3] = (byte) (i2 & 255);
            int i5 = i3 + 2;
            this.zze = i5;
            bArr[i4] = (byte) ((i2 >> 8) & 255);
            int i6 = i3 + 3;
            this.zze = i6;
            bArr[i5] = (byte) ((i2 >> 16) & 255);
            this.zze = i3 + 4;
            bArr[i6] = (byte) ((i2 >> 24) & 255);
        } catch (IndexOutOfBoundsException e2) {
            throw new zzbg(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.zze), Integer.valueOf(this.zzd), 1), e2);
        }
    }

    @Override // com.google.android.gms.internal.play_billing.zzbi
    public final void zzh(int i2, long j2) throws zzbg {
        zzq((i2 << 3) | 1);
        zzi(j2);
    }

    @Override // com.google.android.gms.internal.play_billing.zzbi
    public final void zzi(long j2) throws zzbg {
        try {
            byte[] bArr = this.zzc;
            int i2 = this.zze;
            int i3 = i2 + 1;
            this.zze = i3;
            bArr[i2] = (byte) (((int) j2) & 255);
            int i4 = i2 + 2;
            this.zze = i4;
            bArr[i3] = (byte) (((int) (j2 >> 8)) & 255);
            int i5 = i2 + 3;
            this.zze = i5;
            bArr[i4] = (byte) (((int) (j2 >> 16)) & 255);
            int i6 = i2 + 4;
            this.zze = i6;
            bArr[i5] = (byte) (((int) (j2 >> 24)) & 255);
            int i7 = i2 + 5;
            this.zze = i7;
            bArr[i6] = (byte) (((int) (j2 >> 32)) & 255);
            int i8 = i2 + 6;
            this.zze = i8;
            bArr[i7] = (byte) (((int) (j2 >> 40)) & 255);
            int i9 = i2 + 7;
            this.zze = i9;
            bArr[i8] = (byte) (((int) (j2 >> 48)) & 255);
            this.zze = i2 + 8;
            bArr[i9] = (byte) (((int) (j2 >> 56)) & 255);
        } catch (IndexOutOfBoundsException e2) {
            throw new zzbg(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.zze), Integer.valueOf(this.zzd), 1), e2);
        }
    }

    @Override // com.google.android.gms.internal.play_billing.zzbi
    public final void zzj(int i2, int i3) throws zzbg {
        zzq(i2 << 3);
        zzk(i3);
    }

    @Override // com.google.android.gms.internal.play_billing.zzbi
    public final void zzk(int i2) throws zzbg {
        if (i2 >= 0) {
            zzq(i2);
        } else {
            zzs(i2);
        }
    }

    @Override // com.google.android.gms.internal.play_billing.zzbi
    public final void zzl(byte[] bArr, int i2, int i3) {
        zzc(bArr, 0, i3);
    }

    @Override // com.google.android.gms.internal.play_billing.zzbi
    public final void zzm(int i2, String str) throws zzbg {
        zzq((i2 << 3) | 2);
        zzn(str);
    }

    public final void zzn(String str) throws zzbg {
        int i2 = this.zze;
        try {
            int iZzx = zzbi.zzx(str.length() * 3);
            int iZzx2 = zzbi.zzx(str.length());
            if (iZzx2 != iZzx) {
                zzq(zzev.zzc(str));
                byte[] bArr = this.zzc;
                int i3 = this.zze;
                this.zze = zzev.zzb(str, bArr, i3, this.zzd - i3);
                return;
            }
            int i4 = i2 + iZzx2;
            this.zze = i4;
            int iZzb = zzev.zzb(str, this.zzc, i4, this.zzd - i4);
            this.zze = i2;
            zzq((iZzb - i2) - iZzx2);
            this.zze = iZzb;
        } catch (zzeu e2) {
            this.zze = i2;
            zzB(str, e2);
        } catch (IndexOutOfBoundsException e3) {
            throw new zzbg(e3);
        }
    }

    @Override // com.google.android.gms.internal.play_billing.zzbi
    public final void zzo(int i2, int i3) {
        zzq((i2 << 3) | i3);
    }

    @Override // com.google.android.gms.internal.play_billing.zzbi
    public final void zzp(int i2, int i3) {
        zzq(i2 << 3);
        zzq(i3);
    }

    @Override // com.google.android.gms.internal.play_billing.zzbi
    public final void zzq(int i2) {
        while ((i2 & (-128)) != 0) {
            try {
                byte[] bArr = this.zzc;
                int i3 = this.zze;
                this.zze = i3 + 1;
                bArr[i3] = (byte) ((i2 & 127) | VertexAttributes.Usage.Tangent);
                i2 >>>= 7;
            } catch (IndexOutOfBoundsException e2) {
                throw new zzbg(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.zze), Integer.valueOf(this.zzd), 1), e2);
            }
        }
        byte[] bArr2 = this.zzc;
        int i4 = this.zze;
        this.zze = i4 + 1;
        bArr2[i4] = (byte) i2;
    }

    @Override // com.google.android.gms.internal.play_billing.zzbi
    public final void zzr(int i2, long j2) throws zzbg {
        zzq(i2 << 3);
        zzs(j2);
    }

    @Override // com.google.android.gms.internal.play_billing.zzbi
    public final void zzs(long j2) throws zzbg {
        if (zzbi.zzd && this.zzd - this.zze >= 10) {
            while ((j2 & (-128)) != 0) {
                byte[] bArr = this.zzc;
                int i2 = this.zze;
                this.zze = i2 + 1;
                zzeq.zzn(bArr, i2, (byte) ((((int) j2) & 127) | VertexAttributes.Usage.Tangent));
                j2 >>>= 7;
            }
            byte[] bArr2 = this.zzc;
            int i3 = this.zze;
            this.zze = i3 + 1;
            zzeq.zzn(bArr2, i3, (byte) j2);
            return;
        }
        while ((j2 & (-128)) != 0) {
            try {
                byte[] bArr3 = this.zzc;
                int i4 = this.zze;
                this.zze = i4 + 1;
                bArr3[i4] = (byte) ((((int) j2) & 127) | VertexAttributes.Usage.Tangent);
                j2 >>>= 7;
            } catch (IndexOutOfBoundsException e2) {
                throw new zzbg(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.zze), Integer.valueOf(this.zzd), 1), e2);
            }
        }
        byte[] bArr4 = this.zzc;
        int i5 = this.zze;
        this.zze = i5 + 1;
        bArr4[i5] = (byte) j2;
    }
}
