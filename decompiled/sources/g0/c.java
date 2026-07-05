package g0;

import com.badlogic.gdx.utils.m;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/* JADX INFO: compiled from: AsyncResult.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class c<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Future<T> f2213a;

    c(Future<T> future) {
        this.f2213a = future;
    }

    public final void a() {
        try {
            this.f2213a.get();
        } catch (InterruptedException unused) {
        } catch (ExecutionException e2) {
            throw new m(e2.getCause());
        }
    }

    public final boolean b() {
        return this.f2213a.isDone();
    }
}
