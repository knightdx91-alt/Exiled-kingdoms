package com.badlogic.gdx.utils;

/* JADX INFO: compiled from: Pools.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class d0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final y<Class, c0> f1782a = new y<>();

    public static void a(Object obj) {
        if (obj == null) {
            throw new IllegalArgumentException("object cannot be null.");
        }
        c0 c0VarD = f1782a.d(obj.getClass());
        if (c0VarD == null) {
            return;
        }
        c0VarD.free(obj);
    }

    public static void b(a aVar) {
        if (aVar == null) {
            throw new IllegalArgumentException("objects cannot be null.");
        }
        int i2 = aVar.f1750b;
        c0 c0VarD = null;
        for (int i3 = 0; i3 < i2; i3++) {
            Object obj = aVar.get(i3);
            if (obj != null && (c0VarD != null || (c0VarD = f1782a.d(obj.getClass())) != null)) {
                c0VarD.free(obj);
            }
        }
    }

    public static <T> c0<T> c(Class<T> cls) {
        y<Class, c0> yVar = f1782a;
        c0<T> c0VarD = yVar.d(cls);
        if (c0VarD != null) {
            return c0VarD;
        }
        f0 f0Var = new f0(cls);
        yVar.j(cls, f0Var);
        return f0Var;
    }

    public static <T> T d(Class<T> cls) {
        return (T) c(cls).obtain();
    }
}
