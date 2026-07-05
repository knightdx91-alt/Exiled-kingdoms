package android.support.v4.view;

import android.view.WindowInsets;

/* JADX INFO: compiled from: WindowInsetsCompat.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final WindowInsets f613a;

    private o(WindowInsets windowInsets) {
        this.f613a = windowInsets;
    }

    static Object f(o oVar) {
        if (oVar == null) {
            return null;
        }
        return oVar.f613a;
    }

    static o g(WindowInsets windowInsets) {
        if (windowInsets == null) {
            return null;
        }
        return new o(windowInsets);
    }

    public final int a() {
        return this.f613a.getSystemWindowInsetBottom();
    }

    public final int b() {
        return this.f613a.getSystemWindowInsetLeft();
    }

    public final int c() {
        return this.f613a.getSystemWindowInsetRight();
    }

    public final int d() {
        return this.f613a.getSystemWindowInsetTop();
    }

    public final o e(int i2, int i3, int i4, int i5) {
        return new o(this.f613a.replaceSystemWindowInsets(i2, i3, i4, i5));
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || o.class != obj.getClass()) {
            return false;
        }
        WindowInsets windowInsets = ((o) obj).f613a;
        WindowInsets windowInsets2 = this.f613a;
        return windowInsets2 == null ? windowInsets == null : windowInsets2.equals(windowInsets);
    }

    public final int hashCode() {
        WindowInsets windowInsets = this.f613a;
        if (windowInsets == null) {
            return 0;
        }
        return windowInsets.hashCode();
    }
}
