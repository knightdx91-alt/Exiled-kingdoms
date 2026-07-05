package android.support.v4.util;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

/* JADX INFO: compiled from: MapCollections.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
abstract class g<K, V> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    g<K, V>.b f557a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    g<K, V>.c f558b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    g<K, V>.e f559c;

    /* JADX INFO: compiled from: MapCollections.java */
    final class a<T> implements Iterator<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final int f560a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        int f561b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        int f562c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        boolean f563d = false;

        a(int i2) {
            this.f560a = i2;
            this.f561b = g.this.d();
        }

        @Override // java.util.Iterator
        public final boolean hasNext() {
            return this.f562c < this.f561b;
        }

        @Override // java.util.Iterator
        public final T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T t2 = (T) g.this.b(this.f562c, this.f560a);
            this.f562c++;
            this.f563d = true;
            return t2;
        }

        @Override // java.util.Iterator
        public final void remove() {
            if (!this.f563d) {
                throw new IllegalStateException();
            }
            int i2 = this.f562c - 1;
            this.f562c = i2;
            this.f561b--;
            this.f563d = false;
            g.this.h(i2);
        }
    }

    /* JADX INFO: compiled from: MapCollections.java */
    final class b implements Set<Map.Entry<K, V>> {
        b() {
        }

        @Override // java.util.Set, java.util.Collection
        public final boolean add(Object obj) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Set, java.util.Collection
        public final boolean addAll(Collection<? extends Map.Entry<K, V>> collection) {
            g gVar = g.this;
            int iD = gVar.d();
            for (Map.Entry<K, V> entry : collection) {
                gVar.g(entry.getKey(), entry.getValue());
            }
            return iD != gVar.d();
        }

        @Override // java.util.Set, java.util.Collection
        public final void clear() {
            g.this.a();
        }

        @Override // java.util.Set, java.util.Collection
        public final boolean contains(Object obj) {
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) obj;
            Object key = entry.getKey();
            g gVar = g.this;
            int iE = gVar.e(key);
            if (iE < 0) {
                return false;
            }
            Object objB = gVar.b(iE, 1);
            Object value = entry.getValue();
            return objB == value || (objB != null && objB.equals(value));
        }

        @Override // java.util.Set, java.util.Collection
        public final boolean containsAll(Collection<?> collection) {
            Iterator<?> it = collection.iterator();
            while (it.hasNext()) {
                if (!contains(it.next())) {
                    return false;
                }
            }
            return true;
        }

        @Override // java.util.Set, java.util.Collection
        public final boolean equals(Object obj) {
            return g.j(this, obj);
        }

        @Override // java.util.Set, java.util.Collection
        public final int hashCode() {
            g gVar = g.this;
            int iHashCode = 0;
            for (int iD = gVar.d() - 1; iD >= 0; iD--) {
                Object objB = gVar.b(iD, 0);
                Object objB2 = gVar.b(iD, 1);
                iHashCode += (objB == null ? 0 : objB.hashCode()) ^ (objB2 == null ? 0 : objB2.hashCode());
            }
            return iHashCode;
        }

        @Override // java.util.Set, java.util.Collection
        public final boolean isEmpty() {
            return g.this.d() == 0;
        }

        @Override // java.util.Set, java.util.Collection, java.lang.Iterable
        public final Iterator<Map.Entry<K, V>> iterator() {
            return new d();
        }

        @Override // java.util.Set, java.util.Collection
        public final boolean remove(Object obj) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Set, java.util.Collection
        public final boolean removeAll(Collection<?> collection) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Set, java.util.Collection
        public final boolean retainAll(Collection<?> collection) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Set, java.util.Collection
        public final int size() {
            return g.this.d();
        }

        @Override // java.util.Set, java.util.Collection
        public final Object[] toArray() {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Set, java.util.Collection
        public final <T> T[] toArray(T[] tArr) {
            throw new UnsupportedOperationException();
        }
    }

    /* JADX INFO: compiled from: MapCollections.java */
    final class c implements Set<K> {
        c() {
        }

        @Override // java.util.Set, java.util.Collection
        public final boolean add(K k2) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Set, java.util.Collection
        public final boolean addAll(Collection<? extends K> collection) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Set, java.util.Collection
        public final void clear() {
            g.this.a();
        }

        @Override // java.util.Set, java.util.Collection
        public final boolean contains(Object obj) {
            return g.this.e(obj) >= 0;
        }

        @Override // java.util.Set, java.util.Collection
        public final boolean containsAll(Collection<?> collection) {
            Map<K, V> mapC = g.this.c();
            Iterator<?> it = collection.iterator();
            while (it.hasNext()) {
                if (!mapC.containsKey(it.next())) {
                    return false;
                }
            }
            return true;
        }

        @Override // java.util.Set, java.util.Collection
        public final boolean equals(Object obj) {
            return g.j(this, obj);
        }

        @Override // java.util.Set, java.util.Collection
        public final int hashCode() {
            g gVar = g.this;
            int iHashCode = 0;
            for (int iD = gVar.d() - 1; iD >= 0; iD--) {
                Object objB = gVar.b(iD, 0);
                iHashCode += objB == null ? 0 : objB.hashCode();
            }
            return iHashCode;
        }

        @Override // java.util.Set, java.util.Collection
        public final boolean isEmpty() {
            return g.this.d() == 0;
        }

        @Override // java.util.Set, java.util.Collection, java.lang.Iterable
        public final Iterator<K> iterator() {
            return new a(0);
        }

        @Override // java.util.Set, java.util.Collection
        public final boolean remove(Object obj) {
            g gVar = g.this;
            int iE = gVar.e(obj);
            if (iE < 0) {
                return false;
            }
            gVar.h(iE);
            return true;
        }

        @Override // java.util.Set, java.util.Collection
        public final boolean removeAll(Collection<?> collection) {
            Map<K, V> mapC = g.this.c();
            int size = mapC.size();
            Iterator<?> it = collection.iterator();
            while (it.hasNext()) {
                mapC.remove(it.next());
            }
            return size != mapC.size();
        }

        @Override // java.util.Set, java.util.Collection
        public final boolean retainAll(Collection<?> collection) {
            return g.k(g.this.c(), collection);
        }

        @Override // java.util.Set, java.util.Collection
        public final int size() {
            return g.this.d();
        }

        @Override // java.util.Set, java.util.Collection
        public final <T> T[] toArray(T[] tArr) {
            return (T[]) g.this.l(tArr, 0);
        }

        @Override // java.util.Set, java.util.Collection
        public final Object[] toArray() {
            g gVar = g.this;
            int iD = gVar.d();
            Object[] objArr = new Object[iD];
            for (int i2 = 0; i2 < iD; i2++) {
                objArr[i2] = gVar.b(i2, 0);
            }
            return objArr;
        }
    }

    /* JADX INFO: compiled from: MapCollections.java */
    final class d implements Iterator<Map.Entry<K, V>>, Map.Entry<K, V> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        int f567a;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        boolean f569c = false;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        int f568b = -1;

        d() {
            this.f567a = g.this.d() - 1;
        }

        @Override // java.util.Map.Entry
        public final boolean equals(Object obj) {
            if (!this.f569c) {
                throw new IllegalStateException("This container does not support retaining Map.Entry objects");
            }
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) obj;
            Object key = entry.getKey();
            int i2 = this.f568b;
            g gVar = g.this;
            Object objB = gVar.b(i2, 0);
            if (key != objB && (key == null || !key.equals(objB))) {
                return false;
            }
            Object value = entry.getValue();
            Object objB2 = gVar.b(this.f568b, 1);
            return value == objB2 || (value != null && value.equals(objB2));
        }

        @Override // java.util.Map.Entry
        public final K getKey() {
            if (!this.f569c) {
                throw new IllegalStateException("This container does not support retaining Map.Entry objects");
            }
            return (K) g.this.b(this.f568b, 0);
        }

        @Override // java.util.Map.Entry
        public final V getValue() {
            if (!this.f569c) {
                throw new IllegalStateException("This container does not support retaining Map.Entry objects");
            }
            return (V) g.this.b(this.f568b, 1);
        }

        @Override // java.util.Iterator
        public final boolean hasNext() {
            return this.f568b < this.f567a;
        }

        @Override // java.util.Map.Entry
        public final int hashCode() {
            if (!this.f569c) {
                throw new IllegalStateException("This container does not support retaining Map.Entry objects");
            }
            int i2 = this.f568b;
            g gVar = g.this;
            Object objB = gVar.b(i2, 0);
            Object objB2 = gVar.b(this.f568b, 1);
            return (objB == null ? 0 : objB.hashCode()) ^ (objB2 != null ? objB2.hashCode() : 0);
        }

        @Override // java.util.Iterator
        public final Object next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            this.f568b++;
            this.f569c = true;
            return this;
        }

        @Override // java.util.Iterator
        public final void remove() {
            if (!this.f569c) {
                throw new IllegalStateException();
            }
            g.this.h(this.f568b);
            this.f568b--;
            this.f567a--;
            this.f569c = false;
        }

        @Override // java.util.Map.Entry
        public final V setValue(V v2) {
            if (this.f569c) {
                return (V) g.this.i(this.f568b, v2);
            }
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
        }

        public final String toString() {
            return getKey() + "=" + getValue();
        }
    }

    public static <T> boolean j(Set<T> set, Object obj) {
        if (set == obj) {
            return true;
        }
        if (obj instanceof Set) {
            Set set2 = (Set) obj;
            try {
                if (set.size() == set2.size()) {
                    if (set.containsAll(set2)) {
                        return true;
                    }
                }
                return false;
            } catch (ClassCastException | NullPointerException unused) {
            }
        }
        return false;
    }

    public static <K, V> boolean k(Map<K, V> map, Collection<?> collection) {
        int size = map.size();
        Iterator<K> it = map.keySet().iterator();
        while (it.hasNext()) {
            if (!collection.contains(it.next())) {
                it.remove();
            }
        }
        return size != map.size();
    }

    protected abstract void a();

    protected abstract Object b(int i2, int i3);

    protected abstract Map<K, V> c();

    protected abstract int d();

    protected abstract int e(Object obj);

    protected abstract int f(Object obj);

    protected abstract void g(K k2, V v2);

    protected abstract void h(int i2);

    protected abstract V i(int i2, V v2);

    public final <T> T[] l(T[] tArr, int i2) {
        int iD = d();
        if (tArr.length < iD) {
            tArr = (T[]) ((Object[]) Array.newInstance(tArr.getClass().getComponentType(), iD));
        }
        for (int i3 = 0; i3 < iD; i3++) {
            tArr[i3] = b(i3, i2);
        }
        if (tArr.length > iD) {
            tArr[iD] = null;
        }
        return tArr;
    }

    /* JADX INFO: compiled from: MapCollections.java */
    final class e implements Collection<V> {
        e() {
        }

        @Override // java.util.Collection
        public final boolean add(V v2) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Collection
        public final boolean addAll(Collection<? extends V> collection) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Collection
        public final void clear() {
            g.this.a();
        }

        @Override // java.util.Collection
        public final boolean contains(Object obj) {
            return g.this.f(obj) >= 0;
        }

        @Override // java.util.Collection
        public final boolean containsAll(Collection<?> collection) {
            Iterator<?> it = collection.iterator();
            while (it.hasNext()) {
                if (!contains(it.next())) {
                    return false;
                }
            }
            return true;
        }

        @Override // java.util.Collection
        public final boolean isEmpty() {
            return g.this.d() == 0;
        }

        @Override // java.util.Collection, java.lang.Iterable
        public final Iterator<V> iterator() {
            return new a(1);
        }

        @Override // java.util.Collection
        public final boolean remove(Object obj) {
            g gVar = g.this;
            int iF = gVar.f(obj);
            if (iF < 0) {
                return false;
            }
            gVar.h(iF);
            return true;
        }

        @Override // java.util.Collection
        public final boolean removeAll(Collection<?> collection) {
            g gVar = g.this;
            int iD = gVar.d();
            int i2 = 0;
            boolean z2 = false;
            while (i2 < iD) {
                if (collection.contains(gVar.b(i2, 1))) {
                    gVar.h(i2);
                    i2--;
                    iD--;
                    z2 = true;
                }
                i2++;
            }
            return z2;
        }

        @Override // java.util.Collection
        public final boolean retainAll(Collection<?> collection) {
            g gVar = g.this;
            int iD = gVar.d();
            int i2 = 0;
            boolean z2 = false;
            while (i2 < iD) {
                if (!collection.contains(gVar.b(i2, 1))) {
                    gVar.h(i2);
                    i2--;
                    iD--;
                    z2 = true;
                }
                i2++;
            }
            return z2;
        }

        @Override // java.util.Collection
        public final int size() {
            return g.this.d();
        }

        @Override // java.util.Collection
        public final Object[] toArray() {
            g gVar = g.this;
            int iD = gVar.d();
            Object[] objArr = new Object[iD];
            for (int i2 = 0; i2 < iD; i2++) {
                objArr[i2] = gVar.b(i2, 1);
            }
            return objArr;
        }

        @Override // java.util.Collection
        public final <T> T[] toArray(T[] tArr) {
            return (T[]) g.this.l(tArr, 1);
        }
    }
}
