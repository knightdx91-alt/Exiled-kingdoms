package android.support.v4.util;

import java.util.Map;

/* JADX INFO: compiled from: ArrayMap.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class a extends g {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f537d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    final /* synthetic */ Object f538e;

    public /* synthetic */ a(Object obj, int i2) {
        this.f537d = i2;
        this.f538e = obj;
    }

    @Override // android.support.v4.util.g
    protected final void a() {
        switch (this.f537d) {
            case 0:
                ((b) this.f538e).clear();
                break;
            default:
                ((c) this.f538e).clear();
                break;
        }
    }

    @Override // android.support.v4.util.g
    protected final Object b(int i2, int i3) {
        switch (this.f537d) {
            case 0:
                return ((b) this.f538e).f579b[(i2 << 1) + i3];
            default:
                return ((c) this.f538e).f547b[i2];
        }
    }

    @Override // android.support.v4.util.g
    protected final Map c() {
        switch (this.f537d) {
            case 0:
                return (b) this.f538e;
            default:
                throw new UnsupportedOperationException("not a map");
        }
    }

    @Override // android.support.v4.util.g
    protected final int d() {
        switch (this.f537d) {
            case 0:
                return ((b) this.f538e).f580c;
            default:
                return ((c) this.f538e).f548c;
        }
    }

    @Override // android.support.v4.util.g
    protected final int e(Object obj) {
        switch (this.f537d) {
            case 0:
                return ((b) this.f538e).e(obj);
            default:
                return ((c) this.f538e).indexOf(obj);
        }
    }

    @Override // android.support.v4.util.g
    protected final int f(Object obj) {
        switch (this.f537d) {
            case 0:
                return ((b) this.f538e).g(obj);
            default:
                return ((c) this.f538e).indexOf(obj);
        }
    }

    @Override // android.support.v4.util.g
    protected final void g(Object obj, Object obj2) {
        switch (this.f537d) {
            case 0:
                ((b) this.f538e).put(obj, obj2);
                break;
            default:
                ((c) this.f538e).add(obj);
                break;
        }
    }

    @Override // android.support.v4.util.g
    protected final void h(int i2) {
        switch (this.f537d) {
            case 0:
                ((b) this.f538e).i(i2);
                break;
            default:
                ((c) this.f538e).e(i2);
                break;
        }
    }

    @Override // android.support.v4.util.g
    protected final Object i(int i2, Object obj) {
        switch (this.f537d) {
            case 0:
                int i3 = (i2 << 1) + 1;
                Object[] objArr = ((b) this.f538e).f579b;
                Object obj2 = objArr[i3];
                objArr[i3] = obj;
                return obj2;
            default:
                throw new UnsupportedOperationException("not a map");
        }
    }
}
