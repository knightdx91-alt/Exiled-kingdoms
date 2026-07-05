package d0;

/* JADX INFO: compiled from: FreeTrialPeriod.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final int f2133a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final a f2134b;

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* JADX INFO: compiled from: FreeTrialPeriod.java */
    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final a f2135a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public static final a f2136b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public static final a f2137c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public static final a f2138d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private static final /* synthetic */ a[] f2139e;

        static {
            a aVar = new a("DAY", 0);
            f2135a = aVar;
            a aVar2 = new a("MONTH", 1);
            f2136b = aVar2;
            a aVar3 = new a("WEEK", 2);
            f2137c = aVar3;
            a aVar4 = new a("YEAR", 3);
            f2138d = aVar4;
            f2139e = new a[]{aVar, aVar2, aVar3, aVar4};
        }

        private a() {
            throw null;
        }

        public static a valueOf(String str) {
            return (a) Enum.valueOf(a.class, str);
        }

        public static a[] values() {
            return (a[]) f2139e.clone();
        }
    }

    public b(int i2, a aVar) {
        this.f2133a = i2;
        this.f2134b = aVar;
    }

    public final int a() {
        return this.f2133a;
    }

    public final a b() {
        return this.f2134b;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || b.class != obj.getClass()) {
            return false;
        }
        b bVar = (b) obj;
        return this.f2133a == bVar.f2133a && this.f2134b == bVar.f2134b;
    }

    public final int hashCode() {
        return this.f2134b.hashCode() + (this.f2133a * 31);
    }
}
