package com.badlogic.gdx.graphics.g3d.particles.influencers;

import a.a;
import com.badlogic.gdx.graphics.g3d.particles.ParallelArray;
import com.badlogic.gdx.graphics.g3d.particles.ParticleChannels;
import com.badlogic.gdx.graphics.g3d.particles.values.ScaledNumericValue;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.t;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public abstract class SimpleInfluencer extends Influencer {
    ParallelArray.FloatChannel interpolationChannel;
    ParallelArray.FloatChannel lifeChannel;
    public ScaledNumericValue value;
    ParallelArray.FloatChannel valueChannel;
    ParallelArray.ChannelDescriptor valueChannelDescriptor;

    public SimpleInfluencer() {
        ScaledNumericValue scaledNumericValue = new ScaledNumericValue();
        this.value = scaledNumericValue;
        scaledNumericValue.setHigh(1.0f);
    }

    private void set(SimpleInfluencer simpleInfluencer) {
        this.value.load(simpleInfluencer.value);
        this.valueChannelDescriptor = simpleInfluencer.valueChannelDescriptor;
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
    public void activateParticles(int i2, int i3) {
        if (this.value.isRelative()) {
            int i4 = this.valueChannel.strideSize;
            int i5 = i2 * i4;
            int i6 = i2 * this.interpolationChannel.strideSize;
            int i7 = (i3 * i4) + i5;
            while (i5 < i7) {
                float fNewLowValue = this.value.newLowValue();
                float fNewHighValue = this.value.newHighValue();
                float[] fArr = this.interpolationChannel.data;
                fArr[i6] = fNewLowValue;
                fArr[i6 + 1] = fNewHighValue;
                this.valueChannel.data[i5] = a.b(this.value, 0.0f, fNewHighValue, fNewLowValue);
                i5 += this.valueChannel.strideSize;
                i6 += this.interpolationChannel.strideSize;
            }
            return;
        }
        int i8 = this.valueChannel.strideSize;
        int i9 = i2 * i8;
        int i10 = i2 * this.interpolationChannel.strideSize;
        int i11 = (i3 * i8) + i9;
        while (i9 < i11) {
            float fNewLowValue2 = this.value.newLowValue();
            float fNewHighValue2 = this.value.newHighValue() - fNewLowValue2;
            float[] fArr2 = this.interpolationChannel.data;
            fArr2[i10] = fNewLowValue2;
            fArr2[i10 + 1] = fNewHighValue2;
            this.valueChannel.data[i9] = a.b(this.value, 0.0f, fNewHighValue2, fNewLowValue2);
            i9 += this.valueChannel.strideSize;
            i10 += this.interpolationChannel.strideSize;
        }
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
    public void allocateChannels() {
        this.valueChannel = (ParallelArray.FloatChannel) this.controller.particles.addChannel(this.valueChannelDescriptor);
        ParallelArray.ChannelDescriptor channelDescriptor = ParticleChannels.Interpolation;
        channelDescriptor.id = this.controller.particleChannels.newId();
        this.interpolationChannel = (ParallelArray.FloatChannel) this.controller.particles.addChannel(channelDescriptor);
        this.lifeChannel = (ParallelArray.FloatChannel) this.controller.particles.addChannel(ParticleChannels.Life);
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent, com.badlogic.gdx.utils.Json.b
    public void read(Json json, t tVar) {
        this.value = (ScaledNumericValue) json.readValue("value", ScaledNumericValue.class, tVar);
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
    public void update() {
        int i2 = this.controller.particles.size * this.valueChannel.strideSize;
        int i3 = 0;
        int i4 = 2;
        int i5 = 0;
        while (i3 < i2) {
            float[] fArr = this.valueChannel.data;
            float[] fArr2 = this.interpolationChannel.data;
            fArr[i3] = a.b(this.value, this.lifeChannel.data[i4], fArr2[i5 + 1], fArr2[i5]);
            i3 += this.valueChannel.strideSize;
            i5 += this.interpolationChannel.strideSize;
            i4 += this.lifeChannel.strideSize;
        }
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent, com.badlogic.gdx.utils.Json.b
    public void write(Json json) {
        json.writeValue("value", this.value);
    }

    public SimpleInfluencer(SimpleInfluencer simpleInfluencer) {
        this();
        set(simpleInfluencer);
    }
}
