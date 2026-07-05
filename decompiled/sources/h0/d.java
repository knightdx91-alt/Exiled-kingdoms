package h0;

import com.badlogic.gdx.scenes.scene2d.Actor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* JADX INFO: compiled from: Method.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Method f2216a;

    d(Method method) {
        this.f2216a = method;
    }

    public final String a() {
        return this.f2216a.getName();
    }

    public final Object b(Actor actor, Object... objArr) throws e {
        Method method = this.f2216a;
        try {
            return method.invoke(actor, objArr);
        } catch (IllegalAccessException e2) {
            throw new e("Illegal access to method: " + method.getName(), e2);
        } catch (IllegalArgumentException e3) {
            throw new e("Illegal argument(s) supplied to method: " + method.getName(), e3);
        } catch (InvocationTargetException e4) {
            throw new e("Exception occurred in method: " + method.getName(), e4);
        }
    }
}
