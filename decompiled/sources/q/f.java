package q;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.a;
import com.badlogic.gdx.utils.i;

/* JADX INFO: compiled from: RayHandler.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class f implements i {

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    static boolean f3763t = false;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    static float f3764u = 1.0f;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public static boolean f3765v;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final a f3766a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final a f3767b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final a f3768c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final Matrix4 f3769d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    final Color f3770e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    final com.badlogic.gdx.utils.a<b> f3771f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    final com.badlogic.gdx.utils.a<b> f3772g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    c f3773h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    final ShaderProgram f3774i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    boolean f3775j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    boolean f3776k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    boolean f3777l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    int f3778m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    int f3779n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    float f3780o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    float f3781p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    float f3782q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    float f3783r;
    World s;

    public f(World world) {
        int width = Gdx.graphics.getWidth() / 4;
        int height = Gdx.graphics.getHeight() / 4;
        this.f3766a = new a(GL20.GL_DST_COLOR, 0);
        this.f3767b = new a(1, GL20.GL_ONE_MINUS_SRC_ALPHA);
        this.f3768c = new a(GL20.GL_SRC_ALPHA, 1);
        this.f3769d = new Matrix4();
        this.f3770e = new Color();
        this.f3771f = new com.badlogic.gdx.utils.a<>(false, 16);
        this.f3772g = new com.badlogic.gdx.utils.a<>(false, 16);
        this.f3775j = true;
        this.f3776k = true;
        this.f3777l = true;
        this.f3778m = 1;
        Gdx.graphics.getWidth();
        Gdx.graphics.getHeight();
        this.f3779n = 0;
        this.s = world;
        c cVar = this.f3773h;
        if (cVar != null) {
            cVar.a();
        }
        this.f3773h = new c(this, width, height);
        String strL = a.a.l("#ifdef GL_ES\nprecision lowp float;\n#define MED mediump\n#else\n#define MED \n#endif\nvarying vec4 v_color;\nvoid main()\n{\n  gl_FragColor = ", f3763t ? "sqrt" : "", "(v_color);\n}");
        ShaderProgram.pedantic = false;
        ShaderProgram shaderProgram = new ShaderProgram("attribute vec4 vertex_positions;\nattribute vec4 quad_colors;\nattribute float s;\nuniform mat4 u_projTrans;\nvarying vec4 v_color;\nvoid main()\n{\n   v_color = s * quad_colors;\n   gl_Position =  u_projTrans * vertex_positions;\n}\n", strL);
        if (!shaderProgram.isCompiled()) {
            Gdx.app.log("ERROR", shaderProgram.getLog());
        }
        this.f3774i = shaderProgram;
    }

    public static void d() {
        f3763t = true;
        f3764u = 0.625f;
    }

    public final void a(float f2, float f3, float f4, float f5) {
        this.f3770e.set(f2, f3, f4, f5);
    }

    public final void b() {
        this.f3778m = 3;
    }

    public final void c(OrthographicCamera orthographicCamera) {
        Matrix4 matrix4 = orthographicCamera.combined;
        com.badlogic.gdx.math.a aVar = orthographicCamera.position;
        float f2 = aVar.f1729a;
        float f3 = aVar.f1730b;
        float f4 = orthographicCamera.viewportWidth;
        float f5 = orthographicCamera.zoom;
        float f6 = orthographicCamera.viewportHeight * f5;
        System.arraycopy(matrix4.f1724a, 0, this.f3769d.f1724a, 0, 16);
        float f7 = f4 * f5 * 0.5f;
        this.f3780o = f2 - f7;
        this.f3781p = f2 + f7;
        float f8 = f6 * 0.5f;
        this.f3782q = f3 - f8;
        this.f3783r = f3 + f8;
    }

    @Override // com.badlogic.gdx.utils.i
    public final void dispose() {
        com.badlogic.gdx.utils.a<b> aVar = this.f3771f;
        a.b<b> it = aVar.iterator();
        while (it.hasNext()) {
            it.next().dispose();
        }
        aVar.clear();
        com.badlogic.gdx.utils.a<b> aVar2 = this.f3772g;
        a.b<b> it2 = aVar2.iterator();
        while (it2.hasNext()) {
            it2.next().dispose();
        }
        aVar2.clear();
        c cVar = this.f3773h;
        if (cVar != null) {
            cVar.a();
        }
        ShaderProgram shaderProgram = this.f3774i;
        if (shaderProgram != null) {
            shaderProgram.dispose();
        }
    }

    public final void e() {
        com.badlogic.gdx.utils.a<b> aVar = this.f3771f;
        a.b<b> it = aVar.iterator();
        while (it.hasNext()) {
            it.next().f();
        }
        this.f3779n = 0;
        Gdx.gl.glDepthMask(false);
        Gdx.gl.glEnable(GL20.GL_BLEND);
        GL20 gl20 = Gdx.gl20;
        a aVar2 = this.f3768c;
        gl20.glBlendFunc(aVar2.f3727a, aVar2.f3728b);
        boolean z2 = this.f3776k || this.f3777l;
        if (z2) {
            this.f3773h.f3751b.begin();
            Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        }
        ShaderProgram shaderProgram = this.f3774i;
        shaderProgram.begin();
        shaderProgram.setUniformMatrix("u_projTrans", this.f3769d);
        a.b<b> it2 = aVar.iterator();
        while (it2.hasNext()) {
            it2.next().c();
        }
        shaderProgram.end();
        if (z2) {
            this.f3773h.f3751b.end();
            this.f3773h.b();
        }
    }
}
