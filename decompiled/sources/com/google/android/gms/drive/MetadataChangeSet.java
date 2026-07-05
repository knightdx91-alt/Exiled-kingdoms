package com.google.android.gms.drive;

import android.graphics.Bitmap;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.data.BitmapTeleporter;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.drive.metadata.CustomPropertyKey;
import com.google.android.gms.drive.metadata.MetadataField;
import com.google.android.gms.drive.metadata.internal.AppVisibleCustomProperties;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;
import com.google.android.gms.internal.drive.zzhp;
import com.google.android.gms.internal.drive.zzic;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class MetadataChangeSet {
    public static final int CUSTOM_PROPERTY_SIZE_LIMIT_BYTES = 124;
    public static final int INDEXABLE_TEXT_SIZE_LIMIT_BYTES = 131072;
    public static final int MAX_PRIVATE_PROPERTIES_PER_RESOURCE_PER_APP = 30;
    public static final int MAX_PUBLIC_PROPERTIES_PER_RESOURCE = 30;
    public static final int MAX_TOTAL_PROPERTIES_PER_RESOURCE = 100;
    public static final MetadataChangeSet zzav = new MetadataChangeSet(MetadataBundle.zzaw());
    private final MetadataBundle zzaw;

    public static class Builder {
        private final MetadataBundle zzaw = MetadataBundle.zzaw();
        private AppVisibleCustomProperties.zza zzax;

        private static void zza(String str, int i2, int i3) {
            boolean z2 = i3 <= i2;
            Locale locale = Locale.US;
            Preconditions.checkArgument(z2, str + " must be no more than " + i2 + " bytes, but is " + i3 + " bytes.");
        }

        private static int zzb(String str) {
            if (str == null) {
                return 0;
            }
            return str.getBytes().length;
        }

        private final AppVisibleCustomProperties.zza zzq() {
            if (this.zzax == null) {
                this.zzax = new AppVisibleCustomProperties.zza();
            }
            return this.zzax;
        }

        public MetadataChangeSet build() {
            AppVisibleCustomProperties.zza zzaVar = this.zzax;
            if (zzaVar != null) {
                this.zzaw.zzb(zzhp.zzix, zzaVar.zzat());
            }
            return new MetadataChangeSet(this.zzaw);
        }

        public Builder deleteCustomProperty(CustomPropertyKey customPropertyKey) {
            Preconditions.checkNotNull(customPropertyKey, "key");
            zzq().zza(customPropertyKey, null);
            return this;
        }

        public Builder setCustomProperty(CustomPropertyKey customPropertyKey, String str) {
            Preconditions.checkNotNull(customPropertyKey, "key");
            Preconditions.checkNotNull(str, "value");
            zza("The total size of key string and value string of a custom property", MetadataChangeSet.CUSTOM_PROPERTY_SIZE_LIMIT_BYTES, zzb(customPropertyKey.getKey()) + zzb(str));
            zzq().zza(customPropertyKey, str);
            return this;
        }

        public Builder setDescription(String str) {
            this.zzaw.zzb(zzhp.zziy, str);
            return this;
        }

        public Builder setIndexableText(String str) {
            zza("Indexable text size", MetadataChangeSet.INDEXABLE_TEXT_SIZE_LIMIT_BYTES, zzb(str));
            this.zzaw.zzb(zzhp.zzje, str);
            return this;
        }

        public Builder setLastViewedByMeDate(Date date) {
            this.zzaw.zzb(zzic.zzko, date);
            return this;
        }

        public Builder setMimeType(String str) {
            Preconditions.checkNotNull(str);
            this.zzaw.zzb(zzhp.zzjs, str);
            return this;
        }

        public Builder setPinned(boolean z2) {
            this.zzaw.zzb(zzhp.zzjk, Boolean.valueOf(z2));
            return this;
        }

        public Builder setStarred(boolean z2) {
            this.zzaw.zzb(zzhp.zzjz, Boolean.valueOf(z2));
            return this;
        }

        public Builder setTitle(String str) {
            Preconditions.checkNotNull(str, "Title cannot be null.");
            this.zzaw.zzb(zzhp.zzkb, str);
            return this;
        }

        public Builder setViewed() {
            this.zzaw.zzb(zzhp.zzjr, Boolean.TRUE);
            return this;
        }

        @Deprecated
        public Builder setViewed(boolean z2) {
            if (z2) {
                this.zzaw.zzb(zzhp.zzjr, Boolean.TRUE);
            } else {
                MetadataBundle metadataBundle = this.zzaw;
                MetadataField<Boolean> metadataField = zzhp.zzjr;
                if (metadataBundle.zzd(metadataField)) {
                    this.zzaw.zzc(metadataField);
                }
            }
            return this;
        }
    }

    public MetadataChangeSet(MetadataBundle metadataBundle) {
        this.zzaw = metadataBundle.zzax();
    }

    public final Map<CustomPropertyKey, String> getCustomPropertyChangeMap() {
        AppVisibleCustomProperties appVisibleCustomProperties = (AppVisibleCustomProperties) this.zzaw.zza(zzhp.zzix);
        return appVisibleCustomProperties == null ? Collections.emptyMap() : appVisibleCustomProperties.zzas();
    }

    public final String getDescription() {
        return (String) this.zzaw.zza(zzhp.zziy);
    }

    public final String getIndexableText() {
        return (String) this.zzaw.zza(zzhp.zzje);
    }

    public final Date getLastViewedByMeDate() {
        return (Date) this.zzaw.zza(zzic.zzko);
    }

    public final String getMimeType() {
        return (String) this.zzaw.zza(zzhp.zzjs);
    }

    @KeepForSdk
    public final Bitmap getThumbnail() {
        BitmapTeleporter bitmapTeleporter = (BitmapTeleporter) this.zzaw.zza(zzhp.zzka);
        if (bitmapTeleporter == null) {
            return null;
        }
        return bitmapTeleporter.get();
    }

    public final String getTitle() {
        return (String) this.zzaw.zza(zzhp.zzkb);
    }

    public final Boolean isPinned() {
        return (Boolean) this.zzaw.zza(zzhp.zzjk);
    }

    public final Boolean isStarred() {
        return (Boolean) this.zzaw.zza(zzhp.zzjz);
    }

    public final Boolean isViewed() {
        return (Boolean) this.zzaw.zza(zzhp.zzjr);
    }

    public final MetadataBundle zzp() {
        return this.zzaw;
    }
}
