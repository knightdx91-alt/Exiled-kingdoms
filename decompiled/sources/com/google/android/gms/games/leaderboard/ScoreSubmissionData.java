package com.google.android.gms.games.leaderboard;

import android.util.SparseArray;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.games.zzei;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class ScoreSubmissionData {
    private static final String[] zzmt = {"leaderboardId", "playerId", "timeSpan", "hasResult", "rawScore", "formattedScore", "newBest", "scoreTag"};
    private int statusCode;
    private String zzby;
    private String zzmv;
    private SparseArray<Result> zznz = new SparseArray<>();

    public static final class Result {
        public final String formattedScore;
        public final boolean newBest;
        public final long rawScore;
        public final String scoreTag;

        public Result(long j2, String str, String str2, boolean z2) {
            this.rawScore = j2;
            this.formattedScore = str;
            this.scoreTag = str2;
            this.newBest = z2;
        }

        public final String toString() {
            return Objects.toStringHelper(this).add("RawScore", Long.valueOf(this.rawScore)).add("FormattedScore", this.formattedScore).add("ScoreTag", this.scoreTag).add("NewBest", Boolean.valueOf(this.newBest)).toString();
        }
    }

    public ScoreSubmissionData(DataHolder dataHolder) {
        this.statusCode = dataHolder.getStatusCode();
        int count = dataHolder.getCount();
        Preconditions.checkArgument(count == 3);
        for (int i2 = 0; i2 < count; i2++) {
            int windowIndex = dataHolder.getWindowIndex(i2);
            if (i2 == 0) {
                this.zzmv = dataHolder.getString("leaderboardId", i2, windowIndex);
                this.zzby = dataHolder.getString("playerId", i2, windowIndex);
            }
            if (dataHolder.getBoolean("hasResult", i2, windowIndex)) {
                this.zznz.put(dataHolder.getInteger("timeSpan", i2, windowIndex), new Result(dataHolder.getLong("rawScore", i2, windowIndex), dataHolder.getString("formattedScore", i2, windowIndex), dataHolder.getString("scoreTag", i2, windowIndex), dataHolder.getBoolean("newBest", i2, windowIndex)));
            }
        }
    }

    public final String getLeaderboardId() {
        return this.zzmv;
    }

    public final String getPlayerId() {
        return this.zzby;
    }

    public final Result getScoreResult(int i2) {
        return this.zznz.get(i2);
    }

    public final String toString() {
        Objects.ToStringHelper toStringHelperAdd = Objects.toStringHelper(this).add("PlayerId", this.zzby).add("StatusCode", Integer.valueOf(this.statusCode));
        for (int i2 = 0; i2 < 3; i2++) {
            Result result = this.zznz.get(i2);
            toStringHelperAdd.add("TimesSpan", zzei.zzn(i2));
            toStringHelperAdd.add("Result", result == null ? "null" : result.toString());
        }
        return toStringHelperAdd.toString();
    }
}
