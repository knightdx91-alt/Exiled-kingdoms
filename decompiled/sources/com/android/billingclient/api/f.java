package com.android.billingclient.api;

import android.text.TextUtils;
import com.google.android.gms.internal.play_billing.zzm;
import com.google.android.gms.internal.play_billing.zzu;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: com.android.billingclient:billing@@6.0.1 */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private boolean f1474a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private c f1475b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private zzu f1476c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private ArrayList f1477d;

    /* JADX INFO: compiled from: com.android.billingclient:billing@@6.0.1 */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private ArrayList f1478a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private c.a f1479b;

        a() {
            c.a aVar = new c.a();
            aVar.f1484a = true;
            this.f1479b = aVar;
        }

        public final f a() {
            ArrayList arrayList = this.f1478a;
            boolean z2 = (arrayList == null || arrayList.isEmpty()) ? false : true;
            if (!z2) {
                throw new IllegalArgumentException("Details of the products must be provided.");
            }
            b bVar = (b) this.f1478a.get(0);
            for (int i2 = 0; i2 < this.f1478a.size(); i2++) {
                b bVar2 = (b) this.f1478a.get(i2);
                if (bVar2 == null) {
                    throw new IllegalArgumentException("ProductDetailsParams cannot be null.");
                }
                if (i2 != 0 && !bVar2.a().d().equals(bVar.a().d()) && !bVar2.a().d().equals("play_pass_subs")) {
                    throw new IllegalArgumentException("All products should have same ProductType.");
                }
            }
            String strG = bVar.a().g();
            for (b bVar3 : this.f1478a) {
                if (!bVar.a().d().equals("play_pass_subs") && !bVar3.a().d().equals("play_pass_subs") && !strG.equals(bVar3.a().g())) {
                    throw new IllegalArgumentException("All products must have the same package name.");
                }
            }
            f fVar = new f();
            fVar.f1474a = z2 && !((b) this.f1478a.get(0)).a().g().isEmpty();
            fVar.f1475b = this.f1479b.a();
            fVar.f1477d = new ArrayList();
            ArrayList arrayList2 = this.f1478a;
            fVar.f1476c = arrayList2 != null ? zzu.zzj(arrayList2) : zzu.zzk();
            return fVar;
        }

        public final void b(List list) {
            this.f1478a = new ArrayList(list);
        }
    }

    /* JADX INFO: compiled from: com.android.billingclient:billing@@6.0.1 */
    public static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final k f1480a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final String f1481b;

        /* JADX INFO: compiled from: com.android.billingclient:billing@@6.0.1 */
        public static class a {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            private k f1482a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            private String f1483b;

            public final b a() {
                zzm.zzc(this.f1482a, "ProductDetails is required for constructing ProductDetailsParams.");
                zzm.zzc(this.f1483b, "offerToken is required for constructing ProductDetailsParams.");
                return new b(this);
            }

            public final void b(String str) {
                this.f1483b = str;
            }

            public final void c(k kVar) {
                this.f1482a = kVar;
                if (kVar.b() != null) {
                    kVar.b().getClass();
                    this.f1483b = kVar.b().d();
                }
            }
        }

        /* synthetic */ b(a aVar) {
            this.f1480a = aVar.f1482a;
            this.f1481b = aVar.f1483b;
        }

        public final k a() {
            return this.f1480a;
        }

        public final String b() {
            return this.f1481b;
        }
    }

    /* JADX INFO: compiled from: com.android.billingclient:billing@@6.0.1 */
    public static class c {

        /* JADX INFO: compiled from: com.android.billingclient:billing@@6.0.1 */
        public static class a {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            private boolean f1484a;

            public final c a() {
                boolean z2 = true;
                if (TextUtils.isEmpty(null) && TextUtils.isEmpty(null)) {
                    z2 = false;
                }
                boolean zIsEmpty = TextUtils.isEmpty(null);
                if (z2 && !zIsEmpty) {
                    throw new IllegalArgumentException("Please provide Old SKU purchase information(token/id) or original external transaction id, not both.");
                }
                if (this.f1484a || z2 || !zIsEmpty) {
                    return new c();
                }
                throw new IllegalArgumentException("Old SKU purchase information(token/id) or original external transaction id must be provided.");
            }
        }
    }

    public static a a() {
        return new a();
    }

    @Deprecated
    public final int b() {
        this.f1475b.getClass();
        return 0;
    }

    public final int c() {
        this.f1475b.getClass();
        return 0;
    }

    public final String d() {
        this.f1475b.getClass();
        return null;
    }

    public final String e() {
        this.f1475b.getClass();
        return null;
    }

    public final ArrayList f() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.f1477d);
        return arrayList;
    }

    public final zzu g() {
        return this.f1476c;
    }

    final boolean l() {
        this.f1475b.getClass();
        this.f1475b.getClass();
        this.f1475b.getClass();
        return this.f1474a;
    }
}
