package com.google.android.gms.internal.games;

import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public abstract class zzel {
    private final AtomicReference<zzej> zzkw = new AtomicReference<>();

    public final void flush() {
        zzej zzejVar = this.zzkw.get();
        if (zzejVar != null) {
            zzejVar.flush();
        }
    }

    public final void zza(String str, int i2) {
        zzej zzejVar = this.zzkw.get();
        if (zzejVar == null) {
            zzej zzejVarZzbe = zzbe();
            AtomicReference<zzej> atomicReference = this.zzkw;
            while (true) {
                if (atomicReference.compareAndSet(null, zzejVarZzbe)) {
                    zzejVar = zzejVarZzbe;
                    break;
                } else if (atomicReference.get() != null) {
                    zzejVar = this.zzkw.get();
                    break;
                }
            }
        }
        zzejVar.zzg(str, i2);
    }

    protected abstract zzej zzbe();
}
