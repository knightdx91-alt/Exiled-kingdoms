package com.google.android.gms.internal.play_billing;

/* JADX INFO: compiled from: com.android.billingclient:billing@@6.0.1 */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzei extends zzeg {
    zzei() {
    }

    @Override // com.google.android.gms.internal.play_billing.zzeg
    final /* synthetic */ int zza(Object obj) {
        return ((zzeh) obj).zza();
    }

    @Override // com.google.android.gms.internal.play_billing.zzeg
    final /* synthetic */ int zzb(Object obj) {
        return ((zzeh) obj).zzb();
    }

    @Override // com.google.android.gms.internal.play_billing.zzeg
    final /* bridge */ /* synthetic */ Object zzc(Object obj) {
        zzcb zzcbVar = (zzcb) obj;
        zzeh zzehVar = zzcbVar.zzc;
        if (zzehVar != zzeh.zzc()) {
            return zzehVar;
        }
        zzeh zzehVarZzf = zzeh.zzf();
        zzcbVar.zzc = zzehVarZzf;
        return zzehVarZzf;
    }

    @Override // com.google.android.gms.internal.play_billing.zzeg
    final /* synthetic */ Object zzd(Object obj) {
        return ((zzcb) obj).zzc;
    }

    @Override // com.google.android.gms.internal.play_billing.zzeg
    final /* bridge */ /* synthetic */ Object zze(Object obj, Object obj2) {
        if (zzeh.zzc().equals(obj2)) {
            return obj;
        }
        if (zzeh.zzc().equals(obj)) {
            return zzeh.zze((zzeh) obj, (zzeh) obj2);
        }
        ((zzeh) obj).zzd((zzeh) obj2);
        return obj;
    }

    @Override // com.google.android.gms.internal.play_billing.zzeg
    final /* bridge */ /* synthetic */ void zzf(Object obj, int i2, long j2) {
        ((zzeh) obj).zzj(i2 << 3, Long.valueOf(j2));
    }

    @Override // com.google.android.gms.internal.play_billing.zzeg
    final void zzg(Object obj) {
        ((zzcb) obj).zzc.zzh();
    }

    @Override // com.google.android.gms.internal.play_billing.zzeg
    final /* synthetic */ void zzh(Object obj, Object obj2) {
        ((zzcb) obj).zzc = (zzeh) obj2;
    }

    @Override // com.google.android.gms.internal.play_billing.zzeg
    final /* synthetic */ void zzi(Object obj, zzey zzeyVar) {
        ((zzeh) obj).zzk(zzeyVar);
    }
}
