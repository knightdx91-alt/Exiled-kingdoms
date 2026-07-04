package i;

import i.e;
import i.f;

/* JADX INFO: compiled from: FontsContractCompat.java */
/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
final class c implements f.d<e.d> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ e.d f2226a;

    c(e.d dVar) {
        this.f2226a = dVar;
    }

    @Override // i.f.d
    public final void a(e.d dVar) {
        e.d dVar2 = dVar;
        int i2 = dVar2.f2242b;
        e.d dVar3 = this.f2226a;
        if (i2 == 0) {
            dVar3.b(dVar2.f2241a);
        } else {
            dVar3.a(i2);
        }
    }
}
