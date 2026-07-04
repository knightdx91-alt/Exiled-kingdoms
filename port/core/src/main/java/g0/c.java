package g0;

import com.badlogic.gdx.utils.GdxRuntimeException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/* JADX INFO: compiled from: AsyncResult.java */
/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public final class c<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Future<T> f2213a;

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
