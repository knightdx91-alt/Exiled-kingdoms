package com.badlogic.gdx.graphics.g3d.particles.influencers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.particles.ParallelArray;
import com.badlogic.gdx.graphics.g3d.particles.ParticleChannels;
import com.badlogic.gdx.graphics.g3d.particles.ResourceData;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.a;
import com.badlogic.gdx.utils.t;
import r.d;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public abstract class RegionInfluencer extends Influencer {
    private static final String ASSET_DATA = "atlasAssetData";
    public String atlasName;
    ParallelArray.FloatChannel regionChannel;
    public a<AspectTextureRegion> regions;

    public static class Animated extends RegionInfluencer {
        ParallelArray.FloatChannel lifeChannel;

        public Animated() {
        }

        @Override // com.badlogic.gdx.graphics.g3d.particles.influencers.RegionInfluencer, com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
        public void allocateChannels() {
            super.allocateChannels();
            this.lifeChannel = (ParallelArray.FloatChannel) this.controller.particles.addChannel(ParticleChannels.Life);
        }

        @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
        public void update() {
            int i2 = this.controller.particles.size * this.regionChannel.strideSize;
            int i3 = 0;
            int i4 = 2;
            while (i3 < i2) {
                AspectTextureRegion aspectTextureRegion = this.regions.get((int) (this.lifeChannel.data[i4] * (r3.f1750b - 1)));
                ParallelArray.FloatChannel floatChannel = this.regionChannel;
                float[] fArr = floatChannel.data;
                fArr[i3] = aspectTextureRegion.f1700u;
                fArr[i3 + 1] = aspectTextureRegion.f1701v;
                fArr[i3 + 2] = aspectTextureRegion.u2;
                fArr[i3 + 3] = aspectTextureRegion.v2;
                fArr[i3 + 4] = 0.5f;
                fArr[i3 + 5] = aspectTextureRegion.halfInvAspectRatio;
                i3 += floatChannel.strideSize;
                i4 += this.lifeChannel.strideSize;
            }
        }

        public Animated(Animated animated) {
            super(animated);
        }

        @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
        public Animated copy() {
            return new Animated(this);
        }

        public Animated(TextureRegion textureRegion) {
            super(textureRegion);
        }

        public Animated(Texture texture) {
            super(texture);
        }
    }

    public static class AspectTextureRegion {
        public float halfInvAspectRatio;
        public String imageName;

        /* JADX INFO: renamed from: u, reason: collision with root package name */
        public float f1700u;
        public float u2;

        /* JADX INFO: renamed from: v, reason: collision with root package name */
        public float f1701v;
        public float v2;

        public AspectTextureRegion() {
        }

        public void set(TextureRegion textureRegion) {
            this.f1700u = textureRegion.getU();
            this.f1701v = textureRegion.getV();
            this.u2 = textureRegion.getU2();
            this.v2 = textureRegion.getV2();
            this.halfInvAspectRatio = (textureRegion.getRegionHeight() / textureRegion.getRegionWidth()) * 0.5f;
            if (textureRegion instanceof TextureAtlas.AtlasRegion) {
                this.imageName = ((TextureAtlas.AtlasRegion) textureRegion).name;
            }
        }

        public void updateUV(TextureAtlas textureAtlas) {
            String str = this.imageName;
            if (str == null) {
                return;
            }
            TextureAtlas.AtlasRegion atlasRegionFindRegion = textureAtlas.findRegion(str);
            this.f1700u = atlasRegionFindRegion.getU();
            this.f1701v = atlasRegionFindRegion.getV();
            this.u2 = atlasRegionFindRegion.getU2();
            this.v2 = atlasRegionFindRegion.getV2();
            this.halfInvAspectRatio = (atlasRegionFindRegion.getRegionHeight() / atlasRegionFindRegion.getRegionWidth()) * 0.5f;
        }

        public AspectTextureRegion(AspectTextureRegion aspectTextureRegion) {
            set(aspectTextureRegion);
        }

        public AspectTextureRegion(TextureRegion textureRegion) {
            set(textureRegion);
        }

        public void set(AspectTextureRegion aspectTextureRegion) {
            this.f1700u = aspectTextureRegion.f1700u;
            this.f1701v = aspectTextureRegion.f1701v;
            this.u2 = aspectTextureRegion.u2;
            this.v2 = aspectTextureRegion.v2;
            this.halfInvAspectRatio = aspectTextureRegion.halfInvAspectRatio;
            this.imageName = aspectTextureRegion.imageName;
        }
    }

    public static class Random extends RegionInfluencer {
        public Random() {
        }

        @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
        public void activateParticles(int i2, int i3) {
            int i4 = this.regionChannel.strideSize;
            int i5 = i2 * i4;
            int i6 = (i3 * i4) + i5;
            while (i5 < i6) {
                AspectTextureRegion aspectTextureRegionM = this.regions.m();
                ParallelArray.FloatChannel floatChannel = this.regionChannel;
                float[] fArr = floatChannel.data;
                fArr[i5] = aspectTextureRegionM.f1700u;
                fArr[i5 + 1] = aspectTextureRegionM.f1701v;
                fArr[i5 + 2] = aspectTextureRegionM.u2;
                fArr[i5 + 3] = aspectTextureRegionM.v2;
                fArr[i5 + 4] = 0.5f;
                fArr[i5 + 5] = aspectTextureRegionM.halfInvAspectRatio;
                i5 += floatChannel.strideSize;
            }
        }

        public Random(Random random) {
            super(random);
        }

        @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
        public Random copy() {
            return new Random(this);
        }

        public Random(TextureRegion textureRegion) {
            super(textureRegion);
        }

        public Random(Texture texture) {
            super(texture);
        }
    }

    public static class Single extends RegionInfluencer {
        public Single() {
        }

        @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
        public void init() {
            int i2 = 0;
            AspectTextureRegion aspectTextureRegion = this.regions.f1749a[0];
            int i3 = this.controller.emitter.maxParticleCount * this.regionChannel.strideSize;
            while (i2 < i3) {
                ParallelArray.FloatChannel floatChannel = this.regionChannel;
                float[] fArr = floatChannel.data;
                fArr[i2] = aspectTextureRegion.f1700u;
                fArr[i2 + 1] = aspectTextureRegion.f1701v;
                fArr[i2 + 2] = aspectTextureRegion.u2;
                fArr[i2 + 3] = aspectTextureRegion.v2;
                fArr[i2 + 4] = 0.5f;
                fArr[i2 + 5] = aspectTextureRegion.halfInvAspectRatio;
                i2 += floatChannel.strideSize;
            }
        }

        public Single(Single single) {
            super(single);
        }

        @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
        public Single copy() {
            return new Single(this);
        }

        public Single(TextureRegion textureRegion) {
            super(textureRegion);
        }

        public Single(Texture texture) {
            super(texture);
        }
    }

    public RegionInfluencer(int i2) {
        this.regions = new a<>(false, i2, AspectTextureRegion.class);
    }

    public void add(TextureRegion... textureRegionArr) {
        this.regions.f(textureRegionArr.length);
        for (TextureRegion textureRegion : textureRegionArr) {
            this.regions.a(new AspectTextureRegion(textureRegion));
        }
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
    public void allocateChannels() {
        this.regionChannel = (ParallelArray.FloatChannel) this.controller.particles.addChannel(ParticleChannels.TextureRegion);
    }

    public void clear() {
        this.atlasName = null;
        this.regions.clear();
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent, com.badlogic.gdx.graphics.g3d.particles.ResourceData.Configurable
    public void load(d dVar, ResourceData resourceData) {
        super.load(dVar, resourceData);
        ResourceData.SaveData saveData = resourceData.getSaveData(ASSET_DATA);
        if (saveData == null) {
            return;
        }
        TextureAtlas textureAtlas = (TextureAtlas) dVar.f(saveData.loadAsset());
        a.b<AspectTextureRegion> it = this.regions.iterator();
        while (it.hasNext()) {
            it.next().updateUV(textureAtlas);
        }
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent, com.badlogic.gdx.utils.Json.b
    public void read(Json json, t tVar) {
        this.regions.clear();
        this.regions.b((a) json.readValue("regions", a.class, AspectTextureRegion.class, tVar));
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent, com.badlogic.gdx.graphics.g3d.particles.ResourceData.Configurable
    public void save(d dVar, ResourceData resourceData) {
        super.save(dVar, resourceData);
        if (this.atlasName != null) {
            ResourceData.SaveData saveData = resourceData.getSaveData(ASSET_DATA);
            if (saveData == null) {
                saveData = resourceData.createSaveData(ASSET_DATA);
            }
            saveData.saveAsset(this.atlasName, TextureAtlas.class);
        }
    }

    public void setAtlasName(String str) {
        this.atlasName = str;
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent, com.badlogic.gdx.utils.Json.b
    public void write(Json json) {
        json.writeValue("regions", this.regions, a.class, AspectTextureRegion.class);
    }

    public RegionInfluencer() {
        this(1);
        AspectTextureRegion aspectTextureRegion = new AspectTextureRegion();
        aspectTextureRegion.f1701v = 0.0f;
        aspectTextureRegion.f1700u = 0.0f;
        aspectTextureRegion.v2 = 1.0f;
        aspectTextureRegion.u2 = 1.0f;
        aspectTextureRegion.halfInvAspectRatio = 0.5f;
        this.regions.a(aspectTextureRegion);
    }

    public RegionInfluencer(TextureRegion... textureRegionArr) {
        setAtlasName(null);
        this.regions = new a<>(false, textureRegionArr.length, AspectTextureRegion.class);
        add(textureRegionArr);
    }

    public RegionInfluencer(Texture texture) {
        this(new TextureRegion(texture));
    }

    public RegionInfluencer(RegionInfluencer regionInfluencer) {
        this(regionInfluencer.regions.f1750b);
        this.regions.f(regionInfluencer.regions.f1750b);
        int i2 = 0;
        while (true) {
            a<AspectTextureRegion> aVar = regionInfluencer.regions;
            if (i2 >= aVar.f1750b) {
                return;
            }
            this.regions.a(new AspectTextureRegion(aVar.get(i2)));
            i2++;
        }
    }
}
