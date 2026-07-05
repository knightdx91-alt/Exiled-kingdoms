package android.support.v4.widget;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.view.h;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.FocusFinder;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.animation.AnimationUtils;
import android.widget.EdgeEffect;
import android.widget.FrameLayout;
import android.widget.OverScroller;
import android.widget.ScrollView;
import com.google.android.gms.auth.api.credentials.CredentialsApi;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.drive.MetadataChangeSet;
import java.util.ArrayList;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class NestedScrollView extends FrameLayout {
    private static final a A = new a();
    private static final int[] B = {R.attr.fillViewport};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private long f619b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Rect f620c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private OverScroller f621d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private EdgeEffect f622e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private EdgeEffect f623f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private int f624g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private boolean f625h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private boolean f626i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private View f627j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private boolean f628k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private VelocityTracker f629l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private boolean f630m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private boolean f631n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    private int f632o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    private int f633p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    private int f634q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    private int f635r;
    private final int[] s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    private final int[] f636t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    private int f637u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    private int f638v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    private SavedState f639w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    private final android.support.v4.view.f f640x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    private final android.support.v4.view.d f641y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    private float f642z;

    static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f643a;

        static class a implements Parcelable.Creator<SavedState> {
            @Override // android.os.Parcelable.Creator
            public final SavedState createFromParcel(Parcel parcel) {
                SavedState savedState = new SavedState(parcel);
                savedState.f643a = parcel.readInt();
                return savedState;
            }

            @Override // android.os.Parcelable.Creator
            public final SavedState[] newArray(int i2) {
                return new SavedState[i2];
            }
        }

        public final String toString() {
            StringBuilder sb = new StringBuilder("HorizontalScrollView.SavedState{");
            sb.append(Integer.toHexString(System.identityHashCode(this)));
            sb.append(" scrollPosition=");
            return a.a.p(sb, this.f643a, "}");
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i2) {
            super.writeToParcel(parcel, i2);
            parcel.writeInt(this.f643a);
        }
    }

    static class a extends android.support.v4.view.b {
        @Override // android.support.v4.view.b
        public final void c(View view, AccessibilityEvent accessibilityEvent) {
            super.c(view, accessibilityEvent);
            NestedScrollView nestedScrollView = (NestedScrollView) view;
            accessibilityEvent.setClassName(ScrollView.class.getName());
            accessibilityEvent.setScrollable(nestedScrollView.getScrollRange() > 0);
            accessibilityEvent.setScrollX(nestedScrollView.getScrollX());
            accessibilityEvent.setScrollY(nestedScrollView.getScrollY());
            accessibilityEvent.setMaxScrollX(nestedScrollView.getScrollX());
            accessibilityEvent.setMaxScrollY(nestedScrollView.getScrollRange());
        }

        @Override // android.support.v4.view.b
        public final void d(View view, j.a aVar) {
            int scrollRange;
            super.d(view, aVar);
            NestedScrollView nestedScrollView = (NestedScrollView) view;
            aVar.b("android.widget.ScrollView");
            if (!nestedScrollView.isEnabled() || (scrollRange = nestedScrollView.getScrollRange()) <= 0) {
                return;
            }
            aVar.c();
            if (nestedScrollView.getScrollY() > 0) {
                aVar.a(8192);
            }
            if (nestedScrollView.getScrollY() < scrollRange) {
                aVar.a(4096);
            }
        }

        @Override // android.support.v4.view.b
        public final boolean g(View view, int i2, Bundle bundle) {
            if (super.g(view, i2, bundle)) {
                return true;
            }
            NestedScrollView nestedScrollView = (NestedScrollView) view;
            if (!nestedScrollView.isEnabled()) {
                return false;
            }
            if (i2 == 4096) {
                int iMin = Math.min(nestedScrollView.getScrollY() + ((nestedScrollView.getHeight() - nestedScrollView.getPaddingBottom()) - nestedScrollView.getPaddingTop()), nestedScrollView.getScrollRange());
                if (iMin == nestedScrollView.getScrollY()) {
                    return false;
                }
                nestedScrollView.m(0 - nestedScrollView.getScrollX(), iMin - nestedScrollView.getScrollY());
                return true;
            }
            if (i2 != 8192) {
                return false;
            }
            int iMax = Math.max(nestedScrollView.getScrollY() - ((nestedScrollView.getHeight() - nestedScrollView.getPaddingBottom()) - nestedScrollView.getPaddingTop()), 0);
            if (iMax == nestedScrollView.getScrollY()) {
                return false;
            }
            nestedScrollView.m(0 - nestedScrollView.getScrollX(), iMax - nestedScrollView.getScrollY());
            return true;
        }
    }

    public interface b {
    }

    public NestedScrollView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0);
        this.f620c = new Rect();
        this.f625h = true;
        this.f626i = false;
        this.f627j = null;
        this.f628k = false;
        this.f631n = true;
        this.f635r = -1;
        this.s = new int[2];
        this.f636t = new int[2];
        this.f621d = new OverScroller(getContext());
        setFocusable(true);
        setDescendantFocusability(262144);
        setWillNotDraw(false);
        ViewConfiguration viewConfiguration = ViewConfiguration.get(getContext());
        this.f632o = viewConfiguration.getScaledTouchSlop();
        this.f633p = viewConfiguration.getScaledMinimumFlingVelocity();
        this.f634q = viewConfiguration.getScaledMaximumFlingVelocity();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, B, 0, 0);
        setFillViewport(typedArrayObtainStyledAttributes.getBoolean(0, false));
        typedArrayObtainStyledAttributes.recycle();
        this.f640x = new android.support.v4.view.f();
        this.f641y = new android.support.v4.view.d(this);
        setNestedScrollingEnabled(true);
        h.c(this, A);
    }

    private void c(int i2) {
        if (i2 != 0) {
            if (this.f631n) {
                m(0, i2);
            } else {
                scrollBy(0, i2);
            }
        }
    }

    private void d() {
        if (getOverScrollMode() == 2) {
            this.f622e = null;
            this.f623f = null;
        } else if (this.f622e == null) {
            Context context = getContext();
            this.f622e = new EdgeEffect(context);
            this.f623f = new EdgeEffect(context);
        }
    }

    private void f(int i2) {
        int scrollY = getScrollY();
        boolean z2 = (scrollY > 0 || i2 > 0) && (scrollY < getScrollRange() || i2 < 0);
        float f2 = i2;
        android.support.v4.view.d dVar = this.f641y;
        if (dVar.b(0.0f, f2)) {
            return;
        }
        dispatchNestedFling(0.0f, f2, z2);
        if (getChildCount() > 0) {
            dVar.i(2, 1);
            this.f621d.fling(getScrollX(), getScrollY(), 0, i2, 0, 0, Integer.MIN_VALUE, Api.BaseClientBuilder.API_PRIORITY_OTHER, 0, 0);
            this.f638v = getScrollY();
            postInvalidateOnAnimation();
        }
    }

    private float getVerticalScrollFactorCompat() {
        if (this.f642z == 0.0f) {
            TypedValue typedValue = new TypedValue();
            Context context = getContext();
            if (!context.getTheme().resolveAttribute(R.attr.listPreferredItemHeight, typedValue, true)) {
                throw new IllegalStateException("Expected theme to define listPreferredItemHeight.");
            }
            this.f642z = typedValue.getDimension(context.getResources().getDisplayMetrics());
        }
        return this.f642z;
    }

    private static boolean h(View view, NestedScrollView nestedScrollView) {
        if (view == nestedScrollView) {
            return true;
        }
        Object parent = view.getParent();
        return (parent instanceof ViewGroup) && h((View) parent, nestedScrollView);
    }

    private boolean i(View view, int i2, int i3) {
        Rect rect = this.f620c;
        view.getDrawingRect(rect);
        offsetDescendantRectToMyCoords(view, rect);
        return rect.bottom + i2 >= getScrollY() && rect.top - i2 <= getScrollY() + i3;
    }

    private void j(MotionEvent motionEvent) {
        int actionIndex = motionEvent.getActionIndex();
        if (motionEvent.getPointerId(actionIndex) == this.f635r) {
            int i2 = actionIndex == 0 ? 1 : 0;
            this.f624g = (int) motionEvent.getY(i2);
            this.f635r = motionEvent.getPointerId(i2);
            VelocityTracker velocityTracker = this.f629l;
            if (velocityTracker != null) {
                velocityTracker.clear();
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x0068  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private boolean l(int i2, int i3, int i4) {
        boolean z2;
        int height = getHeight();
        int scrollY = getScrollY();
        int i5 = height + scrollY;
        boolean z3 = i2 == 33;
        ArrayList<View> focusables = getFocusables(2);
        int size = focusables.size();
        View view = null;
        boolean z4 = false;
        for (int i6 = 0; i6 < size; i6++) {
            View view2 = focusables.get(i6);
            int top = view2.getTop();
            int bottom = view2.getBottom();
            if (i3 < bottom && top < i4) {
                boolean z5 = i3 < top && bottom < i4;
                if (view == null) {
                    view = view2;
                    z4 = z5;
                } else {
                    boolean z6 = (z3 && top < view.getTop()) || (!z3 && bottom > view.getBottom());
                    if (z4) {
                        if (z5 && z6) {
                            view = view2;
                        }
                    } else if (z5) {
                        view = view2;
                        z4 = true;
                    } else if (z6) {
                    }
                }
            }
        }
        if (view == null) {
            view = this;
        }
        if (i3 < scrollY || i4 > i5) {
            c(z3 ? i3 - scrollY : i4 - i5);
            z2 = true;
        } else {
            z2 = false;
        }
        if (view != findFocus()) {
            view.requestFocus(i2);
        }
        return z2;
    }

    public final boolean a(int i2) {
        View viewFindFocus = findFocus();
        if (viewFindFocus == this) {
            viewFindFocus = null;
        }
        View viewFindNextFocus = FocusFinder.getInstance().findNextFocus(this, viewFindFocus, i2);
        int maxScrollAmount = getMaxScrollAmount();
        if (viewFindNextFocus == null || !i(viewFindNextFocus, maxScrollAmount, getHeight())) {
            if (i2 == 33 && getScrollY() < maxScrollAmount) {
                maxScrollAmount = getScrollY();
            } else if (i2 == 130 && getChildCount() > 0) {
                int bottom = getChildAt(0).getBottom() - ((getHeight() + getScrollY()) - getPaddingBottom());
                if (bottom < maxScrollAmount) {
                    maxScrollAmount = bottom;
                }
            }
            if (maxScrollAmount == 0) {
                return false;
            }
            if (i2 != 130) {
                maxScrollAmount = -maxScrollAmount;
            }
            c(maxScrollAmount);
        } else {
            Rect rect = this.f620c;
            viewFindNextFocus.getDrawingRect(rect);
            offsetDescendantRectToMyCoords(viewFindNextFocus, rect);
            c(b(rect));
            viewFindNextFocus.requestFocus(i2);
        }
        if (viewFindFocus == null || !viewFindFocus.isFocused() || i(viewFindFocus, 0, getHeight())) {
            return true;
        }
        int descendantFocusability = getDescendantFocusability();
        setDescendantFocusability(MetadataChangeSet.INDEXABLE_TEXT_SIZE_LIMIT_BYTES);
        requestFocus();
        setDescendantFocusability(descendantFocusability);
        return true;
    }

    @Override // android.view.ViewGroup
    public final void addView(View view) {
        if (getChildCount() > 0) {
            throw new IllegalStateException("ScrollView can host only one direct child");
        }
        super.addView(view);
    }

    protected final int b(Rect rect) {
        if (getChildCount() == 0) {
            return 0;
        }
        int height = getHeight();
        int scrollY = getScrollY();
        int i2 = scrollY + height;
        int verticalFadingEdgeLength = getVerticalFadingEdgeLength();
        if (rect.top > 0) {
            scrollY += verticalFadingEdgeLength;
        }
        if (rect.bottom < getChildAt(0).getHeight()) {
            i2 -= verticalFadingEdgeLength;
        }
        int i3 = rect.bottom;
        if (i3 > i2 && rect.top > scrollY) {
            return Math.min(rect.height() > height ? rect.top - scrollY : rect.bottom - i2, getChildAt(0).getBottom() - i2);
        }
        if (rect.top >= scrollY || i3 >= i2) {
            return 0;
        }
        return Math.max(rect.height() > height ? 0 - (i2 - rect.bottom) : 0 - (scrollY - rect.top), -getScrollY());
    }

    @Override // android.view.View
    public final int computeHorizontalScrollExtent() {
        return super.computeHorizontalScrollExtent();
    }

    @Override // android.view.View
    public final int computeHorizontalScrollOffset() {
        return super.computeHorizontalScrollOffset();
    }

    @Override // android.view.View
    public final int computeHorizontalScrollRange() {
        return super.computeHorizontalScrollRange();
    }

    @Override // android.view.View
    public final void computeScroll() {
        int overScrollMode;
        if (!this.f621d.computeScrollOffset()) {
            android.support.v4.view.d dVar = this.f641y;
            if (dVar.f(1)) {
                dVar.j(1);
            }
            this.f638v = 0;
            return;
        }
        this.f621d.getCurrX();
        int currY = this.f621d.getCurrY();
        int i2 = currY - this.f638v;
        if (this.f641y.c(0, i2, this.f636t, null, 1)) {
            i2 -= this.f636t[1];
        }
        if (i2 != 0) {
            int scrollRange = getScrollRange();
            int scrollY = getScrollY();
            k(i2, getScrollX(), scrollY, scrollRange);
            int scrollY2 = getScrollY() - scrollY;
            if (!this.f641y.d(0, scrollY2, 0, i2 - scrollY2, null, 1) && ((overScrollMode = getOverScrollMode()) == 0 || (overScrollMode == 1 && scrollRange > 0))) {
                d();
                if (currY <= 0 && scrollY > 0) {
                    this.f622e.onAbsorb((int) this.f621d.getCurrVelocity());
                } else if (currY >= scrollRange && scrollY < scrollRange) {
                    this.f623f.onAbsorb((int) this.f621d.getCurrVelocity());
                }
            }
        }
        this.f638v = currY;
        postInvalidateOnAnimation();
    }

    @Override // android.view.View
    public final int computeVerticalScrollExtent() {
        return super.computeVerticalScrollExtent();
    }

    @Override // android.view.View
    public final int computeVerticalScrollOffset() {
        return Math.max(0, super.computeVerticalScrollOffset());
    }

    @Override // android.view.View
    public final int computeVerticalScrollRange() {
        int childCount = getChildCount();
        int height = (getHeight() - getPaddingBottom()) - getPaddingTop();
        if (childCount == 0) {
            return height;
        }
        int bottom = getChildAt(0).getBottom();
        int scrollY = getScrollY();
        int iMax = Math.max(0, bottom - height);
        return scrollY < 0 ? bottom - scrollY : scrollY > iMax ? bottom + (scrollY - iMax) : bottom;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return super.dispatchKeyEvent(keyEvent) || e(keyEvent);
    }

    @Override // android.view.View
    public final boolean dispatchNestedFling(float f2, float f3, boolean z2) {
        return this.f641y.a(f2, f3, z2);
    }

    @Override // android.view.View
    public final boolean dispatchNestedPreFling(float f2, float f3) {
        return this.f641y.b(f2, f3);
    }

    @Override // android.view.View
    public final boolean dispatchNestedPreScroll(int i2, int i3, int[] iArr, int[] iArr2) {
        return this.f641y.c(i2, i3, iArr, iArr2, 0);
    }

    @Override // android.view.View
    public final boolean dispatchNestedScroll(int i2, int i3, int i4, int i5, int[] iArr) {
        return this.f641y.d(i2, i3, i4, i5, iArr, 0);
    }

    @Override // android.view.View
    public final void draw(Canvas canvas) {
        super.draw(canvas);
        if (this.f622e != null) {
            int scrollY = getScrollY();
            if (!this.f622e.isFinished()) {
                int iSave = canvas.save();
                int width = (getWidth() - getPaddingLeft()) - getPaddingRight();
                canvas.translate(getPaddingLeft(), Math.min(0, scrollY));
                this.f622e.setSize(width, getHeight());
                if (this.f622e.draw(canvas)) {
                    postInvalidateOnAnimation();
                }
                canvas.restoreToCount(iSave);
            }
            if (this.f623f.isFinished()) {
                return;
            }
            int iSave2 = canvas.save();
            int width2 = (getWidth() - getPaddingLeft()) - getPaddingRight();
            int height = getHeight();
            canvas.translate(getPaddingLeft() + (-width2), Math.max(getScrollRange(), scrollY) + height);
            canvas.rotate(180.0f, width2, 0.0f);
            this.f623f.setSize(width2, height);
            if (this.f623f.draw(canvas)) {
                postInvalidateOnAnimation();
            }
            canvas.restoreToCount(iSave2);
        }
    }

    public final boolean e(KeyEvent keyEvent) {
        Rect rect = this.f620c;
        rect.setEmpty();
        View childAt = getChildAt(0);
        if (childAt != null) {
            if (getHeight() < getPaddingBottom() + getPaddingTop() + childAt.getHeight()) {
                if (keyEvent.getAction() != 0) {
                    return false;
                }
                int keyCode = keyEvent.getKeyCode();
                if (keyCode == 19) {
                    return !keyEvent.isAltPressed() ? a(33) : g(33);
                }
                if (keyCode == 20) {
                    return !keyEvent.isAltPressed() ? a(130) : g(130);
                }
                if (keyCode != 62) {
                    return false;
                }
                int i2 = keyEvent.isShiftPressed() ? 33 : 130;
                boolean z2 = i2 == 130;
                int height = getHeight();
                if (z2) {
                    rect.top = getScrollY() + height;
                    int childCount = getChildCount();
                    if (childCount > 0) {
                        View childAt2 = getChildAt(childCount - 1);
                        if (rect.top + height > childAt2.getBottom()) {
                            rect.top = childAt2.getBottom() - height;
                        }
                    }
                } else {
                    int scrollY = getScrollY() - height;
                    rect.top = scrollY;
                    if (scrollY < 0) {
                        rect.top = 0;
                    }
                }
                int i3 = rect.top;
                int i4 = height + i3;
                rect.bottom = i4;
                l(i2, i3, i4);
                return false;
            }
        }
        if (!isFocused() || keyEvent.getKeyCode() == 4) {
            return false;
        }
        View viewFindFocus = findFocus();
        if (viewFindFocus == this) {
            viewFindFocus = null;
        }
        View viewFindNextFocus = FocusFinder.getInstance().findNextFocus(this, viewFindFocus, 130);
        return (viewFindNextFocus == null || viewFindNextFocus == this || !viewFindNextFocus.requestFocus(130)) ? false : true;
    }

    public final boolean g(int i2) {
        int childCount;
        boolean z2 = i2 == 130;
        int height = getHeight();
        Rect rect = this.f620c;
        rect.top = 0;
        rect.bottom = height;
        if (z2 && (childCount = getChildCount()) > 0) {
            int paddingBottom = getPaddingBottom() + getChildAt(childCount - 1).getBottom();
            rect.bottom = paddingBottom;
            rect.top = paddingBottom - height;
        }
        return l(i2, rect.top, rect.bottom);
    }

    @Override // android.view.View
    protected float getBottomFadingEdgeStrength() {
        if (getChildCount() == 0) {
            return 0.0f;
        }
        int verticalFadingEdgeLength = getVerticalFadingEdgeLength();
        int bottom = (getChildAt(0).getBottom() - getScrollY()) - (getHeight() - getPaddingBottom());
        if (bottom < verticalFadingEdgeLength) {
            return bottom / verticalFadingEdgeLength;
        }
        return 1.0f;
    }

    public int getMaxScrollAmount() {
        return (int) (getHeight() * 0.5f);
    }

    @Override // android.view.ViewGroup
    public int getNestedScrollAxes() {
        return this.f640x.a();
    }

    int getScrollRange() {
        if (getChildCount() > 0) {
            return Math.max(0, getChildAt(0).getHeight() - ((getHeight() - getPaddingBottom()) - getPaddingTop()));
        }
        return 0;
    }

    @Override // android.view.View
    protected float getTopFadingEdgeStrength() {
        if (getChildCount() == 0) {
            return 0.0f;
        }
        int verticalFadingEdgeLength = getVerticalFadingEdgeLength();
        int scrollY = getScrollY();
        if (scrollY < verticalFadingEdgeLength) {
            return scrollY / verticalFadingEdgeLength;
        }
        return 1.0f;
    }

    @Override // android.view.View
    public final boolean hasNestedScrollingParent() {
        return this.f641y.f(0);
    }

    @Override // android.view.View
    public final boolean isNestedScrollingEnabled() {
        return this.f641y.g();
    }

    final boolean k(int i2, int i3, int i4, int i5) {
        boolean z2;
        boolean z3;
        getOverScrollMode();
        super.computeHorizontalScrollRange();
        super.computeHorizontalScrollExtent();
        computeVerticalScrollRange();
        super.computeVerticalScrollExtent();
        int i6 = i4 + i2;
        if (i3 <= 0 && i3 >= 0) {
            z2 = false;
        } else {
            i3 = 0;
            z2 = true;
        }
        if (i6 > i5) {
            z3 = true;
        } else if (i6 < 0) {
            i5 = 0;
            z3 = true;
        } else {
            i5 = i6;
            z3 = false;
        }
        if (z3 && !this.f641y.f(1)) {
            this.f621d.springBack(i3, i5, 0, 0, 0, getScrollRange());
        }
        super.scrollTo(i3, i5);
        return z2 || z3;
    }

    public final void m(int i2, int i3) {
        if (getChildCount() == 0) {
            return;
        }
        if (AnimationUtils.currentAnimationTimeMillis() - this.f619b > 250) {
            int iMax = Math.max(0, getChildAt(0).getHeight() - ((getHeight() - getPaddingBottom()) - getPaddingTop()));
            int scrollY = getScrollY();
            this.f621d.startScroll(getScrollX(), scrollY, 0, Math.max(0, Math.min(i3 + scrollY, iMax)) - scrollY);
            postInvalidateOnAnimation();
        } else {
            if (!this.f621d.isFinished()) {
                this.f621d.abortAnimation();
            }
            scrollBy(i2, i3);
        }
        this.f619b = AnimationUtils.currentAnimationTimeMillis();
    }

    @Override // android.view.ViewGroup
    protected final void measureChild(View view, int i2, int i3) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        view.measure(ViewGroup.getChildMeasureSpec(i2, getPaddingRight() + getPaddingLeft(), layoutParams.width), View.MeasureSpec.makeMeasureSpec(0, 0));
    }

    @Override // android.view.ViewGroup
    protected final void measureChildWithMargins(View view, int i2, int i3, int i4, int i5) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        view.measure(ViewGroup.getChildMeasureSpec(i2, getPaddingRight() + getPaddingLeft() + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin + i3, marginLayoutParams.width), View.MeasureSpec.makeMeasureSpec(marginLayoutParams.topMargin + marginLayoutParams.bottomMargin, 0));
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.f626i = false;
    }

    @Override // android.view.View
    public final boolean onGenericMotionEvent(MotionEvent motionEvent) {
        if ((motionEvent.getSource() & 2) != 0 && motionEvent.getAction() == 8 && !this.f628k) {
            float axisValue = motionEvent.getAxisValue(9);
            if (axisValue != 0.0f) {
                int verticalScrollFactorCompat = (int) (axisValue * getVerticalScrollFactorCompat());
                int scrollRange = getScrollRange();
                int scrollY = getScrollY();
                int i2 = scrollY - verticalScrollFactorCompat;
                if (i2 < 0) {
                    scrollRange = 0;
                } else if (i2 <= scrollRange) {
                    scrollRange = i2;
                }
                if (scrollRange != scrollY) {
                    super.scrollTo(getScrollX(), scrollRange);
                    return true;
                }
            }
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x0085  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x010f  */
    @Override // android.view.ViewGroup
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 2 && this.f628k) {
            return true;
        }
        int i2 = action & 255;
        android.support.v4.view.d dVar = this.f641y;
        if (i2 == 0) {
            int y2 = (int) motionEvent.getY();
            int x2 = (int) motionEvent.getX();
            if (getChildCount() > 0) {
                int scrollY = getScrollY();
                View childAt = getChildAt(0);
                if (y2 < childAt.getTop() - scrollY || y2 >= childAt.getBottom() - scrollY || x2 < childAt.getLeft() || x2 >= childAt.getRight()) {
                    this.f628k = false;
                    VelocityTracker velocityTracker = this.f629l;
                    if (velocityTracker != null) {
                        velocityTracker.recycle();
                        this.f629l = null;
                    }
                } else {
                    this.f624g = y2;
                    this.f635r = motionEvent.getPointerId(0);
                    VelocityTracker velocityTracker2 = this.f629l;
                    if (velocityTracker2 == null) {
                        this.f629l = VelocityTracker.obtain();
                    } else {
                        velocityTracker2.clear();
                    }
                    this.f629l.addMovement(motionEvent);
                    this.f621d.computeScrollOffset();
                    this.f628k = !this.f621d.isFinished();
                    dVar.i(2, 0);
                }
            }
        } else if (i2 == 1) {
            this.f628k = false;
            this.f635r = -1;
            VelocityTracker velocityTracker3 = this.f629l;
            if (velocityTracker3 != null) {
                velocityTracker3.recycle();
                this.f629l = null;
            }
            if (this.f621d.springBack(getScrollX(), getScrollY(), 0, 0, 0, getScrollRange())) {
                postInvalidateOnAnimation();
            }
            dVar.j(0);
        } else if (i2 == 2) {
            int i3 = this.f635r;
            if (i3 != -1) {
                int iFindPointerIndex = motionEvent.findPointerIndex(i3);
                if (iFindPointerIndex == -1) {
                    Log.e("NestedScrollView", "Invalid pointerId=" + i3 + " in onInterceptTouchEvent");
                } else {
                    int y3 = (int) motionEvent.getY(iFindPointerIndex);
                    if (Math.abs(y3 - this.f624g) > this.f632o && (2 & getNestedScrollAxes()) == 0) {
                        this.f628k = true;
                        this.f624g = y3;
                        if (this.f629l == null) {
                            this.f629l = VelocityTracker.obtain();
                        }
                        this.f629l.addMovement(motionEvent);
                        this.f637u = 0;
                        ViewParent parent = getParent();
                        if (parent != null) {
                            parent.requestDisallowInterceptTouchEvent(true);
                        }
                    }
                }
            }
        } else if (i2 != 3) {
            if (i2 == 6) {
                j(motionEvent);
            }
        }
        return this.f628k;
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    protected final void onLayout(boolean z2, int i2, int i3, int i4, int i5) {
        super.onLayout(z2, i2, i3, i4, i5);
        this.f625h = false;
        View view = this.f627j;
        if (view != null && h(view, this)) {
            View view2 = this.f627j;
            Rect rect = this.f620c;
            view2.getDrawingRect(rect);
            offsetDescendantRectToMyCoords(view2, rect);
            int iB = b(rect);
            if (iB != 0) {
                scrollBy(0, iB);
            }
        }
        this.f627j = null;
        if (!this.f626i) {
            if (this.f639w != null) {
                scrollTo(getScrollX(), this.f639w.f643a);
                this.f639w = null;
            }
            int iMax = Math.max(0, (getChildCount() > 0 ? getChildAt(0).getMeasuredHeight() : 0) - (((i5 - i3) - getPaddingBottom()) - getPaddingTop()));
            if (getScrollY() > iMax) {
                scrollTo(getScrollX(), iMax);
            } else if (getScrollY() < 0) {
                scrollTo(getScrollX(), 0);
            }
        }
        scrollTo(getScrollX(), getScrollY());
        this.f626i = true;
    }

    @Override // android.widget.FrameLayout, android.view.View
    protected final void onMeasure(int i2, int i3) {
        super.onMeasure(i2, i3);
        if (this.f630m && View.MeasureSpec.getMode(i3) != 0 && getChildCount() > 0) {
            View childAt = getChildAt(0);
            int measuredHeight = getMeasuredHeight();
            if (childAt.getMeasuredHeight() < measuredHeight) {
                FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) childAt.getLayoutParams();
                childAt.measure(ViewGroup.getChildMeasureSpec(i2, getPaddingRight() + getPaddingLeft(), layoutParams.width), View.MeasureSpec.makeMeasureSpec((measuredHeight - getPaddingTop()) - getPaddingBottom(), 1073741824));
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final boolean onNestedFling(View view, float f2, float f3, boolean z2) {
        if (z2) {
            return false;
        }
        f((int) f3);
        return true;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final boolean onNestedPreFling(View view, float f2, float f3) {
        return dispatchNestedPreFling(f2, f3);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void onNestedPreScroll(View view, int i2, int i3, int[] iArr) {
        dispatchNestedPreScroll(i2, i3, iArr, null);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void onNestedScroll(View view, int i2, int i3, int i4, int i5) {
        int scrollY = getScrollY();
        scrollBy(0, i5);
        int scrollY2 = getScrollY() - scrollY;
        dispatchNestedScroll(0, scrollY2, 0, i5 - scrollY2, null);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void onNestedScrollAccepted(View view, View view2, int i2) {
        this.f640x.b(i2);
        startNestedScroll(2);
    }

    @Override // android.view.View
    protected final void onOverScrolled(int i2, int i3, boolean z2, boolean z3) {
        super.scrollTo(i2, i3);
    }

    @Override // android.view.ViewGroup
    protected final boolean onRequestFocusInDescendants(int i2, Rect rect) {
        if (i2 == 2) {
            i2 = 130;
        } else if (i2 == 1) {
            i2 = 33;
        }
        View viewFindNextFocus = rect == null ? FocusFinder.getInstance().findNextFocus(this, null, i2) : FocusFinder.getInstance().findNextFocusFromRect(this, rect, i2);
        if (viewFindNextFocus != null && i(viewFindNextFocus, 0, getHeight())) {
            return viewFindNextFocus.requestFocus(i2, rect);
        }
        return false;
    }

    @Override // android.view.View
    protected final void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        this.f639w = savedState;
        requestLayout();
    }

    @Override // android.view.View
    protected final Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.f643a = getScrollY();
        return savedState;
    }

    @Override // android.view.View
    protected final void onScrollChanged(int i2, int i3, int i4, int i5) {
        super.onScrollChanged(i2, i3, i4, i5);
    }

    @Override // android.view.View
    protected final void onSizeChanged(int i2, int i3, int i4, int i5) {
        super.onSizeChanged(i2, i3, i4, i5);
        View viewFindFocus = findFocus();
        if (viewFindFocus == null || this == viewFindFocus || !i(viewFindFocus, 0, i5)) {
            return;
        }
        Rect rect = this.f620c;
        viewFindFocus.getDrawingRect(rect);
        offsetDescendantRectToMyCoords(viewFindFocus, rect);
        c(b(rect));
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final boolean onStartNestedScroll(View view, View view2, int i2) {
        return (i2 & 2) != 0;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void onStopNestedScroll(View view) {
        this.f640x.c();
        stopNestedScroll();
    }

    @Override // android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        ViewParent parent;
        if (this.f629l == null) {
            this.f629l = VelocityTracker.obtain();
        }
        MotionEvent motionEventObtain = MotionEvent.obtain(motionEvent);
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.f637u = 0;
        }
        motionEventObtain.offsetLocation(0.0f, this.f637u);
        android.support.v4.view.d dVar = this.f641y;
        if (actionMasked != 0) {
            if (actionMasked == 1) {
                VelocityTracker velocityTracker = this.f629l;
                velocityTracker.computeCurrentVelocity(CredentialsApi.ACTIVITY_RESULT_ADD_ACCOUNT, this.f634q);
                int yVelocity = (int) velocityTracker.getYVelocity(this.f635r);
                if (Math.abs(yVelocity) > this.f633p) {
                    f(-yVelocity);
                } else if (this.f621d.springBack(getScrollX(), getScrollY(), 0, 0, 0, getScrollRange())) {
                    postInvalidateOnAnimation();
                }
                this.f635r = -1;
                this.f628k = false;
                VelocityTracker velocityTracker2 = this.f629l;
                if (velocityTracker2 != null) {
                    velocityTracker2.recycle();
                    this.f629l = null;
                }
                dVar.j(0);
                EdgeEffect edgeEffect = this.f622e;
                if (edgeEffect != null) {
                    edgeEffect.onRelease();
                    this.f623f.onRelease();
                }
            } else if (actionMasked == 2) {
                int iFindPointerIndex = motionEvent.findPointerIndex(this.f635r);
                if (iFindPointerIndex == -1) {
                    Log.e("NestedScrollView", "Invalid pointerId=" + this.f635r + " in onTouchEvent");
                } else {
                    int y2 = (int) motionEvent.getY(iFindPointerIndex);
                    int i2 = this.f624g - y2;
                    boolean zC = this.f641y.c(0, i2, this.f636t, this.s, 0);
                    int[] iArr = this.s;
                    if (zC) {
                        i2 -= this.f636t[1];
                        motionEventObtain.offsetLocation(0.0f, iArr[1]);
                        this.f637u += iArr[1];
                    }
                    if (!this.f628k && Math.abs(i2) > this.f632o) {
                        ViewParent parent2 = getParent();
                        if (parent2 != null) {
                            parent2.requestDisallowInterceptTouchEvent(true);
                        }
                        this.f628k = true;
                        i2 = i2 > 0 ? i2 - this.f632o : i2 + this.f632o;
                    }
                    if (this.f628k) {
                        this.f624g = y2 - iArr[1];
                        int scrollY = getScrollY();
                        int scrollRange = getScrollRange();
                        int overScrollMode = getOverScrollMode();
                        boolean z2 = overScrollMode == 0 || (overScrollMode == 1 && scrollRange > 0);
                        if (k(i2, 0, getScrollY(), scrollRange) && !dVar.f(0)) {
                            this.f629l.clear();
                        }
                        int scrollY2 = getScrollY() - scrollY;
                        if (this.f641y.d(0, scrollY2, 0, i2 - scrollY2, this.s, 0)) {
                            int i3 = this.f624g;
                            int i4 = iArr[1];
                            this.f624g = i3 - i4;
                            motionEventObtain.offsetLocation(0.0f, i4);
                            this.f637u += iArr[1];
                        } else if (z2) {
                            d();
                            int i5 = scrollY + i2;
                            if (i5 < 0) {
                                this.f622e.onPull(i2 / getHeight(), motionEvent.getX(iFindPointerIndex) / getWidth());
                                if (!this.f623f.isFinished()) {
                                    this.f623f.onRelease();
                                }
                            } else if (i5 > scrollRange) {
                                this.f623f.onPull(i2 / getHeight(), 1.0f - (motionEvent.getX(iFindPointerIndex) / getWidth()));
                                if (!this.f622e.isFinished()) {
                                    this.f622e.onRelease();
                                }
                            }
                            EdgeEffect edgeEffect2 = this.f622e;
                            if (edgeEffect2 != null && (!edgeEffect2.isFinished() || !this.f623f.isFinished())) {
                                postInvalidateOnAnimation();
                            }
                        }
                    }
                }
            } else if (actionMasked == 3) {
                if (this.f628k && getChildCount() > 0 && this.f621d.springBack(getScrollX(), getScrollY(), 0, 0, 0, getScrollRange())) {
                    postInvalidateOnAnimation();
                }
                this.f635r = -1;
                this.f628k = false;
                VelocityTracker velocityTracker3 = this.f629l;
                if (velocityTracker3 != null) {
                    velocityTracker3.recycle();
                    this.f629l = null;
                }
                dVar.j(0);
                EdgeEffect edgeEffect3 = this.f622e;
                if (edgeEffect3 != null) {
                    edgeEffect3.onRelease();
                    this.f623f.onRelease();
                }
            } else if (actionMasked == 5) {
                int actionIndex = motionEvent.getActionIndex();
                this.f624g = (int) motionEvent.getY(actionIndex);
                this.f635r = motionEvent.getPointerId(actionIndex);
            } else if (actionMasked == 6) {
                j(motionEvent);
                this.f624g = (int) motionEvent.getY(motionEvent.findPointerIndex(this.f635r));
            }
        } else {
            if (getChildCount() == 0) {
                return false;
            }
            boolean zIsFinished = this.f621d.isFinished();
            this.f628k = !zIsFinished;
            if (!zIsFinished && (parent = getParent()) != null) {
                parent.requestDisallowInterceptTouchEvent(true);
            }
            if (!this.f621d.isFinished()) {
                this.f621d.abortAnimation();
            }
            this.f624g = (int) motionEvent.getY();
            this.f635r = motionEvent.getPointerId(0);
            dVar.i(2, 0);
        }
        VelocityTracker velocityTracker4 = this.f629l;
        if (velocityTracker4 != null) {
            velocityTracker4.addMovement(motionEventObtain);
        }
        motionEventObtain.recycle();
        return true;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void requestChildFocus(View view, View view2) {
        if (this.f625h) {
            this.f627j = view2;
        } else {
            Rect rect = this.f620c;
            view2.getDrawingRect(rect);
            offsetDescendantRectToMyCoords(view2, rect);
            int iB = b(rect);
            if (iB != 0) {
                scrollBy(0, iB);
            }
        }
        super.requestChildFocus(view, view2);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final boolean requestChildRectangleOnScreen(View view, Rect rect, boolean z2) {
        rect.offset(view.getLeft() - view.getScrollX(), view.getTop() - view.getScrollY());
        int iB = b(rect);
        boolean z3 = iB != 0;
        if (z3) {
            if (z2) {
                scrollBy(0, iB);
            } else {
                m(0, iB);
            }
        }
        return z3;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void requestDisallowInterceptTouchEvent(boolean z2) {
        VelocityTracker velocityTracker;
        if (z2 && (velocityTracker = this.f629l) != null) {
            velocityTracker.recycle();
            this.f629l = null;
        }
        super.requestDisallowInterceptTouchEvent(z2);
    }

    @Override // android.view.View, android.view.ViewParent
    public final void requestLayout() {
        this.f625h = true;
        super.requestLayout();
    }

    @Override // android.view.View
    public final void scrollTo(int i2, int i3) {
        if (getChildCount() > 0) {
            View childAt = getChildAt(0);
            int width = (getWidth() - getPaddingRight()) - getPaddingLeft();
            int width2 = childAt.getWidth();
            if (width >= width2 || i2 < 0) {
                i2 = 0;
            } else if (width + i2 > width2) {
                i2 = width2 - width;
            }
            int height = (getHeight() - getPaddingBottom()) - getPaddingTop();
            int height2 = childAt.getHeight();
            if (height >= height2 || i3 < 0) {
                i3 = 0;
            } else if (height + i3 > height2) {
                i3 = height2 - height;
            }
            if (i2 == getScrollX() && i3 == getScrollY()) {
                return;
            }
            super.scrollTo(i2, i3);
        }
    }

    public void setFillViewport(boolean z2) {
        if (z2 != this.f630m) {
            this.f630m = z2;
            requestLayout();
        }
    }

    @Override // android.view.View
    public void setNestedScrollingEnabled(boolean z2) {
        this.f641y.h(z2);
    }

    public void setOnScrollChangeListener(b bVar) {
    }

    public void setSmoothScrollingEnabled(boolean z2) {
        this.f631n = z2;
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup
    public final boolean shouldDelayChildPressedState() {
        return true;
    }

    @Override // android.view.View
    public final boolean startNestedScroll(int i2) {
        return this.f641y.i(i2, 0);
    }

    @Override // android.view.View
    public final void stopNestedScroll() {
        this.f641y.j(0);
    }

    @Override // android.view.ViewGroup
    public final void addView(View view, int i2) {
        if (getChildCount() <= 0) {
            super.addView(view, i2);
            return;
        }
        throw new IllegalStateException("ScrollView can host only one direct child");
    }

    @Override // android.view.ViewGroup, android.view.ViewManager
    public final void addView(View view, ViewGroup.LayoutParams layoutParams) {
        if (getChildCount() <= 0) {
            super.addView(view, layoutParams);
            return;
        }
        throw new IllegalStateException("ScrollView can host only one direct child");
    }

    @Override // android.view.ViewGroup
    public final void addView(View view, int i2, ViewGroup.LayoutParams layoutParams) {
        if (getChildCount() <= 0) {
            super.addView(view, i2, layoutParams);
            return;
        }
        throw new IllegalStateException("ScrollView can host only one direct child");
    }
}
