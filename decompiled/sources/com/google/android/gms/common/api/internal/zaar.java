package com.google.android.gms.common.api.internal;

import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zaar extends com.google.android.gms.signin.internal.zac {
    private final WeakReference<zaak> zagj;

    zaar(zaak zaakVar) {
        this.zagj = new WeakReference<>(zaakVar);
    }

    @Override // com.google.android.gms.signin.internal.zac, com.google.android.gms.signin.internal.zad
    public final void zab(com.google.android.gms.signin.internal.zaj zajVar) {
        zaak zaakVar = this.zagj.get();
        if (zaakVar == null) {
            return;
        }
        zaakVar.zafs.zaa(new zaas(this, zaakVar, zaakVar, zajVar));
    }
}
