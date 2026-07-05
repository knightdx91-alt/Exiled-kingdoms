package android.support.v4.app;

import android.app.Notification;
import android.graphics.Bitmap;
import android.os.Bundle;
import com.badlogic.gdx.graphics.VertexAttributes;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: compiled from: NotificationCompatBuilder.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class g0 implements b0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Notification.Builder f216a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final e0 f217b;

    g0(e0 e0Var) {
        new ArrayList();
        new Bundle();
        this.f217b = e0Var;
        Notification.Builder builder = new Notification.Builder(e0Var.f202a, e0Var.f211j);
        this.f216a = builder;
        Notification notification = e0Var.f212k;
        builder.setWhen(notification.when).setSmallIcon(notification.icon, notification.iconLevel).setContent(notification.contentView).setTicker(notification.tickerText, null).setSound(notification.sound, notification.audioStreamType).setVibrate(notification.vibrate).setLights(notification.ledARGB, notification.ledOnMS, notification.ledOffMS).setOngoing((notification.flags & 2) != 0).setOnlyAlertOnce((notification.flags & 8) != 0).setAutoCancel((notification.flags & 16) != 0).setDefaults(notification.defaults).setContentTitle(e0Var.f204c).setContentText(e0Var.f205d).setContentInfo(null).setContentIntent(e0Var.f206e).setDeleteIntent(notification.deleteIntent).setFullScreenIntent(null, (notification.flags & VertexAttributes.Usage.Tangent) != 0).setLargeIcon((Bitmap) null).setNumber(0).setProgress(0, 0, false);
        builder.setSubText(null).setUsesChronometer(false).setPriority(e0Var.f207f);
        for (c0 c0Var : e0Var.f203b) {
            Notification.Action.Builder builder2 = new Notification.Action.Builder(c0Var.f198c, c0Var.f199d, c0Var.f200e);
            Bundle bundle = c0Var.f196a;
            Bundle bundle2 = bundle != null ? new Bundle(bundle) : new Bundle();
            bundle2.putBoolean("android.support.allowGeneratedReplies", c0Var.a());
            builder2.setAllowGeneratedReplies(c0Var.a());
            builder2.addExtras(bundle2);
            this.f216a.addAction(builder2.build());
        }
        this.f216a.setShowWhen(e0Var.f208g);
        this.f216a.setLocalOnly(e0Var.f210i).setGroup(null).setGroupSummary(false).setSortKey(null);
        this.f216a.setCategory(null).setColor(0).setVisibility(0).setPublicVersion(null);
        Iterator<String> it = e0Var.f213l.iterator();
        while (it.hasNext()) {
            this.f216a.addPerson(it.next());
        }
        this.f216a.setExtras(null).setRemoteInputHistory(null);
        this.f216a.setBadgeIconType(0).setShortcutId(null).setTimeoutAfter(0L).setGroupAlertBehavior(0);
    }

    public final Notification a() {
        e0 e0Var = this.f217b;
        d0 d0Var = e0Var.f209h;
        if (d0Var != null) {
            d0Var.a(this);
        }
        Notification notificationBuild = this.f216a.build();
        if (d0Var != null) {
            e0Var.f209h.getClass();
        }
        if (d0Var != null) {
            Bundle bundle = notificationBuild.extras;
        }
        return notificationBuild;
    }

    public final Notification.Builder b() {
        return this.f216a;
    }
}
