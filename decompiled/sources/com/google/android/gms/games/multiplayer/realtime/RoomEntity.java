package com.google.android.gms.games.multiplayer.realtime;

import a.a;
import android.database.CharArrayBuffer;
import android.os.Bundle;
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
import com.google.android.gms.games.internal.GamesDowngradeableSafeParcel;
import com.google.android.gms.games.multiplayer.Participant;
import com.google.android.gms.games.multiplayer.ParticipantEntity;
import java.util.ArrayList;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@RetainForClient
@SafeParcelable.Class(creator = "RoomEntityCreator")
@SafeParcelable.Reserved({CredentialsApi.ACTIVITY_RESULT_ADD_ACCOUNT})
public final class RoomEntity extends GamesDowngradeableSafeParcel implements Room {
    public static final Parcelable.Creator<RoomEntity> CREATOR = new zza();

    @SafeParcelable.Field(getter = "getDescription", id = 5)
    private final String description;

    @SafeParcelable.Field(getter = "getRoomId", id = 1)
    private final String zzgt;

    @SafeParcelable.Field(getter = "getCreationTimestamp", id = 3)
    private final long zzoa;

    @SafeParcelable.Field(getter = "getParticipants", id = 8)
    private final ArrayList<ParticipantEntity> zzod;

    @SafeParcelable.Field(getter = "getVariant", id = 6)
    private final int zzoe;

    @SafeParcelable.Field(getter = "getAutoMatchCriteria", id = 7)
    private final Bundle zzoz;

    @SafeParcelable.Field(getter = "getCreatorId", id = 2)
    private final String zzpc;

    @SafeParcelable.Field(getter = "getStatus", id = 4)
    private final int zzpd;

    @SafeParcelable.Field(getter = "getAutoMatchWaitEstimateSeconds", id = 9)
    private final int zzpe;

    static final class zza extends zze {
        zza() {
        }

        @Override // com.google.android.gms.games.multiplayer.realtime.zze, android.os.Parcelable.Creator
        /* JADX INFO: renamed from: zzf, reason: merged with bridge method [inline-methods] */
        public final RoomEntity createFromParcel(Parcel parcel) {
            if (GamesDowngradeableSafeParcel.zzb(DowngradeableSafeParcel.getUnparcelClientVersion()) || DowngradeableSafeParcel.canUnparcelSafely(RoomEntity.class.getCanonicalName())) {
                return super.createFromParcel(parcel);
            }
            String string = parcel.readString();
            String string2 = parcel.readString();
            long j2 = parcel.readLong();
            int i2 = parcel.readInt();
            String string3 = parcel.readString();
            int i3 = parcel.readInt();
            Bundle bundle = parcel.readBundle();
            int i4 = parcel.readInt();
            ArrayList arrayList = new ArrayList(i4);
            for (int i5 = 0; i5 < i4; i5++) {
                arrayList.add(ParticipantEntity.CREATOR.createFromParcel(parcel));
            }
            return new RoomEntity(string, string2, j2, i2, string3, i3, bundle, arrayList, -1);
        }
    }

    public RoomEntity(Room room) {
        this.zzgt = room.getRoomId();
        this.zzpc = room.getCreatorId();
        this.zzoa = room.getCreationTimestamp();
        this.zzpd = room.getStatus();
        this.description = room.getDescription();
        this.zzoe = room.getVariant();
        this.zzoz = room.getAutoMatchCriteria();
        ArrayList<Participant> participants = room.getParticipants();
        int size = participants.size();
        this.zzod = new ArrayList<>(size);
        for (int i2 = 0; i2 < size; i2++) {
            this.zzod.add((ParticipantEntity) participants.get(i2).freeze());
        }
        this.zzpe = room.getAutoMatchWaitEstimateSeconds();
    }

    static int zza(Room room) {
        return Objects.hashCode(room.getRoomId(), room.getCreatorId(), Long.valueOf(room.getCreationTimestamp()), Integer.valueOf(room.getStatus()), room.getDescription(), Integer.valueOf(room.getVariant()), Integer.valueOf(com.google.android.gms.games.internal.zzc.zza(room.getAutoMatchCriteria())), room.getParticipants(), Integer.valueOf(room.getAutoMatchWaitEstimateSeconds()));
    }

    static String zzb(Room room) {
        return Objects.toStringHelper(room).add("RoomId", room.getRoomId()).add("CreatorId", room.getCreatorId()).add("CreationTimestamp", Long.valueOf(room.getCreationTimestamp())).add("RoomStatus", Integer.valueOf(room.getStatus())).add("Description", room.getDescription()).add("Variant", Integer.valueOf(room.getVariant())).add("AutoMatchCriteria", room.getAutoMatchCriteria()).add("Participants", room.getParticipants()).add("AutoMatchWaitEstimateSeconds", Integer.valueOf(room.getAutoMatchWaitEstimateSeconds())).toString();
    }

    static Participant zzc(Room room, String str) {
        ArrayList<Participant> participants = room.getParticipants();
        int size = participants.size();
        for (int i2 = 0; i2 < size; i2++) {
            Participant participant = participants.get(i2);
            if (participant.getParticipantId().equals(str)) {
                return participant;
            }
        }
        String roomId = room.getRoomId();
        StringBuilder sb = new StringBuilder(a.e(a.e(29, str), roomId));
        sb.append("Participant ");
        sb.append(str);
        sb.append(" is not in match ");
        sb.append(roomId);
        throw new IllegalStateException(sb.toString());
    }

    public final boolean equals(Object obj) {
        return zza(this, obj);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.android.gms.common.data.Freezable
    public final Room freeze() {
        return this;
    }

    @Override // com.google.android.gms.games.multiplayer.realtime.Room
    public final Bundle getAutoMatchCriteria() {
        return this.zzoz;
    }

    @Override // com.google.android.gms.games.multiplayer.realtime.Room
    public final int getAutoMatchWaitEstimateSeconds() {
        return this.zzpe;
    }

    @Override // com.google.android.gms.games.multiplayer.realtime.Room
    public final long getCreationTimestamp() {
        return this.zzoa;
    }

    @Override // com.google.android.gms.games.multiplayer.realtime.Room
    public final String getCreatorId() {
        return this.zzpc;
    }

    @Override // com.google.android.gms.games.multiplayer.realtime.Room
    public final String getDescription() {
        return this.description;
    }

    @Override // com.google.android.gms.games.multiplayer.realtime.Room
    public final Participant getParticipant(String str) {
        return zzc(this, str);
    }

    @Override // com.google.android.gms.games.multiplayer.realtime.Room
    public final String getParticipantId(String str) {
        return zzb(this, str);
    }

    @Override // com.google.android.gms.games.multiplayer.realtime.Room
    public final ArrayList<String> getParticipantIds() {
        return zzc(this);
    }

    @Override // com.google.android.gms.games.multiplayer.realtime.Room
    public final int getParticipantStatus(String str) {
        return zza((Room) this, str);
    }

    @Override // com.google.android.gms.games.multiplayer.Participatable
    public final ArrayList<Participant> getParticipants() {
        return new ArrayList<>(this.zzod);
    }

    @Override // com.google.android.gms.games.multiplayer.realtime.Room
    public final String getRoomId() {
        return this.zzgt;
    }

    @Override // com.google.android.gms.games.multiplayer.realtime.Room
    public final int getStatus() {
        return this.zzpd;
    }

    @Override // com.google.android.gms.games.multiplayer.realtime.Room
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
        if (!shouldDowngrade()) {
            int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
            SafeParcelWriter.writeString(parcel, 1, getRoomId(), false);
            SafeParcelWriter.writeString(parcel, 2, getCreatorId(), false);
            SafeParcelWriter.writeLong(parcel, 3, getCreationTimestamp());
            SafeParcelWriter.writeInt(parcel, 4, getStatus());
            SafeParcelWriter.writeString(parcel, 5, getDescription(), false);
            SafeParcelWriter.writeInt(parcel, 6, getVariant());
            SafeParcelWriter.writeBundle(parcel, 7, getAutoMatchCriteria(), false);
            SafeParcelWriter.writeTypedList(parcel, 8, getParticipants(), false);
            SafeParcelWriter.writeInt(parcel, 9, getAutoMatchWaitEstimateSeconds());
            SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
            return;
        }
        parcel.writeString(this.zzgt);
        parcel.writeString(this.zzpc);
        parcel.writeLong(this.zzoa);
        parcel.writeInt(this.zzpd);
        parcel.writeString(this.description);
        parcel.writeInt(this.zzoe);
        parcel.writeBundle(this.zzoz);
        int size = this.zzod.size();
        parcel.writeInt(size);
        for (int i3 = 0; i3 < size; i3++) {
            this.zzod.get(i3).writeToParcel(parcel, i2);
        }
    }

    @SafeParcelable.Constructor
    RoomEntity(@SafeParcelable.Param(id = 1) String str, @SafeParcelable.Param(id = 2) String str2, @SafeParcelable.Param(id = 3) long j2, @SafeParcelable.Param(id = 4) int i2, @SafeParcelable.Param(id = 5) String str3, @SafeParcelable.Param(id = 6) int i3, @SafeParcelable.Param(id = 7) Bundle bundle, @SafeParcelable.Param(id = 8) ArrayList<ParticipantEntity> arrayList, @SafeParcelable.Param(id = 9) int i4) {
        this.zzgt = str;
        this.zzpc = str2;
        this.zzoa = j2;
        this.zzpd = i2;
        this.description = str3;
        this.zzoe = i3;
        this.zzoz = bundle;
        this.zzod = arrayList;
        this.zzpe = i4;
    }

    static int zza(Room room, String str) {
        ArrayList<Participant> participants = room.getParticipants();
        int size = participants.size();
        for (int i2 = 0; i2 < size; i2++) {
            Participant participant = participants.get(i2);
            if (participant.getParticipantId().equals(str)) {
                return participant.getStatus();
            }
        }
        String roomId = room.getRoomId();
        StringBuilder sb = new StringBuilder(a.e(a.e(28, str), roomId));
        sb.append("Participant ");
        sb.append(str);
        sb.append(" is not in room ");
        sb.append(roomId);
        throw new IllegalStateException(sb.toString());
    }

    static String zzb(Room room, String str) {
        ArrayList<Participant> participants = room.getParticipants();
        int size = participants.size();
        for (int i2 = 0; i2 < size; i2++) {
            Participant participant = participants.get(i2);
            Player player = participant.getPlayer();
            if (player != null && player.getPlayerId().equals(str)) {
                return participant.getParticipantId();
            }
        }
        return null;
    }

    @Override // com.google.android.gms.games.multiplayer.realtime.Room
    public final void getDescription(CharArrayBuffer charArrayBuffer) {
        DataUtils.copyStringToBuffer(this.description, charArrayBuffer);
    }

    static ArrayList<String> zzc(Room room) {
        ArrayList<Participant> participants = room.getParticipants();
        int size = participants.size();
        ArrayList<String> arrayList = new ArrayList<>(size);
        for (int i2 = 0; i2 < size; i2++) {
            arrayList.add(participants.get(i2).getParticipantId());
        }
        return arrayList;
    }

    static boolean zza(Room room, Object obj) {
        if (!(obj instanceof Room)) {
            return false;
        }
        if (room == obj) {
            return true;
        }
        Room room2 = (Room) obj;
        return Objects.equal(room2.getRoomId(), room.getRoomId()) && Objects.equal(room2.getCreatorId(), room.getCreatorId()) && Objects.equal(Long.valueOf(room2.getCreationTimestamp()), Long.valueOf(room.getCreationTimestamp())) && Objects.equal(Integer.valueOf(room2.getStatus()), Integer.valueOf(room.getStatus())) && Objects.equal(room2.getDescription(), room.getDescription()) && Objects.equal(Integer.valueOf(room2.getVariant()), Integer.valueOf(room.getVariant())) && com.google.android.gms.games.internal.zzc.zza(room2.getAutoMatchCriteria(), room.getAutoMatchCriteria()) && Objects.equal(room2.getParticipants(), room.getParticipants()) && Objects.equal(Integer.valueOf(room2.getAutoMatchWaitEstimateSeconds()), Integer.valueOf(room.getAutoMatchWaitEstimateSeconds()));
    }
}
