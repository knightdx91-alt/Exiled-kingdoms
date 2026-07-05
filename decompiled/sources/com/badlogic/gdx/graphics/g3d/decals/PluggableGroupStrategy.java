package com.badlogic.gdx.graphics.g3d.decals;

import com.badlogic.gdx.utils.a;
import com.badlogic.gdx.utils.q;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public abstract class PluggableGroupStrategy implements GroupStrategy {
    private q<GroupPlug> plugs = new q<>();

    @Override // com.badlogic.gdx.graphics.g3d.decals.GroupStrategy
    public void afterGroup(int i2) {
        this.plugs.get(i2).afterGroup();
    }

    @Override // com.badlogic.gdx.graphics.g3d.decals.GroupStrategy
    public void beforeGroup(int i2, a<Decal> aVar) {
        this.plugs.get(i2).beforeGroup(aVar);
    }

    public void plugIn(GroupPlug groupPlug, int i2) {
        this.plugs.c(i2, groupPlug);
    }

    public GroupPlug unPlug(int i2) {
        return this.plugs.remove(i2);
    }
}
