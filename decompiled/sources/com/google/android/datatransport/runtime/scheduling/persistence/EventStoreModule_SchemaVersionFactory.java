package com.google.android.datatransport.runtime.scheduling.persistence;

import com.google.android.datatransport.runtime.dagger.internal.Factory;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class EventStoreModule_SchemaVersionFactory implements Factory<Integer> {

    private static final class InstanceHolder {
        private static final EventStoreModule_SchemaVersionFactory INSTANCE = new EventStoreModule_SchemaVersionFactory();

        private InstanceHolder() {
        }
    }

    public static EventStoreModule_SchemaVersionFactory create() {
        return InstanceHolder.INSTANCE;
    }

    public static int schemaVersion() {
        return EventStoreModule.schemaVersion();
    }

    @Override // com.google.android.datatransport.runtime.dagger.internal.Factory, j0.a
    public Integer get() {
        return Integer.valueOf(schemaVersion());
    }
}
