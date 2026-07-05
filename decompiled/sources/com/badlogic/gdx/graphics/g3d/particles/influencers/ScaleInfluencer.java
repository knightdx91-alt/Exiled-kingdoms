package com.badlogic.gdx.graphics.g3d.particles.influencers;

import a.a;
import com.badlogic.gdx.graphics.g3d.particles.ParticleChannels;
import com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class ScaleInfluencer extends SimpleInfluencer {
    public ScaleInfluencer() {
        this.valueChannelDescriptor = ParticleChannels.Scale;
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.influencers.SimpleInfluencer, com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
    public void activateParticles(int i2, int i3) {
        if (this.value.isRelative()) {
            int i4 = this.valueChannel.strideSize;
            int i5 = i2 * i4;
            int i6 = i2 * this.interpolationChannel.strideSize;
            int i7 = (i3 * i4) + i5;
            while (i5 < i7) {
                float fNewLowValue = this.value.newLowValue() * this.controller.scale.f1729a;
                float fNewHighValue = this.value.newHighValue() * this.controller.scale.f1729a;
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
            float fNewLowValue2 = this.value.newLowValue() * this.controller.scale.f1729a;
            float fNewHighValue2 = (this.value.newHighValue() * this.controller.scale.f1729a) - fNewLowValue2;
            float[] fArr2 = this.interpolationChannel.data;
            fArr2[i10] = fNewLowValue2;
            fArr2[i10 + 1] = fNewHighValue2;
            this.valueChannel.data[i9] = a.b(this.value, 0.0f, fNewHighValue2, fNewLowValue2);
            i9 += this.valueChannel.strideSize;
            i10 += this.interpolationChannel.strideSize;
        }
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
    public ParticleControllerComponent copy() {
        return new ScaleInfluencer(this);
    }

    public ScaleInfluencer(ScaleInfluencer scaleInfluencer) {
        super(scaleInfluencer);
    }
}
