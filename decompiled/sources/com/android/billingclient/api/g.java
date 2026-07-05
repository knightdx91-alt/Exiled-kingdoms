package com.android.billingclient.api;

import com.google.android.gms.internal.play_billing.zzb;

/* JADX INFO: compiled from: com.android.billingclient:billing@@6.0.1 */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f1488a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private String f1489b;

    /* JADX INFO: compiled from: com.android.billingclient:billing@@6.0.1 */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private int f1490a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private String f1491b = "";

        /* synthetic */ a() {
        }

        public final g a() {
            g gVar = new g();
            gVar.f1488a = this.f1490a;
            gVar.f1489b = this.f1491b;
            return gVar;
        }

        public final void b(String str) {
            this.f1491b = str;
        }

        public final void c(int i2) {
            this.f1490a = i2;
        }
    }

    public static a c() {
        return new a();
    }

    public final String a() {
        return this.f1489b;
    }

    public final int b() {
        return this.f1488a;
    }

    public final String toString() {
        return "Response Code: " + zzb.zzg(this.f1488a) + ", Debug Message: " + this.f1489b;
    }
}
