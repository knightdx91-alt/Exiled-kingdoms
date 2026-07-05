package com.badlogic.gdx.assets.loaders;

import com.badlogic.gdx.assets.loaders.g.a;
import com.badlogic.gdx.assets.loaders.p;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.model.data.ModelData;
import com.badlogic.gdx.graphics.g3d.model.data.ModelMaterial;
import com.badlogic.gdx.graphics.g3d.model.data.ModelTexture;
import com.badlogic.gdx.graphics.g3d.utils.TextureProvider;
import com.badlogic.gdx.utils.a;
import com.badlogic.gdx.utils.y;
import java.util.Iterator;

/* JADX INFO: compiled from: ModelLoader.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public abstract class g<P extends a> extends b<Model, P> {
    protected a defaultParameters;
    protected com.badlogic.gdx.utils.a<y.b<String, ModelData>> items;

    /* JADX INFO: compiled from: ModelLoader.java */
    public static class a extends r.b<Model> {
        public p.b textureParameter;

        public a() {
            p.b bVar = new p.b();
            this.textureParameter = bVar;
            Texture.TextureFilter textureFilter = Texture.TextureFilter.Linear;
            bVar.f1598f = textureFilter;
            bVar.f1597e = textureFilter;
            Texture.TextureWrap textureWrap = Texture.TextureWrap.Repeat;
            bVar.f1600h = textureWrap;
            bVar.f1599g = textureWrap;
        }
    }

    public g(e eVar) {
        super(eVar);
        this.items = new com.badlogic.gdx.utils.a<>();
        this.defaultParameters = new a();
    }

    public Model loadModel(com.badlogic.gdx.files.a aVar, TextureProvider textureProvider, P p2) {
        ModelData modelDataLoadModelData = loadModelData(aVar, p2);
        if (modelDataLoadModelData == null) {
            return null;
        }
        return new Model(modelDataLoadModelData, textureProvider);
    }

    public ModelData loadModelData(com.badlogic.gdx.files.a aVar) {
        return loadModelData(aVar, null);
    }

    public abstract ModelData loadModelData(com.badlogic.gdx.files.a aVar, P p2);

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v1, types: [V, com.badlogic.gdx.graphics.g3d.model.data.ModelData] */
    @Override // com.badlogic.gdx.assets.loaders.a
    public com.badlogic.gdx.utils.a<r.a> getDependencies(String str, com.badlogic.gdx.files.a aVar, P p2) {
        com.badlogic.gdx.utils.a<r.a> aVar2 = new com.badlogic.gdx.utils.a<>();
        ?? LoadModelData = loadModelData(aVar, p2);
        if (LoadModelData == 0) {
            return aVar2;
        }
        y.b<String, ModelData> bVar = new y.b<>();
        bVar.f2057a = str;
        bVar.f2058b = LoadModelData;
        synchronized (this.items) {
            this.items.a(bVar);
        }
        p.b bVar2 = p2 != null ? p2.textureParameter : this.defaultParameters.textureParameter;
        a.b<ModelMaterial> it = LoadModelData.materials.iterator();
        while (it.hasNext()) {
            com.badlogic.gdx.utils.a<ModelTexture> aVar3 = it.next().textures;
            if (aVar3 != null) {
                a.b<ModelTexture> it2 = aVar3.iterator();
                while (it2.hasNext()) {
                    aVar2.a(new r.a(it2.next().fileName, Texture.class, bVar2));
                }
            }
        }
        return aVar2;
    }

    @Override // com.badlogic.gdx.assets.loaders.b
    public void loadAsync(r.d dVar, String str, com.badlogic.gdx.files.a aVar, P p2) {
    }

    @Override // com.badlogic.gdx.assets.loaders.b
    public Model loadSync(r.d dVar, String str, com.badlogic.gdx.files.a aVar, P p2) {
        ModelData modelData;
        synchronized (this.items) {
            int i2 = 0;
            modelData = null;
            while (true) {
                try {
                    com.badlogic.gdx.utils.a<y.b<String, ModelData>> aVar2 = this.items;
                    if (i2 >= aVar2.f1750b) {
                        break;
                    }
                    if (aVar2.get(i2).f2057a.equals(str)) {
                        modelData = this.items.get(i2).f2058b;
                        this.items.o(i2);
                    }
                    i2++;
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        if (modelData == null) {
            return null;
        }
        Model model = new Model(modelData, new TextureProvider.AssetTextureProvider(dVar));
        Iterator<com.badlogic.gdx.utils.i> it = model.getManagedDisposables().iterator();
        while (it.hasNext()) {
            if (it.next() instanceof Texture) {
                it.remove();
            }
        }
        return model;
    }

    public Model loadModel(com.badlogic.gdx.files.a aVar, P p2) {
        return loadModel(aVar, new TextureProvider.FileTextureProvider(), p2);
    }

    public Model loadModel(com.badlogic.gdx.files.a aVar, TextureProvider textureProvider) {
        return loadModel(aVar, textureProvider, null);
    }

    public Model loadModel(com.badlogic.gdx.files.a aVar) {
        return loadModel(aVar, new TextureProvider.FileTextureProvider(), null);
    }
}
