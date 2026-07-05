package com.google.android.gms.internal.play_billing;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

/* JADX INFO: compiled from: com.android.billingclient:billing@@6.0.1 */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
class zzec extends AbstractMap {
    private final int zza;
    private boolean zzd;
    private volatile zzea zze;
    private List zzb = Collections.emptyList();
    private Map zzc = Collections.emptyMap();
    private Map zzf = Collections.emptyMap();

    private final int zzk(Comparable comparable) {
        int size = this.zzb.size();
        int i2 = size - 1;
        int i3 = 0;
        if (i2 >= 0) {
            int iCompareTo = comparable.compareTo(((zzdw) this.zzb.get(i2)).zza());
            if (iCompareTo > 0) {
                return -(size + 1);
            }
            if (iCompareTo == 0) {
                return i2;
            }
        }
        while (i3 <= i2) {
            int i4 = (i3 + i2) / 2;
            int iCompareTo2 = comparable.compareTo(((zzdw) this.zzb.get(i4)).zza());
            if (iCompareTo2 < 0) {
                i2 = i4 - 1;
            } else {
                if (iCompareTo2 <= 0) {
                    return i4;
                }
                i3 = i4 + 1;
            }
        }
        return -(i3 + 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object zzl(int i2) {
        zzn();
        Object value = ((zzdw) this.zzb.remove(i2)).getValue();
        if (!this.zzc.isEmpty()) {
            Iterator it = zzm().entrySet().iterator();
            List list = this.zzb;
            Map.Entry entry = (Map.Entry) it.next();
            list.add(new zzdw(this, (Comparable) entry.getKey(), entry.getValue()));
            it.remove();
        }
        return value;
    }

    private final SortedMap zzm() {
        zzn();
        if (this.zzc.isEmpty() && !(this.zzc instanceof TreeMap)) {
            TreeMap treeMap = new TreeMap();
            this.zzc = treeMap;
            this.zzf = treeMap.descendingMap();
        }
        return (SortedMap) this.zzc;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzn() {
        if (this.zzd) {
            throw new UnsupportedOperationException();
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final void clear() {
        zzn();
        if (!this.zzb.isEmpty()) {
            this.zzb.clear();
        }
        if (this.zzc.isEmpty()) {
            return;
        }
        this.zzc.clear();
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final boolean containsKey(Object obj) {
        Comparable comparable = (Comparable) obj;
        return zzk(comparable) >= 0 || this.zzc.containsKey(comparable);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Set entrySet() {
        if (this.zze == null) {
            this.zze = new zzea(this, null);
        }
        return this.zze;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzec)) {
            return super.equals(obj);
        }
        zzec zzecVar = (zzec) obj;
        int size = size();
        if (size != zzecVar.size()) {
            return false;
        }
        int iZzb = zzb();
        if (iZzb != zzecVar.zzb()) {
            return entrySet().equals(zzecVar.entrySet());
        }
        for (int i2 = 0; i2 < iZzb; i2++) {
            if (!zzg(i2).equals(zzecVar.zzg(i2))) {
                return false;
            }
        }
        if (iZzb != size) {
            return this.zzc.equals(zzecVar.zzc);
        }
        return true;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Object get(Object obj) {
        Comparable comparable = (Comparable) obj;
        int iZzk = zzk(comparable);
        return iZzk >= 0 ? ((zzdw) this.zzb.get(iZzk)).getValue() : this.zzc.get(comparable);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final int hashCode() {
        int iZzb = zzb();
        int iHashCode = 0;
        for (int i2 = 0; i2 < iZzb; i2++) {
            iHashCode += ((zzdw) this.zzb.get(i2)).hashCode();
        }
        return this.zzc.size() > 0 ? this.zzc.hashCode() + iHashCode : iHashCode;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Object remove(Object obj) {
        zzn();
        Comparable comparable = (Comparable) obj;
        int iZzk = zzk(comparable);
        if (iZzk >= 0) {
            return zzl(iZzk);
        }
        if (this.zzc.isEmpty()) {
            return null;
        }
        return this.zzc.remove(comparable);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final int size() {
        return this.zzc.size() + this.zzb.size();
    }

    public void zza() {
        if (this.zzd) {
            return;
        }
        this.zzc = this.zzc.isEmpty() ? Collections.emptyMap() : Collections.unmodifiableMap(this.zzc);
        this.zzf = this.zzf.isEmpty() ? Collections.emptyMap() : Collections.unmodifiableMap(this.zzf);
        this.zzd = true;
    }

    public final int zzb() {
        return this.zzb.size();
    }

    public final Iterable zzc() {
        return this.zzc.isEmpty() ? zzdv.zza() : this.zzc.entrySet();
    }

    @Override // java.util.AbstractMap, java.util.Map
    /* JADX INFO: renamed from: zze, reason: merged with bridge method [inline-methods] */
    public final Object put(Comparable comparable, Object obj) {
        zzn();
        int iZzk = zzk(comparable);
        if (iZzk >= 0) {
            return ((zzdw) this.zzb.get(iZzk)).setValue(obj);
        }
        zzn();
        if (this.zzb.isEmpty() && !(this.zzb instanceof ArrayList)) {
            this.zzb = new ArrayList(this.zza);
        }
        int i2 = -(iZzk + 1);
        if (i2 >= this.zza) {
            return zzm().put(comparable, obj);
        }
        int size = this.zzb.size();
        int i3 = this.zza;
        if (size == i3) {
            zzdw zzdwVar = (zzdw) this.zzb.remove(i3 - 1);
            zzm().put(zzdwVar.zza(), zzdwVar.getValue());
        }
        this.zzb.add(i2, new zzdw(this, comparable, obj));
        return null;
    }

    public final Map.Entry zzg(int i2) {
        return (Map.Entry) this.zzb.get(i2);
    }

    public final boolean zzj() {
        return this.zzd;
    }
}
