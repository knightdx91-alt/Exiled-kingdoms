package com.google.android.datatransport.runtime.scheduling.persistence;

import android.content.Context;
import com.google.android.datatransport.runtime.dagger.Binds;
import com.google.android.datatransport.runtime.dagger.Module;
import com.google.android.datatransport.runtime.dagger.Provides;
import com.google.android.datatransport.runtime.synchronization.SynchronizationGuard;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@Module
public abstract class EventStoreModule {
    @Provides
    static String dbName() {
        return "com.google.android.datatransport.events";
    }

    @Provides
    static String packageName(Context context) {
        return context.getPackageName();
    }

    @Provides
    static int schemaVersion() {
        return SchemaManager.SCHEMA_VERSION;
    }

    @Provides
    static EventStoreConfig storeConfig() {
        return EventStoreConfig.DEFAULT;
    }

    @Binds
    abstract ClientHealthMetricsStore clientHealthMetricsStore(SQLiteEventStore sQLiteEventStore);

    @Binds
    abstract EventStore eventStore(SQLiteEventStore sQLiteEventStore);

    @Binds
    abstract SynchronizationGuard synchronizationGuard(SQLiteEventStore sQLiteEventStore);
}
