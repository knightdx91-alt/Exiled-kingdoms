package com.google.android.gms.common.api;

import com.google.android.gms.common.api.internal.BasePendingResult;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class Batch extends BasePendingResult<BatchResult> {
    private final Object mLock;
    private int zaaz;
    private boolean zaba;
    private boolean zabb;
    private final PendingResult<?>[] zabc;

    public static final class Builder {
        private List<PendingResult<?>> zabe = new ArrayList();
        private GoogleApiClient zabf;

        public Builder(GoogleApiClient googleApiClient) {
            this.zabf = googleApiClient;
        }

        public final <R extends Result> BatchResultToken<R> add(PendingResult<R> pendingResult) {
            BatchResultToken<R> batchResultToken = new BatchResultToken<>(this.zabe.size());
            this.zabe.add(pendingResult);
            return batchResultToken;
        }

        public final Batch build() {
            return new Batch(this.zabe, this.zabf, null);
        }
    }

    private Batch(List<PendingResult<?>> list, GoogleApiClient googleApiClient) {
        super(googleApiClient);
        this.mLock = new Object();
        int size = list.size();
        this.zaaz = size;
        PendingResult<?>[] pendingResultArr = new PendingResult[size];
        this.zabc = pendingResultArr;
        if (list.isEmpty()) {
            setResult(new BatchResult(Status.RESULT_SUCCESS, pendingResultArr));
            return;
        }
        for (int i2 = 0; i2 < list.size(); i2++) {
            PendingResult<?> pendingResult = list.get(i2);
            this.zabc[i2] = pendingResult;
            pendingResult.addStatusListener(new zaa(this));
        }
    }

    static /* synthetic */ boolean zab(Batch batch, boolean z2) {
        batch.zaba = true;
        return true;
    }

    @Override // com.google.android.gms.common.api.internal.BasePendingResult, com.google.android.gms.common.api.PendingResult
    public final void cancel() {
        super.cancel();
        for (PendingResult<?> pendingResult : this.zabc) {
            pendingResult.cancel();
        }
    }

    @Override // com.google.android.gms.common.api.internal.BasePendingResult
    public final BatchResult createFailedResult(Status status) {
        return new BatchResult(status, this.zabc);
    }

    static /* synthetic */ boolean zaa(Batch batch, boolean z2) {
        batch.zabb = true;
        return true;
    }

    static /* synthetic */ int zab(Batch batch) {
        int i2 = batch.zaaz;
        batch.zaaz = i2 - 1;
        return i2;
    }

    /* synthetic */ Batch(List list, GoogleApiClient googleApiClient, zaa zaaVar) {
        this(list, googleApiClient);
    }
}
