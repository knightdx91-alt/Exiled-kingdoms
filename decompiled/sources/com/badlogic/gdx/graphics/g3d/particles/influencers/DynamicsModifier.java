package com.badlogic.gdx.graphics.g3d.particles.influencers;

import a0.j;
import a0.n;
import com.badlogic.gdx.graphics.g3d.particles.ParallelArray;
import com.badlogic.gdx.graphics.g3d.particles.ParticleChannels;
import com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent;
import com.badlogic.gdx.graphics.g3d.particles.values.ScaledNumericValue;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.a;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.t;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public abstract class DynamicsModifier extends Influencer {
    public boolean isGlobal;
    protected ParallelArray.FloatChannel lifeChannel;
    protected static final a TMP_V1 = new a();
    protected static final a TMP_V2 = new a();
    protected static final a TMP_V3 = new a();
    protected static final n TMP_Q = new n();

    public static class BrownianAcceleration extends Strength {
        ParallelArray.FloatChannel accelerationChannel;

        public BrownianAcceleration() {
        }

        @Override // com.badlogic.gdx.graphics.g3d.particles.influencers.DynamicsModifier.Strength, com.badlogic.gdx.graphics.g3d.particles.influencers.DynamicsModifier, com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
        public void allocateChannels() {
            super.allocateChannels();
            this.accelerationChannel = (ParallelArray.FloatChannel) this.controller.particles.addChannel(ParticleChannels.Acceleration);
        }

        @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
        public void update() {
            int i2 = this.controller.particles.size;
            int i3 = 2;
            int i4 = 0;
            int i5 = 0;
            int i6 = 0;
            while (i4 < i2) {
                float[] fArr = this.strengthChannel.data;
                float fB = a.a.b(this.strengthValue, this.lifeChannel.data[i3], fArr[i5 + 1], fArr[i5]);
                a aVar = DynamicsModifier.TMP_V3;
                aVar.t(j.h(-1.0f, 1.0f), j.h(-1.0f, 1.0f), j.h(-1.0f, 1.0f));
                aVar.n();
                aVar.s(fB);
                ParallelArray.FloatChannel floatChannel = this.accelerationChannel;
                float[] fArr2 = floatChannel.data;
                fArr2[i6] = fArr2[i6] + aVar.f1729a;
                int i7 = i6 + 1;
                fArr2[i7] = fArr2[i7] + aVar.f1730b;
                int i8 = i6 + 2;
                fArr2[i8] = fArr2[i8] + aVar.f1731c;
                i4++;
                i5 += this.strengthChannel.strideSize;
                i6 += floatChannel.strideSize;
                i3 += this.lifeChannel.strideSize;
            }
        }

        public BrownianAcceleration(BrownianAcceleration brownianAcceleration) {
            super(brownianAcceleration);
        }

        @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
        public BrownianAcceleration copy() {
            return new BrownianAcceleration(this);
        }
    }

    public static class CentripetalAcceleration extends Strength {
        ParallelArray.FloatChannel accelerationChannel;
        ParallelArray.FloatChannel positionChannel;

        public CentripetalAcceleration() {
        }

        @Override // com.badlogic.gdx.graphics.g3d.particles.influencers.DynamicsModifier.Strength, com.badlogic.gdx.graphics.g3d.particles.influencers.DynamicsModifier, com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
        public void allocateChannels() {
            super.allocateChannels();
            this.accelerationChannel = (ParallelArray.FloatChannel) this.controller.particles.addChannel(ParticleChannels.Acceleration);
            this.positionChannel = (ParallelArray.FloatChannel) this.controller.particles.addChannel(ParticleChannels.Position);
        }

        @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
        public void update() {
            float f2;
            float f3;
            float f4;
            if (this.isGlobal) {
                f2 = 0.0f;
                f3 = 0.0f;
                f4 = 0.0f;
            } else {
                float[] fArr = this.controller.transform.f1724a;
                f2 = fArr[12];
                f4 = fArr[13];
                f3 = fArr[14];
            }
            int i2 = this.controller.particles.size;
            int i3 = 2;
            int i4 = 0;
            int i5 = 0;
            int i6 = 0;
            int i7 = 0;
            while (i4 < i2) {
                float[] fArr2 = this.strengthChannel.data;
                float fB = a.a.b(this.strengthValue, this.lifeChannel.data[i3], fArr2[i5 + 1], fArr2[i5]);
                a aVar = DynamicsModifier.TMP_V3;
                float[] fArr3 = this.positionChannel.data;
                aVar.t(fArr3[i6] - f2, fArr3[i6 + 1] - f4, fArr3[i6 + 2] - f3);
                aVar.n();
                aVar.s(fB);
                ParallelArray.FloatChannel floatChannel = this.accelerationChannel;
                float[] fArr4 = floatChannel.data;
                fArr4[i7] = fArr4[i7] + aVar.f1729a;
                int i8 = i7 + 1;
                fArr4[i8] = fArr4[i8] + aVar.f1730b;
                int i9 = i7 + 2;
                fArr4[i9] = fArr4[i9] + aVar.f1731c;
                i4++;
                i6 += this.positionChannel.strideSize;
                i5 += this.strengthChannel.strideSize;
                i7 += floatChannel.strideSize;
                i3 += this.lifeChannel.strideSize;
            }
        }

        public CentripetalAcceleration(CentripetalAcceleration centripetalAcceleration) {
            super(centripetalAcceleration);
        }

        @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
        public CentripetalAcceleration copy() {
            return new CentripetalAcceleration(this);
        }
    }

    public static class FaceDirection extends DynamicsModifier {
        ParallelArray.FloatChannel accellerationChannel;
        ParallelArray.FloatChannel rotationChannel;

        public FaceDirection() {
        }

        @Override // com.badlogic.gdx.graphics.g3d.particles.influencers.DynamicsModifier, com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
        public void allocateChannels() {
            this.rotationChannel = (ParallelArray.FloatChannel) this.controller.particles.addChannel(ParticleChannels.Rotation3D);
            this.accellerationChannel = (ParallelArray.FloatChannel) this.controller.particles.addChannel(ParticleChannels.Acceleration);
        }

        @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
        public ParticleControllerComponent copy() {
            return new FaceDirection(this);
        }

        @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
        public void update() {
            int i2 = this.controller.particles.size * this.rotationChannel.strideSize;
            int i3 = 0;
            int i4 = 0;
            while (i3 < i2) {
                a aVar = DynamicsModifier.TMP_V1;
                float[] fArr = this.accellerationChannel.data;
                aVar.t(fArr[i4], fArr[i4 + 1], fArr[i4 + 2]);
                aVar.n();
                a aVar2 = DynamicsModifier.TMP_V2;
                aVar2.u(aVar);
                aVar2.d(a.f1726e);
                aVar2.n();
                aVar2.d(aVar);
                aVar2.n();
                a aVar3 = DynamicsModifier.TMP_V3;
                aVar3.getClass();
                aVar3.t(aVar2.f1729a, aVar2.f1730b, aVar2.f1731c);
                aVar3.d(aVar);
                aVar3.n();
                n nVar = DynamicsModifier.TMP_Q;
                nVar.h(false, aVar3.f1729a, aVar2.f1729a, aVar.f1729a, aVar3.f1730b, aVar2.f1730b, aVar.f1730b, aVar3.f1731c, aVar2.f1731c, aVar.f1731c);
                ParallelArray.FloatChannel floatChannel = this.rotationChannel;
                float[] fArr2 = floatChannel.data;
                fArr2[i3] = nVar.f83a;
                fArr2[i3 + 1] = nVar.f84b;
                fArr2[i3 + 2] = nVar.f85c;
                fArr2[i3 + 3] = nVar.f86d;
                i3 += floatChannel.strideSize;
                i4 += this.accellerationChannel.strideSize;
            }
        }

        public FaceDirection(FaceDirection faceDirection) {
            super(faceDirection);
        }
    }

    public static class PolarAcceleration extends Angular {
        ParallelArray.FloatChannel directionalVelocityChannel;

        public PolarAcceleration() {
        }

        @Override // com.badlogic.gdx.graphics.g3d.particles.influencers.DynamicsModifier.Angular, com.badlogic.gdx.graphics.g3d.particles.influencers.DynamicsModifier.Strength, com.badlogic.gdx.graphics.g3d.particles.influencers.DynamicsModifier, com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
        public void allocateChannels() {
            super.allocateChannels();
            this.directionalVelocityChannel = (ParallelArray.FloatChannel) this.controller.particles.addChannel(ParticleChannels.Acceleration);
        }

        @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
        public void update() {
            int i2 = this.controller.particles.size * this.directionalVelocityChannel.strideSize;
            int i3 = 0;
            int i4 = 0;
            int i5 = 2;
            int i6 = 0;
            while (i3 < i2) {
                float f2 = this.lifeChannel.data[i5];
                float[] fArr = this.strengthChannel.data;
                float fB = a.a.b(this.strengthValue, f2, fArr[i6 + 1], fArr[i6]);
                float[] fArr2 = this.angularChannel.data;
                float fB2 = a.a.b(this.phiValue, f2, fArr2[i4 + 3], fArr2[i4 + 2]);
                float[] fArr3 = this.angularChannel.data;
                float fB3 = a.a.b(this.thetaValue, f2, fArr3[i4 + 1], fArr3[i4]);
                float fC = j.c(fB3);
                float fK = j.k(fB3);
                float fC2 = j.c(fB2);
                float fK2 = j.k(fB2);
                a aVar = DynamicsModifier.TMP_V3;
                aVar.t(fC * fK2, fC2, fK * fK2);
                aVar.n();
                aVar.s(fB);
                if (!this.isGlobal) {
                    Matrix4 matrix4 = this.controller.transform;
                    n nVar = DynamicsModifier.TMP_Q;
                    matrix4.a(nVar);
                    nVar.k(aVar);
                }
                ParallelArray.FloatChannel floatChannel = this.directionalVelocityChannel;
                float[] fArr4 = floatChannel.data;
                fArr4[i3] = fArr4[i3] + aVar.f1729a;
                int i7 = i3 + 1;
                fArr4[i7] = fArr4[i7] + aVar.f1730b;
                int i8 = i3 + 2;
                fArr4[i8] = fArr4[i8] + aVar.f1731c;
                i6 += this.strengthChannel.strideSize;
                i3 += floatChannel.strideSize;
                i4 += this.angularChannel.strideSize;
                i5 += this.lifeChannel.strideSize;
            }
        }

        public PolarAcceleration(PolarAcceleration polarAcceleration) {
            super(polarAcceleration);
        }

        @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
        public PolarAcceleration copy() {
            return new PolarAcceleration(this);
        }
    }

    public static class Rotational2D extends Strength {
        ParallelArray.FloatChannel rotationalVelocity2dChannel;

        public Rotational2D() {
        }

        @Override // com.badlogic.gdx.graphics.g3d.particles.influencers.DynamicsModifier.Strength, com.badlogic.gdx.graphics.g3d.particles.influencers.DynamicsModifier, com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
        public void allocateChannels() {
            super.allocateChannels();
            this.rotationalVelocity2dChannel = (ParallelArray.FloatChannel) this.controller.particles.addChannel(ParticleChannels.AngularVelocity2D);
        }

        @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
        public void update() {
            int i2 = this.controller.particles.size * this.rotationalVelocity2dChannel.strideSize;
            int i3 = 0;
            int i4 = 2;
            int i5 = 0;
            while (i3 < i2) {
                float[] fArr = this.rotationalVelocity2dChannel.data;
                float f2 = fArr[i3];
                float[] fArr2 = this.strengthChannel.data;
                fArr[i3] = (this.strengthValue.getScale(this.lifeChannel.data[i4]) * fArr2[i5 + 1]) + fArr2[i5] + f2;
                i5 += this.strengthChannel.strideSize;
                i3 += this.rotationalVelocity2dChannel.strideSize;
                i4 += this.lifeChannel.strideSize;
            }
        }

        public Rotational2D(Rotational2D rotational2D) {
            super(rotational2D);
        }

        @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
        public Rotational2D copy() {
            return new Rotational2D(this);
        }
    }

    public static class Rotational3D extends Angular {
        ParallelArray.FloatChannel rotationChannel;
        ParallelArray.FloatChannel rotationalForceChannel;

        public Rotational3D() {
        }

        @Override // com.badlogic.gdx.graphics.g3d.particles.influencers.DynamicsModifier.Angular, com.badlogic.gdx.graphics.g3d.particles.influencers.DynamicsModifier.Strength, com.badlogic.gdx.graphics.g3d.particles.influencers.DynamicsModifier, com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
        public void allocateChannels() {
            super.allocateChannels();
            this.rotationChannel = (ParallelArray.FloatChannel) this.controller.particles.addChannel(ParticleChannels.Rotation3D);
            this.rotationalForceChannel = (ParallelArray.FloatChannel) this.controller.particles.addChannel(ParticleChannels.AngularVelocity3D);
        }

        @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
        public void update() {
            int i2 = this.controller.particles.size * this.rotationalForceChannel.strideSize;
            int i3 = 0;
            int i4 = 0;
            int i5 = 2;
            int i6 = 0;
            while (i3 < i2) {
                float f2 = this.lifeChannel.data[i5];
                float[] fArr = this.strengthChannel.data;
                float fB = a.a.b(this.strengthValue, f2, fArr[i6 + 1], fArr[i6]);
                float[] fArr2 = this.angularChannel.data;
                float fB2 = a.a.b(this.phiValue, f2, fArr2[i4 + 3], fArr2[i4 + 2]);
                float[] fArr3 = this.angularChannel.data;
                float fB3 = a.a.b(this.thetaValue, f2, fArr3[i4 + 1], fArr3[i4]);
                float fC = j.c(fB3);
                float fK = j.k(fB3);
                float fC2 = j.c(fB2);
                float fK2 = j.k(fB2);
                a aVar = DynamicsModifier.TMP_V3;
                aVar.t(fC * fK2, fC2, fK * fK2);
                aVar.s(fB * 0.017453292f);
                ParallelArray.FloatChannel floatChannel = this.rotationalForceChannel;
                float[] fArr4 = floatChannel.data;
                fArr4[i3] = fArr4[i3] + aVar.f1729a;
                int i7 = i3 + 1;
                fArr4[i7] = fArr4[i7] + aVar.f1730b;
                int i8 = i3 + 2;
                fArr4[i8] = fArr4[i8] + aVar.f1731c;
                i6 += this.strengthChannel.strideSize;
                i3 += floatChannel.strideSize;
                i4 += this.angularChannel.strideSize;
                i5 += this.lifeChannel.strideSize;
            }
        }

        public Rotational3D(Rotational3D rotational3D) {
            super(rotational3D);
        }

        @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
        public Rotational3D copy() {
            return new Rotational3D(this);
        }
    }

    public static class TangentialAcceleration extends Angular {
        ParallelArray.FloatChannel directionalVelocityChannel;
        ParallelArray.FloatChannel positionChannel;

        public TangentialAcceleration() {
        }

        @Override // com.badlogic.gdx.graphics.g3d.particles.influencers.DynamicsModifier.Angular, com.badlogic.gdx.graphics.g3d.particles.influencers.DynamicsModifier.Strength, com.badlogic.gdx.graphics.g3d.particles.influencers.DynamicsModifier, com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
        public void allocateChannels() {
            super.allocateChannels();
            this.directionalVelocityChannel = (ParallelArray.FloatChannel) this.controller.particles.addChannel(ParticleChannels.Acceleration);
            this.positionChannel = (ParallelArray.FloatChannel) this.controller.particles.addChannel(ParticleChannels.Position);
        }

        @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
        public void update() {
            int i2 = this.controller.particles.size * this.directionalVelocityChannel.strideSize;
            int i3 = 0;
            int i4 = 0;
            int i5 = 0;
            int i6 = 2;
            int i7 = 0;
            while (i3 < i2) {
                float f2 = this.lifeChannel.data[i6];
                float[] fArr = this.strengthChannel.data;
                float fB = a.a.b(this.strengthValue, f2, fArr[i7 + 1], fArr[i7]);
                float[] fArr2 = this.angularChannel.data;
                float fB2 = a.a.b(this.phiValue, f2, fArr2[i4 + 3], fArr2[i4 + 2]);
                float[] fArr3 = this.angularChannel.data;
                float fB3 = a.a.b(this.thetaValue, f2, fArr3[i4 + 1], fArr3[i4]);
                float fC = j.c(fB3);
                float fK = j.k(fB3);
                float fC2 = j.c(fB2);
                float fK2 = j.k(fB2);
                a aVar = DynamicsModifier.TMP_V3;
                aVar.t(fC * fK2, fC2, fK * fK2);
                a aVar2 = DynamicsModifier.TMP_V1;
                float[] fArr4 = this.positionChannel.data;
                aVar2.t(fArr4[i5], fArr4[i5 + 1], fArr4[i5 + 2]);
                if (!this.isGlobal) {
                    Matrix4 matrix4 = this.controller.transform;
                    a aVar3 = DynamicsModifier.TMP_V2;
                    matrix4.c(aVar3);
                    aVar2.w(aVar3);
                    Matrix4 matrix42 = this.controller.transform;
                    n nVar = DynamicsModifier.TMP_Q;
                    matrix42.a(nVar);
                    nVar.k(aVar);
                }
                aVar.d(aVar2);
                aVar.n();
                aVar.s(fB);
                ParallelArray.FloatChannel floatChannel = this.directionalVelocityChannel;
                float[] fArr5 = floatChannel.data;
                fArr5[i3] = fArr5[i3] + aVar.f1729a;
                int i8 = i3 + 1;
                fArr5[i8] = fArr5[i8] + aVar.f1730b;
                int i9 = i3 + 2;
                fArr5[i9] = fArr5[i9] + aVar.f1731c;
                i7 += this.strengthChannel.strideSize;
                i3 += floatChannel.strideSize;
                i4 += this.angularChannel.strideSize;
                i6 += this.lifeChannel.strideSize;
                i5 += this.positionChannel.strideSize;
            }
        }

        public TangentialAcceleration(TangentialAcceleration tangentialAcceleration) {
            super(tangentialAcceleration);
        }

        @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
        public TangentialAcceleration copy() {
            return new TangentialAcceleration(this);
        }
    }

    public DynamicsModifier() {
        this.isGlobal = false;
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
    public void allocateChannels() {
        this.lifeChannel = (ParallelArray.FloatChannel) this.controller.particles.addChannel(ParticleChannels.Life);
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent, com.badlogic.gdx.utils.Json.b
    public void read(Json json, t tVar) {
        super.read(json, tVar);
        this.isGlobal = ((Boolean) json.readValue("isGlobal", Boolean.TYPE, tVar)).booleanValue();
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent, com.badlogic.gdx.utils.Json.b
    public void write(Json json) {
        super.write(json);
        json.writeValue("isGlobal", Boolean.valueOf(this.isGlobal));
    }

    public static abstract class Strength extends DynamicsModifier {
        protected ParallelArray.FloatChannel strengthChannel;
        public ScaledNumericValue strengthValue;

        public Strength() {
            this.strengthValue = new ScaledNumericValue();
        }

        @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
        public void activateParticles(int i2, int i3) {
            int i4 = this.strengthChannel.strideSize;
            int i5 = i2 * i4;
            int i6 = (i3 * i4) + i5;
            while (i5 < i6) {
                float fNewLowValue = this.strengthValue.newLowValue();
                float fNewHighValue = this.strengthValue.newHighValue();
                if (!this.strengthValue.isRelative()) {
                    fNewHighValue -= fNewLowValue;
                }
                ParallelArray.FloatChannel floatChannel = this.strengthChannel;
                float[] fArr = floatChannel.data;
                fArr[i5] = fNewLowValue;
                fArr[i5 + 1] = fNewHighValue;
                i5 += floatChannel.strideSize;
            }
        }

        @Override // com.badlogic.gdx.graphics.g3d.particles.influencers.DynamicsModifier, com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
        public void allocateChannels() {
            super.allocateChannels();
            ParallelArray.ChannelDescriptor channelDescriptor = ParticleChannels.Interpolation;
            channelDescriptor.id = this.controller.particleChannels.newId();
            this.strengthChannel = (ParallelArray.FloatChannel) this.controller.particles.addChannel(channelDescriptor);
        }

        @Override // com.badlogic.gdx.graphics.g3d.particles.influencers.DynamicsModifier, com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent, com.badlogic.gdx.utils.Json.b
        public void read(Json json, t tVar) {
            super.read(json, tVar);
            this.strengthValue = (ScaledNumericValue) json.readValue("strengthValue", ScaledNumericValue.class, tVar);
        }

        @Override // com.badlogic.gdx.graphics.g3d.particles.influencers.DynamicsModifier, com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent, com.badlogic.gdx.utils.Json.b
        public void write(Json json) {
            super.write(json);
            json.writeValue("strengthValue", this.strengthValue);
        }

        public Strength(Strength strength) {
            super(strength);
            ScaledNumericValue scaledNumericValue = new ScaledNumericValue();
            this.strengthValue = scaledNumericValue;
            scaledNumericValue.load(strength.strengthValue);
        }
    }

    public static abstract class Angular extends Strength {
        protected ParallelArray.FloatChannel angularChannel;
        public ScaledNumericValue phiValue;
        public ScaledNumericValue thetaValue;

        public Angular() {
            this.thetaValue = new ScaledNumericValue();
            this.phiValue = new ScaledNumericValue();
        }

        @Override // com.badlogic.gdx.graphics.g3d.particles.influencers.DynamicsModifier.Strength, com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
        public void activateParticles(int i2, int i3) {
            super.activateParticles(i2, i3);
            int i4 = this.angularChannel.strideSize;
            int i5 = i2 * i4;
            int i6 = (i3 * i4) + i5;
            while (i5 < i6) {
                float fNewLowValue = this.thetaValue.newLowValue();
                float fNewHighValue = this.thetaValue.newHighValue();
                if (!this.thetaValue.isRelative()) {
                    fNewHighValue -= fNewLowValue;
                }
                float[] fArr = this.angularChannel.data;
                fArr[i5] = fNewLowValue;
                fArr[i5 + 1] = fNewHighValue;
                float fNewLowValue2 = this.phiValue.newLowValue();
                float fNewHighValue2 = this.phiValue.newHighValue();
                if (!this.phiValue.isRelative()) {
                    fNewHighValue2 -= fNewLowValue2;
                }
                ParallelArray.FloatChannel floatChannel = this.angularChannel;
                float[] fArr2 = floatChannel.data;
                fArr2[i5 + 2] = fNewLowValue2;
                fArr2[i5 + 3] = fNewHighValue2;
                i5 += floatChannel.strideSize;
            }
        }

        @Override // com.badlogic.gdx.graphics.g3d.particles.influencers.DynamicsModifier.Strength, com.badlogic.gdx.graphics.g3d.particles.influencers.DynamicsModifier, com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
        public void allocateChannels() {
            super.allocateChannels();
            ParallelArray.ChannelDescriptor channelDescriptor = ParticleChannels.Interpolation4;
            channelDescriptor.id = this.controller.particleChannels.newId();
            this.angularChannel = (ParallelArray.FloatChannel) this.controller.particles.addChannel(channelDescriptor);
        }

        @Override // com.badlogic.gdx.graphics.g3d.particles.influencers.DynamicsModifier.Strength, com.badlogic.gdx.graphics.g3d.particles.influencers.DynamicsModifier, com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent, com.badlogic.gdx.utils.Json.b
        public void read(Json json, t tVar) {
            super.read(json, tVar);
            this.thetaValue = (ScaledNumericValue) json.readValue("thetaValue", ScaledNumericValue.class, tVar);
            this.phiValue = (ScaledNumericValue) json.readValue("phiValue", ScaledNumericValue.class, tVar);
        }

        @Override // com.badlogic.gdx.graphics.g3d.particles.influencers.DynamicsModifier.Strength, com.badlogic.gdx.graphics.g3d.particles.influencers.DynamicsModifier, com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent, com.badlogic.gdx.utils.Json.b
        public void write(Json json) {
            super.write(json);
            json.writeValue("thetaValue", this.thetaValue);
            json.writeValue("phiValue", this.phiValue);
        }

        public Angular(Angular angular) {
            super(angular);
            this.thetaValue = new ScaledNumericValue();
            this.phiValue = new ScaledNumericValue();
            this.thetaValue.load(angular.thetaValue);
            this.phiValue.load(angular.phiValue);
        }
    }

    public DynamicsModifier(DynamicsModifier dynamicsModifier) {
        this.isGlobal = false;
        this.isGlobal = dynamicsModifier.isGlobal;
    }
}
