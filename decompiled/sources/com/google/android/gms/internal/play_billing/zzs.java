package com.google.android.gms.internal.play_billing;

/* JADX INFO: compiled from: com.android.billingclient:billing@@6.0.1 */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzs extends zzo {
    private final zzu zza;

    zzs(zzu zzuVar, int i2) {
        super(zzuVar.size(), i2);
        this.zza = zzuVar;
    }

    @Override // com.google.android.gms.internal.play_billing.zzo
    protected final Object zza(int i2) {
        return this.zza.get(i2);
    }
}
