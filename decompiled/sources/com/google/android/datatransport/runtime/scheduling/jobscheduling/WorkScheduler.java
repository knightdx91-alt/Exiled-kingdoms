package com.google.android.datatransport.runtime.scheduling.jobscheduling;

import com.google.android.datatransport.runtime.TransportContext;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public interface WorkScheduler {
    void schedule(TransportContext transportContext, int i2);

    void schedule(TransportContext transportContext, int i2, boolean z2);
}
