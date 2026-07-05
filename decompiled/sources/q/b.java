package q;

import a0.q;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.utils.i;

/* JADX INFO: compiled from: Light.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public abstract class b implements i {

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    static final Color f3729t = new Color(0.75f, 0.75f, 0.5f, 0.75f);

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    static final float f3730u = Color.toFloatBits(0.0f, 0.0f, 0.0f, 0.0f);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected final Color f3731a = new Color();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    protected f f3732b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    protected boolean f3733c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    protected boolean f3734d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    protected boolean f3735e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    protected boolean f3736f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    protected int f3737g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    protected int f3738h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    protected float f3739i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    protected float f3740j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    protected float f3741k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    protected Mesh f3742l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    protected Mesh f3743m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    protected float[] f3744n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    protected float[] f3745o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    protected float[] f3746p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    protected float[] f3747q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    protected int f3748r;
    final f0.b s;

    /* JADX INFO: compiled from: Light.java */
    final class a implements f0.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ e f3749a;

        a(e eVar) {
            this.f3749a = eVar;
        }

        @Override // f0.b
        public final float a(q qVar, float f2) {
            Color color = b.f3729t;
            e eVar = this.f3749a;
            float[] fArr = eVar.f3745o;
            int i2 = eVar.f3748r;
            fArr[i2] = qVar.f91a;
            eVar.f3746p[i2] = qVar.f92b;
            eVar.f3747q[i2] = f2;
            return f2;
        }
    }

    public b(f fVar, Color color, float f2) {
        new q();
        this.f3733c = true;
        this.f3734d = true;
        this.f3735e = false;
        this.f3736f = true;
        this.f3741k = 2.5f;
        this.f3748r = 0;
        this.s = new a((e) this);
        fVar.f3771f.a(this);
        this.f3732b = fVar;
        e();
        setColor(color);
        d(f2);
    }

    public final float a() {
        return this.f3739i / f.f3764u;
    }

    public final void b() {
        if (this.f3733c) {
            this.f3732b.f3771f.q(this, false);
        } else {
            this.f3732b.f3772g.q(this, false);
        }
        this.f3732b = null;
        dispose();
    }

    abstract void c();

    public abstract void d(float f2);

    @Override // com.badlogic.gdx.utils.i
    public final void dispose() {
        this.f3742l.dispose();
        this.f3743m.dispose();
    }

    void e() {
        this.f3737g = 8;
        this.f3738h = 9;
        this.f3744n = new float[72];
        this.f3745o = new float[9];
        this.f3746p = new float[9];
        this.f3747q = new float[9];
    }

    abstract void f();

    public final void setColor(Color color) {
        Color color2 = this.f3731a;
        if (color != null) {
            color2.set(color);
        } else {
            color2.set(f3729t);
        }
        this.f3740j = color2.toFloatBits();
    }
}
