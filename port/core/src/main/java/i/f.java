package i;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/* JADX INFO: compiled from: SelfDestructiveThread.java */
/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public final class f {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private HandlerThread f2244b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Handler f2245c;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Object f2243a = new Object();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private Handler.Callback f2246d = new a();

    /* JADX INFO: compiled from: SelfDestructiveThread.java */
    final class a implements Handler.Callback {
        a() {
        }

        @Override // android.os.Handler.Callback
        public final boolean handleMessage(Message message) {
            int i2 = message.what;
            f fVar = f.this;
            if (i2 == 0) {
                f.b(fVar);
                return true;
            }
            if (i2 != 1) {
                return true;
            }
            f.a(fVar, (Runnable) message.obj);
            return true;
        }
    }

    /* JADX INFO: compiled from: SelfDestructiveThread.java */
    final class b implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ Callable f2248b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ Handler f2249c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ d f2250d;

        /* JADX INFO: compiled from: SelfDestructiveThread.java */
        final class a implements Runnable {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ Object f2251b;

            a(Object obj) {
                this.f2251b = obj;
            }

            @Override // java.lang.Runnable
            public final void run() {
                b.this.f2250d.a(this.f2251b);
            }
        }

        b(Callable callable, Handler handler, d dVar) {
            this.f2248b = callable;
            this.f2249c = handler;
            this.f2250d = dVar;
        }

        @Override // java.lang.Runnable
        public final void run() {
            Object objCall;
            try {
                objCall = this.f2248b.call();
            } catch (Exception unused) {
                objCall = null;
            }
            this.f2249c.post(new a(objCall));
        }
    }

    /* JADX INFO: compiled from: SelfDestructiveThread.java */
    final class c implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ AtomicReference f2253b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ Callable f2254c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ ReentrantLock f2255d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final /* synthetic */ AtomicBoolean f2256e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        final /* synthetic */ Condition f2257f;

        c(AtomicReference atomicReference, Callable callable, ReentrantLock reentrantLock, AtomicBoolean atomicBoolean, Condition condition) {
            this.f2253b = atomicReference;
            this.f2254c = callable;
            this.f2255d = reentrantLock;
            this.f2256e = atomicBoolean;
            this.f2257f = condition;
        }

        @Override // java.lang.Runnable
        public final void run() {
            try {
                this.f2253b.set(this.f2254c.call());
            } catch (Exception unused) {
            }
            ReentrantLock reentrantLock = this.f2255d;
            reentrantLock.lock();
            try {
                this.f2256e.set(false);
                this.f2257f.signal();
            } finally {
                reentrantLock.unlock();
            }
        }
    }

    /* JADX INFO: compiled from: SelfDestructiveThread.java */
    public interface d<T> {
        void a(T t2);
    }

    static void a(f fVar, Runnable runnable) {
        fVar.getClass();
        runnable.run();
        synchronized (fVar.f2243a) {
            fVar.f2245c.removeMessages(0);
            Handler handler = fVar.f2245c;
            handler.sendMessageDelayed(handler.obtainMessage(0), 10000);
        }
    }

    static void b(f fVar) {
        synchronized (fVar.f2243a) {
            try {
                if (fVar.f2245c.hasMessages(1)) {
                    return;
                }
                fVar.f2244b.quit();
                fVar.f2244b = null;
                fVar.f2245c = null;
            } finally {
            }
        }
    }

    private void c(Runnable runnable) {
        synchronized (this.f2243a) {
            try {
                if (this.f2244b == null) {
                    HandlerThread handlerThread = new HandlerThread("fonts", 10);
                    this.f2244b = handlerThread;
                    handlerThread.start();
                    this.f2245c = new Handler(this.f2244b.getLooper(), this.f2246d);
                }
                this.f2245c.removeMessages(0);
                Handler handler = this.f2245c;
                handler.sendMessage(handler.obtainMessage(1, runnable));
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final <T> void d(Callable<T> callable, d<T> dVar) {
        c(new b(callable, new Handler(), dVar));
    }

    public final <T> T e(Callable<T> callable, int i2) {
        ReentrantLock reentrantLock = new ReentrantLock();
        Condition conditionNewCondition = reentrantLock.newCondition();
        AtomicReference atomicReference = new AtomicReference();
        AtomicBoolean atomicBoolean = new AtomicBoolean(true);
        c(new c(atomicReference, callable, reentrantLock, atomicBoolean, conditionNewCondition));
        reentrantLock.lock();
        try {
            if (!atomicBoolean.get()) {
                return (T) atomicReference.get();
            }
            long nanos = TimeUnit.MILLISECONDS.toNanos(i2);
            do {
                try {
                    nanos = conditionNewCondition.awaitNanos(nanos);
                } catch (InterruptedException unused) {
                }
                if (!atomicBoolean.get()) {
                    return (T) atomicReference.get();
                }
            } while (nanos > 0);
            throw new InterruptedException("timeout");
        } finally {
            reentrantLock.unlock();
        }
    }
}
