package android.arch.lifecycle;

import android.arch.lifecycle.a;
import android.util.Log;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;

/* JADX INFO: compiled from: LifecycleRegistry.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class c extends android.arch.lifecycle.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private b.a<Object, Object> f102a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private a.b f103b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final WeakReference<b> f104c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private boolean f105d;

    /* JADX INFO: compiled from: LifecycleRegistry.java */
    static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f106a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        static final /* synthetic */ int[] f107b;

        static {
            int[] iArr = new int[a.b.values().length];
            f107b = iArr;
            try {
                iArr[1] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f107b[2] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f107b[3] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f107b[4] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f107b[0] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            int[] iArr2 = new int[a.EnumC0002a.values().length];
            f106a = iArr2;
            try {
                iArr2[a.EnumC0002a.ON_CREATE.ordinal()] = 1;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f106a[a.EnumC0002a.ON_STOP.ordinal()] = 2;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f106a[a.EnumC0002a.ON_START.ordinal()] = 3;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f106a[a.EnumC0002a.ON_PAUSE.ordinal()] = 4;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f106a[a.EnumC0002a.ON_RESUME.ordinal()] = 5;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                f106a[a.EnumC0002a.ON_DESTROY.ordinal()] = 6;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                f106a[a.EnumC0002a.ON_ANY.ordinal()] = 7;
            } catch (NoSuchFieldError unused12) {
            }
        }
    }

    public c(b bVar) {
        b.a<Object, Object> aVar = new b.a<>();
        new HashMap();
        this.f102a = aVar;
        this.f105d = false;
        new ArrayList();
        this.f104c = new WeakReference<>(bVar);
        this.f103b = a.b.f97b;
    }

    private void d(a.b bVar) {
        if (this.f103b == bVar) {
            return;
        }
        this.f103b = bVar;
        if (this.f105d) {
            return;
        }
        this.f105d = true;
        if (this.f104c.get() == null) {
            Log.w("LifecycleRegistry", "LifecycleOwner is garbage collected, you shouldn't try dispatch new events from it.");
        } else {
            this.f102a.getClass();
        }
        this.f105d = false;
    }

    @Override // android.arch.lifecycle.a
    public final a.b a() {
        return this.f103b;
    }

    public final void b(a.EnumC0002a enumC0002a) {
        a.b bVar;
        switch (a.f106a[enumC0002a.ordinal()]) {
            case 1:
            case 2:
                bVar = a.b.f98c;
                break;
            case 3:
            case 4:
                bVar = a.b.f99d;
                break;
            case 5:
                bVar = a.b.f100e;
                break;
            case 6:
                bVar = a.b.f96a;
                break;
            default:
                throw new IllegalArgumentException("Unexpected event value " + enumC0002a);
        }
        d(bVar);
    }

    public final void c(a.b bVar) {
        d(bVar);
    }
}
