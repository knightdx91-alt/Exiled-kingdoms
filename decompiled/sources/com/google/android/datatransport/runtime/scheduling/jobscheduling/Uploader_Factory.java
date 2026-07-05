package com.google.android.datatransport.runtime.scheduling.jobscheduling;

import android.content.Context;
import com.google.android.datatransport.runtime.backends.BackendRegistry;
import com.google.android.datatransport.runtime.dagger.internal.Factory;
import com.google.android.datatransport.runtime.scheduling.persistence.ClientHealthMetricsStore;
import com.google.android.datatransport.runtime.scheduling.persistence.EventStore;
import com.google.android.datatransport.runtime.synchronization.SynchronizationGuard;
import com.google.android.datatransport.runtime.time.Clock;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class Uploader_Factory implements Factory<Uploader> {
    private final j0.a<BackendRegistry> backendRegistryProvider;
    private final j0.a<ClientHealthMetricsStore> clientHealthMetricsStoreProvider;
    private final j0.a<Clock> clockProvider;
    private final j0.a<Context> contextProvider;
    private final j0.a<EventStore> eventStoreProvider;
    private final j0.a<Executor> executorProvider;
    private final j0.a<SynchronizationGuard> guardProvider;
    private final j0.a<Clock> uptimeClockProvider;
    private final j0.a<WorkScheduler> workSchedulerProvider;

    public Uploader_Factory(j0.a<Context> aVar, j0.a<BackendRegistry> aVar2, j0.a<EventStore> aVar3, j0.a<WorkScheduler> aVar4, j0.a<Executor> aVar5, j0.a<SynchronizationGuard> aVar6, j0.a<Clock> aVar7, j0.a<Clock> aVar8, j0.a<ClientHealthMetricsStore> aVar9) {
        this.contextProvider = aVar;
        this.backendRegistryProvider = aVar2;
        this.eventStoreProvider = aVar3;
        this.workSchedulerProvider = aVar4;
        this.executorProvider = aVar5;
        this.guardProvider = aVar6;
        this.clockProvider = aVar7;
        this.uptimeClockProvider = aVar8;
        this.clientHealthMetricsStoreProvider = aVar9;
    }

    public static Uploader_Factory create(j0.a<Context> aVar, j0.a<BackendRegistry> aVar2, j0.a<EventStore> aVar3, j0.a<WorkScheduler> aVar4, j0.a<Executor> aVar5, j0.a<SynchronizationGuard> aVar6, j0.a<Clock> aVar7, j0.a<Clock> aVar8, j0.a<ClientHealthMetricsStore> aVar9) {
        return new Uploader_Factory(aVar, aVar2, aVar3, aVar4, aVar5, aVar6, aVar7, aVar8, aVar9);
    }

    public static Uploader newInstance(Context context, BackendRegistry backendRegistry, EventStore eventStore, WorkScheduler workScheduler, Executor executor, SynchronizationGuard synchronizationGuard, Clock clock, Clock clock2, ClientHealthMetricsStore clientHealthMetricsStore) {
        return new Uploader(context, backendRegistry, eventStore, workScheduler, executor, synchronizationGuard, clock, clock2, clientHealthMetricsStore);
    }

    @Override // com.google.android.datatransport.runtime.dagger.internal.Factory, j0.a
    public Uploader get() {
        return newInstance(this.contextProvider.get(), this.backendRegistryProvider.get(), this.eventStoreProvider.get(), this.workSchedulerProvider.get(), this.executorProvider.get(), this.guardProvider.get(), this.clockProvider.get(), this.uptimeClockProvider.get(), this.clientHealthMetricsStoreProvider.get());
    }
}
