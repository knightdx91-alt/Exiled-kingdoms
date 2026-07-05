package android.support.v4.app;

import android.app.Notification;

/* JADX INFO: compiled from: NotificationCompat.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class d0 extends f0 {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private CharSequence f201b;

    public final void a(b0 b0Var) {
        new Notification.BigTextStyle(((g0) b0Var).b()).setBigContentTitle(null).bigText(this.f201b);
    }

    public final void b(String str) {
        this.f201b = e0.b(str);
    }
}
