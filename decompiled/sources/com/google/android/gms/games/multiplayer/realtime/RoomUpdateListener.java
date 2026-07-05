package com.google.android.gms.games.multiplayer.realtime;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@Deprecated
public interface RoomUpdateListener {
    void onJoinedRoom(int i2, Room room);

    void onLeftRoom(int i2, String str);

    void onRoomConnected(int i2, Room room);

    void onRoomCreated(int i2, Room room);
}
