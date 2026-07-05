package com.google.android.datatransport;

import com.google.auto.value.AutoValue;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@AutoValue
public abstract class Event<T> {
    public static <T> Event<T> ofData(int i2, T t2) {
        return new AutoValue_Event(Integer.valueOf(i2), t2, Priority.DEFAULT);
    }

    public static <T> Event<T> ofTelemetry(int i2, T t2) {
        return new AutoValue_Event(Integer.valueOf(i2), t2, Priority.VERY_LOW);
    }

    public static <T> Event<T> ofUrgent(int i2, T t2) {
        return new AutoValue_Event(Integer.valueOf(i2), t2, Priority.HIGHEST);
    }

    public abstract Integer getCode();

    public abstract T getPayload();

    public abstract Priority getPriority();

    public static <T> Event<T> ofData(T t2) {
        return new AutoValue_Event(null, t2, Priority.DEFAULT);
    }

    public static <T> Event<T> ofTelemetry(T t2) {
        return new AutoValue_Event(null, t2, Priority.VERY_LOW);
    }

    public static <T> Event<T> ofUrgent(T t2) {
        return new AutoValue_Event(null, t2, Priority.HIGHEST);
    }
}
