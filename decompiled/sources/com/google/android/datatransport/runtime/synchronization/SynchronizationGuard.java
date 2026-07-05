package com.google.android.datatransport.runtime.synchronization;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public interface SynchronizationGuard {

    public interface CriticalSection<T> {
        T execute();
    }

    <T> T runCriticalSection(CriticalSection<T> criticalSection);
}
