package com.android.billingclient.api;

/* JADX INFO: compiled from: com.android.billingclient:billing@@6.0.1 */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f1434a;

    /* JADX INFO: renamed from: com.android.billingclient.api.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: com.android.billingclient:billing@@6.0.1 */
    public static final class C0020a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private String f1435a;

        public final a a() {
            String str = this.f1435a;
            if (str == null) {
                throw new IllegalArgumentException("Purchase token must be set");
            }
            a aVar = new a();
            aVar.f1434a = str;
            return aVar;
        }

        public final void b(String str) {
            this.f1435a = str;
        }
    }

    public final String a() {
        return this.f1434a;
    }
}
