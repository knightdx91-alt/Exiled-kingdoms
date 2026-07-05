package android.support.v7.view.menu;

import android.support.v4.view.c;
import android.support.v7.view.menu.k;
import android.view.ActionProvider;
import android.view.View;

/* JADX INFO: compiled from: MenuItemWrapperJB.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class l extends k {

    /* JADX INFO: compiled from: MenuItemWrapperJB.java */
    class a extends k.a implements ActionProvider.VisibilityListener {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        c.a f984e;

        @Override // android.support.v4.view.c
        public final boolean b() {
            return this.f979c.isVisible();
        }

        @Override // android.support.v4.view.c
        public final View d(j jVar) {
            return this.f979c.onCreateActionView(jVar);
        }

        @Override // android.support.v4.view.c
        public final boolean g() {
            return this.f979c.overridesItemVisibility();
        }

        @Override // android.support.v4.view.c
        public final void i(c.a aVar) {
            this.f984e = aVar;
            this.f979c.setVisibilityListener(this);
        }

        @Override // android.view.ActionProvider.VisibilityListener
        public final void onActionProviderVisibilityChanged(boolean z2) {
            c.a aVar = this.f984e;
            if (aVar != null) {
                j.this.f965n.v();
            }
        }
    }

    @Override // android.support.v7.view.menu.k
    final k.a h(ActionProvider actionProvider) {
        return new a(this.f874b, actionProvider);
    }
}
