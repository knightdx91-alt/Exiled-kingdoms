package com.google.android.datatransport.runtime.scheduling.persistence;

import com.google.android.datatransport.runtime.firebase.transport.ClientMetrics;
import com.google.android.datatransport.runtime.firebase.transport.LogEventDropped;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public interface ClientHealthMetricsStore {
    ClientMetrics loadClientMetrics();

    void recordLogEventDropped(long j2, LogEventDropped.Reason reason, String str);

    void resetClientMetrics();
}
