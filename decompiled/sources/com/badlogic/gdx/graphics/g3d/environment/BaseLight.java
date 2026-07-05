package com.badlogic.gdx.graphics.g3d.environment;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g3d.environment.BaseLight;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public abstract class BaseLight<T extends BaseLight<T>> {
    public final Color color = new Color(0.0f, 0.0f, 0.0f, 1.0f);

    public T setColor(float f2, float f3, float f4, float f5) {
        this.color.set(f2, f3, f4, f5);
        return this;
    }

    public T setColor(Color color) {
        this.color.set(color);
        return this;
    }
}
