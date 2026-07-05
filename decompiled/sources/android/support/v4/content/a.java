package android.support.v4.content;

import android.content.Context;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.util.k;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: AsyncTaskLoader.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public abstract class a<D> extends b<D> {
    static final boolean DEBUG = false;
    static final String TAG = "AsyncTaskLoader";
    volatile a<D>.RunnableC0003a mCancellingTask;
    private final Executor mExecutor;
    Handler mHandler;
    long mLastLoadCompleteTime;
    volatile a<D>.RunnableC0003a mTask;
    long mUpdateThrottle;

    /* JADX INFO: renamed from: android.support.v4.content.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: AsyncTaskLoader.java */
    final class RunnableC0003a extends c<Void, Void, D> implements Runnable {

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        private final CountDownLatch f361i = new CountDownLatch(1);

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        boolean f362j;

        RunnableC0003a() {
        }

        @Override // android.support.v4.content.c
        protected final Object d() {
            return a.this.onLoadInBackground();
        }

        @Override // android.support.v4.content.c
        protected final void g(D d2) {
            CountDownLatch countDownLatch = this.f361i;
            try {
                a.this.dispatchOnCancelled(this, d2);
            } finally {
                countDownLatch.countDown();
            }
        }

        @Override // android.support.v4.content.c
        protected final void h(D d2) {
            CountDownLatch countDownLatch = this.f361i;
            try {
                a.this.dispatchOnLoadComplete(this, d2);
            } finally {
                countDownLatch.countDown();
            }
        }

        public final void k() {
            try {
                this.f361i.await();
            } catch (InterruptedException unused) {
            }
        }

        @Override // java.lang.Runnable
        public final void run() {
            this.f362j = false;
            a.this.executePendingTask();
        }
    }

    public a(Context context) {
        this(context, c.f364g);
    }

    public void cancelLoadInBackground() {
    }

    void dispatchOnCancelled(a<D>.RunnableC0003a runnableC0003a, D d2) {
        onCanceled(d2);
        if (this.mCancellingTask == runnableC0003a) {
            rollbackContentChanged();
            this.mLastLoadCompleteTime = SystemClock.uptimeMillis();
            this.mCancellingTask = null;
            deliverCancellation();
            executePendingTask();
        }
    }

    void dispatchOnLoadComplete(a<D>.RunnableC0003a runnableC0003a, D d2) {
        if (this.mTask != runnableC0003a) {
            dispatchOnCancelled(runnableC0003a, d2);
            return;
        }
        if (isAbandoned()) {
            onCanceled(d2);
            return;
        }
        commitContentChanged();
        this.mLastLoadCompleteTime = SystemClock.uptimeMillis();
        this.mTask = null;
        deliverResult(d2);
    }

    @Override // android.support.v4.content.b
    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        super.dump(str, fileDescriptor, printWriter, strArr);
        if (this.mTask != null) {
            printWriter.print(str);
            printWriter.print("mTask=");
            printWriter.print(this.mTask);
            printWriter.print(" waiting=");
            printWriter.println(this.mTask.f362j);
        }
        if (this.mCancellingTask != null) {
            printWriter.print(str);
            printWriter.print("mCancellingTask=");
            printWriter.print(this.mCancellingTask);
            printWriter.print(" waiting=");
            printWriter.println(this.mCancellingTask.f362j);
        }
        if (this.mUpdateThrottle != 0) {
            printWriter.print(str);
            printWriter.print("mUpdateThrottle=");
            k.d(this.mUpdateThrottle, printWriter);
            printWriter.print(" mLastLoadCompleteTime=");
            long j2 = this.mLastLoadCompleteTime;
            long jUptimeMillis = SystemClock.uptimeMillis();
            if (j2 == 0) {
                printWriter.print("--");
            } else {
                k.d(j2 - jUptimeMillis, printWriter);
            }
            printWriter.println();
        }
    }

    void executePendingTask() {
        if (this.mCancellingTask != null || this.mTask == null) {
            return;
        }
        if (this.mTask.f362j) {
            this.mTask.f362j = false;
            this.mHandler.removeCallbacks(this.mTask);
        }
        if (this.mUpdateThrottle <= 0 || SystemClock.uptimeMillis() >= this.mLastLoadCompleteTime + this.mUpdateThrottle) {
            this.mTask.e(this.mExecutor);
        } else {
            this.mTask.f362j = true;
            this.mHandler.postAtTime(this.mTask, this.mLastLoadCompleteTime + this.mUpdateThrottle);
        }
    }

    public boolean isLoadInBackgroundCanceled() {
        return this.mCancellingTask != null;
    }

    public abstract D loadInBackground();

    @Override // android.support.v4.content.b
    protected boolean onCancelLoad() {
        if (this.mTask == null) {
            return false;
        }
        if (!this.mStarted) {
            this.mContentChanged = true;
        }
        if (this.mCancellingTask != null) {
            if (this.mTask.f362j) {
                this.mTask.f362j = false;
                this.mHandler.removeCallbacks(this.mTask);
            }
            this.mTask = null;
            return false;
        }
        if (this.mTask.f362j) {
            this.mTask.f362j = false;
            this.mHandler.removeCallbacks(this.mTask);
            this.mTask = null;
            return false;
        }
        boolean zC = this.mTask.c();
        if (zC) {
            this.mCancellingTask = this.mTask;
            cancelLoadInBackground();
        }
        this.mTask = null;
        return zC;
    }

    public void onCanceled(D d2) {
    }

    @Override // android.support.v4.content.b
    protected void onForceLoad() {
        super.onForceLoad();
        cancelLoad();
        this.mTask = new RunnableC0003a();
        executePendingTask();
    }

    protected D onLoadInBackground() {
        return loadInBackground();
    }

    public void setUpdateThrottle(long j2) {
        this.mUpdateThrottle = j2;
        if (j2 != 0) {
            this.mHandler = new Handler();
        }
    }

    public void waitForLoader() {
        a<D>.RunnableC0003a runnableC0003a = this.mTask;
        if (runnableC0003a != null) {
            runnableC0003a.k();
        }
    }

    private a(Context context, Executor executor) {
        super(context);
        this.mLastLoadCompleteTime = -10000L;
        this.mExecutor = executor;
    }
}
