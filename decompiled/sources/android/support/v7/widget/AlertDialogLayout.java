package android.support.v7.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.i0;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class AlertDialogLayout extends i0 {
    public AlertDialogLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    private static int k(View view) {
        int minimumHeight = view.getMinimumHeight();
        if (minimumHeight > 0) {
            return minimumHeight;
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            if (viewGroup.getChildCount() == 1) {
                return k(viewGroup.getChildAt(0));
            }
        }
        return 0;
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x009e  */
    @Override // android.support.v7.widget.i0, android.view.ViewGroup, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    protected final void onLayout(boolean z2, int i2, int i3, int i4, int i5) {
        int i6;
        int i7;
        int i8;
        int paddingLeft = getPaddingLeft();
        int i9 = i4 - i2;
        int paddingRight = i9 - getPaddingRight();
        int paddingRight2 = (i9 - paddingLeft) - getPaddingRight();
        int measuredHeight = getMeasuredHeight();
        int childCount = getChildCount();
        int gravity = getGravity();
        int i10 = gravity & 112;
        int i11 = gravity & 8388615;
        int paddingTop = i10 != 16 ? i10 != 80 ? getPaddingTop() : ((getPaddingTop() + i5) - i3) - measuredHeight : (((i5 - i3) - measuredHeight) / 2) + getPaddingTop();
        Drawable dividerDrawable = getDividerDrawable();
        int intrinsicHeight = dividerDrawable == null ? 0 : dividerDrawable.getIntrinsicHeight();
        for (int i12 = 0; i12 < childCount; i12++) {
            View childAt = getChildAt(i12);
            if (childAt != null && childAt.getVisibility() != 8) {
                int measuredWidth = childAt.getMeasuredWidth();
                int measuredHeight2 = childAt.getMeasuredHeight();
                i0.a aVar = (i0.a) childAt.getLayoutParams();
                int i13 = aVar.f1264b;
                if (i13 < 0) {
                    i13 = i11;
                }
                int absoluteGravity = Gravity.getAbsoluteGravity(i13, getLayoutDirection()) & 7;
                if (absoluteGravity == 1) {
                    i6 = ((paddingRight2 - measuredWidth) / 2) + paddingLeft + ((ViewGroup.MarginLayoutParams) aVar).leftMargin;
                    i7 = ((ViewGroup.MarginLayoutParams) aVar).rightMargin;
                } else if (absoluteGravity != 5) {
                    i8 = ((ViewGroup.MarginLayoutParams) aVar).leftMargin + paddingLeft;
                    if (j(i12)) {
                        paddingTop += intrinsicHeight;
                    }
                    int i14 = paddingTop + ((ViewGroup.MarginLayoutParams) aVar).topMargin;
                    childAt.layout(i8, i14, measuredWidth + i8, i14 + measuredHeight2);
                    paddingTop = measuredHeight2 + ((ViewGroup.MarginLayoutParams) aVar).bottomMargin + i14;
                } else {
                    i6 = paddingRight - measuredWidth;
                    i7 = ((ViewGroup.MarginLayoutParams) aVar).rightMargin;
                }
                i8 = i6 - i7;
                if (j(i12)) {
                }
                int i142 = paddingTop + ((ViewGroup.MarginLayoutParams) aVar).topMargin;
                childAt.layout(i8, i142, measuredWidth + i8, i142 + measuredHeight2);
                paddingTop = measuredHeight2 + ((ViewGroup.MarginLayoutParams) aVar).bottomMargin + i142;
            }
        }
    }

    @Override // android.support.v7.widget.i0, android.view.View
    protected final void onMeasure(int i2, int i3) {
        int iCombineMeasuredStates;
        int iK;
        int measuredHeight;
        int measuredHeight2;
        int childCount = getChildCount();
        View view = null;
        View view2 = null;
        View view3 = null;
        for (int i4 = 0; i4 < childCount; i4++) {
            View childAt = getChildAt(i4);
            if (childAt.getVisibility() != 8) {
                int id = childAt.getId();
                if (id == k.f.topPanel) {
                    view = childAt;
                } else if (id == k.f.buttonPanel) {
                    view2 = childAt;
                } else {
                    if ((id != k.f.contentPanel && id != k.f.customPanel) || view3 != null) {
                        super.onMeasure(i2, i3);
                        return;
                    }
                    view3 = childAt;
                }
            }
        }
        int mode = View.MeasureSpec.getMode(i3);
        int size = View.MeasureSpec.getSize(i3);
        int mode2 = View.MeasureSpec.getMode(i2);
        int paddingBottom = getPaddingBottom() + getPaddingTop();
        if (view != null) {
            view.measure(i2, 0);
            paddingBottom += view.getMeasuredHeight();
            iCombineMeasuredStates = View.combineMeasuredStates(0, view.getMeasuredState());
        } else {
            iCombineMeasuredStates = 0;
        }
        if (view2 != null) {
            view2.measure(i2, 0);
            iK = k(view2);
            measuredHeight = view2.getMeasuredHeight() - iK;
            paddingBottom += iK;
            iCombineMeasuredStates = View.combineMeasuredStates(iCombineMeasuredStates, view2.getMeasuredState());
        } else {
            iK = 0;
            measuredHeight = 0;
        }
        if (view3 != null) {
            view3.measure(i2, mode == 0 ? 0 : View.MeasureSpec.makeMeasureSpec(Math.max(0, size - paddingBottom), mode));
            measuredHeight2 = view3.getMeasuredHeight();
            paddingBottom += measuredHeight2;
            iCombineMeasuredStates = View.combineMeasuredStates(iCombineMeasuredStates, view3.getMeasuredState());
        } else {
            measuredHeight2 = 0;
        }
        int i5 = size - paddingBottom;
        if (view2 != null) {
            int i6 = paddingBottom - iK;
            int iMin = Math.min(i5, measuredHeight);
            if (iMin > 0) {
                i5 -= iMin;
                iK += iMin;
            }
            view2.measure(i2, View.MeasureSpec.makeMeasureSpec(iK, 1073741824));
            paddingBottom = i6 + view2.getMeasuredHeight();
            iCombineMeasuredStates = View.combineMeasuredStates(iCombineMeasuredStates, view2.getMeasuredState());
        }
        if (view3 != null && i5 > 0) {
            view3.measure(i2, View.MeasureSpec.makeMeasureSpec(measuredHeight2 + i5, mode));
            paddingBottom = (paddingBottom - measuredHeight2) + view3.getMeasuredHeight();
            iCombineMeasuredStates = View.combineMeasuredStates(iCombineMeasuredStates, view3.getMeasuredState());
        }
        int iMax = 0;
        for (int i7 = 0; i7 < childCount; i7++) {
            View childAt2 = getChildAt(i7);
            if (childAt2.getVisibility() != 8) {
                iMax = Math.max(iMax, childAt2.getMeasuredWidth());
            }
        }
        setMeasuredDimension(View.resolveSizeAndState(getPaddingRight() + getPaddingLeft() + iMax, i2, iCombineMeasuredStates), View.resolveSizeAndState(paddingBottom, i3, 0));
        if (mode2 != 1073741824) {
            int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 1073741824);
            for (int i8 = 0; i8 < childCount; i8++) {
                View childAt3 = getChildAt(i8);
                if (childAt3.getVisibility() != 8) {
                    i0.a aVar = (i0.a) childAt3.getLayoutParams();
                    if (((ViewGroup.MarginLayoutParams) aVar).width == -1) {
                        int i9 = ((ViewGroup.MarginLayoutParams) aVar).height;
                        ((ViewGroup.MarginLayoutParams) aVar).height = childAt3.getMeasuredHeight();
                        measureChildWithMargins(childAt3, iMakeMeasureSpec, 0, i3, 0);
                        ((ViewGroup.MarginLayoutParams) aVar).height = i9;
                    }
                }
            }
        }
    }
}
