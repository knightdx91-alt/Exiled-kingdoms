package com.google.android.gms.tasks;

import com.google.android.gms.common.internal.Preconditions;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class Tasks {

    private static final class zza implements zzb {
        private final CountDownLatch zzaf;

        private zza() {
            this.zzaf = new CountDownLatch(1);
        }

        public final void await() throws InterruptedException {
            this.zzaf.await();
        }

        @Override // com.google.android.gms.tasks.OnCanceledListener
        public final void onCanceled() {
            this.zzaf.countDown();
        }

        @Override // com.google.android.gms.tasks.OnFailureListener
        public final void onFailure(Exception exc) {
            this.zzaf.countDown();
        }

        @Override // com.google.android.gms.tasks.OnSuccessListener
        public final void onSuccess(Object obj) {
            this.zzaf.countDown();
        }

        public final boolean await(long j2, TimeUnit timeUnit) {
            return this.zzaf.await(j2, timeUnit);
        }

        /* synthetic */ zza(zzv zzvVar) {
            this();
        }
    }

    interface zzb extends OnCanceledListener, OnFailureListener, OnSuccessListener<Object> {
    }

    private static final class zzc implements zzb {
        private final Object mLock = new Object();
        private final zzu<Void> zza;
        private Exception zzab;
        private final int zzag;
        private int zzah;
        private int zzai;
        private int zzaj;
        private boolean zzak;

        public zzc(int i2, zzu<Void> zzuVar) {
            this.zzag = i2;
            this.zza = zzuVar;
        }

        private final void zzf() {
            if (this.zzah + this.zzai + this.zzaj == this.zzag) {
                if (this.zzab == null) {
                    if (this.zzak) {
                        this.zza.zza();
                        return;
                    } else {
                        this.zza.setResult(null);
                        return;
                    }
                }
                zzu<Void> zzuVar = this.zza;
                int i2 = this.zzai;
                int i3 = this.zzag;
                StringBuilder sb = new StringBuilder(54);
                sb.append(i2);
                sb.append(" out of ");
                sb.append(i3);
                sb.append(" underlying tasks failed");
                zzuVar.setException(new ExecutionException(sb.toString(), this.zzab));
            }
        }

        @Override // com.google.android.gms.tasks.OnCanceledListener
        public final void onCanceled() {
            synchronized (this.mLock) {
                this.zzaj++;
                this.zzak = true;
                zzf();
            }
        }

        @Override // com.google.android.gms.tasks.OnFailureListener
        public final void onFailure(Exception exc) {
            synchronized (this.mLock) {
                this.zzai++;
                this.zzab = exc;
                zzf();
            }
        }

        @Override // com.google.android.gms.tasks.OnSuccessListener
        public final void onSuccess(Object obj) {
            synchronized (this.mLock) {
                this.zzah++;
                zzf();
            }
        }
    }

    private Tasks() {
    }

    public static <TResult> TResult await(Task<TResult> task) throws InterruptedException {
        Preconditions.checkNotMainThread();
        Preconditions.checkNotNull(task, "Task must not be null");
        if (task.isComplete()) {
            return (TResult) zzb(task);
        }
        zza zzaVar = new zza(null);
        zza(task, zzaVar);
        zzaVar.await();
        return (TResult) zzb(task);
    }

    public static <TResult> Task<TResult> call(Callable<TResult> callable) {
        return call(TaskExecutors.MAIN_THREAD, callable);
    }

    public static <TResult> Task<TResult> forCanceled() {
        zzu zzuVar = new zzu();
        zzuVar.zza();
        return zzuVar;
    }

    public static <TResult> Task<TResult> forException(Exception exc) {
        zzu zzuVar = new zzu();
        zzuVar.setException(exc);
        return zzuVar;
    }

    public static <TResult> Task<TResult> forResult(TResult tresult) {
        zzu zzuVar = new zzu();
        zzuVar.setResult(tresult);
        return zzuVar;
    }

    public static Task<Void> whenAll(Collection<? extends Task<?>> collection) {
        if (collection.isEmpty()) {
            return forResult(null);
        }
        Iterator<? extends Task<?>> it = collection.iterator();
        while (it.hasNext()) {
            if (it.next() == null) {
                throw new NullPointerException("null tasks are not accepted");
            }
        }
        zzu zzuVar = new zzu();
        zzc zzcVar = new zzc(collection.size(), zzuVar);
        Iterator<? extends Task<?>> it2 = collection.iterator();
        while (it2.hasNext()) {
            zza(it2.next(), zzcVar);
        }
        return zzuVar;
    }

    public static Task<List<Task<?>>> whenAllComplete(Collection<? extends Task<?>> collection) {
        return whenAll(collection).continueWithTask(new zzx(collection));
    }

    public static <TResult> Task<List<TResult>> whenAllSuccess(Collection<? extends Task<?>> collection) {
        return (Task<List<TResult>>) whenAll(collection).continueWith(new zzw(collection));
    }

    private static void zza(Task<?> task, zzb zzbVar) {
        Executor executor = TaskExecutors.zzw;
        task.addOnSuccessListener(executor, zzbVar);
        task.addOnFailureListener(executor, zzbVar);
        task.addOnCanceledListener(executor, zzbVar);
    }

    private static <TResult> TResult zzb(Task<TResult> task) throws ExecutionException {
        if (task.isSuccessful()) {
            return task.getResult();
        }
        if (task.isCanceled()) {
            throw new CancellationException("Task is already canceled");
        }
        throw new ExecutionException(task.getException());
    }

    public static <TResult> Task<TResult> call(Executor executor, Callable<TResult> callable) {
        Preconditions.checkNotNull(executor, "Executor must not be null");
        Preconditions.checkNotNull(callable, "Callback must not be null");
        zzu zzuVar = new zzu();
        executor.execute(new zzv(zzuVar, callable));
        return zzuVar;
    }

    public static Task<List<Task<?>>> whenAllComplete(Task<?>... taskArr) {
        return whenAllComplete(Arrays.asList(taskArr));
    }

    public static <TResult> Task<List<TResult>> whenAllSuccess(Task<?>... taskArr) {
        return whenAllSuccess(Arrays.asList(taskArr));
    }

    public static <TResult> TResult await(Task<TResult> task, long j2, TimeUnit timeUnit) throws TimeoutException {
        Preconditions.checkNotMainThread();
        Preconditions.checkNotNull(task, "Task must not be null");
        Preconditions.checkNotNull(timeUnit, "TimeUnit must not be null");
        if (task.isComplete()) {
            return (TResult) zzb(task);
        }
        zza zzaVar = new zza(null);
        zza(task, zzaVar);
        if (zzaVar.await(j2, timeUnit)) {
            return (TResult) zzb(task);
        }
        throw new TimeoutException("Timed out waiting for Task");
    }

    public static Task<Void> whenAll(Task<?>... taskArr) {
        if (taskArr.length == 0) {
            return forResult(null);
        }
        return whenAll(Arrays.asList(taskArr));
    }
}
