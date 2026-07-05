package com.google.android.gms.games.multiplayer.turnbased;

import android.database.CharArrayBuffer;
import android.os.Bundle;
import android.os.Parcel;
import com.google.android.gms.common.data.DataBufferRef;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.GameRef;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.multiplayer.Participant;
import com.google.android.gms.games.multiplayer.ParticipantRef;
import java.util.ArrayList;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class zzd extends DataBufferRef implements TurnBasedMatch {
    private final Game zzmy;
    private final int zzmz;

    zzd(DataHolder dataHolder, int i2, int i3) {
        super(dataHolder, i2);
        this.zzmy = new GameRef(dataHolder, i2);
        this.zzmz = i3;
    }

    @Override // com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatch
    public final boolean canRematch() {
        return getTurnStatus() == 3 && getRematchId() == null && getParticipants().size() > 1;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // com.google.android.gms.common.data.DataBufferRef
    public final boolean equals(Object obj) {
        return TurnBasedMatchEntity.zza(this, obj);
    }

    @Override // com.google.android.gms.common.data.Freezable
    public final /* synthetic */ TurnBasedMatch freeze() {
        return new TurnBasedMatchEntity(this);
    }

    @Override // com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatch
    public final Bundle getAutoMatchCriteria() {
        if (getBoolean("has_automatch_criteria")) {
            return TurnBasedMatchConfig.createAutoMatchCriteria(getInteger("automatch_min_players"), getInteger("automatch_max_players"), getLong("automatch_bit_mask"));
        }
        return null;
    }

    @Override // com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatch
    public final int getAvailableAutoMatchSlots() {
        if (getBoolean("has_automatch_criteria")) {
            return getInteger("automatch_max_players");
        }
        return 0;
    }

    @Override // com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatch
    public final long getCreationTimestamp() {
        return getLong("creation_timestamp");
    }

    @Override // com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatch
    public final String getCreatorId() {
        return getString("creator_external");
    }

    @Override // com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatch
    public final byte[] getData() {
        return getByteArray("data");
    }

    @Override // com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatch
    public final String getDescription() {
        return getString("description");
    }

    @Override // com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatch
    public final Participant getDescriptionParticipant() {
        String descriptionParticipantId = getDescriptionParticipantId();
        if (descriptionParticipantId == null) {
            return null;
        }
        return getParticipant(descriptionParticipantId);
    }

    @Override // com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatch
    public final String getDescriptionParticipantId() {
        return getString("description_participant_id");
    }

    @Override // com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatch
    public final Game getGame() {
        return this.zzmy;
    }

    @Override // com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatch
    public final long getLastUpdatedTimestamp() {
        return getLong("last_updated_timestamp");
    }

    @Override // com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatch
    public final String getLastUpdaterId() {
        return getString("last_updater_external");
    }

    @Override // com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatch
    public final String getMatchId() {
        return getString("external_match_id");
    }

    @Override // com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatch
    public final int getMatchNumber() {
        return getInteger("match_number");
    }

    @Override // com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatch
    public final Participant getParticipant(String str) {
        return TurnBasedMatchEntity.zzc(this, str);
    }

    @Override // com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatch
    public final String getParticipantId(String str) {
        return TurnBasedMatchEntity.zzb(this, str);
    }

    @Override // com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatch
    public final ArrayList<String> getParticipantIds() {
        return TurnBasedMatchEntity.zzc(this);
    }

    @Override // com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatch
    public final int getParticipantStatus(String str) {
        return TurnBasedMatchEntity.zza((TurnBasedMatch) this, str);
    }

    @Override // com.google.android.gms.games.multiplayer.Participatable
    public final ArrayList<Participant> getParticipants() {
        ArrayList<Participant> arrayList = new ArrayList<>(this.zzmz);
        for (int i2 = 0; i2 < this.zzmz; i2++) {
            arrayList.add(new ParticipantRef(this.mDataHolder, this.mDataRow + i2));
        }
        return arrayList;
    }

    @Override // com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatch
    public final String getPendingParticipantId() {
        return getString("pending_participant_external");
    }

    @Override // com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatch
    public final byte[] getPreviousMatchData() {
        return getByteArray("previous_match_data");
    }

    @Override // com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatch
    public final String getRematchId() {
        return getString("rematch_id");
    }

    @Override // com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatch
    public final int getStatus() {
        return getInteger(Games.EXTRA_STATUS);
    }

    @Override // com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatch
    public final int getTurnStatus() {
        return getInteger("user_match_status");
    }

    @Override // com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatch
    public final int getVariant() {
        return getInteger("variant");
    }

    @Override // com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatch
    public final int getVersion() {
        return getInteger("version");
    }

    @Override // com.google.android.gms.common.data.DataBufferRef
    public final int hashCode() {
        return TurnBasedMatchEntity.zza(this);
    }

    @Override // com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatch
    public final boolean isLocallyModified() {
        return getBoolean("upsync_required");
    }

    public final String toString() {
        return TurnBasedMatchEntity.zzb(this);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        ((TurnBasedMatchEntity) ((TurnBasedMatch) freeze())).writeToParcel(parcel, i2);
    }

    @Override // com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatch
    public final void getDescription(CharArrayBuffer charArrayBuffer) {
        copyToBuffer("description", charArrayBuffer);
    }
}
