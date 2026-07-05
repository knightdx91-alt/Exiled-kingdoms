package com.badlogic.gdx.assets.loaders;

import com.badlogic.gdx.assets.loaders.p;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/* JADX INFO: compiled from: BitmapFontLoader.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class c extends b<BitmapFont, a> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    BitmapFont.BitmapFontData f1570a;

    /* JADX INFO: compiled from: BitmapFontLoader.java */
    public static class a extends r.b<BitmapFont> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public Texture.TextureFilter f1571a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public Texture.TextureFilter f1572b;

        public a() {
            Texture.TextureFilter textureFilter = Texture.TextureFilter.Nearest;
            this.f1571a = textureFilter;
            this.f1572b = textureFilter;
        }
    }

    @Override // com.badlogic.gdx.assets.loaders.a
    public final com.badlogic.gdx.utils.a getDependencies(String str, com.badlogic.gdx.files.a aVar, r.b bVar) {
        a aVar2 = (a) bVar;
        com.badlogic.gdx.utils.a aVar3 = new com.badlogic.gdx.utils.a();
        this.f1570a = new BitmapFont.BitmapFontData(aVar, false);
        for (int i2 = 0; i2 < this.f1570a.getImagePaths().length; i2++) {
            com.badlogic.gdx.files.a aVarResolve = resolve(this.f1570a.getImagePath(i2));
            p.b bVar2 = new p.b();
            if (aVar2 != null) {
                bVar2.f1594b = false;
                bVar2.f1597e = aVar2.f1571a;
                bVar2.f1598f = aVar2.f1572b;
            }
            aVar3.a(new r.a(aVarResolve, bVar2));
        }
        return aVar3;
    }

    @Override // com.badlogic.gdx.assets.loaders.b
    public final /* bridge */ /* synthetic */ void loadAsync(r.d dVar, String str, com.badlogic.gdx.files.a aVar, r.b bVar) {
    }

    @Override // com.badlogic.gdx.assets.loaders.b
    public final BitmapFont loadSync(r.d dVar, String str, com.badlogic.gdx.files.a aVar, r.b bVar) {
        int length = this.f1570a.getImagePaths().length;
        com.badlogic.gdx.utils.a aVar2 = new com.badlogic.gdx.utils.a(true, length);
        for (int i2 = 0; i2 < length; i2++) {
            aVar2.a(new TextureRegion((Texture) dVar.e(this.f1570a.getImagePath(i2), Texture.class)));
        }
        return new BitmapFont(this.f1570a, (com.badlogic.gdx.utils.a<TextureRegion>) aVar2, true);
    }
}
