package android.support.v4.view;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeProvider;

/* JADX INFO: compiled from: AccessibilityDelegateCompat.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class b {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final View.AccessibilityDelegate f595b = new View.AccessibilityDelegate();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final View.AccessibilityDelegate f596a = new android.support.v4.view.a(this);

    /* JADX INFO: compiled from: AccessibilityDelegateCompat.java */
    static class a extends C0011b {
    }

    /* JADX INFO: renamed from: android.support.v4.view.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: AccessibilityDelegateCompat.java */
    static class C0011b {
    }

    public static boolean a(View view, AccessibilityEvent accessibilityEvent) {
        return f595b.dispatchPopulateAccessibilityEvent(view, accessibilityEvent);
    }

    public static j.b b(View view) {
        AccessibilityNodeProvider accessibilityNodeProvider = f595b.getAccessibilityNodeProvider(view);
        if (accessibilityNodeProvider != null) {
            return new j.b(accessibilityNodeProvider);
        }
        return null;
    }

    public static void e(View view, AccessibilityEvent accessibilityEvent) {
        f595b.onPopulateAccessibilityEvent(view, accessibilityEvent);
    }

    public static boolean f(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
        return f595b.onRequestSendAccessibilityEvent(viewGroup, view, accessibilityEvent);
    }

    public static void h(View view, int i2) {
        f595b.sendAccessibilityEvent(view, i2);
    }

    public static void i(View view, AccessibilityEvent accessibilityEvent) {
        f595b.sendAccessibilityEventUnchecked(view, accessibilityEvent);
    }

    public void c(View view, AccessibilityEvent accessibilityEvent) {
        f595b.onInitializeAccessibilityEvent(view, accessibilityEvent);
    }

    public void d(View view, j.a aVar) {
        f595b.onInitializeAccessibilityNodeInfo(view, aVar.d());
    }

    public boolean g(View view, int i2, Bundle bundle) {
        return f595b.performAccessibilityAction(view, i2, bundle);
    }
}
