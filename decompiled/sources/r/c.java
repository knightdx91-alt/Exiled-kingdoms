package r;

import com.badlogic.gdx.assets.loaders.n;
import com.badlogic.gdx.utils.m;
import java.lang.reflect.GenericDeclaration;

/* JADX INFO: compiled from: AssetLoadingTask.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class c implements g0.d<Void> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    d f4030a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final a f4031b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final com.badlogic.gdx.assets.loaders.a f4032c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final g0.b f4033d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    volatile boolean f4034e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    volatile boolean f4035f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    volatile com.badlogic.gdx.utils.a<a> f4036g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    volatile g0.c<Void> f4037h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    volatile g0.c<Void> f4038i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    volatile Object f4039j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    volatile boolean f4040k;

    public c(d dVar, a aVar, com.badlogic.gdx.assets.loaders.a aVar2, g0.b bVar) {
        this.f4030a = dVar;
        this.f4031b = aVar;
        this.f4032c = aVar2;
        this.f4033d = bVar;
        dVar.f4050j.getClass();
    }

    private static void a(com.badlogic.gdx.utils.a aVar) {
        boolean z2 = aVar.f1751c;
        aVar.f1751c = true;
        for (int i2 = 0; i2 < aVar.f1750b; i2++) {
            String str = ((a) aVar.get(i2)).f4026a;
            GenericDeclaration genericDeclaration = ((a) aVar.get(i2)).f4027b;
            for (int i3 = aVar.f1750b - 1; i3 > i2; i3--) {
                if (genericDeclaration == ((a) aVar.get(i3)).f4027b && str.equals(((a) aVar.get(i3)).f4026a)) {
                    aVar.o(i3);
                }
            }
        }
        aVar.f1751c = z2;
    }

    private static com.badlogic.gdx.files.a b(com.badlogic.gdx.assets.loaders.a aVar, a aVar2) {
        if (aVar2.f4029d == null) {
            aVar2.f4029d = aVar.resolve(aVar2.f4026a);
        }
        return aVar2.f4029d;
    }

    public final void c() {
        com.badlogic.gdx.assets.loaders.a aVar = this.f4032c;
        if (aVar instanceof com.badlogic.gdx.assets.loaders.b) {
            a aVar2 = this.f4031b;
            ((com.badlogic.gdx.assets.loaders.b) aVar).unloadAsync(this.f4030a, aVar2.f4026a, b(aVar, aVar2), aVar2.f4028c);
        }
    }

    @Override // g0.d
    public final void call() {
        if (this.f4040k) {
            return;
        }
        com.badlogic.gdx.assets.loaders.b bVar = (com.badlogic.gdx.assets.loaders.b) this.f4032c;
        if (this.f4035f) {
            d dVar = this.f4030a;
            a aVar = this.f4031b;
            bVar.loadAsync(dVar, aVar.f4026a, b(this.f4032c, aVar), this.f4031b.f4028c);
            this.f4034e = true;
            return;
        }
        a aVar2 = this.f4031b;
        this.f4036g = bVar.getDependencies(aVar2.f4026a, b(this.f4032c, aVar2), this.f4031b.f4028c);
        if (this.f4036g != null) {
            a(this.f4036g);
            this.f4030a.o(this.f4031b.f4026a, this.f4036g);
        } else {
            d dVar2 = this.f4030a;
            a aVar3 = this.f4031b;
            bVar.loadAsync(dVar2, aVar3.f4026a, b(this.f4032c, aVar3), this.f4031b.f4028c);
            this.f4034e = true;
        }
    }

    public final boolean d() {
        com.badlogic.gdx.assets.loaders.a aVar = this.f4032c;
        if (aVar instanceof n) {
            n nVar = (n) aVar;
            if (this.f4035f) {
                d dVar = this.f4030a;
                a aVar2 = this.f4031b;
                this.f4039j = nVar.load(dVar, aVar2.f4026a, b(this.f4032c, aVar2), this.f4031b.f4028c);
            } else {
                this.f4035f = true;
                a aVar3 = this.f4031b;
                this.f4036g = nVar.getDependencies(aVar3.f4026a, b(this.f4032c, aVar3), this.f4031b.f4028c);
                if (this.f4036g == null) {
                    d dVar2 = this.f4030a;
                    a aVar4 = this.f4031b;
                    this.f4039j = nVar.load(dVar2, aVar4.f4026a, b(this.f4032c, aVar4), this.f4031b.f4028c);
                } else {
                    a(this.f4036g);
                    this.f4030a.o(this.f4031b.f4026a, this.f4036g);
                }
            }
        } else {
            com.badlogic.gdx.assets.loaders.b bVar = (com.badlogic.gdx.assets.loaders.b) aVar;
            if (this.f4035f) {
                if (this.f4038i == null && !this.f4034e) {
                    this.f4038i = this.f4033d.a(this);
                } else if (this.f4034e) {
                    d dVar3 = this.f4030a;
                    a aVar5 = this.f4031b;
                    this.f4039j = bVar.loadSync(dVar3, aVar5.f4026a, b(this.f4032c, aVar5), this.f4031b.f4028c);
                } else if (this.f4038i.b()) {
                    try {
                        this.f4038i.a();
                        d dVar4 = this.f4030a;
                        a aVar6 = this.f4031b;
                        this.f4039j = bVar.loadSync(dVar4, aVar6.f4026a, b(this.f4032c, aVar6), this.f4031b.f4028c);
                    } catch (Exception e2) {
                        throw new m("Couldn't load asset: " + this.f4031b.f4026a, (Throwable) e2);
                    }
                }
            } else if (this.f4037h == null) {
                this.f4037h = this.f4033d.a(this);
            } else if (this.f4037h.b()) {
                try {
                    this.f4037h.a();
                    this.f4035f = true;
                    if (this.f4034e) {
                        d dVar5 = this.f4030a;
                        a aVar7 = this.f4031b;
                        this.f4039j = bVar.loadSync(dVar5, aVar7.f4026a, b(this.f4032c, aVar7), this.f4031b.f4028c);
                    }
                } catch (Exception e3) {
                    throw new m("Couldn't load dependencies of asset: " + this.f4031b.f4026a, (Throwable) e3);
                }
            }
        }
        return this.f4039j != null;
    }
}
