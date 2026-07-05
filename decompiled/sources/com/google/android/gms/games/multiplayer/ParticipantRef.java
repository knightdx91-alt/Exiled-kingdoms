package com.google.android.gms.games.multiplayer;

import android.database.CharArrayBuffer;
import android.net.Uri;
import android.os.Parcel;
import com.google.android.gms.common.data.DataBufferRef;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.PlayerRef;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class ParticipantRef extends DataBufferRef implements Participant {
    private final PlayerRef zzol;

    public ParticipantRef(DataHolder dataHolder, int i2) {
        super(dataHolder, i2);
        this.zzol = new PlayerRef(dataHolder, i2);
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // com.google.android.gms.common.data.DataBufferRef
    public final boolean equals(Object obj) {
        return ParticipantEntity.zza(this, obj);
    }

    @Override // com.google.android.gms.common.data.Freezable
    public final /* synthetic */ Participant freeze() {
        return new ParticipantEntity(this);
    }

    @Override // com.google.android.gms.games.multiplayer.Participant
    public final int getCapabilities() {
        return getInteger("capabilities");
    }

    @Override // com.google.android.gms.games.multiplayer.Participant
    public final String getDisplayName() {
        return hasNull("external_player_id") ? getString("default_display_name") : this.zzol.getDisplayName();
    }

    @Override // com.google.android.gms.games.multiplayer.Participant
    public final Uri getHiResImageUri() {
        return hasNull("external_player_id") ? parseUri("default_display_hi_res_image_uri") : this.zzol.getHiResImageUri();
    }

    @Override // com.google.android.gms.games.multiplayer.Participant
    public final String getHiResImageUrl() {
        return hasNull("external_player_id") ? getString("default_display_hi_res_image_url") : this.zzol.getHiResImageUrl();
    }

    @Override // com.google.android.gms.games.multiplayer.Participant
    public final Uri getIconImageUri() {
        return hasNull("external_player_id") ? parseUri("default_display_image_uri") : this.zzol.getIconImageUri();
    }

    @Override // com.google.android.gms.games.multiplayer.Participant
    public final String getIconImageUrl() {
        return hasNull("external_player_id") ? getString("default_display_image_url") : this.zzol.getIconImageUrl();
    }

    @Override // com.google.android.gms.games.multiplayer.Participant
    public final String getParticipantId() {
        return getString("external_participant_id");
    }

    @Override // com.google.android.gms.games.multiplayer.Participant
    public final Player getPlayer() {
        if (hasNull("external_player_id")) {
            return null;
        }
        return this.zzol;
    }

    @Override // com.google.android.gms.games.multiplayer.Participant
    public final ParticipantResult getResult() {
        if (hasNull("result_type")) {
            return null;
        }
        return new ParticipantResult(getParticipantId(), getInteger("result_type"), getInteger("placing"));
    }

    @Override // com.google.android.gms.games.multiplayer.Participant
    public final int getStatus() {
        return getInteger("player_status");
    }

    @Override // com.google.android.gms.common.data.DataBufferRef
    public final int hashCode() {
        return ParticipantEntity.zza(this);
    }

    @Override // com.google.android.gms.games.multiplayer.Participant
    public final boolean isConnectedToRoom() {
        return getInteger("connected") > 0;
    }

    public final String toString() {
        return ParticipantEntity.zzb(this);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        ((ParticipantEntity) ((Participant) freeze())).writeToParcel(parcel, i2);
    }

    @Override // com.google.android.gms.games.multiplayer.Participant
    public final String zzcg() {
        return getString("client_address");
    }

    @Override // com.google.android.gms.games.multiplayer.Participant
    public final void getDisplayName(CharArrayBuffer charArrayBuffer) {
        if (hasNull("external_player_id")) {
            copyToBuffer("default_display_name", charArrayBuffer);
        } else {
            this.zzol.getDisplayName(charArrayBuffer);
        }
    }
}
