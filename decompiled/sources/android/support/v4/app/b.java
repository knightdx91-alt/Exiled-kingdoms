package android.support.v4.app;

import android.support.v4.app.j;
import com.badlogic.gdx.graphics.VertexAttributes;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

/* JADX INFO: compiled from: BackStackRecord.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class b extends o implements j.g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final j f172a;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    int f174c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    int f175d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    int f176e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    int f177f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    int f178g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    int f179h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    boolean f180i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    String f181j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    boolean f182k;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    int f184m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    CharSequence f185n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    int f186o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    CharSequence f187p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    ArrayList<String> f188q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    ArrayList<String> f189r;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    ArrayList<a> f173b = new ArrayList<>();

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    int f183l = -1;
    boolean s = false;

    /* JADX INFO: compiled from: BackStackRecord.java */
    static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        int f190a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        Fragment f191b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        int f192c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        int f193d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        int f194e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        int f195f;

        a() {
        }

        a(int i2, Fragment fragment) {
            this.f190a = i2;
            this.f191b = fragment;
        }
    }

    public b(j jVar) {
        this.f172a = jVar;
    }

    private static boolean k(a aVar) {
        Fragment fragment = aVar.f191b;
        return (fragment == null || !fragment.mAdded || fragment.mView == null || fragment.mDetached || fragment.mHidden || !fragment.isPostponed()) ? false : true;
    }

    @Override // android.support.v4.app.j.g
    public final boolean a(ArrayList<b> arrayList, ArrayList<Boolean> arrayList2) {
        Field field = j.D;
        arrayList.add(this);
        arrayList2.add(Boolean.FALSE);
        if (!this.f180i) {
            return true;
        }
        j jVar = this.f172a;
        if (jVar.f235g == null) {
            jVar.f235g = new ArrayList<>();
        }
        jVar.f235g.add(this);
        return true;
    }

    @Override // android.support.v4.app.o
    public final o b(Fragment fragment, String str) {
        Class<?> cls = fragment.getClass();
        int modifiers = cls.getModifiers();
        if (cls.isAnonymousClass() || !Modifier.isPublic(modifiers) || (cls.isMemberClass() && !Modifier.isStatic(modifiers))) {
            throw new IllegalStateException("Fragment " + cls.getCanonicalName() + " must be a public static class to be  properly recreated from instance state.");
        }
        fragment.mFragmentManager = this.f172a;
        if (str != null) {
            String str2 = fragment.mTag;
            if (str2 != null && !str.equals(str2)) {
                throw new IllegalStateException("Can't change tag of fragment " + fragment + ": was " + fragment.mTag + " now " + str);
            }
            fragment.mTag = str;
        }
        d(new a(1, fragment));
        return this;
    }

    @Override // android.support.v4.app.o
    public final int c() {
        return f(true);
    }

    final void d(a aVar) {
        this.f173b.add(aVar);
        aVar.f192c = this.f174c;
        aVar.f193d = this.f175d;
        aVar.f194e = this.f176e;
        aVar.f195f = this.f177f;
    }

    final void e(int i2) {
        if (this.f180i) {
            Field field = j.D;
            ArrayList<a> arrayList = this.f173b;
            int size = arrayList.size();
            for (int i3 = 0; i3 < size; i3++) {
                Fragment fragment = arrayList.get(i3).f191b;
                if (fragment != null) {
                    fragment.mBackStackNesting += i2;
                    Field field2 = j.D;
                }
            }
        }
    }

    final int f(boolean z2) {
        int size;
        if (this.f182k) {
            throw new IllegalStateException("commit already called");
        }
        Field field = j.D;
        this.f182k = true;
        if (this.f180i) {
            j jVar = this.f172a;
            synchronized (jVar) {
                try {
                    ArrayList<Integer> arrayList = jVar.f238j;
                    if (arrayList == null || arrayList.size() <= 0) {
                        if (jVar.f237i == null) {
                            jVar.f237i = new ArrayList<>();
                        }
                        size = jVar.f237i.size();
                        jVar.f237i.add(this);
                    } else {
                        ArrayList<Integer> arrayList2 = jVar.f238j;
                        size = arrayList2.remove(arrayList2.size() - 1).intValue();
                        jVar.f237i.set(size, this);
                    }
                } finally {
                }
            }
            this.f183l = size;
        } else {
            this.f183l = -1;
        }
        this.f172a.R(this, z2);
        return this.f183l;
    }

    final void g() {
        ArrayList<a> arrayList = this.f173b;
        int size = arrayList.size();
        int i2 = 0;
        while (true) {
            j jVar = this.f172a;
            if (i2 >= size) {
                if (this.s) {
                    return;
                }
                jVar.e0(jVar.f240l, true);
                return;
            }
            a aVar = arrayList.get(i2);
            Fragment fragment = aVar.f191b;
            if (fragment != null) {
                fragment.setNextTransition(this.f178g, this.f179h);
            }
            switch (aVar.f190a) {
                case 1:
                    fragment.setNextAnim(aVar.f192c);
                    jVar.h(fragment, false);
                    break;
                case 2:
                default:
                    throw new IllegalArgumentException("Unknown cmd: " + aVar.f190a);
                case 3:
                    fragment.setNextAnim(aVar.f193d);
                    jVar.i0(fragment);
                    break;
                case 4:
                    fragment.setNextAnim(aVar.f193d);
                    jVar.getClass();
                    if (!fragment.mHidden) {
                        fragment.mHidden = true;
                        fragment.mHiddenChanged = !fragment.mHiddenChanged;
                    }
                    break;
                case 5:
                    fragment.setNextAnim(aVar.f192c);
                    jVar.getClass();
                    if (fragment.mHidden) {
                        fragment.mHidden = false;
                        fragment.mHiddenChanged = !fragment.mHiddenChanged;
                    }
                    break;
                case 6:
                    fragment.setNextAnim(aVar.f193d);
                    jVar.m(fragment);
                    break;
                case 7:
                    fragment.setNextAnim(aVar.f192c);
                    jVar.i(fragment);
                    break;
                case 8:
                    jVar.r0(fragment);
                    break;
                case 9:
                    jVar.f244p = null;
                    break;
            }
            if (!this.s && aVar.f190a != 1 && fragment != null) {
                jVar.d0(fragment);
            }
            i2++;
        }
    }

    final void h(boolean z2) {
        ArrayList<a> arrayList = this.f173b;
        int size = arrayList.size() - 1;
        while (true) {
            j jVar = this.f172a;
            if (size < 0) {
                if (this.s || !z2) {
                    return;
                }
                jVar.e0(jVar.f240l, true);
                return;
            }
            a aVar = arrayList.get(size);
            Fragment fragment = aVar.f191b;
            if (fragment != null) {
                int i2 = this.f178g;
                Field field = j.D;
                fragment.setNextTransition(i2 != 4097 ? i2 != 4099 ? i2 != 8194 ? 0 : 4097 : 4099 : 8194, this.f179h);
            }
            switch (aVar.f190a) {
                case 1:
                    fragment.setNextAnim(aVar.f195f);
                    jVar.i0(fragment);
                    break;
                case 2:
                default:
                    throw new IllegalArgumentException("Unknown cmd: " + aVar.f190a);
                case 3:
                    fragment.setNextAnim(aVar.f194e);
                    jVar.h(fragment, false);
                    break;
                case 4:
                    fragment.setNextAnim(aVar.f194e);
                    jVar.getClass();
                    if (fragment.mHidden) {
                        fragment.mHidden = false;
                        fragment.mHiddenChanged = !fragment.mHiddenChanged;
                    }
                    break;
                case 5:
                    fragment.setNextAnim(aVar.f195f);
                    jVar.getClass();
                    if (!fragment.mHidden) {
                        fragment.mHidden = true;
                        fragment.mHiddenChanged = !fragment.mHiddenChanged;
                    }
                    break;
                case 6:
                    fragment.setNextAnim(aVar.f194e);
                    jVar.i(fragment);
                    break;
                case 7:
                    fragment.setNextAnim(aVar.f195f);
                    jVar.m(fragment);
                    break;
                case 8:
                    jVar.f244p = null;
                    break;
                case 9:
                    jVar.r0(fragment);
                    break;
            }
            if (!this.s && aVar.f190a != 3 && fragment != null) {
                jVar.d0(fragment);
            }
            size--;
        }
    }

    final boolean i(int i2) {
        ArrayList<a> arrayList = this.f173b;
        int size = arrayList.size();
        for (int i3 = 0; i3 < size; i3++) {
            Fragment fragment = arrayList.get(i3).f191b;
            int i4 = fragment != null ? fragment.mContainerId : 0;
            if (i4 != 0 && i4 == i2) {
                return true;
            }
        }
        return false;
    }

    final boolean j(int i2, int i3, ArrayList arrayList) {
        if (i3 == i2) {
            return false;
        }
        ArrayList<a> arrayList2 = this.f173b;
        int size = arrayList2.size();
        int i4 = -1;
        for (int i5 = 0; i5 < size; i5++) {
            Fragment fragment = arrayList2.get(i5).f191b;
            int i6 = fragment != null ? fragment.mContainerId : 0;
            if (i6 != 0 && i6 != i4) {
                for (int i7 = i2; i7 < i3; i7++) {
                    b bVar = (b) arrayList.get(i7);
                    int size2 = bVar.f173b.size();
                    for (int i8 = 0; i8 < size2; i8++) {
                        Fragment fragment2 = bVar.f173b.get(i8).f191b;
                        if ((fragment2 != null ? fragment2.mContainerId : 0) == i6) {
                            return true;
                        }
                    }
                }
                i4 = i6;
            }
        }
        return false;
    }

    final boolean l() {
        int i2 = 0;
        while (true) {
            ArrayList<a> arrayList = this.f173b;
            if (i2 >= arrayList.size()) {
                return false;
            }
            if (k(arrayList.get(i2))) {
                return true;
            }
            i2++;
        }
    }

    final void m(j.i iVar) {
        int i2 = 0;
        while (true) {
            ArrayList<a> arrayList = this.f173b;
            if (i2 >= arrayList.size()) {
                return;
            }
            a aVar = arrayList.get(i2);
            if (k(aVar)) {
                aVar.f191b.setOnStartEnterTransitionListener(iVar);
            }
            i2++;
        }
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder(VertexAttributes.Usage.Tangent);
        sb.append("BackStackEntry{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        if (this.f183l >= 0) {
            sb.append(" #");
            sb.append(this.f183l);
        }
        if (this.f181j != null) {
            sb.append(" ");
            sb.append(this.f181j);
        }
        sb.append("}");
        return sb.toString();
    }
}
