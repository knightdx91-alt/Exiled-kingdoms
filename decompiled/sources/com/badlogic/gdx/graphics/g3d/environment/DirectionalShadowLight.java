package com.badlogic.gdx.graphics.g3d.environment;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g3d.utils.TextureDescriptor;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.a;
import com.badlogic.gdx.utils.i;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class DirectionalShadowLight extends DirectionalLight implements ShadowMap, i {
    protected Camera cam;
    protected FrameBuffer fbo;
    protected float halfDepth;
    protected float halfHeight;
    protected final TextureDescriptor textureDesc;
    protected final a tmpV = new a();

    public DirectionalShadowLight(int i2, int i3, float f2, float f3, float f4, float f5) {
        this.fbo = new FrameBuffer(Pixmap.Format.RGBA8888, i2, i3, true);
        OrthographicCamera orthographicCamera = new OrthographicCamera(f2, f3);
        this.cam = orthographicCamera;
        orthographicCamera.near = f4;
        orthographicCamera.far = f5;
        this.halfHeight = f3 * 0.5f;
        this.halfDepth = a.a.C(f5, f4, 0.5f, f4);
        TextureDescriptor textureDescriptor = new TextureDescriptor();
        this.textureDesc = textureDescriptor;
        Texture.TextureFilter textureFilter = Texture.TextureFilter.Nearest;
        textureDescriptor.magFilter = textureFilter;
        textureDescriptor.minFilter = textureFilter;
        Texture.TextureWrap textureWrap = Texture.TextureWrap.ClampToEdge;
        textureDescriptor.vWrap = textureWrap;
        textureDescriptor.uWrap = textureWrap;
    }

    public void begin(Camera camera) {
        update(camera);
        begin();
    }

    @Override // com.badlogic.gdx.utils.i
    public void dispose() {
        FrameBuffer frameBuffer = this.fbo;
        if (frameBuffer != null) {
            frameBuffer.dispose();
        }
        this.fbo = null;
    }

    public void end() {
        Gdx.gl.glDisable(GL20.GL_SCISSOR_TEST);
        this.fbo.end();
    }

    public Camera getCamera() {
        return this.cam;
    }

    @Override // com.badlogic.gdx.graphics.g3d.environment.ShadowMap
    public TextureDescriptor getDepthMap() {
        this.textureDesc.texture = this.fbo.getColorBufferTexture();
        return this.textureDesc;
    }

    public FrameBuffer getFrameBuffer() {
        return this.fbo;
    }

    @Override // com.badlogic.gdx.graphics.g3d.environment.ShadowMap
    public Matrix4 getProjViewTrans() {
        return this.cam.combined;
    }

    public void update(Camera camera) {
        a aVar = this.tmpV;
        aVar.u(camera.direction);
        aVar.s(this.halfHeight);
        update(aVar, camera.direction);
    }

    public void update(a aVar, a aVar2) {
        a aVar3 = this.cam.position;
        aVar3.u(this.direction);
        aVar3.s(-this.halfDepth);
        aVar3.b(aVar);
        a aVar4 = this.cam.direction;
        aVar4.u(this.direction);
        aVar4.n();
        this.cam.normalizeUp();
        this.cam.update();
    }

    public void begin(a aVar, a aVar2) {
        update(aVar, aVar2);
        begin();
    }

    public void begin() {
        int width = this.fbo.getWidth();
        int height = this.fbo.getHeight();
        this.fbo.begin();
        Gdx.gl.glViewport(0, 0, width, height);
        Gdx.gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
        Gdx.gl.glClear(16640);
        Gdx.gl.glEnable(GL20.GL_SCISSOR_TEST);
        Gdx.gl.glScissor(1, 1, width - 2, height - 2);
    }
}
