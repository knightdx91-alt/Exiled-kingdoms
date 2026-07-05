package com.google.android.gms.common.api.internal;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zat implements Runnable {
    private final /* synthetic */ zas zaep;

    zat(zas zasVar) {
        this.zaep = zasVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zaep.zaen.lock();
        try {
            this.zaep.zax();
        } finally {
            this.zaep.zaen.unlock();
        }
    }
}
