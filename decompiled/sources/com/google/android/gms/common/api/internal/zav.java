package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import com.google.android.gms.common.ConnectionResult;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zav implements zabt {
    private final /* synthetic */ zas zaep;

    private zav(zas zasVar) {
        this.zaep = zasVar;
    }

    @Override // com.google.android.gms.common.api.internal.zabt
    public final void zab(Bundle bundle) {
        this.zaep.zaen.lock();
        try {
            this.zaep.zael = ConnectionResult.RESULT_SUCCESS;
            this.zaep.zax();
        } finally {
            this.zaep.zaen.unlock();
        }
    }

    @Override // com.google.android.gms.common.api.internal.zabt
    public final void zac(ConnectionResult connectionResult) {
        this.zaep.zaen.lock();
        try {
            this.zaep.zael = connectionResult;
            this.zaep.zax();
        } finally {
            this.zaep.zaen.unlock();
        }
    }

    /* synthetic */ zav(zas zasVar, zat zatVar) {
        this(zasVar);
    }

    @Override // com.google.android.gms.common.api.internal.zabt
    public final void zab(int i2, boolean z2) {
        this.zaep.zaen.lock();
        try {
            if (this.zaep.zaem) {
                this.zaep.zaem = false;
                this.zaep.zaa(i2, z2);
            } else {
                this.zaep.zaem = true;
                this.zaep.zaee.onConnectionSuspended(i2);
            }
        } finally {
            this.zaep.zaen.unlock();
        }
    }
}
