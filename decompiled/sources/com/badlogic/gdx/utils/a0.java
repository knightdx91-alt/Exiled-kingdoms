package com.badlogic.gdx.utils;

import com.badlogic.gdx.utils.y;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* JADX INFO: compiled from: OrderedMap.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class a0<K, V> extends y<K, V> {

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    final com.badlogic.gdx.utils.a<K> f1761o;

    /* JADX INFO: compiled from: OrderedMap.java */
    public static class a<K, V> extends y.a<K, V> {

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private com.badlogic.gdx.utils.a<K> f1762g;

        public a(a0<K, V> a0Var) {
            super(a0Var);
            this.f1762g = a0Var.f1761o;
        }

        @Override // com.badlogic.gdx.utils.y.a, com.badlogic.gdx.utils.y.d
        public final void b() {
            this.f2062d = -1;
            this.f2061c = 0;
            this.f2059a = this.f2060b.f2043a > 0;
        }

        @Override // com.badlogic.gdx.utils.y.a, java.util.Iterator
        /* JADX INFO: renamed from: c */
        public final y.b next() {
            if (!this.f2059a) {
                throw new NoSuchElementException();
            }
            if (!this.f2063e) {
                throw new m("#iterator() cannot be used nested.");
            }
            int i2 = this.f2061c;
            this.f2062d = i2;
            K k2 = this.f1762g.get(i2);
            y.b<K, V> bVar = this.f2056f;
            bVar.f2057a = k2;
            y<K, V> yVar = this.f2060b;
            bVar.f2058b = yVar.d(k2);
            int i3 = this.f2061c + 1;
            this.f2061c = i3;
            this.f2059a = i3 < yVar.f2043a;
            return bVar;
        }

        @Override // com.badlogic.gdx.utils.y.d, java.util.Iterator
        public final void remove() {
            if (this.f2062d < 0) {
                throw new IllegalStateException("next must be called before remove.");
            }
            this.f2060b.k(this.f2056f.f2057a);
            this.f2061c--;
            this.f2062d = -1;
        }
    }

    /* JADX INFO: compiled from: OrderedMap.java */
    public static class b<K> extends y.c<K> {

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private com.badlogic.gdx.utils.a<K> f1763f;

        public b(a0<K, ?> a0Var) {
            super(a0Var);
            this.f1763f = a0Var.f1761o;
        }

        @Override // com.badlogic.gdx.utils.y.c, com.badlogic.gdx.utils.y.d
        public final void b() {
            this.f2062d = -1;
            this.f2061c = 0;
            this.f2059a = this.f2060b.f2043a > 0;
        }

        @Override // com.badlogic.gdx.utils.y.c
        public final com.badlogic.gdx.utils.a<K> c() {
            com.badlogic.gdx.utils.a<K> aVar = new com.badlogic.gdx.utils.a<>(true, this.f1763f.f1750b - this.f2061c);
            d(aVar);
            return aVar;
        }

        @Override // com.badlogic.gdx.utils.y.c
        public final com.badlogic.gdx.utils.a<K> d(com.badlogic.gdx.utils.a<K> aVar) {
            int i2 = this.f2061c;
            com.badlogic.gdx.utils.a<K> aVar2 = this.f1763f;
            aVar.c(aVar2, i2, aVar2.f1750b - i2);
            this.f2061c = aVar2.f1750b;
            this.f2059a = false;
            return aVar;
        }

        @Override // com.badlogic.gdx.utils.y.c, java.util.Iterator
        public final K next() {
            if (!this.f2059a) {
                throw new NoSuchElementException();
            }
            if (!this.f2063e) {
                throw new m("#iterator() cannot be used nested.");
            }
            K k2 = this.f1763f.get(this.f2061c);
            int i2 = this.f2061c;
            this.f2062d = i2;
            int i3 = i2 + 1;
            this.f2061c = i3;
            this.f2059a = i3 < this.f2060b.f2043a;
            return k2;
        }

        @Override // com.badlogic.gdx.utils.y.d, java.util.Iterator
        public final void remove() {
            int i2 = this.f2062d;
            if (i2 < 0) {
                throw new IllegalStateException("next must be called before remove.");
            }
            ((a0) this.f2060b).o(i2);
            this.f2061c = this.f2062d;
            this.f2062d = -1;
        }
    }

    /* JADX INFO: compiled from: OrderedMap.java */
    public static class c<V> extends y.e<V> {

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private com.badlogic.gdx.utils.a f1764f;

        public c(a0<?, V> a0Var) {
            super(a0Var);
            this.f1764f = a0Var.f1761o;
        }

        @Override // com.badlogic.gdx.utils.y.e, com.badlogic.gdx.utils.y.d
        public final void b() {
            this.f2062d = -1;
            this.f2061c = 0;
            this.f2059a = this.f2060b.f2043a > 0;
        }

        @Override // com.badlogic.gdx.utils.y.e
        public final com.badlogic.gdx.utils.a<V> c() {
            com.badlogic.gdx.utils.a<V> aVar = new com.badlogic.gdx.utils.a<>(true, this.f1764f.f1750b - this.f2061c);
            d(aVar);
            return aVar;
        }

        @Override // com.badlogic.gdx.utils.y.e
        public final com.badlogic.gdx.utils.a<V> d(com.badlogic.gdx.utils.a<V> aVar) {
            com.badlogic.gdx.utils.a aVar2 = this.f1764f;
            int i2 = aVar2.f1750b;
            aVar.f(i2 - this.f2061c);
            Object[] objArr = aVar2.f1749a;
            for (int i3 = this.f2061c; i3 < i2; i3++) {
                aVar.a(this.f2060b.d(objArr[i3]));
            }
            this.f2062d = i2 - 1;
            this.f2061c = i2;
            this.f2059a = false;
            return aVar;
        }

        @Override // com.badlogic.gdx.utils.y.e, java.util.Iterator
        public final V next() {
            if (!this.f2059a) {
                throw new NoSuchElementException();
            }
            if (!this.f2063e) {
                throw new m("#iterator() cannot be used nested.");
            }
            Object obj = this.f1764f.get(this.f2061c);
            y<K, V> yVar = this.f2060b;
            V vD = yVar.d(obj);
            int i2 = this.f2061c;
            this.f2062d = i2;
            int i3 = i2 + 1;
            this.f2061c = i3;
            this.f2059a = i3 < yVar.f2043a;
            return vD;
        }

        @Override // com.badlogic.gdx.utils.y.d, java.util.Iterator
        public final void remove() {
            int i2 = this.f2062d;
            if (i2 < 0) {
                throw new IllegalStateException("next must be called before remove.");
            }
            ((a0) this.f2060b).o(i2);
            this.f2061c = this.f2062d;
            this.f2062d = -1;
        }
    }

    public a0() {
        this.f1761o = new com.badlogic.gdx.utils.a<>();
    }

    @Override // com.badlogic.gdx.utils.y
    public final y.a<K, V> b() {
        if (this.f2050h == null) {
            this.f2050h = new a(this);
            this.f2051i = new a(this);
        }
        y.a aVar = this.f2050h;
        if (aVar.f2063e) {
            this.f2051i.b();
            y.a<K, V> aVar2 = this.f2051i;
            aVar2.f2063e = true;
            this.f2050h.f2063e = false;
            return aVar2;
        }
        aVar.b();
        y.a<K, V> aVar3 = this.f2050h;
        aVar3.f2063e = true;
        this.f2051i.f2063e = false;
        return aVar3;
    }

    @Override // com.badlogic.gdx.utils.y
    public final void clear() {
        this.f1761o.clear();
        super.clear();
    }

    @Override // com.badlogic.gdx.utils.y
    /* JADX INFO: renamed from: f */
    public final y.a<K, V> iterator() {
        return b();
    }

    @Override // com.badlogic.gdx.utils.y
    public final y.c<K> g() {
        if (this.f2054l == null) {
            this.f2054l = new b(this);
            this.f2055m = new b(this);
        }
        y.c cVar = this.f2054l;
        if (cVar.f2063e) {
            this.f2055m.b();
            y.c<K> cVar2 = this.f2055m;
            cVar2.f2063e = true;
            this.f2054l.f2063e = false;
            return cVar2;
        }
        cVar.b();
        y.c<K> cVar3 = this.f2054l;
        cVar3.f2063e = true;
        this.f2055m.f2063e = false;
        return cVar3;
    }

    @Override // com.badlogic.gdx.utils.y, java.lang.Iterable
    public final Iterator iterator() {
        return b();
    }

    @Override // com.badlogic.gdx.utils.y
    public final V j(K k2, V v2) {
        int iH = h(k2);
        if (iH >= 0) {
            V[] vArr = this.f2045c;
            V v3 = vArr[iH];
            vArr[iH] = v2;
            return v3;
        }
        int i2 = -(iH + 1);
        this.f2044b[i2] = k2;
        this.f2045c[i2] = v2;
        this.f1761o.a(k2);
        int i3 = this.f2043a + 1;
        this.f2043a = i3;
        if (i3 < this.f2047e) {
            return null;
        }
        l(this.f2044b.length << 1);
        return null;
    }

    @Override // com.badlogic.gdx.utils.y
    public final V k(K k2) {
        this.f1761o.q(k2, false);
        return (V) super.k(k2);
    }

    @Override // com.badlogic.gdx.utils.y
    protected final String m() {
        if (this.f2043a == 0) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(32);
        sb.append('{');
        com.badlogic.gdx.utils.a<K> aVar = this.f1761o;
        int i2 = aVar.f1750b;
        for (int i3 = 0; i3 < i2; i3++) {
            K k2 = aVar.get(i3);
            if (i3 > 0) {
                sb.append(", ");
            }
            Object obj = "(this)";
            sb.append(k2 == this ? "(this)" : k2);
            sb.append('=');
            V vD = d(k2);
            if (vD != this) {
                obj = vD;
            }
            sb.append(obj);
        }
        sb.append('}');
        return sb.toString();
    }

    @Override // com.badlogic.gdx.utils.y
    public final y.e<V> n() {
        if (this.f2052j == null) {
            this.f2052j = new c(this);
            this.f2053k = new c(this);
        }
        y.e eVar = this.f2052j;
        if (eVar.f2063e) {
            this.f2053k.b();
            y.e<V> eVar2 = this.f2053k;
            eVar2.f2063e = true;
            this.f2052j.f2063e = false;
            return eVar2;
        }
        eVar.b();
        y.e<V> eVar3 = this.f2052j;
        eVar3.f2063e = true;
        this.f2053k.f2063e = false;
        return eVar3;
    }

    public final void o(int i2) {
        super.k(this.f1761o.o(i2));
    }

    public a0(int i2) {
        super(i2, 0);
        this.f1761o = new com.badlogic.gdx.utils.a<>(true, i2);
    }
}
