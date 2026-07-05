package com.badlogic.gdx.graphics.g3d.decals;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.utils.a;
import com.badlogic.gdx.utils.l0;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class SimpleOrthoGroupStrategy implements GroupStrategy {
    private static final int GROUP_BLEND = 1;
    private static final int GROUP_OPAQUE = 0;
    private Comparator comparator = new Comparator();

    class Comparator implements java.util.Comparator<Decal> {
        Comparator() {
        }

        @Override // java.util.Comparator
        public int compare(Decal decal, Decal decal2) {
            if (decal.getZ() == decal2.getZ()) {
                return 0;
            }
            return decal.getZ() - decal2.getZ() < 0.0f ? -1 : 1;
        }
    }

    @Override // com.badlogic.gdx.graphics.g3d.decals.GroupStrategy
    public void afterGroup(int i2) {
        if (i2 == 1) {
            Gdx.gl.glDepthMask(true);
            Gdx.gl.glDisable(GL20.GL_BLEND);
        }
    }

    @Override // com.badlogic.gdx.graphics.g3d.decals.GroupStrategy
    public void afterGroups() {
        Gdx.gl.glDisable(GL20.GL_TEXTURE_2D);
    }

    @Override // com.badlogic.gdx.graphics.g3d.decals.GroupStrategy
    public void beforeGroup(int i2, a<Decal> aVar) {
        if (i2 == 1) {
            l0.a().b(aVar, this.comparator);
            Gdx.gl.glEnable(GL20.GL_BLEND);
            Gdx.gl.glDepthMask(false);
        }
    }

    @Override // com.badlogic.gdx.graphics.g3d.decals.GroupStrategy
    public void beforeGroups() {
        Gdx.gl.glEnable(GL20.GL_TEXTURE_2D);
    }

    @Override // com.badlogic.gdx.graphics.g3d.decals.GroupStrategy
    public int decideGroup(Decal decal) {
        return !decal.getMaterial().isOpaque() ? 1 : 0;
    }

    @Override // com.badlogic.gdx.graphics.g3d.decals.GroupStrategy
    public ShaderProgram getGroupShader(int i2) {
        return null;
    }
}
