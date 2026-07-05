package android.support.v7.widget;

import android.content.Context;
import android.content.ContextWrapper;

/* JADX INFO: compiled from: TintContextWrapper.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class u0 extends ContextWrapper {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final Object f1353a = null;

    public static void a(Context context) {
        if ((context instanceof u0) || (context.getResources() instanceof w0)) {
            return;
        }
        context.getResources();
        int i2 = a1.f1182a;
    }
}
