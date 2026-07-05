package com.badlogic.gdx.utils;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: compiled from: Scaling.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class g0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final g0 f1806a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final g0 f1807b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final g0 f1808c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static final a0.q f1809d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static final /* synthetic */ g0[] f1810e;

    static {
        g0 g0Var = new g0("fit", 0);
        f1806a = g0Var;
        g0 g0Var2 = new g0("fill", 1);
        g0 g0Var3 = new g0("fillX", 2);
        g0 g0Var4 = new g0("fillY", 3);
        g0 g0Var5 = new g0("stretch", 4);
        f1807b = g0Var5;
        g0 g0Var6 = new g0("stretchX", 5);
        g0 g0Var7 = new g0("stretchY", 6);
        g0 g0Var8 = new g0("none", 7);
        f1808c = g0Var8;
        f1810e = new g0[]{g0Var, g0Var2, g0Var3, g0Var4, g0Var5, g0Var6, g0Var7, g0Var8};
        f1809d = new a0.q();
    }

    private g0() {
        throw null;
    }

    public static g0 valueOf(String str) {
        return (g0) Enum.valueOf(g0.class, str);
    }

    public static g0[] values() {
        return (g0[]) f1810e.clone();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public final a0.q a(float f2, float f3, float f4, float f5) {
        int iOrdinal = ordinal();
        a0.q qVar = f1809d;
        switch (iOrdinal) {
            case 0:
                float f6 = f5 / f4 > f3 / f2 ? f4 / f2 : f5 / f3;
                qVar.f91a = f2 * f6;
                qVar.f92b = f3 * f6;
                return qVar;
            case 1:
                float f7 = f5 / f4 < f3 / f2 ? f4 / f2 : f5 / f3;
                qVar.f91a = f2 * f7;
                qVar.f92b = f3 * f7;
                return qVar;
            case 2:
                float f8 = f4 / f2;
                qVar.f91a = f2 * f8;
                qVar.f92b = f3 * f8;
                return qVar;
            case 3:
                float f9 = f5 / f3;
                qVar.f91a = f2 * f9;
                qVar.f92b = f3 * f9;
                return qVar;
            case 4:
                qVar.f91a = f4;
                qVar.f92b = f5;
                return qVar;
            case 5:
                qVar.f91a = f4;
                qVar.f92b = f3;
                return qVar;
            case 6:
                qVar.f91a = f2;
                qVar.f92b = f5;
                return qVar;
            case 7:
                qVar.f91a = f2;
                qVar.f92b = f3;
                return qVar;
            default:
                return qVar;
        }
    }
}
