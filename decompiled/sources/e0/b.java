package e0;

import com.android.billingclient.api.g;
import com.badlogic.gdx.Gdx;

/* JADX INFO: compiled from: PurchaseManagerGoogleBilling.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class b implements com.android.billingclient.api.e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ Runnable f2180a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final /* synthetic */ f f2181b;

    b(f fVar, Runnable runnable) {
        this.f2181b = fVar;
        this.f2180a = runnable;
    }

    @Override // com.android.billingclient.api.e
    public final void a(g gVar) {
        int iB = gVar.b();
        Gdx.app.debug("GdxPay/GoogleBilling", "Setup finished. Response code: " + iB);
        boolean z2 = iB == 0;
        f fVar = this.f2181b;
        fVar.f2190d = z2;
        if (fVar.f2190d) {
            fVar.f2196j = 1000L;
            fVar.f2195i = true;
        } else {
            f.j(fVar);
        }
        Runnable runnable = this.f2180a;
        if (runnable != null) {
            runnable.run();
        }
    }

    @Override // com.android.billingclient.api.e
    public final void b() {
        f fVar = this.f2181b;
        fVar.f2190d = false;
        f.j(fVar);
    }
}
