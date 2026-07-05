package android.support.v7.widget;

import android.graphics.Rect;
import android.util.Log;
import android.view.View;
import java.lang.reflect.Method;

/* JADX INFO: compiled from: ViewUtils.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class b1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static Method f1196a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final /* synthetic */ int f1197b = 0;

    static {
        try {
            Method declaredMethod = View.class.getDeclaredMethod("computeFitSystemWindows", Rect.class, Rect.class);
            f1196a = declaredMethod;
            if (declaredMethod.isAccessible()) {
                return;
            }
            declaredMethod.setAccessible(true);
        } catch (NoSuchMethodException unused) {
            Log.d("ViewUtils", "Could not find method computeFitSystemWindows. Oh well.");
        }
    }

    public static void a(View view, Rect rect, Rect rect2) {
        Method method = f1196a;
        if (method != null) {
            try {
                method.invoke(view, rect, rect2);
            } catch (Exception e2) {
                Log.d("ViewUtils", "Could not invoke computeFitSystemWindows", e2);
            }
        }
    }
}
