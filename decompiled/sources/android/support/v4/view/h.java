package android.support.v4.view;

import android.support.v4.widget.NestedScrollView;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import java.util.WeakHashMap;

/* JADX INFO: compiled from: ViewCompat.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final i f605a;

    /* JADX INFO: compiled from: ViewCompat.java */
    static class a extends j {
    }

    /* JADX INFO: compiled from: ViewCompat.java */
    static class b extends a {
    }

    /* JADX INFO: compiled from: ViewCompat.java */
    static class c extends b {
    }

    /* JADX INFO: compiled from: ViewCompat.java */
    static class d extends c {
    }

    /* JADX INFO: compiled from: ViewCompat.java */
    static class e extends d {
    }

    /* JADX INFO: compiled from: ViewCompat.java */
    static class f extends e {
    }

    /* JADX INFO: compiled from: ViewCompat.java */
    static class g extends f {
    }

    /* JADX INFO: renamed from: android.support.v4.view.h$h, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ViewCompat.java */
    static class C0012h extends g {
    }

    /* JADX INFO: compiled from: ViewCompat.java */
    static class i extends C0012h {
    }

    /* JADX INFO: compiled from: ViewCompat.java */
    static class j {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        WeakHashMap<View, l> f606a;
    }

    static {
        i iVar = new i();
        iVar.f606a = null;
        f605a = iVar;
    }

    public static l a(View view) {
        i iVar = f605a;
        if (iVar.f606a == null) {
            iVar.f606a = new WeakHashMap<>();
        }
        l lVar = iVar.f606a.get(view);
        if (lVar != null) {
            return lVar;
        }
        l lVar2 = new l(view);
        iVar.f606a.put(view, lVar2);
        return lVar2;
    }

    public static o b(View view, o oVar) {
        WindowInsets windowInsets = (WindowInsets) o.f(oVar);
        WindowInsets windowInsetsOnApplyWindowInsets = view.onApplyWindowInsets(windowInsets);
        if (windowInsetsOnApplyWindowInsets != windowInsets) {
            windowInsets = new WindowInsets(windowInsetsOnApplyWindowInsets);
        }
        return o.g(windowInsets);
    }

    public static void c(NestedScrollView nestedScrollView, android.support.v4.view.b bVar) {
        nestedScrollView.setAccessibilityDelegate(bVar == null ? null : bVar.f596a);
    }

    public static void d(ViewGroup viewGroup, android.support.v4.view.g gVar) {
        viewGroup.setOnApplyWindowInsetsListener(new android.support.v4.view.i(gVar));
    }
}
