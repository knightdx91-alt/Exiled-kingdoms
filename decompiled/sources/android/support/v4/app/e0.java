package android.support.v4.app;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import com.badlogic.gdx.graphics.GL20;
import java.util.ArrayList;

/* JADX INFO: compiled from: NotificationCompat.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class e0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Context f202a;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    CharSequence f204c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    CharSequence f205d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    PendingIntent f206e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    int f207f;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    d0 f209h;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    String f211j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    Notification f212k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    @Deprecated
    public ArrayList<String> f213l;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public ArrayList<c0> f203b = new ArrayList<>();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    boolean f208g = true;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    boolean f210i = false;

    @Deprecated
    public e0(Context context) {
        Notification notification = new Notification();
        this.f212k = notification;
        this.f202a = context;
        this.f211j = null;
        notification.when = System.currentTimeMillis();
        notification.audioStreamType = -1;
        this.f207f = 0;
        this.f213l = new ArrayList<>();
    }

    protected static CharSequence b(String str) {
        return (str != null && str.length() > 5120) ? str.subSequence(0, GL20.GL_BYTE) : str;
    }

    public final Notification a() {
        return new g0(this).a();
    }

    public final void c() {
        this.f212k.flags |= 16;
    }

    public final void d(String str) {
        this.f211j = str;
    }

    public final void e(PendingIntent pendingIntent) {
        this.f206e = pendingIntent;
    }

    public final void f(String str) {
        this.f205d = b(str);
    }

    public final void g(String str) {
        this.f204c = b(str);
    }

    public final void h() {
        this.f210i = true;
    }

    public final void i() {
        this.f207f = 2;
    }

    public final void j(int i2) {
        this.f212k.icon = i2;
    }

    public final void k(d0 d0Var) {
        if (this.f209h != d0Var) {
            this.f209h = d0Var;
            if (d0Var.f214a != this) {
                d0Var.f214a = this;
                k(d0Var);
            }
        }
    }

    public final void l(String str) {
        this.f212k.tickerText = b(str);
    }

    public final void m(long j2) {
        this.f212k.when = j2;
    }
}
