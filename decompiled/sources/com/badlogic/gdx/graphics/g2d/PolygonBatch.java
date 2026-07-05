package com.badlogic.gdx.graphics.g2d;

import com.badlogic.gdx.graphics.Texture;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public interface PolygonBatch extends Batch {
    @Override // com.badlogic.gdx.graphics.g2d.Batch, com.badlogic.gdx.utils.i
    /* synthetic */ void dispose();

    void draw(Texture texture, float[] fArr, int i2, int i3, short[] sArr, int i4, int i5);

    void draw(PolygonRegion polygonRegion, float f2, float f3);

    void draw(PolygonRegion polygonRegion, float f2, float f3, float f4, float f5);

    void draw(PolygonRegion polygonRegion, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10);
}
