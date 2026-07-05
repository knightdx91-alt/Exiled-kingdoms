package com.android.billingclient.api;

/* JADX INFO: compiled from: com.android.billingclient:billing@@6.0.1 */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class q {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f1534a;

    /* JADX INFO: compiled from: com.android.billingclient:billing@@6.0.1 */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private String f1535a;

        public final q a() {
            if (this.f1535a != null) {
                return new q(this);
            }
            throw new IllegalArgumentException("Product type must be set");
        }

        public final void b(String str) {
            this.f1535a = str;
        }
    }

    /* synthetic */ q(a aVar) {
        this.f1534a = aVar.f1535a;
    }

    public final String a() {
        return this.f1534a;
    }
}
