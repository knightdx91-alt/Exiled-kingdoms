package r;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.loaders.f;
import com.badlogic.gdx.assets.loaders.h;
import com.badlogic.gdx.assets.loaders.j;
import com.badlogic.gdx.assets.loaders.k;
import com.badlogic.gdx.assets.loaders.m;
import com.badlogic.gdx.assets.loaders.o;
import com.badlogic.gdx.assets.loaders.p;
import com.badlogic.gdx.graphics.Cubemap;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.PolygonRegion;
import com.badlogic.gdx.graphics.g2d.PolygonRegionLoader;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.loader.G3dModelLoader;
import com.badlogic.gdx.graphics.g3d.loader.ObjLoader;
import com.badlogic.gdx.graphics.g3d.particles.ParticleEffectLoader;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.a;
import com.badlogic.gdx.utils.i;
import com.badlogic.gdx.utils.l;
import com.badlogic.gdx.utils.n;
import com.badlogic.gdx.utils.r0;
import com.badlogic.gdx.utils.s;
import com.badlogic.gdx.utils.x;
import com.badlogic.gdx.utils.y;
import com.badlogic.gdx.utils.z;
import java.util.Stack;
import net.fdgames.assets.Assets;
import r.b;

/* JADX INFO: compiled from: AssetManager.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class d implements i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final y<Class, y<String, e>> f4041a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final y<String, Class> f4042b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final y<String, com.badlogic.gdx.utils.a<String>> f4043c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final z<String> f4044d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    final y<Class, y<String, com.badlogic.gdx.assets.loaders.a>> f4045e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    final com.badlogic.gdx.utils.a<a> f4046f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    final g0.b f4047g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    final Stack<c> f4048h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    Assets f4049i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    l f4050j;

    public d() {
        l lVar = new l();
        this.f4041a = new y<>();
        this.f4042b = new y<>();
        this.f4043c = new y<>();
        this.f4044d = new z<>();
        this.f4045e = new y<>();
        this.f4046f = new com.badlogic.gdx.utils.a<>();
        this.f4048h = new Stack<>();
        this.f4050j = new l();
        v(BitmapFont.class, new com.badlogic.gdx.assets.loaders.c(lVar));
        v(t.c.class, new h(lVar));
        v(Pixmap.class, new j(lVar));
        v(t.d.class, new m(lVar));
        v(TextureAtlas.class, new o(lVar));
        v(Texture.class, new p(lVar));
        v(Skin.class, new com.badlogic.gdx.assets.loaders.l(lVar));
        v(ParticleEffect.class, new com.badlogic.gdx.assets.loaders.i(lVar));
        v(com.badlogic.gdx.graphics.g3d.particles.ParticleEffect.class, new ParticleEffectLoader(lVar));
        v(PolygonRegion.class, new PolygonRegionLoader(lVar));
        v(n.class, new f(lVar));
        w(Model.class, ".g3dj", new G3dModelLoader(new s(), lVar));
        w(Model.class, ".g3db", new G3dModelLoader(new r0(), lVar));
        w(Model.class, ".obj", new ObjLoader(lVar));
        v(ShaderProgram.class, new k(lVar));
        v(Cubemap.class, new com.badlogic.gdx.assets.loaders.d(lVar));
        this.f4047g = new g0.b();
    }

    private boolean A() {
        b.a aVar;
        c cVarPeek = this.f4048h.peek();
        try {
            if (!cVarPeek.f4040k) {
                if (!cVarPeek.d()) {
                    return false;
                }
            }
            this.f4048h.size();
            this.f4048h.pop();
            if (cVarPeek.f4040k) {
                return true;
            }
            a aVar2 = cVarPeek.f4031b;
            String str = aVar2.f4026a;
            Class cls = aVar2.f4027b;
            Object obj = cVarPeek.f4039j;
            this.f4042b.j(str, cls);
            y<Class, y<String, e>> yVar = this.f4041a;
            y<String, e> yVarD = yVar.d(cls);
            if (yVarD == null) {
                yVarD = new y<>();
                yVar.j(cls, yVarD);
            }
            e eVar = new e();
            eVar.f4052b = 1;
            if (obj == null) {
                throw new IllegalArgumentException("Object must not be null");
            }
            eVar.f4051a = obj;
            yVarD.j(str, eVar);
            a aVar3 = cVarPeek.f4031b;
            b bVar = aVar3.f4028c;
            if (bVar != null && (aVar = bVar.loadedCallback) != null) {
                aVar.finishedLoading(this, aVar3.f4026a, aVar3.f4027b);
            }
            System.nanoTime();
            l lVar = this.f4050j;
            cVarPeek.f4031b.toString();
            lVar.getClass();
            return true;
        } catch (RuntimeException e2) {
            cVarPeek.f4040k = true;
            throw e2;
        }
    }

    private void a(a aVar) {
        String str = aVar.f4026a;
        Class<T> cls = aVar.f4027b;
        com.badlogic.gdx.assets.loaders.a aVarJ = j(cls, str);
        if (aVarJ == null) {
            throw new com.badlogic.gdx.utils.m("No loader for type: ".concat(cls.getSimpleName()));
        }
        this.f4048h.push(new c(this, aVar, aVarJ, this.f4047g));
    }

    private void m(Throwable th) {
        this.f4050j.getClass();
        if (this.f4048h.isEmpty()) {
            throw new com.badlogic.gdx.utils.m(th);
        }
        c cVarPop = this.f4048h.pop();
        a aVar = cVarPop.f4031b;
        if (cVarPop.f4035f && cVarPop.f4036g != null) {
            a.b<a> it = cVarPop.f4036g.iterator();
            while (it.hasNext()) {
                y(it.next().f4026a);
            }
        }
        this.f4048h.clear();
        if (this.f4049i == null) {
            throw new com.badlogic.gdx.utils.m(th);
        }
        Gdx.app.error("net.fdgames.assets.Assets", "Couldn't load asset '" + aVar + "'", (Exception) th);
    }

    private void n(String str) {
        com.badlogic.gdx.utils.a<String> aVarD = this.f4043c.d(str);
        if (aVarD == null) {
            return;
        }
        a.b<String> it = aVarD.iterator();
        while (it.hasNext()) {
            String next = it.next();
            this.f4041a.d(this.f4042b.d(next)).d(next).f4052b++;
            n(next);
        }
    }

    private synchronized void p(String str, a aVar) {
        try {
            com.badlogic.gdx.utils.a<String> aVarD = this.f4043c.d(str);
            if (aVarD == null) {
                aVarD = new com.badlogic.gdx.utils.a<>();
                this.f4043c.j(str, aVarD);
            }
            aVarD.a(aVar.f4026a);
            if (q(aVar.f4026a)) {
                l lVar = this.f4050j;
                aVar.toString();
                lVar.getClass();
                this.f4041a.d(this.f4042b.d(aVar.f4026a)).d(aVar.f4026a).f4052b++;
                n(aVar.f4026a);
            } else {
                l lVar2 = this.f4050j;
                aVar.toString();
                lVar2.getClass();
                a(aVar);
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    private void t() {
        b.a aVar;
        a aVarO = this.f4046f.o(0);
        boolean zQ = q(aVarO.f4026a);
        l lVar = this.f4050j;
        if (!zQ) {
            aVarO.toString();
            lVar.getClass();
            a(aVarO);
            return;
        }
        aVarO.toString();
        lVar.getClass();
        y<String, Class> yVar = this.f4042b;
        String str = aVarO.f4026a;
        this.f4041a.d(yVar.d(str)).d(str).f4052b++;
        n(str);
        b bVar = aVarO.f4028c;
        if (bVar == null || (aVar = bVar.loadedCallback) == null) {
            return;
        }
        aVar.finishedLoading(this, str, aVarO.f4027b);
    }

    public final synchronized void b() {
        try {
            this.f4046f.clear();
            while (!z()) {
            }
            x xVar = new x();
            while (this.f4042b.f2043a > 0) {
                xVar.clear();
                com.badlogic.gdx.utils.a<String> aVarC = this.f4042b.g().c();
                a.b<String> it = aVarC.iterator();
                while (it.hasNext()) {
                    xVar.e(0, it.next());
                }
                a.b<String> it2 = aVarC.iterator();
                while (it2.hasNext()) {
                    com.badlogic.gdx.utils.a<String> aVarD = this.f4043c.d(it2.next());
                    if (aVarD != null) {
                        a.b<String> it3 = aVarD.iterator();
                        while (it3.hasNext()) {
                            String next = it3.next();
                            xVar.e(xVar.c(0, next) + 1, next);
                        }
                    }
                }
                a.b<String> it4 = aVarC.iterator();
                while (it4.hasNext()) {
                    String next2 = it4.next();
                    if (xVar.c(0, next2) == 0) {
                        y(next2);
                    }
                }
            }
            this.f4041a.clear();
            this.f4042b.clear();
            this.f4043c.clear();
            this.f4046f.clear();
            this.f4048h.clear();
        } catch (Throwable th) {
            throw th;
        }
    }

    public final void c() {
        this.f4050j.getClass();
        while (!z()) {
            Thread.yield();
        }
    }

    public final synchronized <T> T d(String str) {
        T t2;
        try {
            Class clsD = this.f4042b.d(str);
            if (clsD == null) {
                throw new com.badlogic.gdx.utils.m("Asset not loaded: " + str);
            }
            y<String, e> yVarD = this.f4041a.d(clsD);
            if (yVarD == null) {
                throw new com.badlogic.gdx.utils.m("Asset not loaded: " + str);
            }
            e eVarD = yVarD.d(str);
            if (eVarD == null) {
                throw new com.badlogic.gdx.utils.m("Asset not loaded: " + str);
            }
            t2 = (T) eVarD.f4051a;
            if (t2 == null) {
                throw new com.badlogic.gdx.utils.m("Asset not loaded: " + str);
            }
        } catch (Throwable th) {
            throw th;
        }
        return t2;
    }

    @Override // com.badlogic.gdx.utils.i
    public final synchronized void dispose() {
        this.f4050j.getClass();
        b();
        this.f4047g.dispose();
    }

    public final synchronized <T> T e(String str, Class<T> cls) {
        T t2;
        try {
            y<String, e> yVarD = this.f4041a.d(cls);
            if (yVarD == null) {
                throw new com.badlogic.gdx.utils.m("Asset not loaded: " + str);
            }
            e eVarD = yVarD.d(str);
            if (eVarD == null) {
                throw new com.badlogic.gdx.utils.m("Asset not loaded: " + str);
            }
            t2 = (T) eVarD.f4051a;
            if (t2 == null) {
                throw new com.badlogic.gdx.utils.m("Asset not loaded: " + str);
            }
        } catch (Throwable th) {
            throw th;
        }
        return t2;
    }

    public final synchronized <T> T f(a<T> aVar) {
        return (T) e(aVar.f4026a, aVar.f4027b);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final synchronized void g(com.badlogic.gdx.utils.a aVar) {
        y<String, e> yVarD = this.f4041a.d(com.badlogic.gdx.graphics.g3d.particles.ParticleEffect.class);
        if (yVarD != null) {
            y.a<String, e> aVarB = yVarD.b();
            aVarB.getClass();
            while (aVarB.hasNext()) {
                aVar.a(((e) aVarB.next().f2058b).f4051a);
            }
        }
    }

    public final synchronized <T> String h(T t2) {
        y.c<Class> cVarG = this.f4041a.g();
        cVarG.getClass();
        while (cVarG.hasNext()) {
            y<String, e> yVarD = this.f4041a.d(cVarG.next());
            y.c<String> cVarG2 = yVarD.g();
            cVarG2.getClass();
            while (cVarG2.hasNext()) {
                String next = cVarG2.next();
                Object obj = yVarD.d(next).f4051a;
                if (obj == t2 || t2.equals(obj)) {
                    return next;
                }
            }
        }
        return null;
    }

    public final synchronized com.badlogic.gdx.utils.a<String> i(String str) {
        return this.f4043c.d(str);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final <T> com.badlogic.gdx.assets.loaders.a j(Class<T> cls, String str) {
        y<String, com.badlogic.gdx.assets.loaders.a> yVarD = this.f4045e.d(cls);
        com.badlogic.gdx.assets.loaders.a aVar = null;
        if (yVarD != null && yVarD.f2043a >= 1) {
            if (str == null) {
                return yVarD.d("");
            }
            y.a<String, com.badlogic.gdx.assets.loaders.a> aVarB = yVarD.b();
            aVarB.getClass();
            int length = -1;
            while (aVarB.hasNext()) {
                y.b next = aVarB.next();
                if (((String) next.f2057a).length() > length && str.endsWith((String) next.f2057a)) {
                    aVar = (com.badlogic.gdx.assets.loaders.a) next.f2058b;
                    length = ((String) next.f2057a).length();
                }
            }
        }
        return aVar;
    }

    public final l k() {
        return this.f4050j;
    }

    public final synchronized int l(String str) {
        Class clsD;
        clsD = this.f4042b.d(str);
        if (clsD == null) {
            throw new com.badlogic.gdx.utils.m("Asset not loaded: ".concat(str));
        }
        return this.f4041a.d(clsD).d(str).f4052b;
    }

    final synchronized void o(String str, com.badlogic.gdx.utils.a<a> aVar) {
        try {
            z<String> zVar = this.f4044d;
            a.b<a> it = aVar.iterator();
            while (it.hasNext()) {
                a next = it.next();
                if (!zVar.contains(next.f4026a)) {
                    zVar.add(next.f4026a);
                    p(str, next);
                }
            }
            zVar.a(32);
        } catch (Throwable th) {
            throw th;
        }
    }

    public final synchronized boolean q(String str) {
        if (str == null) {
            return false;
        }
        return this.f4042b.a(str);
    }

    public final synchronized void r(Class cls, String str) {
        s(str, cls, null);
    }

    public final synchronized <T> void s(String str, Class<T> cls, b<T> bVar) {
        try {
            if (j(cls, str) == null) {
                throw new com.badlogic.gdx.utils.m("No loader for type: ".concat(cls.getSimpleName()));
            }
            int i2 = this.f4046f.f1750b;
            int i3 = 0;
            while (true) {
                com.badlogic.gdx.utils.a<a> aVar = this.f4046f;
                if (i3 < aVar.f1750b) {
                    a aVar2 = aVar.get(i3);
                    if (aVar2.f4026a.equals(str) && !aVar2.f4027b.equals(cls)) {
                        throw new com.badlogic.gdx.utils.m("Asset with name '" + str + "' already in preload queue, but has different type (expected: " + cls.getSimpleName() + ", found: " + aVar2.f4027b.getSimpleName() + ")");
                    }
                    i3++;
                } else {
                    for (int i4 = 0; i4 < this.f4048h.size(); i4++) {
                        a aVar3 = this.f4048h.get(i4).f4031b;
                        if (aVar3.f4026a.equals(str) && !aVar3.f4027b.equals(cls)) {
                            throw new com.badlogic.gdx.utils.m("Asset with name '" + str + "' already in task list, but has different type (expected: " + cls.getSimpleName() + ", found: " + aVar3.f4027b.getSimpleName() + ")");
                        }
                    }
                    Class clsD = this.f4042b.d(str);
                    if (clsD != null && !clsD.equals(cls)) {
                        throw new com.badlogic.gdx.utils.m("Asset with name '" + str + "' already loaded, but has different type (expected: " + cls.getSimpleName() + ", found: " + clsD.getSimpleName() + ")");
                    }
                    a aVar4 = new a(str, cls, bVar);
                    this.f4046f.a(aVar4);
                    l lVar = this.f4050j;
                    aVar4.toString();
                    lVar.getClass();
                }
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    public final synchronized void u(Assets assets) {
        this.f4049i = assets;
    }

    public final synchronized <T, P extends b<T>> void v(Class<T> cls, com.badlogic.gdx.assets.loaders.a<T, P> aVar) {
        w(cls, null, aVar);
    }

    public final synchronized <T, P extends b<T>> void w(Class<T> cls, String str, com.badlogic.gdx.assets.loaders.a<T, P> aVar) {
        try {
            this.f4050j.getClass();
            y<String, com.badlogic.gdx.assets.loaders.a> yVarD = this.f4045e.d(cls);
            if (yVarD == null) {
                y<Class, y<String, com.badlogic.gdx.assets.loaders.a>> yVar = this.f4045e;
                y<String, com.badlogic.gdx.assets.loaders.a> yVar2 = new y<>();
                yVar.j(cls, yVar2);
                yVarD = yVar2;
            }
            if (str == null) {
                str = "";
            }
            yVarD.j(str, aVar);
        } catch (Throwable th) {
            throw th;
        }
    }

    public final synchronized void x(int i2, String str) {
        Class clsD = this.f4042b.d(str);
        if (clsD == null) {
            throw new com.badlogic.gdx.utils.m("Asset not loaded: " + str);
        }
        this.f4041a.d(clsD).d(str).f4052b = i2;
    }

    public final synchronized void y(String str) {
        b bVar;
        b.a aVar;
        String strReplace = str.replace('\\', '/');
        if (this.f4048h.size() > 0) {
            c cVarFirstElement = this.f4048h.firstElement();
            if (cVarFirstElement.f4031b.f4026a.equals(strReplace)) {
                this.f4050j.getClass();
                cVarFirstElement.f4040k = true;
                cVarFirstElement.c();
                return;
            }
        }
        Class clsD = this.f4042b.d(strReplace);
        int i2 = 0;
        while (true) {
            com.badlogic.gdx.utils.a<a> aVar2 = this.f4046f;
            if (i2 >= aVar2.f1750b) {
                i2 = -1;
                break;
            } else if (aVar2.get(i2).f4026a.equals(strReplace)) {
                break;
            } else {
                i2++;
            }
        }
        if (i2 != -1) {
            a aVarO = this.f4046f.o(i2);
            this.f4050j.getClass();
            if (clsD != null && (bVar = aVarO.f4028c) != null && (aVar = bVar.loadedCallback) != null) {
                aVar.finishedLoading(this, aVarO.f4026a, aVarO.f4027b);
            }
            return;
        }
        if (clsD == null) {
            throw new com.badlogic.gdx.utils.m("Asset not loaded: " + strReplace);
        }
        e eVarD = this.f4041a.d(clsD).d(strReplace);
        int i3 = eVarD.f4052b - 1;
        eVarD.f4052b = i3;
        if (i3 <= 0) {
            this.f4050j.getClass();
            Object obj = eVarD.f4051a;
            if (obj instanceof i) {
                ((i) obj).dispose();
            }
            this.f4042b.k(strReplace);
            this.f4041a.d(clsD).k(strReplace);
        } else {
            this.f4050j.getClass();
        }
        com.badlogic.gdx.utils.a<String> aVarD = this.f4043c.d(strReplace);
        if (aVarD != null) {
            a.b<String> it = aVarD.iterator();
            while (it.hasNext()) {
                String next = it.next();
                if (q(next)) {
                    y(next);
                }
            }
        }
        if (eVarD.f4052b <= 0) {
            this.f4043c.k(strReplace);
        }
    }

    public final synchronized boolean z() {
        boolean z2 = false;
        try {
            if (this.f4048h.size() == 0) {
                while (this.f4046f.f1750b != 0 && this.f4048h.size() == 0) {
                    t();
                }
                if (this.f4048h.size() == 0) {
                    return true;
                }
            }
            if (A() && this.f4046f.f1750b == 0) {
                if (this.f4048h.size() == 0) {
                    z2 = true;
                }
            }
            return z2;
        } catch (Throwable th) {
            m(th);
            return this.f4046f.f1750b == 0;
        }
    }
}
