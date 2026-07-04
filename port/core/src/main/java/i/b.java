package i;

import android.content.Context;
import android.graphics.Typeface;
import i.e;
import java.util.concurrent.Callable;

/* JADX INFO: compiled from: FontsContractCompat.java */
/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
final class b implements Callable<e.d> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ Context f2222a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final /* synthetic */ a f2223b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final /* synthetic */ int f2224c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final /* synthetic */ String f2225d;

    b(Context context, a aVar, int i2, String str) {
        this.f2222a = context;
        this.f2223b = aVar;
        this.f2224c = i2;
        this.f2225d = str;
    }

    @Override // java.util.concurrent.Callable
    public final e.d call() {
        e.d dVarF = e.f(this.f2222a, this.f2223b, this.f2224c);
        Typeface typeface = dVarF.f2241a;
        if (typeface != null) {
            e.f2228a.put(this.f2225d, typeface);
        }
        return dVarF;
    }
}
