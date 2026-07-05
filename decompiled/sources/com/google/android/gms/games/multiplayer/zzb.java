package com.google.android.gms.games.multiplayer;

import android.os.Parcel;
import com.google.android.gms.common.data.DataBufferRef;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.GameRef;
import java.util.ArrayList;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class zzb extends DataBufferRef implements Invitation {
    private final Game zzmy;
    private final ArrayList<Participant> zzod;
    private final ParticipantRef zzog;

    zzb(DataHolder dataHolder, int i2, int i3) {
        super(dataHolder, i2);
        this.zzmy = new GameRef(dataHolder, i2);
        this.zzod = new ArrayList<>(i3);
        String string = getString("external_inviter_id");
        ParticipantRef participantRef = null;
        for (int i4 = 0; i4 < i3; i4++) {
            ParticipantRef participantRef2 = new ParticipantRef(this.mDataHolder, this.mDataRow + i4);
            if (participantRef2.getParticipantId().equals(string)) {
                participantRef = participantRef2;
            }
            this.zzod.add(participantRef2);
        }
        this.zzog = (ParticipantRef) Preconditions.checkNotNull(participantRef, "Must have a valid inviter!");
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // com.google.android.gms.common.data.DataBufferRef
    public final boolean equals(Object obj) {
        return InvitationEntity.zza(this, obj);
    }

    @Override // com.google.android.gms.common.data.Freezable
    public final /* synthetic */ Invitation freeze() {
        return new InvitationEntity(this);
    }

    @Override // com.google.android.gms.games.multiplayer.Invitation
    public final int getAvailableAutoMatchSlots() {
        if (getBoolean("has_automatch_criteria")) {
            return getInteger("automatch_max_players");
        }
        return 0;
    }

    @Override // com.google.android.gms.games.multiplayer.Invitation
    public final long getCreationTimestamp() {
        return Math.max(getLong("creation_timestamp"), getLong("last_modified_timestamp"));
    }

    @Override // com.google.android.gms.games.multiplayer.Invitation
    public final Game getGame() {
        return this.zzmy;
    }

    @Override // com.google.android.gms.games.multiplayer.Invitation
    public final String getInvitationId() {
        return getString("external_invitation_id");
    }

    @Override // com.google.android.gms.games.multiplayer.Invitation
    public final int getInvitationType() {
        return getInteger("type");
    }

    @Override // com.google.android.gms.games.multiplayer.Invitation
    public final Participant getInviter() {
        return this.zzog;
    }

    @Override // com.google.android.gms.games.multiplayer.Participatable
    public final ArrayList<Participant> getParticipants() {
        return this.zzod;
    }

    @Override // com.google.android.gms.games.multiplayer.Invitation
    public final int getVariant() {
        return getInteger("variant");
    }

    @Override // com.google.android.gms.common.data.DataBufferRef
    public final int hashCode() {
        return InvitationEntity.zza(this);
    }

    public final String toString() {
        return InvitationEntity.zzb(this);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        ((InvitationEntity) ((Invitation) freeze())).writeToParcel(parcel, i2);
    }
}
