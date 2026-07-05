package com.google.android.gms.drive;

import com.google.android.gms.common.internal.Objects;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class TransferPreferencesBuilder {
    public static final TransferPreferences DEFAULT_PREFERENCES = new zza(1, true, 256);
    private int zzbj;
    private boolean zzbk;
    private int zzbl;

    static class zza implements TransferPreferences {
        private final int zzbj;
        private final boolean zzbk;
        private final int zzbl;

        zza(int i2, boolean z2, int i3) {
            this.zzbj = i2;
            this.zzbk = z2;
            this.zzbl = i3;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj != null && zza.class == obj.getClass()) {
                zza zzaVar = (zza) obj;
                if (zzaVar.zzbj == this.zzbj && zzaVar.zzbk == this.zzbk && zzaVar.zzbl == this.zzbl) {
                    return true;
                }
            }
            return false;
        }

        @Override // com.google.android.gms.drive.TransferPreferences
        public final int getBatteryUsagePreference() {
            return this.zzbl;
        }

        @Override // com.google.android.gms.drive.TransferPreferences
        public final int getNetworkPreference() {
            return this.zzbj;
        }

        public final int hashCode() {
            return Objects.hashCode(Integer.valueOf(this.zzbj), Boolean.valueOf(this.zzbk), Integer.valueOf(this.zzbl));
        }

        @Override // com.google.android.gms.drive.TransferPreferences
        public final boolean isRoamingAllowed() {
            return this.zzbk;
        }

        public final String toString() {
            return "NetworkPreference: " + this.zzbj + ", IsRoamingAllowed " + this.zzbk + ", BatteryUsagePreference " + this.zzbl;
        }
    }

    public TransferPreferencesBuilder() {
        this(DEFAULT_PREFERENCES);
    }

    public TransferPreferences build() {
        return new zza(this.zzbj, this.zzbk, this.zzbl);
    }

    public TransferPreferencesBuilder setBatteryUsagePreference(int i2) {
        this.zzbl = i2;
        return this;
    }

    public TransferPreferencesBuilder setIsRoamingAllowed(boolean z2) {
        this.zzbk = z2;
        return this;
    }

    public TransferPreferencesBuilder setNetworkPreference(int i2) {
        this.zzbj = i2;
        return this;
    }

    public TransferPreferencesBuilder(FileUploadPreferences fileUploadPreferences) {
        this.zzbj = fileUploadPreferences.getNetworkTypePreference();
        this.zzbk = fileUploadPreferences.isRoamingAllowed();
        this.zzbl = fileUploadPreferences.getBatteryUsagePreference();
    }

    public TransferPreferencesBuilder(TransferPreferences transferPreferences) {
        this.zzbj = transferPreferences.getNetworkPreference();
        this.zzbk = transferPreferences.isRoamingAllowed();
        this.zzbl = transferPreferences.getBatteryUsagePreference();
    }
}
