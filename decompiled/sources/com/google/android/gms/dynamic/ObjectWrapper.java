package com.google.android.gms.dynamic;

import a.a;
import android.os.IBinder;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.lang.reflect.Field;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@KeepForSdk
public final class ObjectWrapper<T> extends IObjectWrapper.Stub {
    private final T zzhz;

    private ObjectWrapper(T t2) {
        this.zzhz = t2;
    }

    @KeepForSdk
    public static <T> T unwrap(IObjectWrapper iObjectWrapper) {
        if (iObjectWrapper instanceof ObjectWrapper) {
            return ((ObjectWrapper) iObjectWrapper).zzhz;
        }
        IBinder iBinderAsBinder = iObjectWrapper.asBinder();
        Field[] declaredFields = iBinderAsBinder.getClass().getDeclaredFields();
        Field field = null;
        int i2 = 0;
        for (Field field2 : declaredFields) {
            if (!field2.isSynthetic()) {
                i2++;
                field = field2;
            }
        }
        if (i2 != 1) {
            throw new IllegalArgumentException(a.g(64, declaredFields.length, "Unexpected number of IObjectWrapper declared fields: "));
        }
        if (field.isAccessible()) {
            throw new IllegalArgumentException("IObjectWrapper declared field not private!");
        }
        field.setAccessible(true);
        try {
            return (T) field.get(iBinderAsBinder);
        } catch (IllegalAccessException e2) {
            throw new IllegalArgumentException("Could not access the field in remoteBinder.", e2);
        } catch (NullPointerException e3) {
            throw new IllegalArgumentException("Binder object is null.", e3);
        }
    }

    @KeepForSdk
    public static <T> IObjectWrapper wrap(T t2) {
        return new ObjectWrapper(t2);
    }
}
