package com.google.android.datatransport.runtime;

import com.google.android.datatransport.runtime.dagger.internal.Factory;
import com.google.android.datatransport.runtime.scheduling.Scheduler;
import com.google.android.datatransport.runtime.scheduling.jobscheduling.Uploader;
import com.google.android.datatransport.runtime.scheduling.jobscheduling.WorkInitializer;
import com.google.android.datatransport.runtime.time.Clock;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class TransportRuntime_Factory implements Factory<TransportRuntime> {
    private final j0.a<Clock> eventClockProvider;
    private final j0.a<WorkInitializer> initializerProvider;
    private final j0.a<Scheduler> schedulerProvider;
    private final j0.a<Uploader> uploaderProvider;
    private final j0.a<Clock> uptimeClockProvider;

    public TransportRuntime_Factory(j0.a<Clock> aVar, j0.a<Clock> aVar2, j0.a<Scheduler> aVar3, j0.a<Uploader> aVar4, j0.a<WorkInitializer> aVar5) {
        this.eventClockProvider = aVar;
        this.uptimeClockProvider = aVar2;
        this.schedulerProvider = aVar3;
        this.uploaderProvider = aVar4;
        this.initializerProvider = aVar5;
    }

    public static TransportRuntime_Factory create(j0.a<Clock> aVar, j0.a<Clock> aVar2, j0.a<Scheduler> aVar3, j0.a<Uploader> aVar4, j0.a<WorkInitializer> aVar5) {
        return new TransportRuntime_Factory(aVar, aVar2, aVar3, aVar4, aVar5);
    }

    public static TransportRuntime newInstance(Clock clock, Clock clock2, Scheduler scheduler, Uploader uploader, WorkInitializer workInitializer) {
        return new TransportRuntime(clock, clock2, scheduler, uploader, workInitializer);
    }

    @Override // com.google.android.datatransport.runtime.dagger.internal.Factory, j0.a
    public TransportRuntime get() {
        return newInstance(this.eventClockProvider.get(), this.uptimeClockProvider.get(), this.schedulerProvider.get(), this.uploaderProvider.get(), this.initializerProvider.get());
    }
}
