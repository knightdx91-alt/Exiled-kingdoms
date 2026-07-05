package com.google.android.datatransport.runtime.dagger.internal;

import com.google.android.datatransport.runtime.dagger.Lazy;
import com.google.android.datatransport.runtime.dagger.internal.AbstractMapFactory;
import j0.a;
import java.util.Map;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class MapProviderFactory<K, V> extends AbstractMapFactory<K, V, a<V>> implements Lazy<Map<K, a<V>>> {

    public static final class Builder<K, V> extends AbstractMapFactory.Builder<K, V, a<V>> {
        public MapProviderFactory<K, V> build() {
            return new MapProviderFactory<>(this.map);
        }

        private Builder(int i2) {
            super(i2);
        }

        @Override // com.google.android.datatransport.runtime.dagger.internal.AbstractMapFactory.Builder
        public Builder<K, V> put(K k2, a<V> aVar) {
            super.put((Object) k2, (a) aVar);
            return this;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.android.datatransport.runtime.dagger.internal.AbstractMapFactory.Builder
        public Builder<K, V> putAll(a<Map<K, a<V>>> aVar) {
            super.putAll((a) aVar);
            return this;
        }
    }

    public static <K, V> Builder<K, V> builder(int i2) {
        return new Builder<>(i2);
    }

    private MapProviderFactory(Map<K, a<V>> map) {
        super(map);
    }

    @Override // com.google.android.datatransport.runtime.dagger.internal.AbstractMapFactory, com.google.android.datatransport.runtime.dagger.internal.Factory, j0.a
    public Map<K, a<V>> get() {
        return contributingMap();
    }
}
