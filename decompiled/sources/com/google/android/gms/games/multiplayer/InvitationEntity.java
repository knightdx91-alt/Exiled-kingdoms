package com.google.android.gms.games.multiplayer;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.auth.api.credentials.CredentialsApi;
import com.google.android.gms.common.internal.DowngradeableSafeParcel;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.util.RetainForClient;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.GameEntity;
import com.google.android.gms.games.internal.GamesDowngradeableSafeParcel;
import java.util.ArrayList;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@RetainForClient
@SafeParcelable.Class(creator = "InvitationEntityCreator")
@SafeParcelable.Reserved({CredentialsApi.ACTIVITY_RESULT_ADD_ACCOUNT})
public final class InvitationEntity extends GamesDowngradeableSafeParcel implements Invitation {
    public static final Parcelable.Creator<InvitationEntity> CREATOR = new zza();

    @SafeParcelable.Field(getter = "getInvitationId", id = 2)
    private final String zzgr;

    @SafeParcelable.Field(getter = "getGame", id = 1)
    private final GameEntity zzky;

    @SafeParcelable.Field(getter = "getCreationTimestamp", id = 3)
    private final long zzoa;

    @SafeParcelable.Field(getter = "getInvitationType", id = 4)
    private final int zzob;

    @SafeParcelable.Field(getter = "getInviter", id = 5)
    private final ParticipantEntity zzoc;

    @SafeParcelable.Field(getter = "getParticipants", id = 6)
    private final ArrayList<ParticipantEntity> zzod;

    @SafeParcelable.Field(getter = "getVariant", id = 7)
    private final int zzoe;

    @SafeParcelable.Field(getter = "getAvailableAutoMatchSlots", id = 8)
    private final int zzof;

    static final class zza extends com.google.android.gms.games.multiplayer.zza {
        zza() {
        }

        @Override // com.google.android.gms.games.multiplayer.zza, android.os.Parcelable.Creator
        /* JADX INFO: renamed from: zzd */
        public final InvitationEntity createFromParcel(Parcel parcel) {
            if (GamesDowngradeableSafeParcel.zzb(DowngradeableSafeParcel.getUnparcelClientVersion()) || DowngradeableSafeParcel.canUnparcelSafely(InvitationEntity.class.getCanonicalName())) {
                return super.createFromParcel(parcel);
            }
            GameEntity gameEntityCreateFromParcel = GameEntity.CREATOR.createFromParcel(parcel);
            String string = parcel.readString();
            long j2 = parcel.readLong();
            int i2 = parcel.readInt();
            ParticipantEntity participantEntityCreateFromParcel = ParticipantEntity.CREATOR.createFromParcel(parcel);
            int i3 = parcel.readInt();
            ArrayList arrayList = new ArrayList(i3);
            for (int i4 = 0; i4 < i3; i4++) {
                arrayList.add(ParticipantEntity.CREATOR.createFromParcel(parcel));
            }
            return new InvitationEntity(gameEntityCreateFromParcel, string, j2, i2, participantEntityCreateFromParcel, arrayList, -1, 0);
        }
    }

    @SafeParcelable.Constructor
    InvitationEntity(@SafeParcelable.Param(id = 1) GameEntity gameEntity, @SafeParcelable.Param(id = 2) String str, @SafeParcelable.Param(id = 3) long j2, @SafeParcelable.Param(id = 4) int i2, @SafeParcelable.Param(id = 5) ParticipantEntity participantEntity, @SafeParcelable.Param(id = 6) ArrayList<ParticipantEntity> arrayList, @SafeParcelable.Param(id = 7) int i3, @SafeParcelable.Param(id = 8) int i4) {
        this.zzky = gameEntity;
        this.zzgr = str;
        this.zzoa = j2;
        this.zzob = i2;
        this.zzoc = participantEntity;
        this.zzod = arrayList;
        this.zzoe = i3;
        this.zzof = i4;
    }

    static int zza(Invitation invitation) {
        return Objects.hashCode(invitation.getGame(), invitation.getInvitationId(), Long.valueOf(invitation.getCreationTimestamp()), Integer.valueOf(invitation.getInvitationType()), invitation.getInviter(), invitation.getParticipants(), Integer.valueOf(invitation.getVariant()), Integer.valueOf(invitation.getAvailableAutoMatchSlots()));
    }

    static String zzb(Invitation invitation) {
        return Objects.toStringHelper(invitation).add("Game", invitation.getGame()).add("InvitationId", invitation.getInvitationId()).add("CreationTimestamp", Long.valueOf(invitation.getCreationTimestamp())).add("InvitationType", Integer.valueOf(invitation.getInvitationType())).add("Inviter", invitation.getInviter()).add("Participants", invitation.getParticipants()).add("Variant", Integer.valueOf(invitation.getVariant())).add("AvailableAutoMatchSlots", Integer.valueOf(invitation.getAvailableAutoMatchSlots())).toString();
    }

    public final boolean equals(Object obj) {
        return zza(this, obj);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.android.gms.common.data.Freezable
    public final Invitation freeze() {
        return this;
    }

    @Override // com.google.android.gms.games.multiplayer.Invitation
    public final int getAvailableAutoMatchSlots() {
        return this.zzof;
    }

    @Override // com.google.android.gms.games.multiplayer.Invitation
    public final long getCreationTimestamp() {
        return this.zzoa;
    }

    @Override // com.google.android.gms.games.multiplayer.Invitation
    public final Game getGame() {
        return this.zzky;
    }

    @Override // com.google.android.gms.games.multiplayer.Invitation
    public final String getInvitationId() {
        return this.zzgr;
    }

    @Override // com.google.android.gms.games.multiplayer.Invitation
    public final int getInvitationType() {
        return this.zzob;
    }

    @Override // com.google.android.gms.games.multiplayer.Invitation
    public final Participant getInviter() {
        return this.zzoc;
    }

    @Override // com.google.android.gms.games.multiplayer.Participatable
    public final ArrayList<Participant> getParticipants() {
        return new ArrayList<>(this.zzod);
    }

    @Override // com.google.android.gms.games.multiplayer.Invitation
    public final int getVariant() {
        return this.zzoe;
    }

    public final int hashCode() {
        return zza(this);
    }

    @Override // com.google.android.gms.common.data.Freezable
    public final boolean isDataValid() {
        return true;
    }

    @Override // com.google.android.gms.common.internal.DowngradeableSafeParcel
    public final void setShouldDowngrade(boolean z2) {
        super.setShouldDowngrade(z2);
        this.zzky.setShouldDowngrade(z2);
        this.zzoc.setShouldDowngrade(z2);
        int size = this.zzod.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.zzod.get(i2).setShouldDowngrade(z2);
        }
    }

    public final String toString() {
        return zzb(this);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        if (shouldDowngrade()) {
            this.zzky.writeToParcel(parcel, i2);
            parcel.writeString(this.zzgr);
            parcel.writeLong(this.zzoa);
            parcel.writeInt(this.zzob);
            this.zzoc.writeToParcel(parcel, i2);
            int size = this.zzod.size();
            parcel.writeInt(size);
            for (int i3 = 0; i3 < size; i3++) {
                this.zzod.get(i3).writeToParcel(parcel, i2);
            }
            return;
        }
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, getGame(), i2, false);
        SafeParcelWriter.writeString(parcel, 2, getInvitationId(), false);
        SafeParcelWriter.writeLong(parcel, 3, getCreationTimestamp());
        SafeParcelWriter.writeInt(parcel, 4, getInvitationType());
        SafeParcelWriter.writeParcelable(parcel, 5, getInviter(), i2, false);
        SafeParcelWriter.writeTypedList(parcel, 6, getParticipants(), false);
        SafeParcelWriter.writeInt(parcel, 7, getVariant());
        SafeParcelWriter.writeInt(parcel, 8, getAvailableAutoMatchSlots());
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    InvitationEntity(Invitation invitation) {
        this.zzky = new GameEntity(invitation.getGame());
        this.zzgr = invitation.getInvitationId();
        this.zzoa = invitation.getCreationTimestamp();
        this.zzob = invitation.getInvitationType();
        this.zzoe = invitation.getVariant();
        this.zzof = invitation.getAvailableAutoMatchSlots();
        String participantId = invitation.getInviter().getParticipantId();
        ArrayList<Participant> participants = invitation.getParticipants();
        int size = participants.size();
        this.zzod = new ArrayList<>(size);
        Participant participant = null;
        for (int i2 = 0; i2 < size; i2++) {
            Participant participant2 = participants.get(i2);
            if (participant2.getParticipantId().equals(participantId)) {
                participant = participant2;
            }
            this.zzod.add((ParticipantEntity) participant2.freeze());
        }
        Preconditions.checkNotNull(participant, "Must have a valid inviter!");
        this.zzoc = (ParticipantEntity) participant.freeze();
    }

    static boolean zza(Invitation invitation, Object obj) {
        if (!(obj instanceof Invitation)) {
            return false;
        }
        if (invitation == obj) {
            return true;
        }
        Invitation invitation2 = (Invitation) obj;
        return Objects.equal(invitation2.getGame(), invitation.getGame()) && Objects.equal(invitation2.getInvitationId(), invitation.getInvitationId()) && Objects.equal(Long.valueOf(invitation2.getCreationTimestamp()), Long.valueOf(invitation.getCreationTimestamp())) && Objects.equal(Integer.valueOf(invitation2.getInvitationType()), Integer.valueOf(invitation.getInvitationType())) && Objects.equal(invitation2.getInviter(), invitation.getInviter()) && Objects.equal(invitation2.getParticipants(), invitation.getParticipants()) && Objects.equal(Integer.valueOf(invitation2.getVariant()), Integer.valueOf(invitation.getVariant())) && Objects.equal(Integer.valueOf(invitation2.getAvailableAutoMatchSlots()), Integer.valueOf(invitation.getAvailableAutoMatchSlots()));
    }
}
