package f;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import com.google.android.gms.common.api.Api;
import e.a;
import i.e;

/* JADX INFO: compiled from: TypefaceCompatBaseImpl.java */
/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
class g {

    /* JADX INFO: Access modifiers changed from: public */
    /* JADX INFO: compiled from: TypefaceCompatBaseImpl.java */
    interface a<T> {
        int a(T t2);

        boolean b(T t2);
    }

    public static <T> T b(T[] tArr, int i2, a<T> aVar) {
        int i3 = (i2 & 1) == 0 ? 400 : 700;
        boolean z2 = (i2 & 2) != 0;
        T t2 = null;
        int i4 = Api.BaseClientBuilder.API_PRIORITY_OTHER;
        for (T t3 : tArr) {
            int iAbs = (Math.abs(aVar.a(t3) - i3) * 2) + (aVar.b(t3) == z2 ? 0 : 1);
            if (t2 == null || i4 > iAbs) {
                t2 = t3;
                i4 = iAbs;
            }
        }
        return t2;
    }

    protected static e.c c(int i2, e.c[] cVarArr) {
        return (e.c) b(cVarArr, i2, new e());
    }

    public Typeface a(Context context, a.b bVar, Resources resources, int i2) {
        a.c cVar = (a.c) b(bVar.a(), i2, new f());
        if (cVar == null) {
            return null;
        }
        return b.c(context, resources, cVar.b(), cVar.a(), i2);
    }
}
