package com.google.android.gms.common.api.internal;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
abstract class zaau implements Runnable {
    private final /* synthetic */ zaak zagi;

    private zaau(zaak zaakVar) {
        this.zagi = zaakVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.zagi.zaen.lock();
        try {
            if (Thread.interrupted()) {
                return;
            }
            zaan();
            return;
        } catch (RuntimeException e2) {
            this.zagi.zafs.zab(e2);
            return;
        } finally {
            this.zagi.zaen.unlock();
        }
        this.zagi.zaen.unlock();
    }

    protected abstract void zaan();

    /* synthetic */ zaau(zaak zaakVar, zaal zaalVar) {
        this(zaakVar);
    }
}
