package com.badlogic.gdx.graphics.g3d.particles;

import a0.n;
import b0.a;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g3d.particles.ParallelArray;
import com.badlogic.gdx.graphics.g3d.particles.ResourceData;
import com.badlogic.gdx.graphics.g3d.particles.emitters.Emitter;
import com.badlogic.gdx.graphics.g3d.particles.influencers.Influencer;
import com.badlogic.gdx.graphics.g3d.particles.renderers.ParticleControllerRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.a;
import com.badlogic.gdx.utils.t;
import r.d;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class ParticleController implements Json.b, ResourceData.Configurable {
    protected static final float DEFAULT_TIME_STEP = 0.016666668f;
    protected a boundingBox;
    public float deltaTime;
    public float deltaTimeSqr;
    public Emitter emitter;
    public com.badlogic.gdx.utils.a<Influencer> influencers;
    public String name;
    public ParticleChannels particleChannels;
    public ParallelArray particles;
    public ParticleControllerRenderer<?, ?> renderer;
    public com.badlogic.gdx.math.a scale;
    public Matrix4 transform;

    public ParticleController() {
        this.transform = new Matrix4();
        this.scale = new com.badlogic.gdx.math.a(1.0f, 1.0f, 1.0f);
        this.influencers = new com.badlogic.gdx.utils.a<>(true, 3, Influencer.class);
        setTimeStep(DEFAULT_TIME_STEP);
    }

    private <K extends Influencer> int findIndex(Class<K> cls) {
        int i2 = 0;
        while (true) {
            com.badlogic.gdx.utils.a<Influencer> aVar = this.influencers;
            if (i2 >= aVar.f1750b) {
                return -1;
            }
            if (cls.isAssignableFrom(aVar.get(i2).getClass())) {
                return i2;
            }
            i2++;
        }
    }

    private void setTimeStep(float f2) {
        this.deltaTime = f2;
        this.deltaTimeSqr = f2 * f2;
    }

    public void activateParticles(int i2, int i3) {
        this.emitter.activateParticles(i2, i3);
        a.b<Influencer> it = this.influencers.iterator();
        while (it.hasNext()) {
            it.next().activateParticles(i2, i3);
        }
    }

    protected void allocateChannels(int i2) {
        this.particles = new ParallelArray(i2);
        this.emitter.allocateChannels();
        a.b<Influencer> it = this.influencers.iterator();
        while (it.hasNext()) {
            it.next().allocateChannels();
        }
        this.renderer.allocateChannels();
    }

    protected void bind() {
        this.emitter.set(this);
        a.b<Influencer> it = this.influencers.iterator();
        while (it.hasNext()) {
            it.next().set(this);
        }
        this.renderer.set(this);
    }

    protected void calculateBoundingBox() {
        b0.a aVar = this.boundingBox;
        com.badlogic.gdx.math.a aVar2 = aVar.f1412a;
        aVar2.t(0.0f, 0.0f, 0.0f);
        com.badlogic.gdx.math.a aVar3 = aVar.f1413b;
        aVar3.t(0.0f, 0.0f, 0.0f);
        aVar.i(aVar2, aVar3);
        ParallelArray.FloatChannel floatChannel = (ParallelArray.FloatChannel) this.particles.getChannel(ParticleChannels.Position);
        int i2 = floatChannel.strideSize * this.particles.size;
        for (int i3 = 0; i3 < i2; i3 += floatChannel.strideSize) {
            b0.a aVar4 = this.boundingBox;
            float[] fArr = floatChannel.data;
            aVar4.a(fArr[i3], fArr[i3 + 1], fArr[i3 + 2]);
        }
    }

    public ParticleController copy() {
        Emitter emitter = (Emitter) this.emitter.copy();
        com.badlogic.gdx.utils.a<Influencer> aVar = this.influencers;
        Influencer[] influencerArr = new Influencer[aVar.f1750b];
        a.b<Influencer> it = aVar.iterator();
        int i2 = 0;
        while (it.hasNext()) {
            influencerArr[i2] = (Influencer) it.next().copy();
            i2++;
        }
        return new ParticleController(new String(this.name), emitter, (ParticleControllerRenderer) this.renderer.copy(), influencerArr);
    }

    public void dispose() {
        this.emitter.dispose();
        a.b<Influencer> it = this.influencers.iterator();
        while (it.hasNext()) {
            it.next().dispose();
        }
    }

    public void draw() {
        if (this.particles.size > 0) {
            this.renderer.update();
        }
    }

    public void end() {
        a.b<Influencer> it = this.influencers.iterator();
        while (it.hasNext()) {
            it.next().end();
        }
        this.emitter.end();
    }

    public <K extends Influencer> K findInfluencer(Class<K> cls) {
        int iFindIndex = findIndex(cls);
        if (iFindIndex > -1) {
            return (K) this.influencers.get(iFindIndex);
        }
        return null;
    }

    public b0.a getBoundingBox() {
        if (this.boundingBox == null) {
            this.boundingBox = new b0.a();
        }
        calculateBoundingBox();
        return this.boundingBox;
    }

    public void getTransform(Matrix4 matrix4) {
        matrix4.o(this.transform);
    }

    public void init() {
        bind();
        if (this.particles != null) {
            end();
            this.particleChannels.resetIds();
        }
        allocateChannels(this.emitter.maxParticleCount);
        this.emitter.init();
        a.b<Influencer> it = this.influencers.iterator();
        while (it.hasNext()) {
            it.next().init();
        }
        this.renderer.init();
    }

    public boolean isComplete() {
        return this.emitter.isComplete();
    }

    public void killParticles(int i2, int i3) {
        this.emitter.killParticles(i2, i3);
        a.b<Influencer> it = this.influencers.iterator();
        while (it.hasNext()) {
            it.next().killParticles(i2, i3);
        }
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.ResourceData.Configurable
    public void load(d dVar, ResourceData resourceData) {
        this.emitter.load(dVar, resourceData);
        a.b<Influencer> it = this.influencers.iterator();
        while (it.hasNext()) {
            it.next().load(dVar, resourceData);
        }
        this.renderer.load(dVar, resourceData);
    }

    public void mul(Matrix4 matrix4) {
        this.transform.g(matrix4);
        this.transform.b(this.scale);
    }

    @Override // com.badlogic.gdx.utils.Json.b
    public void read(Json json, t tVar) {
        this.name = (String) json.readValue("name", String.class, tVar);
        this.emitter = (Emitter) json.readValue("emitter", Emitter.class, tVar);
        this.influencers.b((com.badlogic.gdx.utils.a) json.readValue("influencers", com.badlogic.gdx.utils.a.class, Influencer.class, tVar));
        this.renderer = (ParticleControllerRenderer) json.readValue("renderer", ParticleControllerRenderer.class, tVar);
    }

    public <K extends Influencer> void removeInfluencer(Class<K> cls) {
        int iFindIndex = findIndex(cls);
        if (iFindIndex > -1) {
            this.influencers.o(iFindIndex);
        }
    }

    public <K extends Influencer> boolean replaceInfluencer(Class<K> cls, K k2) {
        int iFindIndex = findIndex(cls);
        if (iFindIndex <= -1) {
            return false;
        }
        this.influencers.i(iFindIndex, k2);
        this.influencers.o(iFindIndex + 1);
        return true;
    }

    public void reset() {
        end();
        start();
    }

    public void rotate(n nVar) {
        this.transform.j(nVar);
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.ResourceData.Configurable
    public void save(d dVar, ResourceData resourceData) {
        this.emitter.save(dVar, resourceData);
        a.b<Influencer> it = this.influencers.iterator();
        while (it.hasNext()) {
            it.next().save(dVar, resourceData);
        }
        this.renderer.save(dVar, resourceData);
    }

    public void scale(float f2, float f3, float f4) {
        this.transform.l(f2, f3, f4);
        this.transform.b(this.scale);
    }

    public void setTransform(Matrix4 matrix4) {
        this.transform.o(matrix4);
        matrix4.b(this.scale);
    }

    public void setTranslation(com.badlogic.gdx.math.a aVar) {
        this.transform.u(aVar);
    }

    public void start() {
        this.emitter.start();
        a.b<Influencer> it = this.influencers.iterator();
        while (it.hasNext()) {
            it.next().start();
        }
    }

    public void translate(com.badlogic.gdx.math.a aVar) {
        Matrix4 matrix4 = this.transform;
        matrix4.getClass();
        matrix4.v(aVar.f1729a, aVar.f1730b, aVar.f1731c);
    }

    public void update() {
        update(Gdx.graphics.getDeltaTime());
    }

    @Override // com.badlogic.gdx.utils.Json.b
    public void write(Json json) {
        json.writeValue("name", this.name);
        json.writeValue("emitter", this.emitter, Emitter.class);
        json.writeValue("influencers", this.influencers, com.badlogic.gdx.utils.a.class, Influencer.class);
        json.writeValue("renderer", this.renderer, ParticleControllerRenderer.class);
    }

    public void rotate(com.badlogic.gdx.math.a aVar, float f2) {
        this.transform.k(aVar, f2);
    }

    public void update(float f2) {
        setTimeStep(f2);
        this.emitter.update();
        a.b<Influencer> it = this.influencers.iterator();
        while (it.hasNext()) {
            it.next().update();
        }
    }

    public void scale(com.badlogic.gdx.math.a aVar) {
        scale(aVar.f1729a, aVar.f1730b, aVar.f1731c);
    }

    public void setTransform(float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9) {
        this.transform.m(f2, f3, f4, f5, f6, f7, f8, f9, f9, f9);
        this.scale.t(f9, f9, f9);
    }

    public ParticleController(String str, Emitter emitter, ParticleControllerRenderer<?, ?> particleControllerRenderer, Influencer... influencerArr) {
        this();
        this.name = str;
        this.emitter = emitter;
        this.renderer = particleControllerRenderer;
        this.particleChannels = new ParticleChannels();
        this.influencers = new com.badlogic.gdx.utils.a<>(influencerArr);
    }
}
