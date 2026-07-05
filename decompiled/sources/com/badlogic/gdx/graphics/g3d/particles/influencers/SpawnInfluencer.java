package com.badlogic.gdx.graphics.g3d.particles.influencers;

import a0.n;
import com.badlogic.gdx.graphics.g3d.particles.ParallelArray;
import com.badlogic.gdx.graphics.g3d.particles.ParticleChannels;
import com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent;
import com.badlogic.gdx.graphics.g3d.particles.ResourceData;
import com.badlogic.gdx.graphics.g3d.particles.values.PointSpawnShapeValue;
import com.badlogic.gdx.graphics.g3d.particles.values.SpawnShapeValue;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.a;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.t;
import r.d;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class SpawnInfluencer extends Influencer {
    ParallelArray.FloatChannel positionChannel;
    ParallelArray.FloatChannel rotationChannel;
    public SpawnShapeValue spawnShapeValue;

    public SpawnInfluencer() {
        this.spawnShapeValue = new PointSpawnShapeValue();
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
    public void activateParticles(int i2, int i3) {
        int i4 = this.positionChannel.strideSize;
        int i5 = i2 * i4;
        int i6 = (i4 * i3) + i5;
        while (i5 < i6) {
            SpawnShapeValue spawnShapeValue = this.spawnShapeValue;
            a aVar = ParticleControllerComponent.TMP_V1;
            spawnShapeValue.spawn(aVar, this.controller.emitter.percent);
            aVar.m(this.controller.transform);
            ParallelArray.FloatChannel floatChannel = this.positionChannel;
            float[] fArr = floatChannel.data;
            fArr[i5] = aVar.f1729a;
            fArr[i5 + 1] = aVar.f1730b;
            fArr[i5 + 2] = aVar.f1731c;
            i5 += floatChannel.strideSize;
        }
        int i7 = this.rotationChannel.strideSize;
        int i8 = i2 * i7;
        int i9 = (i3 * i7) + i8;
        while (i8 < i9) {
            Matrix4 matrix4 = this.controller.transform;
            n nVar = ParticleControllerComponent.TMP_Q;
            matrix4.a(nVar);
            ParallelArray.FloatChannel floatChannel2 = this.rotationChannel;
            float[] fArr2 = floatChannel2.data;
            fArr2[i8] = nVar.f83a;
            fArr2[i8 + 1] = nVar.f84b;
            fArr2[i8 + 2] = nVar.f85c;
            fArr2[i8 + 3] = nVar.f86d;
            i8 += floatChannel2.strideSize;
        }
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
    public void allocateChannels() {
        this.positionChannel = (ParallelArray.FloatChannel) this.controller.particles.addChannel(ParticleChannels.Position);
        this.rotationChannel = (ParallelArray.FloatChannel) this.controller.particles.addChannel(ParticleChannels.Rotation3D);
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
    public void init() {
        this.spawnShapeValue.init();
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent, com.badlogic.gdx.graphics.g3d.particles.ResourceData.Configurable
    public void load(d dVar, ResourceData resourceData) {
        this.spawnShapeValue.load(dVar, resourceData);
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent, com.badlogic.gdx.utils.Json.b
    public void read(Json json, t tVar) {
        this.spawnShapeValue = (SpawnShapeValue) json.readValue("spawnShape", SpawnShapeValue.class, tVar);
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent, com.badlogic.gdx.graphics.g3d.particles.ResourceData.Configurable
    public void save(d dVar, ResourceData resourceData) {
        this.spawnShapeValue.save(dVar, resourceData);
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
    public void start() {
        this.spawnShapeValue.start();
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent, com.badlogic.gdx.utils.Json.b
    public void write(Json json) {
        json.writeValue("spawnShape", this.spawnShapeValue, SpawnShapeValue.class);
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
    public SpawnInfluencer copy() {
        return new SpawnInfluencer(this);
    }

    public SpawnInfluencer(SpawnShapeValue spawnShapeValue) {
        this.spawnShapeValue = spawnShapeValue;
    }

    public SpawnInfluencer(SpawnInfluencer spawnInfluencer) {
        this.spawnShapeValue = spawnInfluencer.spawnShapeValue.copy();
    }
}
