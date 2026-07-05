package com.google.android.gms.games;

import android.app.Activity;
import android.content.Context;
import com.google.android.gms.common.internal.PendingResultUtil;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.event.EventBuffer;
import com.google.android.gms.games.event.Events;
import com.google.android.gms.tasks.Task;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class EventsClient extends com.google.android.gms.internal.games.zzu {
    private static final PendingResultUtil.ResultConverter<Events.LoadEventsResult, EventBuffer> zzj = new zzg();

    EventsClient(Activity activity, Games.GamesOptions gamesOptions) {
        super(activity, gamesOptions);
    }

    public void increment(String str, int i2) {
        doWrite(new zzf(this, str, i2));
    }

    public Task<AnnotatedData<EventBuffer>> load(boolean z2) {
        return com.google.android.gms.games.internal.zzi.zzb(Games.Events.load(asGoogleApiClient(), z2), zzj);
    }

    public Task<AnnotatedData<EventBuffer>> loadByIds(boolean z2, String... strArr) {
        return com.google.android.gms.games.internal.zzi.zzb(Games.Events.loadByIds(asGoogleApiClient(), z2, strArr), zzj);
    }

    EventsClient(Context context, Games.GamesOptions gamesOptions) {
        super(context, gamesOptions);
    }
}
