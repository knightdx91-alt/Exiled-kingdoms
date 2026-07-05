package com.google.android.gms.common.api.internal;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
abstract class zabf {
    private final zabd zaht;

    protected zabf(zabd zabdVar) {
        this.zaht = zabdVar;
    }

    protected abstract void zaan();

    public final void zac(zabe zabeVar) {
        zabeVar.zaen.lock();
        try {
            if (zabeVar.zahp != this.zaht) {
                return;
            }
            zaan();
        } finally {
            zabeVar.zaen.unlock();
        }
    }
}
