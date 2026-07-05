package com.google.android.gms.games.quest;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.auth.api.credentials.CredentialsApi;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.games.internal.zzd;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@SafeParcelable.Class(creator = "MilestoneEntityCreator")
@SafeParcelable.Reserved({CredentialsApi.ACTIVITY_RESULT_ADD_ACCOUNT})
@Deprecated
public final class MilestoneEntity extends zzd implements Milestone {
    public static final Parcelable.Creator<MilestoneEntity> CREATOR = new zza();

    @SafeParcelable.Field(getter = "getState", id = 5)
    private final int state;

    @SafeParcelable.Field(getter = "getEventId", id = 6)
    private final String zzfm;

    @SafeParcelable.Field(getter = "getMilestoneId", id = 1)
    private final String zzho;

    @SafeParcelable.Field(getter = "getCurrentProgress", id = 2)
    private final long zzpu;

    @SafeParcelable.Field(getter = "getTargetProgress", id = 3)
    private final long zzpv;

    @SafeParcelable.Field(getter = "getCompletionRewardData", id = 4)
    private final byte[] zzpw;

    public MilestoneEntity(Milestone milestone) {
        this.zzho = milestone.getMilestoneId();
        this.zzpu = milestone.getCurrentProgress();
        this.zzpv = milestone.getTargetProgress();
        this.state = milestone.getState();
        this.zzfm = milestone.getEventId();
        byte[] completionRewardData = milestone.getCompletionRewardData();
        if (completionRewardData == null) {
            this.zzpw = null;
            return;
        }
        byte[] bArr = new byte[completionRewardData.length];
        this.zzpw = bArr;
        System.arraycopy(completionRewardData, 0, bArr, 0, completionRewardData.length);
    }

    static int zza(Milestone milestone) {
        return Objects.hashCode(milestone.getMilestoneId(), Long.valueOf(milestone.getCurrentProgress()), Long.valueOf(milestone.getTargetProgress()), Integer.valueOf(milestone.getState()), milestone.getEventId());
    }

    static String zzb(Milestone milestone) {
        return Objects.toStringHelper(milestone).add("MilestoneId", milestone.getMilestoneId()).add("CurrentProgress", Long.valueOf(milestone.getCurrentProgress())).add("TargetProgress", Long.valueOf(milestone.getTargetProgress())).add("State", Integer.valueOf(milestone.getState())).add("CompletionRewardData", milestone.getCompletionRewardData()).add("EventId", milestone.getEventId()).toString();
    }

    public final boolean equals(Object obj) {
        return zza(this, obj);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.android.gms.common.data.Freezable
    public final Milestone freeze() {
        return this;
    }

    @Override // com.google.android.gms.games.quest.Milestone
    public final byte[] getCompletionRewardData() {
        return this.zzpw;
    }

    @Override // com.google.android.gms.games.quest.Milestone
    public final long getCurrentProgress() {
        return this.zzpu;
    }

    @Override // com.google.android.gms.games.quest.Milestone
    public final String getEventId() {
        return this.zzfm;
    }

    @Override // com.google.android.gms.games.quest.Milestone
    public final String getMilestoneId() {
        return this.zzho;
    }

    @Override // com.google.android.gms.games.quest.Milestone
    public final int getState() {
        return this.state;
    }

    @Override // com.google.android.gms.games.quest.Milestone
    public final long getTargetProgress() {
        return this.zzpv;
    }

    public final int hashCode() {
        return zza(this);
    }

    @Override // com.google.android.gms.common.data.Freezable
    public final boolean isDataValid() {
        return true;
    }

    public final String toString() {
        return zzb(this);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, getMilestoneId(), false);
        SafeParcelWriter.writeLong(parcel, 2, getCurrentProgress());
        SafeParcelWriter.writeLong(parcel, 3, getTargetProgress());
        SafeParcelWriter.writeByteArray(parcel, 4, getCompletionRewardData(), false);
        SafeParcelWriter.writeInt(parcel, 5, getState());
        SafeParcelWriter.writeString(parcel, 6, getEventId(), false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    @SafeParcelable.Constructor
    MilestoneEntity(@SafeParcelable.Param(id = 1) String str, @SafeParcelable.Param(id = 2) long j2, @SafeParcelable.Param(id = 3) long j3, @SafeParcelable.Param(id = 4) byte[] bArr, @SafeParcelable.Param(id = 5) int i2, @SafeParcelable.Param(id = 6) String str2) {
        this.zzho = str;
        this.zzpu = j2;
        this.zzpv = j3;
        this.zzpw = bArr;
        this.state = i2;
        this.zzfm = str2;
    }

    static boolean zza(Milestone milestone, Object obj) {
        if (!(obj instanceof Milestone)) {
            return false;
        }
        if (milestone == obj) {
            return true;
        }
        Milestone milestone2 = (Milestone) obj;
        return Objects.equal(milestone2.getMilestoneId(), milestone.getMilestoneId()) && Objects.equal(Long.valueOf(milestone2.getCurrentProgress()), Long.valueOf(milestone.getCurrentProgress())) && Objects.equal(Long.valueOf(milestone2.getTargetProgress()), Long.valueOf(milestone.getTargetProgress())) && Objects.equal(Integer.valueOf(milestone2.getState()), Integer.valueOf(milestone.getState())) && Objects.equal(milestone2.getEventId(), milestone.getEventId());
    }
}
