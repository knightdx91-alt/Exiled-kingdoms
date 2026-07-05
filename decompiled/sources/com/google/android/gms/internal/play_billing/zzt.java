package com.google.android.gms.internal.play_billing;

/* JADX INFO: compiled from: com.android.billingclient:billing@@6.0.1 */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzt extends zzu {
    final transient int zza;
    final transient int zzb;
    final /* synthetic */ zzu zzc;

    zzt(zzu zzuVar, int i2, int i3) {
        this.zzc = zzuVar;
        this.zza = i2;
        this.zzb = i3;
    }

    @Override // java.util.List
    public final Object get(int i2) {
        zzm.zza(i2, this.zzb, "index");
        return this.zzc.get(i2 + this.zza);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.play_billing.zzr
    final int zzb() {
        return this.zzc.zzc() + this.zza + this.zzb;
    }

    @Override // com.google.android.gms.internal.play_billing.zzr
    final int zzc() {
        return this.zzc.zzc() + this.zza;
    }

    @Override // com.google.android.gms.internal.play_billing.zzr
    final boolean zzf() {
        return true;
    }

    @Override // com.google.android.gms.internal.play_billing.zzr
    final Object[] zzg() {
        return this.zzc.zzg();
    }

    @Override // com.google.android.gms.internal.play_billing.zzu, java.util.List
    /* JADX INFO: renamed from: zzh */
    public final zzu subList(int i2, int i3) {
        zzm.zzd(i2, i3, this.zzb);
        zzu zzuVar = this.zzc;
        int i4 = this.zza;
        return zzuVar.subList(i2 + i4, i3 + i4);
    }
}
