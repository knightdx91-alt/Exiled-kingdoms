package com.badlogic.gdx.graphics.g3d.particles;

import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.particles.ParallelArray;
import java.util.Arrays;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class ParticleChannels {
    public static final ParallelArray.ChannelDescriptor Acceleration;
    public static final int AlphaOffset = 3;
    public static final ParallelArray.ChannelDescriptor AngularVelocity2D;
    public static final ParallelArray.ChannelDescriptor AngularVelocity3D;
    public static final int BlueOffset = 2;
    public static final ParallelArray.ChannelDescriptor Color;
    public static final int CosineOffset = 0;
    public static final int CurrentLifeOffset = 0;
    public static final int GreenOffset = 1;
    public static final int HalfHeightOffset = 5;
    public static final int HalfWidthOffset = 4;
    public static final ParallelArray.ChannelDescriptor Interpolation;
    public static final ParallelArray.ChannelDescriptor Interpolation4;
    public static final ParallelArray.ChannelDescriptor Interpolation6;
    public static final int InterpolationDiffOffset = 1;
    public static final int InterpolationStartOffset = 0;
    public static final ParallelArray.ChannelDescriptor Life;
    public static final int LifePercentOffset = 2;
    public static final ParallelArray.ChannelDescriptor ModelInstance;
    public static final ParallelArray.ChannelDescriptor ParticleController;
    public static final ParallelArray.ChannelDescriptor Position;
    public static final ParallelArray.ChannelDescriptor PreviousPosition;
    public static final int RedOffset = 0;
    public static final ParallelArray.ChannelDescriptor Rotation2D;
    public static final ParallelArray.ChannelDescriptor Rotation3D;
    public static final ParallelArray.ChannelDescriptor Scale;
    public static final int SineOffset = 1;
    public static final ParallelArray.ChannelDescriptor TextureRegion;
    public static final int TotalLifeOffset = 1;
    public static final int U2Offset = 2;
    public static final int UOffset = 0;
    public static final int V2Offset = 3;
    public static final int VOffset = 1;
    public static final int VelocityPhiDiffOffset = 3;
    public static final int VelocityPhiStartOffset = 2;
    public static final int VelocityStrengthDiffOffset = 1;
    public static final int VelocityStrengthStartOffset = 0;
    public static final int VelocityThetaDiffOffset = 1;
    public static final int VelocityThetaStartOffset = 0;
    public static final int WOffset = 3;
    public static final int XOffset = 0;
    public static final int YOffset = 1;
    public static final int ZOffset = 2;
    private static int currentGlobalId;
    private int currentId;

    public static class ColorInitializer implements ParallelArray.ChannelInitializer<ParallelArray.FloatChannel> {
        private static ColorInitializer instance;

        public static ColorInitializer get() {
            if (instance == null) {
                instance = new ColorInitializer();
            }
            return instance;
        }

        @Override // com.badlogic.gdx.graphics.g3d.particles.ParallelArray.ChannelInitializer
        public void init(ParallelArray.FloatChannel floatChannel) {
            float[] fArr = floatChannel.data;
            Arrays.fill(fArr, 0, fArr.length, 1.0f);
        }
    }

    public static class Rotation2dInitializer implements ParallelArray.ChannelInitializer<ParallelArray.FloatChannel> {
        private static Rotation2dInitializer instance;

        public static Rotation2dInitializer get() {
            if (instance == null) {
                instance = new Rotation2dInitializer();
            }
            return instance;
        }

        @Override // com.badlogic.gdx.graphics.g3d.particles.ParallelArray.ChannelInitializer
        public void init(ParallelArray.FloatChannel floatChannel) {
            int length = floatChannel.data.length;
            int i2 = 0;
            while (i2 < length) {
                float[] fArr = floatChannel.data;
                fArr[i2] = 1.0f;
                fArr[i2 + 1] = 0.0f;
                i2 += floatChannel.strideSize;
            }
        }
    }

    public static class Rotation3dInitializer implements ParallelArray.ChannelInitializer<ParallelArray.FloatChannel> {
        private static Rotation3dInitializer instance;

        public static Rotation3dInitializer get() {
            if (instance == null) {
                instance = new Rotation3dInitializer();
            }
            return instance;
        }

        @Override // com.badlogic.gdx.graphics.g3d.particles.ParallelArray.ChannelInitializer
        public void init(ParallelArray.FloatChannel floatChannel) {
            int length = floatChannel.data.length;
            int i2 = 0;
            while (i2 < length) {
                float[] fArr = floatChannel.data;
                fArr[i2 + 2] = 0.0f;
                fArr[i2 + 1] = 0.0f;
                fArr[i2] = 0.0f;
                fArr[i2 + 3] = 1.0f;
                i2 += floatChannel.strideSize;
            }
        }
    }

    public static class ScaleInitializer implements ParallelArray.ChannelInitializer<ParallelArray.FloatChannel> {
        private static ScaleInitializer instance;

        public static ScaleInitializer get() {
            if (instance == null) {
                instance = new ScaleInitializer();
            }
            return instance;
        }

        @Override // com.badlogic.gdx.graphics.g3d.particles.ParallelArray.ChannelInitializer
        public void init(ParallelArray.FloatChannel floatChannel) {
            float[] fArr = floatChannel.data;
            Arrays.fill(fArr, 0, fArr.length, 1.0f);
        }
    }

    public static class TextureRegionInitializer implements ParallelArray.ChannelInitializer<ParallelArray.FloatChannel> {
        private static TextureRegionInitializer instance;

        public static TextureRegionInitializer get() {
            if (instance == null) {
                instance = new TextureRegionInitializer();
            }
            return instance;
        }

        @Override // com.badlogic.gdx.graphics.g3d.particles.ParallelArray.ChannelInitializer
        public void init(ParallelArray.FloatChannel floatChannel) {
            int length = floatChannel.data.length;
            int i2 = 0;
            while (i2 < length) {
                float[] fArr = floatChannel.data;
                fArr[i2] = 0.0f;
                fArr[i2 + 1] = 0.0f;
                fArr[i2 + 2] = 1.0f;
                fArr[i2 + 3] = 1.0f;
                fArr[i2 + 4] = 0.5f;
                fArr[i2 + 5] = 0.5f;
                i2 += floatChannel.strideSize;
            }
        }
    }

    static {
        int iNewGlobalId = newGlobalId();
        Class cls = Float.TYPE;
        Life = new ParallelArray.ChannelDescriptor(iNewGlobalId, cls, 3);
        Position = new ParallelArray.ChannelDescriptor(newGlobalId(), cls, 3);
        PreviousPosition = new ParallelArray.ChannelDescriptor(newGlobalId(), cls, 3);
        Color = new ParallelArray.ChannelDescriptor(newGlobalId(), cls, 4);
        TextureRegion = new ParallelArray.ChannelDescriptor(newGlobalId(), cls, 6);
        Rotation2D = new ParallelArray.ChannelDescriptor(newGlobalId(), cls, 2);
        Rotation3D = new ParallelArray.ChannelDescriptor(newGlobalId(), cls, 4);
        Scale = new ParallelArray.ChannelDescriptor(newGlobalId(), cls, 1);
        ModelInstance = new ParallelArray.ChannelDescriptor(newGlobalId(), ModelInstance.class, 1);
        ParticleController = new ParallelArray.ChannelDescriptor(newGlobalId(), ParticleController.class, 1);
        Acceleration = new ParallelArray.ChannelDescriptor(newGlobalId(), cls, 3);
        AngularVelocity2D = new ParallelArray.ChannelDescriptor(newGlobalId(), cls, 1);
        AngularVelocity3D = new ParallelArray.ChannelDescriptor(newGlobalId(), cls, 3);
        Interpolation = new ParallelArray.ChannelDescriptor(-1, cls, 2);
        Interpolation4 = new ParallelArray.ChannelDescriptor(-1, cls, 4);
        Interpolation6 = new ParallelArray.ChannelDescriptor(-1, cls, 6);
    }

    public ParticleChannels() {
        resetIds();
    }

    public static int newGlobalId() {
        int i2 = currentGlobalId;
        currentGlobalId = i2 + 1;
        return i2;
    }

    public int newId() {
        int i2 = this.currentId;
        this.currentId = i2 + 1;
        return i2;
    }

    protected void resetIds() {
        this.currentId = currentGlobalId;
    }
}
