package com.google.android.datatransport.runtime;

import com.google.android.datatransport.Priority;
import com.google.android.datatransport.Transport;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class ForcedSender {
    private ForcedSender() {
    }

    private static TransportContext getTransportContextOrThrow(Transport<?> transport) {
        if (transport instanceof TransportImpl) {
            return ((TransportImpl) transport).getTransportContext();
        }
        throw new IllegalArgumentException("Expected instance of TransportImpl.");
    }

    public static void sendBlocking(Transport<?> transport, Priority priority) {
        TransportRuntime.getInstance().getUploader().logAndUpdateState(getTransportContextOrThrow(transport).withPriority(priority), 1);
    }
}
