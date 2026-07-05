package com.android.billingclient.api;

/* JADX INFO: compiled from: com.android.billingclient:billing@@6.0.1 */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f1497a;

    /* JADX INFO: compiled from: com.android.billingclient:billing@@6.0.1 */
    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private String f1498a;

        public final h a() {
            String str = this.f1498a;
            if (str == null) {
                throw new IllegalArgumentException("Purchase token must be set");
            }
            h hVar = new h();
            hVar.f1497a = str;
            return hVar;
        }

        public final void b(String str) {
            this.f1498a = str;
        }
    }

    public final String a() {
        return this.f1497a;
    }
}
