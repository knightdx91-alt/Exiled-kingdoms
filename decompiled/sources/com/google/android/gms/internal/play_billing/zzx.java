package com.google.android.gms.internal.play_billing;

import a.a;
import java.io.Serializable;
import java.util.Map;
import java.util.Set;

/* JADX INFO: compiled from: com.android.billingclient:billing@@6.0.1 */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public abstract class zzx implements Map, Serializable {
    private transient zzy zza;
    private transient zzy zzb;
    private transient zzr zzc;

    zzx() {
    }

    @Override // java.util.Map
    @Deprecated
    public final void clear() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map
    public final boolean containsKey(Object obj) {
        return get(obj) != null;
    }

    @Override // java.util.Map
    public final boolean containsValue(Object obj) {
        return values().contains(obj);
    }

    @Override // java.util.Map
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Map) {
            return entrySet().equals(((Map) obj).entrySet());
        }
        return false;
    }

    @Override // java.util.Map
    public abstract Object get(Object obj);

    @Override // java.util.Map
    public final Object getOrDefault(Object obj, Object obj2) {
        Object obj3 = get(obj);
        return obj3 != null ? obj3 : obj2;
    }

    @Override // java.util.Map
    public final int hashCode() {
        return zzag.zza(entrySet());
    }

    @Override // java.util.Map
    public final boolean isEmpty() {
        return size() == 0;
    }

    @Override // java.util.Map
    public final /* bridge */ /* synthetic */ Set keySet() {
        zzy zzyVar = this.zzb;
        if (zzyVar != null) {
            return zzyVar;
        }
        zzy zzyVarZzd = zzd();
        this.zzb = zzyVarZzd;
        return zzyVarZzd;
    }

    @Override // java.util.Map
    @Deprecated
    public final Object put(Object obj, Object obj2) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map
    @Deprecated
    public final void putAll(Map map) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map
    @Deprecated
    public final Object remove(Object obj) {
        throw new UnsupportedOperationException();
    }

    public final String toString() {
        int size = size();
        if (size < 0) {
            throw new IllegalArgumentException(a.h(size, "size cannot be negative but was: "));
        }
        StringBuilder sb = new StringBuilder((int) Math.min(((long) size) * 8, 1073741824L));
        sb.append('{');
        boolean z2 = true;
        for (Map.Entry entry : entrySet()) {
            if (!z2) {
                sb.append(", ");
            }
            sb.append(entry.getKey());
            sb.append('=');
            sb.append(entry.getValue());
            z2 = false;
        }
        sb.append('}');
        return sb.toString();
    }

    abstract zzr zza();

    @Override // java.util.Map
    /* JADX INFO: renamed from: zzb, reason: merged with bridge method [inline-methods] */
    public final zzr values() {
        zzr zzrVar = this.zzc;
        if (zzrVar != null) {
            return zzrVar;
        }
        zzr zzrVarZza = zza();
        this.zzc = zzrVarZza;
        return zzrVarZza;
    }

    abstract zzy zzc();

    abstract zzy zzd();

    @Override // java.util.Map
    /* JADX INFO: renamed from: zze, reason: merged with bridge method [inline-methods] */
    public final zzy entrySet() {
        zzy zzyVar = this.zza;
        if (zzyVar != null) {
            return zzyVar;
        }
        zzy zzyVarZzc = zzc();
        this.zza = zzyVarZzc;
        return zzyVarZzc;
    }
}
