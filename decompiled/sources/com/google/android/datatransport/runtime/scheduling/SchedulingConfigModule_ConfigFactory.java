package com.google.android.datatransport.runtime.scheduling;

import com.google.android.datatransport.runtime.dagger.internal.Factory;
import com.google.android.datatransport.runtime.dagger.internal.Preconditions;
import com.google.android.datatransport.runtime.scheduling.jobscheduling.SchedulerConfig;
import com.google.android.datatransport.runtime.time.Clock;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class SchedulingConfigModule_ConfigFactory implements Factory<SchedulerConfig> {
    private final j0.a<Clock> clockProvider;

    public SchedulingConfigModule_ConfigFactory(j0.a<Clock> aVar) {
        this.clockProvider = aVar;
    }

    public static SchedulerConfig config(Clock clock) {
        return (SchedulerConfig) Preconditions.checkNotNull(SchedulingConfigModule.config(clock), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static SchedulingConfigModule_ConfigFactory create(j0.a<Clock> aVar) {
        return new SchedulingConfigModule_ConfigFactory(aVar);
    }

    @Override // com.google.android.datatransport.runtime.dagger.internal.Factory, j0.a
    public SchedulerConfig get() {
        return config(this.clockProvider.get());
    }
}
