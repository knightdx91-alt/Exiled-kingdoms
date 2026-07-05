package com.badlogic.gdx.graphics.g3d.decals;

import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.utils.a;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public interface GroupStrategy {
    void afterGroup(int i2);

    void afterGroups();

    void beforeGroup(int i2, a<Decal> aVar);

    void beforeGroups();

    int decideGroup(Decal decal);

    ShaderProgram getGroupShader(int i2);
}
