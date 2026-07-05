package android.support.v7.app;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.f;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Window;
import com.badlogic.gdx.graphics.GL20;

/* JADX INFO: compiled from: AppCompatDelegateImplV14.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
class i extends h {
    private int O;
    private boolean P;
    private boolean Q;
    private b R;

    /* JADX INFO: compiled from: AppCompatDelegateImplV14.java */
    class a extends f.a {
        a(Window.Callback callback) {
            super(callback);
        }
    }

    /* JADX INFO: compiled from: AppCompatDelegateImplV14.java */
    final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private r f773a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private boolean f774b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private BroadcastReceiver f775c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private IntentFilter f776d;

        /* JADX INFO: compiled from: AppCompatDelegateImplV14.java */
        final class a extends BroadcastReceiver {
            a() {
            }

            @Override // android.content.BroadcastReceiver
            public final void onReceive(Context context, Intent intent) {
                b.this.b();
            }
        }

        b(r rVar) {
            this.f773a = rVar;
            this.f774b = rVar.b();
        }

        final void a() {
            BroadcastReceiver broadcastReceiver = this.f775c;
            if (broadcastReceiver != null) {
                i.this.f757b.unregisterReceiver(broadcastReceiver);
                this.f775c = null;
            }
        }

        final void b() {
            boolean zB = this.f773a.b();
            if (zB != this.f774b) {
                this.f774b = zB;
                i.this.d();
            }
        }

        final int c() {
            boolean zB = this.f773a.b();
            this.f774b = zB;
            return zB ? 2 : 1;
        }

        final void d() {
            a();
            if (this.f775c == null) {
                this.f775c = new a();
            }
            if (this.f776d == null) {
                IntentFilter intentFilter = new IntentFilter();
                this.f776d = intentFilter;
                intentFilter.addAction("android.intent.action.TIME_SET");
                this.f776d.addAction("android.intent.action.TIMEZONE_CHANGED");
                this.f776d.addAction("android.intent.action.TIME_TICK");
            }
            i.this.f757b.registerReceiver(this.f775c, this.f776d);
        }
    }

    i(Context context, Window window, d dVar) {
        super(context, window, dVar);
        this.O = -100;
        this.Q = true;
    }

    public final boolean Q() {
        return this.Q;
    }

    int R(int i2) {
        if (i2 == -100) {
            return -1;
        }
        if (i2 != 0) {
            return i2;
        }
        if (this.R == null) {
            this.R = new b(r.a(this.f757b));
        }
        return this.R.c();
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x0058  */
    @Override // android.support.v7.app.e
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final boolean d() {
        int i2 = this.O;
        if (i2 == -100) {
            i2 = -1;
        }
        int iR = R(i2);
        Context context = this.f757b;
        boolean z2 = false;
        if (iR != -1) {
            Resources resources = context.getResources();
            Configuration configuration = resources.getConfiguration();
            int i3 = configuration.uiMode & 48;
            int i4 = iR == 2 ? 32 : 16;
            if (i3 != i4) {
                if (this.P && (context instanceof Activity)) {
                    try {
                    } catch (PackageManager.NameNotFoundException e2) {
                        Log.d("AppCompatDelegate", "Exception while getting ActivityInfo", e2);
                    }
                    if ((context.getPackageManager().getActivityInfo(new ComponentName(context, context.getClass()), 0).configChanges & GL20.GL_NEVER) == 0) {
                        ((Activity) context).recreate();
                    }
                    z2 = true;
                } else {
                    Configuration configuration2 = new Configuration(configuration);
                    DisplayMetrics displayMetrics = resources.getDisplayMetrics();
                    configuration2.uiMode = i4 | (configuration2.uiMode & (-49));
                    resources.updateConfiguration(configuration2, displayMetrics);
                    z2 = true;
                }
            }
        }
        if (i2 == 0) {
            if (this.R == null) {
                this.R = new b(r.a(context));
            }
            this.R.d();
        }
        this.P = true;
        return z2;
    }

    @Override // android.support.v7.app.AppCompatDelegateImplV9, android.support.v7.app.e
    public final void i(Bundle bundle) {
        super.i(bundle);
        if (bundle == null || this.O != -100) {
            return;
        }
        this.O = bundle.getInt("appcompat:local_night_mode", -100);
    }

    @Override // android.support.v7.app.AppCompatDelegateImplV9, android.support.v7.app.f, android.support.v7.app.e
    public final void j() {
        super.j();
        b bVar = this.R;
        if (bVar != null) {
            bVar.a();
        }
    }

    @Override // android.support.v7.app.e
    public final void l(Bundle bundle) {
        int i2 = this.O;
        if (i2 != -100) {
            bundle.putInt("appcompat:local_night_mode", i2);
        }
    }

    @Override // android.support.v7.app.AppCompatDelegateImplV9, android.support.v7.app.e
    public final void n() {
        super.n();
        b bVar = this.R;
        if (bVar != null) {
            bVar.a();
        }
    }
}
