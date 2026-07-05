package android.support.v4.view;

import android.content.Context;
import android.support.v7.view.menu.t;
import android.util.Log;
import android.view.View;

/* JADX INFO: compiled from: ActionProvider.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public abstract class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Context f597a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private a f598b;

    /* JADX INFO: compiled from: ActionProvider.java */
    public interface a {
    }

    public c(Context context) {
        this.f597a = context;
    }

    public boolean a() {
        return false;
    }

    public boolean b() {
        return true;
    }

    public abstract View c();

    public View d(android.support.v7.view.menu.j jVar) {
        return c();
    }

    public boolean e() {
        return false;
    }

    public void f(t tVar) {
    }

    public boolean g() {
        return false;
    }

    public final void h() {
        this.f598b = null;
    }

    public void i(a aVar) {
        if (this.f598b != null) {
            Log.w("ActionProvider(support)", "setVisibilityListener: Setting a new ActionProvider.VisibilityListener when one is already set. Are you reusing this " + getClass().getSimpleName() + " instance while it is still in use somewhere else?");
        }
        this.f598b = aVar;
    }
}
