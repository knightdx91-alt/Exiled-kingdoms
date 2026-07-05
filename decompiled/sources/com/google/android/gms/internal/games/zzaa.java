package com.google.android.gms.internal.games;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.event.EventBuffer;
import com.google.android.gms.games.event.Events;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzaa implements Events.LoadEventsResult {
    private final /* synthetic */ Status zzbc;

    zzaa(zzz zzzVar, Status status) {
        this.zzbc = status;
    }

    @Override // com.google.android.gms.games.event.Events.LoadEventsResult
    public final EventBuffer getEvents() {
        return new EventBuffer(DataHolder.empty(14));
    }

    @Override // com.google.android.gms.common.api.Result
    public final Status getStatus() {
        return this.zzbc;
    }

    @Override // com.google.android.gms.common.api.Releasable
    public final void release() {
    }
}
