package d0;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/* JADX INFO: compiled from: PurchaseManagerConfig.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private ArrayList f2159a = new ArrayList(16);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private HashMap f2160b = new HashMap(16);

    public final synchronized void a(e eVar) {
        this.f2159a.add(eVar);
    }

    public final synchronized void b() {
        this.f2160b.put("GooglePlay", "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAz0cD9mfqFU4MIDtvcy6z+NblV/XcZ5CsL3ZXV00kOB2pjQoi7sdN7pC/dZrJNzCg+sdiKYkMvTBZHXwHcExEDZv7yv0Q8nsRksSUJBfU9PcDjAYYYeAK5VvsDJQpKC0fC6WFx7mKnx4QOCoJqhLAksdi5kIn5JaJJ/cFe10b3ne6TZlqBsX/csWkOtvq4zuV1E4gRh2BfFeSJd8JVcvoXkzmDblLBGo3ku7Zw07aismUeiTiSBAB+JigBBZnDPZlyqKcJUgAt1U29sxy0jjAprpsbHCHfipLkJSz0MAoTe0pc1oCwIO76vMeSH7l/jTk7N7ZkbVE6a5SgFuaacezxwIDAQAB");
    }

    public final synchronized e c(int i2) {
        return (e) this.f2159a.get(i2);
    }

    public final synchronized e d(String str) {
        for (int i2 = 0; i2 < this.f2159a.size(); i2++) {
            if (((e) this.f2159a.get(i2)).a().equals(str)) {
                return (e) this.f2159a.get(i2);
            }
        }
        return null;
    }

    public final synchronized int e() {
        return this.f2159a.size();
    }

    public final synchronized boolean f() {
        f fVar = f.f2157c;
        synchronized (this) {
            Iterator it = this.f2159a.iterator();
            while (it.hasNext()) {
                if (((e) it.next()).c() == fVar) {
                    return true;
                }
            }
            return false;
        }
    }
}
