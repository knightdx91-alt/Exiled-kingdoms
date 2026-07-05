package com.badlogic.gdx.utils;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.utils.u;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* JADX INFO: compiled from: JsonValue.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class t implements Iterable<t> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private c f1953a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private String f1954b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private double f1955c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private long f1956d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public String f1957e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public t f1958f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public t f1959g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public t f1960h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public t f1961i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f1962j;

    /* JADX INFO: compiled from: JsonValue.java */
    public class a implements Iterator<t>, Iterable<t> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        t f1963a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        t f1964b;

        public a() {
            this.f1963a = t.this.f1958f;
        }

        @Override // java.util.Iterator
        public final boolean hasNext() {
            return this.f1963a != null;
        }

        @Override // java.lang.Iterable
        public final Iterator<t> iterator() {
            return this;
        }

        @Override // java.util.Iterator
        public final t next() {
            t tVar = this.f1963a;
            this.f1964b = tVar;
            if (tVar == null) {
                throw new NoSuchElementException();
            }
            this.f1963a = tVar.f1960h;
            return tVar;
        }

        @Override // java.util.Iterator
        public final void remove() {
            t tVar = this.f1964b;
            t tVar2 = tVar.f1961i;
            t tVar3 = t.this;
            if (tVar2 == null) {
                t tVar4 = tVar.f1960h;
                tVar3.f1958f = tVar4;
                if (tVar4 != null) {
                    tVar4.f1961i = null;
                }
            } else {
                tVar2.f1960h = tVar.f1960h;
                t tVar5 = tVar.f1960h;
                if (tVar5 != null) {
                    tVar5.f1961i = tVar2;
                }
            }
            tVar3.f1962j--;
        }
    }

    /* JADX INFO: compiled from: JsonValue.java */
    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public u.b f1966a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f1967b;
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* JADX INFO: compiled from: JsonValue.java */
    public static final class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final c f1968a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public static final c f1969b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public static final c f1970c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public static final c f1971d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public static final c f1972e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public static final c f1973f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public static final c f1974g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        private static final /* synthetic */ c[] f1975h;

        static {
            c cVar = new c("object", 0);
            f1968a = cVar;
            c cVar2 = new c("array", 1);
            f1969b = cVar2;
            c cVar3 = new c("stringValue", 2);
            f1970c = cVar3;
            c cVar4 = new c("doubleValue", 3);
            f1971d = cVar4;
            c cVar5 = new c("longValue", 4);
            f1972e = cVar5;
            c cVar6 = new c("booleanValue", 5);
            f1973f = cVar6;
            c cVar7 = new c("nullValue", 6);
            f1974g = cVar7;
            f1975h = new c[]{cVar, cVar2, cVar3, cVar4, cVar5, cVar6, cVar7};
        }

        private c() {
            throw null;
        }

        public static c valueOf(String str) {
            return (c) Enum.valueOf(c.class, str);
        }

        public static c[] values() {
            return (c[]) f1975h.clone();
        }
    }

    public t(c cVar) {
        this.f1953a = cVar;
    }

    private static void p(int i2, o0 o0Var) {
        for (int i3 = 0; i3 < i2; i3++) {
            o0Var.g('\t');
        }
    }

    private static void x(t tVar, o0 o0Var, int i2, b bVar) {
        boolean z2;
        u.b bVar2 = bVar.f1966a;
        boolean z3 = false;
        if (tVar.t()) {
            t tVar2 = tVar.f1958f;
            if (tVar2 == null) {
                o0Var.h("{}");
                return;
            }
            while (true) {
                if (tVar2 == null) {
                    z3 = true;
                    break;
                } else if (tVar2.t() || tVar2.q()) {
                    break;
                } else {
                    tVar2 = tVar2.f1960h;
                }
            }
            boolean z4 = !z3;
            int i3 = o0Var.f1854b;
            loop1: while (true) {
                o0Var.h(z4 ? "{\n" : "{ ");
                for (t tVar3 = tVar.f1958f; tVar3 != null; tVar3 = tVar3.f1960h) {
                    if (z4) {
                        p(i2, o0Var);
                    }
                    o0Var.h(bVar2.a(tVar3.f1957e));
                    o0Var.h(": ");
                    x(tVar3, o0Var, i2 + 1, bVar);
                    if ((!z4 || bVar2 != u.b.f1986b) && tVar3.f1960h != null) {
                        o0Var.g(',');
                    }
                    o0Var.g(z4 ? '\n' : ' ');
                    if (z4 || o0Var.f1854b - i3 <= bVar.f1967b) {
                    }
                }
                o0Var.n(i3);
                z4 = true;
            }
            if (z4) {
                p(i2 - 1, o0Var);
            }
            o0Var.g('}');
            return;
        }
        if (!tVar.q()) {
            if (tVar.u()) {
                o0Var.h(bVar2.b(tVar.j()));
                return;
            }
            c cVar = tVar.f1953a;
            if (cVar == c.f1971d) {
                double dC = tVar.c();
                double dG = tVar.g();
                if (dC == dG) {
                    dC = dG;
                }
                o0Var.h(Double.toString(dC));
                return;
            }
            if (cVar == c.f1972e) {
                o0Var.b(tVar.g());
                return;
            }
            if (tVar.r()) {
                o0Var.h(tVar.a() ? "true" : "false");
                return;
            } else {
                if (tVar.f1953a != c.f1974g) {
                    throw new h0("Unknown object type: " + tVar);
                }
                o0Var.h("null");
                return;
            }
        }
        t tVar4 = tVar.f1958f;
        if (tVar4 == null) {
            o0Var.h("[]");
            return;
        }
        while (tVar4 != null) {
            if (tVar4.t() || tVar4.q()) {
                z2 = false;
                break;
            }
            tVar4 = tVar4.f1960h;
        }
        z2 = true;
        boolean z5 = !z2;
        t tVar5 = tVar.f1958f;
        while (true) {
            if (tVar5 == null) {
                break;
            }
            if (!tVar5.s()) {
                z3 = true;
                break;
            }
            tVar5 = tVar5.f1960h;
        }
        int i4 = o0Var.f1854b;
        loop5: while (true) {
            o0Var.h(z5 ? "[\n" : "[ ");
            for (t tVar6 = tVar.f1958f; tVar6 != null; tVar6 = tVar6.f1960h) {
                if (z5) {
                    p(i2, o0Var);
                }
                x(tVar6, o0Var, i2 + 1, bVar);
                if ((!z5 || bVar2 != u.b.f1986b) && tVar6.f1960h != null) {
                    o0Var.g(',');
                }
                o0Var.g(z5 ? '\n' : ' ');
                if (!z3 || z5 || o0Var.f1854b - i4 <= bVar.f1967b) {
                }
            }
            o0Var.n(i4);
            z5 = true;
        }
        if (z5) {
            p(i2 - 1, o0Var);
        }
        o0Var.g(']');
    }

    public final void A(String str, long j2) {
        this.f1956d = j2;
        this.f1955c = j2;
        this.f1954b = str;
        this.f1953a = c.f1972e;
    }

    public final String B() {
        t tVar = this.f1959g;
        String str = "[]";
        c cVar = c.f1969b;
        if (tVar == null) {
            c cVar2 = this.f1953a;
            return cVar2 == cVar ? "[]" : cVar2 == c.f1968a ? "{}" : "";
        }
        if (tVar.f1953a == cVar) {
            t tVar2 = tVar.f1958f;
            int i2 = 0;
            while (true) {
                if (tVar2 == null) {
                    break;
                }
                if (tVar2 == this) {
                    str = "[" + i2 + "]";
                    break;
                }
                tVar2 = tVar2.f1960h;
                i2++;
            }
        } else if (this.f1957e.indexOf(46) != -1) {
            str = ".\"" + this.f1957e.replace("\"", "\\\"") + "\"";
        } else {
            str = "." + this.f1957e;
        }
        return this.f1959g.B() + str;
    }

    public final boolean a() {
        int iOrdinal = this.f1953a.ordinal();
        if (iOrdinal == 2) {
            return this.f1954b.equalsIgnoreCase("true");
        }
        if (iOrdinal == 3) {
            return this.f1955c != 0.0d;
        }
        if (iOrdinal == 4) {
            return this.f1956d != 0;
        }
        if (iOrdinal == 5) {
            return this.f1956d != 0;
        }
        throw new IllegalStateException("Value cannot be converted to boolean: " + this.f1953a);
    }

    public final byte b() {
        int iOrdinal = this.f1953a.ordinal();
        if (iOrdinal == 2) {
            return Byte.parseByte(this.f1954b);
        }
        if (iOrdinal == 3) {
            return (byte) this.f1955c;
        }
        if (iOrdinal == 4) {
            return (byte) this.f1956d;
        }
        if (iOrdinal == 5) {
            return this.f1956d != 0 ? (byte) 1 : (byte) 0;
        }
        throw new IllegalStateException("Value cannot be converted to byte: " + this.f1953a);
    }

    public final double c() {
        int iOrdinal = this.f1953a.ordinal();
        if (iOrdinal == 2) {
            return Double.parseDouble(this.f1954b);
        }
        if (iOrdinal == 3) {
            return this.f1955c;
        }
        if (iOrdinal == 4) {
            return this.f1956d;
        }
        if (iOrdinal == 5) {
            return this.f1956d != 0 ? 1.0d : 0.0d;
        }
        throw new IllegalStateException("Value cannot be converted to double: " + this.f1953a);
    }

    public final float d() {
        int iOrdinal = this.f1953a.ordinal();
        if (iOrdinal == 2) {
            return Float.parseFloat(this.f1954b);
        }
        if (iOrdinal == 3) {
            return (float) this.f1955c;
        }
        if (iOrdinal == 4) {
            return this.f1956d;
        }
        if (iOrdinal == 5) {
            return this.f1956d != 0 ? 1.0f : 0.0f;
        }
        throw new IllegalStateException("Value cannot be converted to float: " + this.f1953a);
    }

    public final float[] e() {
        float f2;
        if (this.f1953a != c.f1969b) {
            throw new IllegalStateException("Value is not an array: " + this.f1953a);
        }
        float[] fArr = new float[this.f1962j];
        t tVar = this.f1958f;
        int i2 = 0;
        while (tVar != null) {
            int iOrdinal = tVar.f1953a.ordinal();
            if (iOrdinal == 2) {
                f2 = Float.parseFloat(tVar.f1954b);
            } else if (iOrdinal == 3) {
                f2 = (float) tVar.f1955c;
            } else if (iOrdinal == 4) {
                f2 = tVar.f1956d;
            } else {
                if (iOrdinal != 5) {
                    throw new IllegalStateException("Value cannot be converted to float: " + tVar.f1953a);
                }
                f2 = tVar.f1956d != 0 ? 1.0f : 0.0f;
            }
            fArr[i2] = f2;
            tVar = tVar.f1960h;
            i2++;
        }
        return fArr;
    }

    public final int f() {
        int iOrdinal = this.f1953a.ordinal();
        if (iOrdinal == 2) {
            return Integer.parseInt(this.f1954b);
        }
        if (iOrdinal == 3) {
            return (int) this.f1955c;
        }
        if (iOrdinal == 4) {
            return (int) this.f1956d;
        }
        if (iOrdinal == 5) {
            return this.f1956d != 0 ? 1 : 0;
        }
        throw new IllegalStateException("Value cannot be converted to int: " + this.f1953a);
    }

    public final long g() {
        int iOrdinal = this.f1953a.ordinal();
        if (iOrdinal == 2) {
            return Long.parseLong(this.f1954b);
        }
        if (iOrdinal == 3) {
            return (long) this.f1955c;
        }
        if (iOrdinal == 4) {
            return this.f1956d;
        }
        if (iOrdinal == 5) {
            return this.f1956d != 0 ? 1L : 0L;
        }
        throw new IllegalStateException("Value cannot be converted to long: " + this.f1953a);
    }

    public final short h() {
        int iOrdinal = this.f1953a.ordinal();
        if (iOrdinal == 2) {
            return Short.parseShort(this.f1954b);
        }
        if (iOrdinal == 3) {
            return (short) this.f1955c;
        }
        if (iOrdinal == 4) {
            return (short) this.f1956d;
        }
        if (iOrdinal == 5) {
            return this.f1956d != 0 ? (short) 1 : (short) 0;
        }
        throw new IllegalStateException("Value cannot be converted to short: " + this.f1953a);
    }

    public final short[] i() {
        short s;
        int i2;
        if (this.f1953a != c.f1969b) {
            throw new IllegalStateException("Value is not an array: " + this.f1953a);
        }
        short[] sArr = new short[this.f1962j];
        t tVar = this.f1958f;
        int i3 = 0;
        while (tVar != null) {
            int iOrdinal = tVar.f1953a.ordinal();
            if (iOrdinal != 2) {
                if (iOrdinal == 3) {
                    i2 = (int) tVar.f1955c;
                } else if (iOrdinal == 4) {
                    i2 = (int) tVar.f1956d;
                } else {
                    if (iOrdinal != 5) {
                        throw new IllegalStateException("Value cannot be converted to short: " + tVar.f1953a);
                    }
                    s = tVar.f1956d != 0 ? (short) 1 : (short) 0;
                }
                s = (short) i2;
            } else {
                s = Short.parseShort(tVar.f1954b);
            }
            sArr[i3] = s;
            tVar = tVar.f1960h;
            i3++;
        }
        return sArr;
    }

    @Override // java.lang.Iterable
    public final Iterator<t> iterator() {
        return new a();
    }

    public final String j() {
        int iOrdinal = this.f1953a.ordinal();
        if (iOrdinal == 2) {
            return this.f1954b;
        }
        if (iOrdinal == 3) {
            String str = this.f1954b;
            return str != null ? str : Double.toString(this.f1955c);
        }
        if (iOrdinal == 4) {
            String str2 = this.f1954b;
            return str2 != null ? str2 : Long.toString(this.f1956d);
        }
        if (iOrdinal == 5) {
            return this.f1956d != 0 ? "true" : "false";
        }
        if (iOrdinal == 6) {
            return null;
        }
        throw new IllegalStateException("Value cannot be converted to string: " + this.f1953a);
    }

    public final t k(String str) {
        t tVar = this.f1958f;
        while (tVar != null) {
            String str2 = tVar.f1957e;
            if (str2 != null && str2.equalsIgnoreCase(str)) {
                break;
            }
            tVar = tVar.f1960h;
        }
        return tVar;
    }

    public final float l(int i2) {
        t tVar = this.f1958f;
        while (tVar != null && i2 > 0) {
            i2--;
            tVar = tVar.f1960h;
        }
        if (tVar != null) {
            return tVar.d();
        }
        throw new IllegalArgumentException("Indexed value not found: " + this.f1957e);
    }

    public final float m(String str, float f2) {
        t tVarK = k(str);
        return (tVarK == null || !tVarK.v() || tVarK.f1953a == c.f1974g) ? f2 : tVarK.d();
    }

    public final short n(int i2) {
        t tVar = this.f1958f;
        while (tVar != null && i2 > 0) {
            i2--;
            tVar = tVar.f1960h;
        }
        if (tVar != null) {
            return tVar.h();
        }
        throw new IllegalArgumentException("Indexed value not found: " + this.f1957e);
    }

    public final String o(String str, String str2) {
        t tVarK = k(str);
        return (tVarK == null || !tVarK.v() || tVarK.f1953a == c.f1974g) ? str2 : tVarK.j();
    }

    public final boolean q() {
        return this.f1953a == c.f1969b;
    }

    public final boolean r() {
        return this.f1953a == c.f1973f;
    }

    public final boolean s() {
        c cVar = this.f1953a;
        return cVar == c.f1971d || cVar == c.f1972e;
    }

    public final boolean t() {
        return this.f1953a == c.f1968a;
    }

    public final String toString() {
        String strM;
        if (v()) {
            if (this.f1957e == null) {
                return j();
            }
            return this.f1957e + ": " + j();
        }
        StringBuilder sb = new StringBuilder();
        if (this.f1957e == null) {
            strM = "";
        } else {
            strM = a.a.m(this.f1957e, ": ", new StringBuilder());
        }
        sb.append(strM);
        u.b bVar = u.b.f1986b;
        b bVar2 = new b();
        bVar2.f1966a = bVar;
        bVar2.f1967b = 0;
        sb.append(w(bVar2));
        return sb.toString();
    }

    public final boolean u() {
        return this.f1953a == c.f1970c;
    }

    public final boolean v() {
        int iOrdinal = this.f1953a.ordinal();
        return iOrdinal == 2 || iOrdinal == 3 || iOrdinal == 4 || iOrdinal == 5 || iOrdinal == 6;
    }

    public final String w(b bVar) {
        o0 o0Var = new o0(GL20.GL_NEVER);
        x(this, o0Var, 0, bVar);
        return o0Var.toString();
    }

    public final t y(String str) {
        t tVar = this.f1958f;
        while (tVar != null) {
            String str2 = tVar.f1957e;
            if (str2 != null && str2.equalsIgnoreCase(str)) {
                break;
            }
            tVar = tVar.f1960h;
        }
        if (tVar != null) {
            return tVar;
        }
        throw new IllegalArgumentException("Child not found with name: ".concat(str));
    }

    public final void z(String str, double d2) {
        this.f1955c = d2;
        this.f1956d = (long) d2;
        this.f1954b = str;
        this.f1953a = c.f1971d;
    }

    public t(String str) {
        this.f1954b = str;
        this.f1953a = str == null ? c.f1974g : c.f1970c;
    }

    public t(double d2) {
        z(null, d2);
    }

    public t(long j2) {
        A(null, j2);
    }

    public t(boolean z2) {
        this.f1956d = z2 ? 1L : 0L;
        this.f1953a = c.f1973f;
    }
}
