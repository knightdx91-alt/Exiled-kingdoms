package com.google.android.datatransport.runtime.scheduling.jobscheduling;

import com.google.android.datatransport.runtime.dagger.internal.Factory;
import com.google.android.datatransport.runtime.scheduling.persistence.EventStore;
import com.google.android.datatransport.runtime.synchronization.SynchronizationGuard;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class WorkInitializer_Factory implements Factory<WorkInitializer> {
    private final j0.a<Executor> executorProvider;
    private final j0.a<SynchronizationGuard> guardProvider;
    private final j0.a<WorkScheduler> schedulerProvider;
    private final j0.a<EventStore> storeProvider;

    public WorkInitializer_Factory(j0.a<Executor> aVar, j0.a<EventStore> aVar2, j0.a<WorkScheduler> aVar3, j0.a<SynchronizationGuard> aVar4) {
        this.executorProvider = aVar;
        this.storeProvider = aVar2;
        this.schedulerProvider = aVar3;
        this.guardProvider = aVar4;
    }

    public static WorkInitializer_Factory create(j0.a<Executor> aVar, j0.a<EventStore> aVar2, j0.a<WorkScheduler> aVar3, j0.a<SynchronizationGuard> aVar4) {
        return new WorkInitializer_Factory(aVar, aVar2, aVar3, aVar4);
    }

    public static WorkInitializer newInstance(Executor executor, EventStore eventStore, WorkScheduler workScheduler, SynchronizationGuard synchronizationGuard) {
        return new WorkInitializer(executor, eventStore, workScheduler, synchronizationGuard);
    }

    @Override // com.google.android.datatransport.runtime.dagger.internal.Factory, j0.a
    public WorkInitializer get() {
        return newInstance(this.executorProvider.get(), this.storeProvider.get(), this.schedulerProvider.get(), this.guardProvider.get());
    }
}
