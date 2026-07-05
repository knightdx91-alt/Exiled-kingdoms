package android.support.v7.app;

import android.R;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.app.a0;
import android.support.v7.app.s;
import android.support.v7.app.s.d;
import android.support.v7.view.menu.h;
import android.support.v7.view.menu.o;
import android.support.v7.view.menu.t;
import android.support.v7.widget.ActionBarContextView;
import android.support.v7.widget.ContentFrameLayout;
import android.support.v7.widget.ViewStubCompat;
import android.support.v7.widget.a1;
import android.support.v7.widget.b1;
import android.support.v7.widget.c0;
import android.support.v7.widget.d0;
import android.text.TextUtils;
import android.util.AndroidRuntimeException;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.google.android.gms.auth.api.credentials.CredentialsApi;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import n.b;
import n.f;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
class AppCompatDelegateImplV9 extends f implements h.a, LayoutInflater.Factory2 {
    private View A;
    private boolean B;
    private boolean C;
    private boolean D;
    private PanelFeatureState[] E;
    private PanelFeatureState F;
    private boolean G;
    boolean H;
    int I;
    private final Runnable J;
    private boolean K;
    private Rect L;
    private Rect M;
    private p N;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    private c0 f714p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    private b f715q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    private e f716r;
    n.b s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    ActionBarContextView f717t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    PopupWindow f718u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    Runnable f719v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    android.support.v4.view.l f720w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    private boolean f721x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    private ViewGroup f722y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    private TextView f723z;

    protected static final class PanelFeatureState {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        int f724a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        int f725b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        int f726c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        int f727d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        ViewGroup f728e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        View f729f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        View f730g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        android.support.v7.view.menu.h f731h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        android.support.v7.view.menu.f f732i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        n.d f733j;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        boolean f734k;

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        boolean f735l;

        /* JADX INFO: renamed from: m, reason: collision with root package name */
        boolean f736m;

        /* JADX INFO: renamed from: n, reason: collision with root package name */
        boolean f737n;

        /* JADX INFO: renamed from: o, reason: collision with root package name */
        boolean f738o;

        /* JADX INFO: renamed from: p, reason: collision with root package name */
        Bundle f739p;

        private static class SavedState implements Parcelable {
            public static final Parcelable.Creator<SavedState> CREATOR = new a();

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            int f740a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            boolean f741b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            Bundle f742c;

            static class a implements Parcelable.ClassLoaderCreator<SavedState> {
                @Override // android.os.Parcelable.ClassLoaderCreator
                public final SavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
                    return SavedState.a(parcel, classLoader);
                }

                @Override // android.os.Parcelable.Creator
                public final Object[] newArray(int i2) {
                    return new SavedState[i2];
                }

                @Override // android.os.Parcelable.Creator
                public final Object createFromParcel(Parcel parcel) {
                    return SavedState.a(parcel, null);
                }
            }

            SavedState() {
            }

            static SavedState a(Parcel parcel, ClassLoader classLoader) {
                SavedState savedState = new SavedState();
                savedState.f740a = parcel.readInt();
                boolean z2 = parcel.readInt() == 1;
                savedState.f741b = z2;
                if (z2) {
                    savedState.f742c = parcel.readBundle(classLoader);
                }
                return savedState;
            }

            @Override // android.os.Parcelable
            public final int describeContents() {
                return 0;
            }

            @Override // android.os.Parcelable
            public final void writeToParcel(Parcel parcel, int i2) {
                parcel.writeInt(this.f740a);
                parcel.writeInt(this.f741b ? 1 : 0);
                if (this.f741b) {
                    parcel.writeBundle(this.f742c);
                }
            }
        }
    }

    final class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            AppCompatDelegateImplV9 appCompatDelegateImplV9 = AppCompatDelegateImplV9.this;
            if ((appCompatDelegateImplV9.I & 1) != 0) {
                appCompatDelegateImplV9.F(0);
            }
            if ((appCompatDelegateImplV9.I & 4096) != 0) {
                appCompatDelegateImplV9.F(108);
            }
            appCompatDelegateImplV9.H = false;
            appCompatDelegateImplV9.I = 0;
        }
    }

    private final class b implements o.a {
        b() {
        }

        @Override // android.support.v7.view.menu.o.a
        public final void b(android.support.v7.view.menu.h hVar, boolean z2) {
            AppCompatDelegateImplV9.this.C(hVar);
        }

        @Override // android.support.v7.view.menu.o.a
        public final boolean c(t tVar) {
            Window.Callback callback = AppCompatDelegateImplV9.this.f758c.getCallback();
            if (callback == null) {
                return true;
            }
            callback.onMenuOpened(108, tVar);
            return true;
        }
    }

    class c implements b.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private f.a f745a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ i f746b;

        final class a extends com.badlogic.gdx.utils.l {
            a() {
            }

            @Override // android.support.v4.view.m
            public final void a() {
                c cVar = c.this;
                cVar.f746b.f717t.setVisibility(8);
                i iVar = cVar.f746b;
                PopupWindow popupWindow = iVar.f718u;
                if (popupWindow != null) {
                    popupWindow.dismiss();
                } else if (iVar.f717t.getParent() instanceof View) {
                    ((View) iVar.f717t.getParent()).requestApplyInsets();
                }
                iVar.f717t.removeAllViews();
                iVar.f720w.f(null);
                iVar.f720w = null;
            }
        }

        public c(i iVar, f.a aVar) {
            this.f746b = iVar;
            this.f745a = aVar;
        }

        @Override // n.b.a
        public final boolean a(n.b bVar, android.support.v7.view.menu.j jVar) {
            return this.f745a.a(bVar, jVar);
        }

        @Override // n.b.a
        public final boolean b(n.b bVar, android.support.v7.view.menu.h hVar) {
            return this.f745a.b(bVar, hVar);
        }

        @Override // n.b.a
        public final void c(n.b bVar) {
            this.f745a.c(bVar);
            i iVar = this.f746b;
            if (iVar.f718u != null) {
                iVar.f758c.getDecorView().removeCallbacks(iVar.f719v);
            }
            if (iVar.f717t != null) {
                android.support.v4.view.l lVar = iVar.f720w;
                if (lVar != null) {
                    lVar.b();
                }
                android.support.v4.view.l lVarA = android.support.v4.view.h.a(iVar.f717t);
                lVarA.a(0.0f);
                iVar.f720w = lVarA;
                lVarA.f(new a());
            }
            iVar.s = null;
        }

        public final boolean d(n.b bVar, android.support.v7.view.menu.h hVar) {
            return this.f745a.e(bVar, hVar);
        }
    }

    private class d extends ContentFrameLayout {
        public d(n.d dVar) {
            super(dVar, null);
        }

        @Override // android.view.ViewGroup, android.view.View
        public final boolean dispatchKeyEvent(KeyEvent keyEvent) {
            return AppCompatDelegateImplV9.this.t(keyEvent) || super.dispatchKeyEvent(keyEvent);
        }

        @Override // android.view.ViewGroup
        public final boolean onInterceptTouchEvent(MotionEvent motionEvent) {
            if (motionEvent.getAction() == 0) {
                int x2 = (int) motionEvent.getX();
                int y2 = (int) motionEvent.getY();
                if (x2 < -5 || y2 < -5 || x2 > getWidth() + 5 || y2 > getHeight() + 5) {
                    AppCompatDelegateImplV9 appCompatDelegateImplV9 = AppCompatDelegateImplV9.this;
                    appCompatDelegateImplV9.D(appCompatDelegateImplV9.I(0), true);
                    return true;
                }
            }
            return super.onInterceptTouchEvent(motionEvent);
        }

        @Override // android.view.View
        public final void setBackgroundResource(int i2) {
            setBackgroundDrawable(l.a.a(getContext(), i2));
        }
    }

    private final class e implements o.a {
        e() {
        }

        @Override // android.support.v7.view.menu.o.a
        public final void b(android.support.v7.view.menu.h hVar, boolean z2) {
            android.support.v7.view.menu.h hVarQ = hVar.q();
            boolean z3 = hVarQ != hVar;
            if (z3) {
                hVar = hVarQ;
            }
            AppCompatDelegateImplV9 appCompatDelegateImplV9 = AppCompatDelegateImplV9.this;
            PanelFeatureState panelFeatureStateH = appCompatDelegateImplV9.H(hVar);
            if (panelFeatureStateH != null) {
                if (!z3) {
                    appCompatDelegateImplV9.D(panelFeatureStateH, z2);
                } else {
                    appCompatDelegateImplV9.B(panelFeatureStateH.f724a, panelFeatureStateH, hVarQ);
                    appCompatDelegateImplV9.D(panelFeatureStateH, true);
                }
            }
        }

        @Override // android.support.v7.view.menu.o.a
        public final boolean c(t tVar) {
            Window.Callback callback;
            if (tVar != null) {
                return true;
            }
            AppCompatDelegateImplV9 appCompatDelegateImplV9 = AppCompatDelegateImplV9.this;
            if (!appCompatDelegateImplV9.f763h || (callback = appCompatDelegateImplV9.f758c.getCallback()) == null || appCompatDelegateImplV9.w()) {
                return true;
            }
            callback.onMenuOpened(108, tVar);
            return true;
        }
    }

    AppCompatDelegateImplV9(Context context, Window window, android.support.v7.app.d dVar) {
        super(context, window, dVar);
        this.f720w = null;
        this.J = new a();
    }

    private void G() {
        ViewGroup viewGroup;
        if (this.f721x) {
            return;
        }
        int[] iArr = k.j.AppCompatTheme;
        Context context = this.f757b;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(iArr);
        if (!typedArrayObtainStyledAttributes.hasValue(k.j.AppCompatTheme_windowActionBar)) {
            typedArrayObtainStyledAttributes.recycle();
            throw new IllegalStateException("You need to use a Theme.AppCompat theme (or descendant) with this activity.");
        }
        if (typedArrayObtainStyledAttributes.getBoolean(k.j.AppCompatTheme_windowNoTitle, false)) {
            o(1);
        } else if (typedArrayObtainStyledAttributes.getBoolean(k.j.AppCompatTheme_windowActionBar, false)) {
            o(108);
        }
        if (typedArrayObtainStyledAttributes.getBoolean(k.j.AppCompatTheme_windowActionBarOverlay, false)) {
            o(109);
        }
        if (typedArrayObtainStyledAttributes.getBoolean(k.j.AppCompatTheme_windowActionModeOverlay, false)) {
            o(10);
        }
        this.f766k = typedArrayObtainStyledAttributes.getBoolean(k.j.AppCompatTheme_android_windowIsFloating, false);
        typedArrayObtainStyledAttributes.recycle();
        Window window = this.f758c;
        window.getDecorView();
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(context);
        if (this.f767l) {
            viewGroup = this.f765j ? (ViewGroup) layoutInflaterFrom.inflate(k.g.abc_screen_simple_overlay_action_mode, (ViewGroup) null) : (ViewGroup) layoutInflaterFrom.inflate(k.g.abc_screen_simple, (ViewGroup) null);
            android.support.v4.view.h.d(viewGroup, new k(this));
        } else if (this.f766k) {
            viewGroup = (ViewGroup) layoutInflaterFrom.inflate(k.g.abc_dialog_title_material, (ViewGroup) null);
            this.f764i = false;
            this.f763h = false;
        } else if (this.f763h) {
            TypedValue typedValue = new TypedValue();
            context.getTheme().resolveAttribute(k.a.actionBarTheme, typedValue, true);
            viewGroup = (ViewGroup) LayoutInflater.from(typedValue.resourceId != 0 ? new n.d(context, typedValue.resourceId) : context).inflate(k.g.abc_screen_toolbar, (ViewGroup) null);
            c0 c0Var = (c0) viewGroup.findViewById(k.f.decor_content_parent);
            this.f714p = c0Var;
            c0Var.setWindowCallback(window.getCallback());
            if (this.f764i) {
                this.f714p.h(109);
            }
            if (this.B) {
                this.f714p.h(2);
            }
            if (this.C) {
                this.f714p.h(5);
            }
        } else {
            viewGroup = null;
        }
        if (viewGroup == null) {
            throw new IllegalArgumentException("AppCompat does not support the current theme features: { windowActionBar: " + this.f763h + ", windowActionBarOverlay: " + this.f764i + ", android:windowIsFloating: " + this.f766k + ", windowActionModeOverlay: " + this.f765j + ", windowNoTitle: " + this.f767l + " }");
        }
        if (this.f714p == null) {
            this.f723z = (TextView) viewGroup.findViewById(k.f.title);
        }
        int i2 = b1.f1197b;
        try {
            Method method = viewGroup.getClass().getMethod("makeOptionalFitsSystemWindows", null);
            if (!method.isAccessible()) {
                method.setAccessible(true);
            }
            method.invoke(viewGroup, null);
        } catch (IllegalAccessException e2) {
            Log.d("ViewUtils", "Could not invoke makeOptionalFitsSystemWindows", e2);
        } catch (NoSuchMethodException unused) {
            Log.d("ViewUtils", "Could not find method makeOptionalFitsSystemWindows. Oh well...");
        } catch (InvocationTargetException e3) {
            Log.d("ViewUtils", "Could not invoke makeOptionalFitsSystemWindows", e3);
        }
        ContentFrameLayout contentFrameLayout = (ContentFrameLayout) viewGroup.findViewById(k.f.action_bar_activity_content);
        ViewGroup viewGroup2 = (ViewGroup) window.findViewById(R.id.content);
        if (viewGroup2 != null) {
            while (viewGroup2.getChildCount() > 0) {
                View childAt = viewGroup2.getChildAt(0);
                viewGroup2.removeViewAt(0);
                contentFrameLayout.addView(childAt);
            }
            viewGroup2.setId(-1);
            contentFrameLayout.setId(R.id.content);
            if (viewGroup2 instanceof FrameLayout) {
                ((FrameLayout) viewGroup2).setForeground(null);
            }
        }
        window.setContentView(viewGroup);
        contentFrameLayout.setAttachListener(new l(this));
        this.f722y = viewGroup;
        CharSequence charSequenceU = u();
        if (!TextUtils.isEmpty(charSequenceU)) {
            y(charSequenceU);
        }
        ContentFrameLayout contentFrameLayout2 = (ContentFrameLayout) this.f722y.findViewById(R.id.content);
        View decorView = window.getDecorView();
        contentFrameLayout2.b(decorView.getPaddingLeft(), decorView.getPaddingTop(), decorView.getPaddingRight(), decorView.getPaddingBottom());
        TypedArray typedArrayObtainStyledAttributes2 = context.obtainStyledAttributes(k.j.AppCompatTheme);
        typedArrayObtainStyledAttributes2.getValue(k.j.AppCompatTheme_windowMinWidthMajor, contentFrameLayout2.getMinWidthMajor());
        typedArrayObtainStyledAttributes2.getValue(k.j.AppCompatTheme_windowMinWidthMinor, contentFrameLayout2.getMinWidthMinor());
        if (typedArrayObtainStyledAttributes2.hasValue(k.j.AppCompatTheme_windowFixedWidthMajor)) {
            typedArrayObtainStyledAttributes2.getValue(k.j.AppCompatTheme_windowFixedWidthMajor, contentFrameLayout2.getFixedWidthMajor());
        }
        if (typedArrayObtainStyledAttributes2.hasValue(k.j.AppCompatTheme_windowFixedWidthMinor)) {
            typedArrayObtainStyledAttributes2.getValue(k.j.AppCompatTheme_windowFixedWidthMinor, contentFrameLayout2.getFixedWidthMinor());
        }
        if (typedArrayObtainStyledAttributes2.hasValue(k.j.AppCompatTheme_windowFixedHeightMajor)) {
            typedArrayObtainStyledAttributes2.getValue(k.j.AppCompatTheme_windowFixedHeightMajor, contentFrameLayout2.getFixedHeightMajor());
        }
        if (typedArrayObtainStyledAttributes2.hasValue(k.j.AppCompatTheme_windowFixedHeightMinor)) {
            typedArrayObtainStyledAttributes2.getValue(k.j.AppCompatTheme_windowFixedHeightMinor, contentFrameLayout2.getFixedHeightMinor());
        }
        typedArrayObtainStyledAttributes2.recycle();
        contentFrameLayout2.requestLayout();
        this.f721x = true;
        PanelFeatureState panelFeatureStateI = I(0);
        if (w() || panelFeatureStateI.f731h != null) {
            return;
        }
        this.I |= 4096;
        if (this.H) {
            return;
        }
        window.getDecorView().postOnAnimation(this.J);
        this.H = true;
    }

    private void J(PanelFeatureState panelFeatureState, KeyEvent keyEvent) {
        int i2;
        ViewGroup.LayoutParams layoutParams;
        if (panelFeatureState.f736m || w()) {
            return;
        }
        int i3 = panelFeatureState.f724a;
        Context context = this.f757b;
        if (i3 == 0 && (context.getResources().getConfiguration().screenLayout & 15) == 4) {
            return;
        }
        Window.Callback callback = this.f758c.getCallback();
        if (callback != null && !callback.onMenuOpened(i3, panelFeatureState.f731h)) {
            D(panelFeatureState, true);
            return;
        }
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        if (windowManager != null && L(panelFeatureState, keyEvent)) {
            ViewGroup viewGroup = panelFeatureState.f728e;
            if (viewGroup != null && !panelFeatureState.f737n) {
                View view = panelFeatureState.f730g;
                if (view != null && (layoutParams = view.getLayoutParams()) != null && layoutParams.width == -1) {
                    i2 = -1;
                }
                panelFeatureState.f735l = false;
                WindowManager.LayoutParams layoutParams2 = new WindowManager.LayoutParams(i2, -2, 0, 0, CredentialsApi.ACTIVITY_RESULT_NO_HINTS_AVAILABLE, 8519680, -3);
                layoutParams2.gravity = panelFeatureState.f726c;
                layoutParams2.windowAnimations = panelFeatureState.f727d;
                windowManager.addView(panelFeatureState.f728e, layoutParams2);
                panelFeatureState.f736m = true;
            }
            if (viewGroup == null) {
                v();
                s sVar = this.f761f;
                Context contextD = sVar != null ? sVar.d() : null;
                if (contextD != null) {
                    context = contextD;
                }
                TypedValue typedValue = new TypedValue();
                Resources.Theme themeNewTheme = context.getResources().newTheme();
                themeNewTheme.setTo(context.getTheme());
                themeNewTheme.resolveAttribute(k.a.actionBarPopupTheme, typedValue, true);
                int i4 = typedValue.resourceId;
                if (i4 != 0) {
                    themeNewTheme.applyStyle(i4, true);
                }
                themeNewTheme.resolveAttribute(k.a.panelMenuListTheme, typedValue, true);
                int i5 = typedValue.resourceId;
                if (i5 != 0) {
                    themeNewTheme.applyStyle(i5, true);
                } else {
                    themeNewTheme.applyStyle(k.i.Theme_AppCompat_CompactMenu, true);
                }
                n.d dVar = new n.d(context, 0);
                dVar.getTheme().setTo(themeNewTheme);
                panelFeatureState.f733j = dVar;
                TypedArray typedArrayObtainStyledAttributes = dVar.obtainStyledAttributes(k.j.AppCompatTheme);
                panelFeatureState.f725b = typedArrayObtainStyledAttributes.getResourceId(k.j.AppCompatTheme_panelBackground, 0);
                panelFeatureState.f727d = typedArrayObtainStyledAttributes.getResourceId(k.j.AppCompatTheme_android_windowAnimationStyle, 0);
                typedArrayObtainStyledAttributes.recycle();
                panelFeatureState.f728e = new d(panelFeatureState.f733j);
                panelFeatureState.f726c = 81;
            } else if (panelFeatureState.f737n && viewGroup.getChildCount() > 0) {
                panelFeatureState.f728e.removeAllViews();
            }
            View view2 = panelFeatureState.f730g;
            if (view2 != null) {
                panelFeatureState.f729f = view2;
            } else {
                if (panelFeatureState.f731h == null) {
                    return;
                }
                if (this.f716r == null) {
                    this.f716r = new e();
                }
                e eVar = this.f716r;
                if (panelFeatureState.f732i == null) {
                    android.support.v7.view.menu.f fVar = new android.support.v7.view.menu.f(panelFeatureState.f733j, k.g.abc_list_menu_item_layout);
                    panelFeatureState.f732i = fVar;
                    fVar.d(eVar);
                    panelFeatureState.f731h.b(panelFeatureState.f732i);
                }
                View view3 = (View) panelFeatureState.f732i.f(panelFeatureState.f728e);
                panelFeatureState.f729f = view3;
                if (view3 == null) {
                    return;
                }
            }
            if (panelFeatureState.f729f == null) {
                return;
            }
            if (panelFeatureState.f730g == null && panelFeatureState.f732i.a().getCount() <= 0) {
                return;
            }
            ViewGroup.LayoutParams layoutParams3 = panelFeatureState.f729f.getLayoutParams();
            if (layoutParams3 == null) {
                layoutParams3 = new ViewGroup.LayoutParams(-2, -2);
            }
            panelFeatureState.f728e.setBackgroundResource(panelFeatureState.f725b);
            ViewParent parent = panelFeatureState.f729f.getParent();
            if (parent != null && (parent instanceof ViewGroup)) {
                ((ViewGroup) parent).removeView(panelFeatureState.f729f);
            }
            panelFeatureState.f728e.addView(panelFeatureState.f729f, layoutParams3);
            if (!panelFeatureState.f729f.hasFocus()) {
                panelFeatureState.f729f.requestFocus();
            }
            i2 = -2;
            panelFeatureState.f735l = false;
            WindowManager.LayoutParams layoutParams22 = new WindowManager.LayoutParams(i2, -2, 0, 0, CredentialsApi.ACTIVITY_RESULT_NO_HINTS_AVAILABLE, 8519680, -3);
            layoutParams22.gravity = panelFeatureState.f726c;
            layoutParams22.windowAnimations = panelFeatureState.f727d;
            windowManager.addView(panelFeatureState.f728e, layoutParams22);
            panelFeatureState.f736m = true;
        }
    }

    private boolean K(PanelFeatureState panelFeatureState, int i2, KeyEvent keyEvent) {
        android.support.v7.view.menu.h hVar;
        if (keyEvent.isSystem()) {
            return false;
        }
        if ((panelFeatureState.f734k || L(panelFeatureState, keyEvent)) && (hVar = panelFeatureState.f731h) != null) {
            return hVar.performShortcut(i2, keyEvent, 1);
        }
        return false;
    }

    private boolean L(PanelFeatureState panelFeatureState, KeyEvent keyEvent) {
        c0 c0Var;
        c0 c0Var2;
        Resources.Theme themeNewTheme;
        c0 c0Var3;
        c0 c0Var4;
        if (w()) {
            return false;
        }
        if (panelFeatureState.f734k) {
            return true;
        }
        PanelFeatureState panelFeatureState2 = this.F;
        if (panelFeatureState2 != null && panelFeatureState2 != panelFeatureState) {
            D(panelFeatureState2, false);
        }
        Window.Callback callback = this.f758c.getCallback();
        int i2 = panelFeatureState.f724a;
        if (callback != null) {
            panelFeatureState.f730g = callback.onCreatePanelView(i2);
        }
        boolean z2 = i2 == 0 || i2 == 108;
        if (z2 && (c0Var4 = this.f714p) != null) {
            c0Var4.c();
        }
        if (panelFeatureState.f730g == null) {
            android.support.v7.view.menu.h hVar = panelFeatureState.f731h;
            if (hVar == null || panelFeatureState.f738o) {
                if (hVar == null) {
                    Context context = this.f757b;
                    if ((i2 == 0 || i2 == 108) && this.f714p != null) {
                        TypedValue typedValue = new TypedValue();
                        Resources.Theme theme = context.getTheme();
                        theme.resolveAttribute(k.a.actionBarTheme, typedValue, true);
                        if (typedValue.resourceId != 0) {
                            themeNewTheme = context.getResources().newTheme();
                            themeNewTheme.setTo(theme);
                            themeNewTheme.applyStyle(typedValue.resourceId, true);
                            themeNewTheme.resolveAttribute(k.a.actionBarWidgetTheme, typedValue, true);
                        } else {
                            theme.resolveAttribute(k.a.actionBarWidgetTheme, typedValue, true);
                            themeNewTheme = null;
                        }
                        if (typedValue.resourceId != 0) {
                            if (themeNewTheme == null) {
                                themeNewTheme = context.getResources().newTheme();
                                themeNewTheme.setTo(theme);
                            }
                            themeNewTheme.applyStyle(typedValue.resourceId, true);
                        }
                        if (themeNewTheme != null) {
                            n.d dVar = new n.d(context, 0);
                            dVar.getTheme().setTo(themeNewTheme);
                            context = dVar;
                        }
                    }
                    android.support.v7.view.menu.h hVar2 = new android.support.v7.view.menu.h(context);
                    hVar2.B(this);
                    android.support.v7.view.menu.h hVar3 = panelFeatureState.f731h;
                    if (hVar2 != hVar3) {
                        if (hVar3 != null) {
                            hVar3.y(panelFeatureState.f732i);
                        }
                        panelFeatureState.f731h = hVar2;
                        android.support.v7.view.menu.f fVar = panelFeatureState.f732i;
                        if (fVar != null) {
                            hVar2.b(fVar);
                        }
                    }
                    if (panelFeatureState.f731h == null) {
                        return false;
                    }
                }
                if (z2 && (c0Var2 = this.f714p) != null) {
                    if (this.f715q == null) {
                        this.f715q = new b();
                    }
                    c0Var2.a(panelFeatureState.f731h, this.f715q);
                }
                panelFeatureState.f731h.M();
                if (!callback.onCreatePanelMenu(i2, panelFeatureState.f731h)) {
                    android.support.v7.view.menu.h hVar4 = panelFeatureState.f731h;
                    if (hVar4 != null) {
                        if (hVar4 != null) {
                            hVar4.y(panelFeatureState.f732i);
                        }
                        panelFeatureState.f731h = null;
                    }
                    if (z2 && (c0Var = this.f714p) != null) {
                        c0Var.a(null, this.f715q);
                    }
                    return false;
                }
                panelFeatureState.f738o = false;
            }
            panelFeatureState.f731h.M();
            Bundle bundle = panelFeatureState.f739p;
            if (bundle != null) {
                panelFeatureState.f731h.z(bundle);
                panelFeatureState.f739p = null;
            }
            if (!callback.onPreparePanel(0, panelFeatureState.f730g, panelFeatureState.f731h)) {
                if (z2 && (c0Var3 = this.f714p) != null) {
                    c0Var3.a(null, this.f715q);
                }
                panelFeatureState.f731h.L();
                return false;
            }
            panelFeatureState.f731h.setQwertyMode(KeyCharacterMap.load(keyEvent != null ? keyEvent.getDeviceId() : -1).getKeyboardType() != 1);
            panelFeatureState.f731h.L();
        }
        panelFeatureState.f734k = true;
        panelFeatureState.f735l = false;
        this.F = panelFeatureState;
        return true;
    }

    private void O() {
        if (this.f721x) {
            throw new AndroidRuntimeException("Window feature must be requested before adding content");
        }
    }

    View A(String str, Context context, AttributeSet attributeSet) {
        throw null;
    }

    final void B(int i2, PanelFeatureState panelFeatureState, android.support.v7.view.menu.h hVar) {
        if (hVar == null) {
            if (panelFeatureState == null && i2 >= 0) {
                PanelFeatureState[] panelFeatureStateArr = this.E;
                if (i2 < panelFeatureStateArr.length) {
                    panelFeatureState = panelFeatureStateArr[i2];
                }
            }
            if (panelFeatureState != null) {
                hVar = panelFeatureState.f731h;
            }
        }
        if ((panelFeatureState == null || panelFeatureState.f736m) && !w()) {
            this.f759d.onPanelClosed(i2, hVar);
        }
    }

    final void C(android.support.v7.view.menu.h hVar) {
        if (this.D) {
            return;
        }
        this.D = true;
        this.f714p.i();
        Window.Callback callback = this.f758c.getCallback();
        if (callback != null && !w()) {
            callback.onPanelClosed(108, hVar);
        }
        this.D = false;
    }

    final void D(PanelFeatureState panelFeatureState, boolean z2) {
        ViewGroup viewGroup;
        c0 c0Var;
        if (z2 && panelFeatureState.f724a == 0 && (c0Var = this.f714p) != null && c0Var.b()) {
            C(panelFeatureState.f731h);
            return;
        }
        WindowManager windowManager = (WindowManager) this.f757b.getSystemService("window");
        if (windowManager != null && panelFeatureState.f736m && (viewGroup = panelFeatureState.f728e) != null) {
            windowManager.removeView(viewGroup);
            if (z2) {
                B(panelFeatureState.f724a, panelFeatureState, null);
            }
        }
        panelFeatureState.f734k = false;
        panelFeatureState.f735l = false;
        panelFeatureState.f736m = false;
        panelFeatureState.f729f = null;
        panelFeatureState.f737n = true;
        if (this.F == panelFeatureState) {
            this.F = null;
        }
    }

    final void E() {
        c0 c0Var = this.f714p;
        if (c0Var != null) {
            c0Var.i();
        }
        if (this.f718u != null) {
            this.f758c.getDecorView().removeCallbacks(this.f719v);
            if (this.f718u.isShowing()) {
                try {
                    this.f718u.dismiss();
                } catch (IllegalArgumentException unused) {
                }
            }
            this.f718u = null;
        }
        android.support.v4.view.l lVar = this.f720w;
        if (lVar != null) {
            lVar.b();
        }
        android.support.v7.view.menu.h hVar = I(0).f731h;
        if (hVar != null) {
            hVar.e(true);
        }
    }

    final void F(int i2) {
        PanelFeatureState panelFeatureStateI = I(i2);
        if (panelFeatureStateI.f731h != null) {
            Bundle bundle = new Bundle();
            panelFeatureStateI.f731h.A(bundle);
            if (bundle.size() > 0) {
                panelFeatureStateI.f739p = bundle;
            }
            panelFeatureStateI.f731h.M();
            panelFeatureStateI.f731h.clear();
        }
        panelFeatureStateI.f738o = true;
        panelFeatureStateI.f737n = true;
        if ((i2 == 108 || i2 == 0) && this.f714p != null) {
            PanelFeatureState panelFeatureStateI2 = I(0);
            panelFeatureStateI2.f734k = false;
            L(panelFeatureStateI2, null);
        }
    }

    final PanelFeatureState H(android.support.v7.view.menu.h hVar) {
        PanelFeatureState[] panelFeatureStateArr = this.E;
        int length = panelFeatureStateArr != null ? panelFeatureStateArr.length : 0;
        for (int i2 = 0; i2 < length; i2++) {
            PanelFeatureState panelFeatureState = panelFeatureStateArr[i2];
            if (panelFeatureState != null && panelFeatureState.f731h == hVar) {
                return panelFeatureState;
            }
        }
        return null;
    }

    protected final PanelFeatureState I(int i2) {
        PanelFeatureState[] panelFeatureStateArr = this.E;
        if (panelFeatureStateArr == null || panelFeatureStateArr.length <= i2) {
            PanelFeatureState[] panelFeatureStateArr2 = new PanelFeatureState[i2 + 1];
            if (panelFeatureStateArr != null) {
                System.arraycopy(panelFeatureStateArr, 0, panelFeatureStateArr2, 0, panelFeatureStateArr.length);
            }
            this.E = panelFeatureStateArr2;
            panelFeatureStateArr = panelFeatureStateArr2;
        }
        PanelFeatureState panelFeatureState = panelFeatureStateArr[i2];
        if (panelFeatureState != null) {
            return panelFeatureState;
        }
        PanelFeatureState panelFeatureState2 = new PanelFeatureState();
        panelFeatureState2.f724a = i2;
        panelFeatureState2.f737n = false;
        panelFeatureStateArr[i2] = panelFeatureState2;
        return panelFeatureState2;
    }

    final boolean M() {
        ViewGroup viewGroup;
        return this.f721x && (viewGroup = this.f722y) != null && viewGroup.isLaidOut();
    }

    public final n.b N(f.a aVar) {
        n.b bVar = this.s;
        if (bVar != null) {
            bVar.c();
        }
        i iVar = (i) this;
        c cVar = new c(iVar, aVar);
        v();
        s sVar = this.f761f;
        if (sVar != null) {
            s.d dVar = sVar.f815i;
            if (dVar != null) {
                dVar.c();
            }
            sVar.f809c.setHideOnContentScrollEnabled(false);
            sVar.f812f.i();
            s.d dVar2 = sVar.new d(sVar.f812f.getContext(), cVar);
            if (dVar2.t()) {
                sVar.f815i = dVar2;
                dVar2.k();
                sVar.f812f.f(dVar2);
                sVar.a(true);
                sVar.f812f.sendAccessibilityEvent(32);
            } else {
                dVar2 = null;
            }
            this.s = dVar2;
        }
        if (this.s == null) {
            android.support.v4.view.l lVar = this.f720w;
            if (lVar != null) {
                lVar.b();
            }
            n.b bVar2 = this.s;
            if (bVar2 != null) {
                bVar2.c();
            }
            if (this.f717t == null) {
                boolean z2 = this.f766k;
                Context context = this.f757b;
                if (z2) {
                    TypedValue typedValue = new TypedValue();
                    Resources.Theme theme = context.getTheme();
                    theme.resolveAttribute(k.a.actionBarTheme, typedValue, true);
                    if (typedValue.resourceId != 0) {
                        Resources.Theme themeNewTheme = context.getResources().newTheme();
                        themeNewTheme.setTo(theme);
                        themeNewTheme.applyStyle(typedValue.resourceId, true);
                        n.d dVar3 = new n.d(context, 0);
                        dVar3.getTheme().setTo(themeNewTheme);
                        context = dVar3;
                    }
                    this.f717t = new ActionBarContextView(context, null);
                    PopupWindow popupWindow = new PopupWindow(context, (AttributeSet) null, k.a.actionModePopupWindowStyle);
                    this.f718u = popupWindow;
                    android.support.v4.widget.f.b(popupWindow, 2);
                    this.f718u.setContentView(this.f717t);
                    this.f718u.setWidth(-1);
                    context.getTheme().resolveAttribute(k.a.actionBarSize, typedValue, true);
                    this.f717t.setContentHeight(TypedValue.complexToDimensionPixelSize(typedValue.data, context.getResources().getDisplayMetrics()));
                    this.f718u.setHeight(-2);
                    this.f719v = new m(iVar);
                } else {
                    ViewStubCompat viewStubCompat = (ViewStubCompat) this.f722y.findViewById(k.f.action_mode_bar_stub);
                    if (viewStubCompat != null) {
                        v();
                        s sVar2 = this.f761f;
                        Context contextD = sVar2 != null ? sVar2.d() : null;
                        if (contextD != null) {
                            context = contextD;
                        }
                        viewStubCompat.setLayoutInflater(LayoutInflater.from(context));
                        this.f717t = (ActionBarContextView) viewStubCompat.a();
                    }
                }
            }
            if (this.f717t != null) {
                android.support.v4.view.l lVar2 = this.f720w;
                if (lVar2 != null) {
                    lVar2.b();
                }
                this.f717t.i();
                n.e eVar = new n.e(this.f717t.getContext(), this.f717t, cVar);
                if (cVar.d(eVar, eVar.e())) {
                    eVar.k();
                    this.f717t.f(eVar);
                    this.s = eVar;
                    if (M()) {
                        this.f717t.setAlpha(0.0f);
                        android.support.v4.view.l lVarA = android.support.v4.view.h.a(this.f717t);
                        lVarA.a(1.0f);
                        this.f720w = lVarA;
                        lVarA.f(new n(iVar));
                    } else {
                        this.f717t.setAlpha(1.0f);
                        this.f717t.setVisibility(0);
                        this.f717t.sendAccessibilityEvent(32);
                        if (this.f717t.getParent() instanceof View) {
                            ((View) this.f717t.getParent()).requestApplyInsets();
                        }
                    }
                    if (this.f718u != null) {
                        this.f758c.getDecorView().post(this.f719v);
                    }
                } else {
                    this.s = null;
                }
            }
            this.s = this.s;
        }
        return this.s;
    }

    final int P(int i2) {
        boolean z2;
        boolean z3;
        ActionBarContextView actionBarContextView = this.f717t;
        if (actionBarContextView == null || !(actionBarContextView.getLayoutParams() instanceof ViewGroup.MarginLayoutParams)) {
            z2 = false;
        } else {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.f717t.getLayoutParams();
            if (this.f717t.isShown()) {
                if (this.L == null) {
                    this.L = new Rect();
                    this.M = new Rect();
                }
                Rect rect = this.L;
                Rect rect2 = this.M;
                rect.set(0, i2, 0, 0);
                b1.a(this.f722y, rect, rect2);
                if (marginLayoutParams.topMargin != (rect2.top == 0 ? i2 : 0)) {
                    marginLayoutParams.topMargin = i2;
                    View view = this.A;
                    if (view == null) {
                        Context context = this.f757b;
                        View view2 = new View(context);
                        this.A = view2;
                        view2.setBackgroundColor(context.getResources().getColor(k.c.abc_input_method_navigation_guard));
                        this.f722y.addView(this.A, -1, new ViewGroup.LayoutParams(-1, i2));
                    } else {
                        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                        if (layoutParams.height != i2) {
                            layoutParams.height = i2;
                            this.A.setLayoutParams(layoutParams);
                        }
                    }
                    z3 = true;
                } else {
                    z3 = false;
                }
                z = this.A != null;
                if (!this.f765j && z) {
                    i2 = 0;
                }
                boolean z4 = z;
                z = z3;
                z2 = z4;
            } else if (marginLayoutParams.topMargin != 0) {
                marginLayoutParams.topMargin = 0;
                z2 = false;
            } else {
                z2 = false;
                z = false;
            }
            if (z) {
                this.f717t.setLayoutParams(marginLayoutParams);
            }
        }
        View view3 = this.A;
        if (view3 != null) {
            view3.setVisibility(z2 ? 0 : 8);
        }
        return i2;
    }

    @Override // android.support.v7.view.menu.h.a
    public final boolean a(android.support.v7.view.menu.h hVar, android.support.v7.view.menu.j jVar) {
        PanelFeatureState panelFeatureStateH;
        Window.Callback callback = this.f758c.getCallback();
        if (callback == null || w() || (panelFeatureStateH = H(hVar.q())) == null) {
            return false;
        }
        return callback.onMenuItemSelected(panelFeatureStateH.f724a, jVar);
    }

    @Override // android.support.v7.view.menu.h.a
    public final void b(android.support.v7.view.menu.h hVar) {
        c0 c0Var = this.f714p;
        if (c0Var == null || !c0Var.g() || (ViewConfiguration.get(this.f757b).hasPermanentMenuKey() && !this.f714p.d())) {
            PanelFeatureState panelFeatureStateI = I(0);
            panelFeatureStateI.f737n = true;
            D(panelFeatureStateI, false);
            J(panelFeatureStateI, null);
            return;
        }
        Window window = this.f758c;
        Window.Callback callback = window.getCallback();
        if (this.f714p.b()) {
            this.f714p.e();
            if (w()) {
                return;
            }
            callback.onPanelClosed(108, I(0).f731h);
            return;
        }
        if (callback == null || w()) {
            return;
        }
        if (this.H && (1 & this.I) != 0) {
            View decorView = window.getDecorView();
            Runnable runnable = this.J;
            decorView.removeCallbacks(runnable);
            ((a) runnable).run();
        }
        PanelFeatureState panelFeatureStateI2 = I(0);
        android.support.v7.view.menu.h hVar2 = panelFeatureStateI2.f731h;
        if (hVar2 == null || panelFeatureStateI2.f738o || !callback.onPreparePanel(0, panelFeatureStateI2.f730g, hVar2)) {
            return;
        }
        callback.onMenuOpened(108, panelFeatureStateI2.f731h);
        this.f714p.f();
    }

    @Override // android.support.v7.app.e
    public final void c(View view, ViewGroup.LayoutParams layoutParams) {
        G();
        ((ViewGroup) this.f722y.findViewById(R.id.content)).addView(view, layoutParams);
        this.f759d.onContentChanged();
    }

    @Override // android.support.v7.app.e
    public final <T extends View> T e(int i2) {
        G();
        return (T) this.f758c.findViewById(i2);
    }

    @Override // android.support.v7.app.e
    public final void f() {
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(this.f757b);
        if (layoutInflaterFrom.getFactory() == null) {
            layoutInflaterFrom.setFactory2(this);
        } else {
            if (layoutInflaterFrom.getFactory2() instanceof AppCompatDelegateImplV9) {
                return;
            }
            Log.i("AppCompatDelegate", "The Activity's LayoutInflater already has a Factory installed so we can not install AppCompat's");
        }
    }

    @Override // android.support.v7.app.e
    public final void g() {
        v();
        this.I |= 1;
        if (this.H) {
            return;
        }
        this.f758c.getDecorView().postOnAnimation(this.J);
        this.H = true;
    }

    @Override // android.support.v7.app.e
    public final void h(Configuration configuration) {
        if (this.f763h && this.f721x) {
            v();
            s sVar = this.f761f;
            if (sVar != null) {
                sVar.g();
            }
        }
        android.support.v7.widget.i.c().h(this.f757b);
        d();
    }

    @Override // android.support.v7.app.e
    public void i(Bundle bundle) {
        Window.Callback callback = this.f759d;
        if (callback instanceof Activity) {
            Activity activity = (Activity) callback;
            try {
                if (a0.c(activity, activity.getComponentName()) != null) {
                    s sVar = this.f761f;
                    if (sVar == null) {
                        this.K = true;
                    } else {
                        sVar.j(true);
                    }
                }
            } catch (PackageManager.NameNotFoundException e2) {
                throw new IllegalArgumentException(e2);
            }
        }
    }

    @Override // android.support.v7.app.f, android.support.v7.app.e
    public void j() {
        if (this.H) {
            this.f758c.getDecorView().removeCallbacks(this.J);
        }
        super.j();
    }

    @Override // android.support.v7.app.e
    public final void k() {
        G();
    }

    @Override // android.support.v7.app.e
    public void n() {
        v();
        s sVar = this.f761f;
        if (sVar != null) {
            sVar.l(false);
        }
    }

    @Override // android.support.v7.app.e
    public final boolean o(int i2) {
        if (i2 == 8) {
            Log.i("AppCompatDelegate", "You should now use the AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR id when requesting this feature.");
            i2 = 108;
        } else if (i2 == 9) {
            Log.i("AppCompatDelegate", "You should now use the AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR_OVERLAY id when requesting this feature.");
            i2 = 109;
        }
        if (this.f767l && i2 == 108) {
            return false;
        }
        if (this.f763h && i2 == 1) {
            this.f763h = false;
        }
        if (i2 == 1) {
            O();
            this.f767l = true;
            return true;
        }
        if (i2 == 2) {
            O();
            this.B = true;
            return true;
        }
        if (i2 == 5) {
            O();
            this.C = true;
            return true;
        }
        if (i2 == 10) {
            O();
            this.f765j = true;
            return true;
        }
        if (i2 == 108) {
            O();
            this.f763h = true;
            return true;
        }
        if (i2 != 109) {
            return this.f758c.requestFeature(i2);
        }
        O();
        this.f764i = true;
        return true;
    }

    @Override // android.view.LayoutInflater.Factory2
    public final View onCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        View viewA = A(str, context, attributeSet);
        if (viewA != null) {
            return viewA;
        }
        if (this.N == null) {
            this.N = new p();
        }
        p pVar = this.N;
        int i2 = a1.f1182a;
        return pVar.b(view, str, context, attributeSet);
    }

    @Override // android.support.v7.app.e
    public final void p(int i2) {
        G();
        ViewGroup viewGroup = (ViewGroup) this.f722y.findViewById(R.id.content);
        viewGroup.removeAllViews();
        LayoutInflater.from(this.f757b).inflate(i2, viewGroup);
        this.f759d.onContentChanged();
    }

    @Override // android.support.v7.app.e
    public final void q(View view) {
        G();
        ViewGroup viewGroup = (ViewGroup) this.f722y.findViewById(R.id.content);
        viewGroup.removeAllViews();
        viewGroup.addView(view);
        this.f759d.onContentChanged();
    }

    @Override // android.support.v7.app.e
    public final void r(View view, ViewGroup.LayoutParams layoutParams) {
        G();
        ViewGroup viewGroup = (ViewGroup) this.f722y.findViewById(R.id.content);
        viewGroup.removeAllViews();
        viewGroup.addView(view, layoutParams);
        this.f759d.onContentChanged();
    }

    /* JADX WARN: Removed duplicated region for block: B:56:0x00ac  */
    /* JADX WARN: Removed duplicated region for block: B:89:? A[RETURN, SYNTHETIC] */
    @Override // android.support.v7.app.f
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    final boolean t(KeyEvent keyEvent) {
        boolean z2;
        d0 d0Var;
        boolean zE;
        boolean zL;
        if (keyEvent.getKeyCode() == 82 && this.f759d.dispatchKeyEvent(keyEvent)) {
            return true;
        }
        int keyCode = keyEvent.getKeyCode();
        if (keyEvent.getAction() == 0) {
            if (keyCode == 4) {
                this.G = (keyEvent.getFlags() & VertexAttributes.Usage.Tangent) != 0;
            } else if (keyCode == 82) {
                if (keyEvent.getRepeatCount() != 0) {
                    return true;
                }
                PanelFeatureState panelFeatureStateI = I(0);
                if (panelFeatureStateI.f736m) {
                    return true;
                }
                L(panelFeatureStateI, keyEvent);
                return true;
            }
        } else if (keyCode == 4) {
            boolean z3 = this.G;
            this.G = false;
            PanelFeatureState panelFeatureStateI2 = I(0);
            if (panelFeatureStateI2.f736m) {
                if (z3) {
                    return true;
                }
                D(panelFeatureStateI2, true);
                return true;
            }
            n.b bVar = this.s;
            if (bVar != null) {
                bVar.c();
            } else {
                v();
                s sVar = this.f761f;
                if (sVar == null || (d0Var = sVar.f811e) == null || !d0Var.k()) {
                    z2 = false;
                    if (z2) {
                        return true;
                    }
                } else {
                    sVar.f811e.collapseActionView();
                }
            }
            z2 = true;
            if (z2) {
            }
        } else if (keyCode == 82) {
            if (this.s != null) {
                return true;
            }
            PanelFeatureState panelFeatureStateI3 = I(0);
            c0 c0Var = this.f714p;
            Context context = this.f757b;
            if (c0Var == null || !c0Var.g() || ViewConfiguration.get(context).hasPermanentMenuKey()) {
                boolean z4 = panelFeatureStateI3.f736m;
                if (z4 || panelFeatureStateI3.f735l) {
                    D(panelFeatureStateI3, true);
                    zE = z4;
                } else if (panelFeatureStateI3.f734k) {
                    if (panelFeatureStateI3.f738o) {
                        panelFeatureStateI3.f734k = false;
                        zL = L(panelFeatureStateI3, keyEvent);
                    } else {
                        zL = true;
                    }
                    if (zL) {
                        J(panelFeatureStateI3, keyEvent);
                        zE = true;
                    }
                } else {
                    zE = false;
                }
            } else if (this.f714p.b()) {
                zE = this.f714p.e();
            } else if (!w() && L(panelFeatureStateI3, keyEvent)) {
                zE = this.f714p.f();
            }
            if (!zE) {
                return true;
            }
            AudioManager audioManager = (AudioManager) context.getSystemService("audio");
            if (audioManager != null) {
                audioManager.playSoundEffect(0);
                return true;
            }
            Log.w("AppCompatDelegate", "Couldn't get audio manager");
            return true;
        }
        return false;
    }

    @Override // android.support.v7.app.f
    public final void v() {
        G();
        if (this.f763h && this.f761f == null) {
            Window.Callback callback = this.f759d;
            if (callback instanceof Activity) {
                this.f761f = new s((Activity) callback, this.f764i);
            } else if (callback instanceof Dialog) {
                this.f761f = new s((Dialog) callback);
            }
            s sVar = this.f761f;
            if (sVar != null) {
                sVar.j(this.K);
            }
        }
    }

    @Override // android.support.v7.app.f
    final boolean x(int i2, KeyEvent keyEvent) {
        android.support.v7.view.menu.h hVarE;
        boolean zPerformShortcut;
        v();
        s sVar = this.f761f;
        if (sVar != null) {
            s.d dVar = sVar.f815i;
            if (dVar == null || (hVarE = dVar.e()) == null) {
                zPerformShortcut = false;
            } else {
                hVarE.setQwertyMode(KeyCharacterMap.load(keyEvent != null ? keyEvent.getDeviceId() : -1).getKeyboardType() != 1);
                zPerformShortcut = hVarE.performShortcut(i2, keyEvent, 0);
            }
            if (zPerformShortcut) {
                return true;
            }
        }
        PanelFeatureState panelFeatureState = this.F;
        if (panelFeatureState != null && K(panelFeatureState, keyEvent.getKeyCode(), keyEvent)) {
            PanelFeatureState panelFeatureState2 = this.F;
            if (panelFeatureState2 != null) {
                panelFeatureState2.f735l = true;
            }
            return true;
        }
        if (this.F == null) {
            PanelFeatureState panelFeatureStateI = I(0);
            L(panelFeatureStateI, keyEvent);
            boolean zK = K(panelFeatureStateI, keyEvent.getKeyCode(), keyEvent);
            panelFeatureStateI.f734k = false;
            if (zK) {
                return true;
            }
        }
        return false;
    }

    @Override // android.support.v7.app.f
    final void y(CharSequence charSequence) {
        c0 c0Var = this.f714p;
        if (c0Var != null) {
            c0Var.setWindowTitle(charSequence);
            return;
        }
        s sVar = this.f761f;
        if (sVar != null) {
            sVar.f811e.setWindowTitle(charSequence);
            return;
        }
        TextView textView = this.f723z;
        if (textView != null) {
            textView.setText(charSequence);
        }
    }

    @Override // android.view.LayoutInflater.Factory
    public final View onCreateView(String str, Context context, AttributeSet attributeSet) {
        return onCreateView(null, str, context, attributeSet);
    }
}
