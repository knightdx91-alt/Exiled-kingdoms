package com.google.android.gms.games.quest;

import android.os.Parcel;
import com.google.android.gms.common.data.DataBufferRef;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.internal.zzh;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class zzb extends DataBufferRef implements Milestone {
    zzb(DataHolder dataHolder, int i2) {
        super(dataHolder, i2);
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // com.google.android.gms.common.data.DataBufferRef
    public final boolean equals(Object obj) {
        return MilestoneEntity.zza(this, obj);
    }

    @Override // com.google.android.gms.common.data.Freezable
    public final /* synthetic */ Milestone freeze() {
        return new MilestoneEntity(this);
    }

    @Override // com.google.android.gms.games.quest.Milestone
    public final byte[] getCompletionRewardData() {
        return getByteArray("completion_reward_data");
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0012  */
    @Override // com.google.android.gms.games.quest.Milestone
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final long getCurrentProgress() {
        long targetProgress;
        int state = getState();
        long j2 = 0;
        if (state == 1) {
            targetProgress = 0;
        } else if (state == 2) {
            targetProgress = getLong("current_value");
            if (getLong("quest_state") != 6) {
                targetProgress -= getLong("initial_value");
            }
        } else if (state == 3 || state == 4) {
            targetProgress = getTargetProgress();
        }
        if (targetProgress < 0) {
            zzh.e("MilestoneRef", "Current progress should never be negative");
        } else {
            j2 = targetProgress;
        }
        if (j2 <= getTargetProgress()) {
            return j2;
        }
        zzh.e("MilestoneRef", "Current progress should never exceed target progress");
        return getTargetProgress();
    }

    @Override // com.google.android.gms.games.quest.Milestone
    public final String getEventId() {
        return getString("external_event_id");
    }

    @Override // com.google.android.gms.games.quest.Milestone
    public final String getMilestoneId() {
        return getString("external_milestone_id");
    }

    @Override // com.google.android.gms.games.quest.Milestone
    public final int getState() {
        return getInteger("milestone_state");
    }

    @Override // com.google.android.gms.games.quest.Milestone
    public final long getTargetProgress() {
        return getLong("target_value");
    }

    @Override // com.google.android.gms.common.data.DataBufferRef
    public final int hashCode() {
        return MilestoneEntity.zza(this);
    }

    public final String toString() {
        return MilestoneEntity.zzb(this);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        ((MilestoneEntity) ((Milestone) freeze())).writeToParcel(parcel, i2);
    }
}
