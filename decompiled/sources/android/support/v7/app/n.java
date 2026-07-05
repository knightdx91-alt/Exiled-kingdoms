package android.support.v7.app;

import android.view.View;

/* JADX INFO: compiled from: AppCompatDelegateImplV9.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class n extends com.badlogic.gdx.utils.l {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final /* synthetic */ i f784c;

    n(i iVar) {
        this.f784c = iVar;
    }

    @Override // android.support.v4.view.m
    public final void a() {
        i iVar = this.f784c;
        iVar.f717t.setAlpha(1.0f);
        iVar.f720w.f(null);
        iVar.f720w = null;
    }

    @Override // com.badlogic.gdx.utils.l, android.support.v4.view.m
    public final void c() {
        i iVar = this.f784c;
        iVar.f717t.setVisibility(0);
        iVar.f717t.sendAccessibilityEvent(32);
        if (iVar.f717t.getParent() instanceof View) {
            ((View) iVar.f717t.getParent()).requestApplyInsets();
        }
    }
}
