package android.support.v7.widget;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.widget.FrameLayout;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class ContentFrameLayout extends FrameLayout {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private TypedValue f1111b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private TypedValue f1112c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private TypedValue f1113d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private TypedValue f1114e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private TypedValue f1115f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private TypedValue f1116g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private final Rect f1117h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private a f1118i;

    public interface a {
        void onDetachedFromWindow();
    }

    public ContentFrameLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0);
        this.f1117h = new Rect();
    }

    public final void a(Rect rect) {
        fitSystemWindows(rect);
    }

    public final void b(int i2, int i3, int i4, int i5) {
        this.f1117h.set(i2, i3, i4, i5);
        if (isLaidOut()) {
            requestLayout();
        }
    }

    public TypedValue getFixedHeightMajor() {
        if (this.f1115f == null) {
            this.f1115f = new TypedValue();
        }
        return this.f1115f;
    }

    public TypedValue getFixedHeightMinor() {
        if (this.f1116g == null) {
            this.f1116g = new TypedValue();
        }
        return this.f1116g;
    }

    public TypedValue getFixedWidthMajor() {
        if (this.f1113d == null) {
            this.f1113d = new TypedValue();
        }
        return this.f1113d;
    }

    public TypedValue getFixedWidthMinor() {
        if (this.f1114e == null) {
            this.f1114e = new TypedValue();
        }
        return this.f1114e;
    }

    public TypedValue getMinWidthMajor() {
        if (this.f1111b == null) {
            this.f1111b = new TypedValue();
        }
        return this.f1111b;
    }

    public TypedValue getMinWidthMinor() {
        if (this.f1112c == null) {
            this.f1112c = new TypedValue();
        }
        return this.f1112c;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected final void onAttachedToWindow() {
        super.onAttachedToWindow();
        a aVar = this.f1118i;
        if (aVar != null) {
            aVar.getClass();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        a aVar = this.f1118i;
        if (aVar != null) {
            aVar.onDetachedFromWindow();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x004e  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0062  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x008a  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x009d  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x00d1  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00d9  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00de  */
    @Override // android.widget.FrameLayout, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    protected final void onMeasure(int i2, int i3) {
        int iMakeMeasureSpec;
        boolean z2;
        int iMakeMeasureSpec2;
        int i4;
        int i5;
        float fraction;
        int i6;
        int i7;
        float fraction2;
        int i8;
        int i9;
        float fraction3;
        DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();
        boolean z3 = true;
        boolean z4 = displayMetrics.widthPixels < displayMetrics.heightPixels;
        int mode = View.MeasureSpec.getMode(i2);
        int mode2 = View.MeasureSpec.getMode(i3);
        Rect rect = this.f1117h;
        if (mode != Integer.MIN_VALUE) {
            iMakeMeasureSpec = i2;
            z2 = false;
        } else {
            TypedValue typedValue = z4 ? this.f1114e : this.f1113d;
            if (typedValue != null && (i8 = typedValue.type) != 0) {
                if (i8 == 5) {
                    fraction3 = typedValue.getDimension(displayMetrics);
                } else if (i8 == 6) {
                    int i10 = displayMetrics.widthPixels;
                    fraction3 = typedValue.getFraction(i10, i10);
                } else {
                    i9 = 0;
                    if (i9 <= 0) {
                        iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(Math.min(i9 - (rect.left + rect.right), View.MeasureSpec.getSize(i2)), 1073741824);
                        z2 = true;
                    }
                }
                i9 = (int) fraction3;
                if (i9 <= 0) {
                }
            }
        }
        if (mode2 != Integer.MIN_VALUE) {
            iMakeMeasureSpec2 = i3;
        } else {
            TypedValue typedValue2 = z4 ? this.f1115f : this.f1116g;
            if (typedValue2 != null && (i6 = typedValue2.type) != 0) {
                if (i6 == 5) {
                    fraction2 = typedValue2.getDimension(displayMetrics);
                } else if (i6 == 6) {
                    int i11 = displayMetrics.heightPixels;
                    fraction2 = typedValue2.getFraction(i11, i11);
                } else {
                    i7 = 0;
                    if (i7 <= 0) {
                        iMakeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(Math.min(i7 - (rect.top + rect.bottom), View.MeasureSpec.getSize(i3)), 1073741824);
                    }
                }
                i7 = (int) fraction2;
                if (i7 <= 0) {
                }
            }
        }
        super.onMeasure(iMakeMeasureSpec, iMakeMeasureSpec2);
        int measuredWidth = getMeasuredWidth();
        int iMakeMeasureSpec3 = View.MeasureSpec.makeMeasureSpec(measuredWidth, 1073741824);
        if (z2 || mode != Integer.MIN_VALUE) {
            z3 = false;
        } else {
            TypedValue typedValue3 = z4 ? this.f1112c : this.f1111b;
            if (typedValue3 != null && (i4 = typedValue3.type) != 0) {
                if (i4 == 5) {
                    fraction = typedValue3.getDimension(displayMetrics);
                } else if (i4 == 6) {
                    int i12 = displayMetrics.widthPixels;
                    fraction = typedValue3.getFraction(i12, i12);
                } else {
                    i5 = 0;
                    if (i5 > 0) {
                        i5 -= rect.left + rect.right;
                    }
                    if (measuredWidth >= i5) {
                        iMakeMeasureSpec3 = View.MeasureSpec.makeMeasureSpec(i5, 1073741824);
                    }
                }
                i5 = (int) fraction;
                if (i5 > 0) {
                }
                if (measuredWidth >= i5) {
                }
            }
        }
        if (z3) {
            super.onMeasure(iMakeMeasureSpec3, iMakeMeasureSpec2);
        }
    }

    public void setAttachListener(a aVar) {
        this.f1118i = aVar;
    }
}
