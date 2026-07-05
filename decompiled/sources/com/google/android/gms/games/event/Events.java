package com.google.android.gms.games.event;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.util.VisibleForTesting;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@VisibleForTesting
@Deprecated
public interface Events {

    @Deprecated
    public interface LoadEventsResult extends Releasable, Result {
        EventBuffer getEvents();
    }

    void increment(GoogleApiClient googleApiClient, String str, int i2);

    PendingResult<LoadEventsResult> load(GoogleApiClient googleApiClient, boolean z2);

    PendingResult<LoadEventsResult> loadByIds(GoogleApiClient googleApiClient, boolean z2, String... strArr);
}
