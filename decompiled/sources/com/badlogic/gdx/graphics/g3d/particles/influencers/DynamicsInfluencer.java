package com.badlogic.gdx.graphics.g3d.particles.influencers;

import a0.j;
import a0.n;
import com.badlogic.gdx.graphics.g3d.particles.ParallelArray;
import com.badlogic.gdx.graphics.g3d.particles.ParticleChannels;
import com.badlogic.gdx.graphics.g3d.particles.ParticleController;
import com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.a;
import com.badlogic.gdx.utils.t;
import java.util.Arrays;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class DynamicsInfluencer extends Influencer {
    private ParallelArray.FloatChannel accellerationChannel;
    private ParallelArray.FloatChannel angularVelocityChannel;
    boolean has2dAngularVelocity;
    boolean has3dAngularVelocity;
    boolean hasAcceleration;
    private ParallelArray.FloatChannel positionChannel;
    private ParallelArray.FloatChannel previousPositionChannel;
    private ParallelArray.FloatChannel rotationChannel;
    public a<DynamicsModifier> velocities;

    public DynamicsInfluencer() {
        this.velocities = new a<>(true, 3, DynamicsModifier.class);
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
    public void activateParticles(int i2, int i3) {
        if (this.hasAcceleration) {
            int i4 = this.positionChannel.strideSize;
            int i5 = i2 * i4;
            int i6 = (i4 * i3) + i5;
            while (i5 < i6) {
                float[] fArr = this.previousPositionChannel.data;
                ParallelArray.FloatChannel floatChannel = this.positionChannel;
                float[] fArr2 = floatChannel.data;
                fArr[i5] = fArr2[i5];
                int i7 = i5 + 1;
                fArr[i7] = fArr2[i7];
                int i8 = i5 + 2;
                fArr[i8] = fArr2[i8];
                i5 += floatChannel.strideSize;
            }
        }
        if (this.has2dAngularVelocity) {
            int i9 = this.rotationChannel.strideSize;
            int i10 = i2 * i9;
            int i11 = (i9 * i3) + i10;
            while (i10 < i11) {
                ParallelArray.FloatChannel floatChannel2 = this.rotationChannel;
                float[] fArr3 = floatChannel2.data;
                fArr3[i10] = 1.0f;
                fArr3[i10 + 1] = 0.0f;
                i10 += floatChannel2.strideSize;
            }
        } else if (this.has3dAngularVelocity) {
            int i12 = this.rotationChannel.strideSize;
            int i13 = i2 * i12;
            int i14 = (i12 * i3) + i13;
            while (i13 < i14) {
                ParallelArray.FloatChannel floatChannel3 = this.rotationChannel;
                float[] fArr4 = floatChannel3.data;
                fArr4[i13] = 0.0f;
                fArr4[i13 + 1] = 0.0f;
                fArr4[i13 + 2] = 0.0f;
                fArr4[i13 + 3] = 1.0f;
                i13 += floatChannel3.strideSize;
            }
        }
        int i15 = 0;
        while (true) {
            a<DynamicsModifier> aVar = this.velocities;
            if (i15 >= aVar.f1750b) {
                return;
            }
            aVar.f1749a[i15].activateParticles(i2, i3);
            i15++;
        }
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
    public void allocateChannels() {
        int i2 = 0;
        while (true) {
            a<DynamicsModifier> aVar = this.velocities;
            if (i2 >= aVar.f1750b) {
                break;
            }
            aVar.f1749a[i2].allocateChannels();
            i2++;
        }
        ParallelArray.FloatChannel floatChannel = (ParallelArray.FloatChannel) this.controller.particles.getChannel(ParticleChannels.Acceleration);
        this.accellerationChannel = floatChannel;
        boolean z2 = floatChannel != null;
        this.hasAcceleration = z2;
        if (z2) {
            this.positionChannel = (ParallelArray.FloatChannel) this.controller.particles.addChannel(ParticleChannels.Position);
            this.previousPositionChannel = (ParallelArray.FloatChannel) this.controller.particles.addChannel(ParticleChannels.PreviousPosition);
        }
        ParallelArray.FloatChannel floatChannel2 = (ParallelArray.FloatChannel) this.controller.particles.getChannel(ParticleChannels.AngularVelocity2D);
        this.angularVelocityChannel = floatChannel2;
        boolean z3 = floatChannel2 != null;
        this.has2dAngularVelocity = z3;
        if (z3) {
            this.rotationChannel = (ParallelArray.FloatChannel) this.controller.particles.addChannel(ParticleChannels.Rotation2D);
            this.has3dAngularVelocity = false;
            return;
        }
        ParallelArray.FloatChannel floatChannel3 = (ParallelArray.FloatChannel) this.controller.particles.getChannel(ParticleChannels.AngularVelocity3D);
        this.angularVelocityChannel = floatChannel3;
        boolean z4 = floatChannel3 != null;
        this.has3dAngularVelocity = z4;
        if (z4) {
            this.rotationChannel = (ParallelArray.FloatChannel) this.controller.particles.addChannel(ParticleChannels.Rotation3D);
        }
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
    public void init() {
        int i2 = 0;
        while (true) {
            a<DynamicsModifier> aVar = this.velocities;
            if (i2 >= aVar.f1750b) {
                return;
            }
            aVar.f1749a[i2].init();
            i2++;
        }
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent, com.badlogic.gdx.utils.Json.b
    public void read(Json json, t tVar) {
        this.velocities.b((a) json.readValue("velocities", a.class, DynamicsModifier.class, tVar));
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
    public void set(ParticleController particleController) {
        super.set(particleController);
        int i2 = 0;
        while (true) {
            a<DynamicsModifier> aVar = this.velocities;
            if (i2 >= aVar.f1750b) {
                return;
            }
            aVar.f1749a[i2].set(particleController);
            i2++;
        }
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
    public void update() {
        float f2 = 0.0f;
        if (this.hasAcceleration) {
            ParallelArray.FloatChannel floatChannel = this.accellerationChannel;
            Arrays.fill(floatChannel.data, 0, this.controller.particles.size * floatChannel.strideSize, 0.0f);
        }
        if (this.has2dAngularVelocity || this.has3dAngularVelocity) {
            ParallelArray.FloatChannel floatChannel2 = this.angularVelocityChannel;
            Arrays.fill(floatChannel2.data, 0, this.controller.particles.size * floatChannel2.strideSize, 0.0f);
        }
        int i2 = 0;
        while (true) {
            a<DynamicsModifier> aVar = this.velocities;
            if (i2 >= aVar.f1750b) {
                break;
            }
            aVar.f1749a[i2].update();
            i2++;
        }
        if (this.hasAcceleration) {
            int i3 = 0;
            int i4 = 0;
            while (true) {
                ParticleController particleController = this.controller;
                if (i3 >= particleController.particles.size) {
                    break;
                }
                ParallelArray.FloatChannel floatChannel3 = this.positionChannel;
                float[] fArr = floatChannel3.data;
                float f3 = fArr[i4];
                int i5 = i4 + 1;
                float f4 = fArr[i5];
                int i6 = i4 + 2;
                float f5 = fArr[i6];
                float[] fArr2 = this.previousPositionChannel.data;
                float f6 = (f3 * 2.0f) - fArr2[i4];
                float[] fArr3 = this.accellerationChannel.data;
                float f7 = fArr3[i4];
                float f8 = particleController.deltaTimeSqr;
                fArr[i4] = (f7 * f8) + f6;
                fArr[i5] = (fArr3[i5] * f8) + ((f4 * 2.0f) - fArr2[i5]);
                fArr[i6] = (fArr3[i6] * f8) + ((2.0f * f5) - fArr2[i6]);
                fArr2[i4] = f3;
                fArr2[i5] = f4;
                fArr2[i6] = f5;
                i3++;
                i4 += floatChannel3.strideSize;
            }
        }
        if (!this.has2dAngularVelocity) {
            if (this.has3dAngularVelocity) {
                int i7 = 0;
                int i8 = 0;
                int i9 = 0;
                while (i8 < this.controller.particles.size) {
                    float[] fArr4 = this.angularVelocityChannel.data;
                    float f9 = fArr4[i9];
                    float f10 = fArr4[i9 + 1];
                    float f11 = fArr4[i9 + 2];
                    float[] fArr5 = this.rotationChannel.data;
                    float f12 = fArr5[i7];
                    int i10 = i7 + 1;
                    float f13 = fArr5[i10];
                    int i11 = i7 + 2;
                    float f14 = fArr5[i11];
                    int i12 = i7 + 3;
                    float f15 = fArr5[i12];
                    n nVar = ParticleControllerComponent.TMP_Q;
                    nVar.e(f9, f10, f11, f2);
                    float f16 = nVar.f86d;
                    float f17 = nVar.f83a;
                    float f18 = nVar.f84b;
                    float f19 = nVar.f85c;
                    float f20 = ((f18 * f14) + ((f17 * f15) + (f16 * f12))) - (f19 * f13);
                    int i13 = i8;
                    float f21 = ((f19 * f12) + ((f18 * f15) + (f16 * f13))) - (f17 * f14);
                    float f22 = ((f17 * f13) + ((f19 * f15) + (f16 * f14))) - (f18 * f12);
                    float f23 = (((f16 * f15) - (f17 * f12)) - (f18 * f13)) - (f19 * f14);
                    nVar.f83a = f20;
                    nVar.f84b = f21;
                    nVar.f85c = f22;
                    nVar.f86d = f23;
                    float f24 = this.controller.deltaTime * 0.5f;
                    nVar.f83a = (f20 * f24) + f12;
                    nVar.f84b = (f21 * f24) + f13;
                    nVar.f85c = (f22 * f24) + f14;
                    nVar.f86d = (f23 * f24) + f15;
                    nVar.d();
                    ParallelArray.FloatChannel floatChannel4 = this.rotationChannel;
                    float[] fArr6 = floatChannel4.data;
                    fArr6[i7] = nVar.f83a;
                    fArr6[i10] = nVar.f84b;
                    fArr6[i11] = nVar.f85c;
                    fArr6[i12] = nVar.f86d;
                    i8 = i13 + 1;
                    i7 += floatChannel4.strideSize;
                    i9 += this.angularVelocityChannel.strideSize;
                    f2 = 0.0f;
                }
                return;
            }
            return;
        }
        int i14 = 0;
        int i15 = 0;
        while (true) {
            ParticleController particleController2 = this.controller;
            if (i14 >= particleController2.particles.size) {
                return;
            }
            float f25 = this.angularVelocityChannel.data[i14] * particleController2.deltaTime;
            if (f25 != 0.0f) {
                float fC = j.c(f25);
                float fK = j.k(f25);
                float[] fArr7 = this.rotationChannel.data;
                float f26 = fArr7[i15];
                int i16 = i15 + 1;
                float f27 = fArr7[i16];
                fArr7[i15] = (f26 * fC) - (f27 * fK);
                fArr7[i16] = (f26 * fK) + (f27 * fC);
            }
            i14++;
            i15 += this.rotationChannel.strideSize;
        }
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent, com.badlogic.gdx.utils.Json.b
    public void write(Json json) {
        json.writeValue("velocities", this.velocities, a.class, DynamicsModifier.class);
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
    public DynamicsInfluencer copy() {
        return new DynamicsInfluencer(this);
    }

    public DynamicsInfluencer(DynamicsModifier... dynamicsModifierArr) {
        this.velocities = new a<>(true, dynamicsModifierArr.length, DynamicsModifier.class);
        for (DynamicsModifier dynamicsModifier : dynamicsModifierArr) {
            this.velocities.a((DynamicsModifier) dynamicsModifier.copy());
        }
    }

    public DynamicsInfluencer(DynamicsInfluencer dynamicsInfluencer) {
        this((DynamicsModifier[]) dynamicsInfluencer.velocities.u(DynamicsModifier.class));
    }
}
