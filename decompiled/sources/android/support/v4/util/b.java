package android.support.v4.util;

import android.support.v4.util.g;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

/* JADX INFO: compiled from: ArrayMap.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class b<K, V> extends i<K, V> implements Map<K, V> {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    a f539h;

    @Override // java.util.Map
    public final Set<Map.Entry<K, V>> entrySet() {
        if (this.f539h == null) {
            this.f539h = new a(this, 0);
        }
        a aVar = this.f539h;
        if (aVar.f557a == null) {
            aVar.f557a = new g.b();
        }
        return aVar.f557a;
    }

    public final void k(Collection collection) {
        g.k(this, collection);
    }

    @Override // java.util.Map
    public final Set<K> keySet() {
        if (this.f539h == null) {
            this.f539h = new a(this, 0);
        }
        a aVar = this.f539h;
        if (aVar.f558b == null) {
            aVar.f558b = new g.c();
        }
        return aVar.f558b;
    }

    @Override // java.util.Map
    public final void putAll(Map<? extends K, ? extends V> map) {
        b(map.size() + this.f580c);
        for (Map.Entry<? extends K, ? extends V> entry : map.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    @Override // java.util.Map
    public final Collection<V> values() {
        if (this.f539h == null) {
            this.f539h = new a(this, 0);
        }
        a aVar = this.f539h;
        if (aVar.f559c == null) {
            aVar.f559c = new g.e();
        }
        return aVar.f559c;
    }
}
