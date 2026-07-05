package com.badlogic.gdx.utils;

import com.badlogic.gdx.utils.z;
import java.util.NoSuchElementException;

/* JADX INFO: compiled from: OrderedSet.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class b0<T> extends z<T> {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    final com.badlogic.gdx.utils.a<T> f1775i = new com.badlogic.gdx.utils.a<>();

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    a f1776j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    a f1777k;

    /* JADX INFO: compiled from: OrderedSet.java */
    public static class a<K> extends z.a<K> {

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private com.badlogic.gdx.utils.a<K> f1778f;

        public a(b0<K> b0Var) {
            super(b0Var);
            this.f1778f = b0Var.f1775i;
        }

        @Override // com.badlogic.gdx.utils.z.a
        public final void a() {
            this.f2074c = 0;
            this.f2072a = this.f2073b.f2064a > 0;
        }

        public final com.badlogic.gdx.utils.a<K> b() {
            com.badlogic.gdx.utils.a<K> aVar = new com.badlogic.gdx.utils.a<>(true, this.f2073b.f2064a - this.f2074c);
            c(aVar);
            return aVar;
        }

        public final void c(com.badlogic.gdx.utils.a aVar) {
            com.badlogic.gdx.utils.a<K> aVar2 = this.f1778f;
            int i2 = this.f2074c;
            aVar.c(aVar2, i2, aVar2.f1750b - i2);
            this.f2074c = aVar2.f1750b;
            this.f2072a = false;
        }

        @Override // com.badlogic.gdx.utils.z.a, java.util.Iterator
        public final K next() {
            if (!this.f2072a) {
                throw new NoSuchElementException();
            }
            if (!this.f2076e) {
                throw new m("#iterator() cannot be used nested.");
            }
            K k2 = this.f1778f.get(this.f2074c);
            int i2 = this.f2074c + 1;
            this.f2074c = i2;
            this.f2072a = i2 < this.f2073b.f2064a;
            return k2;
        }

        @Override // com.badlogic.gdx.utils.z.a, java.util.Iterator
        public final void remove() {
            int i2 = this.f2074c;
            if (i2 < 0) {
                throw new IllegalStateException("next must be called before remove.");
            }
            int i3 = i2 - 1;
            this.f2074c = i3;
            ((b0) this.f2073b).m(i3);
        }
    }

    @Override // com.badlogic.gdx.utils.z
    public final void a(int i2) {
        this.f1775i.clear();
        super.a(i2);
    }

    @Override // com.badlogic.gdx.utils.z
    public final boolean add(T t2) {
        if (!super.add(t2)) {
            return false;
        }
        this.f1775i.a(t2);
        return true;
    }

    @Override // com.badlogic.gdx.utils.z
    public final void clear() {
        this.f1775i.clear();
        super.clear();
    }

    @Override // com.badlogic.gdx.utils.z
    public final String i() {
        com.badlogic.gdx.utils.a<T> aVar = this.f1775i;
        if (aVar.f1750b == 0) {
            return "";
        }
        T[] tArr = aVar.f1749a;
        o0 o0Var = new o0(32);
        o0Var.e(tArr[0]);
        for (int i2 = 1; i2 < aVar.f1750b; i2++) {
            o0Var.h(", ");
            o0Var.e(tArr[i2]);
        }
        return o0Var.toString();
    }

    public final void j(b0<T> b0Var) {
        b(b0Var.f2064a);
        com.badlogic.gdx.utils.a<T> aVar = b0Var.f1775i;
        T[] tArr = aVar.f1749a;
        int i2 = aVar.f1750b;
        for (int i3 = 0; i3 < i2; i3++) {
            add(tArr[i3]);
        }
    }

    @Override // com.badlogic.gdx.utils.z, java.lang.Iterable
    /* JADX INFO: renamed from: k, reason: merged with bridge method [inline-methods] */
    public final a<T> iterator() {
        if (this.f1776j == null) {
            this.f1776j = new a(this);
            this.f1777k = new a(this);
        }
        a aVar = this.f1776j;
        if (aVar.f2076e) {
            this.f1777k.a();
            a<T> aVar2 = this.f1777k;
            aVar2.f2076e = true;
            this.f1776j.f2076e = false;
            return aVar2;
        }
        aVar.a();
        a<T> aVar3 = this.f1776j;
        aVar3.f2076e = true;
        this.f1777k.f2076e = false;
        return aVar3;
    }

    public final com.badlogic.gdx.utils.a<T> l() {
        return this.f1775i;
    }

    public final void m(int i2) {
        super.remove(this.f1775i.o(i2));
    }

    @Override // com.badlogic.gdx.utils.z
    public final boolean remove(T t2) {
        if (!super.remove(t2)) {
            return false;
        }
        this.f1775i.q(t2, false);
        return true;
    }

    @Override // com.badlogic.gdx.utils.z
    public final String toString() {
        if (this.f2064a == 0) {
            return "{}";
        }
        T[] tArr = this.f1775i.f1749a;
        StringBuilder sb = new StringBuilder(32);
        sb.append('{');
        sb.append(tArr[0]);
        for (int i2 = 1; i2 < this.f2064a; i2++) {
            sb.append(", ");
            sb.append(tArr[i2]);
        }
        sb.append('}');
        return sb.toString();
    }
}
