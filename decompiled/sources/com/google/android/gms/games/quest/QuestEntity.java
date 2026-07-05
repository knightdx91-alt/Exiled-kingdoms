package com.google.android.gms.games.quest;

import android.database.CharArrayBuffer;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.auth.api.credentials.CredentialsApi;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.util.DataUtils;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.GameEntity;
import com.google.android.gms.games.internal.zzd;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@SafeParcelable.Class(creator = "QuestEntityCreator")
@SafeParcelable.Reserved({CredentialsApi.ACTIVITY_RESULT_ADD_ACCOUNT})
@Deprecated
public final class QuestEntity extends zzd implements Quest {
    public static final Parcelable.Creator<QuestEntity> CREATOR = new zzc();

    @SafeParcelable.Field(getter = "getDescription", id = 6)
    private final String description;

    @SafeParcelable.Field(getter = "getName", id = 12)
    private final String name;

    @SafeParcelable.Field(getter = "getState", id = 15)
    private final int state;

    @SafeParcelable.Field(getter = "getType", id = 16)
    private final int type;

    @SafeParcelable.Field(getter = "getLastUpdatedTimestamp", id = 8)
    private final long zzfk;

    @SafeParcelable.Field(getter = "getGame", id = 1)
    private final GameEntity zzky;

    @SafeParcelable.Field(getter = "getQuestId", id = 2)
    private final String zzpx;

    @SafeParcelable.Field(getter = "getAcceptedTimestamp", id = 3)
    private final long zzpy;

    @SafeParcelable.Field(getter = "getBannerImageUri", id = 4)
    private final Uri zzpz;

    @SafeParcelable.Field(getter = "getBannerImageUrl", id = 5)
    private final String zzqa;

    @SafeParcelable.Field(getter = "getEndTimestamp", id = 7)
    private final long zzqb;

    @SafeParcelable.Field(getter = "getIconImageUri", id = 9)
    private final Uri zzqc;

    @SafeParcelable.Field(getter = "getIconImageUrl", id = 10)
    private final String zzqd;

    @SafeParcelable.Field(getter = "getNotifyTimestamp", id = 13)
    private final long zzqe;

    @SafeParcelable.Field(getter = "getStartTimestamp", id = 14)
    private final long zzqf;

    @SafeParcelable.Field(getter = "getMilestones", id = 17)
    private final ArrayList<MilestoneEntity> zzqg;

    @SafeParcelable.Constructor
    QuestEntity(@SafeParcelable.Param(id = 1) GameEntity gameEntity, @SafeParcelable.Param(id = 2) String str, @SafeParcelable.Param(id = 3) long j2, @SafeParcelable.Param(id = 4) Uri uri, @SafeParcelable.Param(id = 5) String str2, @SafeParcelable.Param(id = 6) String str3, @SafeParcelable.Param(id = 7) long j3, @SafeParcelable.Param(id = 8) long j4, @SafeParcelable.Param(id = 9) Uri uri2, @SafeParcelable.Param(id = 10) String str4, @SafeParcelable.Param(id = 12) String str5, @SafeParcelable.Param(id = 13) long j5, @SafeParcelable.Param(id = 14) long j6, @SafeParcelable.Param(id = 15) int i2, @SafeParcelable.Param(id = 16) int i3, @SafeParcelable.Param(id = 17) ArrayList<MilestoneEntity> arrayList) {
        this.zzky = gameEntity;
        this.zzpx = str;
        this.zzpy = j2;
        this.zzpz = uri;
        this.zzqa = str2;
        this.description = str3;
        this.zzqb = j3;
        this.zzfk = j4;
        this.zzqc = uri2;
        this.zzqd = str4;
        this.name = str5;
        this.zzqe = j5;
        this.zzqf = j6;
        this.state = i2;
        this.type = i3;
        this.zzqg = arrayList;
    }

    static int zza(Quest quest) {
        return Objects.hashCode(quest.getGame(), quest.getQuestId(), Long.valueOf(quest.getAcceptedTimestamp()), quest.getBannerImageUri(), quest.getDescription(), Long.valueOf(quest.getEndTimestamp()), quest.getIconImageUri(), Long.valueOf(quest.getLastUpdatedTimestamp()), quest.zzcj(), quest.getName(), Long.valueOf(quest.zzck()), Long.valueOf(quest.getStartTimestamp()), Integer.valueOf(quest.getState()));
    }

    static String zzb(Quest quest) {
        return Objects.toStringHelper(quest).add("Game", quest.getGame()).add("QuestId", quest.getQuestId()).add("AcceptedTimestamp", Long.valueOf(quest.getAcceptedTimestamp())).add("BannerImageUri", quest.getBannerImageUri()).add("BannerImageUrl", quest.getBannerImageUrl()).add("Description", quest.getDescription()).add("EndTimestamp", Long.valueOf(quest.getEndTimestamp())).add("IconImageUri", quest.getIconImageUri()).add("IconImageUrl", quest.getIconImageUrl()).add("LastUpdatedTimestamp", Long.valueOf(quest.getLastUpdatedTimestamp())).add("Milestones", quest.zzcj()).add("Name", quest.getName()).add("NotifyTimestamp", Long.valueOf(quest.zzck())).add("StartTimestamp", Long.valueOf(quest.getStartTimestamp())).add("State", Integer.valueOf(quest.getState())).toString();
    }

    public final boolean equals(Object obj) {
        return zza(this, obj);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.android.gms.common.data.Freezable
    public final Quest freeze() {
        return this;
    }

    @Override // com.google.android.gms.games.quest.Quest
    public final long getAcceptedTimestamp() {
        return this.zzpy;
    }

    @Override // com.google.android.gms.games.quest.Quest
    public final Uri getBannerImageUri() {
        return this.zzpz;
    }

    @Override // com.google.android.gms.games.quest.Quest
    public final String getBannerImageUrl() {
        return this.zzqa;
    }

    @Override // com.google.android.gms.games.quest.Quest
    public final Milestone getCurrentMilestone() {
        return zzcj().get(0);
    }

    @Override // com.google.android.gms.games.quest.Quest
    public final String getDescription() {
        return this.description;
    }

    @Override // com.google.android.gms.games.quest.Quest
    public final long getEndTimestamp() {
        return this.zzqb;
    }

    @Override // com.google.android.gms.games.quest.Quest
    public final Game getGame() {
        return this.zzky;
    }

    @Override // com.google.android.gms.games.quest.Quest
    public final Uri getIconImageUri() {
        return this.zzqc;
    }

    @Override // com.google.android.gms.games.quest.Quest
    public final String getIconImageUrl() {
        return this.zzqd;
    }

    @Override // com.google.android.gms.games.quest.Quest
    public final long getLastUpdatedTimestamp() {
        return this.zzfk;
    }

    @Override // com.google.android.gms.games.quest.Quest
    public final String getName() {
        return this.name;
    }

    @Override // com.google.android.gms.games.quest.Quest
    public final String getQuestId() {
        return this.zzpx;
    }

    @Override // com.google.android.gms.games.quest.Quest
    public final long getStartTimestamp() {
        return this.zzqf;
    }

    @Override // com.google.android.gms.games.quest.Quest
    public final int getState() {
        return this.state;
    }

    @Override // com.google.android.gms.games.quest.Quest
    public final int getType() {
        return this.type;
    }

    public final int hashCode() {
        return zza(this);
    }

    @Override // com.google.android.gms.common.data.Freezable
    public final boolean isDataValid() {
        return true;
    }

    @Override // com.google.android.gms.games.quest.Quest
    public final boolean isEndingSoon() {
        return this.zzqe <= System.currentTimeMillis() + 1800000;
    }

    public final String toString() {
        return zzb(this);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, getGame(), i2, false);
        SafeParcelWriter.writeString(parcel, 2, getQuestId(), false);
        SafeParcelWriter.writeLong(parcel, 3, getAcceptedTimestamp());
        SafeParcelWriter.writeParcelable(parcel, 4, getBannerImageUri(), i2, false);
        SafeParcelWriter.writeString(parcel, 5, getBannerImageUrl(), false);
        SafeParcelWriter.writeString(parcel, 6, getDescription(), false);
        SafeParcelWriter.writeLong(parcel, 7, getEndTimestamp());
        SafeParcelWriter.writeLong(parcel, 8, getLastUpdatedTimestamp());
        SafeParcelWriter.writeParcelable(parcel, 9, getIconImageUri(), i2, false);
        SafeParcelWriter.writeString(parcel, 10, getIconImageUrl(), false);
        SafeParcelWriter.writeString(parcel, 12, getName(), false);
        SafeParcelWriter.writeLong(parcel, 13, this.zzqe);
        SafeParcelWriter.writeLong(parcel, 14, getStartTimestamp());
        SafeParcelWriter.writeInt(parcel, 15, getState());
        SafeParcelWriter.writeInt(parcel, 16, this.type);
        SafeParcelWriter.writeTypedList(parcel, 17, zzcj(), false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    @Override // com.google.android.gms.games.quest.Quest
    public final List<Milestone> zzcj() {
        return new ArrayList(this.zzqg);
    }

    @Override // com.google.android.gms.games.quest.Quest
    public final long zzck() {
        return this.zzqe;
    }

    public QuestEntity(Quest quest) {
        this.zzky = new GameEntity(quest.getGame());
        this.zzpx = quest.getQuestId();
        this.zzpy = quest.getAcceptedTimestamp();
        this.description = quest.getDescription();
        this.zzpz = quest.getBannerImageUri();
        this.zzqa = quest.getBannerImageUrl();
        this.zzqb = quest.getEndTimestamp();
        this.zzqc = quest.getIconImageUri();
        this.zzqd = quest.getIconImageUrl();
        this.zzfk = quest.getLastUpdatedTimestamp();
        this.name = quest.getName();
        this.zzqe = quest.zzck();
        this.zzqf = quest.getStartTimestamp();
        this.state = quest.getState();
        this.type = quest.getType();
        List<Milestone> listZzcj = quest.zzcj();
        int size = listZzcj.size();
        this.zzqg = new ArrayList<>(size);
        for (int i2 = 0; i2 < size; i2++) {
            this.zzqg.add((MilestoneEntity) listZzcj.get(i2).freeze());
        }
    }

    static boolean zza(Quest quest, Object obj) {
        if (!(obj instanceof Quest)) {
            return false;
        }
        if (quest == obj) {
            return true;
        }
        Quest quest2 = (Quest) obj;
        return Objects.equal(quest2.getGame(), quest.getGame()) && Objects.equal(quest2.getQuestId(), quest.getQuestId()) && Objects.equal(Long.valueOf(quest2.getAcceptedTimestamp()), Long.valueOf(quest.getAcceptedTimestamp())) && Objects.equal(quest2.getBannerImageUri(), quest.getBannerImageUri()) && Objects.equal(quest2.getDescription(), quest.getDescription()) && Objects.equal(Long.valueOf(quest2.getEndTimestamp()), Long.valueOf(quest.getEndTimestamp())) && Objects.equal(quest2.getIconImageUri(), quest.getIconImageUri()) && Objects.equal(Long.valueOf(quest2.getLastUpdatedTimestamp()), Long.valueOf(quest.getLastUpdatedTimestamp())) && Objects.equal(quest2.zzcj(), quest.zzcj()) && Objects.equal(quest2.getName(), quest.getName()) && Objects.equal(Long.valueOf(quest2.zzck()), Long.valueOf(quest.zzck())) && Objects.equal(Long.valueOf(quest2.getStartTimestamp()), Long.valueOf(quest.getStartTimestamp())) && Objects.equal(Integer.valueOf(quest2.getState()), Integer.valueOf(quest.getState()));
    }

    @Override // com.google.android.gms.games.quest.Quest
    public final void getDescription(CharArrayBuffer charArrayBuffer) {
        DataUtils.copyStringToBuffer(this.description, charArrayBuffer);
    }

    @Override // com.google.android.gms.games.quest.Quest
    public final void getName(CharArrayBuffer charArrayBuffer) {
        DataUtils.copyStringToBuffer(this.name, charArrayBuffer);
    }
}
