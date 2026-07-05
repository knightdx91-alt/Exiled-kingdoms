package android.arch.lifecycle;

/* JADX INFO: compiled from: Lifecycle.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public abstract class a {

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* JADX INFO: renamed from: android.arch.lifecycle.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: Lifecycle.java */
    public static final class EnumC0002a {
        private static final /* synthetic */ EnumC0002a[] $VALUES;
        public static final EnumC0002a ON_ANY;
        public static final EnumC0002a ON_CREATE;
        public static final EnumC0002a ON_DESTROY;
        public static final EnumC0002a ON_PAUSE;
        public static final EnumC0002a ON_RESUME;
        public static final EnumC0002a ON_START;
        public static final EnumC0002a ON_STOP;

        static {
            EnumC0002a enumC0002a = new EnumC0002a("ON_CREATE", 0);
            ON_CREATE = enumC0002a;
            EnumC0002a enumC0002a2 = new EnumC0002a("ON_START", 1);
            ON_START = enumC0002a2;
            EnumC0002a enumC0002a3 = new EnumC0002a("ON_RESUME", 2);
            ON_RESUME = enumC0002a3;
            EnumC0002a enumC0002a4 = new EnumC0002a("ON_PAUSE", 3);
            ON_PAUSE = enumC0002a4;
            EnumC0002a enumC0002a5 = new EnumC0002a("ON_STOP", 4);
            ON_STOP = enumC0002a5;
            EnumC0002a enumC0002a6 = new EnumC0002a("ON_DESTROY", 5);
            ON_DESTROY = enumC0002a6;
            EnumC0002a enumC0002a7 = new EnumC0002a("ON_ANY", 6);
            ON_ANY = enumC0002a7;
            $VALUES = new EnumC0002a[]{enumC0002a, enumC0002a2, enumC0002a3, enumC0002a4, enumC0002a5, enumC0002a6, enumC0002a7};
        }

        private EnumC0002a() {
            throw null;
        }

        public static EnumC0002a valueOf(String str) {
            return (EnumC0002a) Enum.valueOf(EnumC0002a.class, str);
        }

        public static EnumC0002a[] values() {
            return (EnumC0002a[]) $VALUES.clone();
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* JADX INFO: compiled from: Lifecycle.java */
    public static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final b f96a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public static final b f97b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public static final b f98c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public static final b f99d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public static final b f100e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private static final /* synthetic */ b[] f101f;

        static {
            b bVar = new b("DESTROYED", 0);
            f96a = bVar;
            b bVar2 = new b("INITIALIZED", 1);
            f97b = bVar2;
            b bVar3 = new b("CREATED", 2);
            f98c = bVar3;
            b bVar4 = new b("STARTED", 3);
            f99d = bVar4;
            b bVar5 = new b("RESUMED", 4);
            f100e = bVar5;
            f101f = new b[]{bVar, bVar2, bVar3, bVar4, bVar5};
        }

        private b() {
            throw null;
        }

        public static b valueOf(String str) {
            return (b) Enum.valueOf(b.class, str);
        }

        public static b[] values() {
            return (b[]) f101f.clone();
        }
    }

    public abstract b a();
}
