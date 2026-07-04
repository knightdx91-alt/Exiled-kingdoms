package e;

import android.graphics.Typeface;
import android.os.Handler;
import android.os.Looper;

/* JADX INFO: compiled from: ResourcesCompat.java */
/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public abstract class d {
    public final void a(int i2) {
        new Handler(Looper.getMainLooper()).post(new c(this, i2));
    }

    public final void b(Typeface typeface) {
        new Handler(Looper.getMainLooper()).post(new b(this, typeface));
    }

    public abstract void c(Typeface typeface);
}
