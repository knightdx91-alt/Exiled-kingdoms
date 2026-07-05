package com.google.android.gms.auth.api.signin.internal;

import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.util.VisibleForTesting;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class HashAccumulator {

    @VisibleForTesting
    private static int zaah = 31;
    private int zaai = 1;

    @KeepForSdk
    public HashAccumulator addObject(Object obj) {
        this.zaai = (zaah * this.zaai) + (obj == null ? 0 : obj.hashCode());
        return this;
    }

    @KeepForSdk
    public int hash() {
        return this.zaai;
    }

    public final HashAccumulator zaa(boolean z2) {
        this.zaai = (zaah * this.zaai) + (z2 ? 1 : 0);
        return this;
    }
}
