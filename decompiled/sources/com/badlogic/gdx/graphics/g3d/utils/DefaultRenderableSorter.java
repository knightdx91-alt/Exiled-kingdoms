package com.badlogic.gdx.graphics.g3d.utils;

import a0.j;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Renderable;
import com.badlogic.gdx.graphics.g3d.attributes.BlendingAttribute;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.a;
import java.util.Comparator;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class DefaultRenderableSorter implements RenderableSorter, Comparator<Renderable> {
    private Camera camera;
    private final a tmpV1 = new a();
    private final a tmpV2 = new a();

    private a getTranslation(Matrix4 matrix4, a aVar, a aVar2) {
        if (aVar.g()) {
            matrix4.c(aVar2);
        } else {
            float[] fArr = matrix4.f1724a;
            if (j.d(fArr[0], 1.0f) && j.d(fArr[5], 1.0f) && j.d(fArr[10], 1.0f) && j.f(fArr[4]) && j.f(fArr[8]) && j.f(fArr[1]) && j.f(fArr[9]) && j.f(fArr[2]) && j.f(fArr[6])) {
                matrix4.c(aVar2);
                aVar2.b(aVar);
            } else {
                aVar2.getClass();
                aVar2.t(aVar.f1729a, aVar.f1730b, aVar.f1731c);
                aVar2.m(matrix4);
            }
        }
        return aVar2;
    }

    @Override // com.badlogic.gdx.graphics.g3d.utils.RenderableSorter
    public void sort(Camera camera, com.badlogic.gdx.utils.a<Renderable> aVar) {
        this.camera = camera;
        aVar.sort(this);
    }

    @Override // java.util.Comparator
    public int compare(Renderable renderable, Renderable renderable2) {
        Material material = renderable.material;
        long j2 = BlendingAttribute.Type;
        int i2 = 0;
        boolean z2 = material.has(j2) && ((BlendingAttribute) renderable.material.get(j2)).blended;
        if (z2 != (renderable2.material.has(j2) && ((BlendingAttribute) renderable2.material.get(j2)).blended)) {
            return z2 ? 1 : -1;
        }
        getTranslation(renderable.worldTransform, renderable.meshPart.center, this.tmpV1);
        getTranslation(renderable2.worldTransform, renderable2.meshPart.center, this.tmpV2);
        float f2 = ((int) (this.camera.position.f(this.tmpV1) * 1000.0f)) - ((int) (this.camera.position.f(this.tmpV2) * 1000.0f));
        if (f2 < 0.0f) {
            i2 = -1;
        } else if (f2 > 0.0f) {
            i2 = 1;
        }
        return z2 ? -i2 : i2;
    }
}
