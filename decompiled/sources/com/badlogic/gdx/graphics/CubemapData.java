package com.badlogic.gdx.graphics;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public interface CubemapData {
    void consumeCubemapData();

    int getHeight();

    int getWidth();

    boolean isManaged();

    boolean isPrepared();

    void prepare();
}
