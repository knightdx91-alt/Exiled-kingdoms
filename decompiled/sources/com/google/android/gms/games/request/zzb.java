package com.google.android.gms.games.request;

import android.os.Parcel;
import com.google.android.gms.common.data.DataBufferRef;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.GameRef;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.PlayerRef;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@Deprecated
public final class zzb extends DataBufferRef implements GameRequest {
    private final int zzmz;

    public zzb(DataHolder dataHolder, int i2, int i3) {
        super(dataHolder, i2);
        this.zzmz = i3;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // com.google.android.gms.common.data.DataBufferRef
    public final boolean equals(Object obj) {
        return GameRequestEntity.zza(this, obj);
    }

    @Override // com.google.android.gms.common.data.Freezable
    public final /* synthetic */ GameRequest freeze() {
        return new GameRequestEntity(this);
    }

    @Override // com.google.android.gms.games.request.GameRequest
    public final long getCreationTimestamp() {
        return getLong("creation_timestamp");
    }

    @Override // com.google.android.gms.games.request.GameRequest
    public final byte[] getData() {
        return getByteArray("data");
    }

    @Override // com.google.android.gms.games.request.GameRequest
    public final long getExpirationTimestamp() {
        return getLong("expiration_timestamp");
    }

    @Override // com.google.android.gms.games.request.GameRequest
    public final Game getGame() {
        return new GameRef(this.mDataHolder, this.mDataRow);
    }

    @Override // com.google.android.gms.games.request.GameRequest
    public final int getRecipientStatus(String str) {
        for (int i2 = this.mDataRow; i2 < this.mDataRow + this.zzmz; i2++) {
            int windowIndex = this.mDataHolder.getWindowIndex(i2);
            if (this.mDataHolder.getString("recipient_external_player_id", i2, windowIndex).equals(str)) {
                return this.mDataHolder.getInteger("recipient_status", i2, windowIndex);
            }
        }
        return -1;
    }

    @Override // com.google.android.gms.games.request.GameRequest
    public final List<Player> getRecipients() {
        ArrayList arrayList = new ArrayList(this.zzmz);
        for (int i2 = 0; i2 < this.zzmz; i2++) {
            arrayList.add(new PlayerRef(this.mDataHolder, this.mDataRow + i2, "recipient_"));
        }
        return arrayList;
    }

    @Override // com.google.android.gms.games.request.GameRequest
    public final String getRequestId() {
        return getString("external_request_id");
    }

    @Override // com.google.android.gms.games.request.GameRequest
    public final Player getSender() {
        return new PlayerRef(this.mDataHolder, getDataRow(), "sender_");
    }

    @Override // com.google.android.gms.games.request.GameRequest
    public final int getStatus() {
        return getInteger(Games.EXTRA_STATUS);
    }

    @Override // com.google.android.gms.games.request.GameRequest
    public final int getType() {
        return getInteger("type");
    }

    @Override // com.google.android.gms.common.data.DataBufferRef
    public final int hashCode() {
        return GameRequestEntity.zza(this);
    }

    @Override // com.google.android.gms.games.request.GameRequest
    public final boolean isConsumed(String str) {
        return getRecipientStatus(str) == 1;
    }

    public final String toString() {
        return GameRequestEntity.zzc(this);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        ((GameRequestEntity) ((GameRequest) freeze())).writeToParcel(parcel, i2);
    }
}
