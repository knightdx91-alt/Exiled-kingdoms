package com.badlogic.gdx.graphics.g2d;

import b0.a;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.a;
import com.badlogic.gdx.utils.i;
import com.badlogic.gdx.utils.m;
import com.badlogic.gdx.utils.n0;
import com.badlogic.gdx.utils.y;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Writer;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class ParticleEffect implements i {
    private a bounds;
    private final com.badlogic.gdx.utils.a<ParticleEmitter> emitters;
    protected float motionScale;
    private boolean ownsTexture;
    protected float xSizeScale;
    protected float ySizeScale;

    public ParticleEffect() {
        this.xSizeScale = 1.0f;
        this.ySizeScale = 1.0f;
        this.motionScale = 1.0f;
        this.emitters = new com.badlogic.gdx.utils.a<>(true, 8);
    }

    public void allowCompletion() {
        int i2 = this.emitters.f1750b;
        for (int i3 = 0; i3 < i2; i3++) {
            this.emitters.get(i3).allowCompletion();
        }
    }

    @Override // com.badlogic.gdx.utils.i
    public void dispose() {
        if (this.ownsTexture) {
            int i2 = this.emitters.f1750b;
            for (int i3 = 0; i3 < i2; i3++) {
                a.b<Sprite> it = this.emitters.get(i3).getSprites().iterator();
                while (it.hasNext()) {
                    it.next().getTexture().dispose();
                }
            }
        }
    }

    public void draw(Batch batch) {
        int i2 = this.emitters.f1750b;
        for (int i3 = 0; i3 < i2; i3++) {
            this.emitters.get(i3).draw(batch);
        }
    }

    public ParticleEmitter findEmitter(String str) {
        int i2 = this.emitters.f1750b;
        for (int i3 = 0; i3 < i2; i3++) {
            ParticleEmitter particleEmitter = this.emitters.get(i3);
            if (particleEmitter.getName().equals(str)) {
                return particleEmitter;
            }
        }
        return null;
    }

    public void flipY() {
        int i2 = this.emitters.f1750b;
        for (int i3 = 0; i3 < i2; i3++) {
            this.emitters.get(i3).flipY();
        }
    }

    public b0.a getBoundingBox() {
        if (this.bounds == null) {
            this.bounds = new b0.a();
        }
        b0.a aVar = this.bounds;
        aVar.f();
        a.b<ParticleEmitter> it = this.emitters.iterator();
        while (it.hasNext()) {
            aVar.b(it.next().getBoundingBox());
        }
        return aVar;
    }

    public com.badlogic.gdx.utils.a<ParticleEmitter> getEmitters() {
        return this.emitters;
    }

    public boolean isComplete() {
        int i2 = this.emitters.f1750b;
        for (int i3 = 0; i3 < i2; i3++) {
            if (!this.emitters.get(i3).isComplete()) {
                return false;
            }
        }
        return true;
    }

    public void load(com.badlogic.gdx.files.a aVar, com.badlogic.gdx.files.a aVar2) throws Throwable {
        loadEmitters(aVar);
        loadEmitterImages(aVar2);
    }

    public void loadEmitterImages(TextureAtlas textureAtlas) {
        loadEmitterImages(textureAtlas, null);
    }

    public void loadEmitters(com.badlogic.gdx.files.a aVar) throws Throwable {
        InputStream inputStream = aVar.read();
        this.emitters.clear();
        BufferedReader bufferedReader = null;
        try {
            try {
                BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(inputStream), GL20.GL_NEVER);
                do {
                    try {
                        this.emitters.a(newEmitter(bufferedReader2));
                    } catch (IOException e2) {
                        e = e2;
                        bufferedReader = bufferedReader2;
                        throw new m("Error loading effect: " + aVar, (Throwable) e);
                    } catch (Throwable th) {
                        th = th;
                        bufferedReader = bufferedReader2;
                        n0.a(bufferedReader);
                        throw th;
                    }
                } while (bufferedReader2.readLine() != null);
                n0.a(bufferedReader2);
            } catch (IOException e3) {
                e = e3;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    protected Texture loadTexture(com.badlogic.gdx.files.a aVar) {
        return new Texture(aVar, false);
    }

    protected ParticleEmitter newEmitter(BufferedReader bufferedReader) {
        return new ParticleEmitter(bufferedReader);
    }

    public void preAllocateParticles() {
        a.b<ParticleEmitter> it = this.emitters.iterator();
        while (it.hasNext()) {
            it.next().preAllocateParticles();
        }
    }

    public void reset() {
        reset(true);
    }

    public void save(Writer writer) throws IOException {
        int i2 = this.emitters.f1750b;
        int i3 = 0;
        int i4 = 0;
        while (i3 < i2) {
            ParticleEmitter particleEmitter = this.emitters.get(i3);
            int i5 = i4 + 1;
            if (i4 > 0) {
                writer.write("\n");
            }
            particleEmitter.save(writer);
            i3++;
            i4 = i5;
        }
    }

    public void scaleEffect(float f2) {
        scaleEffect(f2, f2, f2);
    }

    public void setDuration(int i2) {
        int i3 = this.emitters.f1750b;
        for (int i4 = 0; i4 < i3; i4++) {
            ParticleEmitter particleEmitter = this.emitters.get(i4);
            particleEmitter.setContinuous(false);
            particleEmitter.duration = i2;
            particleEmitter.durationTimer = 0.0f;
        }
    }

    public void setEmittersCleanUpBlendFunction(boolean z2) {
        int i2 = this.emitters.f1750b;
        for (int i3 = 0; i3 < i2; i3++) {
            this.emitters.get(i3).setCleansUpBlendFunction(z2);
        }
    }

    public void setFlip(boolean z2, boolean z3) {
        int i2 = this.emitters.f1750b;
        for (int i3 = 0; i3 < i2; i3++) {
            this.emitters.get(i3).setFlip(z2, z3);
        }
    }

    public void setPosition(float f2, float f3) {
        int i2 = this.emitters.f1750b;
        for (int i3 = 0; i3 < i2; i3++) {
            this.emitters.get(i3).setPosition(f2, f3);
        }
    }

    public void start() {
        int i2 = this.emitters.f1750b;
        for (int i3 = 0; i3 < i2; i3++) {
            this.emitters.get(i3).start();
        }
    }

    public void update(float f2) {
        int i2 = this.emitters.f1750b;
        for (int i3 = 0; i3 < i2; i3++) {
            this.emitters.get(i3).update(f2);
        }
    }

    public void loadEmitterImages(TextureAtlas textureAtlas, String str) {
        int i2 = this.emitters.f1750b;
        for (int i3 = 0; i3 < i2; i3++) {
            ParticleEmitter particleEmitter = this.emitters.get(i3);
            if (particleEmitter.getImagePaths().f1750b != 0) {
                com.badlogic.gdx.utils.a<Sprite> aVar = new com.badlogic.gdx.utils.a<>();
                a.b<String> it = particleEmitter.getImagePaths().iterator();
                while (it.hasNext()) {
                    String name = new File(it.next().replace('\\', '/')).getName();
                    int iLastIndexOf = name.lastIndexOf(46);
                    if (iLastIndexOf != -1) {
                        name = name.substring(0, iLastIndexOf);
                    }
                    if (str != null) {
                        name = a.a.k(str, name);
                    }
                    Sprite spriteCreateSprite = textureAtlas.createSprite(name);
                    if (spriteCreateSprite == null) {
                        throw new IllegalArgumentException(a.a.A("SpriteSheet missing image: ", name));
                    }
                    aVar.a(spriteCreateSprite);
                }
                particleEmitter.setSprites(aVar);
            }
        }
    }

    protected ParticleEmitter newEmitter(ParticleEmitter particleEmitter) {
        return new ParticleEmitter(particleEmitter);
    }

    public void reset(boolean z2) {
        int i2 = this.emitters.f1750b;
        for (int i3 = 0; i3 < i2; i3++) {
            this.emitters.get(i3).reset();
        }
        if (z2) {
            float f2 = this.xSizeScale;
            if (f2 == 1.0f && this.ySizeScale == 1.0f && this.motionScale == 1.0f) {
                return;
            }
            scaleEffect(1.0f / f2, 1.0f / this.ySizeScale, 1.0f / this.motionScale);
            this.motionScale = 1.0f;
            this.ySizeScale = 1.0f;
            this.xSizeScale = 1.0f;
        }
    }

    public void scaleEffect(float f2, float f3) {
        scaleEffect(f2, f2, f3);
    }

    public void draw(Batch batch, float f2) {
        int i2 = this.emitters.f1750b;
        for (int i3 = 0; i3 < i2; i3++) {
            this.emitters.get(i3).draw(batch, f2);
        }
    }

    public void load(com.badlogic.gdx.files.a aVar, TextureAtlas textureAtlas) throws Throwable {
        load(aVar, textureAtlas, null);
    }

    public void scaleEffect(float f2, float f3, float f4) {
        this.xSizeScale *= f2;
        this.ySizeScale *= f3;
        this.motionScale *= f4;
        a.b<ParticleEmitter> it = this.emitters.iterator();
        while (it.hasNext()) {
            ParticleEmitter next = it.next();
            next.scaleSize(f2, f3);
            next.scaleMotion(f4);
        }
    }

    public void load(com.badlogic.gdx.files.a aVar, TextureAtlas textureAtlas, String str) throws Throwable {
        loadEmitters(aVar);
        loadEmitterImages(textureAtlas, str);
    }

    public ParticleEffect(ParticleEffect particleEffect) {
        this.xSizeScale = 1.0f;
        this.ySizeScale = 1.0f;
        this.motionScale = 1.0f;
        this.emitters = new com.badlogic.gdx.utils.a<>(true, particleEffect.emitters.f1750b);
        int i2 = particleEffect.emitters.f1750b;
        for (int i3 = 0; i3 < i2; i3++) {
            this.emitters.a(newEmitter(particleEffect.emitters.get(i3)));
        }
    }

    public void loadEmitterImages(com.badlogic.gdx.files.a aVar) {
        this.ownsTexture = true;
        y yVar = new y(this.emitters.f1750b, 0);
        int i2 = this.emitters.f1750b;
        for (int i3 = 0; i3 < i2; i3++) {
            ParticleEmitter particleEmitter = this.emitters.get(i3);
            if (particleEmitter.getImagePaths().f1750b != 0) {
                com.badlogic.gdx.utils.a<Sprite> aVar2 = new com.badlogic.gdx.utils.a<>();
                a.b<String> it = particleEmitter.getImagePaths().iterator();
                while (it.hasNext()) {
                    String name = new File(it.next().replace('\\', '/')).getName();
                    Sprite sprite = (Sprite) yVar.d(name);
                    if (sprite == null) {
                        sprite = new Sprite(loadTexture(aVar.child(name)));
                        yVar.j(name, sprite);
                    }
                    aVar2.a(sprite);
                }
                particleEmitter.setSprites(aVar2);
            }
        }
    }
}
