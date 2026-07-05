package com.google.android.datatransport.cct.internal;

import com.google.android.datatransport.cct.internal.AutoValue_ClientInfo;
import com.google.auto.value.AutoValue;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@AutoValue
public abstract class ClientInfo {

    @AutoValue.Builder
    public static abstract class Builder {
        public abstract ClientInfo build();

        public abstract Builder setAndroidClientInfo(AndroidClientInfo androidClientInfo);

        public abstract Builder setClientType(ClientType clientType);
    }

    public enum ClientType {
        UNKNOWN(0),
        ANDROID_FIREBASE(23);

        private final int value;

        ClientType(int i2) {
            this.value = i2;
        }
    }

    public static Builder builder() {
        return new AutoValue_ClientInfo.Builder();
    }

    public abstract AndroidClientInfo getAndroidClientInfo();

    public abstract ClientType getClientType();
}
