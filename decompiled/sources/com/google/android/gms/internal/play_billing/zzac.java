package com.google.android.gms.internal.play_billing;

import java.util.Iterator;
import java.util.Map;

/* JADX INFO: compiled from: com.android.billingclient:billing@@6.0.1 */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzac extends zzy {
    private final transient zzx zza;
    private final transient Object[] zzb;
    private final transient int zzc;

    zzac(zzx zzxVar, Object[] objArr, int i2, int i3) {
        this.zza = zzxVar;
        this.zzb = objArr;
        this.zzc = i3;
    }

    @Override // com.google.android.gms.internal.play_billing.zzr, java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean contains(Object obj) {
        if (obj instanceof Map.Entry) {
            Map.Entry entry = (Map.Entry) obj;
            Object key = entry.getKey();
            Object value = entry.getValue();
            if (value != null && value.equals(this.zza.get(key))) {
                return true;
            }
        }
        return false;
    }

    @Override // com.google.android.gms.internal.play_billing.zzy, com.google.android.gms.internal.play_billing.zzr, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final /* synthetic */ Iterator iterator() {
        return zzd().listIterator(0);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.play_billing.zzr
    final int zza(Object[] objArr, int i2) {
        return zzd().zza(objArr, 0);
    }

    @Override // com.google.android.gms.internal.play_billing.zzy, com.google.android.gms.internal.play_billing.zzr
    /* JADX INFO: renamed from: zze */
    public final zzah iterator() {
        return zzd().listIterator(0);
    }

    @Override // com.google.android.gms.internal.play_billing.zzr
    final boolean zzf() {
        throw null;
    }

    @Override // com.google.android.gms.internal.play_billing.zzy
    final zzu zzh() {
        return new zzab(this);
    }
}
