package com.badlogic.gdx.graphics.g3d.particles.influencers;

import a.a;
import a0.j;
import a0.o;
import com.badlogic.gdx.graphics.g3d.particles.ParallelArray;
import com.badlogic.gdx.graphics.g3d.particles.ParticleChannels;
import com.badlogic.gdx.graphics.g3d.particles.values.GradientColorValue;
import com.badlogic.gdx.graphics.g3d.particles.values.ScaledNumericValue;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.t;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public abstract class ColorInfluencer extends Influencer {
    ParallelArray.FloatChannel colorChannel;

    public static class Random extends ColorInfluencer {
        ParallelArray.FloatChannel colorChannel;

        @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
        public void activateParticles(int i2, int i3) {
            int i4 = this.colorChannel.strideSize;
            int i5 = i2 * i4;
            int i6 = (i3 * i4) + i5;
            while (i5 < i6) {
                float[] fArr = this.colorChannel.data;
                o oVar = j.f69a;
                fArr[i5] = oVar.nextFloat();
                this.colorChannel.data[i5 + 1] = oVar.nextFloat();
                this.colorChannel.data[i5 + 2] = oVar.nextFloat();
                this.colorChannel.data[i5 + 3] = oVar.nextFloat();
                i5 += this.colorChannel.strideSize;
            }
        }

        @Override // com.badlogic.gdx.graphics.g3d.particles.influencers.ColorInfluencer, com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
        public void allocateChannels() {
            this.colorChannel = (ParallelArray.FloatChannel) this.controller.particles.addChannel(ParticleChannels.Color);
        }

        @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
        public Random copy() {
            return new Random();
        }
    }

    public static class Single extends ColorInfluencer {
        ParallelArray.FloatChannel alphaInterpolationChannel;
        public ScaledNumericValue alphaValue;
        public GradientColorValue colorValue;
        ParallelArray.FloatChannel lifeChannel;

        public Single() {
            this.colorValue = new GradientColorValue();
            ScaledNumericValue scaledNumericValue = new ScaledNumericValue();
            this.alphaValue = scaledNumericValue;
            scaledNumericValue.setHigh(1.0f);
        }

        @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
        public void activateParticles(int i2, int i3) {
            int i4 = this.colorChannel.strideSize;
            int i5 = i2 * i4;
            int i6 = this.alphaInterpolationChannel.strideSize * i2;
            int i7 = (i2 * this.lifeChannel.strideSize) + 2;
            int i8 = (i3 * i4) + i5;
            while (i5 < i8) {
                float fNewLowValue = this.alphaValue.newLowValue();
                float fNewHighValue = this.alphaValue.newHighValue() - fNewLowValue;
                this.colorValue.getColor(0.0f, this.colorChannel.data, i5);
                this.colorChannel.data[i5 + 3] = a.b(this.alphaValue, this.lifeChannel.data[i7], fNewHighValue, fNewLowValue);
                ParallelArray.FloatChannel floatChannel = this.alphaInterpolationChannel;
                float[] fArr = floatChannel.data;
                fArr[i6] = fNewLowValue;
                fArr[i6 + 1] = fNewHighValue;
                i5 += this.colorChannel.strideSize;
                i6 += floatChannel.strideSize;
                i7 += this.lifeChannel.strideSize;
            }
        }

        @Override // com.badlogic.gdx.graphics.g3d.particles.influencers.ColorInfluencer, com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
        public void allocateChannels() {
            super.allocateChannels();
            ParallelArray.ChannelDescriptor channelDescriptor = ParticleChannels.Interpolation;
            channelDescriptor.id = this.controller.particleChannels.newId();
            this.alphaInterpolationChannel = (ParallelArray.FloatChannel) this.controller.particles.addChannel(channelDescriptor);
            this.lifeChannel = (ParallelArray.FloatChannel) this.controller.particles.addChannel(ParticleChannels.Life);
        }

        @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent, com.badlogic.gdx.utils.Json.b
        public void read(Json json, t tVar) {
            this.alphaValue = (ScaledNumericValue) json.readValue("alpha", ScaledNumericValue.class, tVar);
            this.colorValue = (GradientColorValue) json.readValue("color", GradientColorValue.class, tVar);
        }

        public void set(Single single) {
            this.colorValue.load(single.colorValue);
            this.alphaValue.load(single.alphaValue);
        }

        @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
        public void update() {
            int i2 = this.controller.particles.size * this.colorChannel.strideSize;
            int i3 = 0;
            int i4 = 2;
            int i5 = 0;
            while (i3 < i2) {
                float f2 = this.lifeChannel.data[i4];
                this.colorValue.getColor(f2, this.colorChannel.data, i3);
                float[] fArr = this.alphaInterpolationChannel.data;
                this.colorChannel.data[i3 + 3] = a.b(this.alphaValue, f2, fArr[i5 + 1], fArr[i5]);
                i3 += this.colorChannel.strideSize;
                i5 += this.alphaInterpolationChannel.strideSize;
                i4 += this.lifeChannel.strideSize;
            }
        }

        @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent, com.badlogic.gdx.utils.Json.b
        public void write(Json json) {
            json.writeValue("alpha", this.alphaValue);
            json.writeValue("color", this.colorValue);
        }

        @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
        public Single copy() {
            return new Single(this);
        }

        public Single(Single single) {
            this();
            set(single);
        }
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
    public void allocateChannels() {
        this.colorChannel = (ParallelArray.FloatChannel) this.controller.particles.addChannel(ParticleChannels.Color);
    }
}
