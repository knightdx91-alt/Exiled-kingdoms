package com.badlogic.gdx.graphics.g3d.particles.renderers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.BlendingAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.particles.ParallelArray;
import com.badlogic.gdx.graphics.g3d.particles.ParticleChannels;
import com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent;
import com.badlogic.gdx.graphics.g3d.particles.batches.ModelInstanceParticleBatch;
import com.badlogic.gdx.graphics.g3d.particles.batches.ParticleBatch;
import com.badlogic.gdx.math.Matrix4;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class ModelInstanceRenderer extends ParticleControllerRenderer<ModelInstanceControllerRenderData, ModelInstanceParticleBatch> {
    private boolean hasColor;
    private boolean hasRotation;
    private boolean hasScale;

    public ModelInstanceRenderer() {
        super(new ModelInstanceControllerRenderData());
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
    public void allocateChannels() {
        ((ModelInstanceControllerRenderData) this.renderData).positionChannel = (ParallelArray.FloatChannel) this.controller.particles.addChannel(ParticleChannels.Position);
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
    public ParticleControllerComponent copy() {
        return new ModelInstanceRenderer((ModelInstanceParticleBatch) this.batch);
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
    public void init() {
        ((ModelInstanceControllerRenderData) this.renderData).modelInstanceChannel = (ParallelArray.ObjectChannel) this.controller.particles.getChannel(ParticleChannels.ModelInstance);
        ((ModelInstanceControllerRenderData) this.renderData).colorChannel = (ParallelArray.FloatChannel) this.controller.particles.getChannel(ParticleChannels.Color);
        ((ModelInstanceControllerRenderData) this.renderData).scaleChannel = (ParallelArray.FloatChannel) this.controller.particles.getChannel(ParticleChannels.Scale);
        ((ModelInstanceControllerRenderData) this.renderData).rotationChannel = (ParallelArray.FloatChannel) this.controller.particles.getChannel(ParticleChannels.Rotation3D);
        D d2 = this.renderData;
        this.hasColor = ((ModelInstanceControllerRenderData) d2).colorChannel != null;
        this.hasScale = ((ModelInstanceControllerRenderData) d2).scaleChannel != null;
        this.hasRotation = ((ModelInstanceControllerRenderData) d2).rotationChannel != null;
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.renderers.ParticleControllerRenderer
    public boolean isCompatible(ParticleBatch<?> particleBatch) {
        return particleBatch instanceof ModelInstanceParticleBatch;
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.renderers.ParticleControllerRenderer, com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
    public void update() {
        float f2;
        float f3;
        float f4;
        float f5;
        int i2 = this.controller.particles.size;
        int i3 = 0;
        int i4 = 0;
        while (i3 < i2) {
            ModelInstanceControllerRenderData modelInstanceControllerRenderData = (ModelInstanceControllerRenderData) this.renderData;
            ModelInstance modelInstance = modelInstanceControllerRenderData.modelInstanceChannel.data[i3];
            float f6 = this.hasScale ? modelInstanceControllerRenderData.scaleChannel.data[i3] : 1.0f;
            if (this.hasRotation) {
                ParallelArray.FloatChannel floatChannel = modelInstanceControllerRenderData.rotationChannel;
                int i5 = floatChannel.strideSize * i3;
                float[] fArr = floatChannel.data;
                float f7 = fArr[i5];
                float f8 = fArr[i5 + 1];
                float f9 = fArr[i5 + 2];
                f5 = fArr[i5 + 3];
                f2 = f7;
                f3 = f8;
                f4 = f9;
            } else {
                f2 = 0.0f;
                f3 = 0.0f;
                f4 = 0.0f;
                f5 = 1.0f;
            }
            Matrix4 matrix4 = modelInstance.transform;
            float[] fArr2 = modelInstanceControllerRenderData.positionChannel.data;
            matrix4.m(fArr2[i4], fArr2[i4 + 1], fArr2[i4 + 2], f2, f3, f4, f5, f6, f6, f6);
            if (this.hasColor) {
                int i6 = ((ModelInstanceControllerRenderData) this.renderData).colorChannel.strideSize * i3;
                ColorAttribute colorAttribute = (ColorAttribute) modelInstance.materials.get(0).get(ColorAttribute.Diffuse);
                BlendingAttribute blendingAttribute = (BlendingAttribute) modelInstance.materials.get(0).get(BlendingAttribute.Type);
                Color color = colorAttribute.color;
                float[] fArr3 = ((ModelInstanceControllerRenderData) this.renderData).colorChannel.data;
                color.f1680r = fArr3[i6];
                color.f1679g = fArr3[i6 + 1];
                color.f1678b = fArr3[i6 + 2];
                if (blendingAttribute != null) {
                    blendingAttribute.opacity = fArr3[i6 + 3];
                }
            }
            i3++;
            i4 += ((ModelInstanceControllerRenderData) this.renderData).positionChannel.strideSize;
        }
        super.update();
    }

    public ModelInstanceRenderer(ModelInstanceParticleBatch modelInstanceParticleBatch) {
        this();
        setBatch(modelInstanceParticleBatch);
    }
}
