package android.support.v7.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class ViewStubCompat extends View {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f1165b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f1166c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private WeakReference<View> f1167d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private LayoutInflater f1168e;

    public interface a {
    }

    public ViewStubCompat(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0);
        this.f1165b = 0;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, k.j.ViewStubCompat, 0, 0);
        this.f1166c = typedArrayObtainStyledAttributes.getResourceId(k.j.ViewStubCompat_android_inflatedId, -1);
        this.f1165b = typedArrayObtainStyledAttributes.getResourceId(k.j.ViewStubCompat_android_layout, 0);
        setId(typedArrayObtainStyledAttributes.getResourceId(k.j.ViewStubCompat_android_id, -1));
        typedArrayObtainStyledAttributes.recycle();
        setVisibility(8);
        setWillNotDraw(true);
    }

    public final View a() {
        ViewParent parent = getParent();
        if (parent == null || !(parent instanceof ViewGroup)) {
            throw new IllegalStateException("ViewStub must have a non-null ViewGroup viewParent");
        }
        if (this.f1165b == 0) {
            throw new IllegalArgumentException("ViewStub must have a valid layoutResource");
        }
        ViewGroup viewGroup = (ViewGroup) parent;
        LayoutInflater layoutInflaterFrom = this.f1168e;
        if (layoutInflaterFrom == null) {
            layoutInflaterFrom = LayoutInflater.from(getContext());
        }
        View viewInflate = layoutInflaterFrom.inflate(this.f1165b, viewGroup, false);
        int i2 = this.f1166c;
        if (i2 != -1) {
            viewInflate.setId(i2);
        }
        int iIndexOfChild = viewGroup.indexOfChild(this);
        viewGroup.removeViewInLayout(this);
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        if (layoutParams != null) {
            viewGroup.addView(viewInflate, iIndexOfChild, layoutParams);
        } else {
            viewGroup.addView(viewInflate, iIndexOfChild);
        }
        this.f1167d = new WeakReference<>(viewInflate);
        return viewInflate;
    }

    @Override // android.view.View
    protected final void dispatchDraw(Canvas canvas) {
    }

    @Override // android.view.View
    @SuppressLint({"MissingSuperCall"})
    public final void draw(Canvas canvas) {
    }

    public int getInflatedId() {
        return this.f1166c;
    }

    public LayoutInflater getLayoutInflater() {
        return this.f1168e;
    }

    public int getLayoutResource() {
        return this.f1165b;
    }

    @Override // android.view.View
    protected final void onMeasure(int i2, int i3) {
        setMeasuredDimension(0, 0);
    }

    public void setInflatedId(int i2) {
        this.f1166c = i2;
    }

    public void setLayoutInflater(LayoutInflater layoutInflater) {
        this.f1168e = layoutInflater;
    }

    public void setLayoutResource(int i2) {
        this.f1165b = i2;
    }

    public void setOnInflateListener(a aVar) {
    }

    @Override // android.view.View
    public void setVisibility(int i2) {
        WeakReference<View> weakReference = this.f1167d;
        if (weakReference != null) {
            View view = weakReference.get();
            if (view == null) {
                throw new IllegalStateException("setVisibility called on un-referenced view");
            }
            view.setVisibility(i2);
            return;
        }
        super.setVisibility(i2);
        if (i2 == 0 || i2 == 4) {
            a();
        }
    }
}
