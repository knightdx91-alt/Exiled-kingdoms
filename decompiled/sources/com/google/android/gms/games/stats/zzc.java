package com.google.android.gms.games.stats;

import android.os.Bundle;
import android.os.Parcel;
import com.google.android.gms.common.data.DataBufferRef;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.Asserts;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class zzc extends DataBufferRef implements PlayerStats {
    private Bundle zzrl;

    zzc(DataHolder dataHolder, int i2) {
        super(dataHolder, i2);
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // com.google.android.gms.common.data.DataBufferRef
    public final boolean equals(Object obj) {
        return zza.zza(this, obj);
    }

    @Override // com.google.android.gms.common.data.Freezable
    public final /* synthetic */ PlayerStats freeze() {
        return new zza(this);
    }

    @Override // com.google.android.gms.games.stats.PlayerStats
    public final float getAverageSessionLength() {
        return getFloat("ave_session_length_minutes");
    }

    @Override // com.google.android.gms.games.stats.PlayerStats
    public final float getChurnProbability() {
        return getFloat("churn_probability");
    }

    @Override // com.google.android.gms.games.stats.PlayerStats
    public final int getDaysSinceLastPlayed() {
        return getInteger("days_since_last_played");
    }

    @Override // com.google.android.gms.games.stats.PlayerStats
    public final float getHighSpenderProbability() {
        if (hasColumn("high_spender_probability")) {
            return getFloat("high_spender_probability");
        }
        return -1.0f;
    }

    @Override // com.google.android.gms.games.stats.PlayerStats
    public final int getNumberOfPurchases() {
        return getInteger("num_purchases");
    }

    @Override // com.google.android.gms.games.stats.PlayerStats
    public final int getNumberOfSessions() {
        return getInteger("num_sessions");
    }

    @Override // com.google.android.gms.games.stats.PlayerStats
    public final float getSessionPercentile() {
        return getFloat("num_sessions_percentile");
    }

    @Override // com.google.android.gms.games.stats.PlayerStats
    public final float getSpendPercentile() {
        return getFloat("spend_percentile");
    }

    @Override // com.google.android.gms.games.stats.PlayerStats
    public final float getSpendProbability() {
        if (hasColumn("spend_probability")) {
            return getFloat("spend_probability");
        }
        return -1.0f;
    }

    @Override // com.google.android.gms.games.stats.PlayerStats
    public final float getTotalSpendNext28Days() {
        if (hasColumn("total_spend_next_28_days")) {
            return getFloat("total_spend_next_28_days");
        }
        return -1.0f;
    }

    @Override // com.google.android.gms.common.data.DataBufferRef
    public final int hashCode() {
        return zza.zza(this);
    }

    public final String toString() {
        return zza.zzb(this);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        ((zza) ((PlayerStats) freeze())).writeToParcel(parcel, i2);
    }

    @Override // com.google.android.gms.games.stats.PlayerStats
    public final Bundle zzcn() {
        Bundle bundle = this.zzrl;
        if (bundle != null) {
            return bundle;
        }
        this.zzrl = new Bundle();
        String string = getString("unknown_raw_keys");
        String string2 = getString("unknown_raw_values");
        if (string != null && string2 != null) {
            String[] strArrSplit = string.split(",");
            String[] strArrSplit2 = string2.split(",");
            Asserts.checkState(strArrSplit.length <= strArrSplit2.length, "Invalid raw arguments!");
            for (int i2 = 0; i2 < strArrSplit.length; i2++) {
                this.zzrl.putString(strArrSplit[i2], strArrSplit2[i2]);
            }
        }
        return this.zzrl;
    }
}
