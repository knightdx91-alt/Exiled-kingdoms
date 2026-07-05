package com.google.android.gms.drive;

import a.a;
import android.text.TextUtils;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.internal.drive.zzaw;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class ExecutionOptions {
    public static final int CONFLICT_STRATEGY_KEEP_REMOTE = 1;
    public static final int CONFLICT_STRATEGY_OVERWRITE_REMOTE = 0;
    public static final int MAX_TRACKING_TAG_STRING_LENGTH = 65536;
    private final String zzal;
    private final boolean zzam;
    private final int zzan;

    public static class Builder {
        protected String zzao;
        protected boolean zzap;
        protected int zzaq = 0;

        public ExecutionOptions build() {
            zzn();
            return new ExecutionOptions(this.zzao, this.zzap, this.zzaq);
        }

        public Builder setConflictStrategy(int i2) {
            if (i2 != 0 && i2 != 1) {
                throw new IllegalArgumentException(a.g(53, i2, "Unrecognized value for conflict strategy: "));
            }
            this.zzaq = i2;
            return this;
        }

        public Builder setNotifyOnCompletion(boolean z2) {
            this.zzap = z2;
            return this;
        }

        public Builder setTrackingTag(String str) {
            if (TextUtils.isEmpty(str) || str.length() > 65536) {
                throw new IllegalArgumentException("trackingTag must not be null nor empty, and the length must be <= the maximum length (65536)");
            }
            this.zzao = str;
            return this;
        }

        protected final void zzn() {
            if (this.zzaq == 1 && !this.zzap) {
                throw new IllegalStateException("Cannot use CONFLICT_STRATEGY_KEEP_REMOTE without requesting completion notifications");
            }
        }
    }

    public ExecutionOptions(String str, boolean z2, int i2) {
        this.zzal = str;
        this.zzam = z2;
        this.zzan = i2;
    }

    public boolean equals(Object obj) {
        if (obj != null && obj.getClass() == getClass()) {
            if (obj == this) {
                return true;
            }
            ExecutionOptions executionOptions = (ExecutionOptions) obj;
            if (Objects.equal(this.zzal, executionOptions.zzal) && this.zzan == executionOptions.zzan && this.zzam == executionOptions.zzam) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        return Objects.hashCode(this.zzal, Integer.valueOf(this.zzan), Boolean.valueOf(this.zzam));
    }

    @Deprecated
    public final void zza(GoogleApiClient googleApiClient) {
        zza((zzaw) googleApiClient.getClient(Drive.CLIENT_KEY));
    }

    public final String zzk() {
        return this.zzal;
    }

    public final boolean zzl() {
        return this.zzam;
    }

    public final int zzm() {
        return this.zzan;
    }

    public final void zza(zzaw zzawVar) {
        if (this.zzam && !zzawVar.zzag()) {
            throw new IllegalStateException("Application must define an exported DriveEventService subclass in AndroidManifest.xml to be notified on completion");
        }
    }

    public static boolean zza(int i2) {
        return i2 == 1;
    }
}
