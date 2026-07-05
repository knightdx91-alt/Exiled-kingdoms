package com.badlogic.gdx.graphics.g3d.particles;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g3d.particles.ParallelArray;
import com.badlogic.gdx.graphics.g3d.particles.renderers.ParticleControllerRenderData;
import com.badlogic.gdx.math.a;
import com.badlogic.gdx.utils.a;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public abstract class ParticleSorter {
    static final a TMP_V1 = new a();
    protected Camera camera;

    public static class Distance extends ParticleSorter {
        private int currentSize = 0;
        private float[] distances;
        private int[] particleIndices;
        private int[] particleOffsets;

        @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleSorter
        public void ensureCapacity(int i2) {
            if (this.currentSize < i2) {
                this.distances = new float[i2];
                this.particleIndices = new int[i2];
                this.particleOffsets = new int[i2];
                this.currentSize = i2;
            }
        }

        public void qsort(int i2, int i3) {
            if (i2 < i3) {
                if (i3 - i2 <= 8) {
                    for (int i4 = i2; i4 <= i3; i4++) {
                        for (int i5 = i4; i5 > i2; i5--) {
                            float[] fArr = this.distances;
                            int i6 = i5 - 1;
                            float f2 = fArr[i6];
                            float f3 = fArr[i5];
                            if (f2 > f3) {
                                fArr[i5] = f2;
                                fArr[i6] = f3;
                                int[] iArr = this.particleIndices;
                                int i7 = iArr[i5];
                                iArr[i5] = iArr[i6];
                                iArr[i6] = i7;
                            }
                        }
                    }
                    return;
                }
                float f4 = this.distances[i2];
                int i8 = i2 + 1;
                int i9 = this.particleIndices[i2];
                int i10 = i8;
                while (i8 <= i3) {
                    float[] fArr2 = this.distances;
                    float f5 = fArr2[i8];
                    if (f4 > f5) {
                        if (i8 > i10) {
                            fArr2[i8] = fArr2[i10];
                            fArr2[i10] = f5;
                            int[] iArr2 = this.particleIndices;
                            int i11 = iArr2[i8];
                            iArr2[i8] = iArr2[i10];
                            iArr2[i10] = i11;
                        }
                        i10++;
                    }
                    i8++;
                }
                float[] fArr3 = this.distances;
                int i12 = i10 - 1;
                fArr3[i2] = fArr3[i12];
                fArr3[i12] = f4;
                int[] iArr3 = this.particleIndices;
                iArr3[i2] = iArr3[i12];
                iArr3[i12] = i9;
                qsort(i2, i10 - 2);
                qsort(i10, i3);
            }
        }

        @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleSorter
        public <T extends ParticleControllerRenderData> int[] sort(com.badlogic.gdx.utils.a<T> aVar) {
            float[] fArr = this.camera.view.f1724a;
            float f2 = fArr[2];
            float f3 = fArr[6];
            float f4 = fArr[10];
            a.b<T> it = aVar.iterator();
            int i2 = 0;
            int i3 = 0;
            while (it.hasNext()) {
                T next = it.next();
                int i4 = next.controller.particles.size + i3;
                int i5 = 0;
                while (i3 < i4) {
                    float[] fArr2 = this.distances;
                    ParallelArray.FloatChannel floatChannel = next.positionChannel;
                    float[] fArr3 = floatChannel.data;
                    fArr2[i3] = (fArr3[i5 + 2] * f4) + (fArr3[i5 + 1] * f3) + (fArr3[i5] * f2);
                    this.particleIndices[i3] = i3;
                    i3++;
                    i5 += floatChannel.strideSize;
                }
                i2 += next.controller.particles.size;
            }
            qsort(0, i2 - 1);
            for (int i6 = 0; i6 < i2; i6++) {
                this.particleOffsets[this.particleIndices[i6]] = i6;
            }
            return this.particleOffsets;
        }
    }

    public static class None extends ParticleSorter {
        int currentCapacity = 0;
        int[] indices;

        @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleSorter
        public void ensureCapacity(int i2) {
            if (this.currentCapacity < i2) {
                this.indices = new int[i2];
                for (int i3 = 0; i3 < i2; i3++) {
                    this.indices[i3] = i3;
                }
                this.currentCapacity = i2;
            }
        }

        @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleSorter
        public <T extends ParticleControllerRenderData> int[] sort(com.badlogic.gdx.utils.a<T> aVar) {
            return this.indices;
        }
    }

    public void ensureCapacity(int i2) {
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    public abstract <T extends ParticleControllerRenderData> int[] sort(com.badlogic.gdx.utils.a<T> aVar);
}
