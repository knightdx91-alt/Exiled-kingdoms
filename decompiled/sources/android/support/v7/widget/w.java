package android.support.v7.widget;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.ThemedSpinnerAdapter;

/* JADX INFO: compiled from: AppCompatSpinner.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class w extends Spinner {

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private static final int[] f1360j = {R.attr.spinnerMode};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final d f1361b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final n.d f1362c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private h0 f1363d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private SpinnerAdapter f1364e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final boolean f1365f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private b f1366g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private int f1367h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private final Rect f1368i;

    /* JADX INFO: compiled from: AppCompatSpinner.java */
    private static class a implements ListAdapter, SpinnerAdapter {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private SpinnerAdapter f1369b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private ListAdapter f1370c;

        public a(SpinnerAdapter spinnerAdapter, Resources.Theme theme) {
            this.f1369b = spinnerAdapter;
            if (spinnerAdapter instanceof ListAdapter) {
                this.f1370c = (ListAdapter) spinnerAdapter;
            }
            if (theme != null) {
                if (spinnerAdapter instanceof ThemedSpinnerAdapter) {
                    ThemedSpinnerAdapter themedSpinnerAdapter = (ThemedSpinnerAdapter) spinnerAdapter;
                    if (themedSpinnerAdapter.getDropDownViewTheme() != theme) {
                        themedSpinnerAdapter.setDropDownViewTheme(theme);
                        return;
                    }
                    return;
                }
                if (spinnerAdapter instanceof t0) {
                    t0 t0Var = (t0) spinnerAdapter;
                    if (t0Var.getDropDownViewTheme() == null) {
                        t0Var.b();
                    }
                }
            }
        }

        @Override // android.widget.ListAdapter
        public final boolean areAllItemsEnabled() {
            ListAdapter listAdapter = this.f1370c;
            if (listAdapter != null) {
                return listAdapter.areAllItemsEnabled();
            }
            return true;
        }

        @Override // android.widget.Adapter
        public final int getCount() {
            SpinnerAdapter spinnerAdapter = this.f1369b;
            if (spinnerAdapter == null) {
                return 0;
            }
            return spinnerAdapter.getCount();
        }

        @Override // android.widget.SpinnerAdapter
        public final View getDropDownView(int i2, View view, ViewGroup viewGroup) {
            SpinnerAdapter spinnerAdapter = this.f1369b;
            if (spinnerAdapter == null) {
                return null;
            }
            return spinnerAdapter.getDropDownView(i2, view, viewGroup);
        }

        @Override // android.widget.Adapter
        public final Object getItem(int i2) {
            SpinnerAdapter spinnerAdapter = this.f1369b;
            if (spinnerAdapter == null) {
                return null;
            }
            return spinnerAdapter.getItem(i2);
        }

        @Override // android.widget.Adapter
        public final long getItemId(int i2) {
            SpinnerAdapter spinnerAdapter = this.f1369b;
            if (spinnerAdapter == null) {
                return -1L;
            }
            return spinnerAdapter.getItemId(i2);
        }

        @Override // android.widget.Adapter
        public final int getItemViewType(int i2) {
            return 0;
        }

        @Override // android.widget.Adapter
        public final View getView(int i2, View view, ViewGroup viewGroup) {
            return getDropDownView(i2, view, viewGroup);
        }

        @Override // android.widget.Adapter
        public final int getViewTypeCount() {
            return 1;
        }

        @Override // android.widget.Adapter
        public final boolean hasStableIds() {
            SpinnerAdapter spinnerAdapter = this.f1369b;
            return spinnerAdapter != null && spinnerAdapter.hasStableIds();
        }

        @Override // android.widget.Adapter
        public final boolean isEmpty() {
            return getCount() == 0;
        }

        @Override // android.widget.ListAdapter
        public final boolean isEnabled(int i2) {
            ListAdapter listAdapter = this.f1370c;
            if (listAdapter != null) {
                return listAdapter.isEnabled(i2);
            }
            return true;
        }

        @Override // android.widget.Adapter
        public final void registerDataSetObserver(DataSetObserver dataSetObserver) {
            SpinnerAdapter spinnerAdapter = this.f1369b;
            if (spinnerAdapter != null) {
                spinnerAdapter.registerDataSetObserver(dataSetObserver);
            }
        }

        @Override // android.widget.Adapter
        public final void unregisterDataSetObserver(DataSetObserver dataSetObserver) {
            SpinnerAdapter spinnerAdapter = this.f1369b;
            if (spinnerAdapter != null) {
                spinnerAdapter.unregisterDataSetObserver(dataSetObserver);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: AppCompatSpinner.java */
    class b extends k0 {
        private CharSequence D;
        ListAdapter E;
        private final Rect F;

        /* JADX INFO: compiled from: AppCompatSpinner.java */
        final class a implements ViewTreeObserver.OnGlobalLayoutListener {
            a() {
            }

            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public final void onGlobalLayout() {
                b bVar = b.this;
                if (!bVar.A(w.this)) {
                    bVar.dismiss();
                } else {
                    bVar.y();
                    b.super.a();
                }
            }
        }

        /* JADX INFO: renamed from: android.support.v7.widget.w$b$b, reason: collision with other inner class name */
        /* JADX INFO: compiled from: AppCompatSpinner.java */
        final class C0017b implements PopupWindow.OnDismissListener {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ ViewTreeObserver.OnGlobalLayoutListener f1372b;

            C0017b(ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener) {
                this.f1372b = onGlobalLayoutListener;
            }

            @Override // android.widget.PopupWindow.OnDismissListener
            public final void onDismiss() {
                ViewTreeObserver viewTreeObserver = w.this.getViewTreeObserver();
                if (viewTreeObserver != null) {
                    viewTreeObserver.removeGlobalOnLayoutListener(this.f1372b);
                }
            }
        }

        public b(n.d dVar, AttributeSet attributeSet, int i2) {
            super(dVar, attributeSet, i2);
            this.F = new Rect();
            l(w.this);
            s();
            u(new x(this));
        }

        final boolean A(w wVar) {
            return wVar.isAttachedToWindow() && wVar.getGlobalVisibleRect(this.F);
        }

        public final void B(CharSequence charSequence) {
            this.D = charSequence;
        }

        @Override // android.support.v7.widget.k0, o.b
        public final void a() {
            ViewTreeObserver viewTreeObserver;
            PopupWindow popupWindow = this.f1291z;
            boolean zIsShowing = popupWindow.isShowing();
            y();
            r();
            super.a();
            this.f1270d.setChoiceMode(1);
            w wVar = w.this;
            int selectedItemPosition = wVar.getSelectedItemPosition();
            f0 f0Var = this.f1270d;
            if (popupWindow.isShowing() && f0Var != null) {
                f0Var.setListSelectionHidden(false);
                f0Var.setSelection(selectedItemPosition);
                if (f0Var.getChoiceMode() != 0) {
                    f0Var.setItemChecked(selectedItemPosition, true);
                }
            }
            if (zIsShowing || (viewTreeObserver = wVar.getViewTreeObserver()) == null) {
                return;
            }
            a aVar = new a();
            viewTreeObserver.addOnGlobalLayoutListener(aVar);
            t(new C0017b(aVar));
        }

        @Override // android.support.v7.widget.k0
        public final void j(ListAdapter listAdapter) {
            super.j(listAdapter);
            this.E = listAdapter;
        }

        final void y() {
            int i2;
            PopupWindow popupWindow = this.f1291z;
            Drawable background = popupWindow.getBackground();
            w wVar = w.this;
            if (background != null) {
                background.getPadding(wVar.f1368i);
                int i3 = b1.f1197b;
                i2 = wVar.getLayoutDirection() == 1 ? wVar.f1368i.right : -wVar.f1368i.left;
            } else {
                Rect rect = wVar.f1368i;
                wVar.f1368i.right = 0;
                rect.left = 0;
                i2 = 0;
            }
            int paddingLeft = wVar.getPaddingLeft();
            int paddingRight = wVar.getPaddingRight();
            int width = wVar.getWidth();
            if (wVar.f1367h == -2) {
                int iD = wVar.d((SpinnerAdapter) this.E, popupWindow.getBackground());
                int i4 = (wVar.getContext().getResources().getDisplayMetrics().widthPixels - wVar.f1368i.left) - wVar.f1368i.right;
                if (iD > i4) {
                    iD = i4;
                }
                n(Math.max(iD, (width - paddingLeft) - paddingRight));
            } else if (wVar.f1367h == -1) {
                n((width - paddingLeft) - paddingRight);
            } else {
                n(wVar.f1367h);
            }
            int i5 = b1.f1197b;
            q(wVar.getLayoutDirection() == 1 ? ((width - paddingRight) - h()) + i2 : i2 + paddingLeft);
        }

        public final CharSequence z() {
            return this.D;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x005b  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0096  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00a2  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00bb  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public w(Context context, AttributeSet attributeSet, int i2) throws Throwable {
        CharSequence[] charSequenceArrP;
        SpinnerAdapter spinnerAdapter;
        TypedArray typedArrayObtainStyledAttributes;
        super(context, attributeSet, i2);
        this.f1368i = new Rect();
        x0 x0VarT = x0.t(context, attributeSet, k.j.Spinner, i2);
        this.f1361b = new d(this);
        int iM = x0VarT.m(k.j.Spinner_popupTheme, 0);
        TypedArray typedArray = null;
        if (iM != 0) {
            this.f1362c = new n.d(context, iM);
        } else {
            this.f1362c = null;
        }
        if (this.f1362c != null) {
            int i3 = -1;
            try {
                try {
                    typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, f1360j, i2, 0);
                    try {
                        if (typedArrayObtainStyledAttributes.hasValue(0)) {
                            i3 = typedArrayObtainStyledAttributes.getInt(0, 0);
                        }
                    } catch (Exception e2) {
                        e = e2;
                        Log.i("AppCompatSpinner", "Could not read android:spinnerMode", e);
                        if (typedArrayObtainStyledAttributes != null) {
                        }
                        if (i3 == 1) {
                        }
                        charSequenceArrP = x0VarT.p(k.j.Spinner_android_entries);
                        if (charSequenceArrP != null) {
                        }
                        x0VarT.u();
                        this.f1365f = true;
                        spinnerAdapter = this.f1364e;
                        if (spinnerAdapter != null) {
                        }
                        this.f1361b.d(attributeSet, i2);
                    }
                } catch (Throwable th) {
                    th = th;
                    typedArray = typedArrayObtainStyledAttributes;
                    if (typedArray != null) {
                        typedArray.recycle();
                    }
                    throw th;
                }
            } catch (Exception e3) {
                e = e3;
                typedArrayObtainStyledAttributes = null;
            } catch (Throwable th2) {
                th = th2;
                if (typedArray != null) {
                }
                throw th;
            }
            typedArrayObtainStyledAttributes.recycle();
            if (i3 == 1) {
                b bVar = new b(this.f1362c, attributeSet, i2);
                x0 x0VarT2 = x0.t(this.f1362c, attributeSet, k.j.Spinner, i2);
                this.f1367h = x0VarT2.l(k.j.Spinner_android_dropDownWidth, -2);
                bVar.f1291z.setBackgroundDrawable(x0VarT2.f(k.j.Spinner_android_popupBackground));
                bVar.B(x0VarT.n(k.j.Spinner_android_prompt));
                x0VarT2.u();
                this.f1366g = bVar;
                this.f1363d = new v(this, this, bVar);
            }
        }
        charSequenceArrP = x0VarT.p(k.j.Spinner_android_entries);
        if (charSequenceArrP != null) {
            ArrayAdapter arrayAdapter = new ArrayAdapter(context, R.layout.simple_spinner_item, charSequenceArrP);
            arrayAdapter.setDropDownViewResource(k.g.support_simple_spinner_dropdown_item);
            setAdapter((SpinnerAdapter) arrayAdapter);
        }
        x0VarT.u();
        this.f1365f = true;
        spinnerAdapter = this.f1364e;
        if (spinnerAdapter != null) {
            setAdapter(spinnerAdapter);
            this.f1364e = null;
        }
        this.f1361b.d(attributeSet, i2);
    }

    final int d(SpinnerAdapter spinnerAdapter, Drawable drawable) {
        int i2 = 0;
        if (spinnerAdapter == null) {
            return 0;
        }
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 0);
        int iMakeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), 0);
        int iMax = Math.max(0, getSelectedItemPosition());
        int iMin = Math.min(spinnerAdapter.getCount(), iMax + 15);
        View view = null;
        int iMax2 = 0;
        for (int iMax3 = Math.max(0, iMax - (15 - (iMin - iMax))); iMax3 < iMin; iMax3++) {
            int itemViewType = spinnerAdapter.getItemViewType(iMax3);
            if (itemViewType != i2) {
                view = null;
                i2 = itemViewType;
            }
            view = spinnerAdapter.getView(iMax3, view, this);
            if (view.getLayoutParams() == null) {
                view.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
            }
            view.measure(iMakeMeasureSpec, iMakeMeasureSpec2);
            iMax2 = Math.max(iMax2, view.getMeasuredWidth());
        }
        if (drawable == null) {
            return iMax2;
        }
        Rect rect = this.f1368i;
        drawable.getPadding(rect);
        return iMax2 + rect.left + rect.right;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected final void drawableStateChanged() {
        super.drawableStateChanged();
        d dVar = this.f1361b;
        if (dVar != null) {
            dVar.a();
        }
    }

    @Override // android.widget.Spinner
    public int getDropDownHorizontalOffset() {
        b bVar = this.f1366g;
        return bVar != null ? bVar.e() : super.getDropDownHorizontalOffset();
    }

    @Override // android.widget.Spinner
    public int getDropDownVerticalOffset() {
        b bVar = this.f1366g;
        return bVar != null ? bVar.g() : super.getDropDownVerticalOffset();
    }

    @Override // android.widget.Spinner
    public int getDropDownWidth() {
        return this.f1366g != null ? this.f1367h : super.getDropDownWidth();
    }

    @Override // android.widget.Spinner
    public Drawable getPopupBackground() {
        b bVar = this.f1366g;
        return bVar != null ? bVar.f1291z.getBackground() : super.getPopupBackground();
    }

    @Override // android.widget.Spinner
    public Context getPopupContext() {
        return this.f1366g != null ? this.f1362c : super.getPopupContext();
    }

    @Override // android.widget.Spinner
    public CharSequence getPrompt() {
        b bVar = this.f1366g;
        return bVar != null ? bVar.z() : super.getPrompt();
    }

    public ColorStateList getSupportBackgroundTintList() {
        d dVar = this.f1361b;
        if (dVar != null) {
            return dVar.b();
        }
        return null;
    }

    public PorterDuff.Mode getSupportBackgroundTintMode() {
        d dVar = this.f1361b;
        if (dVar != null) {
            return dVar.c();
        }
        return null;
    }

    @Override // android.widget.Spinner, android.widget.AdapterView, android.view.ViewGroup, android.view.View
    protected final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        b bVar = this.f1366g;
        if (bVar == null || !bVar.f1291z.isShowing()) {
            return;
        }
        bVar.dismiss();
    }

    @Override // android.widget.Spinner, android.widget.AbsSpinner, android.view.View
    protected final void onMeasure(int i2, int i3) {
        super.onMeasure(i2, i3);
        if (this.f1366g == null || View.MeasureSpec.getMode(i2) != Integer.MIN_VALUE) {
            return;
        }
        setMeasuredDimension(Math.min(Math.max(getMeasuredWidth(), d(getAdapter(), getBackground())), View.MeasureSpec.getSize(i2)), getMeasuredHeight());
    }

    @Override // android.widget.Spinner, android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        h0 h0Var = this.f1363d;
        if (h0Var == null || !h0Var.onTouch(this, motionEvent)) {
            return super.onTouchEvent(motionEvent);
        }
        return true;
    }

    @Override // android.widget.Spinner, android.view.View
    public final boolean performClick() {
        b bVar = this.f1366g;
        if (bVar == null) {
            return super.performClick();
        }
        if (bVar.f1291z.isShowing()) {
            return true;
        }
        bVar.a();
        return true;
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        d dVar = this.f1361b;
        if (dVar != null) {
            dVar.e();
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i2) {
        super.setBackgroundResource(i2);
        d dVar = this.f1361b;
        if (dVar != null) {
            dVar.f(i2);
        }
    }

    @Override // android.widget.Spinner
    public void setDropDownHorizontalOffset(int i2) {
        b bVar = this.f1366g;
        if (bVar != null) {
            bVar.q(i2);
        } else {
            super.setDropDownHorizontalOffset(i2);
        }
    }

    @Override // android.widget.Spinner
    public void setDropDownVerticalOffset(int i2) {
        b bVar = this.f1366g;
        if (bVar != null) {
            bVar.w(i2);
        } else {
            super.setDropDownVerticalOffset(i2);
        }
    }

    @Override // android.widget.Spinner
    public void setDropDownWidth(int i2) {
        if (this.f1366g != null) {
            this.f1367h = i2;
        } else {
            super.setDropDownWidth(i2);
        }
    }

    @Override // android.widget.Spinner
    public void setPopupBackgroundDrawable(Drawable drawable) {
        b bVar = this.f1366g;
        if (bVar != null) {
            bVar.f1291z.setBackgroundDrawable(drawable);
        } else {
            super.setPopupBackgroundDrawable(drawable);
        }
    }

    @Override // android.widget.Spinner
    public void setPopupBackgroundResource(int i2) {
        setPopupBackgroundDrawable(l.a.a(getPopupContext(), i2));
    }

    @Override // android.widget.Spinner
    public void setPrompt(CharSequence charSequence) {
        b bVar = this.f1366g;
        if (bVar != null) {
            bVar.B(charSequence);
        } else {
            super.setPrompt(charSequence);
        }
    }

    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        d dVar = this.f1361b;
        if (dVar != null) {
            dVar.h(colorStateList);
        }
    }

    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        d dVar = this.f1361b;
        if (dVar != null) {
            dVar.i(mode);
        }
    }

    @Override // android.widget.AdapterView
    public void setAdapter(SpinnerAdapter spinnerAdapter) {
        if (!this.f1365f) {
            this.f1364e = spinnerAdapter;
            return;
        }
        super.setAdapter(spinnerAdapter);
        b bVar = this.f1366g;
        if (bVar != null) {
            Context context = this.f1362c;
            if (context == null) {
                context = getContext();
            }
            bVar.j(new a(spinnerAdapter, context.getTheme()));
        }
    }
}
