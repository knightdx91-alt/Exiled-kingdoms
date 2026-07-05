package com.google.android.gms.common.api;

import android.os.Looper;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.internal.BasePendingResult;
import com.google.android.gms.common.api.internal.OptionalPendingResultImpl;
import com.google.android.gms.common.api.internal.StatusPendingResult;
import com.google.android.gms.common.internal.Preconditions;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@KeepForSdk
public final class PendingResults {

    private static final class zaa<R extends Result> extends BasePendingResult<R> {
        private final R zach;

        public zaa(R r2) {
            super(Looper.getMainLooper());
            this.zach = r2;
        }

        @Override // com.google.android.gms.common.api.internal.BasePendingResult
        protected final R createFailedResult(Status status) {
            if (status.getStatusCode() == this.zach.getStatus().getStatusCode()) {
                return this.zach;
            }
            throw new UnsupportedOperationException("Creating failed results is not supported");
        }
    }

    private static final class zab<R extends Result> extends BasePendingResult<R> {
        private final R zaci;

        public zab(GoogleApiClient googleApiClient, R r2) {
            super(googleApiClient);
            this.zaci = r2;
        }

        @Override // com.google.android.gms.common.api.internal.BasePendingResult
        protected final R createFailedResult(Status status) {
            return this.zaci;
        }
    }

    private static final class zac<R extends Result> extends BasePendingResult<R> {
        public zac(GoogleApiClient googleApiClient) {
            super(googleApiClient);
        }

        @Override // com.google.android.gms.common.api.internal.BasePendingResult
        protected final R createFailedResult(Status status) {
            throw new UnsupportedOperationException("Creating failed results is not supported");
        }
    }

    @KeepForSdk
    private PendingResults() {
    }

    public static PendingResult<Status> canceledPendingResult() {
        StatusPendingResult statusPendingResult = new StatusPendingResult(Looper.getMainLooper());
        statusPendingResult.cancel();
        return statusPendingResult;
    }

    @KeepForSdk
    public static <R extends Result> PendingResult<R> immediateFailedResult(R r2, GoogleApiClient googleApiClient) {
        Preconditions.checkNotNull(r2, "Result must not be null");
        Preconditions.checkArgument(!r2.getStatus().isSuccess(), "Status code must not be SUCCESS");
        zab zabVar = new zab(googleApiClient, r2);
        zabVar.setResult(r2);
        return zabVar;
    }

    @KeepForSdk
    public static PendingResult<Status> immediatePendingResult(Status status) {
        Preconditions.checkNotNull(status, "Result must not be null");
        StatusPendingResult statusPendingResult = new StatusPendingResult(Looper.getMainLooper());
        statusPendingResult.setResult(status);
        return statusPendingResult;
    }

    public static <R extends Result> PendingResult<R> canceledPendingResult(R r2) {
        Preconditions.checkNotNull(r2, "Result must not be null");
        Preconditions.checkArgument(r2.getStatus().getStatusCode() == 16, "Status code must be CommonStatusCodes.CANCELED");
        zaa zaaVar = new zaa(r2);
        zaaVar.cancel();
        return zaaVar;
    }

    @KeepForSdk
    public static PendingResult<Status> immediatePendingResult(Status status, GoogleApiClient googleApiClient) {
        Preconditions.checkNotNull(status, "Result must not be null");
        StatusPendingResult statusPendingResult = new StatusPendingResult(googleApiClient);
        statusPendingResult.setResult(status);
        return statusPendingResult;
    }

    @KeepForSdk
    public static <R extends Result> OptionalPendingResult<R> immediatePendingResult(R r2) {
        Preconditions.checkNotNull(r2, "Result must not be null");
        zac zacVar = new zac(null);
        zacVar.setResult(r2);
        return new OptionalPendingResultImpl(zacVar);
    }

    @KeepForSdk
    public static <R extends Result> OptionalPendingResult<R> immediatePendingResult(R r2, GoogleApiClient googleApiClient) {
        Preconditions.checkNotNull(r2, "Result must not be null");
        zac zacVar = new zac(googleApiClient);
        zacVar.setResult(r2);
        return new OptionalPendingResultImpl(zacVar);
    }
}
