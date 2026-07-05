package com.google.android.gms.games.multiplayer.realtime;

import java.util.List;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class zzg implements zzh {
    private final RoomUpdateCallback zzou;
    private final RoomStatusUpdateCallback zzov;
    private final OnRealTimeMessageReceivedListener zzpf;

    public zzg(RoomUpdateCallback roomUpdateCallback, RoomStatusUpdateCallback roomStatusUpdateCallback, OnRealTimeMessageReceivedListener onRealTimeMessageReceivedListener) {
        this.zzou = roomUpdateCallback;
        this.zzov = roomStatusUpdateCallback;
        this.zzpf = onRealTimeMessageReceivedListener;
    }

    @Override // com.google.android.gms.games.multiplayer.realtime.RoomStatusUpdateListener
    public final void onConnectedToRoom(Room room) {
        RoomStatusUpdateCallback roomStatusUpdateCallback = this.zzov;
        if (roomStatusUpdateCallback != null) {
            roomStatusUpdateCallback.onConnectedToRoom(room);
        }
    }

    @Override // com.google.android.gms.games.multiplayer.realtime.RoomStatusUpdateListener
    public final void onDisconnectedFromRoom(Room room) {
        RoomStatusUpdateCallback roomStatusUpdateCallback = this.zzov;
        if (roomStatusUpdateCallback != null) {
            roomStatusUpdateCallback.onDisconnectedFromRoom(room);
        }
    }

    @Override // com.google.android.gms.games.multiplayer.realtime.RoomUpdateListener
    public final void onJoinedRoom(int i2, Room room) {
        RoomUpdateCallback roomUpdateCallback = this.zzou;
        if (roomUpdateCallback != null) {
            roomUpdateCallback.onJoinedRoom(i2, room);
        }
    }

    @Override // com.google.android.gms.games.multiplayer.realtime.RoomUpdateListener
    public final void onLeftRoom(int i2, String str) {
        RoomUpdateCallback roomUpdateCallback = this.zzou;
        if (roomUpdateCallback != null) {
            roomUpdateCallback.onLeftRoom(i2, str);
        }
    }

    @Override // com.google.android.gms.games.multiplayer.realtime.RoomStatusUpdateListener
    public final void onP2PConnected(String str) {
        RoomStatusUpdateCallback roomStatusUpdateCallback = this.zzov;
        if (roomStatusUpdateCallback != null) {
            roomStatusUpdateCallback.onP2PConnected(str);
        }
    }

    @Override // com.google.android.gms.games.multiplayer.realtime.RoomStatusUpdateListener
    public final void onP2PDisconnected(String str) {
        RoomStatusUpdateCallback roomStatusUpdateCallback = this.zzov;
        if (roomStatusUpdateCallback != null) {
            roomStatusUpdateCallback.onP2PDisconnected(str);
        }
    }

    @Override // com.google.android.gms.games.multiplayer.realtime.RoomStatusUpdateListener
    public final void onPeerDeclined(Room room, List<String> list) {
        RoomStatusUpdateCallback roomStatusUpdateCallback = this.zzov;
        if (roomStatusUpdateCallback != null) {
            roomStatusUpdateCallback.onPeerDeclined(room, list);
        }
    }

    @Override // com.google.android.gms.games.multiplayer.realtime.RoomStatusUpdateListener
    public final void onPeerInvitedToRoom(Room room, List<String> list) {
        RoomStatusUpdateCallback roomStatusUpdateCallback = this.zzov;
        if (roomStatusUpdateCallback != null) {
            roomStatusUpdateCallback.onPeerInvitedToRoom(room, list);
        }
    }

    @Override // com.google.android.gms.games.multiplayer.realtime.RoomStatusUpdateListener
    public final void onPeerJoined(Room room, List<String> list) {
        RoomStatusUpdateCallback roomStatusUpdateCallback = this.zzov;
        if (roomStatusUpdateCallback != null) {
            roomStatusUpdateCallback.onPeerJoined(room, list);
        }
    }

    @Override // com.google.android.gms.games.multiplayer.realtime.RoomStatusUpdateListener
    public final void onPeerLeft(Room room, List<String> list) {
        RoomStatusUpdateCallback roomStatusUpdateCallback = this.zzov;
        if (roomStatusUpdateCallback != null) {
            roomStatusUpdateCallback.onPeerLeft(room, list);
        }
    }

    @Override // com.google.android.gms.games.multiplayer.realtime.RoomStatusUpdateListener
    public final void onPeersConnected(Room room, List<String> list) {
        RoomStatusUpdateCallback roomStatusUpdateCallback = this.zzov;
        if (roomStatusUpdateCallback != null) {
            roomStatusUpdateCallback.onPeersConnected(room, list);
        }
    }

    @Override // com.google.android.gms.games.multiplayer.realtime.RoomStatusUpdateListener
    public final void onPeersDisconnected(Room room, List<String> list) {
        RoomStatusUpdateCallback roomStatusUpdateCallback = this.zzov;
        if (roomStatusUpdateCallback != null) {
            roomStatusUpdateCallback.onPeersDisconnected(room, list);
        }
    }

    @Override // com.google.android.gms.games.multiplayer.realtime.OnRealTimeMessageReceivedListener, com.google.android.gms.games.multiplayer.realtime.RealTimeMessageReceivedListener
    public final void onRealTimeMessageReceived(RealTimeMessage realTimeMessage) {
        OnRealTimeMessageReceivedListener onRealTimeMessageReceivedListener = this.zzpf;
        if (onRealTimeMessageReceivedListener != null) {
            onRealTimeMessageReceivedListener.onRealTimeMessageReceived(realTimeMessage);
        }
    }

    @Override // com.google.android.gms.games.multiplayer.realtime.RoomStatusUpdateListener
    public final void onRoomAutoMatching(Room room) {
        RoomStatusUpdateCallback roomStatusUpdateCallback = this.zzov;
        if (roomStatusUpdateCallback != null) {
            roomStatusUpdateCallback.onRoomAutoMatching(room);
        }
    }

    @Override // com.google.android.gms.games.multiplayer.realtime.RoomUpdateListener
    public final void onRoomConnected(int i2, Room room) {
        RoomUpdateCallback roomUpdateCallback = this.zzou;
        if (roomUpdateCallback != null) {
            roomUpdateCallback.onRoomConnected(i2, room);
        }
    }

    @Override // com.google.android.gms.games.multiplayer.realtime.RoomStatusUpdateListener
    public final void onRoomConnecting(Room room) {
        RoomStatusUpdateCallback roomStatusUpdateCallback = this.zzov;
        if (roomStatusUpdateCallback != null) {
            roomStatusUpdateCallback.onRoomConnecting(room);
        }
    }

    @Override // com.google.android.gms.games.multiplayer.realtime.RoomUpdateListener
    public final void onRoomCreated(int i2, Room room) {
        RoomUpdateCallback roomUpdateCallback = this.zzou;
        if (roomUpdateCallback != null) {
            roomUpdateCallback.onRoomCreated(i2, room);
        }
    }
}
