package d0;

/* JADX INFO: compiled from: Information.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class d {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final d f2140e = new d();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f2141a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final String f2142b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final String f2143c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private String f2144d;

    /* JADX INFO: compiled from: Information.java */
    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private String f2145a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private String f2146b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private String f2147c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        @Deprecated
        private Integer f2148d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private Double f2149e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private String f2150f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private b f2151g;

        public final d h() {
            return new d(this);
        }

        public final void i(b bVar) {
            this.f2151g = bVar;
        }

        public final void j(String str) {
            this.f2146b = str;
        }

        public final void k(String str) {
            this.f2145a = str;
        }

        public final void l(String str) {
            this.f2147c = str;
        }

        public final void m(Double d2) {
            this.f2149e = d2;
        }

        public final void n(String str) {
            this.f2150f = str;
        }

        @Deprecated
        public final void o(Integer num) {
            this.f2148d = num;
        }
    }

    public d() {
        this.f2141a = null;
        this.f2142b = null;
        this.f2143c = null;
    }

    public final String a() {
        return this.f2143c;
    }

    public final String b() {
        return this.f2144d;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || d.class != obj.getClass()) {
            return false;
        }
        d dVar = (d) obj;
        String str = dVar.f2141a;
        String str2 = this.f2141a;
        if (str2 == null ? str != null : !str2.equals(str)) {
            return false;
        }
        String str3 = dVar.f2142b;
        String str4 = this.f2142b;
        if (str4 == null ? str3 != null : !str4.equals(str3)) {
            return false;
        }
        String str5 = dVar.f2143c;
        String str6 = this.f2143c;
        if (str6 != null) {
            if (str6.equals(str5)) {
                return true;
            }
        } else if (str5 == null) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        String str = this.f2141a;
        int iHashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.f2142b;
        int iHashCode2 = (iHashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.f2143c;
        return iHashCode2 + (str3 != null ? str3.hashCode() : 0);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("Information{localName='");
        sb.append(this.f2141a);
        sb.append("', localDescription='");
        sb.append(this.f2142b);
        sb.append("', localPricing='");
        return a.a.m(this.f2143c, "'}", sb);
    }

    d(a aVar) {
        this.f2141a = aVar.f2145a;
        this.f2142b = aVar.f2146b;
        this.f2143c = aVar.f2147c;
        aVar.f2148d;
        aVar.f2149e;
        this.f2144d = aVar.f2150f;
        aVar.f2151g;
    }
}
