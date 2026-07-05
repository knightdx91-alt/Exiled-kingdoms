package android.support.v4.app;

import android.graphics.Rect;
import android.support.v4.app.b;
import android.transition.Transition;
import android.transition.TransitionSet;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.Collection;

/* JADX INFO: compiled from: FragmentTransition.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class t {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final int[] f309a = {0, 3, 0, 1, 5, 4, 7, 6, 9, 8};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final x f310b = new u();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final x f311c;

    /* JADX INFO: compiled from: FragmentTransition.java */
    static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public Fragment f312a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public boolean f313b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public b f314c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public Fragment f315d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public boolean f316e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public b f317f;

        a() {
        }
    }

    static {
        x xVar = null;
        try {
            xVar = (x) Class.forName("android.support.transition.FragmentTransitionSupport").getDeclaredConstructor(null).newInstance(null);
        } catch (Exception unused) {
        }
        f311c = xVar;
    }

    static void c(Fragment fragment, Fragment fragment2, boolean z2) {
        if (z2) {
            fragment2.getEnterTransitionCallback();
        } else {
            fragment.getEnterTransitionCallback();
        }
    }

    private static void f(ArrayList<View> arrayList, android.support.v4.util.b<String, View> bVar, Collection<String> collection) {
        for (int size = bVar.size() - 1; size >= 0; size--) {
            View viewJ = bVar.j(size);
            if (collection.contains(viewJ.getTransitionName())) {
                arrayList.add(viewJ);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x0043  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0045  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x005c  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x005e  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0090  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x0093  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static void g(b bVar, b.a aVar, SparseArray<a> sparseArray, boolean z2, boolean z3) {
        int i2;
        boolean z4;
        boolean z5;
        boolean z6;
        boolean z7;
        View view;
        Fragment fragment = aVar.f191b;
        if (fragment == null || (i2 = fragment.mContainerId) == 0) {
            return;
        }
        int i3 = z2 ? f309a[aVar.f190a] : aVar.f190a;
        boolean z8 = false;
        if (i3 == 1) {
            if (z3) {
                z4 = (fragment.mAdded || fragment.mHidden) ? false : true;
                z5 = false;
                z6 = false;
                z8 = z4;
                z7 = true;
            } else {
                z4 = fragment.mIsNewlyAdded;
                z5 = false;
                z6 = false;
                z8 = z4;
                z7 = true;
            }
        } else if (i3 == 3) {
            boolean z9 = z3 ? !(!fragment.mAdded || fragment.mHidden) : !(fragment.mAdded || (view = fragment.mView) == null || view.getVisibility() != 0 || fragment.mPostponedAlpha < 0.0f);
            z6 = z9;
            z7 = false;
            z5 = true;
        } else if (i3 == 4) {
            if (!z3 ? !fragment.mAdded || fragment.mHidden : !fragment.mHiddenChanged || !fragment.mAdded || !fragment.mHidden) {
            }
            z6 = z9;
            z7 = false;
            z5 = true;
        } else if (i3 != 5) {
            if (i3 != 6) {
                if (i3 != 7) {
                    z7 = false;
                    z5 = false;
                    z6 = false;
                }
                if (z3) {
                }
            }
            if (z3) {
            }
            z6 = z9;
            z7 = false;
            z5 = true;
        } else if (z3) {
            if (!fragment.mHiddenChanged || fragment.mHidden || !fragment.mAdded) {
            }
            z5 = false;
            z6 = false;
            z8 = z4;
            z7 = true;
        } else {
            z4 = fragment.mHidden;
            z5 = false;
            z6 = false;
            z8 = z4;
            z7 = true;
        }
        a aVar2 = sparseArray.get(i2);
        if (z8) {
            if (aVar2 == null) {
                aVar2 = new a();
                sparseArray.put(i2, aVar2);
            }
            aVar2.f312a = fragment;
            aVar2.f313b = z2;
            aVar2.f314c = bVar;
        }
        a aVar3 = aVar2;
        if (!z3 && z7) {
            if (aVar3 != null && aVar3.f315d == fragment) {
                aVar3.f315d = null;
            }
            if (fragment.mState < 1) {
                j jVar = bVar.f172a;
                if (jVar.f240l >= 1 && !bVar.s) {
                    jVar.a0(fragment);
                    jVar.f0(fragment, 1, 0, 0, false);
                }
            }
        }
        if (z6 && (aVar3 == null || aVar3.f315d == null)) {
            if (aVar3 == null) {
                aVar3 = new a();
                sparseArray.put(i2, aVar3);
            }
            aVar3.f315d = fragment;
            aVar3.f316e = z2;
            aVar3.f317f = bVar;
        }
        if (z3 || !z5 || aVar3 == null || aVar3.f312a != fragment) {
            return;
        }
        aVar3.f312a = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static android.support.v4.util.b h(android.support.v4.util.b bVar, Object obj, a aVar) {
        ArrayList<String> arrayList;
        Fragment fragment = aVar.f312a;
        View view = fragment.getView();
        if (bVar.isEmpty() || obj == null || view == null) {
            bVar.clear();
            return null;
        }
        android.support.v4.util.b bVar2 = new android.support.v4.util.b();
        x.g(bVar2, view);
        b bVar3 = aVar.f314c;
        if (aVar.f313b) {
            fragment.getExitTransitionCallback();
            arrayList = bVar3.f188q;
        } else {
            fragment.getEnterTransitionCallback();
            arrayList = bVar3.f189r;
        }
        if (arrayList != null) {
            bVar2.k(arrayList);
        }
        for (int size = bVar.size() - 1; size >= 0; size--) {
            if (!bVar2.containsKey((String) bVar.j(size))) {
                bVar.i(size);
            }
        }
        return bVar2;
    }

    private static android.support.v4.util.b i(android.support.v4.util.b bVar, Object obj, a aVar) {
        ArrayList<String> arrayList;
        if (bVar.isEmpty() || obj == null) {
            bVar.clear();
            return null;
        }
        Fragment fragment = aVar.f315d;
        android.support.v4.util.b bVar2 = new android.support.v4.util.b();
        x.g(bVar2, fragment.getView());
        b bVar3 = aVar.f317f;
        if (aVar.f316e) {
            fragment.getEnterTransitionCallback();
            arrayList = bVar3.f189r;
        } else {
            fragment.getExitTransitionCallback();
            arrayList = bVar3.f188q;
        }
        bVar2.k(arrayList);
        bVar.k(bVar2.keySet());
        return bVar2;
    }

    private static x j(Fragment fragment, Fragment fragment2) {
        ArrayList arrayList = new ArrayList();
        if (fragment != null) {
            Object exitTransition = fragment.getExitTransition();
            if (exitTransition != null) {
                arrayList.add(exitTransition);
            }
            Object returnTransition = fragment.getReturnTransition();
            if (returnTransition != null) {
                arrayList.add(returnTransition);
            }
            Object sharedElementReturnTransition = fragment.getSharedElementReturnTransition();
            if (sharedElementReturnTransition != null) {
                arrayList.add(sharedElementReturnTransition);
            }
        }
        if (fragment2 != null) {
            Object enterTransition = fragment2.getEnterTransition();
            if (enterTransition != null) {
                arrayList.add(enterTransition);
            }
            Object reenterTransition = fragment2.getReenterTransition();
            if (reenterTransition != null) {
                arrayList.add(reenterTransition);
            }
            Object sharedElementEnterTransition = fragment2.getSharedElementEnterTransition();
            if (sharedElementEnterTransition != null) {
                arrayList.add(sharedElementEnterTransition);
            }
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        x xVar = f310b;
        if (xVar != null) {
            int size = arrayList.size();
            for (int i2 = 0; i2 < size; i2++) {
                if (xVar.d(arrayList.get(i2))) {
                }
            }
            return xVar;
        }
        x xVar2 = f311c;
        if (xVar2 != null) {
            int size2 = arrayList.size();
            for (int i3 = 0; i3 < size2; i3++) {
                if (xVar2.d(arrayList.get(i3))) {
                }
            }
            return xVar2;
        }
        if (xVar == null && xVar2 == null) {
            return null;
        }
        throw new IllegalArgumentException("Invalid Transition types");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static ArrayList<View> k(x xVar, Object obj, Fragment fragment, ArrayList<View> arrayList, View view) {
        if (obj == null) {
            return null;
        }
        ArrayList<View> arrayList2 = new ArrayList<>();
        View view2 = fragment.getView();
        if (view2 != null) {
            x.e(view2, arrayList2);
        }
        arrayList2.removeAll(arrayList);
        if (arrayList2.isEmpty()) {
            return arrayList2;
        }
        arrayList2.add(view);
        xVar.b(obj, arrayList2);
        return arrayList2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static View l(android.support.v4.util.b<String, View> bVar, a aVar, Object obj, boolean z2) {
        ArrayList<String> arrayList;
        b bVar2 = aVar.f314c;
        if (obj == null || bVar == null || (arrayList = bVar2.f188q) == null || arrayList.isEmpty()) {
            return null;
        }
        return bVar.get(z2 ? bVar2.f188q.get(0) : bVar2.f189r.get(0));
    }

    private static Object m(x xVar, Object obj, Object obj2, Object obj3, Fragment fragment, boolean z2) {
        return (obj == null || obj2 == null || fragment == null) ? true : z2 ? fragment.getAllowReturnTransitionOverlap() : fragment.getAllowEnterTransitionOverlap() ? xVar.j(obj2, obj, obj3) : xVar.i(obj2, obj, obj3);
    }

    private static void n(x xVar, Object obj, Object obj2, android.support.v4.util.b<String, View> bVar, boolean z2, b bVar2) {
        ArrayList<String> arrayList = bVar2.f188q;
        if (arrayList == null || arrayList.isEmpty()) {
            return;
        }
        View view = bVar.get(z2 ? bVar2.f189r.get(0) : bVar2.f188q.get(0));
        xVar.p(obj, view);
        if (obj2 != null) {
            xVar.p(obj2, view);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void o(int i2, ArrayList arrayList) {
        if (arrayList == null) {
            return;
        }
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            ((View) arrayList.get(size)).setVisibility(i2);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:109:0x0236  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x0258  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    static void p(j jVar, ArrayList<b> arrayList, ArrayList<Boolean> arrayList2, int i2, int i3, boolean z2) {
        SparseArray sparseArray;
        int i4;
        int i5;
        Fragment fragment;
        Fragment fragment2;
        x xVarJ;
        Fragment fragment3;
        ArrayList<View> arrayList3;
        ArrayList<View> arrayList4;
        Transition transition;
        android.support.v4.util.b bVar;
        int i6;
        int i7;
        boolean z3;
        Transition transition2;
        TransitionSet transitionSet;
        TransitionSet transitionSet2;
        Fragment fragment4;
        ArrayList<View> arrayList5;
        boolean z4;
        Fragment fragment5;
        Rect rect;
        Fragment fragment6;
        Fragment fragment7;
        x xVarJ2;
        Transition transitionF;
        ArrayList<View> arrayList6;
        Fragment fragment8;
        boolean z5;
        android.support.v4.util.i iVar;
        TransitionSet transitionSet3;
        Object objM;
        android.support.v4.util.i iVar2;
        TransitionSet transitionSetS;
        View view;
        Rect rect2;
        ArrayList<String> arrayList7;
        ArrayList<String> arrayList8;
        j jVar2 = jVar;
        ArrayList<b> arrayList9 = arrayList;
        ArrayList<Boolean> arrayList10 = arrayList2;
        int i8 = i3;
        boolean z6 = z2;
        if (jVar2.f240l < 1) {
            return;
        }
        SparseArray sparseArray2 = new SparseArray();
        for (int i9 = i2; i9 < i8; i9++) {
            b bVar2 = arrayList9.get(i9);
            if (!arrayList10.get(i9).booleanValue()) {
                int size = bVar2.f173b.size();
                for (int i10 = 0; i10 < size; i10++) {
                    g(bVar2, bVar2.f173b.get(i10), sparseArray2, false, z6);
                }
            } else if (bVar2.f172a.f242n.c()) {
                ArrayList<b.a> arrayList11 = bVar2.f173b;
                for (int size2 = arrayList11.size() - 1; size2 >= 0; size2--) {
                    g(bVar2, arrayList11.get(size2), sparseArray2, true, z6);
                }
            }
        }
        if (sparseArray2.size() != 0) {
            View view2 = new View(jVar2.f241m.f219b);
            int size3 = sparseArray2.size();
            int i11 = 0;
            while (i11 < size3) {
                int iKeyAt = sparseArray2.keyAt(i11);
                android.support.v4.util.b bVar3 = new android.support.v4.util.b();
                int i12 = i8 - 1;
                while (i12 >= i2) {
                    b bVar4 = arrayList9.get(i12);
                    if (bVar4.i(iKeyAt)) {
                        boolean zBooleanValue = arrayList10.get(i12).booleanValue();
                        ArrayList<String> arrayList12 = bVar4.f188q;
                        if (arrayList12 != null) {
                            int size4 = arrayList12.size();
                            if (zBooleanValue) {
                                arrayList8 = bVar4.f188q;
                                arrayList7 = bVar4.f189r;
                            } else {
                                ArrayList<String> arrayList13 = bVar4.f188q;
                                ArrayList<String> arrayList14 = bVar4.f189r;
                                arrayList7 = arrayList13;
                                arrayList8 = arrayList14;
                            }
                            int i13 = 0;
                            while (i13 < size4) {
                                String str = arrayList7.get(i13);
                                String str2 = arrayList8.get(i13);
                                int i14 = size4;
                                String str3 = (String) bVar3.remove(str2);
                                if (str3 != null) {
                                    bVar3.put(str, str3);
                                } else {
                                    bVar3.put(str, str2);
                                }
                                i13++;
                                size4 = i14;
                            }
                        }
                    }
                    i12--;
                    arrayList9 = arrayList;
                    arrayList10 = arrayList2;
                }
                a aVar = (a) sparseArray2.valueAt(i11);
                if (z6) {
                    ViewGroup viewGroup = jVar2.f242n.c() ? (ViewGroup) jVar2.f242n.b(iKeyAt) : null;
                    if (viewGroup == null || (xVarJ2 = j((fragment7 = aVar.f315d), (fragment6 = aVar.f312a))) == null) {
                        sparseArray = sparseArray2;
                        i4 = i11;
                        i5 = size3;
                    } else {
                        boolean z7 = aVar.f313b;
                        boolean z8 = aVar.f316e;
                        ArrayList<View> arrayList15 = new ArrayList<>();
                        ArrayList<View> arrayList16 = new ArrayList<>();
                        if (fragment6 == null) {
                            sparseArray = sparseArray2;
                            transitionF = null;
                        } else {
                            sparseArray = sparseArray2;
                            transitionF = xVarJ2.f(z7 ? fragment6.getReenterTransition() : fragment6.getEnterTransition());
                        }
                        Transition transitionF2 = fragment7 == null ? null : xVarJ2.f(z8 ? fragment7.getReturnTransition() : fragment7.getExitTransition());
                        Fragment fragment9 = aVar.f312a;
                        i4 = i11;
                        Fragment fragment10 = aVar.f315d;
                        i5 = size3;
                        if (fragment9 != null) {
                            fragment9.getView().setVisibility(0);
                        }
                        if (fragment9 == null || fragment10 == null) {
                            arrayList6 = arrayList15;
                            fragment8 = fragment6;
                            z5 = z7;
                        } else {
                            boolean z9 = aVar.f313b;
                            if (bVar3.isEmpty()) {
                                z5 = z7;
                                transitionSetS = null;
                            } else {
                                transitionSetS = xVarJ2.s(xVarJ2.f(z9 ? fragment10.getSharedElementReturnTransition() : fragment9.getSharedElementEnterTransition()));
                                z5 = z7;
                            }
                            android.support.v4.util.b bVarI = i(bVar3, transitionSetS, aVar);
                            fragment8 = fragment6;
                            android.support.v4.util.b bVarH = h(bVar3, transitionSetS, aVar);
                            if (bVar3.isEmpty()) {
                                if (bVarI != null) {
                                    bVarI.clear();
                                }
                                if (bVarH != null) {
                                    bVarH.clear();
                                }
                                transitionSet3 = null;
                            } else {
                                f(arrayList16, bVarI, bVar3.keySet());
                                f(arrayList15, bVarH, bVar3.values());
                                transitionSet3 = transitionSetS;
                            }
                            if (transitionF == null && transitionF2 == null && transitionSet3 == null) {
                                arrayList6 = arrayList15;
                            } else {
                                if (z9) {
                                    fragment10.getEnterTransitionCallback();
                                } else {
                                    fragment9.getEnterTransitionCallback();
                                }
                                if (transitionSet3 != null) {
                                    arrayList15.add(view2);
                                    xVarJ2.q(transitionSet3, view2, arrayList16);
                                    iVar = bVar3;
                                    arrayList6 = arrayList15;
                                    n(xVarJ2, transitionSet3, transitionF2, bVarI, aVar.f316e, aVar.f317f);
                                    Rect rect3 = new Rect();
                                    View viewL = l(bVarH, aVar, transitionF, z9);
                                    if (viewL != null) {
                                        xVarJ2.o(transitionF, rect3);
                                    }
                                    view = viewL;
                                    rect2 = rect3;
                                } else {
                                    arrayList6 = arrayList15;
                                    iVar = bVar3;
                                    view = null;
                                    rect2 = null;
                                }
                                h0.a(viewGroup, new r(fragment9, fragment10, z9, bVarH, view, xVarJ2, rect2));
                                if (transitionF == null || transitionSet3 != null || transitionF2 != null) {
                                    ArrayList<View> arrayListK = k(xVarJ2, transitionF2, fragment7, arrayList16, view2);
                                    Fragment fragment11 = fragment8;
                                    ArrayList<View> arrayList17 = arrayList6;
                                    ArrayList<View> arrayListK2 = k(xVarJ2, transitionF, fragment11, arrayList17, view2);
                                    o(4, arrayListK2);
                                    objM = m(xVarJ2, transitionF, transitionF2, transitionSet3, fragment11, z5);
                                    if (objM == null) {
                                        if (fragment7 != null && transitionF2 != null && fragment7.mAdded && fragment7.mHidden && fragment7.mHiddenChanged) {
                                            fragment7.setHideReplaced(true);
                                            xVarJ2.m(transitionF2, fragment7.getView(), arrayListK);
                                            h0.a(fragment7.mContainer, new p(arrayListK));
                                        }
                                        ArrayList arrayList18 = new ArrayList();
                                        int size5 = arrayList17.size();
                                        for (int i15 = 0; i15 < size5; i15++) {
                                            View view3 = arrayList17.get(i15);
                                            arrayList18.add(view3.getTransitionName());
                                            view3.setTransitionName(null);
                                        }
                                        xVarJ2.n(objM, transitionF, arrayListK2, transitionF2, arrayListK, transitionSet3, arrayList17);
                                        xVarJ2.c(viewGroup, objM);
                                        int size6 = arrayList17.size();
                                        ArrayList arrayList19 = new ArrayList();
                                        int i16 = 0;
                                        while (i16 < size6) {
                                            View view4 = arrayList16.get(i16);
                                            String transitionName = view4.getTransitionName();
                                            arrayList19.add(transitionName);
                                            if (transitionName == null) {
                                                iVar2 = iVar;
                                            } else {
                                                view4.setTransitionName(null);
                                                iVar2 = iVar;
                                                String str4 = (String) iVar2.get(transitionName);
                                                int i17 = 0;
                                                while (true) {
                                                    if (i17 >= size6) {
                                                        break;
                                                    }
                                                    if (str4.equals(arrayList18.get(i17))) {
                                                        arrayList17.get(i17).setTransitionName(transitionName);
                                                        break;
                                                    }
                                                    i17++;
                                                }
                                            }
                                            i16++;
                                            iVar = iVar2;
                                        }
                                        h0.a(viewGroup, new v(size6, arrayList17, arrayList18, arrayList16, arrayList19));
                                        o(0, arrayListK2);
                                        xVarJ2.r(transitionSet3, arrayList16, arrayList17);
                                    }
                                }
                                i11 = i6 + 1;
                                jVar2 = jVar;
                                arrayList9 = arrayList;
                                arrayList10 = arrayList2;
                                i8 = i3;
                                z6 = z2;
                                size3 = i7;
                                sparseArray2 = sparseArray;
                            }
                        }
                        iVar = bVar3;
                        transitionSet3 = null;
                        if (transitionF == null) {
                            ArrayList<View> arrayListK3 = k(xVarJ2, transitionF2, fragment7, arrayList16, view2);
                            Fragment fragment112 = fragment8;
                            ArrayList<View> arrayList172 = arrayList6;
                            ArrayList<View> arrayListK22 = k(xVarJ2, transitionF, fragment112, arrayList172, view2);
                            o(4, arrayListK22);
                            objM = m(xVarJ2, transitionF, transitionF2, transitionSet3, fragment112, z5);
                            if (objM == null) {
                            }
                        }
                        i11 = i6 + 1;
                        jVar2 = jVar;
                        arrayList9 = arrayList;
                        arrayList10 = arrayList2;
                        i8 = i3;
                        z6 = z2;
                        size3 = i7;
                        sparseArray2 = sparseArray;
                    }
                } else {
                    j jVar3 = jVar2;
                    sparseArray = sparseArray2;
                    i4 = i11;
                    i5 = size3;
                    ViewGroup viewGroup2 = jVar3.f242n.c() ? (ViewGroup) jVar3.f242n.b(iKeyAt) : null;
                    if (viewGroup2 != null && (xVarJ = j((fragment2 = aVar.f315d), (fragment = aVar.f312a))) != null) {
                        boolean z10 = aVar.f313b;
                        boolean z11 = aVar.f316e;
                        Transition transitionF3 = fragment == null ? null : xVarJ.f(z10 ? fragment.getReenterTransition() : fragment.getEnterTransition());
                        Transition transitionF4 = fragment2 == null ? null : xVarJ.f(z11 ? fragment2.getReturnTransition() : fragment2.getExitTransition());
                        ArrayList<View> arrayList20 = new ArrayList<>();
                        ArrayList<View> arrayList21 = new ArrayList<>();
                        Fragment fragment12 = aVar.f312a;
                        Fragment fragment13 = aVar.f315d;
                        if (fragment12 == null || fragment13 == null) {
                            fragment3 = fragment;
                            arrayList3 = arrayList21;
                            arrayList4 = arrayList20;
                            transition = transitionF4;
                            bVar = bVar3;
                            i6 = i4;
                            i7 = i5;
                            z3 = true;
                            transition2 = null;
                            transitionSet = null;
                        } else {
                            boolean z12 = aVar.f313b;
                            TransitionSet transitionSetS2 = bVar3.isEmpty() ? null : xVarJ.s(xVarJ.f(z12 ? fragment13.getSharedElementReturnTransition() : fragment12.getSharedElementEnterTransition()));
                            android.support.v4.util.b bVarI2 = i(bVar3, transitionSetS2, aVar);
                            if (bVar3.isEmpty()) {
                                transitionSet2 = null;
                            } else {
                                arrayList20.addAll(bVarI2.values());
                                transitionSet2 = transitionSetS2;
                            }
                            if (transitionF3 == null && transitionF4 == null && transitionSet2 == null) {
                                fragment3 = fragment;
                                arrayList3 = arrayList21;
                                arrayList4 = arrayList20;
                                transition = transitionF4;
                                bVar = bVar3;
                                i6 = i4;
                                i7 = i5;
                                transitionSet = null;
                                z3 = true;
                                transition2 = null;
                            } else {
                                if (z12) {
                                    fragment13.getEnterTransitionCallback();
                                } else {
                                    fragment12.getEnterTransitionCallback();
                                }
                                if (transitionSet2 != null) {
                                    rect = new Rect();
                                    xVarJ.q(transitionSet2, view2, arrayList20);
                                    fragment4 = fragment12;
                                    arrayList5 = arrayList21;
                                    z4 = z12;
                                    fragment5 = fragment13;
                                    n(xVarJ, transitionSet2, transitionF4, bVarI2, aVar.f316e, aVar.f317f);
                                    if (transitionF3 != null) {
                                        xVarJ.o(transitionF3, rect);
                                    }
                                } else {
                                    fragment4 = fragment12;
                                    arrayList5 = arrayList21;
                                    z4 = z12;
                                    fragment5 = fragment13;
                                    rect = null;
                                }
                                arrayList3 = arrayList5;
                                fragment3 = fragment;
                                transition = transitionF4;
                                arrayList4 = arrayList20;
                                i6 = i4;
                                z3 = true;
                                transition2 = null;
                                bVar = bVar3;
                                i7 = i5;
                                h0.a(viewGroup2, new s(xVarJ, bVar3, transitionSet2, aVar, arrayList3, view2, fragment4, fragment5, z4, arrayList4, transitionF3, rect));
                                transitionSet = transitionSet2;
                            }
                        }
                        if (transitionF3 != null || transitionSet != null || transition != null) {
                            ArrayList<View> arrayListK4 = k(xVarJ, transition, fragment2, arrayList4, view2);
                            if (arrayListK4 != null && !arrayListK4.isEmpty()) {
                                transition2 = transition;
                            }
                            xVarJ.a(transitionF3, view2);
                            Object objM2 = m(xVarJ, transitionF3, transition2, transitionSet, fragment3, aVar.f313b);
                            if (objM2 != null) {
                                ArrayList<View> arrayList22 = new ArrayList<>();
                                xVarJ.n(objM2, transitionF3, arrayList22, transition2, arrayListK4, transitionSet, arrayList3);
                                h0.a(viewGroup2, new q(transitionF3, xVarJ, view2, fragment3, arrayList3, arrayList22, arrayListK4, transition2));
                                ArrayList<View> arrayList23 = arrayList3;
                                android.support.v4.util.b bVar5 = bVar;
                                h0.a(viewGroup2, new w(arrayList23, bVar5, 0));
                                xVarJ.c(viewGroup2, objM2);
                                h0.a(viewGroup2, new w(arrayList23, bVar5, 1));
                            }
                        }
                    }
                    i11 = i6 + 1;
                    jVar2 = jVar;
                    arrayList9 = arrayList;
                    arrayList10 = arrayList2;
                    i8 = i3;
                    z6 = z2;
                    size3 = i7;
                    sparseArray2 = sparseArray;
                }
                i6 = i4;
                i7 = i5;
                z3 = true;
                i11 = i6 + 1;
                jVar2 = jVar;
                arrayList9 = arrayList;
                arrayList10 = arrayList2;
                i8 = i3;
                z6 = z2;
                size3 = i7;
                sparseArray2 = sparseArray;
            }
        }
    }
}
