package com.google.android.gms.games;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.PendingResultUtil;
import com.google.android.gms.games.event.EventBuffer;
import com.google.android.gms.games.event.Events;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzg implements PendingResultUtil.ResultConverter<Events.LoadEventsResult, EventBuffer> {
    zzg() {
    }

    @Override // com.google.android.gms.common.internal.PendingResultUtil.ResultConverter
    public final /* synthetic */ EventBuffer convert(Result result) {
        Events.LoadEventsResult loadEventsResult = (Events.LoadEventsResult) result;
        if (loadEventsResult == null) {
            return null;
        }
        return loadEventsResult.getEvents();
    }
}
