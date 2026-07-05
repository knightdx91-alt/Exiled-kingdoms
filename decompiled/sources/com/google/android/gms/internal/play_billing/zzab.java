package com.google.android.gms.internal.play_billing;

import java.util.AbstractMap;

/* JADX INFO: compiled from: com.android.billingclient:billing@@6.0.1 */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzab extends zzu {
    final /* synthetic */ zzac zza;

    zzab(zzac zzacVar) {
        this.zza = zzacVar;
    }

    @Override // java.util.List
    public final /* bridge */ /* synthetic */ Object get(int i2) {
        zzm.zza(i2, this.zza.zzc, "index");
        zzac zzacVar = this.zza;
        int i3 = i2 + i2;
        Object obj = zzacVar.zzb[i3];
        obj.getClass();
        Object obj2 = zzacVar.zzb[i3 + 1];
        obj2.getClass();
        return new AbstractMap.SimpleImmutableEntry(obj, obj2);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zza.zzc;
    }

    @Override // com.google.android.gms.internal.play_billing.zzr
    public final boolean zzf() {
        return true;
    }
}
