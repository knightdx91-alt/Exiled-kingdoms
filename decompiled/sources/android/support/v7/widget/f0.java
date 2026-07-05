package android.support.v7.widget;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;

/* JADX INFO: compiled from: DropDownListView.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
class f0 extends l0 {

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private boolean f1211j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private boolean f1212k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private boolean f1213l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private android.support.v4.widget.e f1214m;

    public f0(Context context, boolean z2) {
        super(context, k.a.dropDownListViewStyle);
        this.f1212k = z2;
        setCacheColorHint(0);
    }

    @Override // android.support.v7.widget.l0
    protected final boolean c() {
        return this.f1213l;
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x009e  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00b3  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00b8  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00ce  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0012  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public boolean d(MotionEvent motionEvent, int i2) {
        boolean z2;
        View childAt;
        boolean z3;
        View childAt2;
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 1) {
            z2 = false;
        } else {
            if (actionMasked != 2) {
                if (actionMasked != 3) {
                    z2 = true;
                    z3 = false;
                } else {
                    z3 = false;
                    z2 = false;
                }
                if (z2 || z3) {
                    this.f1213l = false;
                    setPressed(false);
                    drawableStateChanged();
                    childAt2 = getChildAt(this.f1304g - getFirstVisiblePosition());
                    if (childAt2 != null) {
                        childAt2.setPressed(false);
                    }
                }
                if (z2) {
                    android.support.v4.widget.e eVar = this.f1214m;
                    if (eVar != null) {
                        eVar.f(false);
                    }
                } else {
                    if (this.f1214m == null) {
                        this.f1214m = new android.support.v4.widget.e(this);
                    }
                    this.f1214m.f(true);
                    this.f1214m.onTouch(this, motionEvent);
                }
                return z2;
            }
            z2 = true;
        }
        int iFindPointerIndex = motionEvent.findPointerIndex(i2);
        if (iFindPointerIndex >= 0) {
            int x2 = (int) motionEvent.getX(iFindPointerIndex);
            int y2 = (int) motionEvent.getY(iFindPointerIndex);
            int iPointToPosition = pointToPosition(x2, y2);
            if (iPointToPosition == -1) {
                z3 = true;
            } else {
                View childAt3 = getChildAt(iPointToPosition - getFirstVisiblePosition());
                float f2 = x2;
                float f3 = y2;
                this.f1213l = true;
                drawableHotspotChanged(f2, f3);
                if (!isPressed()) {
                    setPressed(true);
                }
                layoutChildren();
                int i3 = this.f1304g;
                if (i3 != -1 && (childAt = getChildAt(i3 - getFirstVisiblePosition())) != null && childAt != childAt3 && childAt.isPressed()) {
                    childAt.setPressed(false);
                }
                this.f1304g = iPointToPosition;
                childAt3.drawableHotspotChanged(f2 - childAt3.getLeft(), f3 - childAt3.getTop());
                if (!childAt3.isPressed()) {
                    childAt3.setPressed(true);
                }
                b(iPointToPosition, childAt3, f2, f3);
                setSelectorEnabled(false);
                refreshDrawableState();
                if (actionMasked == 1) {
                    performItemClick(childAt3, iPointToPosition, getItemIdAtPosition(iPointToPosition));
                }
                z2 = true;
                z3 = false;
            }
        }
        if (z2) {
            this.f1213l = false;
            setPressed(false);
            drawableStateChanged();
            childAt2 = getChildAt(this.f1304g - getFirstVisiblePosition());
            if (childAt2 != null) {
            }
        }
        if (z2) {
        }
        return z2;
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean hasFocus() {
        return this.f1212k || super.hasFocus();
    }

    @Override // android.view.View
    public boolean hasWindowFocus() {
        return this.f1212k || super.hasWindowFocus();
    }

    @Override // android.view.View
    public boolean isFocused() {
        return this.f1212k || super.isFocused();
    }

    @Override // android.view.View
    public boolean isInTouchMode() {
        return (this.f1212k && this.f1211j) || super.isInTouchMode();
    }

    void setListSelectionHidden(boolean z2) {
        this.f1211j = z2;
    }
}
