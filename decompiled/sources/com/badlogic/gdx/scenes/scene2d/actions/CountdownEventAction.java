package com.badlogic.gdx.scenes.scene2d.actions;

import com.badlogic.gdx.scenes.scene2d.Event;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class CountdownEventAction<T extends Event> extends EventAction<T> {
    int count;
    int current;

    public CountdownEventAction(Class<? extends T> cls, int i2) {
        super(cls);
        this.count = i2;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.actions.EventAction
    public boolean handle(T t2) {
        int i2 = this.current + 1;
        this.current = i2;
        return i2 >= this.count;
    }
}
