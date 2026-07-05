package android.support.v7.widget;

import android.R;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.view.menu.o;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.Window;
import android.widget.OverScroller;
import com.google.android.gms.common.api.Api;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class ActionBarOverlayLayout extends ViewGroup implements c0 {
    static final int[] C = {k.a.actionBarSize, R.attr.windowContentOverlay};
    private final Runnable A;
    private final android.support.v4.view.f B;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f1041b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f1042c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private ContentFrameLayout f1043d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    ActionBarContainer f1044e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private d0 f1045f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private Drawable f1046g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private boolean f1047h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private boolean f1048i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private boolean f1049j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private boolean f1050k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    boolean f1051l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private int f1052m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private int f1053n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    private final Rect f1054o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    private final Rect f1055p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    private final Rect f1056q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    private final Rect f1057r;
    private final Rect s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    private final Rect f1058t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    private final Rect f1059u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    private d f1060v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    private OverScroller f1061w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    ViewPropertyAnimator f1062x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    final AnimatorListenerAdapter f1063y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    private final Runnable f1064z;

    final class a extends AnimatorListenerAdapter {
        a() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public final void onAnimationCancel(Animator animator) {
            ActionBarOverlayLayout actionBarOverlayLayout = ActionBarOverlayLayout.this;
            actionBarOverlayLayout.f1062x = null;
            actionBarOverlayLayout.f1051l = false;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public final void onAnimationEnd(Animator animator) {
            ActionBarOverlayLayout actionBarOverlayLayout = ActionBarOverlayLayout.this;
            actionBarOverlayLayout.f1062x = null;
            actionBarOverlayLayout.f1051l = false;
        }
    }

    final class b implements Runnable {
        b() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            ActionBarOverlayLayout actionBarOverlayLayout = ActionBarOverlayLayout.this;
            actionBarOverlayLayout.k();
            actionBarOverlayLayout.f1062x = actionBarOverlayLayout.f1044e.animate().translationY(0.0f).setListener(actionBarOverlayLayout.f1063y);
        }
    }

    final class c implements Runnable {
        c() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            ActionBarOverlayLayout actionBarOverlayLayout = ActionBarOverlayLayout.this;
            actionBarOverlayLayout.k();
            actionBarOverlayLayout.f1062x = actionBarOverlayLayout.f1044e.animate().translationY(-actionBarOverlayLayout.f1044e.getHeight()).setListener(actionBarOverlayLayout.f1063y);
        }
    }

    public interface d {
    }

    public static class e extends ViewGroup.MarginLayoutParams {
    }

    public ActionBarOverlayLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f1042c = 0;
        this.f1054o = new Rect();
        this.f1055p = new Rect();
        this.f1056q = new Rect();
        this.f1057r = new Rect();
        this.s = new Rect();
        this.f1058t = new Rect();
        this.f1059u = new Rect();
        this.f1063y = new a();
        this.f1064z = new b();
        this.A = new c();
        l(context);
        this.B = new android.support.v4.view.f();
    }

    private static boolean j(View view, Rect rect, boolean z2) {
        boolean z3;
        e eVar = (e) view.getLayoutParams();
        int i2 = ((ViewGroup.MarginLayoutParams) eVar).leftMargin;
        int i3 = rect.left;
        if (i2 != i3) {
            ((ViewGroup.MarginLayoutParams) eVar).leftMargin = i3;
            z3 = true;
        } else {
            z3 = false;
        }
        int i4 = ((ViewGroup.MarginLayoutParams) eVar).topMargin;
        int i5 = rect.top;
        if (i4 != i5) {
            ((ViewGroup.MarginLayoutParams) eVar).topMargin = i5;
            z3 = true;
        }
        int i6 = ((ViewGroup.MarginLayoutParams) eVar).rightMargin;
        int i7 = rect.right;
        if (i6 != i7) {
            ((ViewGroup.MarginLayoutParams) eVar).rightMargin = i7;
            z3 = true;
        }
        if (z2) {
            int i8 = ((ViewGroup.MarginLayoutParams) eVar).bottomMargin;
            int i9 = rect.bottom;
            if (i8 != i9) {
                ((ViewGroup.MarginLayoutParams) eVar).bottomMargin = i9;
                return true;
            }
        }
        return z3;
    }

    private void l(Context context) {
        TypedArray typedArrayObtainStyledAttributes = getContext().getTheme().obtainStyledAttributes(C);
        this.f1041b = typedArrayObtainStyledAttributes.getDimensionPixelSize(0, 0);
        Drawable drawable = typedArrayObtainStyledAttributes.getDrawable(1);
        this.f1046g = drawable;
        setWillNotDraw(drawable == null);
        typedArrayObtainStyledAttributes.recycle();
        this.f1047h = context.getApplicationInfo().targetSdkVersion < 19;
        this.f1061w = new OverScroller(context);
    }

    @Override // android.support.v7.widget.c0
    public final void a(android.support.v7.view.menu.h hVar, o.a aVar) {
        n();
        this.f1045f.a(hVar, aVar);
    }

    @Override // android.support.v7.widget.c0
    public final boolean b() {
        n();
        return this.f1045f.b();
    }

    @Override // android.support.v7.widget.c0
    public final void c() {
        n();
        this.f1045f.c();
    }

    @Override // android.view.ViewGroup
    protected final boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof e;
    }

    @Override // android.support.v7.widget.c0
    public final boolean d() {
        n();
        return this.f1045f.d();
    }

    @Override // android.view.View
    public final void draw(Canvas canvas) {
        int translationY;
        super.draw(canvas);
        if (this.f1046g == null || this.f1047h) {
            return;
        }
        if (this.f1044e.getVisibility() == 0) {
            translationY = (int) (this.f1044e.getTranslationY() + this.f1044e.getBottom() + 0.5f);
        } else {
            translationY = 0;
        }
        this.f1046g.setBounds(0, translationY, getWidth(), this.f1046g.getIntrinsicHeight() + translationY);
        this.f1046g.draw(canvas);
    }

    @Override // android.support.v7.widget.c0
    public final boolean e() {
        n();
        return this.f1045f.e();
    }

    @Override // android.support.v7.widget.c0
    public final boolean f() {
        n();
        return this.f1045f.f();
    }

    @Override // android.view.View
    protected final boolean fitSystemWindows(Rect rect) {
        n();
        getWindowSystemUiVisibility();
        boolean zJ = j(this.f1044e, rect, false);
        Rect rect2 = this.f1057r;
        rect2.set(rect);
        Rect rect3 = this.f1054o;
        b1.a(this, rect2, rect3);
        Rect rect4 = this.s;
        if (!rect4.equals(rect2)) {
            rect4.set(rect2);
            zJ = true;
        }
        Rect rect5 = this.f1055p;
        if (!rect5.equals(rect3)) {
            rect5.set(rect3);
            zJ = true;
        }
        if (zJ) {
            requestLayout();
        }
        return true;
    }

    @Override // android.support.v7.widget.c0
    public final boolean g() {
        n();
        return this.f1045f.g();
    }

    @Override // android.view.ViewGroup
    protected final ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new e(-1, -1);
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new e(getContext(), attributeSet);
    }

    public int getActionBarHideOffset() {
        ActionBarContainer actionBarContainer = this.f1044e;
        if (actionBarContainer != null) {
            return -((int) actionBarContainer.getTranslationY());
        }
        return 0;
    }

    @Override // android.view.ViewGroup
    public int getNestedScrollAxes() {
        return this.B.a();
    }

    public CharSequence getTitle() {
        n();
        return this.f1045f.getTitle();
    }

    @Override // android.support.v7.widget.c0
    public final void h(int i2) {
        n();
        if (i2 == 2) {
            this.f1045f.q();
        } else if (i2 == 5) {
            this.f1045f.r();
        } else {
            if (i2 != 109) {
                return;
            }
            setOverlayMode(true);
        }
    }

    @Override // android.support.v7.widget.c0
    public final void i() {
        n();
        this.f1045f.h();
    }

    final void k() {
        removeCallbacks(this.f1064z);
        removeCallbacks(this.A);
        ViewPropertyAnimator viewPropertyAnimator = this.f1062x;
        if (viewPropertyAnimator != null) {
            viewPropertyAnimator.cancel();
        }
    }

    public final boolean m() {
        return this.f1048i;
    }

    final void n() {
        d0 wrapper;
        if (this.f1043d == null) {
            this.f1043d = (ContentFrameLayout) findViewById(k.f.action_bar_activity_content);
            this.f1044e = (ActionBarContainer) findViewById(k.f.action_bar_container);
            KeyEvent.Callback callbackFindViewById = findViewById(k.f.action_bar);
            if (callbackFindViewById instanceof d0) {
                wrapper = (d0) callbackFindViewById;
            } else {
                if (!(callbackFindViewById instanceof Toolbar)) {
                    throw new IllegalStateException("Can't make a decor toolbar out of ".concat(callbackFindViewById.getClass().getSimpleName()));
                }
                wrapper = ((Toolbar) callbackFindViewById).getWrapper();
            }
            this.f1045f = wrapper;
        }
    }

    @Override // android.view.View
    protected final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        l(getContext());
        requestApplyInsets();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        k();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected final void onLayout(boolean z2, int i2, int i3, int i4, int i5) {
        int childCount = getChildCount();
        int paddingLeft = getPaddingLeft();
        getPaddingRight();
        int paddingTop = getPaddingTop();
        getPaddingBottom();
        for (int i6 = 0; i6 < childCount; i6++) {
            View childAt = getChildAt(i6);
            if (childAt.getVisibility() != 8) {
                e eVar = (e) childAt.getLayoutParams();
                int measuredWidth = childAt.getMeasuredWidth();
                int measuredHeight = childAt.getMeasuredHeight();
                int i7 = ((ViewGroup.MarginLayoutParams) eVar).leftMargin + paddingLeft;
                int i8 = ((ViewGroup.MarginLayoutParams) eVar).topMargin + paddingTop;
                childAt.layout(i7, i8, measuredWidth + i7, measuredHeight + i8);
            }
        }
    }

    @Override // android.view.View
    protected final void onMeasure(int i2, int i3) {
        n();
        measureChildWithMargins(this.f1044e, i2, 0, i3, 0);
        e eVar = (e) this.f1044e.getLayoutParams();
        int measuredHeight = 0;
        int iMax = Math.max(0, this.f1044e.getMeasuredWidth() + ((ViewGroup.MarginLayoutParams) eVar).leftMargin + ((ViewGroup.MarginLayoutParams) eVar).rightMargin);
        int iMax2 = Math.max(0, this.f1044e.getMeasuredHeight() + ((ViewGroup.MarginLayoutParams) eVar).topMargin + ((ViewGroup.MarginLayoutParams) eVar).bottomMargin);
        int iCombineMeasuredStates = View.combineMeasuredStates(0, this.f1044e.getMeasuredState());
        boolean z2 = (getWindowSystemUiVisibility() & 256) != 0;
        if (z2) {
            measuredHeight = this.f1041b;
            if (this.f1049j && this.f1044e.getTabContainer() != null) {
                measuredHeight += this.f1041b;
            }
        } else if (this.f1044e.getVisibility() != 8) {
            measuredHeight = this.f1044e.getMeasuredHeight();
        }
        Rect rect = this.f1054o;
        Rect rect2 = this.f1056q;
        rect2.set(rect);
        Rect rect3 = this.f1058t;
        rect3.set(this.f1057r);
        if (this.f1048i || z2) {
            rect3.top += measuredHeight;
            rect3.bottom = rect3.bottom;
        } else {
            rect2.top += measuredHeight;
            rect2.bottom = rect2.bottom;
        }
        j(this.f1043d, rect2, true);
        Rect rect4 = this.f1059u;
        if (!rect4.equals(rect3)) {
            rect4.set(rect3);
            this.f1043d.a(rect3);
        }
        measureChildWithMargins(this.f1043d, i2, 0, i3, 0);
        e eVar2 = (e) this.f1043d.getLayoutParams();
        int iMax3 = Math.max(iMax, this.f1043d.getMeasuredWidth() + ((ViewGroup.MarginLayoutParams) eVar2).leftMargin + ((ViewGroup.MarginLayoutParams) eVar2).rightMargin);
        int iMax4 = Math.max(iMax2, this.f1043d.getMeasuredHeight() + ((ViewGroup.MarginLayoutParams) eVar2).topMargin + ((ViewGroup.MarginLayoutParams) eVar2).bottomMargin);
        int iCombineMeasuredStates2 = View.combineMeasuredStates(iCombineMeasuredStates, this.f1043d.getMeasuredState());
        setMeasuredDimension(View.resolveSizeAndState(Math.max(getPaddingRight() + getPaddingLeft() + iMax3, getSuggestedMinimumWidth()), i2, iCombineMeasuredStates2), View.resolveSizeAndState(Math.max(getPaddingBottom() + getPaddingTop() + iMax4, getSuggestedMinimumHeight()), i3, iCombineMeasuredStates2 << 16));
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final boolean onNestedFling(View view, float f2, float f3, boolean z2) {
        if (!this.f1050k || !z2) {
            return false;
        }
        this.f1061w.fling(0, 0, 0, (int) f3, 0, 0, Integer.MIN_VALUE, Api.BaseClientBuilder.API_PRIORITY_OTHER);
        if (this.f1061w.getFinalY() > this.f1044e.getHeight()) {
            k();
            ((c) this.A).run();
        } else {
            k();
            ((b) this.f1064z).run();
        }
        this.f1051l = true;
        return true;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final boolean onNestedPreFling(View view, float f2, float f3) {
        return false;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void onNestedPreScroll(View view, int i2, int i3, int[] iArr) {
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void onNestedScroll(View view, int i2, int i3, int i4, int i5) {
        int i6 = this.f1052m + i3;
        this.f1052m = i6;
        setActionBarHideOffset(i6);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void onNestedScrollAccepted(View view, View view2, int i2) {
        this.B.b(i2);
        this.f1052m = getActionBarHideOffset();
        k();
        d dVar = this.f1060v;
        if (dVar != null) {
            ((android.support.v7.app.s) dVar).h();
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final boolean onStartNestedScroll(View view, View view2, int i2) {
        if ((i2 & 2) == 0 || this.f1044e.getVisibility() != 0) {
            return false;
        }
        return this.f1050k;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void onStopNestedScroll(View view) {
        if (!this.f1050k || this.f1051l) {
            return;
        }
        if (this.f1052m <= this.f1044e.getHeight()) {
            k();
            postDelayed(this.f1064z, 600L);
        } else {
            k();
            postDelayed(this.A, 600L);
        }
    }

    @Override // android.view.View
    public final void onWindowSystemUiVisibilityChanged(int i2) {
        super.onWindowSystemUiVisibilityChanged(i2);
        n();
        int i3 = this.f1053n ^ i2;
        this.f1053n = i2;
        boolean z2 = (i2 & 4) == 0;
        boolean z3 = (i2 & 256) != 0;
        d dVar = this.f1060v;
        if (dVar != null) {
            ((android.support.v7.app.s) dVar).c(!z3);
            if (z2 || !z3) {
                ((android.support.v7.app.s) this.f1060v).m();
            } else {
                ((android.support.v7.app.s) this.f1060v).e();
            }
        }
        if ((i3 & 256) == 0 || this.f1060v == null) {
            return;
        }
        requestApplyInsets();
    }

    @Override // android.view.View
    protected final void onWindowVisibilityChanged(int i2) {
        super.onWindowVisibilityChanged(i2);
        this.f1042c = i2;
        d dVar = this.f1060v;
        if (dVar != null) {
            ((android.support.v7.app.s) dVar).i(i2);
        }
    }

    public void setActionBarHideOffset(int i2) {
        k();
        this.f1044e.setTranslationY(-Math.max(0, Math.min(i2, this.f1044e.getHeight())));
    }

    public void setActionBarVisibilityCallback(d dVar) {
        this.f1060v = dVar;
        if (getWindowToken() != null) {
            ((android.support.v7.app.s) this.f1060v).i(this.f1042c);
            int i2 = this.f1053n;
            if (i2 != 0) {
                onWindowSystemUiVisibilityChanged(i2);
                requestApplyInsets();
            }
        }
    }

    public void setHasNonEmbeddedTabs(boolean z2) {
        this.f1049j = z2;
    }

    public void setHideOnContentScrollEnabled(boolean z2) {
        if (z2 != this.f1050k) {
            this.f1050k = z2;
            if (z2) {
                return;
            }
            k();
            setActionBarHideOffset(0);
        }
    }

    public void setIcon(int i2) {
        n();
        this.f1045f.setIcon(i2);
    }

    public void setLogo(int i2) {
        n();
        this.f1045f.o(i2);
    }

    public void setOverlayMode(boolean z2) {
        this.f1048i = z2;
        this.f1047h = z2 && getContext().getApplicationInfo().targetSdkVersion < 19;
    }

    public void setShowingForActionMode(boolean z2) {
    }

    public void setUiOptions(int i2) {
    }

    @Override // android.support.v7.widget.c0
    public void setWindowCallback(Window.Callback callback) {
        n();
        this.f1045f.setWindowCallback(callback);
    }

    @Override // android.support.v7.widget.c0
    public void setWindowTitle(CharSequence charSequence) {
        n();
        this.f1045f.setWindowTitle(charSequence);
    }

    @Override // android.view.ViewGroup
    public final boolean shouldDelayChildPressedState() {
        return false;
    }

    @Override // android.view.ViewGroup
    protected final ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new e(layoutParams);
    }

    public void setIcon(Drawable drawable) {
        n();
        this.f1045f.setIcon(drawable);
    }
}
