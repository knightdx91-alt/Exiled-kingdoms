package android.support.v4.widget;

import android.util.Log;
import android.view.View;
import android.widget.PopupWindow;

/* JADX INFO: compiled from: PopupWindowCompat.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final c f679a = new c();

    /* JADX INFO: compiled from: PopupWindowCompat.java */
    static class a extends d {
    }

    /* JADX INFO: compiled from: PopupWindowCompat.java */
    static class b extends a {
        static {
            try {
                PopupWindow.class.getDeclaredField("mOverlapAnchor").setAccessible(true);
            } catch (NoSuchFieldException e2) {
                Log.i("PopupWindowCompatApi21", "Could not fetch mOverlapAnchor field from PopupWindow", e2);
            }
        }
    }

    /* JADX INFO: compiled from: PopupWindowCompat.java */
    static class c extends b {
    }

    /* JADX INFO: compiled from: PopupWindowCompat.java */
    static class d {
    }

    public static void a(PopupWindow popupWindow, boolean z2) {
        f679a.getClass();
        popupWindow.setOverlapAnchor(z2);
    }

    public static void b(PopupWindow popupWindow, int i2) {
        f679a.getClass();
        popupWindow.setWindowLayoutType(i2);
    }

    public static void c(PopupWindow popupWindow, View view, int i2, int i3, int i4) {
        f679a.getClass();
        popupWindow.showAsDropDown(view, i2, i3, i4);
    }
}
