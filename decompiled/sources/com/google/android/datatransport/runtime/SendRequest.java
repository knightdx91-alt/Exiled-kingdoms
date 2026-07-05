package com.google.android.datatransport.runtime;

import com.google.android.datatransport.Encoding;
import com.google.android.datatransport.Event;
import com.google.android.datatransport.Transformer;
import com.google.android.datatransport.runtime.AutoValue_SendRequest;
import com.google.auto.value.AutoValue;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@AutoValue
abstract class SendRequest {

    @AutoValue.Builder
    public static abstract class Builder {
        public abstract SendRequest build();

        abstract Builder setEncoding(Encoding encoding);

        abstract Builder setEvent(Event<?> event);

        public <T> Builder setEvent(Event<T> event, Encoding encoding, Transformer<T, byte[]> transformer) {
            setEvent(event);
            setEncoding(encoding);
            setTransformer(transformer);
            return this;
        }

        abstract Builder setTransformer(Transformer<?, byte[]> transformer);

        public abstract Builder setTransportContext(TransportContext transportContext);

        public abstract Builder setTransportName(String str);
    }

    SendRequest() {
    }

    public static Builder builder() {
        return new AutoValue_SendRequest.Builder();
    }

    public abstract Encoding getEncoding();

    abstract Event<?> getEvent();

    public byte[] getPayload() {
        return getTransformer().apply(getEvent().getPayload());
    }

    abstract Transformer<?, byte[]> getTransformer();

    public abstract TransportContext getTransportContext();

    public abstract String getTransportName();
}
