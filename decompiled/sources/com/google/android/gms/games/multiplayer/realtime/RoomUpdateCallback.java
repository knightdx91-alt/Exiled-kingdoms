package com.google.android.gms.games.multiplayer.realtime;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public abstract class RoomUpdateCallback implements RoomUpdateListener {
    @Override // com.google.android.gms.games.multiplayer.realtime.RoomUpdateListener
    public abstract void onJoinedRoom(int i2, Room room);

    @Override // com.google.android.gms.games.multiplayer.realtime.RoomUpdateListener
    public abstract void onLeftRoom(int i2, String str);

    @Override // com.google.android.gms.games.multiplayer.realtime.RoomUpdateListener
    public abstract void onRoomConnected(int i2, Room room);

    @Override // com.google.android.gms.games.multiplayer.realtime.RoomUpdateListener
    public abstract void onRoomCreated(int i2, Room room);
}
