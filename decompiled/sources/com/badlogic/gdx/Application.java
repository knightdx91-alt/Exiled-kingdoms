package com.badlogic.gdx;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public interface Application {
    public static final int LOG_DEBUG = 3;
    public static final int LOG_ERROR = 1;
    public static final int LOG_INFO = 2;
    public static final int LOG_NONE = 0;

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final a f1563a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public static final a f1564b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public static final a f1565c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public static final a f1566d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public static final a f1567e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private static final /* synthetic */ a[] f1568f;

        static {
            a aVar = new a("Android", 0);
            f1563a = aVar;
            a aVar2 = new a("Desktop", 1);
            f1564b = aVar2;
            a aVar3 = new a("HeadlessDesktop", 2);
            a aVar4 = new a("Applet", 3);
            f1565c = aVar4;
            a aVar5 = new a("WebGL", 4);
            f1566d = aVar5;
            a aVar6 = new a("iOS", 5);
            f1567e = aVar6;
            f1568f = new a[]{aVar, aVar2, aVar3, aVar4, aVar5, aVar6};
        }

        private a() {
            throw null;
        }

        public static a valueOf(String str) {
            return (a) Enum.valueOf(a.class, str);
        }

        public static a[] values() {
            return (a[]) f1568f.clone();
        }
    }

    void addLifecycleListener(k kVar);

    void debug(String str, String str2);

    void debug(String str, String str2, Throwable th);

    void error(String str, String str2);

    void error(String str, String str2, Throwable th);

    void exit();

    com.badlogic.gdx.a getApplicationListener();

    b getApplicationLogger();

    c getAudio();

    com.badlogic.gdx.utils.f getClipboard();

    d getFiles();

    f getGraphics();

    g getInput();

    long getJavaHeap();

    int getLogLevel();

    long getNativeHeap();

    l getNet();

    m getPreferences(String str);

    a getType();

    int getVersion();

    void log(String str, String str2);

    void log(String str, String str2, Throwable th);

    void postRunnable(Runnable runnable);

    void removeLifecycleListener(k kVar);

    void setApplicationLogger(b bVar);

    void setLogLevel(int i2);
}
