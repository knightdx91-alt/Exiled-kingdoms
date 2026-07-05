package com.badlogic.gdx.graphics.g3d.decals;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.utils.a;
import com.badlogic.gdx.utils.c0;
import com.badlogic.gdx.utils.i;
import com.badlogic.gdx.utils.m0;
import java.util.Iterator;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class DecalBatch implements i {
    private static final int DEFAULT_SIZE = 1000;
    private final m0<a<Decal>> groupList;
    private final c0<a<Decal>> groupPool;
    private GroupStrategy groupStrategy;
    private Mesh mesh;
    private final a<a<Decal>> usedGroups;
    private float[] vertices;

    public DecalBatch(GroupStrategy groupStrategy) {
        this(1000, groupStrategy);
    }

    public void add(Decal decal) {
        int iDecideGroup = this.groupStrategy.decideGroup(decal);
        a<Decal> aVarObtain = this.groupList.get(iDecideGroup);
        if (aVarObtain == null) {
            aVarObtain = this.groupPool.obtain();
            aVarObtain.clear();
            this.usedGroups.a(aVarObtain);
            this.groupList.a(iDecideGroup, aVarObtain);
        }
        aVarObtain.a(decal);
    }

    protected void clear() {
        this.groupList.clear();
        this.groupPool.freeAll(this.usedGroups);
        this.usedGroups.clear();
    }

    @Override // com.badlogic.gdx.utils.i
    public void dispose() {
        clear();
        this.vertices = null;
        this.mesh.dispose();
    }

    public void flush() {
        render();
        clear();
    }

    public int getSize() {
        return this.vertices.length / 24;
    }

    public void initialize(int i2) {
        this.vertices = new float[i2 * 24];
        Mesh.VertexDataType vertexDataType = Mesh.VertexDataType.VertexArray;
        if (Gdx.gl30 != null) {
            vertexDataType = Mesh.VertexDataType.VertexBufferObjectWithVAO;
        }
        int i3 = i2 * 4;
        int i4 = i2 * 6;
        this.mesh = new Mesh(vertexDataType, false, i3, i4, new VertexAttribute(1, 3, ShaderProgram.POSITION_ATTRIBUTE), new VertexAttribute(4, 4, ShaderProgram.COLOR_ATTRIBUTE), new VertexAttribute(16, 2, "a_texCoord0"));
        short[] sArr = new short[i4];
        int i5 = 0;
        int i6 = 0;
        while (i5 < i4) {
            sArr[i5] = (short) i6;
            short s = (short) (i6 + 2);
            sArr[i5 + 1] = s;
            short s2 = (short) (i6 + 1);
            sArr[i5 + 2] = s2;
            sArr[i5 + 3] = s2;
            sArr[i5 + 4] = s;
            sArr[i5 + 5] = (short) (i6 + 3);
            i5 += 6;
            i6 += 4;
        }
        this.mesh.setIndices(sArr);
    }

    protected void render() {
        this.groupStrategy.beforeGroups();
        Iterator<m0.b<a<Decal>>> it = this.groupList.iterator();
        while (true) {
            m0.a aVar = (m0.a) it;
            if (!aVar.hasNext()) {
                this.groupStrategy.afterGroups();
                return;
            }
            m0.b bVar = (m0.b) aVar.next();
            this.groupStrategy.beforeGroup(bVar.f1845d, bVar.f1844c);
            render(this.groupStrategy.getGroupShader(bVar.f1845d), bVar.f1844c);
            this.groupStrategy.afterGroup(bVar.f1845d);
        }
    }

    public void setGroupStrategy(GroupStrategy groupStrategy) {
        this.groupStrategy = groupStrategy;
    }

    public DecalBatch(int i2, GroupStrategy groupStrategy) {
        this.groupList = new m0<>();
        this.groupPool = new c0<a<Decal>>(16) { // from class: com.badlogic.gdx.graphics.g3d.decals.DecalBatch.1
            /* JADX INFO: Access modifiers changed from: protected */
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.badlogic.gdx.utils.c0
            public a<Decal> newObject() {
                return new a<>(false, 100);
            }
        };
        this.usedGroups = new a<>(true, 16);
        initialize(i2);
        setGroupStrategy(groupStrategy);
    }

    protected void flush(ShaderProgram shaderProgram, int i2) {
        this.mesh.setVertices(this.vertices, 0, i2);
        this.mesh.render(shaderProgram, 4, 0, i2 / 4);
    }

    private void render(ShaderProgram shaderProgram, a<Decal> aVar) {
        int length;
        a.b<Decal> it = aVar.iterator();
        DecalMaterial decalMaterial = null;
        loop0: while (true) {
            length = 0;
            while (it.hasNext()) {
                Decal next = it.next();
                if (decalMaterial == null || !decalMaterial.equals(next.getMaterial())) {
                    if (length > 0) {
                        flush(shaderProgram, length);
                        length = 0;
                    }
                    next.material.set();
                    decalMaterial = next.material;
                }
                next.update();
                float[] fArr = next.vertices;
                System.arraycopy(fArr, 0, this.vertices, length, fArr.length);
                length += next.vertices.length;
                if (length == this.vertices.length) {
                    break;
                }
            }
            flush(shaderProgram, length);
        }
        if (length > 0) {
            flush(shaderProgram, length);
        }
    }
}
