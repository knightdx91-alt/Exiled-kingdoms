package android.support.v7.app;

/* JADX INFO: compiled from: AppCompatDelegateImplV9.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class m implements Runnable {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final /* synthetic */ i f782b;

    /* JADX INFO: compiled from: AppCompatDelegateImplV9.java */
    final class a extends com.badlogic.gdx.utils.l {
        a() {
        }

        @Override // android.support.v4.view.m
        public final void a() {
            m mVar = m.this;
            mVar.f782b.f717t.setAlpha(1.0f);
            i iVar = mVar.f782b;
            iVar.f720w.f(null);
            iVar.f720w = null;
        }

        @Override // com.badlogic.gdx.utils.l, android.support.v4.view.m
        public final void c() {
            m.this.f782b.f717t.setVisibility(0);
        }
    }

    m(i iVar) {
        this.f782b = iVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        i iVar = this.f782b;
        iVar.f718u.showAtLocation(iVar.f717t, 55, 0, 0);
        android.support.v4.view.l lVar = iVar.f720w;
        if (lVar != null) {
            lVar.b();
        }
        if (!iVar.M()) {
            iVar.f717t.setAlpha(1.0f);
            iVar.f717t.setVisibility(0);
            return;
        }
        iVar.f717t.setAlpha(0.0f);
        android.support.v4.view.l lVarA = android.support.v4.view.h.a(iVar.f717t);
        lVarA.a(1.0f);
        iVar.f720w = lVarA;
        lVarA.f(new a());
    }
}
