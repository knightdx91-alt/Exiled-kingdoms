package com.google.android.datatransport.runtime.scheduling.persistence;

import com.google.android.datatransport.runtime.EventInternal;
import com.google.android.datatransport.runtime.TransportContext;
import com.google.auto.value.AutoValue;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@AutoValue
public abstract class PersistedEvent {
    public static PersistedEvent create(long j2, TransportContext transportContext, EventInternal eventInternal) {
        return new AutoValue_PersistedEvent(j2, transportContext, eventInternal);
    }

    public abstract EventInternal getEvent();

    public abstract long getId();

    public abstract TransportContext getTransportContext();
}
