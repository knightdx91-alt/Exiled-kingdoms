package y;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;
import w.b;

/* JADX INFO: compiled from: BatchTiledMapRenderer.java */
/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public abstract class a implements Disposable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected b f4089a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    protected float f4090b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    protected SpriteBatch f4091c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    protected Rectangle f4092d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    protected boolean f4093e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    protected float[] f4094f;

    public a(b bVar) {
        new Rectangle ();
        this.f4094f = new float[20];
        this.f4089a = bVar;
        this.f4090b = 1.0f;
        this.f4092d = new Rectangle ();
        this.f4091c = new SpriteBatch();
        this.f4093e = true;
    }

    protected final void a() {
        z.a.e();
        this.f4091c.begin();
    }

    protected final void b() {
        this.f4091c.end();
    }

    public final Batch c() {
        return this.f4091c;
    }

    public final void d(OrthographicCamera orthographicCamera) {
        this.f4091c.setProjectionMatrix(orthographicCamera.combined);
        float f2 = orthographicCamera.viewportWidth;
        float f3 = orthographicCamera.zoom;
        float f4 = f2 * f3;
        float f5 = orthographicCamera.viewportHeight * f3;
        float fAbs = (Math.abs(orthographicCamera.up.f1729a) * f5) + (Math.abs(orthographicCamera.up.f1730b) * f4);
        float fAbs2 = (Math.abs(orthographicCamera.up.f1729a) * f4) + (Math.abs(orthographicCamera.up.f1730b) * f5);
        Rectangle pVar = this.f4092d;
        com.badlogic.gdx.math.a aVar = orthographicCamera.position;
        pVar.set(aVar.f1729a - (fAbs / 2.0f), aVar.f1730b - (fAbs2 / 2.0f), fAbs, fAbs2);
    }

    @Override // com.badlogic.gdx.utils.i
    public final void dispose() {
        if (this.f4093e) {
            this.f4091c.dispose();
        }
    }
}
