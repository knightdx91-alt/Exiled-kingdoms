package com.google.android.gms.games.multiplayer.realtime;

import android.database.CharArrayBuffer;
import android.os.Bundle;
import android.os.Parcel;
import com.google.android.gms.common.data.DataBufferRef;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.multiplayer.Participant;
import com.google.android.gms.games.multiplayer.ParticipantRef;
import java.util.ArrayList;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class zzf extends DataBufferRef implements Room {
    private final int zzmz;

    zzf(DataHolder dataHolder, int i2, int i3) {
        super(dataHolder, i2);
        this.zzmz = i3;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // com.google.android.gms.common.data.DataBufferRef
    public final boolean equals(Object obj) {
        return RoomEntity.zza(this, obj);
    }

    @Override // com.google.android.gms.common.data.Freezable
    public final /* synthetic */ Room freeze() {
        return new RoomEntity(this);
    }

    @Override // com.google.android.gms.games.multiplayer.realtime.Room
    public final Bundle getAutoMatchCriteria() {
        if (getBoolean("has_automatch_criteria")) {
            return RoomConfig.createAutoMatchCriteria(getInteger("automatch_min_players"), getInteger("automatch_max_players"), getLong("automatch_bit_mask"));
        }
        return null;
    }

    @Override // com.google.android.gms.games.multiplayer.realtime.Room
    public final int getAutoMatchWaitEstimateSeconds() {
        return getInteger("automatch_wait_estimate_sec");
    }

    @Override // com.google.android.gms.games.multiplayer.realtime.Room
    public final long getCreationTimestamp() {
        return getLong("creation_timestamp");
    }

    @Override // com.google.android.gms.games.multiplayer.realtime.Room
    public final String getCreatorId() {
        return getString("creator_external");
    }

    @Override // com.google.android.gms.games.multiplayer.realtime.Room
    public final String getDescription() {
        return getString("description");
    }

    @Override // com.google.android.gms.games.multiplayer.realtime.Room
    public final Participant getParticipant(String str) {
        return RoomEntity.zzc(this, str);
    }

    @Override // com.google.android.gms.games.multiplayer.realtime.Room
    public final String getParticipantId(String str) {
        return RoomEntity.zzb(this, str);
    }

    @Override // com.google.android.gms.games.multiplayer.realtime.Room
    public final ArrayList<String> getParticipantIds() {
        return RoomEntity.zzc(this);
    }

    @Override // com.google.android.gms.games.multiplayer.realtime.Room
    public final int getParticipantStatus(String str) {
        return RoomEntity.zza((Room) this, str);
    }

    @Override // com.google.android.gms.games.multiplayer.Participatable
    public final ArrayList<Participant> getParticipants() {
        ArrayList<Participant> arrayList = new ArrayList<>(this.zzmz);
        for (int i2 = 0; i2 < this.zzmz; i2++) {
            arrayList.add(new ParticipantRef(this.mDataHolder, this.mDataRow + i2));
        }
        return arrayList;
    }

    @Override // com.google.android.gms.games.multiplayer.realtime.Room
    public final String getRoomId() {
        return getString("external_match_id");
    }

    @Override // com.google.android.gms.games.multiplayer.realtime.Room
    public final int getStatus() {
        return getInteger(Games.EXTRA_STATUS);
    }

    @Override // com.google.android.gms.games.multiplayer.realtime.Room
    public final int getVariant() {
        return getInteger("variant");
    }

    @Override // com.google.android.gms.common.data.DataBufferRef
    public final int hashCode() {
        return RoomEntity.zza(this);
    }

    public final String toString() {
        return RoomEntity.zzb(this);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        ((RoomEntity) ((Room) freeze())).writeToParcel(parcel, i2);
    }

    @Override // com.google.android.gms.games.multiplayer.realtime.Room
    public final void getDescription(CharArrayBuffer charArrayBuffer) {
        copyToBuffer("description", charArrayBuffer);
    }
}
