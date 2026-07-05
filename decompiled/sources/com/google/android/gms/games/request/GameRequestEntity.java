package com.google.android.gms.games.request;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.auth.api.credentials.CredentialsApi;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.GameEntity;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.PlayerEntity;
import com.google.android.gms.games.internal.zzd;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@SafeParcelable.Class(creator = "GameRequestEntityCreator")
@SafeParcelable.Reserved({CredentialsApi.ACTIVITY_RESULT_ADD_ACCOUNT})
@Deprecated
public final class GameRequestEntity extends zzd implements GameRequest {
    public static final Parcelable.Creator<GameRequestEntity> CREATOR = new zza();

    @SafeParcelable.Field(getter = "getData", id = 3)
    private final byte[] data;

    @SafeParcelable.Field(getter = "getStatus", id = 12)
    private final int status;

    @SafeParcelable.Field(getter = "getType", id = 7)
    private final int type;

    @SafeParcelable.Field(getter = "getRequestId", id = 4)
    private final String zzht;

    @SafeParcelable.Field(getter = "getGame", id = 1)
    private final GameEntity zzky;

    @SafeParcelable.Field(getter = "getCreationTimestamp", id = 9)
    private final long zzoa;

    @SafeParcelable.Field(getter = "getSender", id = 2)
    private final PlayerEntity zzqh;

    @SafeParcelable.Field(getter = "getRecipients", id = 5)
    private final ArrayList<PlayerEntity> zzqi;

    @SafeParcelable.Field(getter = "getExpirationTimestamp", id = 10)
    private final long zzqj;

    @SafeParcelable.Field(getter = "getRecipientStatusBundle", id = 11)
    private final Bundle zzqk;

    @SafeParcelable.Constructor
    GameRequestEntity(@SafeParcelable.Param(id = 1) GameEntity gameEntity, @SafeParcelable.Param(id = 2) PlayerEntity playerEntity, @SafeParcelable.Param(id = 3) byte[] bArr, @SafeParcelable.Param(id = 4) String str, @SafeParcelable.Param(id = 5) ArrayList<PlayerEntity> arrayList, @SafeParcelable.Param(id = 7) int i2, @SafeParcelable.Param(id = 9) long j2, @SafeParcelable.Param(id = 10) long j3, @SafeParcelable.Param(id = 11) Bundle bundle, @SafeParcelable.Param(id = 12) int i3) {
        this.zzky = gameEntity;
        this.zzqh = playerEntity;
        this.data = bArr;
        this.zzht = str;
        this.zzqi = arrayList;
        this.type = i2;
        this.zzoa = j2;
        this.zzqj = j3;
        this.zzqk = bundle;
        this.status = i3;
    }

    static int zza(GameRequest gameRequest) {
        return Objects.hashCode(gameRequest.getGame(), gameRequest.getRecipients(), gameRequest.getRequestId(), gameRequest.getSender(), Integer.valueOf(gameRequest.getType()), Long.valueOf(gameRequest.getCreationTimestamp()), Long.valueOf(gameRequest.getExpirationTimestamp())) + (Arrays.hashCode(zzb(gameRequest)) * 31);
    }

    private static int[] zzb(GameRequest gameRequest) {
        List<Player> recipients = gameRequest.getRecipients();
        int size = recipients.size();
        int[] iArr = new int[size];
        for (int i2 = 0; i2 < size; i2++) {
            iArr[i2] = gameRequest.getRecipientStatus(recipients.get(i2).getPlayerId());
        }
        return iArr;
    }

    static String zzc(GameRequest gameRequest) {
        return Objects.toStringHelper(gameRequest).add("Game", gameRequest.getGame()).add("Sender", gameRequest.getSender()).add("Recipients", gameRequest.getRecipients()).add("Data", gameRequest.getData()).add("RequestId", gameRequest.getRequestId()).add("Type", Integer.valueOf(gameRequest.getType())).add("CreationTimestamp", Long.valueOf(gameRequest.getCreationTimestamp())).add("ExpirationTimestamp", Long.valueOf(gameRequest.getExpirationTimestamp())).toString();
    }

    public final boolean equals(Object obj) {
        return zza(this, obj);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.android.gms.common.data.Freezable
    public final GameRequest freeze() {
        return this;
    }

    @Override // com.google.android.gms.games.request.GameRequest
    public final long getCreationTimestamp() {
        return this.zzoa;
    }

    @Override // com.google.android.gms.games.request.GameRequest
    public final byte[] getData() {
        return this.data;
    }

    @Override // com.google.android.gms.games.request.GameRequest
    public final long getExpirationTimestamp() {
        return this.zzqj;
    }

    @Override // com.google.android.gms.games.request.GameRequest
    public final Game getGame() {
        return this.zzky;
    }

    @Override // com.google.android.gms.games.request.GameRequest
    public final int getRecipientStatus(String str) {
        return this.zzqk.getInt(str, 0);
    }

    @Override // com.google.android.gms.games.request.GameRequest
    public final List<Player> getRecipients() {
        return new ArrayList(this.zzqi);
    }

    @Override // com.google.android.gms.games.request.GameRequest
    public final String getRequestId() {
        return this.zzht;
    }

    @Override // com.google.android.gms.games.request.GameRequest
    public final Player getSender() {
        return this.zzqh;
    }

    @Override // com.google.android.gms.games.request.GameRequest
    public final int getStatus() {
        return this.status;
    }

    @Override // com.google.android.gms.games.request.GameRequest
    public final int getType() {
        return this.type;
    }

    public final int hashCode() {
        return zza(this);
    }

    @Override // com.google.android.gms.games.request.GameRequest
    public final boolean isConsumed(String str) {
        return getRecipientStatus(str) == 1;
    }

    @Override // com.google.android.gms.common.data.Freezable
    public final boolean isDataValid() {
        return true;
    }

    public final String toString() {
        return zzc(this);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, getGame(), i2, false);
        SafeParcelWriter.writeParcelable(parcel, 2, getSender(), i2, false);
        SafeParcelWriter.writeByteArray(parcel, 3, getData(), false);
        SafeParcelWriter.writeString(parcel, 4, getRequestId(), false);
        SafeParcelWriter.writeTypedList(parcel, 5, getRecipients(), false);
        SafeParcelWriter.writeInt(parcel, 7, getType());
        SafeParcelWriter.writeLong(parcel, 9, getCreationTimestamp());
        SafeParcelWriter.writeLong(parcel, 10, getExpirationTimestamp());
        SafeParcelWriter.writeBundle(parcel, 11, this.zzqk, false);
        SafeParcelWriter.writeInt(parcel, 12, getStatus());
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    public GameRequestEntity(GameRequest gameRequest) {
        this.zzky = new GameEntity(gameRequest.getGame());
        this.zzqh = new PlayerEntity(gameRequest.getSender());
        this.zzht = gameRequest.getRequestId();
        this.type = gameRequest.getType();
        this.zzoa = gameRequest.getCreationTimestamp();
        this.zzqj = gameRequest.getExpirationTimestamp();
        this.status = gameRequest.getStatus();
        byte[] data = gameRequest.getData();
        if (data == null) {
            this.data = null;
        } else {
            byte[] bArr = new byte[data.length];
            this.data = bArr;
            System.arraycopy(data, 0, bArr, 0, data.length);
        }
        List<Player> recipients = gameRequest.getRecipients();
        int size = recipients.size();
        this.zzqi = new ArrayList<>(size);
        this.zzqk = new Bundle();
        for (int i2 = 0; i2 < size; i2++) {
            Player playerFreeze = recipients.get(i2).freeze();
            String playerId = playerFreeze.getPlayerId();
            this.zzqi.add((PlayerEntity) playerFreeze);
            this.zzqk.putInt(playerId, gameRequest.getRecipientStatus(playerId));
        }
    }

    static boolean zza(GameRequest gameRequest, Object obj) {
        if (!(obj instanceof GameRequest)) {
            return false;
        }
        if (gameRequest == obj) {
            return true;
        }
        GameRequest gameRequest2 = (GameRequest) obj;
        return Objects.equal(gameRequest2.getGame(), gameRequest.getGame()) && Objects.equal(gameRequest2.getRecipients(), gameRequest.getRecipients()) && Objects.equal(gameRequest2.getRequestId(), gameRequest.getRequestId()) && Objects.equal(gameRequest2.getSender(), gameRequest.getSender()) && Arrays.equals(zzb(gameRequest2), zzb(gameRequest)) && Objects.equal(Integer.valueOf(gameRequest2.getType()), Integer.valueOf(gameRequest.getType())) && Objects.equal(Long.valueOf(gameRequest2.getCreationTimestamp()), Long.valueOf(gameRequest.getCreationTimestamp())) && Objects.equal(Long.valueOf(gameRequest2.getExpirationTimestamp()), Long.valueOf(gameRequest.getExpirationTimestamp()));
    }
}
