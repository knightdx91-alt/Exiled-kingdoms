package com.badlogic.gdx.graphics.g3d.utils.shapebuilders;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.a;
import com.badlogic.gdx.utils.k;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class BaseShapeBuilder {
    protected static final Color tmpColor0 = new Color();
    protected static final Color tmpColor1 = new Color();
    protected static final Color tmpColor2 = new Color();
    protected static final Color tmpColor3 = new Color();
    protected static final Color tmpColor4 = new Color();
    protected static final a tmpV0 = new a();
    protected static final a tmpV1 = new a();
    protected static final a tmpV2 = new a();
    protected static final a tmpV3 = new a();
    protected static final a tmpV4 = new a();
    protected static final a tmpV5 = new a();
    protected static final a tmpV6 = new a();
    protected static final a tmpV7 = new a();
    protected static final MeshPartBuilder.VertexInfo vertTmp0 = new MeshPartBuilder.VertexInfo();
    protected static final MeshPartBuilder.VertexInfo vertTmp1 = new MeshPartBuilder.VertexInfo();
    protected static final MeshPartBuilder.VertexInfo vertTmp2 = new MeshPartBuilder.VertexInfo();
    protected static final MeshPartBuilder.VertexInfo vertTmp3 = new MeshPartBuilder.VertexInfo();
    protected static final MeshPartBuilder.VertexInfo vertTmp4 = new MeshPartBuilder.VertexInfo();
    protected static final MeshPartBuilder.VertexInfo vertTmp5 = new MeshPartBuilder.VertexInfo();
    protected static final MeshPartBuilder.VertexInfo vertTmp6 = new MeshPartBuilder.VertexInfo();
    protected static final MeshPartBuilder.VertexInfo vertTmp7 = new MeshPartBuilder.VertexInfo();
    protected static final MeshPartBuilder.VertexInfo vertTmp8 = new MeshPartBuilder.VertexInfo();
    protected static final Matrix4 matTmp1 = new Matrix4();
    private static final k<a> vectorPool = new k<a>() { // from class: com.badlogic.gdx.graphics.g3d.utils.shapebuilders.BaseShapeBuilder.1
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.badlogic.gdx.utils.c0
        public a newObject() {
            return new a();
        }
    };
    private static final k<Matrix4> matrices4Pool = new k<Matrix4>() { // from class: com.badlogic.gdx.graphics.g3d.utils.shapebuilders.BaseShapeBuilder.2
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.badlogic.gdx.utils.c0
        public Matrix4 newObject() {
            return new Matrix4();
        }
    };

    protected static void freeAll() {
        vectorPool.flush();
        matrices4Pool.flush();
    }

    protected static Matrix4 obtainM4() {
        return matrices4Pool.obtain();
    }

    protected static a obtainV3() {
        return vectorPool.obtain();
    }
}
