package com.google.android.gms.internal.play_billing;

/* JADX INFO: compiled from: com.android.billingclient:billing@@6.0.1 */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzcr extends zzct {
    /* synthetic */ zzcr(zzcq zzcqVar) {
        super(null);
    }

    @Override // com.google.android.gms.internal.play_billing.zzct
    final void zza(Object obj, long j2) {
        ((zzcf) zzeq.zzf(obj, j2)).zzb();
    }

    @Override // com.google.android.gms.internal.play_billing.zzct
    final void zzb(Object obj, Object obj2, long j2) {
        zzcf zzcfVarZzd = (zzcf) zzeq.zzf(obj, j2);
        zzcf zzcfVar = (zzcf) zzeq.zzf(obj2, j2);
        int size = zzcfVarZzd.size();
        int size2 = zzcfVar.size();
        if (size > 0 && size2 > 0) {
            if (!zzcfVarZzd.zzc()) {
                zzcfVarZzd = zzcfVarZzd.zzd(size2 + size);
            }
            zzcfVarZzd.addAll(zzcfVar);
        }
        if (size > 0) {
            zzcfVar = zzcfVarZzd;
        }
        zzeq.zzs(obj, j2, zzcfVar);
    }

    private zzcr() {
        super(null);
    }
}
