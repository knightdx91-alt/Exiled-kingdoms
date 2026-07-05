package com.google.android.gms.games;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.PendingResultUtil;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.snapshot.Snapshot;
import com.google.android.gms.games.snapshot.SnapshotContents;
import com.google.android.gms.games.snapshot.SnapshotMetadata;
import com.google.android.gms.games.snapshot.SnapshotMetadataBuffer;
import com.google.android.gms.games.snapshot.SnapshotMetadataChange;
import com.google.android.gms.games.snapshot.Snapshots;
import com.google.android.gms.tasks.Task;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class SnapshotsClient extends com.google.android.gms.internal.games.zzu {
    public static final int DISPLAY_LIMIT_NONE = -1;
    public static final String EXTRA_SNAPSHOT_METADATA = "com.google.android.gms.games.SNAPSHOT_METADATA";
    public static final String EXTRA_SNAPSHOT_NEW = "com.google.android.gms.games.SNAPSHOT_NEW";
    public static final int RESOLUTION_POLICY_HIGHEST_PROGRESS = 4;
    public static final int RESOLUTION_POLICY_LAST_KNOWN_GOOD = 2;
    public static final int RESOLUTION_POLICY_LONGEST_PLAYTIME = 1;
    public static final int RESOLUTION_POLICY_MANUAL = -1;
    public static final int RESOLUTION_POLICY_MOST_RECENTLY_MODIFIED = 3;
    private static final com.google.android.gms.games.internal.zzp<Snapshots.OpenSnapshotResult> zzdu = new zzby();
    private static final PendingResultUtil.ResultConverter<Snapshots.DeleteSnapshotResult, String> zzdv = new zzbz();
    private static final PendingResultUtil.ResultConverter<Snapshots.CommitSnapshotResult, SnapshotMetadata> zzdw = new zzca();
    private static final PendingResultUtil.ResultConverter<Snapshots.OpenSnapshotResult, Snapshots.OpenSnapshotResult> zzdx = new zzcb();
    private static final com.google.android.gms.games.internal.zzr zzdy = new zzcc();
    private static final PendingResultUtil.ResultConverter<Snapshots.OpenSnapshotResult, DataOrConflict<Snapshot>> zzdz = new zzbt();
    private static final PendingResultUtil.ResultConverter<Snapshots.LoadSnapshotsResult, SnapshotMetadataBuffer> zzea = new zzbu();

    public static class DataOrConflict<T> {
        private final T data;
        private final SnapshotConflict zzeg;

        DataOrConflict(T t2, SnapshotConflict snapshotConflict) {
            this.data = t2;
            this.zzeg = snapshotConflict;
        }

        public SnapshotConflict getConflict() {
            if (isConflict()) {
                return this.zzeg;
            }
            throw new IllegalStateException("getConflict called when there is no conflict.");
        }

        public T getData() {
            if (isConflict()) {
                throw new IllegalStateException("getData called when there is a conflict.");
            }
            return this.data;
        }

        public boolean isConflict() {
            return this.zzeg != null;
        }
    }

    public static class SnapshotConflict {
        private final Snapshot zzeh;
        private final String zzei;
        private final Snapshot zzej;
        private final SnapshotContents zzek;

        SnapshotConflict(Snapshot snapshot, String str, Snapshot snapshot2, SnapshotContents snapshotContents) {
            this.zzeh = snapshot;
            this.zzei = str;
            this.zzej = snapshot2;
            this.zzek = snapshotContents;
        }

        public String getConflictId() {
            return this.zzei;
        }

        public Snapshot getConflictingSnapshot() {
            return this.zzej;
        }

        public SnapshotContents getResolutionSnapshotContents() {
            return this.zzek;
        }

        public Snapshot getSnapshot() {
            return this.zzeh;
        }
    }

    public static class SnapshotContentUnavailableApiException extends ApiException {
        protected final SnapshotMetadata metadata;

        SnapshotContentUnavailableApiException(Status status, SnapshotMetadata snapshotMetadata) {
            super(status);
            this.metadata = snapshotMetadata;
        }

        public SnapshotMetadata getSnapshotMetadata() {
            return this.metadata;
        }
    }

    SnapshotsClient(Activity activity, Games.GamesOptions gamesOptions) {
        super(activity, gamesOptions);
    }

    public static SnapshotMetadata getSnapshotFromBundle(Bundle bundle) {
        return Games.Snapshots.getSnapshotFromBundle(bundle);
    }

    private static Task<DataOrConflict<Snapshot>> zzc(PendingResult<Snapshots.OpenSnapshotResult> pendingResult) {
        return com.google.android.gms.games.internal.zzi.zza(pendingResult, zzdy, zzdz, zzdx, zzdu);
    }

    public Task<SnapshotMetadata> commitAndClose(Snapshot snapshot, SnapshotMetadataChange snapshotMetadataChange) {
        return com.google.android.gms.games.internal.zzi.toTask(Games.Snapshots.commitAndClose(asGoogleApiClient(), snapshot, snapshotMetadataChange), zzdw);
    }

    public Task<String> delete(SnapshotMetadata snapshotMetadata) {
        return com.google.android.gms.games.internal.zzi.toTask(Games.Snapshots.delete(asGoogleApiClient(), snapshotMetadata), zzdv);
    }

    public Task<Void> discardAndClose(Snapshot snapshot) {
        return doWrite(new zzbx(this, snapshot));
    }

    public Task<Integer> getMaxCoverImageSize() {
        return doRead(new zzbv(this));
    }

    public Task<Integer> getMaxDataSize() {
        return doRead(new zzbs(this));
    }

    public Task<Intent> getSelectSnapshotIntent(String str, boolean z2, boolean z3, int i2) {
        return doRead(new zzbw(this, str, z2, z3, i2));
    }

    public Task<AnnotatedData<SnapshotMetadataBuffer>> load(boolean z2) {
        return com.google.android.gms.games.internal.zzi.zzb(Games.Snapshots.load(asGoogleApiClient(), z2), zzea);
    }

    public Task<DataOrConflict<Snapshot>> open(SnapshotMetadata snapshotMetadata) {
        return zzc(Games.Snapshots.open(asGoogleApiClient(), snapshotMetadata));
    }

    public Task<DataOrConflict<Snapshot>> resolveConflict(String str, Snapshot snapshot) {
        return zzc(Games.Snapshots.resolveConflict(asGoogleApiClient(), str, snapshot));
    }

    SnapshotsClient(Context context, Games.GamesOptions gamesOptions) {
        super(context, gamesOptions);
    }

    public Task<DataOrConflict<Snapshot>> open(SnapshotMetadata snapshotMetadata, int i2) {
        return zzc(Games.Snapshots.open(asGoogleApiClient(), snapshotMetadata, i2));
    }

    public Task<DataOrConflict<Snapshot>> resolveConflict(String str, String str2, SnapshotMetadataChange snapshotMetadataChange, SnapshotContents snapshotContents) {
        return zzc(Games.Snapshots.resolveConflict(asGoogleApiClient(), str, str2, snapshotMetadataChange, snapshotContents));
    }

    public Task<DataOrConflict<Snapshot>> open(String str, boolean z2) {
        return zzc(Games.Snapshots.open(asGoogleApiClient(), str, z2));
    }

    public Task<DataOrConflict<Snapshot>> open(String str, boolean z2, int i2) {
        return zzc(Games.Snapshots.open(asGoogleApiClient(), str, z2, i2));
    }
}
