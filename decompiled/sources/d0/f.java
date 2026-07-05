package d0;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: compiled from: OfferType.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final f f2155a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final f f2156b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final f f2157c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static final /* synthetic */ f[] f2158d;

    static {
        f fVar = new f("CONSUMABLE", 0);
        f2155a = fVar;
        f fVar2 = new f("ENTITLEMENT", 1);
        f2156b = fVar2;
        f fVar3 = new f("SUBSCRIPTION", 2);
        f2157c = fVar3;
        f2158d = new f[]{fVar, fVar2, fVar3};
    }

    private f() {
        throw null;
    }

    public static f valueOf(String str) {
        return (f) Enum.valueOf(f.class, str);
    }

    public static f[] values() {
        return (f[]) f2158d.clone();
    }
}
