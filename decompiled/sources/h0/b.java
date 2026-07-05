package h0;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/* JADX INFO: compiled from: Constructor.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Constructor f2214a;

    b(Constructor constructor) {
        this.f2214a = constructor;
    }

    public final Class a() {
        return this.f2214a.getDeclaringClass();
    }

    public final Object b(Object... objArr) throws e {
        Constructor constructor = this.f2214a;
        try {
            return constructor.newInstance(objArr);
        } catch (IllegalAccessException e2) {
            throw new e("Could not instantiate instance of class: ".concat(constructor.getDeclaringClass().getName()), e2);
        } catch (IllegalArgumentException e3) {
            throw new e("Illegal argument(s) supplied to constructor for class: ".concat(constructor.getDeclaringClass().getName()), e3);
        } catch (InstantiationException e4) {
            throw new e("Could not instantiate instance of class: ".concat(constructor.getDeclaringClass().getName()), e4);
        } catch (InvocationTargetException e5) {
            throw new e("Exception occurred in constructor for class: ".concat(constructor.getDeclaringClass().getName()), e5);
        }
    }

    public final void c() {
        this.f2214a.setAccessible(true);
    }
}
