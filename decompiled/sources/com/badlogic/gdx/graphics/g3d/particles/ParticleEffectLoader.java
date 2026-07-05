package com.badlogic.gdx.graphics.g3d.particles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.loaders.b;
import com.badlogic.gdx.assets.loaders.e;
import com.badlogic.gdx.graphics.g3d.particles.ResourceData;
import com.badlogic.gdx.graphics.g3d.particles.batches.ParticleBatch;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.a;
import com.badlogic.gdx.utils.y;
import r.d;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class ParticleEffectLoader extends b<ParticleEffect, ParticleEffectLoadParameter> {
    protected a<y.b<String, ResourceData<ParticleEffect>>> items;

    public static class ParticleEffectLoadParameter extends r.b<ParticleEffect> {
        a<ParticleBatch<?>> batches;

        public ParticleEffectLoadParameter(a<ParticleBatch<?>> aVar) {
            this.batches = aVar;
        }
    }

    public static class ParticleEffectSaveParameter extends r.b<ParticleEffect> {
        a<ParticleBatch<?>> batches;
        com.badlogic.gdx.files.a file;
        d manager;

        public ParticleEffectSaveParameter(com.badlogic.gdx.files.a aVar, d dVar, a<ParticleBatch<?>> aVar2) {
            this.batches = aVar2;
            this.file = aVar;
            this.manager = dVar;
        }
    }

    public ParticleEffectLoader(e eVar) {
        super(eVar);
        this.items = new a<>();
    }

    private <T> T find(a<?> aVar, Class<T> cls) {
        a.b<?> it = aVar.iterator();
        while (it.hasNext()) {
            T t2 = (T) it.next();
            if (cls.isAssignableFrom(t2.getClass())) {
                return t2;
            }
        }
        return null;
    }

    public void save(ParticleEffect particleEffect, ParticleEffectSaveParameter particleEffectSaveParameter) {
        ResourceData resourceData = new ResourceData(particleEffect);
        particleEffect.save(particleEffectSaveParameter.manager, resourceData);
        a<ParticleBatch<?>> aVar = particleEffectSaveParameter.batches;
        if (aVar != null) {
            a.b<ParticleBatch<?>> it = aVar.iterator();
            while (it.hasNext()) {
                ParticleBatch<?> next = it.next();
                a.b<ParticleController> it2 = particleEffect.getControllers().iterator();
                while (true) {
                    if (!it2.hasNext()) {
                        break;
                    } else if (it2.next().renderer.isCompatible(next)) {
                        next.save(particleEffectSaveParameter.manager, resourceData);
                        break;
                    }
                }
            }
        }
        new Json().toJson(resourceData, particleEffectSaveParameter.file);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v2, types: [V, com.badlogic.gdx.graphics.g3d.particles.ResourceData] */
    @Override // com.badlogic.gdx.assets.loaders.a
    public a<r.a> getDependencies(String str, com.badlogic.gdx.files.a aVar, ParticleEffectLoadParameter particleEffectLoadParameter) {
        a<ResourceData.AssetData> assets;
        ?? r02 = (ResourceData) new Json().fromJson(ResourceData.class, aVar);
        synchronized (this.items) {
            y.b<String, ResourceData<ParticleEffect>> bVar = new y.b<>();
            bVar.f2057a = str;
            bVar.f2058b = r02;
            this.items.a(bVar);
            assets = r02.getAssets();
        }
        a<r.a> aVar2 = new a<>();
        a.b<ResourceData.AssetData> it = assets.iterator();
        while (it.hasNext()) {
            ResourceData.AssetData next = it.next();
            if (!resolve(next.filename).exists()) {
                next.filename = aVar.parent().child(Gdx.files.internal(next.filename).name()).path();
            }
            Class<T> cls = next.type;
            if (cls == ParticleEffect.class) {
                aVar2.a(new r.a(next.filename, cls, particleEffectLoadParameter));
            } else {
                aVar2.a(new r.a(next.filename, cls, null));
            }
        }
        return aVar2;
    }

    @Override // com.badlogic.gdx.assets.loaders.b
    public void loadAsync(d dVar, String str, com.badlogic.gdx.files.a aVar, ParticleEffectLoadParameter particleEffectLoadParameter) {
    }

    @Override // com.badlogic.gdx.assets.loaders.b
    public ParticleEffect loadSync(d dVar, String str, com.badlogic.gdx.files.a aVar, ParticleEffectLoadParameter particleEffectLoadParameter) {
        ResourceData<ParticleEffect> resourceData;
        synchronized (this.items) {
            int i2 = 0;
            while (true) {
                try {
                    a<y.b<String, ResourceData<ParticleEffect>>> aVar2 = this.items;
                    if (i2 >= aVar2.f1750b) {
                        resourceData = null;
                        break;
                    }
                    y.b<String, ResourceData<ParticleEffect>> bVar = aVar2.get(i2);
                    if (bVar.f2057a.equals(str)) {
                        resourceData = bVar.f2058b;
                        this.items.o(i2);
                        break;
                    }
                    i2++;
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        resourceData.resource.load(dVar, resourceData);
        if (particleEffectLoadParameter != null) {
            a<ParticleBatch<?>> aVar3 = particleEffectLoadParameter.batches;
            if (aVar3 != null) {
                a.b<ParticleBatch<?>> it = aVar3.iterator();
                while (it.hasNext()) {
                    it.next().load(dVar, resourceData);
                }
            }
            resourceData.resource.setBatch(particleEffectLoadParameter.batches);
        }
        return resourceData.resource;
    }
}
