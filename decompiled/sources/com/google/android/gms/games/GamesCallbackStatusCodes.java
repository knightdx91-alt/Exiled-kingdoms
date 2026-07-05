package com.google.android.gms.games;

import a.a;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class GamesCallbackStatusCodes {

    @Deprecated
    public static final int CLIENT_RECONNECT_REQUIRED = 2;
    public static final int INTERNAL_ERROR = 1;
    public static final int MULTIPLAYER_DISABLED = 6003;
    public static final int OK = 0;
    public static final int REAL_TIME_CONNECTION_FAILED = 7000;
    public static final int REAL_TIME_MESSAGE_SEND_FAILED = 7001;
    public static final int REAL_TIME_ROOM_NOT_JOINED = 7004;

    @Retention(RetentionPolicy.SOURCE)
    public @interface OnJoinedRoomStatusCodes {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface OnLeftRoomStatusCodes {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface OnRealTimeMessageSentStatusCodes {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface OnRoomConnectedStatusCodes {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface OnRoomCreatedStatusCodes {
    }

    private GamesCallbackStatusCodes() {
    }

    public static String getStatusCodeString(int i2) {
        return i2 != 0 ? i2 != 1 ? i2 != 2 ? i2 != 6003 ? i2 != 7004 ? i2 != 7000 ? i2 != 7001 ? a.g(47, i2, "unknown games callback status code: ") : "REAL_TIME_MESSAGE_SEND_FAILED" : "REAL_TIME_CONNECTION_FAILED" : "REAL_TIME_ROOM_NOT_JOINED" : "MULTIPLAYER_DISABLED" : "CLIENT_RECONNECT_REQUIRED" : "INTERNAL_ERROR" : "OK";
    }
}
