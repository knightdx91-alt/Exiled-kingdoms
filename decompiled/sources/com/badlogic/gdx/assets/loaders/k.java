package com.badlogic.gdx.assets.loaders;

import com.badlogic.gdx.graphics.glutils.ShaderProgram;

/* JADX INFO: compiled from: ShaderProgramLoader.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class k extends b<ShaderProgram, a> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f1585a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private String f1586b;

    /* JADX INFO: compiled from: ShaderProgramLoader.java */
    public static class a extends r.b<ShaderProgram> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f1587a = true;
    }

    public k(com.badlogic.gdx.utils.l lVar) {
        super(lVar);
        this.f1585a = ".vert";
        this.f1586b = ".frag";
    }

    @Override // com.badlogic.gdx.assets.loaders.a
    public final /* bridge */ /* synthetic */ com.badlogic.gdx.utils.a getDependencies(String str, com.badlogic.gdx.files.a aVar, r.b bVar) {
        return null;
    }

    @Override // com.badlogic.gdx.assets.loaders.b
    public final /* bridge */ /* synthetic */ void loadAsync(r.d dVar, String str, com.badlogic.gdx.files.a aVar, r.b bVar) {
    }

    @Override // com.badlogic.gdx.assets.loaders.b
    public final ShaderProgram loadSync(r.d dVar, String str, com.badlogic.gdx.files.a aVar, r.b bVar) {
        String str2;
        a aVar2 = (a) bVar;
        String str3 = this.f1586b;
        boolean zEndsWith = str.endsWith(str3);
        String str4 = this.f1585a;
        String str5 = null;
        if (zEndsWith) {
            str2 = str.substring(0, str.length() - str3.length()) + str4;
        } else {
            str2 = null;
        }
        if (str.endsWith(str4)) {
            str5 = str.substring(0, str.length() - str4.length()) + str3;
        }
        com.badlogic.gdx.files.a aVarResolve = str2 == null ? aVar : resolve(str2);
        if (str5 != null) {
            aVar = resolve(str5);
        }
        String string = aVarResolve.readString();
        ShaderProgram shaderProgram = new ShaderProgram(string, aVarResolve.equals(aVar) ? string : aVar.readString());
        if ((aVar2 == null || aVar2.f1587a) && !shaderProgram.isCompiled()) {
            com.badlogic.gdx.utils.l lVarK = dVar.k();
            shaderProgram.getLog();
            lVarK.getClass();
        }
        return shaderProgram;
    }
}
