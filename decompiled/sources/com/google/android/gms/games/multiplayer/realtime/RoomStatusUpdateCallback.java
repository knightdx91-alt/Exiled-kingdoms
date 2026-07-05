package com.google.android.gms.games.multiplayer.realtime;

import java.util.List;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public abstract class RoomStatusUpdateCallback implements RoomStatusUpdateListener {
    @Override // com.google.android.gms.games.multiplayer.realtime.RoomStatusUpdateListener
    public abstract void onConnectedToRoom(Room room);

    @Override // com.google.android.gms.games.multiplayer.realtime.RoomStatusUpdateListener
    public abstract void onDisconnectedFromRoom(Room room);

    @Override // com.google.android.gms.games.multiplayer.realtime.RoomStatusUpdateListener
    public abstract void onP2PConnected(String str);

    @Override // com.google.android.gms.games.multiplayer.realtime.RoomStatusUpdateListener
    public abstract void onP2PDisconnected(String str);

    @Override // com.google.android.gms.games.multiplayer.realtime.RoomStatusUpdateListener
    public abstract void onPeerDeclined(Room room, List<String> list);

    @Override // com.google.android.gms.games.multiplayer.realtime.RoomStatusUpdateListener
    public abstract void onPeerInvitedToRoom(Room room, List<String> list);

    @Override // com.google.android.gms.games.multiplayer.realtime.RoomStatusUpdateListener
    public abstract void onPeerJoined(Room room, List<String> list);

    @Override // com.google.android.gms.games.multiplayer.realtime.RoomStatusUpdateListener
    public abstract void onPeerLeft(Room room, List<String> list);

    @Override // com.google.android.gms.games.multiplayer.realtime.RoomStatusUpdateListener
    public abstract void onPeersConnected(Room room, List<String> list);

    @Override // com.google.android.gms.games.multiplayer.realtime.RoomStatusUpdateListener
    public abstract void onPeersDisconnected(Room room, List<String> list);

    @Override // com.google.android.gms.games.multiplayer.realtime.RoomStatusUpdateListener
    public abstract void onRoomAutoMatching(Room room);

    @Override // com.google.android.gms.games.multiplayer.realtime.RoomStatusUpdateListener
    public abstract void onRoomConnecting(Room room);
}
