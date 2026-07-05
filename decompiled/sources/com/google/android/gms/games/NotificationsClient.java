package com.google.android.gms.games;

import android.app.Activity;
import android.content.Context;
import com.google.android.gms.games.Games;
import com.google.android.gms.tasks.Task;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class NotificationsClient extends com.google.android.gms.internal.games.zzu {
    public static final int NOTIFICATION_TYPES_ALL = 19;
    public static final int NOTIFICATION_TYPES_MULTIPLAYER = 3;
    public static final int NOTIFICATION_TYPE_INVITATION = 1;
    public static final int NOTIFICATION_TYPE_LEVEL_UP = 16;
    public static final int NOTIFICATION_TYPE_MATCH_UPDATE = 2;

    NotificationsClient(Activity activity, Games.GamesOptions gamesOptions) {
        super(activity, gamesOptions);
    }

    public Task<Void> clear(int i2) {
        return doWrite(new zzao(this, i2));
    }

    public Task<Void> clearAll() {
        return clear(19);
    }

    NotificationsClient(Context context, Games.GamesOptions gamesOptions) {
        super(context, gamesOptions);
    }
}
