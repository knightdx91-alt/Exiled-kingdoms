package com.badlogic.gdx.graphics.g2d;

import a0.d;
import com.badlogic.gdx.assets.loaders.e;
import com.badlogic.gdx.assets.loaders.n;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.a;
import com.badlogic.gdx.utils.j0;
import com.badlogic.gdx.utils.l;
import com.badlogic.gdx.utils.m;
import com.badlogic.gdx.utils.n0;
import java.io.BufferedReader;
import java.io.IOException;
import r.b;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class PolygonRegionLoader extends n<PolygonRegion, PolygonRegionParameters> {
    private PolygonRegionParameters defaultParameters;
    private d triangulator;

    public static class PolygonRegionParameters extends b<PolygonRegion> {
        public String texturePrefix = "i ";
        public int readerBuffer = GL20.GL_STENCIL_BUFFER_BIT;
        public String[] textureExtensions = {"png", "PNG", "jpeg", "JPEG", "jpg", "JPG", "cim", "CIM", "etc1", "ETC1", "ktx", "KTX", "zktx", "ZKTX"};
    }

    public PolygonRegionLoader() {
        this(new l());
    }

    @Override // com.badlogic.gdx.assets.loaders.a
    public a<r.a> getDependencies(String str, com.badlogic.gdx.files.a aVar, PolygonRegionParameters polygonRegionParameters) {
        String strName;
        String[] strArr;
        if (polygonRegionParameters == null) {
            polygonRegionParameters = this.defaultParameters;
        }
        try {
            BufferedReader erVar = aVar.reader(polygonRegionParameters.readerBuffer);
            while (true) {
                String line = erVar.readLine();
                if (line == null) {
                    strName = null;
                    break;
                }
                if (line.startsWith(polygonRegionParameters.texturePrefix)) {
                    strName = line.substring(polygonRegionParameters.texturePrefix.length());
                    break;
                }
            }
            erVar.close();
            if (strName == null && (strArr = polygonRegionParameters.textureExtensions) != null) {
                for (String str2 : strArr) {
                    com.badlogic.gdx.files.a aVarSibling = aVar.sibling(aVar.nameWithoutExtension().concat("." + str2));
                    if (aVarSibling.exists()) {
                        strName = aVarSibling.name();
                    }
                }
            }
            if (strName == null) {
                return null;
            }
            a<r.a> aVar2 = new a<>(true, 1);
            aVar2.a(new r.a(aVar.sibling(strName), null));
            return aVar2;
        } catch (IOException e2) {
            throw new m(a.a.A("Error reading ", str), (Throwable) e2);
        }
    }

    @Override // com.badlogic.gdx.assets.loaders.n
    public PolygonRegion load(r.d dVar, String str, com.badlogic.gdx.files.a aVar, PolygonRegionParameters polygonRegionParameters) {
        return load(new TextureRegion((Texture) dVar.d(dVar.i(str).g())), aVar);
    }

    public PolygonRegionLoader(e eVar) {
        super(eVar);
        this.defaultParameters = new PolygonRegionParameters();
        this.triangulator = new d();
    }

    public PolygonRegion load(TextureRegion textureRegion, com.badlogic.gdx.files.a aVar) {
        String line;
        BufferedReader erVar = aVar.reader(256);
        do {
            try {
                try {
                    line = erVar.readLine();
                    if (line == null) {
                        n0.a(erVar);
                        throw new m(a.a.i(aVar, "Polygon shape not found: "));
                    }
                } catch (IOException e2) {
                    throw new m("Error reading polygon shape file: " + aVar, (Throwable) e2);
                }
            } catch (Throwable th) {
                n0.a(erVar);
                throw th;
            }
            n0.a(erVar);
            throw th;
        } while (!line.startsWith("s"));
        String[] strArrSplit = line.substring(1).trim().split(",");
        int length = strArrSplit.length;
        float[] fArr = new float[length];
        for (int i2 = 0; i2 < length; i2++) {
            fArr[i2] = Float.parseFloat(strArrSplit[i2]);
        }
        j0 j0VarC = this.triangulator.c(fArr);
        int i3 = j0VarC.f1827b;
        short[] sArr = new short[i3];
        System.arraycopy(j0VarC.f1826a, 0, sArr, 0, i3);
        PolygonRegion polygonRegion = new PolygonRegion(textureRegion, fArr, sArr);
        n0.a(erVar);
        return polygonRegion;
    }
}
