package com.google.android.gms.internal.play_billing;

import java.nio.charset.Charset;

/* JADX INFO: compiled from: com.android.billingclient:billing@@6.0.1 */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
class zzax extends zzaw {
    protected final byte[] zza;

    zzax(byte[] bArr) {
        bArr.getClass();
        this.zza = bArr;
    }

    @Override // com.google.android.gms.internal.play_billing.zzba
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzba) || zzd() != ((zzba) obj).zzd()) {
            return false;
        }
        if (zzd() == 0) {
            return true;
        }
        if (!(obj instanceof zzax)) {
            return obj.equals(this);
        }
        zzax zzaxVar = (zzax) obj;
        int iZzk = zzk();
        int iZzk2 = zzaxVar.zzk();
        if (iZzk != 0 && iZzk2 != 0 && iZzk != iZzk2) {
            return false;
        }
        int iZzd = zzd();
        if (iZzd > zzaxVar.zzd()) {
            throw new IllegalArgumentException("Length too large: " + iZzd + zzd());
        }
        if (iZzd > zzaxVar.zzd()) {
            throw new IllegalArgumentException("Ran off end of other: 0, " + iZzd + ", " + zzaxVar.zzd());
        }
        byte[] bArr = this.zza;
        byte[] bArr2 = zzaxVar.zza;
        zzaxVar.zzc();
        int i2 = 0;
        int i3 = 0;
        while (i2 < iZzd) {
            if (bArr[i2] != bArr2[i3]) {
                return false;
            }
            i2++;
            i3++;
        }
        return true;
    }

    @Override // com.google.android.gms.internal.play_billing.zzba
    public byte zza(int i2) {
        return this.zza[i2];
    }

    @Override // com.google.android.gms.internal.play_billing.zzba
    byte zzb(int i2) {
        return this.zza[i2];
    }

    protected int zzc() {
        return 0;
    }

    @Override // com.google.android.gms.internal.play_billing.zzba
    public int zzd() {
        return this.zza.length;
    }

    @Override // com.google.android.gms.internal.play_billing.zzba
    protected final int zze(int i2, int i3, int i4) {
        return zzcg.zzb(i2, this.zza, 0, i4);
    }

    @Override // com.google.android.gms.internal.play_billing.zzba
    public final zzba zzf(int i2, int i3) {
        int iZzj = zzba.zzj(0, i3, zzd());
        return iZzj == 0 ? zzba.zzb : new zzau(this.zza, 0, iZzj);
    }

    @Override // com.google.android.gms.internal.play_billing.zzba
    protected final String zzg(Charset charset) {
        return new String(this.zza, 0, zzd(), charset);
    }

    @Override // com.google.android.gms.internal.play_billing.zzba
    final void zzh(zzaq zzaqVar) {
        ((zzbf) zzaqVar).zzc(this.zza, 0, zzd());
    }

    @Override // com.google.android.gms.internal.play_billing.zzba
    public final boolean zzi() {
        return zzev.zze(this.zza, 0, zzd());
    }
}
