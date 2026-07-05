package com.google.android.gms.internal.play_billing;

import java.util.NoSuchElementException;

/* JADX INFO: compiled from: com.android.billingclient:billing@@6.0.1 */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
abstract class zzo extends zzai {
    private final int zza;
    private int zzb;

    protected zzo(int i2, int i3) {
        zzm.zzb(i3, i2, "index");
        this.zza = i2;
        this.zzb = i3;
    }

    @Override // java.util.Iterator, java.util.ListIterator
    public final boolean hasNext() {
        return this.zzb < this.zza;
    }

    @Override // java.util.ListIterator
    public final boolean hasPrevious() {
        return this.zzb > 0;
    }

    @Override // java.util.Iterator, java.util.ListIterator
    public final Object next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        int i2 = this.zzb;
        this.zzb = i2 + 1;
        return zza(i2);
    }

    @Override // java.util.ListIterator
    public final int nextIndex() {
        return this.zzb;
    }

    @Override // java.util.ListIterator
    public final Object previous() {
        if (!hasPrevious()) {
            throw new NoSuchElementException();
        }
        int i2 = this.zzb - 1;
        this.zzb = i2;
        return zza(i2);
    }

    @Override // java.util.ListIterator
    public final int previousIndex() {
        return this.zzb - 1;
    }

    protected abstract Object zza(int i2);
}
