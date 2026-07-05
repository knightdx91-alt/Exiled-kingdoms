package com.badlogic.gdx.graphics.g3d.particles;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.a;
import com.badlogic.gdx.utils.m;
import com.badlogic.gdx.utils.o;
import com.badlogic.gdx.utils.t;
import com.badlogic.gdx.utils.y;
import h0.e;
import r.d;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class ResourceData<T> implements Json.b {
    private int currentLoadIndex;
    private a<SaveData> data;
    public T resource;
    a<AssetData> sharedAssets;
    private y<String, SaveData> uniqueData;

    public static class AssetData<T> implements Json.b {
        public String filename;
        public Class<T> type;

        public AssetData() {
        }

        @Override // com.badlogic.gdx.utils.Json.b
        public void read(Json json, t tVar) {
            this.filename = (String) json.readValue("filename", String.class, tVar);
            String str = (String) json.readValue("type", String.class, tVar);
            try {
                this.type = h0.a.a(str);
            } catch (e e2) {
                throw new m(a.a.A("Class not found: ", str), (Throwable) e2);
            }
        }

        @Override // com.badlogic.gdx.utils.Json.b
        public void write(Json json) {
            json.writeValue("filename", this.filename);
            json.writeValue("type", this.type.getName());
        }

        public AssetData(String str, Class<T> cls) {
            this.filename = str;
            this.type = cls;
        }
    }

    public interface Configurable<T> {
        void load(d dVar, ResourceData<T> resourceData);

        void save(d dVar, ResourceData<T> resourceData);
    }

    public ResourceData() {
        this.uniqueData = new y<>();
        this.data = new a<>(true, 3, SaveData.class);
        this.sharedAssets = new a<>();
        this.currentLoadIndex = 0;
    }

    public SaveData createSaveData() {
        SaveData saveData = new SaveData(this);
        this.data.a(saveData);
        return saveData;
    }

    <K> int getAssetData(String str, Class<K> cls) {
        a.b<AssetData> it = this.sharedAssets.iterator();
        int i2 = 0;
        while (it.hasNext()) {
            AssetData next = it.next();
            if (next.filename.equals(str) && next.type.equals(cls)) {
                return i2;
            }
            i2++;
        }
        return -1;
    }

    public a<r.a> getAssetDescriptors() {
        a<r.a> aVar = new a<>();
        a.b<AssetData> it = this.sharedAssets.iterator();
        while (it.hasNext()) {
            AssetData next = it.next();
            aVar.a(new r.a(next.filename, next.type, null));
        }
        return aVar;
    }

    public a<AssetData> getAssets() {
        return this.sharedAssets;
    }

    public SaveData getSaveData() {
        a<SaveData> aVar = this.data;
        int i2 = this.currentLoadIndex;
        this.currentLoadIndex = i2 + 1;
        return aVar.get(i2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.badlogic.gdx.utils.Json.b
    public void read(Json json, t tVar) {
        y<String, SaveData> yVar = (y) json.readValue("unique", y.class, tVar);
        this.uniqueData = yVar;
        y.a<String, SaveData> aVarB = yVar.b();
        aVarB.getClass();
        while (aVarB.hasNext()) {
            ((SaveData) aVarB.next().f2058b).resources = this;
        }
        a<SaveData> aVar = (a) json.readValue("data", (Class) a.class, SaveData.class, tVar);
        this.data = aVar;
        a.b<SaveData> it = aVar.iterator();
        while (it.hasNext()) {
            it.next().resources = this;
        }
        this.sharedAssets.b((a) json.readValue("assets", (Class) a.class, AssetData.class, tVar));
        this.resource = (T) json.readValue("resource", (Class) null, tVar);
    }

    @Override // com.badlogic.gdx.utils.Json.b
    public void write(Json json) {
        json.writeValue("unique", this.uniqueData, y.class);
        json.writeValue("data", this.data, a.class, SaveData.class);
        json.writeValue("assets", this.sharedAssets.u(AssetData.class), AssetData[].class);
        json.writeValue("resource", this.resource, (Class) null);
    }

    public SaveData getSaveData(String str) {
        return this.uniqueData.d(str);
    }

    public SaveData createSaveData(String str) {
        SaveData saveData = new SaveData(this);
        if (!this.uniqueData.a(str)) {
            this.uniqueData.j(str, saveData);
            return saveData;
        }
        throw new RuntimeException("Key already used, data must be unique, use a different key");
    }

    public static class SaveData implements Json.b {
        protected ResourceData resources;
        y<String, Object> data = new y<>();
        o assets = new o();
        private int loadIndex = 0;

        public SaveData() {
        }

        public <K> K load(String str) {
            return (K) this.data.d(str);
        }

        public r.a loadAsset() {
            int i2 = this.loadIndex;
            o oVar = this.assets;
            if (i2 == oVar.f1850b) {
                return null;
            }
            a<AssetData> aVar = this.resources.sharedAssets;
            this.loadIndex = i2 + 1;
            AssetData assetData = aVar.get(oVar.d(i2));
            return new r.a(assetData.filename, assetData.type, null);
        }

        @Override // com.badlogic.gdx.utils.Json.b
        public void read(Json json, t tVar) {
            this.data = (y) json.readValue("data", y.class, tVar);
            this.assets.b((int[]) json.readValue("indices", int[].class, tVar));
        }

        public void save(String str, Object obj) {
            this.data.j(str, obj);
        }

        public <K> void saveAsset(String str, Class<K> cls) {
            int assetData = this.resources.getAssetData(str, cls);
            if (assetData == -1) {
                this.resources.sharedAssets.a(new AssetData(str, cls));
                assetData = this.resources.sharedAssets.f1750b - 1;
            }
            this.assets.a(assetData);
        }

        @Override // com.badlogic.gdx.utils.Json.b
        public void write(Json json) {
            json.writeValue("data", this.data, y.class);
            o oVar = this.assets;
            int i2 = oVar.f1850b;
            int[] iArr = new int[i2];
            System.arraycopy(oVar.f1849a, 0, iArr, 0, i2);
            json.writeValue("indices", iArr, int[].class);
        }

        public SaveData(ResourceData resourceData) {
            this.resources = resourceData;
        }
    }

    public ResourceData(T t2) {
        this();
        this.resource = t2;
    }
}
