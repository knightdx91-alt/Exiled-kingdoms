package android.support.v4.app;

import android.R;
import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Looper;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.b;
import android.support.v4.app.i;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.ScaleAnimation;
import com.badlogic.gdx.graphics.VertexAttributes;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: compiled from: FragmentManager.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class j extends android.support.v4.app.i implements LayoutInflater.Factory2 {
    static Field D;
    static final DecelerateInterpolator E = new DecelerateInterpolator(2.5f);
    static final DecelerateInterpolator F = new DecelerateInterpolator(1.5f);
    ArrayList<i> A;
    n B;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    ArrayList<g> f230b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    boolean f231c;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    SparseArray<Fragment> f234f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    ArrayList<android.support.v4.app.b> f235g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    ArrayList<Fragment> f236h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    ArrayList<android.support.v4.app.b> f237i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    ArrayList<Integer> f238j;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    android.support.v4.app.h f241m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    android.support.v4.app.f f242n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    Fragment f243o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    Fragment f244p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    boolean f245q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    boolean f246r;
    boolean s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    String f247t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    boolean f248u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    ArrayList<android.support.v4.app.b> f249v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    ArrayList<Boolean> f250w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    ArrayList<Fragment> f251x;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    int f232d = 0;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    final ArrayList<Fragment> f233e = new ArrayList<>();

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private final CopyOnWriteArrayList<android.support.v4.util.h<i.a, Boolean>> f239k = new CopyOnWriteArrayList<>();

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    int f240l = 0;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    Bundle f252y = null;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    SparseArray<Parcelable> f253z = null;
    Runnable C = new a();

    /* JADX INFO: compiled from: FragmentManager.java */
    final class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            j.this.T();
        }
    }

    /* JADX INFO: compiled from: FragmentManager.java */
    private static class b extends c {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        View f255b;

        /* JADX INFO: compiled from: FragmentManager.java */
        final class a implements Runnable {
            a() {
            }

            @Override // java.lang.Runnable
            public final void run() {
                b.this.f255b.setLayerType(0, null);
            }
        }

        @Override // android.support.v4.app.j.c, android.view.animation.Animation.AnimationListener
        public final void onAnimationEnd(Animation animation) {
            View view = this.f255b;
            view.isAttachedToWindow();
            view.post(new a());
            super.onAnimationEnd(animation);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: FragmentManager.java */
    static class c implements Animation.AnimationListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Animation.AnimationListener f257a;

        c(Animation.AnimationListener animationListener) {
            this.f257a = animationListener;
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationEnd(Animation animation) {
            Animation.AnimationListener animationListener = this.f257a;
            if (animationListener != null) {
                animationListener.onAnimationEnd(animation);
            }
        }

        @Override // android.view.animation.Animation.AnimationListener
        public final void onAnimationRepeat(Animation animation) {
            Animation.AnimationListener animationListener = this.f257a;
            if (animationListener != null) {
                animationListener.onAnimationRepeat(animation);
            }
        }

        @Override // android.view.animation.Animation.AnimationListener
        public final void onAnimationStart(Animation animation) {
            Animation.AnimationListener animationListener = this.f257a;
            if (animationListener != null) {
                animationListener.onAnimationStart(animation);
            }
        }
    }

    /* JADX INFO: compiled from: FragmentManager.java */
    private static class e extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        View f260a;

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public final void onAnimationEnd(Animator animator) {
            this.f260a.setLayerType(0, null);
            animator.removeListener(this);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public final void onAnimationStart(Animator animator) {
            this.f260a.setLayerType(2, null);
        }
    }

    /* JADX INFO: compiled from: FragmentManager.java */
    static class f {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final int[] f261a = {R.attr.name, R.attr.id, R.attr.tag};
    }

    /* JADX INFO: compiled from: FragmentManager.java */
    interface g {
        boolean a(ArrayList<android.support.v4.app.b> arrayList, ArrayList<Boolean> arrayList2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: FragmentManager.java */
    class h implements g {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final int f262a;

        h(int i2) {
            this.f262a = i2;
        }

        @Override // android.support.v4.app.j.g
        public final boolean a(ArrayList<android.support.v4.app.b> arrayList, ArrayList<Boolean> arrayList2) {
            android.support.v4.app.i iVarPeekChildFragmentManager;
            j jVar = j.this;
            Fragment fragment = jVar.f244p;
            int i2 = this.f262a;
            if (fragment == null || i2 >= 0 || (iVarPeekChildFragmentManager = fragment.peekChildFragmentManager()) == null || !iVarPeekChildFragmentManager.d()) {
                return jVar.h0(arrayList, arrayList2, i2, 1);
            }
            return false;
        }
    }

    /* JADX INFO: compiled from: FragmentManager.java */
    static class i implements Fragment.e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final boolean f264a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final android.support.v4.app.b f265b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private int f266c;

        i(android.support.v4.app.b bVar, boolean z2) {
            this.f264a = z2;
            this.f265b = bVar;
        }

        public final void c() {
            android.support.v4.app.b bVar = this.f265b;
            j.f(bVar.f172a, bVar, this.f264a, false, false);
        }

        public final void d() {
            boolean z2 = this.f266c > 0;
            android.support.v4.app.b bVar = this.f265b;
            j jVar = bVar.f172a;
            int size = jVar.f233e.size();
            for (int i2 = 0; i2 < size; i2++) {
                Fragment fragment = jVar.f233e.get(i2);
                fragment.setOnStartEnterTransitionListener(null);
                if (z2 && fragment.isPostponed()) {
                    fragment.startPostponedEnterTransition();
                }
            }
            j.f(bVar.f172a, bVar, this.f264a, !z2, true);
        }

        public final boolean e() {
            return this.f266c == 0;
        }

        public final void f() {
            int i2 = this.f266c - 1;
            this.f266c = i2;
            if (i2 != 0) {
                return;
            }
            this.f265b.f172a.p0();
        }

        public final void g() {
            this.f266c++;
        }
    }

    static {
        new AccelerateInterpolator(2.5f);
        new AccelerateInterpolator(1.5f);
    }

    j() {
    }

    private void O(int i2) {
        try {
            this.f231c = true;
            e0(i2, false);
            this.f231c = false;
            T();
        } catch (Throwable th) {
            this.f231c = false;
            throw th;
        }
    }

    private void S() {
        if (this.f231c) {
            throw new IllegalStateException("FragmentManager is already executing transactions");
        }
        if (Looper.myLooper() != this.f241m.i().getLooper()) {
            throw new IllegalStateException("Must be called from main thread of fragment host");
        }
        if (this.f249v == null) {
            this.f249v = new ArrayList<>();
            this.f250w = new ArrayList<>();
        }
        this.f231c = true;
        try {
            V(null, null);
        } finally {
            this.f231c = false;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:62:0x0131  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void U(ArrayList<android.support.v4.app.b> arrayList, ArrayList<Boolean> arrayList2, int i2, int i3) {
        int i4;
        int i5;
        int i6;
        int i7;
        ArrayList<android.support.v4.app.b> arrayList3 = arrayList;
        ArrayList<Boolean> arrayList4 = arrayList2;
        boolean z2 = arrayList3.get(i2).s;
        ArrayList<Fragment> arrayList5 = this.f251x;
        if (arrayList5 == null) {
            this.f251x = new ArrayList<>();
        } else {
            arrayList5.clear();
        }
        this.f251x.addAll(this.f233e);
        Fragment fragment = this.f244p;
        int i8 = i2;
        boolean z3 = false;
        while (true) {
            int i9 = 1;
            if (i8 >= i3) {
                this.f251x.clear();
                if (!z2) {
                    t.p(this, arrayList, arrayList2, i2, i3, false);
                }
                int i10 = i2;
                while (i10 < i3) {
                    android.support.v4.app.b bVar = arrayList.get(i10);
                    if (arrayList2.get(i10).booleanValue()) {
                        bVar.e(-1);
                        bVar.h(i10 == i3 + (-1));
                    } else {
                        bVar.e(1);
                        bVar.g();
                    }
                    i10++;
                }
                if (z2) {
                    android.support.v4.util.c<Fragment> cVar = new android.support.v4.util.c<>();
                    g(cVar);
                    i4 = i2;
                    int i11 = i3;
                    for (int i12 = i3 - 1; i12 >= i4; i12--) {
                        android.support.v4.app.b bVar2 = arrayList.get(i12);
                        boolean zBooleanValue = arrayList2.get(i12).booleanValue();
                        if (bVar2.l() && !bVar2.j(i12 + 1, i3, arrayList)) {
                            if (this.A == null) {
                                this.A = new ArrayList<>();
                            }
                            i iVar = new i(bVar2, zBooleanValue);
                            this.A.add(iVar);
                            bVar2.m(iVar);
                            if (zBooleanValue) {
                                bVar2.g();
                            } else {
                                bVar2.h(false);
                            }
                            i11--;
                            if (i12 != i11) {
                                arrayList.remove(i12);
                                arrayList.add(i11, bVar2);
                            }
                            g(cVar);
                        }
                    }
                    int size = cVar.size();
                    for (int i13 = 0; i13 < size; i13++) {
                        Fragment fragmentF = cVar.f(i13);
                        if (!fragmentF.mAdded) {
                            View view = fragmentF.getView();
                            fragmentF.mPostponedAlpha = view.getAlpha();
                            view.setAlpha(0.0f);
                        }
                    }
                    i5 = i11;
                } else {
                    i4 = i2;
                    i5 = i3;
                }
                if (i5 != i4 && z2) {
                    t.p(this, arrayList, arrayList2, i2, i5, true);
                    e0(this.f240l, true);
                }
                while (i4 < i3) {
                    android.support.v4.app.b bVar3 = arrayList.get(i4);
                    if (arrayList2.get(i4).booleanValue() && (i6 = bVar3.f183l) >= 0) {
                        synchronized (this) {
                            try {
                                this.f237i.set(i6, null);
                                if (this.f238j == null) {
                                    this.f238j = new ArrayList<>();
                                }
                                this.f238j.add(Integer.valueOf(i6));
                            } catch (Throwable th) {
                                throw th;
                            }
                        }
                        bVar3.f183l = -1;
                    }
                    bVar3.getClass();
                    i4++;
                }
                return;
            }
            android.support.v4.app.b bVar4 = arrayList3.get(i8);
            if (arrayList4.get(i8).booleanValue()) {
                ArrayList<Fragment> arrayList6 = this.f251x;
                int i14 = 0;
                while (true) {
                    ArrayList<b.a> arrayList7 = bVar4.f173b;
                    if (i14 < arrayList7.size()) {
                        b.a aVar = arrayList7.get(i14);
                        int i15 = aVar.f190a;
                        if (i15 != 1) {
                            if (i15 != 3) {
                                switch (i15) {
                                    case 6:
                                        arrayList6.add(aVar.f191b);
                                        break;
                                    case 8:
                                        fragment = null;
                                        break;
                                    case 9:
                                        fragment = aVar.f191b;
                                        break;
                                }
                            }
                            i14++;
                        }
                        arrayList6.remove(aVar.f191b);
                        i14++;
                    }
                }
            } else {
                ArrayList<Fragment> arrayList8 = this.f251x;
                int i16 = 0;
                while (true) {
                    ArrayList<b.a> arrayList9 = bVar4.f173b;
                    if (i16 < arrayList9.size()) {
                        b.a aVar2 = arrayList9.get(i16);
                        int i17 = aVar2.f190a;
                        if (i17 != i9) {
                            if (i17 != 2) {
                                if (i17 == 3 || i17 == 6) {
                                    arrayList8.remove(aVar2.f191b);
                                    Fragment fragment2 = aVar2.f191b;
                                    if (fragment2 == fragment) {
                                        arrayList9.add(i16, new b.a(9, fragment2));
                                        i16++;
                                        i7 = 1;
                                        fragment = null;
                                    }
                                } else if (i17 == 7) {
                                    i7 = 1;
                                } else if (i17 == 8) {
                                    arrayList9.add(i16, new b.a(9, fragment));
                                    i16++;
                                    fragment = aVar2.f191b;
                                }
                                i7 = 1;
                            } else {
                                Fragment fragment3 = aVar2.f191b;
                                int i18 = fragment3.mContainerId;
                                boolean z4 = false;
                                for (int size2 = arrayList8.size() - 1; size2 >= 0; size2--) {
                                    Fragment fragment4 = arrayList8.get(size2);
                                    if (fragment4.mContainerId == i18) {
                                        if (fragment4 == fragment3) {
                                            z4 = true;
                                        } else {
                                            if (fragment4 == fragment) {
                                                arrayList9.add(i16, new b.a(9, fragment4));
                                                i16++;
                                                fragment = null;
                                            }
                                            b.a aVar3 = new b.a(3, fragment4);
                                            aVar3.f192c = aVar2.f192c;
                                            aVar3.f194e = aVar2.f194e;
                                            aVar3.f193d = aVar2.f193d;
                                            aVar3.f195f = aVar2.f195f;
                                            arrayList9.add(i16, aVar3);
                                            arrayList8.remove(fragment4);
                                            i16++;
                                            fragment = fragment;
                                        }
                                    }
                                }
                                i7 = 1;
                                if (z4) {
                                    arrayList9.remove(i16);
                                    i16--;
                                } else {
                                    aVar2.f190a = 1;
                                    arrayList8.add(fragment3);
                                }
                            }
                            i16 += i7;
                            i9 = i7;
                        } else {
                            i7 = i9;
                        }
                        arrayList8.add(aVar2.f191b);
                        i16 += i7;
                        i9 = i7;
                    }
                }
            }
            z3 = z3 || bVar4.f180i;
            i8++;
            arrayList3 = arrayList;
            arrayList4 = arrayList2;
        }
    }

    private void V(ArrayList<android.support.v4.app.b> arrayList, ArrayList<Boolean> arrayList2) {
        int iIndexOf;
        int iIndexOf2;
        ArrayList<i> arrayList3 = this.A;
        int size = arrayList3 == null ? 0 : arrayList3.size();
        int i2 = 0;
        while (i2 < size) {
            i iVar = this.A.get(i2);
            if (arrayList != null && !iVar.f264a && (iIndexOf2 = arrayList.indexOf(iVar.f265b)) != -1 && arrayList2.get(iIndexOf2).booleanValue()) {
                iVar.c();
            } else if (iVar.e() || (arrayList != null && iVar.f265b.j(0, arrayList.size(), arrayList))) {
                this.A.remove(i2);
                i2--;
                size--;
                if (arrayList == null || iVar.f264a || (iIndexOf = arrayList.indexOf(iVar.f265b)) == -1 || !arrayList2.get(iIndexOf).booleanValue()) {
                    iVar.d();
                } else {
                    iVar.c();
                }
            }
            i2++;
        }
    }

    private static Animation.AnimationListener Y(Animation animation) {
        try {
            if (D == null) {
                Field declaredField = Animation.class.getDeclaredField("mListener");
                D = declaredField;
                declaredField.setAccessible(true);
            }
            return (Animation.AnimationListener) D.get(animation);
        } catch (IllegalAccessException e2) {
            Log.e("FragmentManager", "Cannot access Animation's mListener field", e2);
            return null;
        } catch (NoSuchFieldException e3) {
            Log.e("FragmentManager", "No field with the name mListener is found in Animation class", e3);
            return null;
        }
    }

    static d b0(float f2, float f3, float f4, float f5) {
        AnimationSet animationSet = new AnimationSet(false);
        ScaleAnimation scaleAnimation = new ScaleAnimation(f2, f3, f2, f3, 1, 0.5f, 1, 0.5f);
        scaleAnimation.setInterpolator(E);
        scaleAnimation.setDuration(220L);
        animationSet.addAnimation(scaleAnimation);
        AlphaAnimation alphaAnimation = new AlphaAnimation(f4, f5);
        alphaAnimation.setInterpolator(F);
        alphaAnimation.setDuration(220L);
        animationSet.addAnimation(alphaAnimation);
        return new d(animationSet);
    }

    static boolean c0(Animator animator) {
        if (animator == null) {
            return false;
        }
        if (animator instanceof ValueAnimator) {
            for (PropertyValuesHolder propertyValuesHolder : ((ValueAnimator) animator).getValues()) {
                if ("alpha".equals(propertyValuesHolder.getPropertyName())) {
                    return true;
                }
            }
        } else if (animator instanceof AnimatorSet) {
            ArrayList<Animator> childAnimations = ((AnimatorSet) animator).getChildAnimations();
            for (int i2 = 0; i2 < childAnimations.size(); i2++) {
                if (c0(childAnimations.get(i2))) {
                    return true;
                }
            }
        }
        return false;
    }

    static void f(j jVar, android.support.v4.app.b bVar, boolean z2, boolean z3, boolean z4) {
        jVar.getClass();
        if (z2) {
            bVar.h(z4);
        } else {
            bVar.g();
        }
        ArrayList arrayList = new ArrayList(1);
        ArrayList arrayList2 = new ArrayList(1);
        arrayList.add(bVar);
        arrayList2.add(Boolean.valueOf(z2));
        if (z3) {
            t.p(jVar, arrayList, arrayList2, 0, 1, true);
        }
        if (z4) {
            jVar.e0(jVar.f240l, true);
        }
        SparseArray<Fragment> sparseArray = jVar.f234f;
        if (sparseArray != null) {
            int size = sparseArray.size();
            for (int i2 = 0; i2 < size; i2++) {
                Fragment fragmentValueAt = jVar.f234f.valueAt(i2);
                if (fragmentValueAt != null && fragmentValueAt.mView != null && fragmentValueAt.mIsNewlyAdded && bVar.i(fragmentValueAt.mContainerId)) {
                    float f2 = fragmentValueAt.mPostponedAlpha;
                    if (f2 > 0.0f) {
                        fragmentValueAt.mView.setAlpha(f2);
                    }
                    if (z4) {
                        fragmentValueAt.mPostponedAlpha = 0.0f;
                    } else {
                        fragmentValueAt.mPostponedAlpha = -1.0f;
                        fragmentValueAt.mIsNewlyAdded = false;
                    }
                }
            }
        }
    }

    private void g(android.support.v4.util.c<Fragment> cVar) {
        int i2 = this.f240l;
        if (i2 < 1) {
            return;
        }
        int iMin = Math.min(i2, 4);
        ArrayList<Fragment> arrayList = this.f233e;
        int size = arrayList.size();
        for (int i3 = 0; i3 < size; i3++) {
            Fragment fragment = arrayList.get(i3);
            if (fragment.mState < iMin) {
                f0(fragment, iMin, fragment.getNextAnim(), fragment.getNextTransition(), false);
                if (fragment.mView != null && !fragment.mHidden && fragment.mIsNewlyAdded) {
                    cVar.add(fragment);
                }
            }
        }
    }

    private void j() {
        SparseArray<Fragment> sparseArray = this.f234f;
        if (sparseArray != null) {
            for (int size = sparseArray.size() - 1; size >= 0; size--) {
                if (this.f234f.valueAt(size) == null) {
                    SparseArray<Fragment> sparseArray2 = this.f234f;
                    sparseArray2.delete(sparseArray2.keyAt(size));
                }
            }
        }
    }

    private void j0(ArrayList<android.support.v4.app.b> arrayList, ArrayList<Boolean> arrayList2) {
        if (arrayList == null || arrayList.isEmpty()) {
            return;
        }
        if (arrayList2 == null || arrayList.size() != arrayList2.size()) {
            throw new IllegalStateException("Internal error with the back stack records");
        }
        V(arrayList, arrayList2);
        int size = arrayList.size();
        int i2 = 0;
        int i3 = 0;
        while (i2 < size) {
            if (!arrayList.get(i2).s) {
                if (i3 != i2) {
                    U(arrayList, arrayList2, i3, i2);
                }
                i3 = i2 + 1;
                if (arrayList2.get(i2).booleanValue()) {
                    while (i3 < size && arrayList2.get(i3).booleanValue() && !arrayList.get(i3).s) {
                        i3++;
                    }
                }
                U(arrayList, arrayList2, i2, i3);
                i2 = i3 - 1;
            }
            i2++;
        }
        if (i3 != size) {
            U(arrayList, arrayList2, i3, size);
        }
    }

    private void k() {
        if (this.f246r) {
            throw new IllegalStateException("Can not perform this action after onSaveInstanceState");
        }
        if (this.f247t == null) {
            return;
        }
        throw new IllegalStateException("Can not perform this action inside of " + this.f247t);
    }

    private void l() {
        this.f231c = false;
        this.f250w.clear();
        this.f249v.clear();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void p0() {
        synchronized (this) {
            try {
                ArrayList<i> arrayList = this.A;
                boolean z2 = false;
                boolean z3 = (arrayList == null || arrayList.isEmpty()) ? false : true;
                ArrayList<g> arrayList2 = this.f230b;
                if (arrayList2 != null && arrayList2.size() == 1) {
                    z2 = true;
                }
                if (z3 || z2) {
                    this.f241m.i().removeCallbacks(this.C);
                    this.f241m.i().post(this.C);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0040  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static void q0(View view, d dVar) {
        boolean zC0;
        if (view != null) {
            int layerType = view.getLayerType();
            Animator animator = dVar.f259b;
            Animation animation = dVar.f258a;
            boolean z2 = false;
            if (layerType == 0 && view.hasOverlappingRendering()) {
                if (animation instanceof AlphaAnimation) {
                    zC0 = true;
                    if (zC0) {
                    }
                } else {
                    if (animation instanceof AnimationSet) {
                        List<Animation> animations = ((AnimationSet) animation).getAnimations();
                        for (int i2 = 0; i2 < animations.size(); i2++) {
                            if (animations.get(i2) instanceof AlphaAnimation) {
                                zC0 = true;
                                break;
                            }
                        }
                        zC0 = false;
                    } else {
                        zC0 = c0(animator);
                    }
                    if (zC0) {
                        z2 = true;
                    }
                }
            }
            if (z2) {
                if (animator != null) {
                    e eVar = new e();
                    eVar.f260a = view;
                    animator.addListener(eVar);
                } else {
                    Animation.AnimationListener animationListenerY = Y(animation);
                    view.setLayerType(2, null);
                    b bVar = new b(animationListenerY);
                    bVar.f255b = view;
                    animation.setAnimationListener(bVar);
                }
            }
        }
    }

    private static void s0(n nVar) {
        if (nVar == null) {
            return;
        }
        List<Fragment> listB = nVar.b();
        if (listB != null) {
            Iterator<Fragment> it = listB.iterator();
            while (it.hasNext()) {
                it.next().mRetaining = true;
            }
        }
        List<n> listA = nVar.a();
        if (listA != null) {
            Iterator<n> it2 = listA.iterator();
            while (it2.hasNext()) {
                s0(it2.next());
            }
        }
    }

    private void u0(RuntimeException runtimeException) {
        Log.e("FragmentManager", runtimeException.getMessage());
        Log.e("FragmentManager", "Activity state:");
        PrintWriter printWriter = new PrintWriter(new android.support.v4.util.d());
        android.support.v4.app.h hVar = this.f241m;
        if (hVar == null) {
            try {
                b("  ", null, printWriter, new String[0]);
                throw runtimeException;
            } catch (Exception e2) {
                Log.e("FragmentManager", "Failed dumping state", e2);
                throw runtimeException;
            }
        }
        try {
            FragmentActivity.this.dump("  ", null, printWriter, new String[0]);
            throw runtimeException;
        } catch (Exception e3) {
            Log.e("FragmentManager", "Failed dumping state", e3);
            throw runtimeException;
        }
    }

    final void A(boolean z2) {
        Fragment fragment = this.f243o;
        if (fragment != null) {
            android.support.v4.app.i fragmentManager = fragment.getFragmentManager();
            if (fragmentManager instanceof j) {
                ((j) fragmentManager).A(true);
            }
        }
        for (android.support.v4.util.h<i.a, Boolean> hVar : this.f239k) {
            if (!z2 || hVar.f573b.booleanValue()) {
                hVar.f572a.getClass();
            }
        }
    }

    final void B(boolean z2) {
        Fragment fragment = this.f243o;
        if (fragment != null) {
            android.support.v4.app.i fragmentManager = fragment.getFragmentManager();
            if (fragmentManager instanceof j) {
                ((j) fragmentManager).B(true);
            }
        }
        for (android.support.v4.util.h<i.a, Boolean> hVar : this.f239k) {
            if (!z2 || hVar.f573b.booleanValue()) {
                hVar.f572a.getClass();
            }
        }
    }

    final void C(boolean z2) {
        Fragment fragment = this.f243o;
        if (fragment != null) {
            android.support.v4.app.i fragmentManager = fragment.getFragmentManager();
            if (fragmentManager instanceof j) {
                ((j) fragmentManager).C(true);
            }
        }
        for (android.support.v4.util.h<i.a, Boolean> hVar : this.f239k) {
            if (!z2 || hVar.f573b.booleanValue()) {
                hVar.f572a.getClass();
            }
        }
    }

    final void D(boolean z2) {
        Fragment fragment = this.f243o;
        if (fragment != null) {
            android.support.v4.app.i fragmentManager = fragment.getFragmentManager();
            if (fragmentManager instanceof j) {
                ((j) fragmentManager).D(true);
            }
        }
        for (android.support.v4.util.h<i.a, Boolean> hVar : this.f239k) {
            if (!z2 || hVar.f573b.booleanValue()) {
                hVar.f572a.getClass();
            }
        }
    }

    final void E(boolean z2) {
        Fragment fragment = this.f243o;
        if (fragment != null) {
            android.support.v4.app.i fragmentManager = fragment.getFragmentManager();
            if (fragmentManager instanceof j) {
                ((j) fragmentManager).E(true);
            }
        }
        for (android.support.v4.util.h<i.a, Boolean> hVar : this.f239k) {
            if (!z2 || hVar.f573b.booleanValue()) {
                hVar.f572a.getClass();
            }
        }
    }

    final void F(boolean z2) {
        Fragment fragment = this.f243o;
        if (fragment != null) {
            android.support.v4.app.i fragmentManager = fragment.getFragmentManager();
            if (fragmentManager instanceof j) {
                ((j) fragmentManager).F(true);
            }
        }
        for (android.support.v4.util.h<i.a, Boolean> hVar : this.f239k) {
            if (!z2 || hVar.f573b.booleanValue()) {
                hVar.f572a.getClass();
            }
        }
    }

    final void G(boolean z2) {
        Fragment fragment = this.f243o;
        if (fragment != null) {
            android.support.v4.app.i fragmentManager = fragment.getFragmentManager();
            if (fragmentManager instanceof j) {
                ((j) fragmentManager).G(true);
            }
        }
        for (android.support.v4.util.h<i.a, Boolean> hVar : this.f239k) {
            if (!z2 || hVar.f573b.booleanValue()) {
                hVar.f572a.getClass();
            }
        }
    }

    public final boolean H(MenuItem menuItem) {
        if (this.f240l < 1) {
            return false;
        }
        int i2 = 0;
        while (true) {
            ArrayList<Fragment> arrayList = this.f233e;
            if (i2 >= arrayList.size()) {
                return false;
            }
            Fragment fragment = arrayList.get(i2);
            if (fragment != null && fragment.performOptionsItemSelected(menuItem)) {
                return true;
            }
            i2++;
        }
    }

    public final void I(Menu menu) {
        if (this.f240l < 1) {
            return;
        }
        int i2 = 0;
        while (true) {
            ArrayList<Fragment> arrayList = this.f233e;
            if (i2 >= arrayList.size()) {
                return;
            }
            Fragment fragment = arrayList.get(i2);
            if (fragment != null) {
                fragment.performOptionsMenuClosed(menu);
            }
            i2++;
        }
    }

    public final void J() {
        O(4);
    }

    public final boolean K(Menu menu) {
        int i2 = 0;
        if (this.f240l < 1) {
            return false;
        }
        boolean z2 = false;
        while (true) {
            ArrayList<Fragment> arrayList = this.f233e;
            if (i2 >= arrayList.size()) {
                return z2;
            }
            Fragment fragment = arrayList.get(i2);
            if (fragment != null && fragment.performPrepareOptionsMenu(menu)) {
                z2 = true;
            }
            i2++;
        }
    }

    public final void L() {
        O(2);
    }

    public final void M() {
        this.f246r = false;
        O(5);
    }

    public final void N() {
        this.f246r = false;
        O(4);
    }

    public final void P() {
        this.f246r = true;
        O(3);
    }

    final void Q() {
        z zVar;
        if (this.f248u) {
            boolean zH = false;
            for (int i2 = 0; i2 < this.f234f.size(); i2++) {
                Fragment fragmentValueAt = this.f234f.valueAt(i2);
                if (fragmentValueAt != null && (zVar = fragmentValueAt.mLoaderManager) != null) {
                    zH |= zVar.h();
                }
            }
            if (zH) {
                return;
            }
            this.f248u = false;
            t0();
        }
    }

    public final void R(g gVar, boolean z2) {
        if (!z2) {
            k();
        }
        synchronized (this) {
            try {
                if (!this.s && this.f241m != null) {
                    if (this.f230b == null) {
                        this.f230b = new ArrayList<>();
                    }
                    this.f230b.add(gVar);
                    p0();
                    return;
                }
                if (!z2) {
                    throw new IllegalStateException("Activity has been destroyed");
                }
            } finally {
            }
        }
    }

    public final boolean T() {
        boolean zA;
        S();
        boolean z2 = false;
        while (true) {
            ArrayList<android.support.v4.app.b> arrayList = this.f249v;
            ArrayList<Boolean> arrayList2 = this.f250w;
            synchronized (this) {
                try {
                    ArrayList<g> arrayList3 = this.f230b;
                    if (arrayList3 == null || arrayList3.size() == 0) {
                        zA = false;
                    } else {
                        int size = this.f230b.size();
                        zA = false;
                        for (int i2 = 0; i2 < size; i2++) {
                            zA |= this.f230b.get(i2).a(arrayList, arrayList2);
                        }
                        this.f230b.clear();
                        this.f241m.i().removeCallbacks(this.C);
                    }
                } finally {
                }
            }
            if (!zA) {
                Q();
                j();
                return z2;
            }
            z2 = true;
            this.f231c = true;
            try {
                j0(this.f249v, this.f250w);
            } finally {
                l();
            }
        }
    }

    public final Fragment W(int i2) {
        ArrayList<Fragment> arrayList = this.f233e;
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            Fragment fragment = arrayList.get(size);
            if (fragment != null && fragment.mFragmentId == i2) {
                return fragment;
            }
        }
        SparseArray<Fragment> sparseArray = this.f234f;
        if (sparseArray == null) {
            return null;
        }
        for (int size2 = sparseArray.size() - 1; size2 >= 0; size2--) {
            Fragment fragmentValueAt = this.f234f.valueAt(size2);
            if (fragmentValueAt != null && fragmentValueAt.mFragmentId == i2) {
                return fragmentValueAt;
            }
        }
        return null;
    }

    public final Fragment X(String str) {
        Fragment fragmentFindFragmentByWho;
        SparseArray<Fragment> sparseArray = this.f234f;
        if (sparseArray == null || str == null) {
            return null;
        }
        for (int size = sparseArray.size() - 1; size >= 0; size--) {
            Fragment fragmentValueAt = this.f234f.valueAt(size);
            if (fragmentValueAt != null && (fragmentFindFragmentByWho = fragmentValueAt.findFragmentByWho(str)) != null) {
                return fragmentFindFragmentByWho;
            }
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x004e A[Catch: RuntimeException -> 0x0054, TRY_LEAVE, TryCatch #0 {RuntimeException -> 0x0054, blocks: (B:19:0x0044, B:21:0x004e), top: B:74:0x0044 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    final d Z(Fragment fragment, int i2, boolean z2, int i3) {
        Window window;
        Animator animatorLoadAnimator;
        int nextAnim = fragment.getNextAnim();
        Animation animationOnCreateAnimation = fragment.onCreateAnimation(i2, z2, nextAnim);
        if (animationOnCreateAnimation != null) {
            return new d(animationOnCreateAnimation);
        }
        Animator animatorOnCreateAnimator = fragment.onCreateAnimator(i2, z2, nextAnim);
        if (animatorOnCreateAnimator != null) {
            return new d(animatorOnCreateAnimator);
        }
        if (nextAnim != 0) {
            boolean zEquals = "anim".equals(this.f241m.f219b.getResources().getResourceTypeName(nextAnim));
            if (zEquals) {
                try {
                    Animation animationLoadAnimation = AnimationUtils.loadAnimation(this.f241m.f219b, nextAnim);
                    if (animationLoadAnimation != null) {
                        return new d(animationLoadAnimation);
                    }
                } catch (Resources.NotFoundException e2) {
                    throw e2;
                } catch (RuntimeException unused) {
                    try {
                        animatorLoadAnimator = AnimatorInflater.loadAnimator(this.f241m.f219b, nextAnim);
                        if (animatorLoadAnimator != null) {
                        }
                    } catch (RuntimeException e3) {
                        if (zEquals) {
                            throw e3;
                        }
                        Animation animationLoadAnimation2 = AnimationUtils.loadAnimation(this.f241m.f219b, nextAnim);
                        if (animationLoadAnimation2 != null) {
                            return new d(animationLoadAnimation2);
                        }
                    }
                }
            } else {
                animatorLoadAnimator = AnimatorInflater.loadAnimator(this.f241m.f219b, nextAnim);
                if (animatorLoadAnimator != null) {
                    return new d(animatorLoadAnimator);
                }
            }
        }
        if (i2 == 0) {
            return null;
        }
        byte b2 = i2 != 4097 ? i2 != 4099 ? i2 != 8194 ? (byte) -1 : z2 ? (byte) 3 : (byte) 4 : z2 ? (byte) 5 : (byte) 6 : z2 ? (byte) 1 : (byte) 2;
        if (b2 < 0) {
            return null;
        }
        DecelerateInterpolator decelerateInterpolator = F;
        switch (b2) {
            case 1:
                FragmentActivity fragmentActivity = this.f241m.f219b;
                return b0(1.125f, 1.0f, 0.0f, 1.0f);
            case 2:
                FragmentActivity fragmentActivity2 = this.f241m.f219b;
                return b0(1.0f, 0.975f, 1.0f, 0.0f);
            case 3:
                FragmentActivity fragmentActivity3 = this.f241m.f219b;
                return b0(0.975f, 1.0f, 0.0f, 1.0f);
            case 4:
                FragmentActivity fragmentActivity4 = this.f241m.f219b;
                return b0(1.0f, 1.075f, 1.0f, 0.0f);
            case 5:
                FragmentActivity fragmentActivity5 = this.f241m.f219b;
                AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
                alphaAnimation.setInterpolator(decelerateInterpolator);
                alphaAnimation.setDuration(220L);
                return new d(alphaAnimation);
            case 6:
                FragmentActivity fragmentActivity6 = this.f241m.f219b;
                AlphaAnimation alphaAnimation2 = new AlphaAnimation(1.0f, 0.0f);
                alphaAnimation2.setInterpolator(decelerateInterpolator);
                alphaAnimation2.setDuration(220L);
                return new d(alphaAnimation2);
            default:
                if (i3 == 0 && FragmentActivity.this.getWindow() != null && (window = FragmentActivity.this.getWindow()) != null) {
                    int i4 = window.getAttributes().windowAnimations;
                }
                return null;
        }
    }

    @Override // android.support.v4.app.i
    public final o a() {
        return new android.support.v4.app.b(this);
    }

    final void a0(Fragment fragment) {
        if (fragment.mIndex >= 0) {
            return;
        }
        int i2 = this.f232d;
        this.f232d = i2 + 1;
        fragment.setIndex(i2, this.f243o);
        if (this.f234f == null) {
            this.f234f = new SparseArray<>();
        }
        this.f234f.put(fragment.mIndex, fragment);
    }

    @Override // android.support.v4.app.i
    public final void b(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        int size;
        int size2;
        int size3;
        String str2;
        int size4;
        int size5;
        String strK = a.a.k(str, "    ");
        SparseArray<Fragment> sparseArray = this.f234f;
        if (sparseArray != null && (size5 = sparseArray.size()) > 0) {
            printWriter.print(str);
            printWriter.print("Active Fragments in ");
            printWriter.print(Integer.toHexString(System.identityHashCode(this)));
            printWriter.println(":");
            for (int i2 = 0; i2 < size5; i2++) {
                Fragment fragmentValueAt = this.f234f.valueAt(i2);
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i2);
                printWriter.print(": ");
                printWriter.println(fragmentValueAt);
                if (fragmentValueAt != null) {
                    fragmentValueAt.dump(strK, fileDescriptor, printWriter, strArr);
                }
            }
        }
        int size6 = this.f233e.size();
        if (size6 > 0) {
            printWriter.print(str);
            printWriter.println("Added Fragments:");
            for (int i3 = 0; i3 < size6; i3++) {
                Fragment fragment = this.f233e.get(i3);
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i3);
                printWriter.print(": ");
                printWriter.println(fragment.toString());
            }
        }
        ArrayList<Fragment> arrayList = this.f236h;
        if (arrayList != null && (size4 = arrayList.size()) > 0) {
            printWriter.print(str);
            printWriter.println("Fragments Created Menus:");
            for (int i4 = 0; i4 < size4; i4++) {
                Fragment fragment2 = this.f236h.get(i4);
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i4);
                printWriter.print(": ");
                printWriter.println(fragment2.toString());
            }
        }
        ArrayList<android.support.v4.app.b> arrayList2 = this.f235g;
        if (arrayList2 != null && (size3 = arrayList2.size()) > 0) {
            printWriter.print(str);
            printWriter.println("Back Stack:");
            for (int i5 = 0; i5 < size3; i5++) {
                android.support.v4.app.b bVar = this.f235g.get(i5);
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i5);
                printWriter.print(": ");
                printWriter.println(bVar.toString());
                printWriter.print(strK);
                printWriter.print("mName=");
                printWriter.print(bVar.f181j);
                printWriter.print(" mIndex=");
                printWriter.print(bVar.f183l);
                printWriter.print(" mCommitted=");
                printWriter.println(bVar.f182k);
                if (bVar.f178g != 0) {
                    printWriter.print(strK);
                    printWriter.print("mTransition=#");
                    printWriter.print(Integer.toHexString(bVar.f178g));
                    printWriter.print(" mTransitionStyle=#");
                    printWriter.println(Integer.toHexString(bVar.f179h));
                }
                if (bVar.f174c != 0 || bVar.f175d != 0) {
                    printWriter.print(strK);
                    printWriter.print("mEnterAnim=#");
                    printWriter.print(Integer.toHexString(bVar.f174c));
                    printWriter.print(" mExitAnim=#");
                    printWriter.println(Integer.toHexString(bVar.f175d));
                }
                if (bVar.f176e != 0 || bVar.f177f != 0) {
                    printWriter.print(strK);
                    printWriter.print("mPopEnterAnim=#");
                    printWriter.print(Integer.toHexString(bVar.f176e));
                    printWriter.print(" mPopExitAnim=#");
                    printWriter.println(Integer.toHexString(bVar.f177f));
                }
                if (bVar.f184m != 0 || bVar.f185n != null) {
                    printWriter.print(strK);
                    printWriter.print("mBreadCrumbTitleRes=#");
                    printWriter.print(Integer.toHexString(bVar.f184m));
                    printWriter.print(" mBreadCrumbTitleText=");
                    printWriter.println(bVar.f185n);
                }
                if (bVar.f186o != 0 || bVar.f187p != null) {
                    printWriter.print(strK);
                    printWriter.print("mBreadCrumbShortTitleRes=#");
                    printWriter.print(Integer.toHexString(bVar.f186o));
                    printWriter.print(" mBreadCrumbShortTitleText=");
                    printWriter.println(bVar.f187p);
                }
                ArrayList<b.a> arrayList3 = bVar.f173b;
                if (!arrayList3.isEmpty()) {
                    printWriter.print(strK);
                    printWriter.println("Operations:");
                    int size7 = arrayList3.size();
                    for (int i6 = 0; i6 < size7; i6++) {
                        b.a aVar = arrayList3.get(i6);
                        switch (aVar.f190a) {
                            case 0:
                                str2 = "NULL";
                                break;
                            case 1:
                                str2 = "ADD";
                                break;
                            case 2:
                                str2 = "REPLACE";
                                break;
                            case 3:
                                str2 = "REMOVE";
                                break;
                            case 4:
                                str2 = "HIDE";
                                break;
                            case 5:
                                str2 = "SHOW";
                                break;
                            case 6:
                                str2 = "DETACH";
                                break;
                            case 7:
                                str2 = "ATTACH";
                                break;
                            case 8:
                                str2 = "SET_PRIMARY_NAV";
                                break;
                            case 9:
                                str2 = "UNSET_PRIMARY_NAV";
                                break;
                            default:
                                str2 = "cmd=" + aVar.f190a;
                                break;
                        }
                        printWriter.print(strK);
                        printWriter.print("  Op #");
                        printWriter.print(i6);
                        printWriter.print(": ");
                        printWriter.print(str2);
                        printWriter.print(" ");
                        printWriter.println(aVar.f191b);
                        if (aVar.f192c != 0 || aVar.f193d != 0) {
                            printWriter.print(strK);
                            printWriter.print("enterAnim=#");
                            printWriter.print(Integer.toHexString(aVar.f192c));
                            printWriter.print(" exitAnim=#");
                            printWriter.println(Integer.toHexString(aVar.f193d));
                        }
                        if (aVar.f194e != 0 || aVar.f195f != 0) {
                            printWriter.print(strK);
                            printWriter.print("popEnterAnim=#");
                            printWriter.print(Integer.toHexString(aVar.f194e));
                            printWriter.print(" popExitAnim=#");
                            printWriter.println(Integer.toHexString(aVar.f195f));
                        }
                    }
                }
            }
        }
        synchronized (this) {
            try {
                ArrayList<android.support.v4.app.b> arrayList4 = this.f237i;
                if (arrayList4 != null && (size2 = arrayList4.size()) > 0) {
                    printWriter.print(str);
                    printWriter.println("Back Stack Indices:");
                    for (int i7 = 0; i7 < size2; i7++) {
                        Object obj = (android.support.v4.app.b) this.f237i.get(i7);
                        printWriter.print(str);
                        printWriter.print("  #");
                        printWriter.print(i7);
                        printWriter.print(": ");
                        printWriter.println(obj);
                    }
                }
                ArrayList<Integer> arrayList5 = this.f238j;
                if (arrayList5 != null && arrayList5.size() > 0) {
                    printWriter.print(str);
                    printWriter.print("mAvailBackStackIndices: ");
                    printWriter.println(Arrays.toString(this.f238j.toArray()));
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        ArrayList<g> arrayList6 = this.f230b;
        if (arrayList6 != null && (size = arrayList6.size()) > 0) {
            printWriter.print(str);
            printWriter.println("Pending Actions:");
            for (int i8 = 0; i8 < size; i8++) {
                Object obj2 = (g) this.f230b.get(i8);
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i8);
                printWriter.print(": ");
                printWriter.println(obj2);
            }
        }
        printWriter.print(str);
        printWriter.println("FragmentManager misc state:");
        printWriter.print(str);
        printWriter.print("  mHost=");
        printWriter.println(this.f241m);
        printWriter.print(str);
        printWriter.print("  mContainer=");
        printWriter.println(this.f242n);
        if (this.f243o != null) {
            printWriter.print(str);
            printWriter.print("  mParent=");
            printWriter.println(this.f243o);
        }
        printWriter.print(str);
        printWriter.print("  mCurState=");
        printWriter.print(this.f240l);
        printWriter.print(" mStateSaved=");
        printWriter.print(this.f246r);
        printWriter.print(" mDestroyed=");
        printWriter.println(this.s);
        if (this.f245q) {
            printWriter.print(str);
            printWriter.print("  mNeedMenuInvalidate=");
            printWriter.println(this.f245q);
        }
        if (this.f247t != null) {
            printWriter.print(str);
            printWriter.print("  mNoTransactionsBecause=");
            printWriter.println(this.f247t);
        }
    }

    @Override // android.support.v4.app.i
    public final Fragment c(String str) {
        ArrayList<Fragment> arrayList = this.f233e;
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            Fragment fragment = arrayList.get(size);
            if (fragment != null && str.equals(fragment.mTag)) {
                return fragment;
            }
        }
        SparseArray<Fragment> sparseArray = this.f234f;
        if (sparseArray == null) {
            return null;
        }
        for (int size2 = sparseArray.size() - 1; size2 >= 0; size2--) {
            Fragment fragmentValueAt = this.f234f.valueAt(size2);
            if (fragmentValueAt != null && str.equals(fragmentValueAt.mTag)) {
                return fragmentValueAt;
            }
        }
        return null;
    }

    @Override // android.support.v4.app.i
    public final boolean d() {
        android.support.v4.app.i iVarPeekChildFragmentManager;
        k();
        T();
        S();
        Fragment fragment = this.f244p;
        if (fragment != null && (iVarPeekChildFragmentManager = fragment.peekChildFragmentManager()) != null && iVarPeekChildFragmentManager.d()) {
            return true;
        }
        boolean zH0 = h0(this.f249v, this.f250w, -1, 0);
        if (zH0) {
            this.f231c = true;
            try {
                j0(this.f249v, this.f250w);
            } finally {
                l();
            }
        }
        Q();
        j();
        return zH0;
    }

    final void d0(Fragment fragment) {
        Animator animator;
        if (fragment == null) {
            return;
        }
        int iMin = this.f240l;
        if (fragment.mRemoving) {
            iMin = fragment.isInBackStack() ? Math.min(iMin, 1) : Math.min(iMin, 0);
        }
        f0(fragment, iMin, fragment.getNextTransition(), fragment.getNextTransitionStyle(), false);
        if (fragment.mView != null) {
            ViewGroup viewGroup = fragment.mContainer;
            Fragment fragment2 = null;
            if (viewGroup != null) {
                ArrayList<Fragment> arrayList = this.f233e;
                int iIndexOf = arrayList.indexOf(fragment) - 1;
                while (true) {
                    if (iIndexOf < 0) {
                        break;
                    }
                    Fragment fragment3 = arrayList.get(iIndexOf);
                    if (fragment3.mContainer == viewGroup && fragment3.mView != null) {
                        fragment2 = fragment3;
                        break;
                    }
                    iIndexOf--;
                }
            }
            if (fragment2 != null) {
                View view = fragment2.mView;
                ViewGroup viewGroup2 = fragment.mContainer;
                int iIndexOfChild = viewGroup2.indexOfChild(view);
                int iIndexOfChild2 = viewGroup2.indexOfChild(fragment.mView);
                if (iIndexOfChild2 < iIndexOfChild) {
                    viewGroup2.removeViewAt(iIndexOfChild2);
                    viewGroup2.addView(fragment.mView, iIndexOfChild);
                }
            }
            if (fragment.mIsNewlyAdded && fragment.mContainer != null) {
                float f2 = fragment.mPostponedAlpha;
                if (f2 > 0.0f) {
                    fragment.mView.setAlpha(f2);
                }
                fragment.mPostponedAlpha = 0.0f;
                fragment.mIsNewlyAdded = false;
                d dVarZ = Z(fragment, fragment.getNextTransition(), true, fragment.getNextTransitionStyle());
                if (dVarZ != null) {
                    q0(fragment.mView, dVarZ);
                    Animation animation = dVarZ.f258a;
                    if (animation != null) {
                        fragment.mView.startAnimation(animation);
                    } else {
                        View view2 = fragment.mView;
                        Animator animator2 = dVarZ.f259b;
                        animator2.setTarget(view2);
                        animator2.start();
                    }
                }
            }
        }
        if (fragment.mHiddenChanged) {
            if (fragment.mView != null) {
                d dVarZ2 = Z(fragment, fragment.getNextTransition(), !fragment.mHidden, fragment.getNextTransitionStyle());
                if (dVarZ2 == null || (animator = dVarZ2.f259b) == null) {
                    if (dVarZ2 != null) {
                        q0(fragment.mView, dVarZ2);
                        View view3 = fragment.mView;
                        Animation animation2 = dVarZ2.f258a;
                        view3.startAnimation(animation2);
                        animation2.start();
                    }
                    fragment.mView.setVisibility((!fragment.mHidden || fragment.isHideReplaced()) ? 0 : 8);
                    if (fragment.isHideReplaced()) {
                        fragment.setHideReplaced(false);
                    }
                } else {
                    animator.setTarget(fragment.mView);
                    if (!fragment.mHidden) {
                        fragment.mView.setVisibility(0);
                    } else if (fragment.isHideReplaced()) {
                        fragment.setHideReplaced(false);
                    } else {
                        ViewGroup viewGroup3 = fragment.mContainer;
                        View view4 = fragment.mView;
                        viewGroup3.startViewTransition(view4);
                        animator.addListener(new m(viewGroup3, view4, fragment));
                    }
                    q0(fragment.mView, dVarZ2);
                    animator.start();
                }
            }
            if (fragment.mAdded && fragment.mHasMenu && fragment.mMenuVisible) {
                this.f245q = true;
            }
            fragment.mHiddenChanged = false;
            fragment.onHiddenChanged(fragment.mHidden);
        }
    }

    final void e0(int i2, boolean z2) {
        android.support.v4.app.h hVar;
        if (this.f241m == null && i2 != 0) {
            throw new IllegalStateException("No activity");
        }
        if (z2 || i2 != this.f240l) {
            this.f240l = i2;
            if (this.f234f != null) {
                ArrayList<Fragment> arrayList = this.f233e;
                int size = arrayList.size();
                boolean zH = false;
                for (int i3 = 0; i3 < size; i3++) {
                    Fragment fragment = arrayList.get(i3);
                    d0(fragment);
                    z zVar = fragment.mLoaderManager;
                    if (zVar != null) {
                        zH |= zVar.h();
                    }
                }
                int size2 = this.f234f.size();
                for (int i4 = 0; i4 < size2; i4++) {
                    Fragment fragmentValueAt = this.f234f.valueAt(i4);
                    if (fragmentValueAt != null && ((fragmentValueAt.mRemoving || fragmentValueAt.mDetached) && !fragmentValueAt.mIsNewlyAdded)) {
                        d0(fragmentValueAt);
                        z zVar2 = fragmentValueAt.mLoaderManager;
                        if (zVar2 != null) {
                            zH = zVar2.h() | zH;
                        }
                    }
                }
                if (!zH) {
                    t0();
                }
                if (this.f245q && (hVar = this.f241m) != null && this.f240l == 5) {
                    FragmentActivity.this.supportInvalidateOptionsMenu();
                    this.f245q = false;
                }
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:142:0x027b  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x027f  */
    /* JADX WARN: Removed duplicated region for block: B:147:0x0288  */
    /* JADX WARN: Removed duplicated region for block: B:194:0x0369  */
    /* JADX WARN: Removed duplicated region for block: B:224:0x03d9  */
    /* JADX WARN: Removed duplicated region for block: B:228:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:75:0x0110  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    final void f0(Fragment fragment, int i2, int i3, int i4, boolean z2) {
        int i5;
        String str;
        Fragment fragment2;
        ViewGroup viewGroup;
        String resourceName;
        int i6;
        int i7;
        int i8 = 1;
        if (!fragment.mAdded || fragment.mDetached) {
            i5 = i2;
            if (i5 > 1) {
                i5 = 1;
            }
        } else {
            i5 = i2;
        }
        if (fragment.mRemoving && i5 > (i7 = fragment.mState)) {
            i5 = (i7 == 0 && fragment.isInBackStack()) ? 1 : fragment.mState;
        }
        int i9 = (!fragment.mDeferStart || fragment.mState >= 4 || i5 <= 3) ? i5 : 3;
        int i10 = fragment.mState;
        if (i10 > i9) {
            if (i10 > i9) {
                if (i10 != 1) {
                    if (i10 != 2) {
                        if (i10 != 3) {
                            if (i10 != 4) {
                                if (i10 == 5) {
                                    if (i9 < 5) {
                                        fragment.performPause();
                                        y(false);
                                    }
                                }
                            }
                            if (i9 < 4) {
                                fragment.performStop();
                                E(false);
                            }
                        }
                        if (i9 < 3) {
                            fragment.performReallyStop();
                        }
                    }
                    if (i9 < 2) {
                        if (fragment.mView != null && !FragmentActivity.this.isFinishing() && fragment.mSavedViewState == null) {
                            n0(fragment);
                        }
                        fragment.performDestroyView();
                        G(false);
                        View view = fragment.mView;
                        if (view != null && fragment.mContainer != null) {
                            view.clearAnimation();
                            fragment.mContainer.endViewTransition(fragment.mView);
                            d dVarZ = (this.f240l <= 0 || this.s || fragment.mView.getVisibility() != 0 || fragment.mPostponedAlpha < 0.0f) ? null : Z(fragment, i3, false, i4);
                            fragment.mPostponedAlpha = 0.0f;
                            if (dVarZ != null) {
                                View view2 = fragment.mView;
                                ViewGroup viewGroup2 = fragment.mContainer;
                                viewGroup2.startViewTransition(view2);
                                fragment.setStateAfterAnimating(i9);
                                Animation animation = dVarZ.f258a;
                                if (animation != null) {
                                    fragment.setAnimatingAway(fragment.mView);
                                    animation.setAnimationListener(new k(this, Y(animation), viewGroup2, view2, fragment));
                                    q0(view2, dVarZ);
                                    fragment.mView.startAnimation(animation);
                                } else {
                                    Animator animator = dVarZ.f259b;
                                    fragment.setAnimator(animator);
                                    animator.addListener(new l(this, viewGroup2, view2, fragment));
                                    animator.setTarget(fragment.mView);
                                    q0(fragment.mView, dVarZ);
                                    animator.start();
                                }
                            }
                            fragment.mContainer.removeView(fragment.mView);
                        }
                        fragment.mContainer = null;
                        fragment.mView = null;
                        fragment.mInnerView = null;
                        fragment.mInLayout = false;
                    }
                    if (i9 < 1) {
                    }
                } else if (i9 < 1) {
                    if (this.s) {
                        if (fragment.getAnimatingAway() != null) {
                            View animatingAway = fragment.getAnimatingAway();
                            fragment.setAnimatingAway(null);
                            animatingAway.clearAnimation();
                        } else if (fragment.getAnimator() != null) {
                            Animator animator2 = fragment.getAnimator();
                            fragment.setAnimator(null);
                            animator2.cancel();
                        }
                    }
                    if (fragment.getAnimatingAway() == null && fragment.getAnimator() == null) {
                        if (fragment.mRetaining) {
                            fragment.mState = 0;
                        } else {
                            fragment.performDestroy();
                            w(false);
                        }
                        fragment.performDetach();
                        x(false);
                        if (!z2) {
                            if (fragment.mRetaining) {
                                fragment.mHost = null;
                                fragment.mParentFragment = null;
                                fragment.mFragmentManager = null;
                            } else {
                                int i11 = fragment.mIndex;
                                if (i11 >= 0) {
                                    this.f234f.put(i11, null);
                                    this.f241m.m(fragment.mWho);
                                    fragment.initState();
                                }
                            }
                        }
                    } else {
                        fragment.setStateAfterAnimating(i9);
                    }
                }
            }
            if (fragment.mState != i8) {
            }
        } else {
            if (fragment.mFromLayout && !fragment.mInLayout) {
                return;
            }
            if (fragment.getAnimatingAway() != null || fragment.getAnimator() != null) {
                fragment.setAnimatingAway(null);
                fragment.setAnimator(null);
                f0(fragment, fragment.getStateAfterAnimating(), 0, 0, true);
            }
            int i12 = fragment.mState;
            if (i12 != 0) {
                if (i12 != 1) {
                    if (i12 != 2) {
                        if (i12 != 3) {
                            i6 = i12 == 4 ? 4 : 4;
                        }
                        if (i9 > i6) {
                            fragment.performResume();
                            B(false);
                            fragment.mSavedFragmentState = null;
                            fragment.mSavedViewState = null;
                        }
                    }
                    if (i9 > 3) {
                        fragment.performStart();
                        D(false);
                    }
                    if (i9 > i6) {
                    }
                }
                if (i9 > 2) {
                    fragment.mState = 3;
                }
                if (i9 > 3) {
                }
                if (i9 > i6) {
                }
            } else if (i9 > 0) {
                Bundle bundle = fragment.mSavedFragmentState;
                if (bundle != null) {
                    bundle.setClassLoader(this.f241m.f219b.getClassLoader());
                    fragment.mSavedViewState = fragment.mSavedFragmentState.getSparseParcelableArray("android:view_state");
                    int i13 = fragment.mSavedFragmentState.getInt("android:target_state", -1);
                    if (i13 == -1) {
                        fragment2 = null;
                    } else {
                        fragment2 = this.f234f.get(i13);
                        if (fragment2 == null) {
                            u0(new IllegalStateException(a.a.h(i13, "Fragment no longer exists for key android:target_state: index ")));
                            throw null;
                        }
                    }
                    fragment.mTarget = fragment2;
                    if (fragment2 != null) {
                        fragment.mTargetRequestCode = fragment.mSavedFragmentState.getInt("android:target_req_state", 0);
                    }
                    boolean z3 = fragment.mSavedFragmentState.getBoolean("android:user_visible_hint", true);
                    fragment.mUserVisibleHint = z3;
                    if (!z3) {
                        fragment.mDeferStart = true;
                        if (i9 > 3) {
                            i9 = 3;
                        }
                    }
                }
                android.support.v4.app.h hVar = this.f241m;
                fragment.mHost = hVar;
                Fragment fragment3 = this.f243o;
                fragment.mParentFragment = fragment3;
                fragment.mFragmentManager = fragment3 != null ? fragment3.mChildFragmentManager : hVar.f221d;
                Fragment fragment4 = fragment.mTarget;
                if (fragment4 != null) {
                    Fragment fragment5 = this.f234f.get(fragment4.mIndex);
                    Fragment fragment6 = fragment.mTarget;
                    if (fragment5 != fragment6) {
                        throw new IllegalStateException("Fragment " + fragment + " declared target fragment " + fragment.mTarget + " that does not belong to this FragmentManager!");
                    }
                    if (fragment6.mState < 1) {
                        str = "Fragment ";
                        f0(fragment6, 1, 0, 0, true);
                    } else {
                        str = "Fragment ";
                    }
                    FragmentActivity fragmentActivity = this.f241m.f219b;
                    z(false);
                    fragment.mCalled = false;
                    fragment.onAttach((Context) this.f241m.f219b);
                    if (!fragment.mCalled) {
                        throw new j0(a.a.j(str, fragment, " did not call through to super.onAttach()"));
                    }
                    Fragment fragment7 = fragment.mParentFragment;
                    if (fragment7 == null) {
                        FragmentActivity.this.onAttachFragment(fragment);
                    } else {
                        fragment7.onAttachFragment(fragment);
                    }
                    FragmentActivity fragmentActivity2 = this.f241m.f219b;
                    u(false);
                    if (fragment.mIsCreated) {
                        fragment.restoreChildFragmentState(fragment.mSavedFragmentState);
                        fragment.mState = 1;
                    } else {
                        A(false);
                        fragment.performCreate(fragment.mSavedFragmentState);
                        v(false);
                    }
                    fragment.mRetaining = false;
                }
                if (fragment.mState != i8) {
                    Log.w("FragmentManager", "moveToState: Fragment state for " + fragment + " not updated inline; expected state " + i8 + " found " + fragment.mState);
                    fragment.mState = i8;
                    return;
                }
                return;
            }
            if (fragment.mFromLayout && !fragment.mPerformedCreateView) {
                View viewPerformCreateView = fragment.performCreateView(fragment.performGetLayoutInflater(fragment.mSavedFragmentState), null, fragment.mSavedFragmentState);
                fragment.mView = viewPerformCreateView;
                if (viewPerformCreateView != null) {
                    fragment.mInnerView = viewPerformCreateView;
                    viewPerformCreateView.setSaveFromParentEnabled(false);
                    if (fragment.mHidden) {
                        fragment.mView.setVisibility(8);
                    }
                    fragment.onViewCreated(fragment.mView, fragment.mSavedFragmentState);
                    F(false);
                } else {
                    fragment.mInnerView = null;
                }
            }
            if (i9 > 1) {
                if (!fragment.mFromLayout) {
                    int i14 = fragment.mContainerId;
                    if (i14 == 0) {
                        viewGroup = null;
                    } else {
                        if (i14 == -1) {
                            u0(new IllegalArgumentException(a.a.j("Cannot create fragment ", fragment, " for a container view with no id")));
                            throw null;
                        }
                        viewGroup = (ViewGroup) this.f242n.b(i14);
                        if (viewGroup == null && !fragment.mRestored) {
                            try {
                                resourceName = fragment.getResources().getResourceName(fragment.mContainerId);
                            } catch (Resources.NotFoundException unused) {
                                resourceName = "unknown";
                            }
                            u0(new IllegalArgumentException("No view found for id 0x" + Integer.toHexString(fragment.mContainerId) + " (" + resourceName + ") for fragment " + fragment));
                            throw null;
                        }
                    }
                    fragment.mContainer = viewGroup;
                    View viewPerformCreateView2 = fragment.performCreateView(fragment.performGetLayoutInflater(fragment.mSavedFragmentState), viewGroup, fragment.mSavedFragmentState);
                    fragment.mView = viewPerformCreateView2;
                    if (viewPerformCreateView2 != null) {
                        fragment.mInnerView = viewPerformCreateView2;
                        viewPerformCreateView2.setSaveFromParentEnabled(false);
                        if (viewGroup != null) {
                            viewGroup.addView(fragment.mView);
                        }
                        if (fragment.mHidden) {
                            fragment.mView.setVisibility(8);
                        }
                        fragment.onViewCreated(fragment.mView, fragment.mSavedFragmentState);
                        F(false);
                        fragment.mIsNewlyAdded = fragment.mView.getVisibility() == 0 && fragment.mContainer != null;
                    } else {
                        fragment.mInnerView = null;
                    }
                }
                fragment.performActivityCreated(fragment.mSavedFragmentState);
                t(false);
                if (fragment.mView != null) {
                    fragment.restoreViewState(fragment.mSavedFragmentState);
                }
                fragment.mSavedFragmentState = null;
            }
            if (i9 > 2) {
            }
            if (i9 > 3) {
            }
            if (i9 > i6) {
            }
        }
        i8 = i9;
        if (fragment.mState != i8) {
        }
    }

    public final void g0() {
        this.B = null;
        this.f246r = false;
        ArrayList<Fragment> arrayList = this.f233e;
        int size = arrayList.size();
        for (int i2 = 0; i2 < size; i2++) {
            Fragment fragment = arrayList.get(i2);
            if (fragment != null) {
                fragment.noteStateNotSaved();
            }
        }
    }

    public final void h(Fragment fragment, boolean z2) {
        a0(fragment);
        if (fragment.mDetached) {
            return;
        }
        if (this.f233e.contains(fragment)) {
            throw new IllegalStateException("Fragment already added: " + fragment);
        }
        synchronized (this.f233e) {
            this.f233e.add(fragment);
        }
        fragment.mAdded = true;
        fragment.mRemoving = false;
        if (fragment.mView == null) {
            fragment.mHiddenChanged = false;
        }
        if (fragment.mHasMenu && fragment.mMenuVisible) {
            this.f245q = true;
        }
        if (z2) {
            f0(fragment, this.f240l, 0, 0, false);
        }
    }

    final boolean h0(ArrayList arrayList, ArrayList arrayList2, int i2, int i3) {
        int size;
        android.support.v4.app.b bVar;
        ArrayList<android.support.v4.app.b> arrayList3 = this.f235g;
        if (arrayList3 == null) {
            return false;
        }
        if (i2 >= 0 || (i3 & 1) != 0) {
            if (i2 >= 0) {
                size = arrayList3.size() - 1;
                while (size >= 0) {
                    android.support.v4.app.b bVar2 = this.f235g.get(size);
                    if (i2 >= 0 && i2 == bVar2.f183l) {
                        break;
                    }
                    size--;
                }
                if (size < 0) {
                    return false;
                }
                if ((i3 & 1) != 0) {
                    do {
                        size--;
                        if (size < 0) {
                            break;
                        }
                        bVar = this.f235g.get(size);
                        if (i2 < 0) {
                            break;
                        }
                    } while (i2 == bVar.f183l);
                }
            } else {
                size = -1;
            }
            if (size == this.f235g.size() - 1) {
                return false;
            }
            for (int size2 = this.f235g.size() - 1; size2 > size; size2--) {
                arrayList.add(this.f235g.remove(size2));
                arrayList2.add(Boolean.TRUE);
            }
        } else {
            int size3 = arrayList3.size() - 1;
            if (size3 < 0) {
                return false;
            }
            arrayList.add(this.f235g.remove(size3));
            arrayList2.add(Boolean.TRUE);
        }
        return true;
    }

    public final void i(Fragment fragment) {
        if (fragment.mDetached) {
            fragment.mDetached = false;
            if (fragment.mAdded) {
                return;
            }
            if (this.f233e.contains(fragment)) {
                throw new IllegalStateException("Fragment already added: " + fragment);
            }
            synchronized (this.f233e) {
                this.f233e.add(fragment);
            }
            fragment.mAdded = true;
            if (fragment.mHasMenu && fragment.mMenuVisible) {
                this.f245q = true;
            }
        }
    }

    public final void i0(Fragment fragment) {
        boolean zIsInBackStack = fragment.isInBackStack();
        if (fragment.mDetached && zIsInBackStack) {
            return;
        }
        synchronized (this.f233e) {
            this.f233e.remove(fragment);
        }
        if (fragment.mHasMenu && fragment.mMenuVisible) {
            this.f245q = true;
        }
        fragment.mAdded = false;
        fragment.mRemoving = true;
    }

    final void k0(Parcelable parcelable, n nVar) {
        List<n> listA;
        FragmentState[] fragmentStateArr;
        if (parcelable == null) {
            return;
        }
        FragmentManagerState fragmentManagerState = (FragmentManagerState) parcelable;
        if (fragmentManagerState.f146a == null) {
            return;
        }
        if (nVar != null) {
            List<Fragment> listB = nVar.b();
            listA = nVar.a();
            int size = listB != null ? listB.size() : 0;
            for (int i2 = 0; i2 < size; i2++) {
                Fragment fragment = listB.get(i2);
                int i3 = 0;
                while (true) {
                    fragmentStateArr = fragmentManagerState.f146a;
                    if (i3 >= fragmentStateArr.length || fragmentStateArr[i3].f152b == fragment.mIndex) {
                        break;
                    } else {
                        i3++;
                    }
                }
                if (i3 == fragmentStateArr.length) {
                    u0(new IllegalStateException("Could not find active fragment with index " + fragment.mIndex));
                    throw null;
                }
                FragmentState fragmentState = fragmentStateArr[i3];
                fragmentState.f162l = fragment;
                fragment.mSavedViewState = null;
                fragment.mBackStackNesting = 0;
                fragment.mInLayout = false;
                fragment.mAdded = false;
                fragment.mTarget = null;
                Bundle bundle = fragmentState.f161k;
                if (bundle != null) {
                    bundle.setClassLoader(this.f241m.f219b.getClassLoader());
                    fragment.mSavedViewState = fragmentState.f161k.getSparseParcelableArray("android:view_state");
                    fragment.mSavedFragmentState = fragmentState.f161k;
                }
            }
        } else {
            listA = null;
        }
        this.f234f = new SparseArray<>(fragmentManagerState.f146a.length);
        int i4 = 0;
        while (true) {
            FragmentState[] fragmentStateArr2 = fragmentManagerState.f146a;
            if (i4 >= fragmentStateArr2.length) {
                break;
            }
            FragmentState fragmentState2 = fragmentStateArr2[i4];
            if (fragmentState2 != null) {
                n nVar2 = (listA == null || i4 >= listA.size()) ? null : listA.get(i4);
                android.support.v4.app.h hVar = this.f241m;
                android.support.v4.app.f fVar = this.f242n;
                Fragment fragment2 = this.f243o;
                if (fragmentState2.f162l == null) {
                    FragmentActivity fragmentActivity = hVar.f219b;
                    Bundle bundle2 = fragmentState2.f159i;
                    if (bundle2 != null) {
                        bundle2.setClassLoader(fragmentActivity.getClassLoader());
                    }
                    String str = fragmentState2.f151a;
                    if (fVar != null) {
                        fragmentState2.f162l = fVar.a(fragmentActivity, str, bundle2);
                    } else {
                        fragmentState2.f162l = Fragment.instantiate(fragmentActivity, str, bundle2);
                    }
                    Bundle bundle3 = fragmentState2.f161k;
                    if (bundle3 != null) {
                        bundle3.setClassLoader(fragmentActivity.getClassLoader());
                        fragmentState2.f162l.mSavedFragmentState = fragmentState2.f161k;
                    }
                    fragmentState2.f162l.setIndex(fragmentState2.f152b, fragment2);
                    Fragment fragment3 = fragmentState2.f162l;
                    fragment3.mFromLayout = fragmentState2.f153c;
                    fragment3.mRestored = true;
                    fragment3.mFragmentId = fragmentState2.f154d;
                    fragment3.mContainerId = fragmentState2.f155e;
                    fragment3.mTag = fragmentState2.f156f;
                    fragment3.mRetainInstance = fragmentState2.f157g;
                    fragment3.mDetached = fragmentState2.f158h;
                    fragment3.mHidden = fragmentState2.f160j;
                    fragment3.mFragmentManager = hVar.f221d;
                }
                Fragment fragment4 = fragmentState2.f162l;
                fragment4.mChildNonConfig = nVar2;
                this.f234f.put(fragment4.mIndex, fragment4);
                fragmentState2.f162l = null;
            }
            i4++;
        }
        if (nVar != null) {
            List<Fragment> listB2 = nVar.b();
            int size2 = listB2 != null ? listB2.size() : 0;
            for (int i5 = 0; i5 < size2; i5++) {
                Fragment fragment5 = listB2.get(i5);
                int i6 = fragment5.mTargetIndex;
                if (i6 >= 0) {
                    Fragment fragment6 = this.f234f.get(i6);
                    fragment5.mTarget = fragment6;
                    if (fragment6 == null) {
                        Log.w("FragmentManager", "Re-attaching retained fragment " + fragment5 + " target no longer exists: " + fragment5.mTargetIndex);
                    }
                }
            }
        }
        this.f233e.clear();
        if (fragmentManagerState.f147b != null) {
            int i7 = 0;
            while (true) {
                int[] iArr = fragmentManagerState.f147b;
                if (i7 >= iArr.length) {
                    break;
                }
                Fragment fragment7 = this.f234f.get(iArr[i7]);
                if (fragment7 == null) {
                    u0(new IllegalStateException("No instantiated fragment for index #" + fragmentManagerState.f147b[i7]));
                    throw null;
                }
                fragment7.mAdded = true;
                if (this.f233e.contains(fragment7)) {
                    throw new IllegalStateException("Already added!");
                }
                synchronized (this.f233e) {
                    this.f233e.add(fragment7);
                }
                i7++;
            }
        }
        if (fragmentManagerState.f148c != null) {
            this.f235g = new ArrayList<>(fragmentManagerState.f148c.length);
            int i8 = 0;
            while (true) {
                BackStackState[] backStackStateArr = fragmentManagerState.f148c;
                if (i8 >= backStackStateArr.length) {
                    break;
                }
                BackStackState backStackState = backStackStateArr[i8];
                backStackState.getClass();
                android.support.v4.app.b bVar = new android.support.v4.app.b(this);
                int i9 = 0;
                while (true) {
                    int[] iArr2 = backStackState.f109a;
                    if (i9 >= iArr2.length) {
                        break;
                    }
                    b.a aVar = new b.a();
                    aVar.f190a = iArr2[i9];
                    int i10 = i9 + 2;
                    int i11 = iArr2[i9 + 1];
                    if (i11 >= 0) {
                        aVar.f191b = this.f234f.get(i11);
                    } else {
                        aVar.f191b = null;
                    }
                    int i12 = iArr2[i10];
                    aVar.f192c = i12;
                    int i13 = iArr2[i9 + 3];
                    aVar.f193d = i13;
                    int i14 = i9 + 5;
                    int i15 = iArr2[i9 + 4];
                    aVar.f194e = i15;
                    i9 += 6;
                    int i16 = iArr2[i14];
                    aVar.f195f = i16;
                    bVar.f174c = i12;
                    bVar.f175d = i13;
                    bVar.f176e = i15;
                    bVar.f177f = i16;
                    bVar.d(aVar);
                }
                bVar.f178g = backStackState.f110b;
                bVar.f179h = backStackState.f111c;
                bVar.f181j = backStackState.f112d;
                bVar.f183l = backStackState.f113e;
                bVar.f180i = true;
                bVar.f184m = backStackState.f114f;
                bVar.f185n = backStackState.f115g;
                bVar.f186o = backStackState.f116h;
                bVar.f187p = backStackState.f117i;
                bVar.f188q = backStackState.f118j;
                bVar.f189r = backStackState.f119k;
                bVar.s = backStackState.f120l;
                bVar.e(1);
                this.f235g.add(bVar);
                int i17 = bVar.f183l;
                if (i17 >= 0) {
                    synchronized (this) {
                        try {
                            if (this.f237i == null) {
                                this.f237i = new ArrayList<>();
                            }
                            int size3 = this.f237i.size();
                            if (i17 < size3) {
                                this.f237i.set(i17, bVar);
                            } else {
                                while (size3 < i17) {
                                    this.f237i.add(null);
                                    if (this.f238j == null) {
                                        this.f238j = new ArrayList<>();
                                    }
                                    this.f238j.add(Integer.valueOf(size3));
                                    size3++;
                                }
                                this.f237i.add(bVar);
                            }
                        } finally {
                        }
                    }
                }
                i8++;
            }
        } else {
            this.f235g = null;
        }
        int i18 = fragmentManagerState.f149d;
        if (i18 >= 0) {
            this.f244p = this.f234f.get(i18);
        }
        this.f232d = fragmentManagerState.f150e;
    }

    final n l0() {
        s0(this.B);
        return this.B;
    }

    public final void m(Fragment fragment) {
        if (fragment.mDetached) {
            return;
        }
        fragment.mDetached = true;
        if (fragment.mAdded) {
            synchronized (this.f233e) {
                this.f233e.remove(fragment);
            }
            if (fragment.mHasMenu && fragment.mMenuVisible) {
                this.f245q = true;
            }
            fragment.mAdded = false;
        }
    }

    final Parcelable m0() {
        BackStackState[] backStackStateArr;
        int[] iArr;
        int size;
        Bundle bundle;
        if (this.A != null) {
            while (!this.A.isEmpty()) {
                this.A.remove(0).d();
            }
        }
        SparseArray<Fragment> sparseArray = this.f234f;
        int size2 = sparseArray == null ? 0 : sparseArray.size();
        int i2 = 0;
        while (true) {
            backStackStateArr = null;
            if (i2 >= size2) {
                break;
            }
            Fragment fragmentValueAt = this.f234f.valueAt(i2);
            if (fragmentValueAt != null) {
                if (fragmentValueAt.getAnimatingAway() != null) {
                    int stateAfterAnimating = fragmentValueAt.getStateAfterAnimating();
                    View animatingAway = fragmentValueAt.getAnimatingAway();
                    Animation animation = animatingAway.getAnimation();
                    if (animation != null) {
                        animation.cancel();
                        animatingAway.clearAnimation();
                    }
                    fragmentValueAt.setAnimatingAway(null);
                    f0(fragmentValueAt, stateAfterAnimating, 0, 0, false);
                } else if (fragmentValueAt.getAnimator() != null) {
                    fragmentValueAt.getAnimator().end();
                }
            }
            i2++;
        }
        T();
        this.f246r = true;
        this.B = null;
        SparseArray<Fragment> sparseArray2 = this.f234f;
        if (sparseArray2 == null || sparseArray2.size() <= 0) {
            return null;
        }
        int size3 = this.f234f.size();
        FragmentState[] fragmentStateArr = new FragmentState[size3];
        boolean z2 = false;
        for (int i3 = 0; i3 < size3; i3++) {
            Fragment fragmentValueAt2 = this.f234f.valueAt(i3);
            if (fragmentValueAt2 != null) {
                if (fragmentValueAt2.mIndex < 0) {
                    u0(new IllegalStateException("Failure saving state: active " + fragmentValueAt2 + " has cleared index: " + fragmentValueAt2.mIndex));
                    throw null;
                }
                FragmentState fragmentState = new FragmentState(fragmentValueAt2);
                fragmentStateArr[i3] = fragmentState;
                if (fragmentValueAt2.mState <= 0 || fragmentState.f161k != null) {
                    fragmentState.f161k = fragmentValueAt2.mSavedFragmentState;
                } else {
                    if (this.f252y == null) {
                        this.f252y = new Bundle();
                    }
                    fragmentValueAt2.performSaveInstanceState(this.f252y);
                    C(false);
                    if (this.f252y.isEmpty()) {
                        bundle = null;
                    } else {
                        bundle = this.f252y;
                        this.f252y = null;
                    }
                    if (fragmentValueAt2.mView != null) {
                        n0(fragmentValueAt2);
                    }
                    if (fragmentValueAt2.mSavedViewState != null) {
                        if (bundle == null) {
                            bundle = new Bundle();
                        }
                        bundle.putSparseParcelableArray("android:view_state", fragmentValueAt2.mSavedViewState);
                    }
                    if (!fragmentValueAt2.mUserVisibleHint) {
                        if (bundle == null) {
                            bundle = new Bundle();
                        }
                        bundle.putBoolean("android:user_visible_hint", fragmentValueAt2.mUserVisibleHint);
                    }
                    fragmentState.f161k = bundle;
                    Fragment fragment = fragmentValueAt2.mTarget;
                    if (fragment != null) {
                        if (fragment.mIndex < 0) {
                            u0(new IllegalStateException("Failure saving state: " + fragmentValueAt2 + " has target not in fragment manager: " + fragmentValueAt2.mTarget));
                            throw null;
                        }
                        if (bundle == null) {
                            fragmentState.f161k = new Bundle();
                        }
                        Bundle bundle2 = fragmentState.f161k;
                        Fragment fragment2 = fragmentValueAt2.mTarget;
                        int i4 = fragment2.mIndex;
                        if (i4 < 0) {
                            u0(new IllegalStateException(a.a.j("Fragment ", fragment2, " is not currently in the FragmentManager")));
                            throw null;
                        }
                        bundle2.putInt("android:target_state", i4);
                        int i5 = fragmentValueAt2.mTargetRequestCode;
                        if (i5 != 0) {
                            fragmentState.f161k.putInt("android:target_req_state", i5);
                        }
                    }
                }
                z2 = true;
            }
        }
        if (!z2) {
            return null;
        }
        ArrayList<Fragment> arrayList = this.f233e;
        int size4 = arrayList.size();
        if (size4 > 0) {
            iArr = new int[size4];
            for (int i6 = 0; i6 < size4; i6++) {
                int i7 = arrayList.get(i6).mIndex;
                iArr[i6] = i7;
                if (i7 < 0) {
                    u0(new IllegalStateException("Failure saving state: active " + arrayList.get(i6) + " has cleared index: " + iArr[i6]));
                    throw null;
                }
            }
        } else {
            iArr = null;
        }
        ArrayList<android.support.v4.app.b> arrayList2 = this.f235g;
        if (arrayList2 != null && (size = arrayList2.size()) > 0) {
            backStackStateArr = new BackStackState[size];
            for (int i8 = 0; i8 < size; i8++) {
                backStackStateArr[i8] = new BackStackState(this.f235g.get(i8));
            }
        }
        FragmentManagerState fragmentManagerState = new FragmentManagerState();
        fragmentManagerState.f146a = fragmentStateArr;
        fragmentManagerState.f147b = iArr;
        fragmentManagerState.f148c = backStackStateArr;
        Fragment fragment3 = this.f244p;
        if (fragment3 != null) {
            fragmentManagerState.f149d = fragment3.mIndex;
        }
        fragmentManagerState.f150e = this.f232d;
        o0();
        return fragmentManagerState;
    }

    public final void n() {
        this.f246r = false;
        O(2);
    }

    final void n0(Fragment fragment) {
        if (fragment.mInnerView == null) {
            return;
        }
        SparseArray<Parcelable> sparseArray = this.f253z;
        if (sparseArray == null) {
            this.f253z = new SparseArray<>();
        } else {
            sparseArray.clear();
        }
        fragment.mInnerView.saveHierarchyState(this.f253z);
        if (this.f253z.size() > 0) {
            fragment.mSavedViewState = this.f253z;
            this.f253z = null;
        }
    }

    public final boolean o(MenuItem menuItem) {
        if (this.f240l < 1) {
            return false;
        }
        int i2 = 0;
        while (true) {
            ArrayList<Fragment> arrayList = this.f233e;
            if (i2 >= arrayList.size()) {
                return false;
            }
            Fragment fragment = arrayList.get(i2);
            if (fragment != null && fragment.performContextItemSelected(menuItem)) {
                return true;
            }
            i2++;
        }
    }

    final void o0() {
        ArrayList arrayList;
        ArrayList arrayList2;
        n nVar;
        if (this.f234f != null) {
            arrayList = null;
            arrayList2 = null;
            for (int i2 = 0; i2 < this.f234f.size(); i2++) {
                Fragment fragmentValueAt = this.f234f.valueAt(i2);
                if (fragmentValueAt != null) {
                    if (fragmentValueAt.mRetainInstance) {
                        if (arrayList == null) {
                            arrayList = new ArrayList();
                        }
                        arrayList.add(fragmentValueAt);
                        Fragment fragment = fragmentValueAt.mTarget;
                        fragmentValueAt.mTargetIndex = fragment != null ? fragment.mIndex : -1;
                    }
                    j jVar = fragmentValueAt.mChildFragmentManager;
                    if (jVar != null) {
                        jVar.o0();
                        nVar = fragmentValueAt.mChildFragmentManager.B;
                    } else {
                        nVar = fragmentValueAt.mChildNonConfig;
                    }
                    if (arrayList2 == null && nVar != null) {
                        arrayList2 = new ArrayList(this.f234f.size());
                        for (int i3 = 0; i3 < i2; i3++) {
                            arrayList2.add(null);
                        }
                    }
                    if (arrayList2 != null) {
                        arrayList2.add(nVar);
                    }
                }
            }
        } else {
            arrayList = null;
            arrayList2 = null;
        }
        if (arrayList == null && arrayList2 == null) {
            this.B = null;
        } else {
            this.B = new n(arrayList, arrayList2);
        }
    }

    @Override // android.view.LayoutInflater.Factory2
    public final View onCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        if (!"fragment".equals(str)) {
            return null;
        }
        String attributeValue = attributeSet.getAttributeValue(null, "class");
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, f.f261a);
        if (attributeValue == null) {
            attributeValue = typedArrayObtainStyledAttributes.getString(0);
        }
        String str2 = attributeValue;
        int resourceId = typedArrayObtainStyledAttributes.getResourceId(1, -1);
        String string = typedArrayObtainStyledAttributes.getString(2);
        typedArrayObtainStyledAttributes.recycle();
        if (!Fragment.isSupportFragmentClass(this.f241m.f219b, str2)) {
            return null;
        }
        int id = view != null ? view.getId() : 0;
        if (id == -1 && resourceId == -1 && string == null) {
            throw new IllegalArgumentException(attributeSet.getPositionDescription() + ": Must specify unique android:id, android:tag, or have a parent with an id for " + str2);
        }
        Fragment fragmentW = resourceId != -1 ? W(resourceId) : null;
        if (fragmentW == null && string != null) {
            fragmentW = c(string);
        }
        if (fragmentW == null && id != -1) {
            fragmentW = W(id);
        }
        if (fragmentW == null) {
            fragmentW = this.f242n.a(context, str2, null);
            fragmentW.mFromLayout = true;
            fragmentW.mFragmentId = resourceId != 0 ? resourceId : id;
            fragmentW.mContainerId = id;
            fragmentW.mTag = string;
            fragmentW.mInLayout = true;
            fragmentW.mFragmentManager = this;
            android.support.v4.app.h hVar = this.f241m;
            fragmentW.mHost = hVar;
            fragmentW.onInflate((Context) hVar.f219b, attributeSet, fragmentW.mSavedFragmentState);
            h(fragmentW, true);
        } else {
            if (fragmentW.mInLayout) {
                throw new IllegalArgumentException(attributeSet.getPositionDescription() + ": Duplicate id 0x" + Integer.toHexString(resourceId) + ", tag " + string + ", or parent id 0x" + Integer.toHexString(id) + " with another fragment for " + str2);
            }
            fragmentW.mInLayout = true;
            android.support.v4.app.h hVar2 = this.f241m;
            fragmentW.mHost = hVar2;
            if (!fragmentW.mRetaining) {
                fragmentW.onInflate((Context) hVar2.f219b, attributeSet, fragmentW.mSavedFragmentState);
            }
        }
        Fragment fragment = fragmentW;
        int i2 = this.f240l;
        if (i2 >= 1 || !fragment.mFromLayout) {
            f0(fragment, i2, 0, 0, false);
        } else {
            f0(fragment, 1, 0, 0, false);
        }
        View view2 = fragment.mView;
        if (view2 == null) {
            throw new IllegalStateException(a.a.l("Fragment ", str2, " did not create a view."));
        }
        if (resourceId != 0) {
            view2.setId(resourceId);
        }
        if (fragment.mView.getTag() == null) {
            fragment.mView.setTag(string);
        }
        return fragment.mView;
    }

    public final void p() {
        this.f246r = false;
        O(1);
    }

    public final boolean q(Menu menu, MenuInflater menuInflater) {
        if (this.f240l < 1) {
            return false;
        }
        ArrayList<Fragment> arrayList = null;
        int i2 = 0;
        boolean z2 = false;
        while (true) {
            ArrayList<Fragment> arrayList2 = this.f233e;
            if (i2 >= arrayList2.size()) {
                break;
            }
            Fragment fragment = arrayList2.get(i2);
            if (fragment != null && fragment.performCreateOptionsMenu(menu, menuInflater)) {
                if (arrayList == null) {
                    arrayList = new ArrayList<>();
                }
                arrayList.add(fragment);
                z2 = true;
            }
            i2++;
        }
        if (this.f236h != null) {
            for (int i3 = 0; i3 < this.f236h.size(); i3++) {
                Fragment fragment2 = this.f236h.get(i3);
                if (arrayList == null || !arrayList.contains(fragment2)) {
                    fragment2.onDestroyOptionsMenu();
                }
            }
        }
        this.f236h = arrayList;
        return z2;
    }

    public final void r() {
        this.s = true;
        T();
        O(0);
        this.f241m = null;
        this.f242n = null;
        this.f243o = null;
    }

    public final void r0(Fragment fragment) {
        if (fragment == null || (this.f234f.get(fragment.mIndex) == fragment && (fragment.mHost == null || fragment.getFragmentManager() == this))) {
            this.f244p = fragment;
            return;
        }
        throw new IllegalArgumentException("Fragment " + fragment + " is not an active fragment of FragmentManager " + this);
    }

    public final void s() {
        O(1);
    }

    final void t(boolean z2) {
        Fragment fragment = this.f243o;
        if (fragment != null) {
            android.support.v4.app.i fragmentManager = fragment.getFragmentManager();
            if (fragmentManager instanceof j) {
                ((j) fragmentManager).t(true);
            }
        }
        for (android.support.v4.util.h<i.a, Boolean> hVar : this.f239k) {
            if (!z2 || hVar.f573b.booleanValue()) {
                hVar.f572a.getClass();
            }
        }
    }

    final void t0() {
        if (this.f234f == null) {
            return;
        }
        for (int i2 = 0; i2 < this.f234f.size(); i2++) {
            Fragment fragmentValueAt = this.f234f.valueAt(i2);
            if (fragmentValueAt != null && fragmentValueAt.mDeferStart) {
                if (this.f231c) {
                    this.f248u = true;
                } else {
                    fragmentValueAt.mDeferStart = false;
                    f0(fragmentValueAt, this.f240l, 0, 0, false);
                }
            }
        }
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder(VertexAttributes.Usage.Tangent);
        sb.append("FragmentManager{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append(" in ");
        Fragment fragment = this.f243o;
        if (fragment != null) {
            android.support.v4.util.k.c(fragment, sb);
        } else {
            android.support.v4.util.k.c(this.f241m, sb);
        }
        sb.append("}}");
        return sb.toString();
    }

    final void u(boolean z2) {
        Fragment fragment = this.f243o;
        if (fragment != null) {
            android.support.v4.app.i fragmentManager = fragment.getFragmentManager();
            if (fragmentManager instanceof j) {
                ((j) fragmentManager).u(true);
            }
        }
        for (android.support.v4.util.h<i.a, Boolean> hVar : this.f239k) {
            if (!z2 || hVar.f573b.booleanValue()) {
                hVar.f572a.getClass();
            }
        }
    }

    final void v(boolean z2) {
        Fragment fragment = this.f243o;
        if (fragment != null) {
            android.support.v4.app.i fragmentManager = fragment.getFragmentManager();
            if (fragmentManager instanceof j) {
                ((j) fragmentManager).v(true);
            }
        }
        for (android.support.v4.util.h<i.a, Boolean> hVar : this.f239k) {
            if (!z2 || hVar.f573b.booleanValue()) {
                hVar.f572a.getClass();
            }
        }
    }

    final void w(boolean z2) {
        Fragment fragment = this.f243o;
        if (fragment != null) {
            android.support.v4.app.i fragmentManager = fragment.getFragmentManager();
            if (fragmentManager instanceof j) {
                ((j) fragmentManager).w(true);
            }
        }
        for (android.support.v4.util.h<i.a, Boolean> hVar : this.f239k) {
            if (!z2 || hVar.f573b.booleanValue()) {
                hVar.f572a.getClass();
            }
        }
    }

    final void x(boolean z2) {
        Fragment fragment = this.f243o;
        if (fragment != null) {
            android.support.v4.app.i fragmentManager = fragment.getFragmentManager();
            if (fragmentManager instanceof j) {
                ((j) fragmentManager).x(true);
            }
        }
        for (android.support.v4.util.h<i.a, Boolean> hVar : this.f239k) {
            if (!z2 || hVar.f573b.booleanValue()) {
                hVar.f572a.getClass();
            }
        }
    }

    final void y(boolean z2) {
        Fragment fragment = this.f243o;
        if (fragment != null) {
            android.support.v4.app.i fragmentManager = fragment.getFragmentManager();
            if (fragmentManager instanceof j) {
                ((j) fragmentManager).y(true);
            }
        }
        for (android.support.v4.util.h<i.a, Boolean> hVar : this.f239k) {
            if (!z2 || hVar.f573b.booleanValue()) {
                hVar.f572a.getClass();
            }
        }
    }

    final void z(boolean z2) {
        Fragment fragment = this.f243o;
        if (fragment != null) {
            android.support.v4.app.i fragmentManager = fragment.getFragmentManager();
            if (fragmentManager instanceof j) {
                ((j) fragmentManager).z(true);
            }
        }
        for (android.support.v4.util.h<i.a, Boolean> hVar : this.f239k) {
            if (!z2 || hVar.f573b.booleanValue()) {
                hVar.f572a.getClass();
            }
        }
    }

    /* JADX INFO: compiled from: FragmentManager.java */
    private static class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Animation f258a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final Animator f259b;

        d(Animation animation) {
            this.f258a = animation;
            this.f259b = null;
        }

        d(Animator animator) {
            this.f258a = null;
            this.f259b = animator;
        }
    }

    @Override // android.view.LayoutInflater.Factory
    public final View onCreateView(String str, Context context, AttributeSet attributeSet) {
        return onCreateView(null, str, context, attributeSet);
    }
}
