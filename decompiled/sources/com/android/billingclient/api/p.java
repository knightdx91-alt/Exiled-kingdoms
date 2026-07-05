package com.android.billingclient.api;

import com.google.android.gms.internal.play_billing.zzu;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

/* JADX INFO: compiled from: com.android.billingclient:billing@@6.0.1 */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class p {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final zzu f1528a;

    /* JADX INFO: compiled from: com.android.billingclient:billing@@6.0.1 */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private zzu f1529a;

        public final p a() {
            return new p(this);
        }

        public final void b(ArrayList arrayList) {
            if (arrayList.isEmpty()) {
                throw new IllegalArgumentException("Product list cannot be empty.");
            }
            HashSet hashSet = new HashSet();
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                b bVar = (b) it.next();
                if (!"play_pass_subs".equals(bVar.b())) {
                    hashSet.add(bVar.b());
                }
            }
            if (hashSet.size() > 1) {
                throw new IllegalArgumentException("All products should be of the same product type.");
            }
            this.f1529a = zzu.zzj(arrayList);
        }
    }

    /* JADX INFO: compiled from: com.android.billingclient:billing@@6.0.1 */
    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final String f1530a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final String f1531b;

        /* JADX INFO: compiled from: com.android.billingclient:billing@@6.0.1 */
        public static class a {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            private String f1532a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            private String f1533b;

            public final b a() {
                if ("first_party".equals(this.f1533b)) {
                    throw new IllegalArgumentException("Serialized doc id must be provided for first party products.");
                }
                if (this.f1532a == null) {
                    throw new IllegalArgumentException("Product id must be provided.");
                }
                if (this.f1533b != null) {
                    return new b(this);
                }
                throw new IllegalArgumentException("Product type must be provided.");
            }

            public final void b(String str) {
                this.f1532a = str;
            }

            public final void c(String str) {
                this.f1533b = str;
            }
        }

        /* synthetic */ b(a aVar) {
            this.f1530a = aVar.f1532a;
            this.f1531b = aVar.f1533b;
        }

        public final String a() {
            return this.f1530a;
        }

        public final String b() {
            return this.f1531b;
        }
    }

    /* synthetic */ p(a aVar) {
        this.f1528a = aVar.f1529a;
    }

    public final zzu a() {
        return this.f1528a;
    }

    public final String b() {
        return ((b) this.f1528a.get(0)).b();
    }
}
