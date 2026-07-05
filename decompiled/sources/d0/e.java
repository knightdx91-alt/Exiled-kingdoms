package d0;

import java.util.HashMap;

/* JADX INFO: compiled from: Offer.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private f f2152a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private String f2153b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private HashMap f2154c = new HashMap(16);

    public final synchronized String a() {
        return this.f2153b;
    }

    public final synchronized String b() {
        String str = (String) this.f2154c.get("GooglePlay");
        if (str != null) {
            return str;
        }
        return this.f2153b;
    }

    public final synchronized f c() {
        return this.f2152a;
    }

    public final synchronized void d(String str) {
        this.f2153b = str;
    }

    public final synchronized void e(f fVar) {
        this.f2152a = fVar;
    }

    public final String toString() {
        return "Offer{type=" + this.f2152a + ", identifier='" + this.f2153b + "', identifierForStores=" + this.f2154c + '}';
    }
}
