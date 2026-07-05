package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import com.google.android.gms.common.ConnectionResult;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zau implements zabt {
    private final /* synthetic */ zas zaep;

    private zau(zas zasVar) {
        this.zaep = zasVar;
    }

    @Override // com.google.android.gms.common.api.internal.zabt
    public final void zab(Bundle bundle) {
        this.zaep.zaen.lock();
        try {
            this.zaep.zaa(bundle);
            this.zaep.zaek = ConnectionResult.RESULT_SUCCESS;
            this.zaep.zax();
        } finally {
            this.zaep.zaen.unlock();
        }
    }

    @Override // com.google.android.gms.common.api.internal.zabt
    public final void zac(ConnectionResult connectionResult) {
        this.zaep.zaen.lock();
        try {
            this.zaep.zaek = connectionResult;
            this.zaep.zax();
        } finally {
            this.zaep.zaen.unlock();
        }
    }

    /* synthetic */ zau(zas zasVar, zat zatVar) {
        this(zasVar);
    }

    @Override // com.google.android.gms.common.api.internal.zabt
    public final void zab(int i2, boolean z2) {
        this.zaep.zaen.lock();
        try {
            if (!this.zaep.zaem && this.zaep.zael != null && this.zaep.zael.isSuccess()) {
                this.zaep.zaem = true;
                this.zaep.zaef.onConnectionSuspended(i2);
                return;
            }
            this.zaep.zaem = false;
            this.zaep.zaa(i2, z2);
        } finally {
            this.zaep.zaen.unlock();
        }
    }
}
