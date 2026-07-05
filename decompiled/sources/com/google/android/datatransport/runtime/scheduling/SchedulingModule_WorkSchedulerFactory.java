package com.google.android.datatransport.runtime.scheduling;

import android.content.Context;
import com.google.android.datatransport.runtime.dagger.internal.Factory;
import com.google.android.datatransport.runtime.dagger.internal.Preconditions;
import com.google.android.datatransport.runtime.scheduling.jobscheduling.SchedulerConfig;
import com.google.android.datatransport.runtime.scheduling.jobscheduling.WorkScheduler;
import com.google.android.datatransport.runtime.scheduling.persistence.EventStore;
import com.google.android.datatransport.runtime.time.Clock;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class SchedulingModule_WorkSchedulerFactory implements Factory<WorkScheduler> {
    private final j0.a<Clock> clockProvider;
    private final j0.a<SchedulerConfig> configProvider;
    private final j0.a<Context> contextProvider;
    private final j0.a<EventStore> eventStoreProvider;

    public SchedulingModule_WorkSchedulerFactory(j0.a<Context> aVar, j0.a<EventStore> aVar2, j0.a<SchedulerConfig> aVar3, j0.a<Clock> aVar4) {
        this.contextProvider = aVar;
        this.eventStoreProvider = aVar2;
        this.configProvider = aVar3;
        this.clockProvider = aVar4;
    }

    public static SchedulingModule_WorkSchedulerFactory create(j0.a<Context> aVar, j0.a<EventStore> aVar2, j0.a<SchedulerConfig> aVar3, j0.a<Clock> aVar4) {
        return new SchedulingModule_WorkSchedulerFactory(aVar, aVar2, aVar3, aVar4);
    }

    public static WorkScheduler workScheduler(Context context, EventStore eventStore, SchedulerConfig schedulerConfig, Clock clock) {
        return (WorkScheduler) Preconditions.checkNotNull(SchedulingModule.workScheduler(context, eventStore, schedulerConfig, clock), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // com.google.android.datatransport.runtime.dagger.internal.Factory, j0.a
    public WorkScheduler get() {
        return workScheduler(this.contextProvider.get(), this.eventStoreProvider.get(), this.configProvider.get(), this.clockProvider.get());
    }
}
