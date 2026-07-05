package com.google.android.gms.internal.play_billing;

import java.util.NoSuchElementException;

/* JADX INFO: compiled from: com.android.billingclient:billing@@6.0.1 */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzar extends zzat {
    final /* synthetic */ zzba zza;
    private int zzb = 0;
    private final int zzc;

    zzar(zzba zzbaVar) {
        this.zza = zzbaVar;
        this.zzc = zzbaVar.zzd();
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.zzb < this.zzc;
    }

    @Override // com.google.android.gms.internal.play_billing.zzav
    public final byte zza() {
        int i2 = this.zzb;
        if (i2 >= this.zzc) {
            throw new NoSuchElementException();
        }
        this.zzb = i2 + 1;
        return this.zza.zzb(i2);
    }
}
