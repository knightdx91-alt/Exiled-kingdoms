package android.support.v4.util;

/* JADX INFO: compiled from: Pair.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class h<F, S> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final F f572a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final S f573b;

    public h(F f2, S s) {
        this.f572a = f2;
        this.f573b = s;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof h)) {
            return false;
        }
        h hVar = (h) obj;
        F f2 = hVar.f572a;
        F f3 = this.f572a;
        if (f2 != f3 && (f2 == null || !f2.equals(f3))) {
            return false;
        }
        S s = hVar.f573b;
        S s2 = this.f573b;
        return s == s2 || (s != null && s.equals(s2));
    }

    public final int hashCode() {
        F f2 = this.f572a;
        int iHashCode = f2 == null ? 0 : f2.hashCode();
        S s = this.f573b;
        return (s != null ? s.hashCode() : 0) ^ iHashCode;
    }

    public final String toString() {
        return "Pair{" + String.valueOf(this.f572a) + " " + String.valueOf(this.f573b) + "}";
    }
}
