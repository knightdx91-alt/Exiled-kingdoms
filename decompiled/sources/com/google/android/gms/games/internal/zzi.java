package com.google.android.gms.games.internal;

import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.ApiExceptionUtil;
import com.google.android.gms.common.internal.PendingResultUtil;
import com.google.android.gms.games.AnnotatedData;
import com.google.android.gms.games.GamesClientStatusCodes;
import com.google.android.gms.games.GamesStatusCodes;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class zzi {
    private static final zzr zzim = zzo.zziz;

    public static <R, PendingR extends Result> Task<R> toTask(final PendingResult<PendingR> pendingResult, final PendingResultUtil.ResultConverter<PendingR, R> resultConverter) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        pendingResult.addStatusListener(new PendingResult.StatusListener(pendingResult, taskCompletionSource, resultConverter) { // from class: com.google.android.gms.games.internal.zzk
            private final PendingResult zzin;
            private final TaskCompletionSource zzit;
            private final PendingResultUtil.ResultConverter zziu;

            {
                this.zzin = pendingResult;
                this.zzit = taskCompletionSource;
                this.zziu = resultConverter;
            }

            @Override // com.google.android.gms.common.api.PendingResult.StatusListener
            public final void onComplete(Status status) {
                zzi.zza(this.zzin, this.zzit, this.zziu, status);
            }
        });
        return taskCompletionSource.getTask();
    }

    public static <R, PendingR extends Result> Task<AnnotatedData<R>> zza(PendingResult<PendingR> pendingResult, PendingResultUtil.ResultConverter<PendingR, R> resultConverter) {
        return zza(pendingResult, resultConverter, (zzq) null);
    }

    public static <R extends Releasable, PendingR extends Result> Task<AnnotatedData<R>> zzb(final PendingResult<PendingR> pendingResult, final PendingResultUtil.ResultConverter<PendingR, R> resultConverter) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        pendingResult.addStatusListener(new PendingResult.StatusListener(resultConverter, pendingResult, taskCompletionSource) { // from class: com.google.android.gms.games.internal.zzm
            private final TaskCompletionSource zzip;
            private final PendingResultUtil.ResultConverter zziw;
            private final PendingResult zzix;

            {
                this.zziw = resultConverter;
                this.zzix = pendingResult;
                this.zzip = taskCompletionSource;
            }

            @Override // com.google.android.gms.common.api.PendingResult.StatusListener
            public final void onComplete(Status status) {
                zzi.zza(this.zziw, this.zzix, this.zzip, status);
            }
        });
        return taskCompletionSource.getTask();
    }

    private static Status zzc(Status status) {
        int iZzb = GamesClientStatusCodes.zzb(status.getStatusCode());
        return iZzb != status.getStatusCode() ? GamesStatusCodes.getStatusString(status.getStatusCode()).equals(status.getStatusMessage()) ? GamesClientStatusCodes.zza(iZzb) : new Status(iZzb, status.getStatusMessage()) : status;
    }

    public static <R, PendingR extends Result> Task<AnnotatedData<R>> zza(final PendingResult<PendingR> pendingResult, final PendingResultUtil.ResultConverter<PendingR, R> resultConverter, final zzq<PendingR> zzqVar) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        pendingResult.addStatusListener(new PendingResult.StatusListener(pendingResult, taskCompletionSource, resultConverter, zzqVar) { // from class: com.google.android.gms.games.internal.zzl
            private final PendingResult zzin;
            private final TaskCompletionSource zzit;
            private final PendingResultUtil.ResultConverter zziu;
            private final zzq zziv;

            {
                this.zzin = pendingResult;
                this.zzit = taskCompletionSource;
                this.zziu = resultConverter;
                this.zziv = zzqVar;
            }

            @Override // com.google.android.gms.common.api.PendingResult.StatusListener
            public final void onComplete(Status status) {
                zzi.zza(this.zzin, this.zzit, this.zziu, this.zziv, status);
            }
        });
        return taskCompletionSource.getTask();
    }

    public static <R, PendingR extends Result> Task<R> zza(final PendingResult<PendingR> pendingResult, final zzr zzrVar, final PendingResultUtil.ResultConverter<PendingR, R> resultConverter) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        pendingResult.addStatusListener(new PendingResult.StatusListener(zzrVar, pendingResult, taskCompletionSource, resultConverter) { // from class: com.google.android.gms.games.internal.zzn
            private final TaskCompletionSource zzip;
            private final PendingResultUtil.ResultConverter zziq;
            private final PendingResult zzix;
            private final zzr zziy;

            {
                this.zziy = zzrVar;
                this.zzix = pendingResult;
                this.zzip = taskCompletionSource;
                this.zziq = resultConverter;
            }

            @Override // com.google.android.gms.common.api.PendingResult.StatusListener
            public final void onComplete(Status status) {
                zzi.zza(this.zziy, this.zzix, this.zzip, this.zziq, status);
            }
        });
        return taskCompletionSource.getTask();
    }

    public static <R, PendingR extends Result, ExceptionData> Task<R> zza(final PendingResult<PendingR> pendingResult, final zzr zzrVar, final PendingResultUtil.ResultConverter<PendingR, R> resultConverter, final PendingResultUtil.ResultConverter<PendingR, ExceptionData> resultConverter2, final zzp<ExceptionData> zzpVar) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        pendingResult.addStatusListener(new PendingResult.StatusListener(pendingResult, zzrVar, taskCompletionSource, resultConverter, resultConverter2, zzpVar) { // from class: com.google.android.gms.games.internal.zzj
            private final PendingResult zzin;
            private final zzr zzio;
            private final TaskCompletionSource zzip;
            private final PendingResultUtil.ResultConverter zziq;
            private final PendingResultUtil.ResultConverter zzir;
            private final zzp zzis;

            {
                this.zzin = pendingResult;
                this.zzio = zzrVar;
                this.zzip = taskCompletionSource;
                this.zziq = resultConverter;
                this.zzir = resultConverter2;
                this.zzis = zzpVar;
            }

            @Override // com.google.android.gms.common.api.PendingResult.StatusListener
            public final void onComplete(Status status) {
                zzi.zza(this.zzin, this.zzio, this.zzip, this.zziq, this.zzir, this.zzis, status);
            }
        });
        return taskCompletionSource.getTask();
    }

    static final /* synthetic */ void zza(PendingResult pendingResult, zzr zzrVar, TaskCompletionSource taskCompletionSource, PendingResultUtil.ResultConverter resultConverter, PendingResultUtil.ResultConverter resultConverter2, zzp zzpVar, Status status) {
        Result resultAwait = pendingResult.await(0L, TimeUnit.MILLISECONDS);
        if (zzrVar.zza(status)) {
            taskCompletionSource.setResult(resultConverter.convert(resultAwait));
            return;
        }
        Object objConvert = resultConverter2.convert(resultAwait);
        if (objConvert != null) {
            taskCompletionSource.setException(zzpVar.zza(zzc(status), objConvert));
        } else {
            taskCompletionSource.setException(ApiExceptionUtil.fromStatus(zzc(status)));
        }
    }

    static final /* synthetic */ void zza(PendingResult pendingResult, TaskCompletionSource taskCompletionSource, PendingResultUtil.ResultConverter resultConverter, Status status) {
        Result resultAwait = pendingResult.await(0L, TimeUnit.MILLISECONDS);
        if (status.isSuccess()) {
            taskCompletionSource.setResult(resultConverter.convert(resultAwait));
        } else {
            taskCompletionSource.setException(ApiExceptionUtil.fromStatus(zzc(status)));
        }
    }

    static final /* synthetic */ void zza(PendingResult pendingResult, TaskCompletionSource taskCompletionSource, PendingResultUtil.ResultConverter resultConverter, zzq zzqVar, Status status) {
        boolean z2 = status.getStatusCode() == 3;
        Result resultAwait = pendingResult.await(0L, TimeUnit.MILLISECONDS);
        if (status.isSuccess() || z2) {
            taskCompletionSource.setResult(new AnnotatedData(resultConverter.convert(resultAwait), z2));
            return;
        }
        if (resultAwait != null && zzqVar != null) {
            zzqVar.release(resultAwait);
        }
        taskCompletionSource.setException(ApiExceptionUtil.fromStatus(zzc(status)));
    }

    static final /* synthetic */ void zza(PendingResultUtil.ResultConverter resultConverter, PendingResult pendingResult, TaskCompletionSource taskCompletionSource, Status status) {
        boolean z2 = status.getStatusCode() == 3;
        Releasable releasable = (Releasable) resultConverter.convert(pendingResult.await(0L, TimeUnit.MILLISECONDS));
        if (status.isSuccess() || z2) {
            taskCompletionSource.setResult(new AnnotatedData(releasable, z2));
            return;
        }
        if (releasable != null) {
            releasable.release();
        }
        taskCompletionSource.setException(ApiExceptionUtil.fromStatus(zzc(status)));
    }

    static final /* synthetic */ void zza(zzr zzrVar, PendingResult pendingResult, TaskCompletionSource taskCompletionSource, PendingResultUtil.ResultConverter resultConverter, Status status) {
        if (zzrVar.zza(status)) {
            taskCompletionSource.setResult(resultConverter.convert(pendingResult.await(0L, TimeUnit.MILLISECONDS)));
        } else {
            taskCompletionSource.setException(ApiExceptionUtil.fromStatus(zzc(status)));
        }
    }
}
