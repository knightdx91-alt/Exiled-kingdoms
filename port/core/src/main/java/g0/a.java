package g0;

import java.util.concurrent.ThreadFactory;

/* JADX INFO: compiled from: AsyncExecutor.java */
/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
final class a implements ThreadFactory {
    @Override // java.util.concurrent.ThreadFactory
    public final Thread newThread(Runnable runnable) {
        Thread thread = new Thread(runnable, "AssetManager");
        thread.setDaemon(true);
        return thread;
    }
}
