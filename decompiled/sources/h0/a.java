package h0;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/* JADX INFO: compiled from: ClassReflection.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class a {
    public static Class a(String str) throws e {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e2) {
            throw new e(a.a.A("Class not found: ", str), e2);
        }
    }

    public static b b(Class cls) throws e {
        try {
            return new b(cls.getConstructor(null));
        } catch (NoSuchMethodException e2) {
            throw new e("Constructor not found for class: ".concat(cls.getName()), e2);
        } catch (SecurityException e3) {
            throw new e("Security violation occurred while getting constructor for class: '" + cls.getName() + "'.", e3);
        }
    }

    public static b c(Class cls, Class... clsArr) throws e {
        try {
            return new b(cls.getDeclaredConstructor(clsArr));
        } catch (NoSuchMethodException e2) {
            throw new e("Constructor not found for class: ".concat(cls.getName()), e2);
        } catch (SecurityException e3) {
            throw new e("Security violation while getting constructor for class: ".concat(cls.getName()), e3);
        }
    }

    public static c[] d(Class cls) {
        Field[] declaredFields = cls.getDeclaredFields();
        c[] cVarArr = new c[declaredFields.length];
        int length = declaredFields.length;
        for (int i2 = 0; i2 < length; i2++) {
            cVarArr[i2] = new c(declaredFields[i2]);
        }
        return cVarArr;
    }

    public static d[] e(Class cls) {
        Method[] methods = cls.getMethods();
        d[] dVarArr = new d[methods.length];
        int length = methods.length;
        for (int i2 = 0; i2 < length; i2++) {
            dVarArr[i2] = new d(methods[i2]);
        }
        return dVarArr;
    }

    public static <T> T f(Class<T> cls) throws e {
        try {
            return cls.newInstance();
        } catch (IllegalAccessException e2) {
            throw new e("Could not instantiate instance of class: ".concat(cls.getName()), e2);
        } catch (InstantiationException e3) {
            throw new e("Could not instantiate instance of class: ".concat(cls.getName()), e3);
        }
    }
}
