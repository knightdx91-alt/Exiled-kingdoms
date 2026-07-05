package android.support.v7.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import com.google.android.gms.auth.api.credentials.CredentialsApi;
import com.google.android.gms.common.api.Api;
import java.lang.reflect.Method;

/* JADX INFO: compiled from: ListPopupWindow.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class k0 implements o.b {
    private static Method A;
    private static Method B;
    private static Method C;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Context f1268b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private ListAdapter f1269c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    f0 f1270d;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private int f1273g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private int f1274h;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private boolean f1276j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private boolean f1277k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private boolean f1278l;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    private DataSetObserver f1281o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    private View f1282p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    private AdapterView.OnItemClickListener f1283q;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    final Handler f1287v;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    private Rect f1289x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    private boolean f1290y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    PopupWindow f1291z;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f1271e = -2;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private int f1272f = -2;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private int f1275i = CredentialsApi.ACTIVITY_RESULT_NO_HINTS_AVAILABLE;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private int f1279m = 0;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    int f1280n = Api.BaseClientBuilder.API_PRIORITY_OTHER;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    final e f1284r = new e();
    private final d s = new d();

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    private final c f1285t = new c();

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    private final a f1286u = new a();

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    private final Rect f1288w = new Rect();

    /* JADX INFO: compiled from: ListPopupWindow.java */
    private class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            f0 f0Var = k0.this.f1270d;
            if (f0Var != null) {
                f0Var.setListSelectionHidden(true);
                f0Var.requestLayout();
            }
        }
    }

    /* JADX INFO: compiled from: ListPopupWindow.java */
    private class b extends DataSetObserver {
        b() {
        }

        @Override // android.database.DataSetObserver
        public final void onChanged() {
            k0 k0Var = k0.this;
            if (k0Var.f1291z.isShowing()) {
                k0Var.a();
            }
        }

        @Override // android.database.DataSetObserver
        public final void onInvalidated() {
            k0.this.dismiss();
        }
    }

    /* JADX INFO: compiled from: ListPopupWindow.java */
    private class c implements AbsListView.OnScrollListener {
        c() {
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public final void onScroll(AbsListView absListView, int i2, int i3, int i4) {
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public final void onScrollStateChanged(AbsListView absListView, int i2) {
            if (i2 == 1) {
                k0 k0Var = k0.this;
                if (k0Var.f1291z.getInputMethodMode() == 2 || k0Var.f1291z.getContentView() == null) {
                    return;
                }
                Handler handler = k0Var.f1287v;
                e eVar = k0Var.f1284r;
                handler.removeCallbacks(eVar);
                eVar.run();
            }
        }
    }

    /* JADX INFO: compiled from: ListPopupWindow.java */
    private class d implements View.OnTouchListener {
        d() {
        }

        @Override // android.view.View.OnTouchListener
        public final boolean onTouch(View view, MotionEvent motionEvent) {
            PopupWindow popupWindow;
            int action = motionEvent.getAction();
            int x2 = (int) motionEvent.getX();
            int y2 = (int) motionEvent.getY();
            k0 k0Var = k0.this;
            if (action == 0 && (popupWindow = k0Var.f1291z) != null && popupWindow.isShowing() && x2 >= 0 && x2 < k0Var.f1291z.getWidth() && y2 >= 0 && y2 < k0Var.f1291z.getHeight()) {
                k0Var.f1287v.postDelayed(k0Var.f1284r, 250L);
                return false;
            }
            if (action != 1) {
                return false;
            }
            k0Var.f1287v.removeCallbacks(k0Var.f1284r);
            return false;
        }
    }

    /* JADX INFO: compiled from: ListPopupWindow.java */
    private class e implements Runnable {
        e() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            k0 k0Var = k0.this;
            f0 f0Var = k0Var.f1270d;
            if (f0Var == null || !f0Var.isAttachedToWindow() || k0Var.f1270d.getCount() <= k0Var.f1270d.getChildCount() || k0Var.f1270d.getChildCount() > k0Var.f1280n) {
                return;
            }
            k0Var.f1291z.setInputMethodMode(2);
            k0Var.a();
        }
    }

    static {
        try {
            A = PopupWindow.class.getDeclaredMethod("setClipToScreenEnabled", Boolean.TYPE);
        } catch (NoSuchMethodException unused) {
            Log.i("ListPopupWindow", "Could not find method setClipToScreenEnabled() on PopupWindow. Oh well.");
        }
        try {
            B = PopupWindow.class.getDeclaredMethod("getMaxAvailableHeight", View.class, Integer.TYPE, Boolean.TYPE);
        } catch (NoSuchMethodException unused2) {
            Log.i("ListPopupWindow", "Could not find method getMaxAvailableHeight(View, int, boolean) on PopupWindow. Oh well.");
        }
        try {
            C = PopupWindow.class.getDeclaredMethod("setEpicenterBounds", Rect.class);
        } catch (NoSuchMethodException unused3) {
            Log.i("ListPopupWindow", "Could not find method setEpicenterBounds(Rect) on PopupWindow. Oh well.");
        }
    }

    public k0(Context context, AttributeSet attributeSet, int i2) {
        this.f1268b = context;
        this.f1287v = new Handler(context.getMainLooper());
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, k.j.ListPopupWindow, i2, 0);
        this.f1273g = typedArrayObtainStyledAttributes.getDimensionPixelOffset(k.j.ListPopupWindow_android_dropDownHorizontalOffset, 0);
        int dimensionPixelOffset = typedArrayObtainStyledAttributes.getDimensionPixelOffset(k.j.ListPopupWindow_android_dropDownVerticalOffset, 0);
        this.f1274h = dimensionPixelOffset;
        if (dimensionPixelOffset != 0) {
            this.f1276j = true;
        }
        typedArrayObtainStyledAttributes.recycle();
        p pVar = new p(context, attributeSet, i2, 0);
        x0 x0VarT = x0.t(context, attributeSet, k.j.PopupWindow, i2);
        if (x0VarT.q(k.j.PopupWindow_overlapAnchor)) {
            android.support.v4.widget.f.a(pVar, x0VarT.a(k.j.PopupWindow_overlapAnchor, false));
        }
        pVar.setBackgroundDrawable(x0VarT.f(k.j.PopupWindow_android_popupBackground));
        x0VarT.u();
        this.f1291z = pVar;
        pVar.setInputMethodMode(1);
    }

    @Override // o.b
    public void a() {
        int i2;
        int iIntValue;
        int paddingBottom;
        f0 f0Var;
        f0 f0Var2 = this.f1270d;
        PopupWindow popupWindow = this.f1291z;
        Context context = this.f1268b;
        if (f0Var2 == null) {
            f0 f0VarD = d(context, !this.f1290y);
            this.f1270d = f0VarD;
            f0VarD.setAdapter(this.f1269c);
            this.f1270d.setOnItemClickListener(this.f1283q);
            this.f1270d.setFocusable(true);
            this.f1270d.setFocusableInTouchMode(true);
            this.f1270d.setOnItemSelectedListener(new j0(this));
            this.f1270d.setOnScrollListener(this.f1285t);
            popupWindow.setContentView(this.f1270d);
        }
        Drawable background = popupWindow.getBackground();
        Rect rect = this.f1288w;
        if (background != null) {
            background.getPadding(rect);
            int i3 = rect.top;
            i2 = rect.bottom + i3;
            if (!this.f1276j) {
                this.f1274h = -i3;
            }
        } else {
            rect.setEmpty();
            i2 = 0;
        }
        boolean z2 = popupWindow.getInputMethodMode() == 2;
        View view = this.f1282p;
        int i4 = this.f1274h;
        Method method = B;
        if (method != null) {
            try {
                iIntValue = ((Integer) method.invoke(popupWindow, view, Integer.valueOf(i4), Boolean.valueOf(z2))).intValue();
            } catch (Exception unused) {
                Log.i("ListPopupWindow", "Could not call getMaxAvailableHeightMethod(View, int, boolean) on PopupWindow. Using the public version.");
                iIntValue = popupWindow.getMaxAvailableHeight(view, i4);
            }
        } else {
            iIntValue = popupWindow.getMaxAvailableHeight(view, i4);
        }
        int i5 = this.f1271e;
        if (i5 == -1) {
            paddingBottom = iIntValue + i2;
        } else {
            int i6 = this.f1272f;
            int iA = this.f1270d.a(i6 != -2 ? i6 != -1 ? View.MeasureSpec.makeMeasureSpec(i6, 1073741824) : View.MeasureSpec.makeMeasureSpec(context.getResources().getDisplayMetrics().widthPixels - (rect.left + rect.right), 1073741824) : View.MeasureSpec.makeMeasureSpec(context.getResources().getDisplayMetrics().widthPixels - (rect.left + rect.right), Integer.MIN_VALUE), iIntValue);
            paddingBottom = iA + (iA > 0 ? this.f1270d.getPaddingBottom() + this.f1270d.getPaddingTop() + i2 : 0);
        }
        boolean z3 = this.f1291z.getInputMethodMode() == 2;
        android.support.v4.widget.f.b(popupWindow, this.f1275i);
        if (popupWindow.isShowing()) {
            if (this.f1282p.isAttachedToWindow()) {
                int width = this.f1272f;
                if (width == -1) {
                    width = -1;
                } else if (width == -2) {
                    width = this.f1282p.getWidth();
                }
                if (i5 == -1) {
                    i5 = z3 ? paddingBottom : -1;
                    if (z3) {
                        popupWindow.setWidth(this.f1272f == -1 ? -1 : 0);
                        popupWindow.setHeight(0);
                    } else {
                        popupWindow.setWidth(this.f1272f == -1 ? -1 : 0);
                        popupWindow.setHeight(-1);
                    }
                } else if (i5 == -2) {
                    i5 = paddingBottom;
                }
                popupWindow.setOutsideTouchable(true);
                View view2 = this.f1282p;
                int i7 = this.f1273g;
                int i8 = this.f1274h;
                if (width < 0) {
                    width = -1;
                }
                popupWindow.update(view2, i7, i8, width, i5 < 0 ? -1 : i5);
                return;
            }
            return;
        }
        int width2 = this.f1272f;
        if (width2 == -1) {
            width2 = -1;
        } else if (width2 == -2) {
            width2 = this.f1282p.getWidth();
        }
        if (i5 == -1) {
            i5 = -1;
        } else if (i5 == -2) {
            i5 = paddingBottom;
        }
        popupWindow.setWidth(width2);
        popupWindow.setHeight(i5);
        Method method2 = A;
        if (method2 != null) {
            try {
                method2.invoke(popupWindow, Boolean.TRUE);
            } catch (Exception unused2) {
                Log.i("ListPopupWindow", "Could not call setClipToScreenEnabled() on PopupWindow. Oh well.");
            }
        }
        popupWindow.setOutsideTouchable(true);
        popupWindow.setTouchInterceptor(this.s);
        if (this.f1278l) {
            android.support.v4.widget.f.a(popupWindow, this.f1277k);
        }
        Method method3 = C;
        if (method3 != null) {
            try {
                method3.invoke(popupWindow, this.f1289x);
            } catch (Exception e2) {
                Log.e("ListPopupWindow", "Could not invoke setEpicenterBounds on PopupWindow", e2);
            }
        }
        android.support.v4.widget.f.c(popupWindow, this.f1282p, this.f1273g, this.f1274h, this.f1279m);
        this.f1270d.setSelection(-1);
        if ((!this.f1290y || this.f1270d.isInTouchMode()) && (f0Var = this.f1270d) != null) {
            f0Var.setListSelectionHidden(true);
            f0Var.requestLayout();
        }
        if (this.f1290y) {
            return;
        }
        this.f1287v.post(this.f1286u);
    }

    f0 d(Context context, boolean z2) {
        return new f0(context, z2);
    }

    @Override // o.b
    public final void dismiss() {
        PopupWindow popupWindow = this.f1291z;
        popupWindow.dismiss();
        popupWindow.setContentView(null);
        this.f1270d = null;
        this.f1287v.removeCallbacks(this.f1284r);
    }

    public final int e() {
        return this.f1273g;
    }

    @Override // o.b
    public final ListView f() {
        return this.f1270d;
    }

    public final int g() {
        if (this.f1276j) {
            return this.f1274h;
        }
        return 0;
    }

    public final int h() {
        return this.f1272f;
    }

    public final boolean i() {
        return this.f1290y;
    }

    public void j(ListAdapter listAdapter) {
        DataSetObserver dataSetObserver = this.f1281o;
        if (dataSetObserver == null) {
            this.f1281o = new b();
        } else {
            ListAdapter listAdapter2 = this.f1269c;
            if (listAdapter2 != null) {
                listAdapter2.unregisterDataSetObserver(dataSetObserver);
            }
        }
        this.f1269c = listAdapter;
        if (listAdapter != null) {
            listAdapter.registerDataSetObserver(this.f1281o);
        }
        f0 f0Var = this.f1270d;
        if (f0Var != null) {
            f0Var.setAdapter(this.f1269c);
        }
    }

    @Override // o.b
    public final boolean k() {
        return this.f1291z.isShowing();
    }

    public final void l(View view) {
        this.f1282p = view;
    }

    public final void m() {
        this.f1291z.setAnimationStyle(0);
    }

    public final void n(int i2) {
        Drawable background = this.f1291z.getBackground();
        if (background == null) {
            this.f1272f = i2;
            return;
        }
        Rect rect = this.f1288w;
        background.getPadding(rect);
        this.f1272f = rect.left + rect.right + i2;
    }

    public final void o(int i2) {
        this.f1279m = i2;
    }

    public final void p(Rect rect) {
        this.f1289x = rect;
    }

    public final void q(int i2) {
        this.f1273g = i2;
    }

    public final void r() {
        this.f1291z.setInputMethodMode(2);
    }

    public final void s() {
        this.f1290y = true;
        this.f1291z.setFocusable(true);
    }

    public final void t(PopupWindow.OnDismissListener onDismissListener) {
        this.f1291z.setOnDismissListener(onDismissListener);
    }

    public final void u(AdapterView.OnItemClickListener onItemClickListener) {
        this.f1283q = onItemClickListener;
    }

    public final void v() {
        this.f1278l = true;
        this.f1277k = true;
    }

    public final void w(int i2) {
        this.f1274h = i2;
        this.f1276j = true;
    }
}
