package com.android.billingclient.api;

import android.app.Activity;
import com.badlogic.gdx.backends.android.AndroidApplication;

/* JADX INFO: compiled from: com.android.billingclient:billing@@6.0.1 */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public abstract class c {

    /* JADX INFO: compiled from: com.android.billingclient:billing@@6.0.1 */
    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private volatile x f1440a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final Activity f1441b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private volatile e0.f f1442c;

        /* synthetic */ a(Activity activity) {
            this.f1441b = activity;
        }

        public final c a() {
            if (this.f1442c == null) {
                throw new IllegalArgumentException("Please provide a valid listener for purchases updates.");
            }
            if (this.f1440a != null) {
                return this.f1442c != null ? new d(this.f1441b, this.f1442c) : new d(this.f1441b);
            }
            throw new IllegalArgumentException("Pending purchases for one-time products must be supported.");
        }

        public final void b() {
            z zVar = new z();
            zVar.a();
            this.f1440a = zVar.b();
        }

        public final void c(e0.f fVar) {
            this.f1442c = fVar;
        }
    }

    public static a d(Activity activity) {
        return new a(activity);
    }

    public abstract void a(com.android.billingclient.api.a aVar, b bVar);

    public abstract void b(h hVar, i iVar);

    public abstract g c(AndroidApplication androidApplication, f fVar);

    public abstract void e(p pVar, l lVar);

    public abstract void f(q qVar, n nVar);

    public abstract void g(e eVar);
}
