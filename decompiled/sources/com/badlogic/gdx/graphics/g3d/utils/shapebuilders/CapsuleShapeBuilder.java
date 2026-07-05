package com.badlogic.gdx.graphics.g3d.utils.shapebuilders;

import com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.utils.m;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class CapsuleShapeBuilder extends BaseShapeBuilder {
    public static void build(MeshPartBuilder meshPartBuilder, float f2, float f3, int i2) {
        float f4 = 2.0f * f2;
        if (f3 < f4) {
            throw new m("Height must be at least twice the radius");
        }
        float f5 = f3 - f4;
        CylinderShapeBuilder.build(meshPartBuilder, f4, f5, f4, i2, 0.0f, 360.0f, false);
        Matrix4 matrix4 = BaseShapeBuilder.matTmp1;
        matrix4.t(0.0f, 0.5f * f5, 0.0f);
        SphereShapeBuilder.build(meshPartBuilder, matrix4, f4, f4, f4, i2, i2, 0.0f, 360.0f, 0.0f, 90.0f);
        matrix4.t(0.0f, f5 * (-0.5f), 0.0f);
        SphereShapeBuilder.build(meshPartBuilder, matrix4, f4, f4, f4, i2, i2, 0.0f, 360.0f, 90.0f, 180.0f);
    }
}
