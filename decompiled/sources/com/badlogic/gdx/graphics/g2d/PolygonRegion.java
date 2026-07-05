package com.badlogic.gdx.graphics.g2d;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class PolygonRegion {
    final TextureRegion region;
    final float[] textureCoords;
    final short[] triangles;
    final float[] vertices;

    public PolygonRegion(TextureRegion textureRegion, float[] fArr, short[] sArr) {
        this.region = textureRegion;
        this.vertices = fArr;
        this.triangles = sArr;
        float[] fArr2 = new float[fArr.length];
        this.textureCoords = fArr2;
        float f2 = textureRegion.f1698u;
        float f3 = textureRegion.f1699v;
        float f4 = textureRegion.u2 - f2;
        float f5 = textureRegion.v2 - f3;
        int i2 = textureRegion.regionWidth;
        int i3 = textureRegion.regionHeight;
        int length = fArr.length;
        for (int i4 = 0; i4 < length; i4 += 2) {
            fArr2[i4] = ((fArr[i4] / i2) * f4) + f2;
            int i5 = i4 + 1;
            fArr2[i5] = ((1.0f - (fArr[i5] / i3)) * f5) + f3;
        }
    }

    public TextureRegion getRegion() {
        return this.region;
    }

    public float[] getTextureCoords() {
        return this.textureCoords;
    }

    public short[] getTriangles() {
        return this.triangles;
    }

    public float[] getVertices() {
        return this.vertices;
    }
}
