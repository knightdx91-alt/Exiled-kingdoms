package com.google.android.gms.internal.play_billing;

/* JADX INFO: compiled from: com.android.billingclient:billing@@6.0.1 */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzcw implements zzdd {
    private final zzdd[] zza;

    zzcw(zzdd... zzddVarArr) {
        this.zza = zzddVarArr;
    }

    @Override // com.google.android.gms.internal.play_billing.zzdd
    public final zzdc zzb(Class cls) {
        zzdd[] zzddVarArr = this.zza;
        for (int i2 = 0; i2 < 2; i2++) {
            zzdd zzddVar = zzddVarArr[i2];
            if (zzddVar.zzc(cls)) {
                return zzddVar.zzb(cls);
            }
        }
        throw new UnsupportedOperationException("No factory is available for message type: ".concat(cls.getName()));
    }

    @Override // com.google.android.gms.internal.play_billing.zzdd
    public final boolean zzc(Class cls) {
        zzdd[] zzddVarArr = this.zza;
        for (int i2 = 0; i2 < 2; i2++) {
            if (zzddVarArr[i2].zzc(cls)) {
                return true;
            }
        }
        return false;
    }
}
