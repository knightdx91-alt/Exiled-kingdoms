package e;

import android.graphics.Typeface;

/* JADX INFO: compiled from: ResourcesCompat.java */
/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
final class b implements Runnable {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final /* synthetic */ Typeface f2177b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final /* synthetic */ d f2178c;

    b(d dVar, Typeface typeface) {
        this.f2178c = dVar;
        this.f2177b = typeface;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f2178c.c(this.f2177b);
    }
}
