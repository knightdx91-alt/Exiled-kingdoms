package com.google.android.datatransport.runtime.backends;

import com.google.android.datatransport.runtime.EventInternal;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public interface TransportBackend {
    EventInternal decorate(EventInternal eventInternal);

    BackendResponse send(BackendRequest backendRequest);
}
