package com.google.android.datatransport.cct.internal;

import a.a;
import com.google.android.datatransport.cct.internal.LogEvent;
import java.util.Arrays;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class AutoValue_LogEvent extends LogEvent {
    private final Integer eventCode;
    private final long eventTimeMs;
    private final long eventUptimeMs;
    private final NetworkConnectionInfo networkConnectionInfo;
    private final byte[] sourceExtension;
    private final String sourceExtensionJsonProto3;
    private final long timezoneOffsetSeconds;

    static final class Builder extends LogEvent.Builder {
        private Integer eventCode;
        private Long eventTimeMs;
        private Long eventUptimeMs;
        private NetworkConnectionInfo networkConnectionInfo;
        private byte[] sourceExtension;
        private String sourceExtensionJsonProto3;
        private Long timezoneOffsetSeconds;

        Builder() {
        }

        @Override // com.google.android.datatransport.cct.internal.LogEvent.Builder
        public LogEvent build() {
            String strK = this.eventTimeMs == null ? " eventTimeMs" : "";
            if (this.eventUptimeMs == null) {
                strK = a.k(strK, " eventUptimeMs");
            }
            if (this.timezoneOffsetSeconds == null) {
                strK = a.k(strK, " timezoneOffsetSeconds");
            }
            if (strK.isEmpty()) {
                return new AutoValue_LogEvent(this.eventTimeMs.longValue(), this.eventCode, this.eventUptimeMs.longValue(), this.sourceExtension, this.sourceExtensionJsonProto3, this.timezoneOffsetSeconds.longValue(), this.networkConnectionInfo);
            }
            throw new IllegalStateException("Missing required properties:".concat(strK));
        }

        @Override // com.google.android.datatransport.cct.internal.LogEvent.Builder
        public LogEvent.Builder setEventCode(Integer num) {
            this.eventCode = num;
            return this;
        }

        @Override // com.google.android.datatransport.cct.internal.LogEvent.Builder
        public LogEvent.Builder setEventTimeMs(long j2) {
            this.eventTimeMs = Long.valueOf(j2);
            return this;
        }

        @Override // com.google.android.datatransport.cct.internal.LogEvent.Builder
        public LogEvent.Builder setEventUptimeMs(long j2) {
            this.eventUptimeMs = Long.valueOf(j2);
            return this;
        }

        @Override // com.google.android.datatransport.cct.internal.LogEvent.Builder
        public LogEvent.Builder setNetworkConnectionInfo(NetworkConnectionInfo networkConnectionInfo) {
            this.networkConnectionInfo = networkConnectionInfo;
            return this;
        }

        @Override // com.google.android.datatransport.cct.internal.LogEvent.Builder
        LogEvent.Builder setSourceExtension(byte[] bArr) {
            this.sourceExtension = bArr;
            return this;
        }

        @Override // com.google.android.datatransport.cct.internal.LogEvent.Builder
        LogEvent.Builder setSourceExtensionJsonProto3(String str) {
            this.sourceExtensionJsonProto3 = str;
            return this;
        }

        @Override // com.google.android.datatransport.cct.internal.LogEvent.Builder
        public LogEvent.Builder setTimezoneOffsetSeconds(long j2) {
            this.timezoneOffsetSeconds = Long.valueOf(j2);
            return this;
        }
    }

    public boolean equals(Object obj) {
        Integer num;
        String str;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof LogEvent)) {
            return false;
        }
        LogEvent logEvent = (LogEvent) obj;
        if (this.eventTimeMs == logEvent.getEventTimeMs() && ((num = this.eventCode) != null ? num.equals(logEvent.getEventCode()) : logEvent.getEventCode() == null) && this.eventUptimeMs == logEvent.getEventUptimeMs()) {
            if (Arrays.equals(this.sourceExtension, logEvent instanceof AutoValue_LogEvent ? ((AutoValue_LogEvent) logEvent).sourceExtension : logEvent.getSourceExtension()) && ((str = this.sourceExtensionJsonProto3) != null ? str.equals(logEvent.getSourceExtensionJsonProto3()) : logEvent.getSourceExtensionJsonProto3() == null) && this.timezoneOffsetSeconds == logEvent.getTimezoneOffsetSeconds()) {
                NetworkConnectionInfo networkConnectionInfo = this.networkConnectionInfo;
                if (networkConnectionInfo == null) {
                    if (logEvent.getNetworkConnectionInfo() == null) {
                        return true;
                    }
                } else if (networkConnectionInfo.equals(logEvent.getNetworkConnectionInfo())) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override // com.google.android.datatransport.cct.internal.LogEvent
    public Integer getEventCode() {
        return this.eventCode;
    }

    @Override // com.google.android.datatransport.cct.internal.LogEvent
    public long getEventTimeMs() {
        return this.eventTimeMs;
    }

    @Override // com.google.android.datatransport.cct.internal.LogEvent
    public long getEventUptimeMs() {
        return this.eventUptimeMs;
    }

    @Override // com.google.android.datatransport.cct.internal.LogEvent
    public NetworkConnectionInfo getNetworkConnectionInfo() {
        return this.networkConnectionInfo;
    }

    @Override // com.google.android.datatransport.cct.internal.LogEvent
    public byte[] getSourceExtension() {
        return this.sourceExtension;
    }

    @Override // com.google.android.datatransport.cct.internal.LogEvent
    public String getSourceExtensionJsonProto3() {
        return this.sourceExtensionJsonProto3;
    }

    @Override // com.google.android.datatransport.cct.internal.LogEvent
    public long getTimezoneOffsetSeconds() {
        return this.timezoneOffsetSeconds;
    }

    public int hashCode() {
        long j2 = this.eventTimeMs;
        int i2 = (((int) (j2 ^ (j2 >>> 32))) ^ 1000003) * 1000003;
        Integer num = this.eventCode;
        int iHashCode = num == null ? 0 : num.hashCode();
        long j3 = this.eventUptimeMs;
        int iHashCode2 = (((((i2 ^ iHashCode) * 1000003) ^ ((int) (j3 ^ (j3 >>> 32)))) * 1000003) ^ Arrays.hashCode(this.sourceExtension)) * 1000003;
        String str = this.sourceExtensionJsonProto3;
        int iHashCode3 = str == null ? 0 : str.hashCode();
        long j4 = this.timezoneOffsetSeconds;
        int i3 = (((iHashCode2 ^ iHashCode3) * 1000003) ^ ((int) ((j4 >>> 32) ^ j4))) * 1000003;
        NetworkConnectionInfo networkConnectionInfo = this.networkConnectionInfo;
        return i3 ^ (networkConnectionInfo != null ? networkConnectionInfo.hashCode() : 0);
    }

    public String toString() {
        return "LogEvent{eventTimeMs=" + this.eventTimeMs + ", eventCode=" + this.eventCode + ", eventUptimeMs=" + this.eventUptimeMs + ", sourceExtension=" + Arrays.toString(this.sourceExtension) + ", sourceExtensionJsonProto3=" + this.sourceExtensionJsonProto3 + ", timezoneOffsetSeconds=" + this.timezoneOffsetSeconds + ", networkConnectionInfo=" + this.networkConnectionInfo + "}";
    }

    private AutoValue_LogEvent(long j2, Integer num, long j3, byte[] bArr, String str, long j4, NetworkConnectionInfo networkConnectionInfo) {
        this.eventTimeMs = j2;
        this.eventCode = num;
        this.eventUptimeMs = j3;
        this.sourceExtension = bArr;
        this.sourceExtensionJsonProto3 = str;
        this.timezoneOffsetSeconds = j4;
        this.networkConnectionInfo = networkConnectionInfo;
    }
}
