package com.google.android.datatransport.runtime;

import android.content.Context;
import com.google.android.datatransport.runtime.TransportRuntimeComponent;
import com.google.android.datatransport.runtime.backends.CreationContextFactory_Factory;
import com.google.android.datatransport.runtime.backends.MetadataBackendRegistry_Factory;
import com.google.android.datatransport.runtime.dagger.internal.DoubleCheck;
import com.google.android.datatransport.runtime.dagger.internal.Factory;
import com.google.android.datatransport.runtime.dagger.internal.InstanceFactory;
import com.google.android.datatransport.runtime.dagger.internal.Preconditions;
import com.google.android.datatransport.runtime.scheduling.DefaultScheduler;
import com.google.android.datatransport.runtime.scheduling.DefaultScheduler_Factory;
import com.google.android.datatransport.runtime.scheduling.SchedulingConfigModule_ConfigFactory;
import com.google.android.datatransport.runtime.scheduling.SchedulingModule_WorkSchedulerFactory;
import com.google.android.datatransport.runtime.scheduling.jobscheduling.SchedulerConfig;
import com.google.android.datatransport.runtime.scheduling.jobscheduling.Uploader;
import com.google.android.datatransport.runtime.scheduling.jobscheduling.Uploader_Factory;
import com.google.android.datatransport.runtime.scheduling.jobscheduling.WorkInitializer;
import com.google.android.datatransport.runtime.scheduling.jobscheduling.WorkInitializer_Factory;
import com.google.android.datatransport.runtime.scheduling.jobscheduling.WorkScheduler;
import com.google.android.datatransport.runtime.scheduling.persistence.EventStore;
import com.google.android.datatransport.runtime.scheduling.persistence.EventStoreModule_DbNameFactory;
import com.google.android.datatransport.runtime.scheduling.persistence.EventStoreModule_PackageNameFactory;
import com.google.android.datatransport.runtime.scheduling.persistence.EventStoreModule_SchemaVersionFactory;
import com.google.android.datatransport.runtime.scheduling.persistence.EventStoreModule_StoreConfigFactory;
import com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore;
import com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore_Factory;
import com.google.android.datatransport.runtime.scheduling.persistence.SchemaManager_Factory;
import com.google.android.datatransport.runtime.time.TimeModule_EventClockFactory;
import com.google.android.datatransport.runtime.time.TimeModule_UptimeClockFactory;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class DaggerTransportRuntimeComponent extends TransportRuntimeComponent {
    private j0.a<SchedulerConfig> configProvider;
    private j0.a creationContextFactoryProvider;
    private j0.a<DefaultScheduler> defaultSchedulerProvider;
    private j0.a<Executor> executorProvider;
    private j0.a metadataBackendRegistryProvider;
    private j0.a<String> packageNameProvider;
    private j0.a<SQLiteEventStore> sQLiteEventStoreProvider;
    private j0.a schemaManagerProvider;
    private j0.a<Context> setApplicationContextProvider;
    private j0.a<TransportRuntime> transportRuntimeProvider;
    private j0.a<Uploader> uploaderProvider;
    private j0.a<WorkInitializer> workInitializerProvider;
    private j0.a<WorkScheduler> workSchedulerProvider;

    private static final class Builder implements TransportRuntimeComponent.Builder {
        private Context setApplicationContext;

        private Builder() {
        }

        @Override // com.google.android.datatransport.runtime.TransportRuntimeComponent.Builder
        public TransportRuntimeComponent build() {
            Preconditions.checkBuilderRequirement(this.setApplicationContext, Context.class);
            return new DaggerTransportRuntimeComponent(this.setApplicationContext);
        }

        @Override // com.google.android.datatransport.runtime.TransportRuntimeComponent.Builder
        public Builder setApplicationContext(Context context) {
            this.setApplicationContext = (Context) Preconditions.checkNotNull(context);
            return this;
        }
    }

    public static TransportRuntimeComponent.Builder builder() {
        return new Builder();
    }

    private void initialize(Context context) {
        this.executorProvider = DoubleCheck.provider(ExecutionModule_ExecutorFactory.create());
        Factory factoryCreate = InstanceFactory.create(context);
        this.setApplicationContextProvider = factoryCreate;
        CreationContextFactory_Factory creationContextFactory_FactoryCreate = CreationContextFactory_Factory.create(factoryCreate, TimeModule_EventClockFactory.create(), TimeModule_UptimeClockFactory.create());
        this.creationContextFactoryProvider = creationContextFactory_FactoryCreate;
        this.metadataBackendRegistryProvider = DoubleCheck.provider(MetadataBackendRegistry_Factory.create(this.setApplicationContextProvider, creationContextFactory_FactoryCreate));
        this.schemaManagerProvider = SchemaManager_Factory.create(this.setApplicationContextProvider, EventStoreModule_DbNameFactory.create(), EventStoreModule_SchemaVersionFactory.create());
        this.packageNameProvider = DoubleCheck.provider(EventStoreModule_PackageNameFactory.create(this.setApplicationContextProvider));
        this.sQLiteEventStoreProvider = DoubleCheck.provider(SQLiteEventStore_Factory.create(TimeModule_EventClockFactory.create(), TimeModule_UptimeClockFactory.create(), EventStoreModule_StoreConfigFactory.create(), this.schemaManagerProvider, this.packageNameProvider));
        SchedulingConfigModule_ConfigFactory schedulingConfigModule_ConfigFactoryCreate = SchedulingConfigModule_ConfigFactory.create(TimeModule_EventClockFactory.create());
        this.configProvider = schedulingConfigModule_ConfigFactoryCreate;
        SchedulingModule_WorkSchedulerFactory schedulingModule_WorkSchedulerFactoryCreate = SchedulingModule_WorkSchedulerFactory.create(this.setApplicationContextProvider, this.sQLiteEventStoreProvider, schedulingConfigModule_ConfigFactoryCreate, TimeModule_UptimeClockFactory.create());
        this.workSchedulerProvider = schedulingModule_WorkSchedulerFactoryCreate;
        j0.a<Executor> aVar = this.executorProvider;
        j0.a aVar2 = this.metadataBackendRegistryProvider;
        j0.a<SQLiteEventStore> aVar3 = this.sQLiteEventStoreProvider;
        this.defaultSchedulerProvider = DefaultScheduler_Factory.create(aVar, aVar2, schedulingModule_WorkSchedulerFactoryCreate, aVar3, aVar3);
        j0.a<Context> aVar4 = this.setApplicationContextProvider;
        j0.a aVar5 = this.metadataBackendRegistryProvider;
        j0.a<SQLiteEventStore> aVar6 = this.sQLiteEventStoreProvider;
        this.uploaderProvider = Uploader_Factory.create(aVar4, aVar5, aVar6, this.workSchedulerProvider, this.executorProvider, aVar6, TimeModule_EventClockFactory.create(), TimeModule_UptimeClockFactory.create(), this.sQLiteEventStoreProvider);
        j0.a<Executor> aVar7 = this.executorProvider;
        j0.a<SQLiteEventStore> aVar8 = this.sQLiteEventStoreProvider;
        this.workInitializerProvider = WorkInitializer_Factory.create(aVar7, aVar8, this.workSchedulerProvider, aVar8);
        this.transportRuntimeProvider = DoubleCheck.provider(TransportRuntime_Factory.create(TimeModule_EventClockFactory.create(), TimeModule_UptimeClockFactory.create(), this.defaultSchedulerProvider, this.uploaderProvider, this.workInitializerProvider));
    }

    @Override // com.google.android.datatransport.runtime.TransportRuntimeComponent
    EventStore getEventStore() {
        return this.sQLiteEventStoreProvider.get();
    }

    @Override // com.google.android.datatransport.runtime.TransportRuntimeComponent
    TransportRuntime getTransportRuntime() {
        return this.transportRuntimeProvider.get();
    }

    private DaggerTransportRuntimeComponent(Context context) {
        initialize(context);
    }
}
