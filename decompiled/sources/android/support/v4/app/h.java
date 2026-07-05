package android.support.v4.app;

import android.app.Activity;
import android.os.Handler;
import android.support.v4.app.z;
import java.io.FileDescriptor;
import java.io.PrintWriter;

/* JADX INFO: compiled from: FragmentHostCallback.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public abstract class h<E> extends f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final FragmentActivity f218a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final FragmentActivity f219b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Handler f220c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final j f221d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private android.support.v4.util.i<String, y> f222e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private boolean f223f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private z f224g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private boolean f225h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private boolean f226i;

    h(FragmentActivity fragmentActivity) {
        Handler handler = fragmentActivity.mHandler;
        this.f221d = new j();
        this.f218a = fragmentActivity;
        this.f219b = fragmentActivity;
        this.f220c = handler;
    }

    final void d() {
        z zVar = this.f224g;
        if (zVar == null) {
            return;
        }
        zVar.b();
    }

    final void e() {
        if (this.f226i) {
            return;
        }
        this.f226i = true;
        z zVar = this.f224g;
        if (zVar != null) {
            zVar.e();
        } else if (!this.f225h) {
            z zVarJ = j("(root)", true, false);
            this.f224g = zVarJ;
            if (zVarJ != null && !zVarJ.f340d) {
                zVarJ.e();
            }
        }
        this.f225h = true;
    }

    final void f(boolean z2) {
        this.f223f = z2;
        z zVar = this.f224g;
        if (zVar != null && this.f226i) {
            this.f226i = false;
            if (z2) {
                zVar.d();
            } else {
                zVar.f();
            }
        }
    }

    final void g(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        printWriter.print(str);
        printWriter.print("mLoadersStarted=");
        printWriter.println(this.f226i);
        if (this.f224g != null) {
            printWriter.print(str);
            printWriter.print("Loader Manager ");
            printWriter.print(Integer.toHexString(System.identityHashCode(this.f224g)));
            printWriter.println(":");
            this.f224g.g(a.a.k(str, "  "), fileDescriptor, printWriter, strArr);
        }
    }

    final Activity h() {
        return this.f218a;
    }

    final Handler i() {
        return this.f220c;
    }

    final z j(String str, boolean z2, boolean z3) {
        if (this.f222e == null) {
            this.f222e = new android.support.v4.util.i<>();
        }
        z zVar = (z) this.f222e.get(str);
        if (zVar == null && z3) {
            z zVar2 = new z(str, this, z2);
            this.f222e.put(str, zVar2);
            return zVar2;
        }
        if (!z2 || zVar == null || zVar.f340d) {
            return zVar;
        }
        zVar.e();
        return zVar;
    }

    final z k() {
        z zVar = this.f224g;
        if (zVar != null) {
            return zVar;
        }
        this.f225h = true;
        z zVarJ = j("(root)", this.f226i, true);
        this.f224g = zVarJ;
        return zVarJ;
    }

    final boolean l() {
        return this.f223f;
    }

    final void m(String str) {
        z zVar;
        android.support.v4.util.i<String, y> iVar = this.f222e;
        if (iVar == null || (zVar = (z) iVar.get(str)) == null || zVar.f341e) {
            return;
        }
        zVar.b();
        this.f222e.remove(str);
    }

    final void n() {
        android.support.v4.util.i<String, y> iVar = this.f222e;
        if (iVar != null) {
            int size = iVar.size();
            z[] zVarArr = new z[size];
            for (int i2 = size - 1; i2 >= 0; i2--) {
                zVarArr[i2] = (z) this.f222e.j(i2);
            }
            for (int i3 = 0; i3 < size; i3++) {
                z zVar = zVarArr[i3];
                if (zVar.f341e) {
                    zVar.f341e = false;
                    android.support.v4.util.j<z.a> jVar = zVar.f337a;
                    for (int i4 = jVar.i() - 1; i4 >= 0; i4--) {
                        z.a aVarJ = jVar.j(i4);
                        if (aVarJ.f350g) {
                            aVarJ.f350g = false;
                            boolean z2 = aVarJ.f349f;
                            if (z2 != aVarJ.f351h && !z2) {
                                aVarJ.g();
                            }
                        }
                        if (aVarJ.f349f && aVarJ.f346c && !aVarJ.f352i) {
                            aVarJ.c(aVarJ.f345b, aVarJ.f348e);
                        }
                    }
                }
                zVar.c();
            }
        }
    }

    final void o(android.support.v4.util.i<String, y> iVar) {
        if (iVar != null) {
            int size = iVar.size();
            for (int i2 = 0; i2 < size; i2++) {
                ((z) iVar.j(i2)).f343g = this;
            }
        }
        this.f222e = iVar;
    }

    final android.support.v4.util.i<String, y> p() {
        android.support.v4.util.i<String, y> iVar = this.f222e;
        int i2 = 0;
        if (iVar != null) {
            int size = iVar.size();
            z[] zVarArr = new z[size];
            for (int i3 = size - 1; i3 >= 0; i3--) {
                zVarArr[i3] = (z) this.f222e.j(i3);
            }
            boolean z2 = this.f223f;
            int i4 = 0;
            while (i2 < size) {
                z zVar = zVarArr[i2];
                if (!zVar.f341e && z2) {
                    if (!zVar.f340d) {
                        zVar.e();
                    }
                    zVar.d();
                }
                if (zVar.f341e) {
                    i4 = 1;
                } else {
                    zVar.b();
                    this.f222e.remove(zVar.f339c);
                }
                i2++;
            }
            i2 = i4;
        }
        if (i2 != 0) {
            return this.f222e;
        }
        return null;
    }
}
