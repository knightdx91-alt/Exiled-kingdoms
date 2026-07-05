package android.support.v4.app;

import android.support.v4.app.y;
import android.support.v4.content.b;
import android.util.Log;
import com.badlogic.gdx.graphics.VertexAttributes;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.reflect.Modifier;

/* JADX INFO: compiled from: LoaderManager.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class z extends y {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final android.support.v4.util.j<a> f337a = new android.support.v4.util.j<>();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final android.support.v4.util.j<a> f338b = new android.support.v4.util.j<>();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final String f339c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    boolean f340d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    boolean f341e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    boolean f342f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    h f343g;

    /* JADX INFO: compiled from: LoaderManager.java */
    final class a implements b.InterfaceC0004b<Object>, b.a<Object> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        y.a<Object> f344a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        android.support.v4.content.b<Object> f345b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        boolean f346c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        boolean f347d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        Object f348e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        boolean f349f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        boolean f350g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        boolean f351h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        boolean f352i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        boolean f353j;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        boolean f354k;

        public a(y.a aVar) {
            this.f344a = aVar;
        }

        @Override // android.support.v4.content.b.InterfaceC0004b
        public final void a(android.support.v4.content.b<Object> bVar, Object obj) {
            if (this.f353j) {
                return;
            }
            z zVar = z.this;
            if (zVar.f337a.d(0) != this) {
                return;
            }
            if (this.f348e != obj || !this.f346c) {
                this.f348e = obj;
                this.f346c = true;
                if (this.f349f) {
                    c(bVar, obj);
                }
            }
            android.support.v4.util.j<a> jVar = zVar.f338b;
            a aVarD = jVar.d(0);
            if (aVarD != null && aVarD != this) {
                aVarD.f347d = false;
                aVarD.d();
                jVar.h(0);
            }
            if (zVar.f343g == null || zVar.h()) {
                return;
            }
            zVar.f343g.f221d.t0();
        }

        @Override // android.support.v4.content.b.a
        public final void b() {
            if (this.f353j) {
                return;
            }
            z.this.f337a.d(0);
        }

        final void c(android.support.v4.content.b<Object> bVar, Object obj) {
            String str;
            y.a<Object> aVar = this.f344a;
            if (aVar != null) {
                z zVar = z.this;
                h hVar = zVar.f343g;
                if (hVar != null) {
                    j jVar = hVar.f221d;
                    str = jVar.f247t;
                    jVar.f247t = "onLoadFinished";
                } else {
                    str = null;
                }
                try {
                    aVar.onLoadFinished(bVar, obj);
                    this.f347d = true;
                } finally {
                    h hVar2 = zVar.f343g;
                    if (hVar2 != null) {
                        hVar2.f221d.f247t = str;
                    }
                }
            }
        }

        final void d() {
            android.support.v4.content.b<Object> bVar;
            String str;
            this.f353j = true;
            boolean z2 = this.f347d;
            this.f347d = false;
            y.a<Object> aVar = this.f344a;
            if (aVar != null && (bVar = this.f345b) != null && this.f346c && z2) {
                z zVar = z.this;
                h hVar = zVar.f343g;
                if (hVar != null) {
                    j jVar = hVar.f221d;
                    str = jVar.f247t;
                    jVar.f247t = "onLoaderReset";
                } else {
                    str = null;
                }
                try {
                    aVar.onLoaderReset(bVar);
                } finally {
                    h hVar2 = zVar.f343g;
                    if (hVar2 != null) {
                        hVar2.f221d.f247t = str;
                    }
                }
            }
            this.f344a = null;
            this.f348e = null;
            this.f346c = false;
            android.support.v4.content.b<Object> bVar2 = this.f345b;
            if (bVar2 != null) {
                if (this.f354k) {
                    this.f354k = false;
                    bVar2.unregisterListener(this);
                    this.f345b.unregisterOnLoadCanceledListener(this);
                }
                this.f345b.reset();
            }
        }

        public final void e(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
            printWriter.print(str);
            printWriter.print("mId=");
            printWriter.print(0);
            printWriter.print(" mArgs=");
            printWriter.println((Object) null);
            printWriter.print(str);
            printWriter.print("mCallbacks=");
            printWriter.println(this.f344a);
            printWriter.print(str);
            printWriter.print("mLoader=");
            printWriter.println(this.f345b);
            android.support.v4.content.b<Object> bVar = this.f345b;
            if (bVar != null) {
                bVar.dump(a.a.k(str, "  "), fileDescriptor, printWriter, strArr);
            }
            if (this.f346c || this.f347d) {
                printWriter.print(str);
                printWriter.print("mHaveData=");
                printWriter.print(this.f346c);
                printWriter.print("  mDeliveredData=");
                printWriter.println(this.f347d);
                printWriter.print(str);
                printWriter.print("mData=");
                printWriter.println(this.f348e);
            }
            printWriter.print(str);
            printWriter.print("mStarted=");
            printWriter.print(this.f349f);
            printWriter.print(" mReportNextStart=");
            printWriter.print(this.f352i);
            printWriter.print(" mDestroyed=");
            printWriter.println(this.f353j);
            printWriter.print(str);
            printWriter.print("mRetaining=");
            printWriter.print(this.f350g);
            printWriter.print(" mRetainingStarted=");
            printWriter.print(this.f351h);
            printWriter.print(" mListenerRegistered=");
            printWriter.println(this.f354k);
        }

        final void f() {
            y.a<Object> aVar;
            if (this.f350g && this.f351h) {
                this.f349f = true;
                return;
            }
            if (this.f349f) {
                return;
            }
            this.f349f = true;
            if (this.f345b == null && (aVar = this.f344a) != null) {
                this.f345b = aVar.onCreateLoader(0, null);
            }
            android.support.v4.content.b<Object> bVar = this.f345b;
            if (bVar != null) {
                if (bVar.getClass().isMemberClass() && !Modifier.isStatic(this.f345b.getClass().getModifiers())) {
                    throw new IllegalArgumentException("Object returned from onCreateLoader must not be a non-static inner member class: " + this.f345b);
                }
                if (!this.f354k) {
                    this.f345b.registerListener(0, this);
                    this.f345b.registerOnLoadCanceledListener(this);
                    this.f354k = true;
                }
                this.f345b.startLoading();
            }
        }

        final void g() {
            android.support.v4.content.b<Object> bVar;
            this.f349f = false;
            if (this.f350g || (bVar = this.f345b) == null || !this.f354k) {
                return;
            }
            this.f354k = false;
            bVar.unregisterListener(this);
            this.f345b.unregisterOnLoadCanceledListener(this);
            this.f345b.stopLoading();
        }

        public final String toString() {
            StringBuilder sb = new StringBuilder(64);
            sb.append("LoaderInfo{");
            sb.append(Integer.toHexString(System.identityHashCode(this)));
            sb.append(" #0 : ");
            android.support.v4.util.k.c(this.f345b, sb);
            sb.append("}}");
            return sb.toString();
        }
    }

    z(String str, h hVar, boolean z2) {
        this.f339c = str;
        this.f343g = hVar;
        this.f340d = z2;
    }

    @Override // android.support.v4.app.y
    public final android.support.v4.content.b a(y.a aVar) {
        if (this.f342f) {
            throw new IllegalStateException("Called while creating a loader");
        }
        android.support.v4.util.j<a> jVar = this.f337a;
        a aVarD = jVar.d(0);
        if (aVarD == null) {
            try {
                this.f342f = true;
                aVarD = new a(aVar);
                aVarD.f345b = aVar.onCreateLoader(0, null);
                jVar.g(0, aVarD);
                if (this.f340d) {
                    aVarD.f();
                }
            } finally {
                this.f342f = false;
            }
        } else {
            aVarD.f344a = aVar;
        }
        if (aVarD.f346c && this.f340d) {
            aVarD.c(aVarD.f345b, aVarD.f348e);
        }
        return aVarD.f345b;
    }

    final void b() {
        if (!this.f341e) {
            android.support.v4.util.j<a> jVar = this.f337a;
            for (int i2 = jVar.i() - 1; i2 >= 0; i2--) {
                jVar.j(i2).d();
            }
            jVar.b();
        }
        android.support.v4.util.j<a> jVar2 = this.f338b;
        for (int i3 = jVar2.i() - 1; i3 >= 0; i3--) {
            jVar2.j(i3).d();
        }
        jVar2.b();
        this.f343g = null;
    }

    final void c() {
        android.support.v4.util.j<a> jVar = this.f337a;
        for (int i2 = jVar.i() - 1; i2 >= 0; i2--) {
            a aVarJ = jVar.j(i2);
            if (aVarJ.f349f && aVarJ.f352i) {
                aVarJ.f352i = false;
                if (aVarJ.f346c && !aVarJ.f350g) {
                    aVarJ.c(aVarJ.f345b, aVarJ.f348e);
                }
            }
        }
    }

    final void d() {
        if (!this.f340d) {
            RuntimeException runtimeException = new RuntimeException("here");
            runtimeException.fillInStackTrace();
            Log.w("LoaderManager", "Called doRetain when not started: " + this, runtimeException);
            return;
        }
        this.f341e = true;
        this.f340d = false;
        android.support.v4.util.j<a> jVar = this.f337a;
        for (int i2 = jVar.i() - 1; i2 >= 0; i2--) {
            a aVarJ = jVar.j(i2);
            aVarJ.f350g = true;
            aVarJ.f351h = aVarJ.f349f;
            aVarJ.f349f = false;
            aVarJ.f344a = null;
        }
    }

    final void e() {
        if (this.f340d) {
            RuntimeException runtimeException = new RuntimeException("here");
            runtimeException.fillInStackTrace();
            Log.w("LoaderManager", "Called doStart when already started: " + this, runtimeException);
            return;
        }
        this.f340d = true;
        android.support.v4.util.j<a> jVar = this.f337a;
        for (int i2 = jVar.i() - 1; i2 >= 0; i2--) {
            jVar.j(i2).f();
        }
    }

    final void f() {
        if (!this.f340d) {
            RuntimeException runtimeException = new RuntimeException("here");
            runtimeException.fillInStackTrace();
            Log.w("LoaderManager", "Called doStop when not started: " + this, runtimeException);
            return;
        }
        android.support.v4.util.j<a> jVar = this.f337a;
        for (int i2 = jVar.i() - 1; i2 >= 0; i2--) {
            jVar.j(i2).g();
        }
        this.f340d = false;
    }

    public final void g(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        android.support.v4.util.j<a> jVar = this.f337a;
        if (jVar.i() > 0) {
            printWriter.print(str);
            printWriter.println("Active Loaders:");
            String str2 = str + "    ";
            for (int i2 = 0; i2 < jVar.i(); i2++) {
                a aVarJ = jVar.j(i2);
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(jVar.f(i2));
                printWriter.print(": ");
                printWriter.println(aVarJ.toString());
                aVarJ.e(str2, fileDescriptor, printWriter, strArr);
            }
        }
        android.support.v4.util.j<a> jVar2 = this.f338b;
        if (jVar2.i() > 0) {
            printWriter.print(str);
            printWriter.println("Inactive Loaders:");
            String str3 = str + "    ";
            for (int i3 = 0; i3 < jVar2.i(); i3++) {
                a aVarJ2 = jVar2.j(i3);
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(jVar2.f(i3));
                printWriter.print(": ");
                printWriter.println(aVarJ2.toString());
                aVarJ2.e(str3, fileDescriptor, printWriter, strArr);
            }
        }
    }

    public final boolean h() {
        android.support.v4.util.j<a> jVar = this.f337a;
        int i2 = jVar.i();
        boolean z2 = false;
        for (int i3 = 0; i3 < i2; i3++) {
            a aVarJ = jVar.j(i3);
            z2 |= aVarJ.f349f && !aVarJ.f347d;
        }
        return z2;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder(VertexAttributes.Usage.Tangent);
        sb.append("LoaderManager{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append(" in ");
        android.support.v4.util.k.c(this.f343g, sb);
        sb.append("}}");
        return sb.toString();
    }
}
