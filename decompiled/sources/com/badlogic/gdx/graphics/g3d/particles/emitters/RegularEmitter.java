package com.badlogic.gdx.graphics.g3d.particles.emitters;

import a.a;
import com.badlogic.gdx.graphics.g3d.particles.ParallelArray;
import com.badlogic.gdx.graphics.g3d.particles.ParticleChannels;
import com.badlogic.gdx.graphics.g3d.particles.ParticleController;
import com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent;
import com.badlogic.gdx.graphics.g3d.particles.values.RangedNumericValue;
import com.badlogic.gdx.graphics.g3d.particles.values.ScaledNumericValue;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.t;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class RegularEmitter extends Emitter {
    private boolean continuous;
    protected float delay;
    protected float delayTimer;
    public RangedNumericValue delayValue;
    protected float duration;
    protected float durationTimer;
    public RangedNumericValue durationValue;
    protected int emission;
    protected int emissionDelta;
    protected int emissionDiff;
    private EmissionMode emissionMode;
    public ScaledNumericValue emissionValue;
    protected int life;
    private ParallelArray.FloatChannel lifeChannel;
    protected int lifeDiff;
    protected int lifeOffset;
    protected int lifeOffsetDiff;
    public ScaledNumericValue lifeOffsetValue;
    public ScaledNumericValue lifeValue;

    public enum EmissionMode {
        Enabled,
        EnabledUntilCycleEnd,
        Disabled
    }

    public RegularEmitter() {
        this.delayValue = new RangedNumericValue();
        this.durationValue = new RangedNumericValue();
        this.lifeOffsetValue = new ScaledNumericValue();
        this.lifeValue = new ScaledNumericValue();
        this.emissionValue = new ScaledNumericValue();
        this.durationValue.setActive(true);
        this.emissionValue.setActive(true);
        this.lifeValue.setActive(true);
        this.continuous = true;
        this.emissionMode = EmissionMode.Enabled;
    }

    private void addParticles(int i2) {
        int iMin = Math.min(i2, this.maxParticleCount - this.controller.particles.size);
        if (iMin <= 0) {
            return;
        }
        ParticleController particleController = this.controller;
        particleController.activateParticles(particleController.particles.size, iMin);
        this.controller.particles.size += iMin;
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
    public void activateParticles(int i2, int i3) {
        int i4;
        int scale = this.life + ((int) (this.lifeValue.getScale(this.percent) * this.lifeDiff));
        int iB = (int) a.b(this.lifeOffsetValue, this.percent, this.lifeOffsetDiff, this.lifeOffset);
        if (iB > 0) {
            if (iB >= scale) {
                iB = scale - 1;
            }
            i4 = scale - iB;
        } else {
            i4 = scale;
        }
        float f2 = i4;
        float f3 = scale;
        float f4 = 1.0f - (f2 / f3);
        int i5 = this.lifeChannel.strideSize;
        int i6 = i2 * i5;
        int i7 = (i3 * i5) + i6;
        while (i6 < i7) {
            ParallelArray.FloatChannel floatChannel = this.lifeChannel;
            float[] fArr = floatChannel.data;
            fArr[i6] = f2;
            fArr[i6 + 1] = f3;
            fArr[i6 + 2] = f4;
            i6 += floatChannel.strideSize;
        }
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
    public void allocateChannels() {
        this.lifeChannel = (ParallelArray.FloatChannel) this.controller.particles.addChannel(ParticleChannels.Life);
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
    public ParticleControllerComponent copy() {
        return new RegularEmitter(this);
    }

    public RangedNumericValue getDelay() {
        return this.delayValue;
    }

    public RangedNumericValue getDuration() {
        return this.durationValue;
    }

    public ScaledNumericValue getEmission() {
        return this.emissionValue;
    }

    public EmissionMode getEmissionMode() {
        return this.emissionMode;
    }

    public ScaledNumericValue getLife() {
        return this.lifeValue;
    }

    public ScaledNumericValue getLifeOffset() {
        return this.lifeOffsetValue;
    }

    public float getPercentComplete() {
        if (this.delayTimer < this.delay) {
            return 0.0f;
        }
        return Math.min(1.0f, this.durationTimer / this.duration);
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.emitters.Emitter, com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
    public void init() {
        super.init();
        this.emissionDelta = 0;
        this.durationTimer = this.duration;
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.emitters.Emitter
    public boolean isComplete() {
        return this.delayTimer >= this.delay && this.durationTimer >= this.duration && this.controller.particles.size == 0;
    }

    public boolean isContinuous() {
        return this.continuous;
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.emitters.Emitter, com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent, com.badlogic.gdx.utils.Json.b
    public void read(Json json, t tVar) {
        super.read(json, tVar);
        this.continuous = ((Boolean) json.readValue("continous", Boolean.TYPE, tVar)).booleanValue();
        this.emissionValue = (ScaledNumericValue) json.readValue("emission", ScaledNumericValue.class, tVar);
        this.delayValue = (RangedNumericValue) json.readValue("delay", RangedNumericValue.class, tVar);
        this.durationValue = (RangedNumericValue) json.readValue("duration", RangedNumericValue.class, tVar);
        this.lifeValue = (ScaledNumericValue) json.readValue("life", ScaledNumericValue.class, tVar);
        this.lifeOffsetValue = (ScaledNumericValue) json.readValue("lifeOffset", ScaledNumericValue.class, tVar);
    }

    public void set(RegularEmitter regularEmitter) {
        super.set((Emitter) regularEmitter);
        this.delayValue.load(regularEmitter.delayValue);
        this.durationValue.load(regularEmitter.durationValue);
        this.lifeOffsetValue.load(regularEmitter.lifeOffsetValue);
        this.lifeValue.load(regularEmitter.lifeValue);
        this.emissionValue.load(regularEmitter.emissionValue);
        this.emission = regularEmitter.emission;
        this.emissionDiff = regularEmitter.emissionDiff;
        this.emissionDelta = regularEmitter.emissionDelta;
        this.lifeOffset = regularEmitter.lifeOffset;
        this.lifeOffsetDiff = regularEmitter.lifeOffsetDiff;
        this.life = regularEmitter.life;
        this.lifeDiff = regularEmitter.lifeDiff;
        this.duration = regularEmitter.duration;
        this.delay = regularEmitter.delay;
        this.durationTimer = regularEmitter.durationTimer;
        this.delayTimer = regularEmitter.delayTimer;
        this.continuous = regularEmitter.continuous;
    }

    public void setContinuous(boolean z2) {
        this.continuous = z2;
    }

    public void setEmissionMode(EmissionMode emissionMode) {
        this.emissionMode = emissionMode;
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
    public void start() {
        RangedNumericValue rangedNumericValue = this.delayValue;
        this.delay = rangedNumericValue.active ? rangedNumericValue.newLowValue() : 0.0f;
        this.delayTimer = 0.0f;
        this.durationTimer = 0.0f;
        float fNewLowValue = this.durationValue.newLowValue();
        this.duration = fNewLowValue;
        this.percent = this.durationTimer / fNewLowValue;
        this.emission = (int) this.emissionValue.newLowValue();
        this.emissionDiff = (int) this.emissionValue.newHighValue();
        if (!this.emissionValue.isRelative()) {
            this.emissionDiff -= this.emission;
        }
        this.life = (int) this.lifeValue.newLowValue();
        this.lifeDiff = (int) this.lifeValue.newHighValue();
        if (!this.lifeValue.isRelative()) {
            this.lifeDiff -= this.life;
        }
        ScaledNumericValue scaledNumericValue = this.lifeOffsetValue;
        this.lifeOffset = scaledNumericValue.active ? (int) scaledNumericValue.newLowValue() : 0;
        this.lifeOffsetDiff = (int) this.lifeOffsetValue.newHighValue();
        if (this.lifeOffsetValue.isRelative()) {
            return;
        }
        this.lifeOffsetDiff -= this.lifeOffset;
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
    public void update() {
        ParticleController particleController;
        int i2;
        ParticleController particleController2 = this.controller;
        float f2 = particleController2.deltaTime * 1000.0f;
        float f3 = this.delayTimer;
        int i3 = 0;
        if (f3 < this.delay) {
            this.delayTimer = f3 + f2;
        } else {
            EmissionMode emissionMode = this.emissionMode;
            boolean z2 = emissionMode != EmissionMode.Disabled;
            float f4 = this.durationTimer;
            float f5 = this.duration;
            if (f4 < f5) {
                float f6 = f4 + f2;
                this.durationTimer = f6;
                this.percent = f6 / f5;
            } else if (this.continuous && z2 && emissionMode == EmissionMode.Enabled) {
                particleController2.start();
            } else {
                z2 = false;
            }
            if (z2) {
                this.emissionDelta = (int) (this.emissionDelta + f2);
                float fB = a.b(this.emissionValue, this.percent, this.emissionDiff, this.emission);
                if (fB > 0.0f) {
                    float f7 = 1000.0f / fB;
                    int i4 = this.emissionDelta;
                    if (i4 >= f7) {
                        int iMin = Math.min((int) (i4 / f7), this.maxParticleCount - this.controller.particles.size);
                        this.emissionDelta = (int) (((int) (this.emissionDelta - (iMin * f7))) % f7);
                        addParticles(iMin);
                    }
                }
                int i5 = this.controller.particles.size;
                int i6 = this.minParticleCount;
                if (i5 < i6) {
                    addParticles(i6 - i5);
                }
            }
        }
        int i7 = this.controller.particles.size;
        int i8 = 0;
        while (true) {
            particleController = this.controller;
            ParallelArray parallelArray = particleController.particles;
            i2 = parallelArray.size;
            if (i3 >= i2) {
                break;
            }
            ParallelArray.FloatChannel floatChannel = this.lifeChannel;
            float[] fArr = floatChannel.data;
            float f8 = fArr[i8] - f2;
            fArr[i8] = f8;
            if (f8 <= 0.0f) {
                parallelArray.removeElement(i3);
            } else {
                fArr[i8 + 2] = 1.0f - (f8 / fArr[i8 + 1]);
                i3++;
                i8 += floatChannel.strideSize;
            }
        }
        if (i2 < i7) {
            particleController.killParticles(i2, i7 - i2);
        }
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.emitters.Emitter, com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent, com.badlogic.gdx.utils.Json.b
    public void write(Json json) {
        super.write(json);
        json.writeValue("continous", Boolean.valueOf(this.continuous));
        json.writeValue("emission", this.emissionValue);
        json.writeValue("delay", this.delayValue);
        json.writeValue("duration", this.durationValue);
        json.writeValue("life", this.lifeValue);
        json.writeValue("lifeOffset", this.lifeOffsetValue);
    }

    public RegularEmitter(RegularEmitter regularEmitter) {
        this();
        set(regularEmitter);
    }
}
