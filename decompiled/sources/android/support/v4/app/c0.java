package android.support.v4.app;

import android.app.PendingIntent;
import android.os.Bundle;

/* JADX INFO: compiled from: NotificationCompat.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class c0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final Bundle f196a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private boolean f197b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f198c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public CharSequence f199d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public PendingIntent f200e;

    public c0(int i2, String str, PendingIntent pendingIntent) {
        Bundle bundle = new Bundle();
        this.f198c = i2;
        this.f199d = e0.b(str);
        this.f200e = pendingIntent;
        this.f196a = bundle;
        this.f197b = true;
    }

    public final boolean a() {
        return this.f197b;
    }
}
