package com.google.android.gms.common.api.internal;

import a.a;
import android.os.DeadObjectException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl;
import com.google.android.gms.common.api.internal.GoogleApiManager;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class zae<A extends BaseImplementation.ApiMethodImpl<? extends Result, Api.AnyClient>> extends zab {
    private final A zacn;

    public zae(int i2, A a2) {
        super(i2);
        this.zacn = a2;
    }

    @Override // com.google.android.gms.common.api.internal.zab
    public final void zaa(GoogleApiManager.zaa<?> zaaVar) throws DeadObjectException {
        try {
            this.zacn.run(zaaVar.zaab());
        } catch (RuntimeException e2) {
            zaa(e2);
        }
    }

    @Override // com.google.android.gms.common.api.internal.zab
    public final void zaa(Status status) {
        this.zacn.setFailedResult(status);
    }

    @Override // com.google.android.gms.common.api.internal.zab
    public final void zaa(RuntimeException runtimeException) {
        String simpleName = runtimeException.getClass().getSimpleName();
        String localizedMessage = runtimeException.getLocalizedMessage();
        StringBuilder sb = new StringBuilder(a.e(simpleName.length() + 2, localizedMessage));
        sb.append(simpleName);
        sb.append(": ");
        sb.append(localizedMessage);
        this.zacn.setFailedResult(new Status(10, sb.toString()));
    }

    @Override // com.google.android.gms.common.api.internal.zab
    public final void zaa(zaab zaabVar, boolean z2) {
        zaabVar.zaa(this.zacn, z2);
    }
}
