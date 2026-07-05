package com.google.android.gms.internal.play_billing;

import a.a;

/* JADX INFO: compiled from: com.android.billingclient:billing@@6.0.1 */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzau extends zzax {
    private final int zzc;

    zzau(byte[] bArr, int i2, int i3) {
        super(bArr);
        zzba.zzj(0, i3, bArr.length);
        this.zzc = i3;
    }

    @Override // com.google.android.gms.internal.play_billing.zzax, com.google.android.gms.internal.play_billing.zzba
    public final byte zza(int i2) {
        int i3 = this.zzc;
        if (((i3 - (i2 + 1)) | i2) >= 0) {
            return this.zza[i2];
        }
        if (i2 < 0) {
            throw new ArrayIndexOutOfBoundsException(a.h(i2, "Index < 0: "));
        }
        throw new ArrayIndexOutOfBoundsException("Index > length: " + i2 + ", " + i3);
    }

    @Override // com.google.android.gms.internal.play_billing.zzax, com.google.android.gms.internal.play_billing.zzba
    final byte zzb(int i2) {
        return this.zza[i2];
    }

    @Override // com.google.android.gms.internal.play_billing.zzax
    protected final int zzc() {
        return 0;
    }

    @Override // com.google.android.gms.internal.play_billing.zzax, com.google.android.gms.internal.play_billing.zzba
    public final int zzd() {
        return this.zzc;
    }
}
