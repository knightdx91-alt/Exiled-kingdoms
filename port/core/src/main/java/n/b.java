package n;

import android.support.v7.view.menu.j;
import android.view.View;

/* JADX INFO: compiled from: ActionMode.java */
/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public abstract class b {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Object f2454b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f2455c;

    /* JADX INFO: compiled from: ActionMode.java */
    public interface a {
        boolean a(b bVar, j jVar);

        boolean b(b bVar, android.support.v7.view.menu.h hVar);

        void c(b bVar);
    }

    public abstract void c();

    public abstract View d();

    public abstract android.support.v7.view.menu.h e();

    public abstract g f();

    public abstract CharSequence g();

    public final Object h() {
        return this.f2454b;
    }

    public abstract CharSequence i();

    public final boolean j() {
        return this.f2455c;
    }

    public abstract void k();

    public abstract boolean l();

    public abstract void m(View view);

    public abstract void n(int i2);

    public abstract void o(CharSequence charSequence);

    public final void p(Object obj) {
        this.f2454b = obj;
    }

    public abstract void q(int i2);

    public abstract void r(CharSequence charSequence);

    public void s(boolean z2) {
        this.f2455c = z2;
    }
}
