package e0;

import com.android.billingclient.api.g;
import com.android.billingclient.api.k;
import com.android.billingclient.api.l;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import d0.d;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: compiled from: PurchaseManagerGoogleBilling.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class e implements l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ f f2185a;

    e(f fVar) {
        this.f2185a = fVar;
    }

    @Override // com.android.billingclient.api.l
    public final void a(g gVar, ArrayList arrayList) {
        Application application;
        d0.b bVar;
        k.b bVar2;
        k.b bVar3;
        int iB = gVar.b();
        f fVar = this.f2185a;
        if (fVar.f2193g == null || (application = Gdx.app) == null) {
            return;
        }
        if (iB != 0) {
            application.error("GdxPay/GoogleBilling", "onProductDetailsResponse failed, error code is " + iB);
            if (fVar.f2191e) {
                return;
            }
            fVar.f2193g.f(new d0.a(a.a.l("Failed to fetch item list - check your connection (", String.valueOf(iB), ")")));
            return;
        }
        application.debug("GdxPay/GoogleBilling", "Retrieved product count: " + arrayList.size());
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            k kVar = (k) it.next();
            ConcurrentHashMap concurrentHashMap = fVar.f2187a;
            String strC = kVar.c();
            Gdx.app.debug("GdxPay/GoogleBilling", "Converting productDetails: \n" + kVar);
            d.a aVar = new d.a();
            aVar.k(kVar.f());
            aVar.j(kVar.a());
            if ("subs".equals(kVar.d())) {
                ArrayList arrayListE = kVar.e();
                if (arrayListE.isEmpty()) {
                    Gdx.app.error("GdxPay/GoogleBilling", "Empty SubscriptionOfferDetails");
                } else {
                    k.d dVar = (k.d) arrayListE.get(0);
                    if (dVar.b().a().isEmpty()) {
                        Gdx.app.error("GdxPay/GoogleBilling", "getPricingPhases()  or empty ");
                    } else {
                        Iterator it2 = dVar.b().a().iterator();
                        while (true) {
                            bVar = null;
                            if (!it2.hasNext()) {
                                bVar2 = null;
                                break;
                            } else {
                                bVar2 = (k.b) it2.next();
                                if (bVar2.d() > 0) {
                                    break;
                                }
                            }
                        }
                        if (bVar2 == null) {
                            Gdx.app.error("GdxPay/GoogleBilling", "no paidRecurringPricingPhase found ");
                        } else {
                            aVar.l(bVar2.c());
                            aVar.n(bVar2.e());
                            aVar.o(Integer.valueOf(((int) bVar2.d()) / 10000));
                            aVar.m(Double.valueOf(bVar2.d() / 1000000.0d));
                            Iterator it3 = dVar.b().a().iterator();
                            while (true) {
                                if (!it3.hasNext()) {
                                    bVar3 = null;
                                    break;
                                }
                                bVar3 = (k.b) it3.next();
                                if (bVar3.d() == 0 && (bVar3.f() == 3 || bVar3.f() == 2)) {
                                    break;
                                }
                            }
                            if (bVar3 != null) {
                                String strB = bVar3.b();
                                int iA = bVar3.a();
                                if (strB != null && !strB.isEmpty()) {
                                    try {
                                        d0.b bVarA = a.a(strB);
                                        bVar = iA > 1 ? new d0.b(bVarA.a() * iA, bVarA.b()) : bVarA;
                                    } catch (RuntimeException e2) {
                                        Gdx.app.error("GdxPay/GoogleBilling", "Failed to parse iso8601Duration: ".concat(strB), e2);
                                    }
                                }
                                aVar.i(bVar);
                            }
                        }
                    }
                }
            } else {
                k.a aVarB = kVar.b();
                aVar.l(aVarB.a());
                aVar.n(aVarB.c());
                aVar.o(Integer.valueOf((int) (aVarB.b() / 10000)));
                aVar.m(Double.valueOf(aVarB.b() / 1000000.0d));
            }
            concurrentHashMap.put(strC, aVar.h());
            fVar.f2189c.put(kVar.c(), kVar);
        }
        f.f(fVar);
    }
}
