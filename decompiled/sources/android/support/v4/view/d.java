package android.support.v4.view;

import android.support.v4.widget.NestedScrollView;
import android.util.Log;
import android.view.View;
import android.view.ViewParent;

/* JADX INFO: compiled from: NestedScrollingChildHelper.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private ViewParent f599a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private ViewParent f600b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final NestedScrollView f601c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private boolean f602d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int[] f603e;

    public d(NestedScrollView nestedScrollView) {
        this.f601c = nestedScrollView;
    }

    private ViewParent e(int i2) {
        if (i2 == 0) {
            return this.f599a;
        }
        if (i2 != 1) {
            return null;
        }
        return this.f600b;
    }

    public final boolean a(float f2, float f3, boolean z2) {
        ViewParent viewParentE;
        if (!this.f602d || (viewParentE = e(0)) == null) {
            return false;
        }
        try {
            return viewParentE.onNestedFling(this.f601c, f2, f3, z2);
        } catch (AbstractMethodError e2) {
            Log.e("ViewParentCompat", "ViewParent " + viewParentE + " does not implement interface method onNestedFling", e2);
            return false;
        }
    }

    public final boolean b(float f2, float f3) {
        ViewParent viewParentE;
        if (!this.f602d || (viewParentE = e(0)) == null) {
            return false;
        }
        try {
            return viewParentE.onNestedPreFling(this.f601c, f2, f3);
        } catch (AbstractMethodError e2) {
            Log.e("ViewParentCompat", "ViewParent " + viewParentE + " does not implement interface method onNestedPreFling", e2);
            return false;
        }
    }

    public final boolean c(int i2, int i3, int[] iArr, int[] iArr2, int i4) {
        ViewParent viewParentE;
        int i5;
        int i6;
        if (!this.f602d || (viewParentE = e(i4)) == null) {
            return false;
        }
        if (i2 == 0 && i3 == 0) {
            if (iArr2 == null) {
                return false;
            }
            iArr2[0] = 0;
            iArr2[1] = 0;
            return false;
        }
        NestedScrollView nestedScrollView = this.f601c;
        if (iArr2 != null) {
            nestedScrollView.getLocationInWindow(iArr2);
            i5 = iArr2[0];
            i6 = iArr2[1];
        } else {
            i5 = 0;
            i6 = 0;
        }
        if (iArr == null) {
            if (this.f603e == null) {
                this.f603e = new int[2];
            }
            iArr = this.f603e;
        }
        iArr[0] = 0;
        iArr[1] = 0;
        if (viewParentE instanceof e) {
            ((e) viewParentE).c();
        } else if (i4 == 0) {
            try {
                viewParentE.onNestedPreScroll(nestedScrollView, i2, i3, iArr);
            } catch (AbstractMethodError e2) {
                Log.e("ViewParentCompat", "ViewParent " + viewParentE + " does not implement interface method onNestedPreScroll", e2);
            }
        }
        if (iArr2 != null) {
            nestedScrollView.getLocationInWindow(iArr2);
            iArr2[0] = iArr2[0] - i5;
            iArr2[1] = iArr2[1] - i6;
        }
        return (iArr[0] == 0 && iArr[1] == 0) ? false : true;
    }

    public final boolean d(int i2, int i3, int i4, int i5, int[] iArr, int i6) {
        ViewParent viewParentE;
        int i7;
        int i8;
        if (!this.f602d || (viewParentE = e(i6)) == null) {
            return false;
        }
        if (i2 == 0 && i3 == 0 && i4 == 0 && i5 == 0) {
            if (iArr != null) {
                iArr[0] = 0;
                iArr[1] = 0;
            }
            return false;
        }
        NestedScrollView nestedScrollView = this.f601c;
        if (iArr != null) {
            nestedScrollView.getLocationInWindow(iArr);
            i7 = iArr[0];
            i8 = iArr[1];
        } else {
            i7 = 0;
            i8 = 0;
        }
        if (viewParentE instanceof e) {
            ((e) viewParentE).e();
        } else if (i6 == 0) {
            try {
                viewParentE.onNestedScroll(nestedScrollView, i2, i3, i4, i5);
            } catch (AbstractMethodError e2) {
                Log.e("ViewParentCompat", "ViewParent " + viewParentE + " does not implement interface method onNestedScroll", e2);
            }
        }
        if (iArr != null) {
            nestedScrollView.getLocationInWindow(iArr);
            iArr[0] = iArr[0] - i7;
            iArr[1] = iArr[1] - i8;
        }
        return true;
    }

    public final boolean f(int i2) {
        return e(i2) != null;
    }

    public final boolean g() {
        return this.f602d;
    }

    public final void h(boolean z2) {
        if (this.f602d) {
            this.f601c.stopNestedScroll();
        }
        this.f602d = z2;
    }

    public final boolean i(int i2, int i3) {
        boolean zOnStartNestedScroll;
        if (f(i3)) {
            return true;
        }
        if (this.f602d) {
            View view = this.f601c;
            View view2 = view;
            for (ViewParent parent = view.getParent(); parent != null; parent = parent.getParent()) {
                boolean z2 = parent instanceof e;
                if (z2) {
                    zOnStartNestedScroll = ((e) parent).d();
                } else if (i3 == 0) {
                    try {
                        zOnStartNestedScroll = parent.onStartNestedScroll(view2, view, i2);
                    } catch (AbstractMethodError e2) {
                        Log.e("ViewParentCompat", "ViewParent " + parent + " does not implement interface method onStartNestedScroll", e2);
                        zOnStartNestedScroll = false;
                    }
                } else {
                    zOnStartNestedScroll = false;
                }
                if (zOnStartNestedScroll) {
                    if (i3 == 0) {
                        this.f599a = parent;
                    } else if (i3 == 1) {
                        this.f600b = parent;
                    }
                    if (z2) {
                        ((e) parent).b();
                    } else if (i3 == 0) {
                        try {
                            parent.onNestedScrollAccepted(view2, view, i2);
                        } catch (AbstractMethodError e3) {
                            Log.e("ViewParentCompat", "ViewParent " + parent + " does not implement interface method onNestedScrollAccepted", e3);
                        }
                    }
                    return true;
                }
                if (parent instanceof View) {
                    view2 = (View) parent;
                }
            }
        }
        return false;
    }

    public final void j(int i2) {
        ViewParent viewParentE = e(i2);
        if (viewParentE != null) {
            boolean z2 = viewParentE instanceof e;
            NestedScrollView nestedScrollView = this.f601c;
            if (z2) {
                ((e) viewParentE).a();
            } else if (i2 == 0) {
                try {
                    viewParentE.onStopNestedScroll(nestedScrollView);
                } catch (AbstractMethodError e2) {
                    Log.e("ViewParentCompat", "ViewParent " + viewParentE + " does not implement interface method onStopNestedScroll", e2);
                }
            }
            if (i2 == 0) {
                this.f599a = null;
            } else {
                if (i2 != 1) {
                    return;
                }
                this.f600b = null;
            }
        }
    }
}
