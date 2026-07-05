package com.badlogic.gdx.utils;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;

/* JADX INFO: compiled from: Timer.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class q0 {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    static final Object f1902b = new Object();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    static b f1903c;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final com.badlogic.gdx.utils.a<a> f1904a = new com.badlogic.gdx.utils.a<>(false, 8);

    /* JADX INFO: compiled from: Timer.java */
    public static abstract class a implements Runnable {
        final Application app;
        long executeTimeMillis;
        long intervalMillis;
        int repeatCount;
        volatile q0 timer;

        public a() {
            Application application = Gdx.app;
            this.app = application;
            if (application == null) {
                throw new IllegalStateException("Gdx.app not available.");
            }
        }

        public void cancel() {
            q0 q0Var = this.timer;
            if (q0Var == null) {
                synchronized (this) {
                    this.executeTimeMillis = 0L;
                    this.timer = null;
                }
            } else {
                synchronized (q0Var) {
                    synchronized (this) {
                        this.executeTimeMillis = 0L;
                        this.timer = null;
                        q0Var.f1904a.q(this, true);
                    }
                }
            }
        }

        public synchronized long getExecuteTimeMillis() {
            return this.executeTimeMillis;
        }

        public boolean isScheduled() {
            return this.timer != null;
        }

        @Override // java.lang.Runnable
        public abstract void run();
    }

    /* JADX INFO: compiled from: Timer.java */
    static class b implements Runnable, com.badlogic.gdx.k {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final Application f1906c;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        q0 f1908e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        long f1909f;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final com.badlogic.gdx.utils.a<q0> f1907d = new com.badlogic.gdx.utils.a<>(true, 1);

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final com.badlogic.gdx.d f1905b = Gdx.files;

        public b() {
            Application application = Gdx.app;
            this.f1906c = application;
            application.addLifecycleListener(this);
            resume();
            Thread thread = new Thread(this, "Timer");
            thread.setDaemon(true);
            thread.start();
        }

        @Override // com.badlogic.gdx.k
        public final void dispose() {
            Object obj = q0.f1902b;
            synchronized (obj) {
                try {
                    if (q0.f1903c == this) {
                        q0.f1903c = null;
                    }
                    this.f1907d.clear();
                    obj.notifyAll();
                } catch (Throwable th) {
                    throw th;
                }
            }
            this.f1906c.removeLifecycleListener(this);
        }

        @Override // com.badlogic.gdx.k
        public final void pause() {
            Object obj = q0.f1902b;
            synchronized (obj) {
                this.f1909f = System.nanoTime() / 1000000;
                obj.notifyAll();
            }
        }

        @Override // com.badlogic.gdx.k
        public final void resume() {
            synchronized (q0.f1902b) {
                try {
                    long jNanoTime = (System.nanoTime() / 1000000) - this.f1909f;
                    int i2 = this.f1907d.f1750b;
                    for (int i3 = 0; i3 < i2; i3++) {
                        this.f1907d.get(i3).a(jNanoTime);
                    }
                    this.f1909f = 0L;
                    q0.f1902b.notifyAll();
                } catch (Throwable th) {
                    throw th;
                }
            }
        }

        @Override // java.lang.Runnable
        public final void run() {
            while (true) {
                synchronized (q0.f1902b) {
                    try {
                        if (q0.f1903c != this || this.f1905b != Gdx.files) {
                            break;
                        }
                        long jF = 5000;
                        if (this.f1909f == 0) {
                            long jNanoTime = System.nanoTime() / 1000000;
                            int i2 = this.f1907d.f1750b;
                            for (int i3 = 0; i3 < i2; i3++) {
                                try {
                                    jF = this.f1907d.get(i3).f(jNanoTime, jF);
                                } catch (Throwable th) {
                                    throw new m("Task failed: " + this.f1907d.get(i3).getClass().getName(), th);
                                }
                            }
                        }
                        if (q0.f1903c != this || this.f1905b != Gdx.files) {
                            break;
                        } else if (jF > 0) {
                            try {
                                q0.f1902b.wait(jF);
                            } catch (InterruptedException unused) {
                            }
                        }
                        dispose();
                    } finally {
                    }
                }
            }
            dispose();
        }
    }

    public q0() {
        Object obj = f1902b;
        synchronized (obj) {
            try {
                com.badlogic.gdx.utils.a<q0> aVar = e().f1907d;
                if (aVar.e(this, true)) {
                    return;
                }
                aVar.a(this);
                obj.notifyAll();
            } finally {
            }
        }
    }

    public static q0 b() {
        q0 q0Var;
        synchronized (f1902b) {
            try {
                b bVarE = e();
                if (bVarE.f1908e == null) {
                    bVarE.f1908e = new q0();
                }
                q0Var = bVarE.f1908e;
            } catch (Throwable th) {
                throw th;
            }
        }
        return q0Var;
    }

    public static void c(a aVar, float f2, float f3) {
        b().d(aVar, f2, f3, -1);
    }

    private static b e() {
        b bVar;
        synchronized (f1902b) {
            try {
                b bVar2 = f1903c;
                if (bVar2 == null || bVar2.f1905b != Gdx.files) {
                    if (bVar2 != null) {
                        bVar2.dispose();
                    }
                    f1903c = new b();
                }
                bVar = f1903c;
            } catch (Throwable th) {
                throw th;
            }
        }
        return bVar;
    }

    public final synchronized void a(long j2) {
        int i2 = this.f1904a.f1750b;
        for (int i3 = 0; i3 < i2; i3++) {
            a aVar = this.f1904a.get(i3);
            synchronized (aVar) {
                aVar.executeTimeMillis += j2;
            }
        }
    }

    public final void d(a aVar, float f2, float f3, int i2) {
        Object obj = f1902b;
        synchronized (obj) {
            synchronized (this) {
                synchronized (aVar) {
                    if (aVar.timer != null) {
                        throw new IllegalArgumentException("The same task may not be scheduled twice.");
                    }
                    aVar.timer = this;
                    long jNanoTime = System.nanoTime() / 1000000;
                    long j2 = ((long) (f2 * 1000.0f)) + jNanoTime;
                    long j3 = f1903c.f1909f;
                    if (j3 > 0) {
                        j2 -= jNanoTime - j3;
                    }
                    aVar.executeTimeMillis = j2;
                    aVar.intervalMillis = (long) (f3 * 1000.0f);
                    aVar.repeatCount = i2;
                    this.f1904a.a(aVar);
                }
            }
            obj.notifyAll();
        }
    }

    final synchronized long f(long j2, long j3) {
        int i2 = this.f1904a.f1750b;
        int i3 = 0;
        while (i3 < i2) {
            a aVar = this.f1904a.get(i3);
            synchronized (aVar) {
                try {
                    long j4 = aVar.executeTimeMillis;
                    if (j4 > j2) {
                        j3 = Math.min(j3, j4 - j2);
                    } else {
                        if (aVar.repeatCount == 0) {
                            aVar.timer = null;
                            this.f1904a.o(i3);
                            i3--;
                            i2--;
                        } else {
                            long j5 = aVar.intervalMillis;
                            aVar.executeTimeMillis = j2 + j5;
                            j3 = Math.min(j3, j5);
                            int i4 = aVar.repeatCount;
                            if (i4 > 0) {
                                aVar.repeatCount = i4 - 1;
                            }
                        }
                        aVar.app.postRunnable(aVar);
                    }
                } finally {
                }
            }
            i3++;
        }
        return j3;
    }
}
