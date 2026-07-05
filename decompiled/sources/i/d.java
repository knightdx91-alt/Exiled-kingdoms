package i;

import i.e;
import i.f;
import java.util.ArrayList;

/* JADX INFO: compiled from: FontsContractCompat.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class d implements f.d<e.d> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ String f2227a;

    d(String str) {
        this.f2227a = str;
    }

    @Override // i.f.d
    public final void a(e.d dVar) {
        e.d dVar2 = dVar;
        synchronized (e.f2230c) {
            try {
                ArrayList arrayList = (ArrayList) e.f2231d.get(this.f2227a);
                if (arrayList == null) {
                    return;
                }
                e.f2231d.remove(this.f2227a);
                for (int i2 = 0; i2 < arrayList.size(); i2++) {
                    ((f.d) arrayList.get(i2)).a(dVar2);
                }
            } finally {
            }
        }
    }
}
