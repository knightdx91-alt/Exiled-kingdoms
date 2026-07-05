package android.support.v7.widget;

import android.content.res.Configuration;
import android.support.v7.app.a;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.animation.DecelerateInterpolator;
import android.widget.AdapterView;
import android.widget.HorizontalScrollView;

/* JADX INFO: compiled from: ScrollingTabContainerView.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class q0 extends HorizontalScrollView implements AdapterView.OnItemSelectedListener {

    /* JADX INFO: compiled from: ScrollingTabContainerView.java */
    private class a extends i0 {
        @Override // android.support.v7.widget.i0, android.view.View
        public final void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
            super.onInitializeAccessibilityEvent(accessibilityEvent);
            accessibilityEvent.setClassName(a.c.class.getName());
        }

        @Override // android.support.v7.widget.i0, android.view.View
        public final void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
            super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
            accessibilityNodeInfo.setClassName(a.c.class.getName());
        }

        @Override // android.support.v7.widget.i0, android.view.View
        public final void onMeasure(int i2, int i3) {
            super.onMeasure(i2, i3);
            throw null;
        }

        @Override // android.view.View
        public final void setSelected(boolean z2) {
            boolean z3 = isSelected() != z2;
            super.setSelected(z2);
            if (z3 && z2) {
                sendAccessibilityEvent(4);
            }
        }
    }

    static {
        new DecelerateInterpolator();
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    @Override // android.view.View
    protected final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        n.a aVarB = n.a.b(getContext());
        setContentHeight(aVarB.f());
        aVarB.e();
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public final void onItemSelected(AdapterView<?> adapterView, View view, int i2, long j2) {
        ((a) view).getClass();
        throw null;
    }

    @Override // android.widget.HorizontalScrollView, android.widget.FrameLayout, android.view.View
    public final void onMeasure(int i2, int i3) {
        setFillViewport(View.MeasureSpec.getMode(i2) == 1073741824);
        throw null;
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public final void onNothingSelected(AdapterView<?> adapterView) {
    }

    public void setAllowCollapse(boolean z2) {
    }

    public void setContentHeight(int i2) {
        requestLayout();
    }

    public void setTabSelected(int i2) {
        throw null;
    }
}
