package com.badlogic.gdx.graphics.g3d.utils.shapebuilders;

import a0.f;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder;
import com.badlogic.gdx.math.a;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class FrustumShapeBuilder extends BaseShapeBuilder {
    public static void build(MeshPartBuilder meshPartBuilder, Camera camera) {
        build(meshPartBuilder, camera, BaseShapeBuilder.tmpColor0.set(1.0f, 0.66f, 0.0f, 1.0f), BaseShapeBuilder.tmpColor1.set(1.0f, 0.0f, 0.0f, 1.0f), BaseShapeBuilder.tmpColor2.set(0.0f, 0.66f, 1.0f, 1.0f), BaseShapeBuilder.tmpColor3.set(1.0f, 1.0f, 1.0f, 1.0f), BaseShapeBuilder.tmpColor4.set(0.2f, 0.2f, 0.2f, 1.0f));
    }

    private static a centerPoint(a aVar, a aVar2, a aVar3) {
        a aVar4 = BaseShapeBuilder.tmpV0;
        aVar4.u(aVar2);
        aVar4.w(aVar);
        aVar4.s(0.5f);
        a aVar5 = BaseShapeBuilder.tmpV1;
        aVar5.getClass();
        aVar5.t(aVar.f1729a, aVar.f1730b, aVar.f1731c);
        aVar5.b(aVar4);
        aVar4.u(aVar3);
        aVar4.v(aVar2.f1729a, aVar2.f1730b, aVar2.f1731c);
        aVar4.s(0.5f);
        aVar5.b(aVar4);
        return aVar5;
    }

    private static a middlePoint(a aVar, a aVar2) {
        a aVar3 = BaseShapeBuilder.tmpV0;
        aVar3.u(aVar2);
        aVar3.w(aVar);
        aVar3.s(0.5f);
        a aVar4 = BaseShapeBuilder.tmpV1;
        aVar4.getClass();
        aVar4.t(aVar.f1729a, aVar.f1730b, aVar.f1731c);
        aVar4.b(aVar3);
        return aVar4;
    }

    public static void build(MeshPartBuilder meshPartBuilder, Camera camera, Color color, Color color2, Color color3, Color color4, Color color5) {
        f fVar = camera.frustum;
        a[] aVarArr = fVar.f49b;
        build(meshPartBuilder, fVar, color, color5);
        meshPartBuilder.line(aVarArr[0], color2, camera.position, color2);
        meshPartBuilder.line(aVarArr[1], color2, camera.position, color2);
        meshPartBuilder.line(aVarArr[2], color2, camera.position, color2);
        meshPartBuilder.line(aVarArr[3], color2, camera.position, color2);
        meshPartBuilder.line(camera.position, color4, centerPoint(aVarArr[4], aVarArr[5], aVarArr[6]), color4);
        a aVar = BaseShapeBuilder.tmpV0;
        aVar.u(aVarArr[1]);
        aVar.w(aVarArr[0]);
        aVar.s(0.5f);
        float fH = aVar.h();
        a aVarCenterPoint = centerPoint(aVarArr[0], aVarArr[1], aVarArr[2]);
        aVar.u(camera.up);
        aVar.s(fH * 2.0f);
        aVarCenterPoint.b(aVar);
        meshPartBuilder.line(aVarCenterPoint, color3, aVarArr[2], color3);
        meshPartBuilder.line(aVarArr[2], color3, aVarArr[3], color3);
        meshPartBuilder.line(aVarArr[3], color3, aVarCenterPoint, color3);
    }

    public static void build(MeshPartBuilder meshPartBuilder, f fVar, Color color, Color color2) {
        a[] aVarArr = fVar.f49b;
        meshPartBuilder.line(aVarArr[0], color, aVarArr[1], color);
        meshPartBuilder.line(aVarArr[1], color, aVarArr[2], color);
        meshPartBuilder.line(aVarArr[2], color, aVarArr[3], color);
        meshPartBuilder.line(aVarArr[3], color, aVarArr[0], color);
        meshPartBuilder.line(aVarArr[4], color, aVarArr[5], color);
        meshPartBuilder.line(aVarArr[5], color, aVarArr[6], color);
        meshPartBuilder.line(aVarArr[6], color, aVarArr[7], color);
        meshPartBuilder.line(aVarArr[7], color, aVarArr[4], color);
        meshPartBuilder.line(aVarArr[0], color, aVarArr[4], color);
        meshPartBuilder.line(aVarArr[1], color, aVarArr[5], color);
        meshPartBuilder.line(aVarArr[2], color, aVarArr[6], color);
        meshPartBuilder.line(aVarArr[3], color, aVarArr[7], color);
        meshPartBuilder.line(middlePoint(aVarArr[1], aVarArr[0]), color2, middlePoint(aVarArr[3], aVarArr[2]), color2);
        meshPartBuilder.line(middlePoint(aVarArr[2], aVarArr[1]), color2, middlePoint(aVarArr[3], aVarArr[0]), color2);
        meshPartBuilder.line(middlePoint(aVarArr[5], aVarArr[4]), color2, middlePoint(aVarArr[7], aVarArr[6]), color2);
        meshPartBuilder.line(middlePoint(aVarArr[6], aVarArr[5]), color2, middlePoint(aVarArr[7], aVarArr[4]), color2);
    }
}
