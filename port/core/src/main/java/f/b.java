package f;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import e.a;
import i.e;

/* JADX INFO: compiled from: TypefaceCompat.java */
/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final d f2199a = new d();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final android.support.v4.util.f<String, Typeface> f2200b = new android.support.v4.util.f<>(16);

    public static Typeface a(Context context, e.c[] cVarArr, int i2) {
        return f2199a.g(context, cVarArr, i2);
    }

    public static Typeface b(Context context, a.InterfaceC0030a interfaceC0030a, Resources resources, int i2, int i3, e.d dVar) {
        Typeface typefaceA;
        if (interfaceC0030a instanceof a.d) {
            a.d dVar2 = (a.d) interfaceC0030a;
            typefaceA = i.e.g(context, dVar2.b(), dVar, dVar2.a() == 0, dVar2.c(), i3);
        } else {
            typefaceA = f2199a.a(context, (a.b) interfaceC0030a, resources, i3);
            if (typefaceA != null) {
                dVar.b(typefaceA);
            } else {
                dVar.a(-3);
            }
        }
        if (typefaceA != null) {
            f2200b.put(d(resources, i2, i3), typefaceA);
        }
        return typefaceA;
    }

    public static Typeface c(Context context, Resources resources, int i2, String str, int i3) {
        Typeface typefaceH = f2199a.h(context, resources, i2, str, i3);
        if (typefaceH != null) {
            f2200b.put(d(resources, i2, i3), typefaceH);
        }
        return typefaceH;
    }

    public static String d(Resources resources, int i2, int i3) {
        return resources.getResourcePackageName(i2) + "-" + i2 + "-" + i3;
    }

    public static Typeface e(Resources resources, int i2, int i3) {
        return f2200b.get(d(resources, i2, i3));
    }
}
