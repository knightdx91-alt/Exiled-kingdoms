package android.support.v7.widget;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.util.TypedValue;

/* JADX INFO: compiled from: ThemeUtils.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class s0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final ThreadLocal<TypedValue> f1339a = new ThreadLocal<>();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    static final int[] f1340b = {-16842910};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    static final int[] f1341c = {R.attr.state_focused};

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    static final int[] f1342d = {R.attr.state_pressed};

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    static final int[] f1343e = {R.attr.state_checked};

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    static final int[] f1344f = new int[0];

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private static final int[] f1345g = new int[1];

    public static int a(Context context, int i2) {
        ColorStateList colorStateListC = c(context, i2);
        if (colorStateListC != null && colorStateListC.isStateful()) {
            return colorStateListC.getColorForState(f1340b, colorStateListC.getDefaultColor());
        }
        ThreadLocal<TypedValue> threadLocal = f1339a;
        TypedValue typedValue = threadLocal.get();
        if (typedValue == null) {
            typedValue = new TypedValue();
            threadLocal.set(typedValue);
        }
        context.getTheme().resolveAttribute(R.attr.disabledAlpha, typedValue, true);
        float f2 = typedValue.getFloat();
        int iB = b(context, i2);
        int iRound = Math.round(Color.alpha(iB) * f2);
        int i3 = f.a.f2198a;
        if (iRound < 0 || iRound > 255) {
            throw new IllegalArgumentException("alpha must be between 0 and 255.");
        }
        return (iB & 16777215) | (iRound << 24);
    }

    public static int b(Context context, int i2) {
        int[] iArr = f1345g;
        iArr[0] = i2;
        x0 x0VarS = x0.s(context, null, iArr);
        try {
            return x0VarS.b(0, 0);
        } finally {
            x0VarS.u();
        }
    }

    public static ColorStateList c(Context context, int i2) {
        int[] iArr = f1345g;
        iArr[0] = i2;
        x0 x0VarS = x0.s(context, null, iArr);
        try {
            return x0VarS.c(0);
        } finally {
            x0VarS.u();
        }
    }
}
