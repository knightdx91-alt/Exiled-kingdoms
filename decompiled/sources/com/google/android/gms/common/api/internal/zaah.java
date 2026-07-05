package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import android.os.DeadObjectException;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.SimpleClientAdapter;
import java.util.Iterator;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class zaah implements zabd {
    private final zabe zafs;
    private boolean zaft = false;

    public zaah(zabe zabeVar) {
        this.zafs = zabeVar;
    }

    @Override // com.google.android.gms.common.api.internal.zabd
    public final void begin() {
    }

    @Override // com.google.android.gms.common.api.internal.zabd
    public final void connect() {
        if (this.zaft) {
            this.zaft = false;
            this.zafs.zaa(new zaaj(this, this));
        }
    }

    @Override // com.google.android.gms.common.api.internal.zabd
    public final boolean disconnect() {
        if (this.zaft) {
            return false;
        }
        if (!this.zafs.zaed.zaax()) {
            this.zafs.zaf(null);
            return true;
        }
        this.zaft = true;
        Iterator<zacm> it = this.zafs.zaed.zahd.iterator();
        while (it.hasNext()) {
            it.next().zabv();
        }
        return false;
    }

    @Override // com.google.android.gms.common.api.internal.zabd
    public final <A extends Api.AnyClient, R extends Result, T extends BaseImplementation.ApiMethodImpl<R, A>> T enqueue(T t2) {
        return (T) execute(t2);
    }

    @Override // com.google.android.gms.common.api.internal.zabd
    public final <A extends Api.AnyClient, T extends BaseImplementation.ApiMethodImpl<? extends Result, A>> T execute(T t2) {
        try {
            this.zafs.zaed.zahe.zab(t2);
            zaaw zaawVar = this.zafs.zaed;
            Api.Client client = zaawVar.zagy.get(t2.getClientKey());
            Preconditions.checkNotNull(client, "Appropriate Api was not requested.");
            if (client.isConnected() || !this.zafs.zaho.containsKey(t2.getClientKey())) {
                boolean z2 = client instanceof SimpleClientAdapter;
                A client2 = client;
                if (z2) {
                    client2 = ((SimpleClientAdapter) client).getClient();
                }
                t2.run(client2);
            } else {
                t2.setFailedResult(new Status(17));
            }
        } catch (DeadObjectException unused) {
            this.zafs.zaa(new zaai(this, this));
        }
        return t2;
    }

    @Override // com.google.android.gms.common.api.internal.zabd
    public final void onConnected(Bundle bundle) {
    }

    @Override // com.google.android.gms.common.api.internal.zabd
    public final void onConnectionSuspended(int i2) {
        this.zafs.zaf(null);
        this.zafs.zahs.zab(i2, this.zaft);
    }

    @Override // com.google.android.gms.common.api.internal.zabd
    public final void zaa(ConnectionResult connectionResult, Api<?> api, boolean z2) {
    }

    final void zaam() {
        if (this.zaft) {
            this.zaft = false;
            this.zafs.zaed.zahe.release();
            disconnect();
        }
    }
}
