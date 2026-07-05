package com.google.android.gms.games.snapshot;

import android.graphics.Bitmap;
import android.net.Uri;
import com.google.android.gms.common.data.BitmapTeleporter;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public interface SnapshotMetadataChange {
    public static final SnapshotMetadataChange EMPTY_CHANGE = new zze();

    public static final class Builder {
        private String description;
        private Long zzqp;
        private Long zzqq;
        private BitmapTeleporter zzqr;
        private Uri zzqs;

        public final SnapshotMetadataChange build() {
            return new zze(this.description, this.zzqp, this.zzqr, this.zzqs, this.zzqq);
        }

        public final Builder fromMetadata(SnapshotMetadata snapshotMetadata) {
            this.description = snapshotMetadata.getDescription();
            this.zzqp = Long.valueOf(snapshotMetadata.getPlayedTime());
            this.zzqq = Long.valueOf(snapshotMetadata.getProgressValue());
            if (this.zzqp.longValue() == -1) {
                this.zzqp = null;
            }
            Uri coverImageUri = snapshotMetadata.getCoverImageUri();
            this.zzqs = coverImageUri;
            if (coverImageUri != null) {
                this.zzqr = null;
            }
            return this;
        }

        public final Builder setCoverImage(Bitmap bitmap) {
            this.zzqr = new BitmapTeleporter(bitmap);
            this.zzqs = null;
            return this;
        }

        public final Builder setDescription(String str) {
            this.description = str;
            return this;
        }

        public final Builder setPlayedTimeMillis(long j2) {
            this.zzqp = Long.valueOf(j2);
            return this;
        }

        public final Builder setProgressValue(long j2) {
            this.zzqq = Long.valueOf(j2);
            return this;
        }
    }

    Bitmap getCoverImage();

    String getDescription();

    Long getPlayedTimeMillis();

    Long getProgressValue();

    BitmapTeleporter zzcm();
}
