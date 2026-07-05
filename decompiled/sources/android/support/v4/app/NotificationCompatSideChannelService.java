package android.support.v4.app;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public abstract class NotificationCompatSideChannelService extends Service {
    @Override // android.app.Service
    public final IBinder onBind(Intent intent) {
        intent.getAction().equals("android.support.BIND_NOTIFICATION_SIDE_CHANNEL");
        return null;
    }
}
