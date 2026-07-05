package com.google.android.gms.internal.play_billing;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.RandomAccess;

/* JADX INFO: compiled from: com.android.billingclient:billing@@6.0.1 */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class zzcm extends zzal implements RandomAccess, zzcn {

    @Deprecated
    public static final zzcn zza;
    private static final zzcm zzb;
    private final List zzc;

    static {
        zzcm zzcmVar = new zzcm(false);
        zzb = zzcmVar;
        zza = zzcmVar;
    }

    public zzcm() {
        this(10);
    }

    private static String zzi(Object obj) {
        return obj instanceof String ? (String) obj : obj instanceof zzba ? ((zzba) obj).zzm(zzcg.zzb) : zzcg.zzd((byte[]) obj);
    }

    @Override // com.google.android.gms.internal.play_billing.zzal, java.util.AbstractList, java.util.List
    public final /* bridge */ /* synthetic */ void add(int i2, Object obj) {
        zza();
        this.zzc.add(i2, (String) obj);
        ((AbstractList) this).modCount++;
    }

    @Override // com.google.android.gms.internal.play_billing.zzal, java.util.AbstractList, java.util.List
    public final boolean addAll(int i2, Collection collection) {
        zza();
        if (collection instanceof zzcn) {
            collection = ((zzcn) collection).zzh();
        }
        boolean zAddAll = this.zzc.addAll(i2, collection);
        ((AbstractList) this).modCount++;
        return zAddAll;
    }

    @Override // com.google.android.gms.internal.play_billing.zzal, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final void clear() {
        zza();
        this.zzc.clear();
        ((AbstractList) this).modCount++;
    }

    @Override // com.google.android.gms.internal.play_billing.zzal, java.util.AbstractList, java.util.List
    public final /* bridge */ /* synthetic */ Object remove(int i2) {
        zza();
        Object objRemove = this.zzc.remove(i2);
        ((AbstractList) this).modCount++;
        return zzi(objRemove);
    }

    @Override // com.google.android.gms.internal.play_billing.zzal, java.util.AbstractList, java.util.List
    public final /* bridge */ /* synthetic */ Object set(int i2, Object obj) {
        zza();
        return zzi(this.zzc.set(i2, (String) obj));
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zzc.size();
    }

    @Override // com.google.android.gms.internal.play_billing.zzcf
    public final /* bridge */ /* synthetic */ zzcf zzd(int i2) {
        if (i2 < size()) {
            throw new IllegalArgumentException();
        }
        ArrayList arrayList = new ArrayList(i2);
        arrayList.addAll(this.zzc);
        return new zzcm(arrayList);
    }

    @Override // com.google.android.gms.internal.play_billing.zzcn
    public final zzcn zze() {
        return zzc() ? new zzel(this) : this;
    }

    @Override // com.google.android.gms.internal.play_billing.zzcn
    public final Object zzf(int i2) {
        return this.zzc.get(i2);
    }

    @Override // java.util.AbstractList, java.util.List
    /* JADX INFO: renamed from: zzg, reason: merged with bridge method [inline-methods] */
    public final String get(int i2) {
        Object obj = this.zzc.get(i2);
        if (obj instanceof String) {
            return (String) obj;
        }
        if (obj instanceof zzba) {
            zzba zzbaVar = (zzba) obj;
            String strZzm = zzbaVar.zzm(zzcg.zzb);
            if (zzbaVar.zzi()) {
                this.zzc.set(i2, strZzm);
            }
            return strZzm;
        }
        byte[] bArr = (byte[]) obj;
        String strZzd = zzcg.zzd(bArr);
        if (zzev.zzd(bArr)) {
            this.zzc.set(i2, strZzd);
        }
        return strZzd;
    }

    @Override // com.google.android.gms.internal.play_billing.zzcn
    public final List zzh() {
        return Collections.unmodifiableList(this.zzc);
    }

    public zzcm(int i2) {
        ArrayList arrayList = new ArrayList(i2);
        super(true);
        this.zzc = arrayList;
    }

    private zzcm(ArrayList arrayList) {
        super(true);
        this.zzc = arrayList;
    }

    private zzcm(boolean z2) {
        super(false);
        this.zzc = Collections.emptyList();
    }

    @Override // com.google.android.gms.internal.play_billing.zzal, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection collection) {
        return addAll(size(), collection);
    }
}
