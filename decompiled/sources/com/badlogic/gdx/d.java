package com.badlogic.gdx;

/* JADX INFO: compiled from: Files.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public interface d {

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* JADX INFO: compiled from: Files.java */
    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final a f1644a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public static final a f1645b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public static final a f1646c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public static final a f1647d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public static final a f1648e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private static final /* synthetic */ a[] f1649f;

        static {
            a aVar = new a("Classpath", 0);
            f1644a = aVar;
            a aVar2 = new a("Internal", 1);
            f1645b = aVar2;
            a aVar3 = new a("External", 2);
            f1646c = aVar3;
            a aVar4 = new a("Absolute", 3);
            f1647d = aVar4;
            a aVar5 = new a("Local", 4);
            f1648e = aVar5;
            f1649f = new a[]{aVar, aVar2, aVar3, aVar4, aVar5};
        }

        private a() {
            throw null;
        }

        public static a valueOf(String str) {
            return (a) Enum.valueOf(a.class, str);
        }

        public static a[] values() {
            return (a[]) f1649f.clone();
        }
    }

    com.badlogic.gdx.files.a classpath(String str);

    com.badlogic.gdx.files.a external(String str);

    String getExternalStoragePath();

    com.badlogic.gdx.files.a getFileHandle(String str, a aVar);

    String getLocalStoragePath();

    com.badlogic.gdx.files.a internal(String str);

    com.badlogic.gdx.files.a local(String str);
}
