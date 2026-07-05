package android.support.v7.widget;

import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewParent;

/* JADX INFO: compiled from: ForwardingListener.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public abstract class h0 implements View.OnTouchListener, View.OnAttachStateChangeListener {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final float f1223b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final int f1224c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final int f1225d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    final View f1226e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private Runnable f1227f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private Runnable f1228g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private boolean f1229h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private int f1230i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private final int[] f1231j = new int[2];

    /* JADX INFO: compiled from: ForwardingListener.java */
    private class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            ViewParent parent = h0.this.f1226e.getParent();
            if (parent != null) {
                parent.requestDisallowInterceptTouchEvent(true);
            }
        }
    }

    /* JADX INFO: compiled from: ForwardingListener.java */
    private class b implements Runnable {
        b() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            h0.this.e();
        }
    }

    public h0(View view) {
        this.f1226e = view;
        view.setLongClickable(true);
        view.addOnAttachStateChangeListener(this);
        this.f1223b = ViewConfiguration.get(view.getContext()).getScaledTouchSlop();
        int tapTimeout = ViewConfiguration.getTapTimeout();
        this.f1224c = tapTimeout;
        this.f1225d = (ViewConfiguration.getLongPressTimeout() + tapTimeout) / 2;
    }

    private void a() {
        Runnable runnable = this.f1228g;
        View view = this.f1226e;
        if (runnable != null) {
            view.removeCallbacks(runnable);
        }
        Runnable runnable2 = this.f1227f;
        if (runnable2 != null) {
            view.removeCallbacks(runnable2);
        }
    }

    public abstract o.b b();

    protected abstract boolean c();

    protected boolean d() {
        o.b bVarB = b();
        if (bVarB == null || !bVarB.k()) {
            return true;
        }
        bVarB.dismiss();
        return true;
    }

    final void e() {
        a();
        View view = this.f1226e;
        if (view.isEnabled() && !view.isLongClickable() && c()) {
            view.getParent().requestDisallowInterceptTouchEvent(true);
            long jUptimeMillis = SystemClock.uptimeMillis();
            MotionEvent motionEventObtain = MotionEvent.obtain(jUptimeMillis, jUptimeMillis, 3, 0.0f, 0.0f, 0);
            view.onTouchEvent(motionEventObtain);
            motionEventObtain.recycle();
            this.f1229h = true;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x005e  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0064  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x00cd  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0100  */
    @Override // android.view.View.OnTouchListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final boolean onTouch(View view, MotionEvent motionEvent) {
        boolean z2;
        f0 f0Var;
        boolean z3 = this.f1229h;
        View view2 = this.f1226e;
        if (z3) {
            o.b bVarB = b();
            if (bVarB == null || !bVarB.k() || (f0Var = (f0) bVarB.f()) == null || !f0Var.isShown()) {
                z2 = !d();
            } else {
                MotionEvent motionEventObtainNoHistory = MotionEvent.obtainNoHistory(motionEvent);
                int[] iArr = this.f1231j;
                view2.getLocationOnScreen(iArr);
                motionEventObtainNoHistory.offsetLocation(iArr[0], iArr[1]);
                f0Var.getLocationOnScreen(iArr);
                motionEventObtainNoHistory.offsetLocation(-iArr[0], -iArr[1]);
                boolean zD = f0Var.d(motionEventObtainNoHistory, this.f1230i);
                motionEventObtainNoHistory.recycle();
                int actionMasked = motionEvent.getActionMasked();
                boolean z4 = (actionMasked == 1 || actionMasked == 3) ? false : true;
                if (!zD || !z4) {
                }
            }
        } else if (view2.isEnabled()) {
            int actionMasked2 = motionEvent.getActionMasked();
            if (actionMasked2 == 0) {
                this.f1230i = motionEvent.getPointerId(0);
                if (this.f1227f == null) {
                    this.f1227f = new a();
                }
                view2.postDelayed(this.f1227f, this.f1224c);
                if (this.f1228g == null) {
                    this.f1228g = new b();
                }
                view2.postDelayed(this.f1228g, this.f1225d);
            } else if (actionMasked2 == 1) {
                a();
            } else if (actionMasked2 == 2) {
                int iFindPointerIndex = motionEvent.findPointerIndex(this.f1230i);
                if (iFindPointerIndex >= 0) {
                    float x2 = motionEvent.getX(iFindPointerIndex);
                    float y2 = motionEvent.getY(iFindPointerIndex);
                    float f2 = this.f1223b;
                    float f3 = -f2;
                    if (x2 < f3 || y2 < f3 || x2 >= (view2.getRight() - view2.getLeft()) + f2 || y2 >= (view2.getBottom() - view2.getTop()) + f2) {
                        a();
                        view2.getParent().requestDisallowInterceptTouchEvent(true);
                        if (c()) {
                            z2 = true;
                        }
                        if (z2) {
                            long jUptimeMillis = SystemClock.uptimeMillis();
                            MotionEvent motionEventObtain = MotionEvent.obtain(jUptimeMillis, jUptimeMillis, 3, 0.0f, 0.0f, 0);
                            view2.onTouchEvent(motionEventObtain);
                            motionEventObtain.recycle();
                        }
                    }
                }
            } else if (actionMasked2 == 3) {
            }
            z2 = false;
            if (z2) {
            }
        } else {
            z2 = false;
            if (z2) {
            }
        }
        this.f1229h = z2;
        return z2 || z3;
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewAttachedToWindow(View view) {
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewDetachedFromWindow(View view) {
        this.f1229h = false;
        this.f1230i = -1;
        Runnable runnable = this.f1227f;
        if (runnable != null) {
            this.f1226e.removeCallbacks(runnable);
        }
    }
}
