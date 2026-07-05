package com.badlogic.gdx.graphics.g3d;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.model.MeshPart;
import com.badlogic.gdx.graphics.g3d.utils.MeshBuilder;
import com.badlogic.gdx.graphics.g3d.utils.RenderableSorter;
import com.badlogic.gdx.utils.a;
import com.badlogic.gdx.utils.c0;
import com.badlogic.gdx.utils.i;
import com.badlogic.gdx.utils.k;
import com.badlogic.gdx.utils.m;
import java.util.Comparator;
import java.util.Iterator;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class ModelCache implements i, RenderableProvider {
    private boolean building;
    private Camera camera;
    private a<Renderable> items;
    private MeshBuilder meshBuilder;
    private k<MeshPart> meshPartPool;
    private MeshPool meshPool;
    private a<Renderable> renderables;
    private k<Renderable> renderablesPool;
    private RenderableSorter sorter;
    private a<Renderable> tmp;

    public interface MeshPool extends i {
        @Override // com.badlogic.gdx.utils.i
        /* synthetic */ void dispose();

        void flush();

        Mesh obtain(VertexAttributes vertexAttributes, int i2, int i3);
    }

    public static class SimpleMeshPool implements MeshPool {
        private a<Mesh> freeMeshes = new a<>();
        private a<Mesh> usedMeshes = new a<>();

        @Override // com.badlogic.gdx.graphics.g3d.ModelCache.MeshPool, com.badlogic.gdx.utils.i
        public void dispose() {
            a.b<Mesh> it = this.usedMeshes.iterator();
            while (it.hasNext()) {
                it.next().dispose();
            }
            this.usedMeshes.clear();
            a.b<Mesh> it2 = this.freeMeshes.iterator();
            while (it2.hasNext()) {
                it2.next().dispose();
            }
            this.freeMeshes.clear();
        }

        @Override // com.badlogic.gdx.graphics.g3d.ModelCache.MeshPool
        public void flush() {
            this.freeMeshes.b(this.usedMeshes);
            this.usedMeshes.clear();
        }

        @Override // com.badlogic.gdx.graphics.g3d.ModelCache.MeshPool
        public Mesh obtain(VertexAttributes vertexAttributes, int i2, int i3) {
            int i4 = this.freeMeshes.f1750b;
            for (int i5 = 0; i5 < i4; i5++) {
                Mesh mesh = this.freeMeshes.get(i5);
                if (mesh.getVertexAttributes().equals(vertexAttributes) && mesh.getMaxVertices() >= i2 && mesh.getMaxIndices() >= i3) {
                    this.freeMeshes.o(i5);
                    this.usedMeshes.a(mesh);
                    return mesh;
                }
            }
            Mesh mesh2 = new Mesh(false, 65536, Math.max(65536, 1 << (32 - Integer.numberOfLeadingZeros(i3 - 1))), vertexAttributes);
            this.usedMeshes.a(mesh2);
            return mesh2;
        }
    }

    public static class Sorter implements RenderableSorter, Comparator<Renderable> {
        @Override // com.badlogic.gdx.graphics.g3d.utils.RenderableSorter
        public void sort(Camera camera, a<Renderable> aVar) {
            aVar.sort(this);
        }

        @Override // java.util.Comparator
        public int compare(Renderable renderable, Renderable renderable2) {
            int iCompareTo = renderable.meshPart.mesh.getVertexAttributes().compareTo(renderable2.meshPart.mesh.getVertexAttributes());
            return (iCompareTo == 0 && (iCompareTo = renderable.material.compareTo((Attributes) renderable2.material)) == 0) ? renderable.meshPart.primitiveType - renderable2.meshPart.primitiveType : iCompareTo;
        }
    }

    public static class TightMeshPool implements MeshPool {
        private a<Mesh> freeMeshes = new a<>();
        private a<Mesh> usedMeshes = new a<>();

        @Override // com.badlogic.gdx.graphics.g3d.ModelCache.MeshPool, com.badlogic.gdx.utils.i
        public void dispose() {
            a.b<Mesh> it = this.usedMeshes.iterator();
            while (it.hasNext()) {
                it.next().dispose();
            }
            this.usedMeshes.clear();
            a.b<Mesh> it2 = this.freeMeshes.iterator();
            while (it2.hasNext()) {
                it2.next().dispose();
            }
            this.freeMeshes.clear();
        }

        @Override // com.badlogic.gdx.graphics.g3d.ModelCache.MeshPool
        public void flush() {
            this.freeMeshes.b(this.usedMeshes);
            this.usedMeshes.clear();
        }

        @Override // com.badlogic.gdx.graphics.g3d.ModelCache.MeshPool
        public Mesh obtain(VertexAttributes vertexAttributes, int i2, int i3) {
            int i4 = this.freeMeshes.f1750b;
            for (int i5 = 0; i5 < i4; i5++) {
                Mesh mesh = this.freeMeshes.get(i5);
                if (mesh.getVertexAttributes().equals(vertexAttributes) && mesh.getMaxVertices() == i2 && mesh.getMaxIndices() == i3) {
                    this.freeMeshes.o(i5);
                    this.usedMeshes.a(mesh);
                    return mesh;
                }
            }
            Mesh mesh2 = new Mesh(true, i2, i3, vertexAttributes);
            this.usedMeshes.a(mesh2);
            return mesh2;
        }
    }

    public ModelCache() {
        this(new Sorter(), new SimpleMeshPool());
    }

    private Renderable obtainRenderable(Material material, int i2) {
        Renderable renderableObtain = this.renderablesPool.obtain();
        renderableObtain.bones = null;
        renderableObtain.environment = null;
        renderableObtain.material = material;
        MeshPart meshPart = renderableObtain.meshPart;
        meshPart.mesh = null;
        meshPart.offset = 0;
        meshPart.size = 0;
        meshPart.primitiveType = i2;
        meshPart.center.t(0.0f, 0.0f, 0.0f);
        renderableObtain.meshPart.halfExtents.t(0.0f, 0.0f, 0.0f);
        renderableObtain.meshPart.radius = -1.0f;
        renderableObtain.shader = null;
        renderableObtain.userData = null;
        renderableObtain.worldTransform.d();
        return renderableObtain;
    }

    public void add(Renderable renderable) {
        if (!this.building) {
            throw new m("Can only add items to the ModelCache in between .begin() and .end()");
        }
        if (renderable.bones == null) {
            this.items.a(renderable);
        } else {
            this.renderables.a(renderable);
        }
    }

    public void begin() {
        begin(null);
    }

    @Override // com.badlogic.gdx.utils.i
    public void dispose() {
        if (this.building) {
            throw new m("Cannot dispose a ModelCache in between .begin() and .end()");
        }
        this.meshPool.dispose();
    }

    public void end() {
        if (!this.building) {
            throw new m("Call begin() prior to calling end()");
        }
        this.building = false;
        a<Renderable> aVar = this.items;
        if (aVar.f1750b == 0) {
            return;
        }
        this.sorter.sort(this.camera, aVar);
        a<Renderable> aVar2 = this.items;
        int i2 = aVar2.f1750b;
        int i3 = this.renderables.f1750b;
        Renderable renderable = aVar2.get(0);
        VertexAttributes vertexAttributes = renderable.meshPart.mesh.getVertexAttributes();
        Material material = renderable.material;
        int i4 = renderable.meshPart.primitiveType;
        int i5 = this.renderables.f1750b;
        this.meshBuilder.begin(vertexAttributes);
        MeshPart meshPartPart = this.meshBuilder.part("", i4, this.meshPartPool.obtain());
        this.renderables.a(obtainRenderable(material, i4));
        int i6 = this.items.f1750b;
        for (int i7 = 0; i7 < i6; i7++) {
            Renderable renderable2 = this.items.get(i7);
            VertexAttributes vertexAttributes2 = renderable2.meshPart.mesh.getVertexAttributes();
            Material material2 = renderable2.material;
            int i8 = renderable2.meshPart.primitiveType;
            boolean z2 = vertexAttributes2.equals(vertexAttributes) && (this.meshBuilder.getNumVertices() + (renderable2.meshPart.mesh.getNumIndices() > 0 ? renderable2.meshPart.mesh.getNumVertices() : renderable2.meshPart.size) <= 65536);
            if (!z2 || i8 != i4 || !material2.same(material, true)) {
                if (!z2) {
                    MeshBuilder meshBuilder = this.meshBuilder;
                    Mesh meshEnd = meshBuilder.end(this.meshPool.obtain(vertexAttributes, meshBuilder.getNumVertices(), this.meshBuilder.getNumIndices()));
                    while (true) {
                        a<Renderable> aVar3 = this.renderables;
                        if (i5 >= aVar3.f1750b) {
                            break;
                        }
                        aVar3.get(i5).meshPart.mesh = meshEnd;
                        i5++;
                    }
                    this.meshBuilder.begin(vertexAttributes2);
                    vertexAttributes = vertexAttributes2;
                }
                MeshPart meshPartPart2 = this.meshBuilder.part("", i8, this.meshPartPool.obtain());
                a<Renderable> aVar4 = this.renderables;
                MeshPart meshPart = aVar4.get(aVar4.f1750b - 1).meshPart;
                meshPart.offset = meshPartPart.offset;
                meshPart.size = meshPartPart.size;
                this.renderables.a(obtainRenderable(material2, i8));
                meshPartPart = meshPartPart2;
                material = material2;
                i4 = i8;
            }
            this.meshBuilder.setVertexTransform(renderable2.worldTransform);
            MeshBuilder meshBuilder2 = this.meshBuilder;
            MeshPart meshPart2 = renderable2.meshPart;
            meshBuilder2.addMesh(meshPart2.mesh, meshPart2.offset, meshPart2.size);
        }
        MeshBuilder meshBuilder3 = this.meshBuilder;
        Mesh meshEnd2 = meshBuilder3.end(this.meshPool.obtain(vertexAttributes, meshBuilder3.getNumVertices(), this.meshBuilder.getNumIndices()));
        while (true) {
            a<Renderable> aVar5 = this.renderables;
            int i9 = aVar5.f1750b;
            if (i5 >= i9) {
                MeshPart meshPart3 = aVar5.get(i9 - 1).meshPart;
                meshPart3.offset = meshPartPart.offset;
                meshPart3.size = meshPartPart.size;
                return;
            }
            aVar5.get(i5).meshPart.mesh = meshEnd2;
            i5++;
        }
    }

    @Override // com.badlogic.gdx.graphics.g3d.RenderableProvider
    public void getRenderables(a<Renderable> aVar, c0<Renderable> c0Var) {
        if (this.building) {
            throw new m("Cannot render a ModelCache in between .begin() and .end()");
        }
        a.b<Renderable> it = this.renderables.iterator();
        while (it.hasNext()) {
            Renderable next = it.next();
            next.shader = null;
            next.environment = null;
        }
        aVar.b(this.renderables);
    }

    public ModelCache(RenderableSorter renderableSorter, MeshPool meshPool) {
        this.renderables = new a<>();
        this.renderablesPool = new k<Renderable>() { // from class: com.badlogic.gdx.graphics.g3d.ModelCache.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.badlogic.gdx.utils.c0
            public Renderable newObject() {
                return new Renderable();
            }
        };
        this.meshPartPool = new k<MeshPart>() { // from class: com.badlogic.gdx.graphics.g3d.ModelCache.2
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.badlogic.gdx.utils.c0
            public MeshPart newObject() {
                return new MeshPart();
            }
        };
        this.items = new a<>();
        this.tmp = new a<>();
        this.sorter = renderableSorter;
        this.meshPool = meshPool;
        this.meshBuilder = new MeshBuilder();
    }

    public void begin(Camera camera) {
        if (this.building) {
            throw new m("Call end() after calling begin()");
        }
        this.building = true;
        this.camera = camera;
        this.renderablesPool.flush();
        this.renderables.clear();
        this.items.clear();
        this.meshPartPool.flush();
        this.meshPool.flush();
    }

    public void add(RenderableProvider renderableProvider) {
        renderableProvider.getRenderables(this.tmp, this.renderablesPool);
        int i2 = this.tmp.f1750b;
        for (int i3 = 0; i3 < i2; i3++) {
            add(this.tmp.get(i3));
        }
        this.tmp.clear();
    }

    public <T extends RenderableProvider> void add(Iterable<T> iterable) {
        Iterator<T> it = iterable.iterator();
        while (it.hasNext()) {
            add(it.next());
        }
    }
}
