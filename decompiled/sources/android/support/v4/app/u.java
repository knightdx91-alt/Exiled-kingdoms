package android.support.v4.app;

import android.graphics.Rect;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.transition.TransitionSet;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: FragmentTransitionCompat21.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class u extends x {

    /* JADX INFO: compiled from: FragmentTransitionCompat21.java */
    final class a extends Transition.EpicenterCallback {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Rect f318a;

        a(Rect rect) {
            this.f318a = rect;
        }

        @Override // android.transition.Transition.EpicenterCallback
        public final Rect onGetEpicenter(Transition transition) {
            return this.f318a;
        }
    }

    /* JADX INFO: compiled from: FragmentTransitionCompat21.java */
    final class b implements Transition.TransitionListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ View f319a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ ArrayList f320b;

        b(View view, ArrayList arrayList) {
            this.f319a = view;
            this.f320b = arrayList;
        }

        @Override // android.transition.Transition.TransitionListener
        public final void onTransitionCancel(Transition transition) {
        }

        @Override // android.transition.Transition.TransitionListener
        public final void onTransitionEnd(Transition transition) {
            transition.removeListener(this);
            this.f319a.setVisibility(8);
            ArrayList arrayList = this.f320b;
            int size = arrayList.size();
            for (int i2 = 0; i2 < size; i2++) {
                ((View) arrayList.get(i2)).setVisibility(0);
            }
        }

        @Override // android.transition.Transition.TransitionListener
        public final void onTransitionPause(Transition transition) {
        }

        @Override // android.transition.Transition.TransitionListener
        public final void onTransitionResume(Transition transition) {
        }

        @Override // android.transition.Transition.TransitionListener
        public final void onTransitionStart(Transition transition) {
        }
    }

    /* JADX INFO: compiled from: FragmentTransitionCompat21.java */
    final class c implements Transition.TransitionListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Object f321a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ ArrayList f322b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ Object f323c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ ArrayList f324d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final /* synthetic */ Object f325e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        final /* synthetic */ ArrayList f326f;

        c(Object obj, ArrayList arrayList, Object obj2, ArrayList arrayList2, Object obj3, ArrayList arrayList3) {
            this.f321a = obj;
            this.f322b = arrayList;
            this.f323c = obj2;
            this.f324d = arrayList2;
            this.f325e = obj3;
            this.f326f = arrayList3;
        }

        @Override // android.transition.Transition.TransitionListener
        public final void onTransitionCancel(Transition transition) {
        }

        @Override // android.transition.Transition.TransitionListener
        public final void onTransitionEnd(Transition transition) {
        }

        @Override // android.transition.Transition.TransitionListener
        public final void onTransitionPause(Transition transition) {
        }

        @Override // android.transition.Transition.TransitionListener
        public final void onTransitionResume(Transition transition) {
        }

        @Override // android.transition.Transition.TransitionListener
        public final void onTransitionStart(Transition transition) {
            u uVar = u.this;
            Object obj = this.f321a;
            if (obj != null) {
                uVar.l(obj, this.f322b, null);
            }
            Object obj2 = this.f323c;
            if (obj2 != null) {
                uVar.l(obj2, this.f324d, null);
            }
            Object obj3 = this.f325e;
            if (obj3 != null) {
                uVar.l(obj3, this.f326f, null);
            }
        }
    }

    /* JADX INFO: compiled from: FragmentTransitionCompat21.java */
    final class d extends Transition.EpicenterCallback {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Rect f328a;

        d(Rect rect) {
            this.f328a = rect;
        }

        @Override // android.transition.Transition.EpicenterCallback
        public final Rect onGetEpicenter(Transition transition) {
            Rect rect = this.f328a;
            if (rect == null || rect.isEmpty()) {
                return null;
            }
            return rect;
        }
    }

    private static boolean t(Transition transition) {
        List<String> targetNames;
        List<Class> targetTypes;
        List<Integer> targetIds = transition.getTargetIds();
        return ((targetIds == null || targetIds.isEmpty()) && ((targetNames = transition.getTargetNames()) == null || targetNames.isEmpty()) && ((targetTypes = transition.getTargetTypes()) == null || targetTypes.isEmpty())) ? false : true;
    }

    @Override // android.support.v4.app.x
    public final void a(Object obj, View view) {
        if (obj != null) {
            ((Transition) obj).addTarget(view);
        }
    }

    @Override // android.support.v4.app.x
    public final void b(Object obj, ArrayList<View> arrayList) {
        Transition transition = (Transition) obj;
        if (transition == null) {
            return;
        }
        int i2 = 0;
        if (transition instanceof TransitionSet) {
            TransitionSet transitionSet = (TransitionSet) transition;
            int transitionCount = transitionSet.getTransitionCount();
            while (i2 < transitionCount) {
                b(transitionSet.getTransitionAt(i2), arrayList);
                i2++;
            }
            return;
        }
        if (t(transition)) {
            return;
        }
        List<View> targets = transition.getTargets();
        if (targets == null || targets.isEmpty()) {
            int size = arrayList.size();
            while (i2 < size) {
                transition.addTarget(arrayList.get(i2));
                i2++;
            }
        }
    }

    @Override // android.support.v4.app.x
    public final void c(ViewGroup viewGroup, Object obj) {
        TransitionManager.beginDelayedTransition(viewGroup, (Transition) obj);
    }

    @Override // android.support.v4.app.x
    public final boolean d(Object obj) {
        return obj instanceof Transition;
    }

    @Override // android.support.v4.app.x
    public final Transition f(Object obj) {
        if (obj != null) {
            return ((Transition) obj).clone();
        }
        return null;
    }

    @Override // android.support.v4.app.x
    public final Transition i(Object obj, Object obj2, Object obj3) {
        Transition ordering = (Transition) obj;
        Transition transition = (Transition) obj2;
        Transition transition2 = (Transition) obj3;
        if (ordering != null && transition != null) {
            ordering = new TransitionSet().addTransition(ordering).addTransition(transition).setOrdering(1);
        } else if (ordering == null) {
            ordering = transition != null ? transition : null;
        }
        if (transition2 == null) {
            return ordering;
        }
        TransitionSet transitionSet = new TransitionSet();
        if (ordering != null) {
            transitionSet.addTransition(ordering);
        }
        transitionSet.addTransition(transition2);
        return transitionSet;
    }

    @Override // android.support.v4.app.x
    public final TransitionSet j(Object obj, Object obj2, Object obj3) {
        TransitionSet transitionSet = new TransitionSet();
        if (obj != null) {
            transitionSet.addTransition((Transition) obj);
        }
        if (obj2 != null) {
            transitionSet.addTransition((Transition) obj2);
        }
        if (obj3 != null) {
            transitionSet.addTransition((Transition) obj3);
        }
        return transitionSet;
    }

    @Override // android.support.v4.app.x
    public final void k(Object obj, View view) {
        if (obj != null) {
            ((Transition) obj).removeTarget(view);
        }
    }

    @Override // android.support.v4.app.x
    public final void l(Object obj, ArrayList<View> arrayList, ArrayList<View> arrayList2) {
        List<View> targets;
        Transition transition = (Transition) obj;
        int i2 = 0;
        if (transition instanceof TransitionSet) {
            TransitionSet transitionSet = (TransitionSet) transition;
            int transitionCount = transitionSet.getTransitionCount();
            while (i2 < transitionCount) {
                l(transitionSet.getTransitionAt(i2), arrayList, arrayList2);
                i2++;
            }
            return;
        }
        if (t(transition) || (targets = transition.getTargets()) == null || targets.size() != arrayList.size() || !targets.containsAll(arrayList)) {
            return;
        }
        int size = arrayList2 == null ? 0 : arrayList2.size();
        while (i2 < size) {
            transition.addTarget(arrayList2.get(i2));
            i2++;
        }
        for (int size2 = arrayList.size() - 1; size2 >= 0; size2--) {
            transition.removeTarget(arrayList.get(size2));
        }
    }

    @Override // android.support.v4.app.x
    public final void m(Object obj, View view, ArrayList<View> arrayList) {
        ((Transition) obj).addListener(new b(view, arrayList));
    }

    @Override // android.support.v4.app.x
    public final void n(Object obj, Object obj2, ArrayList<View> arrayList, Object obj3, ArrayList<View> arrayList2, Object obj4, ArrayList<View> arrayList3) {
        ((Transition) obj).addListener(new c(obj2, arrayList, obj3, arrayList2, obj4, arrayList3));
    }

    @Override // android.support.v4.app.x
    public final void o(Object obj, Rect rect) {
        if (obj != null) {
            ((Transition) obj).setEpicenterCallback(new d(rect));
        }
    }

    @Override // android.support.v4.app.x
    public final void p(Object obj, View view) {
        if (view != null) {
            Rect rect = new Rect();
            x.h(view, rect);
            ((Transition) obj).setEpicenterCallback(new a(rect));
        }
    }

    @Override // android.support.v4.app.x
    public final void q(Object obj, View view, ArrayList<View> arrayList) {
        TransitionSet transitionSet = (TransitionSet) obj;
        List<View> targets = transitionSet.getTargets();
        targets.clear();
        int size = arrayList.size();
        for (int i2 = 0; i2 < size; i2++) {
            View view2 = arrayList.get(i2);
            int size2 = targets.size();
            int i3 = 0;
            while (true) {
                if (i3 >= size2) {
                    targets.add(view2);
                    for (int i4 = size2; i4 < targets.size(); i4++) {
                        View view3 = targets.get(i4);
                        if (view3 instanceof ViewGroup) {
                            ViewGroup viewGroup = (ViewGroup) view3;
                            int childCount = viewGroup.getChildCount();
                            for (int i5 = 0; i5 < childCount; i5++) {
                                View childAt = viewGroup.getChildAt(i5);
                                int i6 = 0;
                                while (true) {
                                    if (i6 >= size2) {
                                        targets.add(childAt);
                                        break;
                                    } else if (targets.get(i6) == childAt) {
                                        break;
                                    } else {
                                        i6++;
                                    }
                                }
                            }
                        }
                    }
                } else if (targets.get(i3) == view2) {
                    break;
                } else {
                    i3++;
                }
            }
        }
        targets.add(view);
        arrayList.add(view);
        b(transitionSet, arrayList);
    }

    @Override // android.support.v4.app.x
    public final void r(Object obj, ArrayList<View> arrayList, ArrayList<View> arrayList2) {
        TransitionSet transitionSet = (TransitionSet) obj;
        if (transitionSet != null) {
            transitionSet.getTargets().clear();
            transitionSet.getTargets().addAll(arrayList2);
            l(transitionSet, arrayList, arrayList2);
        }
    }

    @Override // android.support.v4.app.x
    public final TransitionSet s(Object obj) {
        if (obj == null) {
            return null;
        }
        TransitionSet transitionSet = new TransitionSet();
        transitionSet.addTransition((Transition) obj);
        return transitionSet;
    }
}
