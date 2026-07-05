package g0;

import java.util.concurrent.ThreadFactory;

/* JADX INFO: compiled from: AsyncExecutor.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class a implements ThreadFactory {
    @Override // java.util.concurrent.ThreadFactory
    public final Thread newThread(Runnable runnable) {
        Thread thread = new Thread(runnable, "AssetManager");
        thread.setDaemon(true);
        return thread;
    }
}
