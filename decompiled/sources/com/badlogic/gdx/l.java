package com.badlogic.gdx;

import com.badlogic.gdx.utils.c0;
import java.util.HashMap;

/* JADX INFO: compiled from: Net.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public interface l {

    /* JADX INFO: compiled from: Net.java */
    public static class a implements c0.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private String f1713a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private String f1714b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private HashMap f1715c = new HashMap();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private String f1716d;

        public a(String str) {
            this.f1713a = str;
        }

        public final String a() {
            return this.f1716d;
        }

        public final HashMap b() {
            return this.f1715c;
        }

        public final String c() {
            return this.f1713a;
        }

        public final String d() {
            return this.f1714b;
        }

        public final void e(String str) {
            this.f1716d = str;
        }

        public final void f(String str) {
            this.f1714b = str;
        }

        @Override // com.badlogic.gdx.utils.c0.a
        public final void reset() {
            this.f1713a = null;
            this.f1714b = null;
            this.f1715c.clear();
            this.f1716d = null;
        }
    }

    /* JADX INFO: compiled from: Net.java */
    public interface b {
        byte[] getResult();
    }

    /* JADX INFO: compiled from: Net.java */
    public interface c {
        void cancelled();

        void failed(Throwable th);

        void handleHttpResponse(b bVar);
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* JADX INFO: compiled from: Net.java */
    public static final class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private static final /* synthetic */ d[] f1717a = {new d("TCP", 0)};

        /* JADX INFO: Fake field, exist only in values array */
        d EF5;

        private d() {
            throw null;
        }

        public static d valueOf(String str) {
            return (d) Enum.valueOf(d.class, str);
        }

        public static d[] values() {
            return (d[]) f1717a.clone();
        }
    }

    boolean openURI(String str);

    void sendHttpRequest(a aVar, c cVar);
}
