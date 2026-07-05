package n;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.PorterDuff;
import android.support.v7.view.menu.j;
import android.support.v7.view.menu.k;
import android.support.v7.widget.e0;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Xml;
import android.view.InflateException;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: compiled from: SupportMenuInflater.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class g extends MenuInflater {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    static final Class<?>[] f2472e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    static final Class<?>[] f2473f;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final Object[] f2474a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final Object[] f2475b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    Context f2476c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private Object f2477d;

    /* JADX INFO: compiled from: SupportMenuInflater.java */
    private static class a implements MenuItem.OnMenuItemClickListener {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private static final Class<?>[] f2478c = {MenuItem.class};

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private Object f2479a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private Method f2480b;

        public a(Object obj, String str) {
            this.f2479a = obj;
            Class<?> cls = obj.getClass();
            try {
                this.f2480b = cls.getMethod(str, f2478c);
            } catch (Exception e2) {
                StringBuilder sbU = a.a.u("Couldn't resolve menu item onClick handler ", str, " in class ");
                sbU.append(cls.getName());
                InflateException inflateException = new InflateException(sbU.toString());
                inflateException.initCause(e2);
                throw inflateException;
            }
        }

        @Override // android.view.MenuItem.OnMenuItemClickListener
        public final boolean onMenuItemClick(MenuItem menuItem) {
            Method method = this.f2480b;
            try {
                Class<?> returnType = method.getReturnType();
                Class<?> cls = Boolean.TYPE;
                Object obj = this.f2479a;
                if (returnType == cls) {
                    return ((Boolean) method.invoke(obj, menuItem)).booleanValue();
                }
                method.invoke(obj, menuItem);
                return true;
            } catch (Exception e2) {
                throw new RuntimeException(e2);
            }
        }
    }

    /* JADX INFO: compiled from: SupportMenuInflater.java */
    private class b {
        private CharSequence A;
        private CharSequence B;
        private ColorStateList C = null;
        private PorterDuff.Mode D = null;

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private Menu f2481a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private int f2482b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private int f2483c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private int f2484d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private int f2485e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private boolean f2486f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private boolean f2487g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        private boolean f2488h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        private int f2489i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        private int f2490j;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        private CharSequence f2491k;

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        private CharSequence f2492l;

        /* JADX INFO: renamed from: m, reason: collision with root package name */
        private int f2493m;

        /* JADX INFO: renamed from: n, reason: collision with root package name */
        private char f2494n;

        /* JADX INFO: renamed from: o, reason: collision with root package name */
        private int f2495o;

        /* JADX INFO: renamed from: p, reason: collision with root package name */
        private char f2496p;

        /* JADX INFO: renamed from: q, reason: collision with root package name */
        private int f2497q;

        /* JADX INFO: renamed from: r, reason: collision with root package name */
        private int f2498r;
        private boolean s;

        /* JADX INFO: renamed from: t, reason: collision with root package name */
        private boolean f2499t;

        /* JADX INFO: renamed from: u, reason: collision with root package name */
        private boolean f2500u;

        /* JADX INFO: renamed from: v, reason: collision with root package name */
        private int f2501v;

        /* JADX INFO: renamed from: w, reason: collision with root package name */
        private int f2502w;

        /* JADX INFO: renamed from: x, reason: collision with root package name */
        private String f2503x;

        /* JADX INFO: renamed from: y, reason: collision with root package name */
        private String f2504y;

        /* JADX INFO: renamed from: z, reason: collision with root package name */
        android.support.v4.view.c f2505z;

        public b(Menu menu) {
            this.f2481a = menu;
            g();
        }

        private <T> T d(String str, Class<?>[] clsArr, Object[] objArr) {
            try {
                Constructor<?> constructor = g.this.f2476c.getClassLoader().loadClass(str).getConstructor(clsArr);
                constructor.setAccessible(true);
                return (T) constructor.newInstance(objArr);
            } catch (Exception e2) {
                Log.w("SupportMenuInflater", "Cannot instantiate class: " + str, e2);
                return null;
            }
        }

        private void h(MenuItem menuItem) {
            boolean z2 = false;
            menuItem.setChecked(this.s).setVisible(this.f2499t).setEnabled(this.f2500u).setCheckable(this.f2498r >= 1).setTitleCondensed(this.f2492l).setIcon(this.f2493m);
            int i2 = this.f2501v;
            if (i2 >= 0) {
                menuItem.setShowAsAction(i2);
            }
            String str = this.f2504y;
            g gVar = g.this;
            if (str != null) {
                if (gVar.f2476c.isRestricted()) {
                    throw new IllegalStateException("The android:onClick attribute cannot be used within a restricted context");
                }
                menuItem.setOnMenuItemClickListener(new a(gVar.b(), this.f2504y));
            }
            boolean z3 = menuItem instanceof j;
            if (z3) {
            }
            if (this.f2498r >= 2) {
                if (z3) {
                    ((j) menuItem).o(true);
                } else if (menuItem instanceof k) {
                    ((k) menuItem).i();
                }
            }
            String str2 = this.f2503x;
            if (str2 != null) {
                menuItem.setActionView((View) d(str2, g.f2472e, gVar.f2474a));
                z2 = true;
            }
            int i3 = this.f2502w;
            if (i3 > 0) {
                if (z2) {
                    Log.w("SupportMenuInflater", "Ignoring attribute 'itemActionViewLayout'. Action view already specified.");
                } else {
                    menuItem.setActionView(i3);
                }
            }
            android.support.v4.view.c cVar = this.f2505z;
            if (cVar != null) {
                if (menuItem instanceof h.b) {
                    ((h.b) menuItem).b(cVar);
                } else {
                    Log.w("MenuItemCompat", "setActionProvider: item does not implement SupportMenuItem; ignoring");
                }
            }
            CharSequence charSequence = this.A;
            boolean z4 = menuItem instanceof h.b;
            if (z4) {
                ((h.b) menuItem).setContentDescription(charSequence);
            } else {
                menuItem.setContentDescription(charSequence);
            }
            CharSequence charSequence2 = this.B;
            if (z4) {
                ((h.b) menuItem).setTooltipText(charSequence2);
            } else {
                menuItem.setTooltipText(charSequence2);
            }
            char c2 = this.f2494n;
            int i4 = this.f2495o;
            if (z4) {
                ((h.b) menuItem).setAlphabeticShortcut(c2, i4);
            } else {
                menuItem.setAlphabeticShortcut(c2, i4);
            }
            char c3 = this.f2496p;
            int i5 = this.f2497q;
            if (z4) {
                ((h.b) menuItem).setNumericShortcut(c3, i5);
            } else {
                menuItem.setNumericShortcut(c3, i5);
            }
            PorterDuff.Mode mode = this.D;
            if (mode != null) {
                if (z4) {
                    ((h.b) menuItem).setIconTintMode(mode);
                } else {
                    menuItem.setIconTintMode(mode);
                }
            }
            ColorStateList colorStateList = this.C;
            if (colorStateList != null) {
                if (z4) {
                    ((h.b) menuItem).setIconTintList(colorStateList);
                } else {
                    menuItem.setIconTintList(colorStateList);
                }
            }
        }

        public final void a() {
            this.f2488h = true;
            h(this.f2481a.add(this.f2482b, this.f2489i, this.f2490j, this.f2491k));
        }

        public final SubMenu b() {
            this.f2488h = true;
            SubMenu subMenuAddSubMenu = this.f2481a.addSubMenu(this.f2482b, this.f2489i, this.f2490j, this.f2491k);
            h(subMenuAddSubMenu.getItem());
            return subMenuAddSubMenu;
        }

        public final boolean c() {
            return this.f2488h;
        }

        public final void e(AttributeSet attributeSet) {
            TypedArray typedArrayObtainStyledAttributes = g.this.f2476c.obtainStyledAttributes(attributeSet, k.j.MenuGroup);
            this.f2482b = typedArrayObtainStyledAttributes.getResourceId(k.j.MenuGroup_android_id, 0);
            this.f2483c = typedArrayObtainStyledAttributes.getInt(k.j.MenuGroup_android_menuCategory, 0);
            this.f2484d = typedArrayObtainStyledAttributes.getInt(k.j.MenuGroup_android_orderInCategory, 0);
            this.f2485e = typedArrayObtainStyledAttributes.getInt(k.j.MenuGroup_android_checkableBehavior, 0);
            this.f2486f = typedArrayObtainStyledAttributes.getBoolean(k.j.MenuGroup_android_visible, true);
            this.f2487g = typedArrayObtainStyledAttributes.getBoolean(k.j.MenuGroup_android_enabled, true);
            typedArrayObtainStyledAttributes.recycle();
        }

        public final void f(AttributeSet attributeSet) {
            g gVar = g.this;
            TypedArray typedArrayObtainStyledAttributes = gVar.f2476c.obtainStyledAttributes(attributeSet, k.j.MenuItem);
            this.f2489i = typedArrayObtainStyledAttributes.getResourceId(k.j.MenuItem_android_id, 0);
            this.f2490j = (typedArrayObtainStyledAttributes.getInt(k.j.MenuItem_android_menuCategory, this.f2483c) & (-65536)) | (typedArrayObtainStyledAttributes.getInt(k.j.MenuItem_android_orderInCategory, this.f2484d) & 65535);
            this.f2491k = typedArrayObtainStyledAttributes.getText(k.j.MenuItem_android_title);
            this.f2492l = typedArrayObtainStyledAttributes.getText(k.j.MenuItem_android_titleCondensed);
            this.f2493m = typedArrayObtainStyledAttributes.getResourceId(k.j.MenuItem_android_icon, 0);
            String string = typedArrayObtainStyledAttributes.getString(k.j.MenuItem_android_alphabeticShortcut);
            this.f2494n = string == null ? (char) 0 : string.charAt(0);
            this.f2495o = typedArrayObtainStyledAttributes.getInt(k.j.MenuItem_alphabeticModifiers, 4096);
            String string2 = typedArrayObtainStyledAttributes.getString(k.j.MenuItem_android_numericShortcut);
            this.f2496p = string2 == null ? (char) 0 : string2.charAt(0);
            this.f2497q = typedArrayObtainStyledAttributes.getInt(k.j.MenuItem_numericModifiers, 4096);
            if (typedArrayObtainStyledAttributes.hasValue(k.j.MenuItem_android_checkable)) {
                this.f2498r = typedArrayObtainStyledAttributes.getBoolean(k.j.MenuItem_android_checkable, false) ? 1 : 0;
            } else {
                this.f2498r = this.f2485e;
            }
            this.s = typedArrayObtainStyledAttributes.getBoolean(k.j.MenuItem_android_checked, false);
            this.f2499t = typedArrayObtainStyledAttributes.getBoolean(k.j.MenuItem_android_visible, this.f2486f);
            this.f2500u = typedArrayObtainStyledAttributes.getBoolean(k.j.MenuItem_android_enabled, this.f2487g);
            this.f2501v = typedArrayObtainStyledAttributes.getInt(k.j.MenuItem_showAsAction, -1);
            this.f2504y = typedArrayObtainStyledAttributes.getString(k.j.MenuItem_android_onClick);
            this.f2502w = typedArrayObtainStyledAttributes.getResourceId(k.j.MenuItem_actionLayout, 0);
            this.f2503x = typedArrayObtainStyledAttributes.getString(k.j.MenuItem_actionViewClass);
            String string3 = typedArrayObtainStyledAttributes.getString(k.j.MenuItem_actionProviderClass);
            boolean z2 = string3 != null;
            if (z2 && this.f2502w == 0 && this.f2503x == null) {
                this.f2505z = (android.support.v4.view.c) d(string3, g.f2473f, gVar.f2475b);
            } else {
                if (z2) {
                    Log.w("SupportMenuInflater", "Ignoring attribute 'actionProviderClass'. Action view already specified.");
                }
                this.f2505z = null;
            }
            this.A = typedArrayObtainStyledAttributes.getText(k.j.MenuItem_contentDescription);
            this.B = typedArrayObtainStyledAttributes.getText(k.j.MenuItem_tooltipText);
            if (typedArrayObtainStyledAttributes.hasValue(k.j.MenuItem_iconTintMode)) {
                this.D = e0.b(typedArrayObtainStyledAttributes.getInt(k.j.MenuItem_iconTintMode, -1), this.D);
            } else {
                this.D = null;
            }
            if (typedArrayObtainStyledAttributes.hasValue(k.j.MenuItem_iconTint)) {
                this.C = typedArrayObtainStyledAttributes.getColorStateList(k.j.MenuItem_iconTint);
            } else {
                this.C = null;
            }
            typedArrayObtainStyledAttributes.recycle();
            this.f2488h = false;
        }

        public final void g() {
            this.f2482b = 0;
            this.f2483c = 0;
            this.f2484d = 0;
            this.f2485e = 0;
            this.f2486f = true;
            this.f2487g = true;
        }
    }

    static {
        Class<?>[] clsArr = {Context.class};
        f2472e = clsArr;
        f2473f = clsArr;
    }

    public g(Context context) {
        super(context);
        this.f2476c = context;
        Object[] objArr = {context};
        this.f2474a = objArr;
        this.f2475b = objArr;
    }

    private static Object a(Object obj) {
        return (!(obj instanceof Activity) && (obj instanceof ContextWrapper)) ? a(((ContextWrapper) obj).getBaseContext()) : obj;
    }

    private void c(XmlResourceParser xmlResourceParser, AttributeSet attributeSet, Menu menu) throws XmlPullParserException, IOException {
        b bVar = new b(menu);
        int eventType = xmlResourceParser.getEventType();
        while (true) {
            if (eventType == 2) {
                String name = xmlResourceParser.getName();
                if (!name.equals("menu")) {
                    throw new RuntimeException("Expecting menu, got ".concat(name));
                }
                eventType = xmlResourceParser.next();
            } else {
                eventType = xmlResourceParser.next();
                if (eventType == 1) {
                    break;
                }
            }
        }
        boolean z2 = false;
        boolean z3 = false;
        String str = null;
        while (!z2) {
            if (eventType == 1) {
                throw new RuntimeException("Unexpected end of document");
            }
            if (eventType != 2) {
                if (eventType == 3) {
                    String name2 = xmlResourceParser.getName();
                    if (z3 && name2.equals(str)) {
                        z3 = false;
                        str = null;
                    } else if (name2.equals("group")) {
                        bVar.g();
                    } else if (name2.equals("item")) {
                        if (!bVar.c()) {
                            android.support.v4.view.c cVar = bVar.f2505z;
                            if (cVar == null || !cVar.a()) {
                                bVar.a();
                            } else {
                                bVar.b();
                            }
                        }
                    } else if (name2.equals("menu")) {
                        z2 = true;
                    }
                }
            } else if (!z3) {
                String name3 = xmlResourceParser.getName();
                if (name3.equals("group")) {
                    bVar.e(attributeSet);
                } else if (name3.equals("item")) {
                    bVar.f(attributeSet);
                } else if (name3.equals("menu")) {
                    c(xmlResourceParser, attributeSet, bVar.b());
                } else {
                    str = name3;
                    z3 = true;
                }
            }
            eventType = xmlResourceParser.next();
        }
    }

    final Object b() {
        if (this.f2477d == null) {
            this.f2477d = a(this.f2476c);
        }
        return this.f2477d;
    }

    @Override // android.view.MenuInflater
    public final void inflate(int i2, Menu menu) {
        if (!(menu instanceof h.a)) {
            super.inflate(i2, menu);
            return;
        }
        XmlResourceParser layout = null;
        try {
            try {
                try {
                    layout = this.f2476c.getResources().getLayout(i2);
                    c(layout, Xml.asAttributeSet(layout), menu);
                    layout.close();
                } catch (IOException e2) {
                    throw new InflateException("Error inflating menu XML", e2);
                }
            } catch (XmlPullParserException e3) {
                throw new InflateException("Error inflating menu XML", e3);
            }
        } catch (Throwable th) {
            if (layout != null) {
                layout.close();
            }
            throw th;
        }
    }
}
