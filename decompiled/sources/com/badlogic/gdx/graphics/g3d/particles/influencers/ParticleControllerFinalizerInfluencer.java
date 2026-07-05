package com.badlogic.gdx.graphics.g3d.particles.influencers;

import com.badlogic.gdx.graphics.g3d.particles.ParallelArray;
import com.badlogic.gdx.graphics.g3d.particles.ParticleChannels;
import com.badlogic.gdx.graphics.g3d.particles.ParticleController;
import com.badlogic.gdx.utils.m;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class ParticleControllerFinalizerInfluencer extends Influencer {
    ParallelArray.ObjectChannel<ParticleController> controllerChannel;
    boolean hasRotation;
    boolean hasScale;
    ParallelArray.FloatChannel positionChannel;
    ParallelArray.FloatChannel rotationChannel;
    ParallelArray.FloatChannel scaleChannel;

    @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
    public void allocateChannels() {
        this.positionChannel = (ParallelArray.FloatChannel) this.controller.particles.addChannel(ParticleChannels.Position);
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
    public void init() {
        ParallelArray.ObjectChannel<ParticleController> objectChannel = (ParallelArray.ObjectChannel) this.controller.particles.getChannel(ParticleChannels.ParticleController);
        this.controllerChannel = objectChannel;
        if (objectChannel == null) {
            throw new m("ParticleController channel not found, specify an influencer which will allocate it please.");
        }
        this.scaleChannel = (ParallelArray.FloatChannel) this.controller.particles.getChannel(ParticleChannels.Scale);
        ParallelArray.FloatChannel floatChannel = (ParallelArray.FloatChannel) this.controller.particles.getChannel(ParticleChannels.Rotation3D);
        this.rotationChannel = floatChannel;
        this.hasScale = this.scaleChannel != null;
        this.hasRotation = floatChannel != null;
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
    public void update() {
        float f2;
        float f3;
        float f4;
        float f5;
        int i2 = this.controller.particles.size;
        int i3 = 0;
        int i4 = 0;
        while (i3 < i2) {
            ParticleController particleController = this.controllerChannel.data[i3];
            float f6 = this.hasScale ? this.scaleChannel.data[i3] : 1.0f;
            if (this.hasRotation) {
                ParallelArray.FloatChannel floatChannel = this.rotationChannel;
                int i5 = floatChannel.strideSize * i3;
                float[] fArr = floatChannel.data;
                float f7 = fArr[i5];
                float f8 = fArr[i5 + 1];
                float f9 = fArr[i5 + 2];
                f5 = fArr[i5 + 3];
                f3 = f8;
                f4 = f9;
                f2 = f7;
            } else {
                f2 = 0.0f;
                f3 = 0.0f;
                f4 = 0.0f;
                f5 = 1.0f;
            }
            float[] fArr2 = this.positionChannel.data;
            particleController.setTransform(fArr2[i4], fArr2[i4 + 1], fArr2[i4 + 2], f2, f3, f4, f5, f6);
            particleController.update();
            i3++;
            i4 += this.positionChannel.strideSize;
        }
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
    public ParticleControllerFinalizerInfluencer copy() {
        return new ParticleControllerFinalizerInfluencer();
    }
}
