package com.google.android.gms.common.api.internal;

import android.os.DeadObjectException;
import android.os.RemoteException;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.GoogleApiManager;
import com.google.android.gms.tasks.TaskCompletionSource;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class zag<ResultT> extends zac {
    private final TaskCompletionSource<ResultT> zacm;
    private final TaskApiCall<Api.AnyClient, ResultT> zacq;
    private final StatusExceptionMapper zacr;

    public zag(int i2, TaskApiCall<Api.AnyClient, ResultT> taskApiCall, TaskCompletionSource<ResultT> taskCompletionSource, StatusExceptionMapper statusExceptionMapper) {
        super(i2);
        this.zacm = taskCompletionSource;
        this.zacq = taskApiCall;
        this.zacr = statusExceptionMapper;
    }

    @Override // com.google.android.gms.common.api.internal.zab
    public final void zaa(GoogleApiManager.zaa<?> zaaVar) throws DeadObjectException {
        try {
            this.zacq.doExecute(zaaVar.zaab(), this.zacm);
        } catch (DeadObjectException e2) {
            throw e2;
        } catch (RemoteException e3) {
            zaa(zab.zaa(e3));
        } catch (RuntimeException e4) {
            zaa(e4);
        }
    }

    @Override // com.google.android.gms.common.api.internal.zac
    public final Feature[] zab(GoogleApiManager.zaa<?> zaaVar) {
        return this.zacq.zabt();
    }

    @Override // com.google.android.gms.common.api.internal.zac
    public final boolean zac(GoogleApiManager.zaa<?> zaaVar) {
        return this.zacq.shouldAutoResolveMissingFeatures();
    }

    @Override // com.google.android.gms.common.api.internal.zab
    public final void zaa(Status status) {
        this.zacm.trySetException(this.zacr.getException(status));
    }

    @Override // com.google.android.gms.common.api.internal.zab
    public final void zaa(RuntimeException runtimeException) {
        this.zacm.trySetException(runtimeException);
    }

    @Override // com.google.android.gms.common.api.internal.zab
    public final void zaa(zaab zaabVar, boolean z2) {
        zaabVar.zaa(this.zacm, z2);
    }
}
