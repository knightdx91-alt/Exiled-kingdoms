package h0;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/* JADX INFO: compiled from: Field.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Field f2215a;

    c(Field field) {
        this.f2215a = field;
    }

    public final Object a(Object obj) throws e {
        Field field = this.f2215a;
        try {
            return field.get(obj);
        } catch (IllegalAccessException e2) {
            throw new e("Illegal access to field: " + field.getName(), e2);
        } catch (IllegalArgumentException e3) {
            throw new e("Object is not an instance of " + field.getDeclaringClass(), e3);
        }
    }

    public final Class b() {
        return this.f2215a.getDeclaringClass();
    }

    public final Class c(int i2) {
        Type genericType = this.f2215a.getGenericType();
        if (!(genericType instanceof ParameterizedType)) {
            return null;
        }
        Type[] actualTypeArguments = ((ParameterizedType) genericType).getActualTypeArguments();
        if (actualTypeArguments.length - 1 < i2) {
            return null;
        }
        Type type = actualTypeArguments[i2];
        if (type instanceof Class) {
            return (Class) type;
        }
        if (type instanceof ParameterizedType) {
            return (Class) ((ParameterizedType) type).getRawType();
        }
        if (!(type instanceof GenericArrayType)) {
            return null;
        }
        Type genericComponentType = ((GenericArrayType) type).getGenericComponentType();
        if (genericComponentType instanceof Class) {
            return Array.newInstance((Class<?>) genericComponentType, 0).getClass();
        }
        return null;
    }

    public final String d() {
        return this.f2215a.getName();
    }

    public final Class e() {
        return this.f2215a.getType();
    }

    public final boolean f() {
        return this.f2215a.isAccessible();
    }

    public final boolean g() {
        return this.f2215a.isAnnotationPresent(Deprecated.class);
    }

    public final boolean h() {
        return Modifier.isStatic(this.f2215a.getModifiers());
    }

    public final boolean i() {
        return this.f2215a.isSynthetic();
    }

    public final boolean j() {
        return Modifier.isTransient(this.f2215a.getModifiers());
    }

    public final void k(Object obj, Object obj2) throws e {
        Field field = this.f2215a;
        try {
            field.set(obj, obj2);
        } catch (IllegalAccessException e2) {
            throw new e("Illegal access to field: " + field.getName(), e2);
        } catch (IllegalArgumentException e3) {
            throw new e("Argument not valid for field: " + field.getName(), e3);
        }
    }

    public final void l() {
        this.f2215a.setAccessible(true);
    }
}
