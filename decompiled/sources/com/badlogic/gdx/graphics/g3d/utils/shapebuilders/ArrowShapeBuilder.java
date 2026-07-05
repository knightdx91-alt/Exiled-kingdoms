package com.badlogic.gdx.graphics.g3d.utils.shapebuilders;

import com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.a;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class ArrowShapeBuilder extends BaseShapeBuilder {
    public static void build(MeshPartBuilder meshPartBuilder, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, int i2) {
        a aVarObtainV3 = BaseShapeBuilder.obtainV3();
        aVarObtainV3.t(f2, f3, f4);
        a aVarObtainV32 = BaseShapeBuilder.obtainV3();
        aVarObtainV32.t(f5, f6, f7);
        float fE = aVarObtainV3.e(aVarObtainV32);
        float f10 = fE * f8;
        float fSqrt = ((float) (Math.sqrt(0.3333333432674408d) * ((double) f10))) * 2.0f;
        float f11 = fE - f10;
        float f12 = fSqrt * f9;
        a aVarObtainV33 = BaseShapeBuilder.obtainV3();
        aVarObtainV33.getClass();
        aVarObtainV33.t(aVarObtainV32.f1729a, aVarObtainV32.f1730b, aVarObtainV32.f1731c);
        aVarObtainV33.v(aVarObtainV3.f1729a, aVarObtainV3.f1730b, aVarObtainV3.f1731c);
        aVarObtainV33.n();
        a aVarObtainV34 = BaseShapeBuilder.obtainV3();
        aVarObtainV34.getClass();
        aVarObtainV34.t(aVarObtainV33.f1729a, aVarObtainV33.f1730b, aVarObtainV33.f1731c);
        aVarObtainV34.d(a.f1727f);
        if (aVarObtainV34.g()) {
            aVarObtainV34.u(a.f1725d);
        }
        aVarObtainV34.d(aVarObtainV33);
        aVarObtainV34.n();
        a aVarObtainV35 = BaseShapeBuilder.obtainV3();
        aVarObtainV35.getClass();
        aVarObtainV35.t(aVarObtainV33.f1729a, aVarObtainV33.f1730b, aVarObtainV33.f1731c);
        aVarObtainV35.d(aVarObtainV34);
        aVarObtainV35.n();
        a aVarObtainV36 = BaseShapeBuilder.obtainV3();
        aVarObtainV36.getClass();
        aVarObtainV36.t(aVarObtainV32.f1729a, aVarObtainV32.f1730b, aVarObtainV32.f1731c);
        aVarObtainV36.v(aVarObtainV3.f1729a, aVarObtainV3.f1730b, aVarObtainV3.f1731c);
        aVarObtainV36.n();
        Matrix4 vertexTransform = meshPartBuilder.getVertexTransform(BaseShapeBuilder.obtainM4());
        Matrix4 matrix4ObtainM4 = BaseShapeBuilder.obtainM4();
        float[] fArr = matrix4ObtainM4.f1724a;
        fArr[0] = aVarObtainV35.f1729a;
        fArr[4] = aVarObtainV33.f1729a;
        fArr[8] = aVarObtainV34.f1729a;
        fArr[1] = aVarObtainV35.f1730b;
        fArr[5] = aVarObtainV33.f1730b;
        fArr[9] = aVarObtainV34.f1730b;
        fArr[2] = aVarObtainV35.f1731c;
        fArr[6] = aVarObtainV33.f1731c;
        fArr[10] = aVarObtainV34.f1731c;
        Matrix4 matrix4ObtainM42 = BaseShapeBuilder.obtainM4();
        a aVarObtainV37 = BaseShapeBuilder.obtainV3();
        aVarObtainV37.getClass();
        aVarObtainV37.t(aVarObtainV36.f1729a, aVarObtainV36.f1730b, aVarObtainV36.f1731c);
        aVarObtainV37.s(f11 / 2.0f);
        aVarObtainV37.a(f2, f3, f4);
        matrix4ObtainM4.u(aVarObtainV37);
        matrix4ObtainM42.getClass();
        float[] fArr2 = matrix4ObtainM42.f1724a;
        int length = fArr2.length;
        float[] fArr3 = matrix4ObtainM4.f1724a;
        System.arraycopy(fArr3, 0, fArr2, 0, length);
        matrix4ObtainM42.g(vertexTransform);
        meshPartBuilder.setVertexTransform(matrix4ObtainM42);
        CylinderShapeBuilder.build(meshPartBuilder, f12, f11, f12, i2);
        a aVarObtainV38 = BaseShapeBuilder.obtainV3();
        aVarObtainV38.getClass();
        aVarObtainV38.t(aVarObtainV36.f1729a, aVarObtainV36.f1730b, aVarObtainV36.f1731c);
        aVarObtainV38.s(f11);
        aVarObtainV38.a(f2, f3, f4);
        matrix4ObtainM4.u(aVarObtainV38);
        System.arraycopy(fArr3, 0, fArr2, 0, fArr2.length);
        Matrix4.h(fArr2, vertexTransform.f1724a);
        meshPartBuilder.setVertexTransform(matrix4ObtainM42);
        ConeShapeBuilder.build(meshPartBuilder, fSqrt, f10, fSqrt, i2);
        meshPartBuilder.setVertexTransform(vertexTransform);
        BaseShapeBuilder.freeAll();
    }
}
