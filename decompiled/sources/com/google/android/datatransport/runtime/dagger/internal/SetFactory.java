package com.google.android.datatransport.runtime.dagger.internal;

import j0.a;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class SetFactory<T> implements Factory<Set<T>> {
    private static final Factory<Set<Object>> EMPTY_FACTORY = InstanceFactory.create(Collections.emptySet());
    private final List<a<Collection<T>>> collectionProviders;
    private final List<a<T>> individualProviders;

    public static final class Builder<T> {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private final List<a<Collection<T>>> collectionProviders;
        private final List<a<T>> individualProviders;

        public Builder<T> addCollectionProvider(a<? extends Collection<? extends T>> aVar) {
            this.collectionProviders.add(aVar);
            return this;
        }

        public Builder<T> addProvider(a<? extends T> aVar) {
            this.individualProviders.add(aVar);
            return this;
        }

        public SetFactory<T> build() {
            return new SetFactory<>(this.individualProviders, this.collectionProviders);
        }

        private Builder(int i2, int i3) {
            this.individualProviders = DaggerCollections.presizedList(i2);
            this.collectionProviders = DaggerCollections.presizedList(i3);
        }
    }

    public static <T> Builder<T> builder(int i2, int i3) {
        return new Builder<>(i2, i3);
    }

    public static <T> Factory<Set<T>> empty() {
        return (Factory<Set<T>>) EMPTY_FACTORY;
    }

    private SetFactory(List<a<T>> list, List<a<Collection<T>>> list2) {
        this.individualProviders = list;
        this.collectionProviders = list2;
    }

    @Override // com.google.android.datatransport.runtime.dagger.internal.Factory, j0.a
    public Set<T> get() {
        int size = this.individualProviders.size();
        ArrayList arrayList = new ArrayList(this.collectionProviders.size());
        int size2 = this.collectionProviders.size();
        for (int i2 = 0; i2 < size2; i2++) {
            Collection<T> collection = this.collectionProviders.get(i2).get();
            size += collection.size();
            arrayList.add(collection);
        }
        HashSet hashSetNewHashSetWithExpectedSize = DaggerCollections.newHashSetWithExpectedSize(size);
        int size3 = this.individualProviders.size();
        for (int i3 = 0; i3 < size3; i3++) {
            hashSetNewHashSetWithExpectedSize.add(Preconditions.checkNotNull(this.individualProviders.get(i3).get()));
        }
        int size4 = arrayList.size();
        for (int i4 = 0; i4 < size4; i4++) {
            Iterator it = ((Collection) arrayList.get(i4)).iterator();
            while (it.hasNext()) {
                hashSetNewHashSetWithExpectedSize.add(Preconditions.checkNotNull(it.next()));
            }
        }
        return Collections.unmodifiableSet(hashSetNewHashSetWithExpectedSize);
    }
}
