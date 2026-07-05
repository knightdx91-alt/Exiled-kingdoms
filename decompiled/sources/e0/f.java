package e0;

import android.os.Handler;
import android.os.Looper;
import com.android.billingclient.api.a;
import com.android.billingclient.api.c;
import com.android.billingclient.api.f;
import com.android.billingclient.api.h;
import com.android.billingclient.api.k;
import com.android.billingclient.api.m;
import com.android.billingclient.api.n;
import com.android.billingclient.api.o;
import com.android.billingclient.api.p;
import com.android.billingclient.api.q;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.android.AndroidApplication;
import d0.g;
import d0.h;
import d0.i;
import d0.j;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: compiled from: PurchaseManagerGoogleBilling.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class f implements g, o {

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private static final Handler f2186k = new Handler(Looper.getMainLooper());

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final AndroidApplication f2188b;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private boolean f2190d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private boolean f2191e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private com.android.billingclient.api.c f2192f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private i f2193g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private h f2194h;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final ConcurrentHashMap f2187a = new ConcurrentHashMap();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final HashMap f2189c = new HashMap();

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private boolean f2195i = false;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private long f2196j = 1000;

    /* JADX INFO: compiled from: PurchaseManagerGoogleBilling.java */
    final class a implements n {
        a() {
        }

        @Override // com.android.billingclient.api.n
        public final void a(com.android.billingclient.api.g gVar, List<m> list) {
            int iB = gVar.b();
            f fVar = f.this;
            if (iB == 0) {
                fVar.r(list, true);
                return;
            }
            Gdx.app.error("GdxPay/GoogleBilling", "queryPurchases failed with responseCode " + iB);
            fVar.f2193g.e(new d0.c(a.a.h(iB, "queryPurchases failed with responseCode ")));
        }
    }

    /* JADX INFO: compiled from: PurchaseManagerGoogleBilling.java */
    final class b implements com.android.billingclient.api.i {
    }

    /* JADX INFO: compiled from: PurchaseManagerGoogleBilling.java */
    final class c implements com.android.billingclient.api.b {
    }

    public f(AndroidApplication androidApplication) {
        this.f2188b = androidApplication;
        c.a aVarD = com.android.billingclient.api.c.d(androidApplication);
        aVarD.c(this);
        aVarD.b();
        this.f2192f = aVarD.a();
    }

    static void f(f fVar) {
        if (fVar.f2191e) {
            return;
        }
        fVar.f2191e = true;
        fVar.f2193g.a();
    }

    static void j(f fVar) {
        fVar.getClass();
        f2186k.postDelayed(new d(fVar), fVar.f2196j);
        fVar.f2196j = Math.min(fVar.f2196j * 2, 900000L);
    }

    static void k(f fVar, e0.c cVar) {
        fVar.f2192f.g(new e0.b(fVar, cVar));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void q() {
        String str;
        i iVar = this.f2193g;
        if (iVar == null) {
            return;
        }
        if (!this.f2190d) {
            iVar.f(new d0.c("Connection to Play Billing not possible"));
            return;
        }
        Gdx.app.debug("GdxPay/GoogleBilling", "Called fetchOfferDetails()");
        this.f2189c.clear();
        int iE = this.f2194h.e();
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < iE; i2++) {
            d0.e eVarC = this.f2194h.c(i2);
            p.b.a aVar = new p.b.a();
            aVar.b(eVarC.b());
            d0.f fVarC = eVarC.c();
            int iOrdinal = fVarC.ordinal();
            if (iOrdinal == 0 || iOrdinal == 1) {
                str = "inapp";
            } else {
                if (iOrdinal != 2) {
                    throw new IllegalStateException("Unsupported OfferType: " + fVarC);
                }
                str = "subs";
            }
            aVar.c(str);
            arrayList.add(aVar.a());
        }
        if (arrayList.isEmpty()) {
            Gdx.app.debug("GdxPay/GoogleBilling", "No products configured");
            if (this.f2191e) {
                return;
            }
            this.f2191e = true;
            this.f2193g.a();
            return;
        }
        p.a aVar2 = new p.a();
        aVar2.b(arrayList);
        p pVarA = aVar2.a();
        Gdx.app.debug("GdxPay/GoogleBilling", "QueryProductDetailsParams: " + pVarA);
        this.f2192f.e(pVarA, new e(this));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void r(List<m> list, boolean z2) {
        ArrayList arrayList = new ArrayList(list.size());
        for (m mVar : list) {
            if (mVar.d() == 1) {
                String str = (String) mVar.c().get(0);
                j jVar = new j();
                jVar.d(str);
                jVar.e(mVar.a());
                jVar.h(mVar.f());
                jVar.i();
                jVar.g(new Date(mVar.e()));
                jVar.f("Purchased: " + str);
                jVar.j(mVar.b());
                jVar.k(mVar.g());
                if (z2) {
                    arrayList.add(jVar);
                } else {
                    this.f2193g.c(jVar);
                }
                d0.e eVarD = this.f2194h.d(str);
                if (eVarD != null) {
                    int iOrdinal = eVarD.c().ordinal();
                    com.android.billingclient.api.c cVar = this.f2192f;
                    if (iOrdinal == 0) {
                        h.a aVar = new h.a();
                        aVar.b(mVar.f());
                        cVar.b(aVar.a(), new b());
                    } else if (iOrdinal == 1 || iOrdinal == 2) {
                        if (!mVar.h()) {
                            a.C0020a c0020a = new a.C0020a();
                            c0020a.b(mVar.f());
                            cVar.a(c0020a.a(), new c());
                        }
                    }
                }
            }
        }
        if (z2) {
            this.f2193g.d((j[]) arrayList.toArray(new j[0]));
        }
    }

    public final d0.d p() {
        d0.d dVar = (d0.d) this.f2187a.get("full_ek_license");
        return dVar == null ? d0.d.f2140e : dVar;
    }

    public final void s(i iVar, d0.h hVar) {
        Gdx.app.debug("GdxPay/GoogleBilling", "Called install()");
        this.f2193g = iVar;
        this.f2194h = hVar;
        this.f2191e = false;
        this.f2192f.g(new e0.b(this, new e0.c(this, 1)));
    }

    public final boolean t() {
        return this.f2191e;
    }

    public final void u(com.android.billingclient.api.g gVar, List<m> list) {
        int iB = gVar.b();
        i iVar = this.f2193g;
        if (iVar == null) {
            return;
        }
        if (iB == 0 && list != null) {
            r(list, false);
            return;
        }
        if (iB == 1) {
            return;
        }
        if (iB == 7) {
            iVar.b(new d0.a("Purchase failed: Item is already owned."));
            return;
        }
        if (iB == 4) {
            iVar.b(new d0.a(""));
            return;
        }
        Gdx.app.error("GdxPay/GoogleBilling", "onPurchasesUpdated failed with responseCode " + iB);
        this.f2193g.b(new d0.c(a.a.h(iB, "onPurchasesUpdated failed with responseCode ")));
    }

    public final void v(String str) {
        String strA;
        List listSingletonList;
        k kVar = (k) this.f2189c.get(str);
        if (kVar == null) {
            this.f2193g.b(new d0.a(str));
            return;
        }
        com.android.billingclient.api.c cVar = this.f2192f;
        if (kVar.d().equals("inapp")) {
            f.b.a aVar = new f.b.a();
            aVar.c(kVar);
            listSingletonList = Collections.singletonList(aVar.a());
        } else {
            ArrayList arrayListE = kVar.e();
            if (arrayListE == null || arrayListE.isEmpty()) {
                Gdx.app.error("GdxPay/GoogleBilling", "subscriptionOfferDetails are empty for product: " + kVar);
                strA = null;
            } else {
                strA = ((k.d) arrayListE.get(0)).a();
            }
            f.b.a aVar2 = new f.b.a();
            aVar2.c(kVar);
            aVar2.b(strA);
            listSingletonList = Collections.singletonList(aVar2.a());
        }
        f.a aVarA = com.android.billingclient.api.f.a();
        aVarA.b(listSingletonList);
        cVar.c(this.f2188b, aVarA.a());
    }

    public final void w() {
        String str = this.f2194h.f() ? "subs" : "inapp";
        com.android.billingclient.api.c cVar = this.f2192f;
        q.a aVar = new q.a();
        aVar.b(str);
        cVar.f(aVar.a(), new a());
    }
}
