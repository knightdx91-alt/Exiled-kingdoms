package com.google.android.gms.games.multiplayer;

import android.database.CharArrayBuffer;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.auth.api.credentials.CredentialsApi;
import com.google.android.gms.common.internal.DowngradeableSafeParcel;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.util.DataUtils;
import com.google.android.gms.common.util.RetainForClient;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.PlayerEntity;
import com.google.android.gms.games.internal.GamesDowngradeableSafeParcel;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@RetainForClient
@SafeParcelable.Class(creator = "ParticipantEntityCreator")
@SafeParcelable.Reserved({CredentialsApi.ACTIVITY_RESULT_ADD_ACCOUNT})
public final class ParticipantEntity extends GamesDowngradeableSafeParcel implements Participant {
    public static final Parcelable.Creator<ParticipantEntity> CREATOR = new zza();

    @SafeParcelable.Field(getter = "getStatus", id = 5)
    private final int status;

    @SafeParcelable.Field(getter = "getIconImageUrl", id = 11)
    private final String zzac;

    @SafeParcelable.Field(getter = "getHiResImageUrl", id = 12)
    private final String zzad;

    @SafeParcelable.Field(getter = "getPlayer", id = 8)
    private final PlayerEntity zzfh;

    @SafeParcelable.Field(getter = "getParticipantId", id = 1)
    private final String zzhl;

    @SafeParcelable.Field(getter = "getDisplayName", id = 2)
    private final String zzn;

    @SafeParcelable.Field(getter = "getClientAddress", id = 6)
    private final String zzoh;

    @SafeParcelable.Field(getter = "isConnectedToRoom", id = 7)
    private final boolean zzoi;

    @SafeParcelable.Field(getter = "getCapabilities", id = 9)
    private final int zzoj;

    @SafeParcelable.Field(getter = "getResult", id = 10)
    private final ParticipantResult zzok;

    @SafeParcelable.Field(getter = "getIconImageUri", id = 3)
    private final Uri zzr;

    @SafeParcelable.Field(getter = "getHiResImageUri", id = 4)
    private final Uri zzs;

    static final class zza extends zzc {
        zza() {
        }

        @Override // com.google.android.gms.games.multiplayer.zzc, android.os.Parcelable.Creator
        /* JADX INFO: renamed from: zze, reason: merged with bridge method [inline-methods] */
        public final ParticipantEntity createFromParcel(Parcel parcel) {
            if (GamesDowngradeableSafeParcel.zzb(DowngradeableSafeParcel.getUnparcelClientVersion()) || DowngradeableSafeParcel.canUnparcelSafely(ParticipantEntity.class.getCanonicalName())) {
                return super.createFromParcel(parcel);
            }
            String string = parcel.readString();
            String string2 = parcel.readString();
            String string3 = parcel.readString();
            Uri uri = string3 == null ? null : Uri.parse(string3);
            String string4 = parcel.readString();
            return new ParticipantEntity(string, string2, uri, string4 == null ? null : Uri.parse(string4), parcel.readInt(), parcel.readString(), parcel.readInt() > 0, parcel.readInt() > 0 ? PlayerEntity.CREATOR.createFromParcel(parcel) : null, 7, null, null, null);
        }
    }

    public ParticipantEntity(Participant participant) {
        this.zzhl = participant.getParticipantId();
        this.zzn = participant.getDisplayName();
        this.zzr = participant.getIconImageUri();
        this.zzs = participant.getHiResImageUri();
        this.status = participant.getStatus();
        this.zzoh = participant.zzcg();
        this.zzoi = participant.isConnectedToRoom();
        Player player = participant.getPlayer();
        this.zzfh = player == null ? null : new PlayerEntity(player);
        this.zzoj = participant.getCapabilities();
        this.zzok = participant.getResult();
        this.zzac = participant.getIconImageUrl();
        this.zzad = participant.getHiResImageUrl();
    }

    static int zza(Participant participant) {
        return Objects.hashCode(participant.getPlayer(), Integer.valueOf(participant.getStatus()), participant.zzcg(), Boolean.valueOf(participant.isConnectedToRoom()), participant.getDisplayName(), participant.getIconImageUri(), participant.getHiResImageUri(), Integer.valueOf(participant.getCapabilities()), participant.getResult(), participant.getParticipantId());
    }

    static String zzb(Participant participant) {
        return Objects.toStringHelper(participant).add("ParticipantId", participant.getParticipantId()).add("Player", participant.getPlayer()).add("Status", Integer.valueOf(participant.getStatus())).add("ClientAddress", participant.zzcg()).add("ConnectedToRoom", Boolean.valueOf(participant.isConnectedToRoom())).add("DisplayName", participant.getDisplayName()).add("IconImage", participant.getIconImageUri()).add("IconImageUrl", participant.getIconImageUrl()).add("HiResImage", participant.getHiResImageUri()).add("HiResImageUrl", participant.getHiResImageUrl()).add("Capabilities", Integer.valueOf(participant.getCapabilities())).add("Result", participant.getResult()).toString();
    }

    public final boolean equals(Object obj) {
        return zza(this, obj);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.android.gms.common.data.Freezable
    public final Participant freeze() {
        return this;
    }

    @Override // com.google.android.gms.games.multiplayer.Participant
    public final int getCapabilities() {
        return this.zzoj;
    }

    @Override // com.google.android.gms.games.multiplayer.Participant
    public final String getDisplayName() {
        PlayerEntity playerEntity = this.zzfh;
        return playerEntity == null ? this.zzn : playerEntity.getDisplayName();
    }

    @Override // com.google.android.gms.games.multiplayer.Participant
    public final Uri getHiResImageUri() {
        PlayerEntity playerEntity = this.zzfh;
        return playerEntity == null ? this.zzs : playerEntity.getHiResImageUri();
    }

    @Override // com.google.android.gms.games.multiplayer.Participant
    public final String getHiResImageUrl() {
        PlayerEntity playerEntity = this.zzfh;
        return playerEntity == null ? this.zzad : playerEntity.getHiResImageUrl();
    }

    @Override // com.google.android.gms.games.multiplayer.Participant
    public final Uri getIconImageUri() {
        PlayerEntity playerEntity = this.zzfh;
        return playerEntity == null ? this.zzr : playerEntity.getIconImageUri();
    }

    @Override // com.google.android.gms.games.multiplayer.Participant
    public final String getIconImageUrl() {
        PlayerEntity playerEntity = this.zzfh;
        return playerEntity == null ? this.zzac : playerEntity.getIconImageUrl();
    }

    @Override // com.google.android.gms.games.multiplayer.Participant
    public final String getParticipantId() {
        return this.zzhl;
    }

    @Override // com.google.android.gms.games.multiplayer.Participant
    public final Player getPlayer() {
        return this.zzfh;
    }

    @Override // com.google.android.gms.games.multiplayer.Participant
    public final ParticipantResult getResult() {
        return this.zzok;
    }

    @Override // com.google.android.gms.games.multiplayer.Participant
    public final int getStatus() {
        return this.status;
    }

    public final int hashCode() {
        return zza(this);
    }

    @Override // com.google.android.gms.games.multiplayer.Participant
    public final boolean isConnectedToRoom() {
        return this.zzoi;
    }

    @Override // com.google.android.gms.common.data.Freezable
    public final boolean isDataValid() {
        return true;
    }

    @Override // com.google.android.gms.common.internal.DowngradeableSafeParcel
    public final void setShouldDowngrade(boolean z2) {
        super.setShouldDowngrade(z2);
        PlayerEntity playerEntity = this.zzfh;
        if (playerEntity != null) {
            playerEntity.setShouldDowngrade(z2);
        }
    }

    public final String toString() {
        return zzb(this);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        if (shouldDowngrade()) {
            parcel.writeString(this.zzhl);
            parcel.writeString(this.zzn);
            Uri uri = this.zzr;
            parcel.writeString(uri == null ? null : uri.toString());
            Uri uri2 = this.zzs;
            parcel.writeString(uri2 != null ? uri2.toString() : null);
            parcel.writeInt(this.status);
            parcel.writeString(this.zzoh);
            parcel.writeInt(this.zzoi ? 1 : 0);
            parcel.writeInt(this.zzfh == null ? 0 : 1);
            PlayerEntity playerEntity = this.zzfh;
            if (playerEntity != null) {
                playerEntity.writeToParcel(parcel, i2);
                return;
            }
            return;
        }
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, getParticipantId(), false);
        SafeParcelWriter.writeString(parcel, 2, getDisplayName(), false);
        SafeParcelWriter.writeParcelable(parcel, 3, getIconImageUri(), i2, false);
        SafeParcelWriter.writeParcelable(parcel, 4, getHiResImageUri(), i2, false);
        SafeParcelWriter.writeInt(parcel, 5, getStatus());
        SafeParcelWriter.writeString(parcel, 6, this.zzoh, false);
        SafeParcelWriter.writeBoolean(parcel, 7, isConnectedToRoom());
        SafeParcelWriter.writeParcelable(parcel, 8, getPlayer(), i2, false);
        SafeParcelWriter.writeInt(parcel, 9, this.zzoj);
        SafeParcelWriter.writeParcelable(parcel, 10, getResult(), i2, false);
        SafeParcelWriter.writeString(parcel, 11, getIconImageUrl(), false);
        SafeParcelWriter.writeString(parcel, 12, getHiResImageUrl(), false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    @Override // com.google.android.gms.games.multiplayer.Participant
    public final String zzcg() {
        return this.zzoh;
    }

    @SafeParcelable.Constructor
    ParticipantEntity(@SafeParcelable.Param(id = 1) String str, @SafeParcelable.Param(id = 2) String str2, @SafeParcelable.Param(id = 3) Uri uri, @SafeParcelable.Param(id = 4) Uri uri2, @SafeParcelable.Param(id = 5) int i2, @SafeParcelable.Param(id = 6) String str3, @SafeParcelable.Param(id = 7) boolean z2, @SafeParcelable.Param(id = 8) PlayerEntity playerEntity, @SafeParcelable.Param(id = 9) int i3, @SafeParcelable.Param(id = 10) ParticipantResult participantResult, @SafeParcelable.Param(id = 11) String str4, @SafeParcelable.Param(id = 12) String str5) {
        this.zzhl = str;
        this.zzn = str2;
        this.zzr = uri;
        this.zzs = uri2;
        this.status = i2;
        this.zzoh = str3;
        this.zzoi = z2;
        this.zzfh = playerEntity;
        this.zzoj = i3;
        this.zzok = participantResult;
        this.zzac = str4;
        this.zzad = str5;
    }

    static boolean zza(Participant participant, Object obj) {
        if (!(obj instanceof Participant)) {
            return false;
        }
        if (participant == obj) {
            return true;
        }
        Participant participant2 = (Participant) obj;
        return Objects.equal(participant2.getPlayer(), participant.getPlayer()) && Objects.equal(Integer.valueOf(participant2.getStatus()), Integer.valueOf(participant.getStatus())) && Objects.equal(participant2.zzcg(), participant.zzcg()) && Objects.equal(Boolean.valueOf(participant2.isConnectedToRoom()), Boolean.valueOf(participant.isConnectedToRoom())) && Objects.equal(participant2.getDisplayName(), participant.getDisplayName()) && Objects.equal(participant2.getIconImageUri(), participant.getIconImageUri()) && Objects.equal(participant2.getHiResImageUri(), participant.getHiResImageUri()) && Objects.equal(Integer.valueOf(participant2.getCapabilities()), Integer.valueOf(participant.getCapabilities())) && Objects.equal(participant2.getResult(), participant.getResult()) && Objects.equal(participant2.getParticipantId(), participant.getParticipantId());
    }

    @Override // com.google.android.gms.games.multiplayer.Participant
    public final void getDisplayName(CharArrayBuffer charArrayBuffer) {
        PlayerEntity playerEntity = this.zzfh;
        if (playerEntity == null) {
            DataUtils.copyStringToBuffer(this.zzn, charArrayBuffer);
        } else {
            playerEntity.getDisplayName(charArrayBuffer);
        }
    }
}
