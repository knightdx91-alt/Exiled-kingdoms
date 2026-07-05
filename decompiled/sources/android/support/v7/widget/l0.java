package android.support.v7.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListAdapter;
import android.widget.ListView;
import java.lang.reflect.Field;

/* JADX INFO: compiled from: ListViewCompat.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class l0 extends ListView {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final Rect f1299b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    int f1300c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    int f1301d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    int f1302e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    int f1303f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    protected int f1304g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private Field f1305h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private a f1306i;

    /* JADX INFO: compiled from: ListViewCompat.java */
    private static class a extends m.a {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private boolean f1307c;

        public a(Drawable drawable) {
            super(drawable);
            this.f1307c = true;
        }

        final void b(boolean z2) {
            this.f1307c = z2;
        }

        @Override // m.a, android.graphics.drawable.Drawable
        public final void draw(Canvas canvas) {
            if (this.f1307c) {
                super.draw(canvas);
            }
        }

        @Override // m.a, android.graphics.drawable.Drawable
        public final void setHotspot(float f2, float f3) {
            if (this.f1307c) {
                super.setHotspot(f2, f3);
            }
        }

        @Override // m.a, android.graphics.drawable.Drawable
        public final void setHotspotBounds(int i2, int i3, int i4, int i5) {
            if (this.f1307c) {
                super.setHotspotBounds(i2, i3, i4, i5);
            }
        }

        @Override // m.a, android.graphics.drawable.Drawable
        public final boolean setState(int[] iArr) {
            if (this.f1307c) {
                return super.setState(iArr);
            }
            return false;
        }

        @Override // m.a, android.graphics.drawable.Drawable
        public final boolean setVisible(boolean z2, boolean z3) {
            if (this.f1307c) {
                return super.setVisible(z2, z3);
            }
            return false;
        }
    }

    public l0(Context context, int i2) {
        super(context, null, i2);
        this.f1299b = new Rect();
        this.f1300c = 0;
        this.f1301d = 0;
        this.f1302e = 0;
        this.f1303f = 0;
        try {
            Field declaredField = AbsListView.class.getDeclaredField("mIsChildViewEnabled");
            this.f1305h = declaredField;
            declaredField.setAccessible(true);
        } catch (NoSuchFieldException e2) {
            e2.printStackTrace();
        }
    }

    public final int a(int i2, int i3) {
        int listPaddingTop = getListPaddingTop();
        int listPaddingBottom = getListPaddingBottom();
        getListPaddingLeft();
        getListPaddingRight();
        int dividerHeight = getDividerHeight();
        Drawable divider = getDivider();
        ListAdapter adapter = getAdapter();
        if (adapter == null) {
            return listPaddingTop + listPaddingBottom;
        }
        int measuredHeight = listPaddingTop + listPaddingBottom;
        if (dividerHeight <= 0 || divider == null) {
            dividerHeight = 0;
        }
        int count = adapter.getCount();
        int i4 = 0;
        View view = null;
        for (int i5 = 0; i5 < count; i5++) {
            int itemViewType = adapter.getItemViewType(i5);
            if (itemViewType != i4) {
                view = null;
                i4 = itemViewType;
            }
            view = adapter.getView(i5, view, this);
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            if (layoutParams == null) {
                layoutParams = generateDefaultLayoutParams();
                view.setLayoutParams(layoutParams);
            }
            int i6 = layoutParams.height;
            view.measure(i2, i6 > 0 ? View.MeasureSpec.makeMeasureSpec(i6, 1073741824) : View.MeasureSpec.makeMeasureSpec(0, 0));
            view.forceLayout();
            if (i5 > 0) {
                measuredHeight += dividerHeight;
            }
            measuredHeight += view.getMeasuredHeight();
            if (measuredHeight >= i3) {
                return i3;
            }
        }
        return measuredHeight;
    }

    protected final void b(int i2, View view, float f2, float f3) {
        Drawable selector = getSelector();
        boolean z2 = (selector == null || i2 == -1) ? false : true;
        if (z2) {
            selector.setVisible(false, false);
        }
        Field field = this.f1305h;
        int left = view.getLeft();
        int top = view.getTop();
        int right = view.getRight();
        int bottom = view.getBottom();
        Rect rect = this.f1299b;
        rect.set(left, top, right, bottom);
        rect.left -= this.f1300c;
        rect.top -= this.f1301d;
        rect.right += this.f1302e;
        rect.bottom += this.f1303f;
        try {
            boolean z3 = field.getBoolean(this);
            if (view.isEnabled() != z3) {
                field.set(this, Boolean.valueOf(!z3));
                if (i2 != -1) {
                    refreshDrawableState();
                }
            }
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
        }
        if (z2) {
            float fExactCenterX = rect.exactCenterX();
            float fExactCenterY = rect.exactCenterY();
            selector.setVisible(getVisibility() == 0, false);
            selector.setHotspot(fExactCenterX, fExactCenterY);
        }
        Drawable selector2 = getSelector();
        if (selector2 == null || i2 == -1) {
            return;
        }
        selector2.setHotspot(f2, f3);
    }

    protected boolean c() {
        return false;
    }

    @Override // android.widget.ListView, android.widget.AbsListView, android.view.ViewGroup, android.view.View
    protected final void dispatchDraw(Canvas canvas) {
        Drawable selector;
        Rect rect = this.f1299b;
        if (!rect.isEmpty() && (selector = getSelector()) != null) {
            selector.setBounds(rect);
            selector.draw(canvas);
        }
        super.dispatchDraw(canvas);
    }

    @Override // android.widget.AbsListView, android.view.ViewGroup, android.view.View
    protected final void drawableStateChanged() {
        super.drawableStateChanged();
        setSelectorEnabled(true);
        Drawable selector = getSelector();
        if (selector != null && c() && isPressed()) {
            selector.setState(getDrawableState());
        }
    }

    @Override // android.widget.AbsListView, android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            this.f1304g = pointToPosition((int) motionEvent.getX(), (int) motionEvent.getY());
        }
        return super.onTouchEvent(motionEvent);
    }

    @Override // android.widget.AbsListView
    public void setSelector(Drawable drawable) {
        a aVar = drawable != null ? new a(drawable) : null;
        this.f1306i = aVar;
        super.setSelector(aVar);
        Rect rect = new Rect();
        if (drawable != null) {
            drawable.getPadding(rect);
        }
        this.f1300c = rect.left;
        this.f1301d = rect.top;
        this.f1302e = rect.right;
        this.f1303f = rect.bottom;
    }

    protected void setSelectorEnabled(boolean z2) {
        a aVar = this.f1306i;
        if (aVar != null) {
            aVar.b(z2);
        }
    }
}
