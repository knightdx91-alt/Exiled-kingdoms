package g0;

import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.GdxRuntimeException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: AsyncExecutor.java */
/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public final class b implements Disposable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final ExecutorService f2211a = Executors.newFixedThreadPool(1, new g0.a());

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: compiled from: AsyncExecutor.java */
    final class a<T> implements Callable<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ d f2212a;

        a(d dVar) {
            this.f2212a = dVar;
        }

        @Override // java.util.concurrent.Callable
        public final T call() {
            this.f2212a.call();
            return null;
        }
    }

    public final <T> c<T> a(d<T> dVar) {
        ExecutorService executorService = this.f2211a;
        if (executorService.isShutdown()) {
            throw new GdxRuntimeException ("Cannot run tasks on an executor that has been shutdown (disposed)");
        }
        return new c<>(executorService.submit(new a(dVar)));
    }

    @Override // com.badlogic.gdx.utils.i
    public final void dispose() {
        ExecutorService executorService = this.f2211a;
        executorService.shutdown();
        try {
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
        } catch (InterruptedException e2) {
            throw new GdxRuntimeException ("Couldn't shutdown loading thread", (Throwable) e2);
        }
    }
}
