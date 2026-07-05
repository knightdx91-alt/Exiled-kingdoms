package com.google.android.gms.games;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Looper;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.ListenerHolders;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.multiplayer.realtime.RealTimeMultiplayer;
import com.google.android.gms.games.multiplayer.realtime.Room;
import com.google.android.gms.games.multiplayer.realtime.RoomConfig;
import com.google.android.gms.tasks.Task;
import java.util.List;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class RealTimeMultiplayerClient extends com.google.android.gms.internal.games.zzu {

    public interface ReliableMessageSentCallback extends RealTimeMultiplayer.ReliableMessageSentCallback {
        @Override // com.google.android.gms.games.multiplayer.realtime.RealTimeMultiplayer.ReliableMessageSentCallback
        void onRealTimeMessageSent(int i2, int i3, String str);
    }

    RealTimeMultiplayerClient(Activity activity, Games.GamesOptions gamesOptions) {
        super(activity, gamesOptions);
    }

    public Task<Void> create(RoomConfig roomConfig) {
        ListenerHolder<L> listenerHolderRegisterListener = registerListener(roomConfig.zzch(), "zzh");
        return doRegisterEventListener(new zzbj(this, listenerHolderRegisterListener, listenerHolderRegisterListener, roomConfig), new zzbk(this, listenerHolderRegisterListener.getListenerKey()));
    }

    public Task<Void> declineInvitation(String str) {
        return doWrite(new zzbe(this, str));
    }

    public Task<Void> dismissInvitation(String str) {
        return doWrite(new zzbf(this, str));
    }

    public Task<Intent> getSelectOpponentsIntent(int i2, int i3) {
        return getSelectOpponentsIntent(i2, i3, true);
    }

    public Task<Intent> getWaitingRoomIntent(Room room, int i2) {
        return doRead(new zzba(this, room, i2));
    }

    public Task<Void> join(RoomConfig roomConfig) {
        ListenerHolder<L> listenerHolderRegisterListener = registerListener(roomConfig.zzch(), "zzh");
        return doRegisterEventListener(new zzbl(this, listenerHolderRegisterListener, listenerHolderRegisterListener, roomConfig), new zzbm(this, listenerHolderRegisterListener.getListenerKey()));
    }

    public Task<Void> leave(RoomConfig roomConfig, String str) {
        ListenerHolder<L> listenerHolderRegisterListener = registerListener(roomConfig.zzch(), "zzh");
        return doRead(new zzbg(this, str)).continueWithTask(new zzbq(this, listenerHolderRegisterListener)).continueWithTask(new zzbn(this, listenerHolderRegisterListener, str, roomConfig));
    }

    public Task<Integer> sendReliableMessage(byte[] bArr, String str, String str2, ReliableMessageSentCallback reliableMessageSentCallback) {
        return doWrite(new zzbr(this, reliableMessageSentCallback != null ? ListenerHolders.createListenerHolder(reliableMessageSentCallback, Looper.getMainLooper(), ReliableMessageSentCallback.class.getSimpleName()) : null, bArr, str, str2));
    }

    public Task<Void> sendUnreliableMessage(byte[] bArr, String str, String str2) {
        return doWrite(new zzbb(this, bArr, str, str2));
    }

    public Task<Void> sendUnreliableMessageToOthers(byte[] bArr, String str) {
        return doWrite(new zzbd(this, bArr, str));
    }

    RealTimeMultiplayerClient(Context context, Games.GamesOptions gamesOptions) {
        super(context, gamesOptions);
    }

    public Task<Intent> getSelectOpponentsIntent(int i2, int i3, boolean z2) {
        return doRead(new zzbi(this, i2, i3, z2));
    }

    public Task<Void> sendUnreliableMessage(byte[] bArr, String str, List<String> list) {
        return doWrite(new zzbc(this, list, bArr, str));
    }
}
