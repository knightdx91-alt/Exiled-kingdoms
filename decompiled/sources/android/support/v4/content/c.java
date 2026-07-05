package android.support.v4.content;

import android.os.Binder;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.util.Log;
import com.badlogic.gdx.graphics.VertexAttributes;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: compiled from: ModernAsyncTask.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
abstract class c<Params, Progress, Result> {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final ThreadPoolExecutor f364g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private static e f365h;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final b f366b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final FutureTask<Result> f367c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private volatile f f368d = f.f376a;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final AtomicBoolean f369e = new AtomicBoolean();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final AtomicBoolean f370f = new AtomicBoolean();

    /* JADX INFO: compiled from: ModernAsyncTask.java */
    static class a implements ThreadFactory {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final AtomicInteger f371a = new AtomicInteger(1);

        a() {
        }

        @Override // java.util.concurrent.ThreadFactory
        public final Thread newThread(Runnable runnable) {
            return new Thread(runnable, "ModernAsyncTask #" + this.f371a.getAndIncrement());
        }
    }

    /* JADX INFO: compiled from: ModernAsyncTask.java */
    final class b extends g<Params, Result> {
        b() {
        }

        /* JADX WARN: Type inference fix 'apply assigned field type' failed
        java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
        	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
        	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
        	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
         */
        @Override // java.util.concurrent.Callable
        public final Result call() {
            c cVar = c.this;
            cVar.f370f.set(true);
            Result result = null;
            try {
                Process.setThreadPriority(10);
                result = (Result) cVar.d();
                Binder.flushPendingCommands();
                return result;
            } finally {
            }
        }
    }

    /* JADX INFO: renamed from: android.support.v4.content.c$c, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ModernAsyncTask.java */
    final class C0005c extends FutureTask<Result> {
        C0005c(Callable callable) {
            super(callable);
        }

        @Override // java.util.concurrent.FutureTask
        protected final void done() {
            c cVar = c.this;
            try {
                cVar.j(get());
            } catch (InterruptedException e2) {
                Log.w("AsyncTask", e2);
            } catch (CancellationException unused) {
                cVar.j(null);
            } catch (ExecutionException e3) {
                throw new RuntimeException("An error occurred while executing doInBackground()", e3.getCause());
            } catch (Throwable th) {
                throw new RuntimeException("An error occurred while executing doInBackground()", th);
            }
        }
    }

    /* JADX INFO: compiled from: ModernAsyncTask.java */
    private static class d<Data> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final c f374a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final Data[] f375b;

        d(c cVar, Data... dataArr) {
            this.f374a = cVar;
            this.f375b = dataArr;
        }
    }

    /* JADX INFO: compiled from: ModernAsyncTask.java */
    private static class e extends Handler {
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference fix 'apply assigned field type' failed
        java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
        	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
        	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
        	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
         */
        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            d dVar = (d) message.obj;
            int i2 = message.what;
            if (i2 == 1) {
                dVar.f374a.f(dVar.f375b[0]);
            } else {
                if (i2 != 2) {
                    return;
                }
                dVar.f374a.getClass();
            }
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* JADX INFO: compiled from: ModernAsyncTask.java */
    public static final class f {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final f f376a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public static final f f377b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public static final f f378c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private static final /* synthetic */ f[] f379d;

        static {
            f fVar = new f("PENDING", 0);
            f376a = fVar;
            f fVar2 = new f("RUNNING", 1);
            f377b = fVar2;
            f fVar3 = new f("FINISHED", 2);
            f378c = fVar3;
            f379d = new f[]{fVar, fVar2, fVar3};
        }

        private f() {
            throw null;
        }

        public static f valueOf(String str) {
            return (f) Enum.valueOf(f.class, str);
        }

        public static f[] values() {
            return (f[]) f379d.clone();
        }
    }

    /* JADX INFO: compiled from: ModernAsyncTask.java */
    private static abstract class g<Params, Result> implements Callable<Result> {
    }

    static {
        a aVar = new a();
        f364g = new ThreadPoolExecutor(5, VertexAttributes.Usage.Tangent, 1L, TimeUnit.SECONDS, new LinkedBlockingQueue(10), aVar);
    }

    c() {
        b bVar = new b();
        this.f366b = bVar;
        this.f367c = new C0005c(bVar);
    }

    public final boolean c() {
        this.f369e.set(true);
        return this.f367c.cancel(false);
    }

    protected abstract Object d();

    public final void e(Executor executor) {
        if (this.f368d == f.f376a) {
            this.f368d = f.f377b;
            this.f366b.getClass();
            executor.execute(this.f367c);
        } else {
            int iOrdinal = this.f368d.ordinal();
            if (iOrdinal == 1) {
                throw new IllegalStateException("Cannot execute task: the task is already running.");
            }
            if (iOrdinal == 2) {
                throw new IllegalStateException("Cannot execute task: the task has already been executed (a task can be executed only once)");
            }
            throw new IllegalStateException("We should never reach this state");
        }
    }

    final void f(Result result) {
        if (this.f369e.get()) {
            g(result);
        } else {
            h(result);
        }
        this.f368d = f.f378c;
    }

    protected void g(Result result) {
    }

    protected void h(Result result) {
    }

    final void i(Object obj) {
        e eVar;
        synchronized (c.class) {
            try {
                if (f365h == null) {
                    f365h = new e(Looper.getMainLooper());
                }
                eVar = f365h;
            } catch (Throwable th) {
                throw th;
            }
        }
        eVar.obtainMessage(1, new d(this, obj)).sendToTarget();
    }

    final void j(Result result) {
        if (this.f370f.get()) {
            return;
        }
        i(result);
    }
}
