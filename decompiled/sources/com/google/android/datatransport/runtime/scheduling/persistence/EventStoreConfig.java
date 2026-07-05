package com.google.android.datatransport.runtime.scheduling.persistence;

import com.google.android.datatransport.runtime.scheduling.persistence.AutoValue_EventStoreConfig;
import com.google.auto.value.AutoValue;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@AutoValue
abstract class EventStoreConfig {
    private static final long MAX_DB_STORAGE_SIZE_IN_BYTES = 10485760;
    private static final int LOAD_BATCH_SIZE = 200;
    private static final int LOCK_TIME_OUT_MS = 10000;
    private static final long DURATION_ONE_WEEK_MS = 604800000;
    private static final int MAX_BLOB_BYTE_SIZE_PER_ROW = 81920;
    static final EventStoreConfig DEFAULT = builder().setMaxStorageSizeInBytes(MAX_DB_STORAGE_SIZE_IN_BYTES).setLoadBatchSize(LOAD_BATCH_SIZE).setCriticalSectionEnterTimeoutMs(LOCK_TIME_OUT_MS).setEventCleanUpAge(DURATION_ONE_WEEK_MS).setMaxBlobByteSizePerRow(MAX_BLOB_BYTE_SIZE_PER_ROW).build();

    @AutoValue.Builder
    static abstract class Builder {
        Builder() {
        }

        abstract EventStoreConfig build();

        abstract Builder setCriticalSectionEnterTimeoutMs(int i2);

        abstract Builder setEventCleanUpAge(long j2);

        abstract Builder setLoadBatchSize(int i2);

        abstract Builder setMaxBlobByteSizePerRow(int i2);

        abstract Builder setMaxStorageSizeInBytes(long j2);
    }

    EventStoreConfig() {
    }

    static Builder builder() {
        return new AutoValue_EventStoreConfig.Builder();
    }

    abstract int getCriticalSectionEnterTimeoutMs();

    abstract long getEventCleanUpAge();

    abstract int getLoadBatchSize();

    abstract int getMaxBlobByteSizePerRow();

    abstract long getMaxStorageSizeInBytes();

    Builder toBuilder() {
        return builder().setMaxStorageSizeInBytes(getMaxStorageSizeInBytes()).setLoadBatchSize(getLoadBatchSize()).setCriticalSectionEnterTimeoutMs(getCriticalSectionEnterTimeoutMs()).setEventCleanUpAge(getEventCleanUpAge()).setMaxBlobByteSizePerRow(getMaxBlobByteSizePerRow());
    }
}
