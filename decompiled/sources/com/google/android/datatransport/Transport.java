package com.google.android.datatransport;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public interface Transport<T> {
    void schedule(Event<T> event, TransportScheduleCallback transportScheduleCallback);

    void send(Event<T> event);
}
