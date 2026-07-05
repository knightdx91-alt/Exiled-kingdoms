package e;

import android.graphics.Typeface;
import android.os.Handler;
import android.os.Looper;

/* JADX INFO: compiled from: ResourcesCompat.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public abstract class d {
    public final void a(int i2) {
        new Handler(Looper.getMainLooper()).post(new c(this, i2));
    }

    public final void b(Typeface typeface) {
        new Handler(Looper.getMainLooper()).post(new b(this, typeface));
    }

    public abstract void c(Typeface typeface);
}
