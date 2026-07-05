package com.badlogic.gdx.utils;

/* JADX INFO: compiled from: ReflectionPool.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class f0<T> extends c0<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final h0.b f1798a;

    public f0(Class cls) {
        h0.b bVarB;
        super(4, 100);
        try {
            bVarB = h0.a.b(cls);
        } catch (Exception unused) {
            bVarB = null;
            try {
                h0.b bVarC = h0.a.c(cls, null);
                bVarC.c();
                bVarB = bVarC;
            } catch (h0.e unused2) {
            }
        }
        this.f1798a = bVarB;
        if (bVarB == null) {
            throw new RuntimeException("Class cannot be created (missing no-arg constructor): ".concat(cls.getName()));
        }
    }

    @Override // com.badlogic.gdx.utils.c0
    protected final T newObject() {
        h0.b bVar = this.f1798a;
        try {
            return (T) bVar.b(null);
        } catch (Exception e2) {
            throw new m("Unable to create new instance: ".concat(bVar.a().getName()), (Throwable) e2);
        }
    }
}
