package com.google.android.gms.common;

import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
abstract class zzg extends zze {
    private static final WeakReference<byte[]> zzw = new WeakReference<>(null);
    private WeakReference<byte[]> zzv;

    zzg(byte[] bArr) {
        super(bArr);
        this.zzv = zzw;
    }

    @Override // com.google.android.gms.common.zze
    final byte[] getBytes() {
        byte[] bArrZzd;
        synchronized (this) {
            try {
                bArrZzd = this.zzv.get();
                if (bArrZzd == null) {
                    bArrZzd = zzd();
                    this.zzv = new WeakReference<>(bArrZzd);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return bArrZzd;
    }

    protected abstract byte[] zzd();
}
