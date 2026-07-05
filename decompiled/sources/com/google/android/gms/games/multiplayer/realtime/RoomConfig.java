package com.google.android.gms.games.multiplayer.realtime;

import android.os.Bundle;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.games.multiplayer.Multiplayer;
import java.util.ArrayList;
import java.util.Arrays;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public abstract class RoomConfig {

    public static final class Builder {
        int zzoe;

        @Deprecated
        final RoomUpdateListener zzor;

        @Deprecated
        RoomStatusUpdateListener zzos;

        @Deprecated
        RealTimeMessageReceivedListener zzot;
        final RoomUpdateCallback zzou;
        RoomStatusUpdateCallback zzov;
        OnRealTimeMessageReceivedListener zzow;
        String zzox;
        ArrayList<String> zzoy;
        Bundle zzoz;

        private Builder(RoomUpdateCallback roomUpdateCallback) {
            this.zzox = null;
            this.zzoe = -1;
            this.zzoy = new ArrayList<>();
            this.zzou = (RoomUpdateCallback) Preconditions.checkNotNull(roomUpdateCallback, "Must provide a RoomUpdateCallback");
            this.zzor = null;
        }

        public final Builder addPlayersToInvite(ArrayList<String> arrayList) {
            Preconditions.checkNotNull(arrayList);
            this.zzoy.addAll(arrayList);
            return this;
        }

        public final RoomConfig build() {
            return new zzd(this);
        }

        public final Builder setAutoMatchCriteria(Bundle bundle) {
            this.zzoz = bundle;
            return this;
        }

        public final Builder setInvitationIdToAccept(String str) {
            Preconditions.checkNotNull(str);
            this.zzox = str;
            return this;
        }

        @Deprecated
        public final Builder setMessageReceivedListener(RealTimeMessageReceivedListener realTimeMessageReceivedListener) {
            this.zzot = realTimeMessageReceivedListener;
            return this;
        }

        public final Builder setOnMessageReceivedListener(OnRealTimeMessageReceivedListener onRealTimeMessageReceivedListener) {
            this.zzow = onRealTimeMessageReceivedListener;
            return this;
        }

        public final Builder setRoomStatusUpdateCallback(RoomStatusUpdateCallback roomStatusUpdateCallback) {
            this.zzov = roomStatusUpdateCallback;
            return this;
        }

        @Deprecated
        public final Builder setRoomStatusUpdateListener(RoomStatusUpdateListener roomStatusUpdateListener) {
            this.zzos = roomStatusUpdateListener;
            return this;
        }

        public final Builder setVariant(int i2) {
            Preconditions.checkArgument(i2 == -1 || i2 > 0, "Variant must be a positive integer or Room.ROOM_VARIANT_ANY");
            this.zzoe = i2;
            return this;
        }

        public final Builder addPlayersToInvite(String... strArr) {
            Preconditions.checkNotNull(strArr);
            this.zzoy.addAll(Arrays.asList(strArr));
            return this;
        }

        @Deprecated
        private Builder(RoomUpdateListener roomUpdateListener) {
            this.zzox = null;
            this.zzoe = -1;
            this.zzoy = new ArrayList<>();
            this.zzor = (RoomUpdateListener) Preconditions.checkNotNull(roomUpdateListener, "Must provide a RoomUpdateListener");
            this.zzou = null;
        }
    }

    protected RoomConfig() {
    }

    public static Builder builder(RoomUpdateCallback roomUpdateCallback) {
        return new Builder(roomUpdateCallback);
    }

    public static Bundle createAutoMatchCriteria(int i2, int i3, long j2) {
        Bundle bundle = new Bundle();
        bundle.putInt(Multiplayer.EXTRA_MIN_AUTOMATCH_PLAYERS, i2);
        bundle.putInt(Multiplayer.EXTRA_MAX_AUTOMATCH_PLAYERS, i3);
        bundle.putLong(Multiplayer.EXTRA_EXCLUSIVE_BIT_MASK, j2);
        return bundle;
    }

    public abstract Bundle getAutoMatchCriteria();

    public abstract String getInvitationId();

    public abstract String[] getInvitedPlayerIds();

    @Deprecated
    public abstract RealTimeMessageReceivedListener getMessageReceivedListener();

    public abstract OnRealTimeMessageReceivedListener getOnMessageReceivedListener();

    public abstract RoomStatusUpdateCallback getRoomStatusUpdateCallback();

    @Deprecated
    public abstract RoomStatusUpdateListener getRoomStatusUpdateListener();

    public abstract RoomUpdateCallback getRoomUpdateCallback();

    @Deprecated
    public abstract RoomUpdateListener getRoomUpdateListener();

    public abstract int getVariant();

    public abstract zzh zzch();

    @Deprecated
    public static Builder builder(RoomUpdateListener roomUpdateListener) {
        return new Builder(roomUpdateListener);
    }
}
