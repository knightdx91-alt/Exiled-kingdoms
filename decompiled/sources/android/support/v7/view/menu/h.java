package android.support.v7.view.menu;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.SparseArray;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: compiled from: MenuBuilder.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class h implements h.a {

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    private static final int[] f926x = {1, 4, 5, 3, 2, 0};

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Context f927a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Resources f928b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private boolean f929c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private boolean f930d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private a f931e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private ArrayList<j> f932f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private ArrayList<j> f933g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private boolean f934h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private ArrayList<j> f935i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private ArrayList<j> f936j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private boolean f937k;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    CharSequence f939m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    Drawable f940n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    View f941o;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    private j f947v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    private boolean f948w;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private int f938l = 0;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    private boolean f942p = false;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    private boolean f943q = false;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    private boolean f944r = false;
    private boolean s = false;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    private ArrayList<j> f945t = new ArrayList<>();

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    private CopyOnWriteArrayList<WeakReference<o>> f946u = new CopyOnWriteArrayList<>();

    /* JADX INFO: compiled from: MenuBuilder.java */
    public interface a {
        boolean a(h hVar, j jVar);

        void b(h hVar);
    }

    /* JADX INFO: compiled from: MenuBuilder.java */
    public interface b {
        boolean b(j jVar);
    }

    public h(Context context) {
        boolean z2 = false;
        this.f927a = context;
        Resources resources = context.getResources();
        this.f928b = resources;
        this.f932f = new ArrayList<>();
        this.f933g = new ArrayList<>();
        this.f934h = true;
        this.f935i = new ArrayList<>();
        this.f936j = new ArrayList<>();
        this.f937k = true;
        if (resources.getConfiguration().keyboard != 1 && resources.getBoolean(k.b.abc_config_showMenuShortcutsWhenKeyboardPresent)) {
            z2 = true;
        }
        this.f930d = z2;
    }

    private void G(int i2, CharSequence charSequence, int i3, Drawable drawable, View view) {
        if (view != null) {
            this.f941o = view;
            this.f939m = null;
            this.f940n = null;
        } else {
            if (i2 > 0) {
                this.f939m = this.f928b.getText(i2);
            } else if (charSequence != null) {
                this.f939m = charSequence;
            }
            if (i3 > 0) {
                this.f940n = this.f927a.getDrawable(i3);
            } else if (drawable != null) {
                this.f940n = drawable;
            }
            this.f941o = null;
        }
        w(false);
    }

    public final void A(Bundle bundle) {
        int size = this.f932f.size();
        SparseArray<? extends Parcelable> sparseArray = null;
        for (int i2 = 0; i2 < size; i2++) {
            MenuItem item = getItem(i2);
            View actionView = item.getActionView();
            if (actionView != null && actionView.getId() != -1) {
                if (sparseArray == null) {
                    sparseArray = new SparseArray<>();
                }
                actionView.saveHierarchyState(sparseArray);
                if (item.isActionViewExpanded()) {
                    bundle.putInt("android:menu:expandedactionview", item.getItemId());
                }
            }
            if (item.hasSubMenu()) {
                ((t) item.getSubMenu()).A(bundle);
            }
        }
        if (sparseArray != null) {
            bundle.putSparseParcelableArray(m(), sparseArray);
        }
    }

    public void B(a aVar) {
        this.f931e = aVar;
    }

    public final void C() {
        this.f938l = 1;
    }

    final void D(j jVar) {
        int groupId = jVar.getGroupId();
        ArrayList<j> arrayList = this.f932f;
        int size = arrayList.size();
        M();
        for (int i2 = 0; i2 < size; i2++) {
            j jVar2 = arrayList.get(i2);
            if (jVar2.getGroupId() == groupId && jVar2.j() && jVar2.isCheckable()) {
                jVar2.n(jVar2 == jVar);
            }
        }
        L();
    }

    protected final void E(int i2) {
        G(0, null, i2, null, null);
    }

    protected final void F(Drawable drawable) {
        G(0, null, 0, drawable, null);
    }

    protected final void H(int i2) {
        G(i2, null, 0, null, null);
    }

    protected final void I(CharSequence charSequence) {
        G(0, charSequence, 0, null, null);
    }

    protected final void J(View view) {
        G(0, null, 0, null, view);
    }

    public final void K(boolean z2) {
        this.f948w = z2;
    }

    public final void L() {
        this.f942p = false;
        if (this.f943q) {
            this.f943q = false;
            w(this.f944r);
        }
    }

    public final void M() {
        if (this.f942p) {
            return;
        }
        this.f942p = true;
        this.f943q = false;
        this.f944r = false;
    }

    protected final j a(int i2, int i3, int i4, CharSequence charSequence) {
        int i5;
        int i6 = ((-65536) & i4) >> 16;
        if (i6 < 0 || i6 >= 6) {
            throw new IllegalArgumentException("order does not contain a valid category.");
        }
        int i7 = (f926x[i6] << 16) | (65535 & i4);
        j jVar = new j(this, i2, i3, i4, i7, charSequence, this.f938l);
        ArrayList<j> arrayList = this.f932f;
        int size = arrayList.size() - 1;
        while (true) {
            if (size < 0) {
                i5 = 0;
                break;
            }
            if (arrayList.get(size).d() <= i7) {
                i5 = size + 1;
                break;
            }
            size--;
        }
        arrayList.add(i5, jVar);
        w(true);
        return jVar;
    }

    @Override // android.view.Menu
    public final MenuItem add(CharSequence charSequence) {
        return a(0, 0, 0, charSequence);
    }

    @Override // android.view.Menu
    public final int addIntentOptions(int i2, int i3, int i4, ComponentName componentName, Intent[] intentArr, Intent intent, int i5, MenuItem[] menuItemArr) {
        int i6;
        PackageManager packageManager = this.f927a.getPackageManager();
        List<ResolveInfo> listQueryIntentActivityOptions = packageManager.queryIntentActivityOptions(componentName, intentArr, intent, 0);
        int size = listQueryIntentActivityOptions != null ? listQueryIntentActivityOptions.size() : 0;
        if ((i5 & 1) == 0) {
            removeGroup(i2);
        }
        for (int i7 = 0; i7 < size; i7++) {
            ResolveInfo resolveInfo = listQueryIntentActivityOptions.get(i7);
            int i8 = resolveInfo.specificIndex;
            Intent intent2 = new Intent(i8 < 0 ? intent : intentArr[i8]);
            ActivityInfo activityInfo = resolveInfo.activityInfo;
            intent2.setComponent(new ComponentName(activityInfo.applicationInfo.packageName, activityInfo.name));
            j jVarA = a(i2, i3, i4, resolveInfo.loadLabel(packageManager));
            jVarA.setIcon(resolveInfo.loadIcon(packageManager));
            jVarA.setIntent(intent2);
            if (menuItemArr != null && (i6 = resolveInfo.specificIndex) >= 0) {
                menuItemArr[i6] = jVarA;
            }
        }
        return size;
    }

    @Override // android.view.Menu
    public final SubMenu addSubMenu(CharSequence charSequence) {
        return addSubMenu(0, 0, 0, charSequence);
    }

    public final void b(o oVar) {
        c(oVar, this.f927a);
    }

    public final void c(o oVar, Context context) {
        this.f946u.add(new WeakReference<>(oVar));
        oVar.e(context, this);
        this.f937k = true;
    }

    @Override // android.view.Menu
    public final void clear() {
        j jVar = this.f947v;
        if (jVar != null) {
            f(jVar);
        }
        this.f932f.clear();
        w(true);
    }

    public final void clearHeader() {
        this.f940n = null;
        this.f939m = null;
        this.f941o = null;
        w(false);
    }

    @Override // android.view.Menu
    public final void close() {
        e(true);
    }

    public final void d() {
        a aVar = this.f931e;
        if (aVar != null) {
            aVar.b(this);
        }
    }

    public final void e(boolean z2) {
        if (this.s) {
            return;
        }
        this.s = true;
        CopyOnWriteArrayList<WeakReference<o>> copyOnWriteArrayList = this.f946u;
        for (WeakReference<o> weakReference : copyOnWriteArrayList) {
            o oVar = weakReference.get();
            if (oVar == null) {
                copyOnWriteArrayList.remove(weakReference);
            } else {
                oVar.b(this, z2);
            }
        }
        this.s = false;
    }

    public boolean f(j jVar) {
        CopyOnWriteArrayList<WeakReference<o>> copyOnWriteArrayList = this.f946u;
        boolean zI = false;
        if (!copyOnWriteArrayList.isEmpty() && this.f947v == jVar) {
            M();
            for (WeakReference<o> weakReference : copyOnWriteArrayList) {
                o oVar = weakReference.get();
                if (oVar != null) {
                    zI = oVar.i(jVar);
                    if (zI) {
                        break;
                    }
                } else {
                    copyOnWriteArrayList.remove(weakReference);
                }
            }
            L();
            if (zI) {
                this.f947v = null;
            }
        }
        return zI;
    }

    @Override // android.view.Menu
    public final MenuItem findItem(int i2) {
        MenuItem menuItemFindItem;
        ArrayList<j> arrayList = this.f932f;
        int size = arrayList.size();
        for (int i3 = 0; i3 < size; i3++) {
            j jVar = arrayList.get(i3);
            if (jVar.getItemId() == i2) {
                return jVar;
            }
            if (jVar.hasSubMenu() && (menuItemFindItem = ((h) jVar.getSubMenu()).findItem(i2)) != null) {
                return menuItemFindItem;
            }
        }
        return null;
    }

    boolean g(h hVar, j jVar) {
        a aVar = this.f931e;
        return aVar != null && aVar.a(hVar, jVar);
    }

    @Override // android.view.Menu
    public final MenuItem getItem(int i2) {
        return this.f932f.get(i2);
    }

    public boolean h(j jVar) {
        CopyOnWriteArrayList<WeakReference<o>> copyOnWriteArrayList = this.f946u;
        boolean zG = false;
        if (copyOnWriteArrayList.isEmpty()) {
            return false;
        }
        M();
        for (WeakReference<o> weakReference : copyOnWriteArrayList) {
            o oVar = weakReference.get();
            if (oVar != null) {
                zG = oVar.g(jVar);
                if (zG) {
                    break;
                }
            } else {
                copyOnWriteArrayList.remove(weakReference);
            }
        }
        L();
        if (zG) {
            this.f947v = jVar;
        }
        return zG;
    }

    @Override // android.view.Menu
    public final boolean hasVisibleItems() {
        if (this.f948w) {
            return true;
        }
        ArrayList<j> arrayList = this.f932f;
        int size = arrayList.size();
        for (int i2 = 0; i2 < size; i2++) {
            if (arrayList.get(i2).isVisible()) {
                return true;
            }
        }
        return false;
    }

    final j i(int i2, KeyEvent keyEvent) {
        ArrayList<j> arrayList = this.f945t;
        arrayList.clear();
        j(arrayList, i2, keyEvent);
        if (arrayList.isEmpty()) {
            return null;
        }
        int metaState = keyEvent.getMetaState();
        KeyCharacterMap.KeyData keyData = new KeyCharacterMap.KeyData();
        keyEvent.getKeyData(keyData);
        int size = arrayList.size();
        if (size == 1) {
            return arrayList.get(0);
        }
        boolean zS = s();
        for (int i3 = 0; i3 < size; i3++) {
            j jVar = arrayList.get(i3);
            char alphabeticShortcut = zS ? jVar.getAlphabeticShortcut() : jVar.getNumericShortcut();
            char[] cArr = keyData.meta;
            if ((alphabeticShortcut == cArr[0] && (metaState & 2) == 0) || ((alphabeticShortcut == cArr[2] && (metaState & 2) != 0) || (zS && alphabeticShortcut == '\b' && i2 == 67))) {
                return jVar;
            }
        }
        return null;
    }

    @Override // android.view.Menu
    public final boolean isShortcutKey(int i2, KeyEvent keyEvent) {
        return i(i2, keyEvent) != null;
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x0075  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    final void j(ArrayList arrayList, int i2, KeyEvent keyEvent) {
        boolean zS = s();
        int modifiers = keyEvent.getModifiers();
        KeyCharacterMap.KeyData keyData = new KeyCharacterMap.KeyData();
        if (keyEvent.getKeyData(keyData) || i2 == 67) {
            ArrayList<j> arrayList2 = this.f932f;
            int size = arrayList2.size();
            for (int i3 = 0; i3 < size; i3++) {
                j jVar = arrayList2.get(i3);
                if (jVar.hasSubMenu()) {
                    ((h) jVar.getSubMenu()).j(arrayList, i2, keyEvent);
                }
                char alphabeticShortcut = zS ? jVar.getAlphabeticShortcut() : jVar.getNumericShortcut();
                if ((modifiers & 69647) == ((zS ? jVar.getAlphabeticModifiers() : jVar.getNumericModifiers()) & 69647) && alphabeticShortcut != 0) {
                    char[] cArr = keyData.meta;
                    if (alphabeticShortcut != cArr[0] && alphabeticShortcut != cArr[2]) {
                        if (zS && alphabeticShortcut == '\b') {
                            if (i2 == 67) {
                            }
                        }
                    }
                    if (jVar.isEnabled()) {
                        arrayList.add(jVar);
                    }
                }
            }
        }
    }

    public final void k() {
        ArrayList<j> arrayListR = r();
        if (this.f937k) {
            CopyOnWriteArrayList<WeakReference<o>> copyOnWriteArrayList = this.f946u;
            boolean zJ = false;
            for (WeakReference<o> weakReference : copyOnWriteArrayList) {
                o oVar = weakReference.get();
                if (oVar == null) {
                    copyOnWriteArrayList.remove(weakReference);
                } else {
                    zJ |= oVar.j();
                }
            }
            ArrayList<j> arrayList = this.f935i;
            ArrayList<j> arrayList2 = this.f936j;
            if (zJ) {
                arrayList.clear();
                arrayList2.clear();
                int size = arrayListR.size();
                for (int i2 = 0; i2 < size; i2++) {
                    j jVar = arrayListR.get(i2);
                    if (jVar.i()) {
                        arrayList.add(jVar);
                    } else {
                        arrayList2.add(jVar);
                    }
                }
            } else {
                arrayList.clear();
                arrayList2.clear();
                arrayList2.addAll(r());
            }
            this.f937k = false;
        }
    }

    public final ArrayList<j> l() {
        k();
        return this.f935i;
    }

    protected String m() {
        return "android:menu:actionviewstates";
    }

    public final Context n() {
        return this.f927a;
    }

    public final j o() {
        return this.f947v;
    }

    public final ArrayList<j> p() {
        k();
        return this.f936j;
    }

    @Override // android.view.Menu
    public final boolean performIdentifierAction(int i2, int i3) {
        return x(findItem(i2), null, i3);
    }

    @Override // android.view.Menu
    public final boolean performShortcut(int i2, KeyEvent keyEvent, int i3) {
        j jVarI = i(i2, keyEvent);
        boolean zX = jVarI != null ? x(jVarI, null, i3) : false;
        if ((i3 & 2) != 0) {
            e(true);
        }
        return zX;
    }

    public h q() {
        return this;
    }

    public final ArrayList<j> r() {
        boolean z2 = this.f934h;
        ArrayList<j> arrayList = this.f933g;
        if (!z2) {
            return arrayList;
        }
        arrayList.clear();
        ArrayList<j> arrayList2 = this.f932f;
        int size = arrayList2.size();
        for (int i2 = 0; i2 < size; i2++) {
            j jVar = arrayList2.get(i2);
            if (jVar.isVisible()) {
                arrayList.add(jVar);
            }
        }
        this.f934h = false;
        this.f937k = true;
        return arrayList;
    }

    @Override // android.view.Menu
    public final void removeGroup(int i2) {
        ArrayList<j> arrayList = this.f932f;
        int size = arrayList.size();
        int i3 = 0;
        int i4 = 0;
        while (true) {
            if (i4 >= size) {
                i4 = -1;
                break;
            } else if (arrayList.get(i4).getGroupId() == i2) {
                break;
            } else {
                i4++;
            }
        }
        if (i4 >= 0) {
            int size2 = arrayList.size() - i4;
            while (true) {
                int i5 = i3 + 1;
                if (i3 >= size2 || arrayList.get(i4).getGroupId() != i2) {
                    break;
                }
                if (i4 >= 0) {
                    ArrayList<j> arrayList2 = this.f932f;
                    if (i4 < arrayList2.size()) {
                        arrayList2.remove(i4);
                    }
                }
                i3 = i5;
            }
            w(true);
        }
    }

    @Override // android.view.Menu
    public final void removeItem(int i2) {
        ArrayList<j> arrayList = this.f932f;
        int size = arrayList.size();
        int i3 = 0;
        while (true) {
            if (i3 >= size) {
                i3 = -1;
                break;
            } else if (arrayList.get(i3).getItemId() == i2) {
                break;
            } else {
                i3++;
            }
        }
        if (i3 >= 0) {
            ArrayList<j> arrayList2 = this.f932f;
            if (i3 >= arrayList2.size()) {
                return;
            }
            arrayList2.remove(i3);
            w(true);
        }
    }

    boolean s() {
        return this.f929c;
    }

    @Override // android.view.Menu
    public final void setGroupCheckable(int i2, boolean z2, boolean z3) {
        ArrayList<j> arrayList = this.f932f;
        int size = arrayList.size();
        for (int i3 = 0; i3 < size; i3++) {
            j jVar = arrayList.get(i3);
            if (jVar.getGroupId() == i2) {
                jVar.o(z3);
                jVar.setCheckable(z2);
            }
        }
    }

    @Override // android.view.Menu
    public final void setGroupEnabled(int i2, boolean z2) {
        ArrayList<j> arrayList = this.f932f;
        int size = arrayList.size();
        for (int i3 = 0; i3 < size; i3++) {
            j jVar = arrayList.get(i3);
            if (jVar.getGroupId() == i2) {
                jVar.setEnabled(z2);
            }
        }
    }

    @Override // android.view.Menu
    public final void setGroupVisible(int i2, boolean z2) {
        ArrayList<j> arrayList = this.f932f;
        int size = arrayList.size();
        boolean z3 = false;
        for (int i3 = 0; i3 < size; i3++) {
            j jVar = arrayList.get(i3);
            if (jVar.getGroupId() == i2 && jVar.r(z2)) {
                z3 = true;
            }
        }
        if (z3) {
            w(true);
        }
    }

    @Override // android.view.Menu
    public void setQwertyMode(boolean z2) {
        this.f929c = z2;
        w(false);
    }

    @Override // android.view.Menu
    public final int size() {
        return this.f932f.size();
    }

    public boolean t() {
        return this.f930d;
    }

    final void u() {
        this.f937k = true;
        w(true);
    }

    final void v() {
        this.f934h = true;
        w(true);
    }

    public final void w(boolean z2) {
        if (this.f942p) {
            this.f943q = true;
            if (z2) {
                this.f944r = true;
                return;
            }
            return;
        }
        if (z2) {
            this.f934h = true;
            this.f937k = true;
        }
        CopyOnWriteArrayList<WeakReference<o>> copyOnWriteArrayList = this.f946u;
        if (copyOnWriteArrayList.isEmpty()) {
            return;
        }
        M();
        for (WeakReference<o> weakReference : copyOnWriteArrayList) {
            o oVar = weakReference.get();
            if (oVar == null) {
                copyOnWriteArrayList.remove(weakReference);
            } else {
                oVar.h(z2);
            }
        }
        L();
    }

    public final boolean x(MenuItem menuItem, o oVar, int i2) {
        j jVar = (j) menuItem;
        if (jVar == null || !jVar.isEnabled()) {
            return false;
        }
        boolean zH = jVar.h();
        android.support.v4.view.c cVarA = jVar.a();
        boolean z2 = cVarA != null && cVarA.a();
        if (jVar.g()) {
            zH |= jVar.expandActionView();
            if (zH) {
                e(true);
            }
        } else if (jVar.hasSubMenu() || z2) {
            if ((i2 & 4) == 0) {
                e(false);
            }
            if (!jVar.hasSubMenu()) {
                jVar.q(new t(this.f927a, this, jVar));
            }
            t tVar = (t) jVar.getSubMenu();
            if (z2) {
                cVarA.f(tVar);
            }
            CopyOnWriteArrayList<WeakReference<o>> copyOnWriteArrayList = this.f946u;
            if (!copyOnWriteArrayList.isEmpty()) {
                zC = oVar != null ? oVar.c(tVar) : false;
                for (WeakReference<o> weakReference : copyOnWriteArrayList) {
                    o oVar2 = weakReference.get();
                    if (oVar2 == null) {
                        copyOnWriteArrayList.remove(weakReference);
                    } else if (!zC) {
                        zC = oVar2.c(tVar);
                    }
                }
            }
            zH |= zC;
            if (!zH) {
                e(true);
            }
        } else if ((i2 & 1) == 0) {
            e(true);
        }
        return zH;
    }

    public final void y(o oVar) {
        CopyOnWriteArrayList<WeakReference<o>> copyOnWriteArrayList = this.f946u;
        for (WeakReference<o> weakReference : copyOnWriteArrayList) {
            o oVar2 = weakReference.get();
            if (oVar2 == null || oVar2 == oVar) {
                copyOnWriteArrayList.remove(weakReference);
            }
        }
    }

    public final void z(Bundle bundle) {
        MenuItem menuItemFindItem;
        if (bundle == null) {
            return;
        }
        SparseArray<Parcelable> sparseParcelableArray = bundle.getSparseParcelableArray(m());
        int size = this.f932f.size();
        for (int i2 = 0; i2 < size; i2++) {
            MenuItem item = getItem(i2);
            View actionView = item.getActionView();
            if (actionView != null && actionView.getId() != -1) {
                actionView.restoreHierarchyState(sparseParcelableArray);
            }
            if (item.hasSubMenu()) {
                ((t) item.getSubMenu()).z(bundle);
            }
        }
        int i3 = bundle.getInt("android:menu:expandedactionview");
        if (i3 <= 0 || (menuItemFindItem = findItem(i3)) == null) {
            return;
        }
        menuItemFindItem.expandActionView();
    }

    @Override // android.view.Menu
    public final MenuItem add(int i2) {
        return a(0, 0, 0, this.f928b.getString(i2));
    }

    @Override // android.view.Menu
    public final SubMenu addSubMenu(int i2) {
        return addSubMenu(0, 0, 0, this.f928b.getString(i2));
    }

    @Override // android.view.Menu
    public final MenuItem add(int i2, int i3, int i4, CharSequence charSequence) {
        return a(i2, i3, i4, charSequence);
    }

    @Override // android.view.Menu
    public final SubMenu addSubMenu(int i2, int i3, int i4, CharSequence charSequence) {
        j jVarA = a(i2, i3, i4, charSequence);
        t tVar = new t(this.f927a, this, jVarA);
        jVarA.q(tVar);
        return tVar;
    }

    @Override // android.view.Menu
    public final MenuItem add(int i2, int i3, int i4, int i5) {
        return a(i2, i3, i4, this.f928b.getString(i5));
    }

    @Override // android.view.Menu
    public final SubMenu addSubMenu(int i2, int i3, int i4, int i5) {
        return addSubMenu(i2, i3, i4, this.f928b.getString(i5));
    }
}
