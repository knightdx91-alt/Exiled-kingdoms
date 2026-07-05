package com.google.android.datatransport.runtime.scheduling.jobscheduling;

import com.google.android.datatransport.runtime.scheduling.persistence.ClientHealthMetricsStore;
import com.google.android.datatransport.runtime.scheduling.persistence.EventStore;
import com.google.android.datatransport.runtime.synchronization.SynchronizationGuard;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final /* synthetic */ class c implements SynchronizationGuard.CriticalSection {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f2087a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f2088b;

    public /* synthetic */ c(Object obj, int i2) {
        this.f2087a = i2;
        this.f2088b = obj;
    }

    @Override // com.google.android.datatransport.runtime.synchronization.SynchronizationGuard.CriticalSection
    public final Object execute() {
        switch (this.f2087a) {
            case 0:
                return Integer.valueOf(((EventStore) this.f2088b).cleanUp());
            case 1:
                return ((ClientHealthMetricsStore) this.f2088b).loadClientMetrics();
            case 2:
                return ((Uploader) this.f2088b).lambda$logAndUpdateState$6();
            default:
                return ((WorkInitializer) this.f2088b).lambda$ensureContextsScheduled$0();
        }
    }
}
