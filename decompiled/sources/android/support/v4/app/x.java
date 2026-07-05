package android.support.v4.app;

import android.graphics.Rect;
import android.transition.Transition;
import android.transition.TransitionSet;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;

/* JADX INFO: compiled from: FragmentTransitionImpl.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public abstract class x {
    static void e(View view, ArrayList arrayList) {
        if (view.getVisibility() == 0) {
            if (!(view instanceof ViewGroup)) {
                arrayList.add(view);
                return;
            }
            ViewGroup viewGroup = (ViewGroup) view;
            if (viewGroup.isTransitionGroup()) {
                arrayList.add(viewGroup);
                return;
            }
            int childCount = viewGroup.getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                e(viewGroup.getChildAt(i2), arrayList);
            }
        }
    }

    static void g(android.support.v4.util.b bVar, View view) {
        if (view.getVisibility() == 0) {
            String transitionName = view.getTransitionName();
            if (transitionName != null) {
                bVar.put(transitionName, view);
            }
            if (view instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view;
                int childCount = viewGroup.getChildCount();
                for (int i2 = 0; i2 < childCount; i2++) {
                    g(bVar, viewGroup.getChildAt(i2));
                }
            }
        }
    }

    protected static void h(View view, Rect rect) {
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        int i2 = iArr[0];
        rect.set(i2, iArr[1], view.getWidth() + i2, view.getHeight() + iArr[1]);
    }

    public abstract void a(Object obj, View view);

    public abstract void b(Object obj, ArrayList<View> arrayList);

    public abstract void c(ViewGroup viewGroup, Object obj);

    public abstract boolean d(Object obj);

    public abstract Transition f(Object obj);

    public abstract Transition i(Object obj, Object obj2, Object obj3);

    public abstract TransitionSet j(Object obj, Object obj2, Object obj3);

    public abstract void k(Object obj, View view);

    public abstract void l(Object obj, ArrayList<View> arrayList, ArrayList<View> arrayList2);

    public abstract void m(Object obj, View view, ArrayList<View> arrayList);

    public abstract void n(Object obj, Object obj2, ArrayList<View> arrayList, Object obj3, ArrayList<View> arrayList2, Object obj4, ArrayList<View> arrayList3);

    public abstract void o(Object obj, Rect rect);

    public abstract void p(Object obj, View view);

    public abstract void q(Object obj, View view, ArrayList<View> arrayList);

    public abstract void r(Object obj, ArrayList<View> arrayList, ArrayList<View> arrayList2);

    public abstract TransitionSet s(Object obj);
}
