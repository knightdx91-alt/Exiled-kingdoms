package com.google.android.datatransport.runtime.dagger.internal;

import j0.a;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
abstract class AbstractMapFactory<K, V, V2> implements Factory<Map<K, V2>> {
    private final Map<K, a<V>> contributingMap;

    public static abstract class Builder<K, V, V2> {
        final LinkedHashMap<K, a<V>> map;

        Builder(int i2) {
            this.map = DaggerCollections.newLinkedHashMapWithExpectedSize(i2);
        }

        Builder<K, V, V2> put(K k2, a<V> aVar) {
            this.map.put((K) Preconditions.checkNotNull(k2, "key"), (a<V>) Preconditions.checkNotNull(aVar, "provider"));
            return this;
        }

        Builder<K, V, V2> putAll(a<Map<K, V2>> aVar) {
            if (aVar instanceof DelegateFactory) {
                return putAll(((DelegateFactory) aVar).getDelegate());
            }
            this.map.putAll(((AbstractMapFactory) aVar).contributingMap);
            return this;
        }
    }

    AbstractMapFactory(Map<K, a<V>> map) {
        this.contributingMap = Collections.unmodifiableMap(map);
    }

    final Map<K, a<V>> contributingMap() {
        return this.contributingMap;
    }

    @Override // com.google.android.datatransport.runtime.dagger.internal.Factory, j0.a
    public abstract /* synthetic */ Object get();
}
