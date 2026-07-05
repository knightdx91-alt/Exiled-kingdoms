package b;

import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;

/* JADX INFO: compiled from: SafeIterableMap.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class b<K, V> implements Iterable<Map.Entry<K, V>> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private WeakHashMap<Object, Boolean> f1411a = new WeakHashMap<>();

    /* JADX INFO: compiled from: SafeIterableMap.java */
    static class a<K, V> extends AbstractC0018b<K, V> {
    }

    /* JADX INFO: renamed from: b.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: SafeIterableMap.java */
    private static abstract class AbstractC0018b<K, V> implements Iterator<Map.Entry<K, V>> {
        @Override // java.util.Iterator
        public final boolean hasNext() {
            return false;
        }

        @Override // java.util.Iterator
        public final /* bridge */ /* synthetic */ Object next() {
            return null;
        }
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof b)) {
            return false;
        }
        b bVar = (b) obj;
        bVar.getClass();
        iterator();
        bVar.iterator();
        return true;
    }

    @Override // java.lang.Iterable
    public final Iterator<Map.Entry<K, V>> iterator() {
        a aVar = new a();
        this.f1411a.put(aVar, Boolean.FALSE);
        return aVar;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("[");
        iterator();
        sb.append("]");
        return sb.toString();
    }
}
