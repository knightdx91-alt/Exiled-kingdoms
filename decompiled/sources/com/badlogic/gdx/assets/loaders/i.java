package com.badlogic.gdx.assets.loaders;

import com.badlogic.gdx.graphics.g2d.ParticleEffect;

/* JADX INFO: compiled from: ParticleEffectLoader.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class i extends n<ParticleEffect, a> {

    /* JADX INFO: compiled from: ParticleEffectLoader.java */
    public static class a extends r.b<ParticleEffect> {
    }

    @Override // com.badlogic.gdx.assets.loaders.a
    public final /* bridge */ /* synthetic */ com.badlogic.gdx.utils.a getDependencies(String str, com.badlogic.gdx.files.a aVar, r.b bVar) {
        return null;
    }

    @Override // com.badlogic.gdx.assets.loaders.n
    public final ParticleEffect load(r.d dVar, String str, com.badlogic.gdx.files.a aVar, r.b bVar) throws Throwable {
        ParticleEffect particleEffect = new ParticleEffect();
        particleEffect.load(aVar, aVar.parent());
        return particleEffect;
    }
}
