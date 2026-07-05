package e0;

/* JADX INFO: compiled from: PurchaseManagerGoogleBilling.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class d implements Runnable {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final /* synthetic */ f f2184b;

    d(f fVar) {
        this.f2184b = fVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        f fVar = this.f2184b;
        f.k(fVar, fVar.f2195i ? null : new c(fVar, 0));
    }
}
