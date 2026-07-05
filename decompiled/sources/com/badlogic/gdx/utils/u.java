package com.badlogic.gdx.utils;

import java.io.IOException;
import java.io.Writer;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.regex.Pattern;

/* JADX INFO: compiled from: JsonWriter.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class u extends Writer {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final Writer f1976a;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private a f1978c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private boolean f1979d;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final com.badlogic.gdx.utils.a<a> f1977b = new com.badlogic.gdx.utils.a<>();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private b f1980e = b.f1985a;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private boolean f1981f = false;

    /* JADX INFO: compiled from: JsonWriter.java */
    private class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final boolean f1982a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        boolean f1983b;

        a(boolean z2) throws IOException {
            this.f1982a = z2;
            u.this.f1976a.write(z2 ? 91 : 123);
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* JADX INFO: compiled from: JsonWriter.java */
    public static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final b f1985a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public static final b f1986b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private static Pattern f1987c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private static Pattern f1988d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private static Pattern f1989e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private static final /* synthetic */ b[] f1990f;

        static {
            b bVar = new b("json", 0);
            f1985a = bVar;
            b bVar2 = new b("javascript", 1);
            b bVar3 = new b("minimal", 2);
            f1986b = bVar3;
            f1990f = new b[]{bVar, bVar2, bVar3};
            f1987c = Pattern.compile("^[a-zA-Z_$][a-zA-Z_$0-9]*$");
            f1988d = Pattern.compile("^[^\":,}/ ][^:]*$");
            f1989e = Pattern.compile("^[^\":,{\\[\\]/ ][^}\\],]*$");
        }

        private b() {
            throw null;
        }

        public static b valueOf(String str) {
            return (b) Enum.valueOf(b.class, str);
        }

        public static b[] values() {
            return (b[]) f1990f.clone();
        }

        /* JADX WARN: Removed duplicated region for block: B:17:0x0059  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final String a(String str) {
            o0 o0Var = new o0(str);
            o0Var.m('\\', "\\\\");
            o0Var.m('\r', "\\r");
            o0Var.m('\n', "\\n");
            o0Var.m('\t', "\\t");
            int iOrdinal = ordinal();
            if (iOrdinal == 1) {
                if (f1987c.matcher(o0Var).matches()) {
                    return o0Var.toString();
                }
            } else if (iOrdinal == 2) {
                if (!str.contains("//") && !str.contains("/*") && f1988d.matcher(o0Var).matches()) {
                    return o0Var.toString();
                }
                if (f1987c.matcher(o0Var).matches()) {
                }
            }
            StringBuilder sb = new StringBuilder("\"");
            o0Var.m('\"', "\\\"");
            sb.append(o0Var.toString());
            sb.append('\"');
            return sb.toString();
        }

        public final String b(Object obj) {
            int i2;
            if (obj == null) {
                return "null";
            }
            String string = obj.toString();
            if ((obj instanceof Number) || (obj instanceof Boolean)) {
                return string;
            }
            o0 o0Var = new o0(string);
            o0Var.m('\\', "\\\\");
            o0Var.m('\r', "\\r");
            o0Var.m('\n', "\\n");
            o0Var.m('\t', "\\t");
            if (this == f1986b && !string.equals("true") && !string.equals("false") && !string.equals("null") && !string.contains("//") && !string.contains("/*") && (i2 = o0Var.f1854b) > 0 && o0Var.charAt(i2 - 1) != ' ' && f1989e.matcher(o0Var).matches()) {
                return o0Var.toString();
            }
            StringBuilder sb = new StringBuilder("\"");
            o0Var.m('\"', "\\\"");
            sb.append(o0Var.toString());
            sb.append('\"');
            return sb.toString();
        }
    }

    public u(Writer writer) {
        this.f1976a = writer;
    }

    private void e() throws IOException {
        a aVar = this.f1978c;
        if (aVar == null) {
            return;
        }
        if (!aVar.f1982a) {
            if (!this.f1979d) {
                throw new IllegalStateException("Name must be set.");
            }
            this.f1979d = false;
        } else if (aVar.f1983b) {
            this.f1976a.write(44);
        } else {
            aVar.f1983b = true;
        }
    }

    public final void a() throws IOException {
        e();
        com.badlogic.gdx.utils.a<a> aVar = this.f1977b;
        a aVar2 = new a(true);
        this.f1978c = aVar2;
        aVar.a(aVar2);
    }

    public final void b(String str) throws IOException {
        a aVar = this.f1978c;
        if (aVar == null || aVar.f1982a) {
            throw new IllegalStateException("Current item must be an object.");
        }
        boolean z2 = aVar.f1983b;
        Writer writer = this.f1976a;
        if (z2) {
            writer.write(44);
        } else {
            aVar.f1983b = true;
        }
        writer.write(this.f1980e.a(str));
        writer.write(58);
        this.f1979d = true;
    }

    public final void c() throws IOException {
        e();
        com.badlogic.gdx.utils.a<a> aVar = this.f1977b;
        a aVar2 = new a(false);
        this.f1978c = aVar2;
        aVar.a(aVar2);
    }

    @Override // java.io.Writer, java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        while (this.f1977b.f1750b > 0) {
            d();
        }
        this.f1976a.close();
    }

    public final void d() throws IOException {
        if (this.f1979d) {
            throw new IllegalStateException("Expected an object, array, or value since a name was set.");
        }
        com.badlogic.gdx.utils.a<a> aVar = this.f1977b;
        a aVarL = aVar.l();
        u.this.f1976a.write(aVarL.f1982a ? 93 : 125);
        this.f1978c = aVar.f1750b == 0 ? null : aVar.k();
    }

    public final void f(b bVar) {
        this.f1980e = bVar;
    }

    @Override // java.io.Writer, java.io.Flushable
    public final void flush() throws IOException {
        this.f1976a.flush();
    }

    public final void g(boolean z2) {
        this.f1981f = z2;
    }

    public final void h(Object obj) throws IOException {
        if (this.f1981f && ((obj instanceof Long) || (obj instanceof Double) || (obj instanceof BigDecimal) || (obj instanceof BigInteger))) {
            obj = obj.toString();
        } else if (obj instanceof Number) {
            Number number = (Number) obj;
            long jLongValue = number.longValue();
            if (number.doubleValue() == jLongValue) {
                obj = Long.valueOf(jLongValue);
            }
        }
        e();
        this.f1976a.write(this.f1980e.b(obj));
    }

    @Override // java.io.Writer
    public final void write(char[] cArr, int i2, int i3) throws IOException {
        this.f1976a.write(cArr, i2, i3);
    }
}
